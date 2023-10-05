package com.nguyenanhthu.ptm.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nguyenanhthu.ptm.category.department.model.DepartmentModel;
import com.nguyenanhthu.ptm.utils.DateAudit;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "nguoi_dung")
@Where(clause = "da_xoa IS NULL")
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@ToString
public class UserModel extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false)
    @NotNull(message = "Username không được trống")
    @NotEmpty
    @NotBlank
    private String username;

    @Column(name = "password", nullable = false)
    @NotNull(message = "Password không được trống")
    @NotEmpty
    @NotBlank
    private String password;

    @Column(name = "token")
    private String token;

    @Column(name = "ten_day_du", nullable = false)
    @NotBlank
    @NotEmpty
    @NotNull(message = "Tên đầy đủ không được trống")
    private String fullName;/*tên đầy đủ*/

    @Column(name = "so_dien_thoai")
    private String phone;/*phone*/

    @Column(name = "anh_dai_dien")
    private String avatar;/*ảnh đại diện*/

    @Column(name = "chuc_vu")
    private String position;/*chức vụ*/

    @Column(name = "phong_dai_id")
    private Integer departmentId;/*phòng đài id*/

    @Column(name = "ghi_chu")
    private String note;/*ghi chú*/

    @Column(name = "gioi_tinh")
    @Comment("0: nam; 1: nữ")
    @Enumerated(EnumType.ORDINAL)
    private Gender gender;/*giới tính*/

    @Column(name = "loai_tai_khoan")
    @Comment("0: super admin; 1: quản trị viên; 2: tài xế")
    @Enumerated(EnumType.ORDINAL)
    private TypeAccount typeAccount;/*loại account*/

    @Column(name = "trang_thai_dang_nhap")
    @Comment("0: đăng xuất; 1: đăng nhập")
    @Enumerated(EnumType.ORDINAL)
    private LoginStatus loginStatus;/*trạng thái đăng nhập*/

    @Column(name = "trang_thai_hoat_dong")
    @Comment("0: ngừng hoạt động; 1: hoạt động")
    @Enumerated(EnumType.ORDINAL)
    private ActiveStatus activeStatus;/*trạng thái hoạt động*/

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "phong_dai_id", insertable = false, updatable = false)
    private DepartmentModel department;

}
