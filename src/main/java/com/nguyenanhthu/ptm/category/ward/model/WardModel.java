package com.nguyenanhthu.ptm.category.ward.model;

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

@Entity
@Table(name = "xa")
@Where(clause = "da_xoa IS NULL")
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@ToString
public class WardModel extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ten_xa", nullable = false)
    @NotNull(message = "Tên xã không được trống")
    @NotEmpty
    @NotBlank
    private String name;/*tên xã*/

    @Column(name = "code", nullable = false)
    private String code;/*mã code xã*/

    @Column(name = "huyen_id", nullable = false)
    private Integer districtId;/*id xã*/

    @Column(name = "ghi_chu")
    private String note;/*ghi chú*/

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "huyen_id", insertable = false, updatable = false)
    private DistrictModel district;
}