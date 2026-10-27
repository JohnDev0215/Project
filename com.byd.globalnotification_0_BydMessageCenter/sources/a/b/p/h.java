package a.b.p;

import a.b.k.l;
import android.R;
import android.content.Context;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.CheckedTextView;

/* JADX INFO: loaded from: classes.dex */
public class h extends CheckedTextView {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int[] f266b = {R.attr.checkMark};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final y f267a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public h(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.checkedTextViewStyle);
        u0.a(context);
        s0.a(this, getContext());
        y yVar = new y(this);
        this.f267a = yVar;
        yVar.d(attributeSet, R.attr.checkedTextViewStyle);
        this.f267a.b();
        x0 x0VarQ = x0.q(getContext(), attributeSet, f266b, R.attr.checkedTextViewStyle, 0);
        setCheckMarkDrawable(x0VarQ.g(0));
        x0VarQ.f390b.recycle();
    }

    @Override // android.widget.CheckedTextView, android.widget.TextView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        y yVar = this.f267a;
        if (yVar != null) {
            yVar.b();
        }
    }

    @Override // android.widget.TextView, android.view.View
    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        InputConnection inputConnectionOnCreateInputConnection = super.onCreateInputConnection(editorInfo);
        l.i.Z(inputConnectionOnCreateInputConnection, editorInfo, this);
        return inputConnectionOnCreateInputConnection;
    }

    @Override // android.widget.CheckedTextView
    public void setCheckMarkDrawable(int i) {
        setCheckMarkDrawable(a.b.l.a.a.b(getContext(), i));
    }

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(callback);
    }

    @Override // android.widget.TextView
    public void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        y yVar = this.f267a;
        if (yVar != null) {
            yVar.e(context, i);
        }
    }
}
