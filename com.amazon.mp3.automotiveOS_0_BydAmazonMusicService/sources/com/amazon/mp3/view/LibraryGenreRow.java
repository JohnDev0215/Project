package com.amazon.mp3.view;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.util.ImageProcessor;
import com.amazon.mp3.util.RoundedCornerImageProcessor;

/* JADX INFO: loaded from: classes4.dex */
public class LibraryGenreRow extends LinearLayout implements IDownloadProgressView {
    private ImageView mGenreArtwork;
    private ImageProcessor mImageProcessor;
    private DownloadProgressBadgeView mProgressBadge;

    public LibraryGenreRow(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        View.inflate(context, R.layout.library_genre_row_market, this);
        this.mProgressBadge = (DownloadProgressBadgeView) findViewById(R.id.DownloadProgress);
        this.mGenreArtwork = (ImageView) findViewById(R.id.GenreArtwork);
        this.mImageProcessor = new RoundedCornerImageProcessor(getContext());
    }

    @Override // com.amazon.mp3.view.IDownloadProgressView
    public void setProgress(int percent) {
        this.mProgressBadge.setProgress(percent);
    }

    @Override // com.amazon.mp3.view.IDownloadProgressView
    public void setDownloadState(int downloadState) {
        this.mProgressBadge.setDownloadState(downloadState);
        this.mProgressBadge.setVisibility(DownloadProgressViewUtil.shouldShowProgress(downloadState) ? 0 : 8);
    }

    @Override // com.amazon.mp3.view.IDownloadProgressView
    public void setArtworkDrawable(Drawable drawable) {
        this.mImageProcessor.process((BitmapDrawable) drawable, this.mGenreArtwork);
    }

    @Override // com.amazon.mp3.view.IDownloadProgressView
    public View getClickableBadgeView() {
        return this.mProgressBadge;
    }
}
