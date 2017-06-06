$(document).ready(function () {
    $('#category').on('change', function () {

        var select = $(this).val();
        console.log(select);
        switch (select){
            case "sluzbowy":
                $("#subCategoryy").show();
                $("#otherr").hide()

                break;
            case "inny":
                $("#otherr").show();
                $("#subCategoryy").hide();
                break;
            default:
                $("#subCategoryy").hide();
                $("#otherr").hide()
            }
        });
    });
