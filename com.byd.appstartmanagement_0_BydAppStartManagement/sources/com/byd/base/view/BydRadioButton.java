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

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Context f545b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public CharSequence[] f546c;

    public BydRadioButton(Context context) {
        this(context, null);
    }

    @Override // android.widget.TextView, android.view.View
    public void onMeasure(int i, int i2) {
        int size = View.MeasureSpec.getSize(i);
        View.MeasureSpec.getSize(i2);
        if (this.f546c == null) {
            super.onMeasure(i, i2);
            return;
        }
        int i3 = 0;
        int iMax = 0;
        while (true) {
            CharSequence[] charSequenceArr = this.f546c;
            if (i3 >= charSequenceArr.length) {
                float f = iMax;
                Resources resources = this.f545b.getResources();
                int i4 = R$dimen.dp_40;
                setMeasuredDimension(size, (int) Math.max(f, resources.getDimension(i4)));
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) ((RadioGroup) getParent()).getLayoutParams();
                layoutParams.height = (int) Math.max(f, this.f545b.getResources().getDimension(i4));
                ((RadioGroup) getParent()).setLayoutParams(layoutParams);
                return;
            }
            String string = charSequenceArr[i3].toString();
            getPaint().getTextBounds(string, 0, string.length(), new Rect());
            iMax = Math.max(iMax, (int) ((r3.height() * ((((r3.width() + getPaddingStart()) + getPaddingEnd()) / ((size / 2) - this.f545b.getResources().getDimension(R$dimen.dp_8))) + 1.0f)) + getPaddingBottom() + getPaddingTop()));
            i3++;
        }
    }

    public BydRadioButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BydRadioButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f545b = context;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.BydRadioButton);
        this.f546c = typedArrayObtainStyledAttributes.getTextArray(R$styleable.BydRadioButton_cal_height_strings_list);
        typedArrayObtainStyledAttributes.recycle();
    }
}
