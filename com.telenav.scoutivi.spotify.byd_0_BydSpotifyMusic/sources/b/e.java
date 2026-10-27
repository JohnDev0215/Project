package b;

import android.hardware.bydauto.doorlock.AbsBYDAutoDoorLockListener;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;

/* JADX INFO: compiled from: TestDoorlockManagerImplCallback.java */
/* JADX INFO: loaded from: classes.dex */
public class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private AbsBYDAutoDoorLockListener f3435a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Handler f3436b;

    /* JADX INFO: compiled from: TestDoorlockManagerImplCallback.java */
    class a extends Handler {
        a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            e.this.a(message);
        }
    }

    public e() {
        HandlerThread handlerThread = new HandlerThread("TestDoorlockManagerImplCallback");
        handlerThread.start();
        this.f3436b = new a(handlerThread.getLooper());
    }

    public void a(Message message) {
        AbsBYDAutoDoorLockListener absBYDAutoDoorLockListener = this.f3435a;
        if (absBYDAutoDoorLockListener != null) {
            int i7 = message.what;
            int i8 = message.arg1;
            int i9 = message.arg2;
            if (i7 != 32000) {
                return;
            }
            absBYDAutoDoorLockListener.onDoorLockStatusChanged(i8, i9);
        }
    }

    public void b(int i7, int i8, int i9) {
        if (this.f3436b != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = i7;
            messageObtain.arg1 = i8;
            messageObtain.arg2 = i9;
            this.f3436b.sendMessage(messageObtain);
        }
    }

    public void c(AbsBYDAutoDoorLockListener absBYDAutoDoorLockListener) {
        if (absBYDAutoDoorLockListener == null) {
            return;
        }
        this.f3435a = absBYDAutoDoorLockListener;
    }
}
