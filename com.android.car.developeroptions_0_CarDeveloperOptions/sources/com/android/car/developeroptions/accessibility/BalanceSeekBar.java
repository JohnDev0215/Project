package com.android.car.developeroptions.accessibility;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.provider.Settings;
import android.util.AttributeSet;
import android.widget.SeekBar;

/* JADX INFO: loaded from: classes.dex */
public class BalanceSeekBar extends SeekBar {
    static final float SNAP_TO_PERCENTAGE = 0.03f;
    private int mCenter;
    private final Paint mCenterMarkerPaint;
    private final Rect mCenterMarkerRect;
    private final Context mContext;
    private final Object mListenerLock;
    private SeekBar.OnSeekBarChangeListener mOnSeekBarChangeListener;
    private final SeekBar.OnSeekBarChangeListener mProxySeekBarListener;
    private float mSnapThreshold;

    public BalanceSeekBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.seekBarStyle);
    }

    public BalanceSeekBar(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public BalanceSeekBar(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mListenerLock = new Object();
        this.mProxySeekBarListener = new SeekBar.OnSeekBarChangeListener() { // from class: com.android.car.developeroptions.accessibility.BalanceSeekBar.1
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
                synchronized (BalanceSeekBar.this.mListenerLock) {
                    if (BalanceSeekBar.this.mOnSeekBarChangeListener != null) {
                        BalanceSeekBar.this.mOnSeekBarChangeListener.onStopTrackingTouch(seekBar);
                    }
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
                synchronized (BalanceSeekBar.this.mListenerLock) {
                    if (BalanceSeekBar.this.mOnSeekBarChangeListener != null) {
                        BalanceSeekBar.this.mOnSeekBarChangeListener.onStartTrackingTouch(seekBar);
                    }
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i3, boolean z) {
                if (z) {
                    if (i3 != BalanceSeekBar.this.mCenter) {
                        float f = i3;
                        if (f > BalanceSeekBar.this.mCenter - BalanceSeekBar.this.mSnapThreshold && f < BalanceSeekBar.this.mCenter + BalanceSeekBar.this.mSnapThreshold) {
                            i3 = BalanceSeekBar.this.mCenter;
                            seekBar.setProgress(i3);
                        }
                    }
                    Settings.System.putFloatForUser(BalanceSeekBar.this.mContext.getContentResolver(), "master_balance", (i3 - BalanceSeekBar.this.mCenter) * 0.01f, -2);
                }
                synchronized (BalanceSeekBar.this.mListenerLock) {
                    if (BalanceSeekBar.this.mOnSeekBarChangeListener != null) {
                        BalanceSeekBar.this.mOnSeekBarChangeListener.onProgressChanged(seekBar, i3, z);
                    }
                }
            }
        };
        this.mContext = context;
        Resources resources = getResources();
        this.mCenterMarkerRect = new Rect(0, 0, resources.getDimensionPixelSize(com.android.car.developeroptions.R.dimen.balance_seekbar_center_marker_width), resources.getDimensionPixelSize(com.android.car.developeroptions.R.dimen.balance_seekbar_center_marker_height));
        Paint paint = new Paint();
        this.mCenterMarkerPaint = paint;
        paint.setColor(-16777216);
        this.mCenterMarkerPaint.setStyle(Paint.Style.FILL);
        setProgressTintList(ColorStateList.valueOf(0));
        super.setOnSeekBarChangeListener(this.mProxySeekBarListener);
    }

    @Override // android.widget.SeekBar
    public void setOnSeekBarChangeListener(SeekBar.OnSeekBarChangeListener onSeekBarChangeListener) {
        synchronized (this.mListenerLock) {
            this.mOnSeekBarChangeListener = onSeekBarChangeListener;
        }
    }

    @Override // android.widget.AbsSeekBar, android.widget.ProgressBar
    public synchronized void setMax(int i) {
        super.setMax(i);
        this.mCenter = i / 2;
        this.mSnapThreshold = i * SNAP_TO_PERCENTAGE;
    }

    @Override // android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    protected synchronized void onDraw(Canvas canvas) {
        int height = (canvas.getHeight() - getPaddingBottom()) / 2;
        canvas.save();
        canvas.translate((canvas.getWidth() - this.mCenterMarkerRect.right) / 2, height - (this.mCenterMarkerRect.bottom / 2));
        canvas.drawRect(this.mCenterMarkerRect, this.mCenterMarkerPaint);
        canvas.restore();
        super.onDraw(canvas);
    }

    SeekBar.OnSeekBarChangeListener getProxySeekBarListener() {
        return this.mProxySeekBarListener;
    }
}
