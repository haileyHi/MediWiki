package com.test.medicineproject;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.test.medicineproject.Search.SearchDetailActivity;

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

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( v.getContext(), SearchDetailActivity.class);
                intent.putExtra("medicine_info", tv_medicineTitle.getText().toString()); //여기서 title에 내용이 없어서 안 넘어가는 것 같다.
                v.getContext().startActivity(intent);
            }
        });
    }

    public final void bind(MedicineData myData) {
        tv_medicineTitle.setText(myData.getMedicineTitle());
        tv_medicineCompany.setText(myData.getMedicineCompany());
        if (myData.getMedicineImage().equals("null")) {
            GlideApp.with(itemView).load(R.drawable.medicine_null).into(iv_medicineImage);
        }else{
            GlideApp.with(itemView).load(myData.getMedicineImage()).into(iv_medicineImage);
        }

    }
}
