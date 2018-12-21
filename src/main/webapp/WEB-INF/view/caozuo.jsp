
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>详情打印</title>
    <link rel="stylesheet" href="/layUI/src/css/layui.css" type="text/css">
</head>
<body>
<div class="layui-row" >
    <div class="grid-demo grid-demo-bg1" style="text-align: center;font-size: 36px;color: #888888">详情信息</div>
</div>
<div class="layui-row" style="height: 50px"></div>
<div class="layui-col-md5 layui-col-md-offset3" style="border: 1px solid silver">
    <div class="layui-row" style="height: 20px"></div>
    <div class="layui-row">
        <div class="layui-inline" style="background:#eeeeee;width: 300px;margin: 0px 10px">
            <label class="layui-form-label" style="width: 300px;text-align: left" >车辆前身照</label>
        </div>
        <div class="layui-inline">
            <button class="layui-btn layui-btn-lg layui-btn-normal" style="width: 300px" value="${insur.frontimg}" onclick="location.href='/api/print?imgfile=${insur.frontimg}'">下载图片</button>
        </div>
    </div>
    <div class="layui-row" style="height: 20px"></div>
    <div class="layui-row">
        <div class="layui-inline" style="background:#eeeeee;width: 300px;margin: 0px 10px">
            <label class="layui-form-label" style="width: 300px;text-align: left"  >车辆后身照</label>
        </div>
        <div class="layui-inline">
            <button class="layui-btn layui-btn-lg layui-btn-normal" style="width: 300px" value="${insur.afterimg}" onclick="location.href='/api/print?imgfile=${insur.afterimg}'">下载图片</button>
        </div>
    </div>
    <div class="layui-row" style="height: 20px"></div>
    <div class="layui-row">
        <div class="layui-inline" style="background:#eeeeee;width: 300px;margin: 0px 10px">
            <label class="layui-form-label" style="width: 300px;text-align: left" >登记上牌防盗备案表整表照片</label>
        </div>
        <div class="layui-inline">
            <button class="layui-btn layui-btn-lg layui-btn-normal" style="width: 300px" value="${insur.recordimg}" onclick="location.href='/api/print?imgfile=${insur.recordimg}'">下载图片</button>
        </div>
    </div>
    <div class="layui-row" style="height: 20px"></div>
    <div class="layui-row">
        <div class="layui-inline" style="background:#eeeeee;width: 300px;margin: 0px 10px">
            <label class="layui-form-label" style="width: 300px;text-align: left" >身份证照片</label>
        </div>
        <div class="layui-inline">
            <button class="layui-btn layui-btn-lg layui-btn-normal" style="width: 300px" value="${insur.cerimg}" onclick="location.href='/api/print?imgfile=${insur.cerimg}'">下载图片</button>
        </div>
    </div>
    <div class="layui-row" style="height: 20px"></div>
    <div class="layui-row">
        <div class="layui-inline" style="background:#eeeeee;width: 300px;margin: 0px 10px">
            <label class="layui-form-label" style="width: 300px;text-align: left" >安装有源标签位置的照片</label>
        </div>
        <div class="layui-inline">
            <button class="layui-btn layui-btn-lg layui-btn-normal" style="width: 300px" value="${insur.labelimg}" onclick="location.href='/api/print?imgfile=${insur.labelimg}'">下载图片</button>
        </div>
    </div>
    <div class="layui-row" style="height: 20px"></div>
</div>

</body>
</html>