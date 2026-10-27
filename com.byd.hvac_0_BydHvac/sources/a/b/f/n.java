package a.b.f;

import android.hardware.bydauto.sensor.AbsBYDAutoSensorListener;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public AbsBYDAutoSensorListener f50a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Handler f51b;

    /* JADX INFO: compiled from: caradapter-bug */
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
        this.f51b = new a(handlerThread.getLooper());
    }

    public void a(Message message) {
        AbsBYDAutoSensorListener absBYDAutoSensorListener = this.f50a;
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
        if (this.f51b != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = i;
            messageObtain.arg1 = i2;
            this.f51b.sendMessage(messageObtain);
        }
    }
}
