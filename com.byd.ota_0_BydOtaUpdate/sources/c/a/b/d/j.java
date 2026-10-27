package c.a.b.d;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/* JADX INFO: compiled from: ShellUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class j {
    public static String a(String str) {
        InputStream inputStream;
        BufferedReader bufferedReader;
        StringBuilder sb = new StringBuilder();
        try {
            Process processExec = Runtime.getRuntime().exec(str);
            inputStream = processExec.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            try {
                processExec.waitFor();
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            }
        } catch (IOException e3) {
            e3.printStackTrace();
        }
        while (true) {
            String line = bufferedReader.readLine();
            if (line == null) {
                break;
            }
            sb.append(line);
            sb.append("\n");
            return sb.toString();
        }
        inputStream.close();
        bufferedReader.close();
        return sb.toString();
    }
}
