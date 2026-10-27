package e2;

import android.net.Uri;
import android.os.Bundle;
import h2.AbstractC1343A;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class B {
    public static final B d = new B(new l3.P(22, false));

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final String f13851e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final String f13852f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final String f13853g;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Uri f13854a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f13855b;
    public final Bundle c;

    static {
        int i7 = AbstractC1343A.f15195a;
        f13851e = Integer.toString(0, 36);
        f13852f = Integer.toString(1, 36);
        f13853g = Integer.toString(2, 36);
    }

    public B(l3.P p10) {
        this.f13854a = (Uri) p10.f18537m;
        this.f13855b = (String) p10.f18538n;
        this.c = (Bundle) p10.o;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof B)) {
            return false;
        }
        B b10 = (B) obj;
        Uri uri = b10.f13854a;
        int i7 = AbstractC1343A.f15195a;
        if (Objects.equals(this.f13854a, uri) && Objects.equals(this.f13855b, b10.f13855b)) {
            if ((this.c == null) == (b10.c == null)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        Uri uri = this.f13854a;
        int iHashCode = (uri == null ? 0 : uri.hashCode()) * 31;
        String str = this.f13855b;
        return ((iHashCode + (str == null ? 0 : str.hashCode())) * 31) + (this.c != null ? 1 : 0);
    }
}
