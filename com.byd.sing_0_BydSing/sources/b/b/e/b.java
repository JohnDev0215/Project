package b.b.e;

import android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: TestBodyworkManagerImplCallback.java */
/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final List<AbsBYDAutoBodyworkListener> f1926a = new ArrayList();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Handler f1927b;

    /* JADX INFO: compiled from: TestBodyworkManagerImplCallback.java */
    class a extends Handler {
        a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            b.this.a(message);
        }
    }

    public b() {
        HandlerThread handlerThread = new HandlerThread("TestBodyworkManagerImplCallback");
        handlerThread.start();
        this.f1927b = new a(handlerThread.getLooper());
    }

    public void a(Message message) {
        if (this.f1926a.isEmpty()) {
        }
        int i = message.what;
        int i2 = message.arg1;
        int i3 = message.arg2;
        switch (i) {
            case 4000:
                Iterator<AbsBYDAutoBodyworkListener> it = this.f1926a.iterator();
                while (it.hasNext()) {
                    it.next().onWindowOpenPercentChanged(i2, i3);
                }
                break;
            case 4001:
                Iterator<AbsBYDAutoBodyworkListener> it2 = this.f1926a.iterator();
                while (it2.hasNext()) {
                    it2.next().onWindowLockStateChanged(i2, i3);
                }
                break;
            case 4002:
                Iterator<AbsBYDAutoBodyworkListener> it3 = this.f1926a.iterator();
                while (it3.hasNext()) {
                    it3.next().onWindowControlStateChanged(i2, i3);
                }
                break;
        }
    }

    public void b(int i, int i2) {
        if (this.f1927b != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = i;
            messageObtain.arg1 = i2;
            messageObtain.arg2 = 0;
            this.f1927b.sendMessage(messageObtain);
        }
    }

    public void c(int i, int i2, int i3) {
        if (this.f1927b != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = i;
            messageObtain.arg1 = i2;
            messageObtain.arg2 = i3;
            this.f1927b.sendMessage(messageObtain);
        }
    }

    public void d(AbsBYDAutoBodyworkListener absBYDAutoBodyworkListener) {
        if (absBYDAutoBodyworkListener != null) {
            this.f1926a.remove(absBYDAutoBodyworkListener);
        }
    }

    public void e(AbsBYDAutoBodyworkListener absBYDAutoBodyworkListener) {
        if (absBYDAutoBodyworkListener == null || this.f1926a.contains(absBYDAutoBodyworkListener)) {
            return;
        }
        this.f1926a.add(absBYDAutoBodyworkListener);
    }
}
