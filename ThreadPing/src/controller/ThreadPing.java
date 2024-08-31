package controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ThreadPing extends Thread
{
    private String nome;
    private String servidor;

    public ThreadPing(String nome, String servidor)
    {
        this.nome = nome;
        this.servidor = servidor;
    }

    @Override
    public void run() 
    {
        try {
            String proc = "ping -4 -c 10 " + this.servidor;
            String[] procArr = proc.split(" ");
            Process p = Runtime.getRuntime().exec(procArr);
            InputStreamReader procOut = new InputStreamReader(p.getInputStream());
            BufferedReader buffer = new BufferedReader(procOut);
            String line = buffer.readLine();
            int i = 1;
            while (line != null) {
                if (line.contains("ms")) {
                    if (line.contains("rtt")) {
						sleep(100);
                        System.out.println("• " + String.format("%-7s", this.nome.toUpperCase()) +
                                           " --> Ping médio: " + line.split("/")[4] + " ms");
                    } else if (line.contains("seq")) {
                        System.out.println(String.format("%-7s", this.nome) + "(" +
                                           String.format("%2d", i++) + "/10) -> " +
                                           line.split("=")[3]);
                    }
                }
                line = buffer.readLine();
            }
            buffer.close();
            procOut.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}