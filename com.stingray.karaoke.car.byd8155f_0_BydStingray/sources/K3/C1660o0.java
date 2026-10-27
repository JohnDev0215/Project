package k3;

import android.os.Bundle;
import h2.AbstractC1343A;

/* JADX INFO: renamed from: k3.o0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1660o0 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final String f18019e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final String f18020f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final String f18021g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final String f18022h;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Bundle f18023a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f18024b;
    public final boolean c;
    public final boolean d;

    static {
        int i7 = AbstractC1343A.f15195a;
        f18019e = Integer.toString(0, 36);
        f18020f = Integer.toString(1, 36);
        f18021g = Integer.toString(2, 36);
        f18022h = Integer.toString(3, 36);
    }

    public C1660o0(Bundle bundle, boolean z6, boolean z10, boolean z11) {
        this.f18023a = new Bundle(bundle);
        this.f18024b = z6;
        this.c = z10;
        this.d = z11;
    }

    public static C1660o0 a(Bundle bundle) {
        Bundle bundle2 = bundle.getBundle(f18019e);
        boolean z6 = bundle.getBoolean(f18020f, false);
        boolean z10 = bundle.getBoolean(f18021g, false);
        boolean z11 = bundle.getBoolean(f18022h, false);
        if (bundle2 == null) {
            bundle2 = Bundle.EMPTY;
        }
        return new C1660o0(bundle2, z6, z10, z11);
    }
}
