package t0;

import A7.m;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import l3.C1764u;
import x1.AbstractC2663b;

/* JADX INFO: renamed from: t0.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2423a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final XmlResourceParser f22425a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f22426b = 0;
    public final C1764u c;

    public C2423a(XmlResourceParser xmlResourceParser) {
        this.f22425a = xmlResourceParser;
        C1764u c1764u = new C1764u(21, false);
        c1764u.f18608m = new float[64];
        this.c = c1764u;
    }

    public final float a(TypedArray typedArray, String str, int i7, float f10) {
        if (AbstractC2663b.c(this.f22425a, str)) {
            f10 = typedArray.getFloat(i7, f10);
        }
        b(typedArray.getChangingConfigurations());
        return f10;
    }

    public final void b(int i7) {
        this.f22426b = i7 | this.f22426b;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C2423a)) {
            return false;
        }
        C2423a c2423a = (C2423a) obj;
        return m.a(this.f22425a, c2423a.f22425a) && this.f22426b == c2423a.f22426b;
    }

    public final int hashCode() {
        return Integer.hashCode(this.f22426b) + (this.f22425a.hashCode() * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("AndroidVectorParser(xmlParser=");
        sb.append(this.f22425a);
        sb.append(", config=");
        return android.hardware.bydauto.ac.tsimpl.a.j(sb, this.f22426b, ')');
    }
}
