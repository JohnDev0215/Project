package b0;

import A7.m;
import g1.AbstractC1274a;

/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f12193a;

    public c(int i7) {
        this.f12193a = i7;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("IntRef(element = ");
        sb.append(this.f12193a);
        sb.append(")@");
        int iHashCode = hashCode();
        AbstractC1274a.y(16);
        String string = Integer.toString(iHashCode, 16);
        m.e(string, "toString(this, checkRadix(radix))");
        sb.append(string);
        return sb.toString();
    }
}
