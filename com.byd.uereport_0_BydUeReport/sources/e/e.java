package e;

import android.hardware.bydauto.doorlock.AbsBYDAutoDoorLockListener;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;

/* JADX INFO: compiled from: TestDoorlockManagerImplCallback.java */
/* JADX INFO: loaded from: classes.dex */
public class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public AbsBYDAutoDoorLockListener f4483a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Handler f4484b;

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
        this.f4484b = new a(handlerThread.getLooper());
    }

    public void a(Message message) {
        AbsBYDAutoDoorLockListener absBYDAutoDoorLockListener = this.f4483a;
        if (absBYDAutoDoorLockListener != null) {
            int i8 = message.what;
            int i9 = message.arg1;
            int i10 = message.arg2;
            if (i8 != 32000) {
                return;
            }
            absBYDAutoDoorLockListener.onDoorLockStatusChanged(i9, i10);
        }
    }

    public void b(int i8, int i9, int i10) {
        if (this.f4484b != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = i8;
            messageObtain.arg1 = i9;
            messageObtain.arg2 = i10;
            this.f4484b.sendMessage(messageObtain);
        }
    }

    public void c(AbsBYDAutoDoorLockListener absBYDAutoDoorLockListener) {
        if (absBYDAutoDoorLockListener == null) {
            return;
        }
        this.f4483a = absBYDAutoDoorLockListener;
    }
}
