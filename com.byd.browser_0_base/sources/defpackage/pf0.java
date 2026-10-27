package defpackage;

import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

/* JADX INFO: compiled from: EditorDialog.java */
/* JADX INFO: loaded from: classes.dex */
public final class pf0 implements TextView.OnEditorActionListener {
    public final /* synthetic */ tf0 e;

    public pf0(tf0 tf0Var) {
        this.e = tf0Var;
    }

    @Override // android.widget.TextView.OnEditorActionListener
    public final boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        View viewFocusSearch;
        if (i == 6) {
            this.e.r.performClick();
            return true;
        }
        if (i != 5 || (viewFocusSearch = textView.focusSearch(2)) == null) {
            return false;
        }
        viewFocusSearch.requestFocus();
        return true;
    }
}
