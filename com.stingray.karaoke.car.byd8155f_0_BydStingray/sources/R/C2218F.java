package r;

import F1.AbstractC0260t;
import a.AbstractC0904a;
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
import android.util.TypedValue;
import android.view.ActionMode;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;
import android.view.textclassifier.TextClassifier;
import android.widget.TextView;
import g1.AbstractC1274a;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/* JADX INFO: renamed from: r.F, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C2218F extends TextView {
    public final i2.q l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final C2215C f21430m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public C2261t f21431n;
    public boolean o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public m2.b f21432p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public Future f21433q;

    public C2218F(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.textViewStyle);
    }

    private C2261t getEmojiTextViewHelper() {
        if (this.f21431n == null) {
            this.f21431n = new C2261t(this);
        }
        return this.f21431n;
    }

    @Override // android.widget.TextView, android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        i2.q qVar = this.l;
        if (qVar != null) {
            qVar.b();
        }
        C2215C c2215c = this.f21430m;
        if (c2215c != null) {
            c2215c.b();
        }
    }

    public final void g() {
        Future future = this.f21433q;
        if (future == null) {
            return;
        }
        try {
            this.f21433q = null;
            if (future.get() != null) {
                throw new ClassCastException();
            }
            throw null;
        } catch (InterruptedException | ExecutionException unused) {
        }
    }

    @Override // android.widget.TextView
    public int getAutoSizeMaxTextSize() {
        return super.getAutoSizeMaxTextSize();
    }

    @Override // android.widget.TextView
    public int getAutoSizeMinTextSize() {
        return super.getAutoSizeMinTextSize();
    }

    @Override // android.widget.TextView
    public int getAutoSizeStepGranularity() {
        return super.getAutoSizeStepGranularity();
    }

    @Override // android.widget.TextView
    public int[] getAutoSizeTextAvailableSizes() {
        return super.getAutoSizeTextAvailableSizes();
    }

    @Override // android.widget.TextView
    @SuppressLint({"WrongConstant"})
    public int getAutoSizeTextType() {
        return super.getAutoSizeTextType() == 1 ? 1 : 0;
    }

    @Override // android.widget.TextView
    public ActionMode.Callback getCustomSelectionActionModeCallback() {
        return super.getCustomSelectionActionModeCallback();
    }

    @Override // android.widget.TextView
    public int getFirstBaselineToTopHeight() {
        return getPaddingTop() - getPaint().getFontMetricsInt().top;
    }

    @Override // android.widget.TextView
    public int getLastBaselineToBottomHeight() {
        return getPaddingBottom() + getPaint().getFontMetricsInt().bottom;
    }

    public InterfaceC2216D getSuperCaller() {
        if (this.f21432p == null) {
            if (Build.VERSION.SDK_INT >= 34) {
                this.f21432p = new C2217E(this);
            } else {
                this.f21432p = new m2.b(this);
            }
        }
        return this.f21432p;
    }

    public ColorStateList getSupportBackgroundTintList() {
        i2.q qVar = this.l;
        if (qVar != null) {
            return qVar.d();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        i2.q qVar = this.l;
        if (qVar != null) {
            return qVar.e();
        }
        return null;
    }

    public ColorStateList getSupportCompoundDrawablesTintList() {
        c9.m mVar = this.f21430m.f21423h;
        if (mVar != null) {
            return (ColorStateList) mVar.c;
        }
        return null;
    }

    public PorterDuff.Mode getSupportCompoundDrawablesTintMode() {
        c9.m mVar = this.f21430m.f21423h;
        if (mVar != null) {
            return (PorterDuff.Mode) mVar.d;
        }
        return null;
    }

    @Override // android.widget.TextView
    public CharSequence getText() {
        g();
        return super.getText();
    }

    @Override // android.widget.TextView
    public TextClassifier getTextClassifier() {
        return super.getTextClassifier();
    }

    public D1.c getTextMetricsParamsCompat() {
        return new D1.c(getTextMetricsParams());
    }

    @Override // android.widget.TextView, android.view.View
    public final InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        InputConnection inputConnectionOnCreateInputConnection = super.onCreateInputConnection(editorInfo);
        this.f21430m.getClass();
        if (Build.VERSION.SDK_INT < 30 && inputConnectionOnCreateInputConnection != null) {
            H7.H.P(editorInfo, getText());
        }
        n7.z.t(inputConnectionOnCreateInputConnection, editorInfo, this);
        return inputConnectionOnCreateInputConnection;
    }

    @Override // android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        int i7 = Build.VERSION.SDK_INT;
        if (i7 < 30 || i7 >= 33 || !onCheckIsTextEditor()) {
            return;
        }
        ((InputMethodManager) getContext().getSystemService("input_method")).isActive(this);
    }

    @Override // android.widget.TextView, android.view.View
    public final void onLayout(boolean z6, int i7, int i10, int i11, int i12) {
        super.onLayout(z6, i7, i10, i11, i12);
        C2215C c2215c = this.f21430m;
        if (c2215c != null) {
            c2215c.getClass();
        }
    }

    @Override // android.widget.TextView, android.view.View
    public void onMeasure(int i7, int i10) {
        g();
        super.onMeasure(i7, i10);
    }

    @Override // android.widget.TextView
    public final void onTextChanged(CharSequence charSequence, int i7, int i10, int i11) {
        super.onTextChanged(charSequence, i7, i10, i11);
    }

    @Override // android.widget.TextView
    public void setAllCaps(boolean z6) {
        super.setAllCaps(z6);
        ((AbstractC0904a) getEmojiTextViewHelper().f21559b.l).Q(z6);
    }

    @Override // android.widget.TextView
    public final void setAutoSizeTextTypeUniformWithConfiguration(int i7, int i10, int i11, int i12) {
        super.setAutoSizeTextTypeUniformWithConfiguration(i7, i10, i11, i12);
    }

    @Override // android.widget.TextView
    public final void setAutoSizeTextTypeUniformWithPresetSizes(int[] iArr, int i7) {
        super.setAutoSizeTextTypeUniformWithPresetSizes(iArr, i7);
    }

    @Override // android.widget.TextView
    public void setAutoSizeTextTypeWithDefaults(int i7) {
        super.setAutoSizeTextTypeWithDefaults(i7);
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        i2.q qVar = this.l;
        if (qVar != null) {
            qVar.g();
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i7) {
        super.setBackgroundResource(i7);
        i2.q qVar = this.l;
        if (qVar != null) {
            qVar.h(i7);
        }
    }

    @Override // android.widget.TextView
    public final void setCompoundDrawables(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawables(drawable, drawable2, drawable3, drawable4);
        C2215C c2215c = this.f21430m;
        if (c2215c != null) {
            c2215c.b();
        }
    }

    @Override // android.widget.TextView
    public final void setCompoundDrawablesRelative(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesRelative(drawable, drawable2, drawable3, drawable4);
        C2215C c2215c = this.f21430m;
        if (c2215c != null) {
            c2215c.b();
        }
    }

    @Override // android.widget.TextView
    public final void setCompoundDrawablesRelativeWithIntrinsicBounds(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
        C2215C c2215c = this.f21430m;
        if (c2215c != null) {
            c2215c.b();
        }
    }

    @Override // android.widget.TextView
    public final void setCompoundDrawablesWithIntrinsicBounds(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
        C2215C c2215c = this.f21430m;
        if (c2215c != null) {
            c2215c.b();
        }
    }

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(callback);
    }

    public void setEmojiCompatEnabled(boolean z6) {
        ((AbstractC0904a) getEmojiTextViewHelper().f21559b.l).R(z6);
    }

    @Override // android.widget.TextView
    public void setFilters(InputFilter[] inputFilterArr) {
        super.setFilters(((AbstractC0904a) getEmojiTextViewHelper().f21559b.l).E(inputFilterArr));
    }

    @Override // android.widget.TextView
    public void setFirstBaselineToTopHeight(int i7) {
        getSuperCaller().T(i7);
    }

    @Override // android.widget.TextView
    public void setLastBaselineToBottomHeight(int i7) {
        getSuperCaller().u(i7);
    }

    @Override // android.widget.TextView
    public void setLineHeight(int i7) {
        AbstractC1274a.a0(this, i7);
    }

    public void setPrecomputedText(D1.d dVar) {
        throw null;
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        i2.q qVar = this.l;
        if (qVar != null) {
            qVar.j(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        i2.q qVar = this.l;
        if (qVar != null) {
            qVar.k(mode);
        }
    }

    public void setSupportCompoundDrawablesTintList(ColorStateList colorStateList) {
        C2215C c2215c = this.f21430m;
        c2215c.f(colorStateList);
        c2215c.b();
    }

    public void setSupportCompoundDrawablesTintMode(PorterDuff.Mode mode) {
        C2215C c2215c = this.f21430m;
        c2215c.g(mode);
        c2215c.b();
    }

    @Override // android.widget.TextView
    public void setTextAppearance(Context context, int i7) {
        super.setTextAppearance(context, i7);
        C2215C c2215c = this.f21430m;
        if (c2215c != null) {
            c2215c.e(context, i7);
        }
    }

    @Override // android.widget.TextView
    public void setTextClassifier(TextClassifier textClassifier) {
        super.setTextClassifier(textClassifier);
    }

    public void setTextFuture(Future<D1.d> future) {
        this.f21433q = future;
        if (future != null) {
            requestLayout();
        }
    }

    public void setTextMetricsParamsCompat(D1.c cVar) {
        TextDirectionHeuristic textDirectionHeuristic;
        TextDirectionHeuristic textDirectionHeuristic2 = cVar.f1135b;
        TextDirectionHeuristic textDirectionHeuristic3 = TextDirectionHeuristics.FIRSTSTRONG_RTL;
        int i7 = 1;
        if (textDirectionHeuristic2 != textDirectionHeuristic3 && textDirectionHeuristic2 != (textDirectionHeuristic = TextDirectionHeuristics.FIRSTSTRONG_LTR)) {
            if (textDirectionHeuristic2 == TextDirectionHeuristics.ANYRTL_LTR) {
                i7 = 2;
            } else if (textDirectionHeuristic2 == TextDirectionHeuristics.LTR) {
                i7 = 3;
            } else if (textDirectionHeuristic2 == TextDirectionHeuristics.RTL) {
                i7 = 4;
            } else if (textDirectionHeuristic2 == TextDirectionHeuristics.LOCALE) {
                i7 = 5;
            } else if (textDirectionHeuristic2 == textDirectionHeuristic) {
                i7 = 6;
            } else if (textDirectionHeuristic2 == textDirectionHeuristic3) {
                i7 = 7;
            }
        }
        setTextDirection(i7);
        getPaint().set(cVar.f1134a);
        setBreakStrategy(cVar.c);
        setHyphenationFrequency(cVar.d);
    }

    @Override // android.widget.TextView
    public final void setTypeface(Typeface typeface, int i7) {
        Typeface typefaceCreate;
        if (this.o) {
            return;
        }
        if (typeface == null || i7 <= 0) {
            typefaceCreate = null;
        } else {
            Context context = getContext();
            m6.b bVar = y1.d.f24116a;
            if (context == null) {
                throw new IllegalArgumentException("Context cannot be null");
            }
            typefaceCreate = Typeface.create(typeface, i7);
        }
        this.o = true;
        if (typefaceCreate != null) {
            typeface = typefaceCreate;
        }
        try {
            super.setTypeface(typeface, i7);
        } finally {
            this.o = false;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C2218F(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        r0.a(context);
        this.o = false;
        this.f21432p = null;
        q0.a(this, getContext());
        i2.q qVar = new i2.q(this);
        this.l = qVar;
        qVar.f(attributeSet, i7);
        C2215C c2215c = new C2215C(this);
        this.f21430m = c2215c;
        c2215c.d(attributeSet, i7);
        c2215c.b();
        getEmojiTextViewHelper().a(attributeSet, i7);
    }

    @Override // android.widget.TextView
    public final void setLineHeight(int i7, float f10) {
        int i10 = Build.VERSION.SDK_INT;
        if (i10 >= 34) {
            getSuperCaller().i0(i7, f10);
        } else if (i10 >= 34) {
            AbstractC0260t.h(this, i7, f10);
        } else {
            AbstractC1274a.a0(this, Math.round(TypedValue.applyDimension(i7, f10, getResources().getDisplayMetrics())));
        }
    }

    @Override // android.widget.TextView
    public final void setCompoundDrawablesRelativeWithIntrinsicBounds(int i7, int i10, int i11, int i12) {
        Context context = getContext();
        setCompoundDrawablesRelativeWithIntrinsicBounds(i7 != 0 ? h6.f.k(context, i7) : null, i10 != 0 ? h6.f.k(context, i10) : null, i11 != 0 ? h6.f.k(context, i11) : null, i12 != 0 ? h6.f.k(context, i12) : null);
        C2215C c2215c = this.f21430m;
        if (c2215c != null) {
            c2215c.b();
        }
    }

    @Override // android.widget.TextView
    public final void setCompoundDrawablesWithIntrinsicBounds(int i7, int i10, int i11, int i12) {
        Context context = getContext();
        setCompoundDrawablesWithIntrinsicBounds(i7 != 0 ? h6.f.k(context, i7) : null, i10 != 0 ? h6.f.k(context, i10) : null, i11 != 0 ? h6.f.k(context, i11) : null, i12 != 0 ? h6.f.k(context, i12) : null);
        C2215C c2215c = this.f21430m;
        if (c2215c != null) {
            c2215c.b();
        }
    }
}
