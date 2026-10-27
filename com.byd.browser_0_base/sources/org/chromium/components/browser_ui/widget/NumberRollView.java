package org.chromium.components.browser_ui.widget;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.util.AttributeSet;
import android.util.FloatProperty;
import android.widget.FrameLayout;
import android.widget.TextView;
import defpackage.pc;
import java.text.NumberFormat;

/* JADX INFO: loaded from: classes2.dex */
public class NumberRollView extends FrameLayout {
    public static final a k = new a();
    public TextView e;
    public TextView f;
    public float g;
    public ObjectAnimator h;
    public int i;
    public int j;

    public class a extends FloatProperty<NumberRollView> {
        public a() {
            super("");
        }

        @Override // android.util.Property
        public final Float get(Object obj) {
            return Float.valueOf(((NumberRollView) obj).getNumberRoll());
        }

        @Override // android.util.FloatProperty
        public final void setValue(NumberRollView numberRollView, float f) {
            numberRollView.setNumberRoll(f);
        }
    }

    public NumberRollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float getNumberRoll() {
        return this.g;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setNumberRoll(float f) {
        this.g = f;
        int i = (int) f;
        int i2 = i + 1;
        NumberFormat integerInstance = NumberFormat.getIntegerInstance();
        String quantityString = this.i != 0 ? (i2 != 0 || this.j == 0) ? getResources().getQuantityString(this.i, i2, Integer.valueOf(i2)) : getResources().getString(this.j) : integerInstance.format(i2);
        if (!quantityString.equals(this.e.getText().toString())) {
            this.e.setText(quantityString);
        }
        String quantityString2 = this.i != 0 ? (i != 0 || this.j == 0) ? getResources().getQuantityString(this.i, i, Integer.valueOf(i)) : getResources().getString(this.j) : integerInstance.format(i);
        if (!quantityString2.equals(this.f.getText().toString())) {
            this.f.setText(quantityString2);
        }
        float f2 = f % 1.0f;
        this.e.setTranslationY((f2 - 1.0f) * r1.getHeight());
        this.f.setTranslationY(r1.getHeight() * f2);
        this.e.setAlpha(f2);
        this.f.setAlpha(1.0f - f2);
    }

    public final void c(int i, boolean z) {
        ObjectAnimator objectAnimator = this.h;
        if (objectAnimator != null) {
            objectAnimator.cancel();
        }
        if (!z) {
            setNumberRoll(i);
            return;
        }
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this, k, i);
        objectAnimatorOfFloat.setInterpolator(pc.c);
        objectAnimatorOfFloat.start();
        this.h = objectAnimatorOfFloat;
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.e = (TextView) findViewById(R$id.up);
        this.f = (TextView) findViewById(R$id.down);
        setNumberRoll(this.g);
    }

    public void setString(int i) {
        this.i = i;
    }

    public void setStringForZero(int i) {
        this.j = i;
    }

    public void setTextColorStateList(ColorStateList colorStateList) {
        this.e.setTextColor(colorStateList);
        this.f.setTextColor(colorStateList);
    }
}
