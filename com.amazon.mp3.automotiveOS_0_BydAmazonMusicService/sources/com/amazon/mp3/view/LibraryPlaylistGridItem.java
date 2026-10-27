package com.amazon.mp3.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.prime.Branding;

/* JADX INFO: loaded from: classes4.dex */
public class LibraryPlaylistGridItem extends RelativeLayout implements IDownloadProgressView, IPrimeMarkableView, IContentDisabledMarkableView {
    private IBadgeableView mBadgeableView;
    private boolean mIsContentEnabled;
    private ImageView mPrimePlaylistUpdatedDot;
    private ImageView mPrimeSash;
    private int mProgress;

    @Override // com.amazon.mp3.view.IDownloadProgressView
    public View getClickableBadgeView() {
        return null;
    }

    public LibraryPlaylistGridItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mIsContentEnabled = true;
        init(context);
    }

    private void init(Context context) {
        View.inflate(context, R.layout.library_playlist_griditem_market, this);
        this.mBadgeableView = (IBadgeableView) findViewById(R.id.cover);
        this.mPrimeSash = (ImageView) findViewById(R.id.PlaylistPrimeIndicator);
        int primeSashDrawableId = Branding.getPrimeSashDrawableId(context);
        if (primeSashDrawableId > 0) {
            this.mPrimeSash.setImageResource(primeSashDrawableId);
        }
        this.mPrimePlaylistUpdatedDot = (ImageView) findViewById(R.id.PrimePlaylistUpdatedDot);
        findViewById(R.id.overflow_button_open).setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.view.LibraryPlaylistGridItem.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                LibraryPlaylistGridItem.this.performLongClick();
            }
        });
    }

    @Override // com.amazon.mp3.view.IDownloadProgressView
    public void setProgress(int percent) {
        this.mProgress = percent;
        this.mBadgeableView.setProgress(percent);
    }

    @Override // com.amazon.mp3.view.IDownloadProgressView
    public void setDownloadState(int downloadState) {
        this.mBadgeableView.setDownloadState(downloadState, this.mProgress);
    }

    @Override // com.amazon.mp3.view.IDownloadProgressView
    public void setArtworkDrawable(Drawable drawable) {
        this.mBadgeableView.setCoverDrawable(drawable);
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
