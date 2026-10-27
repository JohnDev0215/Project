package androidx.appcompat.widget;

import android.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.textclassifier.TextClassifier;
import android.widget.TextView;
import defpackage.b3;
import defpackage.d3;
import defpackage.f2;
import defpackage.i2;
import defpackage.ja;
import defpackage.n2;
import defpackage.o2;
import defpackage.o8;
import defpackage.q7;
import defpackage.qa;
import defpackage.s9;
import defpackage.sa;
import defpackage.u0;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/* JADX INFO: loaded from: classes.dex */
public class AppCompatTextView extends TextView implements s9, sa, ja {
    public final f2 b;
    public final o2 c;
    public final n2 d;
    public Future<o8> e;

    public AppCompatTextView(Context context) {
        this(context, null);
    }

    public final void c() {
        Future<o8> future = this.e;
        if (future != null) {
            try {
                this.e = null;
                qa.p(this, future.get());
            } catch (InterruptedException | ExecutionException unused) {
            }
        }
    }

    @Override // android.widget.TextView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        f2 f2Var = this.b;
        if (f2Var != null) {
            f2Var.b();
        }
        o2 o2Var = this.c;
        if (o2Var != null) {
            o2Var.b();
        }
    }

    @Override // android.widget.TextView
    public int getAutoSizeMaxTextSize() {
        if (ja.a) {
            return super.getAutoSizeMaxTextSize();
        }
        o2 o2Var = this.c;
        if (o2Var != null) {
            return o2Var.e();
        }
        return -1;
    }

    @Override // android.widget.TextView
    public int getAutoSizeMinTextSize() {
        if (ja.a) {
            return super.getAutoSizeMinTextSize();
        }
        o2 o2Var = this.c;
        if (o2Var != null) {
            return o2Var.f();
        }
        return -1;
    }

    @Override // android.widget.TextView
    public int getAutoSizeStepGranularity() {
        if (ja.a) {
            return super.getAutoSizeStepGranularity();
        }
        o2 o2Var = this.c;
        if (o2Var != null) {
            return o2Var.g();
        }
        return -1;
    }

    @Override // android.widget.TextView
    public int[] getAutoSizeTextAvailableSizes() {
        if (ja.a) {
            return super.getAutoSizeTextAvailableSizes();
        }
        o2 o2Var = this.c;
        return o2Var != null ? o2Var.h() : new int[0];
    }

    @Override // android.widget.TextView
    @SuppressLint({"WrongConstant"})
    public int getAutoSizeTextType() {
        if (ja.a) {
            return super.getAutoSizeTextType() == 1 ? 1 : 0;
        }
        o2 o2Var = this.c;
        if (o2Var != null) {
            return o2Var.i();
        }
        return 0;
    }

    @Override // android.widget.TextView
    public int getFirstBaselineToTopHeight() {
        return qa.b(this);
    }

    @Override // android.widget.TextView
    public int getLastBaselineToBottomHeight() {
        return qa.c(this);
    }

    @Override // defpackage.s9
    public ColorStateList getSupportBackgroundTintList() {
        f2 f2Var = this.b;
        if (f2Var != null) {
            return f2Var.c();
        }
        return null;
    }

    @Override // defpackage.s9
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        f2 f2Var = this.b;
        if (f2Var != null) {
            return f2Var.d();
        }
        return null;
    }

    public ColorStateList getSupportCompoundDrawablesTintList() {
        return this.c.j();
    }

    public PorterDuff.Mode getSupportCompoundDrawablesTintMode() {
        return this.c.k();
    }

    @Override // android.widget.TextView
    public CharSequence getText() {
        c();
        return super.getText();
    }

    @Override // android.widget.TextView
    public TextClassifier getTextClassifier() {
        n2 n2Var;
        return (Build.VERSION.SDK_INT >= 28 || (n2Var = this.d) == null) ? super.getTextClassifier() : n2Var.a();
    }

    public o8.a getTextMetricsParamsCompat() {
        return qa.g(this);
    }

    @Override // android.widget.TextView, android.view.View
    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        return i2.a(super.onCreateInputConnection(editorInfo), editorInfo, this);
    }

    @Override // android.widget.TextView, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        o2 o2Var = this.c;
        if (o2Var != null) {
            o2Var.o(z, i, i2, i3, i4);
        }
    }

    @Override // android.widget.TextView, android.view.View
    public void onMeasure(int i, int i2) {
        c();
        super.onMeasure(i, i2);
    }

    @Override // android.widget.TextView
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        super.onTextChanged(charSequence, i, i2, i3);
        o2 o2Var = this.c;
        if (o2Var == null || ja.a || !o2Var.l()) {
            return;
        }
        this.c.c();
    }

    @Override // android.widget.TextView
    public void setAutoSizeTextTypeUniformWithConfiguration(int i, int i2, int i3, int i4) throws IllegalArgumentException {
        if (ja.a) {
            super.setAutoSizeTextTypeUniformWithConfiguration(i, i2, i3, i4);
            return;
        }
        o2 o2Var = this.c;
        if (o2Var != null) {
            o2Var.s(i, i2, i3, i4);
        }
    }

    @Override // android.widget.TextView
    public void setAutoSizeTextTypeUniformWithPresetSizes(int[] iArr, int i) throws IllegalArgumentException {
        if (ja.a) {
            super.setAutoSizeTextTypeUniformWithPresetSizes(iArr, i);
            return;
        }
        o2 o2Var = this.c;
        if (o2Var != null) {
            o2Var.t(iArr, i);
        }
    }

    @Override // android.widget.TextView
    public void setAutoSizeTextTypeWithDefaults(int i) {
        if (ja.a) {
            super.setAutoSizeTextTypeWithDefaults(i);
            return;
        }
        o2 o2Var = this.c;
        if (o2Var != null) {
            o2Var.u(i);
        }
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        f2 f2Var = this.b;
        if (f2Var != null) {
            f2Var.f(drawable);
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        f2 f2Var = this.b;
        if (f2Var != null) {
            f2Var.g(i);
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawables(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawables(drawable, drawable2, drawable3, drawable4);
        o2 o2Var = this.c;
        if (o2Var != null) {
            o2Var.p();
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesRelative(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesRelative(drawable, drawable2, drawable3, drawable4);
        o2 o2Var = this.c;
        if (o2Var != null) {
            o2Var.p();
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesRelativeWithIntrinsicBounds(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
        o2 o2Var = this.c;
        if (o2Var != null) {
            o2Var.p();
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesWithIntrinsicBounds(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
        o2 o2Var = this.c;
        if (o2Var != null) {
            o2Var.p();
        }
    }

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(qa.s(this, callback));
    }

    @Override // android.widget.TextView
    public void setFirstBaselineToTopHeight(int i) {
        if (Build.VERSION.SDK_INT >= 28) {
            super.setFirstBaselineToTopHeight(i);
        } else {
            qa.m(this, i);
        }
    }

    @Override // android.widget.TextView
    public void setLastBaselineToBottomHeight(int i) {
        if (Build.VERSION.SDK_INT >= 28) {
            super.setLastBaselineToBottomHeight(i);
        } else {
            qa.n(this, i);
        }
    }

    @Override // android.widget.TextView
    public void setLineHeight(int i) {
        qa.o(this, i);
    }

    public void setPrecomputedText(o8 o8Var) {
        qa.p(this, o8Var);
    }

    @Override // defpackage.s9
    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        f2 f2Var = this.b;
        if (f2Var != null) {
            f2Var.i(colorStateList);
        }
    }

    @Override // defpackage.s9
    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        f2 f2Var = this.b;
        if (f2Var != null) {
            f2Var.j(mode);
        }
    }

    @Override // defpackage.sa
    public void setSupportCompoundDrawablesTintList(ColorStateList colorStateList) {
        this.c.v(colorStateList);
        this.c.b();
    }

    @Override // defpackage.sa
    public void setSupportCompoundDrawablesTintMode(PorterDuff.Mode mode) {
        this.c.w(mode);
        this.c.b();
    }

    @Override // android.widget.TextView
    public void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        o2 o2Var = this.c;
        if (o2Var != null) {
            o2Var.q(context, i);
        }
    }

    @Override // android.widget.TextView
    public void setTextClassifier(TextClassifier textClassifier) {
        n2 n2Var;
        if (Build.VERSION.SDK_INT >= 28 || (n2Var = this.d) == null) {
            super.setTextClassifier(textClassifier);
        } else {
            n2Var.b(textClassifier);
        }
    }

    public void setTextFuture(Future<o8> future) {
        this.e = future;
        if (future != null) {
            requestLayout();
        }
    }

    public void setTextMetricsParamsCompat(o8.a aVar) {
        qa.r(this, aVar);
    }

    @Override // android.widget.TextView
    public void setTextSize(int i, float f) {
        if (ja.a) {
            super.setTextSize(i, f);
            return;
        }
        o2 o2Var = this.c;
        if (o2Var != null) {
            o2Var.z(i, f);
        }
    }

    @Override // android.widget.TextView
    public void setTypeface(Typeface typeface, int i) {
        Typeface typefaceA = (typeface == null || i <= 0) ? null : q7.a(getContext(), typeface, i);
        if (typefaceA != null) {
            typeface = typefaceA;
        }
        super.setTypeface(typeface, i);
    }

    public AppCompatTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.textViewStyle);
    }

    public AppCompatTextView(Context context, AttributeSet attributeSet, int i) {
        super(d3.b(context), attributeSet, i);
        b3.a(this, getContext());
        f2 f2Var = new f2(this);
        this.b = f2Var;
        f2Var.e(attributeSet, i);
        o2 o2Var = new o2(this);
        this.c = o2Var;
        o2Var.m(attributeSet, i);
        o2Var.b();
        this.d = new n2(this);
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesRelativeWithIntrinsicBounds(int i, int i2, int i3, int i4) {
        Context context = getContext();
        setCompoundDrawablesRelativeWithIntrinsicBounds(i != 0 ? u0.d(context, i) : null, i2 != 0 ? u0.d(context, i2) : null, i3 != 0 ? u0.d(context, i3) : null, i4 != 0 ? u0.d(context, i4) : null);
        o2 o2Var = this.c;
        if (o2Var != null) {
            o2Var.p();
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesWithIntrinsicBounds(int i, int i2, int i3, int i4) {
        Context context = getContext();
        setCompoundDrawablesWithIntrinsicBounds(i != 0 ? u0.d(context, i) : null, i2 != 0 ? u0.d(context, i2) : null, i3 != 0 ? u0.d(context, i3) : null, i4 != 0 ? u0.d(context, i4) : null);
        o2 o2Var = this.c;
        if (o2Var != null) {
            o2Var.p();
        }
    }
}
