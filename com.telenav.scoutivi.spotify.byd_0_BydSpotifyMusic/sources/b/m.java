package b;

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
    private final List<AbsBYDAutoSeatListener> f3459a = new ArrayList();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Handler f3460b;

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
        this.f3460b = new a(handlerThread.getLooper());
    }

    public void a(Message message) {
        if (this.f3459a.isEmpty()) {
        }
        int i7 = message.what;
        int i8 = message.arg1;
        int i9 = message.arg2;
        switch (i7) {
            case 3000:
                Iterator<AbsBYDAutoSeatListener> it = this.f3459a.iterator();
                while (it.hasNext()) {
                    it.next().onSeatWarmStateChanged(i8, i9);
                }
                break;
            case 3001:
                Iterator<AbsBYDAutoSeatListener> it2 = this.f3459a.iterator();
                while (it2.hasNext()) {
                    it2.next().onSeatMassageLevelChange(i8, i9);
                }
                break;
            case 3002:
                Iterator<AbsBYDAutoSeatListener> it3 = this.f3459a.iterator();
                while (it3.hasNext()) {
                    it3.next().onSeatMassageTypeChange(i8, i9);
                }
                break;
            case 3003:
                Iterator<AbsBYDAutoSeatListener> it4 = this.f3459a.iterator();
                while (it4.hasNext()) {
                    it4.next().onSteeringWheelWarmStateChanged(i8);
                }
                break;
            case 3004:
                Iterator<AbsBYDAutoSeatListener> it5 = this.f3459a.iterator();
                while (it5.hasNext()) {
                    it5.next().onSeatVentilationState(i8, i9);
                }
                break;
        }
    }

    public void b(int i7, int i8) {
        if (this.f3460b != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = i7;
            messageObtain.arg1 = i8;
            messageObtain.arg2 = 0;
            this.f3460b.sendMessage(messageObtain);
        }
    }

    public void c(int i7, int i8, int i9) {
        if (this.f3460b != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = i7;
            messageObtain.arg1 = i8;
            messageObtain.arg2 = i9;
            this.f3460b.sendMessage(messageObtain);
        }
    }

    public void d(AbsBYDAutoSeatListener absBYDAutoSeatListener) {
        if (absBYDAutoSeatListener != null) {
            this.f3459a.remove(absBYDAutoSeatListener);
        }
    }

    public void e(AbsBYDAutoSeatListener absBYDAutoSeatListener) {
        if (absBYDAutoSeatListener == null || this.f3459a.contains(absBYDAutoSeatListener)) {
            return;
        }
        this.f3459a.add(absBYDAutoSeatListener);
    }
}
