package org.chromium.content.browser;

import android.app.Activity;
import android.view.View;
import android.widget.FrameLayout;

/* JADX INFO: loaded from: classes3.dex */
public class ActivityContentVideoViewEmbedder implements ContentVideoViewEmbedder {
    private final Activity mActivity;
    private View mView;

    @Override // org.chromium.content.browser.ContentVideoViewEmbedder
    public View getVideoLoadingProgressView() {
        return null;
    }

    public ActivityContentVideoViewEmbedder(Activity activity) {
        this.mActivity = activity;
    }

    @Override // org.chromium.content.browser.ContentVideoViewEmbedder
    public void enterFullscreenVideo(View view) {
        ((FrameLayout) this.mActivity.getWindow().getDecorView()).addView(view, 0, new FrameLayout.LayoutParams(-1, -1, 17));
        setSystemUiVisibility(true);
        this.mView = view;
    }

    @Override // org.chromium.content.browser.ContentVideoViewEmbedder
    public void exitFullscreenVideo() {
        ((FrameLayout) this.mActivity.getWindow().getDecorView()).removeView(this.mView);
        setSystemUiVisibility(false);
        this.mView = null;
    }

    @Override // org.chromium.content.browser.ContentVideoViewEmbedder
    public void setSystemUiVisibility(boolean z) {
        View decorView = this.mActivity.getWindow().getDecorView();
        if (z) {
            this.mActivity.getWindow().setFlags(1024, 1024);
        } else {
            this.mActivity.getWindow().clearFlags(1024);
        }
        int systemUiVisibility = decorView.getSystemUiVisibility();
        decorView.setSystemUiVisibility(z ? systemUiVisibility | 5638 : systemUiVisibility & (-5639));
    }
}
