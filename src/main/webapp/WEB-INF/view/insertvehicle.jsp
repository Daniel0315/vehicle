<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>添加数据</title>
    <link type="text/css" rel="stylesheet" href="/layUI/src/css/layui.css">
    <style>
        #insurance{
            color:#FF5722;
            margin:0px auto;
        }
    </style>
</head>
<body>
<div class="layui-row" >
    <div class="grid-demo grid-demo-bg1" style="text-align: center;font-size: 36px;color: #888888">车辆基本信息</div>
</div>
<div class="layui-row" style="height: 30px"></div>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>车辆基本信息</legend>
</fieldset>
<form class="layui-form layui-form-pane1" method="post" action="/api/insert"  enctype="multipart/form-data">
    <div class="layui-row layui-col-space20">
        <div class="layui-inline">
            <div class="layui-inline">
                <label class="layui-form-label">颜色</label>
                <div class="layui-input-inline ">
                    <input type="text" autocomplete="off" name="vcolor" lay-verify="required"  placeholder="请选择颜色" class="layui-input" id="vcolor" style="width: 148px">
                </div>
                <div class="layui-inline" style="left: -11px;">
                    <div id="col"></div>
                </div>
            </div>
        </div>
        <div class="layui-inline">
            <div class="layui-inline">
                <label class="layui-form-label">车辆品牌</label>
            </div>
            <div class="layui-input-inline ">
                <input type="text" autocomplete="off" lay-verify="required" class="layui-input" name="vbrand">
            </div>
        </div>
        <div class="layui-inline">
            <div class="layui-inline">
                <label class="layui-form-label">购买时间</label>
            </div>
            <div class="layui-input-inline ">
                <input type="text" name="vbuytime" id="buytime"  lay-verify="date" placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <div class="layui-inline">
                <label class="layui-form-label">车牌号</label>
            </div>
            <div class="layui-input-inline ">
                <input type="text" autocomplete="off" name="platenum" lay-verify="required" class="layui-input" >
            </div>
        </div>
        <div class="layui-inline">
            <div class="layui-inline">
                <label class="layui-form-label">防盗标签号</label>
            </div>
            <div class="layui-input-inline ">
                <input type="text" autocomplete="off" class="layui-input"  name="theftnum">
            </div>
        </div>
        <div class="layui-inline">
            <div class="layui-inline">
                <label class="layui-form-label">车架号</label>
            </div>
            <div class="layui-input-inline ">
                <input type="text" autocomplete="off" lay-verify="required" class="layui-input"  name="framenum">
            </div>
        </div>
        <div class="layui-inline">
            <div class="layui-inline">
                <label class="layui-form-label">电机号</label>
            </div>
            <div class="layui-input-inline ">
                <input type="text" autocomplete="off" lay-verify="required" class="layui-input"  name="motornum">
            </div>
        </div>
        <div class="layui-form-item" pane>
            <label class="layui-form-label">赠送保险</label>
            <div class="layui-input-block">
                <input type="radio" id="ins1" name="isinsurance" value="1" title="有">
                <input type="radio" id="ins0" name="isinsurance" value="0" title="无" checked>
                <div class="layui-inline">
                    <span id="insurance">如果有保险，请上传车辆保险证明文件，文件格式为jpg、png、jpeg、gif</span>
                </div>
            </div>
        </div>
    </div>
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
        <legend>车辆保险证明</legend>
    </fieldset>
    <div class="insurance">
        <div class="layui-row layui-col-space30">
            <div class="layui-inline">
                <div class="layui-inline">
                    <label class="layui-form-label">投保时间</label>
                </div>
                <div class="layui-input-inline ">
                    <input type="text" name="insuretime" id="insuretime"  lay-verify="date" placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <div class="layui-inline">
                    <label class="layui-form-label">在保年限</label>
                </div>
                <div class="layui-input-inline ">
                    <input type="text" autocomplete="off" name="lefttime" lay-verify="required" class="layui-input">
                </div>
            </div>
        </div>
            <div class="layui-row layui-col-space20">
            <div class="layui-inline">
                <div class="layui-inline">
                    <label class="layui-form-label">车辆前身照</label>
                </div>
                <div class="layui-upload-drag" id="frontimg" name="frontimg">
                    <i class="layui-icon">&#xe67c;</i>
                    <p>点击上传，或将文件拖拽到此处</p>
                </div>
            </div>
            <div class="layui-inline">
                <div class="layui-inline">
                    <label class="layui-form-label">车辆后身照</label>
                </div>
                <div class="layui-upload-drag" id="afterimg" name="afterimg">
                    <i class="layui-icon">&#xe67c;</i>
                    <p>点击上传，或将文件拖拽到此处</p>
                </div>
            </div>
            <div class="layui-inline">
                <div class="layui-inline">
                    <label class="layui-form-label">登记上牌防盗备案表整表照片</label>
                </div>
                <div class="layui-upload-drag" id="recordimg" name="recordimg">
                    <i class="layui-icon">&#xe67c;</i>
                    <p>点击上传，或将文件拖拽到此处</p>
                </div>
            </div>
        </div>
        <div class="layui-row layui-col-space30">
            <div class="layui-inline">
                <div class="layui-inline">
                    <label class="layui-form-label">身份证照片</label>
                </div>
                <div class="layui-upload-drag" id="cerimg" name="cerimg">
                    <i class="layui-icon">&#xe67c;</i>
                    <p>点击上传，或将文件拖拽到此处</p>
                </div>
            </div>
            <div class="layui-inline">
                <div class="layui-inline">
                    <label class="layui-form-label">安装有源标签位置的照片</label>
                </div>
                <div class="layui-upload-drag" id="labelimg" name="labelimg">
                    <i class="layui-icon">&#xe67c;</i>
                    <p>点击上传，或将文件拖拽到此处</p>
                </div>
            </div>
        </div>
    </div>
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
        <legend>车辆所有人信息</legend>
    </fieldset>
    <div class="layui-row layui-col-space30">
        <div class="layui-inline">
            <div class="layui-inline">
                <label class="layui-form-label">姓名</label>
            </div>
            <div class="layui-input-inline ">
                <input type="text" class="layui-input" autocomplete="off" lay-verify="required"  name="ownname">
            </div>
        </div>
        <div class="layui-inline">
            <div class="layui-inline">
                <label class="layui-form-label">联系电话</label>
            </div>
            <div class="layui-input-inline ">
                <input type="text" class="layui-input" autocomplete="off" lay-verify="phone"  name="ownphone">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">证件类型</label>
            <div class="layui-input-inline">
                <select  name="cerid" lay-verify="required"  lay-search>
                    <option value="">请选择</option>
                    <c:forEach items="${cert}" var="cert">
                        <option value="${cert.cerid}">${cert.certype}</option>
                    </c:forEach>

                </select>
            </div>
        </div>
        <div class="layui-inline">
            <div class="layui-inline">
                <label class="layui-form-label">证件号码</label>
            </div>
            <div class="layui-input-inline ">
                <input type="text" class="layui-input" autocomplete="off" name="cernum" lay-verify="required"  name="platenum">
            </div>
        </div>

        <div class="layui-inline">
            <div class="layui-inline">
                <label >车辆所有人现居住地址</label>
            </div>
            <div class="layui-input-inline ">
                <input type="text" class="layui-input" autocomplete="off" lay-verify="required"  name="ownarea">
            </div>
            <div class="layui-inline">
                <label>区</label>
            </div>
            <div class="layui-input-inline ">
                <input type="text" class="layui-input" autocomplete="off" lay-verify="required"  name="ownvillage">
            </div>
            <div class="layui-inline">
                <label >小区 (宿舍楼、公寓等)</label>
            </div>
            <div class="layui-input-inline ">
                <input type="text" class="layui-input" autocomplete="off" lay-verify="required"  name="ownfloor">
            </div>
            <div class="layui-inline">
                <label>楼</label>
            </div>
            <div class="layui-input-inline ">
                <input type="text" class="layui-input" autocomplete="off" lay-verify="required"  name="ownunit">
            </div>
            <div class="layui-inline">
                <label>门</label>
            </div>
            <div class="layui-input-inline ">
                <input type="text" class="layui-input" autocomplete="off" lay-verify="required"  name="ownhouse">
            </div>
            <div class="layui-inline">
                <label >号</label>
            </div>
        </div>

        <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
            <legend>委托代办人信息</legend>
        </fieldset>
        <div class="layui-row layui-col-space30">
            <div class="layui-inline">
                <div class="layui-inline">
                    <label class="layui-form-label">代办人姓名</label>
                </div>
                <div class="layui-input-inline ">
                    <input type="text" class="layui-input" autocomplete="off" lay-verify="required"  name="cliname">
                </div>
            </div>
            <div class="layui-inline">
                <div class="layui-inline">
                    <label class="layui-form-label">联系电话</label>
                </div>
                <div class="layui-input-inline ">
                    <input type="text" class="layui-input" autocomplete="off" lay-verify="phone"  name="cliphone">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">证件类型</label>
                <div class="layui-input-inline">
                    <select name="clicerid" lay-verify="required"  lay-search>
                        <option value="">请选择</option>
                        <c:forEach items="${cert}" var="cert">
                            <option value="${cert.cerid}">${cert.certype}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="layui-inline">
                <div class="layui-inline">
                    <label class="layui-form-label">代办人证件号码</label>
                </div>
                <div class="layui-input-inline ">
                    <input type="text" class="layui-input" autocomplete="off" lay-verify="required"  name="clicernum">
                </div>
            </div>

            <div class="layui-inline">
                <div class="layui-inline">
                    <label >代办人现居住地址</label>
                </div>
                <div class="layui-input-inline ">
                    <input type="text" class="layui-input" autocomplete="off" lay-verify="required"  name="cliarea">
                </div>
                <div class="layui-inline">
                    <label>区</label>
                </div>
                <div class="layui-input-inline ">
                    <input type="text" class="layui-input" autocomplete="off" lay-verify="required"  name="clivillage">
                </div>
                <div class="layui-inline">
                    <label >小区 (宿舍楼、公寓等)</label>
                </div>
                <div class="layui-input-inline ">
                    <input type="text" class="layui-input" autocomplete="off" lay-verify="required"  name="clifloor">
                </div>
                <div class="layui-inline">
                    <label>楼</label>
                </div>
                <div class="layui-input-inline ">
                    <input type="text" class="layui-input" autocomplete="off" lay-verify="required"  name="cliunit">
                </div>
                <div class="layui-inline">
                    <label>门</label>
                </div>
                <div class="layui-input-inline ">
                    <input type="text" class="layui-input" autocomplete="off" lay-verify="required" name="clihouse">
                </div>
                <div class="layui-inline">
                    <label >号</label>
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" id="subform" lay-submit>立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
</form>
<script type="text/javascript" src="/layUI/src/layui.js"></script>

