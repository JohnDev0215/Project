package b;

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
    private final List<AbsBYDAutoBodyworkListener> f3426a = new ArrayList();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Handler f3427b;

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
        this.f3427b = new a(handlerThread.getLooper());
    }

    public void a(Message message) {
        if (this.f3426a.isEmpty()) {
        }
        int i7 = message.what;
        int i8 = message.arg1;
        int i9 = message.arg2;
        switch (i7) {
            case 4000:
                Iterator<AbsBYDAutoBodyworkListener> it = this.f3426a.iterator();
                while (it.hasNext()) {
                    it.next().onWindowOpenPercentChanged(i8, i9);
                }
                break;
            case 4001:
                Iterator<AbsBYDAutoBodyworkListener> it2 = this.f3426a.iterator();
                while (it2.hasNext()) {
                    it2.next().onWindowLockStateChanged(i8, i9);
                }
                break;
            case 4002:
                Iterator<AbsBYDAutoBodyworkListener> it3 = this.f3426a.iterator();
                while (it3.hasNext()) {
                    it3.next().onWindowControlStateChanged(i8, i9);
                }
                break;
        }
    }

    public void b(int i7, int i8) {
        if (this.f3427b != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = i7;
            messageObtain.arg1 = i8;
            messageObtain.arg2 = 0;
            this.f3427b.sendMessage(messageObtain);
        }
    }

    public void c(int i7, int i8, int i9) {
        if (this.f3427b != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = i7;
            messageObtain.arg1 = i8;
            messageObtain.arg2 = i9;
            this.f3427b.sendMessage(messageObtain);
        }
    }

    public void d(AbsBYDAutoBodyworkListener absBYDAutoBodyworkListener) {
        if (absBYDAutoBodyworkListener != null) {
            this.f3426a.remove(absBYDAutoBodyworkListener);
        }
    }

    public void e(AbsBYDAutoBodyworkListener absBYDAutoBodyworkListener) {
        if (absBYDAutoBodyworkListener == null || this.f3426a.contains(absBYDAutoBodyworkListener)) {
            return;
        }
        this.f3426a.add(absBYDAutoBodyworkListener);
    }
}
