package b.b.a.r.i;

import b.b.a.o;
import b.b.a.r.i.i;
import java.io.IOException;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

/* JADX INFO: compiled from: TypeAdapterRuntimeTypeWrapper.java */
/* JADX INFO: loaded from: classes2.dex */
public final class m<T> extends o<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final b.b.a.d f471a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final o<T> f472b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Type f473c;

    public m(b.b.a.d dVar, o<T> oVar, Type type) {
        this.f471a = dVar;
        this.f472b = oVar;
        this.f473c = type;
    }

    @Override // b.b.a.o
    public T b(b.b.a.t.a aVar) throws IOException {
        return this.f472b.b(aVar);
    }

    @Override // b.b.a.o
    public void d(b.b.a.t.b bVar, T t) throws IOException {
        o<T> oVarK = this.f472b;
        Type typeE = e(this.f473c, t);
        if (typeE != this.f473c) {
            oVarK = this.f471a.k(b.b.a.s.a.b(typeE));
            if (oVarK instanceof i.b) {
                o<T> oVar = this.f472b;
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
