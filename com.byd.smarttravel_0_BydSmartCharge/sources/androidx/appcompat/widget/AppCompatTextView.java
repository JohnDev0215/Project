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
import b.b.b.a.a;
import b.b.f.b0;
import b.b.f.d;
import b.b.f.g;
import b.b.f.l;
import b.b.f.m;
import b.b.f.z;
import b.g.h.c;
import b.g.j.s;
import b.g.k.b;
import b.g.k.i;
import b.g.k.k;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/* JADX INFO: loaded from: classes.dex */
public class AppCompatTextView extends TextView implements s, k, b {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final d f211b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final m f212c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final l f213d;
    public Future<c> e;

    public AppCompatTextView(Context context) {
        this(context, null);
    }

    public final void c() {
        Future<c> future = this.e;
        if (future != null) {
            try {
                this.e = null;
                i.p(this, future.get());
            } catch (InterruptedException | ExecutionException unused) {
            }
        }
    }

    @Override // android.widget.TextView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        d dVar = this.f211b;
        if (dVar != null) {
            dVar.b();
        }
        m mVar = this.f212c;
        if (mVar != null) {
            mVar.b();
        }
    }

    @Override // android.widget.TextView
    public int getAutoSizeMaxTextSize() {
        if (b.f1177a) {
            return super.getAutoSizeMaxTextSize();
        }
        m mVar = this.f212c;
        if (mVar != null) {
            return mVar.e();
        }
        return -1;
    }

    @Override // android.widget.TextView
    public int getAutoSizeMinTextSize() {
        if (b.f1177a) {
            return super.getAutoSizeMinTextSize();
        }
        m mVar = this.f212c;
        if (mVar != null) {
            return mVar.f();
        }
        return -1;
    }

    @Override // android.widget.TextView
    public int getAutoSizeStepGranularity() {
        if (b.f1177a) {
            return super.getAutoSizeStepGranularity();
        }
        m mVar = this.f212c;
        if (mVar != null) {
            return mVar.g();
        }
        return -1;
    }

    @Override // android.widget.TextView
    public int[] getAutoSizeTextAvailableSizes() {
        if (b.f1177a) {
            return super.getAutoSizeTextAvailableSizes();
        }
        m mVar = this.f212c;
        return mVar != null ? mVar.h() : new int[0];
    }

    @Override // android.widget.TextView
    @SuppressLint({"WrongConstant"})
    public int getAutoSizeTextType() {
        if (b.f1177a) {
            return super.getAutoSizeTextType() == 1 ? 1 : 0;
        }
        m mVar = this.f212c;
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

    @Override // b.g.j.s
    public ColorStateList getSupportBackgroundTintList() {
        d dVar = this.f211b;
        if (dVar != null) {
            return dVar.c();
        }
        return null;
    }

    @Override // b.g.j.s
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        d dVar = this.f211b;
        if (dVar != null) {
            return dVar.d();
        }
        return null;
    }

    public ColorStateList getSupportCompoundDrawablesTintList() {
        return this.f212c.j();
    }

    public PorterDuff.Mode getSupportCompoundDrawablesTintMode() {
        return this.f212c.k();
    }

    @Override // android.widget.TextView
    public CharSequence getText() {
        c();
        return super.getText();
    }

    @Override // android.widget.TextView
    public TextClassifier getTextClassifier() {
        l lVar;
        return (Build.VERSION.SDK_INT >= 28 || (lVar = this.f213d) == null) ? super.getTextClassifier() : lVar.a();
    }

    public c.a getTextMetricsParamsCompat() {
        return i.g(this);
    }

    @Override // android.widget.TextView, android.view.View
    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        return g.a(super.onCreateInputConnection(editorInfo), editorInfo, this);
    }

    @Override // android.widget.TextView, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        m mVar = this.f212c;
        if (mVar != null) {
            mVar.o(z, i, i2, i3, i4);
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
        m mVar = this.f212c;
        if (mVar == null || b.f1177a || !mVar.l()) {
            return;
        }
        this.f212c.c();
    }

    @Override // android.widget.TextView
    public void setAutoSizeTextTypeUniformWithConfiguration(int i, int i2, int i3, int i4) throws IllegalArgumentException {
        if (b.f1177a) {
            super.setAutoSizeTextTypeUniformWithConfiguration(i, i2, i3, i4);
            return;
        }
        m mVar = this.f212c;
        if (mVar != null) {
            mVar.s(i, i2, i3, i4);
        }
    }

    @Override // android.widget.TextView
    public void setAutoSizeTextTypeUniformWithPresetSizes(int[] iArr, int i) throws IllegalArgumentException {
        if (b.f1177a) {
            super.setAutoSizeTextTypeUniformWithPresetSizes(iArr, i);
            return;
        }
        m mVar = this.f212c;
        if (mVar != null) {
            mVar.t(iArr, i);
        }
    }

    @Override // android.widget.TextView
    public void setAutoSizeTextTypeWithDefaults(int i) {
        if (b.f1177a) {
            super.setAutoSizeTextTypeWithDefaults(i);
            return;
        }
        m mVar = this.f212c;
        if (mVar != null) {
            mVar.u(i);
        }
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        d dVar = this.f211b;
        if (dVar != null) {
            dVar.f(drawable);
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        d dVar = this.f211b;
        if (dVar != null) {
            dVar.g(i);
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawables(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawables(drawable, drawable2, drawable3, drawable4);
        m mVar = this.f212c;
        if (mVar != null) {
            mVar.p();
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesRelative(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesRelative(drawable, drawable2, drawable3, drawable4);
        m mVar = this.f212c;
        if (mVar != null) {
            mVar.p();
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesRelativeWithIntrinsicBounds(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
        m mVar = this.f212c;
        if (mVar != null) {
            mVar.p();
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesWithIntrinsicBounds(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
        m mVar = this.f212c;
        if (mVar != null) {
            mVar.p();
        }
    }

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(i.s(this, callback));
    }

    @Override // android.widget.TextView
    public void setFirstBaselineToTopHeight(int i) {
        if (Build.VERSION.SDK_INT >= 28) {
            super.setFirstBaselineToTopHeight(i);
        } else {
            i.m(this, i);
        }
    }

    @Override // android.widget.TextView
    public void setLastBaselineToBottomHeight(int i) {
        if (Build.VERSION.SDK_INT >= 28) {
            super.setLastBaselineToBottomHeight(i);
        } else {
            i.n(this, i);
        }
    }

    @Override // android.widget.TextView
    public void setLineHeight(int i) {
        i.o(this, i);
    }

    public void setPrecomputedText(c cVar) {
        i.p(this, cVar);
    }

    @Override // b.g.j.s
    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        d dVar = this.f211b;
        if (dVar != null) {
            dVar.i(colorStateList);
        }
    }

    @Override // b.g.j.s
    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        d dVar = this.f211b;
        if (dVar != null) {
            dVar.j(mode);
        }
    }

    @Override // b.g.k.k
    public void setSupportCompoundDrawablesTintList(ColorStateList colorStateList) {
        this.f212c.v(colorStateList);
        this.f212c.b();
    }

    @Override // b.g.k.k
    public void setSupportCompoundDrawablesTintMode(PorterDuff.Mode mode) {
        this.f212c.w(mode);
        this.f212c.b();
    }

    @Override // android.widget.TextView
    public void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        m mVar = this.f212c;
        if (mVar != null) {
            mVar.q(context, i);
        }
    }

    @Override // android.widget.TextView
    public void setTextClassifier(TextClassifier textClassifier) {
        l lVar;
        if (Build.VERSION.SDK_INT >= 28 || (lVar = this.f213d) == null) {
            super.setTextClassifier(textClassifier);
        } else {
            lVar.b(textClassifier);
        }
    }

    public void setTextFuture(Future<c> future) {
        this.e = future;
        if (future != null) {
            requestLayout();
        }
    }

    public void setTextMetricsParamsCompat(c.a aVar) {
        i.r(this, aVar);
    }

    @Override // android.widget.TextView
    public void setTextSize(int i, float f) {
        if (b.f1177a) {
            super.setTextSize(i, f);
            return;
        }
        m mVar = this.f212c;
        if (mVar != null) {
            mVar.z(i, f);
        }
    }

    @Override // android.widget.TextView
    public void setTypeface(Typeface typeface, int i) {
        Typeface typefaceA = (typeface == null || i <= 0) ? null : b.g.c.d.a(getContext(), typeface, i);
        if (typefaceA != null) {
            typeface = typefaceA;
        }
        super.setTypeface(typeface, i);
    }

    public AppCompatTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.textViewStyle);
    }

    public AppCompatTextView(Context context, AttributeSet attributeSet, int i) {
        super(b0.b(context), attributeSet, i);
        z.a(this, getContext());
        d dVar = new d(this);
        this.f211b = dVar;
        dVar.e(attributeSet, i);
        m mVar = new m(this);
        this.f212c = mVar;
        mVar.m(attributeSet, i);
        mVar.b();
        this.f213d = new l(this);
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesRelativeWithIntrinsicBounds(int i, int i2, int i3, int i4) {
        Context context = getContext();
        setCompoundDrawablesRelativeWithIntrinsicBounds(i != 0 ? a.d(context, i) : null, i2 != 0 ? a.d(context, i2) : null, i3 != 0 ? a.d(context, i3) : null, i4 != 0 ? a.d(context, i4) : null);
        m mVar = this.f212c;
        if (mVar != null) {
            mVar.p();
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesWithIntrinsicBounds(int i, int i2, int i3, int i4) {
        Context context = getContext();
        setCompoundDrawablesWithIntrinsicBounds(i != 0 ? a.d(context, i) : null, i2 != 0 ? a.d(context, i2) : null, i3 != 0 ? a.d(context, i3) : null, i4 != 0 ? a.d(context, i4) : null);
        m mVar = this.f212c;
        if (mVar != null) {
            mVar.p();
        }
    }
}
