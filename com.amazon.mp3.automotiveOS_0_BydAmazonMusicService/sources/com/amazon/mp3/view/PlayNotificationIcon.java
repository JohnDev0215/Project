package com.amazon.mp3.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.amazon.mp3.R$styleable;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.playback.playbackcontrol.PlaybackControllerProvider;
import com.amazon.music.media.playback.ControlSource;

/* JADX INFO: loaded from: classes4.dex */
public class PlayNotificationIcon extends ImageView {
    private boolean mIsPlaying;
    private AnimationDrawable mNowPlayingDrawable;
    private boolean mUseLargeIcons;

    public PlayNotificationIcon(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    private void init(final AttributeSet attrs) {
        TypedArray typedArrayObtainStyledAttributes = getContext().getTheme().obtainStyledAttributes(attrs, R$styleable.PlayNotificationIcon, 0, 0);
        try {
            this.mUseLargeIcons = typedArrayObtainStyledAttributes.getBoolean(0, false);
            typedArrayObtainStyledAttributes.recycle();
            init();
        } catch (Throwable th) {
            typedArrayObtainStyledAttributes.recycle();
            throw th;
        }
    }

    private void init() {
        this.mNowPlayingDrawable = (AnimationDrawable) getContext().getResources().getDrawable(this.mUseLargeIcons ? R.drawable.now_playing_animation_list_gridview : R.drawable.now_playing_animation_list);
        setIsPlaying(false);
    }

    public void setIsPlaying(boolean isPlaying) {
        this.mIsPlaying = isPlaying;
        if (isPlaying) {
            setImageDrawable(this.mNowPlayingDrawable);
            this.mNowPlayingDrawable.start();
        } else {
            this.mNowPlayingDrawable.stop();
            setImageDrawable(getContext().getResources().getDrawable(this.mUseLargeIcons ? R.drawable.mp3_icon_songplaying_paused_grid : R.drawable.mp3_icon_songplaying_paused));
        }
    }

    public void setUseLargeIcons(final boolean useLargeIcons) {
        this.mUseLargeIcons = useLargeIcons;
    }

    public void setCircularBackground() {
        setBackground(getResources().getDrawable(R.drawable.circle_dimmed));
    }

    public static boolean shouldAnimate() {
        return PlaybackControllerProvider.getController(ControlSource.NONE).getPlayStatus().shouldBePlaying();
    }
}
