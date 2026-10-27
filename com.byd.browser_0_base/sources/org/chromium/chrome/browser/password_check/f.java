package org.chromium.chrome.browser.password_check;

import J.N;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Pair;
import defpackage.n3;
import defpackage.n62;
import defpackage.oj2;
import defpackage.p81;
import defpackage.qm0;
import defpackage.rh3;
import defpackage.sb2;
import defpackage.ww1;
import defpackage.xa1;
import defpackage.xw1;
import defpackage.yw1;
import defpackage.z7;
import java.util.HashSet;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.password_check.PasswordCheckCoordinator;
import org.chromium.chrome.browser.password_check.PasswordCheckDialogFragment;
import org.chromium.chrome.browser.password_check.a;
import org.chromium.chrome.browser.password_check.b;
import org.chromium.chrome.browser.password_check.h;
import org.chromium.components.browser_ui.settings.SettingsLauncher;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.widget.Toast;

/* JADX INFO: compiled from: PasswordCheckMediator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class f implements PasswordCheckCoordinator.a, a.InterfaceC0101a {
    public final ww1 a;
    public final xw1 b;
    public PropertyModel c;
    public b.InterfaceC0102b d;
    public Runnable e;
    public HashSet<CompromisedCredential> f;
    public final SettingsLauncher g;
    public final yw1 h;
    public long i;
    public boolean j;

    /* JADX INFO: compiled from: PasswordCheckMediator.java */
    public class a implements PasswordCheckDialogFragment.a {
        public final /* synthetic */ CompromisedCredential e;

        public a(CompromisedCredential compromisedCredential) {
            this.e = compromisedCredential;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public final void onClick(DialogInterface dialogInterface, int i) {
            z7.b0(12);
            oj2.U(2, 5, "PasswordManager.AutomaticChange.AcceptanceWithoutAutoButton");
            if (i != -1) {
                return;
            }
            f fVar = f.this;
            N.MShNfrj$(((e) fVar.d).a.a, this.e);
            fVar.c.p(h.b, null);
            fVar.c.p(h.c, null);
        }

        @Override // org.chromium.chrome.browser.password_check.PasswordCheckDialogFragment.a
        public final void onDismiss() {
            f.this.c.p(h.b, null);
        }
    }

    public f(xw1 xw1Var, ww1 ww1Var, n3 n3Var, yw1 yw1Var) {
        this.b = xw1Var;
        this.a = ww1Var;
        this.g = n3Var;
        this.h = yw1Var;
    }

    @Override // org.chromium.chrome.browser.password_check.PasswordCheckCoordinator.a
    public final void a(CompromisedCredential compromisedCredential, Context context) {
        z7.b0(9);
        e eVar = (e) this.d;
        N.M4w82atV(eVar.a.a, compromisedCredential, context, eVar.c);
    }

    @Override // org.chromium.chrome.browser.password_check.a.InterfaceC0101a
    public final void b(int i) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        p81 p81Var = (p81) this.c.j(h.a);
        boolean z = false;
        if (p81Var.size() > 0 && ((xa1) p81Var.get(0)).b.h(h.b.b) == 1 && i != 1 && this.i + 1000 > jCurrentTimeMillis) {
            z = true;
        }
        if (!z) {
            this.i = jCurrentTimeMillis;
            i(i);
        } else {
            long j = this.i + 1000;
            this.i = j;
            PostTask.b(rh3.a, new sb2(i, 2, this), j - jCurrentTimeMillis);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:30:0x011c  */
    /* JADX WARN: Type inference failed for: r3v8, types: [T, org.chromium.chrome.browser.password_check.CompromisedCredential] */
    /* JADX WARN: Type inference failed for: r8v18, types: [T, java.lang.Runnable] */
    /* JADX WARN: Type inference failed for: r8v19, types: [T, eu1] */
    @Override // org.chromium.chrome.browser.password_check.a.InterfaceC0101a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void c() {
        /*
            Method dump skipped, instruction units count: 425
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.password_check.f.c():void");
    }

    @Override // org.chromium.chrome.browser.password_check.PasswordCheckCoordinator.a
    public final void d(CompromisedCredential compromisedCredential) {
        z7.b0(11);
        this.c.p(h.c, compromisedCredential.h);
        this.c.p(h.b, new a(compromisedCredential));
    }

    @Override // org.chromium.chrome.browser.password_check.a.InterfaceC0101a
    public final void f(int i, int i2) {
        PropertyModel propertyModel = ((xa1) ((p81) this.c.j(h.a)).get(0)).b;
        PropertyModel.p pVar = h.b.b;
        if (propertyModel.h(pVar) != 1) {
            this.i = System.currentTimeMillis();
            propertyModel.n(pVar, 1);
        }
        propertyModel.p(h.b.a, new Pair(Integer.valueOf(i), Integer.valueOf(i + i2)));
        propertyModel.p(h.b.c, null);
        propertyModel.p(h.b.d, null);
    }

    @Override // org.chromium.chrome.browser.password_check.PasswordCheckCoordinator.a
    public final void g(CompromisedCredential compromisedCredential) {
        z7.b0(6);
        ww1 ww1Var = this.a;
        if (((KeyguardManager) ww1Var.a.getSystemService("keyguard")).isKeyguardSecure()) {
            ww1Var.a(new n62(this, 9, compromisedCredential), 0);
        } else {
            Toast.c(ww1Var.a, R$string.password_entry_view_set_screen_lock, 1).e();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:6:0x003e  */
    @Override // org.chromium.chrome.browser.password_check.PasswordCheckCoordinator.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void h(org.chromium.chrome.browser.password_check.CompromisedCredential r5) {
        /*
            r4 = this;
            r0 = 3
            defpackage.z7.b0(r0)
            r0 = 5
            java.lang.String r1 = "PasswordManager.AutomaticChange.AcceptanceWithoutAutoButton"
            r2 = 0
            defpackage.oj2.U(r2, r0, r1)
            r0 = 1
            r4.j = r0
            org.chromium.chrome.browser.password_check.b$b r1 = r4.d
            org.chromium.chrome.browser.password_check.e r1 = (org.chromium.chrome.browser.password_check.e) r1
            r1.getClass()
            org.chromium.url.GURL r1 = r5.getAssociatedUrl()
            java.lang.String r3 = r5.getUsername()
            J.N.MkPYGQUS(r1, r3)
            xw1 r4 = r4.b
            r4.getClass()
            java.lang.String r1 = r5.k
            boolean r3 = r1.isEmpty()
            java.lang.String r5 = r5.l
            if (r3 == 0) goto L3e
            android.content.Context r3 = r4.a
            java.util.Objects.requireNonNull(r3)
            android.content.pm.PackageManager r3 = r3.getPackageManager()
            android.content.Intent r3 = r3.getLaunchIntentForPackage(r5)
            if (r3 == 0) goto L3f
        L3e:
            r2 = r0
        L3f:
            if (r2 != 0) goto L42
            goto L9d
        L42:
            boolean r2 = r5.isEmpty()
            android.content.Context r3 = r4.a
            if (r2 == 0) goto L8c
            g50$a r5 = new g50$a
            r5.<init>()
            r5.d()
            g50 r5 = r5.a()
            android.net.Uri r1 = android.net.Uri.parse(r1)
            android.content.Intent r5 = r5.a
            r5.setData(r1)
            org.chromium.chrome.browser.password_check.b$a r1 = r4.c
            pd r1 = (defpackage.pd) r1
            int r1 = r1.e
            switch(r1) {
                case 13: goto L68;
                default: goto L68;
            }
        L68:
            android.content.Intent r5 = org.chromium.chrome.browser.c.e(r3, r5)
            java.lang.String r1 = r3.getPackageName()
            r5.setPackage(r1)
            java.lang.String r1 = "com.android.browser.application_id"
            java.lang.String r2 = r3.getPackageName()
            r5.putExtra(r1, r2)
            java.lang.String r1 = "org.chromium.chrome.browser.password_check.PASSWORD_CHANGE_TYPE"
            r5.putExtra(r1, r0)
            org.chromium.chrome.browser.password_check.b$c r4 = r4.d
            i83 r4 = (defpackage.i83) r4
            r4.getClass()
            defpackage.q11.a(r5)
            goto L97
        L8c:
            java.util.Objects.requireNonNull(r3)
            android.content.pm.PackageManager r4 = r3.getPackageManager()
            android.content.Intent r5 = r4.getLaunchIntentForPackage(r5)
        L97:
            android.content.ComponentName r4 = defpackage.q11.a
            r4 = 0
            r3.startActivity(r5, r4)     // Catch: android.content.ActivityNotFoundException -> L9d
        L9d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.password_check.f.h(org.chromium.chrome.browser.password_check.CompromisedCredential):void");
    }

    public final void i(int i) {
        PropertyModel propertyModelA;
        Integer num;
        if (i == 2) {
            return;
        }
        p81 p81Var = (p81) this.c.j(h.a);
        Long l = null;
        if (p81Var.size() == 0) {
            PropertyModel.b bVar = new PropertyModel.b(h.b.h);
            bVar.e(h.b.a, h.b.i);
            bVar.c(h.b.b, 1);
            bVar.e(h.b.c, null);
            bVar.e(h.b.d, null);
            bVar.e(h.b.e, this.e);
            bVar.e(h.b.f, new qm0(19, this));
            bVar.b(h.b.g, false);
            propertyModelA = bVar.a();
        } else {
            propertyModelA = ((xa1) p81Var.get(0)).b;
        }
        PropertyModel.p pVar = h.b.b;
        int iH = propertyModelA.h(pVar);
        propertyModelA.n(pVar, i);
        PropertyModel.r<Pair<Integer, Integer>> rVar = h.b.a;
        Pair<Integer, Integer> pair = (Pair) propertyModelA.j(rVar);
        if (pair == null) {
            pair = h.b.i;
        }
        if (i != 1) {
            pair = null;
        }
        propertyModelA.p(rVar, pair);
        if (i == 0) {
            Integer numValueOf = Integer.valueOf(N.Mu_fY_2N(((e) j()).a.a));
            Long lValueOf = Long.valueOf(N.MXktgJl6(((e) j()).a.a));
            propertyModelA.l(h.b.g, true);
            if (iH == 1) {
                oj2.P(numValueOf.intValue(), "PasswordManager.BulkCheck.CompromisedCredentialsCountAfterCheckAndroid");
            }
            l = lValueOf;
            num = numValueOf;
        } else {
            num = null;
        }
        propertyModelA.p(h.b.c, l);
        propertyModelA.p(h.b.d, num);
        if (p81Var.size() == 0) {
            p81Var.t(new xa1(1, propertyModelA));
        }
    }

    public final org.chromium.chrome.browser.password_check.a j() {
        return n3.i0(this.g);
    }

    @Override // org.chromium.chrome.browser.password_check.a.InterfaceC0101a
    public final void e() {
    }
}
