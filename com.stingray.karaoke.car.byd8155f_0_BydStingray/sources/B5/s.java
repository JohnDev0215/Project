package B5;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import l3.P;

/* JADX INFO: loaded from: classes.dex */
public final class s extends H7.H {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ Map f733e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ C5.d f734f;

    public s(Map map, C5.d dVar) {
        this.f733e = map;
        this.f734f = dVar;
    }

    @Override // H7.H
    public final void N(String str, C5.g gVar, y yVar) {
        List listEmptyList = (List) this.f733e.get(str);
        if (listEmptyList == null) {
            listEmptyList = Collections.emptyList();
        }
        P p10 = new P(str, gVar, yVar, 2);
        C5.d dVar = this.f734f;
        new u(0, dVar, listEmptyList, p10).r(dVar);
    }

    public final String toString() {
        return this.f734f.toString();
    }
}
