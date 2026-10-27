package a.b.e;

import android.hardware.bydauto.sensor.AbsBYDAutoSensorListener;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;

/* JADX INFO: compiled from: TestSensorManagerImplCallback.java */
/* JADX INFO: loaded from: classes.dex */
public class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private AbsBYDAutoSensorListener f38a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Handler f39b;

    /* JADX INFO: compiled from: TestSensorManagerImplCallback.java */
    class a extends Handler {
        a(Looper looper) {
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
        this.f39b = new a(handlerThread.getLooper());
    }

    public void a(Message message) {
        AbsBYDAutoSensorListener absBYDAutoSensorListener = this.f38a;
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
        if (this.f39b != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = i;
            messageObtain.arg1 = i2;
            this.f39b.sendMessage(messageObtain);
        }
    }
}
