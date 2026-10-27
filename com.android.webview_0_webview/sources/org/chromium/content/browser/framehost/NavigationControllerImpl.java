package org.chromium.content.browser.framehost;

import J.N;
import android.graphics.Bitmap;
import defpackage.C0984jE;
import defpackage.RD;
import defpackage.VD;
import org.chromium.content_public.browser.NavigationController;
import org.chromium.content_public.browser.NavigationEntry;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class NavigationControllerImpl implements NavigationController {
    public long a;

    public NavigationControllerImpl(long j) {
        this.a = j;
    }

    public static void addToNavigationHistory(Object obj, Object obj2) {
        ((VD) obj).a.add((NavigationEntry) obj2);
    }

    public static NavigationControllerImpl create(long j) {
        return new NavigationControllerImpl(j);
    }

    public static NavigationEntry createNavigationEntry(int i, String str, String str2, String str3, String str4, String str5, Bitmap bitmap, int i2, long j) {
        return new NavigationEntry(i, str, str2, str3, str4, str5, bitmap, i2, j);
    }

    private void destroy() {
        this.a = 0L;
    }

    @Override // org.chromium.content_public.browser.NavigationController
    public void a(int i) {
        if (this.a != 0) {
            N.ML_uBbdi(this.a, this, i);
        }
    }

    @Override // org.chromium.content_public.browser.NavigationController
    public boolean b(int i) {
        return this.a != 0 && N.M4jjW0jG(this.a, this, i);
    }

    @Override // org.chromium.content_public.browser.NavigationController
    public void c(boolean z) {
        if (this.a != 0) {
            N.M81h$w2r(this.a, this, z);
        }
    }

    @Override // org.chromium.content_public.browser.NavigationController
    public boolean canGoBack() {
        return this.a != 0 && N.MgAw5sIR(this.a, this);
    }

    @Override // org.chromium.content_public.browser.NavigationController
    public boolean canGoForward() {
        return this.a != 0 && N.MCUxt83x(this.a, this);
    }

    @Override // org.chromium.content_public.browser.NavigationController
    public void clearHistory() {
        if (this.a != 0) {
            N.MK2ttcH1(this.a, this);
        }
    }

    @Override // org.chromium.content_public.browser.NavigationController
    public void clearSslPreferences() {
        if (this.a != 0) {
            N.MpKIKzer(this.a, this);
        }
    }

    @Override // org.chromium.content_public.browser.NavigationController
    public void d() {
        if (this.a != 0) {
            N.MNF4lMMb(this.a, this);
        }
    }

    @Override // org.chromium.content_public.browser.NavigationController
    public void e(RD rd) {
        long j = this.a;
        if (j != 0) {
            String str = rd.a;
            int i = rd.b;
            int i2 = rd.c;
            C0984jE c0984jE = rd.d;
            String str2 = c0984jE != null ? c0984jE.a : null;
            C0984jE c0984jE2 = rd.d;
            N.MAqmDh4t(j, this, str, i, i2, str2, c0984jE2 != null ? c0984jE2.b : 0, rd.f, rd.d("\n", false), rd.g, rd.h, rd.i, rd.j, rd.k, false, false);
        }
    }

    @Override // org.chromium.content_public.browser.NavigationController
    public void f() {
        if (this.a != 0) {
            N.Mp5SLq_N(this.a, this);
        }
    }

    @Override // org.chromium.content_public.browser.NavigationController
    public VD g() {
        if (this.a == 0) {
            return null;
        }
        VD vd = new VD();
        vd.b = N.MUZRDUOx(this.a, this, vd);
        return vd;
    }

    @Override // org.chromium.content_public.browser.NavigationController
    public void goBack() {
        if (this.a != 0) {
            N.Mdhd0AR3(this.a, this);
        }
    }

    @Override // org.chromium.content_public.browser.NavigationController
    public void goForward() {
        if (this.a != 0) {
            N.MEEEhNfT(this.a, this);
        }
    }
}
