package d0;

import T.F;
import e0.C1178r;
import java.util.Map;
import n3.C1945j;
import o3.C2042o;
import w.C2579j;

/* JADX INFO: loaded from: classes.dex */
public final class g implements F {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f13266a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f13267b;
    public final /* synthetic */ Object c;
    public final /* synthetic */ Object d;

    public /* synthetic */ g(Object obj, Object obj2, Object obj3, int i7) {
        this.f13266a = i7;
        this.f13267b = obj;
        this.c = obj2;
        this.d = obj3;
    }

    @Override // T.F
    public final void a() {
        switch (this.f13266a) {
            case 0:
                h hVar = (h) this.c;
                Map map = hVar.f13268a;
                f fVar = (f) this.f13267b;
                if (fVar.f13265b) {
                    Map mapD = fVar.c.d();
                    boolean zIsEmpty = mapD.isEmpty();
                    Object obj = fVar.f13264a;
                    if (zIsEmpty) {
                        map.remove(obj);
                    } else {
                        map.put(obj, mapD);
                    }
                }
                hVar.f13269b.remove(this.d);
                break;
            case 1:
                C2042o c2042o = (C2042o) this.f13267b;
                C1945j c1945j = (C1945j) this.c;
                c2042o.b().b(c1945j);
                ((C1178r) this.d).remove(c1945j);
                break;
            default:
                C1178r c1178r = (C1178r) this.f13267b;
                Object obj2 = this.d;
                c1178r.remove(obj2);
                ((C2579j) this.c).d.g(obj2);
                break;
        }
    }

    public g(C1178r c1178r, Object obj, C2579j c2579j) {
        this.f13266a = 2;
        this.f13267b = c1178r;
        this.d = obj;
        this.c = c2579j;
    }
}
