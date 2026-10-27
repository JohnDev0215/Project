package defpackage;

import android.util.Log;
import java.io.File;

/* JADX INFO: compiled from: TabStateDirectory.java */
/* JADX INFO: loaded from: classes2.dex */
public final class s33 {
    public static final Object a = new Object();
    public static final Object b = new Object();
    public static File c;
    public static File d;

    /* JADX INFO: compiled from: TabStateDirectory.java */
    public static class a {
        public static final File a = xz.a.getDir("tabs", 0);
    }

    public static File a() {
        synchronized (a) {
            if (c == null) {
                c = new File(a.a, "0");
                ku2 ku2VarC = ku2.C();
                try {
                    if (!c.exists() && !c.mkdirs()) {
                        Log.e("cr_tabpersistence", "Failed to create state folder: " + c);
                    }
                    ku2VarC.close();
                } catch (Throwable th) {
                    try {
                        ku2VarC.close();
                    } catch (Throwable unused) {
                    }
                    throw th;
                }
            }
        }
        return c;
    }
}
