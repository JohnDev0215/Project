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
    public AbsBYDAutoSensorListener f2782a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Handler f2783b;

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
        this.f2783b = new a(handlerThread.getLooper());
    }

    public void a(Message message) {
        AbsBYDAutoSensorListener absBYDAutoSensorListener = this.f2782a;
        if (absBYDAutoSensorListener != null) {
            int i3 = message.what;
            int i4 = message.arg1;
            if (i3 != 22000) {
                return;
            }
            absBYDAutoSensorListener.onSlopeValueChanged(i4);
        }
    }

    public void b(int i3, int i4) {
        if (this.f2783b != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = i3;
            messageObtain.arg1 = i4;
            this.f2783b.sendMessage(messageObtain);
        }
    }
}
