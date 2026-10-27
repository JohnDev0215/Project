package a.b.f;

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
    public final List<AbsBYDAutoEngineListener> f29a = new ArrayList();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Handler f30b;

    /* JADX INFO: compiled from: TestEngineManagerImplCallback.java */
    public class a extends Handler {
        public a(Looper looper) {
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
        this.f30b = new a(handlerThread.getLooper());
    }

    public void a(Message message) {
        if (this.f29a.isEmpty()) {
            return;
        }
        int i = message.what;
        int i2 = message.arg1;
        if (i != 8000) {
            return;
        }
        Iterator<AbsBYDAutoEngineListener> it = this.f29a.iterator();
        while (it.hasNext()) {
            it.next().onEngineConditionChanged(i2);
        }
    }

    public void b(int i, int i2) {
        if (this.f30b != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = i;
            messageObtain.arg1 = i2;
            this.f30b.sendEmptyMessage(i);
        }
    }

    public void c(AbsBYDAutoEngineListener absBYDAutoEngineListener) {
        if (absBYDAutoEngineListener != null) {
            this.f29a.remove(absBYDAutoEngineListener);
        }
    }

    public void d(AbsBYDAutoEngineListener absBYDAutoEngineListener) {
        if (absBYDAutoEngineListener == null || this.f29a.contains(absBYDAutoEngineListener)) {
            return;
        }
        this.f29a.add(absBYDAutoEngineListener);
    }
}
