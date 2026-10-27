package org.chromium.chrome.browser.enterprise.util;

import android.app.admin.DevicePolicyManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import defpackage.a9;
import defpackage.d73;
import defpackage.i9;
import defpackage.iv;
import defpackage.m62;
import defpackage.th0;
import defpackage.xz;
import java.util.LinkedList;
import java.util.concurrent.RejectedExecutionException;
import org.chromium.base.Callback;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.enterprise.util.EnterpriseInfo;

/* JADX INFO: compiled from: EnterpriseInfoImpl.java */
/* JADX INFO: loaded from: classes.dex */
public final class a extends EnterpriseInfo {
    public EnterpriseInfo.a c = null;
    public final LinkedList d = new LinkedList();
    public final Handler b = new Handler(Looper.myLooper());

    /* JADX INFO: renamed from: org.chromium.chrome.browser.enterprise.util.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: EnterpriseInfoImpl.java */
    public class C0081a extends i9<EnterpriseInfo.a> {
        public C0081a() {
        }

        @Override // defpackage.i9
        public final EnterpriseInfo.a b() {
            Context context = xz.a;
            PackageManager packageManager = context.getPackageManager();
            DevicePolicyManager devicePolicyManager = (DevicePolicyManager) context.getSystemService("device_policy");
            boolean zG = iv.e().g("force-device-ownership");
            boolean z = false;
            for (PackageInfo packageInfo : packageManager.getInstalledPackages(0)) {
                if (devicePolicyManager.isProfileOwnerApp(packageInfo.packageName)) {
                    z = true;
                }
                if (devicePolicyManager.isDeviceOwnerApp(packageInfo.packageName)) {
                    zG = true;
                }
                if (z && zG) {
                    break;
                }
            }
            return new EnterpriseInfo.a(zG, z);
        }

        @Override // defpackage.i9
        public final void l(EnterpriseInfo.a aVar) {
            a aVar2 = a.this;
            aVar2.getClass();
            Object obj = ThreadUtils.a;
            aVar2.c = aVar;
            while (true) {
                LinkedList linkedList = aVar2.d;
                if (linkedList.size() <= 0) {
                    return;
                } else {
                    ((Callback) linkedList.remove()).onResult(aVar2.c);
                }
            }
        }
    }

    @Override // org.chromium.chrome.browser.enterprise.util.EnterpriseInfo
    public final void a(Callback<EnterpriseInfo.a> callback) {
        Object obj = ThreadUtils.a;
        EnterpriseInfo.a aVar = this.c;
        Handler handler = this.b;
        if (aVar != null) {
            handler.post(new a9(this, 7, callback));
            return;
        }
        LinkedList linkedList = this.d;
        linkedList.add(callback);
        if (linkedList.size() > 1) {
            return;
        }
        try {
            new C0081a().e(d73.h);
        } catch (RejectedExecutionException unused) {
            Log.w("cr_EnterpriseInfoImpl", "Thread limit reached, unable to determine managed state.");
            handler.post(new th0((Callback) linkedList.remove(), 0));
        }
    }

    @Override // org.chromium.chrome.browser.enterprise.util.EnterpriseInfo
    public final void c() {
        a(new m62(3));
    }
}
