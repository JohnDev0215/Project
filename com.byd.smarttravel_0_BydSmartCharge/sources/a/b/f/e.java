package a.b.f;

import android.hardware.bydauto.doorlock.AbsBYDAutoDoorLockListener;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;

/* JADX INFO: compiled from: TestDoorlockManagerImplCallback.java */
/* JADX INFO: loaded from: classes.dex */
public class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public AbsBYDAutoDoorLockListener f23a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Handler f24b;

    /* JADX INFO: compiled from: TestDoorlockManagerImplCallback.java */
    public class a extends Handler {
        public a(Looper looper) {
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
        this.f24b = new a(handlerThread.getLooper());
    }

    public void a(Message message) {
        AbsBYDAutoDoorLockListener absBYDAutoDoorLockListener = this.f23a;
        if (absBYDAutoDoorLockListener != null) {
            int i = message.what;
            int i2 = message.arg1;
            int i3 = message.arg2;
            if (i != 32000) {
                return;
            }
            absBYDAutoDoorLockListener.onDoorLockStatusChanged(i2, i3);
        }
    }

    public void b(int i, int i2, int i3) {
        if (this.f24b != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = i;
            messageObtain.arg1 = i2;
            messageObtain.arg2 = i3;
            this.f24b.sendMessage(messageObtain);
        }
    }

    public void c(AbsBYDAutoDoorLockListener absBYDAutoDoorLockListener) {
        if (absBYDAutoDoorLockListener == null) {
            return;
        }
        this.f23a = absBYDAutoDoorLockListener;
    }
}
