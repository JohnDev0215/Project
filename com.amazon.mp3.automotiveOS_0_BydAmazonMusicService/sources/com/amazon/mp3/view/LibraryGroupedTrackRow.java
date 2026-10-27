package com.amazon.mp3.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.playback.view.lyrics.TextViewMarkerDecorator;

/* JADX INFO: loaded from: classes4.dex */
public class LibraryGroupedTrackRow extends RelativeLayout implements IDownloadProgressView, IPrimeMarkableView, IContentDisabledMarkableView {
    private boolean mIsContentEnabled;
    private boolean mLyricsSupported;
    private DownloadProgressBadgeView mProgressBadge;
    private TextView mTitle;
    private TextViewMarkerDecorator mTitleDecorator;

    private static boolean shouldShowProgress(int downloadState) {
        return (downloadState == -1 || downloadState == 5) ? false : true;
    }

    @Override // com.amazon.mp3.view.IPrimeMarkableView
    public void markAsNew(boolean isNew) {
    }

    @Override // com.amazon.mp3.view.IDownloadProgressView
    public void setArtworkDrawable(Drawable drawable) {
    }

    public LibraryGroupedTrackRow(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mIsContentEnabled = true;
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.library_grouped_track_row, this);
        this.mProgressBadge = (DownloadProgressBadgeView) findViewById(R.id.DownloadProgress);
        TextView textView = (TextView) findViewById(R.id.PrimaryTitleTextView);
        this.mTitle = textView;
        this.mTitleDecorator = new TextViewMarkerDecorator(textView);
        this.mLyricsSupported = AmazonApplication.getCapabilities().isLyricsSupported();
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
}
