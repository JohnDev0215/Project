package defpackage;

import android.util.Log;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: MinidumpLogcatPrepender.java */
/* JADX INFO: loaded from: classes2.dex */
public final class uh1 {
    public final d10 a;
    public final File b;
    public final List<String> c;

    public uh1(d10 d10Var, File file, List<String> list) {
        this.a = d10Var;
        this.b = file;
        this.c = list;
    }

    public static void a(File file, File file2) throws Throwable {
        Throwable th;
        BufferedOutputStream bufferedOutputStream;
        BufferedInputStream bufferedInputStream = null;
        BufferedOutputStream bufferedOutputStream2 = null;
        try {
            byte[] bArr = new byte[256];
            BufferedInputStream bufferedInputStream2 = new BufferedInputStream(new FileInputStream(file));
            try {
                BufferedOutputStream bufferedOutputStream3 = new BufferedOutputStream(new FileOutputStream(file2, true));
                while (true) {
                    try {
                        int i = bufferedInputStream2.read(bArr);
                        if (i == -1) {
                            bufferedInputStream2.close();
                            bufferedOutputStream3.close();
                            return;
                        }
                        bufferedOutputStream3.write(bArr, 0, i);
                    } catch (Throwable th2) {
                        th = th2;
                        bufferedOutputStream2 = bufferedOutputStream3;
                        bufferedOutputStream = bufferedOutputStream2;
                        bufferedInputStream = bufferedInputStream2;
                        if (bufferedInputStream != null) {
                            bufferedInputStream.close();
                        }
                        if (bufferedOutputStream == null) {
                            throw th;
                        }
                        bufferedOutputStream.close();
                        throw th;
                    }
                }
            } catch (Throwable th3) {
                th = th3;
            }
        } catch (Throwable th4) {
            th = th4;
            bufferedOutputStream = null;
        }
    }

    public static void c(File file, List<String> list, String str) throws Throwable {
        BufferedWriter bufferedWriter;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(file, false));
        } catch (Throwable th) {
            th = th;
            bufferedWriter = null;
        }
        try {
            bufferedWriter.write(str);
            bufferedWriter.newLine();
            bufferedWriter.write("Content-Disposition: form-data; name=\"logcat\"; filename=\"logcat\"");
            bufferedWriter.newLine();
            bufferedWriter.write("Content-Type: text/plain");
            bufferedWriter.newLine();
            bufferedWriter.newLine();
            Iterator<String> it = list.iterator();
            while (it.hasNext()) {
                bufferedWriter.write(it.next());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (Throwable th2) {
            th = th2;
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            throw th;
        }
    }

    public final File b() {
        boolean z;
        BufferedReader bufferedReader;
        String line;
        List<String> list = this.c;
        boolean zIsEmpty = list.isEmpty();
        File file = this.b;
        if (zIsEmpty) {
            return file;
        }
        String str = file.getName() + ".try0";
        File fileB = null;
        try {
            try {
                bufferedReader = new BufferedReader(new FileReader(file));
                try {
                    line = bufferedReader.readLine();
                    bufferedReader.close();
                } catch (Throwable th) {
                    th = th;
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                bufferedReader = null;
            }
        } catch (IOException e) {
            fa1.l("LogcatPrepender", "Error while trying to annotate minidump file %s with logcat data", file.getAbsoluteFile(), e);
            if (0 != 0) {
                d10.c(null);
            }
            z = false;
        }
        if (line == null) {
            return file;
        }
        fileB = this.a.b(str);
        c(fileB, list, line);
        a(file, fileB);
        z = true;
        if (!z) {
            return file;
        }
        if (!file.delete()) {
            Log.w("cr_LogcatPrepender", "Failed to delete minidump file: " + file.getName());
        }
        return fileB;
    }
}
