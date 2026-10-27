package defpackage;

import android.content.Context;
import android.util.Pair;
import org.chromium.base.Callback;
import org.chromium.chrome.R$string;
import org.chromium.components.permissions.AndroidPermissionRequester;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: compiled from: FileAccessPermissionHelper.java */
/* JADX INFO: loaded from: classes.dex */
public final class km0 {
    public static void a(WindowAndroid windowAndroid, Callback<Pair<Boolean, String>> callback) {
        if (yj.a() || windowAndroid.hasPermission("android.permission.WRITE_EXTERNAL_STORAGE")) {
            callback.onResult(Pair.create(Boolean.TRUE, null));
            return;
        }
        if (!windowAndroid.canRequestPermission("android.permission.WRITE_EXTERNAL_STORAGE")) {
            callback.onResult(Pair.create(Boolean.FALSE, windowAndroid.i("android.permission.WRITE_EXTERNAL_STORAGE") ? null : "android.permission.WRITE_EXTERNAL_STORAGE"));
            return;
        }
        Context context = windowAndroid.i.get();
        if (context == null) {
            callback.onResult(Pair.create(Boolean.FALSE, null));
            return;
        }
        im0 im0Var = new im0(windowAndroid, callback, windowAndroid, 0);
        if (windowAndroid.n() != null) {
            AndroidPermissionRequester.b(windowAndroid, context.getString(R$string.missing_storage_permission_download_education_text), im0Var, callback.p1(Pair.create(Boolean.FALSE, null)));
        } else {
            im0Var.accept(null);
        }
    }
}
