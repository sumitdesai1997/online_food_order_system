staffModule.service("staffService", ['$q', '$filter', 'CommonService', function ($q, $filter ,CommonService) {

	var staffS = this;
	
	staffS.insertStaffDetails = function(dataObj){
		var dfd = $q.defer();
		
		var request = CommonService.getRequest("StaffDetails", "insertStaffDetails", dataObj);
		CommonService.doAjaxCall(request, function (res) {
			dfd.resolve(res.data);	
		}, function (err) {
			debug(err);
			dfd.resolve(err)
		});
		return dfd.promise;
	};
	
	staffS.deleteStaffDetails = function(dataObj){
		var dfd = $q.defer();
		
		var request = CommonService.getRequest("StaffDetails", "deleteStaffDetails", dataObj);
		CommonService.doAjaxCall(request, function (res) {
			dfd.resolve(res.data);	
		}, function (err) {
			debug(err);
			dfd.resolve(err)
		});
		return dfd.promise;
	};
	
	staffS.selectStaffDetails = function(dataObj){
		var dfd = $q.defer();
		
		var request = CommonService.getRequest("StaffDetails", "selectStaffDetails", dataObj);
		CommonService.doAjaxCall(request, function (res) {
			dfd.resolve(res.data);	
		}, function (err) {
			debug(err);
			dfd.resolve(err)
		});
		return dfd.promise;
	};
	
	staffS.updateStaffDetails = function(dataObj){
		var dfd = $q.defer();
		
		var request = CommonService.getRequest("StaffDetails", "updateStaffDetails", dataObj);
		CommonService.doAjaxCall(request, function (res) {
			dfd.resolve(res.data);	
		}, function (err) {
			debug(err);
			dfd.resolve(err)
		});
		return dfd.promise;
	};
	
	staffS.getStaffInfo = function(){
		var dfd = $q.defer();
		
		var request = CommonService.getRequest("StaffDetails", "getStaffInfo", {});
		CommonService.doAjaxCall(request, function (res) {
			dfd.resolve(res.data);	
		}, function (err) {
			debug(err);
			dfd.resolve(err)
		});
		return dfd.promise;
	};
	
	staffS.getadminInfo = function(){
		var dfd = $q.defer();
		
		var request = CommonService.getRequest("StaffDetails", "getadminInfo", {});
		CommonService.doAjaxCall(request, function (res) {
			dfd.resolve(res.data);	
		}, function (err) {
			debug(err);
			dfd.resolve(err)
		});
		return dfd.promise;
	};
	
}]);