package p;

import android.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.InputFilter;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.textclassifier.TextClassifier;
import android.widget.TextView;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/* JADX INFO: loaded from: classes.dex */
public class fa extends TextView {
    private final v7 c;
    private final ca e;
    private final v9 k;
    private y8 l;
    public boolean m;
    public oh1 n;
    public Future o;

    public fa(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.textViewStyle);
    }

    private y8 getEmojiTextViewHelper() {
        if (this.l == null) {
            this.l = new y8(this);
        }
        return this.l;
    }

    @Override // android.widget.TextView, android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        v7 v7Var = this.c;
        if (v7Var != null) {
            v7Var.a();
        }
        ca caVar = this.e;
        if (caVar != null) {
            caVar.b();
        }
    }

    @Override // android.widget.TextView
    public int getAutoSizeMaxTextSize() {
        if (ev2.b) {
            return super.getAutoSizeMaxTextSize();
        }
        ca caVar = this.e;
        if (caVar != null) {
            return caVar.e();
        }
        return -1;
    }

    @Override // android.widget.TextView
    public int getAutoSizeMinTextSize() {
        if (ev2.b) {
            return super.getAutoSizeMinTextSize();
        }
        ca caVar = this.e;
        if (caVar != null) {
            return caVar.f();
        }
        return -1;
    }

    @Override // android.widget.TextView
    public int getAutoSizeStepGranularity() {
        if (ev2.b) {
            return super.getAutoSizeStepGranularity();
        }
        ca caVar = this.e;
        if (caVar != null) {
            return caVar.g();
        }
        return -1;
    }

    @Override // android.widget.TextView
    public int[] getAutoSizeTextAvailableSizes() {
        if (ev2.b) {
            return super.getAutoSizeTextAvailableSizes();
        }
        ca caVar = this.e;
        return caVar != null ? caVar.h() : new int[0];
    }

    @Override // android.widget.TextView
    @SuppressLint({"WrongConstant"})
    public int getAutoSizeTextType() {
        if (ev2.b) {
            return super.getAutoSizeTextType() == 1 ? 1 : 0;
        }
        ca caVar = this.e;
        if (caVar != null) {
            return caVar.i();
        }
        return 0;
    }

    @Override // android.widget.TextView
    public ActionMode.Callback getCustomSelectionActionModeCallback() {
        ActionMode.Callback customSelectionActionModeCallback = super.getCustomSelectionActionModeCallback();
        return customSelectionActionModeCallback instanceof fk2 ? ((fk2) customSelectionActionModeCallback).a : customSelectionActionModeCallback;
    }

    @Override // android.widget.TextView
    public int getFirstBaselineToTopHeight() {
        return getPaddingTop() - getPaint().getFontMetricsInt().top;
    }

    @Override // android.widget.TextView
    public int getLastBaselineToBottomHeight() {
        return getPaddingBottom() + getPaint().getFontMetricsInt().bottom;
    }

    public da getSuperCaller() {
        if (this.n == null) {
            if (Build.VERSION.SDK_INT >= 28) {
                this.n = new ea(this);
            } else {
                this.n = new oh1(3, this);
            }
        }
        return this.n;
    }

    public ColorStateList getSupportBackgroundTintList() {
        v7 v7Var = this.c;
        if (v7Var != null) {
            return v7Var.b();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        v7 v7Var = this.c;
        if (v7Var != null) {
            return v7Var.c();
        }
        return null;
    }

    public ColorStateList getSupportCompoundDrawablesTintList() {
        return this.e.j();
    }

    public PorterDuff.Mode getSupportCompoundDrawablesTintMode() {
        return this.e.k();
    }

    @Override // android.widget.TextView
    public CharSequence getText() {
        o();
        return super.getText();
    }

    @Override // android.widget.TextView
    public TextClassifier getTextClassifier() {
        v9 v9Var;
        if (Build.VERSION.SDK_INT >= 28 || (v9Var = this.k) == null) {
            return super.getTextClassifier();
        }
        TextClassifier textClassifier = v9Var.b;
        return textClassifier == null ? u9.a(v9Var.a) : textClassifier;
    }

    public sw1 getTextMetricsParamsCompat() {
        return f60.v(this);
    }

    public final void o() {
        Future future = this.o;
        if (future == null) {
            return;
        }
        try {
            this.o = null;
            u4.z(future.get());
            if (Build.VERSION.SDK_INT >= 29) {
                throw null;
            }
            f60.v(this);
            throw null;
        } catch (InterruptedException | ExecutionException unused) {
        }
    }

    @Override // android.widget.TextView, android.view.View
    public final InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        InputConnection inputConnectionOnCreateInputConnection = super.onCreateInputConnection(editorInfo);
        this.e.getClass();
        ca.o(this, inputConnectionOnCreateInputConnection, editorInfo);
        ln1.t(this, editorInfo, inputConnectionOnCreateInputConnection);
        return inputConnectionOnCreateInputConnection;
    }

    @Override // android.widget.TextView, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        ca caVar = this.e;
        if (caVar == null || ev2.b) {
            return;
        }
        caVar.c();
    }

    @Override // android.widget.TextView, android.view.View
    public void onMeasure(int i, int i2) {
        o();
        super.onMeasure(i, i2);
    }

    @Override // android.widget.TextView
    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        super.onTextChanged(charSequence, i, i2, i3);
        ca caVar = this.e;
        if (caVar == null || ev2.b || !caVar.l()) {
            return;
        }
        this.e.c();
    }

    @Override // android.widget.TextView
    public void setAllCaps(boolean z) {
        super.setAllCaps(z);
        getEmojiTextViewHelper().c(z);
    }

    @Override // android.widget.TextView
    public final void setAutoSizeTextTypeUniformWithConfiguration(int i, int i2, int i3, int i4) {
        if (ev2.b) {
            super.setAutoSizeTextTypeUniformWithConfiguration(i, i2, i3, i4);
            return;
        }
        ca caVar = this.e;
        if (caVar != null) {
            caVar.p(i, i2, i3, i4);
        }
    }

    @Override // android.widget.TextView
    public final void setAutoSizeTextTypeUniformWithPresetSizes(int[] iArr, int i) {
        if (ev2.b) {
            super.setAutoSizeTextTypeUniformWithPresetSizes(iArr, i);
            return;
        }
        ca caVar = this.e;
        if (caVar != null) {
            caVar.q(iArr, i);
        }
    }

    @Override // android.widget.TextView
    public void setAutoSizeTextTypeWithDefaults(int i) {
        if (ev2.b) {
            super.setAutoSizeTextTypeWithDefaults(i);
            return;
        }
        ca caVar = this.e;
        if (caVar != null) {
            caVar.r(i);
        }
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        v7 v7Var = this.c;
        if (v7Var != null) {
            v7Var.e();
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        v7 v7Var = this.c;
        if (v7Var != null) {
            v7Var.f(i);
        }
    }

    @Override // android.widget.TextView
    public final void setCompoundDrawables(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawables(drawable, drawable2, drawable3, drawable4);
        ca caVar = this.e;
        if (caVar != null) {
            caVar.b();
        }
    }

    @Override // android.widget.TextView
    public final void setCompoundDrawablesRelative(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesRelative(drawable, drawable2, drawable3, drawable4);
        ca caVar = this.e;
        if (caVar != null) {
            caVar.b();
        }
    }

    @Override // android.widget.TextView
    public final void setCompoundDrawablesRelativeWithIntrinsicBounds(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
        ca caVar = this.e;
        if (caVar != null) {
            caVar.b();
        }
    }

    @Override // android.widget.TextView
    public final void setCompoundDrawablesWithIntrinsicBounds(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
        ca caVar = this.e;
        if (caVar != null) {
            caVar.b();
        }
    }

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(f60.T(callback, this));
    }

    public void setEmojiCompatEnabled(boolean z) {
        getEmojiTextViewHelper().d(z);
    }

    @Override // android.widget.TextView
    public void setFilters(InputFilter[] inputFilterArr) {
        super.setFilters(getEmojiTextViewHelper().a(inputFilterArr));
    }

    @Override // android.widget.TextView
    public void setFirstBaselineToTopHeight(int i) {
        if (Build.VERSION.SDK_INT >= 28) {
            getSuperCaller().g(i);
        } else {
            f60.G(this, i);
        }
    }

    @Override // android.widget.TextView
    public void setLastBaselineToBottomHeight(int i) {
        if (Build.VERSION.SDK_INT >= 28) {
            getSuperCaller().a(i);
        } else {
            f60.H(this, i);
        }
    }

    @Override // android.widget.TextView
    public void setLineHeight(int i) {
        p02.d(i);
        if (i != getPaint().getFontMetricsInt(null)) {
            setLineSpacing(i - r0, 1.0f);
        }
    }

    public void setPrecomputedText(tw1 tw1Var) {
        if (Build.VERSION.SDK_INT >= 29) {
            throw null;
        }
        f60.v(this);
        throw null;
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        v7 v7Var = this.c;
        if (v7Var != null) {
            v7Var.h(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        v7 v7Var = this.c;
        if (v7Var != null) {
            v7Var.i(mode);
        }
    }

    public void setSupportCompoundDrawablesTintList(ColorStateList colorStateList) {
        this.e.s(colorStateList);
        this.e.b();
    }

    public void setSupportCompoundDrawablesTintMode(PorterDuff.Mode mode) {
        this.e.t(mode);
        this.e.b();
    }

    @Override // android.widget.TextView
    public void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        ca caVar = this.e;
        if (caVar != null) {
            caVar.n(context, i);
        }
    }

    @Override // android.widget.TextView
    public void setTextClassifier(TextClassifier textClassifier) {
        v9 v9Var;
        if (Build.VERSION.SDK_INT >= 28 || (v9Var = this.k) == null) {
            super.setTextClassifier(textClassifier);
        } else {
            v9Var.b = textClassifier;
        }
    }

    public void setTextFuture(Future<tw1> future) {
        this.o = future;
        if (future != null) {
            requestLayout();
        }
    }

    public void setTextMetricsParamsCompat(sw1 sw1Var) {
        TextDirectionHeuristic textDirectionHeuristic;
        TextDirectionHeuristic textDirectionHeuristic2 = sw1Var.b;
        TextDirectionHeuristic textDirectionHeuristic3 = TextDirectionHeuristics.FIRSTSTRONG_RTL;
        int i = 1;
        if (textDirectionHeuristic2 != textDirectionHeuristic3 && textDirectionHeuristic2 != (textDirectionHeuristic = TextDirectionHeuristics.FIRSTSTRONG_LTR)) {
            if (textDirectionHeuristic2 == TextDirectionHeuristics.ANYRTL_LTR) {
                i = 2;
            } else if (textDirectionHeuristic2 == TextDirectionHeuristics.LTR) {
                i = 3;
            } else if (textDirectionHeuristic2 == TextDirectionHeuristics.RTL) {
                i = 4;
            } else if (textDirectionHeuristic2 == TextDirectionHeuristics.LOCALE) {
                i = 5;
            } else if (textDirectionHeuristic2 == textDirectionHeuristic) {
                i = 6;
            } else if (textDirectionHeuristic2 == textDirectionHeuristic3) {
                i = 7;
            }
        }
        bk2.h(this, i);
        getPaint().set(sw1Var.a);
        ck2.e(this, sw1Var.c);
        ck2.h(this, sw1Var.d);
    }

    @Override // android.widget.TextView
    public final void setTextSize(int i, float f) {
        if (ev2.b) {
            super.setTextSize(i, f);
            return;
        }
        ca caVar = this.e;
        if (caVar != null) {
            caVar.u(i, f);
        }
    }

    @Override // android.widget.TextView
    public final void setTypeface(Typeface typeface, int i) {
        Typeface typefaceCreate;
        if (this.m) {
            return;
        }
        if (typeface == null || i <= 0) {
            typefaceCreate = null;
        } else {
            Context context = getContext();
            hk0 hk0Var = ro2.a;
            if (context == null) {
                throw new IllegalArgumentException("Context cannot be null");
            }
            typefaceCreate = Typeface.create(typeface, i);
        }
        this.m = true;
        if (typefaceCreate != null) {
            typeface = typefaceCreate;
        }
        try {
            super.setTypeface(typeface, i);
        } finally {
            this.m = false;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public fa(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        wl2.a(context);
        this.m = false;
        this.n = null;
        ik2.a(this, getContext());
        v7 v7Var = new v7(this);
        this.c = v7Var;
        v7Var.d(attributeSet, i);
        ca caVar = new ca(this);
        this.e = caVar;
        caVar.m(attributeSet, i);
        caVar.b();
        v9 v9Var = new v9();
        v9Var.a = this;
        this.k = v9Var;
        getEmojiTextViewHelper().b(attributeSet, i);
    }

    @Override // android.widget.TextView
    public final void setCompoundDrawablesRelativeWithIntrinsicBounds(int i, int i2, int i3, int i4) {
        Context context = getContext();
        setCompoundDrawablesRelativeWithIntrinsicBounds(i != 0 ? l13.f(context, i) : null, i2 != 0 ? l13.f(context, i2) : null, i3 != 0 ? l13.f(context, i3) : null, i4 != 0 ? l13.f(context, i4) : null);
        ca caVar = this.e;
        if (caVar != null) {
            caVar.b();
        }
    }

    @Override // android.widget.TextView
    public final void setCompoundDrawablesWithIntrinsicBounds(int i, int i2, int i3, int i4) {
        Context context = getContext();
        setCompoundDrawablesWithIntrinsicBounds(i != 0 ? l13.f(context, i) : null, i2 != 0 ? l13.f(context, i2) : null, i3 != 0 ? l13.f(context, i3) : null, i4 != 0 ? l13.f(context, i4) : null);
        ca caVar = this.e;
        if (caVar != null) {
            caVar.b();
        }
    }
}
