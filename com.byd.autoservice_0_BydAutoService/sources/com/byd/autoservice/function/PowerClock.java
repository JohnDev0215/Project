package com.byd.autoservice.function;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.byd.autoservice.R;
import com.byd.autoservice.function.BydClockView;
import com.bydux.api.common.tools.datetime.BydDateTimeHelper;

/* JADX INFO: loaded from: classes.dex */
public class PowerClock extends RelativeLayout {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public BydClockView f1855b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public float f1856c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public float f1857d;

    public class a implements BydClockView.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Context f1858a;

        public a(Context context) {
            this.f1858a = context;
        }

        @Override // com.byd.autoservice.function.BydClockView.b
        public void a() {
            BydDateTimeHelper.getSystemTime(this.f1858a, 1);
            BydDateTimeHelper.getSystemTime(this.f1858a, 6);
            BydDateTimeHelper.getSystemTime(this.f1858a, 2);
        }
    }

    public PowerClock(Context context) {
        super(context);
        this.f1856c = 0.0f;
        this.f1857d = 0.0f;
        a(context);
    }

    public final void a(Context context) {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.view_clock, (ViewGroup) null);
        this.f1855b = (BydClockView) viewInflate.findViewById(R.id.bcv_clock_screen);
        addView(viewInflate, layoutParams);
        this.f1855b.setOnUiUpdateCallback(new a(context));
    }

    public PowerClock(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1856c = 0.0f;
        this.f1857d = 0.0f;
        a(context);
    }

    public PowerClock(Context context, AttributeSet attributeSet, int i3) {
        super(context, attributeSet, i3);
        this.f1856c = 0.0f;
        this.f1857d = 0.0f;
        a(context);
    }
}
