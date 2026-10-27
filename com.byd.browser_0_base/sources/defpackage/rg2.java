package defpackage;

import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import org.chromium.chrome.browser.search_resumption.SearchResumptionTileContainerView;

/* JADX INFO: compiled from: SearchResumptionTileContainerView.java */
/* JADX INFO: loaded from: classes2.dex */
public final class rg2 extends Animation {
    public final /* synthetic */ int e;
    public final /* synthetic */ SearchResumptionTileContainerView f;

    public rg2(SearchResumptionTileContainerView searchResumptionTileContainerView, int i) {
        this.f = searchResumptionTileContainerView;
        this.e = i;
    }

    @Override // android.view.animation.Animation
    public final void applyTransformation(float f, Transformation transformation) {
        SearchResumptionTileContainerView searchResumptionTileContainerView = this.f;
        if (f == 1.0f) {
            searchResumptionTileContainerView.setVisibility(8);
            return;
        }
        ViewGroup.LayoutParams layoutParams = searchResumptionTileContainerView.getLayoutParams();
        int i = this.e;
        layoutParams.height = i - ((int) (i * f));
        searchResumptionTileContainerView.requestLayout();
    }

    @Override // android.view.animation.Animation
    public final boolean willChangeBounds() {
        return true;
    }
}
