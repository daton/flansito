package org.gelatina.flansito;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ControladorMensajeria {

    //La API para envio de mensaje del proyecto android-clinica
    String key="AIzaSyCtM2_HCch_qxkf-8mTHOTcduC1pGy5rVY";
    @Autowired
    RepoClave repoClave;
    Clave clavesita;
    Mensajeria mensa = new Mensajeria();
    /*
    Registro del celular

     */

    @PostMapping(path="/mensajeria",consumes = "application/json")
    Mono<Estatus> postearClave(@RequestBody Clave clave)throws Exception {

        System.out.println("Lo que llego "+clave);

        repoClave.save(clave).subscribe();

        System.out.println("Deespues de la supuesta insercion");
        Estatus estatus=new Estatus("maloooos ", true);
        Mono<Estatus> monito=Mono.just(new Estatus("bien todo", true));
        //   monito.subscribe();
        return monito.map(este->este);
    }


    @GetMapping("/mensajeria/{mensaje}")
    Mono<Estatus> getPorId(@PathVariable String mensaje) {




        Mono<Clave> clave =repoClave.findAll().elementAt(0);
        clave.doOnNext(clave1 -> {

            clavesita=clave1; System.out.println("La calve que se enviara es "+clavesita);
            mensa.setTo(clavesita.getToken());
        }).subscribe();


        System.out.println("Clave a enviar "+clave);



        Data data =new Data();
        data.setTitle("yo");
        data.setBody(mensaje);
        mensa.setData(data);

        System.out.println("MENSJE A ENVIAR POR FIREBAS "+mensa);
// Ajustamos los headers
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(new MediaType("application","json"));

        //headers.add("Authorization:AIzaSyDhBeG0yER-PYaTnATRWRgFTLVZQqn8Nso");
        requestHeaders.set("Authorization","key=AIzaSyCtM2_HCch_qxkf-8mTHOTcduC1pGy5rVY");

        HttpEntity<Mensajeria> requestEntity = new HttpEntity<Mensajeria>(mensa, requestHeaders);

// Creaamos un RestTemplate
        RestTemplate restTemplate = new RestTemplate();

// Mandamos al uri del servicio web comomo consumimos normalmente un servicio Rest
        ResponseEntity<String> responseEntity = restTemplate.exchange("https://fcm.googleapis.com/fcm/send", HttpMethod.POST, requestEntity, String.class);
        String resultado = responseEntity.getBody();

        Estatus estatus=new Estatus(resultado, true);
        Mono<Estatus> monito=Mono.just(new Estatus("bien todo", true));
        //   monito.subscribe();
        return monito.map(este->este);
    }




}
