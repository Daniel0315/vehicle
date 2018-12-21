package com.pengshengtj.comment;

public enum Insert {
    ERROR_VEHICLE(0,"车辆基本信息添加失败"),
    ERROR_OWNER(1,"车辆所有人信息添加失败"),
    ERROR_CLIENT(2,"委托人信息添加失败"),
    SUCCESS_INSERT(3,"添加成功"),
    SUCCESS_DOWN(4,"图片下载成功"),
    ERROR_DOWN(5,"图片下载失败"),
    NOT_FILE(6,"图片不存在");
    private Integer eum;
    private String mess;

    Insert() {
    }

    Insert(Integer eum, String mess) {
        this.eum = eum;
        this.mess = mess;
    }
}
