package com.pengshengtj.service.impl;

import com.pengshengtj.dao.InsuranceMapper;
import com.pengshengtj.popj.Insurance;
import com.pengshengtj.service.InsuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InsuranceServiceImpl implements InsuranceService {
    @Autowired
    private InsuranceMapper insuranceMapper;
    @Override
    public Insurance selectInsurByVid(Integer vid) {
        return insuranceMapper.selectByVid(vid);
    }
}
