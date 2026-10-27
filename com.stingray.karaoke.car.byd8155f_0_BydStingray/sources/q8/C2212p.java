package q8;

/* JADX INFO: renamed from: q8.p, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2212p extends r {
    public C2212p() {
        super("HTML", 1);
    }

    @Override // q8.r
    public final String a(String str) {
        A7.m.f(str, "string");
        return Q8.o.x0(Q8.o.x0(str, "<", "&lt;"), ">", "&gt;");
    }
}
