package org.chromium.policy;

import defpackage.AbstractC1047kL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.chromium.base.ThreadUtils;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class CombinedPolicyProvider {
    public static CombinedPolicyProvider e;
    public long a;
    public PolicyConverter b;
    public final List c = new ArrayList();
    public final List d = new ArrayList();

    public CombinedPolicyProvider() {
        new ArrayList();
    }

    public static CombinedPolicyProvider a() {
        if (e == null) {
            e = new CombinedPolicyProvider();
        }
        return e;
    }

    public static CombinedPolicyProvider linkNative(long j, PolicyConverter policyConverter) {
        ThreadUtils.b();
        CombinedPolicyProvider combinedPolicyProviderA = a();
        combinedPolicyProviderA.a = j;
        combinedPolicyProviderA.b = policyConverter;
        if (j != 0) {
            Iterator it = combinedPolicyProviderA.c.iterator();
            while (it.hasNext()) {
                ((AbstractC1047kL) it.next()).b();
            }
        }
        return a();
    }

    public void refreshPolicies() {
        for (int i = 0; i < this.d.size(); i++) {
            this.d.set(i, null);
        }
        Iterator it = this.c.iterator();
        while (it.hasNext()) {
            ((AbstractC1047kL) it.next()).b();
        }
    }
}
