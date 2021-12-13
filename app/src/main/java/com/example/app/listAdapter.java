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

public class     listAdapter extends RecyclerView.Adapter<listAdapter.ViewHolder> {

    private List<listaCartas> cartas;
    private LayoutInflater inflater;
    private Context context;
    final listAdapter.OnItemClickListener listener;

    public interface OnItemClickListener{
        void onItemClick(listaCartas item);
    }

    public listAdapter(List<listaCartas> lista, Context context, listAdapter.OnItemClickListener listener){
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.cartas = lista;
        this.listener = listener;
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
        TextView campo1, campo2, campo3, campo4, campo5;
        TextView c1, c2, c3;
        String tipo;

        ViewHolder(View itemView){
            super(itemView);
            carta = itemView.findViewById(R.id.iconoCarta);
            jugador = itemView.findViewById(R.id.fotoPerfil);
            campo1 = itemView.findViewById(R.id.c1);
            campo2 = itemView.findViewById(R.id.c2);
            campo3 = itemView.findViewById(R.id.c3);
            campo4 = itemView.findViewById(R.id.c4);
            campo5 = itemView.findViewById(R.id.c5);
            c1 = itemView.findViewById(R.id.posicion);
            c2 = itemView.findViewById(R.id.altura);
            c3 = itemView.findViewById(R.id.ctdEquipos);
        }


        void bindData(final listaCartas item){

            tipo = item.getAtributo6();
            if (tipo.equals("j")){
                campo1.setText(item.getAtributo1());
                campo2.setText(item.getAtributo2());
                campo3.setText(item.getAtributo3());
                campo4.setText(item.getAtributo4());
                campo5.setText(item.getAtributo5());

                Glide.with(itemView)
                        .load(item.getLink())
                        .fitCenter()
                        .centerCrop()
                        .into(jugador);
            }

            if (tipo.equals("e")){
                c1.setText("Equipo actual: ");
                c2.setText("Años activo: ");
                c3.setText("Interesado en: ");
                campo1.setText(item.getAtributo1());
                campo2.setText(item.getAtributo2());
                campo3.setText(item.getAtributo3());
                campo4.setText(item.getAtributo4());
                campo5.setText(item.getAtributo5());

                Glide.with(itemView)
                        .load(item.getLink())
                        .into(jugador);
            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });

        }
    }
}
