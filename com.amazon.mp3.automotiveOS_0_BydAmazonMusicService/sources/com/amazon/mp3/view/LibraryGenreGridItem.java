package com.amazon.mp3.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import com.amazon.mp3.automotiveOS.R;

/* JADX INFO: loaded from: classes4.dex */
public class LibraryGenreGridItem extends RelativeLayout implements IDownloadProgressView {
    private IBadgeableView mBadgeableView;
    private int mProgress;

    @Override // com.amazon.mp3.view.IDownloadProgressView
    public View getClickableBadgeView() {
        return null;
    }

    public LibraryGenreGridItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        View.inflate(context, R.layout.library_genre_griditem_market, this);
        this.mBadgeableView = (IBadgeableView) findViewById(R.id.cover);
        findViewById(R.id.overflow_button_open).setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.view.LibraryGenreGridItem.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                LibraryGenreGridItem.this.performLongClick();
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
}
