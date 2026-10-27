package defpackage;

import org.chromium.components.browser_ui.widget.chips.ChipView;
import org.chromium.ui.widget.LoadingView;

/* JADX INFO: compiled from: WebFeedMainMenuItem.java */
/* JADX INFO: loaded from: classes.dex */
public final class er3 implements LoadingView.c {
    public boolean e;
    public final /* synthetic */ ChipView f;
    public final /* synthetic */ Runnable g;

    public er3(ChipView chipView, Runnable runnable) {
        this.f = chipView;
        this.g = runnable;
    }

    @Override // org.chromium.ui.widget.LoadingView.c
    public final void M() {
        if (this.e) {
            return;
        }
        this.e = true;
        this.f.setVisibility(8);
        this.g.run();
    }

    @Override // org.chromium.ui.widget.LoadingView.c
    public final void V0() {
    }
}
