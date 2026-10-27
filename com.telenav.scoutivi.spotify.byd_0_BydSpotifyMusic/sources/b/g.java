package b;

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
    private final List<AbsBYDAutoEngineListener> f3441a = new ArrayList();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Handler f3442b;

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
        this.f3442b = new a(handlerThread.getLooper());
    }

    public void a(Message message) {
        if (this.f3441a.isEmpty()) {
            return;
        }
        int i7 = message.what;
        int i8 = message.arg1;
        if (i7 != 8000) {
            return;
        }
        Iterator<AbsBYDAutoEngineListener> it = this.f3441a.iterator();
        while (it.hasNext()) {
            it.next().onEngineConditionChanged(i8);
        }
    }

    public void b(int i7, int i8) {
        if (this.f3442b != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = i7;
            messageObtain.arg1 = i8;
            this.f3442b.sendEmptyMessage(i7);
        }
    }

    public void c(AbsBYDAutoEngineListener absBYDAutoEngineListener) {
        if (absBYDAutoEngineListener != null) {
            this.f3441a.remove(absBYDAutoEngineListener);
        }
    }

    public void d(AbsBYDAutoEngineListener absBYDAutoEngineListener) {
        if (absBYDAutoEngineListener == null || this.f3441a.contains(absBYDAutoEngineListener)) {
            return;
        }
        this.f3441a.add(absBYDAutoEngineListener);
    }
}
