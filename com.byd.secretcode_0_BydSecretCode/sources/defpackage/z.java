package defpackage;

import android.hardware.bydauto.sensor.AbsBYDAutoSensorListener;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;

/* JADX INFO: compiled from: TestSensorManagerImplCallback.java */
/* JADX INFO: loaded from: classes.dex */
public class z {
    public AbsBYDAutoSensorListener a;
    public Handler b;

    /* JADX INFO: compiled from: TestSensorManagerImplCallback.java */
    public class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            z.this.a(message);
        }
    }

    public z() {
        HandlerThread handlerThread = new HandlerThread("TestSensorManagerImplCallback");
        handlerThread.start();
        this.b = new a(handlerThread.getLooper());
    }

    public void a(Message message) {
        AbsBYDAutoSensorListener absBYDAutoSensorListener = this.a;
        if (absBYDAutoSensorListener != null) {
            int i = message.what;
            int i2 = message.arg1;
            if (i != 22000) {
                return;
            }
            absBYDAutoSensorListener.onSlopeValueChanged(i2);
        }
    }

    public void b(int i, int i2) {
        if (this.b != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = i;
            messageObtain.arg1 = i2;
            this.b.sendMessage(messageObtain);
        }
    }
}
