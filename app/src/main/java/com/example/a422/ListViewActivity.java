package com.example.a422;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListViewActivity extends AppCompatActivity {
    static final String KEY1 = "Key1";
    static final String KEY2 = "Key2";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ListView list = findViewById(R.id.list);

        List<Map<String, String>> values = prepareContent();

        BaseAdapter listContentAdapter = createAdapter(values);

        list.setAdapter(listContentAdapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }


    @NonNull
    private BaseAdapter createAdapter(List<Map<String, String>> values) {
        return new SimpleAdapter(ListViewActivity.this,values, R.layout.list_layout, new String[]{KEY1, KEY2}, new int[] {R.id.TextOne, R.id.TextTwo} );
    }

    @NonNull
    private List<Map<String, String>> prepareContent() {
        List<Map<String, String>> data = new ArrayList<>();
        String[] arrayContent = getString(R.string.large_text).split("\n\n");
        for(int i = 0; i < arrayContent.length; i++){
            Map<String, String> temp = new HashMap<>();
            temp.put(KEY1,arrayContent[i]);
            temp.put(KEY2, String.valueOf(arrayContent[i].length()));
            data.add(temp);
        }
        return  data;
    }
}
