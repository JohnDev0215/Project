package K7;

import java.lang.reflect.Constructor;
import n7.AbstractC1996k;

/* JADX INFO: renamed from: K7.g, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0383g extends v0 {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Constructor f3787m;

    public C0383g(Constructor constructor) {
        A7.m.f(constructor, "constructor");
        this.f3787m = constructor;
    }

    @Override // K7.v0
    public final String i() {
        Class<?>[] parameterTypes = this.f3787m.getParameterTypes();
        A7.m.e(parameterTypes, "constructor.parameterTypes");
        return AbstractC1996k.V(parameterTypes, "<init>(", ")V", C0377b.f3768t);
    }
}
