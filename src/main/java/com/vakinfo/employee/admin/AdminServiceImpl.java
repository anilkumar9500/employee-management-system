package com.vakinfo.employee.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;

    private final BCryptPasswordEncoder passwordEncoder =
            new BCryptPasswordEncoder();

    @Override
    public boolean login(String username, String password) {

        return adminRepository.findByUsername(username)
                .map(admin ->
                        passwordEncoder.matches(
                                password,
                                admin.getPassword()
                        )
                )
                .orElse(false);
    }
}