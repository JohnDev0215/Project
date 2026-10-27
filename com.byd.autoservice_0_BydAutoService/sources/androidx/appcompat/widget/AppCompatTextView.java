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
import j.a;
import j0.c;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import l0.s;
import n0.b;
import n0.i;
import n0.k;
import o.b0;
import o.d;
import o.g;
import o.l;
import o.m;
import o.z;

/* JADX INFO: loaded from: classes.dex */
public class AppCompatTextView extends TextView implements s, k, b {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final d f493b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final m f494c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final l f495d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Future<c> f496e;

    public AppCompatTextView(Context context) {
        this(context, null);
    }

    public final void c() {
        Future<c> future = this.f496e;
        if (future != null) {
            try {
                this.f496e = null;
                i.p(this, future.get());
            } catch (InterruptedException | ExecutionException unused) {
            }
        }
    }

    @Override // android.widget.TextView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        d dVar = this.f493b;
        if (dVar != null) {
            dVar.b();
        }
        m mVar = this.f494c;
        if (mVar != null) {
            mVar.b();
        }
    }

    @Override // android.widget.TextView
    public int getAutoSizeMaxTextSize() {
        if (b.f4070a) {
            return super.getAutoSizeMaxTextSize();
        }
        m mVar = this.f494c;
        if (mVar != null) {
            return mVar.e();
        }
        return -1;
    }

    @Override // android.widget.TextView
    public int getAutoSizeMinTextSize() {
        if (b.f4070a) {
            return super.getAutoSizeMinTextSize();
        }
        m mVar = this.f494c;
        if (mVar != null) {
            return mVar.f();
        }
        return -1;
    }

    @Override // android.widget.TextView
    public int getAutoSizeStepGranularity() {
        if (b.f4070a) {
            return super.getAutoSizeStepGranularity();
        }
        m mVar = this.f494c;
        if (mVar != null) {
            return mVar.g();
        }
        return -1;
    }

    @Override // android.widget.TextView
    public int[] getAutoSizeTextAvailableSizes() {
        if (b.f4070a) {
            return super.getAutoSizeTextAvailableSizes();
        }
        m mVar = this.f494c;
        return mVar != null ? mVar.h() : new int[0];
    }

    @Override // android.widget.TextView
    @SuppressLint({"WrongConstant"})
    public int getAutoSizeTextType() {
        if (b.f4070a) {
            return super.getAutoSizeTextType() == 1 ? 1 : 0;
        }
        m mVar = this.f494c;
        if (mVar != null) {
            return mVar.i();
        }
        return 0;
    }

    @Override // android.widget.TextView
    public int getFirstBaselineToTopHeight() {
        return i.b(this);
    }

    @Override // android.widget.TextView
    public int getLastBaselineToBottomHeight() {
        return i.c(this);
    }

    @Override // l0.s
    public ColorStateList getSupportBackgroundTintList() {
        d dVar = this.f493b;
        if (dVar != null) {
            return dVar.c();
        }
        return null;
    }

    @Override // l0.s
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        d dVar = this.f493b;
        if (dVar != null) {
            return dVar.d();
        }
        return null;
    }

    public ColorStateList getSupportCompoundDrawablesTintList() {
        return this.f494c.j();
    }

    public PorterDuff.Mode getSupportCompoundDrawablesTintMode() {
        return this.f494c.k();
    }

    @Override // android.widget.TextView
    public CharSequence getText() {
        c();
        return super.getText();
    }

    @Override // android.widget.TextView
    public TextClassifier getTextClassifier() {
        l lVar;
        return (Build.VERSION.SDK_INT >= 28 || (lVar = this.f495d) == null) ? super.getTextClassifier() : lVar.a();
    }

    public c.a getTextMetricsParamsCompat() {
        return i.g(this);
    }

    @Override // android.widget.TextView, android.view.View
    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        return g.a(super.onCreateInputConnection(editorInfo), editorInfo, this);
    }

    @Override // android.widget.TextView, android.view.View
    public void onLayout(boolean z3, int i3, int i4, int i5, int i6) {
        super.onLayout(z3, i3, i4, i5, i6);
        m mVar = this.f494c;
        if (mVar != null) {
            mVar.o(z3, i3, i4, i5, i6);
        }
    }

    @Override // android.widget.TextView, android.view.View
    public void onMeasure(int i3, int i4) {
        c();
        super.onMeasure(i3, i4);
    }

    @Override // android.widget.TextView
    public void onTextChanged(CharSequence charSequence, int i3, int i4, int i5) {
        super.onTextChanged(charSequence, i3, i4, i5);
        m mVar = this.f494c;
        if (mVar == null || b.f4070a || !mVar.l()) {
            return;
        }
        this.f494c.c();
    }

    @Override // android.widget.TextView
    public void setAutoSizeTextTypeUniformWithConfiguration(int i3, int i4, int i5, int i6) throws IllegalArgumentException {
        if (b.f4070a) {
            super.setAutoSizeTextTypeUniformWithConfiguration(i3, i4, i5, i6);
            return;
        }
        m mVar = this.f494c;
        if (mVar != null) {
            mVar.s(i3, i4, i5, i6);
        }
    }

    @Override // android.widget.TextView
    public void setAutoSizeTextTypeUniformWithPresetSizes(int[] iArr, int i3) throws IllegalArgumentException {
        if (b.f4070a) {
            super.setAutoSizeTextTypeUniformWithPresetSizes(iArr, i3);
            return;
        }
        m mVar = this.f494c;
        if (mVar != null) {
            mVar.t(iArr, i3);
        }
    }

    @Override // android.widget.TextView
    public void setAutoSizeTextTypeWithDefaults(int i3) {
        if (b.f4070a) {
            super.setAutoSizeTextTypeWithDefaults(i3);
            return;
        }
        m mVar = this.f494c;
        if (mVar != null) {
            mVar.u(i3);
        }
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        d dVar = this.f493b;
        if (dVar != null) {
            dVar.f(drawable);
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i3) {
        super.setBackgroundResource(i3);
        d dVar = this.f493b;
        if (dVar != null) {
            dVar.g(i3);
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawables(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawables(drawable, drawable2, drawable3, drawable4);
        m mVar = this.f494c;
        if (mVar != null) {
            mVar.p();
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesRelative(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesRelative(drawable, drawable2, drawable3, drawable4);
        m mVar = this.f494c;
        if (mVar != null) {
            mVar.p();
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesRelativeWithIntrinsicBounds(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
        m mVar = this.f494c;
        if (mVar != null) {
            mVar.p();
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesWithIntrinsicBounds(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
        m mVar = this.f494c;
        if (mVar != null) {
            mVar.p();
        }
    }

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(i.s(this, callback));
    }

    @Override // android.widget.TextView
    public void setFirstBaselineToTopHeight(int i3) {
        if (Build.VERSION.SDK_INT >= 28) {
            super.setFirstBaselineToTopHeight(i3);
        } else {
            i.m(this, i3);
        }
    }

    @Override // android.widget.TextView
    public void setLastBaselineToBottomHeight(int i3) {
        if (Build.VERSION.SDK_INT >= 28) {
            super.setLastBaselineToBottomHeight(i3);
        } else {
            i.n(this, i3);
        }
    }

    @Override // android.widget.TextView
    public void setLineHeight(int i3) {
        i.o(this, i3);
    }

    public void setPrecomputedText(c cVar) {
        i.p(this, cVar);
    }

    @Override // l0.s
    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        d dVar = this.f493b;
        if (dVar != null) {
            dVar.i(colorStateList);
        }
    }

    @Override // l0.s
    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        d dVar = this.f493b;
        if (dVar != null) {
            dVar.j(mode);
        }
    }

    @Override // n0.k
    public void setSupportCompoundDrawablesTintList(ColorStateList colorStateList) {
        this.f494c.v(colorStateList);
        this.f494c.b();
    }

    @Override // n0.k
    public void setSupportCompoundDrawablesTintMode(PorterDuff.Mode mode) {
        this.f494c.w(mode);
        this.f494c.b();
    }

    @Override // android.widget.TextView
    public void setTextAppearance(Context context, int i3) {
        super.setTextAppearance(context, i3);
        m mVar = this.f494c;
        if (mVar != null) {
            mVar.q(context, i3);
        }
    }

    @Override // android.widget.TextView
    public void setTextClassifier(TextClassifier textClassifier) {
        l lVar;
        if (Build.VERSION.SDK_INT >= 28 || (lVar = this.f495d) == null) {
            super.setTextClassifier(textClassifier);
        } else {
            lVar.b(textClassifier);
        }
    }

    public void setTextFuture(Future<c> future) {
        this.f496e = future;
        if (future != null) {
            requestLayout();
        }
    }

    public void setTextMetricsParamsCompat(c.a aVar) {
        i.r(this, aVar);
    }

    @Override // android.widget.TextView
    public void setTextSize(int i3, float f3) {
        if (b.f4070a) {
            super.setTextSize(i3, f3);
            return;
        }
        m mVar = this.f494c;
        if (mVar != null) {
            mVar.z(i3, f3);
        }
    }

    @Override // android.widget.TextView
    public void setTypeface(Typeface typeface, int i3) {
        Typeface typefaceA = (typeface == null || i3 <= 0) ? null : d0.d.a(getContext(), typeface, i3);
        if (typefaceA != null) {
            typeface = typefaceA;
        }
        super.setTypeface(typeface, i3);
    }

    public AppCompatTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.textViewStyle);
    }

    public AppCompatTextView(Context context, AttributeSet attributeSet, int i3) {
        super(b0.b(context), attributeSet, i3);
        z.a(this, getContext());
        d dVar = new d(this);
        this.f493b = dVar;
        dVar.e(attributeSet, i3);
        m mVar = new m(this);
        this.f494c = mVar;
        mVar.m(attributeSet, i3);
        mVar.b();
        this.f495d = new l(this);
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesRelativeWithIntrinsicBounds(int i3, int i4, int i5, int i6) {
        Context context = getContext();
        setCompoundDrawablesRelativeWithIntrinsicBounds(i3 != 0 ? a.d(context, i3) : null, i4 != 0 ? a.d(context, i4) : null, i5 != 0 ? a.d(context, i5) : null, i6 != 0 ? a.d(context, i6) : null);
        m mVar = this.f494c;
        if (mVar != null) {
            mVar.p();
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesWithIntrinsicBounds(int i3, int i4, int i5, int i6) {
        Context context = getContext();
        setCompoundDrawablesWithIntrinsicBounds(i3 != 0 ? a.d(context, i3) : null, i4 != 0 ? a.d(context, i4) : null, i5 != 0 ? a.d(context, i5) : null, i6 != 0 ? a.d(context, i6) : null);
        m mVar = this.f494c;
        if (mVar != null) {
            mVar.p();
        }
    }
}
