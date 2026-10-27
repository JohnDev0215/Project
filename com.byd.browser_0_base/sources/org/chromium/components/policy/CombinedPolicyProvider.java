package org.chromium.components.policy;

import defpackage.m22;
import defpackage.p22;
import java.util.ArrayList;
import java.util.Iterator;
import org.chromium.base.ThreadUtils;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes3.dex */
public class CombinedPolicyProvider {
    public static CombinedPolicyProvider g;
    public long a;
    public PolicyConverter b;
    public m22 c;
    public final ArrayList d = new ArrayList();
    public final ArrayList e = new ArrayList();
    public final ArrayList f = new ArrayList();

    public static CombinedPolicyProvider a() {
        if (g == null) {
            g = new CombinedPolicyProvider();
        }
        return g;
    }

    @CalledByNative
    public static CombinedPolicyProvider linkNative(long j, PolicyConverter policyConverter) {
        Object obj = ThreadUtils.a;
        CombinedPolicyProvider combinedPolicyProviderA = a();
        combinedPolicyProviderA.a = j;
        combinedPolicyProviderA.b = policyConverter;
        if (j != 0) {
            if (combinedPolicyProviderA.d.isEmpty()) {
                m22 m22Var = new m22();
                combinedPolicyProviderA.c = m22Var;
                m22Var.b = 0;
                m22Var.a = combinedPolicyProviderA;
            }
            combinedPolicyProviderA.refreshPolicies();
        }
        return a();
    }

    @CalledByNative
    public void refreshPolicies() {
        m22 m22Var = this.c;
        int i = 0;
        if (m22Var != null) {
            m22Var.b();
            return;
        }
        while (true) {
            ArrayList arrayList = this.e;
            if (i >= arrayList.size()) {
                break;
            }
            arrayList.set(i, null);
            i++;
        }
        Iterator it = this.d.iterator();
        while (it.hasNext()) {
            ((p22) it.next()).b();
        }
    }
}
