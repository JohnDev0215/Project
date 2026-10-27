package c.a.d.m;

import android.content.DialogInterface;
import androidx.fragment.app.DialogFragment;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: OnDismissListenerImp.java */
/* JADX INFO: loaded from: classes.dex */
public class c implements DialogInterface.OnDismissListener {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public DialogFragment f3575b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public WeakReference<DialogFragment> f3576c;

    public c(DialogFragment dialogFragment) {
        this.f3575b = dialogFragment;
        this.f3576c = new WeakReference<>(dialogFragment);
    }

    public void a() {
        this.f3576c.clear();
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        if (this.f3576c.get() != null) {
            this.f3576c.get().onDismiss(dialogInterface);
        }
    }
}
