package com.amrut.spring.controller;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

import org.junit.runner.RunWith;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(packages = "com.amrut.spring")
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


}
