package com.bydux.api.common.views.ctrl;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bydux.api.R;
import com.bydux.api.common.tools.datetime.BydDateTimeHelper;
import com.bydux.api.common.views.ctrl.BydTimer;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class BydDigitalClock extends LinearLayout {
    private final String TAG;
    private Context mContext;
    private Drawable mDigitalClockAm;
    private int mDigitalClockAmId;
    private Drawable mDigitalClockColon;
    private TextView mDigitalClockColon0;
    private TextView mDigitalClockColon1;
    private int mDigitalClockColonId;
    private TextView mDigitalClockHour0;
    private TextView mDigitalClockHour1;
    private TextView mDigitalClockMinute0;
    private TextView mDigitalClockMinute1;
    private TextView mDigitalClockMode;
    private Drawable mDigitalClockNum0;
    private int mDigitalClockNum0Id;
    private Drawable mDigitalClockNum1;
    private int mDigitalClockNum1Id;
    private Drawable mDigitalClockNum2;
    private int mDigitalClockNum2Id;
    private Drawable mDigitalClockNum3;
    private int mDigitalClockNum3Id;
    private Drawable mDigitalClockNum4;
    private int mDigitalClockNum4Id;
    private Drawable mDigitalClockNum5;
    private int mDigitalClockNum5Id;
    private Drawable mDigitalClockNum6;
    private int mDigitalClockNum6Id;
    private Drawable mDigitalClockNum7;
    private int mDigitalClockNum7Id;
    private Drawable mDigitalClockNum8;
    private int mDigitalClockNum8Id;
    private Drawable mDigitalClockNum9;
    private int mDigitalClockNum9Id;
    private Drawable mDigitalClockPm;
    private int mDigitalClockPmId;
    private TextView mDigitalClockSecond0;
    private TextView mDigitalClockSecond1;
    private boolean mShowSecond;
    private BydTimer mTimer;

    public BydDigitalClock(Context context) {
        super(context);
        this.TAG = getClass().getSimpleName();
        this.mDigitalClockHour0 = null;
        this.mDigitalClockHour1 = null;
        this.mDigitalClockMinute0 = null;
        this.mDigitalClockMinute1 = null;
        this.mDigitalClockSecond0 = null;
        this.mDigitalClockSecond1 = null;
        this.mDigitalClockColon0 = null;
        this.mDigitalClockColon1 = null;
        this.mDigitalClockMode = null;
        this.mDigitalClockNum0Id = 0;
        this.mDigitalClockNum1Id = 0;
        this.mDigitalClockNum2Id = 0;
        this.mDigitalClockNum3Id = 0;
        this.mDigitalClockNum4Id = 0;
        this.mDigitalClockNum5Id = 0;
        this.mDigitalClockNum6Id = 0;
        this.mDigitalClockNum7Id = 0;
        this.mDigitalClockNum8Id = 0;
        this.mDigitalClockNum9Id = 0;
        this.mDigitalClockAmId = 0;
        this.mDigitalClockPmId = 0;
        this.mDigitalClockColonId = 0;
        this.mShowSecond = false;
        this.mDigitalClockNum0 = null;
        this.mDigitalClockNum1 = null;
        this.mDigitalClockNum2 = null;
        this.mDigitalClockNum3 = null;
        this.mDigitalClockNum4 = null;
        this.mDigitalClockNum5 = null;
        this.mDigitalClockNum6 = null;
        this.mDigitalClockNum7 = null;
        this.mDigitalClockNum8 = null;
        this.mDigitalClockNum9 = null;
        this.mDigitalClockAm = null;
        this.mDigitalClockPm = null;
        this.mDigitalClockColon = null;
        initView(context);
    }

    private Drawable getNumDrawable(int i) {
        switch (i) {
            case 0:
                return this.mDigitalClockNum0;
            case 1:
                return this.mDigitalClockNum1;
            case 2:
                return this.mDigitalClockNum2;
            case 3:
                return this.mDigitalClockNum3;
            case 4:
                return this.mDigitalClockNum4;
            case 5:
                return this.mDigitalClockNum5;
            case 6:
                return this.mDigitalClockNum6;
            case 7:
                return this.mDigitalClockNum7;
            case 8:
                return this.mDigitalClockNum8;
            case 9:
                return this.mDigitalClockNum9;
            default:
                return null;
        }
    }

    private void initView(Context context) {
        this.mContext = context;
        this.mDigitalClockHour0 = new TextView(this.mContext);
        this.mDigitalClockHour1 = new TextView(this.mContext);
        this.mDigitalClockMinute0 = new TextView(this.mContext);
        this.mDigitalClockMinute1 = new TextView(this.mContext);
        this.mDigitalClockSecond0 = new TextView(this.mContext);
        this.mDigitalClockSecond1 = new TextView(this.mContext);
        this.mDigitalClockColon0 = new TextView(this.mContext);
        this.mDigitalClockColon1 = new TextView(this.mContext);
        this.mDigitalClockMode = new TextView(this.mContext);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 17;
        this.mDigitalClockHour0.setLayoutParams(layoutParams);
        this.mDigitalClockHour1.setLayoutParams(layoutParams);
        this.mDigitalClockMinute0.setLayoutParams(layoutParams);
        this.mDigitalClockMinute1.setLayoutParams(layoutParams);
        this.mDigitalClockSecond0.setLayoutParams(layoutParams);
        this.mDigitalClockSecond1.setLayoutParams(layoutParams);
        this.mDigitalClockColon0.setLayoutParams(layoutParams);
        this.mDigitalClockColon1.setLayoutParams(layoutParams);
        this.mDigitalClockMode.setLayoutParams(layoutParams);
        addView(this.mDigitalClockHour0);
        addView(this.mDigitalClockHour1);
        addView(this.mDigitalClockColon0);
        addView(this.mDigitalClockMinute0);
        addView(this.mDigitalClockMinute1);
        addView(this.mDigitalClockColon1);
        addView(this.mDigitalClockSecond0);
        addView(this.mDigitalClockSecond1);
        addView(this.mDigitalClockMode);
        setOrientation(0);
        BydTimer bydTimer = new BydTimer(context);
        this.mTimer = bydTimer;
        bydTimer.setBydTimerListener(new BydTimer.BydTimerListener() { // from class: com.bydux.api.common.views.ctrl.BydDigitalClock.1
            @Override // com.bydux.api.common.views.ctrl.BydTimer.BydTimerListener
            public void onTimeUp(View view) {
                BydDigitalClock.this.updateShow();
            }
        });
    }

    private void loadAttrs(AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = this.mContext.obtainStyledAttributes(attributeSet, R.styleable.BydDigitalClock);
            this.mDigitalClockNum0Id = typedArrayObtainStyledAttributes.getResourceId(R.styleable.BydDigitalClock_byd_dgclock_num0, this.mDigitalClockNum0Id);
            this.mDigitalClockNum1Id = typedArrayObtainStyledAttributes.getResourceId(R.styleable.BydDigitalClock_byd_dgclock_num1, this.mDigitalClockNum1Id);
            this.mDigitalClockNum2Id = typedArrayObtainStyledAttributes.getResourceId(R.styleable.BydDigitalClock_byd_dgclock_num2, this.mDigitalClockNum2Id);
            this.mDigitalClockNum3Id = typedArrayObtainStyledAttributes.getResourceId(R.styleable.BydDigitalClock_byd_dgclock_num3, this.mDigitalClockNum3Id);
            this.mDigitalClockNum4Id = typedArrayObtainStyledAttributes.getResourceId(R.styleable.BydDigitalClock_byd_dgclock_num4, this.mDigitalClockNum4Id);
            this.mDigitalClockNum5Id = typedArrayObtainStyledAttributes.getResourceId(R.styleable.BydDigitalClock_byd_dgclock_num5, this.mDigitalClockNum5Id);
            this.mDigitalClockNum6Id = typedArrayObtainStyledAttributes.getResourceId(R.styleable.BydDigitalClock_byd_dgclock_num6, this.mDigitalClockNum6Id);
            this.mDigitalClockNum7Id = typedArrayObtainStyledAttributes.getResourceId(R.styleable.BydDigitalClock_byd_dgclock_num7, this.mDigitalClockNum7Id);
            this.mDigitalClockNum8Id = typedArrayObtainStyledAttributes.getResourceId(R.styleable.BydDigitalClock_byd_dgclock_num8, this.mDigitalClockNum8Id);
            this.mDigitalClockNum9Id = typedArrayObtainStyledAttributes.getResourceId(R.styleable.BydDigitalClock_byd_dgclock_num9, this.mDigitalClockNum9Id);
            this.mDigitalClockAmId = typedArrayObtainStyledAttributes.getResourceId(R.styleable.BydDigitalClock_byd_dgclock_am, this.mDigitalClockAmId);
            this.mDigitalClockPmId = typedArrayObtainStyledAttributes.getResourceId(R.styleable.BydDigitalClock_byd_dgclock_pm, this.mDigitalClockPmId);
            this.mDigitalClockColonId = typedArrayObtainStyledAttributes.getResourceId(R.styleable.BydDigitalClock_byd_dgclock_colon, this.mDigitalClockColonId);
            this.mShowSecond = typedArrayObtainStyledAttributes.getBoolean(R.styleable.BydDigitalClock_byd_dgclock_show_second, this.mShowSecond);
            typedArrayObtainStyledAttributes.recycle();
            int i = this.mDigitalClockNum0Id;
            if (i > 0) {
                setDigitalClockNum0(this.mContext.getDrawable(i));
            }
            int i2 = this.mDigitalClockNum1Id;
            if (i2 > 0) {
                setDigitalClockNum1(this.mContext.getDrawable(i2));
            }
            int i3 = this.mDigitalClockNum2Id;
            if (i3 > 0) {
                setDigitalClockNum2(this.mContext.getDrawable(i3));
            }
            int i4 = this.mDigitalClockNum3Id;
            if (i4 > 0) {
                setDigitalClockNum3(this.mContext.getDrawable(i4));
            }
            int i5 = this.mDigitalClockNum4Id;
            if (i5 > 0) {
                setDigitalClockNum4(this.mContext.getDrawable(i5));
            }
            int i6 = this.mDigitalClockNum5Id;
            if (i6 > 0) {
                setDigitalClockNum5(this.mContext.getDrawable(i6));
            }
            int i7 = this.mDigitalClockNum6Id;
            if (i7 > 0) {
                setDigitalClockNum6(this.mContext.getDrawable(i7));
            }
            int i8 = this.mDigitalClockNum7Id;
            if (i8 > 0) {
                setDigitalClockNum7(this.mContext.getDrawable(i8));
            }
            int i9 = this.mDigitalClockNum8Id;
            if (i9 > 0) {
                setDigitalClockNum8(this.mContext.getDrawable(i9));
            }
            int i10 = this.mDigitalClockNum9Id;
            if (i10 > 0) {
                setDigitalClockNum9(this.mContext.getDrawable(i10));
            }
            int i11 = this.mDigitalClockAmId;
            if (i11 > 0) {
                setDigitalClockAm(this.mContext.getDrawable(i11));
            }
            int i12 = this.mDigitalClockPmId;
            if (i12 > 0) {
                setDigitalClockPm(this.mContext.getDrawable(i12));
            }
            int i13 = this.mDigitalClockColonId;
            if (i13 > 0) {
                setDigitalClockColon(this.mContext.getDrawable(i13));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateShow() {
        int systemTime = BydDateTimeHelper.getSystemTime(this.mContext, 3);
        int systemTime2 = BydDateTimeHelper.getSystemTime(this.mContext, 4);
        int systemTime3 = BydDateTimeHelper.getSystemTime(this.mContext, 5);
        if (systemTime < 10) {
            this.mDigitalClockHour0.setBackground(getNumDrawable(0));
            this.mDigitalClockHour1.setBackground(getNumDrawable(systemTime));
        } else {
            this.mDigitalClockHour0.setBackground(getNumDrawable(systemTime / 10));
            this.mDigitalClockHour1.setBackground(getNumDrawable(systemTime % 10));
        }
        if (systemTime2 < 10) {
            this.mDigitalClockMinute0.setBackground(getNumDrawable(0));
            this.mDigitalClockMinute1.setBackground(getNumDrawable(systemTime2));
        } else {
            this.mDigitalClockMinute0.setBackground(getNumDrawable(systemTime2 / 10));
            this.mDigitalClockMinute1.setBackground(getNumDrawable(systemTime2 % 10));
        }
        if (systemTime3 < 10) {
            this.mDigitalClockSecond0.setBackground(getNumDrawable(0));
            this.mDigitalClockSecond1.setBackground(getNumDrawable(systemTime3));
        } else {
            this.mDigitalClockSecond0.setBackground(getNumDrawable(systemTime3 / 10));
            this.mDigitalClockSecond1.setBackground(getNumDrawable(systemTime3 % 10));
        }
        this.mDigitalClockColon0.setBackground(this.mDigitalClockColon);
        this.mDigitalClockColon1.setBackground(this.mDigitalClockColon);
        if (this.mShowSecond) {
            this.mDigitalClockColon0.setVisibility(0);
            this.mDigitalClockColon1.setVisibility(0);
            this.mDigitalClockSecond0.setVisibility(0);
            this.mDigitalClockSecond1.setVisibility(0);
        } else {
            if (this.mDigitalClockColon0.getVisibility() != 0) {
                this.mDigitalClockColon0.setVisibility(0);
            } else {
                this.mDigitalClockColon0.setVisibility(4);
            }
            this.mDigitalClockColon1.setVisibility(8);
            this.mDigitalClockSecond0.setVisibility(8);
            this.mDigitalClockSecond1.setVisibility(8);
        }
        int systemTime4 = BydDateTimeHelper.getSystemTime(this.mContext, 7);
        if (systemTime4 == 0) {
            this.mDigitalClockMode.setVisibility(8);
            return;
        }
        this.mDigitalClockMode.setVisibility(0);
        if (systemTime4 == 1) {
            this.mDigitalClockMode.setBackground(this.mDigitalClockAm);
        } else {
            this.mDigitalClockMode.setBackground(this.mDigitalClockPm);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        updateShow();
        BydTimer bydTimer = this.mTimer;
        if (bydTimer != null) {
            bydTimer.startTimer(500L);
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

    public void setDigitalClockAm(Drawable drawable) {
        this.mDigitalClockAm = drawable;
    }

    public void setDigitalClockColon(Drawable drawable) {
        this.mDigitalClockColon = drawable;
    }

    public void setDigitalClockNum0(Drawable drawable) {
        this.mDigitalClockNum0 = drawable;
    }

    public void setDigitalClockNum1(Drawable drawable) {
        this.mDigitalClockNum1 = drawable;
    }

    public void setDigitalClockNum2(Drawable drawable) {
        this.mDigitalClockNum2 = drawable;
    }

    public void setDigitalClockNum3(Drawable drawable) {
        this.mDigitalClockNum3 = drawable;
    }

    public void setDigitalClockNum4(Drawable drawable) {
        this.mDigitalClockNum4 = drawable;
    }

    public void setDigitalClockNum5(Drawable drawable) {
        this.mDigitalClockNum5 = drawable;
    }

    public void setDigitalClockNum6(Drawable drawable) {
        this.mDigitalClockNum6 = drawable;
    }

    public void setDigitalClockNum7(Drawable drawable) {
        this.mDigitalClockNum7 = drawable;
    }

    public void setDigitalClockNum8(Drawable drawable) {
        this.mDigitalClockNum8 = drawable;
    }

    public void setDigitalClockNum9(Drawable drawable) {
        this.mDigitalClockNum9 = drawable;
    }

    public void setDigitalClockPm(Drawable drawable) {
        this.mDigitalClockPm = drawable;
    }

    public void setSecondShow(boolean z) {
        this.mShowSecond = z;
    }

    public BydDigitalClock(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.TAG = getClass().getSimpleName();
        this.mDigitalClockHour0 = null;
        this.mDigitalClockHour1 = null;
        this.mDigitalClockMinute0 = null;
        this.mDigitalClockMinute1 = null;
        this.mDigitalClockSecond0 = null;
        this.mDigitalClockSecond1 = null;
        this.mDigitalClockColon0 = null;
        this.mDigitalClockColon1 = null;
        this.mDigitalClockMode = null;
        this.mDigitalClockNum0Id = 0;
        this.mDigitalClockNum1Id = 0;
        this.mDigitalClockNum2Id = 0;
        this.mDigitalClockNum3Id = 0;
        this.mDigitalClockNum4Id = 0;
        this.mDigitalClockNum5Id = 0;
        this.mDigitalClockNum6Id = 0;
        this.mDigitalClockNum7Id = 0;
        this.mDigitalClockNum8Id = 0;
        this.mDigitalClockNum9Id = 0;
        this.mDigitalClockAmId = 0;
        this.mDigitalClockPmId = 0;
        this.mDigitalClockColonId = 0;
        this.mShowSecond = false;
        this.mDigitalClockNum0 = null;
        this.mDigitalClockNum1 = null;
        this.mDigitalClockNum2 = null;
        this.mDigitalClockNum3 = null;
        this.mDigitalClockNum4 = null;
        this.mDigitalClockNum5 = null;
        this.mDigitalClockNum6 = null;
        this.mDigitalClockNum7 = null;
        this.mDigitalClockNum8 = null;
        this.mDigitalClockNum9 = null;
        this.mDigitalClockAm = null;
        this.mDigitalClockPm = null;
        this.mDigitalClockColon = null;
        initView(context);
        loadAttrs(attributeSet);
    }
}
