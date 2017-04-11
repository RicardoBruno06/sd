package com.example.ricardo.clienteservidor;

import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by ricardo on 10/04/2017.
 */

public abstract class Cliente {

    public static String enviar(String s){

        String modifiedSentence;

        try {

            // Cria um Socket cliente passando como parâmetro o ip e a porta do servidor
            Socket client = new Socket("10.180.41.142", 40000);

            // Cria um stream de saída
            DataOutputStream outToServer = new DataOutputStream(client.getOutputStream());

            // Cria um buffer que armazenará as informações retornadas pelo servidor
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(client.getInputStream()));


            // Disponibiliza as informações contidas em "sentence" para a stream de saída do cliente
            outToServer.writeBytes(s + "\n");



            // Atribui as informações modificadas pelo servidor na variável "modifiedSentence"
            modifiedSentence = inFromServer.readLine();

            // Imprime no console do cliente a informação retornada pelo servidor

            //System.out.println("From Server: " + modifiedSentence);

            // Fecha o Socket
            client.close();
            return modifiedSentence;
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "";

    }
}
