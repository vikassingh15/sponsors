package com.sponsors.service.impl;

import com.sponsors.repository.StaffRepository;
import com.sponsors.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StaffServiceImpl  implements StaffService {

    @Autowired
    private StaffRepository staffRepository;

}
