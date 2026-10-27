package q1;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/* JADX INFO: compiled from: ResourceRecycler.java */
/* JADX INFO: loaded from: classes.dex */
class y {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private boolean f9135a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Handler f9136b = new Handler(Looper.getMainLooper(), new a());

    /* JADX INFO: compiled from: ResourceRecycler.java */
    private static final class a implements Handler.Callback {
        a() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            if (message.what != 1) {
                return false;
            }
            ((v) message.obj).a();
            return true;
        }
    }

    y() {
    }

    synchronized void a(v<?> vVar) {
        if (this.f9135a) {
            this.f9136b.obtainMessage(1, vVar).sendToTarget();
        } else {
            this.f9135a = true;
            vVar.a();
            this.f9135a = false;
        }
    }
}
