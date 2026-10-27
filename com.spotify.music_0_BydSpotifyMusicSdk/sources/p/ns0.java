package p;

/* JADX INFO: loaded from: classes.dex */
public final class ns0 {
    public final int a;

    public ns0(int i) {
        this.a = i;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ns0) && this.a == ((ns0) obj).a;
    }

    public final int hashCode() {
        return Integer.hashCode(this.a);
    }

    public final String toString() {
        return u4.k(new StringBuilder("OnUserLibraryEpisodePage(totalCount="), this.a, ')');
    }
}
