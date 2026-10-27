package a.b.e;

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
    private final List<AbsBYDAutoInstrumentListener> f26a = new ArrayList();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Handler f27b;

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
        this.f27b = new a(handlerThread.getLooper());
    }

    public void a(Message message) {
        if (this.f26a.isEmpty()) {
        }
        int i = message.what;
        int i2 = message.arg1;
        int i3 = message.arg2;
        switch (i) {
            case 7000:
                Iterator<AbsBYDAutoInstrumentListener> it = this.f26a.iterator();
                while (it.hasNext()) {
                    it.next().onInstrumentThemeChange(i2);
                }
                break;
            case 7001:
                Iterator<AbsBYDAutoInstrumentListener> it2 = this.f26a.iterator();
                while (it2.hasNext()) {
                    it2.next().onMilesTypeChanged(i2);
                }
                break;
            case 7002:
                Iterator<AbsBYDAutoInstrumentListener> it3 = this.f26a.iterator();
                while (it3.hasNext()) {
                    it3.next().onMaintenanceInfoStateChanged(i2, i3);
                }
                break;
            case 7003:
                Iterator<AbsBYDAutoInstrumentListener> it4 = this.f26a.iterator();
                while (it4.hasNext()) {
                    it4.next().onInstrumentAutoDimmStateChange(i2);
                }
                break;
            case 7004:
                Iterator<AbsBYDAutoInstrumentListener> it5 = this.f26a.iterator();
                while (it5.hasNext()) {
                    it5.next().onInstrumentBrightnessChanged(i2);
                }
                break;
        }
    }

    public void b(int i, int i2) {
        if (this.f27b != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = i;
            messageObtain.arg1 = i2;
            messageObtain.arg2 = 0;
            this.f27b.sendMessage(messageObtain);
        }
    }

    public void c(AbsBYDAutoInstrumentListener absBYDAutoInstrumentListener) {
        if (absBYDAutoInstrumentListener != null) {
            this.f26a.remove(absBYDAutoInstrumentListener);
        }
    }

    public void d(AbsBYDAutoInstrumentListener absBYDAutoInstrumentListener) {
        if (absBYDAutoInstrumentListener == null || this.f26a.contains(absBYDAutoInstrumentListener)) {
            return;
        }
        this.f26a.add(absBYDAutoInstrumentListener);
    }
}
