var EMPLOYEES_URL = "http://localhost:8080/employees";
$.dto = null;

$('#employeeList').on("click", ".small-button.delete", function () {
    var id = $(this).parent().find(".employeeDel").attr("employeeId");
    deleteEmployee(id);
});

$('#employeeList').on("click", ".small-button.edit", function () {
    var id = $(this).parent().find(".employeeEdit").attr("employeeId");
    var uri = 'EditEmployee.html?id=' + id;
    window.location = uri;
});

$('#btnNew').click(function () {
    window.location = 'EditEmployee.html?id=null';
});

findAll();

function findAll() {
    console.log('findAll');
    $.ajax({
        type: 'GET',
        url: EMPLOYEES_URL,
        dataType: "json", // data type of response
        success: renderList,
        error: function(jqXHR, textStatus, errorThrown) {
            console.log(jqXHR, textStatus, errorThrown);
            alert('findAll: ' + textStatus);
        }
    });
}

function deleteEmployee(id) {
    console.log('deletePlayer');
    $.ajax({
        type: 'DELETE',
        contentType: 'json',
        url: EMPLOYEES_URL +  '/' + id,
        success: function (data, textStatus, jqXHR) {
            alert('Employee deleted successfully');
            findAll();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert('deleteEmployee error: ' + errorThrown);
        }
    });
}

function renderList(data) {
    dto = data == null ? [] : (data instanceof Array ? data : [data]);
    $('#employeeList tr').remove();
    $.each(dto, function (index, employee) {
        drawRow(employee);
    });
}

function drawRow(employee) {
    console.log(employee)
    var row = $("<tr />");

    $("#employeeList").append(row);
    row.append($('<td>' + employee.firstName + '</td>'));
    row.append($("<td>" + employee.lastName + "</td>"));
    row.append($("<td>" + employee.departmentId + "</td>"));
    row.append($("<td>" + employee.jobTitle + "</td>"));
    row.append($("<td>" + employee.gender + "</td>"));
    row.append($("<td>" + employee.dateOfBirth + "</td>"));
    row.append($('<td>'
        + '<span class="small-button delete"><button>delete</button></span>' + '<span class="employeeDel" employeeId="' + employee.id + '">'
        + '<span class="small-button edit"><button>edit</button></span>' + '<span class="employeeEdit" employeeId="' + employee.id + '">'));
}