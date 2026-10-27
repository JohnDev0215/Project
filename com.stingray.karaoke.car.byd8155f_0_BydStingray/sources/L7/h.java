package L7;

import java.lang.reflect.Field;

/* JADX INFO: loaded from: classes.dex */
public final class h extends k implements d {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Object f4053e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public h(Field field, Object obj) {
        super(field, false);
        A7.m.f(field, "field");
        this.f4053e = obj;
    }

    @Override // L7.k, L7.e
    public final Object q(Object[] objArr) {
        l9.d.H(this, objArr);
        return ((Field) this.f4062a).get(this.f4053e);
    }
}
