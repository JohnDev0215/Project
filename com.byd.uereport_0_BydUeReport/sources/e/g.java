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
    public final List<AbsBYDAutoEngineListener> f4489a = new ArrayList();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Handler f4490b;

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
        this.f4490b = new a(handlerThread.getLooper());
    }

    public void a(Message message) {
        if (this.f4489a.isEmpty()) {
            return;
        }
        int i8 = message.what;
        int i9 = message.arg1;
        if (i8 != 8000) {
            return;
        }
        Iterator<AbsBYDAutoEngineListener> it = this.f4489a.iterator();
        while (it.hasNext()) {
            it.next().onEngineConditionChanged(i9);
        }
    }

    public void b(int i8, int i9) {
        if (this.f4490b != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = i8;
            messageObtain.arg1 = i9;
            this.f4490b.sendEmptyMessage(i8);
        }
    }

    public void c(AbsBYDAutoEngineListener absBYDAutoEngineListener) {
        if (absBYDAutoEngineListener != null) {
            this.f4489a.remove(absBYDAutoEngineListener);
        }
    }

    public void d(AbsBYDAutoEngineListener absBYDAutoEngineListener) {
        if (absBYDAutoEngineListener == null || this.f4489a.contains(absBYDAutoEngineListener)) {
            return;
        }
        this.f4489a.add(absBYDAutoEngineListener);
    }
}
