package a.a.e;

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
    private final List<AbsBYDAutoEngineListener> f15a = new ArrayList();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Handler f16b;

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
        this.f16b = new a(handlerThread.getLooper());
    }

    public void a(Message message) {
        if (this.f15a.isEmpty()) {
            return;
        }
        int i2 = message.what;
        int i3 = message.arg1;
        if (i2 != 8000) {
            return;
        }
        Iterator<AbsBYDAutoEngineListener> it = this.f15a.iterator();
        while (it.hasNext()) {
            it.next().onEngineConditionChanged(i3);
        }
    }

    public void b(int i2, int i3) {
        if (this.f16b != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = i2;
            messageObtain.arg1 = i3;
            this.f16b.sendEmptyMessage(i2);
        }
    }

    public void c(AbsBYDAutoEngineListener absBYDAutoEngineListener) {
        if (absBYDAutoEngineListener != null) {
            this.f15a.remove(absBYDAutoEngineListener);
        }
    }

    public void d(AbsBYDAutoEngineListener absBYDAutoEngineListener) {
        if (absBYDAutoEngineListener == null || this.f15a.contains(absBYDAutoEngineListener)) {
            return;
        }
        this.f15a.add(absBYDAutoEngineListener);
    }
}
