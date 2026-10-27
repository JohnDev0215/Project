package Y;

import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes.dex */
public final class b extends a implements B7.d {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final O8.h f10585n;
    public Object o;

    public b(O8.h hVar, Object obj, Object obj2) {
        super(obj, obj2);
        this.f10585n = hVar;
        this.o = obj2;
    }

    @Override // Y.a, java.util.Map.Entry
    public final Object getValue() {
        return this.o;
    }

    @Override // Y.a, java.util.Map.Entry
    public final Object setValue(Object obj) {
        Object obj2 = this.o;
        this.o = obj;
        f fVar = (f) this.f10585n.f5193m;
        e eVar = fVar.o;
        Object obj3 = this.l;
        if (eVar.containsKey(obj3)) {
            boolean z6 = fVar.f10589n;
            if (!z6) {
                eVar.put(obj3, obj);
            } else {
                if (!z6) {
                    throw new NoSuchElementException();
                }
                n nVar = fVar.l[fVar.f10588m];
                Object obj4 = nVar.l[nVar.f10604n];
                eVar.put(obj3, obj);
                fVar.d(obj4 != null ? obj4.hashCode() : 0, eVar.f10591n, obj4, 0);
            }
            fVar.f10596r = eVar.f10592p;
        }
        return obj2;
    }
}
