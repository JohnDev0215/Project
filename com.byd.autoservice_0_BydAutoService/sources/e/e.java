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
    public AbsBYDAutoDoorLockListener f2755a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Handler f2756b;

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
        this.f2756b = new a(handlerThread.getLooper());
    }

    public void a(Message message) {
        AbsBYDAutoDoorLockListener absBYDAutoDoorLockListener = this.f2755a;
        if (absBYDAutoDoorLockListener != null) {
            int i3 = message.what;
            int i4 = message.arg1;
            int i5 = message.arg2;
            if (i3 != 32000) {
                return;
            }
            absBYDAutoDoorLockListener.onDoorLockStatusChanged(i4, i5);
        }
    }

    public void b(int i3, int i4, int i5) {
        if (this.f2756b != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = i3;
            messageObtain.arg1 = i4;
            messageObtain.arg2 = i5;
            this.f2756b.sendMessage(messageObtain);
        }
    }

    public void c(AbsBYDAutoDoorLockListener absBYDAutoDoorLockListener) {
        if (absBYDAutoDoorLockListener == null) {
            return;
        }
        this.f2755a = absBYDAutoDoorLockListener;
    }
}
