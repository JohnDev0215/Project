package org.chromium.components.permissions;

import J.N;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import defpackage.AbstractC0279Pk;
import defpackage.AbstractC0371Um;
import defpackage.AbstractC0425Xm;
import defpackage.AbstractC1079ky;
import defpackage.AbstractC1135ly;
import defpackage.AbstractC1190my;
import defpackage.AbstractC1711wO;
import defpackage.BO;
import defpackage.C0683dt;
import defpackage.C1546tO;
import defpackage.InterfaceC1023jy;
import defpackage.InterfaceC1656vO;
import defpackage.JO;
import java.util.LinkedList;
import java.util.List;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class PermissionDialogController implements InterfaceC1023jy, InterfaceC1656vO {
    public JO a;
    public PermissionDialogDelegate b;
    public C1546tO c;
    public List d = new LinkedList();
    public int e = 0;

    public PermissionDialogController(AbstractC1135ly abstractC1135ly) {
    }

    public static void createDialog(PermissionDialogDelegate permissionDialogDelegate) {
        PermissionDialogController permissionDialogController = AbstractC1190my.a;
        permissionDialogController.d.add(permissionDialogDelegate);
        permissionDialogDelegate.b = permissionDialogController;
        permissionDialogController.f();
    }

    @Override // defpackage.InterfaceC1023jy
    public void a() {
        PermissionDialogDelegate permissionDialogDelegate = this.b;
        if (permissionDialogDelegate == null) {
            this.e = 0;
        } else {
            N.My1ZBTOK(permissionDialogDelegate.a, permissionDialogDelegate);
            e();
        }
        f();
    }

    @Override // defpackage.InterfaceC1656vO
    public void b(JO jo, int i) {
        this.a = null;
        PermissionDialogDelegate permissionDialogDelegate = this.b;
        if (permissionDialogDelegate == null) {
            this.e = 0;
            return;
        }
        int i2 = this.e;
        if (i2 != 3) {
            if (i2 == 4) {
                N.MG2fhXvZ(permissionDialogDelegate.a, permissionDialogDelegate);
            } else {
                N.My1ZBTOK(permissionDialogDelegate.a, permissionDialogDelegate);
            }
            e();
            f();
            return;
        }
        this.e = 5;
        if (AbstractC1079ky.a(permissionDialogDelegate.c, (int[]) permissionDialogDelegate.i.clone(), this, this.b.h)) {
            return;
        }
        PermissionDialogDelegate permissionDialogDelegate2 = this.b;
        if (permissionDialogDelegate2 == null) {
            this.e = 0;
        } else {
            N.MoC5mife(permissionDialogDelegate2.a, permissionDialogDelegate2);
            e();
        }
        f();
    }

    @Override // defpackage.InterfaceC1023jy
    public void c() {
        PermissionDialogDelegate permissionDialogDelegate = this.b;
        if (permissionDialogDelegate == null) {
            this.e = 0;
        } else {
            N.MoC5mife(permissionDialogDelegate.a, permissionDialogDelegate);
            e();
        }
        f();
    }

    @Override // defpackage.InterfaceC1656vO
    public void d(JO jo, int i) {
        if (i == 0) {
            this.e = 3;
            this.c.b(jo, 1);
        } else {
            if (i != 1) {
                return;
            }
            this.e = 4;
            this.c.b(jo, 2);
        }
    }

    public final void e() {
        PermissionDialogDelegate permissionDialogDelegate = this.b;
        N.MLMIuACo(permissionDialogDelegate.a, permissionDialogDelegate);
        permissionDialogDelegate.a = 0L;
        this.b = null;
        this.e = 0;
    }

    public final void f() {
        if (this.e != 0 || this.d.isEmpty()) {
            return;
        }
        PermissionDialogDelegate permissionDialogDelegate = (PermissionDialogDelegate) this.d.remove(0);
        this.b = permissionDialogDelegate;
        if (((Activity) permissionDialogDelegate.c.Q().get()) == null) {
            PermissionDialogDelegate permissionDialogDelegate2 = this.b;
            N.My1ZBTOK(permissionDialogDelegate2.a, permissionDialogDelegate2);
            e();
            return;
        }
        PermissionDialogDelegate permissionDialogDelegate3 = this.b;
        if (permissionDialogDelegate3 == null) {
            this.e = 0;
            f();
            return;
        }
        this.c = permissionDialogDelegate3.c.T();
        PermissionDialogDelegate permissionDialogDelegate4 = this.b;
        Context context = (Context) permissionDialogDelegate4.c.S().get();
        C0683dt c0683dtX = C0683dt.x();
        try {
            View viewInflate = LayoutInflater.from(context).inflate(AbstractC0425Xm.N0, (ViewGroup) null);
            c0683dtX.close();
            String str = permissionDialogDelegate4.e;
            TextView textView = (TextView) viewInflate.findViewById(AbstractC0371Um.H4);
            textView.setText(str);
            textView.setCompoundDrawablesRelativeWithIntrinsicBounds(permissionDialogDelegate4.d, 0, 0, 0);
            BO bo = new BO(AbstractC1711wO.q);
            bo.d(AbstractC1711wO.a, this);
            bo.d(AbstractC1711wO.f, viewInflate);
            bo.d(AbstractC1711wO.g, permissionDialogDelegate4.f);
            bo.d(AbstractC1711wO.j, permissionDialogDelegate4.g);
            bo.d(AbstractC1711wO.b, permissionDialogDelegate4.e);
            bo.b(AbstractC1711wO.n, true);
            JO joA = bo.a();
            this.a = joA;
            this.c.e(joA, 1, false);
            this.e = 2;
        } catch (Throwable th) {
            try {
                c0683dtX.close();
            } catch (Throwable th2) {
                AbstractC0279Pk.a.a(th, th2);
            }
            throw th;
        }
    }
}
