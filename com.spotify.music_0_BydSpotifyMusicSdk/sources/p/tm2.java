package p;

/* JADX INFO: loaded from: classes.dex */
public final class tm2 {
    public final String a;

    public tm2(String str) {
        this.a = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof tm2) && v03.b(this.a, ((tm2) obj).a);
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public final String toString() {
        return hf2.d(new StringBuilder("Profile(name="), this.a, ')');
    }
}
