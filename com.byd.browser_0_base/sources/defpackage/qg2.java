package defpackage;

import android.view.animation.Animation;
import android.view.animation.Transformation;
import org.chromium.chrome.browser.search_resumption.SearchResumptionTileContainerView;

/* JADX INFO: compiled from: SearchResumptionTileContainerView.java */
/* JADX INFO: loaded from: classes2.dex */
public final class qg2 extends Animation {
    public final /* synthetic */ int e;
    public final /* synthetic */ SearchResumptionTileContainerView f;

    public qg2(SearchResumptionTileContainerView searchResumptionTileContainerView, int i) {
        this.f = searchResumptionTileContainerView;
        this.e = i;
    }

    @Override // android.view.animation.Animation
    public final void applyTransformation(float f, Transformation transformation) {
        int i = f == 1.0f ? -2 : (int) (this.e * f);
        SearchResumptionTileContainerView searchResumptionTileContainerView = this.f;
        searchResumptionTileContainerView.getLayoutParams().height = i;
        searchResumptionTileContainerView.requestLayout();
    }

    @Override // android.view.animation.Animation
    public final boolean willChangeBounds() {
        return true;
    }
}
