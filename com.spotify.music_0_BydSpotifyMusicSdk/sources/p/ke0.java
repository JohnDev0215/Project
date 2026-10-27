package p;

/* JADX INFO: loaded from: classes.dex */
public final class ke0 {
    public final String a;

    public ke0(String str) {
        this.a = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ke0) && v03.b(this.a, ((ke0) obj).a);
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public final String toString() {
        return hf2.d(new StringBuilder("OnPodcast(name="), this.a, ')');
    }
}
