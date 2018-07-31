var roleCode = null;
var roleName = null;
var saveBtn = null;
var backBtn = null;

$(function(){
    roleCode = $("#roleCode");
    roleName = $("#roleName");
    saveBtn = $("#save");
    backBtn = $("#back");

    //初始化的时候，要把所有的提示信息变为：* 以提示必填项，更灵活，不要写在页面上
    roleCode.next().html("*");
    roleName.next().html("*");

    /*
     * 验证
     * 失焦\获焦
     * jquery的方法传递
     */
    roleCode.on("focus",function(){
        validateTip(roleCode.next(),{"color":"#666666"},"* 请输入用户编码",false);
    }).on("blur",function(){
        if(roleCode.val() != null && roleCode.val() != ""){
            validateTip(roleCode.next(),{"color":"green"},imgYes,true);
        }else{
            validateTip(roleCode.next(),{"color":"red"},imgNo+" 用户编码不能为空，请重新输入",false);
        }

    });

    roleName.on("focus",function(){
        validateTip(roleName.next(),{"color":"#666666"},"* 请输入用户名称",false);
    }).on("blur",function(){
        if(roleName.val() != null && roleName.val() != ""){
            validateTip(roleName.next(),{"color":"green"},imgYes,true);
        }else{
            validateTip(roleName.next(),{"color":"red"},imgNo+" 用户名称不能为空，请重新输入",false);
        }
    });

    saveBtn.on("click",function(){
        roleName.blur();
        if(roleName.attr("validateStatus") == "true"){
            if(confirm("是否确认提交数据")){
                $("#roleForm").submit();
            }
        }
    });

    backBtn.on("click",function(){
        //alert("modify: "+referer);
        if(referer != undefined
            && null != referer
            && "" != referer
            && "null" != referer
            && referer.length > 4){
            window.location.href = referer;
        }else{
            history.back(-1);
        }
    });
});