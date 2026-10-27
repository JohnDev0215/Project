package com.android.car.media.common;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Size;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import com.android.car.apps.common.BackgroundImageView;
import com.android.car.apps.common.MinimizedControlBar;
import com.android.car.media.common.MediaItemMetadata;
import com.android.car.media.common.playback.PlaybackViewModel;
import spi.b0;
import spi.c3;
import spi.m8;
import spi.v4;
import spi.y6;

/* JADX INFO: loaded from: classes.dex */
public class MinimizedPlaybackControlBar extends MinimizedControlBar {
    public static final /* synthetic */ int n = 0;
    public y6 g;
    public ProgressBar h;
    public ProgressBar i;
    public v4<MediaItemMetadata.b> j;
    public PlaybackViewModel k;
    public boolean l;
    public boolean m;

    public MinimizedPlaybackControlBar(Context context) {
        this(context, null);
    }

    public final void f(@NonNull PlaybackViewModel playbackViewModel, @NonNull LifecycleOwner lifecycleOwner, @NonNull Size size) {
        this.g.d(playbackViewModel, lifecycleOwner);
        new a(lifecycleOwner, playbackViewModel, this.d, this.e, null, null, null, null, null, null, this.f, size);
        this.k = playbackViewModel;
        getContext();
        c3.a(lifecycleOwner, this.k, this.h, this.l);
        getContext();
        c3.a(lifecycleOwner, this.k, this.i, this.m);
        if (this.j != null) {
            this.k.e.observe(lifecycleOwner, new b0(this, 2));
        }
    }

    public void setLinearProgressBarDrawable(Drawable drawable) {
        this.h.setProgressDrawable(drawable);
    }

    public void setPlaybackBarBg(Drawable drawable) {
        findViewById(com.android.car.ui.R$id.ll_mini_playback_control_bar).setBackground(drawable);
    }

    public MinimizedPlaybackControlBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MinimizedPlaybackControlBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i, com.android.car.ui.R$layout.minimized_playback_control_bar);
        this.j = null;
        this.g = new y6(context, this, com.android.car.ui.R$color.playback_control_color, com.android.car.ui.R$layout.minimized_play_pause_stop_button_layout, com.android.car.ui.R$drawable.ic_skip_previous, com.android.car.ui.R$drawable.ic_skip_next);
        this.l = context.getResources().getBoolean(com.android.car.ui.R$bool.show_linear_progress_bar);
        this.h = (ProgressBar) findViewById(com.android.car.ui.R$id.linear_progress_bar);
        this.m = context.getResources().getBoolean(com.android.car.ui.R$bool.show_circular_progress_bar);
        this.i = (ProgressBar) findViewById(com.android.car.ui.R$id.circular_progress_bar);
        BackgroundImageView backgroundImageView = (BackgroundImageView) findViewById(com.android.car.ui.R$id.art_background);
        if (backgroundImageView != null) {
            int integer = getResources().getInteger(com.android.car.ui.R$integer.media_items_bitmap_max_size_px);
            this.j = new v4<>(v4.b.BACKGROUND, new Size(integer, integer), new m8(backgroundImageView, 1));
        }
    }
}
