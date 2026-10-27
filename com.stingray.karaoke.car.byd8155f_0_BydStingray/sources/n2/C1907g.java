package n2;

import android.text.TextUtils;
import e2.C1208p;
import h2.AbstractC1346c;

/* JADX INFO: renamed from: n2.g, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1907g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f19658a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final C1208p f19659b;
    public final C1208p c;
    public final int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f19660e;

    public C1907g(String str, C1208p c1208p, C1208p c1208p2, int i7, int i10) {
        AbstractC1346c.c(i7 == 0 || i10 == 0);
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException();
        }
        this.f19658a = str;
        c1208p.getClass();
        this.f19659b = c1208p;
        c1208p2.getClass();
        this.c = c1208p2;
        this.d = i7;
        this.f19660e = i10;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || C1907g.class != obj.getClass()) {
            return false;
        }
        C1907g c1907g = (C1907g) obj;
        return this.d == c1907g.d && this.f19660e == c1907g.f19660e && this.f19658a.equals(c1907g.f19658a) && this.f19659b.equals(c1907g.f19659b) && this.c.equals(c1907g.c);
    }

    public final int hashCode() {
        return this.c.hashCode() + ((this.f19659b.hashCode() + M5.b.c(this.f19658a, (((527 + this.d) * 31) + this.f19660e) * 31, 31)) * 31);
    }
}
