/**
* NOTE: Login feature.
* @class Login
* @static
*/

/**
* NOTE: Display login form.
* If the credential is correct, makes an ajax call and redirect to tool page.
* @method (anonymous self-invoking function).
*/
$(function(){
	$("#login-button").on('click', function(){
		var username = $("#username").val();
		var password = $("#password").val();

		if(username === ""){
			alert("Error: Please enter username");
			$('#username').focus();
			return false;
		}

		if(password === ""){
			alert("Error: Please enter password");
			$('#password').focus();
			return false;
		}

		//$.cookie('username',username,{expires:1});
		var data=JSON.stringify({"name":username,"password":password});
		$.ajax({
		    type: 'POST',
		    url: '/urlPostLoginOut',
		    dataType: 'json',
		    contentType: 'application/json',
		    data: data,
		    statusCode: {
		        401: function() {
		            alert("Invalid Username/Password");
		        },
		        202: function() {
		        	localStorage.setItem('admin', JSON.stringify(username));
		           window.location.href = 'http://localhost:9000';
		        },
				200: function() {
					localStorage.setItem('admin', username);
		            window.location.href = 'http://localhost:9000';
		        },
		    },
		    async: false
		});
	});
});