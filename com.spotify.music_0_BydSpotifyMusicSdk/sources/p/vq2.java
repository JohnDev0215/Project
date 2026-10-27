package p;

import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public final class vq2 extends zu {
    public final /* synthetic */ Method i;
    public final /* synthetic */ int j;

    public vq2(int i, Method method) {
        this.i = method;
        this.j = i;
    }

    @Override // p.zu
    public final Object t0(Class cls) {
        zu.h(cls);
        return this.i.invoke(null, cls, Integer.valueOf(this.j));
    }
}
