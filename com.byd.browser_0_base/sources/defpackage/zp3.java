package defpackage;

import java.io.File;

/* JADX INFO: compiled from: WebApkUmaRecorder.java */
/* JADX INFO: loaded from: classes.dex */
public final class zp3 {
    public static long a(File file) {
        if (file == null) {
            return 0L;
        }
        if (!file.isDirectory()) {
            return file.length();
        }
        try {
            File[] fileArrListFiles = file.listFiles();
            if (fileArrListFiles == null) {
                return 0L;
            }
            long jA = 0;
            for (File file2 : fileArrListFiles) {
                jA += a(file2);
            }
            return jA;
        } catch (SecurityException unused) {
            return 0L;
        }
    }
}
