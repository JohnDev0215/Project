package a.b.f;

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
public class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final List<AbsBYDAutoSeatListener> f47a = new ArrayList();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Handler f48b;

    /* JADX INFO: compiled from: TestSeatManagerImplCallback.java */
    public class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            m.this.a(message);
        }
    }

    public m() {
        HandlerThread handlerThread = new HandlerThread("TestSeatManagerImplCallback");
        handlerThread.start();
        this.f48b = new a(handlerThread.getLooper());
    }

    public void a(Message message) {
        if (this.f47a.isEmpty()) {
        }
        int i = message.what;
        int i2 = message.arg1;
        int i3 = message.arg2;
        switch (i) {
            case 3000:
                Iterator<AbsBYDAutoSeatListener> it = this.f47a.iterator();
                while (it.hasNext()) {
                    it.next().onSeatWarmStateChanged(i2, i3);
                }
                break;
            case 3001:
                Iterator<AbsBYDAutoSeatListener> it2 = this.f47a.iterator();
                while (it2.hasNext()) {
                    it2.next().onSeatMassageLevelChange(i2, i3);
                }
                break;
            case 3002:
                Iterator<AbsBYDAutoSeatListener> it3 = this.f47a.iterator();
                while (it3.hasNext()) {
                    it3.next().onSeatMassageTypeChange(i2, i3);
                }
                break;
            case 3003:
                Iterator<AbsBYDAutoSeatListener> it4 = this.f47a.iterator();
                while (it4.hasNext()) {
                    it4.next().onSteeringWheelWarmStateChanged(i2);
                }
                break;
            case 3004:
                Iterator<AbsBYDAutoSeatListener> it5 = this.f47a.iterator();
                while (it5.hasNext()) {
                    it5.next().onSeatVentilationState(i2, i3);
                }
                break;
        }
    }

    public void b(int i, int i2) {
        if (this.f48b != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = i;
            messageObtain.arg1 = i2;
            messageObtain.arg2 = 0;
            this.f48b.sendMessage(messageObtain);
        }
    }

    public void c(int i, int i2, int i3) {
        if (this.f48b != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = i;
            messageObtain.arg1 = i2;
            messageObtain.arg2 = i3;
            this.f48b.sendMessage(messageObtain);
        }
    }

    public void d(AbsBYDAutoSeatListener absBYDAutoSeatListener) {
        if (absBYDAutoSeatListener != null) {
            this.f47a.remove(absBYDAutoSeatListener);
        }
    }

    public void e(AbsBYDAutoSeatListener absBYDAutoSeatListener) {
        if (absBYDAutoSeatListener == null || this.f47a.contains(absBYDAutoSeatListener)) {
            return;
        }
        this.f47a.add(absBYDAutoSeatListener);
    }
}
