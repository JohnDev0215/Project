package org.chromium.content.browser.framehost;

import J.N;
import android.graphics.Bitmap;
import android.os.SystemClock;
import android.util.Log;
import defpackage.nw2;
import defpackage.oj2;
import defpackage.on2;
import defpackage.xk1;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.content_public.browser.LoadUrlParams;
import org.chromium.content_public.browser.NavigationController;
import org.chromium.content_public.browser.NavigationEntry;
import org.chromium.content_public.browser.NavigationHandle;
import org.chromium.content_public.common.ResourceRequestBody;
import org.chromium.url.GURL;
import org.chromium.url.Origin;

/* JADX INFO: loaded from: classes3.dex */
public class NavigationControllerImpl implements NavigationController {
    public long a;

    public NavigationControllerImpl(long j) {
        this.a = j;
    }

    @CalledByNative
    public static void addToNavigationHistory(Object obj, Object obj2) {
        ((xk1) obj).a.add((NavigationEntry) obj2);
    }

    @CalledByNative
    public static NavigationControllerImpl create(long j) {
        return new NavigationControllerImpl(j);
    }

    @CalledByNative
    public static NavigationEntry createNavigationEntry(int i, GURL gurl, GURL gurl2, GURL gurl3, String str, Bitmap bitmap, int i2, long j, boolean z) {
        return new NavigationEntry(i, gurl, gurl2, gurl3, str, bitmap);
    }

    @Override // org.chromium.content_public.browser.NavigationController
    public final void a() {
        long j = this.a;
        if (j != 0) {
            N.M81h$w2r(j, this, true);
        }
    }

    @Override // org.chromium.content_public.browser.NavigationController
    public final void b(LoadUrlParams loadUrlParams) {
        if (this.a != 0) {
            String strA = loadUrlParams.f == null ? loadUrlParams.g : loadUrlParams.a();
            long j = loadUrlParams.q;
            if (j == 0) {
                j = loadUrlParams.p;
            }
            long j2 = j;
            oj2.b0(SystemClock.uptimeMillis() - j2, "Android.Omnibox.InputToNavigationControllerStart");
            long j3 = this.a;
            String str = loadUrlParams.a;
            int i = loadUrlParams.c;
            int i2 = loadUrlParams.d;
            on2 on2Var = loadUrlParams.e;
            String str2 = on2Var != null ? (String) on2Var.b : null;
            int i3 = on2Var != null ? on2Var.a : 0;
            int i4 = loadUrlParams.h;
            ResourceRequestBody resourceRequestBody = loadUrlParams.i;
            String str3 = loadUrlParams.j;
            String str4 = loadUrlParams.k;
            String str5 = loadUrlParams.l;
            boolean z = loadUrlParams.m;
            boolean z2 = loadUrlParams.n;
            boolean z3 = loadUrlParams.o;
            Origin origin = loadUrlParams.b;
            boolean z4 = loadUrlParams.r;
            boolean z5 = loadUrlParams.s;
            nw2<Long> nw2Var = loadUrlParams.t;
            NavigationHandle navigationHandle = (NavigationHandle) N.MAqmDh4t(j3, this, str, i, i2, str2, i3, i4, strA, resourceRequestBody, str3, str4, str5, z, z2, z3, origin, z4, z5, j2, nw2Var != null ? nw2Var.get().longValue() : 0L);
            if (navigationHandle != null) {
                navigationHandle.s = null;
            }
        }
    }

    @Override // org.chromium.content_public.browser.NavigationController
    public final void c() {
        N.MQgIP2q9(this.a, this);
    }

    @Override // org.chromium.content_public.browser.NavigationController
    public final NavigationEntry d(int i) {
        long j = this.a;
        if (j != 0) {
            return (NavigationEntry) N.MLcS2$Fy(j, this, i);
        }
        return null;
    }

    @CalledByNative
    public final void destroy() {
        this.a = 0L;
    }

    @Override // org.chromium.content_public.browser.NavigationController
    public final void e() {
        long j = this.a;
        if (j != 0) {
            N.Mdhd0AR3(j, this);
        }
    }

