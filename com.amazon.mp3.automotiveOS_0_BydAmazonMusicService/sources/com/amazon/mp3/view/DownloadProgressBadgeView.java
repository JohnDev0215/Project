package com.amazon.mp3.view;

import android.R;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import androidx.annotation.StringRes;

/* JADX INFO: loaded from: classes4.dex */
public class DownloadProgressBadgeView extends FrameLayout {
    private ImageView mBadgeImageView;
    private int mDownloadState;
    private int mProgress;
    private ProgressBar mProgressBar;

    public DownloadProgressBadgeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mDownloadState = -1;
        init(context);
    }

    private void init(Context context) {
        setWillNotDraw(false);
        ImageView imageView = new ImageView(context);
        this.mBadgeImageView = imageView;
        imageView.setScaleType(ImageView.ScaleType.CENTER);
        addView(this.mBadgeImageView);
        ProgressBar progressBar = new ProgressBar(context, null, R.attr.progressBarStyleHorizontal);
        this.mProgressBar = progressBar;
        progressBar.setMax(100);
        this.mProgressBar.setProgressDrawable(context.getResources().getDrawable(com.amazon.mp3.automotiveOS.R.drawable.download_progress_circular));
        this.mProgressBar.setVisibility(8);
        addView(this.mProgressBar);
        ViewGroup.LayoutParams layoutParams = this.mProgressBar.getLayoutParams();
        int dimensionPixelSize = getResources().getDimensionPixelSize(com.amazon.mp3.automotiveOS.R.dimen.download_circular_progress_bar_width);
        layoutParams.width = dimensionPixelSize;
        layoutParams.height = dimensionPixelSize;
        this.mProgressBar.setLayoutParams(layoutParams);
        if (isInEditMode()) {
            setDownloadState(4);
            setProgress(50);
        }
    }

    public void setProgress(int progress) {
        if (this.mProgress == progress) {
            return;
        }
        this.mProgress = progress;
        if (progress >= 100 || !isDownloadingOrPaused()) {
            return;
        }
        this.mProgressBar.setProgress(progress);
    }

    public int getProgress() {
        return this.mProgress;
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0021, code lost:
    
        if (r4 != 4) goto L22;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void setDownloadState(int r4) {
        /*
            r3 = this;
            int r0 = r3.mDownloadState
            if (r0 != r4) goto L5
            return
        L5:
            r3.mDownloadState = r4
            android.widget.ProgressBar r0 = r3.mProgressBar
            r1 = 8
            r0.setVisibility(r1)
            r0 = -2
            r1 = 0
            if (r4 == r0) goto L6c
            if (r4 == 0) goto L5b
            r0 = 1
            r2 = 2131231127(0x7f080197, float:1.8078326E38)
            if (r4 == r0) goto L48
            r0 = 2
            if (r4 == r0) goto L37
            r0 = 3
            if (r4 == r0) goto L24
            r0 = 4
            if (r4 == r0) goto L48
            goto L71
        L24:
            android.widget.ProgressBar r4 = r3.mProgressBar
            r4.setVisibility(r1)
            android.widget.ImageView r4 = r3.mBadgeImageView
            r4.setImageResource(r2)
            android.widget.ImageView r4 = r3.mBadgeImageView
            r0 = 2132018051(0x7f140383, float:1.9674398E38)
            r3.setContentDescription(r4, r0)
            goto L7c
        L37:
            android.widget.ImageView r4 = r3.mBadgeImageView
            r0 = 2131231126(0x7f080196, float:1.8078324E38)
            r4.setImageResource(r0)
            android.widget.ImageView r4 = r3.mBadgeImageView
            r0 = 2132019323(0x7f14087b, float:1.9676978E38)
            r3.setContentDescription(r4, r0)
            goto L7c
        L48:
            android.widget.ProgressBar r4 = r3.mProgressBar
            r4.setVisibility(r1)
            android.widget.ImageView r4 = r3.mBadgeImageView
            r4.setImageResource(r2)
            android.widget.ImageView r4 = r3.mBadgeImageView
            r0 = 2132017333(0x7f1400b5, float:1.9672941E38)
            r3.setContentDescription(r4, r0)
            goto L7c
        L5b:
            android.widget.ImageView r4 = r3.mBadgeImageView
            r0 = 2131231394(0x7f0802a2, float:1.8078868E38)
            r4.setImageResource(r0)
            android.widget.ImageView r4 = r3.mBadgeImageView
            r0 = 2132019328(0x7f140880, float:1.9676988E38)
            r3.setContentDescription(r4, r0)
            goto L7c
        L6c:
            android.widget.ProgressBar r4 = r3.mProgressBar
            r4.setVisibility(r1)
        L71:
            android.widget.ImageView r4 = r3.mBadgeImageView
            r0 = 0
            r4.setImageDrawable(r0)
            android.widget.ImageView r4 = r3.mBadgeImageView
            r4.setContentDescription(r0)
        L7c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.view.DownloadProgressBadgeView.setDownloadState(int):void");
    }

    private boolean isDownloadingOrPaused() {
        int i = this.mDownloadState;
        return i == 4 || i == 3;
    }

    public int getDownloadState() {
        return this.mDownloadState;
    }

    private void setContentDescription(final View view, @StringRes final int resourceId) {
        Context context = getContext();
        if (context == null || view == null) {
            return;
        }
        view.setContentDescription(context.getString(resourceId));
    }
}
