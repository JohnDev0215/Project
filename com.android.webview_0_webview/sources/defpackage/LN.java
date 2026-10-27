package defpackage;

import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;
import android.os.Build;
import android.os.Handler;
import android.os.Process;
import android.text.TextUtils;
import android.util.SparseArray;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class LN implements JN {
    public Handler s = new Handler();
    public SparseArray t = new SparseArray();
    public int u;

    @Override // defpackage.JN
    public final boolean B(int i, String[] strArr, int[] iArr) {
        SharedPreferences.Editor editorEdit = Hs.a.edit();
        for (String str : strArr) {
            editorEdit.putBoolean(a(str), true);
        }
        editorEdit.apply();
        RN rn = (RN) this.t.get(i);
        this.t.delete(i);
        if (rn == null) {
            return false;
        }
        rn.a(strArr, iArr);
        return true;
    }

    @Override // defpackage.JN
    public final void E(String[] strArr, RN rn) {
        boolean z = false;
        if (Build.VERSION.SDK_INT >= 23) {
            int i = this.u;
            int i2 = i + 1000;
            this.u = (i + 1) % 100;
            this.t.put(i2, rn);
            if (c(strArr, i2)) {
                z = true;
            } else {
                this.t.delete(i2);
            }
        }
        if (z) {
            return;
        }
        this.s.post(new KN(this, strArr, rn));
    }

    public final String a(String str) {
        if (Build.VERSION.SDK_INT < 26) {
            try {
                PermissionInfo permissionInfo = Is.a.getPackageManager().getPermissionInfo(str, 128);
                if (!TextUtils.isEmpty(permissionInfo.group)) {
                    str = permissionInfo.group;
                }
            } catch (PackageManager.NameNotFoundException unused) {
            }
        }
        return Z7.c("HasRequestedAndroidPermission::", str);
    }

    public abstract boolean b(String str);

    public abstract boolean c(String[] strArr, int i);

    @Override // defpackage.JN
    public final boolean canRequestPermission(String str) {
        int i = Build.VERSION.SDK_INT;
        if (i < 23) {
            return false;
        }
        if (i < 23 ? false : b(str)) {
            return false;
        }
        if (d(str)) {
            return true;
        }
        return !Hs.a.getBoolean(a(str), false);
    }

    public abstract boolean d(String str);

    @Override // defpackage.JN
    public final boolean hasPermission(String str) {
        return AbstractC1623us.a(Is.a, str, Process.myPid(), Process.myUid()) == 0;
    }
}
