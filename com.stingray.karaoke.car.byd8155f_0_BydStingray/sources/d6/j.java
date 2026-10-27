package d6;

import A7.m;
import K5.F;
import N5.Z;
import N5.q0;
import S8.AbstractC0727y;
import T.C0735d;
import T.C0738e0;
import T.S;
import V8.N;
import V8.f0;
import android.content.Context;
import com.stingray.karaoke.car.ui.viewmodel.base.LoadingInterface$LoadingState;
import j6.n;
import k6.C1704l;
import k6.C1705m;
import kotlin.Metadata;
import m7.q;
import n6.u;
import q7.InterfaceC2189c;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Ld6/j;", "Lb6/i;", "car_byd8155fEnvProdRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class j extends b6.i {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public final C0738e0 f13477A;
    public final C0738e0 B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public final C0738e0 f13478C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public final N f13479D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public final f0 f13480E;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final W5.e f13481t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final F f13482u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final q0 f13483v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final Z f13484w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final u f13485x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final C0738e0 f13486y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public final C0738e0 f13487z;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public j(Context context, C1705m c1705m, W5.e eVar, F f10, q0 q0Var, Z z6, u uVar, K5.u uVar2, n nVar, L5.i iVar, C1704l c1704l) {
        super(c1705m, eVar, uVar2, iVar, nVar);
        m.f(context, "appContext");
        m.f(c1705m, "languageManager");
        m.f(eVar, "appNavigationManager");
        m.f(f10, "songsRepository");
        m.f(q0Var, "songQueue");
        m.f(z6, "playerManager");
        m.f(uVar, "microphoneManager");
        m.f(uVar2, "karaokeItemRepository");
        m.f(nVar, "profileManager");
        m.f(iVar, "deepLinkManager");
        m.f(c1704l, "driveModeManager");
        this.f13481t = eVar;
        this.f13482u = f10;
        this.f13483v = q0Var;
        this.f13484w = z6;
        this.f13485x = uVar;
        S s6 = S.f8224q;
        this.f13486y = C0735d.O(null, s6);
        this.f13487z = C0735d.O(Boolean.FALSE, s6);
        this.f13477A = C0735d.O(n7.u.l, s6);
        this.B = C0735d.O(LoadingInterface$LoadingState.Loading, s6);
        this.f13478C = C0735d.O(null, s6);
        this.f13479D = c1704l.d;
        this.f13480E = uVar.c;
        final int i7 = 0;
        this.f12245m = new InterfaceC2782k(this) { // from class: d6.f

            /* JADX INFO: renamed from: m, reason: collision with root package name */
            public final /* synthetic */ j f13470m;

            {
                this.f13470m = this;
            }

            @Override // z7.InterfaceC2782k
            public final Object b(Object obj) {
                switch (i7) {
                    case 0:
                        j6.a aVar = (j6.a) obj;
                        m.f(aVar, "it");
                        j jVar = this.f13470m;
                        if (m.a(aVar.f17033b, jVar.f12250s)) {
                            jVar.f13487z.setValue(Boolean.valueOf(aVar.f17032a));
                        }
                        break;
                    default:
                        String str = (String) obj;
                        m.f(str, "it");
                        j jVar2 = this.f13470m;
                        AbstractC0727y.w(jVar2.f12236f, null, null, new g(jVar2, str, null), 3);
                        break;
                }
                return q.f19267a;
            }
        };
        final int i10 = 1;
        this.o = new InterfaceC2782k(this) { // from class: d6.f

            /* JADX INFO: renamed from: m, reason: collision with root package name */
            public final /* synthetic */ j f13470m;

            {
                this.f13470m = this;
            }

            @Override // z7.InterfaceC2782k
            public final Object b(Object obj) {
                switch (i10) {
                    case 0:
                        j6.a aVar = (j6.a) obj;
                        m.f(aVar, "it");
                        j jVar = this.f13470m;
                        if (m.a(aVar.f17033b, jVar.f12250s)) {
                            jVar.f13487z.setValue(Boolean.valueOf(aVar.f17032a));
                        }
                        break;
                    default:
                        String str = (String) obj;
                        m.f(str, "it");
                        j jVar2 = this.f13470m;
                        AbstractC0727y.w(jVar2.f12236f, null, null, new g(jVar2, str, null), 3);
                        break;
                }
                return q.f19267a;
            }
        };
    }

    @Override // b6.f
    public final Object f(InterfaceC2189c interfaceC2189c) {
        String str = this.f12250s;
        if (str != null) {
            h hVar = new h(this, str, null);
            X8.c cVar = this.f12236f;
            AbstractC0727y.w(cVar, null, null, hVar, 3);
            String str2 = this.f12250s;
            if (str2 != null) {
                AbstractC0727y.w(cVar, null, null, new i(this, str2, null), 3);
            } else {
                this.B.setValue(LoadingInterface$LoadingState.Error);
            }
        } else {
            l(LoadingInterface$LoadingState.Error);
        }
        return q.f19267a;
    }
}
