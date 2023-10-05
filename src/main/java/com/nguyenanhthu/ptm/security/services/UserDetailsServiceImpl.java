package com.nguyenanhthu.ptm.security.services;

import com.nguyenanhthu.ptm.user.model.UserModel;
import com.nguyenanhthu.ptm.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsServiceImplUser")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository; // UserRepository là interface tương tác với cơ sở dữ liệu

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Triển khai xác thực người dùng từ cơ sở dữ liệu
        UserModel user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        return UserDetailsImpl.build(user);
    }
}