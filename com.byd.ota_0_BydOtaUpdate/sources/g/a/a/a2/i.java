package g.a.a.a2;

import g.a.a.l;
import g.a.a.q;
import g.a.a.r;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class i extends l implements g.a.a.d {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f3761c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final l f3762d;

    public i(j jVar) {
        this((g.a.a.e) jVar);
    }

    public i(g.a.a.e eVar) {
        if (!(eVar instanceof r) && !(eVar instanceof j)) {
            throw new IllegalArgumentException("Unknown check object in integrity check.");
        }
        this.f3761c = 0;
        this.f3762d = j.g(eVar);
    }

    public static i g(Object obj) {
        if (obj instanceof i) {
            return (i) obj;
        }
        if (obj instanceof byte[]) {
            try {
                return new i(q.j((byte[]) obj));
            } catch (IOException unused) {
                throw new IllegalArgumentException("Unable to parse integrity check details.");
            }
        }
        if (obj != null) {
            return new i((g.a.a.e) obj);
        }
        return null;
    }

    @Override // g.a.a.l, g.a.a.e
    public q c() {
        return this.f3762d.c();
    }

    public l h() {
        return this.f3762d;
    }

    public int i() {
        return this.f3761c;
    }
}
