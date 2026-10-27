package p;

import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public final class wq2 extends zu {
    public final /* synthetic */ Method i;

    public wq2(Method method) {
        this.i = method;
    }

    @Override // p.zu
    public final Object t0(Class cls) {
        zu.h(cls);
        return this.i.invoke(null, cls, Object.class);
    }
}
