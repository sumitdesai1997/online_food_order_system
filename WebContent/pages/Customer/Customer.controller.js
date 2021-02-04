custModule.controller('customerController', ['$scope', '$state', 'customerService', function($scope, $state, customerService){
	console.log("hi");
	var custC = this;
	
	custC.saveCustomerDetails = function (custForm){
		if(typeof custForm != "undefined" && custForm.$invalid){
			custC.submit = true;
			alert("Please fill all the required fields with the valid data!");   
			return;
		}
		
		custC.CUST_ID = "C_" + Math.floor(Math.random()*90000);
		
		/*
		 *Object preparation for database operations
	     */
		var dataObj = {
			"CUSTOMER": {
						"set": {
							"CUST_ID": custC.CUST_ID,
							"FIRST_NAME": custC.FIRST_NAME,
							"LAST_NAME": custC.LAST_NAME,
							"HOUSE_NO": custC.HOUSE_NO,
							"STREET_NAME": custC.STREET_NAME,
							"PINCODE": custC.PINCODE
						}
				},
			"CUSTOMER_CONTACT_NO": {
						"set": {
							"CUST_ID": custC.CUST_ID,
							"CONTACT_NO": custC.CONTACT_NO
						}
				}
		};
		
		/*
		 * if 2 contact number has been added then only adding value to dataObj
	     */
		if(typeof custC.ALT_CONTACT_NO != "undefined" && custC.ALT_CONTACT_NO != null && custC.ALT_CONTACT_NO != ""){
			dataObj.ALT_CONTACT_NO = {
						"set": {
							"CUST_ID": custC.CUST_ID,
							"CONTACT_NO": custC.ALT_CONTACT_NO
						} 
			}
		}
		
		customerService.saveCustomerDetails(dataObj).then(function(res){
			$state.go("Order", {custId: custC.CUST_ID});		
		})
	}
}]);