package com.amazon.mp3.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ContextMenu;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.prime.Branding;

/* JADX INFO: loaded from: classes4.dex */
public class RecentlyPlayedItemView extends RelativeLayout implements ContextMenu.ContextMenuInfo {
    private DownloadProgressBadgeView mDownloadBadgeView;
    private ImageView mPlayIcon;
    private PlayNotificationIcon mPlayNotificationIcon;
    private ImageView mPrimeSash;
    private TextView mSecondaryTitleTextView;
    private ImageView mThumbnailImageView;
    private TextView mTitleTextView;

    public RecentlyPlayedItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        View.inflate(context, R.layout.grid_tile, this);
        this.mTitleTextView = (TextView) findViewById(R.id.title);
        this.mSecondaryTitleTextView = (TextView) findViewById(R.id.subtitle);
        this.mThumbnailImageView = (ImageView) findViewById(R.id.artwork);
        this.mPrimeSash = (ImageView) findViewById(R.id.PrimeSash);
        int primeSashDrawableId = Branding.getPrimeSashDrawableId(context);
        if (primeSashDrawableId > 0) {
            this.mPrimeSash.setImageResource(primeSashDrawableId);
        }
        ImageView imageView = (ImageView) findViewById(R.id.PlayIcon);
        this.mPlayIcon = imageView;
        imageView.setVisibility(0);
        this.mPlayNotificationIcon = (PlayNotificationIcon) findViewById(R.id.PlaystateIcon);
        this.mDownloadBadgeView = (DownloadProgressBadgeView) findViewById(R.id.DownloadBadge);
        findViewById(R.id.overflow_button_open).setVisibility(8);
    }

    public TextView getTitleTextView() {
        return this.mTitleTextView;
    }

    public TextView getSecondaryTitleTextView() {
        return this.mSecondaryTitleTextView;
    }

    public ImageView getThumbnailImageView() {
        return this.mThumbnailImageView;
    }

    public DownloadProgressBadgeView getDownloadBadgeView() {
        return this.mDownloadBadgeView;
    }

    public void setTitle(String title) {
        this.mTitleTextView.setText(title);
    }

    public void setSecondaryTitle(String secondaryTitle) {
        this.mSecondaryTitleTextView.setText(secondaryTitle);
    }

    public void setThumbnail(Bitmap bitmap) {
        this.mThumbnailImageView.setImageBitmap(bitmap);
    }

    public void showPrimeSash(boolean show) {
        this.mPrimeSash.setVisibility((show && Branding.shouldShowPrimeBranding()) ? 0 : 8);
    }

    public void setPlayIconDrawable(Drawable drawable) {
        this.mPlayIcon.setImageDrawable(drawable);
    }

    public void showPlayIcon(boolean show) {
        this.mPlayIcon.setVisibility(show ? 0 : 8);
    }

    public void showPlaystateIcon(boolean show) {
        this.mPlayNotificationIcon.setVisibility(show ? 0 : 8);
    }

    public void setIsPlaying(boolean isPlaying) {
        this.mPlayNotificationIcon.setIsPlaying(isPlaying);
    }

    public void setCircularBackground() {
        this.mPlayNotificationIcon.setCircularBackground();
    }

    public void setContentEnabled(boolean enabled) {
        ContentDisabledHelper.setContentEnabled(this, enabled);
    }
}
