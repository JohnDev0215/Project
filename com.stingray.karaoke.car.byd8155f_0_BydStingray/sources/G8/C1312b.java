package g8;

import F8.AbstractC0287w;
import Q7.InterfaceC0652e;
import Q7.N;
import java.util.Map;
import o8.C2058c;
import v8.AbstractC2564d;

/* JADX INFO: renamed from: g8.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1312b implements R7.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final C1312b f14998a = new C1312b();

    @Override // R7.b
    public final C2058c a() {
        InterfaceC0652e interfaceC0652eD = AbstractC2564d.d(this);
        if (interfaceC0652eD == null) {
            return null;
        }
        if (H8.i.f(interfaceC0652eD)) {
            interfaceC0652eD = null;
        }
        if (interfaceC0652eD != null) {
            return AbstractC2564d.c(interfaceC0652eD);
        }
        return null;
    }

    @Override // R7.b
    public final AbstractC0287w b() {
        throw new IllegalStateException("No methods should be called on this descriptor. Only its presence matters");
    }

    @Override // R7.b
    public final Map c() {
        throw new IllegalStateException("No methods should be called on this descriptor. Only its presence matters");
    }

    @Override // R7.b
    public final N g() {
        throw new IllegalStateException("No methods should be called on this descriptor. Only its presence matters");
    }

    public final String toString() {
        return "[EnhancedType]";
    }
}