<script>
    layui.use(['form','colorpicker','laydate','upload'],function () {
        var $ = layui.$
            ,colorpicker = layui.colorpicker
            ,form=layui.form
            ,laydate=layui.laydate
            ,upload = layui.upload;

        //颜色选择
        colorpicker.render({
            elem: '#col'
            ,color: '#1c97f5'
            ,done: function(color){
                layui.$('#vcolor').val(color);
            }
        });

        laydate.render({
            elem: '#buytime'
            ,type: 'date'
        });

        laydate.render({
            elem: '#insuretime'
            ,type: 'date'
        });

        upload.render({
            elem: '#frontimg'
            ,exts:'jpg|png|gif|jpeg'
            ,auto:false
            ,method:'post'
            ,field:'frontimg'
            ,bindAction:'#subform'
            ,url: '/api/insert'

        });
        upload.render({
            elem: '#afterimg'
            ,exts:'jpg|png|gif|jpeg'
            ,auto:false
            ,method:'post'
            ,field:'afterimg'
            ,bindAction:'#subform'
            ,url: '/api/insert'

        });
        upload.render({
            elem: '#recordimg'
            ,exts:'jpg|png|gif|jpeg'
            ,auto:false
            ,field:'recordimg'
            ,method:'post'
            ,bindAction:'#subform'
            ,url: '/api/insert'

        });
        upload.render({
            elem: '#cerimg'
            ,exts:'jpg|png|gif|jpeg'
            ,auto:false
            ,method:'post'
            ,field:'cerimg'
            ,bindAction:'#subform'
            ,url: '/api/insert'

        });
        upload.render({
            elem: '#labelimg'
            ,exts:'jpg|png|gif|jpeg'
            ,auto:false
            ,method:'post'
            ,field:'labelimg'
            ,bindAction:'#subform'
            ,url: '/api/insert'

        });
    });


</script>
</body>
</html>