package w6;

import A7.m;
import android.graphics.Paint;

/* JADX INFO: renamed from: w6.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2604a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Paint f23338a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Paint f23339b;

    public C2604a(Paint paint, Paint paint2) {
        this.f23338a = paint;
        this.f23339b = paint2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C2604a)) {
            return false;
        }
        C2604a c2604a = (C2604a) obj;
        return m.a(this.f23338a, c2604a.f23338a) && m.a(this.f23339b, c2604a.f23339b);
    }

    public final int hashCode() {
        return this.f23339b.hashCode() + (this.f23338a.hashCode() * 31);
    }

    public final String toString() {
        return "BreakPaint(background=" + this.f23338a + ", progress=" + this.f23339b + ")";
    }
}
