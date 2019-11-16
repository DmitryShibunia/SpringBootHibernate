var employeeId = getParameterByName('id');
var EMPLOYEES_URL = "http://localhost:8080/employees";

if(employeeId != "null"){
    $("#id").val(employeeId);
    getEmployeeById();
}

$('#saveBtn').click(function () {
    if ($('#id').val() == '')
        addEmployee();
    else
        updateEmployee();
    return false;
});


$('#btnCancel').click(function () {

    window.location = 'index.html';

});

function getEmployeeById() {
    console.log('getEmployeeById');
    console.log(employeeId)
    $.ajax({
        type: 'GET',
        url: EMPLOYEES_URL + '/' + employeeId,
        dataType: "json", // data type of response
        success: fillFields,
        error: function(jqXHR, textStatus, errorThrown) {
            console.log(jqXHR, textStatus, errorThrown);
            alert('getEmployeeById: ' + textStatus);
        }
    });
}

function addEmployee() {
    console.log('addEmployee');
    console.log(formToJSON());
    $.ajax({
        type: 'POST',
        contentType: 'application/json',
        url: EMPLOYEES_URL,
        data: formToJSON(),
        success: function (data, textStatus, jqXHR) {
            alert('Employee created successfully');
            console.log('Employee created successfully');
            window.location = 'index.html';
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert('addEmployee error: ' + errorThrown);
        }
    });

}

function updateEmployee() {
    console.log('updateEmployee');
    console.log(formToJSON());

    $.ajax({
        type: 'PUT',
        contentType: 'application/json',
        url: EMPLOYEES_URL,
        data: formToJSON(),
        success: function (data, textStatus, jqXHR) {
            alert('Employee updated successfully');
            console.log('Employee updated successfully');
            window.location = 'index.html';
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert('updateEmployee error: ' + errorThrown);
        }
    });
}

function fillFields(data) {
    $("#firstName").val(data.firstName);
    $("#lastName").val(data.lastName);
    $("#department").val(data.departmentId);
    $("#jobTitle").val(data.jobTitle);
    $("#gender").val(data.gender);
    $("#datepicker").val(data.dateOfBirth);
}

function formToJSON() {
    var id = $('#id').val();
    return JSON.stringify({
        "id": $('#id').val() == "" ? null : parseInt(id),
        "firstName": $('#firstName').val(),
        "lastName": $('#lastName').val(),
        "departmentId": $('#department').val(),
        "jobTitle": $('#jobTitle').val(),
        "gender": $('#gender').val(),
        "dateOfBirth": $('#datepicker').val()
    });
}

function getParameterByName(name, url) {
    if (!url) {
        url = window.location.href;
    }
    name = name.replace(/[\[\]]/g, "\\$&");
    var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
        results = regex.exec(url);
    if (!results) return null;
    if (!results[2]) return '';
    return decodeURIComponent(results[2].replace(/\+/g, " "));
}