package com.nguyenanhthu.ptm.seeder.service;

import com.nguyenanhthu.ptm.category.department.model.DepartmentModel;
import com.nguyenanhthu.ptm.category.department.model.Type;
import com.nguyenanhthu.ptm.category.department.repository.DepartmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
@Slf4j
@Service
public class DepartmentSeederService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public void seedDepartment() {
        try {
            File resource = new ClassPathResource("data/department.xlsx").getFile();
            if (!resource.exists()) {
                throw new FileNotFoundException("File import không tồn tại!");
            }

            XSSFWorkbook workbook = new XSSFWorkbook(resource);
            XSSFSheet sheet = workbook.getSheetAt(0);
            if (departmentRepository.count() == 0) {
                for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
                    XSSFRow row = sheet.getRow(i);
                    XSSFCell name = row.getCell(0);
                    XSSFCell abbreviation = row.getCell(1);
                    XSSFCell type = row.getCell(2);

                    String departmentAbbreviation = abbreviation.getStringCellValue();

                    // Kiểm tra trùng lặp theo tên
                    if (!departmentRepository.existsByAbbreviationAndName(departmentAbbreviation, name.getStringCellValue())) {
                        DepartmentModel department = new DepartmentModel();
                        department.setName(name.getStringCellValue());
                        department.setAbbreviation(departmentAbbreviation);
                        if ("Đài".equals(type.getStringCellValue())) {
                            department.setType(Type.DAI);
                        } else {
                            department.setType(Type.PHONG);
                        }
                        department.setCreatedBy("System");
                        department.setUpdatedBy("System");
                        // Lưu bản ghi mới vào cơ sở dữ liệu
                        departmentRepository.save(department);
                    }
                }
                log.info("Nạp dữ liệu phòng đài thành công!");
            }
        } catch (Exception e) {
            log.error("Seeding throw exception:", e);
        }
    }
}
