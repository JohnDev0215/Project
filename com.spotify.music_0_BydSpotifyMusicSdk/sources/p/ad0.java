package p;

import android.widget.EditText;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public final class ad0 extends hc0 {
    public final WeakReference a;

    public ad0(EditText editText) {
        this.a = new WeakReference(editText);
    }

    @Override // p.hc0
    public final void a() {
        bd0.a((EditText) this.a.get(), 1);
    }
}
