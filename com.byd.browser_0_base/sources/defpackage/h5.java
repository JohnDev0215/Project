package defpackage;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Handler;
import android.os.Process;
import android.text.TextUtils;
import android.util.SparseArray;
import defpackage.xz;
import java.util.HashMap;
import org.chromium.ui.permissions.AndroidPermissionDelegate;
import org.chromium.ui.permissions.PermissionCallback;

/* JADX INFO: compiled from: AndroidPermissionDelegateWithRequester.java */
/* JADX INFO: loaded from: classes3.dex */
public abstract class h5 implements AndroidPermissionDelegate {
    public final Handler e = new Handler();
    public final SparseArray<b> f = new SparseArray<>();
    public int g;

    /* JADX INFO: compiled from: AndroidPermissionDelegateWithRequester.java */
    public class a implements Runnable {
        public final /* synthetic */ String[] e;
        public final /* synthetic */ PermissionCallback f;

        public a(String[] strArr, PermissionCallback permissionCallback) {
            this.e = strArr;
            this.f = permissionCallback;
        }

        @Override // java.lang.Runnable
        public final void run() {
            String[] strArr = this.e;
            int[] iArr = new int[strArr.length];
            for (int i = 0; i < strArr.length; i++) {
                iArr[i] = h5.this.hasPermission(strArr[i]) ? 0 : -1;
            }
            this.f.s(iArr, strArr);
        }
    }

    /* JADX INFO: compiled from: AndroidPermissionDelegateWithRequester.java */
    public class b {
        public final PermissionCallback a;
        public final HashMap b = new HashMap();

        public b(h5 h5Var, String[] strArr, PermissionCallback permissionCallback) {
            if (Build.VERSION.SDK_INT >= 30) {
                for (String str : strArr) {
                    this.b.put(str, Boolean.valueOf(h5Var.g(str)));
                }
            }
            this.a = permissionCallback;
        }
    }

    public abstract boolean a(String str);

    @Override // org.chromium.ui.permissions.AndroidPermissionDelegate
    public final void b(String[] strArr, PermissionCallback permissionCallback) {
        boolean z;
        int i = this.g;
        int i2 = i + 1000;
        boolean z2 = true;
        this.g = (i + 1) % 100;
        SparseArray<b> sparseArray = this.f;
        sparseArray.put(i2, new b(this, strArr, permissionCallback));
        if (c(strArr, i2)) {
            z = true;
        } else {
            sparseArray.delete(i2);
            z = false;
        }
        if (!z) {
            this.e.post(new a(strArr, permissionCallback));
            return;
        }
        int length = strArr.length;
        int i3 = 0;
        while (true) {
            if (i3 >= length) {
                z2 = false;
                break;
            } else if (TextUtils.equals(strArr[i3], "android.permission.POST_NOTIFICATIONS")) {
                break;
            } else {
                i3++;
            }
        }
        if (z2) {
            xz.a.a.edit().putLong("AndroidPermissionRequestTimestamp::android.permission.POST_NOTIFICATIONS", System.currentTimeMillis()).apply();
        }
    }

    public abstract boolean c(String[] strArr, int i);

    @Override // org.chromium.ui.permissions.AndroidPermissionDelegate
    public final boolean canRequestPermission(String str) {
        if (hasPermission(str)) {
            return true;
        }
        if (a(str)) {
            return false;
        }
        if (!g(str)) {
            return !xz.a.a.getBoolean(e02.a(str), false);
        }
        SharedPreferences.Editor editorEdit = xz.a.a.edit();
        editorEdit.remove(e02.a(str));
        editorEdit.apply();
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0043  */
    @Override // org.chromium.ui.permissions.AndroidPermissionDelegate
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean h(int r9, java.lang.String[] r10, int[] r11) {
        /*
            r8 = this;
            android.util.SparseArray<h5$b> r0 = r8.f
            java.lang.Object r1 = r0.get(r9)
            h5$b r1 = (h5.b) r1
            r0.delete(r9)
            java.util.ArrayList r9 = new java.util.ArrayList
            r9.<init>()
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r2 = 0
            r3 = r2
        L17:
            int r4 = r10.length
            r5 = 1
            if (r3 >= r4) goto L58
            r4 = r11[r3]
            if (r4 != 0) goto L25
            r4 = r10[r3]
            r9.add(r4)
            goto L55
        L25:
            r4 = r10[r3]
            int r6 = android.os.Build.VERSION.SDK_INT
            r7 = 30
            if (r6 >= r7) goto L2e
            goto L4e
        L2e:
            if (r1 == 0) goto L43
            java.util.HashMap r6 = r1.b
            java.lang.Object r7 = r6.get(r4)
            if (r7 == 0) goto L43
            java.lang.Object r6 = r6.get(r4)
            java.lang.Boolean r6 = (java.lang.Boolean) r6
            boolean r6 = r6.booleanValue()
            goto L44
        L43:
            r6 = r2
        L44:
            if (r6 != 0) goto L4e
            boolean r4 = r8.g(r4)
            if (r4 == 0) goto L4d
            goto L4e
        L4d:
            r5 = r2
        L4e:
            if (r5 == 0) goto L55
            r4 = r10[r3]
            r0.add(r4)
        L55:
            int r3 = r3 + 1
            goto L17
        L58:
            android.content.SharedPreferences r8 = xz.a.a
            android.content.SharedPreferences$Editor r8 = r8.edit()
            java.util.Iterator r9 = r9.iterator()
        L62:
            boolean r3 = r9.hasNext()
            if (r3 == 0) goto L76
            java.lang.Object r3 = r9.next()
            java.lang.String r3 = (java.lang.String) r3
            java.lang.String r3 = defpackage.e02.a(r3)
            r8.remove(r3)
            goto L62
        L76:
            java.util.Iterator r9 = r0.iterator()
        L7a:
            boolean r0 = r9.hasNext()
            if (r0 == 0) goto L8e
            java.lang.Object r0 = r9.next()
            java.lang.String r0 = (java.lang.String) r0
            java.lang.String r0 = defpackage.e02.a(r0)
            r8.putBoolean(r0, r5)
            goto L7a
        L8e:
            r8.apply()
            if (r1 == 0) goto L9c
            org.chromium.ui.permissions.PermissionCallback r8 = r1.a
            if (r8 != 0) goto L98
            goto L9c
        L98:
            r8.s(r11, r10)
            return r5
        L9c:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.h5.h(int, java.lang.String[], int[]):boolean");
    }

    @Override // org.chromium.ui.permissions.AndroidPermissionDelegate
    public final boolean hasPermission(String str) {
        boolean z = c6.a(Process.myPid(), Process.myUid(), xz.a, str) == 0;
        if (z) {
            SharedPreferences.Editor editorEdit = xz.a.a.edit();
            editorEdit.remove(e02.a(str));
            editorEdit.apply();
        }
        return z;
    }

    @Override // org.chromium.ui.permissions.AndroidPermissionDelegate
    public final boolean i(String str) {
        return a(str);
    }
}
