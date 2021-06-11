package com.test.medicineproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MedicineListAdapter extends RecyclerView.Adapter<MedicineListViewHolder> {

    private ArrayList<MedicineData> mData = null;

    public MedicineListAdapter(ArrayList<MedicineData> mData) {
        this.mData = mData;
    }

    @Override
    public MedicineListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.item_medicine, parent, false);
        MedicineListViewHolder viewHolder = new MedicineListViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MedicineListViewHolder holder, int position) {
        holder.bind(mData.get(position));
        //클릭 리스너 추가하기
        //holder.itemView.setOnClickListener();
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
