/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.util.Timer;
import java.util.TimerTask;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import vista.Login;

/**
 *
 * @author Kevin
 */
public class Principal {
    
    static int cont = 0;
    
    public static void main(String[] args){
        login();
    }
    
    public static void login(){
        int vel = 5;
        Timer timer;
        TimerTask tarea;
        
        int velocidad = vel*1000;
        //Formulario de login
       Login login = new Login();
       login.show();
       //cambio de imagenes
       tarea = new TimerTask(){
           public void run(){
               
               Icon icono;
               switch(cont){
                   case 0: cont = 1; 
                        icono = new ImageIcon(getClass().getResource("/vista/iconos/bodega1.jpg"));
                        login.jLabel2.setIcon(icono);
                        break;
                   case 1: cont = 2;
                        icono = new ImageIcon(getClass().getResource("/vista/iconos/bodega2.jpg"));
                        login.jLabel2.setIcon(icono);
                        break;
                   case 2: cont = 0;
                        icono = new ImageIcon(getClass().getResource("/vista/iconos/unsplash.jpg"));
                        login.jLabel2.setIcon(icono);
                        break;     
               }
           }
       };
       
       timer = new Timer();
       
       timer.scheduleAtFixedRate(tarea, velocidad, velocidad);
    }
    
}

