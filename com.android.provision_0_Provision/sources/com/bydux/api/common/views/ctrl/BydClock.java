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

    public BydClock(Context context, AttributeSet attrs) {
        super(context, attrs);
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
        loadAttrs(attrs);
    }

    private void initView(Context context) {
        this.mContext = context;
        this.mClockBackground = new TextView(this.mContext);
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

    private void loadAttrs(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray typedArrayObtainStyledAttributes = this.mContext.obtainStyledAttributes(attrs, R.styleable.BydClock);
            this.mClockBackgroundId = typedArrayObtainStyledAttributes.getResourceId(R.styleable.BydClock_byd_clock_background, this.mClockBackgroundId);
            this.mClockHourId = typedArrayObtainStyledAttributes.getResourceId(R.styleable.BydClock_byd_clock_hour, this.mClockHourId);
            this.mClockMinuteId = typedArrayObtainStyledAttributes.getResourceId(R.styleable.BydClock_byd_clock_minute, this.mClockMinuteId);
            this.mClockSecondId = typedArrayObtainStyledAttributes.getResourceId(R.styleable.BydClock_byd_clock_second, this.mClockSecondId);
            typedArrayObtainStyledAttributes.recycle();
            int i = this.mClockBackgroundId;
            if (i > 0) {
                setClockBackground(this.mContext.getDrawable(i));
            }
            int i2 = this.mClockHourId;
            if (i2 > 0) {
                setClockHour(this.mContext.getDrawable(i2));
            }
            int i3 = this.mClockMinuteId;
            if (i3 > 0) {
                setClockMinute(this.mContext.getDrawable(i3));
            }
            int i4 = this.mClockSecondId;
            if (i4 > 0) {
                setClockSecond(this.mContext.getDrawable(i4));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateShow() {
        int systemTime = BydDateTimeHelper.getSystemTime(this.mContext, 3);
        int systemTime2 = BydDateTimeHelper.getSystemTime(this.mContext, 4);
        int systemTime3 = BydDateTimeHelper.getSystemTime(this.mContext, 5);
        float f = ((systemTime % 12) * 30) + (systemTime2 / 2);
        float f2 = (systemTime2 * 6) + (systemTime3 / 10);
        float f3 = systemTime3 * 6;
        if (this.mHourDegree != f) {
            this.mHourDegree = f;
            this.mClockHour.setRotation(f);
        }
        if (this.mMinuteDegree != f2) {
            this.mMinuteDegree = f2;
            this.mClockMinute.setRotation(f2);
        }
        if (this.mSecondDegree != f3) {
            this.mSecondDegree = f3;
            this.mClockSecond.setRotation(f3);
        }
    }

    @Override // android.view.ViewGroup
    protected boolean drawChild(Canvas canvas, View child, long drawingTime) {
        child.setPivotX(child.getMeasuredWidth() / 2);
        child.setPivotY(child.getMeasuredHeight() / 2);
        return super.drawChild(canvas, child, drawingTime);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        updateShow();
        BydTimer bydTimer = this.mTimer;
        if (bydTimer != null) {
            bydTimer.startTimer(200L);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        BydTimer bydTimer = this.mTimer;
        if (bydTimer != null) {
            bydTimer.stopTimer();
        }
    }
}
