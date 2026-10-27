package com.byd.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import b.g.b.a;
import c.a.d.d;
import c.a.d.g;
import com.bydux.api.R;

/* JADX INFO: loaded from: classes.dex */
public class ChargeAnimView extends FrameLayout {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public transient g f1978b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public transient int f1979c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public transient ImageView f1980d;
    public transient ImageView e;
    public transient ImageView f;
    public transient TextView g;

    public ChargeAnimView(Context context) {
        super(context, null);
        this.f1979c = -2;
    }

    public void a(g... gVarArr) {
        if (gVarArr == null || gVarArr.length <= 0) {
            return;
        }
        for (g gVar : gVarArr) {
            if (gVar != null) {
                gVar.o();
                gVar.m();
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
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.anim_charge_view, (ViewGroup) null, false);
        addView(viewInflate);
        ImageView imageView = (ImageView) viewInflate.findViewById(R.id.iv_car);
        this.f1980d = imageView;
        imageView.setRotationY(d.f1595a ? 180.0f : 0.0f);
        this.e = (ImageView) viewInflate.findViewById(R.id.iv_battery_progress);
        this.g = (TextView) viewInflate.findViewById(R.id.tv_battery);
        this.f = (ImageView) viewInflate.findViewById(R.id.iv_scan);
    }

    public void d() {
        a(this.f1978b);
        this.f1978b = null;
        this.f1979c = -2;
    }

    public void setBatteryCharge(Context context) {
        this.f1980d.setImageDrawable(a.d(getContext(), c.a.d.a.b(BYDAutoBodyworkDevice.getInstance(context).getAutoType())));
    }

    public void setBatteryProgress(int i) {
        if (i < 0 || i > 100) {
            this.e.setImageDrawable(a.d(getContext(), R.drawable.battery_progress_000));
            this.g.setText("--");
            return;
        }
        TypedArray typedArrayObtainTypedArray = getContext().getResources().obtainTypedArray(R.array.battery_progress);
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
        this.e.setImageDrawable(a.d(getContext(), iArr[i3 >= 0 ? i3 : 0]));
        this.g.setText(i + "");
    }

    public void setBatteryScan(int i) {
        if (i == this.f1979c) {
            return;
        }
        this.f1979c = i;
        a(this.f1978b);
        b(this.f);
        if (this.f1979c == 1) {
            g gVar = new g(this.f, getContext());
            this.f1978b = gVar;
            gVar.i(R.array.battery_scan, 40);
            this.f1978b.n();
        }
    }

    public ChargeAnimView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1979c = -2;
        c(context);
    }
}
