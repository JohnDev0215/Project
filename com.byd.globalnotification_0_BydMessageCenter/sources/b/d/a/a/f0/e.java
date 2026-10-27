package b.d.a.a.f0;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public class e {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static e f1889c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f1890a = new Object();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Handler f1891b = new Handler(Looper.getMainLooper(), new a());

    public class a implements Handler.Callback {
        public a() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            if (message.what != 0) {
                return false;
            }
            e eVar = e.this;
            c cVar = (c) message.obj;
            synchronized (eVar.f1890a) {
                if (cVar == null) {
                    b bVar = cVar.f1893a.get();
                    if (bVar != null) {
                        eVar.f1891b.removeCallbacksAndMessages(cVar);
                        bVar.a(2);
                    }
                }
            }
            return true;
        }
    }

    public interface b {
        void a(int i);
    }

    public static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final WeakReference<b> f1893a;
    }
}
