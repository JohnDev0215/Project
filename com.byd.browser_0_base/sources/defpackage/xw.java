package defpackage;

import org.chromium.chrome.browser.compositor.CompositorViewHolder;
import org.chromium.chrome.browser.tab.Tab;

/* JADX INFO: compiled from: CompositorViewHolder.java */
/* JADX INFO: loaded from: classes.dex */
public final class xw implements e23 {
    public final /* synthetic */ CompositorViewHolder e;

    public xw(CompositorViewHolder compositorViewHolder) {
        this.e = compositorViewHolder;
    }

    @Override // defpackage.e23
    public final void b() {
        this.e.t();
    }

    @Override // defpackage.e23
    public final void i(Tab tab) {
        yj1 yj1Var = CompositorViewHolder.b0;
        this.e.q(tab);
    }
}
