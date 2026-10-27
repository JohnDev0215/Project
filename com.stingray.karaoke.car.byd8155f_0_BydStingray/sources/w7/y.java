package W7;

import f8.InterfaceC1268d;
import java.lang.reflect.Type;
import java.util.Collection;

/* JADX INFO: loaded from: classes.dex */
public final class y extends A implements InterfaceC1268d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Class f9901a;

    public y(Class cls) {
        this.f9901a = cls;
    }

    @Override // W7.A
    public final Type b() {
        return this.f9901a;
    }

    @Override // f8.InterfaceC1266b
    public final Collection k() {
        return n7.u.l;
    }
}
