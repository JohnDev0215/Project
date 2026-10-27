package defpackage;

import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import org.chromium.chrome.browser.feed.sort_ui.FeedOptionsView;

/* JADX INFO: compiled from: FeedOptionsView.java */
/* JADX INFO: loaded from: classes.dex */
public final class gl0 extends Animation {
    public final /* synthetic */ int e;
    public final /* synthetic */ FeedOptionsView f;

    public gl0(FeedOptionsView feedOptionsView, int i) {
        this.f = feedOptionsView;
        this.e = i;
    }

    @Override // android.view.animation.Animation
    public final void applyTransformation(float f, Transformation transformation) {
        FeedOptionsView feedOptionsView = this.f;
        if (f == 1.0f) {
            feedOptionsView.setVisibility(8);
            return;
        }
        ViewGroup.LayoutParams layoutParams = feedOptionsView.getLayoutParams();
        int i = this.e;
        layoutParams.height = i - ((int) (i * f));
        ao3.e(feedOptionsView, "FeedOptionsView.collapse.Animation.applyTransformation");
    }

    @Override // android.view.animation.Animation
    public final boolean willChangeBounds() {
        return true;
    }
}
