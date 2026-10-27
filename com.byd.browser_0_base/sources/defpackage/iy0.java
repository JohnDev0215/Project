package defpackage;

import android.os.Bundle;
import defpackage.hz0;
import defpackage.l61;
import defpackage.ny0;
import java.util.ArrayList;
import java.util.Iterator;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.state.CriticalPersistedTabData;
import org.chromium.chrome.browser.tabmodel.TabModel;
import org.chromium.chrome.browser.tasks.tab_management.s1;

/* JADX INFO: compiled from: IncognitoReauthControllerImpl.java */
/* JADX INFO: loaded from: classes.dex */
public final class iy0 implements hy0, hz0.a, kr2, vd2, ApplicationStatus.e {
    public final ArrayList e = new ArrayList();
    public final a f = new a();
    public final b g;
    public final c h;
    public final d i;
    public final e j;
    public final ll k;
    public final q2 l;
    public final y13 m;
    public final zp1<Profile> n;
    public final ly0 o;
    public final int p;
    public final boolean q;
    public final f r;
    public final Runnable s;
    public ky0 t;
    public l61 u;
    public Profile v;
    public boolean w;
    public boolean x;

    /* JADX INFO: compiled from: IncognitoReauthControllerImpl.java */
    public class a implements ny0.a {
        public a() {
        }

        @Override // ny0.a
        public final void a() {
            iy0 iy0Var = iy0.this;
            iy0Var.x = false;
            iy0Var.g(13);
            Iterator it = iy0Var.e.iterator();
            while (it.hasNext()) {
                ((ny0.a) it.next()).a();
            }
        }

        @Override // ny0.a
        public final void b() {
            iy0 iy0Var = iy0.this;
            iy0Var.g(14);
            Iterator it = iy0Var.e.iterator();
            while (it.hasNext()) {
                ((ny0.a) it.next()).b();
            }
        }

        @Override // ny0.a
        public final void c() {
            Iterator it = iy0.this.e.iterator();
            while (it.hasNext()) {
                ((ny0.a) it.next()).c();
            }
        }
    }

    /* JADX INFO: compiled from: IncognitoReauthControllerImpl.java */
    public class b implements hz0 {
        public b() {
        }

        @Override // defpackage.hz0
        public final void q() {
            iy0.this.x = false;
        }
    }

    /* JADX INFO: compiled from: IncognitoReauthControllerImpl.java */
    public class c implements e23 {
        public c() {
        }

        @Override // defpackage.e23
        public final void g() {
            iy0.this.i();
        }
    }

    /* JADX INFO: compiled from: IncognitoReauthControllerImpl.java */
    public class d implements l61.a {
        public d() {
        }

        @Override // l61.a
        public final void f(int i) {
            if (i == 2) {
                iy0.this.g(12);
            }
        }

        @Override // l61.a
        public final void g(int i, boolean z) {
            if (i == 1) {
                iy0.this.k();
            }
        }
    }

    /* JADX INFO: compiled from: IncognitoReauthControllerImpl.java */
    public class e implements Callback<Profile> {
        public e() {
        }

        @Override // org.chromium.base.Callback
        public final void onResult(Profile profile) {
            iy0 iy0Var = iy0.this;
            iy0Var.v = profile;
            iy0Var.k();
            if (iy0Var.w) {
                return;
            }
            oj2.O("Android.IncognitoReauth.ToggleOnOrOff", ny0.a(iy0Var.v));
            iy0Var.w = true;
        }
    }

    /* JADX INFO: compiled from: IncognitoReauthControllerImpl.java */
    public class f extends xr1 {
        public f() {
            super(false);
        }

        @Override // defpackage.xr1
        public final void a() {
            iy0.this.s.run();
        }
    }

    public iy0(y13 y13Var, q2 q2Var, qs1<l61> qs1Var, zp1<Profile> zp1Var, ly0 ly0Var, int i) {
        b bVar = new b();
        this.g = bVar;
        c cVar = new c();
        this.h = cVar;
        this.i = new d();
        e eVar = new e();
        this.j = eVar;
        ll llVar = new ll();
        this.k = llVar;
        this.r = new f();
        this.m = y13Var;
        this.l = q2Var;
        this.n = zp1Var;
        zp1Var.p(eVar);
        this.o = ly0Var;
        this.q = ly0Var.f;
        this.s = ly0Var.a();
        this.p = i;
        qs1Var.h(llVar.b(new ko0(7, this)));
        y13Var.v(this);
        y13Var.h(bVar);
        y13Var.m(cVar);
        q2Var.f(this);
        if (ApplicationStatus.h == null) {
            ApplicationStatus.h = new org.chromium.base.b<>();
        }
        ApplicationStatus.h.a(this);
        if (y13Var.t()) {
            i();
        }
    }

    @Override // org.chromium.base.ApplicationStatus.e
    public final void a(int i, boolean z) {
        if (i == this.p && !z) {
            this.x = this.m.g(true).getCount() > 0;
        }
    }

    @Override // defpackage.hy0
    public final void b(ny0.a aVar) {
        ArrayList arrayList = this.e;
        if (arrayList.contains(aVar)) {
            return;
        }
        arrayList.add(aVar);
    }

    @Override // defpackage.hy0
    public final void c(s1.a aVar) {
        this.e.remove(aVar);
    }

    @Override // defpackage.vd2
    public final void c0(Bundle bundle) {
        bundle.putBoolean("incognitoReauthPending", this.x);
    }

    @Override // defpackage.hy0
    public final boolean d() {
        return this.t != null;
    }

    @Override // defpackage.hy0
    public final boolean e() {
        return this.x && ny0.a(this.v);
    }

    public final void g(int i) {
        if (this.t != null) {
            this.r.b(false);
            this.t.hide(i);
            this.t = null;
        }
    }

    public final void i() {
        boolean z = true;
        TabModel tabModelG = this.m.g(true);
        int i = 0;
        while (true) {
            if (i >= tabModelG.getCount()) {
                z = false;
                break;
            } else if (CriticalPersistedTabData.m(tabModelG.getTabAt(i)).w.intValue() == 3) {
                break;
            } else {
                i++;
            }
        }
        this.x = z;
        k();
    }

    @Override // defpackage.kr2
    public final void j() {
        k();
    }

    public final void k() {
        Profile profile;
        if (this.t != null) {
            return;
        }
        l61 l61Var = this.u;
        boolean z = this.q;
        if (!(l61Var == null && z) && this.x && this.m.r() && (profile = this.v) != null && ny0.a(profile)) {
            int i = 2;
            boolean z2 = (z && this.u.q(2)) ? false : true;
            a aVar = this.f;
            f fVar = this.r;
            ly0 ly0Var = this.o;
            ky0 lq0Var = z2 ? new lq0(ly0Var.a, ly0Var.d, aVar, ly0Var.a(), ly0Var.c, new qy0(ly0Var.a, new o90(i), ly0Var.e), fVar) : new x43(ly0Var.a, ly0Var.d, aVar, ly0Var.a(), ly0Var.a(), ly0Var.h, ly0Var.g);
            this.t = lq0Var;
            lq0Var.a();
            this.r.b(z2);
        }
    }

    @Override // defpackage.kr2
    public final void l() {
    }
}
