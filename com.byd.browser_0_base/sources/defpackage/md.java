package defpackage;

import J.N;
import android.content.Intent;
import android.graphics.Rect;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.customtabs.CustomTabsSessionToken;
import java.util.Objects;
import org.chromium.base.Callback;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.customtabs.CustomTabsConnection;
import org.chromium.chrome.browser.customtabs.a;
import org.chromium.chrome.browser.customtabs.features.toolbar.CustomTabToolbar;
import org.chromium.chrome.browser.document.ChromeLauncherActivity;
import org.chromium.chrome.browser.toolbar.g;
import org.chromium.ui.base.DeviceFormFactor;
import org.chromium.ui.widget.Toast;

/* JADX INFO: compiled from: BaseCustomTabRootUiCoordinator.java */
/* JADX INFO: loaded from: classes.dex */
public final class md extends nc2 {
    public final nw2<u40> F0;
    public final nw2<w20> G0;
    public final nw2<oj> H0;
    public final nw2<x20> I0;
    public r30 J0;
    public bj K0;
    public t80 L0;

    /* JADX WARN: Removed duplicated region for block: B:18:0x00f1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public md(androidx.appcompat.app.AppCompatActivity r42, defpackage.gk2 r43, defpackage.z2 r44, defpackage.f23 r45, defpackage.aq1 r46, defpackage.aq1 r47, defpackage.g23 r48, defpackage.fj r49, defpackage.g3 r50, defpackage.r2 r51, defpackage.aq1 r52, defpackage.jf1 r53, defpackage.fd r54, defpackage.aq1 r55, defpackage.y6 r56, defpackage.ur r57, defpackage.cp r58, defpackage.hz2 r59, defpackage.qq0 r60, defpackage.aq1 r61, defpackage.aq1 r62, defpackage.fd r63, int r64, defpackage.gd r65, defpackage.hd r66, defpackage.e7 r67, dt2.a r68, org.chromium.ui.base.d r69, defpackage.yq2 r70, defpackage.fd r71, defpackage.gd r72, defpackage.hd r73, defpackage.zb r74, defpackage.gd r75) {
        /*
            Method dump skipped, instruction units count: 272
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.md.<init>(androidx.appcompat.app.AppCompatActivity, gk2, z2, f23, aq1, aq1, g23, fj, g3, r2, aq1, jf1, fd, aq1, y6, ur, cp, hz2, qq0, aq1, aq1, fd, int, gd, hd, e7, dt2$a, org.chromium.ui.base.d, yq2, fd, gd, hd, zb, gd):void");
    }

    @Override // defpackage.nc2, defpackage.hk1
    public final void G0() {
        super.G0();
        sk skVar = bq.a;
        if (N.M09VlOh_("ReengagementNotification")) {
            new ms1(this.L, this.Q.b(new xr(12, this)));
        }
    }

    @Override // defpackage.nc2, defpackage.sz0
    public final void Y0() {
        super.Y0();
        this.J0.Y0();
    }

    @Override // defpackage.nc2
    public final Rect d() {
        if (!this.H0.get().O()) {
            return super.d();
        }
        View viewFindViewById = this.g.findViewById(R$id.coordinator);
        int[] iArr = new int[2];
        viewFindViewById.getLocationInWindow(iArr);
        int i = iArr[0];
        return new Rect(i, iArr[1], viewFindViewById.getWidth() + i, viewFindViewById.getHeight() + iArr[1]);
    }

    @Override // defpackage.nc2
    public final ly0 e() {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.setClass(this.g, ChromeLauncherActivity.class);
        intent.putExtra("org.chromium.chrome.browser.open_regular_overview_mode", true);
        q11.a(intent);
        return new ly0(this.g, this.T.get(), this.j0.get(), new ny0(), new n3(), null, null, intent, false);
    }

    @Override // defpackage.nc2
    public final void i() throws Throwable {
        Callback ldVar;
        View viewFindViewById;
        super.i();
        u40 u40Var = this.F0.get();
        g gVar = this.x;
        u40Var.k = gVar;
        t40 t40Var = u40Var.j;
        t40Var.f = gVar;
        t40Var.c.c(new s40(t40Var));
        t40Var.a();
        mu muVar = u40Var.h;
        muVar.b = gVar;
        if (gVar != null) {
            gVar.k.a.setCloseButtonImageResource(muVar.c ? muVar.a : null);
        }
        int i = 1;
        gVar.k.a.getLocationBar().m(u40Var.a.E() == 1);
        CustomTabsConnection customTabsConnection = u40Var.c;
        CustomTabsSessionToken customTabsSessionTokenZ = u40Var.a.z();
        a aVar = customTabsConnection.c;
        synchronized (aVar) {
            if (((a.e) aVar.c.get(customTabsSessionTokenZ)) == null) {
            }
        }
        if ((u40Var.a.J() == 1) && (viewFindViewById = gVar.l.findViewById(R$id.toolbar_hairline)) != null) {
            viewFindViewById.setVisibility(8);
        }
        for (m20 m20Var : u40Var.a.o()) {
            String string = xz.a.getString(R$string.share);
            String strD = m20Var.d();
            if (strD == null || !string.equals(strD)) {
                u40Var.k.k.a.a(m20Var.a(u40Var.d), strD, new n40(u40Var, i, m20Var));
            }
        }
        this.G0.get().m = this.x;
        CustomTabToolbar customTabToolbar = (CustomTabToolbar) this.g.findViewById(R$id.toolbar);
        this.J0.k(this.g.findViewById(R$id.coordinator), customTabToolbar, this.H0.get().w());
        bj bjVar = this.K0;
        if (bjVar != null) {
            sb3 brandingDelegate = customTabToolbar.getBrandingDelegate();
            if (bjVar.j) {
                Log.e("cr_CctBrand", "BrandingController should not be access after destroyed.");
                org.chromium.components.crash.a aVar2 = bjVar.k;
                if (aVar2 != null) {
                    aVar2.a(new Throwable("BrandingController should not be access after destroyed."));
                }
            } else {
                bjVar.i = SystemClock.elapsedRealtime();
                bjVar.g = brandingDelegate;
                ((CustomTabToolbar.b) brandingDelegate).t = bjVar.f;
                PostTask.b(rh3.c, bjVar.a.c(new l10(5, bjVar)), bj.l.c());
                CustomTabToolbar.b bVar = (CustomTabToolbar.b) bjVar.g;
                bVar.s = true;
                bVar.r = true;
                bVar.v = Integer.valueOf(bVar.e);
                bVar.e = 3;
                bVar.j.setVisibility(8);
                bVar.k.setVisibility(8);
                bjVar.a();
            }
        }
        customTabToolbar.setCloseButtonPosition(this.H0.get().l());
        if (this.H0.get().O()) {
            if (bq.o.a()) {
                cw1 cw1Var = (cw1) this.J0;
                Objects.requireNonNull(cw1Var);
                ldVar = new so(18, cw1Var);
            } else {
                aw1 aw1Var = (aw1) this.J0;
                Objects.requireNonNull(aw1Var);
                ldVar = new ld(aw1Var, 0);
            }
            this.I0.get().i.g.add(new mw1(ldVar));
        }
    }

    @Override // defpackage.nc2
    public final void k() {
        this.J0.d();
    }

    @Override // defpackage.nc2
    public final void m() {
        this.J0.c();
    }

    @Override // defpackage.nc2, defpackage.v80
    public final void onDestroy() {
        super.onDestroy();
        bj bjVar = this.K0;
        if (bjVar != null) {
            bjVar.j = true;
            bjVar.a.a();
            bjVar.c.a(true);
            Toast toast = bjVar.h;
            if (toast != null) {
                toast.a();
            }
            this.K0 = null;
        }
        t80 t80Var = this.L0;
        if (t80Var != null) {
            s80 s80Var = t80Var.e;
            if (s80Var != null) {
                s80Var.destroy();
                t80Var.f.clear();
            }
            this.L0 = null;
        }
        this.J0.g();
    }

    @Override // defpackage.nc2
    public final void p(float f) {
        super.p(f);
        this.J0.m(f);
    }

    @Override // defpackage.nc2, defpackage.sz0
    public final void q() {
        r30 cw1Var;
        md mdVar = this;
        super.q();
        oj ojVar = mdVar.H0.get();
        AppCompatActivity appCompatActivity = mdVar.g;
        int iR = ojVar.r();
        int iS = ojVar.s();
        int iB = ojVar.b();
        boolean zP = ojVar.P();
        CustomTabsConnection customTabsConnectionE = CustomTabsConnection.e();
        CustomTabsSessionToken customTabsSessionTokenZ = ojVar.z();
        q2 q2Var = mdVar.g0;
        rq0 rq0Var = mdVar.o0;
        boolean zB = DeviceFormFactor.b(mdVar.j);
        boolean zA = ojVar.a();
        boolean zD0 = ojVar.d0();
        int iC = ojVar.c();
        int iC2 = ojVar.C();
        int iD = ojVar.D();
        if (iR > 0 || (bq.o.a() && iS > 0)) {
            cw1Var = bq.o.a() ? new cw1(appCompatActivity, iR, iS, iB, zP, new z70(customTabsConnectionE, 5, customTabsSessionTokenZ), q2Var, rq0Var, zB, zA, zD0, iC, iC2, iD) : new aw1(appCompatActivity, iR, zP, new i40(customTabsConnectionE, customTabsSessionTokenZ), q2Var, rq0Var, zB, zA, false, new fw1());
            mdVar = this;
        } else {
            cw1Var = new r30();
        }
        mdVar.J0 = cw1Var;
    }

    @Override // defpackage.nc2
    public final boolean r() {
        int i = this.s0;
        return i == 2 || i == 4;
    }

    @Override // defpackage.nc2
    public final boolean s() {
        int i = this.s0;
        return i == 2 || i == 4;
    }
}
