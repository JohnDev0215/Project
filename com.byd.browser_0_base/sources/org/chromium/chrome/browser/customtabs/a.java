package org.chromium.chrome.browser.customtabs;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.SparseBooleanArray;
import androidx.browser.customtabs.CustomTabsSessionToken;
import defpackage.a32;
import defpackage.a8;
import defpackage.b80;
import defpackage.d50;
import defpackage.ft1;
import defpackage.jb2;
import defpackage.n3;
import defpackage.oj2;
import defpackage.on2;
import defpackage.rh3;
import defpackage.sq;
import defpackage.vt1;
import defpackage.w01;
import defpackage.x22;
import defpackage.z22;
import java.util.HashMap;
import java.util.HashSet;
import org.chromium.base.SysUtils;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.browserservices.verification.ChromeOriginVerifier;
import org.chromium.components.content_relationship_verification.OriginVerifier;
import org.chromium.content_public.browser.WebContents;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: ClientManager.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {
    public final sq a;
    public final b b;
    public final HashMap c;
    public final SparseBooleanArray d;
    public boolean e;

    /* JADX INFO: renamed from: org.chromium.chrome.browser.customtabs.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ClientManager.java */
    public interface InterfaceC0076a {
    }

    /* JADX INFO: compiled from: ClientManager.java */
    public interface b {
    }

    /* JADX INFO: compiled from: ClientManager.java */
    public static class d implements b {
    }

    /* JADX INFO: compiled from: ClientManager.java */
    public static class e {
        public final int a;
        public n3 b;
        public final InterfaceC0076a c;
        public final z22 d;
        public final a32 e;
        public final HashSet f = new HashSet();
        public ChromeOriginVerifier g;
        public boolean h;
        public boolean i;
        public final String j;
        public boolean k;
        public c l;
        public String m;
        public long n;
        public boolean o;
        public boolean p;

        public e(Context context, int i, CustomTabsSessionToken.a aVar, org.chromium.chrome.browser.customtabs.b bVar, z22 z22Var, d50 d50Var) {
            this.a = i;
            String[] packagesForUid = context.getPackageManager().getPackagesForUid(i);
            String str = (packagesForUid.length != 1 || TextUtils.isEmpty(packagesForUid[0])) ? null : packagesForUid[0];
            this.j = str;
            this.b = aVar;
            this.c = bVar;
            this.d = z22Var;
            this.e = d50Var;
            d50Var.h = str;
        }
    }

    /* JADX INFO: compiled from: ClientManager.java */
    public interface f<T> {
        T o(e eVar);
    }

    public a() {
        a8 a8Var = new a8();
        d dVar = new d();
        this.c = new HashMap();
        this.d = new SparseBooleanArray();
        this.a = a8Var;
        this.b = dVar;
        jb2.b();
    }

    public static void e(e eVar) {
        if (eVar.p) {
            return;
        }
        boolean z = eVar.o;
        int i = 0;
        boolean z2 = eVar.l != null;
        boolean zIsCurrentlyLowMemory = SysUtils.isCurrentlyLowMemory();
        if (zIsCurrentlyLowMemory && z && z2) {
            i = 6;
        } else if (zIsCurrentlyLowMemory && z && !z2) {
            i = 5;
        } else if (zIsCurrentlyLowMemory && !z && z2) {
            i = 8;
        } else if (zIsCurrentlyLowMemory && !z && !z2) {
            i = 7;
        } else if (z && !z2) {
            i = 1;
        } else if (z && z2) {
            i = 2;
        } else if (!z && !z2) {
            i = 3;
        } else if (!z && z2) {
            i = 4;
        }
        oj2.U(i, 9, "CustomTabs.SessionDisconnectStatus");
        eVar.p = true;
    }

    public final synchronized <T> T a(CustomTabsSessionToken customTabsSessionToken, T t, f<T> fVar) {
        e eVar = (e) this.c.get(customTabsSessionToken);
        if (eVar == null) {
            return t;
        }
        return fVar.o(eVar);
    }

    public final n3 b(CustomTabsSessionToken customTabsSessionToken) {
        synchronized (this) {
            e eVar = (e) this.c.get(customTabsSessionToken);
            if (eVar == null) {
                return null;
            }
            return eVar.b;
        }
    }

    public final String c(CustomTabsSessionToken customTabsSessionToken) {
        synchronized (this) {
            e eVar = (e) this.c.get(customTabsSessionToken);
            if (eVar == null) {
                return null;
            }
            return eVar.j;
        }
    }

    public final synchronized on2 d(CustomTabsSessionToken customTabsSessionToken) {
        String strC;
        strC = c(customTabsSessionToken);
        return TextUtils.isEmpty(strC) ? null : new on2(new Uri.Builder().scheme("android-app").authority(strC).build().toString(), 1);
    }

    public final void f(CustomTabsSessionToken customTabsSessionToken, WebContents webContents) {
        synchronized (this) {
            e eVar = (e) this.c.get(customTabsSessionToken);
            if (eVar == null) {
                return;
            }
            z22 z22Var = eVar.d;
            if (webContents != null) {
                z22Var.getClass();
                if (webContents.isDestroyed()) {
                    z22Var.b();
                } else if (!webContents.equals(z22Var.c)) {
                    z22Var.c = webContents;
                    if (z22Var.e != null) {
                        new x22(z22Var, webContents, webContents);
                    }
                }
            } else {
                z22Var.b();
            }
        }
    }

    public final synchronized boolean g(final CustomTabsSessionToken customTabsSessionToken, final int i, final ft1 ft1Var, final boolean z) {
        final e eVar = (e) this.c.get(customTabsSessionToken);
        if (eVar != null && !TextUtils.isEmpty(eVar.j)) {
            OriginVerifier.a aVar = new OriginVerifier.a() { // from class: cu
                @Override // org.chromium.components.content_relationship_verification.OriginVerifier.a
                public final void a(String str, ft1 ft1Var2, boolean z2, Boolean bool) {
                    Bundle bundle;
                    n3 n3VarB = this.a.b(customTabsSessionToken);
                    if (n3VarB != null) {
                        if (!z2 || bool == null) {
                            bundle = null;
                        } else {
                            bundle = new Bundle();
                            bundle.putBoolean("online", bool.booleanValue());
                        }
                        n3VarB.U0(i, ft1Var.a, z2, bundle);
                    }
                    if (z) {
                        eVar.d.a(str, ft1Var2, z2, bool);
                    }
                }
            };
            eVar.g = this.a.a(eVar.j, i, null, null);
            PostTask.d(rh3.a, new b80(eVar, 3, aVar, ft1Var));
            if (i == 2) {
                b bVar = this.b;
                String str = eVar.j;
                ((d) bVar).getClass();
                if (w01.v0(str, new GURL(ft1Var.toString()), new vt1())) {
                    eVar.f.add(ft1Var);
                }
            }
            return true;
        }
        return false;
    }

    /* JADX INFO: compiled from: ClientManager.java */
    public static class c implements ServiceConnection {
        public final Context e;
        public final Intent f;
        public boolean g;
        public boolean h;

        public c(Context context, Intent intent) {
            this.e = context;
            this.f = intent;
        }

        @Override // android.content.ServiceConnection
        public final void onServiceDisconnected(ComponentName componentName) {
            boolean z = this.h;
            if (z) {
                this.g = true;
                if (z) {
                    this.e.unbindService(this);
                    this.h = false;
                }
            }
        }

        @Override // android.content.ServiceConnection
        public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        }
    }
}
