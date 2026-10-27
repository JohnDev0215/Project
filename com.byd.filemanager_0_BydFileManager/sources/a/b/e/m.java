package a.b.e;

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
    private final List<AbsBYDAutoSeatListener> f35a = new ArrayList();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Handler f36b;

    /* JADX INFO: compiled from: TestSeatManagerImplCallback.java */
    class a extends Handler {
        a(Looper looper) {
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
        this.f36b = new a(handlerThread.getLooper());
    }

    public void a(Message message) {
        if (this.f35a.isEmpty()) {
        }
        int i = message.what;
        int i2 = message.arg1;
        int i3 = message.arg2;
        switch (i) {
            case 3000:
                Iterator<AbsBYDAutoSeatListener> it = this.f35a.iterator();
                while (it.hasNext()) {
                    it.next().onSeatWarmStateChanged(i2, i3);
                }
                break;
            case 3001:
                Iterator<AbsBYDAutoSeatListener> it2 = this.f35a.iterator();
                while (it2.hasNext()) {
                    it2.next().onSeatMassageLevelChange(i2, i3);
                }
                break;
            case 3002:
                Iterator<AbsBYDAutoSeatListener> it3 = this.f35a.iterator();
                while (it3.hasNext()) {
                    it3.next().onSeatMassageTypeChange(i2, i3);
                }
                break;
            case 3003:
                Iterator<AbsBYDAutoSeatListener> it4 = this.f35a.iterator();
                while (it4.hasNext()) {
                    it4.next().onSteeringWheelWarmStateChanged(i2);
                }
                break;
            case 3004:
                Iterator<AbsBYDAutoSeatListener> it5 = this.f35a.iterator();
                while (it5.hasNext()) {
                    it5.next().onSeatVentilationState(i2, i3);
                }
                break;
        }
    }

    public void b(int i, int i2) {
        if (this.f36b != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = i;
            messageObtain.arg1 = i2;
            messageObtain.arg2 = 0;
            this.f36b.sendMessage(messageObtain);
        }
    }

    public void c(int i, int i2, int i3) {
        if (this.f36b != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = i;
            messageObtain.arg1 = i2;
            messageObtain.arg2 = i3;
            this.f36b.sendMessage(messageObtain);
        }
    }

    public void d(AbsBYDAutoSeatListener absBYDAutoSeatListener) {
        if (absBYDAutoSeatListener != null) {
            this.f35a.remove(absBYDAutoSeatListener);
        }
    }

    public void e(AbsBYDAutoSeatListener absBYDAutoSeatListener) {
        if (absBYDAutoSeatListener == null || this.f35a.contains(absBYDAutoSeatListener)) {
            return;
        }
        this.f35a.add(absBYDAutoSeatListener);
    }
}
