package com.amazon.mp3.view;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

/* JADX INFO: loaded from: classes4.dex */
public interface IBadgeableView {
    View getClickableBadgeView();

    ImageView getCoverImageView();

    void setCoverDrawable(Drawable d);

    void setDownloadState(int state, int progress);

    void setOverlayBadgeVisible(boolean visible);

    void setProgress(int progress);
}
