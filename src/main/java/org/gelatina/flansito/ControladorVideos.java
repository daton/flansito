package org.gelatina.flansito;


import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;

@CrossOrigin(origins = { "https://daton.github.io" , "http://localhost:4200" }, allowCredentials = "true")
@RestController
@RequestMapping("/api")
public class ControladorVideos {

    @Autowired
    ConfiguracionFtp.MyGateway gateway;

    @Autowired
    ConfiguracionFtp configuracionFtp;
    public static File convert(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }


    @CrossOrigin(origins = { "https://daton.github.io" , "http://localhost:4200" }, allowCredentials = "true")
    @RequestMapping(value="/archivaldo", method= RequestMethod.POST)
    public Estatus
    handleFileUpload(@RequestParam("file") MultipartFile file, @RequestParam("materia") String materia, @RequestParam("bloque") String bloque,
    @RequestParam("curso") String curso)throws Exception {
        String nombre=file.getOriginalFilename();
        String prefijo="";
        LocalDate date=LocalDate.now();
        int dia=  date.getDayOfYear();
        LocalTime time=LocalTime.now();
        int segundo=  time.getSecond();
        long tamano= file.getSize();
        String fileName = null;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        String extencion="";
        if (file.getSize() > 0) {
            inputStream = file.getInputStream();//Probando


            String contenido=  file.getContentType();
            int punto=nombre.indexOf(".");

            String nombreSolo=nombre.substring(0, punto);
            extencion=nombre.substring(punto+1,nombre.length());
            prefijo="dia"+dia+"segundo"+segundo;
            //   System.out.println("nombre de archivo:"+fileName);
            //Guardamos imagen, si es que hay
            // gridFsTemplate.store(inputStream,prefijo+nombre,file.getContentType());


        }

        System.out.println("LLego el archivo  "+file.getOriginalFilename());

        File filesito=convert(file);
        configuracionFtp.setNombre(materia+"-"+bloque+"-"+curso+"."+extencion);
        gateway.sendToSftp(filesito);








        System.out.println("haaaaaa que paso con el otro "+materia);

        Estatus estatus=new Estatus();
        estatus.setMensaje("SE subio correctamente");
        System.out.println("NOp se subio??");
        estatus.setSuccess(true);
        return estatus;

    }


    @RequestMapping(value="/leer-archivaldo/{daton:.+}", method= RequestMethod.GET)
    ResponseEntity<byte[]> handleFileUploadee(@PathVariable String daton)throws Exception {
        InputStream inputStream=null;
        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<byte[]> responseEntity=null;
        try{
            System.out.println("Ver la ruta"+System.getProperty("user.dir"));
            System.out.println("El nombre que llego es "+daton);
            System.out.println(new File(".").getCanonicalPath());
            FileInputStream fileIn = null;
            FileOutputStream fileOut = null;



            URL loadedResource = this.getClass().getClassLoader().getResource(daton);
            inputStream = loadedResource.openStream();

            byte[] media = IOUtils.toByteArray(inputStream);
            headers.setCacheControl(CacheControl.noCache().getHeaderValue());

            responseEntity = new ResponseEntity<>(media, headers, HttpStatus.OK);


            System.out.println("si se pudo!!!");



        }catch(Exception e){
            System.out.println("mal "+e.getMessage());

        }
return   responseEntity;


    }

}



