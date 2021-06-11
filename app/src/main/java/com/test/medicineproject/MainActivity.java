package com.test.medicineproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //api key 나중에 제거하기
    private String key;
    private String address = "http://apis.data.go.kr/1471000/DrbEasyDrugInfoService/getDrbEasyDrugList";
    private RecyclerView recyclerView;
    private ArrayList<MedicineData> medicineList;
    private EditText searchText;
    private ImageView searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicinelist);

        // 리사이클러뷰에 표시할 데이터 리스트 생성
        medicineList = new ArrayList<>();
        //임시 데이터 넣기.
        medicineList.add(new MedicineData("타이레놀", "얀센", "http://nedrug.mfds.go.kr/pbp/cmn/itemImageDownload/147426411393800107"));
        medicineList.add(new MedicineData("탁센", "가나다", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR7TVi20iFw3BOZAeb3XB2M1er8EuFbzrFL7A&usqp=CAU"));

        // 리사이클러뷰에 adapter 적용
        recyclerView = (RecyclerView) findViewById(R.id.rv_medicine_list);
        MedicineListAdapter adapter = new MedicineListAdapter(medicineList);
        recyclerView.setAdapter(adapter);

        //버튼 클릭 리스너 적용하기
        searchButton = (ImageView) findViewById(R.id.iv_search_button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(){
                    @Override
                    public void run() {
                        medicineList.clear();
                        String urlAddress = address + "?serviceKey=" + key;
                        try {
                            URL url = new URL(urlAddress);

                            InputStream is = url.openStream();
                            InputStreamReader isr = new InputStreamReader(is);
                            BufferedReader reader = new BufferedReader(isr);

                            StringBuffer buffer = new StringBuffer();
                            String line = reader.readLine();
                            while (line != null) {
                                buffer.append(line + "\n");
                                line = reader.readLine();
                            }

                            String jsonData = buffer.toString();

                            //jsonData를 JSONObject로
                            JSONObject obj = new JSONObject(jsonData);
                            //obj의 items를 JSONObject로 추출
                            JSONObject itemResult = (JSONObject) obj.get("body");
                            // itemResult의 JSONObject에서 JSONArray추출
                            JSONArray itemsList = (JSONArray) itemResult.get("items");

                            for (int i = 0; i < itemsList.length(); i++) {
                                JSONObject temp = itemsList.getJSONObject(i);

                                String medicineName = temp.getString("itemName");
                                String medicineCompany = temp.getString("entpName");
                                String medicineImage = temp.getString("itemImage");
                            }

                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };

            }
        });
    }


}