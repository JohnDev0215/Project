package com.android.car.apps.common;

import android.car.drivingstate.XCarUxRestrictions;
import android.content.Context;
import android.graphics.Rect;
import android.text.method.TransformationMethod;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import com.android.car.ui.R$integer;
import com.android.car.ui.R$string;
import spi.e2;
import spi.mc;

/* JADX INFO: loaded from: classes.dex */
public class UxrTextView extends TextView {
    public e2 a;
    public XCarUxRestrictions b;
    public mc c;

    public class b implements TransformationMethod {
        private b() {
        }

        @Override // android.text.method.TransformationMethod
        public final CharSequence getTransformation(CharSequence charSequence, View view) {
            if (charSequence == null) {
                return "";
            }
            Context context = UxrTextView.this.getContext();
            String string = charSequence.toString();
            XCarUxRestrictions xCarUxRestrictions = UxrTextView.this.b;
            if (!e2.b(4, xCarUxRestrictions)) {
                return string;
            }
            int integer = xCarUxRestrictions == null ? context.getResources().getInteger(R$integer.car_ui_default_max_string_length) : xCarUxRestrictions.getMaxRestrictedStringLength();
            if (string.length() <= integer) {
                return string;
            }
            return string.substring(0, integer) + context.getString(R$string.car_ui_ellipsis);
        }

        @Override // android.text.method.TransformationMethod
        public final void onFocusChanged(View view, CharSequence charSequence, boolean z, int i, Rect rect) {
        }
    }

    public UxrTextView(Context context) {
        super(context);
        a(context);
    }

    public final void a(Context context) {
        this.a = e2.a(context);
        this.c = new mc(this, 0);
    }

    @Override // android.widget.TextView, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.a.register(this.c);
    }

    @Override // android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.a.unregister(this.c);
    }

    @Override // android.widget.TextView
    public void setAllCaps(boolean z) {
    }

    @Override // android.widget.TextView
    public void setInputType(int i) {
    }

    @Override // android.widget.TextView
    public void setSingleLine(boolean z) {
    }

    public UxrTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public UxrTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    public UxrTextView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        a(context);
    }
}
