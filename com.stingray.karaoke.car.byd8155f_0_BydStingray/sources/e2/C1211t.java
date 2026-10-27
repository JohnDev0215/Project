package e2;

import android.net.Uri;
import h2.AbstractC1343A;

/* JADX INFO: renamed from: e2.t, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1211t {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final String f14338b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Uri f14339a;

    static {
        int i7 = AbstractC1343A.f15195a;
        f14338b = Integer.toString(0, 36);
    }

    public C1211t(Q q4) {
        this.f14339a = (Uri) q4.f13998a;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj instanceof C1211t) && this.f14339a.equals(((C1211t) obj).f14339a)) {
            int i7 = AbstractC1343A.f15195a;
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return this.f14339a.hashCode() * 31;
    }
}
