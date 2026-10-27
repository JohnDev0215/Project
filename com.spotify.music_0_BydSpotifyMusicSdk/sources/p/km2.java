package p;

import android.os.Trace;

/* JADX INFO: loaded from: classes.dex */
public abstract class km2 {
    public static void a(String str) {
        Trace.beginSection(str);
    }

    public static void b() {
        Trace.endSection();
    }
}
