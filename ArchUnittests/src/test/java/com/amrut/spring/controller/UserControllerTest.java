package com.amrut.spring.controller;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.runner.RunWith;
import org.springframework.web.bind.annotation.RestController;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(packages = "com.amrut.spring",importOptions = {ImportOption.DontIncludeTests.class})
public class UserControllerTest {

    @ArchTest
    public final static ArchRule controller_reside_in_controller_package = classes()
            .that()
                .areAnnotatedWith(RestController.class)
            .should()
                .resideInAPackage("com.amrut.spring.controller");

    @ArchTest
    public final static ArchRule controller_shouldEnd_with_controller_name = classes()
            .that()
                .areAnnotatedWith(RestController.class)
            .should()
                .haveSimpleNameEndingWith("Controller");

    @ArchTest
    public final static ArchRule controller_should_not_End_with_service_name = classes()
            .that()
                .areAnnotatedWith(RestController.class)
            .should()
                .haveSimpleNameNotEndingWith("Service");


    @ArchTest
    public static final ArchRule service_should_notaccess_controller = noClasses()
            .that()
                .resideInAPackage("com.amrut.spring.service")
            .should()
                .accessClassesThat()
                .resideInAPackage("com.amrut.spring.controller");


}
