package e2;

import android.net.Uri;
import h2.AbstractC1343A;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class E {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final String f13861h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final String f13862i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final String f13863j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final String f13864k;
    public static final String l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final String f13865m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final String f13866n;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Uri f13867a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f13868b;
    public final String c;
    public final int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f13869e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final String f13870f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final String f13871g;

    static {
        int i7 = AbstractC1343A.f15195a;
        f13861h = Integer.toString(0, 36);
        f13862i = Integer.toString(1, 36);
        f13863j = Integer.toString(2, 36);
        f13864k = Integer.toString(3, 36);
        l = Integer.toString(4, 36);
        f13865m = Integer.toString(5, 36);
        f13866n = Integer.toString(6, 36);
    }

    public E(D d) {
        this.f13867a = d.f13856a;
        this.f13868b = d.f13857b;
        this.c = d.c;
        this.d = d.d;
        this.f13869e = d.f13858e;
        this.f13870f = d.f13859f;
        this.f13871g = d.f13860g;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof E)) {
            return false;
        }
        E e10 = (E) obj;
        if (this.f13867a.equals(e10.f13867a)) {
            int i7 = AbstractC1343A.f15195a;
            if (Objects.equals(this.f13868b, e10.f13868b) && Objects.equals(this.c, e10.c) && this.d == e10.d && this.f13869e == e10.f13869e && Objects.equals(this.f13870f, e10.f13870f) && Objects.equals(this.f13871g, e10.f13871g)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int iHashCode = this.f13867a.hashCode() * 31;
        String str = this.f13868b;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.c;
        int iHashCode3 = (((((iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31) + this.d) * 31) + this.f13869e) * 31;
        String str3 = this.f13870f;
        int iHashCode4 = (iHashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.f13871g;
        return iHashCode4 + (str4 != null ? str4.hashCode() : 0);
    }
}
