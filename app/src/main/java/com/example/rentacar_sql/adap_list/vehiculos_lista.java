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
import com.example.rentacar_sql.gest_vehiculos;
import com.example.rentacar_sql.objects.Vehiculos;
import com.example.rentacar_sql.objects.Vehiculos_Clientes;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class vehiculos_lista extends RecyclerView.Adapter<vehiculos_lista.ViewHolderVehiculos> {
    ArrayList<Vehiculos> listaVehiculos;

    public vehiculos_lista(ArrayList<Vehiculos> listaVehiculos){
        this.listaVehiculos = listaVehiculos;
    }

    @NonNull
    @Override
    public ViewHolderVehiculos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_vehiculos_adapter,null,false);
        return new ViewHolderVehiculos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderVehiculos holder, int position) {
        try {
            holder.modelo.setText(listaVehiculos.get(position).getModelo());
            holder.idVehiculo.setText(String.valueOf(listaVehiculos.get(position).getId()));
            holder.placa.setText(listaVehiculos.get(position).getPlaca());
            holder.tipo.setText(listaVehiculos.get(position).getTipo());
            holder.estadoVehiculo.setText(listaVehiculos.get(position).getEstado());
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }

    @Override
    public int getItemCount() {
        return listaVehiculos.size();
    }

    public class ViewHolderVehiculos extends RecyclerView.ViewHolder {
        TextView idVehiculo,modelo,placa,tipo,estadoVehiculo;
        FloatingActionButton edit;

        public ViewHolderVehiculos(@NonNull View itemView) {
            super(itemView);

            idVehiculo = itemView.findViewById(R.id.idVehiculo);
            modelo = itemView.findViewById(R.id.modelo);
            placa = itemView.findViewById(R.id.placa);
            tipo = itemView.findViewById(R.id.tipoVehiculo);
            estadoVehiculo = itemView.findViewById(R.id.estadoVehiculo);
            edit = itemView.findViewById(R.id.editarVehiculoFloat);

            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, gest_vehiculos.class);
                    intent.putExtra("ID",listaVehiculos.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });
        }
    }

}
