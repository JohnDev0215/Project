package com.byd.trafficmonitor.widget;

import android.R;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.ViewParent;
import android.widget.LinearLayout;
import c.a.d.l;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: loaded from: classes.dex */
public class RotateLinearLayout extends LinearLayout {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f4441b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f4442c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f4443d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f4444e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f4445f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f4446g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f4447h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f4448i;

    public RotateLinearLayout(Context context) {
        this(context, null);
    }

    @TargetApi(29)
    public final void a(Context context, AttributeSet attributeSet) {
        boolean z = context.getResources().getConfiguration().orientation == 2;
        b(attributeSet);
        BydLogHelper.logD("RotateLinearLayout", " landscape =" + z + ", initAttribute  layoutWidth=" + this.f4447h + ", layoutHeight =" + this.f4448i);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, l.RotateLinearLayout, 0, 0);
        this.f4442c = typedArrayObtainStyledAttributes.getDimensionPixelSize(6, 0);
        this.f4441b = typedArrayObtainStyledAttributes.getDimensionPixelSize(5, 0);
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(1, 0);
        if (!z || dimensionPixelSize <= 0) {
            dimensionPixelSize = this.f4441b;
        }
        this.f4441b = dimensionPixelSize;
        this.f4446g = typedArrayObtainStyledAttributes.getBoolean(2, false);
        if (z) {
            this.f4443d = typedArrayObtainStyledAttributes.getDimensionPixelSize(4, 0);
            this.f4444e = typedArrayObtainStyledAttributes.getDimensionPixelSize(3, 0);
            int i2 = typedArrayObtainStyledAttributes.getInt(0, 0);
            this.f4445f = i2;
            setOrientation(i2);
        }
        typedArrayObtainStyledAttributes.recycle();
    }

    public final void b(AttributeSet attributeSet) {
        if (attributeSet != null) {
            if (this.f4448i <= 0 || this.f4447h <= 0) {
                TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, new int[]{R.attr.layout_width, R.attr.layout_height});
                if (typedArrayObtainStyledAttributes == null) {
                    return;
                }
                this.f4447h = typedArrayObtainStyledAttributes.getLayoutDimension(0, -1);
                this.f4448i = typedArrayObtainStyledAttributes.getLayoutDimension(1, -1);
                typedArrayObtainStyledAttributes.recycle();
            }
        }
    }

    public final void c(LinearLayout.LayoutParams layoutParams, int i2, int i3) {
        if (!this.f4446g || i2 <= 0 || this.f4445f == 1) {
            return;
        }
        ViewParent parent = getParent();
        if ((parent instanceof LinearLayout) && ((LinearLayout) parent).getOrientation() == i3) {
            if (i3 == 0) {
                layoutParams.leftMargin = i2;
                layoutParams.topMargin = 0;
            } else {
                layoutParams.topMargin = i2;
                layoutParams.leftMargin = 0;
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) getLayoutParams();
        if (layoutParams == null) {
            return;
        }
        int i2 = getContext().getResources().getConfiguration().orientation != 1 ? 0 : 1;
        int i3 = i2 ^ 1;
        if (i2 == 0) {
            int i4 = this.f4441b;
            if (i4 > 0) {
                layoutParams.leftMargin = i4;
            }
            int i5 = this.f4443d;
            if (i5 > 0) {
                layoutParams.width = i5;
            }
            setOrientation(this.f4445f);
            c(layoutParams, this.f4442c, i3);
        } else {
            int i6 = this.f4442c;
            if (i6 > 0) {
                layoutParams.topMargin = i6;
            }
            c(layoutParams, this.f4441b, i3);
        }
        BydLogHelper.logD("RotateLinearLayout", " onAttachedToWindow is end");
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onMeasure(int i2, int i3) {
        if (getContext().getResources().getConfiguration().orientation == 2) {
            int i4 = this.f4443d;
            if (i4 > 0) {
                i2 = i4;
            }
            int i5 = this.f4444e;
            if (i5 > 0) {
                i3 = i5;
            }
        }
        super.onMeasure(i2, i3);
    }

    public RotateLinearLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RotateLinearLayout(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        a(context, attributeSet);
    }
}
