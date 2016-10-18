package cc.easyandroid.flexiblelistviewadatpterdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import java.util.ArrayList;

import cc.easyandroid.flexiblelistviewadapter.EasyFlexibleListViewAdapter;
import cc.easyandroid.flexiblelistviewadatpterdemo.pojo.IFITem;
import cc.easyandroid.flexiblelistviewadatpterdemo.pojo.IFITem1;

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
        ArrayList iFITems = new ArrayList<>();
        for (int i = 0; i < 300; i++) {
            IFITem ifiTem = new IFITem();
            ifiTem.setData("+i=" + i);
            iFITems.add(ifiTem);
        }
        IFITem1 ifiTem1 = new IFITem1();
        ifiTem1.setData("ddddddddddddd");
        iFITems.add(0, ifiTem1);
        adapter.addItems(iFITems);
        adapter.setViewTypeCount(2);
        listView.setAdapter(adapter);
    }

}
