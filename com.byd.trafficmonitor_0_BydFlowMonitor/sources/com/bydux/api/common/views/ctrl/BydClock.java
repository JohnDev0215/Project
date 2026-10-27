package com.bydux.api.common.views.ctrl;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.bydux.api.R;
import com.bydux.api.common.tools.datetime.BydDateTimeHelper;
import com.bydux.api.common.views.ctrl.BydTimer;

/* JADX INFO: loaded from: classes.dex */
public class BydClock extends FrameLayout {
    private final String TAG;
    private TextView mClockBackground;
    private int mClockBackgroundId;
    private TextView mClockHour;
    private int mClockHourId;
    private TextView mClockMinute;
    private int mClockMinuteId;
    private TextView mClockSecond;
    private int mClockSecondId;
    private Context mContext;
    private float mHourDegree;
    private float mMinuteDegree;
    private float mSecondDegree;
    private BydTimer mTimer;

    public BydClock(Context context) {
        super(context);
        this.TAG = getClass().getSimpleName();
        this.mClockBackground = null;
        this.mClockHour = null;
        this.mClockMinute = null;
        this.mClockSecond = null;
        this.mClockBackgroundId = 0;
        this.mClockHourId = 0;
        this.mClockMinuteId = 0;
        this.mClockSecondId = 0;
        this.mHourDegree = 0.0f;
        this.mMinuteDegree = 0.0f;
        this.mSecondDegree = 0.0f;
        initView(context);
    }

    private void initView(Context context) {
        this.mContext = context;
        this.mClockBackground = new TextView(context);
        this.mClockHour = new TextView(this.mContext);
        this.mClockMinute = new TextView(this.mContext);
        this.mClockSecond = new TextView(this.mContext);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 17;
        this.mClockBackground.setLayoutParams(layoutParams);
        this.mClockHour.setLayoutParams(layoutParams);
        this.mClockMinute.setLayoutParams(layoutParams);
        this.mClockSecond.setLayoutParams(layoutParams);
        addView(this.mClockBackground);
        addView(this.mClockHour);
        addView(this.mClockMinute);
        addView(this.mClockSecond);
        BydTimer bydTimer = new BydTimer(context);
        this.mTimer = bydTimer;
        bydTimer.setBydTimerListener(new BydTimer.BydTimerListener() { // from class: com.bydux.api.common.views.ctrl.BydClock.1
            @Override // com.bydux.api.common.views.ctrl.BydTimer.BydTimerListener
            public void onTimeUp(View view) {
                BydClock.this.updateShow();
            }
        });
    }

    private void loadAttrs(AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = this.mContext.obtainStyledAttributes(attributeSet, R.styleable.BydClock);
            this.mClockBackgroundId = typedArrayObtainStyledAttributes.getResourceId(R.styleable.BydClock_byd_clock_background, this.mClockBackgroundId);
            this.mClockHourId = typedArrayObtainStyledAttributes.getResourceId(R.styleable.BydClock_byd_clock_hour, this.mClockHourId);
            this.mClockMinuteId = typedArrayObtainStyledAttributes.getResourceId(R.styleable.BydClock_byd_clock_minute, this.mClockMinuteId);
            this.mClockSecondId = typedArrayObtainStyledAttributes.getResourceId(R.styleable.BydClock_byd_clock_second, this.mClockSecondId);
            typedArrayObtainStyledAttributes.recycle();
            int i2 = this.mClockBackgroundId;
            if (i2 > 0) {
                setClockBackground(this.mContext.getDrawable(i2));
            }
            int i3 = this.mClockHourId;
            if (i3 > 0) {
                setClockHour(this.mContext.getDrawable(i3));
            }
            int i4 = this.mClockMinuteId;
            if (i4 > 0) {
                setClockMinute(this.mContext.getDrawable(i4));
            }
            int i5 = this.mClockSecondId;
            if (i5 > 0) {
                setClockSecond(this.mContext.getDrawable(i5));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateShow() {
        int systemTime = BydDateTimeHelper.getSystemTime(this.mContext, 3);
        int systemTime2 = BydDateTimeHelper.getSystemTime(this.mContext, 4);
        int systemTime3 = BydDateTimeHelper.getSystemTime(this.mContext, 5);
        float f2 = ((systemTime % 12) * 30) + (systemTime2 / 2);
        float f3 = (systemTime2 * 6) + (systemTime3 / 10);
        float f4 = systemTime3 * 6;
        if (this.mHourDegree != f2) {
            this.mHourDegree = f2;
            this.mClockHour.setRotation(f2);
        }
        if (this.mMinuteDegree != f3) {
            this.mMinuteDegree = f3;
            this.mClockMinute.setRotation(f3);
        }
        if (this.mSecondDegree != f4) {
            this.mSecondDegree = f4;
            this.mClockSecond.setRotation(f4);
        }
    }

    @Override // android.view.ViewGroup
    public boolean drawChild(Canvas canvas, View view, long j) {
        view.setPivotX(view.getMeasuredWidth() / 2);
        view.setPivotY(view.getMeasuredHeight() / 2);
        return super.drawChild(canvas, view, j);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        updateShow();
        BydTimer bydTimer = this.mTimer;
        if (bydTimer != null) {
            bydTimer.startTimer(200L);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        BydTimer bydTimer = this.mTimer;
        if (bydTimer != null) {
            bydTimer.stopTimer();
        }
    }

    public void setClockBackground(Drawable drawable) {
        this.mClockBackground.setBackground(drawable);
    }

    public void setClockHour(Drawable drawable) {
        this.mClockHour.setBackground(drawable);
    }

    public void setClockMinute(Drawable drawable) {
        this.mClockMinute.setBackground(drawable);
    }

    public void setClockSecond(Drawable drawable) {
        this.mClockSecond.setBackground(drawable);
    }

    public BydClock(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.TAG = getClass().getSimpleName();
        this.mClockBackground = null;
        this.mClockHour = null;
        this.mClockMinute = null;
        this.mClockSecond = null;
        this.mClockBackgroundId = 0;
        this.mClockHourId = 0;
        this.mClockMinuteId = 0;
        this.mClockSecondId = 0;
        this.mHourDegree = 0.0f;
        this.mMinuteDegree = 0.0f;
        this.mSecondDegree = 0.0f;
        initView(context);
        loadAttrs(attributeSet);
    }
}
