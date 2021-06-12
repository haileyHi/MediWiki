package com.test.medicineproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.test.medicineproject.Home.HomeFragment;
import com.test.medicineproject.Search.SearchFragment;
import com.test.medicineproject.Alarm.AlarmFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager = getSupportFragmentManager();

    private SearchFragment searchFragment = new SearchFragment();
    private HomeFragment homeFragment = new HomeFragment();
    private AlarmFragment alarmFragment = new AlarmFragment();
    private Toolbar toolbar;

    //api key 나중에 제거하기
    private String key = "3rkNwN1XWQWLf9YXRoB%2FWJ8wjGN7qZDfCTpG7ffeeOpYD6AsPYrNG0H8bpqxwLUtpwrvTqx6rc5MO%2BPH63dd%2Fg%3D%3D";
    private String address = "http://apis.data.go.kr/1471000/DrbEasyDrugInfoService/getDrbEasyDrugList";
    private RecyclerView recyclerView;
    private ArrayList<MedicineData> medicineList;
    private EditText searchText;
    private ImageView searchButton;
    MedicineListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowCustomEnabled(true);  // custom하기 위해
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);// 뒤로가기 버튼
        toolbar.setElevation(5F);

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fl_main, searchFragment).commitAllowingStateLoss();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav_view);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction transaction = fragmentManager.beginTransaction();

                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        transaction.replace(R.id.fl_main, homeFragment).commitAllowingStateLoss();
                        break;
                    case R.id.navigation_search:
                        transaction.replace(R.id.fl_main, searchFragment).commitAllowingStateLoss();
                        break;
                    case R.id.navigation_alarm:
                        transaction.replace(R.id.fl_main, alarmFragment).commitAllowingStateLoss();
                        break;
                }
                return true;
            }
        });

        // 리사이클러뷰에 표시할 데이터 리스트 생성
//        medicineList = new ArrayList<>();
////
//////        medicineList.add(new MedicineData("타이레놀", "얀센", "http://nedrug.mfds.go.kr/pbp/cmn/itemImageDownload/147426411393800107"));
//////        medicineList.add(new MedicineData("탁센", "가나다", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR7TVi20iFw3BOZAeb3XB2M1er8EuFbzrFL7A&usqp=CAU"));
////
////        // 리사이클러뷰에 adapter 적용
////        recyclerView = (RecyclerView) findViewById(R.id.rv_medicine_list);
////        adapter = new MedicineListAdapter(medicineList);
////        recyclerView.setAdapter(adapter);
////
////        //임시 데이터 넣기.
////        loadData();
////        searchText = (EditText) findViewById(R.id.et_search);
////
////        //버튼 클릭 리스너 적용하기
////        searchButton = (ImageView) findViewById(R.id.iv_search_button);
////        searchButton.setOnClickListener(new View.OnClickListener() {
////
////            @Override
////            public void onClick(View view) {
////                Log.d(">>> onclick 확인 ", "검색 버튼 눌림!");
////                final String keyword = searchText.getText().toString();
////                new Thread(){
////                    @Override
////                    public void run() {
////                        medicineList.clear();
////                        String urlAddress = address + "?serviceKey=" + key + "&itemName=" + keyword + "&numOfRows=10" + "&type=json";
////                        try {
////                            URL url = new URL(urlAddress);
////
////                            InputStream is = url.openStream();
////                            InputStreamReader isr = new InputStreamReader(is);
////                            BufferedReader reader = new BufferedReader(isr);
////
////                            StringBuffer buffer = new StringBuffer();
////                            String line = reader.readLine();
////                            while (line != null) {
////                                buffer.append(line + "\n");
////                                line = reader.readLine();
////                            }
////
////                            String jsonData = buffer.toString();
////
////                            Gson gson = new Gson();
////                            //jsonData를 JSONObject로
////                            JSONObject obj = new JSONObject(jsonData);
////                            //obj의 items를 JSONObject로 추출
////                            JSONObject body = (JSONObject) obj.get("body");
////                            // itemResult의 JSONObject에서 JSONArray추출
////                            JSONArray itemsList = (JSONArray) body.get("items");
////
////                            Log.d("data 확인", ((JSONObject) itemsList.get(0)).getString("entpName"));
////                            for (int i = 0; i < itemsList.length(); i++) {
////                                JSONObject temp = itemsList.getJSONObject(i);
////
////                                String medicineName = temp.getString("itemName");
////                                String medicineCompany = temp.getString("entpName");
////                                String medicineImage = temp.getString("itemImage");
////
//////                                if(medicineName.contains(searchText.toString()))
////                                 medicineList.add(new MedicineData(medicineName, medicineCompany, medicineImage));
////                            }
////
////                            runOnUiThread(new Runnable() {
////                                @Override
////                                public void run() {
////                                    adapter.notifyDataSetChanged();
////                                }
////                            });
////
////                        } catch (MalformedURLException e) {
////                            Log.d("URL 문제", "URL이 잘못 되었다.");
////                            e.printStackTrace();
////                        } catch (IOException e) {
////                            e.printStackTrace();
////                        } catch (JSONException e) {
////                            e.printStackTrace();
////                        }
////
////                    }
////                }.start();
////                adapter.notifyDataSetChanged();
////            }
////        });
    }

//    private void loadData(){
//        new Thread(){
//            @Override
//            public void run() {
//                medicineList.clear();
//                String urlAddress = address + "?serviceKey=" + key + "&numOfRows=30" + "&type=json";
//                try {
//                    URL url = new URL(urlAddress);
//
//                    InputStream is = url.openStream();
//                    InputStreamReader isr = new InputStreamReader(is);
//                    BufferedReader reader = new BufferedReader(isr);
//
//                    StringBuffer buffer = new StringBuffer();
//                    String line = reader.readLine();
//                    while (line != null) {
//                        buffer.append(line + "\n");
//                        line = reader.readLine();
//                    }
//
//                    String jsonData = buffer.toString();
//
//                    Gson gson = new Gson();
//                    //jsonData를 JSONObject로
//                    JSONObject obj = new JSONObject(jsonData);
//                    //obj의 items를 JSONObject로 추출
//                    JSONObject body = (JSONObject) obj.get("body");
//                    // itemResult의 JSONObject에서 JSONArray추출
//                    JSONArray itemsList = (JSONArray) body.get("items");
//
//                    Log.d("data 확인", ((JSONObject) itemsList.get(0)).getString("entpName"));
//                    for (int i = 0; i < itemsList.length(); i++) {
//                        JSONObject temp = itemsList.getJSONObject(i);
//
//                        String medicineName = temp.getString("itemName");
//                        String medicineCompany = temp.getString("entpName");
//                        String medicineImage = temp.getString("itemImage");
//
//                        medicineList.add(new MedicineData(medicineName, medicineCompany, medicineImage));
//                    }
//
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            adapter.notifyDataSetChanged();
//                        }
//                    });
//
//                } catch (MalformedURLException e) {
//                    Log.d("URL 문제", "URL이 잘못 되었다.");
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        }.start();
//        adapter.notifyDataSetChanged();
//    }


}