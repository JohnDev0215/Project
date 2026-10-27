package X5;

import T.C0738e0;
import com.stingray.drivemodedetector.R;
import com.stingray.karaoke.car.ui.player.SideMenuType;
import com.stingray.kca.model.KaraokeSongResponseV2;
import com.stingray.shared.logs.segment.ActionOwner;
import java.lang.reflect.InvocationTargetException;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: X5.v, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class C0888v implements InterfaceC2782k {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ D0 f10393m;

    public /* synthetic */ C0888v(D0 d02, int i7) {
        this.l = i7;
        this.f10393m = d02;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) throws IllegalAccessException, InvocationTargetException {
        KaraokeSongResponseV2 karaokeSongResponseV2;
        String str = null;
        m7.q qVar = m7.q.f19267a;
        D0 d02 = this.f10393m;
        switch (this.l) {
            case 0:
                int iIntValue = ((Integer) obj).intValue();
                d02.getClass();
                boolean z6 = iIntValue == E0.f10164a.indexOf(Integer.valueOf(R.string.Scoring_Score));
                Boolean bool = Boolean.FALSE;
                V8.f0 f0Var = d02.f10119O;
                f0Var.getClass();
                f0Var.m(null, bool);
                d02.f10162z.b();
                N5.Z.y(d02.f10158x, z6, true, ActionOwner.HOST, false, false, 24);
                d02.t();
                break;
            case 1:
                SideMenuType sideMenuType = (SideMenuType) obj;
                A7.m.f(sideMenuType, "it");
                io.sentry.hints.i iVar = k6.o.f18284a;
                if (!io.sentry.hints.i.h()) {
                    d02.p(sideMenuType);
                }
                break;
            default:
                j6.a aVar = (j6.a) obj;
                A7.m.f(aVar, "it");
                N5.f0 f0Var2 = (N5.f0) ((V8.f0) d02.f10158x.f4637P.l).getValue();
                if (f0Var2 != null && (karaokeSongResponseV2 = f0Var2.f4691a) != null) {
                    str = karaokeSongResponseV2.f13136a;
                }
                String str2 = aVar.f17033b;
                boolean zA = A7.m.a(str2, str);
                C0738e0 c0738e0 = d02.f10109F;
                if (zA) {
                    c0738e0.setValue(Boolean.valueOf(aVar.f17032a));
                } else if (str2 == null) {
                    c0738e0.setValue(Boolean.FALSE);
                }
                d02.f10158x.F(((Boolean) c0738e0.getValue()).booleanValue());
                break;
        }
        return qVar;
    }
}
