package defpackage;

import android.content.DialogInterface;
import android.widget.EditText;

/* JADX INFO: compiled from: AddExceptionPreference.java */
/* JADX INFO: loaded from: classes2.dex */
public final class r3 implements DialogInterface.OnShowListener {
    public final /* synthetic */ EditText e;

    public r3(EditText editText) {
        this.e = editText;
    }

    @Override // android.content.DialogInterface.OnShowListener
    public final void onShow(DialogInterface dialogInterface) {
        t41.f.i(this.e);
    }
}
