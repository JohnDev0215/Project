package p;

/* JADX INFO: loaded from: classes.dex */
public final class pr {
    public final ir a;

    public pr(ir irVar) {
        this.a = irVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof pr) && v03.b(this.a, ((pr) obj).a);
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public final String toString() {
        return "ChapterWrapper(data=" + this.a + ')';
    }
}
