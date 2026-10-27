package defpackage;

import J.N;
import android.content.Context;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.payments.ui.j;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.components.payments.PaymentApp;
import org.chromium.components.payments.e;

/* JADX INFO: compiled from: MojoPaymentRequestGateKeeper.java */
/* JADX INFO: loaded from: classes3.dex */
public final class li1 implements oz1 {
    public final a e;
    public e f;

    /* JADX INFO: compiled from: MojoPaymentRequestGateKeeper.java */
    public interface a {
    }

    public li1(zo1 zo1Var) {
        this.e = zo1Var;
    }

    @Override // defpackage.oz1
    public final void L0(int i) {
        e eVar = this.f;
        if (eVar == null || eVar.B == null) {
            return;
        }
        if (i != 0) {
            s31 s31Var = eVar.g;
            if (!s31Var.b) {
                s31Var.b = true;
                N.My$biNCo(s31Var.a, s31Var);
            }
        }
        yq yqVar = eVar.B;
        if (i != 0) {
            yqVar.getClass();
            if (!SharedPreferencesManager.getInstance().readBoolean("payment_complete_once", false)) {
                SharedPreferencesManager.getInstance().l("payment_complete_once", true);
            }
        }
        String str = yqVar.f.q().g;
        SharedPreferencesManager.getInstance().c(ar.m.b(str));
        SharedPreferencesManager.getInstance().n(System.currentTimeMillis(), ar.n.b(str));
        pz1 pz1Var = eVar.A;
        if (pz1Var != null) {
            pz1Var.b();
        }
    }

    @Override // defpackage.oz1
    public final void M() {
        e eVar = this.f;
        if (eVar == null) {
            return;
        }
        PaymentApp paymentApp = eVar.L;
        if (paymentApp != null) {
            paymentApp.e(eVar);
        } else {
            eVar.n(true);
        }
    }

    @Override // defpackage.oz1
    public final void c(boolean z) {
        mz1 mz1VarS;
        e eVar = this.f;
        if (eVar == null || eVar.B == null) {
            return;
        }
        if (eVar.w) {
            eVar.g.a(2);
            eVar.l(1, "Attempted show twice.");
            return;
        }
        if (e.M != null) {
            eVar.u(2, 4, "Another PaymentRequest UI is already showing in a different tab or window.");
            return;
        }
        e.M = eVar;
        s31 s31Var = eVar.g;
        N.MpLIjj0f(s31Var.a, s31Var, 1);
        eVar.w = true;
        eVar.x = z;
        if (!eVar.v || (mz1VarS = eVar.s()) == null) {
            return;
        }
        eVar.u(mz1VarS.a, mz1VarS.c, mz1VarS.b);
    }

