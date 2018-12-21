package com.pengshengtj.controller;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pengshengtj.comment.Insert;
import com.pengshengtj.comment.InsertResult;
import com.pengshengtj.comment.JsonResult;
import com.pengshengtj.comment.VehiceResult;
import com.pengshengtj.dao.InsuranceMapper;
import com.pengshengtj.popj.Certif;
import com.pengshengtj.popj.Insurance;
import com.pengshengtj.popj.Owner;
import com.pengshengtj.popj.Vehice;
import com.pengshengtj.service.CertifService;
import com.pengshengtj.service.InsuranceService;
import com.pengshengtj.service.VehicleService;
import com.pengshengtj.utils.DateUtils;
import com.pengshengtj.utils.ImgUtils;
import net.sf.json.JSONArray;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("api")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private CertifService certifService;
    @Autowired
    private InsuranceService insuranceService;

    @RequestMapping("vehicleview")
    public String vehicleView(){
        return "vehicle";
    }


    /**
     * 查询到的数据
     * @param starttime
     * @param endtime
     * @param ownname
     * @param ownphone
     * @param pageNum
     * @param session
     * @return
     */
    @GetMapping("vehicle")
    public String vehicle(String starttime, String endtime, String ownname, String ownphone, Integer pageNum, HttpSession session,Model model){
        if (pageNum==null){
            pageNum=1;
        }
        Page page=PageHelper.startPage(pageNum,20);
        List<VehiceResult> vehiceResultList=vehicleService.selectByRegtime(starttime,endtime,ownname,ownphone);
        PageInfo pageInfo=new PageInfo(vehiceResultList,1);
        System.out.println(pageInfo);
        model.addAttribute("vehice",vehiceResultList);
        model.addAttribute("page",pageInfo);
        return "vehicle";
    }


    @GetMapping("insertview")
    public String insertView(Model model){
       List<Certif> certifs= certifService.certif();
       model.addAttribute("cert",certifs);
       return "insertvehicle";
    }

    @PostMapping("insert")
    public String insert(InsertResult insertResult, HttpServletRequest request){
        String path=request.getServletContext().getRealPath("/")+"onload/";
        System.out.println(insertResult);
        int i=vehicleService.insertVehicle(insertResult,path);
        if (i==1){
            return "vehicle";
        }else {
           return "/api/insertview";
       }
    }

    public String update(MultipartFile file, HttpServletRequest request){
        String path=request.getServletContext().getRealPath("/")+"onload/";
       String fileimg=ImgUtils.onload(path,file);
       return fileimg;
    }
    @RequestMapping("updatevehicleview")
    public String updatevehicle(Integer vid,Model model){
        List<Certif> certifs=certifService.certif();
        InsertResult insert=vehicleService.selectVegicle(vid);
        System.out.println(insert);
        System.out.println(certifs);
        model.addAttribute("insert",insert);
        model.addAttribute("cert",certifs);
        return "updatevehicle";
    }

    @PostMapping("update")
    public String update(InsertResult insertResult,HttpServletRequest request){
        String path=request.getServletContext().getRealPath("/")+"onload/";
       int insert=vehicleService.update(insertResult,path);
        if (insert==1){
            return "vehicle";
        }else {
            return "redirect:/api/updatevehicleview";
        }
    }

    @RequestMapping("delete")
    public String delete(Integer vid){
        int deletevel=vehicleService.delete(vid);
        if ((deletevel > 0)) {
            return "vehicle";
        }else {
            return null;
        }
    }

    @RequestMapping("printview")
    public String printview(Integer vid,Model model){
        Insurance insurance=insuranceService.selectInsurByVid(vid);
        model.addAttribute("insur",insurance);
        return "caozuo";
    }

    @GetMapping("print")
    @ResponseBody
    public Insert print(String imgfile, HttpServletRequest request, HttpServletResponse response){
        if (imgfile==""){
            return Insert.NOT_FILE;
        }else {
            String fileImg=request.getServletContext().getRealPath("/")+"onload/"+imgfile;
            try {
                InputStream bis = new BufferedInputStream(new FileInputStream(new File(fileImg)));
                imgfile = URLEncoder.encode(imgfile,"UTF-8");
                response.addHeader("Content-Disposition", "attachment;filename=" + imgfile);
                response.setContentType("multipart/form-data");
                BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
                int len = 0;
                while((len = bis.read()) != -1){
                    out.write(len);
                    out.flush();
                }
                out.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return Insert.SUCCESS_DOWN;
        }
    }


}
