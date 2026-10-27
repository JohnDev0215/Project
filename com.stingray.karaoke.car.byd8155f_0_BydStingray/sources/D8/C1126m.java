package d8;

import W7.C0838b;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import n7.AbstractC1985B;
import n7.AbstractC1996k;
import n7.AbstractC1998m;
import n7.AbstractC2000o;
import z7.InterfaceC2772a;

/* JADX INFO: renamed from: d8.m, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1126m extends A7.o implements InterfaceC2772a {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f13603m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ C1127n f13604n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C1126m(C1127n c1127n, int i7) {
        super(0);
        this.f13603m = i7;
        this.f13604n = c1127n;
    }

    @Override // z7.InterfaceC2772a
    public final Object a() {
        switch (this.f13603m) {
            case 0:
                List listB = this.f13604n.o.b();
                ArrayList arrayList = new ArrayList();
                for (Object obj : listB) {
                    if (((W7.t) obj).f9897a.isEnumConstant()) {
                        arrayList.add(obj);
                    }
                }
                int iQ = n7.z.q(AbstractC2000o.J(arrayList, 10));
                if (iQ < 16) {
                    iQ = 16;
                }
                LinkedHashMap linkedHashMap = new LinkedHashMap(iQ);
                for (Object obj2 : arrayList) {
                    linkedHashMap.put(((W7.t) obj2).c(), obj2);
                }
                return linkedHashMap;
            case 1:
                Class<?>[] declaredClasses = this.f13604n.o.f9891a.getDeclaredClasses();
                A7.m.e(declaredClasses, "klass.declaredClasses");
                return AbstractC1998m.D0(P8.m.i0(P8.m.h0(new P8.g(AbstractC1996k.y(declaredClasses), false, C0838b.f9875p), C0838b.f9876q)));
            default:
                C1127n c1127n = this.f13604n;
                return AbstractC1985B.q(c1127n.e(), c1127n.f());
        }
    }
}
