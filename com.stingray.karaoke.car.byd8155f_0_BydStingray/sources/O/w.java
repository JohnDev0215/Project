package O;

import h2.AbstractC1347d;
import x.AbstractC2641j;

/* JADX INFO: loaded from: classes.dex */
public final class w {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final K.O f5000a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f5001b;
    public final int c;
    public final boolean d;

    public w(K.O o, long j3, int i7, boolean z6) {
        this.f5000a = o;
        this.f5001b = j3;
        this.c = i7;
        this.d = z6;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof w)) {
            return false;
        }
        w wVar = (w) obj;
        return this.f5000a == wVar.f5000a && m0.c.b(this.f5001b, wVar.f5001b) && this.c == wVar.c && this.d == wVar.d;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.d) + ((AbstractC2641j.c(this.c) + AbstractC1347d.c(this.f5000a.hashCode() * 31, 31, this.f5001b)) * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("SelectionHandleInfo(handle=");
        sb.append(this.f5000a);
        sb.append(", position=");
        sb.append((Object) m0.c.j(this.f5001b));
        sb.append(", anchor=");
        int i7 = this.c;
        sb.append(i7 != 1 ? i7 != 2 ? i7 != 3 ? "null" : "Right" : "Middle" : "Left");
        sb.append(", visible=");
        return AbstractC1347d.i(sb, this.d, ')');
    }
}
