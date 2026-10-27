package d0;

import S0.p;
import T.R0;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f13264a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f13265b = true;
    public final l c;

    public f(h hVar, Object obj) {
        this.f13264a = obj;
        Map map = (Map) hVar.f13268a.get(obj);
        p pVar = new p(hVar, 11);
        R0 r02 = m.f13274a;
        this.c = new l(map, pVar);
    }
}
