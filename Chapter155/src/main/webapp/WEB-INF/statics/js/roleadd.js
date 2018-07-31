var roleName = null;
var roleCole = null;
var addBtn = null;
var backBtn = null;

$(function(){
    roleCole = $("#roleCode");
    roleName = $("#roleName");
    addBtn = $("#add");
    backBtn = $("#back");
    //初始化的时候，要把所有的提示信息变为：* 以提示必填项，更灵活，不要写在页面上
    roleCole.next().html("*");
    roleName.next().html("*");


    /*
     * 验证
     * 失焦\获焦
     * jquery的方法传递
     */
    roleCole.on("blur",function(){
        if(roleCole.val() == ""){
            validateTip(roleCole.next(),{"color":"red"},imgNo+ " 请输入角色编码",false);
            return;
        }
        $.ajax({
            type:"GET",//请求类型
            url:path+"/user/CountRole",//请求的url
            data:{"roleCode":roleCole.val()},//请求参数
            dataType:"json",//ajax接口（请求url）返回的数据类型
            success:function(data){//data：返回数据（json对象）
                if(data.role == "exist"){//账号已存在，错误提示
                    validateTip(roleCole.next(),{"color":"red"},imgNo+ " 该用户账号已存在",false);
                }else if(data.role == "true"){//账号可用，正确提示
                    validateTip(roleCole.next(),{"color":"green"},imgYes+" 该账号可以使用",true);
                }
            },
            error:function(data){//当访问时候，404，500 等非200的错误状态码
                validateTip(roleCole.next(),{"color":"red"},imgNo+" 您访问的页面不存在",false);
            }
        });
    }).on("focus",function(){
        //显示友情提示
        validateTip(roleCole.next(),{"color":"#666666"},"* 请输入供应商编码",false);
    }).focus();

    roleName.on("focus",function(){
        validateTip(roleName.next(),{"color":"#666666"},"* 请输入角色名称",false);
    }).on("blur",function(){
        if(roleName.val() != null && roleName.val() != ""){
            validateTip(roleName.next(),{"color":"green"},imgYes,true);
        }else{
            validateTip(roleName.next(),{"color":"red"},imgNo+" 角色名称不能为空，请重新输入",false);
        }

    });

    addBtn.bind("click",function(){
        if(roleCole.attr("validateStatus") != "true"){
            roleCole.blur();
        }else if(roleName.attr("validateStatus") != "true"){
            roleName.blur();
        }else{
            if(confirm("是否确认提交数据")){
                $("#roleForm").submit();
            }
        }
    });

    backBtn.on("click",function(){
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