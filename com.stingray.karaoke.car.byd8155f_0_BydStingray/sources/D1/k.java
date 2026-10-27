package d1;

import E0.F;
import androidx.lifecycle.A;
import m7.q;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class k extends A7.o implements InterfaceC2785n {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final k f13315n = new k(2, 0);
    public static final k o = new k(2, 1);

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final k f13316p = new k(2, 2);

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final k f13317q = new k(2, 3);

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final k f13318r = new k(2, 4);

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final k f13319s = new k(2, 5);

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final k f13320t = new k(2, 6);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f13321m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ k(int i7, int i10) {
        super(i7);
        this.f13321m = i10;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        int i7;
        switch (this.f13321m) {
            case 0:
                androidx.compose.ui.viewinterop.a.c((F) obj).setUpdateBlock((InterfaceC2782k) obj2);
                return q.f19267a;
            case 1:
                androidx.compose.ui.viewinterop.a.c((F) obj).setReleaseBlock((InterfaceC2782k) obj2);
                return q.f19267a;
            case 2:
                androidx.compose.ui.viewinterop.a.c((F) obj).setModifier((g0.o) obj2);
                return q.f19267a;
            case 3:
                androidx.compose.ui.viewinterop.a.c((F) obj).setDensity((a1.b) obj2);
                return q.f19267a;
            case 4:
                androidx.compose.ui.viewinterop.a.c((F) obj).setLifecycleOwner((A) obj2);
                return q.f19267a;
            case 5:
                androidx.compose.ui.viewinterop.a.c((F) obj).setSavedStateRegistryOwner((t3.e) obj2);
                return q.f19267a;
            default:
                o oVarC = androidx.compose.ui.viewinterop.a.c((F) obj);
                int iOrdinal = ((a1.k) obj2).ordinal();
                if (iOrdinal != 0) {
                    i7 = 1;
                    if (iOrdinal != 1) {
                        throw new A9.k();
                    }
                } else {
                    i7 = 0;
                }
                oVarC.setLayoutDirection(i7);
                return q.f19267a;
        }
    }
}
