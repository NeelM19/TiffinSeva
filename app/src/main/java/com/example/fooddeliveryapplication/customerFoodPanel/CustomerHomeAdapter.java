package com.example.fooddeliveryapplication.customerFoodPanel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddeliveryapplication.R;
import com.example.fooddeliveryapplication.UpdateDishModel;
import com.google.firebase.database.DatabaseReference;

import java.util.List;

public class CustomerHomeAdapter extends RecyclerView.Adapter<CustomerHomeAdapter.ViewHolder> {

    private Context mcontext;
    private List<UpdateDishModel>updateDishModelsList;
    DatabaseReference databaseReference;
    private Object Glide;

    public CustomerHomeAdapter(Context context , List<UpdateDishModel>updateDishModelslist){

        this.updateDishModelsList = updateDishModelslist;
        this.mcontext = context;
    }




    @NonNull
    @Override
    public CustomerHomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.customer_menudish,parent,false);
        return new CustomerHomeAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final UpdateDishModel updateDishModel = updateDishModelsList.get(position);
//        Glide.with(mcontext).load(updateDishModel.getImageURL()).into(holder.imageView);
        holder.Dishname.setText(updateDishModel.getPrice());
        updateDishModel.getRandomUID();
        updateDishModel.getChefId();
        holder.Price.setText("Price: "+updateDishModel.getPrice()+"Rs");

    }

    @Override
    public int getItemCount() {
        return updateDishModelsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView Dishname,Price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.menu_image);
            Dishname = itemView.findViewById(R.id.dishname);
            Price = itemView.findViewById(R.id.dishprice);
        }

    }
}
