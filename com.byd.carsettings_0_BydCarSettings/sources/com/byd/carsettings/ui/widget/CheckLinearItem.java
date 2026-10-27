package com.byd.carsettings.ui.widget;

import android.content.Context;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import b.b.g.q.h.b;
import com.byd.carsettings.R;
import com.byd.carsettings.ui.widget.LinearItem;

/* JADX INFO: loaded from: classes2.dex */
public class CheckLinearItem extends LinearItem implements b {
    public View D;
    public volatile boolean E;
    public boolean F;

    public CheckLinearItem(Context context) {
        super(context);
        this.E = false;
        this.F = true;
        r();
    }

    @Override // com.byd.carsettings.ui.widget.LinearItem
    public int getLayoutId() {
        return R.layout.item_check_linear;
    }

    @Override // android.widget.Checkable
    public boolean isChecked() {
        return this.E;
    }

    @Override // com.byd.carsettings.ui.widget.LinearItem
    public void m() {
        if (h()) {
            return;
        }
        q();
    }

    @Override // com.byd.carsettings.ui.widget.LinearItem, android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
    }

    public final void q() {
        ImageView imageView;
        int i;
        if (this.f3428c == null || (imageView = this.f3429d) == null || imageView.getVisibility() == 8) {
            return;
        }
        TextView textView = this.f3428c;
        ViewGroup.LayoutParams layoutParams = textView.getLayoutParams();
        int measuredWidth = ((ViewGroup) textView.getParent()).getMeasuredWidth();
        int measuredWidth2 = textView.getMeasuredWidth();
        int measuredWidth3 = this.f3429d.getMeasuredWidth();
        if (measuredWidth <= 0 || measuredWidth2 <= (i = measuredWidth - measuredWidth3)) {
            this.x = LinearItem.RelayoutState.DO_NOTHING_CHECKED_L;
            return;
        }
        layoutParams.width = i;
        textView.setSingleLine(true);
        TransformationMethod transformationMethod = textView.getTransformationMethod();
        TransformationMethod hideReturnsTransformationMethod = null;
        if (this.s && this.t) {
            if (!(transformationMethod instanceof PasswordTransformationMethod)) {
                hideReturnsTransformationMethod = PasswordTransformationMethod.getInstance();
            }
        } else if (!(transformationMethod instanceof HideReturnsTransformationMethod)) {
            hideReturnsTransformationMethod = HideReturnsTransformationMethod.getInstance();
        }
        if (hideReturnsTransformationMethod != null) {
            textView.setTransformationMethod(hideReturnsTransformationMethod);
        }
        textView.setLayoutParams(layoutParams);
        this.x = LinearItem.RelayoutState.LAYOUT_COMPLETED;
    }

    public final void r() {
        this.D = this.f3426a.findViewById(R.id.v_item_bg_checked_item_check_linear);
    }

    public void setCheckable(boolean z) {
        this.F = z;
    }

    @Override // android.widget.Checkable
    public void setChecked(boolean z) {
        if (this.F) {
            this.E = z;
            this.D.setVisibility(z ? 0 : 8);
        }
    }

    @Override // com.byd.carsettings.ui.widget.LinearItem, b.b.a.j.c.a
    public /* bridge */ /* synthetic */ void setInvalid(boolean z) {
        super.setInvalid(z);
    }

    @Override // com.byd.carsettings.ui.widget.LinearItem, b.b.a.j.c.a
    public /* bridge */ /* synthetic */ void setInvalid(boolean z, boolean z2) {
        super.setInvalid(z, z2);
    }

    @Override // b.b.g.q.h.b
    public void setOnCheckedChangeWidgetListener(CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
    }

    @Override // android.widget.Checkable
    public void toggle() {
        setChecked(!this.E);
    }

    public CheckLinearItem(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.E = false;
        this.F = true;
        r();
    }

    public CheckLinearItem(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.E = false;
        this.F = true;
        r();
    }
}
