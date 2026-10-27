package com.byd.carsettings.ui.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import b.b.a.j.c.a;
import com.byd.carsettings.R;

/* JADX INFO: loaded from: classes2.dex */
public class SimpleLinearItem extends FrameLayout implements a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public View f3447a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public TextView f3448b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public ImageView f3449c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public View f3450d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f3451e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f3452f;
    public int g;
    public int h;
    public int i;
    public int j;
    public boolean k;
    public boolean l;

    public SimpleLinearItem(Context context) {
        super(context);
        this.f3451e = 0;
        this.f3452f = 0;
        this.g = 0;
        this.h = 0;
        this.i = 0;
        this.j = 0;
        this.k = true;
        this.l = false;
        e(context);
    }

    public final void d(Context context, AttributeSet attributeSet) {
        if (attributeSet == null) {
            return;
        }
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.linear_item);
        this.f3451e = typedArrayObtainStyledAttributes.getResourceId(6, this.f3451e);
        this.k = typedArrayObtainStyledAttributes.getBoolean(5, this.k);
        this.l = typedArrayObtainStyledAttributes.getBoolean(4, this.l);
        this.f3452f = typedArrayObtainStyledAttributes.getResourceId(7, this.f3452f);
        this.g = typedArrayObtainStyledAttributes.getDimensionPixelSize(8, this.g);
        this.h = typedArrayObtainStyledAttributes.getDimensionPixelSize(2, this.h);
        this.i = typedArrayObtainStyledAttributes.getDimensionPixelSize(1, this.i);
        this.j = typedArrayObtainStyledAttributes.getResourceId(0, this.j);
        typedArrayObtainStyledAttributes.recycle();
    }

    public final void e(Context context) {
        View viewInflate = LayoutInflater.from(context).inflate(getLayoutId(), (ViewGroup) null);
        this.f3447a = viewInflate;
        this.f3448b = (TextView) viewInflate.findViewById(R.id.tv_left_item_simple_linear);
        this.f3449c = (ImageView) this.f3447a.findViewById(R.id.iv_right_item_simple_linear);
        this.f3450d = this.f3447a.findViewById(R.id.v_line_botoom_item_simple_linear);
        Resources resources = getResources();
        int i = this.f3451e;
        if (i != 0) {
            this.f3448b.setText(resources.getString(i));
        }
        int i2 = this.f3452f;
        if (i2 != 0) {
            this.f3448b.setTextColor(resources.getColor(i2));
        }
        int i3 = this.g;
        if (i3 != 0) {
            this.f3448b.setTextSize(0, i3);
        }
        int i4 = this.h;
        if (i4 != 0) {
            f(this.f3448b, i4, -1);
        }
        int i5 = this.i;
        if (i5 != 0) {
            f(this.f3450d, i5, i5);
        }
        int i6 = this.j;
        if (i6 != 0) {
            this.f3450d.setBackgroundColor(resources.getColor(i6));
        }
        this.f3449c.setVisibility(this.k ? 0 : 8);
        this.f3450d.setVisibility(this.l ? 0 : 8);
        addView(this.f3447a);
    }

    public final void f(View view, int i, int i2) {
        if (view == null) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
            if (i >= 0) {
                b.b.m.g.a.d(marginLayoutParams, "startMargin", Integer.valueOf(i));
            }
            if (i2 >= 0) {
                b.b.m.g.a.d(marginLayoutParams, "endMargin", Integer.valueOf(i2));
            }
            view.setLayoutParams(marginLayoutParams);
        }
    }

    public int getLayoutId() {
        return R.layout.item_simple_linear;
    }

    @Override // b.b.a.j.c.a
    public /* bridge */ /* synthetic */ void setInvalid(boolean z) {
        super.setInvalid(z);
    }

    @Override // b.b.a.j.c.a
    public /* bridge */ /* synthetic */ void setInvalid(boolean z, boolean z2) {
        super.setInvalid(z, z2);
    }

    public void setLeftText(String str) {
        TextView textView = this.f3448b;
        if (textView != null) {
            textView.setText(str);
        }
    }

    public void setView(View view) {
        if (view == null) {
            return;
        }
        removeAllViews();
        addView(view);
    }

    public SimpleLinearItem(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f3451e = 0;
        this.f3452f = 0;
        this.g = 0;
        this.h = 0;
        this.i = 0;
        this.j = 0;
        this.k = true;
        this.l = false;
        d(context, attributeSet);
        e(context);
    }

    public SimpleLinearItem(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f3451e = 0;
        this.f3452f = 0;
        this.g = 0;
        this.h = 0;
        this.i = 0;
        this.j = 0;
        this.k = true;
        this.l = false;
        d(context, attributeSet);
        e(context);
    }
}
