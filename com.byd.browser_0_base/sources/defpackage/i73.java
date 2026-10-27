package defpackage;

import android.text.Editable;
import android.text.TextWatcher;

/* JADX INFO: compiled from: TasksSurfaceMediator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class i73 implements TextWatcher {
    public final /* synthetic */ m73 e;

    public i73(m73 m73Var) {
        this.e = m73Var;
    }

    @Override // android.text.TextWatcher
    public final void afterTextChanged(Editable editable) {
        if (editable.length() == 0) {
            return;
        }
        this.e.e.s(editable.toString(), 7, true);
        n3.X0("TasksSurface.FakeBox.LongPressed");
        editable.clear();
    }

    @Override // android.text.TextWatcher
    public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    @Override // android.text.TextWatcher
    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }
}
