package com.nguyenanhthu.ptm.utils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@Data
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(allowGetters = true)
public class DateAudit implements Serializable {

    @Column(name = "thoi_gian_khoi_tao", updatable = false, columnDefinition = "timestamp default current_timestamp")
    @CreatedDate
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date createdAt;

    @Column(name = "nguoi_khoi_tao", updatable = false, columnDefinition = "varchar(255) default 'System'")
    @CreatedBy
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String createdBy;

    @Column(name = "thoi_gian_cap_nhat", columnDefinition = "timestamp default current_timestamp")
    @LastModifiedDate
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date updatedAt;

    @Column(name = "nguoi_cap_nhat", columnDefinition = "varchar(255) default 'System'")
    @LastModifiedBy
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String updatedBy;

    @Column(name = "da_xoa")
    private Boolean isDelete;

    public DateAudit() {
        this.isDelete = null;
    }

    @PreUpdate
    @PrePersist
    public void updateTimeStamps() {
        updatedAt = new Date();
        if (createdAt == null) {
            createdAt = new Date();
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            String username = ((UserDetails) authentication.getPrincipal()).getUsername();
            if (createdBy == null) {
                createdBy = username;
            }
            updatedBy = username;
        }
    }
}
