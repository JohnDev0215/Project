package b.c.a.r.i;

import b.c.a.o;
import b.c.a.r.i.i;
import java.io.IOException;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

/* JADX INFO: compiled from: TypeAdapterRuntimeTypeWrapper.java */
/* JADX INFO: loaded from: classes3.dex */
public final class m<T> extends o<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final b.c.a.d f2292a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final o<T> f2293b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Type f2294c;

    public m(b.c.a.d dVar, o<T> oVar, Type type) {
        this.f2292a = dVar;
        this.f2293b = oVar;
        this.f2294c = type;
    }

    @Override // b.c.a.o
    public T b(b.c.a.t.a aVar) throws IOException {
        return this.f2293b.b(aVar);
    }

    @Override // b.c.a.o
    public void d(b.c.a.t.b bVar, T t) throws IOException {
        o<T> oVarK = this.f2293b;
        Type typeE = e(this.f2294c, t);
        if (typeE != this.f2294c) {
            oVarK = this.f2292a.k(b.c.a.s.a.b(typeE));
            if (oVarK instanceof i.b) {
                o<T> oVar = this.f2293b;
                if (!(oVar instanceof i.b)) {
                    oVarK = oVar;
                }
            }
        }
        oVarK.d(bVar, t);
    }

    public final Type e(Type type, Object obj) {
        return obj != null ? (type == Object.class || (type instanceof TypeVariable) || (type instanceof Class)) ? obj.getClass() : type : type;
    }
}
