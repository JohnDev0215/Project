package defpackage;

import android.app.Activity;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: ActivityAndroidPermissionDelegate.java */
/* JADX INFO: loaded from: classes3.dex */
public final class l2 extends h5 {
    public final WeakReference<Activity> h;

    public l2(WeakReference<Activity> weakReference) {
        this.h = weakReference;
    }

    @Override // defpackage.h5
    public final boolean a(String str) {
        Activity activity = this.h.get();
        if (activity == null) {
            return false;
        }
        return activity.getPackageManager().isPermissionRevokedByPolicy(str, activity.getPackageName());
    }

    @Override // defpackage.h5
    public final boolean c(String[] strArr, int i) {
        Activity activity = this.h.get();
        if (activity == null) {
            return false;
        }
        activity.requestPermissions(strArr, i);
        return true;
    }

    @Override // org.chromium.ui.permissions.AndroidPermissionDelegate
    public final boolean g(String str) {
        Activity activity = this.h.get();
        if (activity == null) {
            return false;
        }
        return activity.shouldShowRequestPermissionRationale(str);
    }
}
