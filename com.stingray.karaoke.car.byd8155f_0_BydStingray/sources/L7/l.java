package L7;

import java.lang.reflect.Field;
import n7.AbstractC1996k;

/* JADX INFO: loaded from: classes.dex */
public final class l extends o implements d {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Object f4055g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public l(Field field, boolean z6, Object obj) {
        super(field, z6, false);
        A7.m.f(field, "field");
        this.f4055g = obj;
    }

    @Override // L7.o, L7.e
    public final Object q(Object[] objArr) throws IllegalAccessException {
        a(objArr);
        ((Field) this.f4062a).set(this.f4055g, AbstractC1996k.N(objArr));
        return m7.q.f19267a;
    }
}
