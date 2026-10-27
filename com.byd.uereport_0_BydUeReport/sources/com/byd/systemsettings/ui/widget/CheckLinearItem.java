package com.byd.systemsettings.ui.widget;

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
import com.byd.systemsettings.R;
import g4.a;
import s2.a;

/* JADX INFO: loaded from: classes2.dex */
public class CheckLinearItem extends LinearItem implements a {
    private static final String TAG = "CheckLinearItem";
    public boolean checkable;
    public volatile boolean isChecked;
    public View vChecked;

    public CheckLinearItem(Context context) {
        super(context);
        this.isChecked = false;
        this.checkable = true;
    }

    private void checkAndChangeTextRightWidth() {
        ImageView imageView;
        int i8;
        if (this.tvRight == null || (imageView = this.ivRight) == null || imageView.getVisibility() == 8) {
            return;
        }
        TextView textView = this.tvRight;
        ViewGroup.LayoutParams layoutParams = textView.getLayoutParams();
        int measuredWidth = ((ViewGroup) textView.getParent()).getMeasuredWidth();
        int measuredWidth2 = textView.getMeasuredWidth();
        int measuredWidth3 = this.ivRight.getMeasuredWidth();
        if (measuredWidth <= 0 || measuredWidth2 <= (i8 = measuredWidth - measuredWidth3)) {
            return;
        }
        x3.a.a(TAG, "---checkAndChangeTextRightWidth---ItemTag:" + getItemTag() + ", parentWidth: " + measuredWidth + ", viewWidth: " + measuredWidth2 + ", ivWidth: " + measuredWidth3);
        layoutParams.width = i8;
        textView.setSingleLine(true);
        TransformationMethod transformationMethod = textView.getTransformationMethod();
        TransformationMethod hideReturnsTransformationMethod = null;
        if (this.isPwdHide && this.pwdRight) {
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
    }

    @Override // com.byd.systemsettings.ui.widget.LinearItem
    public int getLayoutId() {
        return R.layout.item_check_linear;
    }

    @Override // com.byd.systemsettings.ui.widget.LinearItem
    public void initViews(Context context) {
        super.initViews(context);
        this.vChecked = this.content.findViewById(R.id.v_item_bg_checked_item_check_linear);
    }

    public boolean isCheckable() {
        return this.checkable;
    }

    @Override // android.widget.Checkable
    public boolean isChecked() {
        return this.isChecked;
    }

    @Override // com.byd.systemsettings.ui.widget.LinearItem, s2.a
    public /* bridge */ /* synthetic */ boolean isInvalid() {
        return super.isInvalid();
    }

    @Override // com.byd.systemsettings.ui.widget.LinearItem
    public void multilingualStrategy() {
        if (checkNothingTodo()) {
            return;
        }
        x3.a.a(TAG, "---multilingualStrategy---strategyExecuteCount: " + this.strategyExecuteCount + ", ItemTag: " + getItemTag());
        checkAndChangeTextRightWidth();
    }

    @Override // com.byd.systemsettings.ui.widget.LinearItem, android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z7, int i8, int i9, int i10, int i11) {
        super.onLayout(z7, i8, i9, i10, i11);
    }

    @Override // com.byd.systemsettings.ui.widget.LinearItem, s2.a
    public /* bridge */ /* synthetic */ void registerInvalidStateChangedCallback(a.InterfaceC0117a interfaceC0117a) {
        super.registerInvalidStateChangedCallback(interfaceC0117a);
    }

    public void setCheckable(boolean z7) {
        this.checkable = z7;
    }

    @Override // android.widget.Checkable
    public void setChecked(boolean z7) {
        if (this.checkable) {
            this.isChecked = z7;
            this.vChecked.setVisibility(z7 ? 0 : 8);
        }
    }

    @Override // com.byd.systemsettings.ui.widget.LinearItem, s2.a
    public /* bridge */ /* synthetic */ void setInvalid(boolean z7) {
        super.setInvalid(z7);
    }

    @Override // com.byd.systemsettings.ui.widget.LinearItem, s2.a
    public /* bridge */ /* synthetic */ void setInvalid(boolean z7, boolean z8) {
        super.setInvalid(z7, z8);
    }

    public void setOnCheckedChangeWidgetListener(CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
    }

    @Override // android.widget.Checkable
    public void toggle() {
        setChecked(!this.isChecked);
    }

    @Override // com.byd.systemsettings.ui.widget.LinearItem, s2.a
    public /* bridge */ /* synthetic */ void unregisterInvalidStateChangedCallback() {
        super.unregisterInvalidStateChangedCallback();
    }

    public CheckLinearItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.isChecked = false;
        this.checkable = true;
    }

    public CheckLinearItem(Context context, AttributeSet attributeSet, int i8) {
        super(context, attributeSet, i8);
        this.isChecked = false;
        this.checkable = true;
    }

    public CheckLinearItem(Context context, AttributeSet attributeSet, int i8, int i9) {
        super(context, attributeSet, i8, i9);
        this.isChecked = false;
        this.checkable = true;
    }
}
