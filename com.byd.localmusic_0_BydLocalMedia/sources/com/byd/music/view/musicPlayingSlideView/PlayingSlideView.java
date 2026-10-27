package com.byd.music.view.musicPlayingSlideView;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatImageView;
import com.byd.music.R;
import com.byd.music.view.musicPlayingSlideView.PlayingSlideIndicatorController;

/* JADX INFO: loaded from: classes5.dex */
public class PlayingSlideView extends AppCompatImageView implements PlayingSlideProxy {
    private boolean isPlaying;
    private PlayingSlideIndicatorController mIndicatorController;

    public PlayingSlideView(Context context) {
        super(context);
        init();
    }

    public PlayingSlideView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public PlayingSlideView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    private void init() {
        setImageResource(R.drawable.music_playing_slide_view);
        this.mIndicatorController = new PlayingSlideIndicatorController();
        bringToFront();
        this.mIndicatorController.setTarget(this);
        applyAnimation();
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.isPlaying) {
            this.mIndicatorController.setAnimationStatus(PlayingSlideIndicatorController.AnimStatus.START);
        }
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mIndicatorController.setAnimationStatus(PlayingSlideIndicatorController.AnimStatus.CANCEL);
    }

    public void applyAnimation() {
        this.mIndicatorController.initAnimation((AnimationDrawable) getDrawable());
    }

    @Override // com.byd.music.view.musicPlayingSlideView.PlayingSlideProxy
    public void cancel() {
        setVisibility(8);
        this.isPlaying = false;
        this.mIndicatorController.setAnimationStatus(PlayingSlideIndicatorController.AnimStatus.CANCEL);
    }

    @Override // com.byd.music.view.musicPlayingSlideView.PlayingSlideProxy
    public void pause() {
        setVisibility(0);
        this.isPlaying = false;
        this.mIndicatorController.setAnimationStatus(PlayingSlideIndicatorController.AnimStatus.END);
    }

    @Override // com.byd.music.view.musicPlayingSlideView.PlayingSlideProxy
    public void start() {
        setVisibility(0);
        applyAnimation();
        this.isPlaying = true;
        this.mIndicatorController.setAnimationStatus(PlayingSlideIndicatorController.AnimStatus.START);
    }
}
