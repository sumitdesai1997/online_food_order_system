custModule.service("customerService", ['$q', '$filter', 'CommonService', function ($q, $filter ,CommonService) {

	var custS = this;
	
	custS.saveCustomerDetails = function(dataObj){
		var dfd = $q.defer();
		
		var request = CommonService.getRequest("CustomerDetails", "saveCustomerDetails", dataObj);
		CommonService.doAjaxCall(request, function (res) {
			dfd.resolve(res.data);	
		}, function (err) {
			debug(err);
			dfd.resolve(err)
		});
		return dfd.promise;
	};
	
}]);