package com.example.fragmentos.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fragmentos.R;
import com.example.fragmentos.Model.Planeta;

import java.util.ArrayList;

public class AdapterPlanetas extends RecyclerView.Adapter<AdapterPlanetas.ViewHolderPlaneta> {

    ArrayList<Planeta> listPlanetas;

    public AdapterPlanetas(ArrayList<Planeta> listaEntrada) {
        this.listPlanetas = listaEntrada;
    }

    @NonNull
    @Override
    public ViewHolderPlaneta onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_planeta, parent, false);
        return new ViewHolderPlaneta(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderPlaneta holder, int position) {
        holder.asignarDatos(listPlanetas.get(position));
    }

    @Override
    public int getItemCount() { return listPlanetas.size(); }

    public class ViewHolderPlaneta extends RecyclerView.ViewHolder {
        TextView txtName, txtDestroyed, txtDesc;

        public ViewHolderPlaneta(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtDestroyed = itemView.findViewById(R.id.txtDestroyed);
            txtDesc = itemView.findViewById(R.id.txtDesc);
        }

        public void asignarDatos(Planeta planeta) {
            txtName.setText(planeta.getName());
            if (planeta.isDestroyed()) {
                txtDestroyed.setText("Destruido");
            } else {
                txtDestroyed.setText("Indemne");
            }
            txtDesc.setText(planeta.getDescription());
        }
    }
}
