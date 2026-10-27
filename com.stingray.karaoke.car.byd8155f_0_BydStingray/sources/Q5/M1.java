package Q5;

import g1.C1285l;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class M1 implements C0.K {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f6439a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ T.X f6440b;
    public final /* synthetic */ g1.p c;
    public final /* synthetic */ C1285l d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ T.X f6441e;

    public /* synthetic */ M1(T.X x8, g1.p pVar, C1285l c1285l, T.X x9, int i7) {
        this.f6439a = i7;
        this.f6440b = x8;
        this.c = pVar;
        this.d = c1285l;
        this.f6441e = x9;
    }

    @Override // C0.K
    public final C0.L h(C0.M m10, List list, long j3) {
        switch (this.f6439a) {
            case 0:
                this.f6440b.getValue();
                long jF = this.c.f(j3, m10.getLayoutDirection(), this.d, list);
                this.f6441e.getValue();
                return m10.m0((int) (jF >> 32), (int) (jF & 4294967295L), n7.v.l, new L1(this.c, list, 0));
            case 1:
                this.f6440b.getValue();
                long jF2 = this.c.f(j3, m10.getLayoutDirection(), this.d, list);
                this.f6441e.getValue();
                return m10.m0((int) (jF2 >> 32), (int) (jF2 & 4294967295L), n7.v.l, new L1(this.c, list, 1));
            case 2:
                this.f6440b.getValue();
                long jF3 = this.c.f(j3, m10.getLayoutDirection(), this.d, list);
                this.f6441e.getValue();
                return m10.m0((int) (jF3 >> 32), (int) (jF3 & 4294967295L), n7.v.l, new L1(this.c, list, 2));
            case 3:
                this.f6440b.getValue();
                long jF4 = this.c.f(j3, m10.getLayoutDirection(), this.d, list);
                this.f6441e.getValue();
                return m10.m0((int) (jF4 >> 32), (int) (jF4 & 4294967295L), n7.v.l, new L1(this.c, list, 3));
            case 4:
                this.f6440b.getValue();
                long jF5 = this.c.f(j3, m10.getLayoutDirection(), this.d, list);
                this.f6441e.getValue();
                return m10.m0((int) (jF5 >> 32), (int) (jF5 & 4294967295L), n7.v.l, new L1(this.c, list, 4));
            case 5:
                this.f6440b.getValue();
                long jF6 = this.c.f(j3, m10.getLayoutDirection(), this.d, list);
                this.f6441e.getValue();
                return m10.m0((int) (jF6 >> 32), (int) (jF6 & 4294967295L), n7.v.l, new L1(this.c, list, 5));
            case 6:
                this.f6440b.getValue();
                long jF7 = this.c.f(j3, m10.getLayoutDirection(), this.d, list);
                this.f6441e.getValue();
                return m10.m0((int) (jF7 >> 32), (int) (jF7 & 4294967295L), n7.v.l, new L1(this.c, list, 6));
            case 7:
                this.f6440b.getValue();
                long jF8 = this.c.f(j3, m10.getLayoutDirection(), this.d, list);
                this.f6441e.getValue();
                return m10.m0((int) (jF8 >> 32), (int) (jF8 & 4294967295L), n7.v.l, new L1(this.c, list, 7));
            default:
                this.f6440b.getValue();
                long jF9 = this.c.f(j3, m10.getLayoutDirection(), this.d, list);
                this.f6441e.getValue();
                return m10.m0((int) (jF9 >> 32), (int) (jF9 & 4294967295L), n7.v.l, new L1(this.c, list, 8));
        }
    }
}
