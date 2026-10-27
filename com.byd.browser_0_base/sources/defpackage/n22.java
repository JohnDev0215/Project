package defpackage;

import J.N;
import org.chromium.base.b;
import org.chromium.components.policy.PolicyService;

/* JADX INFO: compiled from: PolicyLoadListener.java */
/* JADX INFO: loaded from: classes.dex */
public final class n22 implements PolicyService.a {
    public final /* synthetic */ PolicyService a;
    public final /* synthetic */ o22 b;

    public n22(o22 o22Var, PolicyService policyService) {
        this.b = o22Var;
        this.a = policyService;
    }

    @Override // org.chromium.components.policy.PolicyService.a
    public final void b() {
        o22 o22Var = this.b;
        PolicyService.a aVar = o22Var.h;
        PolicyService policyService = this.a;
        b<PolicyService.a> bVar = policyService.b;
        bVar.c(aVar);
        if (bVar.isEmpty()) {
            N.MU0pXsSP(policyService.a, policyService);
        }
        o22Var.h = null;
        o22Var.a();
    }
}
