package com.nguyenanhthu.ptm.category.province.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nguyenanhthu.ptm.category.district.model.DistrictModel;
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
@Table(name = "tinh")
@Where(clause = "da_xoa IS NULL")
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@ToString
public class ProvinceModel extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ten_tinh", nullable = false)
    @NotNull(message = "Tên tỉnh không được trống")
    @NotEmpty
    @NotBlank
    private String name;/*tên tỉnh*/

    @Column(name = "code", nullable = false)
    private String code;/*mã code tỉnh*/

    @Column(name = "ghi_chu")
    private String note;/*ghi chú*/

    @OneToMany(mappedBy = "province", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<DistrictModel> districts = new HashSet<>();
}
