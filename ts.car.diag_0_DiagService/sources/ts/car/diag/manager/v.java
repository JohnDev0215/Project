package ts.car.diag.manager;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
public class v {

    /* JADX INFO: compiled from: caradapter-bug */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f1632a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public String f1633b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public String f1634c;

        public a(int i, String str, String str2) {
            this.f1632a = i;
            this.f1633b = str;
            this.f1634c = str2;
        }

        public String toString() {
            return "CommandResult{result=" + this.f1632a + ", successMsg='" + this.f1633b + "', errorMsg='" + this.f1634c + "'}";
        }
    }

    public static a a(String str, boolean z) {
        return b(new String[]{str}, z, true);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00c5  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00c7  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00ce  */
    /* JADX WARN: Type inference failed for: r0v24 */
    /* JADX WARN: Type inference failed for: r0v25 */
    /* JADX WARN: Type inference failed for: r0v6, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r0v8 */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1, types: [java.io.DataOutputStream] */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v5 */
    /* JADX WARN: Type inference failed for: r2v17 */
    /* JADX WARN: Type inference failed for: r2v2 */
    /* JADX WARN: Type inference failed for: r2v3, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r4v19 */
    /* JADX WARN: Type inference failed for: r4v2, types: [java.io.DataOutputStream] */
    /* JADX WARN: Type inference failed for: r4v20 */
    /* JADX WARN: Type inference failed for: r5v0 */
    /* JADX WARN: Type inference failed for: r5v20 */
    /* JADX WARN: Type inference failed for: r5v21 */
    /* JADX WARN: Type inference failed for: r5v22 */
    /* JADX WARN: Type inference failed for: r5v23 */
    /* JADX WARN: Type inference failed for: r5v3, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r5v4, types: [java.io.BufferedReader] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static ts.car.diag.manager.v.a b(java.lang.String[] r8, boolean r9, boolean r10) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 228
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: ts.car.diag.manager.v.b(java.lang.String[], boolean, boolean):ts.car.diag.manager.v$a");
    }

    private static void c(DataOutputStream dataOutputStream, BufferedReader bufferedReader, BufferedReader bufferedReader2, Process process) {
        if (dataOutputStream != null) {
            try {
                dataOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (bufferedReader != null) {
            bufferedReader.close();
        }
        if (bufferedReader2 != null) {
            bufferedReader2.close();
        }
        if (process != null) {
            process.destroy();
        }
    }

    private static void d(DataOutputStream dataOutputStream, String[] strArr) {
        for (String str : strArr) {
            if (str != null) {
                try {
                    dataOutputStream.write(str.getBytes("UTF-8"));
                    dataOutputStream.writeBytes("\n");
                    dataOutputStream.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            dataOutputStream.writeBytes("exit\n");
            dataOutputStream.flush();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
