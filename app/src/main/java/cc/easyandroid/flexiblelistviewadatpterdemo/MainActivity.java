package cc.easyandroid.flexiblelistviewadatpterdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import java.util.ArrayList;

import cc.easyandroid.flexiblelistviewadapter.EasyFlexibleListViewAdapter;
import cc.easyandroid.flexiblelistviewadatpterdemo.pojo.IFITem;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    EasyFlexibleListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        listView = (ListView) findViewById(R.id.listview);
        adapter = new EasyFlexibleListViewAdapter(getLayoutInflater());
        ArrayList<IFITem> iFITems = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            IFITem ifiTem = new IFITem();
            iFITems.add(ifiTem);
        }
        adapter.addItems(iFITems);
        listView.setAdapter(adapter);
    }

}
