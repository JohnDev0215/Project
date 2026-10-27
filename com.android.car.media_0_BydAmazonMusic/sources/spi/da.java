package spi;

import android.car.utils.Logs;
import android.util.Pair;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: ShellUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public final class da {
    public static Pair<List<String>, List<String>> a(String str) {
        try {
            Process processExec = Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", str});
            return new Pair<>(b(processExec, processExec.getInputStream()), b(processExec, processExec.getErrorStream()));
        } catch (IOException unused) {
            return null;
        }
    }

    public static List<String> b(Process process, InputStream inputStream) {
        LineNumberReader lineNumberReader = new LineNumberReader(new InputStreamReader(inputStream));
        try {
            process.waitFor();
            ArrayList arrayList = new ArrayList();
            try {
                for (String line = lineNumberReader.readLine(); line != null; line = lineNumberReader.readLine()) {
                    arrayList.add(line);
                }
                return arrayList;
            } catch (IOException unused) {
                return Collections.emptyList();
            }
        } catch (Exception e) {
            Logs.e("ShellUtils", "InterruptedException: " + e);
            return Collections.emptyList();
        }
    }
}
