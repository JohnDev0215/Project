package p;

import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public final class uq2 extends zu {
    public final /* synthetic */ Method i;
    public final /* synthetic */ Object j;

    public uq2(Object obj, Method method) {
        this.i = method;
        this.j = obj;
    }

    @Override // p.zu
    public final Object t0(Class cls) {
        zu.h(cls);
        return this.i.invoke(this.j, cls);
    }
}
