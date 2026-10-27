package androidx.lifecycle;

import android.os.Bundle;
import java.util.Map;
import r.C2257p;

/* JADX INFO: loaded from: classes.dex */
public final class b0 implements t3.d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C2257p f11967a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f11968b;
    public Bundle c;
    public final m7.n d;

    public b0(C2257p c2257p, l0 l0Var) {
        A7.m.f(c2257p, "savedStateRegistry");
        this.f11967a = c2257p;
        this.d = h6.r.o(new K7.K(l0Var, 27));
    }

    @Override // t3.d
    public final Bundle a() {
        Bundle bundle = new Bundle();
        Bundle bundle2 = this.c;
        if (bundle2 != null) {
            bundle.putAll(bundle2);
        }
        for (Map.Entry entry : ((c0) this.d.getValue()).f11971b.entrySet()) {
            String str = (String) entry.getKey();
            Bundle bundleA = ((X) entry.getValue()).f11961e.a();
            if (!A7.m.a(bundleA, Bundle.EMPTY)) {
                bundle.putBundle(str, bundleA);
            }
        }
        this.f11968b = false;
        return bundle;
    }

    public final void b() {
        if (this.f11968b) {
            return;
        }
        Bundle bundleB = this.f11967a.b("androidx.lifecycle.internal.SavedStateHandlesProvider");
        Bundle bundle = new Bundle();
        Bundle bundle2 = this.c;
        if (bundle2 != null) {
            bundle.putAll(bundle2);
        }
        if (bundleB != null) {
            bundle.putAll(bundleB);
        }
        this.c = bundle;
        this.f11968b = true;
    }
}
