package com.amazon.mp3.view;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.util.BitmapUtil;
import rx.Subscription;

/* JADX INFO: loaded from: classes4.dex */
public class LibraryArtistRow extends LinearLayout implements IDownloadProgressView {
    private ImageView mArtistArtWork;
    private Subscription mCropSubscription;
    private DownloadProgressBadgeView mProgressBadge;

    public LibraryArtistRow(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        View.inflate(context, R.layout.library_artist_row_market, this);
        this.mProgressBadge = (DownloadProgressBadgeView) findViewById(R.id.DownloadProgress);
        this.mArtistArtWork = (ImageView) findViewById(R.id.ArtistArtwork);
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
        if (drawable instanceof BitmapDrawable) {
            this.mCropSubscription = BitmapUtil.cropDrawableAsCircle(getContext(), (BitmapDrawable) drawable, this.mArtistArtWork);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Subscription subscription = this.mCropSubscription;
        if (subscription == null || subscription.isUnsubscribed()) {
            return;
        }
        this.mCropSubscription.unsubscribe();
        this.mCropSubscription = null;
    }

    @Override // com.amazon.mp3.view.IDownloadProgressView
    public View getClickableBadgeView() {
        return this.mProgressBadge;
    }
}
