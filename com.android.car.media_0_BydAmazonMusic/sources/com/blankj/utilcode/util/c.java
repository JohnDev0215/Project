package com.blankj.utilcode.util;

import android.app.Activity;
import com.blankj.utilcode.util.Utils;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: compiled from: UtilsActivityLifecycleImpl.java */
/* JADX INFO: loaded from: classes2.dex */
public final class c implements Runnable {
    public final /* synthetic */ Activity a;
    public final /* synthetic */ Utils.ActivityLifecycleCallbacks b;
    public final /* synthetic */ e c;

    public c(e eVar, Activity activity, Utils.ActivityLifecycleCallbacks activityLifecycleCallbacks) {
        this.c = eVar;
        this.a = activity;
        this.b = activityLifecycleCallbacks;
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @Override // java.lang.Runnable
    public final void run() {
        e eVar = this.c;
        Activity activity = this.a;
        Utils.ActivityLifecycleCallbacks activityLifecycleCallbacks = this.b;
        List<Utils.ActivityLifecycleCallbacks> copyOnWriteArrayList = eVar.c.get(activity);
        if (copyOnWriteArrayList == null) {
            copyOnWriteArrayList = new CopyOnWriteArrayList<>();
            eVar.c.put(activity, copyOnWriteArrayList);
        } else if (copyOnWriteArrayList.contains(activityLifecycleCallbacks)) {
            return;
        }
        copyOnWriteArrayList.add(activityLifecycleCallbacks);
    }
}
