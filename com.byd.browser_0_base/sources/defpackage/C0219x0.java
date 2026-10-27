package defpackage;

import android.accounts.Account;
import android.app.Activity;
import android.text.TextUtils;
import defpackage.a1;
import defpackage.b1;
import defpackage.h62;
import java.util.List;
import org.chromium.components.signin.AccountManagerFacade;
import org.chromium.components.signin.AccountManagerFacadeProvider;
import org.chromium.components.signin.b;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX WARN: Classes with same name are omitted, all sources:
  assets/webapk8.dex
  classes2.dex
 */
/* JADX INFO: renamed from: x0, reason: case insensitive filesystem */
/* JADX INFO: compiled from: AccountPickerBottomSheetMediator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class C0219x0 implements b1.a, a1.a, z1, h62.b {
    public final WindowAndroid e;
    public final Activity f;
    public final c1 g;
    public final h62 h;
    public final PropertyModel i;
    public final AccountManagerFacade j;
    public String k;
    public String l;
    public String m;
    public final mw n;
    public final aq1<Boolean> o = new aq1<>();

    public C0219x0(WindowAndroid windowAndroid, c1 c1Var, zx1 zx1Var, C0217w0 c0217w0) {
        this.e = windowAndroid;
        Activity activity = (Activity) windowAndroid.k().get();
        this.f = activity;
        this.g = c1Var;
        h62 h62VarC = h62.c(activity);
        this.h = h62VarC;
        d92 d92Var = new d92(19, this);
        zx1 zx1Var2 = new zx1(28, this);
        p10 p10Var = new p10(5, zx1Var);
        int iB = c1Var.b();
        PropertyModel.b bVar = new PropertyModel.b(C0221y0.h);
        bVar.e(C0221y0.a, new yy0(1, d92Var));
        bVar.e(C0221y0.b, null);
        bVar.e(C0221y0.c, new p10(6, zx1Var2));
        bVar.e(C0221y0.d, p10Var);
        bVar.c(C0221y0.e, 0);
        bVar.c(C0221y0.f, iB);
        bVar.e(C0221y0.g, c0217w0);
        PropertyModel propertyModelA = bVar.a();
        this.i = propertyModelA;
        mw mwVar = new mw(2, this);
        this.n = mwVar;
        propertyModelA.a(mwVar);
        h62VarC.b(this);
        AccountManagerFacade accountManagerFacadeProvider = AccountManagerFacadeProvider.getInstance();
        this.j = accountManagerFacadeProvider;
        accountManagerFacadeProvider.a(this);
        this.m = null;
        a(b.e(accountManagerFacadeProvider.b()));
    }

    @Override // defpackage.z1
    public final void F() {
        this.j.b().g(new se3(5, this));
    }

    public final void a(List<Account> list) {
        boolean zIsEmpty = list.isEmpty();
        PropertyModel.p pVar = C0221y0.e;
        PropertyModel propertyModel = this.i;
        PropertyModel.r<fb0> rVar = C0221y0.b;
        if (zIsEmpty) {
            propertyModel.n(pVar, 0);
            this.k = null;
            this.l = null;
            propertyModel.p(rVar, null);
            return;
        }
        this.l = list.get(0).name;
        int iH = propertyModel.h(pVar);
        h62 h62Var = this.h;
        if (iH == 0) {
            String str = this.l;
            this.k = str;
            if (TextUtils.equals(str, str)) {
                propertyModel.p(rVar, h62Var.d(str));
            }
            propertyModel.n(pVar, 1);
            return;
        }
        if (iH == 1 && b.d(this.k, list) == null) {
            String str2 = this.l;
            this.k = str2;
            if (TextUtils.equals(str2, str2)) {
                propertyModel.p(rVar, h62Var.d(str2));
            }
        }
    }

    @Override // b1.a
    public final void l(String str) {
        PropertyModel propertyModel = this.i;
        propertyModel.n(C0221y0.e, 1);
        this.k = str;
        if (TextUtils.equals(str, str)) {
            propertyModel.p(C0221y0.b, this.h.d(str));
        }
    }

    @Override // h62.b
    public final void n(String str) {
        if (TextUtils.equals(this.k, str)) {
            this.i.p(C0221y0.b, this.h.d(str));
        }
    }

    @Override // b1.a
    public final void u() {
        oj2.K(2);
        this.j.k();
    }
}
