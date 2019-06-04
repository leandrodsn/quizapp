package com.example.quizti.controller;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quizti.R;
import com.example.quizti.model.Usuario;

import java.util.List;

public class UsuariosAdapter extends ArrayAdapter<Usuario> {
    Context contexto;
    int id;
    List<Usuario> listUsuario;

    public UsuariosAdapter(Context context, int id, List<Usuario> list) {
        super(context, id, list);
        this.contexto = context;
        this.id = id;
        this.listUsuario = list;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        View view = convertView;
        Usuario usuario;
        TextView nome;
        TextView email;
        ImageView foto;
        Bitmap raw;
        byte[] fotoArray;

        if(view == null){
            LayoutInflater inflater = LayoutInflater.from(contexto);
            view = inflater.inflate(id, parent, false);
        }

        nome = (TextView)view.findViewById(R.id.txtItemNome);
        email = (TextView)view.findViewById(R.id.txtItemEmail);
        foto = (ImageView)view.findViewById(R.id.imgView);

        usuario = listUsuario.get(position);

        nome.setText(usuario.getNome());
        email.setText(usuario.getEmail());
        fotoArray = usuario.getFoto();

        if(fotoArray != null) {
            raw = BitmapFactory.decodeByteArray(fotoArray,0,fotoArray.length);
            foto.setImageBitmap(raw);
        }

        return view;
    }
}
