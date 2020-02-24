package com.example.a422;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListViewActivity extends AppCompatActivity {
    static final String KEY1 = "Key1";
    static final String KEY2 = "Key2";
    static final String DATAS = "DataS";

    List<Map<String, String>> simpleAdapterContent = new ArrayList<>();
    ListView list;
    SharedPreferences sharedPref;
    SharedPreferences.Editor myEditor;
    SwipeRefreshLayout swipeLayout;
    BaseAdapter listContentAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//-------------------------------------------------------------------------------------------
        sharedPref = getSharedPreferences("MyPref", MODE_PRIVATE);
        myEditor = sharedPref.edit();
        list = findViewById(R.id.list);
        swipeLayout = findViewById(R.id.swiperefresh);


        myEditor.putString(DATAS, getString(R.string.large_text));
        myEditor.apply();

        prepareContent();

        listContentAdapter = createAdapter(simpleAdapterContent);

        list.setAdapter(listContentAdapter);


        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                simpleAdapterContent.clear();
                prepareContent();
                listContentAdapter.notifyDataSetChanged();
                swipeLayout.setRefreshing(false);

            }
        });

    }

    @NonNull
    private BaseAdapter createAdapter(List<Map<String, String>> values) {
        return new MyCustomAdapter(values, getApplicationContext());
    }

    @NonNull
    private void prepareContent() {
        String[] arrayContent = sharedPref.getString(DATAS, "").split("\n\n");
        for (int i = 0; i < arrayContent.length; i++) {
            Map<String, String> temp = new HashMap<>();
            temp.put(KEY1, arrayContent[i]);
            temp.put(KEY2, String.valueOf(arrayContent[i].length()));
            simpleAdapterContent.add(temp);
        }
    }
}
