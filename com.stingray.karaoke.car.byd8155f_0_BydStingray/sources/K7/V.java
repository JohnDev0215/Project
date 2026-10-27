package K7;

import Q7.InterfaceC0652e;
import Q7.InterfaceC0657j;
import java.lang.reflect.Type;
import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public final class V extends A7.o implements InterfaceC2772a {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f3752m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ W f3753n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ V(W w10, int i7) {
        super(0);
        this.f3752m = i7;
        this.f3753n = w10;
    }

    @Override // z7.InterfaceC2772a
    public final Object a() {
        switch (this.f3752m) {
            case 0:
                return A0.d(this.f3753n.a());
            default:
                W w10 = this.f3753n;
                Q7.J jA = w10.a();
                boolean z6 = jA instanceof T7.u;
                r rVar = w10.f3755a;
                if (!z6 || !A7.m.a(A0.g(rVar.n()), jA) || rVar.n().N() != 2) {
                    return (Type) rVar.i().r().get(w10.f3756b);
                }
                InterfaceC0657j interfaceC0657jO = rVar.n().o();
                A7.m.d(interfaceC0657jO, "null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor");
                Class clsJ = A0.j((InterfaceC0652e) interfaceC0657jO);
                if (clsJ != null) {
                    return clsJ;
                }
                throw new r0("Cannot determine receiver Java type of inherited declaration: " + jA);
        }
    }
}
