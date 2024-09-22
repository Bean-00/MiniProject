let $modal

const createTodoList = (todoList) => {
    const $todoListUl = $("#todo-list")
    let html = ''
    for (let item of todoList) {
        html += `
       <li class="pt-3 pb-3 sm:pt-4">
            <div class="flex items-center space-x-4 rtl:space-x-reverse">
                <div class="flex-shrink-0" onclick="updateItem(${item.id}, ${item.completed})">
                    <i class="fa-solid fa-check text-2xl cursor-pointer ${item.completed ? 'text-green-500' : 'text-neutral-300'}"></i>
                </div>
                <div class="flex-1 min-w-0">
                    <p class="text-sm font-medium text-gray-900 truncate dark:text-white">
                        ${item.content}
                    </p>
                </div>
                <div class="inline-flex items-center text-base font-semibold text-gray-900 dark:text-white">
                    <i class="fa-solid fa-circle-xmark cursor-pointer text-neutral-300" onclick="deleteItem(${item.id})"></i>
                </div>
            </div>
       </li>`
       $todoListUl.html(html)
    }
}

const createTodoCountElement = (todoList) => {
    const $todoCountEl = $('#todo-count')
    if (!$todoCountEl) return

    const completeCount = todoList.filter(item => item.completed).length

    $todoCountEl.text(`모든 할 일 ${todoList.length}개  /  남은 할 일 ${completeCount}개`)
    console.debug(completeCount)
}

const getTodoList = () => {
    sendGetAjax('/api/todo-items', (todoList) => {
        console.debug("res.target.status: ", todoList)
        createTodoCountElement(todoList)
        createTodoList(todoList)
    })
}

const addItem = (event) => {
    event.preventDefault()
    const $el = $('#todo-content')
    const content = $el.val()
    sendPostAjax('/api/todo-items', {content: content}, () => {
        $el.val('')
        getTodoList()
    })
}

const updateItem = (id, completed) => {
    sendPutAjax(`/api/todo-items/${id}`, {completed: !completed}, getTodoList)
}

const deleteItem = (id) => {
    const $modalBtn = $('#modal-btn')
    const $deleteBtn = $('#delete-btn')
    const listener = event => {
        console.debug("event", event)
        sendDeleteAjax(`/api/todo-items/${id}`, getTodoList)
        $deleteBtn.off('click')
    }
    $modalBtn.click()
    $deleteBtn.on('click', listener)
}

const sendGetAjax = (url, successCallback) => {
    sendAjax(url, 'GET', null, 'json', successCallback)
}

const sendPostAjax = (url, body, successCallback) => {
    sendAjax(url, 'POST', body, 'text', successCallback)
}

const sendPutAjax = (url, body, successCallback) => {
    sendAjax(url, 'PUT', body, 'text', successCallback)
}

const sendDeleteAjax = (url, successCallback) => {
    sendAjax(url, 'DELETE', null, 'text', successCallback)
}

const sendAjax = (url, method, body, dataType, successCallback) => {
    //contentType: request body type, dataType: response body type
    $.ajax({
        url,
        type: method,
        data: JSON.stringify(body),
        contentType: 'application/json',
        dataType: dataType,
        success: successCallback,
        error(xhr, status, error) {
            console.error("ERROR !", error)
        }
    })
}


$(document).ready(() => {
    getTodoList()
})
