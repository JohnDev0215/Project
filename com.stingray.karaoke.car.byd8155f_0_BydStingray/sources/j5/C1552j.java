package j5;

import java.io.IOException;
import r5.C2297a;
import r5.C2298b;

/* JADX INFO: renamed from: j5.j, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1552j extends y {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f17017a;

    @Override // j5.y
    public final Object a(C2297a c2297a) throws IOException {
        switch (this.f17017a) {
            case 0:
                if (c2297a.Q() != 9) {
                    return Double.valueOf(c2297a.F());
                }
                c2297a.M();
                return null;
            case 1:
                if (c2297a.Q() != 9) {
                    return Float.valueOf((float) c2297a.F());
                }
                c2297a.M();
                return null;
            default:
                c2297a.W();
                return null;
        }
    }

    @Override // j5.y
    public final void b(C2298b c2298b, Object obj) throws IOException {
        switch (this.f17017a) {
            case 0:
                Number number = (Number) obj;
                if (number != null) {
                    double dDoubleValue = number.doubleValue();
                    m.a(dDoubleValue);
                    c2298b.G(dDoubleValue);
                } else {
                    c2298b.v();
                }
                break;
            case 1:
                Number numberValueOf = (Number) obj;
                if (numberValueOf != null) {
                    float fFloatValue = numberValueOf.floatValue();
                    m.a(fFloatValue);
                    if (!(numberValueOf instanceof Float)) {
                        numberValueOf = Float.valueOf(fFloatValue);
                    }
                    c2298b.K(numberValueOf);
                } else {
                    c2298b.v();
                }
                break;
            default:
                c2298b.v();
                break;
        }
    }

    public String toString() {
        switch (this.f17017a) {
            case 2:
                return "AnonymousOrNonStaticLocalClassAdapter";
            default:
                return super.toString();
        }
    }
}
