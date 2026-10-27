package androidx.appcompat.widget;

import android.R;
import android.content.Context;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.CheckedTextView;

/* JADX INFO: loaded from: classes.dex */
public class g extends CheckedTextView {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final int[] f934b = {R.attr.checkMark};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final x f935c;

    public g(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.checkedTextViewStyle);
    }

    public g(Context context, AttributeSet attributeSet, int i) {
        super(s0.b(context), attributeSet, i);
        q0.a(this, getContext());
        x xVar = new x(this);
        this.f935c = xVar;
        xVar.m(attributeSet, i);
        xVar.b();
        v0 v0VarV = v0.v(getContext(), attributeSet, f934b, i, 0);
        setCheckMarkDrawable(v0VarV.g(0));
        v0VarV.w();
    }

    @Override // android.widget.CheckedTextView, android.widget.TextView, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        x xVar = this.f935c;
        if (xVar != null) {
            xVar.b();
        }
    }

    @Override // android.widget.TextView, android.view.View
    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        return k.a(super.onCreateInputConnection(editorInfo), editorInfo, this);
    }

    @Override // android.widget.CheckedTextView
    public void setCheckMarkDrawable(int i) {
        setCheckMarkDrawable(a.a.k.a.a.d(getContext(), i));
    }

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(androidx.core.widget.i.s(this, callback));
    }

    @Override // android.widget.TextView
    public void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        x xVar = this.f935c;
        if (xVar != null) {
            xVar.q(context, i);
        }
    }
}
