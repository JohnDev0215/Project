package T5;

import T.C0735d;
import T.C0761q;
import V8.N;
import com.stingray.karaoke.car.player.PlayerStatus;
import com.stingray.karaoke.car.utils.FontSize;
import g0.o;
import m7.q;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class b implements InterfaceC2785n {
    public final /* synthetic */ int l = 1;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ o f8662m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ N f8663n;
    public final /* synthetic */ boolean o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ boolean f8664p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2772a f8665q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ int f8666r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ Object f8667s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ m7.c f8668t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final /* synthetic */ Object f8669u;

    public /* synthetic */ b(o oVar, N n10, Long l, boolean z6, boolean z10, InterfaceC2782k interfaceC2782k, InterfaceC2772a interfaceC2772a, FontSize fontSize, int i7) {
        this.f8662m = oVar;
        this.f8663n = n10;
        this.f8667s = l;
        this.o = z6;
        this.f8664p = z10;
        this.f8668t = interfaceC2782k;
        this.f8665q = interfaceC2772a;
        this.f8669u = fontSize;
        this.f8666r = i7;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        switch (this.l) {
            case 0:
                ((Integer) obj2).getClass();
                int iZ = C0735d.Z(this.f8666r | 1);
                InterfaceC2772a interfaceC2772a = (InterfaceC2772a) this.f8668t;
                InterfaceC2772a interfaceC2772a2 = (InterfaceC2772a) this.f8669u;
                l9.l.r(this.f8662m, this.o, this.f8664p, (PlayerStatus) this.f8667s, this.f8663n, this.f8665q, interfaceC2772a, interfaceC2772a2, (C0761q) obj, iZ);
                break;
            default:
                ((Integer) obj2).getClass();
                int iZ2 = C0735d.Z(this.f8666r | 1);
                Long l = (Long) this.f8667s;
                InterfaceC2772a interfaceC2772a3 = this.f8665q;
                FontSize fontSize = (FontSize) this.f8669u;
                C7.a.m(this.f8662m, this.f8663n, l, this.o, this.f8664p, (InterfaceC2782k) this.f8668t, interfaceC2772a3, fontSize, (C0761q) obj, iZ2);
                break;
        }
        return q.f19267a;
    }

    public /* synthetic */ b(o oVar, boolean z6, boolean z10, PlayerStatus playerStatus, N n10, InterfaceC2772a interfaceC2772a, InterfaceC2772a interfaceC2772a2, InterfaceC2772a interfaceC2772a3, int i7) {
        this.f8662m = oVar;
        this.o = z6;
        this.f8664p = z10;
        this.f8667s = playerStatus;
        this.f8663n = n10;
        this.f8665q = interfaceC2772a;
        this.f8668t = interfaceC2772a2;
        this.f8669u = interfaceC2772a3;
        this.f8666r = i7;
    }
}
