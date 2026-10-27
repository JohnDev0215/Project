package c.a.g.l;

import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/* JADX INFO: compiled from: EcuDiagUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f2764a = "e";

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v14 */
    /* JADX WARN: Type inference failed for: r2v15 */
    /* JADX WARN: Type inference failed for: r2v2 */
    /* JADX WARN: Type inference failed for: r2v3 */
    /* JADX WARN: Type inference failed for: r2v4, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r2v6 */
    /* JADX WARN: Type inference failed for: r2v7, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v8 */
    public static String a() throws Throwable {
        String str;
        BufferedReader bufferedReader;
        File file = new File(c.a.b.b.a.m + "ecudiag");
        ?? r2 = 0;
        String line = null;
        BufferedReader bufferedReader2 = null;
        try {
            if (!file.exists()) {
                return null;
            }
            try {
                bufferedReader = new BufferedReader(new FileReader(file));
            } catch (Exception e2) {
                e = e2;
                str = null;
            }
            try {
                line = bufferedReader.readLine();
                c.a.b.d.e.c(f2764a, "getEcuDiagFileContent->content = " + line);
                try {
                    bufferedReader.close();
                    r2 = line;
                } catch (Exception e3) {
                    e3.printStackTrace();
                    r2 = line;
                }
            } catch (Exception e4) {
                e = e4;
                String str2 = line;
                bufferedReader2 = bufferedReader;
                str = str2;
                e.printStackTrace();
                try {
                    bufferedReader2.close();
                } catch (Exception e5) {
                    e5.printStackTrace();
                }
                r2 = str;
            } catch (Throwable th) {
                th = th;
                r2 = bufferedReader;
            }
            return r2;
        } catch (Throwable th2) {
            th = th2;
        }
        try {
            r2.close();
        } catch (Exception e6) {
            e6.printStackTrace();
        }
        throw th;
    }

    public static boolean b() throws Throwable {
        if (!c()) {
            return true;
        }
        String strA = a();
        return TextUtils.isEmpty(strA) || !strA.equals("ECU_DIAG_FUNC_SWITCH 1");
    }

    public static boolean c() {
        return c.a.b.d.d.n(c.a.b.b.a.m + "ecudiag");
    }
}
