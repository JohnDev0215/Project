package androidx.datastore.preferences.protobuf;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class D extends E {
    @Override // androidx.datastore.preferences.protobuf.E
    public final void a(long j3, Object obj) {
        ((AbstractC0939b) ((InterfaceC0958v) r0.c.i(j3, obj))).l = false;
    }

    @Override // androidx.datastore.preferences.protobuf.E
    public final void b(long j3, Object obj, Object obj2) {
        q0 q0Var = r0.c;
        InterfaceC0958v interfaceC0958vH = (InterfaceC0958v) q0Var.i(j3, obj);
        InterfaceC0958v interfaceC0958v = (InterfaceC0958v) q0Var.i(j3, obj2);
        int size = interfaceC0958vH.size();
        int size2 = interfaceC0958v.size();
        if (size > 0 && size2 > 0) {
            if (!((AbstractC0939b) interfaceC0958vH).l) {
                interfaceC0958vH = interfaceC0958vH.h(size2 + size);
            }
            interfaceC0958vH.addAll(interfaceC0958v);
        }
        if (size > 0) {
            interfaceC0958v = interfaceC0958vH;
        }
        r0.s(j3, obj, interfaceC0958v);
    }

    @Override // androidx.datastore.preferences.protobuf.E
    public final List c(long j3, Object obj) {
        InterfaceC0958v interfaceC0958v = (InterfaceC0958v) r0.c.i(j3, obj);
        if (((AbstractC0939b) interfaceC0958v).l) {
            return interfaceC0958v;
        }
        int size = interfaceC0958v.size();
        InterfaceC0958v interfaceC0958vH = interfaceC0958v.h(size == 0 ? 10 : size * 2);
        r0.s(j3, obj, interfaceC0958vH);
        return interfaceC0958vH;
    }
}
