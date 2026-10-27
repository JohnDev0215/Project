package a.b.f;

import android.hardware.bydauto.sensor.AbsBYDAutoSensorListener;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;

/* JADX INFO: compiled from: TestSensorManagerImplCallback.java */
/* JADX INFO: loaded from: classes.dex */
public class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public AbsBYDAutoSensorListener f49a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Handler f50b;

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
        this.f50b = new a(handlerThread.getLooper());
    }

    public void a(Message message) {
        AbsBYDAutoSensorListener absBYDAutoSensorListener = this.f49a;
        if (absBYDAutoSensorListener != null) {
            int i2 = message.what;
            int i3 = message.arg1;
            if (i2 != 22000) {
                return;
            }
            absBYDAutoSensorListener.onSlopeValueChanged(i3);
        }
    }

    public void b(int i2, int i3) {
        if (this.f50b != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = i2;
            messageObtain.arg1 = i3;
            this.f50b.sendMessage(messageObtain);
        }
    }
}
