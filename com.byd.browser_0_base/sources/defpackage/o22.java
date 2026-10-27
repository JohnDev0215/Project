package defpackage;

import defpackage.ll;
import org.chromium.base.Callback;
import org.chromium.base.ThreadUtils;
import org.chromium.components.policy.PolicyService;

/* JADX INFO: compiled from: PolicyLoadListener.java */
/* JADX INFO: loaded from: classes.dex */
public final class o22 implements qs1<Boolean> {
    public final ll e;
    public final rs1<Boolean> f;
    public final qs1<PolicyService> g;
    public PolicyService.a h;
    public Boolean i;

    public o22(fn0 fn0Var, rs1 rs1Var) {
        ll llVar = new ll();
        this.e = llVar;
        this.f = new rs1<>();
        this.g = rs1Var;
        ll.b bVarB = llVar.b(new c3(26, this));
        fn0Var.getClass();
        Object obj = ThreadUtils.a;
        if (fn0Var.a) {
            bVarB.onResult(Boolean.valueOf(fn0Var.b));
        } else {
            fn0Var.d.add(bVarB);
        }
        rs1Var.h(llVar.b(new xr(20, this)));
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x002f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void a() {
        /*
            r6 = this;
            rs1<java.lang.Boolean> r0 = r6.f
            java.lang.Object r1 = r0.get()
            if (r1 == 0) goto L9
            return
        L9:
            java.lang.Boolean r1 = r6.i
            r2 = 1
            r3 = 0
            if (r1 == 0) goto L17
            boolean r1 = r1.booleanValue()
            if (r1 != 0) goto L17
            r1 = r2
            goto L18
        L17:
            r1 = r3
        L18:
            qs1<org.chromium.components.policy.PolicyService> r6 = r6.g
            java.lang.Object r4 = r6.get()
            if (r4 == 0) goto L2f
            java.lang.Object r6 = r6.get()
            org.chromium.components.policy.PolicyService r6 = (org.chromium.components.policy.PolicyService) r6
            long r4 = r6.a
            boolean r6 = J.N.MCCtS0px(r4, r6)
            if (r6 == 0) goto L2f
            goto L30
        L2f:
            r2 = r3
        L30:
            if (r1 == 0) goto L38
            java.lang.Boolean r6 = java.lang.Boolean.FALSE
            r0.a(r6)
            goto L3f
        L38:
            if (r2 == 0) goto L3f
            java.lang.Boolean r6 = java.lang.Boolean.TRUE
            r0.a(r6)
        L3f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.o22.a():void");
    }

    @Override // defpackage.nw2
    public final Object get() {
        return this.f.get();
    }

    @Override // defpackage.qs1
    public final Boolean h(Callback<Boolean> callback) {
        return this.f.h(this.e.b(callback));
    }
}
