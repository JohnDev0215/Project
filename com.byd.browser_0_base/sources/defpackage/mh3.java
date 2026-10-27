package defpackage;

import java.util.Objects;

/* JADX INFO: compiled from: TypefaceRequest.java */
/* JADX INFO: loaded from: classes.dex */
public final class mh3 {
    public final String a;
    public final int b;

    public mh3(String str, int i) {
        this.a = str;
        this.b = i;
    }

    public final String a() {
        String str = this.a;
        int iLastIndexOf = str.lastIndexOf(" Condensed");
        int i = this.b;
        return iLastIndexOf != -1 ? String.format(null, "name=%s&weight=%d&width=%d", str.substring(0, iLastIndexOf), Integer.valueOf(i), 75) : String.format(null, "name=%s&weight=%d", str, Integer.valueOf(i));
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof mh3) {
            return a().equalsIgnoreCase(((mh3) obj).a());
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hash(a());
    }
}
