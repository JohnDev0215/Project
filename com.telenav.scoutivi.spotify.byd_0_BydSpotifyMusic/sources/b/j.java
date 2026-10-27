package b;

import android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: TestInstrumentManagerImplCallback.java */
/* JADX INFO: loaded from: classes.dex */
public class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final List<AbsBYDAutoInstrumentListener> f3450a = new ArrayList();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Handler f3451b;

    /* JADX INFO: compiled from: TestInstrumentManagerImplCallback.java */
    class a extends Handler {
        a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            j.this.a(message);
        }
    }

    public j() {
        HandlerThread handlerThread = new HandlerThread("TestInstrumentManagerImplCallback");
        handlerThread.start();
        this.f3451b = new a(handlerThread.getLooper());
    }

    public void a(Message message) {
        if (this.f3450a.isEmpty()) {
        }
        int i7 = message.what;
        int i8 = message.arg1;
        int i9 = message.arg2;
        switch (i7) {
            case 7000:
                Iterator<AbsBYDAutoInstrumentListener> it = this.f3450a.iterator();
                while (it.hasNext()) {
                    it.next().onInstrumentThemeChange(i8);
                }
                break;
            case 7001:
                Iterator<AbsBYDAutoInstrumentListener> it2 = this.f3450a.iterator();
                while (it2.hasNext()) {
                    it2.next().onMilesTypeChanged(i8);
                }
                break;
            case 7002:
                Iterator<AbsBYDAutoInstrumentListener> it3 = this.f3450a.iterator();
                while (it3.hasNext()) {
                    it3.next().onMaintenanceInfoStateChanged(i8, i9);
                }
                break;
            case 7003:
                Iterator<AbsBYDAutoInstrumentListener> it4 = this.f3450a.iterator();
                while (it4.hasNext()) {
                    it4.next().onInstrumentAutoDimmStateChange(i8);
                }
                break;
            case 7004:
                Iterator<AbsBYDAutoInstrumentListener> it5 = this.f3450a.iterator();
                while (it5.hasNext()) {
                    it5.next().onInstrumentBrightnessChanged(i8);
                }
                break;
        }
    }

    public void b(int i7, int i8) {
        if (this.f3451b != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = i7;
            messageObtain.arg1 = i8;
            messageObtain.arg2 = 0;
            this.f3451b.sendMessage(messageObtain);
        }
    }

    public void c(AbsBYDAutoInstrumentListener absBYDAutoInstrumentListener) {
        if (absBYDAutoInstrumentListener != null) {
            this.f3450a.remove(absBYDAutoInstrumentListener);
        }
    }

    public void d(AbsBYDAutoInstrumentListener absBYDAutoInstrumentListener) {
        if (absBYDAutoInstrumentListener == null || this.f3450a.contains(absBYDAutoInstrumentListener)) {
            return;
        }
        this.f3450a.add(absBYDAutoInstrumentListener);
    }
}
