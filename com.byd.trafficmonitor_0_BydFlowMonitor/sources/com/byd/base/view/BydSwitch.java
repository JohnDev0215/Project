package com.byd.base.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.CompoundButton;
import androidx.appcompat.widget.SwitchCompat;
import b.h.f.l.a;
import c.a.a.f;

/* JADX INFO: loaded from: classes.dex */
public class BydSwitch extends SwitchCompat implements CompoundButton.OnCheckedChangeListener {
    public int Q;
    public int R;
    public CompoundButton.OnCheckedChangeListener S;

    public BydSwitch(Context context) {
        super(context);
        this.Q = 0;
        this.R = 0;
        o(context);
    }

    public void m(int i2, int i3) {
        Drawable drawable = getResources().getDrawable(i2);
        if (i3 <= 0) {
            setBackground(drawable);
            return;
        }
        Drawable drawableR = a.r(drawable.mutate());
        a.n(drawableR, b.h.e.a.b(getContext(), i3));
        setBackground(drawableR);
    }

    public final void n(Context context, AttributeSet attributeSet) {
        if (attributeSet == null) {
            return;
        }
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, f.byd_switch);
        this.Q = typedArrayObtainStyledAttributes.getResourceId(f.byd_switch_bgOpenColor, this.Q);
        this.R = typedArrayObtainStyledAttributes.getResourceId(f.byd_switch_bgCloseColor, this.R);
        typedArrayObtainStyledAttributes.recycle();
    }

    public final void o(Context context) {
        super.setOnCheckedChangeListener(this);
        setScrollBarFadeDuration(1000);
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        if (z) {
            m(c.a.a.a.img_switch_bg_select, this.Q);
        } else {
            m(c.a.a.a.img_switch_bg_normal, this.R);
        }
        CompoundButton.OnCheckedChangeListener onCheckedChangeListener = this.S;
        if (onCheckedChangeListener != null) {
            onCheckedChangeListener.onCheckedChanged(compoundButton, z);
        }
    }

    @Override // android.widget.CompoundButton
    public void setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        this.S = onCheckedChangeListener;
    }

    public BydSwitch(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.Q = 0;
        this.R = 0;
        n(context, attributeSet);
        o(context);
    }

    public BydSwitch(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.Q = 0;
        this.R = 0;
        n(context, attributeSet);
        o(context);
    }
}
