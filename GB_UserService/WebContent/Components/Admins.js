$(document).ready(function()
{
	if ($("#alertSuccess").text().trim() == "")
	{
	$("#alertSuccess").hide();
	}
	$("#alertError").hide();
});

//SAVE
$(document).on("click", "#btnSave", function(event)
	{
	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
	
	// Form validation-------------------
	var status = validateItemForm();
	if (status != true)
	{
	$("#alertError").text(status);
	$("#alertError").show();
	return;
	}
	
	// If valid------------------------
	var type = ($("#hidItemIDSave").val() == "") ? "POST" : "PUT";
	$.ajax(
	{
	url : "AdminAPI",
	type : type,
	data : $("#formItem").serialize(),
	dataType : "text",
	complete : function(response, status)
	{
	onItemSaveComplete(response.responseText, status);
	}
	});
});


function onItemSaveComplete(response, status)
{
	if (status == "success")
	{
	var resultSet = JSON.parse(response);
	if (resultSet.status.trim() == "success")
	{
	$("#alertSuccess").text("Successfully saved.");
	$("#alertSuccess").show();
	$("#divItemsGrid").html(resultSet.data);
	} else if (resultSet.status.trim() == "error")
	{
	$("#alertError").text(resultSet.data);
	$("#alertError").show();
	}
	} else if (status == "error")
	{
	$("#alertError").text("Error while saving.");
	$("#alertError").show();
	} else
	{
	$("#alertError").text("Unknown error while saving..");
	$("#alertError").show();
	}
	$("#hidItemIDSave").val("");
	$("#formItem")[0].reset();
}


$(document).on("click", ".btnUpdateA", function(event)
{
	$("#hidItemIDSave").val($(this).data("userid"));
	$("#userName").val($(this).closest("tr").find('td:eq(0)').text());
	$("#email").val($(this).closest("tr").find('td:eq(1)').text());
	$("#firstName").val($(this).closest("tr").find('td:eq(2)').text());
	$("#lastName").val($(this).closest("tr").find('td:eq(3)').text());
	$("#cardNumber").val($(this).closest("tr").find('td:eq(4)').text());
	$("#cvv").val($(this).closest("tr").find('td:eq(5)').text());
	$("#expDate").val($(this).closest("tr").find('td:eq(6)').text());
	$("#password").val($(this).closest("tr").find('td:eq(7)').text());
})


$(document).on("click", ".btnRemoveA", function(event)
{
	$.ajax(
	{
	url : "AdminAPI",
	type : "DELETE",
	data : "userId=" + $(this).data("userid"),
	dataType : "text",
	complete : function(response, status)
	{
	onItemDeleteComplete(response.responseText, status);
	}
	});
})


function onItemDeleteComplete(response, status)
{
	if (status == "success")
	{
	var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success")
		{
		$("#alertSuccess").text("Successfully deleted.");
		$("#alertSuccess").show();
		$("#divItemsGrid").html(resultSet.data);
		} 
		else if (resultSet.status.trim() == "error")
		{
		$("#alertError").text(resultSet.data);
		$("#alertError").show();
		}
	} 
	else if (status == "error")
	{
	$("#alertError").text("Error while deleting.");
	$("#alertError").show();
	} 
	else
	{
	$("#alertError").text("Unknown error while deleting..");
	$("#alertError").show();
	}
}





// CLIENT-MODEL================================================================
function validateItemForm()
{
// USERNAME
	if ($("#userName").val().trim() == "")
	{
	return "Insert userName.";
	}
// EMAIL
	if ($("#email").val().trim() == "")
	{
	return "Insert email.";
}
// FNAME
	if ($("#firstName").val().trim() == "")
	{
	return "Insert firstName.";
	}
// LNAME
	if ($("#lastName").val().trim() == "")
	{
	return "Insert lastName.";
}

// CARDNUMBER-------------------------------
if ($("#cardNumber").val().trim() == "")
{
return "Insert cardNumber.";
}

// is numerical value
var tmpPrice = $("#cardNumber").val().trim();
if (!$.isNumeric(tmpPrice))
{
return "Insert a numerical value for cardNumber.";
}


//CVV-------------------------------
if ($("#cvv").val().trim() == "")
{
return "Insert Item cvv.";
}

// is numerical value
var tmpPrice = $("#cvv").val().trim();
if (!$.isNumeric(tmpPrice))
{
return "Insert a numerical value for Cvv.";
}


//EXPDATE
if ($("#expDate").val().trim() == "")
{
return "Insert expDate.";
}
//PASSWORD
if ($("#password").val().trim() == "")
{
return "Insert password.";
}



return true;
}