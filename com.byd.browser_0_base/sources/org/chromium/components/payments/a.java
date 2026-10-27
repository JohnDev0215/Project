package org.chromium.components.payments;

import J.N;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.RemoteException;
import android.util.Log;
import androidx.appcompat.app.AlertDialog;
import defpackage.c12;
import defpackage.d4;
import defpackage.im2;
import defpackage.kw0;
import defpackage.kz1;
import defpackage.l51;
import defpackage.lz1;
import defpackage.my1;
import defpackage.nf0;
import defpackage.nr3;
import defpackage.nz1;
import defpackage.oo;
import defpackage.rr3;
import defpackage.rw2;
import defpackage.ry1;
import defpackage.rz1;
import defpackage.se3;
import defpackage.sr3;
import defpackage.ty1;
import defpackage.uy1;
import defpackage.vr3;
import defpackage.wz1;
import defpackage.x21;
import defpackage.y70;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.chromium.base.Callback;
import org.chromium.base.CollectionUtil;
import org.chromium.base.ThreadUtils;
import org.chromium.components.payments.PaymentApp;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: compiled from: AndroidPaymentApp.java */
/* JADX INFO: loaded from: classes3.dex */
public final class a extends PaymentApp implements x21.a {
    public final Handler k;
    public final c l;
    public final HashSet m;
    public final boolean n;
    public final String o;
    public final String p;
    public final String q;
    public b r;
    public PaymentApp.b s;
    public final String t;
    public final rw2 u;
    public boolean v;
    public rr3 w;

    /* JADX INFO: renamed from: org.chromium.components.payments.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AndroidPaymentApp.java */
    public static class C0138a {
        public int a;
        public Intent b;
    }

    /* JADX INFO: compiled from: AndroidPaymentApp.java */
    public interface b {
    }

    /* JADX INFO: compiled from: AndroidPaymentApp.java */
    public interface c {
    }

    /* JADX INFO: compiled from: AndroidPaymentApp.java */
    public static class d implements c, WindowAndroid.c {
        public final WebContents a;
        public Callback<C0138a> b;

        public d(WebContents webContents) {
            this.a = webContents;
        }

        @Override // org.chromium.ui.base.WindowAndroid.c
        public final void b(Intent intent, int i) {
            C0138a c0138a = new C0138a();
            c0138a.a = i;
            c0138a.b = intent;
            this.b.onResult(c0138a);
            this.b = null;
        }
    }

    public a(d dVar, String str, String str2, String str3, String str4, Drawable drawable, boolean z, String str5, rw2 rw2Var) {
        super(drawable, str, str4, null);
        Object obj = ThreadUtils.a;
        this.k = new Handler();
        this.l = dVar;
        this.o = str;
        this.p = str2;
        this.q = str3;
        this.m = new HashSet();
        this.n = z;
        this.t = str5;
        this.u = rw2Var;
        this.v = false;
    }

    public final void A(boolean z) {
        Object obj = ThreadUtils.a;
        b bVar = this.r;
        if (bVar == null) {
            return;
        }
        org.chromium.components.payments.b bVar2 = (org.chromium.components.payments.b) ((im2) bVar).e;
        my1 my1Var = bVar2.f;
        if (z) {
            my1Var.i(this);
        }
        int i = bVar2.p - 1;
        bVar2.p = i;
        if (i == 0) {
            my1Var.e(bVar2.g);
        }
        this.r = null;
    }

    @Override // org.chromium.components.payments.PaymentApp
    public final String h() {
        return this.t;
    }

    @Override // org.chromium.components.payments.PaymentApp
    public final Set<String> j() {
        return Collections.unmodifiableSet(this.m);
    }

    @Override // org.chromium.components.payments.PaymentApp
    public final int k() {
        return 1;
    }

    @Override // org.chromium.components.payments.PaymentApp
    public final boolean m() {
        return this.u.d;
    }

    @Override // org.chromium.components.payments.PaymentApp
    public final boolean n() {
        return this.u.b;
    }

    @Override // org.chromium.components.payments.PaymentApp
    public final boolean o() {
        return this.u.c;
    }

    @Override // org.chromium.components.payments.PaymentApp
    public final boolean p() {
        return this.u.a;
    }

