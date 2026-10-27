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
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import m.a;
import m0.c;
import o0.s;
import r.b0;
import r.d;
import r.g;
import r.l;
import r.m;
import r.z;
import r0.b;
import r0.i;
import r0.k;

/* JADX INFO: loaded from: classes.dex */
public class AppCompatTextView extends TextView implements s, k, b {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final d f594d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final m f595e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final l f596f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Future<c> f597g;

    public AppCompatTextView(Context context) {
        this(context, null);
    }

    public final void c() {
        Future<c> future = this.f597g;
        if (future != null) {
            try {
                this.f597g = null;
                i.p(this, future.get());
            } catch (InterruptedException | ExecutionException unused) {
            }
        }
    }

    @Override // android.widget.TextView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        d dVar = this.f594d;
        if (dVar != null) {
            dVar.b();
        }
        m mVar = this.f595e;
        if (mVar != null) {
            mVar.b();
        }
    }

    @Override // android.widget.TextView
    public int getAutoSizeMaxTextSize() {
        if (b.f7723a) {
            return super.getAutoSizeMaxTextSize();
        }
        m mVar = this.f595e;
        if (mVar != null) {
            return mVar.e();
        }
        return -1;
    }

    @Override // android.widget.TextView
    public int getAutoSizeMinTextSize() {
        if (b.f7723a) {
            return super.getAutoSizeMinTextSize();
        }
        m mVar = this.f595e;
        if (mVar != null) {
            return mVar.f();
        }
        return -1;
    }

    @Override // android.widget.TextView
    public int getAutoSizeStepGranularity() {
        if (b.f7723a) {
            return super.getAutoSizeStepGranularity();
        }
        m mVar = this.f595e;
        if (mVar != null) {
            return mVar.g();
        }
        return -1;
    }

    @Override // android.widget.TextView
    public int[] getAutoSizeTextAvailableSizes() {
        if (b.f7723a) {
            return super.getAutoSizeTextAvailableSizes();
        }
        m mVar = this.f595e;
        return mVar != null ? mVar.h() : new int[0];
    }

    @Override // android.widget.TextView
    @SuppressLint({"WrongConstant"})
    public int getAutoSizeTextType() {
        if (b.f7723a) {
            return super.getAutoSizeTextType() == 1 ? 1 : 0;
        }
        m mVar = this.f595e;
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

    @Override // o0.s
    public ColorStateList getSupportBackgroundTintList() {
        d dVar = this.f594d;
        if (dVar != null) {
            return dVar.c();
        }
        return null;
    }

    @Override // o0.s
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        d dVar = this.f594d;
        if (dVar != null) {
            return dVar.d();
        }
        return null;
    }

    public ColorStateList getSupportCompoundDrawablesTintList() {
        return this.f595e.j();
    }

    public PorterDuff.Mode getSupportCompoundDrawablesTintMode() {
        return this.f595e.k();
    }

    @Override // android.widget.TextView
    public CharSequence getText() {
        c();
        return super.getText();
    }

    @Override // android.widget.TextView
    public TextClassifier getTextClassifier() {
        l lVar;
        return (Build.VERSION.SDK_INT >= 28 || (lVar = this.f596f) == null) ? super.getTextClassifier() : lVar.a();
    }

    public c.a getTextMetricsParamsCompat() {
        return i.g(this);
    }

    @Override // android.widget.TextView, android.view.View
    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        return g.a(super.onCreateInputConnection(editorInfo), editorInfo, this);
    }

    @Override // android.widget.TextView, android.view.View
    public void onLayout(boolean z7, int i8, int i9, int i10, int i11) {
        super.onLayout(z7, i8, i9, i10, i11);
        m mVar = this.f595e;
        if (mVar != null) {
            mVar.o(z7, i8, i9, i10, i11);
        }
    }

    @Override // android.widget.TextView, android.view.View
    public void onMeasure(int i8, int i9) {
        c();
        super.onMeasure(i8, i9);
    }

    @Override // android.widget.TextView
    public void onTextChanged(CharSequence charSequence, int i8, int i9, int i10) {
        super.onTextChanged(charSequence, i8, i9, i10);
        m mVar = this.f595e;
        if (mVar == null || b.f7723a || !mVar.l()) {
            return;
        }
        this.f595e.c();
    }

    @Override // android.widget.TextView
    public void setAutoSizeTextTypeUniformWithConfiguration(int i8, int i9, int i10, int i11) throws IllegalArgumentException {
        if (b.f7723a) {
            super.setAutoSizeTextTypeUniformWithConfiguration(i8, i9, i10, i11);
            return;
        }
        m mVar = this.f595e;
        if (mVar != null) {
            mVar.s(i8, i9, i10, i11);
        }
    }

    @Override // android.widget.TextView
    public void setAutoSizeTextTypeUniformWithPresetSizes(int[] iArr, int i8) throws IllegalArgumentException {
        if (b.f7723a) {
            super.setAutoSizeTextTypeUniformWithPresetSizes(iArr, i8);
            return;
        }
        m mVar = this.f595e;
        if (mVar != null) {
            mVar.t(iArr, i8);
        }
    }

    @Override // android.widget.TextView
    public void setAutoSizeTextTypeWithDefaults(int i8) {
        if (b.f7723a) {
            super.setAutoSizeTextTypeWithDefaults(i8);
            return;
        }
        m mVar = this.f595e;
        if (mVar != null) {
            mVar.u(i8);
        }
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        d dVar = this.f594d;
        if (dVar != null) {
            dVar.f(drawable);
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i8) {
        super.setBackgroundResource(i8);
        d dVar = this.f594d;
        if (dVar != null) {
            dVar.g(i8);
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawables(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawables(drawable, drawable2, drawable3, drawable4);
        m mVar = this.f595e;
        if (mVar != null) {
            mVar.p();
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesRelative(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesRelative(drawable, drawable2, drawable3, drawable4);
        m mVar = this.f595e;
        if (mVar != null) {
            mVar.p();
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesRelativeWithIntrinsicBounds(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
        m mVar = this.f595e;
        if (mVar != null) {
            mVar.p();
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesWithIntrinsicBounds(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
        m mVar = this.f595e;
        if (mVar != null) {
            mVar.p();
        }
    }

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(i.s(this, callback));
    }

    @Override // android.widget.TextView
    public void setFirstBaselineToTopHeight(int i8) {
        if (Build.VERSION.SDK_INT >= 28) {
            super.setFirstBaselineToTopHeight(i8);
        } else {
            i.m(this, i8);
        }
    }

    @Override // android.widget.TextView
    public void setLastBaselineToBottomHeight(int i8) {
        if (Build.VERSION.SDK_INT >= 28) {
            super.setLastBaselineToBottomHeight(i8);
        } else {
            i.n(this, i8);
        }
    }

    @Override // android.widget.TextView
    public void setLineHeight(int i8) {
        i.o(this, i8);
    }

    public void setPrecomputedText(c cVar) {
        i.p(this, cVar);
    }

    @Override // o0.s
    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        d dVar = this.f594d;
        if (dVar != null) {
            dVar.i(colorStateList);
        }
    }

    @Override // o0.s
    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        d dVar = this.f594d;
        if (dVar != null) {
            dVar.j(mode);
        }
    }

    @Override // r0.k
    public void setSupportCompoundDrawablesTintList(ColorStateList colorStateList) {
        this.f595e.v(colorStateList);
        this.f595e.b();
    }

    @Override // r0.k
    public void setSupportCompoundDrawablesTintMode(PorterDuff.Mode mode) {
        this.f595e.w(mode);
        this.f595e.b();
    }

    @Override // android.widget.TextView
    public void setTextAppearance(Context context, int i8) {
        super.setTextAppearance(context, i8);
        m mVar = this.f595e;
        if (mVar != null) {
            mVar.q(context, i8);
        }
    }

    @Override // android.widget.TextView
    public void setTextClassifier(TextClassifier textClassifier) {
        l lVar;
        if (Build.VERSION.SDK_INT >= 28 || (lVar = this.f596f) == null) {
            super.setTextClassifier(textClassifier);
        } else {
            lVar.b(textClassifier);
        }
    }

    public void setTextFuture(Future<c> future) {
        this.f597g = future;
        if (future != null) {
            requestLayout();
        }
    }

    public void setTextMetricsParamsCompat(c.a aVar) {
        i.r(this, aVar);
    }

    @Override // android.widget.TextView
    public void setTextSize(int i8, float f8) {
        if (b.f7723a) {
            super.setTextSize(i8, f8);
            return;
        }
        m mVar = this.f595e;
        if (mVar != null) {
            mVar.z(i8, f8);
        }
    }

    @Override // android.widget.TextView
    public void setTypeface(Typeface typeface, int i8) {
        Typeface typefaceA = (typeface == null || i8 <= 0) ? null : g0.d.a(getContext(), typeface, i8);
        if (typefaceA != null) {
            typeface = typefaceA;
        }
        super.setTypeface(typeface, i8);
    }

    public AppCompatTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.textViewStyle);
    }

    public AppCompatTextView(Context context, AttributeSet attributeSet, int i8) {
        super(b0.b(context), attributeSet, i8);
        z.a(this, getContext());
        d dVar = new d(this);
        this.f594d = dVar;
        dVar.e(attributeSet, i8);
        m mVar = new m(this);
        this.f595e = mVar;
        mVar.m(attributeSet, i8);
        mVar.b();
        this.f596f = new l(this);
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesRelativeWithIntrinsicBounds(int i8, int i9, int i10, int i11) {
        Context context = getContext();
        setCompoundDrawablesRelativeWithIntrinsicBounds(i8 != 0 ? a.d(context, i8) : null, i9 != 0 ? a.d(context, i9) : null, i10 != 0 ? a.d(context, i10) : null, i11 != 0 ? a.d(context, i11) : null);
        m mVar = this.f595e;
        if (mVar != null) {
            mVar.p();
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesWithIntrinsicBounds(int i8, int i9, int i10, int i11) {
        Context context = getContext();
        setCompoundDrawablesWithIntrinsicBounds(i8 != 0 ? a.d(context, i8) : null, i9 != 0 ? a.d(context, i9) : null, i10 != 0 ? a.d(context, i10) : null, i11 != 0 ? a.d(context, i11) : null);
        m mVar = this.f595e;
        if (mVar != null) {
            mVar.p();
        }
    }
}
