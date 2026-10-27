package androidx.lifecycle;

import H7.InterfaceC0318c;
import b2.C0993c;

/* JADX INFO: loaded from: classes.dex */
public interface h0 {
    default f0 a(Class cls) {
        throw new UnsupportedOperationException("`Factory.create(String, CreationExtras)` is not implemented. You may need to override the method and provide a custom implementation. Note that using `Factory.create(String)` is not supported and considered an error.");
    }

    default f0 b(InterfaceC0318c interfaceC0318c, C0993c c0993c) {
        A7.m.f(interfaceC0318c, "modelClass");
        return c(n7.z.i(interfaceC0318c), c0993c);
    }

    default f0 c(Class cls, C0993c c0993c) {
        return a(cls);
    }
}
