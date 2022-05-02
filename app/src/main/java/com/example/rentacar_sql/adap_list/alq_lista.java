package com.example.rentacar_sql.adap_list;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rentacar_sql.R;
import com.example.rentacar_sql.gest_alq;
import com.example.rentacar_sql.objects.AlqList;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class alq_lista extends RecyclerView.Adapter<alq_lista.ViewHolder> {
    ArrayList<AlqList> lista;

    public alq_lista (ArrayList<AlqList> lista){
        this.lista = lista;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_alq_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        try {
            holder.nombreV.setText(lista.get(position).getModelo());
            holder.nombreC.setText(lista.get(position).getNombre());
            holder.id.setText(String.valueOf(lista.get(position).getIdAlquiler()));
        }catch (Exception e){
            e.toString();
        }
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nombreC,nombreV,id;
        FloatingActionButton editar;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nombreC = itemView.findViewById(R.id.m);
            nombreV = itemView.findViewById(R.id.modelo);
            id = itemView.findViewById(R.id.idAlquiler);
            editar = itemView.findViewById(R.id.editarAlquilerFloat);

            editar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, gest_alq.class);
                    intent.putExtra("ID",lista.get(getAdapterPosition()).getIdAlquiler());
                    context.startActivity(intent);
                }
            });
        }
    }

}
