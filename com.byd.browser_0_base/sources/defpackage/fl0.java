package defpackage;

import android.view.animation.Animation;
import android.view.animation.Transformation;
import org.chromium.chrome.browser.feed.sort_ui.FeedOptionsView;

/* JADX INFO: compiled from: FeedOptionsView.java */
/* JADX INFO: loaded from: classes.dex */
public final class fl0 extends Animation {
    public final /* synthetic */ int e;
    public final /* synthetic */ FeedOptionsView f;

    public fl0(FeedOptionsView feedOptionsView, int i) {
        this.f = feedOptionsView;
        this.e = i;
    }

    @Override // android.view.animation.Animation
    public final void applyTransformation(float f, Transformation transformation) {
        int i = f == 1.0f ? -2 : (int) (this.e * f);
        FeedOptionsView feedOptionsView = this.f;
        feedOptionsView.getLayoutParams().height = i;
        ao3.e(feedOptionsView, "FeedOptionsView.expand.Animation.applyTransformation");
    }

    @Override // android.view.animation.Animation
    public final boolean willChangeBounds() {
        return true;
    }
}
