package com.example.kto.blokowaniepolaczen2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    Button button1;                                                                                 //przycisk od wyswietlania kontaktu

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1=(Button) findViewById(R.id.buttonContacts);
        FrameContactsFragment frameContactsFragment=(FrameContactsFragment) getFragmentManager().findFragmentById(R.id.fragment);
    }

    public void showContacts(View view){
        Intent i=new Intent(this,ListContactsActivity.class);
        startActivity(i);                                                                           //uruchamiamy nasza nowa aktywnosc
                                                                                                    //z lista kontaktow
    }
}
