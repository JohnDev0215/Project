package defpackage;

import android.app.Dialog;
import android.os.Process;

/* JADX INFO: compiled from: PermissionUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public final class i02 {
    public static Dialog a;

    /* JADX INFO: compiled from: PermissionUtils.java */
    public interface a {
    }

    public static boolean a(String[] strArr) {
        for (String str : strArr) {
            if (c6.a(Process.myPid(), Process.myUid(), xz.a, str) != 0) {
                return false;
            }
        }
        Dialog dialog = a;
        if (dialog == null || !dialog.isShowing()) {
            return true;
        }
        a.dismiss();
        a = null;
        ik.g().p.sendEmptyMessage(5);
        return true;
    }
}
