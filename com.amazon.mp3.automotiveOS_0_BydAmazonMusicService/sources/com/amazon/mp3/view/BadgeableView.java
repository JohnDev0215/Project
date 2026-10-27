package com.amazon.mp3.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.amazon.mp3.automotiveOS.R;

/* JADX INFO: loaded from: classes4.dex */
public class BadgeableView extends RelativeLayout implements IBadgeableView {
    private ImageView mBadgeView;
    private ImageView mCoverImageView;
    private DownloadProgressBadgeView mProgressBadge;
    private ProgressBar mProgressBar;
    private TextView mStatusText;

    @Override // com.amazon.mp3.view.IBadgeableView
    public void setOverlayBadgeVisible(boolean visible) {
    }

    public BadgeableView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        View.inflate(context, R.layout.library_badgeable_view, this);
        this.mCoverImageView = (ImageView) findViewById(R.id.ImageView);
        this.mProgressBar = (ProgressBar) findViewById(R.id.ProgressBar);
        this.mBadgeView = (ImageView) findViewById(R.id.BadgeView);
        this.mProgressBadge = (DownloadProgressBadgeView) findViewById(R.id.DownloadProgress);
        this.mStatusText = (TextView) findViewById(R.id.StatusTextView);
    }

    @Override // com.amazon.mp3.view.IBadgeableView
    public void setDownloadState(int state, int progress) {
        this.mProgressBadge.setDownloadState(state);
        this.mProgressBadge.setVisibility(DownloadProgressViewUtil.shouldShowProgress(state) ? 0 : 8);
        if (state != 0) {
            if (state == 1) {
                setProgress(0);
                return;
            }
            if (state != 2) {
                if (state == 3) {
                    setProgress(progress);
                } else {
                    if (state == 4) {
                        setProgress(progress);
                        return;
                    }
                    this.mBadgeView.setVisibility(8);
                    this.mStatusText.setVisibility(8);
                    this.mProgressBar.setVisibility(8);
                }
            }
        }
    }

    @Override // com.amazon.mp3.view.IBadgeableView
    public void setProgress(int progress) {
        this.mProgressBadge.setProgress(progress);
    }

    @Override // com.amazon.mp3.view.IBadgeableView
    public void setCoverDrawable(Drawable d) {
        this.mCoverImageView.setImageDrawable(d);
    }

    @Override // com.amazon.mp3.view.IBadgeableView
    public View getClickableBadgeView() {
        return this.mProgressBadge;
    }

    @Override // com.amazon.mp3.view.IBadgeableView
    public ImageView getCoverImageView() {
        return this.mCoverImageView;
    }
}
