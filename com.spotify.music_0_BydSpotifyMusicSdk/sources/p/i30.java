package p;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import java.util.concurrent.ExecutorService;

/* JADX INFO: loaded from: classes.dex */
public final class i30 {
    public final Context a;
    public final x50 b;
    public final ww1 c;
    public final long d;
    public g90 e;
    public g90 f;
    public boolean g;
    public com.google.firebase.crashlytics.internal.common.a h;
    public final e31 i;
    public final gu0 j;
    public final mn k;
    public final q6 l;
    public final ExecutorService m;
    public final gh2 n;
    public final j30 o;

    public i30(ru0 ru0Var, e31 e31Var, k30 k30Var, x50 x50Var, o6 o6Var, o6 o6Var2, gu0 gu0Var, ExecutorService executorService) {
        this.b = x50Var;
        ru0Var.a();
        this.a = ru0Var.a;
        this.i = e31Var;
        this.o = k30Var;
        this.k = o6Var;
        this.l = o6Var2;
        this.m = executorService;
        this.j = gu0Var;
        this.n = new gh2(executorService, 10);
        this.d = System.currentTimeMillis();
        this.c = new ww1();
    }

    public static b33 a(i30 i30Var, com.google.firebase.crashlytics.internal.settings.a aVar) {
        b33 b33Var;
        h30 h30Var;
        gh2 gh2Var = i30Var.n;
        gh2 gh2Var2 = i30Var.n;
        if (!Boolean.TRUE.equals(((ThreadLocal) gh2Var.m).get())) {
            throw new IllegalStateException("Not running on background worker thread as intended.");
        }
        i30Var.e.d();
        Log.isLoggable("FirebaseCrashlytics", 2);
        int i = 0;
        try {
            try {
                i30Var.k.f(new g30());
                if (aVar.b().b.a) {
                    i30Var.h.e(aVar);
                    b33Var = i30Var.h.g(((bj2) aVar.i.get()).a);
                    h30Var = new h30(i, i30Var);
                } else {
                    Log.isLoggable("FirebaseCrashlytics", 3);
                    RuntimeException runtimeException = new RuntimeException("Collection of crash reports disabled in Crashlytics settings.");
                    b33Var = new b33();
                    b33Var.f(runtimeException);
                    h30Var = new h30(i, i30Var);
                }
            } catch (Exception e) {
                Log.e("FirebaseCrashlytics", "Crashlytics encountered a problem during asynchronous initialization.", e);
                b33Var = new b33();
                b33Var.f(e);
                h30Var = new h30(i, i30Var);
            }
            gh2Var2.x(h30Var);
            return b33Var;
        } catch (Throwable th) {
            gh2Var2.x(new h30(i, i30Var));
            throw th;
        }
    }

    public final void b(Boolean bool) {
        Boolean boolA;
        x50 x50Var = this.b;
        synchronized (x50Var) {
            if (bool != null) {
                try {
                    x50Var.c = false;
                } catch (Throwable th) {
                    throw th;
                }
            }
            if (bool != null) {
                boolA = bool;
            } else {
                ru0 ru0Var = (ru0) x50Var.e;
                ru0Var.a();
                boolA = x50Var.a(ru0Var.a);
            }
            x50Var.i = boolA;
            SharedPreferences.Editor editorEdit = ((SharedPreferences) x50Var.d).edit();
            if (bool != null) {
                editorEdit.putBoolean("firebase_crashlytics_collection_enabled", bool.booleanValue());
            } else {
                editorEdit.remove("firebase_crashlytics_collection_enabled");
            }
            editorEdit.apply();
            synchronized (x50Var.f) {
                try {
                    if (x50Var.b()) {
                        if (!x50Var.b) {
                            ((bj2) x50Var.g).b(null);
                            x50Var.b = true;
                        }
                    } else if (x50Var.b) {
                        x50Var.g = new bj2();
                        x50Var.b = false;
                    }
                } finally {
                }
            }
        }
    }

    public final void c(String str, String str2) {
        com.google.firebase.crashlytics.internal.common.a aVar = this.h;
        aVar.getClass();
        try {
            aVar.d.q(str, str2);
        } catch (IllegalArgumentException e) {
            Context context = aVar.a;
            if (context != null && (context.getApplicationInfo().flags & 2) != 0) {
                throw e;
            }
            Log.e("FirebaseCrashlytics", "Attempting to set custom attribute with null key, ignoring.", null);
        }
    }
}
