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
    public final List<AbsBYDAutoBodyworkListener> f4474a = new ArrayList();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Handler f4475b;

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
        this.f4475b = new a(handlerThread.getLooper());
    }

    public void a(Message message) {
        if (this.f4474a.isEmpty()) {
        }
        int i8 = message.what;
        int i9 = message.arg1;
        int i10 = message.arg2;
        switch (i8) {
            case 4000:
                Iterator<AbsBYDAutoBodyworkListener> it = this.f4474a.iterator();
                while (it.hasNext()) {
                    it.next().onWindowOpenPercentChanged(i9, i10);
                }
                break;
            case 4001:
                Iterator<AbsBYDAutoBodyworkListener> it2 = this.f4474a.iterator();
                while (it2.hasNext()) {
                    it2.next().onWindowLockStateChanged(i9, i10);
                }
                break;
            case 4002:
                Iterator<AbsBYDAutoBodyworkListener> it3 = this.f4474a.iterator();
                while (it3.hasNext()) {
                    it3.next().onWindowControlStateChanged(i9, i10);
                }
                break;
        }
    }

    public void b(int i8, int i9) {
        if (this.f4475b != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = i8;
            messageObtain.arg1 = i9;
            messageObtain.arg2 = 0;
            this.f4475b.sendMessage(messageObtain);
        }
    }

    public void c(int i8, int i9, int i10) {
        if (this.f4475b != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = i8;
            messageObtain.arg1 = i9;
            messageObtain.arg2 = i10;
            this.f4475b.sendMessage(messageObtain);
        }
    }

    public void d(AbsBYDAutoBodyworkListener absBYDAutoBodyworkListener) {
        if (absBYDAutoBodyworkListener != null) {
            this.f4474a.remove(absBYDAutoBodyworkListener);
        }
    }

    public void e(AbsBYDAutoBodyworkListener absBYDAutoBodyworkListener) {
        if (absBYDAutoBodyworkListener == null || this.f4474a.contains(absBYDAutoBodyworkListener)) {
            return;
        }
        this.f4474a.add(absBYDAutoBodyworkListener);
    }
}
