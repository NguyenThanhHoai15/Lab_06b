package com.example.projectabouteplace;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.PlaceViewHolder> {

    private Context context;
    private List<Place> list;
    private DatabaseHandler databaseHandler;
   // public static String text;

    public PlaceAdapter(Context context, List<Place> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public PlaceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.place_item, parent, false);
        return new PlaceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceViewHolder holder, int position) {
        Place place =list.get(position);
        holder.placeName.setText(place.getName());
        databaseHandler = new DatabaseHandler(context);

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //delete place
                databaseHandler.deletePlace(place.getID());
                list.remove(position);
                notifyDataSetChanged();
            }
        });

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //System.out.println( MainActivity.editValue.getText().toString());
                String text = MainActivity.editValue.getText().toString();
                if(text.isEmpty())
                    MainActivity.editValue.setError("please enter place name!");
                else{
                    databaseHandler.updatePlace(place.getID(), text.trim());
                    place.setName(text.trim());
                    notifyDataSetChanged();
                    MainActivity.editValue.setText("");
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class PlaceViewHolder extends RecyclerView.ViewHolder{

        private TextView placeName;
        private ImageView delete, edit;
        private EditText editValue;
        public PlaceViewHolder(@NonNull View itemView) {
            super(itemView);
            placeName = itemView.findViewById(R.id.placeName);
            delete = itemView.findViewById(R.id.btnDelete);
            edit = itemView.findViewById(R.id.btnEdit);
        }
    }
}
