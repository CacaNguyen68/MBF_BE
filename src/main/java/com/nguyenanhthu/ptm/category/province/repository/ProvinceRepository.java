package com.nguyenanhthu.ptm.category.province.repository;

import com.nguyenanhthu.ptm.category.province.model.ProvinceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvinceRepository extends JpaRepository<ProvinceModel, Long> {
}
