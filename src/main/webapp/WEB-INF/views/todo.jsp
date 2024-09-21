<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>MiniProject</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css">
    <script src="https://cdn.tailwindcss.com"></script>
    <link href="https://cdn.jsdelivr.net/npm/flowbite@2.5.1/dist/flowbite.min.css" rel="stylesheet" />
    <script src="https://cdn.jsdelivr.net/npm/flowbite@2.5.1/dist/flowbite.min.js"></script>

</head>
<body>
    <h1 class="text-3xl text-center pt-5 pb-5 font-bold">TODO List</h1>
    <h3 class="text-1xl text-center pb-5">모든 할 일 ${todoList.size()}개  /  남은 할 일 ${todoList.stream().filter(item -> item.isCompleted()).count()}개</h3>


    <form name = "itemForm" class="max-w-md mx-auto" onsubmit="javascipt:addItem(event)">
        <label for="todo-content" class="mb-2 text-sm font-medium text-gray-900 sr-only dark:text-white">Todo Content</label>
        <div class="relative">
            <div class="absolute inset-y-0 start-0 flex items-center ps-3 pointer-events-none">
                <svg class="w-4 h-4 text-gray-500 dark:text-gray-400" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 20 20">
                    <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="m19 19-4-4m0-7A7 7 0 1 1 1 8a7 7 0 0 1 14 0Z"/>
                </svg>
            </div>
            <input name="content" type="search" id="todo-content" class="block w-full p-4 ps-10 text-sm text-gray-900 border border-gray-300 rounded-lg bg-gray-50 focus:ring-blue-500 focus:border-blue-500 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" placeholder="오늘의 할 일은?" required />
            <button class="text-white absolute end-2.5 bottom-2.5 bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-4 py-2 dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">+</button>
            <input name="id" type="hidden"/>
            <input name="completed" type="hidden"/>
        </div>
    </form>


    <div class="fixed top-0 left-0 w-56 max-w-sm p-6 bg-white border border-gray-200 rounded-lg shadow dark:bg-gray-800 dark:border-gray-700 mx-auto">
        <a href="#">
            <h5 class="mb-2 text-2xl font-bold tracking-tight text-gray-900 dark:text-white">Noteworthy technology acquisitions 2021</h5>
        </a>
        <p class="mb-3 font-normal text-gray-700 dark:text-gray-400">Here are the biggest enterprise technology acquisitions of 2021 so far, in reverse chronological order.</p>
        <a href="#" class="inline-flex items-center px-3 py-2 text-sm font-medium text-center text-white bg-blue-700 rounded-lg hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">
            Read more
            <svg class="rtl:rotate-180 w-3.5 h-3.5 ms-2" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 14 10">
                <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M1 5h12m0 0L9 1m4 4L9 9"/>
            </svg>
        </a>
    </div>


    <ul class="max-w-md divide-y divide-gray-200 dark:divide-gray-700 mx-auto">
        <c:forEach var ="todoItem" items="${todoList}">
            <li class="pt-3 pb-3 sm:pt-4">
                <div class="flex items-center space-x-4 rtl:space-x-reverse">
                    <div class="flex-shrink-0" onclick="updateItem(${todoItem.id}, '${todoItem.content}', ${todoItem.completed})">
                        <c:if test ="${todoItem.completed}">
                        <i class="fa-solid fa-check text-2xl cursor-pointer text-green-500"></i>
                        </c:if>
                        <c:if test = "${!todoItem.completed}">
                            <i class="fa-solid fa-check text-2xl cursor-pointer text-neutral-300"></i>
                        </c:if>
                    </div>
                    <div class="flex-1 min-w-0">
                        <p class="text-sm font-medium text-gray-900 truncate dark:text-white">
                            ${todoItem.content}
                        </p>
                    </div>
                    <div class="inline-flex items-center text-base font-semibold text-gray-900 dark:text-white">
                        <i class="fa-solid fa-circle-xmark cursor-pointer text-neutral-300" onclick="deleteItem(${todoItem.id})"></i>
                    </div>
                </div>
            </li>
        </c:forEach>
    </ul>

    <script>
        function addItem(event) {
            event.preventDefault();
            const $form = document.itemForm;

            $form.method = "POST";
            $form.action = "/todo/addItem";
            $form.id.value = 0;
            $form.completed.value = false;
            $form.submit();
        }

        function updateItem(itemId, itemContent, itemCompleted) {
            console.log("0-0", itemId, itemCompleted);
            const $form = document.itemForm;

            $form.method = "POST";
            $form.id.value = itemId;
            $form.content.value = "";
            $form.completed.value = !itemCompleted;
            $form.action = "/todo/updateItem";
            $form.submit();

        }

        function deleteItem(itemId) {
            console.log("0-0", itemId);
            const $form = document.itemForm;

            $form.method = "POST";
            $form.id.value = itemId;
            $form.completed.value = false;
            $form.action = "/todo/deleteItem";
            $form.submit();
        }
    </script>


</body>
</html>