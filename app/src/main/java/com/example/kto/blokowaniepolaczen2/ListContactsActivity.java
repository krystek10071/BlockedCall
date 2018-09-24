package com.example.kto.blokowaniepolaczen2;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListContactsActivity extends AppCompatActivity {

    ListView listView;
    Cursor cursor;
    private static final int PERMISSIONS_REQUEST_READ_CONTACTS=100;
    ArrayList<String> contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_contacts);
        listView=(ListView)findViewById(R.id.listView);
        int permissionCheck= ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS);

        if(permissionCheck== PackageManager.PERMISSION_GRANTED){
            showContacts();
        }
        else
        {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_CONTACTS},
                    PERMISSIONS_REQUEST_READ_CONTACTS);
        }

        ArrayAdapter<String> adapter=new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, contacts
        );
        listView.setAdapter(adapter);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==PERMISSIONS_REQUEST_READ_CONTACTS){
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                //Permission is granted
                showContacts();
            }
            else
            {
                Toast.makeText(this,"Prosze nadaj uprawnienia",Toast.LENGTH_LONG).show();
            }
        }
    }

    private void showContacts(){
        cursor=getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,null,null,
                ContactsContract.Contacts.DISPLAY_NAME + " ASC");
        contacts=new ArrayList<>();
        while(cursor.moveToNext()){
            String contactName=cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String phoneNumber=cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            contacts.add("Name: "+contactName+ "\n"+"PhoneNu: "+ phoneNumber);
        }
        cursor.close();
    }

}
