package defpackage;

import android.text.TextUtils;

/* JADX INFO: compiled from: ContentId.java */
/* JADX INFO: loaded from: classes3.dex */
public final class ny {
    public String a;
    public String b;

    public ny() {
    }

    public ny(String str, String str2) {
        this.a = str == null ? "" : str;
        this.b = str2 == null ? "" : str2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ny)) {
            return false;
        }
        ny nyVar = (ny) obj;
        return TextUtils.equals(this.a, nyVar.a) && TextUtils.equals(this.b, nyVar.b);
    }

    public final int hashCode() {
        String str = this.a;
        int iHashCode = (1891 + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.b;
        return iHashCode + (str2 != null ? str2.hashCode() : 0);
    }

    public final String toString() {
        return C0175b.k(this.a, "_", this.b);
    }
}
