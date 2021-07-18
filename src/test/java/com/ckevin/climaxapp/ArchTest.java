package com.ckevin.climaxapp;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.ckevin.climaxapp");

        noClasses()
            .that()
            .resideInAnyPackage("com.ckevin.climaxapp.service..")
            .or()
            .resideInAnyPackage("com.ckevin.climaxapp.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.ckevin.climaxapp.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
