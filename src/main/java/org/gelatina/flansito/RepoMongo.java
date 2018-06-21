package org.gelatina.flansito;


import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface RepoMongo extends ReactiveMongoRepository<Mensaje, String> {
}
