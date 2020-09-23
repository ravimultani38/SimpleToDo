package com.example.simpletodo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
// Responsible for displaying data from the model into a role in the recycler view
public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {

    public interface OnClickListner{
        void onItemClicked(int position);
    }
    public interface OnLongClickListner{
        void onItemLongClicked(int position);
    }
    List<String> items;
    OnLongClickListner longClickListner;
    OnClickListner clickListner;

    public ItemsAdapter(List<String> items, OnLongClickListner longClickListner, OnClickListner clickListner) {
        this.items  = items;
        this.longClickListner = longClickListner;
        this.clickListner = clickListner;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // use layout inflator to inflate a view

        View todoView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        // wrap it inside a view holder and return it
        return new ViewHolder(todoView);
    }
    // Responsible for binding data to a particular view holder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Grab the item at the position
       String item =  items.get(position);

        // Bind the item into the specified view holder
        holder.bind(item);

    }

    // Tells the Rv how many items in the list
    @Override
    public int getItemCount() {
        return items.size();
    }

    //Conatiner to provide the easy access to views that represent each row of the list
    class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvItem;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItem = itemView.findViewById(android.R.id.text1);
        }

        //update the view inside the view holder with this data
        public void bind(String item) {
            tvItem.setText(item);
            tvItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListner.onItemClicked(getAdapterPosition());

                }
            });
            tvItem.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    //notify the listner which position was long pressed.
                    longClickListner.onItemLongClicked(getAdapterPosition());
                    return true;
                }
            });
        }
    }
}
