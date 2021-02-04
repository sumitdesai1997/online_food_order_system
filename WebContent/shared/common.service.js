commonModule.service('CommonService', ['$q', '$http', function ($q, $http) {
	"use strict";
	var cs = this;
	
	var REQ_FORMAT = {
	    request: {
	        helper: "",
	        procedure: "",
	        data: {}
	    }
	}
	
	this.getRequest = function(helper, procedure, data){
		var request = JSON.parse(JSON.stringify(REQ_FORMAT));
		request.request.helper = helper;
		request.request.procedure = procedure;
		request.request.data = data;
		return request;
	}
	
	this.ajaxCount = 0;
	this.doAjaxCall = function (data, successCallback, errorCallback) {
		if(cs.ajaxCount <= 0){
     		cs.ajaxCount = 0;
		}
		cs.ajaxCount++;
		
		try {
			var settings = {
				"url": contexturl+"SellOnline",
				"method": "POST",
				"dataType":"text",
				"headers": {},
				"timeout": parseInt("50000"),
				"data": JSON.stringify(data),
				success:function (response) {
					response = JSON.parse(response);
					
					if(cs.ajaxCount == 1){
						if(!cs.hideLoadingCondition){
						}
							 cs.ajaxCount--;
					}else{
						 cs.ajaxCount--;
					}

					var endTime = new Date().getTime();
					var totalTime = (endTime - startTime) +' ms';																
					if (response.status == 200){
						console.log("success");
					} else {
						console.log("failed");
					}
					successCallback(response);
				},
				error:function (error) {
					if(cs.ajaxCount == 1){
						setTimeout(function(){
							 cs.ajaxCount--;
						},1000)
					}else{
						 cs.ajaxCount--;
					}
					if(error.statusText == 'timeout' && data.request.procedure != "generateSis"){
						return;
					}
					else{
						errorCallback(error);
					}
				}
			}
			var startTime = new Date().getTime(); 
			$.ajax(settings);
		} catch (ex) {
			console.log(ex);
			return null;
		}
	}
}]);