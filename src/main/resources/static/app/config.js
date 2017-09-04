(function () {
    'use strict';

    function routeConfig($stateProvider, $urlRouterProvider) {
        $urlRouterProvider.otherwise('/home');
        $stateProvider.state('root', {
            abstract: true,
            url: ''
        })
            .state('root.home', {
                url: '/home',
                views: {
                    'content@': {
                        templateUrl: 'app/todo/todo.html',
                        controller: 'mainCtrl',
                        controllerAs: 'vm'
                    }
                }
            });
    };

    angular.module('mainApp')
        .config(routeConfig);
})();