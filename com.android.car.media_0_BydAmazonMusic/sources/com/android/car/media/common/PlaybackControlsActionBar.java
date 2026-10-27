package com.android.car.media.common;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import com.android.car.apps.common.ControlBar;
import spi.y6;

/* JADX INFO: loaded from: classes.dex */
public class PlaybackControlsActionBar extends ControlBar {
    public ImageButton r;
    public ProgressBar s;
    public y6 t;
    public boolean u;

    public PlaybackControlsActionBar(Context context) {
        this(context, null, 0, 0);
    }

    public PlaybackControlsActionBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 0);
    }

    public PlaybackControlsActionBar(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public PlaybackControlsActionBar(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        ImageButton imageButtonE = e(context.getDrawable(com.android.car.ui.R$drawable.ic_overflow_button), com.android.car.ui.R$layout.control_bar_selectable_button);
        this.r = imageButtonE;
        imageButtonE.setId(com.android.car.ui.R$id.overflow);
        setExpandCollapseView(this.r);
        this.t = new y6(context, this, com.android.car.ui.R$color.playback_control_color, com.android.car.ui.R$layout.play_pause_stop_button_layout, com.android.car.ui.R$drawable.ic_skip_previous, com.android.car.ui.R$drawable.ic_skip_next);
        this.u = context.getResources().getBoolean(com.android.car.ui.R$bool.show_circular_progress_bar);
        this.s = (ProgressBar) findViewById(com.android.car.ui.R$id.circular_progress_bar);
    }
}
