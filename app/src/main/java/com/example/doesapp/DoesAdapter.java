package com.example.doesapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class DoesAdapter extends RecyclerView.Adapter<DoesAdapter.MyViewHolder> {

    Context context;
    ArrayList<MyDoes> myDoes;

    public DoesAdapter(Context c, ArrayList<MyDoes> p) {
        context = c;
        myDoes = p;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_does, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        myViewHolder.titleDoes.setText(myDoes.get(position).getTitleDoes());
        myViewHolder.descDoes.setText(myDoes.get(position).getDescDoes());
        myViewHolder.dateDoes.setText(myDoes.get(position).getDateDoes());

        final String getTitleDoes = myDoes.get(position).getTitleDoes();
        final String getDescDoes = myDoes.get(position).getDescDoes();
        final String getDateDoes = myDoes.get(position).getDateDoes();
        final String getKeyDoes = myDoes.get(position).getKeyDoes();

        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EditTaskDesk.class);
                intent.putExtra("titleDoes", getTitleDoes);
                intent.putExtra("descDoes", getDescDoes);
                intent.putExtra("dateDoes", getDateDoes);
                intent.putExtra("keyDoes", getKeyDoes);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return myDoes.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView titleDoes, descDoes, dateDoes,keyDoes;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            titleDoes = (TextView) itemView.findViewById(R.id.titledoes);
            descDoes =  (TextView) itemView.findViewById(R.id.descdoes);
            dateDoes =  (TextView) itemView.findViewById(R.id.datedoes);
        }
    }

}
