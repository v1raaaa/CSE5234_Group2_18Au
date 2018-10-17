function isValidQuantity(x) {
  return !isNaN(x) && 
     parseInt(Number(x)) == x && 
     !isNaN(parseInt(x, 10)) &&
     Number(x) >= 0;
}
function validateQuantity() {
	var items = document.forms["orderForm"].elements;
	var formValid = true;
	for (var i = 0; i < items.length - 2; i+=3) {
		var quantity = items[i].value;
		if(quantity.trim().length > 0 && !isValidQuantity(quantity)) { 
			console.log("Invalid quantity: " + quantity)
			formValid = false; 
		}
	}
	if (!formValid) {
		document.getElementById("invalid").innerHTML = "<p style=\'color: red;\'>Improper quantity for insurance added. Please use positive whole numbers.</p>"
	}
	return formValid;
}
