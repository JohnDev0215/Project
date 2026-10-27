package org.chromium.chrome.browser.permissions;

import J.N;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import defpackage.xz;
import java.util.Collections;
import java.util.HashSet;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.ui.permissions.PermissionCallback;

/* JADX INFO: loaded from: classes2.dex */
public class PermissionUpdateRequester implements PermissionCallback {
    public final WebContents e;
    public final HashSet f;
    public final String[] g;
    public long h;
    public ApplicationStatus.c i;

    public class a implements ApplicationStatus.c {
        public a() {
        }

        @Override // org.chromium.base.ApplicationStatus.c
        public final void d(Activity activity, int i) {
            PermissionUpdateRequester permissionUpdateRequester = PermissionUpdateRequester.this;
            if (i == 6) {
                ApplicationStatus.i(this);
                permissionUpdateRequester.i = null;
                N.ML3pG92D(permissionUpdateRequester.h, false);
            } else if (i == 3) {
                ApplicationStatus.i(this);
                permissionUpdateRequester.i = null;
                permissionUpdateRequester.a();
            }
        }
    }

    public PermissionUpdateRequester(long j, WebContents webContents, String[] strArr, String[] strArr2) {
        this.h = j;
        this.e = webContents;
        HashSet hashSet = new HashSet();
        this.f = hashSet;
        Collections.addAll(hashSet, strArr);
        HashSet hashSet2 = new HashSet();
        Collections.addAll(hashSet2, strArr);
        Collections.addAll(hashSet2, strArr2);
        this.g = (String[]) hashSet2.toArray(new String[hashSet2.size()]);
    }

    @CalledByNative
    public static PermissionUpdateRequester create(long j, WebContents webContents, String[] strArr, String[] strArr2) {
        return new PermissionUpdateRequester(j, webContents, strArr, strArr2);
    }

    public final void a() {
        WindowAndroid windowAndroidV = this.e.V();
        boolean z = false;
        int i = 0;
        if (windowAndroidV != null) {
            boolean zHasPermission = true;
            while (true) {
                String[] strArr = this.g;
                if (i >= strArr.length) {
                    break;
                }
                if (this.f.contains(strArr[i])) {
                    zHasPermission &= windowAndroidV.hasPermission(strArr[i]);
                }
                i++;
            }
            z = zHasPermission;
        }
        long j = this.h;
        if (j != 0) {
            N.ML3pG92D(j, z);
        }
    }

    @CalledByNative
    public final void onNativeDestroyed() {
        this.h = 0L;
        ApplicationStatus.c cVar = this.i;
        if (cVar != null) {
            ApplicationStatus.i(cVar);
            this.i = null;
        }
    }

    @CalledByNative
    public final void requestPermissions() {
        String[] strArr;
        WindowAndroid windowAndroidV = this.e.V();
        if (windowAndroidV == null) {
            N.ML3pG92D(this.h, false);
            return;
        }
        int i = 0;
        boolean z = true;
        while (true) {
            strArr = this.g;
            if (i >= strArr.length) {
                break;
            }
            z &= windowAndroidV.hasPermission(strArr[i]) || windowAndroidV.canRequestPermission(strArr[i]);
            i++;
        }
        Activity activity = (Activity) windowAndroidV.k().get();
        if (z) {
            windowAndroidV.b(strArr, this);
            return;
        }
        if (activity == null) {
            N.ML3pG92D(this.h, false);
            return;
        }
        a aVar = new a();
        this.i = aVar;
        ApplicationStatus.f(aVar, activity);
        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.parse("package:" + xz.a.getPackageName()));
        intent.setFlags(268435456);
        activity.startActivity(intent);
    }

    @Override // org.chromium.ui.permissions.PermissionCallback
    public final void s(int[] iArr, String[] strArr) {
        a();
    }
}
