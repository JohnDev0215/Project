package androidx.appcompat.widget;

import android.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.textclassifier.TextClassifier;
import android.widget.TextView;
import g0.b;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/* JADX INFO: compiled from: AppCompatTextView.java */
/* JADX INFO: loaded from: classes.dex */
public class w extends TextView implements androidx.core.widget.b {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final d f1238e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final v f1239g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final u f1240h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private boolean f1241i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private Future<g0.b> f1242j;

    public w(Context context) {
        this(context, null);
    }

    private void c() {
        Future<g0.b> future = this.f1242j;
        if (future != null) {
            try {
                this.f1242j = null;
                androidx.core.widget.i.m(this, future.get());
            } catch (InterruptedException | ExecutionException unused) {
            }
        }
    }

    @Override // android.widget.TextView, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        d dVar = this.f1238e;
        if (dVar != null) {
            dVar.b();
        }
        v vVar = this.f1239g;
        if (vVar != null) {
            vVar.b();
        }
    }

    @Override // android.widget.TextView
    public int getAutoSizeMaxTextSize() {
        if (androidx.core.widget.b.f2018a) {
            return super.getAutoSizeMaxTextSize();
        }
        v vVar = this.f1239g;
        if (vVar != null) {
            return vVar.e();
        }
        return -1;
    }

    @Override // android.widget.TextView
    public int getAutoSizeMinTextSize() {
        if (androidx.core.widget.b.f2018a) {
            return super.getAutoSizeMinTextSize();
        }
        v vVar = this.f1239g;
        if (vVar != null) {
            return vVar.f();
        }
        return -1;
    }

    @Override // android.widget.TextView
    public int getAutoSizeStepGranularity() {
        if (androidx.core.widget.b.f2018a) {
            return super.getAutoSizeStepGranularity();
        }
        v vVar = this.f1239g;
        if (vVar != null) {
            return vVar.g();
        }
        return -1;
    }

    @Override // android.widget.TextView
    public int[] getAutoSizeTextAvailableSizes() {
        if (androidx.core.widget.b.f2018a) {
            return super.getAutoSizeTextAvailableSizes();
        }
        v vVar = this.f1239g;
        return vVar != null ? vVar.h() : new int[0];
    }

    @Override // android.widget.TextView
    @SuppressLint({"WrongConstant"})
    public int getAutoSizeTextType() {
        if (androidx.core.widget.b.f2018a) {
            return super.getAutoSizeTextType() == 1 ? 1 : 0;
        }
        v vVar = this.f1239g;
        if (vVar != null) {
            return vVar.i();
        }
        return 0;
    }

    @Override // android.widget.TextView
    public int getFirstBaselineToTopHeight() {
        return androidx.core.widget.i.b(this);
    }

    @Override // android.widget.TextView
    public int getLastBaselineToBottomHeight() {
        return androidx.core.widget.i.c(this);
    }

    public ColorStateList getSupportBackgroundTintList() {
        d dVar = this.f1238e;
        if (dVar != null) {
            return dVar.c();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        d dVar = this.f1238e;
        if (dVar != null) {
            return dVar.d();
        }
        return null;
    }

    public ColorStateList getSupportCompoundDrawablesTintList() {
        return this.f1239g.j();
    }

    public PorterDuff.Mode getSupportCompoundDrawablesTintMode() {
        return this.f1239g.k();
    }

    @Override // android.widget.TextView
    public CharSequence getText() {
        c();
        return super.getText();
    }

    @Override // android.widget.TextView
    public TextClassifier getTextClassifier() {
        return super.getTextClassifier();
    }

    public b.a getTextMetricsParamsCompat() {
        return androidx.core.widget.i.f(this);
    }

    @Override // android.widget.TextView, android.view.View
    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        InputConnection inputConnectionOnCreateInputConnection = super.onCreateInputConnection(editorInfo);
        this.f1239g.r(this, inputConnectionOnCreateInputConnection, editorInfo);
        return j.a(inputConnectionOnCreateInputConnection, editorInfo, this);
    }

    @Override // android.widget.TextView, android.view.View
    protected void onLayout(boolean z6, int i7, int i8, int i9, int i10) {
        super.onLayout(z6, i7, i8, i9, i10);
        v vVar = this.f1239g;
        if (vVar != null) {
            vVar.o(z6, i7, i8, i9, i10);
        }
    }

    @Override // android.widget.TextView, android.view.View
    protected void onMeasure(int i7, int i8) {
        c();
        super.onMeasure(i7, i8);
    }

    @Override // android.widget.TextView
    protected void onTextChanged(CharSequence charSequence, int i7, int i8, int i9) {
        super.onTextChanged(charSequence, i7, i8, i9);
        v vVar = this.f1239g;
        if (vVar == null || androidx.core.widget.b.f2018a || !vVar.l()) {
            return;
        }
        this.f1239g.c();
    }

    @Override // android.widget.TextView
    public void setAutoSizeTextTypeUniformWithConfiguration(int i7, int i8, int i9, int i10) {
        if (androidx.core.widget.b.f2018a) {
            super.setAutoSizeTextTypeUniformWithConfiguration(i7, i8, i9, i10);
            return;
        }
        v vVar = this.f1239g;
        if (vVar != null) {
            vVar.t(i7, i8, i9, i10);
        }
    }

    @Override // android.widget.TextView
    public void setAutoSizeTextTypeUniformWithPresetSizes(int[] iArr, int i7) {
        if (androidx.core.widget.b.f2018a) {
            super.setAutoSizeTextTypeUniformWithPresetSizes(iArr, i7);
            return;
        }
        v vVar = this.f1239g;
        if (vVar != null) {
            vVar.u(iArr, i7);
        }
    }

    @Override // android.widget.TextView
    public void setAutoSizeTextTypeWithDefaults(int i7) {
        if (androidx.core.widget.b.f2018a) {
            super.setAutoSizeTextTypeWithDefaults(i7);
            return;
        }
        v vVar = this.f1239g;
        if (vVar != null) {
            vVar.v(i7);
        }
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        d dVar = this.f1238e;
        if (dVar != null) {
            dVar.f(drawable);
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i7) {
        super.setBackgroundResource(i7);
        d dVar = this.f1238e;
        if (dVar != null) {
            dVar.g(i7);
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawables(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawables(drawable, drawable2, drawable3, drawable4);
        v vVar = this.f1239g;
        if (vVar != null) {
            vVar.p();
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesRelative(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesRelative(drawable, drawable2, drawable3, drawable4);
        v vVar = this.f1239g;
        if (vVar != null) {
            vVar.p();
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesRelativeWithIntrinsicBounds(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
        v vVar = this.f1239g;
        if (vVar != null) {
            vVar.p();
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesWithIntrinsicBounds(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
        v vVar = this.f1239g;
        if (vVar != null) {
            vVar.p();
        }
    }

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(androidx.core.widget.i.p(this, callback));
    }

    @Override // android.widget.TextView
    public void setFirstBaselineToTopHeight(int i7) {
        super.setFirstBaselineToTopHeight(i7);
    }

    @Override // android.widget.TextView
    public void setLastBaselineToBottomHeight(int i7) {
        super.setLastBaselineToBottomHeight(i7);
    }

    @Override // android.widget.TextView
    public void setLineHeight(int i7) {
        androidx.core.widget.i.l(this, i7);
    }

    public void setPrecomputedText(g0.b bVar) {
        androidx.core.widget.i.m(this, bVar);
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        d dVar = this.f1238e;
        if (dVar != null) {
            dVar.i(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        d dVar = this.f1238e;
        if (dVar != null) {
            dVar.j(mode);
        }
    }

    public void setSupportCompoundDrawablesTintList(ColorStateList colorStateList) {
        this.f1239g.w(colorStateList);
        this.f1239g.b();
    }

    public void setSupportCompoundDrawablesTintMode(PorterDuff.Mode mode) {
        this.f1239g.x(mode);
        this.f1239g.b();
    }

    @Override // android.widget.TextView
    public void setTextAppearance(Context context, int i7) {
        super.setTextAppearance(context, i7);
        v vVar = this.f1239g;
        if (vVar != null) {
            vVar.q(context, i7);
        }
    }

    @Override // android.widget.TextView
    public void setTextClassifier(TextClassifier textClassifier) {
        super.setTextClassifier(textClassifier);
    }

    public void setTextFuture(Future<g0.b> future) {
        this.f1242j = future;
        if (future != null) {
            requestLayout();
        }
    }

    public void setTextMetricsParamsCompat(b.a aVar) {
        androidx.core.widget.i.o(this, aVar);
    }

    @Override // android.widget.TextView
    public void setTextSize(int i7, float f7) {
        if (androidx.core.widget.b.f2018a) {
            super.setTextSize(i7, f7);
            return;
        }
        v vVar = this.f1239g;
        if (vVar != null) {
            vVar.A(i7, f7);
        }
    }

    @Override // android.widget.TextView
    public void setTypeface(Typeface typeface, int i7) {
        if (this.f1241i) {
            return;
        }
        Typeface typefaceA = null;
        if (typeface != null && i7 > 0) {
            typefaceA = z.c.a(getContext(), typeface, i7);
        }
        this.f1241i = true;
        if (typefaceA != null) {
            typeface = typefaceA;
        }
        try {
            super.setTypeface(typeface, i7);
        } finally {
            this.f1241i = false;
        }
    }

    public w(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.textViewStyle);
    }

    public w(Context context, AttributeSet attributeSet, int i7) {
        super(p0.b(context), attributeSet, i7);
        this.f1241i = false;
        n0.a(this, getContext());
        d dVar = new d(this);
        this.f1238e = dVar;
        dVar.e(attributeSet, i7);
        v vVar = new v(this);
        this.f1239g = vVar;
        vVar.m(attributeSet, i7);
        vVar.b();
        this.f1240h = new u(this);
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesRelativeWithIntrinsicBounds(int i7, int i8, int i9, int i10) {
        Context context = getContext();
        setCompoundDrawablesRelativeWithIntrinsicBounds(i7 != 0 ? f.a.b(context, i7) : null, i8 != 0 ? f.a.b(context, i8) : null, i9 != 0 ? f.a.b(context, i9) : null, i10 != 0 ? f.a.b(context, i10) : null);
        v vVar = this.f1239g;
        if (vVar != null) {
            vVar.p();
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesWithIntrinsicBounds(int i7, int i8, int i9, int i10) {
        Context context = getContext();
        setCompoundDrawablesWithIntrinsicBounds(i7 != 0 ? f.a.b(context, i7) : null, i8 != 0 ? f.a.b(context, i8) : null, i9 != 0 ? f.a.b(context, i9) : null, i10 != 0 ? f.a.b(context, i10) : null);
        v vVar = this.f1239g;
        if (vVar != null) {
            vVar.p();
        }
    }
}
