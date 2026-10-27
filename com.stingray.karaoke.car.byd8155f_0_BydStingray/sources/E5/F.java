package E5;

import java.lang.reflect.Type;

/* JADX INFO: loaded from: classes.dex */
public final class F extends q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Type f1768a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f1769b;
    public final Object c;
    public q d;

    public F(Type type, String str, Object obj) {
        this.f1768a = type;
        this.f1769b = str;
        this.c = obj;
    }

    @Override // E5.q
    public final Object a(u uVar) {
        q qVar = this.d;
        if (qVar != null) {
            return qVar.a(uVar);
        }
        throw new IllegalStateException("JsonAdapter isn't ready");
    }

    @Override // E5.q
    public final void d(x xVar, Object obj) {
        q qVar = this.d;
        if (qVar == null) {
            throw new IllegalStateException("JsonAdapter isn't ready");
        }
        qVar.d(xVar, obj);
    }

    public final String toString() {
        q qVar = this.d;
        return qVar != null ? qVar.toString() : super.toString();
    }
}
