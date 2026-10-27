package com.byd.auto_photo;

import android.app.Activity;
import android.content.Context;
import android.os.Process;
import com.byd.util.w;

/* JADX INFO: loaded from: classes.dex */
public class PhotoApp extends PhotoBaseApp {
    @Override // com.byd.auto_photo.PhotoBaseApp
    public void f(Activity activity) {
        if (PhotoBaseApp.b().size() == 1 && ((activity instanceof ImageShowActivity) || (activity instanceof VideoPlayActivity) || (activity instanceof ImageSelectActivity))) {
            super.f(activity);
            return;
        }
        super.f(activity);
        if (d()) {
            w.c("==all photo activity finish==");
            Process.killProcess(Process.myPid());
        }
    }

    @Override // com.byd.auto_photo.PhotoBaseApp
    public void g(Context context, int i, boolean z) {
        if (!z) {
            super.g(context, i, false);
        } else if (context instanceof Activity) {
            w.i((Activity) context, getResources().getString(i), 0, R.drawable.icon_toast_ok);
        } else {
            w.l(context, i, 0, R.drawable.icon_toast_ok);
        }
    }

    @Override // com.byd.auto_photo.PhotoBaseApp, android.app.Application
    public void onCreate() {
        super.onCreate();
        com.byd.util.a.a(this);
    }
}
