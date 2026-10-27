package p;

import android.util.Log;
import java.io.File;
import java.util.Iterator;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import kotlin.Pair;

/* JADX INFO: loaded from: classes.dex */
public final class a30 implements Callable {
    public final /* synthetic */ int c;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object k;

    public /* synthetic */ a30(Object obj, int i, Object obj2) {
        this.c = i;
        this.k = obj;
        this.e = obj2;
    }

    public final b33 a() {
        int i = this.c;
        Object obj = this.e;
        Object obj2 = this.k;
        switch (i) {
            case 1:
                Boolean bool = (Boolean) obj;
                if (bool.booleanValue()) {
                    Log.isLoggable("FirebaseCrashlytics", 3);
                    boolean zBooleanValue = bool.booleanValue();
                    x42 x42Var = (x42) obj2;
                    x50 x50Var = ((com.google.firebase.crashlytics.internal.common.a) x42Var.k).b;
                    if (!zBooleanValue) {
                        x50Var.getClass();
                        throw new IllegalStateException("An invalid data collection token was used.");
                    }
                    ((bj2) x50Var.h).b(null);
                    Executor executor = (Executor) ((com.google.firebase.crashlytics.internal.common.a) x42Var.k).e.e;
                    return ((aj2) x42Var.e).e(executor, new ay2(this, executor, 0));
                }
                Log.isLoggable("FirebaseCrashlytics", 2);
                x42 x42Var2 = (x42) obj2;
                com.google.firebase.crashlytics.internal.common.a aVar = (com.google.firebase.crashlytics.internal.common.a) x42Var2.k;
                Iterator it = gu0.p(((File) aVar.g.b).listFiles(com.google.firebase.crashlytics.internal.common.a.r)).iterator();
                while (it.hasNext()) {
                    ((File) it.next()).delete();
                }
                gu0 gu0Var = ((com.google.firebase.crashlytics.internal.common.a) x42Var2.k).l.b.b;
                j40.a(gu0.p(((File) gu0Var.d).listFiles()));
                j40.a(gu0.p(((File) gu0Var.e).listFiles()));
                j40.a(gu0.p(((File) gu0Var.f).listFiles()));
                ((com.google.firebase.crashlytics.internal.common.a) x42Var2.k).f38p.b(null);
                return ci1.t(null);
            default:
                return i30.a((i30) obj2, (com.google.firebase.crashlytics.internal.settings.a) obj);
        }
    }

    @Override // java.util.concurrent.Callable
    public final Object call() {
        Object obj = this.e;
        Object obj2 = this.k;
        int i = this.c;
        switch (i) {
            case 0:
                switch (i) {
                    case 0:
                        ((Runnable) obj).run();
                        return null;
                    default:
                        com.google.firebase.crashlytics.internal.common.a.a((com.google.firebase.crashlytics.internal.common.a) obj2, (String) obj);
                        return null;
                }
            case 1:
                return a();
            case 2:
                switch (i) {
                    case 0:
                        ((Runnable) obj).run();
                        return null;
                    default:
                        com.google.firebase.crashlytics.internal.common.a.a((com.google.firebase.crashlytics.internal.common.a) obj2, (String) obj);
                        return null;
                }
            case 3:
                return a();
            default:
                try {
                    ts1 ts1Var = (ts1) obj2;
                    v03.i(ts1Var, "event");
                    jw0.a((jw0) obj, ts1Var);
                    return new Pair(((ts1) obj2).a, null);
                } catch (Throwable th) {
                    return new Pair(((ts1) obj2).a, th);
                }
        }
    }

    public a30(jw0 jw0Var, ts1 ts1Var) {
        this.c = 4;
        this.e = jw0Var;
        this.k = ts1Var;
    }
}
