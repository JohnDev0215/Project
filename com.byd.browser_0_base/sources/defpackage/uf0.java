package defpackage;

import android.util.Pair;
import android.view.View;
import android.widget.AdapterView;
import defpackage.tf0;
import org.chromium.base.Callback;

/* JADX INFO: compiled from: EditorDropdownField.java */
/* JADX INFO: loaded from: classes.dex */
public final class uf0 implements AdapterView.OnItemSelectedListener {
    public final /* synthetic */ Runnable e;
    public final /* synthetic */ wf0 f;

    public uf0(wf0 wf0Var, tf0.b bVar) {
        this.f = wf0Var;
        this.e = bVar;
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public final void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
        String str;
        wf0 wf0Var = this.f;
        if (wf0Var.k != i) {
            CharSequence charSequence = (CharSequence) wf0Var.l.getItem(i);
            xf0 xf0Var = wf0Var.f;
            if (charSequence == null) {
                xf0Var.getClass();
                str = null;
            } else {
                str = xf0Var.d.get(charSequence.toString());
            }
            CharSequence charSequence2 = xf0Var.o;
            if (charSequence2 != null && i == 0) {
                str = null;
            }
            wf0Var.k = i;
            if (str != null || charSequence2 != null) {
                xf0Var.n = str;
                Callback<Pair<String, Runnable>> callback = xf0Var.p;
                if (callback != null) {
                    callback.onResult(new Pair<>(str, this.e));
                }
            }
            xf0Var.k = null;
            wf0Var.c(false);
        }
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public final void onNothingSelected(AdapterView<?> adapterView) {
    }
}
