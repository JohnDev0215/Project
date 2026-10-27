package org.chromium.components.policy;

import java.util.Iterator;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.b;

/* JADX INFO: loaded from: classes3.dex */
public class PolicyService {
    public final long a;
    public final b<a> b = new b<>();

    @CalledByNative
    public PolicyService(long j) {
        this.a = j;
    }

    @CalledByNative
    public final void onPolicyServiceInitialized() {
        Iterator<a> it = this.b.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((a) aVar.next()).b();
            }
        }
    }

    @CalledByNative
    public final void onPolicyUpdated(PolicyMap policyMap, PolicyMap policyMap2) {
        Iterator<a> it = this.b.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((a) aVar.next()).a();
            }
        }
    }

    public interface a {
        default void a() {
        }

        default void b() {
        }
    }
}
