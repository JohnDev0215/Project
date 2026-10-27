package com.byd.systemsettings.screensaver.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.byd.mvp.ui.widget.BydClockView;
import com.byd.systemsettings.R;
import com.bydux.api.common.tools.datetime.BydDateTimeHelper;

/* JADX INFO: loaded from: classes2.dex */
public class BydScreenClock extends RelativeLayout {
    private BydClockView mClock;
    private TextView mClockDay;
    private TextView mClockMonth;
    private TextView mClockWeek;
    private float mMonthDegree;
    private float mWeekDegree;
    private StringBuffer sBuf;

    public BydScreenClock(Context context) {
        super(context);
        this.mMonthDegree = 0.0f;
        this.mWeekDegree = 0.0f;
        initViews(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getDoubleDigit(int i8) {
        if (this.sBuf == null) {
            this.sBuf = new StringBuffer();
        }
        this.sBuf.setLength(0);
        if (i8 < 10) {
            this.sBuf.append(0);
        }
        this.sBuf.append(i8);
        return this.sBuf.toString();
    }

    private void initViews(final Context context) {
        setLayoutDirection(0);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.view_clock, (ViewGroup) null);
        this.mClock = (BydClockView) viewInflate.findViewById(R.id.bcv_clock_screen);
        addView(viewInflate, layoutParams);
        View viewInflate2 = LayoutInflater.from(context).inflate(R.layout.view_subclock, (ViewGroup) null);
        this.mClockMonth = (TextView) viewInflate2.findViewById(R.id.tv_clock_month);
        this.mClockWeek = (TextView) viewInflate2.findViewById(R.id.tv_clock_week);
        this.mClockDay = (TextView) viewInflate2.findViewById(R.id.tv_clock_day);
        this.mClock.setSubView(viewInflate2);
        this.mClock.setOnUiUpdateCallback(new BydClockView.b() { // from class: com.byd.systemsettings.screensaver.view.BydScreenClock.1
            @Override // com.byd.mvp.ui.widget.BydClockView.b
            public void onUiUpdated() {
                int systemTime = BydDateTimeHelper.getSystemTime(context, 1);
                int systemTime2 = BydDateTimeHelper.getSystemTime(context, 6);
                int systemTime3 = BydDateTimeHelper.getSystemTime(context, 2);
                float f8 = (systemTime % 12) * 30;
                float f9 = (systemTime2 % 7) * 51.42857f;
                if (BydScreenClock.this.mClockMonth != null && BydScreenClock.this.mMonthDegree != f8) {
                    BydScreenClock.this.mMonthDegree = f8;
                    BydScreenClock.this.mClockMonth.setRotation(BydScreenClock.this.mMonthDegree);
                }
                if (BydScreenClock.this.mClockWeek != null && BydScreenClock.this.mWeekDegree != f9) {
                    BydScreenClock.this.mWeekDegree = f9;
                    BydScreenClock.this.mClockWeek.setRotation(BydScreenClock.this.mWeekDegree);
                }
                BydScreenClock.this.mClockDay.setText(BydScreenClock.this.getDoubleDigit(systemTime3));
            }
        });
    }

    public BydScreenClock(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mMonthDegree = 0.0f;
        this.mWeekDegree = 0.0f;
        initViews(context);
    }

    public BydScreenClock(Context context, AttributeSet attributeSet, int i8) {
        super(context, attributeSet, i8);
        this.mMonthDegree = 0.0f;
        this.mWeekDegree = 0.0f;
        initViews(context);
    }
}
