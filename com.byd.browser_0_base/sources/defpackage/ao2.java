package defpackage;

import org.chromium.url.GURL;

/* JADX INFO: compiled from: SiteSuggestion.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ao2 {
    public final String a;
    public final GURL b;
    public final int c;
    public final int d;
    public final int e;

    public ao2(int i, int i2, int i3, String str, GURL gurl) {
        this.a = str;
        this.b = gurl;
        this.d = i2;
        this.c = i;
        this.e = i3;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || ao2.class != obj.getClass()) {
            return false;
        }
        ao2 ao2Var = (ao2) obj;
        if (this.d == ao2Var.d && this.c == ao2Var.c && this.e == ao2Var.e && this.a.equals(ao2Var.a)) {
            return this.b.equals(ao2Var.b);
        }
        return false;
    }

    public final int hashCode() {
        return ((((((this.b.hashCode() + (this.a.hashCode() * 31)) * 31) + this.d) * 31) + this.e) * 31) + this.c;
    }
}
