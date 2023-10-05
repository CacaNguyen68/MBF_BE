package com.nguyenanhthu.ptm.authentication.controller;

import com.nguyenanhthu.ptm.authentication.request.RegisterRequest;
import com.nguyenanhthu.ptm.authentication.response.MessageResponse;
import com.nguyenanhthu.ptm.category.department.repository.DepartmentRepository;
import com.nguyenanhthu.ptm.security.jwt.AuthTokenFilter;
import com.nguyenanhthu.ptm.security.jwt.JwtUtils;
import com.nguyenanhthu.ptm.security.services.TokenService;
import com.nguyenanhthu.ptm.security.services.UserDetailsServiceImplConfig;
import com.nguyenanhthu.ptm.user.model.LoginStatus;
import com.nguyenanhthu.ptm.user.model.TypeAccount;
import com.nguyenanhthu.ptm.user.model.UserModel;
import com.nguyenanhthu.ptm.user.repository.UserRepository;
import com.nguyenanhthu.ptm.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthenticationController {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserService userService;
    @Autowired
    private AuthTokenFilter authTokenFilter;
    @Autowired
    private UserDetailsServiceImplConfig userDetailsService;
    @Autowired
    private AuthenticationProvider authenticationProvider;
    @Autowired
    private DepartmentRepository departmentRepository;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest) {
        if (userRepository.existsByUsername(registerRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(MessageResponse.builder().message("Lỗi: Tên người dùng đã được sử dụng!").build());
        }
        /*check request*/
        if (registerRequest.getFullName().isEmpty()) {
            return ResponseEntity.ok("Tên đầy đủ hoặc người khởi tạo bị thiếu!");
        }
        if (!departmentRepository.existsById((long) registerRequest.getDepartmentId())) {
            return ResponseEntity.ok("Phòng đài không tồn tại trong hệ thống!");
        }
        if (registerRequest.getTypeAccount().name() == TypeAccount.ADMIN.name()
                || registerRequest.getTypeAccount().name() == TypeAccount.SUPER_ADMIN.name()
                || registerRequest.getTypeAccount().name() == TypeAccount.USER.name()) {
            if (registerRequest.getTypeAccount().name() == TypeAccount.ADMIN.name() ||
                    registerRequest.getTypeAccount().name() == TypeAccount.USER.name()) {
                registerRequest.setPassword("$ptm2022");
            }

            // Create new user's account
            UserModel user = new UserModel(
                    registerRequest.getUsername(),
                    encoder.encode(registerRequest.getPassword()),
                    null,
                    registerRequest.getFullName(),
                    registerRequest.getPhone(),
                    registerRequest.getAvatar(),
                    registerRequest.getPosition(),
                    registerRequest.getDepartmentId(),
                    registerRequest.getNote(),
                    registerRequest.getGender(),
                    registerRequest.getTypeAccount(),
                    registerRequest.getActiveStatus());
            UserModel saved = userRepository.save(user);
            if (saved != null) {
                return ResponseEntity.ok().body(MessageResponse.builder().message("Người dùng đã tạo thành công!").build());
            } else {
                return ResponseEntity.ok().body(MessageResponse.builder().message("Tạo người dùng thất bại").build());
            }
        }
        return ResponseEntity.ok().body(MessageResponse.builder().message("Loại tài khoản không có trong từ điển!").build());
    }

}
