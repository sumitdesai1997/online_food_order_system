orderModule.controller("orderController",['$state', 'orderService','$stateParams', 'staffInfo', function($state, orderService, $stateParams, staffInfo){

	var orderC = this;
	
	/*
	 *fetching value from the resolve (mentioned in app.config file) and making an array of staffIds available in the database
	 */
	orderC.staffData = staffInfo.staffData;
	orderC.staffIds = [];
	
	if(typeof orderC.staffData != "undefined" && orderC.staffData != null){
		for(var i = 0; i<orderC.staffData.length ; i++){
			orderC.staffIds.push(orderC.staffData[i].STAFF_ID);
		}
	}
	
	/*
	 *assigning order to staff id by choosing staff id randomly from the staffIds array
	 */
	const random = Math.floor(Math.random() * orderC.staffIds.length);
	orderC.STAFF_ID = orderC.staffIds[random];
	
	/*
	 *fetching value of parameters from the URL using $stateParams
	 */
	orderC.CUST_ID = $stateParams.custId;
	
	orderC.VegItemList = [{
		"DISPLAY" : "Pizza and Garlic bread Combo",
		"VALUE" : "1",
		"PRICE" : "200"
	}, {
		"DISPLAY" : "Burger and French fries Combo",
		"VALUE" : "2",
		"PRICE" : "150"
	}, {
		"DISPLAY" : "Dosa and Idli Combo",
		"VALUE" : "3",
		"PRICE" : "100"
	}];
	
	orderC.NonVegItemList = [{
		"DISPLAY" : "Grilled Chicken with Salsa",
		"VALUE" : "1",
		"PRICE" : "350"
	}, {
		"DISPLAY" : "Mutton curry",
		"VALUE" : "2",
		"PRICE" : "250"
	}, {
		"DISPLAY" : "Fish biryani",
		"VALUE" : "3",
		"PRICE" : "150"
	}];
	
	orderC.ItemQuanityList = [{
		"DISPLAY" : "1",
		"VALUE" : "1"
	}, {
		"DISPLAY" : "2",
		"VALUE" : "2"
	}, {
		"DISPLAY" : "3",
		"VALUE" : "3"
	}];
	
	orderC.vegQuantitySet = function (){
		orderC.VEG_ITEM_QUANTITY = "1";
	}
	
	orderC.nonvegQuantitySet = function (){
		orderC.NON_VEG_ITEM_QUANTITY = "1";
	}
	
	/*
	 *on change of veg item name or quanity calling this function to fetch the item price
	 */
	orderC.vegItemChange = function (vegItem,vegQuantity){
		if(typeof vegItem == "undefined" || vegItem == null || typeof vegQuantity == "undefined" || vegQuantity == null){
			return;
		}
		if(vegItem.VALUE == "1"){
			orderC.VEG_ITEM_PRICE = 200;
			if(vegQuantity.VALUE == "1"){
				orderC.VEG_ITEM_PRICE = orderC.VEG_ITEM_PRICE;
			} else if(vegQuantity.VALUE == "2"){
				orderC.VEG_ITEM_PRICE = orderC.VEG_ITEM_PRICE * 2;
			} else if(vegQuantity.VALUE == "3"){
				orderC.VEG_ITEM_PRICE = orderC.VEG_ITEM_PRICE * 3;
			}
		} else if(vegItem.VALUE == "2"){
			orderC.VEG_ITEM_PRICE = 150;
			if(vegQuantity.VALUE == "1"){
				orderC.VEG_ITEM_PRICE = orderC.VEG_ITEM_PRICE;
			} else if(vegQuantity.VALUE == "2"){
				orderC.VEG_ITEM_PRICE = orderC.VEG_ITEM_PRICE * 2;
			} else if(vegQuantity.VALUE == "3"){
				orderC.VEG_ITEM_PRICE = orderC.VEG_ITEM_PRICE * 3;
			}
		} else if(vegItem.VALUE == "3"){
			orderC.VEG_ITEM_PRICE = 100;
			if(vegQuantity.VALUE == "1"){
				orderC.VEG_ITEM_PRICE = orderC.VEG_ITEM_PRICE;
			} else if(vegQuantity.VALUE == "2"){
				orderC.VEG_ITEM_PRICE = orderC.VEG_ITEM_PRICE * 2;
			} else if(vegQuantity.VALUE == "3"){
				orderC.VEG_ITEM_PRICE = orderC.VEG_ITEM_PRICE * 3;
			}
		}
	}
	
	/*
	 *on change of non veg item name or quanity calling this function to fetch the item price
	 */
	orderC.nonvegItemChange = function (nonvegItem,nonvegQuantity){
		if(typeof nonvegItem == "undefined" || nonvegItem == null || typeof nonvegQuantity == "undefined" || nonvegQuantity == null){
			return;
		}
		if(nonvegItem.VALUE == "1"){
			orderC.NON_VEG_ITEM_PRICE = 350;
			if(nonvegQuantity.VALUE == "1"){
				orderC.NON_VEG_ITEM_PRICE = orderC.NON_VEG_ITEM_PRICE;
			} else if(nonvegQuantity.VALUE == "2"){
				orderC.NON_VEG_ITEM_PRICE = orderC.NON_VEG_ITEM_PRICE * 2;
			} else if(nonvegQuantity.VALUE == "3"){
				orderC.NON_VEG_ITEM_PRICE = orderC.NON_VEG_ITEM_PRICE * 3;
			}
		} else if(nonvegItem.VALUE == "2"){
			orderC.NON_VEG_ITEM_PRICE = 250;
			if(nonvegQuantity.VALUE == "1"){
				orderC.NON_VEG_ITEM_PRICE = orderC.NON_VEG_ITEM_PRICE;
			} else if(nonvegQuantity.VALUE == "2"){
				orderC.NON_VEG_ITEM_PRICE = orderC.NON_VEG_ITEM_PRICE * 2;
			} else if(nonvegQuantity.VALUE == "3"){
				orderC.NON_VEG_ITEM_PRICE = orderC.NON_VEG_ITEM_PRICE * 3;
			}
		} else if(nonvegItem.VALUE == "3"){
			orderC.NON_VEG_ITEM_PRICE = 150;
			if(nonvegQuantity.VALUE == "1"){
				orderC.NON_VEG_ITEM_PRICE = orderC.NON_VEG_ITEM_PRICE;
			} else if(nonvegQuantity.VALUE == "2"){
				orderC.NON_VEG_ITEM_PRICE = orderC.NON_VEG_ITEM_PRICE * 2;
			} else if(nonvegQuantity.VALUE == "3"){
				orderC.NON_VEG_ITEM_PRICE = orderC.NON_VEG_ITEM_PRICE * 3;
			}
		}
	}
	
	orderC.saveOrderDetails = function (orderForm){
		if(orderC.VEG_QUEST == "N" && orderC.NON_VEG_QUEST == "N"){
			alert("Please select any of the item to proceed ahead!"); 
			return;
		}
		
		if(typeof orderForm != "undefined" && orderForm.$invalid){
			orderC.submit = true;
			alert("Please fill all the required fields with the valid data!");   
			return;
		}
		
		orderC.ORDER_ID = "O_" + Math.floor(Math.random()*90000);
		
		if(typeof orderC.VEG_ITEM_PRICE == "undefined" || orderC.VEG_ITEM_PRICE == ""){
			orderC.VEG_ITEM_PRICE = 0;	
		}
		if(typeof orderC.NON_VEG_ITEM_PRICE == "undefined" || orderC.NON_VEG_ITEM_PRICE == ""){
			orderC.NON_VEG_ITEM_PRICE = 0;	
		}
		
		/*
		 *Object preparation for database operations
	     */
		var dataObj = {
			"FOOD_ORDER": {
						"set": {
							"ORDER_ID": orderC.ORDER_ID,
							"O_TIMESTAMP": "",
							"ORDER_PRICE": orderC.VEG_ITEM_PRICE + orderC.NON_VEG_ITEM_PRICE,
							"ORDER_STATUS": "CONFIRM",
							"CUST_ID": orderC.CUST_ID,
							"STAFF_ID": orderC.STAFF_ID
						}
				}
		};
		
		/*
		 *if veg item is selected by the user
	     */
		if(orderC.VEG_QUEST == "Y"){
			dataObj.ORDER_VEG = {
						"set": {
							"ORDER_ID": orderC.ORDER_ID,
							"VEG_ID": orderC.VEG_ITEM_NAME.VALUE
						} 
			};
			dataObj.VEG = {
						"set": {
							"VEG_ID": orderC.VEG_ITEM_NAME.VALUE,
							"ITEM_NAME": orderC.VEG_ITEM_NAME.DISPLAY,
							"ITEM_QUANTITY": orderC.VEG_ITEM_QUANTITY.DISPLAY,
							"ITEM_PRICE": orderC.VEG_ITEM_PRICE
						} 
			};
		}
		
		/*
		 *if non veg item is selected by the user
	     */
		if(orderC.NON_VEG_QUEST == "Y"){
			dataObj.ORDER_NON_VEG = {
						"set": {
							"ORDER_ID": orderC.ORDER_ID,
							"NON_VEG_ID": orderC.NON_VEG_ITEM_NAME.VALUE
						} 
			};
			dataObj.NON_VEG = {
						"set": {
							"NON_VEG_ID": orderC.NON_VEG_ITEM_NAME.VALUE,
							"ITEM_NAME": orderC.NON_VEG_ITEM_NAME.DISPLAY,
							"ITEM_QUANTITY": orderC.NON_VEG_ITEM_QUANTITY.DISPLAY,
							"ITEM_PRICE": orderC.NON_VEG_ITEM_PRICE
						} 
			};
		}
		
		orderService.saveOrderDetails(dataObj).then(function(){
			$state.go("Payment", {paymentInfo : orderC.CUST_ID + "|" + (orderC.VEG_ITEM_PRICE + orderC.NON_VEG_ITEM_PRICE)});
		})
	} 
}]);