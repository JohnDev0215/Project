package com.amazon.music.widget.nowplaying;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.SeekBar;

/* JADX INFO: loaded from: classes4.dex */
public class NowPlayingSeekBar extends SeekBar implements View.OnTouchListener {
    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return false;
    }

    @Override // android.widget.AbsSeekBar, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return false;
    }

    public NowPlayingSeekBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
