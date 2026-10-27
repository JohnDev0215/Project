package N5;

import S8.AbstractC0727y;
import com.stingray.karaoke.car.player.PlayerStatus;
import com.stingray.kca.model.AssetTypeV2;
import com.stingray.kca.model.KaraokeSongResponseV2;
import k3.C1672v;
import timber.log.Timber;

/* JADX INFO: loaded from: classes.dex */
public final class Q implements h5.s {
    public final /* synthetic */ Z l;

    public /* synthetic */ Q(Z z6) {
        this.l = z6;
    }

    public void a(boolean z6) {
        KaraokeSongResponseV2 karaokeSongResponseV2;
        String str;
        Z z10 = this.l;
        f0 f0Var = (f0) z10.f4636O.getValue();
        if (f0Var == null || (karaokeSongResponseV2 = f0Var.f4691a) == null || (str = karaokeSongResponseV2.f13136a) == null) {
            return;
        }
        j6.n nVar = z10.f4656f;
        if (z6) {
            nVar.c(str, AssetTypeV2.SONG, new A3.v(3), false);
        } else {
            nVar.e(AssetTypeV2.SONG, str, false);
        }
    }

    @Override // h5.s
    public void h(Throwable th) {
        A7.m.f(th, "t");
        String str = "Failed to create media controller: " + th;
        A7.m.f(str, "error");
        B9.a aVar = Timber.f22577a;
        aVar.e("");
        aVar.b(str, new Object[0]);
    }

    @Override // h5.s
    public void i(Object obj) {
        C1672v c1672v = (C1672v) obj;
        A7.m.f(c1672v, "result");
        try {
            c1672v.D0(new S(this.l));
            Z z6 = this.l;
            z6.f4667p = c1672v;
            V8.f0 f0Var = z6.f4655e0;
            Boolean bool = Boolean.TRUE;
            f0Var.getClass();
            f0Var.m(null, bool);
            q0 q0Var = this.l.c;
            synchronized (q0Var) {
                AbstractC0727y.w(q0Var.f4739g, null, null, new m0(q0Var, null), 3);
            }
            if (!this.l.c.f4740h.isEmpty() && this.l.f4634M.getValue() == PlayerStatus.STOPPED) {
                this.l.C(PlayerStatus.PAUSED);
            }
            Z z10 = this.l;
            z10.c.f4751u.d(new a0(new C0452h(z10, 4)));
            Z z11 = this.l;
            AbstractC0727y.w(z11.f4625D, null, null, new U(z11, null), 3);
        } catch (Exception e10) {
            String str = "Error creating media controller: " + e10;
            A7.m.f(str, "error");
            B9.a aVar = Timber.f22577a;
            aVar.e("");
            aVar.b(str, new Object[0]);
        }
    }
}
