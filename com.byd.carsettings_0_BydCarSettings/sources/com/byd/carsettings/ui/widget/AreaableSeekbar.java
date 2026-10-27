package com.byd.carsettings.ui.widget;

import android.content.Context;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.SeekBar;

/* JADX INFO: loaded from: classes2.dex */
public class AreaableSeekbar extends SeekBar implements b.b.a.j.c.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f3354a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public volatile int f3355b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f3356c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public SeekBar.OnSeekBarChangeListener f3357d;

    public class a implements SeekBar.OnSeekBarChangeListener {
        public a() {
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            if (AreaableSeekbar.this.a()) {
                if (i != AreaableSeekbar.this.f3356c) {
                    AreaableSeekbar.this.setInvalid(false, z);
                } else {
                    AreaableSeekbar.this.setInvalid(true, z);
                }
                b.b.m.c.a.a("AreaableSeekbar", "--onProgressChanged--Invalid--fromUser: " + z + ", progress: " + i + ", progressOnActionDown: " + AreaableSeekbar.this.f3356c);
            }
            if (AreaableSeekbar.this.f3357d != null) {
                AreaableSeekbar.this.f3357d.onProgressChanged(seekBar, i, z);
            }
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStartTrackingTouch(SeekBar seekBar) {
            if (AreaableSeekbar.this.f3357d != null) {
                AreaableSeekbar.this.f3357d.onStartTrackingTouch(seekBar);
            }
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStopTrackingTouch(SeekBar seekBar) {
            if (AreaableSeekbar.this.f3357d != null) {
                AreaableSeekbar.this.f3357d.onStopTrackingTouch(seekBar);
            }
        }
    }

    public AreaableSeekbar(Context context) {
        super(context);
        this.f3354a = false;
        this.f3356c = -1;
        f(context);
    }

    public final void f(Context context) {
        super.setOnSeekBarChangeListener(new a());
    }

    @Override // android.widget.ProgressBar, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.f3357d == null) {
            super.setOnSeekBarChangeListener(null);
        }
    }

    @Override // android.widget.ProgressBar, android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        b.b.m.g.a.d(parcelable, "progress", Integer.valueOf(this.f3355b));
        super.onRestoreInstanceState(parcelable);
    }

    @Override // android.widget.AbsSeekBar, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (a() && motionEvent.getAction() == 0) {
            this.f3356c = getProgress();
            setEnabled(true);
            b.b.m.c.a.a("AreaableSeekbar", "--onTouchEvent--Invalid--ACTION_DOWN--progressOnActionDown: " + this.f3356c);
        }
        return super.onTouchEvent(motionEvent);
    }

    @Override // b.b.a.j.c.a
    public /* bridge */ /* synthetic */ void setInvalid(boolean z) {
        super.setInvalid(z);
    }

    @Override // b.b.a.j.c.a
    public /* bridge */ /* synthetic */ void setInvalid(boolean z, boolean z2) {
        super.setInvalid(z, z2);
    }

    @Override // android.widget.SeekBar
    public void setOnSeekBarChangeListener(SeekBar.OnSeekBarChangeListener onSeekBarChangeListener) {
        this.f3357d = onSeekBarChangeListener;
        if (onSeekBarChangeListener == null) {
            super.setOnSeekBarChangeListener(null);
        }
    }

    public void setParentAreaable(boolean z) {
        this.f3354a = z;
    }

    @Override // android.widget.ProgressBar
    public synchronized void setProgress(int i) {
        this.f3355b = i;
        super.setProgress(i);
    }

    public AreaableSeekbar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f3354a = false;
        this.f3356c = -1;
        f(context);
    }

    public AreaableSeekbar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f3354a = false;
        this.f3356c = -1;
        f(context);
    }
}
