package androidx.appcompat.widget;

import android.R;
import android.content.Context;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.CheckedTextView;
import j.a;
import n0.i;
import o.b0;
import o.e0;
import o.g;
import o.m;
import o.z;

/* JADX INFO: loaded from: classes.dex */
public class AppCompatCheckedTextView extends CheckedTextView {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int[] f452b = {R.attr.checkMark};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final m f453c;

    public AppCompatCheckedTextView(Context context) {
        this(context, null);
    }

    @Override // android.widget.CheckedTextView, android.widget.TextView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        m mVar = this.f453c;
        if (mVar != null) {
            mVar.b();
        }
    }

    @Override // android.widget.TextView, android.view.View
    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        return g.a(super.onCreateInputConnection(editorInfo), editorInfo, this);
    }

    @Override // android.widget.CheckedTextView
    public void setCheckMarkDrawable(int i3) {
        setCheckMarkDrawable(a.d(getContext(), i3));
    }

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(i.s(this, callback));
    }

    @Override // android.widget.TextView
    public void setTextAppearance(Context context, int i3) {
        super.setTextAppearance(context, i3);
        m mVar = this.f453c;
        if (mVar != null) {
            mVar.q(context, i3);
        }
    }

    public AppCompatCheckedTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.checkedTextViewStyle);
    }

    public AppCompatCheckedTextView(Context context, AttributeSet attributeSet, int i3) {
        super(b0.b(context), attributeSet, i3);
        z.a(this, getContext());
        m mVar = new m(this);
        this.f453c = mVar;
        mVar.m(attributeSet, i3);
        mVar.b();
        e0 e0VarV = e0.v(getContext(), attributeSet, f452b, i3, 0);
        setCheckMarkDrawable(e0VarV.g(0));
        e0VarV.w();
    }
}
