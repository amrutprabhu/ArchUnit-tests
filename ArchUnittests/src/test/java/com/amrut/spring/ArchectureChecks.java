package com.amrut.spring;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.runner.RunWith;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(packages = "com.amrut.spring")
public class ArchectureChecks {

    @ArchTest
    public static final ArchRule check_architecture_of_packages = layeredArchitecture()
            .layer("ControllerLayer").definedBy("com.amrut.spring.controller")
            .layer("ServiceLayer").definedBy("com.amrut.spring.service")
            .layer("RepositoryLayer").definedBy("com.amrut.spring.repository")

            .whereLayer("ControllerLayer").mayNotBeAccessedByAnyLayer()
            .whereLayer("ServiceLayer").mayOnlyBeAccessedByLayers("ControllerLayer")
            .whereLayer("RepositoryLayer").mayOnlyBeAccessedByLayers("ServiceLayer");
}
