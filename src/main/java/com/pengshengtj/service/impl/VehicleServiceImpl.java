package com.pengshengtj.service.impl;


import com.pengshengtj.comment.Insert;
import com.pengshengtj.comment.InsertResult;
import com.pengshengtj.comment.VehiceResult;

import com.pengshengtj.dao.ClientMapper;
import com.pengshengtj.dao.InsuranceMapper;
import com.pengshengtj.dao.OwnerMapper;
import com.pengshengtj.dao.VehiceMapper;
import com.pengshengtj.popj.Client;
import com.pengshengtj.popj.Insurance;
import com.pengshengtj.popj.Owner;
import com.pengshengtj.popj.Vehice;
import com.pengshengtj.service.VehicleService;
import com.pengshengtj.utils.DateUtils;
import com.pengshengtj.utils.ImgUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehiceMapper vehiceMapper;
    @Autowired
    private OwnerMapper ownerMapper;
    @Autowired
    private ClientMapper clientMapper;

    @Autowired
    private InsuranceMapper insuranceMapper;
    @Override
    public List<Vehice> selectAll() {
        return vehiceMapper.selectAll();
    }

    /**
     * 通过条件进行查询
     * @param startTime
     * @param endTime
     * @param ownname
     * @param ownphone
     * @return
     */
    @Override
    public List<VehiceResult> selectByRegtime(String startTime, String endTime, String ownname, String ownphone) {
        /**
         * 根据登记时间
         */
        List<VehiceResult> vehiceResultList = new ArrayList<>();

        if (startTime !="" && endTime !="") {
            Date stime = DateUtils.strToDate(startTime);
            Date etime = DateUtils.strToDate(endTime);
            /**
             * 根据时间段查出来的车辆登记信息
             */
            List<Vehice> vehices = vehiceMapper.selectByRegtime(stime, etime);
            if (ownname == "") {
                /**
                 * 名字为空
                 */
                for (int i = 0; i < vehices.size(); i++) {
                    VehiceResult vehiceResult = new VehiceResult();
                   Owner owner=ownerMapper.selectByVid(vehices.get(i).getVid());
                    /**
                     * 通过vid查询登记上牌防盗备案表整表照片
                     */
                   Insurance insurance=insuranceMapper.selectByVid(vehices.get(i).getVid());
                   if (insurance.getRecordimg()!=null){
                       vehiceResult.setRecordimg(insurance.getRecordimg());
                   }
                    vehiceResult.setIsinsurance(vehices.get(i).getIsinsurance());
                    vehiceResult.setRegtime(DateUtils.dateToStr(vehices.get(i).getRegtime()));
                        vehiceResult.setVid(owner.getVid());
                        vehiceResult.setOwnphone(owner.getOwnphone());
                        vehiceResult.setOwnname(owner.getOwnname());
                        vehiceResult.setOwnid(owner.getOwnid());
                        vehiceResultList.add(vehiceResult);
                }

                return vehiceResultList;
            }

           /*
             * 根据登记时间和名字
             */
            if (ownname != "") {
                if (vehices != null && vehices.size() != 0) {
                    List<Integer> list = new ArrayList<>();
                    for (Vehice vehice : vehices) {
                        list.add(vehice.getVid());
                    }
                    List<Owner> owners = ownerMapper.selectByOweNameAndTime(ownname,ownphone, list);
                    if (owners != null && owners.size() != 0) {
                        for (Vehice vehice : vehices) {
                            for (Owner owner : owners) {
                                if (owner.getVid().equals(vehice.getVid())) {
                                    VehiceResult vehiceResult = new VehiceResult();
                                    Insurance insurance=insuranceMapper.selectByVid(vehice.getVid());
                                    if (insurance.getRecordimg()!=null){
                                        vehiceResult.setRecordimg(insurance.getRecordimg());
                                    }
                                    vehiceResult.setVid(owner.getVid());
                                    vehiceResult.setIsinsurance(vehice.getIsinsurance());
                                    vehiceResult.setRegtime(DateUtils.dateToStr(vehice.getRegtime()));
                                    vehiceResult.setOwnid(owner.getOwnid());
                                    vehiceResult.setOwnname(owner.getOwnname());
                                    vehiceResult.setOwnphone(owner.getOwnphone());
                                    vehiceResultList.add(vehiceResult);
                                }
                            }
                        }
                    }
                }
            }
            return vehiceResultList;
        }
        /**
         * 只要开始时间，没有结束时间
         */
        if(startTime!=""&&endTime==""){
            Date stime = DateUtils.strToDate(startTime);
            List<Vehice> vehices=vehiceMapper.selectByStartTime(stime);
            if (vehices!=null&&vehices.size()!=0){
                List<Integer> list = new ArrayList<>();
                for (Vehice vehice : vehices) {
                    list.add(vehice.getVid());
                }
                List<Owner> owners = ownerMapper.selectOwnerByVid(list);
                if (owners != null && owners.size() != 0) {
                    for (Vehice vehice : vehices) {
                        for (Owner owner : owners) {
                            if (owner.getVid().equals(vehice.getVid())) {
                                VehiceResult vehiceResult = new VehiceResult();
                                Insurance insurance=insuranceMapper.selectByVid(vehice.getVid());
                                if (insurance!=null){
                                    vehiceResult.setRecordimg(insurance.getRecordimg());
                                }
                                vehiceResult.setVid(owner.getVid());
                                vehiceResult.setIsinsurance(vehice.getIsinsurance());
                                vehiceResult.setRegtime(DateUtils.dateToStr(vehice.getRegtime()));
                                vehiceResult.setOwnid(owner.getOwnid());
                                vehiceResult.setOwnname(owner.getOwnname());
                                vehiceResult.setOwnphone(owner.getOwnphone());
                                vehiceResultList.add(vehiceResult);
                            }
                        }
                    }
                }
            }
            return vehiceResultList;
        }

        /**
         * 通过名字模糊查询
         */
        if (startTime == "" && endTime == "") {
            if (ownname != "") {
                List<Owner> owners = ownerMapper.selectByOweName(ownname);
                if (owners != null && owners.size() != 0) {
                    List<Integer> list = new ArrayList<>();
                    for (Owner owner : owners) {
                        list.add(owner.getVid());
                    }
                    List<Vehice> vehices = vehiceMapper.selectByVid(list);
                    for (Vehice vehice : vehices) {
                        for (Owner owner : owners) {
                            if (owner.getVid().equals(vehice.getVid())) {
                                VehiceResult vehiceResult = new VehiceResult();
                                Insurance insurance=insuranceMapper.selectByVid(vehice.getVid());
                                if (insurance.getRecordimg()!=null){
                                    vehiceResult.setRecordimg(insurance.getRecordimg());
                                }
                                vehiceResult.setVid(owner.getVid());
                                vehiceResult.setIsinsurance(vehice.getIsinsurance());
                                vehiceResult.setRegtime(DateUtils.dateToStr(vehice.getRegtime()));
                                vehiceResult.setOwnid(owner.getOwnid());
                                vehiceResult.setOwnname(owner.getOwnname());
                                vehiceResult.setOwnphone(owner.getOwnphone());
                                vehiceResultList.add(vehiceResult);
                            }
                        }
                    }
                }
            }
            return vehiceResultList;
        }
        return null;
}


    /**
     * 添加车辆信息
     * @param insertResult
     * @param path
     * @return
     */

    @Override
    public int insertVehicle(InsertResult insertResult,String path) {
        Vehice vehice=new Vehice();
        Owner owner=new Owner();
        Client client=new Client();
        int ins=0;
        int i=0;
        int owe=0;
        int cli=0;
        Insurance insurance=new Insurance();
        vehice.setFramenum(insertResult.getFramenum());
        vehice.setMotornum(insertResult.getMotornum());
        vehice.setPlatenum(insertResult.getPlatenum());
        vehice.setRegtime(new Date());
        vehice.setTheftnum(insertResult.getTheftnum());
        vehice.setVbrand(insertResult.getVbrand());
        vehice.setVbuytime(DateUtils.strToDate(insertResult.getVbuytime()));
        vehice.setVcolor(insertResult.getVcolor());
        vehice.setIsinsurance(Integer.valueOf(insertResult.getIsinsurance()));
         i=vehiceMapper.insertVehice(vehice);
        if (i>0) {

            /**
             * 车辆所有人
             */
            Vehice vid = vehiceMapper.selectVidByRegtime(vehice.getRegtime());
            owner.setCerid(Integer.valueOf(insertResult.getCerid()));
            owner.setVid(vid.getVid());
            owner.setOwnname(insertResult.getOwnname());
            owner.setOwnphone(insertResult.getOwnphone());
            owner.setCernum(insertResult.getCernum());
            owner.setOwnarea(insertResult.getOwnarea());
            owner.setOwnvillage(insertResult.getOwnvillage());
            owner.setOwnfloor(Integer.valueOf(insertResult.getOwnfloor()));
            owner.setOwnunit(Integer.valueOf(insertResult.getOwnunit()));
            owner.setOwnhouse(Integer.valueOf(insertResult.getOwnhouse()));
             owe = ownerMapper.insertOwner(owner);
            if (owe > 0) {
                /**
                 * 委托人
                 */
                Owner ownerid = ownerMapper.selectOwnidByVid(vid.getVid());
                client.setOwnid(ownerid.getOwnid());
                client.setCliname(insertResult.getCliname());
                client.setCliphone(insertResult.getCliphone());
                client.setCerid(Integer.valueOf(insertResult.getClicerid()));
                client.setCernum(insertResult.getClicernum());
                client.setCliarea(insertResult.getCliarea());
                client.setClivillage(insertResult.getClivillage());
                client.setClifloor(Integer.valueOf(insertResult.getClifloor()));
                client.setCliunit(Integer.valueOf(insertResult.getCliunit()));
                client.setClihouse(Integer.valueOf(insertResult.getClihouse()));
                 cli = clientMapper.insertClient(client);
            }
            /**
             * 赠送保险
             */
            if (insertResult.getIsinsurance().equals("1")){
                insurance.setVid(vid.getVid());
                String afterimg=ImgUtils.onload(path,insertResult.getAfterimg());
                String cerimg=ImgUtils.onload(path,insertResult.getCerimg());
                String frontimg=ImgUtils.onload(path,insertResult.getFrontimg());
                String recordimg=ImgUtils.onload(path,insertResult.getRecordimg());
                String labelimg=ImgUtils.onload(path,insertResult.getLabelimg());
                insurance.setAfterimg(afterimg);
                insurance.setFrontimg(frontimg);
                insurance.setLabelimg(labelimg);
                insurance.setRecordimg(recordimg);
                insurance.setCerimg(cerimg);
                insurance.setInsuretime(DateUtils.strToDate(insertResult.getInsuretime()));
                insurance.setLefttime(Integer.valueOf(insertResult.getLefttime()));
                ins=insuranceMapper.insert(insurance);
            }
        }
        if (ins>=0&&cli>=0&&owe>=0&&i>=0){
            return 1;
        }else {
            return 0;
        }
    }


    /**
     * 修改时在视图上显示的信息
     * @param vid
     * @return
     */
    @Override
    @Transactional
    public InsertResult selectVegicle(Integer vid) {
        InsertResult insertResult=new InsertResult();
       Vehice vehice=vehiceMapper.selectByPrimaryKey(vid);
       Owner owner=ownerMapper.selectByVid(vid);
       Insurance insurance=insuranceMapper.selectByVid(vid);
       if (owner!=null&&owner!=null){
           Client client=clientMapper.selectClient(owner.getOwnid());
           insertResult.setVid(String.valueOf(vehice.getVid()));
           insertResult.setCerid(String.valueOf(owner.getCerid()));
           insertResult.setCernum(owner.getCernum());
           insertResult.setCliarea(client.getCliarea());
           insertResult.setClicerid(String.valueOf(client.getCerid()));
           insertResult.setClifloor(String.valueOf(client.getClifloor()));
           insertResult.setClicernum(client.getCernum());
           insertResult.setClihouse(String.valueOf(client.getClihouse()));
           insertResult.setCliname(client.getCliname());
           insertResult.setCliphone(client.getCliphone());
           insertResult.setCliunit(String.valueOf(client.getCliunit()));
           insertResult.setClivillage(String.valueOf(client.getClivillage()));
           insertResult.setOwnarea(String.valueOf(owner.getOwnarea()));
           insertResult.setOwnfloor(String.valueOf(owner.getOwnfloor()));
           insertResult.setOwnhouse(String.valueOf(owner.getOwnhouse()));
           insertResult.setOwnid(String.valueOf(owner.getOwnid()));
           insertResult.setOwnname(owner.getOwnname());
           insertResult.setOwnphone(owner.getOwnphone());
           insertResult.setOwnunit(String.valueOf(owner.getOwnunit()));
           insertResult.setOwnvillage(String.valueOf(owner.getOwnvillage()));
           insertResult.setVbrand(vehice.getVbrand());
           insertResult.setVbuytime(String.valueOf(vehice.getVbuytime()));
           insertResult.setVcolor(vehice.getVcolor());
           insertResult.setVbuytime(DateUtils.dateToStr(vehice.getVbuytime()));
           insertResult.setIsinsurance(String.valueOf(vehice.getIsinsurance()));
           insertResult.setPlatenum(vehice.getPlatenum());
           insertResult.setTheftnum(vehice.getTheftnum());
           insertResult.setFramenum(vehice.getFramenum());
           insertResult.setMotornum(vehice.getMotornum());
           insertResult.setInsuretime(DateUtils.dateToStr(insurance.getInsuretime()));
           insertResult.setLefttime(String.valueOf(insurance.getLefttime()));
       }
        return insertResult;
    }


    /**
     *修改到数据库
     * @param insertResult
     * @return
     */
    public int update(InsertResult insertResult,String path) {
        Vehice vehice = new Vehice();
        Owner owner = new Owner();
        Client client = new Client();
        Insurance insurance=new Insurance();
        /**
         * 修改车辆信息和所有人信息
         */
        vehice.setVid(Integer.valueOf(insertResult.getVid()));
        vehice.setFramenum(insertResult.getFramenum());
        vehice.setMotornum(insertResult.getMotornum());
        vehice.setPlatenum(insertResult.getPlatenum());
        vehice.setRegtime(new Date());
        vehice.setTheftnum(insertResult.getTheftnum());
        vehice.setVbrand(insertResult.getVbrand());
        vehice.setVbuytime(DateUtils.strToDate(insertResult.getVbuytime()));
        vehice.setVcolor(insertResult.getVcolor());
        vehice.setIsinsurance(Integer.valueOf(insertResult.getIsinsurance()));
        owner.setVid(Integer.valueOf(insertResult.getVid()));
        owner.setCerid(Integer.valueOf(insertResult.getCerid()));
        owner.setOwnname(insertResult.getOwnname());
        owner.setOwnphone(insertResult.getOwnphone());
        owner.setCernum(insertResult.getCernum());
        owner.setOwnarea(insertResult.getOwnarea());
        owner.setOwnvillage(insertResult.getOwnvillage());
        owner.setOwnfloor(Integer.valueOf(insertResult.getOwnfloor()));
        owner.setOwnunit(Integer.valueOf(insertResult.getOwnunit()));
        owner.setOwnhouse(Integer.valueOf(insertResult.getOwnhouse()));
        int veh = vehiceMapper.updateVehice(vehice);
        int own = ownerMapper.updateOwner(owner);
        int cli = -1;
        int ins = -1;
        /**
         * 查找所有人的id
         */
        Owner ownerid = ownerMapper.selectOwnidByVid(Integer.valueOf(insertResult.getVid()));
        if (ownerid != null) {
            /**
             * 修改委托人
             */
            client.setOwnid(ownerid.getOwnid());
            client.setCliname(insertResult.getCliname());
            client.setCliphone(insertResult.getCliphone());
            client.setCerid(Integer.valueOf(insertResult.getClicerid()));
            client.setCernum(insertResult.getClicernum());
            client.setCliarea(insertResult.getCliarea());
            client.setClivillage(insertResult.getClivillage());
            client.setClifloor(Integer.valueOf(insertResult.getClifloor()));
            client.setCliunit(Integer.valueOf(insertResult.getCliunit()));
            client.setClihouse(Integer.valueOf(insertResult.getClihouse()));
            cli = clientMapper.updateClient(client);
        }
        /**
         * 修改保险
         */
        if (insertResult.getIsinsurance().equals("1")){
            insurance.setVid(Integer.valueOf(insertResult.getVid()));
            String afterimg=ImgUtils.onload(path,insertResult.getAfterimg());
            String cerimg=ImgUtils.onload(path,insertResult.getCerimg());
            String frontimg=ImgUtils.onload(path,insertResult.getFrontimg());
            String recordimg=ImgUtils.onload(path,insertResult.getRecordimg());
            String labelimg=ImgUtils.onload(path,insertResult.getLabelimg());
            insurance.setAfterimg(afterimg);
            insurance.setFrontimg(frontimg);
            insurance.setLabelimg(labelimg);
            insurance.setRecordimg(recordimg);
            insurance.setCerimg(cerimg);
            insurance.setInsuretime(DateUtils.strToDate(insertResult.getInsuretime()));
            insurance.setLefttime(Integer.valueOf(insertResult.getLefttime()));
            ins=insuranceMapper.updateByVid(insurance);
    }
        if (ins>=0&&cli>=0&&own>=0&&veh>=0){
            return 1;
        }else {
            return 0;
        }
    }

    /**
     * 删除信息
     * @param vid
     * @return
     */
    @Override
    @Transactional
    public int delete(Integer vid) {
        Vehice vehice=vehiceMapper.selectByPrimaryKey(vid);
        int deleteVeh=vehiceMapper.deleteByPrimaryKey(vid);
        Owner owner=ownerMapper.selectByVid(vid);
        int deleteOwn=ownerMapper.deleteOwner(vid);
        int deleteCli=clientMapper.deleteClient(owner.getOwnid());
        int deleteIns=0;
        if (vehice.getIsinsurance()==1){
            deleteIns=insuranceMapper.deleteByVid(vid);
        }
        if (deleteCli>0&&deleteVeh>0&&deleteOwn>0&&deleteIns>=0){
            return 1;
        }else {
            return -1;
        }
    }





}
