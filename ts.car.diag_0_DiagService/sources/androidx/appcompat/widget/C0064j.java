package androidx.appcompat.widget;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Editable;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.textclassifier.TextClassifier;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.appcompat.R$attr;
import androidx.core.widget.TextViewCompat;

/* JADX INFO: renamed from: androidx.appcompat.widget.j, reason: case insensitive filesystem */
/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class C0064j extends EditText implements androidx.core.view.r {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final C0058d f370c;
    private final C0076w d;
    private final C0075v e;

    @Nullable
    private androidx.core.widget.i<TextView> f;

    public C0064j(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.editTextStyle);
    }

    @Override // androidx.core.view.r
    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public PorterDuff.Mode c() {
        C0058d c0058d = this.f370c;
        if (c0058d != null) {
            return c0058d.d();
        }
        return null;
    }

    @Override // android.widget.TextView, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        C0058d c0058d = this.f370c;
        if (c0058d != null) {
            c0058d.b();
        }
        C0076w c0076w = this.d;
        if (c0076w != null) {
            c0076w.b();
        }
    }

    @Override // androidx.core.view.r
    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public ColorStateList f() {
        C0058d c0058d = this.f370c;
        if (c0058d != null) {
            return c0058d.c();
        }
        return null;
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
        C0058d c0058d = this.f370c;
        if (c0058d != null) {
            c0058d.j(mode);
        }
    }

    @Override // androidx.core.view.r
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public void i(@Nullable ColorStateList colorStateList) {
        C0058d c0058d = this.f370c;
        if (c0058d != null) {
            c0058d.i(colorStateList);
        }
    }

    @Override // android.widget.TextView, android.view.View
    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        androidx.core.widget.i<TextView> iVar;
        InputConnection inputConnectionOnCreateInputConnection = super.onCreateInputConnection(editorInfo);
        C0065k.a(inputConnectionOnCreateInputConnection, editorInfo, this);
        if (inputConnectionOnCreateInputConnection == null || (iVar = this.f) == null) {
            return inputConnectionOnCreateInputConnection;
        }
        iVar.b(inputConnectionOnCreateInputConnection, editorInfo);
        throw null;
    }

    @Override // android.widget.EditText, android.widget.TextView
    public boolean onTextContextMenuItem(int i) {
        if (this.f == null) {
            return super.onTextContextMenuItem(i);
        }
        if (i != 16908322 && i != 16908337) {
            return super.onTextContextMenuItem(i);
        }
        ClipboardManager clipboardManager = (ClipboardManager) getContext().getSystemService("clipboard");
        ClipData primaryClip = clipboardManager == null ? null : clipboardManager.getPrimaryClip();
        if (primaryClip != null) {
            this.f.a(this, primaryClip, 0, i == 16908322 ? 0 : 1);
        }
        return true;
    }

    @Override // android.view.View
    public void setBackgroundDrawable(@Nullable Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        C0058d c0058d = this.f370c;
        if (c0058d != null) {
            c0058d.f(drawable);
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(@DrawableRes int i) {
        super.setBackgroundResource(i);
        C0058d c0058d = this.f370c;
        if (c0058d != null) {
            c0058d.g(i);
        }
    }

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(TextViewCompat.k(this, callback));
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

    public C0064j(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(Q.b(context), attributeSet, i);
        O.a(this, getContext());
        C0058d c0058d = new C0058d(this);
        this.f370c = c0058d;
        c0058d.e(attributeSet, i);
        C0076w c0076w = new C0076w(this);
        this.d = c0076w;
        c0076w.k(attributeSet, i);
        this.d.b();
        this.e = new C0075v(this);
    }

    @Override // android.widget.EditText, android.widget.TextView
    @Nullable
    public Editable getText() {
        return Build.VERSION.SDK_INT >= 28 ? super.getText() : super.getEditableText();
    }
}
