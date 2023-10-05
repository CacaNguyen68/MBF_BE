package com.nguyenanhthu.ptm.command;

import com.nguyenanhthu.ptm.seeder.service.DepartmentSeederService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final DepartmentSeederService departmentSeederService;

    public DataInitializer(DepartmentSeederService departmentSeederService) {
        this.departmentSeederService = departmentSeederService;
    }

    @Override
    public void run(String... args) throws Exception {
        departmentSeederService.seedDepartment();
    }
}