    @Override // defpackage.y11, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        e eVar = this.f;
        if (eVar == null) {
            return;
        }
        eVar.g.a(4);
        eVar.k();
        this.f = null;
    }

    @Override // defpackage.oz1
    public final void h0() {
        e eVar = this.f;
        if (eVar == null) {
            return;
        }
        if (eVar.v) {
            eVar.w();
        } else {
            eVar.K = true;
        }
    }

    @Override // defpackage.oz1
    public final void j0(xz1 xz1Var) {
        e eVar = this.f;
        if (eVar == null || eVar.B == null) {
            return;
        }
        if (!(xz1Var == null ? false : N.MQ3mQqrV(xz1Var.b()))) {
            eVar.g.a(2);
            eVar.l(1, "Invalid payment validation errors.");
            return;
        }
        N.MiyjJ0og(eVar.t.a, xz1Var.b());
        yq yqVar = eVar.B;
        yqVar.g = true;
        yqVar.c.getClass();
        Context contextA = e.a.a(yqVar.b);
        if (contextA == null) {
            yqVar.b("Unable to find Chrome context.");
        } else {
            j jVar = yqVar.f;
            dh2 dh2Var = new dh2(4, 0, new ArrayList(Arrays.asList(jVar.q())));
            jVar.z = dh2Var;
            jVar.x.n(4, dh2Var);
            org.chromium.chrome.browser.payments.ui.e eVar2 = jVar.x;
            eVar2.G = false;
            eVar2.a(false);
            j.d dVar = eVar2.i;
            dVar.b = true;
            dVar.a();
            eVar2.m();
            if (TextUtils.isEmpty(xz1Var.b)) {
                jVar.x.i(contextA.getResources().getString(R$string.payments_error_message));
            } else {
                jVar.x.i(xz1Var.b);
            }
            boolean zW = jVar.w();
            LinkedList linkedList = jVar.h;
            if (zW) {
                d4 d4Var = xz1Var.d;
                if ((TextUtils.isEmpty(d4Var.b) && TextUtils.isEmpty(d4Var.c) && TextUtils.isEmpty(d4Var.d) && TextUtils.isEmpty(d4Var.e) && TextUtils.isEmpty(d4Var.f) && TextUtils.isEmpty(d4Var.g) && TextUtils.isEmpty(d4Var.h) && TextUtils.isEmpty(d4Var.i) && TextUtils.isEmpty(d4Var.j) && TextUtils.isEmpty(d4Var.k)) ? false : true) {
                    linkedList.add(new z8(jVar, 20, xz1Var));
                }
            }
            if (jVar.v()) {
                jy1 jy1Var = xz1Var.c;
                if ((TextUtils.isEmpty(jy1Var.c) && TextUtils.isEmpty(jy1Var.d) && TextUtils.isEmpty(jy1Var.b)) ? false : true) {
                    linkedList.add(new a9(jVar, 17, xz1Var));
                }
            }
            if (!linkedList.isEmpty()) {
                jVar.g.post((Runnable) linkedList.remove());
            }
        }
        uy1.a().getClass();
        Object obj = ThreadUtils.a;
        uy1.e = null;
    }

    @Override // defpackage.fx
    public final void q(ki1 ki1Var) {
        e eVar = this.f;
        if (eVar == null) {
            return;
        }
        eVar.g.a(3);
        eVar.k();
        this.f = null;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:107:0x0202  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x020c  */
    /* JADX WARN: Removed duplicated region for block: B:182:0x0388  */
    /* JADX WARN: Removed duplicated region for block: B:221:0x0447 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:222:0x0448  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x018e  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0199  */
    @Override // defpackage.oz1
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void t0(defpackage.pz1 r22, defpackage.lz1[] r23, defpackage.sy1 r24, defpackage.nz1 r25) {
        /*
            Method dump skipped, instruction units count: 1122
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.li1.t0(pz1, lz1[], sy1, nz1):void");
    }

    @Override // defpackage.oz1
    public final void w() {
        e eVar = this.f;
        if (eVar == null) {
            return;
        }
        if (eVar.v) {
            eVar.v();
        } else {
            eVar.f88J = true;
        }
    }

    @Override // defpackage.oz1
    public final void y() {
        e eVar = this.f;
        if (eVar == null || eVar.B == null) {
            return;
        }
        if (!eVar.w) {
            eVar.g.a(2);
            eVar.l(1, "Attempted updateWith without show.");
            return;
        }
        N.M_B2Caox(eVar.t.a);
        PaymentApp paymentApp = eVar.L;
        if (paymentApp != null && paymentApp.u()) {
            eVar.L.v();
            return;
        }
        yq yqVar = eVar.B;
        String strMCGOhrza = N.MCGOhrza(eVar.t.a);
        j jVar = yqVar.f;
        jVar.x(strMCGOhrza);
        jVar.k();
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0033  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00dd  */
    @Override // defpackage.oz1
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void z(defpackage.sy1 r14) {
        /*
            Method dump skipped, instruction units count: 453
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.li1.z(sy1):void");
    }
}
