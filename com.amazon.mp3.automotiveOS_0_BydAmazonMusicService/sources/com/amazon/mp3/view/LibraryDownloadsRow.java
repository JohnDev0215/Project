package com.amazon.mp3.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.prime.Branding;

/* JADX INFO: loaded from: classes4.dex */
public class LibraryDownloadsRow extends LinearLayout implements IDownloadProgressView, IPrimeMarkableView {
    private ImageView mDownloadArtWork;
    private ImageView mPrimeSash;
    private ProgressBar mProgressBadge;

    @Override // com.amazon.mp3.view.IPrimeMarkableView
    public void markAsNew(boolean isNew) {
    }

    @Override // com.amazon.mp3.view.IDownloadProgressView
    public void setDownloadState(int downloadState) {
    }

    public LibraryDownloadsRow(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        View.inflate(context, R.layout.library_downloads_row_market, this);
        this.mProgressBadge = (ProgressBar) findViewById(R.id.DownloadProgress);
        this.mDownloadArtWork = (ImageView) findViewById(R.id.DownloadArtwork);
        this.mPrimeSash = (ImageView) findViewById(R.id.AlbumPrimeIndicator);
        int primeSashDrawableId = Branding.getPrimeSashDrawableId(context);
        if (primeSashDrawableId > 0) {
            this.mPrimeSash.setImageResource(primeSashDrawableId);
        }
    }

    @Override // com.amazon.mp3.view.IDownloadProgressView
    public void setProgress(int percent) {
        this.mProgressBadge.setProgress(percent);
    }

    @Override // com.amazon.mp3.view.IDownloadProgressView
    public void setArtworkDrawable(Drawable drawable) {
        this.mDownloadArtWork.setImageDrawable(drawable);
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
}
