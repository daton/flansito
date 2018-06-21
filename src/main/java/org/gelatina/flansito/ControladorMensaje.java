package org.gelatina.flansito;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class ControladorMensaje {

    @Autowired
    RepoMongo repoMongo;

    /*
    GET TODOS
     */
    @GetMapping("/mensaje")
    Flux<Mensaje> getTodos() {



      //  Flux<Mensaje> flux= Flux.fromIterable(repoMongo.findAll());
        Flux<Mensaje> flux=repoMongo.findAll();
        System.out.println("SE activo el metodo get todos");
         flux.subscribe();
        return flux.map(it -> it);
    }


    /*
    Buscar por Id
     */

    @GetMapping("/mensaje/{id}")
    Mono<Mensaje> getPorId(@PathVariable String id) {


      //  Mono<Mensaje> monito =Mono.just(repoMongo.findById(id).get());

        Mono<Mensaje> monito =repoMongo.findById(id);
        System.out.println("SE activo el metodo get todos");
        monito.subscribe();
        return monito.map(it -> it);
    }



    /*

    Post para insertar
     */

    @PostMapping(path="/mensaje",consumes = "application/json")
    Mono<Estatus> getPorId(@RequestBody Mensaje mensa)throws Exception {

        System.out.println("Lo que llego "+mensa);

        repoMongo.save(mensa).subscribe();

        System.out.println("Deespues de la supuesta insercion");
        Estatus estatus=new Estatus("maloooos ", true);
        Mono<Estatus> monito=Mono.just(new Estatus("bien todo", true));
     //   monito.subscribe();
        return monito.map(este->este);
    }

    /*
    ACTUALIZAR
     */
    @PutMapping(path = "/mensaje" , consumes = "application/json")
    Mono<Estatus> actualizar(@RequestBody Mensaje mensa)throws Exception {

        System.out.println("LLego bien "+mensa);
        Mensaje men=mensa;
        repoMongo.save(mensa).subscribe();
        Mono<Estatus> monito=Mono.just(new Estatus("Mensaje Actualizado", true));
          //  monito.subscribe();
        return monito.map(topo->topo);
    }


    /*
    BORRAR CON SOLO REACTIVO
     */



    @DeleteMapping("/mensaje")
    Mono<Estatus> borrarPorId(@RequestBody Mensaje mensa) {


      repoMongo.delete(mensa).subscribe();
        System.out.println("SE  va a borrar un registro");
        Estatus estatus=new Estatus("maloooos ", true);
        Mono<Estatus> monito=Mono.just(new Estatus("bien todo", true));

        monito.subscribe();
        return monito.map(it -> it);
    }



}
