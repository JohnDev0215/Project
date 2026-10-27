package e;

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
    public final List<AbsBYDAutoBodyworkListener> f2746a = new ArrayList();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Handler f2747b;

    /* JADX INFO: compiled from: TestBodyworkManagerImplCallback.java */
    public class a extends Handler {
        public a(Looper looper) {
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
        this.f2747b = new a(handlerThread.getLooper());
    }

    public void a(Message message) {
        if (this.f2746a.isEmpty()) {
        }
        int i3 = message.what;
        int i4 = message.arg1;
        int i5 = message.arg2;
        switch (i3) {
            case 4000:
                Iterator<AbsBYDAutoBodyworkListener> it = this.f2746a.iterator();
                while (it.hasNext()) {
                    it.next().onWindowOpenPercentChanged(i4, i5);
                }
                break;
            case 4001:
                Iterator<AbsBYDAutoBodyworkListener> it2 = this.f2746a.iterator();
                while (it2.hasNext()) {
                    it2.next().onWindowLockStateChanged(i4, i5);
                }
                break;
            case 4002:
                Iterator<AbsBYDAutoBodyworkListener> it3 = this.f2746a.iterator();
                while (it3.hasNext()) {
                    it3.next().onWindowControlStateChanged(i4, i5);
                }
                break;
        }
    }

    public void b(int i3, int i4) {
        if (this.f2747b != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = i3;
            messageObtain.arg1 = i4;
            messageObtain.arg2 = 0;
            this.f2747b.sendMessage(messageObtain);
        }
    }

    public void c(int i3, int i4, int i5) {
        if (this.f2747b != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = i3;
            messageObtain.arg1 = i4;
            messageObtain.arg2 = i5;
            this.f2747b.sendMessage(messageObtain);
        }
    }

    public void d(AbsBYDAutoBodyworkListener absBYDAutoBodyworkListener) {
        if (absBYDAutoBodyworkListener != null) {
            this.f2746a.remove(absBYDAutoBodyworkListener);
        }
    }

    public void e(AbsBYDAutoBodyworkListener absBYDAutoBodyworkListener) {
        if (absBYDAutoBodyworkListener == null || this.f2746a.contains(absBYDAutoBodyworkListener)) {
            return;
        }
        this.f2746a.add(absBYDAutoBodyworkListener);
    }
}
