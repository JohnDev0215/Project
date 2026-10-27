package Q5;

import F0.C0221p;
import T.C0753m;
import T.C0761q;
import com.stingray.karaoke.car.player.SongQueuePosition;
import z7.InterfaceC2772a;
import z7.InterfaceC2786o;

/* JADX INFO: renamed from: Q5.k1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0587k1 implements InterfaceC2786o {
    public final /* synthetic */ String l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ String f6928m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ boolean f6929n;
    public final /* synthetic */ boolean o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ int f6930p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ int f6931q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ C0603o1 f6932r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ C0221p f6933s;

    public C0587k1(String str, String str2, boolean z6, boolean z10, int i7, int i10, C0603o1 c0603o1, C0221p c0221p) {
        this.l = str;
        this.f6928m = str2;
        this.f6929n = z6;
        this.o = z10;
        this.f6930p = i7;
        this.f6931q = i10;
        this.f6932r = c0603o1;
        this.f6933s = c0221p;
    }

    @Override // z7.InterfaceC2786o
    public final Object g(Object obj, Object obj2, Object obj3) {
        String str = (String) obj;
        C0761q c0761q = (C0761q) obj2;
        int iIntValue = ((Number) obj3).intValue();
        A7.m.f(str, "id");
        if ((iIntValue & 6) == 0) {
            iIntValue |= c0761q.f(str) ? 4 : 2;
        }
        if ((iIntValue & 19) == 18 && c0761q.x()) {
            c0761q.K();
        } else {
            c0761q.Q(-702717519);
            final C0603o1 c0603o1 = this.f6932r;
            boolean zF = c0761q.f(c0603o1);
            C0221p c0221p = this.f6933s;
            boolean zF2 = zF | c0761q.f(c0221p);
            Object objG = c0761q.G();
            T.S s6 = C0753m.f8270a;
            if (zF2 || objG == s6) {
                objG = new M5.d(c0603o1, 2, c0221p);
                c0761q.a0(objG);
            }
            InterfaceC2772a interfaceC2772a = (InterfaceC2772a) objG;
            c0761q.p(false);
            c0761q.Q(-702712623);
            boolean zF3 = c0761q.f(c0603o1);
            Object objG2 = c0761q.G();
            if (zF3 || objG2 == s6) {
                final int i7 = 0;
                objG2 = new InterfaceC2772a() { // from class: Q5.j1
                    @Override // z7.InterfaceC2772a
                    public final Object a() {
                        switch (i7) {
                            case 0:
                                c0603o1.invoke(SongQueuePosition.SING_LAST, Boolean.FALSE);
                                break;
                            default:
                                c0603o1.invoke(SongQueuePosition.SING_NOW, Boolean.TRUE);
                                break;
                        }
                        return m7.q.f19267a;
                    }
                };
                c0761q.a0(objG2);
            }
            InterfaceC2772a interfaceC2772a2 = (InterfaceC2772a) objG2;
            c0761q.p(false);
            c0761q.Q(-702708657);
            boolean zF4 = c0761q.f(c0603o1);
            Object objG3 = c0761q.G();
            if (zF4 || objG3 == s6) {
                final int i10 = 1;
                objG3 = new InterfaceC2772a() { // from class: Q5.j1
                    @Override // z7.InterfaceC2772a
                    public final Object a() {
                        switch (i10) {
                            case 0:
                                c0603o1.invoke(SongQueuePosition.SING_LAST, Boolean.FALSE);
                                break;
                            default:
                                c0603o1.invoke(SongQueuePosition.SING_NOW, Boolean.TRUE);
                                break;
                        }
                        return m7.q.f19267a;
                    }
                };
                c0761q.a0(objG3);
            }
            c0761q.p(false);
            C7.a.q(str, this.l, this.f6928m, this.f6929n, this.o, this.f6930p, this.f6931q, interfaceC2772a, interfaceC2772a2, (InterfaceC2772a) objG3, c0761q, iIntValue & 14);
        }
        return m7.q.f19267a;
    }
}
