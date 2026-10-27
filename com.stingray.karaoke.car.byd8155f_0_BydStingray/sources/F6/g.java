package f6;

import A7.v;
import K5.F;
import K5.u;
import S8.AbstractC0727y;
import T.C0735d;
import T.C0738e0;
import T.S;
import androidx.lifecycle.a0;
import com.stingray.karaoke.car.ui.navigation.NavigationType;
import k6.C1705m;
import k6.r;
import kotlin.Metadata;
import m7.q;
import q7.InterfaceC2189c;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Lf6/g;", "Lb6/i;", "car_byd8155fEnvProdRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class g extends b6.i {

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final F f14612t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final r f14613u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final F f14614v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final L5.i f14615w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final C0738e0 f14616x;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public g(C1705m c1705m, W5.e eVar, F f10, u uVar, r rVar, j6.n nVar, F f11, L5.i iVar) {
        super(c1705m, eVar, uVar, iVar, nVar);
        A7.m.f(c1705m, "languageManager");
        A7.m.f(eVar, "appNavigationManager");
        A7.m.f(f10, "songsRepository");
        A7.m.f(uVar, "karaokeItemRepository");
        A7.m.f(rVar, "parentalControlsManager");
        A7.m.f(nVar, "profileManager");
        A7.m.f(f11, "repository");
        A7.m.f(iVar, "deepLinkManager");
        this.f14612t = f10;
        this.f14613u = rVar;
        this.f14614v = f11;
        this.f14615w = iVar;
        this.f14616x = C0735d.O(n7.u.l, S.f8224q);
        m(NavigationType.TAB);
        AbstractC0727y.w(a0.i(this), null, null, new C1263c(new v(), this, null), 3);
        final int i7 = 0;
        this.f12245m = new InterfaceC2782k(this) { // from class: f6.a

            /* JADX INFO: renamed from: m, reason: collision with root package name */
            public final /* synthetic */ g f14601m;

            {
                this.f14601m = this;
            }

            @Override // z7.InterfaceC2782k
            public final Object b(Object obj) {
                switch (i7) {
                    case 0:
                        A7.m.f((j6.a) obj, "it");
                        g gVar = this.f14601m;
                        gVar.getClass();
                        AbstractC0727y.w(gVar.f12236f, null, null, new e(gVar, null), 3);
                        break;
                    default:
                        A7.m.f((String) obj, "it");
                        g gVar2 = this.f14601m;
                        gVar2.getClass();
                        AbstractC0727y.w(gVar2.f12236f, null, null, new e(gVar2, null), 3);
                        break;
                }
                return q.f19267a;
            }
        };
        final int i10 = 1;
        this.f12246n = new InterfaceC2782k(this) { // from class: f6.a

            /* JADX INFO: renamed from: m, reason: collision with root package name */
            public final /* synthetic */ g f14601m;

            {
                this.f14601m = this;
            }

            @Override // z7.InterfaceC2782k
            public final Object b(Object obj) {
                switch (i10) {
                    case 0:
                        A7.m.f((j6.a) obj, "it");
                        g gVar = this.f14601m;
                        gVar.getClass();
                        AbstractC0727y.w(gVar.f12236f, null, null, new e(gVar, null), 3);
                        break;
                    default:
                        A7.m.f((String) obj, "it");
                        g gVar2 = this.f14601m;
                        gVar2.getClass();
                        AbstractC0727y.w(gVar2.f12236f, null, null, new e(gVar2, null), 3);
                        break;
                }
                return q.f19267a;
            }
        };
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0074  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00bc  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x001a  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:23:0x0082 -> B:32:0x00ba). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:27:0x00ad -> B:29:0x00b0). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:30:0x00b7 -> B:31:0x00b8). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object o(f6.g r17, s7.AbstractC2410c r18) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 233
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: f6.g.o(f6.g, s7.c):java.lang.Object");
    }

    @Override // b6.f
    public final Object f(InterfaceC2189c interfaceC2189c) {
        AbstractC0727y.w(this.f12236f, null, null, new d(this, null), 3);
        return q.f19267a;
    }

    @Override // b6.f
    public final void g() {
        i(true);
    }
}
