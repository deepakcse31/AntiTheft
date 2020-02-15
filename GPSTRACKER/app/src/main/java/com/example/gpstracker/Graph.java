package com.example.gpstracker;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class Graph extends AppCompatActivity {
    BarChart BarChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        BarChart=findViewById(R.id.barchart);
        BarChart.setDrawBarShadow(false);
        BarChart.setDrawValueAboveBar(true);
        BarChart.setMaxVisibleValueCount(50);
        BarChart.setPinchZoom(false);
        BarChart.setDrawGridBackground(true);
        ArrayList<BarEntry> barEntries=new ArrayList<>();
        barEntries.add(new BarEntry(1, (int) 40f));
        barEntries.add(new BarEntry(2, (int) 44f));
        barEntries.add(new BarEntry(3, (int) 60f));
        barEntries.add(new BarEntry(4, (int) 70f));

        BarDataSet barDataSet=new BarDataSet(barEntries,"Data set1");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        BarData data=new BarData((List<String>) barDataSet);
        BarChart.setData(data);
    }
}
