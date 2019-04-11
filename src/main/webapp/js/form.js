$(document).ready(function () {
    $("#ping").click(function () {
        $.ajax({
            url:"/ping",
            type:"GET",
            contentType:"application/json; charset=utf-8",
            dataType:"text",
            success: function(result){
                console.log(result);
                alert(result);
            }
        });
    });

    $("#addPerson").click(function () {
        console.log('PERSON');
        var person = {
            name: $("#name").val(),
            age: $("#age").val()
        };
        console.log('PER', person);

        $.ajax({
            url:"/person",
            type:"POST",
            data: JSON.stringify(person),
            contentType:"application/json; charset=utf-8",
            success: function(){
                alert('Success');
            }
        });

        clearFields();
    });

    $("#personList").click(function () {
        $.ajax({
            url:"/person",
            type:"GET",
            contentType:"application/json; charset=utf-8",
            dataType:"json",
            success: function(result){
                console.log(result);
                alert(JSON.stringify(result));
            }
        });
    });
});

var clearFields = function () {
    $("#name").val('');
    $("#age").val('');
};