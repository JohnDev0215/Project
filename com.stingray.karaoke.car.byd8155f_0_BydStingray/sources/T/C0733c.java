package T;

/* JADX INFO: renamed from: T.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0733c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f8235a;

    public C0733c(int i7) {
        this.f8235a = i7;
    }

    public final boolean a() {
        return this.f8235a != Integer.MIN_VALUE;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("{ location = ");
        return M5.b.h(sb, this.f8235a, " }");
    }
}
