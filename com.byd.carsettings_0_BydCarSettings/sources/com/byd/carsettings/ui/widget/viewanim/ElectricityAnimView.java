package com.byd.carsettings.ui.widget.viewanim;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import b.b.a.i.a;
import b.b.g.n.q.b;
import b.b.g.r.n;
import b.b.g.r.o;
import com.byd.carsettings.R;
import com.byd.carsettings.ui.widget.LineTipsView;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: loaded from: classes2.dex */
public class ElectricityAnimView extends FrameLayout {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f3474a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public ImageView f3475b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public ImageView f3476c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public TextView f3477d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public ImageView f3478e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public ImageView f3479f;
    public LineTipsView g;
    public o h;
    public int i;

    public ElectricityAnimView(Context context) {
        super(context, null);
        this.f3474a = "ElectricityManageFragment";
        this.i = -2;
    }

    public void a(o... oVarArr) {
        if (oVarArr == null || oVarArr.length <= 0) {
            return;
        }
        for (o oVar : oVarArr) {
            if (oVar != null) {
                oVar.o();
                oVar.m();
            }
        }
    }

    public void b(ImageView... imageViewArr) {
        if (imageViewArr == null || imageViewArr.length <= 0) {
            return;
        }
        for (ImageView imageView : imageViewArr) {
            if (imageView != null) {
                imageView.setImageDrawable(null);
            }
        }
    }

    public final void c(Context context) {
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.anim_electricity_manage, (ViewGroup) null);
        viewInflate.setRotationY(a.f531a ? 180.0f : 0.0f);
        addView(viewInflate);
        this.f3478e = (ImageView) viewInflate.findViewById(R.id.iv_car);
        this.f3479f = (ImageView) viewInflate.findViewById(R.id.iv_car_bg);
        this.f3475b = (ImageView) viewInflate.findViewById(R.id.iv_scan);
        this.f3476c = (ImageView) viewInflate.findViewById(R.id.iv_battery_progress);
        TextView textView = (TextView) viewInflate.findViewById(R.id.tv_battery_progress);
        this.f3477d = textView;
        textView.setRotationY(a.f531a ? 180.0f : 0.0f);
        LineTipsView lineTipsView = (LineTipsView) viewInflate.findViewById(R.id.lineTipsView);
        this.g = lineTipsView;
        if (a.f531a) {
            lineTipsView.setPoint(new Point(getResources().getDimensionPixelSize(R.dimen.dp_190), getResources().getDimensionPixelSize(R.dimen.dp_194)), new Point(getResources().getDimensionPixelSize(R.dimen.dp_380), getResources().getDimensionPixelSize(R.dimen.dp_194)), new Point(getResources().getDimensionPixelSize(R.dimen.dp_182), getResources().getDimensionPixelSize(R.dimen.dp_264)), new Point(getResources().getDimensionPixelSize(R.dimen.dp_388), getResources().getDimensionPixelSize(R.dimen.dp_264)));
        } else {
            lineTipsView.setPoint(new Point(getResources().getDimensionPixelSize(R.dimen.dp_180), getResources().getDimensionPixelSize(R.dimen.dp_194)), new Point(getResources().getDimensionPixelSize(R.dimen.dp_360), getResources().getDimensionPixelSize(R.dimen.dp_194)), new Point(getResources().getDimensionPixelSize(R.dimen.dp_172), getResources().getDimensionPixelSize(R.dimen.dp_264)), new Point(getResources().getDimensionPixelSize(R.dimen.dp_368), getResources().getDimensionPixelSize(R.dimen.dp_264)));
        }
        this.g.setColor(ContextCompat.getColor(context, R.color.battery_line));
        this.g.setProgressText(getResources().getString(R.string.newenergy_charging_limit));
    }

    public void d() {
        a(this.h);
        this.h = null;
        this.i = -2;
        b(this.f3475b);
        this.g.setAnimation(null);
        this.g.setVisibility(8);
        this.g.c();
    }

    public void setAlphaAnimation() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        long j = 1000;
        alphaAnimation.setDuration(j);
        alphaAnimation.setStartOffset(0L);
        this.f3479f.setAnimation(alphaAnimation);
        AlphaAnimation alphaAnimation2 = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation2.setDuration(750);
        long j2 = 250;
        alphaAnimation2.setStartOffset(j2);
        this.f3477d.setAnimation(alphaAnimation2);
        this.f3476c.setAnimation(alphaAnimation2);
        AlphaAnimation alphaAnimation3 = new AlphaAnimation(0.0f, 1.0f);
        long j3 = 500;
        alphaAnimation3.setDuration(j3);
        alphaAnimation3.setStartOffset(j3);
        this.f3475b.setAnimation(alphaAnimation3);
        AlphaAnimation alphaAnimation4 = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation4.setDuration(j);
        alphaAnimation4.setStartOffset(j2);
        this.f3478e.setAnimation(alphaAnimation4);
    }

    public void setBatteryCharge(int i) {
        try {
            b.b.m.c.a.d(this.f3474a, "[Memory]----[Anim]----[ElectricityManagePager]----setBatteryCharge");
            boolean z = (getResources().getConfiguration().uiMode & 48) == 32;
            int[] iArrA = n.a(b.c().a());
            this.f3478e.setImageDrawable(ContextCompat.getDrawable(getContext(), iArrA[0]));
            this.f3479f.setImageDrawable(z ? null : ContextCompat.getDrawable(getContext(), iArrA[1]));
        } catch (Exception e2) {
            BydLogHelper.logE(e2.getMessage());
        }
    }

    public void setBatteryProgress(int i) {
        if (i < 0 || i > 100) {
            this.f3477d.setText("--%");
            this.f3476c.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.electricity_battery));
            return;
        }
        TypedArray typedArrayObtainTypedArray = getContext().getResources().obtainTypedArray(R.array.anim_battery_progress);
        int length = typedArrayObtainTypedArray.length();
        int length2 = typedArrayObtainTypedArray.length();
        int[] iArr = new int[length2];
        for (int i2 = 0; i2 < length; i2++) {
            iArr[i2] = typedArrayObtainTypedArray.getResourceId(i2, 0);
        }
        typedArrayObtainTypedArray.recycle();
        int i3 = length2 - 1;
        int i4 = (i * i3) / 100;
        if (i4 < length2) {
            i3 = i4;
        }
        this.f3476c.setImageDrawable(ContextCompat.getDrawable(getContext(), iArr[i3 >= 0 ? i3 : 0]));
        this.f3477d.setText(i + "%");
    }

    public void setBatteryScan(int i) {
        if (i == this.i) {
            return;
        }
        this.i = i;
        a(this.h);
        b(this.f3475b);
        if (i != 0) {
            o oVar = new o(this.f3475b, getContext());
            this.h = oVar;
            oVar.i(i == 1 ? R.array.anim_charge_scan : R.array.anim_discharge_scan, 40);
            this.h.n();
        }
    }

    public void setLineTipsView(float f2) {
        this.g.setProgress(f2);
    }

    public void setLineTipsViewGone() {
        if (this.g != null) {
            AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
            alphaAnimation.setDuration(1000L);
            this.g.startAnimation(alphaAnimation);
            this.g.setVisibility(8);
        }
    }

    public void setLineTipsViewVisible() {
        LineTipsView lineTipsView = this.g;
        if (lineTipsView != null) {
            lineTipsView.setVisibility(0);
        }
    }

    public ElectricityAnimView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f3474a = "ElectricityManageFragment";
        this.i = -2;
        c(context);
    }
}
