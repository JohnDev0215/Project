package defpackage;

import org.chromium.chrome.browser.compositor.CompositorViewHolder;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class ww implements Runnable {
    public final /* synthetic */ int e;
    public final /* synthetic */ CompositorViewHolder f;

    public /* synthetic */ ww(CompositorViewHolder compositorViewHolder, int i) {
        this.e = i;
        this.f = compositorViewHolder;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.e;
        CompositorViewHolder compositorViewHolder = this.f;
        switch (i) {
            case 0:
                yj1 yj1Var = CompositorViewHolder.b0;
                compositorViewHolder.o();
                break;
            default:
                compositorViewHolder.requestFocus();
                break;
        }
    }
}
