package c6;

import A7.m;
import V8.d0;
import W5.e;
import b6.f;
import com.stingray.companion.CompanionManager;
import com.stingray.karaoke.car.ui.navigation.NavigationType;
import com.stingray.karaoke.car.ui.viewmodel.base.LoadingInterface$LoadingState;
import k6.C1691C;
import k6.C1705m;
import kotlin.Metadata;
import m7.q;
import q7.InterfaceC2189c;

/* JADX INFO: renamed from: c6.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Lc6/a;", "Lb6/f;", "car_byd8155fEnvProdRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class C1034a extends f {
    public final C1691C l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final d0 f12408m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C1034a(C1705m c1705m, e eVar, C1691C c1691c) {
        super(c1705m, eVar);
        m.f(c1705m, "languageManager");
        m.f(eVar, "appNavigationManager");
        m.f(c1691c, "uxRestrictionManager");
        this.l = c1691c;
        CompanionManager.Companion.getClass();
        this.f12408m = CompanionManager.companionUrl;
        m(NavigationType.TAB);
    }

    @Override // b6.f
    public final Object f(InterfaceC2189c interfaceC2189c) {
        l(LoadingInterface$LoadingState.Done);
        return q.f19267a;
    }
}
