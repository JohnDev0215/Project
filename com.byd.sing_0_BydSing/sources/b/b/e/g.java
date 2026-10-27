package b.b.e;

import android.hardware.bydauto.engine.AbsBYDAutoEngineListener;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: TestEngineManagerImplCallback.java */
/* JADX INFO: loaded from: classes.dex */
public class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final List<AbsBYDAutoEngineListener> f1941a = new ArrayList();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Handler f1942b;

    /* JADX INFO: compiled from: TestEngineManagerImplCallback.java */
    class a extends Handler {
        a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            g.this.a(message);
        }
    }

    public g() {
        HandlerThread handlerThread = new HandlerThread("TestEngineManagerImplCallback");
        handlerThread.start();
        this.f1942b = new a(handlerThread.getLooper());
    }

    public void a(Message message) {
        if (this.f1941a.isEmpty()) {
            return;
        }
        int i = message.what;
        int i2 = message.arg1;
        if (i != 8000) {
            return;
        }
        Iterator<AbsBYDAutoEngineListener> it = this.f1941a.iterator();
        while (it.hasNext()) {
            it.next().onEngineConditionChanged(i2);
        }
    }

    public void b(int i, int i2) {
        if (this.f1942b != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = i;
            messageObtain.arg1 = i2;
            this.f1942b.sendEmptyMessage(i);
        }
    }

    public void c(AbsBYDAutoEngineListener absBYDAutoEngineListener) {
        if (absBYDAutoEngineListener != null) {
            this.f1941a.remove(absBYDAutoEngineListener);
        }
    }

    public void d(AbsBYDAutoEngineListener absBYDAutoEngineListener) {
        if (absBYDAutoEngineListener == null || this.f1941a.contains(absBYDAutoEngineListener)) {
            return;
        }
        this.f1941a.add(absBYDAutoEngineListener);
    }
}
