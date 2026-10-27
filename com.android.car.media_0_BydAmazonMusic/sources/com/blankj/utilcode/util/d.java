package com.blankj.utilcode.util;

import android.app.Activity;
import com.blankj.utilcode.util.Utils;
import java.util.List;

/* JADX INFO: compiled from: UtilsActivityLifecycleImpl.java */
/* JADX INFO: loaded from: classes2.dex */
public final class d implements Runnable {
    public final /* synthetic */ Activity a;
    public final /* synthetic */ Utils.ActivityLifecycleCallbacks b;
    public final /* synthetic */ e c;

    public d(e eVar, Activity activity, Utils.ActivityLifecycleCallbacks activityLifecycleCallbacks) {
        this.c = eVar;
        this.a = activity;
        this.b = activityLifecycleCallbacks;
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.util.Map<android.app.Activity, java.util.List<com.blankj.utilcode.util.Utils$ActivityLifecycleCallbacks>>, java.util.concurrent.ConcurrentHashMap] */
    @Override // java.lang.Runnable
    public final void run() {
        e eVar = this.c;
        Activity activity = this.a;
        Utils.ActivityLifecycleCallbacks activityLifecycleCallbacks = this.b;
        List list = (List) eVar.c.get(activity);
        if (list == null || list.isEmpty()) {
            return;
        }
        list.remove(activityLifecycleCallbacks);
    }
}
