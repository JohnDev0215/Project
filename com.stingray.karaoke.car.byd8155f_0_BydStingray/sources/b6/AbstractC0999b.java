package b6;

import K5.u;
import S8.AbstractC0727y;
import com.stingray.companion.model.HostConfig;
import k6.C1705m;
import k6.q;
import k6.r;

/* JADX INFO: renamed from: b6.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0999b extends i {

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final W5.e f12228t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final r f12229u;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AbstractC0999b(C1705m c1705m, j6.n nVar, u uVar, W5.e eVar, r rVar, L5.i iVar) {
        super(c1705m, eVar, uVar, iVar, nVar);
        A7.m.f(c1705m, "languageManager");
        A7.m.f(nVar, "profileManager");
        A7.m.f(uVar, "karaokeItemRepository");
        A7.m.f(eVar, "appNavigationManager");
        A7.m.f(rVar, "parentalControlsManager");
        A7.m.f(iVar, "deepLinkManager");
        this.f12228t = eVar;
        this.f12229u = rVar;
    }

    public final void o(boolean z6) {
        r rVar = this.f12229u;
        AbstractC0727y.w(rVar.d, null, null, new q(rVar, z6, null), 3);
        B6.b bVar = rVar.f18292a;
        bVar.getClass();
        bVar.a("Parental Controls", z6 ? "ENABLED" : "DISABLED");
        rVar.f18293b.updateHostConfig(new HostConfig(null, null, Boolean.valueOf(z6), null, null, 27, null));
    }
}
