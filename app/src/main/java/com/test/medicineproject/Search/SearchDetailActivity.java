package com.test.medicineproject.Search;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.test.medicineproject.GlideApp;
import com.test.medicineproject.MedicineData;
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

public class SearchDetailActivity extends AppCompatActivity {
    ImageView iv_itemImage;
    TextView tv_itemName, tv_efcyQesitm, tv_useMethodQesitm, tv_atpnWarnQesitm, tv_atpnQesitm, tv_intrcQesitm, tv_seQesitm, tv_depositMethodQesitm;
    MedicineData item;
    private String key = "3rkNwN1XWQWLf9YXRoB%2FWJ8wjGN7qZDfCTpG7ffeeOpYD6AsPYrNG0H8bpqxwLUtpwrvTqx6rc5MO%2BPH63dd%2Fg%3D%3D";
    private String address = "http://apis.data.go.kr/1471000/DrbEasyDrugInfoService/getDrbEasyDrugList";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mediinfo);

        iv_itemImage = (ImageView) findViewById(R.id.iv_itemImage);
        tv_itemName = (TextView) findViewById(R.id.tv_itemName);
        tv_efcyQesitm = (TextView) findViewById(R.id.tv_efcyQesitm);
        tv_useMethodQesitm = (TextView) findViewById(R.id.tv_useMethodQesitm);
        tv_atpnWarnQesitm = (TextView) findViewById(R.id.tv_atpnWarnQesitm);
        tv_atpnQesitm = (TextView) findViewById(R.id.tv_atpnQesitm);
        tv_intrcQesitm = (TextView) findViewById(R.id.tv_intrcQesitm);
        tv_seQesitm = (TextView) findViewById(R.id.tv_seQesitm);
        tv_depositMethodQesitm = (TextView) findViewById(R.id.tv_depositMethodQesitm);

        item = loadData();

//        Log.d("Detail>>>>onCreate>>>>", item.getMedicineTitle());

    }


    private MedicineData loadData() {
        Intent intent = getIntent();

        String keyword = intent.getStringExtra("medicine_info");
        final String urlAddress = address + "?serviceKey=" + key + "&itemName=" + keyword + "&type=json";
        Log.d("Detail>>>>onCreate>>>>loadData", urlAddress);
        new Thread() {
            @Override
            public void run() {
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
                    final JSONArray itemsList = (JSONArray) body.get("items");

                    Log.d("data 확인", ((JSONObject) itemsList.get(0)).getString("entpName"));

                    JSONObject temp = (JSONObject) itemsList.getJSONObject(0);

                    String medicineCompany = temp.getString("entpName");
                    String medicineName = temp.getString("itemName");
                    String medicineEfcy = temp.getString("efcyQesitm");
                    String medicineMethod = temp.getString("useMethodQesitm");
                    String medicineWarn = temp.getString("atpnWarnQesitm");
                    String atpnQesitm = temp.getString("atpnQesitm");
                    String intrcQesitm = temp.getString("intrcQesitm");
                    String seQesitm = temp.getString("seQesitm");
                    String depositMethodQesitm = temp.getString("depositMethodQesitm");
                    String medicineImage = temp.getString("itemImage");
                    if(medicineImage.equals("null")){
                        medicineImage = "null";
                    }

                    item = new MedicineData(medicineName, medicineCompany, medicineImage, medicineEfcy, medicineMethod, medicineWarn, atpnQesitm, intrcQesitm, seQesitm, depositMethodQesitm);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (item.getMedicineImage() == "null") {
                                GlideApp.with(getApplicationContext()).load(R.drawable.medicine_null).into(iv_itemImage);
                            }else{
                                GlideApp.with(getApplicationContext()).load(item.getMedicineImage()).into(iv_itemImage);
                            }
                            tv_itemName.setText(item.getMedicineTitle());
                            if (item.getMedicineEfcy() == "null") {
                                tv_efcyQesitm.setText("별도의 안내 사항이 없습니다.\n");
                            }else{
                                tv_efcyQesitm.setText(Html.fromHtml(item.getMedicineEfcy()));
                            }
                            if(item.getMedicineMethod() == "null"){
                                tv_useMethodQesitm.setText("별도의 안내 사항이 없습니다.\n");
                            }else{
                                tv_useMethodQesitm.setText(Html.fromHtml(item.getMedicineMethod()));
                            }
                            if (item.getMedicineWarn() == "null") {
                                tv_atpnWarnQesitm.setText("별도의 안내 사항이 없습니다.\n");
                            }else {
                                tv_atpnWarnQesitm.setText(Html.fromHtml(item.getMedicineWarn()));
                            }
                            if (item.getAtpnQesitm() == "null") {
                                tv_atpnQesitm.setText("별도의 안내 사항이 없습니다.\n");
                            }else{
                                tv_atpnQesitm.setText(Html.fromHtml(item.getAtpnQesitm()));
                            }
                            if (item.getIntrcQesitm() == "null") {
                                tv_intrcQesitm.setText("별도의 안내 사항이 없습니다.\n");
                            }else{
                                tv_intrcQesitm.setText(Html.fromHtml(item.getIntrcQesitm()));
                            }
                            if (item.getSeQesitm() == "null") {
                                tv_seQesitm.setText("별도의 안내 사항이 없습니다.\n");
                            }else {
                                tv_seQesitm.setText(Html.fromHtml(item.getSeQesitm()));
                            }
                            if (item.getDepositMethodQesitm() == "null") {
                                tv_depositMethodQesitm.setText("별도의 안내 사항이 없습니다.\n");
                            }else{
                                tv_depositMethodQesitm.setText(Html.fromHtml(item.getDepositMethodQesitm()));
                            }
//                            adapter.notifyDataSetChanged();
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
        return item;
    }

}
