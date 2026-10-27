package g.a.a;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class g0 implements s {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public v f3844c;

    public g0(v vVar) {
        this.f3844c = vVar;
    }

    @Override // g.a.a.s1
    public q a() throws IOException {
        return new f0(this.f3844c.d());
    }

    @Override // g.a.a.e
    public q c() {
        try {
            return a();
        } catch (IOException e2) {
            throw new IllegalStateException(e2.getMessage());
        }
    }
}
