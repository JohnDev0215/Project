package b1;

import android.text.TextUtils;

/* JADX INFO: compiled from: MediaSessionManagerImplBase.java */
/* JADX INFO: loaded from: classes.dex */
public class k implements i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f2041a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f2042b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f2043c;

    public k(String str, int i8, int i9) {
        this.f2041a = str;
        this.f2042b = i8;
        this.f2043c = i9;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof k)) {
            return false;
        }
        k kVar = (k) obj;
        return TextUtils.equals(this.f2041a, kVar.f2041a) && this.f2042b == kVar.f2042b && this.f2043c == kVar.f2043c;
    }

    public int hashCode() {
        return n0.c.b(this.f2041a, Integer.valueOf(this.f2042b), Integer.valueOf(this.f2043c));
    }
}
