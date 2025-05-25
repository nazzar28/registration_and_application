package com.example.registration_and_application.mapper;

import com.example.registration_and_application.dto.AdminResponse;
import com.example.registration_and_application.entity.Admin;

import java.util.List;

public interface AdminMapper {
    List<AdminResponse> toDtoS(List<Admin> all);

    AdminResponse toDto(Admin admin);
}