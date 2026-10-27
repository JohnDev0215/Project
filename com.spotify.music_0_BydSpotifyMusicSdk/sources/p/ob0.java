package p;

import android.widget.AutoCompleteTextView;

/* JADX INFO: loaded from: classes.dex */
public final class ob0 implements AutoCompleteTextView.OnDismissListener {
    public final /* synthetic */ pb0 a;

    public ob0(pb0 pb0Var) {
        this.a = pb0Var;
    }

    @Override // android.widget.AutoCompleteTextView.OnDismissListener
    public final void onDismiss() {
        pb0 pb0Var = this.a;
        pb0Var.j = true;
        pb0Var.l = System.currentTimeMillis();
        pb0Var.f(false);
    }
}
