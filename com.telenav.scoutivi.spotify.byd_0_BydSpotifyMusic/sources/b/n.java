package b;

import android.hardware.bydauto.sensor.AbsBYDAutoSensorListener;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;

/* JADX INFO: compiled from: TestSensorManagerImplCallback.java */
/* JADX INFO: loaded from: classes.dex */
public class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private AbsBYDAutoSensorListener f3462a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Handler f3463b;

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
        this.f3463b = new a(handlerThread.getLooper());
    }

    public void a(Message message) {
        AbsBYDAutoSensorListener absBYDAutoSensorListener = this.f3462a;
        if (absBYDAutoSensorListener != null) {
            int i7 = message.what;
            int i8 = message.arg1;
            if (i7 != 22000) {
                return;
            }
            absBYDAutoSensorListener.onSlopeValueChanged(i8);
        }
    }

    public void b(int i7, int i8) {
        if (this.f3463b != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = i7;
            messageObtain.arg1 = i8;
            this.f3463b.sendMessage(messageObtain);
        }
    }
}
