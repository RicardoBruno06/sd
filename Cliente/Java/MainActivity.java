package com.example.ricardo.clienteservidor;

import android.os.StrictMode;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b1 = (Button) findViewById(R.id.button);
        Button b2 = (Button) findViewById(R.id.button2);
        final EditText dado = (EditText) findViewById(R.id.editText);

        b1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                //String s = dado.getText().toString();

                String[] s;
                String sttr = dado.getText().toString();
                s = sttr.split(",");

                int len2 = s.length;
                ArrayList<Integer> vi = new ArrayList<Integer>();
                for (int i = 0; i < len2; i++) {
                    vi.add(Integer.parseInt(String.valueOf(s[i])));
                }

                Collections.sort (vi, new Comparator() {
                    public int compare(Object o1, Object o2) {
                        Integer p1 = (Integer) o1;
                        Integer p2 = (Integer) o2;
                        return p1.intValue() < p2.intValue() ? -1 : (p1.intValue() > p2.intValue() ? +1 : 0);
                    }
                });



                /*/ordenação
                int aux = 0;
                for (int i = 0; i < vi.length; i++){
                    for (int j = 0; j < vi.length; j++){
                        if (vi[i] < vi[j]){
                            aux = vi[i];
                            vi[i] = vi[j];
                            vi[j] = aux;
                        }
                    }
                }*/
                String ord = "";
                for(int i = 0; i < vi.size(); i++){
                    if(i < vi.size() - 1){
                        ord += vi.get(i)+", ";
                    }
                    else{
                        ord += vi.get(i)+".";
                    }

                }

                //for (int i = 0; i < len2; i++) {
                   Toast.makeText(MainActivity.this, "result:" + ord, Toast.LENGTH_LONG).show();
                //}
            }
        });

        b2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                String ip = getIp();
                String msg = dado.getText().toString();
                String pac = Cliente.enviar(msg);
                //String pac = msg+"/"+ip;
                Toast.makeText(MainActivity.this, "result:" + pac, Toast.LENGTH_LONG).show();

            }
        });

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


    }

    public static String getIp() {
        String ipAddress = null;
        Enumeration<NetworkInterface> net;

        try {
            net = NetworkInterface.getNetworkInterfaces();
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }

        while (net.hasMoreElements()) {
            NetworkInterface element = net.nextElement();
            Enumeration<InetAddress> addresses = element.getInetAddresses();

            while (addresses.hasMoreElements()) {
                InetAddress ip = addresses.nextElement();

                if (ip.isSiteLocalAddress()) {
                    ipAddress = ip.getHostAddress();
                }
            }
        }
        return ipAddress;
    }

    /*public static int multi(int n){

        n = n*n;
        return n;
    }*/
}
