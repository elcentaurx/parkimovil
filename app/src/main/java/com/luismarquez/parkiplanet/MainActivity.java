package com.luismarquez.parkiplanet;

import android.content.Context;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    public static String TAG = MainActivity.class.getName();
    private int TestNumberCases;
    private List<TestCase> listCasosDePrueba;
    private List<Archipelago> listDeArchipielagos = new ArrayList<>();
    private TextView resultado;
    private String mFileInfo;
    private String mSampleOutputResult;
    private  ProgressBar spinner;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.button);
        resultado = findViewById(R.id.resultdo);
        spinner = (ProgressBar)findViewById(R.id.progressBar1);
        spinner.setVisibility(View.GONE);
        resultado.setMovementMethod(new ScrollingMovementMethod());
        Button button3 = findViewById(R.id.button3);
        button.setOnClickListener(this::test1);
        button3.setOnClickListener(view -> {
            spinner.setVisibility(View.VISIBLE);
            resultado.setText("");
            new Hilo1().start();
        });
    }

    class Hilo1 extends Thread {
        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Test3();


                    Toast.makeText(MainActivity.this, "End of process", Toast.LENGTH_SHORT).show();
                    spinner.setVisibility(View.GONE);
                }
            });
        }
    }

    public void test1(View v) {
        resultado.setText(" ");
        mFileInfo = mSampleOutputResult = "";
        initData(this, "test1.txt");
        searchArchipelago();
        String results = "";
        results += "\nSample Output:\n";
        results += mSampleOutputResult + "\n";
        results  += "Sample Input:\n" + mFileInfo;
        resultado.setText(results);
    }

    public void test2(View v)  {
        resultado.setText(" ");
        mFileInfo = mSampleOutputResult = "";
        initData(this, "test2.txt");
        searchArchipelago();
        String results = "";
        results += "\nSample Output:\n";
        results += mSampleOutputResult + "\n";
        results  += "Sample Input:\n" + mFileInfo;
        resultado.setText(results);
    }
    public void Test3()  {

        resultado.setText("");
        mFileInfo = mSampleOutputResult = "";
        resultado.setText("");
        initData(this, "test_case.txt");
        searchArchipelago();
        String results = "";
        results += "\nSample Output:\n";
        results += mSampleOutputResult + "\n";
        results  += "Sample Input:\n" + mFileInfo;
        results += mSampleOutputResult + "\n";
        resultado.setText(results);

    }


    public void searchArchipelago() {

        for (TestCase cases : listCasosDePrueba) {
            Log.i(TAG, "Obtain cases" + cases.toString());
            Island[] arrayDeIslas = new Island[cases.getSetOfIslands().size()];

            Util.combinations2Island(cases.getSetOfIslands().values().toArray(arrayDeIslas), 2, 0, new Island[2]);
            //group lines by length
            HashMap<Double, List<Line>> hashMapLineas = groupLinesByLongitud();
            for (Map.Entry<Double, List<Line>> entry : hashMapLineas.entrySet()) {
                ////System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
                Line[] arrayDeLineas = new Line[entry.getValue().size()];
                Util.combinations2Lines(entry.getValue().toArray(arrayDeLineas), 2, 0, new Line[2]);
                findArchipelagos();
                Util.combinacionDelineasEntreIslas = new ArrayList<>();
            }
            Log.i(TAG, "archipelago's inside there" + listDeArchipielagos.size());
            mSampleOutputResult += listDeArchipielagos.size() + "\n";
            Util.lineasEntreIslas = new ArrayList<>();
            listDeArchipielagos = new ArrayList<>();
        }
    }

    public void findArchipelagos() {
        for (Line[] combinationLine : Util.combinacionDelineasEntreIslas) {
            //Si la lineas comparten el mismo punto tenemos un archipielago

            Line line_one = combinationLine[0];

            Line line_two = combinationLine[1];
            if (samePoint(line_one, line_two)) {
                Archipelago archipielago = new Archipelago(line_one, line_two);
                listDeArchipielagos.add(archipielago);
            }
        }
    }

    public boolean samePoint(Line line1, Line line2) {
        if (line1.getIslandOne().equals(line2.getIslandOne())) {
            return true;
        }
        if (line1.getIslandOne().equals(line2.getIslandTwo())) {
            return true;
        }
        if (line1.getIslandTwo().equals(line2.getIslandOne())) {
            return true;
        }
        if (line1.getIslandTwo().equals(line2.getIslandTwo())) {
            return true;
        }
        return false;
    }


    public HashMap<Double, List<Line>> groupLinesByLongitud() {
        HashMap<Double, List<Line>> hashMap = new HashMap<Double, List<Line>>();
        for (Line line : Util.lineasEntreIslas) {
            if (!hashMap.containsKey(line.getLength())) {
                List<Line> list = new ArrayList<Line>();
                list.add(line);
                hashMap.put(line.getLength(), list);
            } else {
                hashMap.get(line.getLength()).add(line);
            }
        }
        return hashMap;
    }

    public void initData(Context context, String fileName) {
        String fileInfo = null;
        try {
            fileInfo = mFileInfo = Util.AssetJSONFile(fileName, context);

        } catch (IOException e) {
        Log.e("Error", e.toString());
        }
        String[] linesInFile = fileInfo.split("\r\n");

        int intNumberOfTest = Integer.parseInt(linesInFile[0]);
        listCasosDePrueba = new ArrayList<>(intNumberOfTest);
        int numberOfIslands = Integer.parseInt(linesInFile[1]);
        TestCase testCase = new TestCase(numberOfIslands);
        listCasosDePrueba.add(testCase);
        for (int lineNumber = 2; lineNumber < linesInFile.length; lineNumber++) {
            String[] pointsofIsland = linesInFile[lineNumber].split(" ");
            if (pointsofIsland.length == 1) {
                numberOfIslands = Integer.parseInt(linesInFile[lineNumber]);
                testCase = new TestCase(numberOfIslands);
                listCasosDePrueba.add(testCase);
            } else {
                int pointX = Integer.parseInt(pointsofIsland[0]);
                int pointY = Integer.parseInt(pointsofIsland[1]);
                testCase.addIslands(new Island(pointX, pointY));
            }
        }
    }



}
