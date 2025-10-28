package org.formacion.procesos.repositories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FileRepositoryTest {

    FileRepository fileRepository;

    @BeforeEach
    void BeforeEach(){
        fileRepository = new FileRepository();
        fileRepository.setFileName("fichero-test.txt");
    }

    @Test
    void addContentTest(){
        boolean resultado = fileRepository.add("texto");
        Assertions.assertTrue(resultado,"no se ha obtenido el resultado esperado");
    }
}
