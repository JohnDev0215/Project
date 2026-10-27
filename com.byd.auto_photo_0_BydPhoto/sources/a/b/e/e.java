package a.b.e;

import android.hardware.bydauto.doorlock.AbsBYDAutoDoorLockListener;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;

/* JADX INFO: loaded from: classes.dex */
public class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private AbsBYDAutoDoorLockListener f176a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Handler f177b;

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
        this.f177b = new a(handlerThread.getLooper());
    }

    public void a(Message message) {
        AbsBYDAutoDoorLockListener absBYDAutoDoorLockListener = this.f176a;
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
        if (this.f177b != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = i;
            messageObtain.arg1 = i2;
            messageObtain.arg2 = i3;
            this.f177b.sendMessage(messageObtain);
        }
    }

    public void c(AbsBYDAutoDoorLockListener absBYDAutoDoorLockListener) {
        if (absBYDAutoDoorLockListener == null) {
            return;
        }
        this.f176a = absBYDAutoDoorLockListener;
    }
}
