package k3;

import android.os.Bundle;
import h2.AbstractC1343A;
import h2.AbstractC1346c;

/* JADX INFO: renamed from: k3.g, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1642g {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final String f17953g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final String f17954h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final String f17955i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final String f17956j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final String f17957k;
    public static final String l;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f17958a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f17959b;
    public final String c;
    public final int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Bundle f17960e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f17961f;

    static {
        int i7 = AbstractC1343A.f15195a;
        f17953g = Integer.toString(0, 36);
        f17954h = Integer.toString(1, 36);
        f17955i = Integer.toString(2, 36);
        f17956j = Integer.toString(3, 36);
        f17957k = Integer.toString(4, 36);
        l = Integer.toString(5, 36);
    }

    public C1642g(String str, int i7, Bundle bundle) {
        this(1005000300, 7, str, i7, new Bundle(bundle), 0);
    }

    public static C1642g a(Bundle bundle) {
        int i7 = bundle.getInt(f17953g, 0);
        int i10 = bundle.getInt(f17957k, 0);
        String string = bundle.getString(f17954h);
        string.getClass();
        String str = f17955i;
        AbstractC1346c.c(bundle.containsKey(str));
        int i11 = bundle.getInt(str);
        Bundle bundle2 = bundle.getBundle(f17956j);
        int i12 = bundle.getInt(l, 0);
        if (bundle2 == null) {
            bundle2 = Bundle.EMPTY;
        }
        return new C1642g(i7, i10, string, i11, bundle2, i12);
    }

    public final Bundle b() {
        Bundle bundle = new Bundle();
        bundle.putInt(f17953g, this.f17958a);
        bundle.putString(f17954h, this.c);
        bundle.putInt(f17955i, this.d);
        bundle.putBundle(f17956j, this.f17960e);
        bundle.putInt(f17957k, this.f17959b);
        bundle.putInt(l, this.f17961f);
        return bundle;
    }

    public C1642g(int i7, int i10, String str, int i11, Bundle bundle, int i12) {
        this.f17958a = i7;
        this.f17959b = i10;
        this.c = str;
        this.d = i11;
        this.f17960e = bundle;
        this.f17961f = i12;
    }
}
