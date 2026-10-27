package ts.car.diag.d;

import android.content.res.AssetManager;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import ts.car.diag.manager.q;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
public class b {
    public static String a(AssetManager assetManager, String str) {
        InputStream inputStreamOpen;
        BufferedReader bufferedReader;
        String string = null;
        try {
            inputStreamOpen = assetManager.open(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStreamOpen);
            try {
                bufferedReader = new BufferedReader(inputStreamReader);
            } finally {
            }
            try {
                StringBuffer stringBuffer = new StringBuffer();
                while (true) {
                    String line = bufferedReader.readLine();
                    if (line == null) {
                        break;
                    }
                    stringBuffer.append(line);
                }
                string = stringBuffer.toString();
                q.a("ReadJsonUtil", "getJson : " + string);
                bufferedReader.close();
                inputStreamReader.close();
                if (inputStreamOpen != null) {
                    inputStreamOpen.close();
                }
                return string;
            } finally {
            }
        } finally {
        }
    }
}
