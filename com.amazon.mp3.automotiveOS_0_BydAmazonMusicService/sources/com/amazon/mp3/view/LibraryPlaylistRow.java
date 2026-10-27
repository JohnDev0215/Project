package com.amazon.mp3.view;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.prime.Branding;
import com.amazon.mp3.util.ImageProcessor;
import com.amazon.mp3.util.RoundedCornerImageProcessor;

/* JADX INFO: loaded from: classes4.dex */
public class LibraryPlaylistRow extends LinearLayout implements IDownloadProgressView, IPrimeMarkableView, IContentDisabledMarkableView {
    private ImageProcessor mImageProcessor;
    private boolean mIsContentEnabled;
    private ImageView mPlaylistArtwork;
    private ImageView mPrimePlaylistUpdatedDot;
    private ImageView mPrimeSash;
    private DownloadProgressBadgeView mProgressBadge;

    public LibraryPlaylistRow(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mIsContentEnabled = true;
        init(context);
    }

    private void init(Context context) {
        View.inflate(context, R.layout.library_playlist_row_market, this);
        this.mProgressBadge = (DownloadProgressBadgeView) findViewById(R.id.DownloadProgress);
        this.mPlaylistArtwork = (ImageView) findViewById(R.id.PlaylistArtwork);
        this.mPrimeSash = (ImageView) findViewById(R.id.PlaylistPrimeIndicator);
        this.mImageProcessor = new RoundedCornerImageProcessor(getContext());
        int primeSashDrawableId = Branding.getPrimeSashDrawableId(context);
        if (primeSashDrawableId > 0) {
            this.mPrimeSash.setImageResource(primeSashDrawableId);
        }
        this.mPrimePlaylistUpdatedDot = (ImageView) findViewById(R.id.PrimePlaylistUpdatedDot);
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
        this.mImageProcessor.process((BitmapDrawable) drawable, this.mPlaylistArtwork);
    }

    @Override // com.amazon.mp3.view.IDownloadProgressView
    public View getClickableBadgeView() {
        return this.mProgressBadge;
    }

    @Override // com.amazon.mp3.view.IPrimeMarkableView
    public void markAsPrime(final boolean isPrime) {
        if (isPrime && Branding.shouldShowPrimeBranding()) {
            this.mPrimeSash.setVisibility(0);
        } else {
            this.mPrimeSash.setVisibility(8);
        }
    }

    @Override // com.amazon.mp3.view.IPrimeMarkableView
    public void markAsNew(boolean isNew) {
        if (isNew) {
            this.mPrimePlaylistUpdatedDot.setVisibility(0);
        } else {
            this.mPrimePlaylistUpdatedDot.setVisibility(8);
        }
    }

    @Override // com.amazon.mp3.view.IContentDisabledMarkableView
    public void setContentEnabled(boolean isContentEnabled) {
        this.mIsContentEnabled = isContentEnabled;
        ContentDisabledHelper.setContentEnabled(this, isContentEnabled);
    }
}
