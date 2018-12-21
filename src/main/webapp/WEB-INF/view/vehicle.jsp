<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <title>天津车辆查询系统</title>
    <link rel="stylesheet" href="/layUI/src/css/layui.css" type="text/css">
</head>
<body>
<div class="layui-row" >
    <div class="grid-demo grid-demo-bg1" style="text-align: center;font-size: 36px;color: #888888">天津车辆查询系统</div>
</div>
<div class="layui-row" style="height: 30px"></div>
<div class="layui-row">
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
        <legend>查询条件：</legend>
    </fieldset>
</div>
<form class="layui-form" action="/api/vehicle" method="get" id="form">
    <div class="layui-inline layui-col-space20">
        <div class="layui-inline">
            <label class="layui-form-label">登记日期</label>
        </div>
        <div class="layui-input-inline ">
            <input type="text" name="starttime" id="starttime"  placeholder="yyyy-MM-dd" autocomplete="off"  class="layui-input">
        </div>
        <div class="layui-inline">
            <label >至</label>
        </div>
        <div class="layui-input-inline">
            <input type="text" name="endtime" id="endtime" placeholder="yyyy-MM-dd" autocomplete="off"  class="layui-input">
        </div>
        <div class="layui-inline">
            <label > 车辆所有人姓名:</label>
        </div>
        <div class="layui-input-inline">
            <input type="text" name="ownname" id="ownname" class="layui-input">
        </div>
        <div class="layui-inline">
            <label> 车辆所有人联系电话:</label>
        </div>
        <div class="layui-input-inline">
            <input type="text" name="ownphone" id="ownphone" class="layui-input">
        </div>
        <button class="layui-btn" id="select" lay-submit >查询</button>
        <button class="layui-btn layui-btn-danger">清空</button>
    </div>
</form>
<div class="layui-row" style="height: 30px"></div>
<div class="layui-form">
    <table id ="layui-table" class="layui-table" lay-filter="vehic">
        <colgroup>
            <col width="300">
            <col width="300">
            <col width="300">
            <col width="300">
            <col width="300">
        </colgroup>
        <tr><button class="layui-btn layui-btn-normal layui-btn-radius" style="position: relative;left: 1300px" onclick="location.href='/api/insertview'">添加信息</button></tr>
        <thead>
            <th lay-data="{align:'center'}">操作</th>
            <th lay-data="{field: 'regtime'}">登记时间</th>
            <th lay-data="{field: 'ownname'}">车辆所有人姓名</th>
            <th lay-data="{field: 'ownphone'}">车辆所有人联系电话</th>
            <th lay-data="{field: 'isinsurance'}">有无保险</th>
        </thead>
        <tbody>
           <c:forEach items="${vehice}" var="veh">
               <tr>
                   <td>
                       <button class="layui-btn layui-btn-sm"><i class="layui-icon"></i></button>
                       <button class="layui-btn layui-btn-sm" onclick="location.href='/api/printview?vid=${veh.vid}'">详情</button>
                       <button class="layui-btn layui-btn-sm" onclick="location.href='/api/print?imgfile=${veh.recordimg}'">下载</button>
                       <button class="layui-btn layui-btn-sm"  lay-event="del"><i class="layui-icon"></i></button>

                   </td>
                   <td>${veh.regtime}</td>
                   <td>${veh.ownname}</td>
                   <td>${veh.ownphone}</td>
                   <td>
                       <c:if test="${veh.isinsurance==1}">有</c:if>
                       <c:if test="${veh.isinsurance==0}">无</c:if>
                   </td>
               </tr>
           </c:forEach>

        </tbody>
    </table>
</div>
<div class="layui-row">
    <div id="page"></div>
</div>
<script type="text/javascript" src="/layUI/src/layui.js"></script>
<script type="text/javascript" src="/js/jquery-3.3.1.min.js"></script>
<script>
    layui.use(['table','laydate'],function () {
        var table = layui.table
            ,laydate = layui.laydate;

        laydate.render({
            elem: '#endtime'
        });
        laydate.render({
            elem: '#starttime'
        });
        //监听行工具事件
        table.on('tool(vehic)', function(obj){
            var data = obj.data //获得当前行数据
                ,layEvent = obj.event; //获得 lay-event 对应的值
            if(layEvent === 'del'){
                layer.confirm('真的删除行么', function(index){
                    obj.del(); //删除对应行（tr）的DOM结构
                    layer.close(index);
                    //向服务端发送删除指令
                    window.location.href="/api/delete?vid=${veh.vid}";
                });
        });
    })

</script>
</body>
</html>
