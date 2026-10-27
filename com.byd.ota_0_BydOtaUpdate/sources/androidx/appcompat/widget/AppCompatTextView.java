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
import b.b.q.b0;
import b.b.q.d;
import b.b.q.g;
import b.b.q.l;
import b.b.q.m;
import b.b.q.z;
import b.f.k.a;
import b.f.m.r;
import b.f.n.b;
import b.f.n.i;
import b.f.n.k;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/* JADX INFO: loaded from: classes.dex */
public class AppCompatTextView extends TextView implements r, k, b {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final d f219c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final m f220d;
    public final l s;
    public Future<a> x;

    public AppCompatTextView(Context context) {
        this(context, null);
    }

    @Override // android.widget.TextView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        d dVar = this.f219c;
        if (dVar != null) {
            dVar.b();
        }
        m mVar = this.f220d;
        if (mVar != null) {
            mVar.b();
        }
    }

    public final void e() {
        Future<a> future = this.x;
        if (future != null) {
            try {
                this.x = null;
                i.k(this, future.get());
            } catch (InterruptedException | ExecutionException unused) {
            }
        }
    }

    @Override // android.widget.TextView
    public int getAutoSizeMaxTextSize() {
        if (b.f1833a) {
            return super.getAutoSizeMaxTextSize();
        }
        m mVar = this.f220d;
        if (mVar != null) {
            return mVar.e();
        }
        return -1;
    }

    @Override // android.widget.TextView
    public int getAutoSizeMinTextSize() {
        if (b.f1833a) {
            return super.getAutoSizeMinTextSize();
        }
        m mVar = this.f220d;
        if (mVar != null) {
            return mVar.f();
        }
        return -1;
    }

    @Override // android.widget.TextView
    public int getAutoSizeStepGranularity() {
        if (b.f1833a) {
            return super.getAutoSizeStepGranularity();
        }
        m mVar = this.f220d;
        if (mVar != null) {
            return mVar.g();
        }
        return -1;
    }

    @Override // android.widget.TextView
    public int[] getAutoSizeTextAvailableSizes() {
        if (b.f1833a) {
            return super.getAutoSizeTextAvailableSizes();
        }
        m mVar = this.f220d;
        return mVar != null ? mVar.h() : new int[0];
    }

    @Override // android.widget.TextView
    @SuppressLint({"WrongConstant"})
    public int getAutoSizeTextType() {
        if (b.f1833a) {
            return super.getAutoSizeTextType() == 1 ? 1 : 0;
        }
        m mVar = this.f220d;
        if (mVar != null) {
            return mVar.i();
        }
        return 0;
    }

    @Override // android.widget.TextView
    public int getFirstBaselineToTopHeight() {
        return i.a(this);
    }

    @Override // android.widget.TextView
    public int getLastBaselineToBottomHeight() {
        return i.b(this);
    }

    @Override // b.f.m.r
    public ColorStateList getSupportBackgroundTintList() {
        d dVar = this.f219c;
        if (dVar != null) {
            return dVar.c();
        }
        return null;
    }

    @Override // b.f.m.r
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        d dVar = this.f219c;
        if (dVar != null) {
            return dVar.d();
        }
        return null;
    }

    public ColorStateList getSupportCompoundDrawablesTintList() {
        return this.f220d.j();
    }

    public PorterDuff.Mode getSupportCompoundDrawablesTintMode() {
        return this.f220d.k();
    }

    @Override // android.widget.TextView
    public CharSequence getText() {
        e();
        return super.getText();
    }

    @Override // android.widget.TextView
    public TextClassifier getTextClassifier() {
        l lVar;
        return (Build.VERSION.SDK_INT >= 28 || (lVar = this.s) == null) ? super.getTextClassifier() : lVar.a();
    }

    public a.C0026a getTextMetricsParamsCompat() {
        return i.e(this);
    }

    @Override // android.widget.TextView, android.view.View
    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        return g.a(super.onCreateInputConnection(editorInfo), editorInfo, this);
    }

    @Override // android.widget.TextView, android.view.View
    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
        m mVar = this.f220d;
        if (mVar != null) {
            mVar.o(z, i2, i3, i4, i5);
        }
    }

    @Override // android.widget.TextView, android.view.View
    public void onMeasure(int i2, int i3) {
        e();
        super.onMeasure(i2, i3);
    }

    @Override // android.widget.TextView
    public void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
        super.onTextChanged(charSequence, i2, i3, i4);
        m mVar = this.f220d;
        if (mVar == null || b.f1833a || !mVar.l()) {
            return;
        }
        this.f220d.c();
    }

    @Override // android.widget.TextView
    public void setAutoSizeTextTypeUniformWithConfiguration(int i2, int i3, int i4, int i5) throws IllegalArgumentException {
        if (b.f1833a) {
            super.setAutoSizeTextTypeUniformWithConfiguration(i2, i3, i4, i5);
            return;
        }
        m mVar = this.f220d;
        if (mVar != null) {
            mVar.s(i2, i3, i4, i5);
        }
    }

    @Override // android.widget.TextView
    public void setAutoSizeTextTypeUniformWithPresetSizes(int[] iArr, int i2) throws IllegalArgumentException {
        if (b.f1833a) {
            super.setAutoSizeTextTypeUniformWithPresetSizes(iArr, i2);
            return;
        }
        m mVar = this.f220d;
        if (mVar != null) {
            mVar.t(iArr, i2);
        }
    }

    @Override // android.widget.TextView
    public void setAutoSizeTextTypeWithDefaults(int i2) {
        if (b.f1833a) {
            super.setAutoSizeTextTypeWithDefaults(i2);
            return;
        }
        m mVar = this.f220d;
        if (mVar != null) {
            mVar.u(i2);
        }
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        d dVar = this.f219c;
        if (dVar != null) {
            dVar.f(drawable);
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i2) {
        super.setBackgroundResource(i2);
        d dVar = this.f219c;
        if (dVar != null) {
            dVar.g(i2);
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawables(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawables(drawable, drawable2, drawable3, drawable4);
        m mVar = this.f220d;
        if (mVar != null) {
            mVar.p();
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesRelative(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesRelative(drawable, drawable2, drawable3, drawable4);
        m mVar = this.f220d;
        if (mVar != null) {
            mVar.p();
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesRelativeWithIntrinsicBounds(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
        m mVar = this.f220d;
        if (mVar != null) {
            mVar.p();
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesWithIntrinsicBounds(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
        m mVar = this.f220d;
        if (mVar != null) {
            mVar.p();
        }
    }

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(i.n(this, callback));
    }

    @Override // android.widget.TextView
    public void setFirstBaselineToTopHeight(int i2) {
        if (Build.VERSION.SDK_INT >= 28) {
            super.setFirstBaselineToTopHeight(i2);
        } else {
            i.h(this, i2);
        }
    }

    @Override // android.widget.TextView
    public void setLastBaselineToBottomHeight(int i2) {
        if (Build.VERSION.SDK_INT >= 28) {
            super.setLastBaselineToBottomHeight(i2);
        } else {
            i.i(this, i2);
        }
    }

    @Override // android.widget.TextView
    public void setLineHeight(int i2) {
        i.j(this, i2);
    }

    public void setPrecomputedText(a aVar) {
        i.k(this, aVar);
    }

    @Override // b.f.m.r
    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        d dVar = this.f219c;
        if (dVar != null) {
            dVar.i(colorStateList);
        }
    }

    @Override // b.f.m.r
    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        d dVar = this.f219c;
        if (dVar != null) {
            dVar.j(mode);
        }
    }

    @Override // b.f.n.k
    public void setSupportCompoundDrawablesTintList(ColorStateList colorStateList) {
        this.f220d.v(colorStateList);
        this.f220d.b();
    }

    @Override // b.f.n.k
    public void setSupportCompoundDrawablesTintMode(PorterDuff.Mode mode) {
        this.f220d.w(mode);
        this.f220d.b();
    }

    @Override // android.widget.TextView
    public void setTextAppearance(Context context, int i2) {
        super.setTextAppearance(context, i2);
        m mVar = this.f220d;
        if (mVar != null) {
            mVar.q(context, i2);
        }
    }

    @Override // android.widget.TextView
    public void setTextClassifier(TextClassifier textClassifier) {
        l lVar;
        if (Build.VERSION.SDK_INT >= 28 || (lVar = this.s) == null) {
            super.setTextClassifier(textClassifier);
        } else {
            lVar.b(textClassifier);
        }
    }

    public void setTextFuture(Future<a> future) {
        this.x = future;
        if (future != null) {
            requestLayout();
        }
    }

    public void setTextMetricsParamsCompat(a.C0026a c0026a) {
        i.m(this, c0026a);
    }

    @Override // android.widget.TextView
    public void setTextSize(int i2, float f2) {
        if (b.f1833a) {
            super.setTextSize(i2, f2);
            return;
        }
        m mVar = this.f220d;
        if (mVar != null) {
            mVar.z(i2, f2);
        }
    }

    @Override // android.widget.TextView
    public void setTypeface(Typeface typeface, int i2) {
        Typeface typefaceA = (typeface == null || i2 <= 0) ? null : b.f.g.d.a(getContext(), typeface, i2);
        if (typefaceA != null) {
            typeface = typefaceA;
        }
        super.setTypeface(typeface, i2);
    }

    public AppCompatTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.textViewStyle);
    }

    public AppCompatTextView(Context context, AttributeSet attributeSet, int i2) {
        super(b0.b(context), attributeSet, i2);
        z.a(this, getContext());
        d dVar = new d(this);
        this.f219c = dVar;
        dVar.e(attributeSet, i2);
        m mVar = new m(this);
        this.f220d = mVar;
        mVar.m(attributeSet, i2);
        mVar.b();
        this.s = new l(this);
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesRelativeWithIntrinsicBounds(int i2, int i3, int i4, int i5) {
        Context context = getContext();
        setCompoundDrawablesRelativeWithIntrinsicBounds(i2 != 0 ? b.b.l.a.a.d(context, i2) : null, i3 != 0 ? b.b.l.a.a.d(context, i3) : null, i4 != 0 ? b.b.l.a.a.d(context, i4) : null, i5 != 0 ? b.b.l.a.a.d(context, i5) : null);
        m mVar = this.f220d;
        if (mVar != null) {
            mVar.p();
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesWithIntrinsicBounds(int i2, int i3, int i4, int i5) {
        Context context = getContext();
        setCompoundDrawablesWithIntrinsicBounds(i2 != 0 ? b.b.l.a.a.d(context, i2) : null, i3 != 0 ? b.b.l.a.a.d(context, i3) : null, i4 != 0 ? b.b.l.a.a.d(context, i4) : null, i5 != 0 ? b.b.l.a.a.d(context, i5) : null);
        m mVar = this.f220d;
        if (mVar != null) {
            mVar.p();
        }
    }
}
