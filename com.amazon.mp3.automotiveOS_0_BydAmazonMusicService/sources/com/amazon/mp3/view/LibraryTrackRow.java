package com.amazon.mp3.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.playback.view.lyrics.TextViewMarkerDecorator;

/* JADX INFO: loaded from: classes4.dex */
public class LibraryTrackRow extends LinearLayout implements IDownloadProgressView, IPrimeMarkableView, IContentDisabledMarkableView {
    private ImageView mAlbumArtwork;
    private boolean mIsContentEnabled;
    protected DownloadProgressBadgeView mProgressBadge;
    protected TextView mTitle;
    protected TextViewMarkerDecorator mTitleDecorator;

    private static boolean shouldShowProgress(int downloadState) {
        return (downloadState == -1 || downloadState == 5) ? false : true;
    }

    public int getTrackRowLayout() {
        return R.layout.library_track_row_market;
    }

    @Override // com.amazon.mp3.view.IPrimeMarkableView
    public void markAsNew(boolean isNew) {
    }

    public LibraryTrackRow(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mIsContentEnabled = true;
        init(context);
    }

    protected void init(Context context) {
        setOrientation(1);
        View.inflate(context, getTrackRowLayout(), this);
        this.mProgressBadge = (DownloadProgressBadgeView) findViewById(R.id.DownloadProgress);
        this.mAlbumArtwork = (ImageView) findViewById(R.id.AlbumArtwork);
        TextView textView = (TextView) findViewById(R.id.TrackTitle);
        this.mTitle = textView;
        this.mTitleDecorator = new TextViewMarkerDecorator(textView);
    }

    @Override // com.amazon.mp3.view.IDownloadProgressView
    public void setProgress(int percent) {
        this.mProgressBadge.setProgress(percent);
    }

    @Override // com.amazon.mp3.view.IDownloadProgressView
    public void setDownloadState(int downloadState) {
        this.mProgressBadge.setDownloadState(downloadState);
        this.mProgressBadge.setVisibility(shouldShowProgress(downloadState) ? 0 : 8);
    }

    @Override // com.amazon.mp3.view.IDownloadProgressView
    public void setArtworkDrawable(Drawable drawable) {
        this.mAlbumArtwork.setImageDrawable(drawable);
    }

    @Override // com.amazon.mp3.view.IDownloadProgressView
    public View getClickableBadgeView() {
        return this.mProgressBadge;
    }

    @Override // com.amazon.mp3.view.IPrimeMarkableView
    public void markAsPrime(boolean isPrime) {
        this.mTitleDecorator.showPrimeMarker(isPrime).decorate();
    }

    @Override // com.amazon.mp3.view.IContentDisabledMarkableView
    public void setContentEnabled(boolean isContentEnabled) {
        this.mIsContentEnabled = isContentEnabled;
        ContentDisabledHelper.setContentEnabled(this, isContentEnabled);
    }

    public TextView getTitle() {
        return this.mTitle;
    }
}
