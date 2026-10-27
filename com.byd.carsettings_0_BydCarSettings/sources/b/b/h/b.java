package b.b.h;

import android.app.Application;
import b.b.h.f.b;
import java.lang.ref.WeakReference;
import net.sqlcipher.database.SQLiteDatabase;

/* JADX INFO: compiled from: DBEnv.java */
/* JADX INFO: loaded from: classes2.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f1850a = "b";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static WeakReference<Application> f1851b;

    public static b.b.h.f.b a(b.a aVar) {
        return b.b.h.f.c.i(aVar);
    }

    public static void b(Application application) {
        f1851b = new WeakReference<>(application);
        if (application != null) {
            SQLiteDatabase.loadLibs(application.getApplicationContext());
        }
    }
}
