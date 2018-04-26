angular.module('demo', [])
    .controller('Hello', function($scope, $http) {
        $http.get('/gameSearch?query=mario').
        then(function(response) {
            $scope.greeting = response.data;
        });
});