/*
 * managing all the modules used in the web application
 */

var app = angular.module('app',['ui.router','ngMessages',
	'customerModule',
	'orderModule',
	'paymentModule',
	'staffModule',
	'app.common',
	'adminModule'
]);

app.run(['$rootScope', function($rootScope){
	console.log("Journey started");
}]);
	