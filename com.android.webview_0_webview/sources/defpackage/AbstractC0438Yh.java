package defpackage;

import java.util.Set;

/* JADX INFO: renamed from: Yh, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0438Yh extends AbstractC0330Sh implements Set {
    public transient AbstractC0366Uh t;

    public static int j(int i) {
        int iMax = Math.max(i, 2);
        if (iMax >= 751619276) {
            if (iMax < 1073741824) {
                return 1073741824;
            }
            throw new IllegalArgumentException("collection too large");
        }
        int iHighestOneBit = Integer.highestOneBit(iMax - 1) << 1;
        while (((double) iHighestOneBit) * 0.7d < iMax) {
            iHighestOneBit <<= 1;
        }
        return iHighestOneBit;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        boolean z = obj instanceof AbstractC0438Yh;
        if (this == obj) {
            return true;
        }
        if (obj instanceof Set) {
            Set set = (Set) obj;
            try {
                if (size() == set.size()) {
                    if (containsAll(set)) {
                        return true;
                    }
                }
            } catch (ClassCastException | NullPointerException unused) {
            }
        }
        return false;
    }

    @Override // java.util.Collection, java.util.Set
    public int hashCode() {
        return AbstractC0784fi.a(this);
    }

    public AbstractC0366Uh i() {
        AbstractC0366Uh abstractC0366Uh = this.t;
        if (abstractC0366Uh != null) {
            return abstractC0366Uh;
        }
        C0561bi c0561bi = new C0561bi((C0617ci) this);
        this.t = c0561bi;
        return c0561bi;
    }
}
