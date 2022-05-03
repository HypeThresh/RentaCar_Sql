package com.example.rentacar_sql.adap_list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.rentacar_sql.R;
import com.example.rentacar_sql.objects.Vehiculos_Clientes;

import java.util.ArrayList;

public class vehiculo_clientes_lista extends RecyclerView.Adapter<vehiculo_clientes_lista.vehiculosClienteViewHolder>{
    ArrayList<Vehiculos_Clientes> listaCCvehiculos;

    public vehiculo_clientes_lista( ArrayList<Vehiculos_Clientes> listaCCvehiculos){
        this.listaCCvehiculos = listaCCvehiculos;
    }
    @NonNull
    @Override
    public vehiculosClienteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_vehiculos_clientes_adapter,null,false);
        return new vehiculosClienteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull vehiculosClienteViewHolder holder, int position) {
        holder.vNombre.setText(listaCCvehiculos.get(position).getModelo());
        holder.vPlaca.setText("# "+ listaCCvehiculos.get(position).getPlaca());
        holder.vInicio.setText(listaCCvehiculos.get(position).getFechaIni());
        holder.vFinal.setText(listaCCvehiculos.get(position).getFechFin());
        holder.vTiempo.setText(listaCCvehiculos.get(position).getTiempoAlq()+" Dias");
        holder.vPrecio.setText("$ "+listaCCvehiculos.get(position).getPrecio());





    }

    @Override
    public int getItemCount() {
        return listaCCvehiculos.size();
    }

    public class vehiculosClienteViewHolder extends RecyclerView.ViewHolder {
        TextView vNombre,vPlaca,vInicio,vFinal,vTiempo,vPrecio;


        public vehiculosClienteViewHolder(@NonNull View itemView) {
            super(itemView);
            vNombre = itemView.findViewById(R.id.CCNombre);
            vPlaca = itemView.findViewById(R.id.CCplaca);
            vInicio = itemView.findViewById(R.id.CCinicioAlquiler);
            vFinal = itemView.findViewById(R.id.CCfinalAlquilr);
            vTiempo = itemView.findViewById(R.id.CCtiempo);
            vPrecio= itemView.findViewById(R.id.CCtrecio);


        }
    }
}
