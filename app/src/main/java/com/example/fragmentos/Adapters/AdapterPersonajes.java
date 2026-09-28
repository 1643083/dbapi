package com.example.fragmentos.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fragmentos.R;
import com.example.fragmentos.Model.Personaje;

import java.util.ArrayList;

public class AdapterPersonajes extends RecyclerView.Adapter<AdapterPersonajes.ViewHolderPersonaje> {

    ArrayList<Personaje> listPersonajes;

    public AdapterPersonajes(ArrayList<Personaje> listaEntrada) {
        this.listPersonajes = listaEntrada;
    }

    @NonNull
    @Override
    public ViewHolderPersonaje onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_personaje, parent, false);
        return new ViewHolderPersonaje(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderPersonaje holder, int position) {
        holder.asignarDatos(listPersonajes.get(position));
    }

    @Override
    public int getItemCount() {
        return listPersonajes.size();
    }

    public class ViewHolderPersonaje extends RecyclerView.ViewHolder {
        TextView txtName, txtRace, txtGenderAff;

        public ViewHolderPersonaje(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtRace = itemView.findViewById(R.id.txtRace);
            txtGenderAff = itemView.findViewById(R.id.txtGenderAff);
        }

        public void asignarDatos(Personaje personaje) {
            txtName.setText(personaje.getName());
            txtRace.setText(personaje.getRace());
            txtGenderAff.setText(personaje.getGender() + " | " + personaje.getAffiliation());
        }
    }
}