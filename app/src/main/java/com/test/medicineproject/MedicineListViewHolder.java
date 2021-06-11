package com.test.medicineproject;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

public class MedicineListViewHolder extends RecyclerView.ViewHolder{
    TextView tv_medicineTitle;
    TextView tv_medicineCompany;
    ImageView iv_medicineImage;

    public MedicineListViewHolder(@NonNull View itemView) {
        super(itemView);

        // 뷰 객체 참조하기
        tv_medicineTitle = itemView.findViewById(R.id.tv_medicine_title);
        tv_medicineCompany = itemView.findViewById(R.id.tv_medicine_company);
        iv_medicineImage = itemView.findViewById(R.id.iv_medicine_image);
    }

    public final void bind(MedicineData myData) {
        tv_medicineTitle.setText(myData.getMedicineTitle());
        tv_medicineCompany.setText(myData.getMedicineCompany());
        if (myData.getMedicineImage().equals("null")) {
            Glide.with(itemView).load(R.drawable.medicine_null).into(iv_medicineImage);
        }else{
            Glide.with(itemView).load(myData.getMedicineImage()).into(iv_medicineImage);
        }

    }
}
