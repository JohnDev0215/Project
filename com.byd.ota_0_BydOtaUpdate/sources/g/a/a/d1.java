package g.a.a;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class d1 implements s {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public v f3792c;

    public d1(v vVar) {
        this.f3792c = vVar;
    }

    @Override // g.a.a.s1
    public q a() throws IOException {
        return new c1(this.f3792c.d());
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
