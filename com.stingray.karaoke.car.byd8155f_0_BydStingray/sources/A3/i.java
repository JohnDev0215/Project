package A3;

import A7.C0053a;
import A7.InterfaceC0059g;
import S8.AbstractC0727y;
import S8.k0;
import V8.C0819h;
import V8.C0821j;
import V8.C0832v;
import V8.InterfaceC0818g;
import X5.C0882s;
import X5.C0884t;
import X5.C0886u;
import coil.compose.AsyncImagePainter;
import com.stingray.karaoke.car.player.PlayerStatus;
import g1.AbstractC1274a;
import java.lang.reflect.InvocationTargetException;
import q7.InterfaceC2189c;
import r7.EnumC2303a;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class i implements InterfaceC0818g, InterfaceC0059g {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f390m;

    public /* synthetic */ i(Object obj, int i7) {
        this.l = i7;
        this.f390m = obj;
    }

    @Override // A7.InterfaceC0059g
    public final m7.c b() {
        switch (this.l) {
            case 0:
                return new C0053a(2, 4, AsyncImagePainter.class, (AsyncImagePainter) this.f390m, "updateState", "updateState(Lcoil/compose/AsyncImagePainter$State;)V");
            default:
                return new C0053a(2, 4, C0886u.class, (C0886u) this.f390m, "handleStatus", "handleStatus(Lcom/stingray/karaoke/car/player/PlayerStatus;)V");
        }
    }

    public final boolean equals(Object obj) {
        switch (this.l) {
            case 0:
                if ((obj instanceof InterfaceC0818g) && (obj instanceof InterfaceC0059g)) {
                    break;
                }
                break;
            default:
                if ((obj instanceof InterfaceC0818g) && (obj instanceof InterfaceC0059g)) {
                    break;
                }
                break;
        }
        return b().equals(((InterfaceC0059g) obj).b());
    }

    @Override // V8.InterfaceC0818g
    public final Object f(Object obj, InterfaceC2189c interfaceC2189c) throws IllegalAccessException, InvocationTargetException {
        long jN;
        m7.q qVar = m7.q.f19267a;
        Object obj2 = this.f390m;
        switch (this.l) {
            case 0:
                ((AsyncImagePainter) obj2).k((f) obj);
                EnumC2303a enumC2303a = EnumC2303a.l;
                break;
            default:
                PlayerStatus playerStatus = (PlayerStatus) obj;
                C0886u c0886u = (C0886u) obj2;
                c0886u.getClass();
                if (playerStatus != PlayerStatus.PLAYING) {
                    k0 k0Var = c0886u.f10388g;
                    if (k0Var != null) {
                        k0Var.g(null);
                    }
                    c0886u.f10388g = null;
                } else if (c0886u.f10388g == null) {
                    int i7 = R8.a.f7565n;
                    R8.c cVar = R8.c.f7568n;
                    A7.m.f(cVar, "unit");
                    if (cVar.compareTo(R8.c.o) <= 0) {
                        jN = AbstractC1274a.D(15, cVar, R8.c.f7567m) << 1;
                        int i10 = R8.b.f7566a;
                    } else {
                        jN = com.bumptech.glide.d.N(15, cVar);
                    }
                    c0886u.f10388g = AbstractC0727y.w(c0886u.f10385b, null, null, new C0821j(new C0832v(new C0819h(new C0884t(0L, jN, null)), new C0882s(c0886u, null)), null), 3);
                }
                if (playerStatus == PlayerStatus.STOPPED) {
                    c0886u.b();
                } else {
                    c0886u.a();
                }
                EnumC2303a enumC2303a2 = EnumC2303a.l;
                break;
        }
        return qVar;
    }

    public final int hashCode() {
        switch (this.l) {
        }
        return b().hashCode();
    }
}
