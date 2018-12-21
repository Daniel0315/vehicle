package com.pengshengtj.service;
import com.pengshengtj.comment.Insert;
import com.pengshengtj.comment.InsertResult;
import com.pengshengtj.comment.VehiceResult;

import com.pengshengtj.popj.Vehice;

import java.util.List;

public interface VehicleService {
    List<Vehice> selectAll();

    List<VehiceResult> selectByRegtime(String startTime, String endTime, String ownname, String ownphone);

    int insertVehicle(InsertResult insertResult,String path);

    InsertResult selectVegicle(Integer vid);

    int update(InsertResult insertResult,String path);

    int delete(Integer vid);

}
