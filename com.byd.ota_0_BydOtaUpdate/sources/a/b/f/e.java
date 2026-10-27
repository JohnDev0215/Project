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
    public AbsBYDAutoDoorLockListener f22a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Handler f23b;

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
        this.f23b = new a(handlerThread.getLooper());
    }

    public void a(Message message) {
        AbsBYDAutoDoorLockListener absBYDAutoDoorLockListener = this.f22a;
        if (absBYDAutoDoorLockListener != null) {
            int i2 = message.what;
            int i3 = message.arg1;
            int i4 = message.arg2;
            if (i2 != 32000) {
                return;
            }
            absBYDAutoDoorLockListener.onDoorLockStatusChanged(i3, i4);
        }
    }

    public void b(int i2, int i3, int i4) {
        if (this.f23b != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = i2;
            messageObtain.arg1 = i3;
            messageObtain.arg2 = i4;
            this.f23b.sendMessage(messageObtain);
        }
    }

    public void c(AbsBYDAutoDoorLockListener absBYDAutoDoorLockListener) {
        if (absBYDAutoDoorLockListener == null) {
            return;
        }
        this.f22a = absBYDAutoDoorLockListener;
    }
}
