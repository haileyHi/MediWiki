package com.test.medicineproject.Search;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.test.medicineproject.MedicineData;
import com.test.medicineproject.MedicineListAdapter;
import com.test.medicineproject.R;

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

public class SearchFragment extends Fragment {
    //api key 나중에 제거하기
    private String key = "3rkNwN1XWQWLf9YXRoB%2FWJ8wjGN7qZDfCTpG7ffeeOpYD6AsPYrNG0H8bpqxwLUtpwrvTqx6rc5MO%2BPH63dd%2Fg%3D%3D";
    private String address = "http://apis.data.go.kr/1471000/DrbEasyDrugInfoService/getDrbEasyDrugList";
    private RecyclerView recyclerView;
    private ArrayList<MedicineData> medicineList;
    private EditText searchText;
    private ImageView searchButton;
    MedicineListAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container ,false);

        // 리사이클러뷰에 표시할 데이터 리스트 생성
        medicineList = new ArrayList<>();

//        medicineList.add(new MedicineData("타이레놀", "얀센", "http://nedrug.mfds.go.kr/pbp/cmn/itemImageDownload/147426411393800107"));
//        medicineList.add(new MedicineData("탁센", "가나다", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR7TVi20iFw3BOZAeb3XB2M1er8EuFbzrFL7A&usqp=CAU"));

        // 리사이클러뷰에 adapter 적용
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_medicine_list);
        adapter = new MedicineListAdapter(medicineList);
        recyclerView.setAdapter(adapter);

        //임시 데이터 넣기.
        loadData(address + "?serviceKey=" + key + "&numOfRows=30" + "&type=json");
        searchText = (EditText) view.findViewById(R.id.et_search);

        //버튼 클릭 리스너 적용하기
        searchButton = (ImageView) view.findViewById(R.id.iv_search_button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Log.d(">>> onclick 확인 ", "검색 버튼 눌림!");
                final String keyword = searchText.getText().toString();
                loadData(address + "?serviceKey=" + key + "&itemName=" + keyword + "&numOfRows=10" + "&type=json");
            }
        });

        return view;
    }

    private void loadData(final String urlAddress){
        new Thread(){
            @Override
            public void run() {
                medicineList.clear();
//                String urlAddress = address + "?serviceKey=" + key + "&numOfRows=30" + "&type=json";
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

                    Gson gson = new Gson();
                    //jsonData를 JSONObject로
                    JSONObject obj = new JSONObject(jsonData);
                    //obj의 items를 JSONObject로 추출
                    JSONObject body = (JSONObject) obj.get("body");
                    // itemResult의 JSONObject에서 JSONArray추출
                    JSONArray itemsList = (JSONArray) body.get("items");

                    Log.d("data 확인", ((JSONObject) itemsList.get(0)).getString("entpName"));
                    for (int i = 0; i < itemsList.length(); i++) {
                        JSONObject temp = itemsList.getJSONObject(i);

                        String medicineName = temp.getString("itemName");
                        String medicineCompany = temp.getString("entpName");
                        String medicineImage = temp.getString("itemImage");

                        medicineList.add(new MedicineData(medicineName, medicineCompany, medicineImage));
                    }

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter.notifyDataSetChanged();
                        }
                    });

                } catch (MalformedURLException e) {
                    Log.d("URL 문제", "URL이 잘못 되었다.");
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }.start();
        adapter.notifyDataSetChanged();
    }


}
