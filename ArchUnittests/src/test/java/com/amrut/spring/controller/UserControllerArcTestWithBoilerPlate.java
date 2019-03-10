package com.amrut.spring.controller;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.Test;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

public class UserControllerArcTestWithBoilerPlate {

    @Test
    public void testControllerArcResideInPackageController() {

        JavaClasses javaClasses = new ClassFileImporter().importPackages("com.amrut.spring");
        ArchRule myRule = classes().that().areAnnotatedWith(RestController.class)
                .should().resideInAPackage("com.amrut.spring.controller")
                ;

        myRule.check(javaClasses);

        myRule = classes()
                .that()
                        .areAnnotatedWith(RestController.class)
                .should()
                        .notBeAnnotatedWith(Component.class)
                .andShould()
                        .haveSimpleNameEndingWith("Controller");


        myRule.check(javaClasses);
    }


    @Test
    public void testControllerShouldEndWithController() {

        JavaClasses javaClasses = new ClassFileImporter().importPackages("com.amrut.spring");
        ArchRule myRule = classes()
                .that()
                .areAnnotatedWith(RestController.class)
                .should()
                .notBeAnnotatedWith(Component.class)
                .andShould()
                .haveSimpleNameEndingWith("Controller");


        myRule.check(javaClasses);
    }
}
