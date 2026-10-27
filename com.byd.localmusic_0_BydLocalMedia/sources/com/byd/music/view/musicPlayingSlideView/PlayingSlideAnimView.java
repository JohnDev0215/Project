package com.byd.music.view.musicPlayingSlideView;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/* JADX INFO: loaded from: classes5.dex */
public class PlayingSlideAnimView extends LinearLayout {
    private final PlayingSlideController mPlayingSlideController;

    public PlayingSlideAnimView(Context context) {
        this(context, null);
    }

    public PlayingSlideAnimView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PlayingSlideAnimView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mPlayingSlideController = new PlayingSlideController();
        initView(context, attributeSet);
    }

    private void initView(Context context, AttributeSet attributeSet) {
        PlayingSlideView playingSlideView = new PlayingSlideView(context, attributeSet);
        addView(playingSlideView, new LinearLayout.LayoutParams(-1, -1));
        this.mPlayingSlideController.setTarget(playingSlideView);
    }

    public void cancel() {
        this.mPlayingSlideController.cancel();
    }

    public void pause() {
        this.mPlayingSlideController.pause();
    }

    public void start() {
        this.mPlayingSlideController.start();
    }
}
