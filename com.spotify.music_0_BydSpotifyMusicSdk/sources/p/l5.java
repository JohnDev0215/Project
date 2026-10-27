package p;

/* JADX INFO: loaded from: classes.dex */
public final class l5 {
    public final String a;

    public l5(String str) {
        this.a = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof l5) && v03.b(this.a, ((l5) obj).a);
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public final String toString() {
        return hf2.d(new StringBuilder("Profile(name="), this.a, ')');
    }
}
