package com.luismarquez.parkiplanet;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import com.luismarquez.parkiplanet.Island;
import com.luismarquez.parkiplanet.Line;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Util {



    public static String AssetJSONFile (String filename, Context context) throws IOException {
        try {
            AssetManager manager = context.getAssets();
            InputStream file = manager.open(filename);
            byte[] formArray = new byte[file.available()];
            file.read(formArray);
            file.close();

            return new String(formArray);
        } catch (Exception ex) {
            return "";
        }


    }



    static List<Line> lineasEntreIslas = new ArrayList<>();
    static List<Line[]> combinacionDelineasEntreIslas = new ArrayList<>();

    public static void combinations2Island(Island[] arr, int len, int startPosition, Island[] result) {
        if (len == 0) {
            Log.i("Combinations", Arrays.toString(result));
            lineasEntreIslas.add(new Line(result[0], result[1]));
            return;
        }
        for (int i = startPosition; i <= arr.length - len; i++) {
            result[result.length - len] = arr[i];
            combinations2Island(arr, len - 1, i + 1, result);
        }
    }

    public static void combinations2Lines(Line[] arr, int len, int startPosition, Line[] result) {
        if (len == 0) {
            Log.i("Combinations:", Arrays.toString(result));
            combinacionDelineasEntreIslas.add(result.clone());
            return;
        }
        for (int i = startPosition; i <= arr.length - len; i++) {
            result[result.length - len] = arr[i];
            combinations2Lines(arr, len - 1, i + 1, result);
        }
    }
}