package C5;

import java.util.Date;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class e extends a {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public Map f1005h;

    @Override // C5.a
    public final d b(String str, Date date, Map map, Map map2, String str2, String str3, boolean z6) {
        if (D5.g.g(str2) && D5.g.h(this.f1005h)) {
            throw new NullPointerException("either userId or traits are required");
        }
        Map map3 = this.f1005h;
        f fVar = new f(c.l, str, date, map, map2, str2, str3, z6);
        fVar.f(map3, "traits");
        return fVar;
    }
}
