package defpackage;

import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/* JADX INFO: compiled from: ViewUtils.java */
/* JADX INFO: loaded from: classes.dex */
public final class vn3 implements Runnable {
    public final /* synthetic */ View e;

    public vn3(EditText editText) {
        this.e = editText;
    }

    @Override // java.lang.Runnable
    public final void run() {
        View view = this.e;
        ((InputMethodManager) view.getContext().getSystemService("input_method")).showSoftInput(view, 1);
    }
}
