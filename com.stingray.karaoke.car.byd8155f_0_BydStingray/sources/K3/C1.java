package k3;

import android.os.Bundle;
import android.os.SystemClock;
import h2.AbstractC1343A;
import h2.AbstractC1346c;

/* JADX INFO: loaded from: classes.dex */
public final class C1 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final String f17743e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final String f17744f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final String f17745g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final String f17746h;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f17747a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Bundle f17748b;
    public final long c;
    public final A1 d;

    static {
        int i7 = AbstractC1343A.f15195a;
        f17743e = Integer.toString(0, 36);
        f17744f = Integer.toString(1, 36);
        f17745g = Integer.toString(2, 36);
        f17746h = Integer.toString(3, 36);
    }

    public C1(int i7) {
        this(i7, Bundle.EMPTY);
    }

    public static C1 a(Bundle bundle) {
        int i7 = bundle.getInt(f17743e, -1);
        Bundle bundle2 = bundle.getBundle(f17744f);
        long j3 = bundle.getLong(f17745g, SystemClock.elapsedRealtime());
        Bundle bundle3 = bundle.getBundle(f17746h);
        A1 a1A = bundle3 != null ? A1.a(bundle3) : i7 != 0 ? new A1(i7) : null;
        if (bundle2 == null) {
            bundle2 = Bundle.EMPTY;
        }
        return new C1(i7, bundle2, j3, a1A);
    }

    public final Bundle b() {
        Bundle bundle = new Bundle();
        bundle.putInt(f17743e, this.f17747a);
        bundle.putBundle(f17744f, this.f17748b);
        bundle.putLong(f17745g, this.c);
        A1 a12 = this.d;
        if (a12 != null) {
            bundle.putBundle(f17746h, a12.b());
        }
        return bundle;
    }

    public C1(int i7, Bundle bundle) {
        this(i7, bundle, SystemClock.elapsedRealtime(), null);
    }

    public C1(int i7, Bundle bundle, long j3, A1 a12) {
        AbstractC1346c.c(a12 == null || i7 < 0);
        this.f17747a = i7;
        this.f17748b = new Bundle(bundle);
        this.c = j3;
        if (a12 == null && i7 < 0) {
            a12 = new A1(i7);
        }
        this.d = a12;
    }
}
