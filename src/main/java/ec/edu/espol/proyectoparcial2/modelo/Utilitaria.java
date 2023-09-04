/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.proyectoparcial2.modelo;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
/**
 *
 * @author luisa
 */
public class Utilitaria {
    
    private Utilitaria(){}
    
    public static int generarID(String nomfile){
        int id=1;
        try(ObjectInputStream in=new ObjectInputStream(new FileInputStream(nomfile))){
            id=((ArrayList<Object>)in.readObject()).size()+1;
        }
        catch(IOException a){
            System.out.println(a.getMessage());
        }
        catch(ClassNotFoundException b){
            System.out.println(b.getMessage());
        }
        catch(Exception d){
            System.out.println(d.getMessage());
        }
        return id;
    }
    
    public static String calcularHash(String contra) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] bytesHash = md.digest(contra.getBytes(StandardCharsets.UTF_8));
            BigInteger numero = new BigInteger(1, bytesHash);
            StringBuilder hashString = new StringBuilder(numero.toString(16));

            while (hashString.length() < 64)
                hashString.insert(0, '0');

            return hashString.toString();
        } 
        catch (NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public static Usuario obtenerUsuarioPorID(int id){   
        ArrayList<Usuario> usuarios=Usuario.readListFileSer("usuarios.ser");
        for(Usuario user:usuarios){
            if(id==user.getID())
                return user;
        }
        return null;
    }
    
    public static Usuario obtenerUsuario(String correo, String clave){   
        ArrayList<Usuario> usuarios=Usuario.readListFileSer("usuarios.ser");
        for(Usuario user:usuarios){
            if(correo.equals(user.getCorreo()) && clave.equals(user.getClave()))
                return user;
        }
        return null;
    }
    
    public static boolean correoDisponible(String correo){
        ArrayList<Usuario> usuarios=Usuario.readListFileSer("usuarios.ser");
        for(Usuario user:usuarios){
            if(user.getCorreo().equals(correo))
                return false;
        }
        return true;
    }
     
    public static Vehiculo obtenerVehiculoPorPlaca(String placa){
         ArrayList<Vehiculo> vehis=Vehiculo.readListFileSer("vehiculos.ser");
         for(Vehiculo v:vehis){
             if(v.getPlaca().equals(placa))
                 return v;
         }
         return null;
    }
    
    public static ArrayList<Vehiculo> filtrarVehiculos(ArrayList<Vehiculo> vehiculos, String parametro, String datos){
        ArrayList<Vehiculo> retorno=new ArrayList<>();
        switch(parametro){
            case "tipo":
                for(Vehiculo vehiculo:vehiculos){
                    if(String.valueOf(vehiculo.getTipo()).equals(datos))
                        retorno.add(vehiculo);
                }
                break;
                
            case "recorrido":
                double inicioRecorrido=Double.parseDouble(datos.split("-")[0]);
                double finRecorrido=Double.parseDouble(datos.split("-")[1]);
                for(Vehiculo vehiculo:vehiculos){
                    if(vehiculo.getRecorrido()>=inicioRecorrido && vehiculo.getRecorrido()<=finRecorrido)
                        retorno.add(vehiculo);
                }
                break;
                
            case "anio":
                int inicioAnio=Integer.parseInt(datos.split("-")[0]);
                int finAnio=Integer.parseInt(datos.split("-")[1]);
                for(Vehiculo vehiculo:vehiculos){
                    if(vehiculo.getAnio()>=inicioAnio && vehiculo.getAnio()<=finAnio)
                        retorno.add(vehiculo);
                }
                break;
                
            case "precio":
                double inicioPrecio=Double.parseDouble(datos.split("-")[0]);
                double finPrecio=Double.parseDouble(datos.split("-")[1]);
                for(Vehiculo vehiculo:vehiculos){
                    if(vehiculo.getPrecio()>=inicioPrecio && vehiculo.getPrecio()<=finPrecio)
                        retorno.add(vehiculo);
                }
                break;
                
        }
        return retorno;
    }
    
    public static void enviarConGMail(String destinatario, String asunto, String cuerpo) {
        //La dirección de correo de envío
        String remitente = "ventavehiculosespol@gmail.com";
        //La clave de aplicación obtenida según se explica en este artículo:
        String claveemail = "yzhvrpcjypmsxxqw";

        Properties props = System.getProperties();
        props.put("mail.smtp.host", "smtp.gmail.com");  //El servidor SMTP de Google
        props.put("mail.smtp.user", remitente);
        props.put("mail.smtp.auth", "true");    //Usar autenticación mediante usuario y clave
        props.put("mail.smtp.starttls.enable", "true"); //Para conectar de manera segura al servidor SMTP
        props.put("mail.smtp.port", "587"); //El puerto SMTP seguro de Google

        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");  //Confía en todos los certificados 

        props.put("mail.debug", "true");
        Session session = Session.getDefaultInstance(props);

        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(remitente));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));   //Se podrían añadir varios de la misma manera
            message.setSubject(asunto);
            message.setText(cuerpo);
            Transport transport = session.getTransport("smtp");
            transport.connect("smtp.gmail.com", remitente, claveemail);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        }
        catch (MessagingException me) {
            me.printStackTrace();   //Si se produce un error
        }
    }
}
