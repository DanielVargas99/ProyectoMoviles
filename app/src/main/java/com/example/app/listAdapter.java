package com.example.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class listAdapter extends RecyclerView.Adapter<listAdapter.ViewHolder> {

    private List<listaCartas> cartas;
    private LayoutInflater inflater;
    private Context context;

    public listAdapter(List<listaCartas> lista, Context context){
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.cartas = lista;
    }

    @Override
    public int getItemCount(){
        return cartas.size();
    }

    @Override
    public listAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int ViewType){
        View view = inflater.inflate(R.layout.cartas, null);
        return new listAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final listAdapter.ViewHolder holder, final int position){
        holder.bindData(cartas.get(position));
    }

    public void setItems(List<listaCartas> items){
        cartas = items;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView carta, jugador;
        TextView nombre, edad, posicion, altura, equipos;

        ViewHolder(View itemView){
            super(itemView);
            carta = itemView.findViewById(R.id.iconoCarta);
            jugador = itemView.findViewById(R.id.jugador);
            nombre = itemView.findViewById(R.id.nomJugador);
            edad = itemView.findViewById(R.id.edadJugador);
            posicion = itemView.findViewById(R.id.posJugador);
            altura = itemView.findViewById(R.id.altJugador);
            equipos = itemView.findViewById(R.id.eqJugador);
        }


        void bindData(final listaCartas item){

            nombre.setText(item.getNombre());
            edad.setText(item.getEdad());
            posicion.setText(item.getPosicion());
            altura.setText(item.getAltura());
            equipos.setText(item.getNroEquipos());

            Glide.with(itemView)
                    .load(item.getLink())
                    .fitCenter()
                    .centerCrop()
                    .into(jugador);
        }
    }
}
