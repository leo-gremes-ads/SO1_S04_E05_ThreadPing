package view;

import controller.*;

public class Main 
{
    public static void main(String args[])
    {
        Thread g = new ThreadPing("Google", "www.google.com.br");
        Thread u = new ThreadPing("Uol", "www.uol.com.br");
        Thread t = new ThreadPing("Terra", "www.terra.com.br");
        if (System.getProperty("os.name").contains("Linux")) {
            g.start();
            u.start();
            t.start();
        } else {
            System.out.println("Sistema Operacional não suportado!");
        }
    }    
}
