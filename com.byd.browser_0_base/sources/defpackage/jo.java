package defpackage;

import org.chromium.components.browser_ui.widget.chips.ChipView;
import org.chromium.ui.widget.LoadingView;

/* JADX INFO: compiled from: ChipView.java */
/* JADX INFO: loaded from: classes2.dex */
public final class jo implements LoadingView.c {
    public final /* synthetic */ ChipView e;

    public jo(ChipView chipView) {
        this.e = chipView;
    }

    @Override // org.chromium.ui.widget.LoadingView.c
    public final void M() {
        this.e.g.setVisibility(0);
    }

    @Override // org.chromium.ui.widget.LoadingView.c
    public final void V0() {
        this.e.g.setVisibility(8);
    }
}
