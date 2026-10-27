package F8;

import Q7.InterfaceC0654g;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class G extends M {
    public final /* synthetic */ ArrayList c;

    public G(ArrayList arrayList) {
        this.c = arrayList;
    }

    @Override // F8.M
    public final O g(K k10) {
        A7.m.f(k10, "key");
        if (!this.c.contains(k10)) {
            return null;
        }
        InterfaceC0654g interfaceC0654gN = k10.n();
        A7.m.d(interfaceC0654gN, "null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.TypeParameterDescriptor");
        return Y.j((Q7.Q) interfaceC0654gN);
    }
}
