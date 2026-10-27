package V1;

import android.widget.EditText;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public final class g extends T1.g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final WeakReference f9323a;

    public g(EditText editText) {
        this.f9323a = new WeakReference(editText);
    }

    @Override // T1.g
    public final void b() {
        h.a((EditText) this.f9323a.get(), 1);
    }
}
