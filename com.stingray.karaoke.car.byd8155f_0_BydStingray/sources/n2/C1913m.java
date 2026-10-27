package n2;

import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import e2.C1208p;
import h2.AbstractC1343A;
import h2.AbstractC1346c;
import java.util.Objects;
import u2.C2492C;

/* JADX INFO: renamed from: n2.m, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1913m extends e2.O {
    public static final String B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public static final String f19687C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public static final String f19688D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public static final String f19689E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public static final String f19690F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public static final String f19691G;

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public final boolean f19692A;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final int f19693u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final String f19694v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final int f19695w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final C1208p f19696x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final int f19697y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public final C2492C f19698z;

    static {
        int i7 = AbstractC1343A.f15195a;
        B = Integer.toString(1001, 36);
        f19687C = Integer.toString(1002, 36);
        f19688D = Integer.toString(1003, 36);
        f19689E = Integer.toString(1004, 36);
        f19690F = Integer.toString(1005, 36);
        f19691G = Integer.toString(1006, 36);
    }

    public C1913m(int i7, Exception exc, int i10) {
        this(i7, exc, i10, null, -1, null, 4, false);
    }

    @Override // e2.O
    public final boolean a(e2.O o) {
        if (!super.a(o)) {
            return false;
        }
        int i7 = AbstractC1343A.f15195a;
        C1913m c1913m = (C1913m) o;
        return this.f19693u == c1913m.f19693u && Objects.equals(this.f19694v, c1913m.f19694v) && this.f19695w == c1913m.f19695w && Objects.equals(this.f19696x, c1913m.f19696x) && this.f19697y == c1913m.f19697y && Objects.equals(this.f19698z, c1913m.f19698z) && this.f19692A == c1913m.f19692A;
    }

    @Override // e2.O
    public final Bundle b() {
        Bundle bundleB = super.b();
        bundleB.putInt(B, this.f19693u);
        bundleB.putString(f19687C, this.f19694v);
        bundleB.putInt(f19688D, this.f19695w);
        C1208p c1208p = this.f19696x;
        if (c1208p != null) {
            bundleB.putBundle(f19689E, c1208p.d(false));
        }
        bundleB.putInt(f19690F, this.f19697y);
        bundleB.putBoolean(f19691G, this.f19692A);
        return bundleB;
    }

    public final C1913m c(C2492C c2492c) {
        String message = getMessage();
        int i7 = AbstractC1343A.f15195a;
        return new C1913m(message, getCause(), this.l, this.f19693u, this.f19694v, this.f19695w, this.f19696x, this.f19697y, c2492c, this.f13991m, this.f19692A);
    }

    public C1913m(String str, Throwable th, int i7, int i10, String str2, int i11, C1208p c1208p, int i12, C2492C c2492c, long j3, boolean z6) {
        super(str, th, i7, Bundle.EMPTY, j3);
        AbstractC1346c.c(!z6 || i10 == 1);
        AbstractC1346c.c(th != null || i10 == 3);
        this.f19693u = i10;
        this.f19694v = str2;
        this.f19695w = i11;
        this.f19696x = c1208p;
        this.f19697y = i12;
        this.f19698z = c2492c;
        this.f19692A = z6;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public C1913m(int i7, Exception exc, int i10, String str, int i11, C1208p c1208p, int i12, boolean z6) {
        String string;
        String str2;
        if (i7 == 0) {
            string = "Source error";
        } else if (i7 == 1) {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(" error, index=");
            sb.append(i11);
            sb.append(", format=");
            sb.append(c1208p);
            sb.append(", format_supported=");
            int i13 = AbstractC1343A.f15195a;
            if (i12 == 0) {
                str2 = "NO";
            } else if (i12 == 1) {
                str2 = "NO_UNSUPPORTED_TYPE";
            } else if (i12 == 2) {
                str2 = "NO_UNSUPPORTED_DRM";
            } else if (i12 == 3) {
                str2 = "NO_EXCEEDS_CAPABILITIES";
            } else if (i12 == 4) {
                str2 = "YES";
            } else {
                throw new IllegalStateException();
            }
            sb.append(str2);
            string = sb.toString();
        } else if (i7 != 3) {
            string = "Unexpected runtime error";
        } else {
            string = "Remote error";
        }
        this(TextUtils.isEmpty(null) ? string : android.hardware.bydauto.ac.tsimpl.a.h(string, ": null"), exc, i10, i7, str, i11, c1208p, i12, null, SystemClock.elapsedRealtime(), z6);
    }
}
