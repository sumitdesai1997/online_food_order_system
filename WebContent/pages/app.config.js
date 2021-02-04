app.config(['$stateProvider', '$urlRouterProvider','$locationProvider', function ($stateProvider, $urlRouterProvider, $locationProvider) {
	
	$stateProvider

		/*
		 * Configuration file for all the state(in angularJS terms every new html page is new state) in web application
		 */
	
		.state("Customer", {
			url: "/Customer",
			templateUrl: contexturl+ "pages/Customer/Customer.html",
			controller: "customerController as custC",
			cache: false,
		})
		
		.state("Order", {
			url: "/Order/:custId",
			templateUrl: contexturl+ "pages/Order/Order.html",
			controller: "orderController as orderC",
			resolve: {
				staffInfo: ["staffService", function(staffService) {
					return staffService.getStaffInfo();
			}]},
			cache: false,
		})
		
		.state("Payment", {
			url: "/Payment/:paymentInfo",
			templateUrl: contexturl+ "pages/Payment/Payment.html",
			controller: "paymentController as paymentC",
			cache: false,
		})
		
		.state("Staff", {
			url: "/Staff",
			templateUrl: contexturl+ "pages/Staff/Staff.html",
			controller: "staffController as staffC",
			cache: false,
		})
		
		.state("Admin", {
			url: "/Admin",
			templateUrl: contexturl+ "pages/Admin/Admin.html",
			controller: "adminController as adminC",
			resolve: {
				adminInfo: ["staffService", function(staffService) {
					return staffService.getadminInfo();
			}]},
			cache: false,
		})
		
}]);