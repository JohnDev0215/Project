package a.a.e;

import android.hardware.bydauto.sensor.AbsBYDAutoSensorListener;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;

/* JADX INFO: compiled from: TestSensorManagerImplCallback.java */
/* JADX INFO: loaded from: classes.dex */
public class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private AbsBYDAutoSensorListener f36a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Handler f37b;

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
        this.f37b = new a(handlerThread.getLooper());
    }

    public void a(Message message) {
        AbsBYDAutoSensorListener absBYDAutoSensorListener = this.f36a;
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
        if (this.f37b != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = i2;
            messageObtain.arg1 = i3;
            this.f37b.sendMessage(messageObtain);
        }
    }
}
