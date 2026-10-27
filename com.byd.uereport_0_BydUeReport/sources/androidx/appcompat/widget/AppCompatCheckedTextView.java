package androidx.appcompat.widget;

import android.R;
import android.content.Context;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.CheckedTextView;
import m.a;
import r.b0;
import r.e0;
import r.g;
import r.m;
import r.z;
import r0.i;

/* JADX INFO: loaded from: classes.dex */
public class AppCompatCheckedTextView extends CheckedTextView {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final int[] f553d = {R.attr.checkMark};

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final m f554e;

    public AppCompatCheckedTextView(Context context) {
        this(context, null);
    }

    @Override // android.widget.CheckedTextView, android.widget.TextView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        m mVar = this.f554e;
        if (mVar != null) {
            mVar.b();
        }
    }

    @Override // android.widget.TextView, android.view.View
    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        return g.a(super.onCreateInputConnection(editorInfo), editorInfo, this);
    }

    @Override // android.widget.CheckedTextView
    public void setCheckMarkDrawable(int i8) {
        setCheckMarkDrawable(a.d(getContext(), i8));
    }

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(i.s(this, callback));
    }

    @Override // android.widget.TextView
    public void setTextAppearance(Context context, int i8) {
        super.setTextAppearance(context, i8);
        m mVar = this.f554e;
        if (mVar != null) {
            mVar.q(context, i8);
        }
    }

    public AppCompatCheckedTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.checkedTextViewStyle);
    }

    public AppCompatCheckedTextView(Context context, AttributeSet attributeSet, int i8) {
        super(b0.b(context), attributeSet, i8);
        z.a(this, getContext());
        m mVar = new m(this);
        this.f554e = mVar;
        mVar.m(attributeSet, i8);
        mVar.b();
        e0 e0VarV = e0.v(getContext(), attributeSet, f553d, i8, 0);
        setCheckMarkDrawable(e0VarV.g(0));
        e0VarV.w();
    }
}
