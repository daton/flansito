package org.gelatina.flansito;


import com.jcraft.jsch.ChannelSftp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.file.FileNameGenerator;
import org.springframework.integration.file.remote.session.CachingSessionFactory;
import org.springframework.integration.file.remote.session.SessionFactory;
import org.springframework.integration.sftp.outbound.SftpMessageHandler;
import org.springframework.integration.sftp.session.DefaultSftpSessionFactory;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;

import java.io.File;

@Configuration
public class ConfiguracionFtp {
String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Bean
    public SessionFactory<ChannelSftp.LsEntry> sftpSessionFactory() {
        DefaultSftpSessionFactory sf = new DefaultSftpSessionFactory(true);
        sf.setHost("home541966489.1and1-data.host");
        sf.setPort(22);
        sf.setUser("u78350917");
        sf.setPassword("carelia2017");
        sf.setAllowUnknownKeys(true);
        return new CachingSessionFactory<ChannelSftp.LsEntry>(sf);
    }

    @Bean
    @ServiceActivator(inputChannel = "toSftpChannel")
    public MessageHandler handler() {
        SftpMessageHandler handler = new SftpMessageHandler(sftpSessionFactory());
        handler.setRemoteDirectoryExpressionString("headers['/']");
        handler.setFileNameGenerator(new FileNameGenerator() {

            @Override
            public String generateFileName(Message<?> message) {
                return getNombre();
            }

        });

        return handler;
    }

    @MessagingGateway
    public interface MyGateway {

        @Gateway(requestChannel = "toSftpChannel")
        void sendToSftp(File file);

    }


}
