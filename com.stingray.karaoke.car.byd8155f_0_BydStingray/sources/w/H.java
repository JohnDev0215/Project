package w;

import x.InterfaceC2615D;

/* JADX INFO: loaded from: classes.dex */
public final class H {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f23070a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final InterfaceC2615D f23071b;

    public H(float f10, InterfaceC2615D interfaceC2615D) {
        this.f23070a = f10;
        this.f23071b = interfaceC2615D;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof H)) {
            return false;
        }
        H h10 = (H) obj;
        return Float.compare(this.f23070a, h10.f23070a) == 0 && A7.m.a(this.f23071b, h10.f23071b);
    }

    public final int hashCode() {
        return this.f23071b.hashCode() + (Float.hashCode(this.f23070a) * 31);
    }

    public final String toString() {
        return "Fade(alpha=" + this.f23070a + ", animationSpec=" + this.f23071b + ')';
    }
}
