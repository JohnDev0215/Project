package org.chromium.chrome.browser.firstrun;

import J.N;
import android.os.SystemClock;
import android.util.Log;
import defpackage.dm0;
import defpackage.fn0;
import defpackage.ko0;
import defpackage.ll;
import defpackage.o22;
import defpackage.oj2;
import defpackage.qs1;
import defpackage.rs1;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.enterprise.util.EnterpriseInfo;
import org.chromium.chrome.browser.firstrun.LightweightFirstRunActivity;

/* JADX INFO: compiled from: SkipTosDialogPolicyListener.java */
/* JADX INFO: loaded from: classes.dex */
public final class a implements qs1<Boolean> {
    public final ll e;
    public final rs1<Boolean> f;
    public final long g;
    public final InterfaceC0086a h;
    public o22 i;
    public Boolean j;
    public Boolean k;

    /* JADX INFO: renamed from: org.chromium.chrome.browser.firstrun.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: SkipTosDialogPolicyListener.java */
    public interface InterfaceC0086a {
    }

    public a(fn0 fn0Var, rs1 rs1Var, EnterpriseInfo enterpriseInfo) {
        this.e = new ll();
        this.f = new rs1<>();
        this.g = SystemClock.elapsedRealtime();
        this.h = null;
        o22 o22Var = new o22(fn0Var, rs1Var);
        this.i = o22Var;
        a(enterpriseInfo, o22Var);
    }

    public final void a(EnterpriseInfo enterpriseInfo, qs1<Boolean> qs1Var) {
        ko0 ko0Var = new ko0(4, this);
        ll llVar = this.e;
        Boolean boolH = qs1Var.h(llVar.b(ko0Var));
        if (boolH != null) {
            c(boolH.booleanValue());
        }
        if (this.f.get() == null) {
            enterpriseInfo.a(llVar.b(new dm0(5, this)));
        }
    }

    @Override // defpackage.qs1
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public final Boolean h(Callback<Boolean> callback) {
        return this.f.h(this.e.b(callback));
    }

    public final void c(boolean z) {
        if (this.j != null) {
            return;
        }
        if (z) {
            this.j = Boolean.valueOf(N.MJs$aI$X());
            InterfaceC0086a interfaceC0086a = this.h;
            if (interfaceC0086a != null) {
                oj2.b0(SystemClock.elapsedRealtime() - this.g, LightweightFirstRunActivity.this.s0 ? "MobileFre.Lightweight.EnterprisePolicyCheckSpeed.SlowerThanInflation" : "MobileFre.Lightweight.EnterprisePolicyCheckSpeed.FasterThanInflation");
            }
        } else {
            this.j = Boolean.TRUE;
        }
        e();
    }

    public final void e() {
        rs1<Boolean> rs1Var = this.f;
        if (rs1Var.get() != null) {
            return;
        }
        Boolean bool = this.k;
        boolean z = (bool == null || bool.booleanValue()) ? false : true;
        Boolean bool2 = this.j;
        boolean z2 = bool2 != null && bool2.booleanValue();
        if (!(this.k == null || this.j == null)) {
            Log.i("cr_SkipTosPolicy", "Supplier available, <TosDialogEnabled>=" + this.j + " <IsDeviceOwned>=" + this.k);
            rs1Var.a(Boolean.valueOf(!this.j.booleanValue() && this.k.booleanValue()));
            return;
        }
        if (z2 || z) {
            Log.i("cr_SkipTosPolicy", "Supplier early out, <confirmedTosDialogEnabled>=" + z2 + " <confirmedDeviceNotOwned>=" + z);
            rs1Var.a(Boolean.FALSE);
        }
    }

    @Override // defpackage.nw2
    public final Object get() {
        return this.f.get();
    }

    public a(qs1 qs1Var, EnterpriseInfo enterpriseInfo, LightweightFirstRunActivity.b bVar) {
        this.e = new ll();
        this.f = new rs1<>();
        this.g = SystemClock.elapsedRealtime();
        this.h = bVar;
        a(enterpriseInfo, qs1Var);
    }
}
