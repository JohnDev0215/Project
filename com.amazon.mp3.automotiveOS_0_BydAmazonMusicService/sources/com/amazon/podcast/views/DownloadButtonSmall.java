package com.amazon.podcast.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.R$color;
import com.amazon.podcast.R$drawable;
import com.amazon.podcast.R$id;
import com.amazon.podcast.R$layout;

/* JADX INFO: loaded from: classes5.dex */
public final class DownloadButtonSmall extends RelativeLayout {
    private ImageView imageView;
    private ProgressBar loadingBar;
    private ProgressBar progressBar;

    public DownloadButtonSmall(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        View.inflate(getContext(), R$layout.podcast_download_button_small, this);
        setClickable(true);
        setFocusable(true);
        this.imageView = (ImageView) findViewById(R$id.podcast_download_button_small_image);
        ProgressBar progressBar = (ProgressBar) findViewById(R$id.podcast_download_button_small_progress_bar);
        this.progressBar = progressBar;
        progressBar.setProgressDrawable(getResources().getDrawable(Podcast.getRuntimeStyleSheet().getDownloadProgressBarSmallDrawableId()));
        this.loadingBar = (ProgressBar) findViewById(R$id.podcast_download_button_small_loading_bar);
    }

    public void bindNotDownloaded() {
        this.imageView.setImageResource(R$drawable.ic_podcast_download);
        this.imageView.setColorFilter(getResources().getColor(R$color.primary));
        this.progressBar.setVisibility(4);
    }

    public void bindQueued() {
        this.imageView.setImageResource(R$drawable.ic_podcast_action_cancel);
        this.imageView.setColorFilter(getResources().getColor(R$color.primary));
        this.loadingBar.setVisibility(4);
        this.progressBar.setVisibility(0);
        this.progressBar.setMax(100);
        this.progressBar.setProgress(0);
    }

    public void bindDownloaded() {
        this.imageView.setImageResource(R$drawable.ic_download_complete);
        this.imageView.setColorFilter(getResources().getColor(R$color.primary));
        this.progressBar.setVisibility(4);
    }

    public void bindInProgress(final long progress, final long maxProgress) {
        this.loadingBar.setVisibility(4);
        this.imageView.setImageResource(R$drawable.ic_podcast_action_cancel);
        this.imageView.setColorFilter(getResources().getColor(R$color.primary));
        this.progressBar.setVisibility(0);
        bindProgress(progress, maxProgress);
    }

    public void bindProgress(final long progress, final long maxProgress) {
        this.loadingBar.setVisibility(4);
        this.progressBar.setMax((int) maxProgress);
        this.progressBar.setProgress((int) progress, true);
    }

    public void bindDownloadRequested() {
        this.loadingBar.setVisibility(0);
    }

    public void bindDownloadRequestCompleted() {
        this.loadingBar.setVisibility(4);
    }
}
