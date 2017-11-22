package com.example.rashmi.test_volley_singleton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RequestQueue requestQueue;
    String url="https://api.myjson.com/bins/w86a";
    List<DataProvider> mList = new ArrayList<>();
    MyAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView= (RecyclerView)findViewById(R.id.rv_recycler);
        adapter = new MyAdapter(mList,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        requestQueue =NetworkController.getInstance(this).getRequestQueue();
        final JsonArrayRequest newreq= new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i=0; i<response.length();i++) {
                    try {
                        JSONObject obj = response.getJSONObject(i);
                        DataProvider provider = new DataProvider(obj.getString("title"), obj.getString("Content"), obj.getString("image"), obj.getInt("ratingbar"));
                        mList.add(provider);
                    } catch (JSONException e) {
                        System.out.println(e.getMessage());
                        e.printStackTrace();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error.getMessage());

            }
        });
        requestQueue.add(newreq);

    }
}
