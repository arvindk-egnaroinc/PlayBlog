     /**
     * Post a new article and send data to database controller
     * @method (anonymous function).
     */
     	$('#newpost-button').on('click', function(){
            var title=$('#title').val();
     		var content =$("#content").val();
     		var tags = $("#tags").val();

     		if(typeof title == 'undefined' || title ===''){
     			alert('Title field should not be empty');
     			return false;

     		}
     		if(typeof content == 'undefined' || content ===''){
     			alert('Content field should not be empty');
     			return false;
     		}

     		if( typeof tags == 'undefined' || tags ===''){
     			alert('Tags field should not be empty');
     			return false;

     	    }
     		else {
     			data = {
     			 		"Title": title,
     				    "Content": content,
     				    "Tags": tags
     				}
     			}
     			var jsonData = JSON.stringify(data);
     			$.ajax({
     		        type : 'POST',
     		        dataType: 'json',
     		        url:'/doPost',
     		        contentType : 'application/json; charset=utf-8',
     		        data : jsonData,
     		        statusCode: {
     			        401: function() {
     			            alert("401 error");
     			        },
     			        202: function() {
     			        	alert("Error 202");
     			        	window.location.href='/new';
     			        },
     					200: function() {
     						alert("Submited Successfully.");
     						window.location.href='/';
     			        },
     			        400: function() {
     						alert("This title is already available");
     			        },
     				},

     		});
     	});

