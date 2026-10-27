package defpackage;

import android.view.View;
import android.view.ViewGroup;
import org.chromium.ui.base.ViewAndroidDelegate;
import org.chromium.weblayer_private.TabImpl;
import org.chromium.weblayer_private.TopControlsContainerView;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class FQ extends ViewAndroidDelegate {
    public final /* synthetic */ TabImpl d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FQ(TabImpl tabImpl, ViewGroup viewGroup) {
        super(null);
        this.d = tabImpl;
    }

    @Override // org.chromium.ui.base.ViewAndroidDelegate
    public void onTopControlsChanged(int i, int i2, int i3) {
        HP hpV = this.d.v();
        if (hpV != null) {
            final TopControlsContainerView topControlsContainerView = hpV.c;
            if (topControlsContainerView.w == null || topControlsContainerView.C) {
                return;
            }
            if (i2 == topControlsContainerView.getHeight()) {
                topControlsContainerView.B = false;
                topControlsContainerView.d(0, i2);
                topControlsContainerView.x.postOnAnimation(new Runnable(topControlsContainerView) { // from class: KQ
                    public final TopControlsContainerView s;

                    {
                        this.s = topControlsContainerView;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        View view = this.s.w;
                        if (view != null) {
                            view.setVisibility(0);
                        }
                    }
                });
            } else {
                if (!topControlsContainerView.B) {
                    topControlsContainerView.B = true;
                    topControlsContainerView.x.postOnAnimation(new Runnable(topControlsContainerView) { // from class: LQ
                        public final TopControlsContainerView s;

                        {
                            this.s = topControlsContainerView;
                        }

                        @Override // java.lang.Runnable
                        public void run() {
                            this.s.b();
                        }
                    });
                }
                topControlsContainerView.d(i, i2);
            }
        }
    }
}
