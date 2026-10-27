package defpackage;

import android.accounts.Account;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import defpackage.b1;
import defpackage.h62;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import org.chromium.base.Callback;
import org.chromium.components.signin.AccountManagerFacade;
import org.chromium.components.signin.AccountManagerFacadeProvider;
import org.chromium.components.signin.b;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX WARN: Classes with same name are omitted, all sources:
  assets/webapk8.dex
  classes2.dex
 */
/* JADX INFO: compiled from: AccountPickerMediator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class f1 implements z1, h62.b {
    public final ya1 e;
    public final b1.a f;
    public final h62 g;
    public final AccountManagerFacade h;

    public f1(Context context, ya1 ya1Var, b1.a aVar) {
        this.e = ya1Var;
        this.f = aVar;
        h62 h62VarC = h62.c(context);
        this.g = h62VarC;
        AccountManagerFacade accountManagerFacadeProvider = AccountManagerFacadeProvider.getInstance();
        this.h = accountManagerFacadeProvider;
        accountManagerFacadeProvider.a(this);
        h62VarC.b(this);
        a(b.e(accountManagerFacadeProvider.b()));
    }

    @Override // defpackage.z1
    public final void F() {
        this.h.b().g(new se3(6, this));
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [T, jb1] */
    /* JADX WARN: Type inference failed for: r2v2, types: [T, nd3] */
    /* JADX WARN: Type inference failed for: r2v6, types: [T, fb0] */
    public final void a(List<Account> list) {
        ya1 ya1Var = this.e;
        ya1Var.clear();
        ?? jb1Var = new jb1(24, this);
        Iterator<Account> it = list.iterator();
        while (it.hasNext()) {
            ?? D = this.g.d(it.next().name);
            HashMap mapE = PropertyModel.e(h1.c);
            PropertyModel.r<fb0> rVar = h1.a;
            int i = zj.a;
            PropertyModel.g gVar = new PropertyModel.g();
            gVar.a = D;
            mapE.put(rVar, gVar);
            PropertyModel.l<Callback<fb0>> lVar = h1.b;
            PropertyModel.g gVar2 = new PropertyModel.g();
            gVar2.a = jb1Var;
            ya1Var.t(new xa1(1, C0175b.p(mapE, lVar, gVar2, mapE)));
        }
        b1.a aVar = this.f;
        Objects.requireNonNull(aVar);
        ve3 ve3Var = new ve3(4, aVar);
        HashMap mapE2 = PropertyModel.e(g1.b);
        PropertyModel.l<View.OnClickListener> lVar2 = g1.a;
        ?? nd3Var = new nd3(9, ve3Var);
        int i2 = zj.a;
        PropertyModel.g gVar3 = new PropertyModel.g();
        gVar3.a = nd3Var;
        ya1Var.t(new xa1(2, C0175b.p(mapE2, lVar2, gVar3, mapE2)));
    }

    @Override // h62.b
    public final void n(String str) {
        for (xa1 xa1Var : this.e) {
            if (xa1Var.a == 1) {
                PropertyModel.r<fb0> rVar = h1.a;
                PropertyModel propertyModel = xa1Var.b;
                if (TextUtils.equals(str, ((fb0) propertyModel.j(rVar)).a)) {
                    propertyModel.p(rVar, this.g.d(str));
                    return;
                }
            }
        }
    }
}
