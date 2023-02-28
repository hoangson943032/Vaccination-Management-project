/**
 * 
 */
 
 // update schedule
 $(".shedule").click(function(){
 
   $(this).addClass('selected').siblings().removeClass('selected');
   var id =  $(this).attr( "data-id" );
   
   
    $(".update").click(function() {
    	var url = "/injection-schedule/update/";

		var parameters = {id : id};
		
    	  location.href = buildUrl(url, parameters);
    })
});

 // update news
 $(".news").click(function(){
 
   $(this).addClass('selected').siblings().removeClass('selected');
   var id =  $(this).attr( "data-id" );
   
   
    $(".update").click(function() {
    	var url = "/news/update-news/";

		var parameters = {id : id};
		
    	  location.href = buildUrl(url, parameters);
    })
});


$(".update-Vaccine").click(function() {
	
	var count = 0;
	var parameters = null;
	$(':checkbox').each(function() {
            if(this.checked === true) {
            	count ++;
				if($(this).attr("id") === "select-all" || count > 1) {
					parameters = null;
					return false;
				}
				parameters = {id : this.value};
				
            }
            
     })
     
     if(parameters !== null) {
            	
            	var entity = $(this).val();
			 	var url = "/" + entity + "/update/";
            	location.href = buildUrl(url, parameters);
     }
     else {
     	alert('Please select a record!');
     }                  
            
      
})


$(".make-inactive").click(function() {
	
	var count = 0;
	var parameters = null;
	const ids = [];
	
	$(':checkbox').each(function() {
            if(this.checked === true) {
				if($(this).attr("id") !== "select-all") {
					ids.push(this.value);
					parameters = {ids : ids};
				}	
            }
            
     })
     
     if(parameters !== null) {
		 if (confirm('Are you sure to make inactive?')) {
			
			var entity = $(this).val();
			 var url = "/" + entity + "/make-inactive/";
			 location.href = buildUrl(url, parameters);
		 }

     }
     else {
     	alert('Please select a record!');
     }                  
            
      
})





	function buildUrl(url, parameters){
	  var qs = "";
	  for(var key in parameters) {
	    var value = parameters[key];
	    qs += encodeURIComponent(key) + "=" + encodeURIComponent(value) + "&";
	  }
	  if (qs.length > 0){
	    qs = qs.substring(0, qs.length-1); //chop off last "&"
	    url = url + "?" + qs;
	  }
	  return url;
	}
	
	
	
	var dropdown = document.getElementsByClassName("dropdown-btn");
	var i;
	
	for (i = 0; i < dropdown.length; i++) {
	    dropdown[i].addEventListener("click", function() {
	        this.classList.toggle("active");
	        var dropdownContent = this.nextElementSibling;
	        if (dropdownContent.style.display === "flex") {
	            dropdownContent.style.display = "none";
	        } else {
	            dropdownContent.style.display = "flex";
	        }
	    });
	}
	
	
	// Listen for click on toggle checkbox
	$('#select-all').click(function(event) {   
	    if(this.checked) {
	        // Iterate each checkbox
	        $(':checkbox').each(function() {
	            this.checked = true;                        
	        });
	    } else {
	        $(':checkbox').each(function() {
	            this.checked = false;                       
	        });
	    }
	}); 
	
	
	window.onload = function() {
	const toastLiveExample = document.getElementById('liveToast');
	const toast = new bootstrap.Toast(toastLiveExample, {
                    animation: true,
                    autohide: true,
                    delay: 3000,
                });
	toast.show();
	
}



$(".numberRecod").click(function() {
  var open = $(this).data("isopen");
  if(open) {
    window.location.href = $(this).val()
  }
  //set isopen to opposite so next time when use clicked select box
  //it wont trigger this event
  $(this).data("isopen", !open);
});


$(".delete").click(function() {
	
	var count = 0;
	var parameters = null;
	const ids = [];
	
	$(':checkbox').each(function() {
            if(this.checked === true) {
				if($(this).attr("id") !== "select-all") {
					ids.push(this.value);
					parameters = {ids : ids};
				}	
            }
            
     })
     
     if(parameters !== null) {
		 if (confirm('Are you sure to delete?')) {
			
			var entity = $(this).val();
			 var url = "/" + entity + "/delete/";
			 location.href = buildUrl(url, parameters);
		 }

     }
     else {
     	alert('Please select a record!');
     }                  
            
      
})
