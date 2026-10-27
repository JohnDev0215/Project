package spi;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/* JADX INFO: loaded from: classes.dex */
public final class hn {
    public boolean a;
    public final Handler b = new Handler(Looper.getMainLooper(), new a());

    public static final class a implements Handler.Callback {
        @Override // android.os.Handler.Callback
        public final boolean handleMessage(Message message) {
            if (message.what != 1) {
                return false;
            }
            ((wm) message.obj).recycle();
            return true;
        }
    }

    public final synchronized void a(wm<?> wmVar, boolean z) {
        if (this.a || z) {
            this.b.obtainMessage(1, wmVar).sendToTarget();
        } else {
            this.a = true;
            wmVar.recycle();
            this.a = false;
        }
    }
}
