package defpackage;

import org.chromium.base.Callback;
import org.chromium.chrome.browser.compositor.CompositorViewHolder;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class sw implements Callback {
    public final /* synthetic */ int e;
    public final /* synthetic */ CompositorViewHolder f;

    public /* synthetic */ sw(CompositorViewHolder compositorViewHolder, int i) {
        this.e = i;
        this.f = compositorViewHolder;
    }

    @Override // org.chromium.base.Callback
    public final void onResult(Object obj) {
        int i = this.e;
        CompositorViewHolder compositorViewHolder = this.f;
        switch (i) {
            case 0:
                if (compositorViewHolder.P != 1) {
                    compositorViewHolder.C();
                    break;
                }
                break;
            default:
                compositorViewHolder.f.a((zd3) obj);
                break;
        }
    }
}
