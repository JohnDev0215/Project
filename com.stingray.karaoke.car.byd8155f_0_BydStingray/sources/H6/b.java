package h6;

import com.stingray.karaoke.car.ui.viewmodel.base.LoadingInterface$LoadingState;
import k6.C1691C;
import k6.C1705m;
import kotlin.Metadata;
import q7.InterfaceC2189c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Lh6/b;", "Lb6/f;", "car_byd8155fEnvProdRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class b extends b6.f {
    public final C1691C l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b(C1705m c1705m, W5.e eVar, C1691C c1691c) {
        super(c1705m, eVar);
        A7.m.f(c1705m, "languageManager");
        A7.m.f(eVar, "appNavigationManager");
        A7.m.f(c1691c, "uxRestrictionManager");
        this.l = c1691c;
    }

    @Override // b6.f
    public final Object f(InterfaceC2189c interfaceC2189c) {
        l(LoadingInterface$LoadingState.Done);
        return m7.q.f19267a;
    }
}