    @Override // org.chromium.components.payments.PaymentApp
    public final void r(final String str, final String str2, String str3, String str4, final byte[][] bArr, final Map<String, lz1> map, final kz1 kz1Var, final List<kz1> list, final Map<String, ty1> map2, final nz1 nz1Var, final List<wz1> list2, PaymentApp.b bVar) {
        this.s = bVar;
        int i = 1;
        final String strMR6Af3ZS = N.MR6Af3ZS(str3, 1);
        final String strMR6Af3ZS2 = N.MR6Af3ZS(str4, 1);
        Runnable runnable = new Runnable() { // from class: e5
            /* JADX WARN: Removed duplicated region for block: B:100:0x022c  */
            /* JADX WARN: Removed duplicated region for block: B:101:0x0230  */
            /* JADX WARN: Removed duplicated region for block: B:80:0x0178  */
            /* JADX WARN: Removed duplicated region for block: B:91:0x01ed A[Catch: IOException -> 0x0201, TryCatch #3 {IOException -> 0x0201, blocks: (B:89:0x01cc, B:91:0x01ed, B:92:0x01f9), top: B:117:0x01cc }] */
            /* JADX WARN: Removed duplicated region for block: B:96:0x0204  */
            /* JADX WARN: Removed duplicated region for block: B:97:0x0207  */
            @Override // java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final void run() {
                /*
                    Method dump skipped, instruction units count: 594
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: defpackage.e5.run():void");
            }
        };
        if (!this.n) {
            runnable.run();
            return;
        }
        se3 se3Var = new se3(17, this);
        WindowAndroid windowAndroidV = ((d) this.l).a.V();
        Context context = windowAndroidV == null ? null : (Context) windowAndroidV.k().get();
        if (context == null) {
            se3Var.onResult("Unable to find Chrome activity.");
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R$style.ThemeOverlay_BrowserUI_AlertDialog);
        builder.i(R$string.external_app_leave_incognito_warning_title);
        builder.b(R$string.external_payment_app_leave_incognito_warning);
        builder.f(R$string.ok, new oo(2, runnable));
        builder.e(R$string.cancel, new nf0(3, se3Var));
        builder.a.l = new c12(i, se3Var);
        builder.k();
    }

    @Override // org.chromium.components.payments.PaymentApp
    public final boolean s() {
        return this.v;
    }

    @Override // org.chromium.components.payments.PaymentApp
    public final boolean u() {
        Object obj = ThreadUtils.a;
        uy1 uy1VarA = uy1.a();
        uy1VarA.getClass();
        return uy1VarA.a != null;
    }

    @Override // org.chromium.components.payments.PaymentApp
    public final void v() {
        Object obj = ThreadUtils.a;
        uy1 uy1VarA = uy1.a();
        uy1VarA.getClass();
        kw0 kw0Var = uy1VarA.a;
        if (kw0Var == null) {
            return;
        }
        try {
            try {
                kw0Var.v();
            } catch (RemoteException e) {
                Log.e("cr_PaymentDetailsUpdate", "Error calling paymentDetailsNotUpdated", e);
            }
        } finally {
            uy1VarA.a = null;
        }
    }

    @Override // org.chromium.components.payments.PaymentApp
    public final void y(rz1 rz1Var) {
        List listAsList;
        ArrayList arrayList;
        Bundle bundle;
        Object obj = ThreadUtils.a;
        uy1 uy1VarA = uy1.a();
        ry1 ry1Var = rz1Var.b;
        nr3 nr3Var = ry1Var == null ? null : new nr3(ry1Var.b, ry1Var.c);
        wz1[] wz1VarArr = rz1Var.c;
        if (wz1VarArr == null || (listAsList = Arrays.asList(wz1VarArr)) == null) {
            arrayList = null;
        } else {
            arrayList = new ArrayList();
            CollectionUtil.a(listAsList, new l51(19, arrayList));
        }
        String str = rz1Var.e;
        String str2 = rz1Var.f;
        d4 d4Var = rz1Var.g;
        if (d4Var == null) {
            bundle = null;
        } else {
            Bundle bundle2 = new Bundle();
            vr3.a("addressLine", d4Var.b, bundle2);
            vr3.a("city", d4Var.c, bundle2);
            vr3.a("countryCode", d4Var.d, bundle2);
            vr3.a("dependentLocality", d4Var.e, bundle2);
            vr3.a("organization", d4Var.f, bundle2);
            vr3.a("phone", d4Var.g, bundle2);
            vr3.a("postalCode", d4Var.h, bundle2);
            vr3.a("recipient", d4Var.i, bundle2);
            vr3.a("region", d4Var.j, bundle2);
            vr3.a("sortingCode", d4Var.k, bundle2);
            bundle = bundle2;
        }
        sr3 sr3Var = new sr3(nr3Var, arrayList, str, str2, bundle);
        uy1VarA.getClass();
        kw0 kw0Var = uy1VarA.a;
        if (kw0Var == null) {
            return;
        }
        try {
            try {
                kw0Var.s(sr3Var.a());
            } catch (RemoteException e) {
                Log.e("cr_PaymentDetailsUpdate", "Error calling updateWith", e);
            }
        } finally {
            uy1VarA.a = null;
        }
    }

    public final void z(String str) {
        this.k.post(new y70(this, 21, str));
    }

    @Override // org.chromium.components.payments.PaymentApp
    public final void g() {
    }
}
