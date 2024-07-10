package br.santos.anderson.exemploprovedorconteudo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS},
                PackageManager.PERMISSION_GRANTED);
    }

    public void pegarNome(View v){
        EditText nome = (EditText)findViewById(R.id.idNome); //quero
        EditText telefone = (EditText)findViewById(R.id.idTelefone);  //forneco

        Uri endereco = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI,
                Uri.encode(telefone.getText().toString()));

        Cursor cursor = getContentResolver().query(endereco,
                new String[]{ContactsContract.PhoneLookup.DISPLAY_NAME},
                null, null, null);

        String nomeProvedor = "Não encontrei";

        if(cursor != null){
            if(cursor.moveToFirst())
                nomeProvedor = cursor.getString(0);
        }

        nome.setText(nomeProvedor);
    }
}