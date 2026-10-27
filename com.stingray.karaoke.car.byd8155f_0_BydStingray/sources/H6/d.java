package h6;

import V8.f0;
import com.stingray.karaoke.car.ui.viewmodel.base.LoadingInterface$LoadingState;
import k6.C1696d;
import k6.C1705m;
import kotlin.Metadata;
import q7.InterfaceC2189c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Lh6/d;", "Lb6/f;", "car_byd8155fEnvProdRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class d extends b6.f {
    public final C1696d l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final f0 f15569m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final f0 f15570n;
    public final f0 o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final f0 f15571p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final f0 f15572q;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public d(C1705m c1705m, W5.e eVar, C1696d c1696d) {
        super(c1705m, eVar);
        A7.m.f(c1705m, "languageManager");
        A7.m.f(eVar, "appNavigationManager");
        A7.m.f(c1696d, "devSettingsManager");
        this.l = c1696d;
        this.f15569m = c1696d.f18254k;
        this.f15570n = c1696d.l;
        this.o = c1696d.f18256n;
        this.f15571p = c1696d.o;
        this.f15572q = c1696d.f18259r;
    }

    @Override // b6.f
    public final Object f(InterfaceC2189c interfaceC2189c) {
        l(LoadingInterface$LoadingState.Done);
        return m7.q.f19267a;
    }
}
