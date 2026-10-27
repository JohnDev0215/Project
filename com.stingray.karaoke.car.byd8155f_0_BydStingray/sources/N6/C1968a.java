package n6;

import D.T;
import Q.c1;
import T.C0761q;
import z7.InterfaceC2786o;

/* JADX INFO: renamed from: n6.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1968a implements InterfaceC2786o {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final C1968a f20050m = new C1968a(0);

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final C1968a f20051n = new C1968a(1);
    public static final C1968a o = new C1968a(2);

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final C1968a f20052p = new C1968a(3);
    public final /* synthetic */ int l;

    public /* synthetic */ C1968a(int i7) {
        this.l = i7;
    }

    @Override // z7.InterfaceC2786o
    public final Object g(Object obj, Object obj2, Object obj3) {
        switch (this.l) {
            case 0:
                C0761q c0761q = (C0761q) obj2;
                int iIntValue = ((Number) obj3).intValue();
                A7.m.f((T) obj, "$this$Button");
                if ((iIntValue & 17) == 16 && c0761q.x()) {
                    c0761q.K();
                } else {
                    c1.b("Settings", null, 0L, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, c0761q, 6, 0, 131070);
                }
                break;
            case 1:
                C0761q c0761q2 = (C0761q) obj2;
                int iIntValue2 = ((Number) obj3).intValue();
                A7.m.f((T) obj, "$this$Button");
                if ((iIntValue2 & 17) == 16 && c0761q2.x()) {
                    c0761q2.K();
                } else {
                    c1.b("Close", null, 0L, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, c0761q2, 6, 0, 131070);
                }
                break;
            case 2:
                C0761q c0761q3 = (C0761q) obj2;
                int iIntValue3 = ((Number) obj3).intValue();
                A7.m.f((T) obj, "$this$Button");
                if ((iIntValue3 & 17) == 16 && c0761q3.x()) {
                    c0761q3.K();
                } else {
                    c1.b("Settings", null, 0L, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, c0761q3, 6, 0, 131070);
                }
                break;
            default:
                C0761q c0761q4 = (C0761q) obj2;
                int iIntValue4 = ((Number) obj3).intValue();
                A7.m.f((T) obj, "$this$Button");
                if ((iIntValue4 & 17) == 16 && c0761q4.x()) {
                    c0761q4.K();
                } else {
                    c1.b("Close", null, 0L, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, c0761q4, 6, 0, 131070);
                }
                break;
        }
        return m7.q.f19267a;
    }
}
