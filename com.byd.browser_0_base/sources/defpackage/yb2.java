package defpackage;

import defpackage.z2;
import defpackage.zb2;
import org.chromium.chrome.browser.tab.Tab;

/* JADX INFO: compiled from: ReturnToChromeUtil.java */
/* JADX INFO: loaded from: classes2.dex */
public final class yb2 extends z2.b {
    public final /* synthetic */ zb2.a h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public yb2(zb2.a aVar, z2 z2Var) {
        super(z2Var, true);
        this.h = aVar;
    }

    @Override // z2.b
    public final void y1(Tab tab) {
        zb2.a aVar = this.h;
        Tab tab2 = (Tab) aVar.h.f;
        aVar.e.r(Boolean.valueOf(tab2 != null && zb2.f(tab2)));
    }
}
