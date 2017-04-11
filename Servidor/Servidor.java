/**
 * Created by ricardo on 10/04/2017.
 */
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    public static void main(String[] args) {

        String clientSentence;
        String capitalized;

        try {

            // Cria um SocketServer (Socket característico de um servidor)
            ServerSocket socket = new ServerSocket(40000);
            System.out.println("Porta 40000 aberta!");

            while(true) {

               /* Cria um objeto Socket, mas passando informações características de um servidor,
                *no qual somente abre uma porta e aguarda a conexão de um cliente
                */
                Socket connectionSocket = socket.accept();

                // Cria uma buffer que irá armazenar as informações enviadas pelo cliente
                BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));

                // Cria uma stream de sáida para retorno das informações ao cliente
                DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

                // Faz a leitura das informações enviadas pelo cliente as amazenam na variável "clientSentence"
                clientSentence = inFromClient.readLine();
				//
				
				/*aqui implementa a função do servidor*/
				
				//
                capitalized = clientSentence + "\n";

                // Imprime a a String modificada no console do servidor
                System.out.println(capitalized);

                // Retorna as informações modificadas, ao cliente
                outToClient.writeBytes(capitalized);
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
