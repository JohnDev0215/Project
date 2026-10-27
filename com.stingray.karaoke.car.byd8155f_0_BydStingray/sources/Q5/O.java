package Q5;

import F0.C0223q;
import Q.EnumC0542y0;
import T.C0741g;
import U5.AbstractC0806c;
import android.content.Context;
import android.view.Window;
import androidx.media3.ui.PlayerView;
import b9.C1015b;
import com.stingray.karaoke.car.player.PlayerStatus;
import java.util.List;
import n0.C1863M;
import n0.C1870U;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class O implements InterfaceC2782k {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f6453m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f6454n;

    public /* synthetic */ O(Object obj, int i7, Object obj2) {
        this.l = i7;
        this.f6453m = obj;
        this.f6454n = obj2;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        switch (this.l) {
            case 0:
                ((C0223q) this.f6453m).b(((List) this.f6454n).get(((Integer) obj).intValue()));
                return m7.q.f19267a;
            case 1:
                EnumC0542y0 enumC0542y0 = (EnumC0542y0) obj;
                A7.m.f(enumC0542y0, "dismissDirection");
                if (enumC0542y0 == EnumC0542y0.f6071m) {
                    ((InterfaceC2782k) this.f6453m).b((String) this.f6454n);
                }
                return Boolean.TRUE;
            case 2:
                Boolean bool = (Boolean) obj;
                bool.booleanValue();
                ((InterfaceC2772a) this.f6453m).a();
                ((InterfaceC2782k) this.f6454n).b(bool);
                return m7.q.f19267a;
            case 3:
                A7.m.f((T.G) obj, "$this$DisposableEffect");
                return new F0.X((InterfaceC2782k) this.f6453m, 5, (T.X) this.f6454n);
            case 4:
                ((T8.d) this.f6453m).f8930n.removeCallbacks((h5.t) this.f6454n);
                return m7.q.f19267a;
            case 5:
                C1863M c1863m = (C1863M) obj;
                A7.m.f(c1863m, "$this$graphicsLayer");
                c1863m.a(((Number) ((T.Q0) this.f6453m).getValue()).floatValue());
                T.Q0 q02 = (T.Q0) this.f6454n;
                c1863m.i(((Number) q02.getValue()).floatValue());
                c1863m.j(((Number) q02.getValue()).floatValue());
                c1863m.o(C1870U.f19422b);
                return m7.q.f19267a;
            case 6:
                E.k kVar = (E.k) obj;
                A7.m.f(kVar, "$this$LazyColumn");
                E.k.m(kVar, AbstractC0806c.f9099a);
                C0576h2 c0576h2 = new C0576h2(4);
                List list = (List) this.f6454n;
                kVar.n(list.size(), new C0741g(c0576h2, 5, list), new C0558d0(6, list), new b0.b(-1091073711, new U5.w(list, (InterfaceC2782k) this.f6453m), true));
                return m7.q.f19267a;
            case 7:
                A7.m.f((T.G) obj, "$this$DisposableEffect");
                return new F0.X((T.X) this.f6453m, 6, (e6.h) this.f6454n);
            case 8:
                A7.m.f((T.G) obj, "$this$DisposableEffect");
                return new F0.X((T.X) this.f6453m, 7, (T.X) this.f6454n);
            case 9:
                A7.m.f((T.G) obj, "$this$DisposableEffect");
                PlayerStatus playerStatus = (PlayerStatus) ((T.X) this.f6454n).getValue();
                PlayerStatus playerStatus2 = PlayerStatus.PLAYING;
                Window window = (Window) this.f6453m;
                if (playerStatus == playerStatus2) {
                    window.addFlags(128);
                } else {
                    window.clearFlags(128);
                }
                return new F0.V(window, 5);
            case 10:
                A7.m.f((Context) obj, "it");
                PlayerView playerView = new PlayerView((Context) this.f6453m);
                playerView.setUseController(false);
                playerView.setBackground(null);
                playerView.setResizeMode(2);
                playerView.setKeepContentOnPlayerReset(true);
                playerView.setPlayer((e2.W) this.f6454n);
                return playerView;
            default:
                ((b9.c) this.f6453m).e(((C1015b) this.f6454n).f12333m);
                return m7.q.f19267a;
        }
    }

    public /* synthetic */ O(List list, InterfaceC2782k interfaceC2782k) {
        this.l = 6;
        this.f6454n = list;
        this.f6453m = interfaceC2782k;
    }
}
