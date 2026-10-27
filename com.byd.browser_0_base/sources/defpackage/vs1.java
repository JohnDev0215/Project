package defpackage;

import J.N;
import java.util.HashMap;
import java.util.List;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.optimization_guide.OptimizationGuideBridge;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.profiles.ProfileManager;

/* JADX INFO: compiled from: OptimizationGuideBridgeFactory.java */
/* JADX INFO: loaded from: classes2.dex */
public final class vs1 {
    public final HashMap a = new HashMap();
    public final List<ou0> b;
    public final a c;

    public vs1(List<ou0> list) {
        this.b = list;
        if (this.c == null) {
            a aVar = new a();
            this.c = aVar;
            ProfileManager.a(aVar);
        }
    }

    public final OptimizationGuideBridge a() {
        Profile profileD = Profile.d();
        HashMap map = this.a;
        OptimizationGuideBridge optimizationGuideBridge = (OptimizationGuideBridge) map.get(profileD);
        if (optimizationGuideBridge == null) {
            optimizationGuideBridge = new OptimizationGuideBridge();
            List<ou0> list = this.b;
            if (list.size() > 0) {
                Object obj = ThreadUtils.a;
                if (optimizationGuideBridge.a != 0) {
                    int[] iArr = new int[list.size()];
                    for (int i = 0; i < list.size(); i++) {
                        iArr[i] = list.get(i).e;
                    }
                    N.MqYUgADF(optimizationGuideBridge.a, iArr);
                }
            }
            map.put(profileD, optimizationGuideBridge);
        }
        return optimizationGuideBridge;
    }

    /* JADX INFO: compiled from: OptimizationGuideBridgeFactory.java */
    public class a implements ProfileManager.a {
        public a() {
        }

        @Override // org.chromium.chrome.browser.profiles.ProfileManager.a
        public final void e(Profile profile) {
            vs1 vs1Var = vs1.this;
            if (vs1Var.a.containsKey(profile)) {
                HashMap map = vs1Var.a;
                OptimizationGuideBridge optimizationGuideBridge = (OptimizationGuideBridge) map.get(profile);
                optimizationGuideBridge.getClass();
                Object obj = ThreadUtils.a;
                long j = optimizationGuideBridge.a;
                if (j != 0) {
                    N.M2siX4Rz(j);
                    optimizationGuideBridge.a = 0L;
                }
                map.remove(profile);
            }
        }

        @Override // org.chromium.chrome.browser.profiles.ProfileManager.a
        public final void b(Profile profile) {
        }
    }
}
