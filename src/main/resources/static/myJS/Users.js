var users = {

} || users;

users.init = function(){
    users.drawTable();
};

users.initClassJoin = function(){
    $.ajax({
        url : "http://localhost:8088/rest/class",
        method : "GET",
        dataType : "json",
        success : function(data){
            $('#classJoin').empty();
            $.each(data, function(i, v){
                $('#classJoin').append(
                    "<option value='"+ v.id +"'>"+ v.className +"</option>"
                );
            });
        }
    });
}
users.get = function(id){
    console.log('get :'+ id);

    $.ajax({
        url : "http://localhost:8088/rest/users/" + id,
        method : "GET",
        dataType : "json",
        success : function(data){
            console.log(data);
            $('#formAddEdit')[0].reset();
            //
            $('#modalTitle').html("Edit User");
            $('#userId').val(data.userId);
            $('#firstName').val(data.firstName);
            $('#lastName').val(data.lastName);
            $('#birthDay').val(data.birthDay);
            $('#classJoin').val(data.classJoin.id);
            $('#id').val(data.id);

            $('#addEditUser').modal('show');
        }
    });
};
users.drawTable = function(){
    $("#user-dataTables").DataTable({
        ajax:{
            url : "http://localhost:8088/rest/users",
            method : "GET" ,
            dataType : "json",
            dataSrc: ""
        },
        columns : [
            { data: "userId" , name: "userId", title: "User Id",orderable: true},
            { data: "firstName" , name: "firstName", title: "First Name",orderable: true},
            { data: "lastName" , name: "lastName", title: "Last Name",orderable: true},
            { data: "birthDay" , name: "birthDay", title: "DOB",orderable: true},
            { data: "userStatus" , name: "userStatus", title: "User Status",orderable: true},
            { data: "dayOff" , name: "dayOff", title: "Day Off",orderable: true},
            { data: "role.roleName" , name: "roleName", title: "Role Name",orderable: true},
            { data: "classJoin.className" , name: "className", title: "Class Name",orderable: true},

            { data: "id", name : "Action", title : "Action",sortable: false,
                orderable: false ,"render": function ( data, type, row, meta ) {
                    var str =  "<a href='javascript:;' title='edit user' onclick='users.get("+ data +")'><i class='fa fa-edit'></i></a> " +
                        "<a href='javascript:;' title='remove user' onclick='users.delete("+ data +")' ><i class='fa fa-trash'></i></a>"
                    return str ;
                }
            }
        ]
    });
};

users.save = function(){
    if ($("#formAddEdit").valid()){
        if($('#id').val() == 0){
            var userObj = {};
            userObj.userId = $('#userId').val();
            userObj.firstName = $('#firstName').val();
            userObj.lastName = $('#lastName').val();
            userObj.birthDay = $('#birthDay').val();
            console.log(userObj);

            //
            var classObj = {};
            classObj.id = $("#classJoin").val();
            classObj.className = $("#classJoin option:selected").html();
            userObj.classJoin = classObj;

            $.ajax({
                url : "http://localhost:8088/rest/users",
                method : "POST",
                dataType : "json",
                contentType : "application/json",
                data : JSON.stringify(userObj),
                done: function(){
                    console.log("POST DONE");
                    $('#addEditUser').modal('hide');
                    $("#user-dataTables").DataTable().ajax.reload();
                },
                success : function(data){
                    console.log("POST success");
                    $('#addEditUser').modal('hide');
                    $("#user-dataTables").DataTable().ajax.reload();

                }
            });
        }
        else{
            var userObj = {};
            userObj.userId = $('#userId').val();
            userObj.firstName = $('#firstName').val();
            userObj.lastName = $('#lastName').val();
            userObj.birthDay = $('#birthDay').val();
            userObj.id = $('#id').val();
            var classObj = {};
            classObj.id = $("#classJoin").val();
            classObj.className = $("#classJoin option:selected").html();
            userObj.classJoin = classObj;
            console.log(userObj);
            $.ajax({
                url : "http://localhost:8088/rest/users/" + userObj.id,
                method : "PUT",
                dataType : "json",
                contentType : "application/json",
                data : JSON.stringify(userObj),
                success : function(data){
                    console.log("Update success");
                    $('#addEditUser').modal('hide');
                    $("#user-dataTables").DataTable().ajax.reload();
                }
            });
        }
    }
};

users.delete = function(id){
    bootbox.confirm({
        title: "Remove user",
        message: "Do you want to remove this user?",
        buttons: {
            cancel: {
                label: '<i class="fa fa-times"></i> No'
            },
            confirm: {
                label: '<i class="fa fa-check"></i> Yes'
            }
        },
        callback: function (result) {
            if(result){
                $.ajax({
                    url : "http://localhost:8088/rest/users/" + id,
                    method: "DELETE",
                    dataType : "json",
                    success : function(data){
                        console.log('This was logged in the callback: ' + result);
                        users.drawTable();
                    }
                });
            }
        }
    });
}

users.resetForm =  function(){
    $('#formAddEdit')[0].reset();
    $('#userId').val('');
    $('#firstName').val('');
    $('#lastName').val('');
    $('#birthDay').val('');
    $('#classJoin').val('');

    //
    var validator = $( "#formAddEdit" ).validate();
    validator.resetForm();
}

users.openModal = function(){
    $('#modalTitle').html("Add new Users");
    users.resetForm();
    $("#addEditUser").modal('show');
};
users.initValidation =  function(){
    $("#modalAddEdit").validate({
        rules: {
            userId: "required",
            firstName: "required",
            lastName: "required",
            birthDay: "required",
            classJoin: "required"
        },
        messages: {
            userId: "Please enter your userId",
            firstName: "Please enter your firstName",
            lastName: "Please enter your lastName",
            birthDay: "Please enter your birthDay",
            classJoin: "Please enter your classJoin",

        }
    });
}

$(document).ready(function(){
    users.init();
    users.initClassJoin();
    users.initValidation();
});