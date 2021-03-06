package com.example.rentacar_sql.adap_list;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rentacar_sql.objects.Clientes;
import com.example.rentacar_sql.R;
import com.example.rentacar_sql.gest_clientes;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class clientes_lista extends RecyclerView.Adapter<clientes_lista.clienteViewHolder> {
    ArrayList<Clientes> listaClientes;

    public clientes_lista(ArrayList<Clientes> listaClientes){
        this.listaClientes =listaClientes;
    }
    @NonNull
    @Override
    public clienteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_clientes_adapter,null,false);
        return new clienteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull clienteViewHolder holder, int position) {
        holder.viewNombre.setText(listaClientes.get(position).getNombre());
        holder.viewId.setText(String.valueOf(listaClientes.get(position).getId()));

    }

    @Override
    public int getItemCount() {
        return listaClientes.size();
    }

    public class clienteViewHolder extends RecyclerView.ViewHolder {
        TextView viewNombre, viewId;
        FloatingActionButton verFB, editarFb;


        public clienteViewHolder(@NonNull View itemView) {
            super(itemView);
            verFB = itemView.findViewById(R.id.verFloatButtonClients);
            editarFb = itemView.findViewById(R.id.editFloatButtonClients);

            viewNombre = itemView.findViewById(R.id.nombreCtxt);
            viewId = itemView.findViewById(R.id.idCtxt);

            editarFb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, gest_clientes.class);
                    intent.putExtra("editar",true);
                    intent.putExtra("id",listaClientes.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });

            verFB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, gest_clientes.class);
                    intent.putExtra("editar",false);
                    intent.putExtra("id",listaClientes.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });

        }
    }
}
