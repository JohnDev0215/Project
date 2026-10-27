package com.byd.auto_photo;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Process;
import com.byd.util.e;
import com.byd.util.w;
import java.util.LinkedHashSet;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public abstract class PhotoBaseApp extends Application {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static Set<Activity> f1126a = new LinkedHashSet();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f1127b;

    public static Set<Activity> b() {
        return f1126a;
    }

    public void a(Activity activity) {
        int size = f1126a.size();
        w.c("task record + : " + activity + " | " + size);
        f1126a.add(activity);
        if (size <= 1 || !(activity instanceof VideoPlayActivity)) {
            return;
        }
        int i = 0;
        for (Activity activity2 : f1126a) {
            if (i >= size) {
                return;
            }
            if ((activity2 instanceof VideoPlayActivity) && ((VideoPlayActivity) activity2).O0()) {
                w.c("task record -> has activity from third in background , finish -> " + activity2);
                activity2.onBackPressed();
            }
            i++;
        }
    }

    public int c() {
        return this.f1127b;
    }

    public boolean d() {
        return f1126a.size() == 0;
    }

    protected void e(String str) {
    }

    public void f(Activity activity) {
        f1126a.remove(activity);
        w.c("task record - : " + activity + " " + f1126a.size());
    }

    public void g(Context context, int i, boolean z) {
        if (context instanceof Activity) {
            w.h((Activity) context, getResources().getString(i), 0);
        } else {
            w.j(context, i);
        }
    }

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        String string = null;
        try {
            ApplicationInfo applicationInfo = getPackageManager().getApplicationInfo(getPackageName(), 128);
            string = applicationInfo.metaData.getString("CHANNEL_NAME");
            this.f1127b = applicationInfo.metaData.getInt("COMPILE_SDK_VERSION");
        } catch (Exception e) {
            e.printStackTrace();
        }
        e(string);
        w.c("==PhotoApp onCreate==" + string + "_" + e.g(this) + "|" + this.f1127b + "|" + getResources().getConfiguration().mcc + "|" + Process.myPid());
    }
}
