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
import androidx.annotation.DrawableRes;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Px;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.core.widget.TextViewCompat;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/* JADX INFO: renamed from: androidx.appcompat.widget.x, reason: case insensitive filesystem */
/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class C0077x extends TextView implements androidx.core.view.r, androidx.core.widget.k, androidx.core.widget.b {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final C0058d f393c;
    private final C0076w d;
    private final C0075v e;

    @Nullable
    private Future<androidx.core.c.a> f;

    public C0077x(@NonNull Context context) {
        this(context, null);
    }

    private void l() {
        Future<androidx.core.c.a> future = this.f;
        if (future != null) {
            try {
                this.f = null;
                TextViewCompat.j(this, future.get());
            } catch (InterruptedException | ExecutionException unused) {
            }
        }
    }

    @Override // androidx.core.view.r
    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public PorterDuff.Mode c() {
        C0058d c0058d = this.f393c;
        if (c0058d != null) {
            return c0058d.d();
        }
        return null;
    }

    @Override // android.widget.TextView, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        C0058d c0058d = this.f393c;
        if (c0058d != null) {
            c0058d.b();
        }
        C0076w c0076w = this.d;
        if (c0076w != null) {
            c0076w.b();
        }
    }

    @Override // androidx.core.widget.k
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public void e(@Nullable ColorStateList colorStateList) {
        this.d.t(colorStateList);
        this.d.b();
    }

    @Override // androidx.core.view.r
    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public ColorStateList f() {
        C0058d c0058d = this.f393c;
        if (c0058d != null) {
            return c0058d.c();
        }
        return null;
    }

    @Override // android.widget.TextView
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public int getAutoSizeMaxTextSize() {
        if (androidx.core.widget.b.f602a) {
            return super.getAutoSizeMaxTextSize();
        }
        C0076w c0076w = this.d;
        if (c0076w != null) {
            return c0076w.e();
        }
        return -1;
    }

    @Override // android.widget.TextView
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public int getAutoSizeMinTextSize() {
        if (androidx.core.widget.b.f602a) {
            return super.getAutoSizeMinTextSize();
        }
        C0076w c0076w = this.d;
        if (c0076w != null) {
            return c0076w.f();
        }
        return -1;
    }

    @Override // android.widget.TextView
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public int getAutoSizeStepGranularity() {
        if (androidx.core.widget.b.f602a) {
            return super.getAutoSizeStepGranularity();
        }
        C0076w c0076w = this.d;
        if (c0076w != null) {
            return c0076w.g();
        }
        return -1;
    }

    @Override // android.widget.TextView
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public int[] getAutoSizeTextAvailableSizes() {
        if (androidx.core.widget.b.f602a) {
            return super.getAutoSizeTextAvailableSizes();
        }
        C0076w c0076w = this.d;
        return c0076w != null ? c0076w.h() : new int[0];
    }

    @Override // android.widget.TextView
    @SuppressLint({"WrongConstant"})
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public int getAutoSizeTextType() {
        if (androidx.core.widget.b.f602a) {
            return super.getAutoSizeTextType() == 1 ? 1 : 0;
        }
        C0076w c0076w = this.d;
        if (c0076w != null) {
            return c0076w.i();
        }
        return 0;
    }

    @Override // android.widget.TextView
    public int getFirstBaselineToTopHeight() {
        return TextViewCompat.a(this);
    }

    @Override // android.widget.TextView
    public int getLastBaselineToBottomHeight() {
        return TextViewCompat.b(this);
    }

    @Override // android.widget.TextView
    public CharSequence getText() {
        l();
        return super.getText();
    }

    @Override // android.widget.TextView
    @NonNull
    @RequiresApi(api = 26)
    public TextClassifier getTextClassifier() {
        C0075v c0075v;
        return (Build.VERSION.SDK_INT >= 28 || (c0075v = this.e) == null) ? super.getTextClassifier() : c0075v.a();
    }

    @Override // androidx.core.view.r
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public void h(@Nullable PorterDuff.Mode mode) {
        C0058d c0058d = this.f393c;
        if (c0058d != null) {
            c0058d.j(mode);
        }
    }

    @Override // androidx.core.view.r
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public void i(@Nullable ColorStateList colorStateList) {
        C0058d c0058d = this.f393c;
        if (c0058d != null) {
            c0058d.i(colorStateList);
        }
    }

    @Override // androidx.core.widget.k
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public void k(@Nullable PorterDuff.Mode mode) {
        this.d.u(mode);
        this.d.b();
    }

    @Override // android.widget.TextView, android.view.View
    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        InputConnection inputConnectionOnCreateInputConnection = super.onCreateInputConnection(editorInfo);
        C0065k.a(inputConnectionOnCreateInputConnection, editorInfo, this);
        return inputConnectionOnCreateInputConnection;
    }

    @Override // android.widget.TextView, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        C0076w c0076w = this.d;
        if (c0076w != null) {
            c0076w.m(z, i, i2, i3, i4);
        }
    }

    @Override // android.widget.TextView, android.view.View
    protected void onMeasure(int i, int i2) {
        l();
        super.onMeasure(i, i2);
    }

    @Override // android.widget.TextView
    protected void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        super.onTextChanged(charSequence, i, i2, i3);
        C0076w c0076w = this.d;
        if (c0076w == null || androidx.core.widget.b.f602a || !c0076w.j()) {
            return;
        }
        this.d.c();
    }

    @Override // android.widget.TextView
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public void setAutoSizeTextTypeUniformWithConfiguration(int i, int i2, int i3, int i4) {
        if (androidx.core.widget.b.f602a) {
            super.setAutoSizeTextTypeUniformWithConfiguration(i, i2, i3, i4);
            return;
        }
        C0076w c0076w = this.d;
        if (c0076w != null) {
            c0076w.q(i, i2, i3, i4);
        }
    }

    @Override // android.widget.TextView
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public void setAutoSizeTextTypeUniformWithPresetSizes(@NonNull int[] iArr, int i) {
        if (androidx.core.widget.b.f602a) {
            super.setAutoSizeTextTypeUniformWithPresetSizes(iArr, i);
            return;
        }
        C0076w c0076w = this.d;
        if (c0076w != null) {
            c0076w.r(iArr, i);
        }
    }

    @Override // android.widget.TextView
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public void setAutoSizeTextTypeWithDefaults(int i) {
        if (androidx.core.widget.b.f602a) {
            super.setAutoSizeTextTypeWithDefaults(i);
            return;
        }
        C0076w c0076w = this.d;
        if (c0076w != null) {
            c0076w.s(i);
        }
    }

    @Override // android.view.View
    public void setBackgroundDrawable(@Nullable Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        C0058d c0058d = this.f393c;
        if (c0058d != null) {
            c0058d.f(drawable);
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(@DrawableRes int i) {
        super.setBackgroundResource(i);
        C0058d c0058d = this.f393c;
        if (c0058d != null) {
            c0058d.g(i);
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawables(@Nullable Drawable drawable, @Nullable Drawable drawable2, @Nullable Drawable drawable3, @Nullable Drawable drawable4) {
        super.setCompoundDrawables(drawable, drawable2, drawable3, drawable4);
        C0076w c0076w = this.d;
        if (c0076w != null) {
            c0076w.n();
        }
    }

    @Override // android.widget.TextView
    @RequiresApi(17)
    public void setCompoundDrawablesRelative(@Nullable Drawable drawable, @Nullable Drawable drawable2, @Nullable Drawable drawable3, @Nullable Drawable drawable4) {
        super.setCompoundDrawablesRelative(drawable, drawable2, drawable3, drawable4);
        C0076w c0076w = this.d;
        if (c0076w != null) {
            c0076w.n();
        }
    }

    @Override // android.widget.TextView
    @RequiresApi(17)
    public void setCompoundDrawablesRelativeWithIntrinsicBounds(@Nullable Drawable drawable, @Nullable Drawable drawable2, @Nullable Drawable drawable3, @Nullable Drawable drawable4) {
        super.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
        C0076w c0076w = this.d;
        if (c0076w != null) {
            c0076w.n();
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesWithIntrinsicBounds(@Nullable Drawable drawable, @Nullable Drawable drawable2, @Nullable Drawable drawable3, @Nullable Drawable drawable4) {
        super.setCompoundDrawablesWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
        C0076w c0076w = this.d;
        if (c0076w != null) {
            c0076w.n();
        }
    }

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(TextViewCompat.k(this, callback));
    }

    @Override // android.widget.TextView
    public void setFirstBaselineToTopHeight(@IntRange(from = 0) @Px int i) {
        if (Build.VERSION.SDK_INT >= 28) {
            super.setFirstBaselineToTopHeight(i);
        } else {
            TextViewCompat.g(this, i);
        }
    }

    @Override // android.widget.TextView
    public void setLastBaselineToBottomHeight(@IntRange(from = 0) @Px int i) {
        if (Build.VERSION.SDK_INT >= 28) {
            super.setLastBaselineToBottomHeight(i);
        } else {
            TextViewCompat.h(this, i);
        }
    }

    @Override // android.widget.TextView
    public void setLineHeight(@IntRange(from = 0) @Px int i) {
        TextViewCompat.i(this, i);
    }

    @Override // android.widget.TextView
    public void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        C0076w c0076w = this.d;
        if (c0076w != null) {
            c0076w.o(context, i);
        }
    }

    @Override // android.widget.TextView
    @RequiresApi(api = 26)
    public void setTextClassifier(@Nullable TextClassifier textClassifier) {
        C0075v c0075v;
        if (Build.VERSION.SDK_INT >= 28 || (c0075v = this.e) == null) {
            super.setTextClassifier(textClassifier);
        } else {
            c0075v.b(textClassifier);
        }
    }

    @Override // android.widget.TextView
    public void setTextSize(int i, float f) {
        if (androidx.core.widget.b.f602a) {
            super.setTextSize(i, f);
            return;
        }
        C0076w c0076w = this.d;
        if (c0076w != null) {
            c0076w.x(i, f);
        }
    }

    @Override // android.widget.TextView
    public void setTypeface(@Nullable Typeface typeface, int i) {
        Typeface typefaceA = (typeface == null || i <= 0) ? null : androidx.core.graphics.d.a(getContext(), typeface, i);
        if (typefaceA != null) {
            typeface = typefaceA;
        }
        super.setTypeface(typeface, i);
    }

    public C0077x(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.textViewStyle);
    }

    public C0077x(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(Q.b(context), attributeSet, i);
        O.a(this, getContext());
        C0058d c0058d = new C0058d(this);
        this.f393c = c0058d;
        c0058d.e(attributeSet, i);
        C0076w c0076w = new C0076w(this);
        this.d = c0076w;
        c0076w.k(attributeSet, i);
        this.d.b();
        this.e = new C0075v(this);
    }

    @Override // android.widget.TextView
    @RequiresApi(17)
    public void setCompoundDrawablesRelativeWithIntrinsicBounds(int i, int i2, int i3, int i4) {
        Context context = getContext();
        setCompoundDrawablesRelativeWithIntrinsicBounds(i != 0 ? androidx.appcompat.a.a.a.b(context, i) : null, i2 != 0 ? androidx.appcompat.a.a.a.b(context, i2) : null, i3 != 0 ? androidx.appcompat.a.a.a.b(context, i3) : null, i4 != 0 ? androidx.appcompat.a.a.a.b(context, i4) : null);
        C0076w c0076w = this.d;
        if (c0076w != null) {
            c0076w.n();
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesWithIntrinsicBounds(int i, int i2, int i3, int i4) {
        Context context = getContext();
        setCompoundDrawablesWithIntrinsicBounds(i != 0 ? androidx.appcompat.a.a.a.b(context, i) : null, i2 != 0 ? androidx.appcompat.a.a.a.b(context, i2) : null, i3 != 0 ? androidx.appcompat.a.a.a.b(context, i3) : null, i4 != 0 ? androidx.appcompat.a.a.a.b(context, i4) : null);
        C0076w c0076w = this.d;
        if (c0076w != null) {
            c0076w.n();
        }
    }
}
