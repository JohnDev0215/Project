package X5;

import com.stingray.karaoke.car.ui.viewmodel.base.LoadingInterface$LoadingState;
import k6.C1696d;
import k6.C1705m;
import kotlin.Metadata;
import q7.InterfaceC2189c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002¨\u0006\u0003"}, d2 = {"LX5/H0;", "Lb6/f;", "", "car_byd8155fEnvProdRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class H0 extends b6.f {
    public final W5.e l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final N5.q0 f10174m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final N5.Z f10175n;
    public final V8.f0 o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public H0(C1705m c1705m, W5.e eVar, N5.q0 q0Var, N5.Z z6, C1696d c1696d) {
        super(c1705m, eVar);
        A7.m.f(c1705m, "languageManager");
        A7.m.f(eVar, "appNavigationManager");
        A7.m.f(q0Var, "songQueue");
        A7.m.f(z6, "playerManager");
        A7.m.f(c1696d, "devSettingsManager");
        this.l = eVar;
        this.f10174m = q0Var;
        this.f10175n = z6;
        this.o = c1696d.f18259r;
    }

    @Override // b6.f
    public final Object f(InterfaceC2189c interfaceC2189c) {
        l(LoadingInterface$LoadingState.Done);
        return m7.q.f19267a;
    }
}
