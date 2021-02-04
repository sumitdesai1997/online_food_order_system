staffModule.controller('staffController', ['$scope', '$state', 'staffService', function($scope, $state, staffService){
	var staffC = this;
	
	/*
	 *if any of the button is selected then making the flag of other buttons false, and using those flags hiding unwanted fields
     */	
	staffC.insertChange = function (){
		staffC.deleteStaff = false;
		staffC.selectStaff = false;
		staffC.updateStaff = false;
		staffC.FIRST_NAME='';
		staffC.LAST_NAME='';
		staffC.BIRTH_DATE='';
		staffC.CONTACT_NO='';
	}
	
	staffC.deleteChange = function (){
		staffC.insertStaff = false;
		staffC.selectStaff = false;
		staffC.updateStaff = false;
		staffC.FIRST_NAME='';
		staffC.LAST_NAME='';
		staffC.BIRTH_DATE='';
		staffC.CONTACT_NO='';
	}
	
	staffC.selectChange = function (){
		staffC.deleteStaff = false;
		staffC.insertStaff = false;
		staffC.updateStaff = false;
		staffC.FIRST_NAME='';
		staffC.LAST_NAME='';
		staffC.BIRTH_DATE='';
		staffC.CONTACT_NO='';
	}
	
	staffC.updateChange = function (){
		staffC.deleteStaff = false;
		staffC.insertStaff = false;
		staffC.selectStaff = false;
		staffC.FIRST_NAME='';
		staffC.LAST_NAME='';
		staffC.BIRTH_DATE='';
		staffC.CONTACT_NO='';
	}
	
	staffC.insertStaffDetails = function (staffForm){
		if(typeof staffForm != "undefined" && staffForm.$invalid){
			staffC.submit = true;
			alert("Please fill all the required fields with the valid data!");   
			return;
		}
		
		staffC.STAFF_ID = "S_" + Math.floor(Math.random()*90000);
		
		/*
		 *Object preparation for database operations
	     */
		var dataObj = {
			"STAFF": {
						"set": {
							"STAFF_ID": staffC.STAFF_ID,
							"FIRST_NAME": staffC.FIRST_NAME,
							"LAST_NAME": staffC.LAST_NAME,
							"BIRTH_DATE": staffC.BIRTH_DATE,
							"CONTACT_NO": staffC.CONTACT_NO,
							"USERNAME": "admin"
						}
				}
		};
		
		staffService.insertStaffDetails(dataObj).then(function(){
			alert("staff data inserted succesfully!");	
		})
		
	}
	
	staffC.deleteStaffDetails = function (staffForm){
		if(typeof staffForm != "undefined" && staffForm.$invalid){
			staffC.submit = true;
			alert("Please fill all the required fields with the valid data!");   
			return;
		}
		
		/*
		 *Object preparation for database operations
	     */
		var dataObj = {
			"STAFF": {
				"where": {
					"STAFF_ID": staffC.STAFF_ID
				}
			}
		};

		staffService.deleteStaffDetails(dataObj).then(function(res){
			if(res.exist == true){
				alert("Data deleted for entered staff id!");
			} else {
				alert("No data found for entered staff id!");	
			}
		}); 
	}
	
	staffC.selectStaffDetails = function (staffForm){
		if(typeof staffForm != "undefined" && staffForm.$invalid){
			staffC.submit = true;
			alert("Please fill all the required fields with the valid data!");   
			return;
		}
		
		/*
		 *Object preparation for database operations
	     */
		var dataObj = {
			"STAFF": {
				"where": {
					"STAFF_ID": staffC.STAFF_ID
				}
			}
		};

		staffService.selectStaffDetails(dataObj).then(function(res){
			if(res.exist == true){
				var staffData = res.staffData[0];
				alert("FIRST NAME:" + staffData.FIRST_NAME + ", LAST NAME:" + staffData.LAST_NAME + ", BIRTH DATE:" + staffData.BIRTH_DATE + ", CONTACT NO:" + staffData.CONTACT_NO);	
			} else {
				alert("No data found for enterd staff id!");	
			}
				
		}); 
	}
	
	staffC.updateStaffDetails = function (staffForm){
		if(typeof staffForm != "undefined" && staffForm.$invalid){
			staffC.submit = true;
			alert("Please fill all the required fields with the valid data!");   
			return;
		}
		
		/*
		 *Object preparation for database operations
	     */
		var dataObj = {
			"STAFF": {
					"set": {
						"FIRST_NAME": staffC.FIRST_NAME,
						"LAST_NAME": staffC.LAST_NAME,
						"BIRTH_DATE": staffC.BIRTH_DATE,
						"CONTACT_NO": staffC.CONTACT_NO
					}, 
					"where": {
						"STAFF_ID": staffC.STAFF_ID
					}
				}
		};

		staffService.updateStaffDetails(dataObj).then(function(res){
			if(res.exist == true){
				alert("Data updated for entered staff id!");
			} else {
				alert("No data found for entered staff id!");	
			}
		})  
	}
	
}]);