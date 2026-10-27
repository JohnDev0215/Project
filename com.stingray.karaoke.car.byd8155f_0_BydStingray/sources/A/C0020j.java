package A;

import S8.AbstractC0724v;
import S8.C0712i;
import g1.AbstractC1274a;

/* JADX INFO: renamed from: A.j, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0020j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final H.e f187a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final C0712i f188b;

    public C0020j(H.e eVar, C0712i c0712i) {
        this.f187a = eVar;
        this.f188b = c0712i;
    }

    public final String toString() {
        C0712i c0712i = this.f188b;
        if (c0712i.f8118p.F(AbstractC0724v.f8139m) != null) {
            throw new ClassCastException();
        }
        StringBuilder sb = new StringBuilder("Request@");
        int iHashCode = hashCode();
        AbstractC1274a.y(16);
        String string = Integer.toString(iHashCode, 16);
        A7.m.e(string, "toString(this, checkRadix(radix))");
        sb.append(string);
        sb.append("(currentBounds()=");
        sb.append(this.f187a.a());
        sb.append(", continuation=");
        sb.append(c0712i);
        sb.append(')');
        return sb.toString();
    }
}
