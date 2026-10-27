package p;

/* JADX INFO: loaded from: classes.dex */
public final class ur0 {
    public final int a;

    public ur0(int i) {
        this.a = i;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ur0) && this.a == ((ur0) obj).a;
    }

    public final int hashCode() {
        return Integer.hashCode(this.a);
    }

    public final String toString() {
        return u4.k(new StringBuilder("OnUserLibraryTrackPage(totalCount="), this.a, ')');
    }
}
