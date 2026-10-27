package e;

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
    public final List<AbsBYDAutoEngineListener> f2761a = new ArrayList();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Handler f2762b;

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
        this.f2762b = new a(handlerThread.getLooper());
    }

    public void a(Message message) {
        if (this.f2761a.isEmpty()) {
            return;
        }
        int i3 = message.what;
        int i4 = message.arg1;
        if (i3 != 8000) {
            return;
        }
        Iterator<AbsBYDAutoEngineListener> it = this.f2761a.iterator();
        while (it.hasNext()) {
            it.next().onEngineConditionChanged(i4);
        }
    }

    public void b(int i3, int i4) {
        if (this.f2762b != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = i3;
            messageObtain.arg1 = i4;
            this.f2762b.sendEmptyMessage(i3);
        }
    }

    public void c(AbsBYDAutoEngineListener absBYDAutoEngineListener) {
        if (absBYDAutoEngineListener != null) {
            this.f2761a.remove(absBYDAutoEngineListener);
        }
    }

    public void d(AbsBYDAutoEngineListener absBYDAutoEngineListener) {
        if (absBYDAutoEngineListener == null || this.f2761a.contains(absBYDAutoEngineListener)) {
            return;
        }
        this.f2761a.add(absBYDAutoEngineListener);
    }
}
