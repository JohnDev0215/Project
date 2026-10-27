package M5;

import A7.m;
import A7.v;
import F0.C0221p;
import F1.W;
import F1.Y;
import F1.r;
import N5.Z;
import Q5.C0603o1;
import T.C0734c0;
import T.X;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.Window;
import com.stingray.karaoke.car.player.PlayerStatus;
import com.stingray.karaoke.car.player.SongQueuePosition;
import l0.InterfaceC1716g;
import m7.q;
import n0.AbstractC1861K;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class d implements InterfaceC2772a {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f4200m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f4201n;

    public /* synthetic */ d(Object obj, int i7, Object obj2) {
        this.l = i7;
        this.f4200m = obj;
        this.f4201n = obj2;
    }

    @Override // z7.InterfaceC2772a
    public final Object a() {
        switch (this.l) {
            case 0:
                long jCurrentTimeMillis = System.currentTimeMillis();
                C0734c0 c0734c0 = (C0734c0) this.f4200m;
                if (jCurrentTimeMillis - c0734c0.i() >= 300) {
                    c0734c0.j(jCurrentTimeMillis);
                    ((InterfaceC2772a) this.f4201n).a();
                }
                break;
            case 1:
                ((Z) this.f4200m).C(PlayerStatus.COUNTDOWN_ENDED);
                ((v) this.f4201n).l = true;
                break;
            case 2:
                ((C0603o1) this.f4200m).invoke(SongQueuePosition.SING_NOW, Boolean.FALSE);
                ((C0221p) this.f4201n).a();
                break;
            case 3:
                InterfaceC1716g.a((InterfaceC1716g) this.f4200m);
                ((InterfaceC2785n) this.f4201n).invoke("", 0L);
                break;
            case 4:
                ((InterfaceC2782k) this.f4200m).b((String) this.f4201n);
                break;
            case 5:
                break;
            case 6:
                ((InterfaceC2782k) this.f4200m).b((String) ((X) this.f4201n).getValue());
                break;
            case 7:
                ((InterfaceC2772a) this.f4201n).a();
                InterfaceC1716g.a((InterfaceC1716g) this.f4200m);
                break;
            default:
                View view = (View) this.f4200m;
                Context context = view.getContext();
                m.d(context, "null cannot be cast to non-null type android.app.Activity");
                Window window = ((Activity) context).getWindow();
                window.setStatusBarColor(AbstractC1861K.E(((l6.a) this.f4201n).f18704g));
                if (Build.VERSION.SDK_INT >= 30) {
                    new r(view, 10).f2345n = view;
                }
                int i7 = Build.VERSION.SDK_INT;
                (i7 >= 35 ? new Y(window) : i7 >= 30 ? new F1.X(window) : new W(window)).A0(true);
                if (Build.VERSION.SDK_INT >= 30) {
                    new r(view, 10).f2345n = view;
                }
                int i10 = Build.VERSION.SDK_INT;
                (i10 >= 35 ? new Y(window) : i10 >= 30 ? new F1.X(window) : new W(window)).z0(true);
                break;
        }
        return q.f19267a;
    }

    public /* synthetic */ d(InterfaceC2772a interfaceC2772a, InterfaceC1716g interfaceC1716g) {
        this.l = 7;
        this.f4201n = interfaceC2772a;
        this.f4200m = interfaceC1716g;
    }
}
