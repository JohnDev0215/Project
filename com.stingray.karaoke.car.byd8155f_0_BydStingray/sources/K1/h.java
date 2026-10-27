package k1;

/* JADX INFO: loaded from: classes.dex */
public final class h extends Exception {
    public final String l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final String f17624m;

    public h(String str, AbstractC1598c abstractC1598c) {
        super(str);
        this.l = str;
        if (abstractC1598c != null) {
            this.f17624m = abstractC1598c.q();
        } else {
            this.f17624m = "unknown";
        }
    }

    @Override // java.lang.Throwable
    public final String toString() {
        StringBuilder sb = new StringBuilder("CLParsingException (");
        sb.append(hashCode());
        sb.append(") : ");
        sb.append(this.l + " (" + this.f17624m + " at line 0)");
        return sb.toString();
    }
}
