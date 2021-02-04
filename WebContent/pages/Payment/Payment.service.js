payModule.service("paymentService", ['$q', '$filter', 'CommonService', function ($q, $filter ,CommonService) {

	var paymentS = this;
	
	paymentS.savePaymentDetails = function(dataObj){
		var dfd = $q.defer();
		
		var request = CommonService.getRequest("PaymentDetails", "savePaymentDetails", dataObj);
		CommonService.doAjaxCall(request, function (res) {
			dfd.resolve(res.data);	
		}, function (err) {
			debug(err);
			dfd.resolve(err)
		});
		return dfd.promise;
	};
	
}]);