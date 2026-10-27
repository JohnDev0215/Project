package j5;

/* JADX INFO: loaded from: classes.dex */
public final class r extends o {
    public final l5.n l = new l5.n(false);

    public final boolean equals(Object obj) {
        return obj == this || ((obj instanceof r) && ((r) obj).l.equals(this.l));
    }

    public final int hashCode() {
        return this.l.hashCode();
    }
}
