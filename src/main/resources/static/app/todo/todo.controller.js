(function () {
    'use strict';
    function mainController($uibModal, todoService, $timeout) {
        var vm = this;
        vm.todosInput = {};
        vm.todos = [];

        activate();

        function activate() {
            return getTodoData().then(function () {
                console.log("View Activated")
            });
        }

        function getTodoData() {
            return todoService.getTodo()
                .then(function (data) {
                    vm.todos = data;
                    return vm.todos;
                });
        }

        // Todo item click to update task status

        vm.todoClicked = function (index) {
            vm.todosInput = {};

            if (vm.todos[index].status == 'In Progress') {
                vm.todosInput = {
                    id: vm.todos[index].id,
                    isCompleted: true,
                    name: vm.todos[index].name,
                    priority: vm.todos[index].priority,
                    status: "Completed"
                }

                updateItem();

                function updateItem() {
                    console.log(JSON.stringify(vm.todosInput));
                    todoService.updateTodo(vm.todosInput, vm.todos[index].id);
                }
                $timeout(function () {
                    activate();
                }, 500);
            }
            else {

                vm.todosInput = {
                    id: vm.todos[index].id,
                    isCompleted: false,
                    name: vm.todos[index].name,
                    priority: vm.todos[index].priority,
                    status: "In Progress"
                }
                updateItem();
                function updateItem() {
                    todoService.updateTodo(vm.todosInput, vm.todos[index].id);
                }
                $timeout(function () {
                    activate();
                }, 500);
            }
        }

        // ui modal functionality 
        vm.animationsEnabled = true;
        vm.openModal = function () {
            var modalInstance = $uibModal.open(
                {
                    animation: vm.animationsEnabled,
                    templateUrl: 'app/todo/modal.html',
                    controller: 'modalInstanceCtrl',
                    controllerAs: 'vm',
                    resolve: {
                        taskName: function () {
                            return vm.taskName;
                        }
                        ,
                        taskPriority: function () {
                            return vm.taskPriority;
                        }
                        ,
                        taskStatus: function () {
                            return vm.taskStatus;
                        }
                    }
                }
            );


            modalInstance.result.then(function (ele) {
                vm.todosInput = {
                    id: "0",
                    isCompleted: false,
                    name: ele.taskName,
                    priority: ele.taskPriority,
                    status: ele.taskStatus
                }
                todoService.postTodo(vm.todosInput);
                $timeout(function () {
                    activate();
                }, 500);
                console.log(JSON.stringify(vm.todosInput));
            }, function () {
                console.log("Model dimiss invoked");
            });
        };
    }

    angular.module('mainApp')
        .controller('mainCtrl', mainController);
    mainController.$inject = ['$uibModal', 'todoService', '$timeout'];
})();

(function () {
    'use strict';
    function modalInstanceCtrl($uibModalInstance, taskName, taskPriority, taskStatus) {
        var vm = this;
        vm.taskName = taskName;
        vm.taskPriority = taskPriority;
        vm.taskStatus = "In Progress";
        vm.ok = function () {
            $uibModalInstance.close({
                taskName: this.taskName,
                taskPriority: this.taskPriority,
                taskStatus: this.taskStatus
            });
        };
        vm.cancel = function () {
            $uibModalInstance.dismiss('cancel');
        };
    };
    angular.module('mainApp')
        .controller('modalInstanceCtrl', modalInstanceCtrl);
})();