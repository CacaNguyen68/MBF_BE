package com.nguyenanhthu.ptm.category.department.repository;

import com.nguyenanhthu.ptm.category.department.model.DepartmentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentModel, Long> {
    boolean existsByAbbreviationAndName(String abbreviation, String name);
}
