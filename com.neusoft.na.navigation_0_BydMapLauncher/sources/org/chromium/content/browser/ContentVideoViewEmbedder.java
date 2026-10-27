package org.chromium.content.browser;

import android.view.View;

/* JADX INFO: loaded from: classes3.dex */
public interface ContentVideoViewEmbedder {
    void enterFullscreenVideo(View view);

    void exitFullscreenVideo();

    View getVideoLoadingProgressView();

    void setSystemUiVisibility(boolean z);
}
