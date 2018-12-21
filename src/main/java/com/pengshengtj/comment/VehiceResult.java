package com.pengshengtj.comment;

import java.util.Date;

public class VehiceResult {
    private Integer vid;
    private String regtime;
    private Integer ownid;
    private String ownname;
    private String ownphone;
    private Integer isinsurance;
    private String recordimg;
    private String inputstatetime;
    private String inputendtime;
    private String inputname;
    private String inputphone;
    public VehiceResult() {
    }

    public VehiceResult(Integer vid, String regtime, Integer ownid, String ownname, String ownphone, Integer isinsurance, String recordimg) {
        this.vid = vid;
        this.regtime = regtime;
        this.ownid = ownid;
        this.ownname = ownname;
        this.ownphone = ownphone;
        this.isinsurance = isinsurance;
        this.recordimg = recordimg;
    }

    public VehiceResult(Integer vid, String regtime, Integer ownid, String ownname, String ownphone, Integer isinsurance, String recordimg, String inputstatetime, String inputendtime, String inputname, String inputphone) {
        this.vid = vid;
        this.regtime = regtime;
        this.ownid = ownid;
        this.ownname = ownname;
        this.ownphone = ownphone;
        this.isinsurance = isinsurance;
        this.recordimg = recordimg;
        this.inputstatetime = inputstatetime;
        this.inputendtime = inputendtime;
        this.inputname = inputname;
        this.inputphone = inputphone;
    }

    public String getRecordimg() {
        return recordimg;
    }

    public void setRecordimg(String recordimg) {
        this.recordimg = recordimg;
    }

    public Integer getVid() {
        return vid;
    }

    public void setVid(Integer vid) {
        this.vid = vid;
    }

    public String getRegtime() {
        return regtime;
    }

    public void setRegtime(String regtime) {
        this.regtime = regtime;
    }

    public Integer getOwnid() {
        return ownid;
    }

    public void setOwnid(Integer ownid) {
        this.ownid = ownid;
    }

    public String getOwnname() {
        return ownname;
    }

    public void setOwnname(String ownname) {
        this.ownname = ownname;
    }

    public String getOwnphone() {
        return ownphone;
    }

    public void setOwnphone(String ownphone) {
        this.ownphone = ownphone;
    }

    public Integer getIsinsurance() {
        return isinsurance;
    }

    public void setIsinsurance(Integer isinsurance) {
        this.isinsurance = isinsurance;
    }

    public String getInputstatetime() {
        return inputstatetime;
    }

    public void setInputstatetime(String inputstatetime) {
        this.inputstatetime = inputstatetime;
    }

    public String getInputendtime() {
        return inputendtime;
    }

    public void setInputendtime(String inputendtime) {
        this.inputendtime = inputendtime;
    }

    public String getInputname() {
        return inputname;
    }

    public void setInputname(String inputname) {
        this.inputname = inputname;
    }

    public String getInputphone() {
        return inputphone;
    }

    public void setInputphone(String inputphone) {
        this.inputphone = inputphone;
    }

    @Override
    public String toString() {
        return "VehiceResult{" +
                "vid=" + vid +
                ", regtime='" + regtime + '\'' +
                ", ownid=" + ownid +
                ", ownname='" + ownname + '\'' +
                ", ownphone='" + ownphone + '\'' +
                ", isinsurance=" + isinsurance +
                ", recordimg='" + recordimg + '\'' +
                '}';
    }
}
