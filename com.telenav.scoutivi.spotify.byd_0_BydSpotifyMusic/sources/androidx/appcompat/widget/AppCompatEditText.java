package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.DragEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.textclassifier.TextClassifier;
import android.widget.EditText;

/* JADX INFO: loaded from: classes.dex */
public class AppCompatEditText extends EditText implements i0.r {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final d f838e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final v f839g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final u f840h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private final androidx.core.widget.j f841i;

    public AppCompatEditText(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, e.a.editTextStyle);
    }

    @Override // i0.r
    public i0.c a(i0.c cVar) {
        return this.f841i.a(this, cVar);
    }

    @Override // android.widget.TextView, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        d dVar = this.f838e;
        if (dVar != null) {
            dVar.b();
        }
        v vVar = this.f839g;
        if (vVar != null) {
            vVar.b();
        }
    }

    public ColorStateList getSupportBackgroundTintList() {
        d dVar = this.f838e;
        if (dVar != null) {
            return dVar.c();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        d dVar = this.f838e;
        if (dVar != null) {
            return dVar.d();
        }
        return null;
    }

    @Override // android.widget.TextView
    public TextClassifier getTextClassifier() {
        return super.getTextClassifier();
    }

    @Override // android.widget.TextView, android.view.View
    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        InputConnection inputConnectionOnCreateInputConnection = super.onCreateInputConnection(editorInfo);
        this.f839g.r(this, inputConnectionOnCreateInputConnection, editorInfo);
        InputConnection inputConnectionA = j.a(inputConnectionOnCreateInputConnection, editorInfo, this);
        String[] strArrB = i0.u.B(this);
        if (inputConnectionA == null || strArrB == null) {
            return inputConnectionA;
        }
        k0.a.c(editorInfo, strArrB);
        return k0.b.a(inputConnectionA, editorInfo, s.a(this));
    }

    @Override // android.widget.TextView, android.view.View
    public boolean onDragEvent(DragEvent dragEvent) {
        if (s.b(this, dragEvent)) {
            return true;
        }
        return super.onDragEvent(dragEvent);
    }

    @Override // android.widget.EditText, android.widget.TextView
    public boolean onTextContextMenuItem(int i7) {
        if (s.c(this, i7)) {
            return true;
        }
        return super.onTextContextMenuItem(i7);
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        d dVar = this.f838e;
        if (dVar != null) {
            dVar.f(drawable);
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i7) {
        super.setBackgroundResource(i7);
        d dVar = this.f838e;
        if (dVar != null) {
            dVar.g(i7);
        }
    }

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(androidx.core.widget.i.p(this, callback));
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        d dVar = this.f838e;
        if (dVar != null) {
            dVar.i(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        d dVar = this.f838e;
        if (dVar != null) {
            dVar.j(mode);
        }
    }

    @Override // android.widget.TextView
    public void setTextAppearance(Context context, int i7) {
        super.setTextAppearance(context, i7);
        v vVar = this.f839g;
        if (vVar != null) {
            vVar.q(context, i7);
        }
    }

    @Override // android.widget.TextView
    public void setTextClassifier(TextClassifier textClassifier) {
        super.setTextClassifier(textClassifier);
    }

    public AppCompatEditText(Context context, AttributeSet attributeSet, int i7) {
        super(p0.b(context), attributeSet, i7);
        n0.a(this, getContext());
        d dVar = new d(this);
        this.f838e = dVar;
        dVar.e(attributeSet, i7);
        v vVar = new v(this);
        this.f839g = vVar;
        vVar.m(attributeSet, i7);
        vVar.b();
        this.f840h = new u(this);
        this.f841i = new androidx.core.widget.j();
    }

    @Override // android.widget.EditText, android.widget.TextView
    public Editable getText() {
        return super.getText();
    }
}
