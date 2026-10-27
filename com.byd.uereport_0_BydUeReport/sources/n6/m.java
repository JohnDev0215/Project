package n6;

import java.io.IOException;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import k6.o;
import n6.i;

/* JADX INFO: compiled from: TypeAdapterRuntimeTypeWrapper.java */
/* JADX INFO: loaded from: classes2.dex */
public final class m<T> extends o<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final k6.d f6610a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final o<T> f6611b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Type f6612c;

    public m(k6.d dVar, o<T> oVar, Type type) {
        this.f6610a = dVar;
        this.f6611b = oVar;
        this.f6612c = type;
    }

    @Override // k6.o
    public T b(q6.a aVar) throws IOException {
        return this.f6611b.b(aVar);
    }

    @Override // k6.o
    public void d(q6.b bVar, T t7) throws IOException {
        o<T> oVarK = this.f6611b;
        Type typeE = e(this.f6612c, t7);
        if (typeE != this.f6612c) {
            oVarK = this.f6610a.k(p6.a.b(typeE));
            if (oVarK instanceof i.b) {
                o<T> oVar = this.f6611b;
                if (!(oVar instanceof i.b)) {
                    oVarK = oVar;
                }
            }
        }
        oVarK.d(bVar, t7);
    }

    public final Type e(Type type, Object obj) {
        return obj != null ? (type == Object.class || (type instanceof TypeVariable) || (type instanceof Class)) ? obj.getClass() : type : type;
    }
}
