package c.b.b.r.i;

import c.b.b.o;
import c.b.b.r.i.i;
import java.io.IOException;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

/* JADX INFO: compiled from: TypeAdapterRuntimeTypeWrapper.java */
/* JADX INFO: loaded from: classes.dex */
public final class m<T> extends o<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final c.b.b.d f4268a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final o<T> f4269b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Type f4270c;

    public m(c.b.b.d dVar, o<T> oVar, Type type) {
        this.f4268a = dVar;
        this.f4269b = oVar;
        this.f4270c = type;
    }

    @Override // c.b.b.o
    public T b(c.b.b.t.a aVar) throws IOException {
        return this.f4269b.b(aVar);
    }

    @Override // c.b.b.o
    public void d(c.b.b.t.b bVar, T t) throws IOException {
        o<T> oVarK = this.f4269b;
        Type typeE = e(this.f4270c, t);
        if (typeE != this.f4270c) {
            oVarK = this.f4268a.k(c.b.b.s.a.b(typeE));
            if (oVarK instanceof i.b) {
                o<T> oVar = this.f4269b;
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
