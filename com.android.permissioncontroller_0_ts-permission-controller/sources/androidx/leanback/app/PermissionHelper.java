package androidx.leanback.app;

import android.app.Fragment;
import android.os.Build;

/* JADX INFO: loaded from: classes3.dex */
public class PermissionHelper {
    public static void requestPermissions(Fragment fragment, String[] permissions, int requestCode) {
        if (Build.VERSION.SDK_INT >= 23) {
            fragment.requestPermissions(permissions, requestCode);
        }
    }

    private PermissionHelper() {
    }
}
