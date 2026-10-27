package defpackage;

import android.os.SystemClock;
import org.chromium.base.Callback;
import org.chromium.base.ThreadUtils;
import org.chromium.components.signin.AccountManagerFacade;

/* JADX INFO: compiled from: ChildAccountStatusSupplier.java */
/* JADX INFO: loaded from: classes.dex */
public final class dn implements qs1<Boolean> {
    public final rs1<Boolean> e = new rs1<>();
    public final long f = SystemClock.elapsedRealtime();
    public Boolean g;
    public Boolean h;

    public dn(AccountManagerFacade accountManagerFacade, fn0 fn0Var) {
        if (fn0Var != null) {
            dm0 dm0Var = new dm0(1, this);
            Object obj = ThreadUtils.a;
            if (fn0Var.a) {
                dm0Var.onResult(Boolean.valueOf(fn0Var.b));
            } else {
                fn0Var.d.add(dm0Var);
            }
        }
        accountManagerFacade.b().g(new gp(this, 13, accountManagerFacade));
    }

    public final void a() {
        rs1<Boolean> rs1Var = this.e;
        if (rs1Var.get() != null) {
            return;
        }
        Boolean bool = this.h;
        if (bool == null) {
            Boolean bool2 = this.g;
            bool = bool2 != null && !bool2.booleanValue() ? Boolean.FALSE : null;
        }
        if (bool == null) {
            return;
        }
        oj2.b0(SystemClock.elapsedRealtime() - this.f, "MobileFre.ChildAccountStatusDuration");
        rs1Var.a(bool);
    }

    @Override // defpackage.nw2
    public final Object get() {
        return this.e.get();
    }

    @Override // defpackage.qs1
    public final Boolean h(Callback<Boolean> callback) {
        return this.e.h(callback);
    }
}
