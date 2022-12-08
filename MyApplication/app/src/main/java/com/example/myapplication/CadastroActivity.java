package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class CadastroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);


        EditText cadastrologin = findViewById(R.id.cadastrologin);
        EditText cadastrosenha = findViewById(R.id.cadastrosenha);
        Button cadastrarbutton = findViewById(R.id.cadastrarbutton);
        Button voltarbutton = findViewById(R.id.voltarbutton);


        AlertDialog.Builder alerta = new AlertDialog.Builder(CadastroActivity.this);
        Banco banco = new Banco(CadastroActivity.this, "bancoap3", null,1);
        cadastrarbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cadastrologin.getText().toString().isEmpty() && cadastrosenha.getText().toString().isEmpty()) {
                    alerta.setMessage("Usuário e Senha para cadastro vazios");
                    alerta.show();
                } else if (cadastrologin.getText().toString().isEmpty()) {
                    alerta.setMessage("Usuário de cadastro vazio");
                    alerta.show();
                } else if (cadastrosenha.getText().toString().isEmpty()) {
                    alerta.setMessage("Senha de cadastro vazia");
                    alerta.show();
                } else {
                    String a = cadastrologin.getText().toString();
                    String b = cadastrosenha.getText().toString();
                    SQLiteDatabase writableDatabase = banco.getWritableDatabase();
                    writableDatabase.execSQL("INSERT INTO tabelap3(Login, Senha) VALUES ('" + a + "','" + b + "')");

                    cadastrologin.setText("");
                    cadastrosenha.setText("");

                    Intent intent = new Intent(CadastroActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }

        });
        voltarbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CadastroActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }


}