package p2;

import d5.L;
import d5.M;
import d5.s0;
import h2.AbstractC1343A;
import java.util.Objects;
import java.util.Set;

/* JADX INFO: renamed from: p2.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2086a {
    public static final C2086a d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f20693a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f20694b;
    public final M c;

    static {
        C2086a c2086a;
        if (AbstractC1343A.f15195a >= 33) {
            L l = new L(4);
            for (int i7 = 1; i7 <= 10; i7++) {
                l.a(Integer.valueOf(AbstractC1343A.p(i7)));
            }
            c2086a = new C2086a(2, l.h());
        } else {
            c2086a = new C2086a(2, 10);
        }
        d = c2086a;
    }

    public C2086a(int i7, Set set) {
        this.f20693a = i7;
        M mV = M.v(set);
        this.c = mV;
        s0 it = mV.iterator();
        int iMax = 0;
        while (it.hasNext()) {
            iMax = Math.max(iMax, Integer.bitCount(((Integer) it.next()).intValue()));
        }
        this.f20694b = iMax;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C2086a)) {
            return false;
        }
        C2086a c2086a = (C2086a) obj;
        if (this.f20693a == c2086a.f20693a && this.f20694b == c2086a.f20694b) {
            int i7 = AbstractC1343A.f15195a;
            if (Objects.equals(this.c, c2086a.c)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int i7 = ((this.f20693a * 31) + this.f20694b) * 31;
        M m10 = this.c;
        return i7 + (m10 == null ? 0 : m10.hashCode());
    }

    public final String toString() {
        return "AudioProfile[format=" + this.f20693a + ", maxChannelCount=" + this.f20694b + ", channelMasks=" + this.c + "]";
    }

    public C2086a(int i7, int i10) {
        this.f20693a = i7;
        this.f20694b = i10;
        this.c = null;
    }
}
