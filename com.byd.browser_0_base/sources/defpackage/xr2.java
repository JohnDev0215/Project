package defpackage;

import android.text.Editable;
import android.text.TextWatcher;

/* JADX INFO: compiled from: StartSurfaceMediator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class xr2 implements TextWatcher {
    public final /* synthetic */ yr2 e;

    public xr2(yr2 yr2Var) {
        this.e = yr2Var;
    }

    @Override // android.text.TextWatcher
    public final void afterTextChanged(Editable editable) {
        if (editable.length() == 0) {
            return;
        }
        this.e.z.s(editable.toString(), 7, true);
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
