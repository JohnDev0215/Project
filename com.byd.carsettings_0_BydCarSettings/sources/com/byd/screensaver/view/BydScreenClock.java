package com.byd.screensaver.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.byd.carsettings.R;
import com.byd.mvp.ui.widget.BydClockView;
import com.bydux.api.common.tools.datetime.BydDateTimeHelper;

/* JADX INFO: loaded from: classes2.dex */
public class BydScreenClock extends RelativeLayout {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public BydClockView f3867a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public TextView f3868b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public TextView f3869c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public TextView f3870d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public float f3871e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public float f3872f;
    public StringBuffer g;

    public class a implements BydClockView.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Context f3873a;

        public a(Context context) {
            this.f3873a = context;
        }

        @Override // com.byd.mvp.ui.widget.BydClockView.b
        public void a() {
            int systemTime = BydDateTimeHelper.getSystemTime(this.f3873a, 1);
            int systemTime2 = BydDateTimeHelper.getSystemTime(this.f3873a, 6);
            int systemTime3 = BydDateTimeHelper.getSystemTime(this.f3873a, 2);
            float f2 = (systemTime % 12) * 30;
            float f3 = (systemTime2 % 7) * 51.42857f;
            if (BydScreenClock.this.f3868b != null && BydScreenClock.this.f3871e != f2) {
                BydScreenClock.this.f3871e = f2;
                BydScreenClock.this.f3868b.setRotation(BydScreenClock.this.f3871e);
            }
            if (BydScreenClock.this.f3869c != null && BydScreenClock.this.f3872f != f3) {
                BydScreenClock.this.f3872f = f3;
                BydScreenClock.this.f3869c.setRotation(BydScreenClock.this.f3872f);
            }
            BydScreenClock.this.f3870d.setText(BydScreenClock.this.i(systemTime3));
        }
    }

    public BydScreenClock(Context context) {
        super(context);
        this.f3871e = 0.0f;
        this.f3872f = 0.0f;
        j(context);
    }

    public final String i(int i) {
        if (this.g == null) {
            this.g = new StringBuffer();
        }
        this.g.setLength(0);
        if (i < 10) {
            this.g.append(0);
        }
        this.g.append(i);
        return this.g.toString();
    }

    public final void j(Context context) {
        setLayoutDirection(0);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.view_clock, (ViewGroup) null);
        this.f3867a = (BydClockView) viewInflate.findViewById(R.id.bcv_clock_screen);
        addView(viewInflate, layoutParams);
        View viewInflate2 = LayoutInflater.from(context).inflate(R.layout.view_subclock, (ViewGroup) null);
        this.f3868b = (TextView) viewInflate2.findViewById(R.id.tv_clock_month);
        this.f3869c = (TextView) viewInflate2.findViewById(R.id.tv_clock_week);
        this.f3870d = (TextView) viewInflate2.findViewById(R.id.tv_clock_day);
        this.f3867a.setSubView(viewInflate2);
        this.f3867a.setOnUiUpdateCallback(new a(context));
    }

    public BydScreenClock(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f3871e = 0.0f;
        this.f3872f = 0.0f;
        j(context);
    }

    public BydScreenClock(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f3871e = 0.0f;
        this.f3872f = 0.0f;
        j(context);
    }
}
