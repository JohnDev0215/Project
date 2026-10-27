package c.b.a.t.i;

import c.b.a.q;
import c.b.a.t.i.i;
import java.io.IOException;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

/* JADX INFO: compiled from: TypeAdapterRuntimeTypeWrapper.java */
/* JADX INFO: loaded from: classes.dex */
public final class m<T> extends q<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final c.b.a.e f3005a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final q<T> f3006b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Type f3007c;

    public m(c.b.a.e eVar, q<T> qVar, Type type) {
        this.f3005a = eVar;
        this.f3006b = qVar;
        this.f3007c = type;
    }

    @Override // c.b.a.q
    public T b(c.b.a.v.a aVar) throws IOException {
        return this.f3006b.b(aVar);
    }

    @Override // c.b.a.q
    public void d(c.b.a.v.b bVar, T t) throws IOException {
        q<T> qVarM = this.f3006b;
        Type typeE = e(this.f3007c, t);
        if (typeE != this.f3007c) {
            qVarM = this.f3005a.m(c.b.a.u.a.b(typeE));
            if (qVarM instanceof i.b) {
                q<T> qVar = this.f3006b;
                if (!(qVar instanceof i.b)) {
                    qVarM = qVar;
                }
            }
        }
        qVarM.d(bVar, t);
    }

    public final Type e(Type type, Object obj) {
        return obj != null ? (type == Object.class || (type instanceof TypeVariable) || (type instanceof Class)) ? obj.getClass() : type : type;
    }
}
