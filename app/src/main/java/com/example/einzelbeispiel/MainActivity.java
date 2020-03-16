package com.example.einzelbeispiel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    Button senden;
    Button rechnen;
    EditText matrikelNr;
    TextView ergebnis;
    EditText eingabeFeld;
    boolean prim=true;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        matrikelNr = findViewById(R.id.editText);
        ergebnis = findViewById((R.id.textView3));
        rechnen = findViewById(R.id.rechnen);
        eingabeFeld = findViewById(R.id.editText);
        senden = findViewById(R.id.senden);

    }



    public void Senden(View v) {
        String matrikelNrT = eingabeFeld.getText().toString();

        TCP tcp = new TCP(matrikelNrT);
        tcp.start();

        try {
            tcp.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ergebnis.setText(tcp.modifiedSentence);
    }

    public void Rechnen (View v){
        char [] a = matrikelNr.getText().toString().toCharArray();
        int j = 0;
        String erg = "";


        for(int i=0;i<a.length;i++){
            prim=true;
            isPrime(Integer.parseInt(String.valueOf(a[i])));
            if(prim==true){
                erg += a[i];
            }
        }

        ergebnis.setText(erg);

    }

    public void isPrime(int zahl){
        if(zahl==1 || zahl ==0){
            prim=false;
        }
        else if(zahl%2==0 && zahl!=2){
            prim=false;
        }
        else if(zahl%3==0 && zahl!=3){
            prim=false;
        }
    }

}



