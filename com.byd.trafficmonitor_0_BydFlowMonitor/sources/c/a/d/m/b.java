package c.a.d.m;

import android.content.DialogInterface;
import androidx.fragment.app.DialogFragment;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: OnCancelListenerImp.java */
/* JADX INFO: loaded from: classes.dex */
public class b implements DialogInterface.OnCancelListener {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public DialogFragment f3573b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public WeakReference<DialogFragment> f3574c;

    public b(DialogFragment dialogFragment) {
        this.f3573b = dialogFragment;
        this.f3574c = new WeakReference<>(dialogFragment);
    }

    public void a() {
        this.f3574c.clear();
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialogInterface) {
        if (this.f3574c.get() != null) {
            this.f3574c.get().onCancel(dialogInterface);
        }
    }
}
