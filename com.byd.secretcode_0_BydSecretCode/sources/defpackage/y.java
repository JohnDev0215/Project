package defpackage;

import android.hardware.bydauto.seat.AbsBYDAutoSeatListener;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: TestSeatManagerImplCallback.java */
/* JADX INFO: loaded from: classes.dex */
public class y {
    public final List<AbsBYDAutoSeatListener> a = new ArrayList();
    public Handler b;

    /* JADX INFO: compiled from: TestSeatManagerImplCallback.java */
    public class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            y.this.a(message);
        }
    }

    public y() {
        HandlerThread handlerThread = new HandlerThread("TestSeatManagerImplCallback");
        handlerThread.start();
        this.b = new a(handlerThread.getLooper());
    }

    public void a(Message message) {
        if (this.a.isEmpty()) {
        }
        int i = message.what;
        int i2 = message.arg1;
        int i3 = message.arg2;
        switch (i) {
            case 3000:
                Iterator<AbsBYDAutoSeatListener> it = this.a.iterator();
                while (it.hasNext()) {
                    it.next().onSeatWarmStateChanged(i2, i3);
                }
                break;
            case 3001:
                Iterator<AbsBYDAutoSeatListener> it2 = this.a.iterator();
                while (it2.hasNext()) {
                    it2.next().onSeatMassageLevelChange(i2, i3);
                }
                break;
            case 3002:
                Iterator<AbsBYDAutoSeatListener> it3 = this.a.iterator();
                while (it3.hasNext()) {
                    it3.next().onSeatMassageTypeChange(i2, i3);
                }
                break;
            case 3003:
                Iterator<AbsBYDAutoSeatListener> it4 = this.a.iterator();
                while (it4.hasNext()) {
                    it4.next().onSteeringWheelWarmStateChanged(i2);
                }
                break;
            case 3004:
                Iterator<AbsBYDAutoSeatListener> it5 = this.a.iterator();
                while (it5.hasNext()) {
                    it5.next().onSeatVentilationState(i2, i3);
                }
                break;
        }
    }

    public void b(int i, int i2) {
        if (this.b != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = i;
            messageObtain.arg1 = i2;
            messageObtain.arg2 = 0;
            this.b.sendMessage(messageObtain);
        }
    }

    public void c(int i, int i2, int i3) {
        if (this.b != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = i;
            messageObtain.arg1 = i2;
            messageObtain.arg2 = i3;
            this.b.sendMessage(messageObtain);
        }
    }

    public void d(AbsBYDAutoSeatListener absBYDAutoSeatListener) {
        if (absBYDAutoSeatListener != null) {
            this.a.remove(absBYDAutoSeatListener);
        }
    }

    public void e(AbsBYDAutoSeatListener absBYDAutoSeatListener) {
        if (absBYDAutoSeatListener == null || this.a.contains(absBYDAutoSeatListener)) {
            return;
        }
        this.a.add(absBYDAutoSeatListener);
    }
}
