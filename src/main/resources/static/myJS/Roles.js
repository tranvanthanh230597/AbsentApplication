var roles = {} || roles;

roles.init = function () {
    roles.drawTable();
};

roles.get = function(id){
    console.log('get :'+ id);

    $.ajax({
        url : "http://localhost:8088/rest/roles/" + id,
        method : "GET",
        dataType : "json",
        success : function(data){
            console.log(data);
            $('#formAddEdit')[0].reset();
            //
            $('#modalTitle').html("Edit Role");
            $('#roleName').val(data.roleName);

            $('#id').val(data.id);
            $('#addEditRole').modal('show');
        }
    });
};
roles.resetForm =  function(){
    $('#formAddEdit')[0].reset();
    $('#roleName').val('');
    //
    var validator = $( "#formAddEdit" ).validate();
    validator.resetForm();
}
roles.drawTable = function () {
    $("#role-dataTables").DataTable({
        ajax:{
            url : "http://localhost:8088/rest/roles",
            method : "GET" ,
            dataType : "json",
            dataSrc: ""
        },
        columns : [
            { data: "id" , name: "id", title: "Role Id",orderable: true},
            { data: "roleName" , name: "roleName", title: "Roles Name",orderable: true},
            { data: "id", name : "Action", title : "Action",sortable: false,
                orderable: false ,"render": function ( data, type, row, meta ) {
                    var str =  "<a href='javascript:;' title='edit user' onclick='roles.get("+ data +")'><i class='fa fa-edit'></i></a> " +
                        "<a href='javascript:;' title='remove user' onclick='roles.delete("+ data +")' ><i class='fa fa-trash'></i></a>"
                    return str ;
                }
            }
        ]
    });
};

roles.save = function () {
    if ($("#formAddEdit").valid()){
        if($('#id').val() == 0){
            var roleObj = {};
            roleObj.roleName = $('#roleName').val();
            console.log(roleObj);

            $.ajax({
                url: "http://localhost:8088/rest/roles",
                method: "POST",
                dataType: "json",
                contentType: "application/json",
                data : JSON.stringify(roleObj),
                done: function(){
                    console.log("POST DONE");
                    $('#addEditRole').modal('hide');
                    $("#role-dataTables").DataTable().ajax.reload();
                },
                success : function(data){
                    console.log("POST success");
                    $('#addEditRole').modal('hide');
                    $("#role-dataTables").DataTable().ajax.reload();

                }
            });
        }
        else{
            var userObj = {};
            userObj.roleName = $('#roleName').val();
            userObj.id = $('#id').val();
            console.log(userObj);
            $.ajax({
                url : "http://localhost:8088/rest/roles/" + userObj.id,
                method : "PUT",
                dataType : "json",
                contentType : "application/json",
                data : JSON.stringify(userObj),
                success : function(data){
                    console.log("Update success");
                    $('#addEditRole').modal('hide');
                    $("#role-dataTables").DataTable().ajax.reload();
                }
            });
        }
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
                        $("#role-dataTables").DataTable().ajax.reload();
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