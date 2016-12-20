package test.bwie.com.myview_demo.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

import java.util.ArrayList;

import test.bwie.com.myview_demo.R;
import test.bwie.com.myview_demo.bean.PieData;
import test.bwie.com.myview_demo.view.MyView_2;

/**
 * Author：ZhaoTieJin
 * date :  2016/12/20
 */

public class SecondActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        MyView_2 myview_2 = (MyView_2) findViewById(R.id.myview_2);

        //实例化几个PieData
        PieData pieData_1 = new PieData("A", Color.GREEN, 30.0f);
        PieData pieData_2 = new PieData("B", Color.RED, 50.0f);
        PieData pieData_3 = new PieData("C", Color.WHITE, 80.0f);
        PieData pieData_4 = new PieData("D", Color.BLACK, 100.0f);
        PieData pieData_5 = new PieData("D", Color.RED, 100.0f);
        //创建集合存放PieData
        ArrayList<PieData> list = new ArrayList<>();
        list.add(pieData_1);
        list.add(pieData_2);
        list.add(pieData_3);
        list.add(pieData_4);
        list.add(pieData_5);
        //给自定义的view设置参数
        myview_2.setPieData(list);
    }
}
