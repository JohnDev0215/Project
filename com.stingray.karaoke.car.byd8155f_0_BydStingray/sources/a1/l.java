package a1;

import b1.InterfaceC0988a;
import h2.AbstractC1347d;

/* JADX INFO: loaded from: classes.dex */
public final class l implements InterfaceC0988a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f11040a;

    public l(float f10) {
        this.f11040a = f10;
    }

    @Override // b1.InterfaceC0988a
    public final float a(float f10) {
        return f10 / this.f11040a;
    }

    @Override // b1.InterfaceC0988a
    public final float b(float f10) {
        return f10 * this.f11040a;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof l) && Float.compare(this.f11040a, ((l) obj).f11040a) == 0;
    }

    public final int hashCode() {
        return Float.hashCode(this.f11040a);
    }

    public final String toString() {
        return AbstractC1347d.h(new StringBuilder("LinearFontScaleConverter(fontScale="), this.f11040a, ')');
    }
}
