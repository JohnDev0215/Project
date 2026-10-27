package b.q;

import android.text.TextUtils;

/* JADX INFO: compiled from: MediaSessionManagerImplBase.java */
/* JADX INFO: loaded from: classes.dex */
public class k implements i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f3012a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f3013b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f3014c;

    public k(String str, int i2, int i3) {
        this.f3012a = str;
        this.f3013b = i2;
        this.f3014c = i3;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof k)) {
            return false;
        }
        k kVar = (k) obj;
        return TextUtils.equals(this.f3012a, kVar.f3012a) && this.f3013b == kVar.f3013b && this.f3014c == kVar.f3014c;
    }

    public int hashCode() {
        return b.h.l.b.b(this.f3012a, Integer.valueOf(this.f3013b), Integer.valueOf(this.f3014c));
    }
}
