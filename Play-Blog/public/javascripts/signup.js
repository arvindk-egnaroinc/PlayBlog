     /**
     * Signup validation and send data to database controller
     * Checks for email availability.
     * If unavailable, it registers the user and sends an email to admin for acceptance.
     * @method (anonymous function).
     */
     $(function(){
     	//var nameBool = false;

//     	$('#username').blur(function(){
//     		var name = $('#username').val();
//     		$.ajax({
//     			type: 'GET',
//     			//url: '/username/check/'+name,
//     			dataType: 'json',
//     			statusCode: {
//     				200: function() {
//     					nameBool=true;
//     				},
//     				404: function() {
//     					alert('username already existed, please choose another');
//     					nameBool=false;
//     				}
//     			},
//     			async: false
//     		});
//     	});
//
//     	$('#email').blur(function(){
//     		var email = $("#email").val();
//     		var emailReg = new RegExp(/^(("[\w-\s]+")|([\w-]+(?:\.[\w-]+)*)|("[\w-\s]+")([\w-]+(?:\.[\w-]+)*))(@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$)|(@\[?((25[0-5]\.|2[0-4][0-9]\.|1[0-9]{2}\.|[0-9]{1,2}\.))((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\.){2}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\]?$)/i);
//     		var valid = emailReg.test(email);
//     		if(valid) {
//     			$.ajax({
//     			    type: 'GET',
//     			    //url: '/isUserIdExist/'+email,
//     			    dataType: 'json',
//     			    statusCode: {
//     			        200: function() {
//     			        	alert('email already existed, please choose another');
//     			    		em=false;
//     			        },
//     			        404: function() {
//     			        	em=true;
//     			        }
//     			    },
//     			    async: false
//     			});
//     		}
//     		else{
//     			alert('invalid email');
//     		}
//     	});

     	$('#signup-button').on('click', function(){
            var nameBool = true;
            var em = true;
            var data=null;


     		var username=$('#username').val();
     		var password =$("#password").val();
     		var confirmPassword = $("#confirm-password").val();
     		var email=$("#email").val();
     		var emailReg = new RegExp(/^(("[\w-\s]+")|([\w-]+(?:\.[\w-]+)*)|("[\w-\s]+")([\w-]+(?:\.[\w-]+)*))(@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$)|(@\[?((25[0-5]\.|2[0-4][0-9]\.|1[0-9]{2}\.|[0-9]{1,2}\.))((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\.){2}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\]?$)/i);
     		var valid = emailReg.test(email);
     		var emailflag = false;
     		var isDirty = false;
     		var emailEmpty = false;

     		if(typeof username == 'undefined' || username ===''){
     			alert('username field should not be empty');
     			return false;

     		}
     		if(typeof email == 'undefined' || email ===''){
     			alert('email field should not be empty');
     			return false;
     		}

     		if( typeof password == 'undefined' || password ===''){
     			alert('password field should not be empty');
     			return false;

     	    }
     		if(typeof confirmPassword == 'undefined' || confirmPassword ===''){
     			alert('confirmPassword field should not be empty');
     			return false;
     	    }

     		if(!valid){
     	        emailflag= false;
     	    }
     		else{
     	    	emailflag= true;
     		}

     		if(!emailflag){
     			alert('invalid email address');
     			$("#email").focus();
     		}

     		else if(password != confirmPassword){
     			alert("Password and confirm password should be same!");
     			$("#confirm-password").focus();
     		}
     		else if(em && nameBool){
     	    	var username = $('#username').val();
     	    	email = $('#email').val();
     			password = $('#password').val();
     			data = {
     			 		"Username": username,
     				    "EmailId": email,
     				    "Password": password
     				}
     			}
     			var jsonData = JSON.stringify(data);
     			$.ajax({
     		        type : 'POST',
     		        dataType: 'json',
     		        url:'/PostSignUpOut',
     		        contentType : 'application/json; charset=utf-8',
     		        data : jsonData,
     		        statusCode: {
     			        401: function() {
     			            alert("401 error");
     			        },
     			        202: function() {
     			        	alert("Error 202");
     			        	window.location.href='/signup';
     			        },
     					200: function() {
     						alert("Submited Successfully. Now you can login");
     						window.location.href='/login';
     			        },
     			        400: function() {
     						alert("This user is already present");
     			        },
     				},
     			});
     			//$('form').fadeOut(500);
                //$('.wrapper').addClass('form-success');
     			if(em === false || nameBool === false){
                     			alert("E-mail already registered please use another!");
                     		}
     		});
     	});

    