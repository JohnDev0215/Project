package t8;

import F8.A;
import F8.AbstractC0287w;
import Q7.AbstractC0669w;
import Q7.InterfaceC0652e;
import Q7.InterfaceC0671y;

/* JADX INFO: loaded from: classes.dex */
public final class x extends n {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ int f22508b = 0;

    public x(byte b10) {
        super(Byte.valueOf(b10));
    }

    @Override // t8.g
    public final AbstractC0287w a(InterfaceC0671y interfaceC0671y) {
        switch (this.f22508b) {
            case 0:
                A7.m.f(interfaceC0671y, "module");
                InterfaceC0652e interfaceC0652eD = AbstractC0669w.d(interfaceC0671y, N7.n.f4834R);
                A aJ = interfaceC0652eD != null ? interfaceC0652eD.j() : null;
                return aJ == null ? H8.i.c(H8.h.f2875K, "UByte") : aJ;
            case 1:
                A7.m.f(interfaceC0671y, "module");
                InterfaceC0652e interfaceC0652eD2 = AbstractC0669w.d(interfaceC0671y, N7.n.f4836T);
                A aJ2 = interfaceC0652eD2 != null ? interfaceC0652eD2.j() : null;
                return aJ2 == null ? H8.i.c(H8.h.f2875K, "UInt") : aJ2;
            case 2:
                A7.m.f(interfaceC0671y, "module");
                InterfaceC0652e interfaceC0652eD3 = AbstractC0669w.d(interfaceC0671y, N7.n.f4837U);
                A aJ3 = interfaceC0652eD3 != null ? interfaceC0652eD3.j() : null;
                return aJ3 == null ? H8.i.c(H8.h.f2875K, "ULong") : aJ3;
            default:
                A7.m.f(interfaceC0671y, "module");
                InterfaceC0652e interfaceC0652eD4 = AbstractC0669w.d(interfaceC0671y, N7.n.f4835S);
                A aJ4 = interfaceC0652eD4 != null ? interfaceC0652eD4.j() : null;
                return aJ4 == null ? H8.i.c(H8.h.f2875K, "UShort") : aJ4;
        }
    }

    @Override // t8.g
    public final String toString() {
        switch (this.f22508b) {
            case 0:
                return ((Number) this.f22499a).intValue() + ".toUByte()";
            case 1:
                return ((Number) this.f22499a).intValue() + ".toUInt()";
            case 2:
                return ((Number) this.f22499a).longValue() + ".toULong()";
            default:
                return ((Number) this.f22499a).intValue() + ".toUShort()";
        }
    }

    public x(short s6) {
        super(Short.valueOf(s6));
    }

    public x(int i7) {
        super(Integer.valueOf(i7));
    }

    public x(long j3) {
        super(Long.valueOf(j3));
    }
}
