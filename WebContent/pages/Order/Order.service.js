orderModule.service("orderService", ['$q', '$filter', 'CommonService', function ($q, $filter ,CommonService) {

	var orderS = this;
	
	orderS.saveOrderDetails = function(dataObj){
		var dfd = $q.defer();
		
		var request = CommonService.getRequest("OrderDetails", "saveOrderDetails", dataObj);
		CommonService.doAjaxCall(request, function (res) {
			dfd.resolve(res.data);	
		}, function (err) {
			debug(err);
			dfd.resolve(err)
		});
		return dfd.promise;
	};
	
}]);