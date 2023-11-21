package com.cifpceuta.cardviewapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Item_adapter extends RecyclerView.Adapter<Item_adapter.ViewHolder> {

    ArrayList<String> list_items;

    public Item_adapter(ArrayList<String> list_items) {
        this.list_items = list_items;
    }

    @NonNull
    @Override
    public Item_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.element_layout,parent,false);


        return new Item_adapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Item_adapter.ViewHolder holder, int position) {

        holder.bindData(list_items.get(position));

    }

    @Override
    public int getItemCount() {
        return list_items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvItem;
        Button btnBorrar;

        private Item_adapter adapter;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvItem = itemView.findViewById(R.id.tvItem);
            btnBorrar = itemView.findViewById(R.id.btnBorrar);

            btnBorrar.setOnClickListener(view -> {
                String texto_item = list_items.get(getAdapterPosition());
                        Toast.makeText(itemView.getContext(),"Elemento eliminado "+texto_item,Toast.LENGTH_LONG).show();
                        list_items.remove(getAdapterPosition());
                        notifyItemRemoved(getAdapterPosition());

                    }
                    );

        }

        void bindData(final String item){ tvItem.setText(item);}
    }


}


