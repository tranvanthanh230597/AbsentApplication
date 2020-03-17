var roles = {} || roles;

roles.init = function () {
    roles.drawTable();
};

roles.drawTable = function () {
    $.ajax({
        url: "http://localhost:8088/rest/roles",
        method: "GET",
        dataType: "json",
        success: function (data) {
            $('#tbRoles').empty();
            $.each(data, function (i, v) {
                $('#tbRoles').append(
                    "<tr>" +
                    "<td>" + v.id + "</td>" +
                    "<td>" + v.roleName + "</td>" +
                    "<td></td>" +
                    "</tr>"
                );
            });
        }
    });
};

roles.save = function () {
    if ($("#formAddEditRole").valid()) {
        var roleObj = {};
        roleObj.roleName = $('#roleName').val();

        $.ajax({
            url: "http://localhost:8088/rest/roles",
            method: "POST",
            dataType: "json",
            contentType: "application/json",
            data: JSON.stringify(roleObj),
            success: function (data) {
                $('#addEditRole').modal('hidden');
                roles.drawTable();
            }
        });
    }
};

roles.delete = function (id) {
    bootbox.confirm({
        title: "Destroy role?",
        message: "Do you want to activate the Deathstar now? This cannot be undone.",
        buttons: {
            cancel: {
                label: '<i class="fa fa-times"></i> Cancel'
            },
            confirm: {
                label: '<i class="fa fa-check"></i> Confirm'
            }
        },
        callback: function (result) {
            if (result) {
                $.ajax({
                    url: "http://localhost:8088/rest/roles/" + id,
                    method: "DELETE",
                    dataType: "json",
                    success: function (data) {
                        console.log('This was logged in the callback: ' + result);
                        roles.drawTable();
                    }
                });
            }
        }
    });
};

roles.openModal = function () {
    $("#addEditRole").modal('show');
};
$(document).ready(function () {
    roles.init();
});