package com.pengshengtj.service.impl;

import com.pengshengtj.dao.CertifMapper;
import com.pengshengtj.popj.Certif;
import com.pengshengtj.service.CertifService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CertifServiceImpl implements CertifService {
    @Autowired
    private CertifMapper certifMapper;
    @Override
    public List<Certif> certif() {
        return certifMapper.selectAll();
    }
}
