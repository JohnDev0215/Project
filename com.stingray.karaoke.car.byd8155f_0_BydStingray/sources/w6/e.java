package w6;

import A7.m;
import android.graphics.Paint;

/* JADX INFO: loaded from: classes.dex */
public final class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Paint f23342a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Paint f23343b;
    public final float c;

    public e(Paint paint, Paint paint2, float f10) {
        this.f23342a = paint;
        this.f23343b = paint2;
        this.c = f10;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof e)) {
            return false;
        }
        e eVar = (e) obj;
        return m.a(this.f23342a, eVar.f23342a) && m.a(this.f23343b, eVar.f23343b) && Float.compare(this.c, eVar.c) == 0;
    }

    public final int hashCode() {
        return Float.hashCode(this.c) + ((this.f23343b.hashCode() + (this.f23342a.hashCode() * 31)) * 31);
    }

    public final String toString() {
        return "SingerPaint(pastPaint=" + this.f23342a + ", futurePaint=" + this.f23343b + ", maxTextSize=" + this.c + ")";
    }
}
