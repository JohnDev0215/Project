package androidx.appcompat.widget;

import android.R;
import android.content.Context;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.CheckedTextView;
import defpackage.b3;
import defpackage.d3;
import defpackage.g3;
import defpackage.i2;
import defpackage.o2;
import defpackage.qa;
import defpackage.u0;

/* JADX INFO: loaded from: classes.dex */
public class AppCompatCheckedTextView extends CheckedTextView {
    public static final int[] b = {R.attr.checkMark};
    public final o2 c;

    public AppCompatCheckedTextView(Context context) {
        this(context, null);
    }

    @Override // android.widget.CheckedTextView, android.widget.TextView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        o2 o2Var = this.c;
        if (o2Var != null) {
            o2Var.b();
        }
    }

    @Override // android.widget.TextView, android.view.View
    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        return i2.a(super.onCreateInputConnection(editorInfo), editorInfo, this);
    }

    @Override // android.widget.CheckedTextView
    public void setCheckMarkDrawable(int i) {
        setCheckMarkDrawable(u0.d(getContext(), i));
    }

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(qa.s(this, callback));
    }

    @Override // android.widget.TextView
    public void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        o2 o2Var = this.c;
        if (o2Var != null) {
            o2Var.q(context, i);
        }
    }

    public AppCompatCheckedTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.checkedTextViewStyle);
    }

    public AppCompatCheckedTextView(Context context, AttributeSet attributeSet, int i) {
        super(d3.b(context), attributeSet, i);
        b3.a(this, getContext());
        o2 o2Var = new o2(this);
        this.c = o2Var;
        o2Var.m(attributeSet, i);
        o2Var.b();
        g3 g3VarV = g3.v(getContext(), attributeSet, b, i, 0);
        setCheckMarkDrawable(g3VarV.g(0));
        g3VarV.w();
    }
}
