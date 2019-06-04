package com.example.quizti.controller;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.quizti.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class CadastroActivity extends AppCompatActivity {
    private EditText nome, email, senha, confirmaSenha, endereco;
    private ImageView foto;
    private Button botaoSave;

    static final int REQUEST_IMAGE_CAPTURE = 1;
    static Uri capturedImageUri = null;
    static String pathUri;

    private Location location;
    private LocationManager locationmanager;

    private Address address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acticity_cadastro);

        inicializarComponentes();

        double latitude = 0.0;
        double longetude = 0.0;

        if(ActivityCompat.checkSelfPermission(CadastroActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getApplicationContext(), "Sem acesso ao GLPS!", Toast.LENGTH_LONG).show();
        } else {
            locationmanager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            location = locationmanager.getLastKnownLocation(locationmanager.GPS_PROVIDER);
        }

        if(location != null) {
            latitude = location.getLatitude();
            longetude = location.getLongitude();
        }

        try {

            address = buscarEndereco(latitude, longetude);

            endereco.setText(address.getAddressLine(address.getMaxAddressLineIndex()));

            endereco.setEnabled(false);
        } catch(IOException e) {
            Log.i("GPS:", e.getMessage());
        }

        botaoSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseController crud = new DatabaseController(getBaseContext());

                //converte bitmap para array de byte
                Bitmap bitmap = ((BitmapDrawable)foto.getDrawable()).getBitmap();
                Uri fileUri = getImageUri(pathUri);

                if ((bitmap.getWidth() >= 1080) || (bitmap.getHeight() >= 1040)) {

                    BitmapFactory.Options bmpOptions = new BitmapFactory.Options();
                    bmpOptions.inSampleSize = 1;

                    while ((bitmap.getWidth() >= 1080) || (bitmap.getHeight() >= 1040)) {

                        bmpOptions.inSampleSize++;
                        bitmap = BitmapFactory.decodeFile(fileUri.getPath(), bmpOptions);

                    }

                }


                ByteArrayOutputStream saida = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,80, saida);
                byte[] fotoArray = saida.toByteArray();

                String nomeString = nome.getText().toString();
                String emailString = String.valueOf(email.getText());
                String senhaString = String.valueOf(senha.getText());
                String confirmaSenhaString = String.valueOf(confirmaSenha.getText());
                String resultado;

                if(nomeString.length() <= 0){
                    Toast.makeText(getApplicationContext(), "Preencha o campo nome!", Toast.LENGTH_LONG).show();
                    nome.requestFocus();
                } else if(emailString.length() <= 0) {
                    Toast.makeText(getApplicationContext(), "Preencha o campo email!", Toast.LENGTH_LONG).show();
                    email.requestFocus();
                }  else if(senhaString.length() <= 0){
                    Toast.makeText(getApplicationContext(), "Preencha o campo senha!", Toast.LENGTH_LONG).show();
                    senha.requestFocus();
                } else {

                    if(senhaString.equals(confirmaSenhaString)){
                        resultado = crud.insereUsuario(nomeString, emailString, senhaString, confirmaSenhaString, fotoArray);
                        Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();

                        Intent intentLogin = new Intent(CadastroActivity.this, LoginMainActivity.class);
                        startActivity(intentLogin);
                    }else {
                        Toast.makeText(getApplicationContext(), "Senhas não conferem!", Toast.LENGTH_LONG).show();
                        confirmaSenha.requestFocus();
                    }

                }
            }
        });

        foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Selecione a foto de perfil"), REQUEST_IMAGE_CAPTURE);
            }
        });

        ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle("Cadastro");

    }

    private Address buscarEndereco(double lat, double longe) throws IOException{

        Geocoder geocoder;
        Address address = null;
        List<Address> addresses;

        geocoder = new Geocoder(CadastroActivity.this);

        addresses = geocoder.getFromLocation(lat, longe, 1);

        if(addresses.size() > 0){
            address = addresses.get(0);
        }

        return address;
    }

    public Uri getImageUri(String path) {
        return Uri.parse(path);
    }

    @Override
    protected void  onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
            capturedImageUri =data.getData();
            pathUri = capturedImageUri.getPath();
            foto.setImageURI(capturedImageUri);
        }
    }


    private void inicializarComponentes(){
        nome = (EditText)findViewById(R.id.edtNome);
        email = (EditText)findViewById(R.id.edtEmail);
        senha = (EditText)findViewById(R.id.edtSenha);
        confirmaSenha = (EditText)findViewById(R.id.edtConfirmaSenha);
        endereco = (EditText)findViewById(R.id.edtEndereco);
        botaoSave = (Button)findViewById(R.id.btnCadastro);
        foto = (ImageView)findViewById(R.id.imgvFoto);
    }
}
