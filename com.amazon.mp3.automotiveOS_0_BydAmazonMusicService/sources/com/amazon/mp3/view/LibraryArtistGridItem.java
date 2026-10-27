package com.amazon.mp3.view;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.util.BitmapUtil;
import rx.Subscription;

/* JADX INFO: loaded from: classes4.dex */
public class LibraryArtistGridItem extends RelativeLayout implements IDownloadProgressView {
    private IBadgeableView mBadgeableView;
    private Subscription mCropSubscription;
    private int mProgress;

    public LibraryArtistGridItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        View.inflate(context, R.layout.library_artist_griditem_market, this);
        this.mBadgeableView = (IBadgeableView) findViewById(R.id.cover);
        findViewById(R.id.overflow_button_open).setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.view.LibraryArtistGridItem.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                LibraryArtistGridItem.this.performLongClick();
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
        if (!(drawable instanceof BitmapDrawable) || this.mBadgeableView == null) {
            return;
        }
        this.mCropSubscription = BitmapUtil.cropDrawableAsCircle(getContext(), (BitmapDrawable) drawable, this.mBadgeableView.getCoverImageView());
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
        return this.mBadgeableView.getClickableBadgeView();
    }
}
