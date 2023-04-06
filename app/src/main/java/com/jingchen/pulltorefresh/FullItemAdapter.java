package com.jingchen.pulltorefresh;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class FullItemAdapter extends RecyclerView.Adapter<FullItemAdapter.FViewHolder> {

    private List<String> mFruitList;



    public FullItemAdapter(List<String> fruitList) {
        mFruitList = fruitList;
    }

    @Override
    public FViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        System.out.println("11111111111111ViewHolder："+viewType);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_layout, parent, false);
        FViewHolder holder = new FViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(view.getContext(),"sad",Toast.LENGTH_SHORT).show();
            }
        });
        return holder;

    }

    @Override
    public void onBindViewHolder(FViewHolder holder, int position) {
        System.out.println("11111111111111222222onBindViewHolder+"+position);
        String fruit = mFruitList.get(position);
//        holder.fruitImage.setImageResource(fruit.getImageId());
        holder.fruitName.setText(fruit+":"+position);
    }

    @Override
    public int getItemCount() {

        return mFruitList.size();
    }

    SparseArray<String> s = new SparseArray<String>();

    static class FViewHolder extends RecyclerView.ViewHolder {

        TextView fruitName;

        public FViewHolder(View view) {
            super(view);

            fruitName = (TextView) view.findViewById(R.id.tv);

        }

    }

}
