<%-- 
    Document   : index
    Created on : 10 Apr, 2017, 1:56:27 PM
    Author     : stpl
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
         <script src="<%=request.getContextPath()%>/js/canvasjs.min.js"></script>
        <title>Weather Temperature</title>
        <script>
   
    $( document ).ready(function() {

      //Find Location
       var location ='';
       var resp = '';
       var dataPoints=[];
       var minTemp = '';
       var maxTemp = '';
       if(confirm("Please press ok to detect location")){
            _detectLocation();
        }

        $("#address").on('click', function(){
                _ajaxCall("getWeatherCondition.action?location="+location+"");

                var chart = new CanvasJS.Chart("chartContainer", {
				title: {
					text: "Current Temperature"
				},
				data: [{
					type: "line",
					dataPoints: dataPoints
				}]
			});
			chart.render();
                        $("#minTemp").css("display","block");
                        $("#maxTemp").css("display","block");
            });

        function _ajaxCall(actionCall){
            $.ajax({
                   type : "GET",
                   url: actionCall,
                   datatype: "application/json",
                   async : false,
                   success: function(result){
                        if(result!=null){
                            drawGraphOfTemperature(result);
                        }
                }
            });
           }

           function _detectLocation(){
                $.get("http://freegeoip.net/json/", function (response) {
                 $("#ip").html("IP: " + response.ip);
                 location = response.city;
                 $("#address").html(response.city);
                 $("#details").html(JSON.stringify(response, null, 4));
                 }, "jsonp");
           }

           function drawGraphOfTemperature(result){
               console.log(result);
               var arrObj = result.list;
              
               $(arrObj).each(function(){
                   var d = new Date(this.dt);
                   minTemp = parseFloat((parseInt(this.temp.min)-273.5).toFixed(2));
                   maxTemp = parseFloat((parseInt(this.temp.max)-273.5).toFixed(2));
                   var dateTime = d.toLocaleTimeString();
                   var dayTemp = parseFloat((parseInt(this.temp.day)-273.5).toFixed(2));
                   dataPoints.push(
                      {
                        y: dayTemp, label: dateTime
                      }
                     );

               });

               $("#minTemp").html("Minimum Temperature :"+minTemp);
               $("#maxTemp").html("Maximum Temperature : "+maxTemp);


           }
  });
       //Graph preparation
        

        </script>
    </head>
    <body>
<!--        <s:form action="test" method="post">
            <s:textfield name="username" />
            <s:submit/>

        </s:form>-->

 <div id="ip" name="ip" style="display:none"></div>
 <center><h3><b><div id="address" name="address" style="display:block"></div></b></h3></center>
<div id="chartContainer" style="height: 400px; width: 100%;"></div>
<center>
<h2>
 <div id="minTemp" name="maxTemp" style="display:none"></div><br>
 <div id="maxTemp" name="maxTemp" style="display:none"></div>
</h2>
</center>


    </body>
   
</html>
