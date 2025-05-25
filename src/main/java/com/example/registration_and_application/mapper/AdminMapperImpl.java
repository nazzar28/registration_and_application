package com.example.registration_and_application.mapper;

import com.example.registration_and_application.dto.AdminResponse;
import com.example.registration_and_application.entity.Admin;
import com.example.registration_and_application.mapper.AdminMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AdminMapperImpl implements AdminMapper {
    
    @Override
    public List<AdminResponse> toDtoS(List<Admin> all) {
        List<AdminResponse> adminResponses = new ArrayList<>();
        for (Admin admin: all){
            adminResponses.add(toDto(admin));
        }
        return adminResponses;
    }

    @Override
    public AdminResponse toDto(Admin admin) {
        AdminResponse adminResponse = new AdminResponse();
        adminResponse.setId(admin.getId());
        adminResponse.setName(admin.getName());
        adminResponse.setPhoneNumber(admin.getPhoneNumber());
        adminResponse.setAge(admin.getAge());
        adminResponse.setEmail(admin.getEmail());
        return adminResponse;
    }
}