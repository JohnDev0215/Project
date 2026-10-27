package com.amazon.mp3.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.prime.Branding;

/* JADX INFO: loaded from: classes4.dex */
public class LibraryAlbumGridItem extends FrameLayout implements IDownloadProgressView, IPrimeMarkableView, IContentDisabledMarkableView {
    private IBadgeableView mBadgeableView;
    private boolean mIsContentEnabled;
    private ImageView mPrimeSash;
    private int mProgress;

    @Override // com.amazon.mp3.view.IPrimeMarkableView
    public void markAsNew(boolean isNew) {
    }

    public LibraryAlbumGridItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mIsContentEnabled = true;
        init(context);
    }

    private void init(Context context) {
        View.inflate(context, R.layout.library_album_griditem_market, this);
        this.mBadgeableView = (IBadgeableView) findViewById(R.id.cover);
        this.mPrimeSash = (ImageView) findViewById(R.id.AlbumPrimeIndicator);
        int primeSashDrawableId = Branding.getPrimeSashDrawableId(context);
        if (primeSashDrawableId > 0) {
            this.mPrimeSash.setImageResource(primeSashDrawableId);
        }
        findViewById(R.id.overflow_button_open).setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.view.LibraryAlbumGridItem.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                LibraryAlbumGridItem.this.performLongClick();
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

    @Override // com.amazon.mp3.view.IDownloadProgressView
    public View getClickableBadgeView() {
        return this.mBadgeableView.getClickableBadgeView();
    }

    @Override // com.amazon.mp3.view.IPrimeMarkableView
    public void markAsPrime(boolean isPrime) {
        if (isPrime && Branding.shouldShowPrimeBranding()) {
            this.mPrimeSash.setVisibility(0);
        } else {
            this.mPrimeSash.setVisibility(8);
        }
    }

    @Override // com.amazon.mp3.view.IContentDisabledMarkableView
    public void setContentEnabled(boolean isContentEnabled) {
        this.mIsContentEnabled = isContentEnabled;
        ContentDisabledHelper.setContentEnabled(this, isContentEnabled);
    }
}
