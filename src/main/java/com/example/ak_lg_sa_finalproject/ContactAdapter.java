package com.example.ak_lg_sa_finalproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    //Store a member variable for PersonModel and context
    private ArrayList<PersonModel> contactArrayList;
    private Context context;
    private ClickListener<PersonModel> clickListener;

    //passing the context and contactArrayList into constructor
    public ContactAdapter(ArrayList<PersonModel> contactArrayList, Context context) {
        this.contactArrayList = contactArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        //Inflate the custom layout
        View view = layoutInflater.inflate(R.layout.contact_item_list, parent, false);

        //return a new holder instance
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        //Getting data model based on position
        PersonModel model = contactArrayList.get(position);

        //setting views based on views and data model
        holder.textFirstName.setText(model.getFirstName());
        holder.textLastName.setText(model.getLastName());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.onItemClick(model);
            }
        });
    }

    //Returns the total count of item in the list
    @Override
    public int getItemCount() {
        return contactArrayList.size();
    }

    //view holder class for initializing of views
    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView textFirstName, textLastName;
        private CardView cardView;

        public ViewHolder(@NonNull View itemView){
            super(itemView);

            textFirstName = itemView.findViewById(R.id.txtFirstName);
            textLastName = itemView.findViewById(R.id.txtLasttName);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }

    interface  ClickListener<PersonModel>{
        void onItemClick(PersonModel data);
    }


    public void setOnItemClickListener(ClickListener<PersonModel> contactClickListener)
    {
        this.clickListener = contactClickListener;
    }
}
