package org.gelatina.flansito;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface RepoClave extends ReactiveMongoRepository<Clave, String> {
}
