var users = {} || users;

users.init = function(){
    users.drawTable();
};

users.drawTable = function(){
    $.ajax({
        url : "http://localhost:8088/rest/users",
        method : "GET" ,
        dataType : "json",
        success : function(data){
            $.each(data, function(i, v){
                $('#tbUsers').append(
                    "<tr>"+
                        "<td>"+v.userId+"</td>"+
                        "<td>"+v.firstName+"</td>"+
                        "<td>"+v.lastName+"</td>"+
                        "<td>"+v.birthDay+"</td>"+
                        "<td>"+v.classJoin.className+"</td>"+
                        "<td>"+v.userStatus+"</td>"+
                        "<td>"+v.role.roleName+"</td>"+
                        "<td></td>"+
                    "</tr>"
                );
            });
        }
    });
};

users.openModal = function(){
    $("#addEditUser").modal('show');
};

$(document).ready(function(){
    users.init();
});