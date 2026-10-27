package defpackage;

import androidx.recyclerview.widget.RecyclerView;
import org.chromium.components.browser_ui.widget.FadingShadowView;

/* JADX INFO: compiled from: TutorialListCoordinatorImpl.java */
/* JADX INFO: loaded from: classes2.dex */
public final class qg3 extends RecyclerView.q {
    public final /* synthetic */ FadingShadowView e;

    public qg3(FadingShadowView fadingShadowView) {
        this.e = fadingShadowView;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.q
    public final void b(RecyclerView recyclerView, int i, int i2) {
        this.e.setVisibility(recyclerView.canScrollVertically(-1) ? 0 : 8);
    }
}
