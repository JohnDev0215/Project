package e2;

import android.media.AudioAttributes;
import android.os.Bundle;
import h2.AbstractC1343A;

/* JADX INFO: renamed from: e2.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1195c {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final C1195c f14046g = new C1195c(0, 0, 1, 1, 0);

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final String f14047h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final String f14048i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final String f14049j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final String f14050k;
    public static final String l;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f14051a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f14052b;
    public final int c;
    public final int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f14053e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Q f14054f;

    static {
        int i7 = AbstractC1343A.f15195a;
        f14047h = Integer.toString(0, 36);
        f14048i = Integer.toString(1, 36);
        f14049j = Integer.toString(2, 36);
        f14050k = Integer.toString(3, 36);
        l = Integer.toString(4, 36);
    }

    public C1195c(int i7, int i10, int i11, int i12, int i13) {
        this.f14051a = i7;
        this.f14052b = i10;
        this.c = i11;
        this.d = i12;
        this.f14053e = i13;
    }

    public static C1195c a(Bundle bundle) {
        String str = f14047h;
        int i7 = bundle.containsKey(str) ? bundle.getInt(str) : 0;
        String str2 = f14048i;
        int i10 = bundle.containsKey(str2) ? bundle.getInt(str2) : 0;
        String str3 = f14049j;
        int i11 = bundle.containsKey(str3) ? bundle.getInt(str3) : 1;
        String str4 = f14050k;
        int i12 = bundle.containsKey(str4) ? bundle.getInt(str4) : 1;
        String str5 = l;
        return new C1195c(i7, i10, i11, i12, bundle.containsKey(str5) ? bundle.getInt(str5) : 0);
    }

    public final Q b() {
        if (this.f14054f == null) {
            Q q4 = new Q();
            AudioAttributes.Builder usage = new AudioAttributes.Builder().setContentType(this.f14051a).setFlags(this.f14052b).setUsage(this.c);
            int i7 = AbstractC1343A.f15195a;
            if (i7 >= 29) {
                usage.setAllowedCapturePolicy(this.d);
            }
            if (i7 >= 32) {
                usage.setSpatializationBehavior(this.f14053e);
            }
            q4.f13998a = usage.build();
            this.f14054f = q4;
        }
        return this.f14054f;
    }

    public final Bundle c() {
        Bundle bundle = new Bundle();
        bundle.putInt(f14047h, this.f14051a);
        bundle.putInt(f14048i, this.f14052b);
        bundle.putInt(f14049j, this.c);
        bundle.putInt(f14050k, this.d);
        bundle.putInt(l, this.f14053e);
        return bundle;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || C1195c.class != obj.getClass()) {
            return false;
        }
        C1195c c1195c = (C1195c) obj;
        return this.f14051a == c1195c.f14051a && this.f14052b == c1195c.f14052b && this.c == c1195c.c && this.d == c1195c.d && this.f14053e == c1195c.f14053e;
    }

    public final int hashCode() {
        return ((((((((527 + this.f14051a) * 31) + this.f14052b) * 31) + this.c) * 31) + this.d) * 31) + this.f14053e;
    }
}
