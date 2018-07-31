<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="../jsp/common/head.jsp"%>

<div class="right">
    <div class="location">
        <strong>你现在所在的位置是:</strong>
        <span>供应商管理页面 >> 供应商修改页</span>
    </div>
    <div class="providerAdd">
        <form id="roleForm" name="providerForm" method="post" action="/user/updateRole">
            <!--div的class 为error是验证错误，ok是验证成功-->
            <input type="hidden" name="rid" value="${role.id }">
            <div class="">
                <label for="roleCode">角色编码：</label>
                <input type="text" name="roleCode" id="roleCode" value="${role.roleCode }" readonly="readonly">
            </div>
            <div>
                <label for="roleName">角色名称：</label>
                <input type="text" name="roleName" id="roleName" value="${role.roleName }">
                <font color="red"></font>
            </div>
            <div class="providerAddBtn">
                <input type="button" name="save" id="save" value="保存">
                <input type="button" id="back" name="back" value="返回" >
            </div>
        </form>
    </div>
</div>
</section>
<%@include file="../jsp/common/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/statics/js/rolemodify.js"></script>