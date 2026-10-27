package e;

import android.hardware.bydauto.sensor.AbsBYDAutoSensorListener;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;

/* JADX INFO: compiled from: TestSensorManagerImplCallback.java */
/* JADX INFO: loaded from: classes.dex */
public class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public AbsBYDAutoSensorListener f4510a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Handler f4511b;

    /* JADX INFO: compiled from: TestSensorManagerImplCallback.java */
    public class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            n.this.a(message);
        }
    }

    public n() {
        HandlerThread handlerThread = new HandlerThread("TestSensorManagerImplCallback");
        handlerThread.start();
        this.f4511b = new a(handlerThread.getLooper());
    }

    public void a(Message message) {
        AbsBYDAutoSensorListener absBYDAutoSensorListener = this.f4510a;
        if (absBYDAutoSensorListener != null) {
            int i8 = message.what;
            int i9 = message.arg1;
            if (i8 != 22000) {
                return;
            }
            absBYDAutoSensorListener.onSlopeValueChanged(i9);
        }
    }

    public void b(int i8, int i9) {
        if (this.f4511b != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = i8;
            messageObtain.arg1 = i9;
            this.f4511b.sendMessage(messageObtain);
        }
    }
}
