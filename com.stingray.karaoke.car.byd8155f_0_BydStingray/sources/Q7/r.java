package Q7;

import java.util.List;
import n7.AbstractC1998m;
import o8.C2057b;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class r extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final r f7202n = new r(1, 0);
    public static final r o = new r(1, 1);

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final r f7203p = new r(1, 2);

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final r f7204q = new r(1, 3);

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final r f7205r = new r(1, 4);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f7206m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ r(int i7, int i10) {
        super(i7);
        this.f7206m = i10;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        switch (this.f7206m) {
            case 0:
                A7.m.f((C2057b) obj, "it");
                return 0;
            case 1:
                D d = (D) obj;
                A7.m.f(d, "it");
                return ((T7.B) d).f8767q;
            case 2:
                InterfaceC0657j interfaceC0657j = (InterfaceC0657j) obj;
                A7.m.f(interfaceC0657j, "it");
                return Boolean.valueOf(interfaceC0657j instanceof InterfaceC0649b);
            case 3:
                A7.m.f((InterfaceC0657j) obj, "it");
                return Boolean.valueOf(!(r1 instanceof InterfaceC0656i));
            default:
                InterfaceC0657j interfaceC0657j2 = (InterfaceC0657j) obj;
                A7.m.f(interfaceC0657j2, "it");
                List listT = ((InterfaceC0649b) interfaceC0657j2).t();
                A7.m.e(listT, "it as CallableDescriptor).typeParameters");
                return AbstractC1998m.S(listT);
        }
    }
}
