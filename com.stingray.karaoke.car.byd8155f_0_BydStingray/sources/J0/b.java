package J0;

import A7.m;
import android.content.res.Resources;

/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Resources.Theme f3044a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f3045b;

    public b(Resources.Theme theme, int i7) {
        this.f3044a = theme;
        this.f3045b = i7;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        return m.a(this.f3044a, bVar.f3044a) && this.f3045b == bVar.f3045b;
    }

    public final int hashCode() {
        return Integer.hashCode(this.f3045b) + (this.f3044a.hashCode() * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Key(theme=");
        sb.append(this.f3044a);
        sb.append(", id=");
        return android.hardware.bydauto.ac.tsimpl.a.j(sb, this.f3045b, ')');
    }
}
