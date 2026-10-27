package F0;

import java.util.Comparator;

/* JADX INFO: loaded from: classes.dex */
public final class G implements Comparator {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final G f2021m = new G(0);

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final G f2022n = new G(1);
    public static final G o = new G(2);
    public final /* synthetic */ int l;

    public /* synthetic */ G(int i7) {
        this.l = i7;
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        switch (this.l) {
            case 0:
                m0.d dVarF = ((L0.o) obj).f();
                m0.d dVarF2 = ((L0.o) obj2).f();
                int iCompare = Float.compare(dVarF.f18882a, dVarF2.f18882a);
                if (iCompare != 0) {
                    return iCompare;
                }
                int iCompare2 = Float.compare(dVarF.f18883b, dVarF2.f18883b);
                if (iCompare2 != 0) {
                    return iCompare2;
                }
                int iCompare3 = Float.compare(dVarF.d, dVarF2.d);
                return iCompare3 != 0 ? iCompare3 : Float.compare(dVarF.c, dVarF2.c);
            case 1:
                m0.d dVarF3 = ((L0.o) obj).f();
                m0.d dVarF4 = ((L0.o) obj2).f();
                int iCompare4 = Float.compare(dVarF4.c, dVarF3.c);
                if (iCompare4 != 0) {
                    return iCompare4;
                }
                int iCompare5 = Float.compare(dVarF3.f18883b, dVarF4.f18883b);
                if (iCompare5 != 0) {
                    return iCompare5;
                }
                int iCompare6 = Float.compare(dVarF3.d, dVarF4.d);
                return iCompare6 != 0 ? iCompare6 : Float.compare(dVarF4.f18882a, dVarF3.f18882a);
            default:
                m7.i iVar = (m7.i) obj;
                m7.i iVar2 = (m7.i) obj2;
                int iCompare7 = Float.compare(((m0.d) iVar.l).f18883b, ((m0.d) iVar2.l).f18883b);
                return iCompare7 != 0 ? iCompare7 : Float.compare(((m0.d) iVar.l).d, ((m0.d) iVar2.l).d);
        }
    }
}
