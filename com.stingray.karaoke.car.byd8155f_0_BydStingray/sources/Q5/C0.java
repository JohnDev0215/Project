package Q5;

import S8.AbstractC0727y;
import com.stingray.karaoke.car.ui.player.PlaybackType;
import com.stingray.shared.logs.segment.ActionOwner;
import k3.C1672v;
import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class C0 implements InterfaceC2772a {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ X5.D0 f6288m;

    public /* synthetic */ C0(X5.D0 d02, int i7) {
        this.l = i7;
        this.f6288m = d02;
    }

    @Override // z7.InterfaceC2772a
    public final Object a() {
        switch (this.l) {
            case 0:
                this.f6288m.r(PlaybackType.SKIP_PREVIOUS);
                return m7.q.f19267a;
            case 1:
                this.f6288m.r(PlaybackType.SKIP_NEXT);
                return m7.q.f19267a;
            default:
                X5.D0 d02 = this.f6288m;
                N5.Z z6 = d02.f10158x;
                C1672v c1672v = z6.f4667p;
                if (c1672v == null) {
                    A7.m.j("mediaController");
                    throw null;
                }
                c1672v.c();
                if (((Boolean) z6.f4638Q.getValue()).booleanValue()) {
                    z6.t(ActionOwner.HOST);
                }
                X5.V0 v02 = d02.f10152u;
                v02.getClass();
                AbstractC0727y.w(v02.d, null, null, new X5.U0(v02, null), 3);
                return m7.q.f19267a;
        }
    }
}
