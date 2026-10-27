package spi;

import android.app.Activity;

/* JADX INFO: compiled from: UtilsActivityLifecycleImpl.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ec implements Runnable {
    public final /* synthetic */ Activity a;
    public final /* synthetic */ com.blankj.utilcode.util.e b;

    public ec(com.blankj.utilcode.util.e eVar, Activity activity) {
        this.b = eVar;
        this.a = activity;
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.util.Map<android.app.Activity, java.util.List<com.blankj.utilcode.util.Utils$ActivityLifecycleCallbacks>>, java.util.concurrent.ConcurrentHashMap] */
    @Override // java.lang.Runnable
    public final void run() {
        this.b.c.remove(this.a);
    }
}
