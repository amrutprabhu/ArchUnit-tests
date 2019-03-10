package com.amrut.spring.controller;

import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaMethod;
import com.tngtech.archunit.core.domain.JavaModifier;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import com.tngtech.archunit.lang.*;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import org.junit.runner.RunWith;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(packages = "com.amrut.spring", importOptions = {ImportOption.DontIncludeTests.class})
public class UsingPredicatesAndCondttions {


    private static ArchCondition<JavaClass> haveOnlyPublicMethodsWithRequestPathAnnotation = new ArchCondition<JavaClass>("have only public methods with Request mapping") {
        @Override
        public void check(JavaClass javaClass, ConditionEvents conditionEvents) {
            for (JavaMethod method : javaClass.getMethods()) {
                if (method.getModifiers().contains(JavaModifier.PUBLIC) && !method.isAnnotatedWith(RequestMapping.class)) {
                    conditionEvents.add(SimpleConditionEvent
                            .violated(method,
                                    String.format("Method `%s` should be private or should have RequestMapping", method.getName())));
                }
            }
        }
    };


    private static DescribedPredicate<? super JavaClass> haveRequestMappingAnnotation = new DescribedPredicate<JavaClass>("have rest controller annotation") {
        @Override
        public boolean apply(JavaClass javaClass) {
            return javaClass.isAnnotatedWith(RestController.class);
        }
    };


    @ArchTest
    public static final ArchRule rule = ArchRuleDefinition.classes()
            .that(haveRequestMappingAnnotation)
            .should(haveOnlyPublicMethodsWithRequestPathAnnotation);
}
