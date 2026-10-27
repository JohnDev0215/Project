package com.amazon.music.picassoscrolllistener;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;

/* JADX INFO: loaded from: classes4.dex */
public class PicassoScrollListener extends RecyclerView.OnScrollListener {
    private boolean isPaused;
    private long lastScrollTimeMs;
    private float lastSpeed;
    private final Context mContext;
    private final Picasso mPicasso = Picasso.get();

    public PicassoScrollListener(Context context) {
        this.mContext = context;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
    public void onScrollStateChanged(RecyclerView recyclerView, int i) {
        if (i == 2) {
            pause();
        } else if (this.isPaused) {
            resume();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
    public void onScrolled(RecyclerView recyclerView, int i, int i2) {
        if (this.isPaused) {
            long jCurrentTimeMillis = System.currentTimeMillis();
            float fAbs = Math.abs((i2 * 1000.0f) / (jCurrentTimeMillis - this.lastScrollTimeMs));
            float f = this.lastSpeed;
            if (f > 0.0f && f < 200.0f && fAbs < 200.0f) {
                resume();
            }
            this.lastSpeed = fAbs;
            this.lastScrollTimeMs = jCurrentTimeMillis;
        }
    }

    private void pause() {
        this.lastSpeed = 0.0f;
        this.lastScrollTimeMs = 0L;
        this.mPicasso.pauseTag(this.mContext);
        this.isPaused = true;
    }

    private void resume() {
        this.mPicasso.resumeTag(this.mContext);
        this.isPaused = false;
    }
}
