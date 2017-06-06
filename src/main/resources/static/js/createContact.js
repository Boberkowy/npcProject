jQuery(document).ready(
    function($) {
        $("#addContact").click(function (event) {
            var contact = {}
            contact["firstname"] = $("#firstname").val();
            contact["lastname"] = $("#lastname").val();
            contact["email"] = $("#email").val();
            contact["password"] = $("#pass").val();
            contact["category"] = $("#category").val();
            contact["subcategory"] = $("#subcategory").val();
            contact["phoneNumber"] = $("#phoneNumber").val();
            contact["dateOfBirth"] = $("#dateOfBirth").val();
            $("#addingForm").hide();


            $.ajax({
                type: 'post',
                contentType: "application/json",
                url: '/addContact',
                data: JSON.stringify(contact),
                dataType: 'json',
                success: function (contact) {
                    $("#contactDiv").html(contact);
                    console.log(contact.subcategory);

                }, error: function (e) {
                    console.log('Error:' + e);
                }
            });
        });
    });
