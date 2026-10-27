package I3;

import A7.m;
import android.graphics.Bitmap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Bitmap f2932a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Map f2933b;

    public b(Bitmap bitmap, Map map) {
        this.f2932a = bitmap;
        this.f2933b = map;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof b) {
            b bVar = (b) obj;
            if (m.a(this.f2932a, bVar.f2932a) && m.a(this.f2933b, bVar.f2933b)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return this.f2933b.hashCode() + (this.f2932a.hashCode() * 31);
    }

    public final String toString() {
        return "Value(bitmap=" + this.f2932a + ", extras=" + this.f2933b + ')';
    }
}
