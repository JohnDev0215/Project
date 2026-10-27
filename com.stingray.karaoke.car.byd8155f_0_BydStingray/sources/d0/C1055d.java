package d0;

import java.util.LinkedHashMap;
import java.util.Map;
import n7.z;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: d0.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1055d extends A7.o implements InterfaceC2785n {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final C1055d f13260n = new C1055d(2, 0);
    public static final C1055d o = new C1055d(2, 1);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f13261m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C1055d(int i7, int i10) {
        super(i7);
        this.f13261m = i10;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f13261m) {
            case 0:
                h hVar = (h) obj2;
                LinkedHashMap linkedHashMapZ = z.z(hVar.f13268a);
                for (f fVar : hVar.f13269b.values()) {
                    if (fVar.f13265b) {
                        Map mapD = fVar.c.d();
                        boolean zIsEmpty = mapD.isEmpty();
                        Object obj3 = fVar.f13264a;
                        if (zIsEmpty) {
                            linkedHashMapZ.remove(obj3);
                        } else {
                            linkedHashMapZ.put(obj3, mapD);
                        }
                    }
                }
                if (linkedHashMapZ.isEmpty()) {
                    return null;
                }
                return linkedHashMapZ;
            default:
                return obj2;
        }
    }
}
