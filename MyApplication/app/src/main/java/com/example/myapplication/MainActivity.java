package com.example.myapplication;



import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    private SharedPreferences startShared(){
        SharedPreferences sharePreferences = getSharedPreferences("bancoapkfinal", Context.MODE_PRIVATE);
        return sharePreferences;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        EditText textologin = findViewById(R.id.textologin);
        EditText textosenha = findViewById(R.id.textosenha);
        Button buttonlogin = findViewById(R.id.buttonlogin);
        Button cadastrarbutton = findViewById(R.id.cadastrobutton);


        SharedPreferences sharedPreferences = startShared();
        String login = sharedPreferences.getString("login", "");
        String senha = sharedPreferences.getString("senha", "");


        AlertDialog.Builder alerta = new AlertDialog.Builder(MainActivity.this);
        buttonlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ;
                Banco banco = new Banco(MainActivity.this, "bancoap3", null,1);
                SQLiteDatabase readableDatabase = banco.getReadableDatabase();

                String[] campos = new String[]{"login", "senha"};
                Cursor cursor = readableDatabase.query("tabelap3", campos, null, null, null, null, null);
                cursor.moveToFirst();

                    int hasUser = 0;
                    int hasPass = 0;

                    if(textologin.getText().toString().isEmpty() && textosenha.getText().toString().isEmpty()){
                    alerta.setMessage("Usuário e Senha Vazios!");
                    alerta.show();
                }
                else if(textologin.getText().toString().isEmpty()){
                    alerta.setMessage("Usuário Vazio!");
                    alerta.show();
                }else if((textosenha.getText().toString().isEmpty())){
                    alerta.setMessage("Senha Vazia!");
                    alerta.show();
                }else {

                    for (int i = 0; i < cursor.getCount(); i++) {
                        System.out.println(cursor.getString(0));
                        System.out.println(cursor.getString(1));
                        if ((textologin.getText().toString().equals(cursor.getString(0)))) {
                            hasUser = 1;
                            if ((textosenha.getText().toString().equals(cursor.getString(1)))) {
                                Intent intent = new Intent(MainActivity.this, Tela2Activity.class);
                                startActivity(intent);
                                System.out.println("Achei");
                                hasPass = 1;
                            }
                        } else {
                            cursor.moveToNext();
                            System.out.println("To procurando");
                        }
                    }
                    if (hasUser != 1 && hasPass != 1) {
                            alerta.setMessage("Usuário e Senha inválidos!");
                            alerta.show();
                        }
                    else if (hasUser != 1) {
                        alerta.setMessage("Usuário inválido!");
                        alerta.show();
                    } else if (hasPass != 1) {
                        alerta.setMessage("Senha inválida!");
                        alerta.show();
                    }
                }


                //if (textologin.getText().toString().equals("andre")){
                    //if(textosenha.getText().toString().equals("123456")){
                        //Intent intent = new Intent(MainActivity.this, Tela2Activity.class);
                        //SharedPreferences.Editor editor =  startShared().edit();
                        //editor.putString("login", textologin.getText().toString());
                        //editor.putString("senha", textosenha.getText().toString());
                        //editor.apply();
                        //startActivity(intent);

                    //}else{
                        //alerta.setMessage("Senha Errado");
                        //alerta.show();
                    //}
                //}else{
                    //alerta.setMessage("Login Errado");
                    //alerta.show();
                //}


            }

        });


        cadastrarbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CadastroActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}