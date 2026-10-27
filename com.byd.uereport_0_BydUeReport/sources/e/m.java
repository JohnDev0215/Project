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
    public final List<AbsBYDAutoSeatListener> f4507a = new ArrayList();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Handler f4508b;

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
        this.f4508b = new a(handlerThread.getLooper());
    }

    public void a(Message message) {
        if (this.f4507a.isEmpty()) {
        }
        int i8 = message.what;
        int i9 = message.arg1;
        int i10 = message.arg2;
        switch (i8) {
            case 3000:
                Iterator<AbsBYDAutoSeatListener> it = this.f4507a.iterator();
                while (it.hasNext()) {
                    it.next().onSeatWarmStateChanged(i9, i10);
                }
                break;
            case 3001:
                Iterator<AbsBYDAutoSeatListener> it2 = this.f4507a.iterator();
                while (it2.hasNext()) {
                    it2.next().onSeatMassageLevelChange(i9, i10);
                }
                break;
            case 3002:
                Iterator<AbsBYDAutoSeatListener> it3 = this.f4507a.iterator();
                while (it3.hasNext()) {
                    it3.next().onSeatMassageTypeChange(i9, i10);
                }
                break;
            case 3003:
                Iterator<AbsBYDAutoSeatListener> it4 = this.f4507a.iterator();
                while (it4.hasNext()) {
                    it4.next().onSteeringWheelWarmStateChanged(i9);
                }
                break;
            case 3004:
                Iterator<AbsBYDAutoSeatListener> it5 = this.f4507a.iterator();
                while (it5.hasNext()) {
                    it5.next().onSeatVentilationState(i9, i10);
                }
                break;
        }
    }

    public void b(int i8, int i9) {
        if (this.f4508b != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = i8;
            messageObtain.arg1 = i9;
            messageObtain.arg2 = 0;
            this.f4508b.sendMessage(messageObtain);
        }
    }

    public void c(int i8, int i9, int i10) {
        if (this.f4508b != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = i8;
            messageObtain.arg1 = i9;
            messageObtain.arg2 = i10;
            this.f4508b.sendMessage(messageObtain);
        }
    }

    public void d(AbsBYDAutoSeatListener absBYDAutoSeatListener) {
        if (absBYDAutoSeatListener != null) {
            this.f4507a.remove(absBYDAutoSeatListener);
        }
    }

    public void e(AbsBYDAutoSeatListener absBYDAutoSeatListener) {
        if (absBYDAutoSeatListener == null || this.f4507a.contains(absBYDAutoSeatListener)) {
            return;
        }
        this.f4507a.add(absBYDAutoSeatListener);
    }
}
