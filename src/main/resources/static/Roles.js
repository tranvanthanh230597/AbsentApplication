var roles = {} || roles;

roles.init = function(){
    roles.drawTable();
};

roles.drawTable = function(){
    $.ajax({
        url : "http://localhost:8088/rest/roles",
        method : "GET" ,
        dataType : "json",
        success : function(data){
            $.each(data, function(i, v){
                $('#tbRoles').append(
                    "<tr>"+
                    "<td>"+v.id+"</td>"+
                    "<td>"+v.roleName+"</td>"+
                    "<td></td>"+
                    "</tr>"
                );
            });
        }
    });
};

roles.save = function(){
    if ($("#formAddEditRole").valid()){
        
    }
};

roles.openModal = function(){
    $("#addEditRole").modal('show');
};

$(document).ready(function(){
    roles.init();
});