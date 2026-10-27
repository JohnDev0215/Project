package b.a;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import androidx.annotation.NonNull;
import com.byd.util.w;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public abstract class d<T> extends Handler {
    private WeakReference<T> obj;

    public d(T t) {
        super(Looper.getMainLooper());
        this.obj = new WeakReference<>(t);
    }

    @Override // android.os.Handler
    public final void handleMessage(@NonNull Message message) {
        T t;
        WeakReference<T> weakReference = this.obj;
        if (weakReference == null || (t = weakReference.get()) == null) {
            return;
        }
        try {
            handleMessage(t, message);
        } catch (Exception e) {
            e.printStackTrace();
            w.c("WeakReferenceHandler handleMessage exception : " + e.getMessage());
        }
    }

    public abstract void handleMessage(T t, Message message);

    public void release() {
        WeakReference<T> weakReference = this.obj;
        if (weakReference != null) {
            weakReference.clear();
            this.obj = null;
        }
    }
}
