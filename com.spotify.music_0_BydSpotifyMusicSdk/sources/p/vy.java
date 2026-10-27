package p;

import android.net.Uri;

/* JADX INFO: loaded from: classes.dex */
public final class vy {
    public final Uri a;
    public final boolean b;

    public vy(Uri uri, boolean z) {
        this.a = uri;
        this.b = z;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!v03.b(vy.class, obj != null ? obj.getClass() : null)) {
            return false;
        }
        v03.g(obj, "null cannot be cast to non-null type androidx.work.Constraints.ContentUriTrigger");
        vy vyVar = (vy) obj;
        return v03.b(this.a, vyVar.a) && this.b == vyVar.b;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.b) + (this.a.hashCode() * 31);
    }
}
