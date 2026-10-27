package defpackage;

import android.accounts.Account;
import android.content.Context;
import android.text.SpannableString;
import android.text.TextUtils;
import defpackage.b1;
import defpackage.h62;
import defpackage.hm2;
import defpackage.lm2;
import java.util.List;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.signin.SigninFirstRunFragment;
import org.chromium.components.signin.AccountManagerFacade;
import org.chromium.components.signin.AccountManagerFacadeProvider;
import org.chromium.components.signin.b;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: SigninFirstRunMediator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class km2 implements z1, h62.b, b1.a {
    public final Context e;
    public final bi1 f;
    public final AccountManagerFacade g;
    public final hm2.a h;
    public final t42 i;
    public final PropertyModel j;
    public final h62 k;
    public boolean l;
    public int m;
    public boolean n;
    public e1 o;
    public String p;
    public String q;
    public boolean r;

    public km2(Context context, bi1 bi1Var, hm2.a aVar, v42 v42Var) {
        this.e = context;
        this.f = bi1Var;
        this.h = aVar;
        this.i = v42Var;
        h62 h62VarC = h62.c(context);
        this.k = h62VarC;
        f52 f52Var = new f52(21, this);
        d92 d92Var = new d92(20, this);
        zx1 zx1Var = new zx1(29, this);
        zi0.b.getClass();
        SpannableString spannableString = new SpannableString("");
        PropertyModel.b bVar = new PropertyModel.b(lm2.l);
        bVar.e(lm2.a, new od3(5, f52Var));
        bVar.e(lm2.b, null);
        bVar.b(lm2.c, false);
        bVar.e(lm2.d, new yy0(2, d92Var));
        bVar.e(lm2.e, new p10(7, zx1Var));
        bVar.b(lm2.h, true);
        bVar.e(lm2.i, null);
        bVar.b(lm2.j, false);
        bVar.e(lm2.k, spannableString);
        this.j = bVar.a();
        SigninFirstRunFragment signinFirstRunFragment = (SigninFirstRunFragment) aVar;
        signinFirstRunFragment.b().l1().g(new dr(27, this));
        signinFirstRunFragment.b().e0().h(new x10(15, this));
        signinFirstRunFragment.b().I().h(new se3(7, this));
        h62VarC.b(this);
        AccountManagerFacade accountManagerFacadeProvider = AccountManagerFacadeProvider.getInstance();
        this.g = accountManagerFacadeProvider;
        accountManagerFacadeProvider.a(this);
        c(b.e(accountManagerFacadeProvider.b()));
    }

    @Override // defpackage.z1
    public final void F() {
        this.g.b().g(new jb1(25, this));
    }

    public final void a() {
        boolean z;
        if (this.l) {
            return;
        }
        SigninFirstRunFragment signinFirstRunFragment = (SigninFirstRunFragment) this.h;
        if (!signinFirstRunFragment.b().l1().d() || signinFirstRunFragment.b().I().get() == null || signinFirstRunFragment.b().e0().get() == null || this.n) {
            return;
        }
        this.n = true;
        boolean zBooleanValue = signinFirstRunFragment.b().e0().get().booleanValue();
        PropertyModel propertyModel = this.j;
        propertyModel.l(lm2.h, false);
        if (zBooleanValue) {
            z7 z7VarM = z7.m();
            Profile profileD = Profile.d();
            z7VarM.getClass();
            z7.D(profileD).e();
            z = !((v42) this.i).b();
            propertyModel.p(lm2.i, new lm2.a());
        } else {
            z = false;
        }
        PropertyModel.n nVar = lm2.j;
        zi0.b.getClass();
        propertyModel.l(nVar, false);
        this.r = true ^ z;
        propertyModel.p(lm2.k, new SpannableString(""));
        signinFirstRunFragment.b().n1();
        oj2.U(this.m, 3, "MobileFre.SlowestLoadPoint");
    }

    public final boolean b() {
        PropertyModel.n nVar = lm2.f;
        PropertyModel propertyModel = this.j;
        return propertyModel.k(nVar) || propertyModel.k(lm2.g);
    }

    public final void c(List<Account> list) {
        boolean zIsEmpty = list.isEmpty();
        PropertyModel propertyModel = this.j;
        PropertyModel.r<fb0> rVar = lm2.b;
        if (zIsEmpty) {
            this.q = null;
            this.p = null;
            propertyModel.p(rVar, null);
            e1 e1Var = this.o;
            if (e1Var != null) {
                e1Var.b.b(3, e1Var.c);
            }
        } else {
            this.q = list.get(0).name;
            String str = this.p;
            if (str == null || b.d(str, list) == null) {
                String str2 = this.q;
                this.p = str2;
                if (TextUtils.equals(str2, str2)) {
                    propertyModel.p(rVar, this.k.d(str2));
                }
            }
        }
        b.b(this.g, list, new gx(26, this));
    }

    @Override // b1.a
    public final void l(String str) {
        this.p = str;
        if (TextUtils.equals(str, str)) {
            this.j.p(lm2.b, this.k.d(str));
        }
        e1 e1Var = this.o;
        if (e1Var != null) {
            e1Var.b.b(3, e1Var.c);
        }
    }

    @Override // h62.b
    public final void n(String str) {
        if (TextUtils.equals(this.p, str)) {
            this.j.p(lm2.b, this.k.d(str));
        }
    }

    @Override // b1.a
    public final void u() {
        ((SigninFirstRunFragment) this.h).b().R0(8);
        AccountManagerFacadeProvider.getInstance().k();
    }
}
