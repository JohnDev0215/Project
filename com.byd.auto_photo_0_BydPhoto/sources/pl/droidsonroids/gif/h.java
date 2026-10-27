package pl.droidsonroids.gif;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import androidx.annotation.NonNull;
import java.lang.ref.WeakReference;
import java.util.Iterator;

/* JADX INFO: loaded from: classes2.dex */
class h extends Handler {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final WeakReference<c> f3200a;

    h(c cVar) {
        super(Looper.getMainLooper());
        this.f3200a = new WeakReference<>(cVar);
    }

    @Override // android.os.Handler
    public void handleMessage(@NonNull Message message) {
        c cVar = this.f3200a.get();
        if (cVar == null) {
            return;
        }
        if (message.what == -1) {
            cVar.invalidateSelf();
            return;
        }
        Iterator<a> it = cVar.h.iterator();
        while (it.hasNext()) {
            it.next().a(message.what);
        }
    }
}
