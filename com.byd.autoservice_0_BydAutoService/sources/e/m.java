package e;

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
    public final List<AbsBYDAutoSeatListener> f2779a = new ArrayList();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Handler f2780b;

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
        this.f2780b = new a(handlerThread.getLooper());
    }

    public void a(Message message) {
        if (this.f2779a.isEmpty()) {
        }
        int i3 = message.what;
        int i4 = message.arg1;
        int i5 = message.arg2;
        switch (i3) {
            case 3000:
                Iterator<AbsBYDAutoSeatListener> it = this.f2779a.iterator();
                while (it.hasNext()) {
                    it.next().onSeatWarmStateChanged(i4, i5);
                }
                break;
            case 3001:
                Iterator<AbsBYDAutoSeatListener> it2 = this.f2779a.iterator();
                while (it2.hasNext()) {
                    it2.next().onSeatMassageLevelChange(i4, i5);
                }
                break;
            case 3002:
                Iterator<AbsBYDAutoSeatListener> it3 = this.f2779a.iterator();
                while (it3.hasNext()) {
                    it3.next().onSeatMassageTypeChange(i4, i5);
                }
                break;
            case 3003:
                Iterator<AbsBYDAutoSeatListener> it4 = this.f2779a.iterator();
                while (it4.hasNext()) {
                    it4.next().onSteeringWheelWarmStateChanged(i4);
                }
                break;
            case 3004:
                Iterator<AbsBYDAutoSeatListener> it5 = this.f2779a.iterator();
                while (it5.hasNext()) {
                    it5.next().onSeatVentilationState(i4, i5);
                }
                break;
        }
    }

    public void b(int i3, int i4) {
        if (this.f2780b != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = i3;
            messageObtain.arg1 = i4;
            messageObtain.arg2 = 0;
            this.f2780b.sendMessage(messageObtain);
        }
    }

    public void c(int i3, int i4, int i5) {
        if (this.f2780b != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = i3;
            messageObtain.arg1 = i4;
            messageObtain.arg2 = i5;
            this.f2780b.sendMessage(messageObtain);
        }
    }

    public void d(AbsBYDAutoSeatListener absBYDAutoSeatListener) {
        if (absBYDAutoSeatListener != null) {
            this.f2779a.remove(absBYDAutoSeatListener);
        }
    }

    public void e(AbsBYDAutoSeatListener absBYDAutoSeatListener) {
        if (absBYDAutoSeatListener == null || this.f2779a.contains(absBYDAutoSeatListener)) {
            return;
        }
        this.f2779a.add(absBYDAutoSeatListener);
    }
}
