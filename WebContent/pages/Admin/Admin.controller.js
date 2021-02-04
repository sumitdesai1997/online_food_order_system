adminModule.controller("adminController", ['$state','adminInfo', function($state,adminInfo){

	var adminC = this;
	adminC.adminInfo = adminInfo.staffData[0];
	adminC.login = function(adminForm){
		if(typeof adminForm != "undefined" && adminForm.$invalid){
			adminC.submit = true;
			alert("Please fill all the required fields with valid data!!");   
			return;
		}
		
		if(adminC.USERNAME == adminC.adminInfo.USERNAME && adminC.PASSWORD == adminC.adminInfo.PASSWORD){
			$state.go("Staff"); 
		} else{
			alert("Please enter correct username and password!!");
			adminC.USERNAME = "";   
			adminC.PASSWORD = "";
		}
		
	}
	
}]);
