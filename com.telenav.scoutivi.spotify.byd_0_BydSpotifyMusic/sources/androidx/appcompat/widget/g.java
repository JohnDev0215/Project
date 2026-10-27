package androidx.appcompat.widget;

import android.R;
import android.content.Context;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.CheckedTextView;

/* JADX INFO: compiled from: AppCompatCheckedTextView.java */
/* JADX INFO: loaded from: classes.dex */
public class g extends CheckedTextView {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static final int[] f1068g = {R.attr.checkMark};

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final v f1069e;

    public g(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.checkedTextViewStyle);
    }

    @Override // android.widget.CheckedTextView, android.widget.TextView, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        v vVar = this.f1069e;
        if (vVar != null) {
            vVar.b();
        }
    }

    @Override // android.widget.TextView, android.view.View
    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        return j.a(super.onCreateInputConnection(editorInfo), editorInfo, this);
    }

    @Override // android.widget.CheckedTextView
    public void setCheckMarkDrawable(int i7) {
        setCheckMarkDrawable(f.a.b(getContext(), i7));
    }

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(androidx.core.widget.i.p(this, callback));
    }

    @Override // android.widget.TextView
    public void setTextAppearance(Context context, int i7) {
        super.setTextAppearance(context, i7);
        v vVar = this.f1069e;
        if (vVar != null) {
            vVar.q(context, i7);
        }
    }

    public g(Context context, AttributeSet attributeSet, int i7) {
        super(p0.b(context), attributeSet, i7);
        n0.a(this, getContext());
        v vVar = new v(this);
        this.f1069e = vVar;
        vVar.m(attributeSet, i7);
        vVar.b();
        s0 s0VarV = s0.v(getContext(), attributeSet, f1068g, i7, 0);
        setCheckMarkDrawable(s0VarV.g(0));
        s0VarV.w();
    }
}
