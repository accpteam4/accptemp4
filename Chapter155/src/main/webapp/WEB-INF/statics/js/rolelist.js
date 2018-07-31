var roleObj;

//供应商管理页面上点击删除按钮弹出删除框(providerlist.jsp)
function deleteProvider(obj){
    $.ajax({
        type:"GET",
        url:path+"/user/delRole",
        data:{/*method:"delprovider",*/id:obj.attr("proid")},
        dataType:"json",
        success:function(data){
            if(data.delRole == "true"){//删除成功：移除删除行
                cancleBtn();
                obj.parents("tr").remove();
            }else if(data.delRole == "false"){//删除失败
                //alert("对不起，删除供应商【"+obj.attr("proname")+"】失败");
                changeDLGContent("对不起，删除角色【"+obj.attr("proname")+"】失败");
            }else if(data.delRole == "notexist"){
                //alert("对不起，供应商【"+obj.attr("proname")+"】不存在");
                changeDLGContent("对不起，角色【"+obj.attr("proname")+"】不存在");
            }else{
                //alert("对不起，该供应商【"+obj.attr("proname")+"】下有【"+data.delResult+"】条订单，不能删除");
                changeDLGContent("对不起，该角色【"+obj.attr("proname")+"】下有【"+data.delRole+"】条用户，不能删除");
            }
        },
        error:function(data){
            //alert("对不起，删除失败");
            changeDLGContent("对不起，删除失败");
        }
    });
}

function openYesOrNoDLG(){
    $('.zhezhao').css('display', 'block');
    $('#removeProv').fadeIn();
}

function cancleBtn(){
    $('.zhezhao').css('display', 'none');
    $('#removeProv').fadeOut();
}
function changeDLGContent(contentStr){
    var p = $(".removeMain").find("p");
    p.html(contentStr);
}
$(function(){
    $(".modifyProvider").on("click",function(){
        var obj = $(this);
        window.location.href=path+"/user/ShowList?rid="+ obj.attr("proid");
    });

    $('#no').click(function () {
        cancleBtn();
    });

    $('#yes').click(function () {
        deleteProvider(roleObj);
    });

    $(".deleteProvider").on("click",function(){
        roleObj = $(this);
        changeDLGContent("你确定要删除角色【"+roleObj.attr("proname")+"】吗？");
        openYesOrNoDLG();
    });

    /*	$(".deleteProvider").on("click",function(){
            var obj = $(this);
            if(confirm("你确定要删除供应商【"+obj.attr("proname")+"】吗？")){
                $.ajax({
                    type:"GET",
                    url:path+"/jsp/provider.do",
                    data:{method:"delprovider",proid:obj.attr("proid")},
                    dataType:"json",
                    success:function(data){
                        if(data.delResult == "true"){//删除成功：移除删除行
                            alert("删除成功");
                            obj.parents("tr").remove();
                        }else if(data.delResult == "false"){//删除失败
                            alert("对不起，删除供应商【"+obj.attr("proname")+"】失败");
                        }else if(data.delResult == "notexist"){
                            alert("对不起，供应商【"+obj.attr("proname")+"】不存在");
                        }else{
                            alert("对不起，该供应商【"+obj.attr("proname")+"】下有【"+data.delResult+"】条订单，不能删除");
                        }
                    },
                    error:function(data){
                        alert("对不起，删除失败");
                    }
                });
            }
        });*/
});