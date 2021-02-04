payModule.controller("paymentController", ['paymentService','$stateParams', function(paymentService, $stateParams){

	var paymentC = this;
	
	/*
	 * fetching value of parameters from the URL using $stateParams
	 */
	paymentC.CUST_ID = $stateParams.paymentInfo.split("|")[0];
	paymentC.PAYMENT_AMOUNT = parseInt($stateParams.paymentInfo.split("|")[1]);
	
	paymentC.PayMethodList = [{
		"DISPLAY" : "Credit/Debit Card",
		"VALUE" : "Credit/Debit Card"
	}, {
		"DISPLAY" : "Netbanking",
		"VALUE" : "Netbanking"
	}, {
		"DISPLAY" : "Google Pay",
		"VALUE" : "Google Pay"
	}];
	
	paymentC.savePaymentDetails = function(paymentForm){
		if(typeof paymentForm != "undefined" && paymentForm.$invalid){
			paymentForm.submit = true;
			alert("Please fill all the required fields with valid data!!");   
			return;
		}
		
		paymentC.PAYMENT_ID = "P_" + Math.floor(Math.random()*90000);
		
		/*
		 *Object preparation for database operations
	     */
		var dataObj = {
			"PAYMENT": {
						"set": {
							"PAYMENT_ID": paymentC.PAYMENT_ID,
							"METHOD": paymentC.PAYMENT_METHOD.VALUE,
							"AMOUNT": paymentC.PAYMENT_AMOUNT,
							"P_TIMESTAMP": "",
							"CUST_ID": paymentC.CUST_ID
						}
				}
		};
		
		paymentService.savePaymentDetails(dataObj).then(function(){
			alert("Payment done successfully!!");
		})
	}
	
}]);
