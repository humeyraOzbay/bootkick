function Hello($scope, $http) {
    $http.get('http://localhost:9000/rest/id/1').
        success(function(data) {
            $scope.greeting = data;
        });
}