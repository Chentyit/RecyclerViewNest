package com.example.administrator.demo3;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class FruitAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<Fruit> mFruitList;

    static class LieViewHolder extends RecyclerView.ViewHolder {
        ImageView fruitImage;
        TextView fruitName;

        private LieViewHolder(View view){
            super(view);
            fruitImage = (ImageView) view.findViewById(R.id.fruit_image);
            fruitName = (TextView) view.findViewById(R.id.fruit_name);
        }
    }

    static class TopHolder extends RecyclerView.ViewHolder{
        RecyclerView recyclerView;

        private TopHolder(View view){
            super(view);
            recyclerView = (RecyclerView) view.findViewById(R.id.recycler_top);
        }
    }

    public int getItemViewType(int position){
        int flag = 0;
        if(position == 0) {
            flag = 1;
        } else {
            flag = 2;
        }
        return flag;
     }

    public FruitAdapter(Context context, List<Fruit> fruitList) {
        this.context = context;
        mFruitList = fruitList;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        RecyclerView.ViewHolder holder = null;
        if(viewType == 1){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.top, parent, false);
            holder = new TopHolder(view);
        } else if(viewType == 2){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fruit_item, parent, false);
            holder = new LieViewHolder(view);
        }
        return holder;
    }

    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof TopHolder){
            settop((TopHolder) holder, position);
        } else if(holder instanceof LieViewHolder){
            setlie((LieViewHolder)holder, position);
        }
    }

    private void setlie(LieViewHolder holder, int position){
        Fruit fruit = mFruitList.get(position);
        holder.fruitImage.setImageResource(fruit.getImageId());
        holder.fruitName.setText(fruit.getName());
    }

    private void settop(TopHolder holder, int position){
        if (position == 0){
            FruitTop fruitTop = new FruitTop(context, mFruitList);
            LinearLayoutManager layoutManager = new LinearLayoutManager(context);
            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            holder.recyclerView.setLayoutManager(layoutManager);
            holder.recyclerView.setHasFixedSize(false);
            holder.recyclerView.setAdapter(fruitTop);
        }
    }

    public int getItemCount() {
        return mFruitList.size();
    }
}
