$(function(){
	$("#mainInput").val("Hi");
	for(var i=0;i<10;i++)
	{
		var div=$("<div class='col-md-3'>");
		var input = $("#mainInput").clone(); 
		div.append(input);
		$("#form").append(div);
		//div.appendTo($("#form"));
	}
	$("#btnAdd").click(function(){
		var div=$("<div class='col-md-3'>");
		var input = $("#mainInput").clone(); 
		input.val("I am Added by button");
		div.append(input);
		$("#form").append(div);
		return false;
	});
});