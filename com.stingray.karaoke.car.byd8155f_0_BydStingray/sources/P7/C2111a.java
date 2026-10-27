package p7;

import h6.f;
import java.util.Comparator;
import s2.u;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: p7.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class C2111a implements Comparator {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f20943m;

    public /* synthetic */ C2111a(Object obj, int i7) {
        this.l = i7;
        this.f20943m = obj;
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        switch (this.l) {
            case 0:
                for (InterfaceC2782k interfaceC2782k : (InterfaceC2782k[]) this.f20943m) {
                    int iB = f.b((Comparable) interfaceC2782k.b(obj), (Comparable) interfaceC2782k.b(obj2));
                    if (iB != 0) {
                        return iB;
                    }
                }
                return 0;
            default:
                u uVar = (u) this.f20943m;
                return uVar.a(obj2) - uVar.a(obj);
        }
    }
}
