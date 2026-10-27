package com.amazon.mp3.view;

import android.graphics.drawable.Drawable;
import android.view.View;

/* JADX INFO: loaded from: classes4.dex */
public interface IDownloadProgressView {
    View getClickableBadgeView();

    void setArtworkDrawable(Drawable drawable);

    void setDownloadState(int downloadState);

    void setProgress(int percent);
}
