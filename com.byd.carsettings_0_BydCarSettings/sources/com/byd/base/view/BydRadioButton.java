package com.byd.base.view;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import com.byd.base.R$dimen;
import com.byd.base.R$styleable;

/* JADX INFO: loaded from: classes.dex */
public class BydRadioButton extends RadioButton {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f2455a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public CharSequence[] f2456b;

    public BydRadioButton(Context context) {
        this(context, null);
    }

    @Override // android.widget.TextView, android.view.View
    public void onMeasure(int i, int i2) {
        int size = View.MeasureSpec.getSize(i);
        View.MeasureSpec.getSize(i2);
        if (this.f2456b == null) {
            super.onMeasure(i, i2);
            return;
        }
        int i3 = 0;
        int iMax = 0;
        while (true) {
            CharSequence[] charSequenceArr = this.f2456b;
            if (i3 >= charSequenceArr.length) {
                float f2 = iMax;
                Resources resources = this.f2455a.getResources();
                int i4 = R$dimen.dp_40;
                setMeasuredDimension(size, (int) Math.max(f2, resources.getDimension(i4)));
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) ((RadioGroup) getParent()).getLayoutParams();
                layoutParams.height = (int) Math.max(f2, this.f2455a.getResources().getDimension(i4));
                ((RadioGroup) getParent()).setLayoutParams(layoutParams);
                return;
            }
            String string = charSequenceArr[i3].toString();
            getPaint().getTextBounds(string, 0, string.length(), new Rect());
            iMax = Math.max(iMax, (int) ((r3.height() * ((((r3.width() + getPaddingStart()) + getPaddingEnd()) / ((size / 2) - this.f2455a.getResources().getDimension(R$dimen.dp_8))) + 1.0f)) + getPaddingBottom() + getPaddingTop()));
            i3++;
        }
    }

    public BydRadioButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BydRadioButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f2455a = context;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.BydRadioButton);
        this.f2456b = typedArrayObtainStyledAttributes.getTextArray(R$styleable.BydRadioButton_cal_height_strings_list);
        typedArrayObtainStyledAttributes.recycle();
    }
}
