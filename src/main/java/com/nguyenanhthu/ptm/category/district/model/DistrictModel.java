package com.nguyenanhthu.ptm.category.district.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nguyenanhthu.ptm.category.province.model.ProvinceModel;
import com.nguyenanhthu.ptm.category.ward.model.WardModel;
import com.nguyenanhthu.ptm.utils.DateAudit;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "huyen")
@Where(clause = "da_xoa IS NULL")
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@ToString
public class DistrictModel extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ten_huyen", nullable = false)
    @NotNull(message = "Tên huyện không được trống")
    @NotEmpty
    @NotBlank
    private String name;/*tên huyện*/

    @Column(name = "code", nullable = false)
    private String code;/*mã code huyện*/

    @Column(name = "tinh_id", nullable = false)
    private Integer provinceId; /*id tỉnh*/

    @Column(name = "ghi_chu")
    private String note;/*ghi chú*/

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "tinh_id", insertable = false, updatable = false)
    private ProvinceModel province;

    @OneToMany(mappedBy = "district", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<WardModel> wards = new HashSet<>();
}
