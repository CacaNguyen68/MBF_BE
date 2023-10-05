package com.nguyenanhthu.ptm.category.department.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nguyenanhthu.ptm.user.model.UserModel;
import com.nguyenanhthu.ptm.utils.DateAudit;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "phong_dai")
@Where(clause = "da_xoa IS NULL")
@JsonIgnoreProperties()
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class DepartmentModel  extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ten_phong_dai", nullable = false)
    @NotNull(message = "Tên phòng đài không được trống")
    @NotEmpty
    @NotBlank
    private String name;/*tên*/

    @Column(name = "ten_viet_tat", nullable = false)
    @NotNull(message = "Tên viết tắt không được trống")
    @NotEmpty
    @NotBlank
    private String abbreviation;/*tên viết tắt*/

    @Column(name = "loai", nullable = false)
    @NotNull(message = "Loại phòng đài không được trống")
    @Comment("0: phòng; 1: đài")
    @Enumerated(EnumType.ORDINAL)
    private Type type; // loại phòng đài

    @Column(name = "ghi_chu")
    private String note;/*ghi chú*/

    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<UserModel> users = new HashSet<>();

}
