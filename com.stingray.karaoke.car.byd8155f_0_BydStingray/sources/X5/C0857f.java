package X5;

import com.stingray.shared.logs.segment.ActionOwner;
import h2.AbstractC1347d;

/* JADX INFO: renamed from: X5.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0857f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f10236a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f10237b;
    public final float c;
    public final float d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final ActionOwner f10238e;

    public C0857f(long j3, String str, float f10, float f11, ActionOwner actionOwner) {
        A7.m.f(str, "countdownString");
        A7.m.f(actionOwner, "actionOwner");
        this.f10236a = j3;
        this.f10237b = str;
        this.c = f10;
        this.d = f11;
        this.f10238e = actionOwner;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C0857f)) {
            return false;
        }
        C0857f c0857f = (C0857f) obj;
        return this.f10236a == c0857f.f10236a && A7.m.a(this.f10237b, c0857f.f10237b) && Float.compare(this.c, c0857f.c) == 0 && Float.compare(this.d, c0857f.d) == 0 && this.f10238e == c0857f.f10238e;
    }

    public final int hashCode() {
        return this.f10238e.hashCode() + AbstractC1347d.b(AbstractC1347d.b(M5.b.c(this.f10237b, Long.hashCode(this.f10236a) * 31, 31), 31, this.c), 31, this.d);
    }

    public final String toString() {
        return "CountdownData(timeLeftMs=" + this.f10236a + ", countdownString=" + this.f10237b + ", scale=" + this.c + ", numberAlpha=" + this.d + ", actionOwner=" + this.f10238e + ")";
    }
}
