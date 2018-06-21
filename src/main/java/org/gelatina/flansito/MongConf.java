package org.gelatina.flansito;


import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
/*
@Configuration
public class MongConf {


    @Bean
    public SimpleMongoDbFactory mongoDbFactory()throws Exception{
        //  MongoURI uri=new MongoURI("mongodb://campitos-gcm:celiesita@paulo.mongohq.com:10037/campitos-base");
        MongoClientURI uri=new MongoClientURI("mongodb+srv://campitos:topoyiyo@cluster0-d36jf.mongodb.net/test");
        return new SimpleMongoDbFactory(uri);
    }

    @Bean
    public MongoTemplate mongoTemplate()throws Exception{
        MongoTemplate mongoTemplate=new MongoTemplate(mongoDbFactory());
        return mongoTemplate;
    }



    @Bean
    public GridFsTemplate gridFsTemplate()throws Exception{
        return new GridFsTemplate(mongoDbFactory(),mappingMongoConverter());
    }
    */


@Configuration
@EnableReactiveMongoRepositories(basePackageClasses = RepoMongo.class)
public class MongConf extends AbstractReactiveMongoConfiguration {

    @Override
    protected String getDatabaseName() {
        return "heroku_q8gmnvc2";
    }

    @Bean
    @Override
    public MongoClient reactiveMongoClient() {

      //  MongoClientURI uri=new MongoClientURI("mongodb+srv://campitos:topoyiyo@cluster0-d36jf.mongodb.net");
        MongoClient client=MongoClients.create("mongodb://campitos:unitec2018@ds263500.mlab.com:63500/heroku_q8gmnvc2");

        return client;
    }

    @Bean
    public ReactiveMongoTemplate reactiveMongoTemplate() {
        return new ReactiveMongoTemplate(reactiveMongoClient(), getDatabaseName());
    }
}


