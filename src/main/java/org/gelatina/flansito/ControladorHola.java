package org.gelatina.flansito;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class ControladorHola {

    @GetMapping("/")
    public  String hola(){
        return "hola mundo mundo agresivoooo y marciano!!";
    }
}
