package com.amazon.podcast.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
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
public final class DownloadButtonMedium extends RelativeLayout {
    private RelativeLayout buttonBackground;
    private ImageView imageView;
    private ProgressBar loadingBar;
    private ProgressBar progressBar;

    public DownloadButtonMedium(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        View.inflate(getContext(), R$layout.podcast_download_button_medium, this);
        setClickable(true);
        setFocusable(true);
        this.buttonBackground = (RelativeLayout) findViewById(R$id.podcast_download_button_medium_background);
        this.imageView = (ImageView) findViewById(R$id.podcast_download_button_medium_image);
        ProgressBar progressBar = (ProgressBar) findViewById(R$id.podcast_download_button_medium_progress_bar);
        this.progressBar = progressBar;
        progressBar.setProgressDrawable(getResources().getDrawable(Podcast.getRuntimeStyleSheet().getDownloadProgressBarMediumDrawableId()));
        this.loadingBar = (ProgressBar) findViewById(R$id.podcast_download_button_medium_loading_bar);
    }

    public void bindNotDownloaded() {
        this.imageView.setImageResource(R$drawable.ic_podcast_download);
        this.imageView.setColorFilter(getResources().getColor(R$color.primary));
        this.progressBar.setVisibility(4);
    }

    public void bindQueued() {
        this.loadingBar.setVisibility(4);
        this.imageView.setImageResource(R$drawable.ic_podcast_action_cancel);
        this.imageView.setColorFilter(getResources().getColor(R$color.primary));
        this.progressBar.setVisibility(0);
        this.progressBar.setMax(100);
        this.progressBar.setProgress(0);
    }

    public void bindDownloaded() {
        this.imageView.setImageResource(R$drawable.ic_download_complete_accent);
        this.imageView.setColorFilter(Podcast.getRuntimeStyleSheet().getAccentColor());
        this.progressBar.setVisibility(4);
    }

    public void bindInProgress(final long progress, final long maxProgress) {
        this.loadingBar.setVisibility(4);
        this.imageView.setImageResource(R$drawable.ic_podcast_action_cancel);
        this.progressBar.setVisibility(0);
        bindProgress(progress, maxProgress);
    }

    public void bindProgress(final long progress, final long maxProgress) {
        this.loadingBar.setVisibility(4);
        int i = (int) maxProgress;
        this.progressBar.setMax(i);
        this.progressBar.setMax(i);
        this.progressBar.setProgress((int) progress, true);
    }

    public void setButtonBackground(final Drawable background) {
        this.buttonBackground.setBackground(background);
    }

    public void bindDownloadRequested() {
        this.loadingBar.setVisibility(0);
    }

    public void bindDownloadRequestCompleted() {
        this.loadingBar.setVisibility(4);
    }
}
