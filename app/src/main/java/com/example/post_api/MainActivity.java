package com.example.post_api;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnCallAPI;
    Recycler_Adapter adapter;
    RecyclerView recyclerView;
    ArrayList<DataModel> dataList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnCallAPI=findViewById(R.id.btnCallAPI);
        recyclerView=findViewById(R.id.Recyclerview);

        btnCallAPI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                String url = "https://jsonplaceholder.typicode.com/posts";
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.d("TTT", "onResponse: "+response);
                                try {
                                    JSONArray jsonArray=new JSONArray(response);
                                    for (int i=0;i<jsonArray.length();i++)
                                    {
                                        JSONObject mainObj=jsonArray.getJSONObject(1);
                                        Integer userid=mainObj.getInt("userId");
                                        Integer id=mainObj.getInt("id");
                                        String title=mainObj.getString("title");
                                        String body=mainObj.getString("body");

                                        DataModel model=new DataModel(userid,id,title,body);
                                        dataList.add(model);
                                        Log.d("AAA", "onResponse: Model="+model.toString());

                                        adapter = new Recycler_Adapter(MainActivity.this, dataList);
                                        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
                                        manager.setOrientation(LinearLayoutManager.VERTICAL);
                                        recyclerView.setLayoutManager(manager);
                                        recyclerView.setAdapter(adapter);
                                    }
                                } catch (JSONException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("TTT", "onErrorResponse: Error"+error.getLocalizedMessage());
                    }
                });

                // Add the request to the RequestQueue.
                queue.add(stringRequest);
            }
        });

    }
}