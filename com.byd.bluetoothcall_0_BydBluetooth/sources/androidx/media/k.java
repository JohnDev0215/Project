package androidx.media;

import android.text.TextUtils;

/* JADX INFO: compiled from: MediaSessionManagerImplBase.java */
/* JADX INFO: loaded from: classes.dex */
class k implements i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f1597a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f1598b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f1599c;

    k(String str, int i, int i2) {
        this.f1597a = str;
        this.f1598b = i;
        this.f1599c = i2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof k)) {
            return false;
        }
        k kVar = (k) obj;
        return TextUtils.equals(this.f1597a, kVar.f1597a) && this.f1598b == kVar.f1598b && this.f1599c == kVar.f1599c;
    }

    public int hashCode() {
        return b.g.j.c.b(this.f1597a, Integer.valueOf(this.f1598b), Integer.valueOf(this.f1599c));
    }
}
