package a.a.f;

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
    public final List<AbsBYDAutoEngineListener> f27a = new ArrayList();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Handler f28b;

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
        this.f28b = new a(handlerThread.getLooper());
    }

    public void a(Message message) {
        if (this.f27a.isEmpty()) {
            return;
        }
        int i = message.what;
        int i2 = message.arg1;
        if (i != 8000) {
            return;
        }
        Iterator<AbsBYDAutoEngineListener> it = this.f27a.iterator();
        while (it.hasNext()) {
            it.next().onEngineConditionChanged(i2);
        }
    }

    public void b(int i, int i2) {
        if (this.f28b != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = i;
            messageObtain.arg1 = i2;
            this.f28b.sendEmptyMessage(i);
        }
    }

    public void c(AbsBYDAutoEngineListener absBYDAutoEngineListener) {
        if (absBYDAutoEngineListener != null) {
            this.f27a.remove(absBYDAutoEngineListener);
        }
    }

    public void d(AbsBYDAutoEngineListener absBYDAutoEngineListener) {
        if (absBYDAutoEngineListener == null || this.f27a.contains(absBYDAutoEngineListener)) {
            return;
        }
        this.f27a.add(absBYDAutoEngineListener);
    }
}
