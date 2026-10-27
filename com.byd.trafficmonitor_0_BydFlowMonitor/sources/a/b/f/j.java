package a.b.f;

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
    public final List<AbsBYDAutoInstrumentListener> f38a = new ArrayList();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Handler f39b;

    /* JADX INFO: compiled from: TestInstrumentManagerImplCallback.java */
    public class a extends Handler {
        public a(Looper looper) {
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
        this.f39b = new a(handlerThread.getLooper());
    }

    public void a(Message message) {
        if (this.f38a.isEmpty()) {
        }
        int i2 = message.what;
        int i3 = message.arg1;
        int i4 = message.arg2;
        switch (i2) {
            case 7000:
                Iterator<AbsBYDAutoInstrumentListener> it = this.f38a.iterator();
                while (it.hasNext()) {
                    it.next().onInstrumentThemeChange(i3);
                }
                break;
            case 7001:
                Iterator<AbsBYDAutoInstrumentListener> it2 = this.f38a.iterator();
                while (it2.hasNext()) {
                    it2.next().onMilesTypeChanged(i3);
                }
                break;
            case 7002:
                Iterator<AbsBYDAutoInstrumentListener> it3 = this.f38a.iterator();
                while (it3.hasNext()) {
                    it3.next().onMaintenanceInfoStateChanged(i3, i4);
                }
                break;
            case 7003:
                Iterator<AbsBYDAutoInstrumentListener> it4 = this.f38a.iterator();
                while (it4.hasNext()) {
                    it4.next().onInstrumentAutoDimmStateChange(i3);
                }
                break;
            case 7004:
                Iterator<AbsBYDAutoInstrumentListener> it5 = this.f38a.iterator();
                while (it5.hasNext()) {
                    it5.next().onInstrumentBrightnessChanged(i3);
                }
                break;
        }
    }

    public void b(int i2, int i3) {
        if (this.f39b != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = i2;
            messageObtain.arg1 = i3;
            messageObtain.arg2 = 0;
            this.f39b.sendMessage(messageObtain);
        }
    }

    public void c(AbsBYDAutoInstrumentListener absBYDAutoInstrumentListener) {
        if (absBYDAutoInstrumentListener != null) {
            this.f38a.remove(absBYDAutoInstrumentListener);
        }
    }

    public void d(AbsBYDAutoInstrumentListener absBYDAutoInstrumentListener) {
        if (absBYDAutoInstrumentListener == null || this.f38a.contains(absBYDAutoInstrumentListener)) {
            return;
        }
        this.f38a.add(absBYDAutoInstrumentListener);
    }
}