    @Override // org.chromium.content_public.browser.NavigationController
    public final boolean f() {
        long j = this.a;
        return j != 0 && N.MsUTH_HQ(j, this);
    }

    @Override // org.chromium.content_public.browser.NavigationController
    public final boolean g() {
        long j = this.a;
        return j != 0 && N.MgAw5sIR(j, this);
    }

    @Override // org.chromium.content_public.browser.NavigationController
    public final void h() {
        long j = this.a;
        if (j != 0) {
            N.MEEEhNfT(j, this);
        }
    }

    @Override // org.chromium.content_public.browser.NavigationController
    public final boolean i() {
        long j = this.a;
        return j != 0 && N.MCUxt83x(j, this);
    }

    @Override // org.chromium.content_public.browser.NavigationController
    public final int j() {
        long j = this.a;
        if (j != 0) {
            return N.Mil0WqAo(j, this);
        }
        return -1;
    }

    @Override // org.chromium.content_public.browser.NavigationController
    public final void k() {
        long j = this.a;
        if (j != 0) {
            N.MWJb9aa$(j, this);
        }
    }

    @Override // org.chromium.content_public.browser.NavigationController
    public final boolean l() {
        long j = this.a;
        if (j == 0) {
            return false;
        }
        return N.MCIN9$qH(j, this);
    }

    @Override // org.chromium.content_public.browser.NavigationController
    public final xk1 m(boolean z) {
        if (this.a == 0) {
            return null;
        }
        xk1 xk1Var = new xk1();
        N.Mx5VuK3_(this.a, this, xk1Var, z, 8);
        return xk1Var;
    }

    @Override // org.chromium.content_public.browser.NavigationController
    public final boolean n() {
        long j = this.a;
        return j != 0 && N.MEOFE6aD(j, this);
    }

    @Override // org.chromium.content_public.browser.NavigationController
    public final xk1 o() {
        if (this.a == 0) {
            return null;
        }
        xk1 xk1Var = new xk1();
        xk1Var.b = N.MUZRDUOx(this.a, this, xk1Var);
        return xk1Var;
    }

    @Override // org.chromium.content_public.browser.NavigationController
    public final boolean p(int i) {
        long j = this.a;
        if (j != 0) {
            return N.MVbDeBRx(j, this, i);
        }
        return false;
    }

    @Override // org.chromium.content_public.browser.NavigationController
    public final boolean q(int i) {
        long j = this.a;
        if (j == 0) {
            return false;
        }
        return N.MVjP87pN(j, this, i);
    }

    @Override // org.chromium.content_public.browser.NavigationController
    public final void r() {
        long j = this.a;
        if (j != 0) {
            N.My0bMgld(j, this, true);
        }
    }

    @Override // org.chromium.content_public.browser.NavigationController
    public final void s(int i, boolean z, boolean z2) {
        if (this.a != 0) {
            Log.i("cr_NavigationController", "Thread dump for debugging, override: " + z + " reloadOnChange: " + z2 + " caller: " + i);
            N.MrilyCK6(this.a, this, z, z2, i);
        }
    }

    @Override // org.chromium.content_public.browser.NavigationController
    public final NavigationEntry t() {
        long j = this.a;
        if (j != 0) {
            return (NavigationEntry) N.Mgxh2WfC(j, this);
        }
        return null;
    }

    @Override // org.chromium.content_public.browser.NavigationController
    public final void u() {
        long j = this.a;
        if (j == 0) {
            return;
        }
        N.M2mQucXv(j, this);
    }

    @Override // org.chromium.content_public.browser.NavigationController
    public final void v() {
        long j = this.a;
        if (j != 0) {
            N.MNF4lMMb(j, this);
        }
    }

    @Override // org.chromium.content_public.browser.NavigationController
    public final void w(int i) {
        long j = this.a;
        if (j != 0) {
            N.MuxwAbEo(j, this, i);
        }
    }

    @Override // org.chromium.content_public.browser.NavigationController
    public final void x() {
        long j = this.a;
        if (j != 0) {
            N.Mp5SLq_N(j, this);
        }
    }
}
