package B3;

import android.graphics.drawable.BitmapDrawable;

/* JADX INFO: loaded from: classes.dex */
public final class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final BitmapDrawable f627a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f628b;

    public g(BitmapDrawable bitmapDrawable, boolean z6) {
        this.f627a = bitmapDrawable;
        this.f628b = z6;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof g) {
            g gVar = (g) obj;
            if (this.f627a.equals(gVar.f627a) && this.f628b == gVar.f628b) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.f628b) + (this.f627a.hashCode() * 31);
    }
}
