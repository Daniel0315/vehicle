<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>添加申请单</title>
</head>
<body>
<div>
    <form id="insert" action="/api/update?vid=${insert.vid}" method="post"  enctype="multipart/form-data">
        <div>车辆基本信息:</div>
        <br/>
        <input type="hidden" value="${insert.vid}">
        颜色：<input type="text" name="vcolor" value="${insert.vcolor}">
        车辆品牌：<input type="text" name="vbrand" value="${insert.vbrand}"><br/><br/>
        购买时间：<input type="date" name="vbuytime" value="${insert.vbuytime}">
        车牌号：<input type="text" name="platenum" value="${insert.platenum}"><br/><br/>
        防盗标签号：<input type="text" name="theftnum" value="${insert.theftnum}">
        车架号：<input type="text" name="framenum" value="${insert.framenum}"><br/><br/>
        电机号：<input type="text" name="motornum" value="${insert.motornum}">

        是否有保险：<input type="radio" name="isinsurance" value="1" <c:if test="${insert.isinsurance=='1'}">checked</c:if>>有<input type="radio" name="isinsurance" value="0" <c:if test="${insert.isinsurance=='0'}">checked</c:if>>无<br/><br/>
        投保日期：<input type="date" name="insuretime" value="${insert.insuretime}">
        在保年限：<input type="number" name="lefttime" value="${insert.lefttime}"><br/><br/>
        车辆前身照：<input type="file" name="frontimg" id="frontimg"><span id="checkfrontimg"></span><br/><br/>
        车辆后身照：<input type="file" name="afterimg" id="afterimg"><span id="checkafterimg"></span><br/><br/>
        登记上牌防盗备案表整表照片：<input type="file" name="recordimg" id="recordimg"><span id="checkrecordimg"></span><br/><br/>
        身份证照片：<input type="file" name="cerimg" id="cerimg"><span id="checkcerimg"></span><br/><br/>
        安装有源标签位置的照片：<input type="file" name="labelimg" id="labelimg"><span id="checklabelimg"></span><br/><br/>

        <br/><br/><br/>
        <br/><br/><br/>
        <div>车辆所有人基本信息：</div>
        <br/>
        姓名：<input type="text" name="ownname" value="${insert.ownname}">
        联系电话：<input type="text" name="ownphone" value="${insert.ownphone}"><br/>
        证件类型：<select name="cerid">
        <c:forEach items="${cert}" var="cert">
                <option value="${cert.cerid}"<c:if test="${cert.cerid}==${insert.cerid}">checked</c:if>>${cert.certype}</option>
        </c:forEach>

    </select>
        证件号码：<input type="text" name="cernum" value="${insert.cernum}"><br/><br/>
        车辆所有人现居住地址：<input type="text" name="ownarea" value="${insert.ownarea}">区<input type="text" name="ownvillage" value="${insert.ownvillage}">小区(宿舍楼、公寓等)
        <input type="text" name="ownfloor" value="${insert.ownfloor}">楼<input type="text" name="ownunit" value="${insert.ownunit}">门<input type="text" name="ownhouse" value="${insert.ownhouse}">号

        <br/><br/><br/>
        <div>委托代办人基本信息：</div>
        <br/>
        代办人姓名：<input type="text" name="cliname" value="${insert.cliname}">
        联系电话：<input type="text" name="cliphone" value="${insert.cliphone}"><br/><br/>
        证件类型：<select name="clicerid">
        <c:forEach items="${cert}" var="cert">
            <option value="${cert.cerid}"<c:if test="${cert.cerid}==${insert.clicerid}">checked</c:if>>${cert.certype}</option>
        </c:forEach>

    </select>
        代办人证件号码：<input type="text" name="clicernum" value="${insert.clicernum}"><br/><br/>
        代办人现居住地址：<input type="text" name="cliarea" value="${insert.cliarea}">区<input type="text" name="clivillage" value="${insert.clivillage}">小区(宿舍楼、公寓等)
        <input type="text" name="clifloor" value="${insert.clifloor}">楼<input type="text" name="cliunit" value="${insert.cliunit}">门<input type="text" name="clihouse" value="${insert.clihouse}">号
        <br/>
        <input type="submit" value="修改" id="submit">
        <span id="checksubmit"></span>
    </form>
</div>
<script type="text/javascript" src="/js/jquery-3.3.1.min.js"></script>
</body>
</html>
<script>
    $(function () {
        $("#frontimg").change(function () {
            var frontimg=$(this).val();
            var suffix=frontimg.substr(frontimg.lastIndexOf("."));
            if (suffix==".png"||suffix==".jpeg"||suffix==".gif"||suffix==".jpg") {
                $("#checkfrontimg").text("");
            }else {
                $("#checkfrontimg").text("请注意：上传的图片格式为png,jpeg,gif,jpg");
            }
        })

        $("#afterimg").change(function () {
            var afterimg=$(this).val();
            var suffix=afterimg.substr(afterimg.lastIndexOf("."));
            if (suffix==".png"||suffix==".jpeg"||suffix==".gif"||suffix==".jpg") {
                $("#checkafterimg").text("");
            }else {
                $("#checkafterimg").text("请注意：上传的图片格式为png,jpeg,gif,jpg");
            }
        })

        $("#recordimg").change(function () {
            var recordimg=$(this).val();
            var suffix=recordimg.substr(recordimg.lastIndexOf("."));
            if (suffix==".png"||suffix==".jpeg"||suffix==".gif"||suffix==".jpg") {
                $("#checkrecordimg").text("");
            }else {
                $("#checkrecordimg").text("请注意：上传的图片格式为png,jpeg,gif,jpg");
            }
        })

        $("#cerimg").change(function () {
            var cerimg=$(this).val();
            var suffix=cerimg.substr(cerimg.lastIndexOf("."));
            if (suffix==".png"||suffix==".jpeg"||suffix==".gif"||suffix==".jpg") {
                $("#checkcerimg").text("");
            }else {
                $("#checkcerimg").text("请注意：上传的图片格式为png,jpeg,gif,jpg");
            }
        })

        $("#labelimg").change(function () {
            var labelimg=$(this).val();
            var suffix=labelimg.substr(labelimg.lastIndexOf("."));
            if (suffix==".png"||suffix==".jpeg"||suffix==".gif"||suffix==".jpg") {
                $("#checklabelimg").text("");
            }else {
                $("#checklabelimg").text("请注意：上传的图片格式为png,jpeg,gif,jpg");
            }
        })

    })
</script>
