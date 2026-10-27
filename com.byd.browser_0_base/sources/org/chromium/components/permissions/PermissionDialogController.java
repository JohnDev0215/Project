package org.chromium.components.permissions;

import J.N;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.TextView;
import defpackage.bi1;
import defpackage.di1;
import defpackage.uh3;
import defpackage.y70;
import defpackage.z7;
import defpackage.zj;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.b;
import org.chromium.components.permissions.AndroidPermissionRequester;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: loaded from: classes3.dex */
public class PermissionDialogController implements AndroidPermissionRequester.c, di1.a {
    public PropertyModel f;
    public PropertyModel g;
    public PermissionDialogDelegate h;
    public bi1 i;
    public final LinkedList j = new LinkedList();
    public int k = 0;
    public final org.chromium.base.b<b> e = new org.chromium.base.b<>();

    public static class a {

        @SuppressLint({"StaticFieldLeak"})
        public static final PermissionDialogController a = new PermissionDialogController();
    }

    public interface b {
        void a(WindowAndroid windowAndroid, int[] iArr, int i);
    }

    @CalledByNative
    public static void createDialog(PermissionDialogDelegate permissionDialogDelegate) {
        PermissionDialogController permissionDialogController = a.a;
        permissionDialogController.j.add(permissionDialogDelegate);
        permissionDialogDelegate.b = permissionDialogController;
        permissionDialogController.d();
    }

    public final void a(int i) {
        if (i != 0) {
            WindowAndroid windowAndroid = this.h.c;
            Iterator<b> it = this.e.iterator();
            while (true) {
                b.a aVar = (b.a) it;
                if (!aVar.hasNext()) {
                    break;
                } else {
                    ((b) aVar.next()).a(windowAndroid, (int[]) ((int[]) this.h.h.clone()).clone(), i);
                }
            }
        }
        PermissionDialogDelegate permissionDialogDelegate = this.h;
        N.MLMIuACo(permissionDialogDelegate.a, permissionDialogDelegate);
        permissionDialogDelegate.a = 0L;
        this.h = null;
        this.k = 0;
    }

    @Override // di1.a
    public final void b(int i) {
        this.f = null;
        PermissionDialogDelegate permissionDialogDelegate = this.h;
        if (permissionDialogDelegate == null) {
            this.k = 0;
            return;
        }
        int i2 = this.k;
        if (i2 == 3) {
            this.k = 5;
            if (AndroidPermissionRequester.a(permissionDialogDelegate.c, (int[]) permissionDialogDelegate.h.clone(), this)) {
                return;
            }
            g();
            return;
        }
        if (i2 == 4) {
            N.MG2fhXvZ(permissionDialogDelegate.a, permissionDialogDelegate);
            a(2);
        } else {
            N.My1ZBTOK(permissionDialogDelegate.a, permissionDialogDelegate);
            a(0);
        }
        d();
    }

    @Override // di1.a
    public final void c(int i, PropertyModel propertyModel) {
        if (i == 0) {
            this.k = 3;
            this.i.b(1, propertyModel);
        } else {
            if (i != 1) {
                return;
            }
            this.k = 4;
            this.i.b(2, propertyModel);
        }
    }

    public final void d() {
        if (this.k == 0) {
            LinkedList linkedList = this.j;
            if (linkedList.isEmpty()) {
                return;
            }
            PermissionDialogDelegate permissionDialogDelegate = (PermissionDialogDelegate) linkedList.remove(0);
            this.h = permissionDialogDelegate;
            Context context = permissionDialogDelegate.c.i.get();
            if (context == null) {
                PermissionDialogDelegate permissionDialogDelegate2 = this.h;
                N.My1ZBTOK(permissionDialogDelegate2.a, permissionDialogDelegate2);
                a(0);
                return;
            }
            bi1 bi1VarN = this.h.c.n();
            this.i = bi1VarN;
            PermissionDialogDelegate permissionDialogDelegate3 = this.h;
            if (permissionDialogDelegate3 == null || bi1VarN == null) {
                this.k = 0;
                d();
                return;
            }
            y70 y70Var = new y70(this, 23, context);
            View viewJ = z7.J(R$layout.permission_dialog, permissionDialogDelegate3.c.i.get());
            TextView textView = (TextView) viewJ.findViewById(R$id.text);
            String str = permissionDialogDelegate3.e;
            textView.setText(str);
            textView.setCompoundDrawablesRelativeWithIntrinsicBounds(permissionDialogDelegate3.d, 0, 0, 0);
            PropertyModel.b bVar = new PropertyModel.b(di1.B);
            bVar.e(di1.a, this);
            bVar.e(di1.h, viewJ);
            bVar.e(di1.j, permissionDialogDelegate3.f);
            bVar.e(di1.m, permissionDialogDelegate3.g);
            bVar.e(di1.b, str);
            bVar.b(di1.r, true);
            bVar.e(di1.s, y70Var);
            PropertyModel.q qVar = di1.A;
            HashMap map = uh3.a;
            int i = zj.a;
            PropertyModel.e eVar = new PropertyModel.e();
            eVar.a = 600L;
            bVar.a.put(qVar, eVar);
            PropertyModel propertyModelA = bVar.a();
            this.f = propertyModelA;
            this.i.j(propertyModelA, 0, false);
            this.k = 2;
        }
    }

    @Override // org.chromium.components.permissions.AndroidPermissionRequester.c
    public final void g() {
        PermissionDialogDelegate permissionDialogDelegate = this.h;
        if (permissionDialogDelegate == null) {
            this.k = 0;
        } else {
            N.MoC5mife(permissionDialogDelegate.a, permissionDialogDelegate);
            a(1);
        }
        d();
    }

    @Override // org.chromium.components.permissions.AndroidPermissionRequester.c
    public final void j() {
        PermissionDialogDelegate permissionDialogDelegate = this.h;
        if (permissionDialogDelegate == null) {
            this.k = 0;
        } else {
            N.My1ZBTOK(permissionDialogDelegate.a, permissionDialogDelegate);
            a(0);
        }
        d();
    }
}
