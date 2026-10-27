package b1;

import A7.m;
import Y4.e;
import java.util.Arrays;

/* JADX INFO: renamed from: b1.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0990c implements InterfaceC0988a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float[] f12200a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float[] f12201b;

    public C0990c(float[] fArr, float[] fArr2) {
        if (fArr.length != fArr2.length || fArr.length == 0) {
            throw new IllegalArgumentException("Array lengths must match and be nonzero");
        }
        this.f12200a = fArr;
        this.f12201b = fArr2;
    }

    @Override // b1.InterfaceC0988a
    public final float a(float f10) {
        return e.B(f10, this.f12201b, this.f12200a);
    }

    @Override // b1.InterfaceC0988a
    public final float b(float f10) {
        return e.B(f10, this.f12200a, this.f12201b);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof C0990c)) {
            return false;
        }
        C0990c c0990c = (C0990c) obj;
        return Arrays.equals(this.f12200a, c0990c.f12200a) && Arrays.equals(this.f12201b, c0990c.f12201b);
    }

    public final int hashCode() {
        return Arrays.hashCode(this.f12201b) + (Arrays.hashCode(this.f12200a) * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("FontScaleConverter{fromSpValues=");
        String string = Arrays.toString(this.f12200a);
        m.e(string, "toString(this)");
        sb.append(string);
        sb.append(", toDpValues=");
        String string2 = Arrays.toString(this.f12201b);
        m.e(string2, "toString(this)");
        sb.append(string2);
        sb.append('}');
        return sb.toString();
    }
}
