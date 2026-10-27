package e;

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
    public final List<AbsBYDAutoInstrumentListener> f4498a = new ArrayList();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Handler f4499b;

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
        this.f4499b = new a(handlerThread.getLooper());
    }

    public void a(Message message) {
        if (this.f4498a.isEmpty()) {
        }
        int i8 = message.what;
        int i9 = message.arg1;
        int i10 = message.arg2;
        switch (i8) {
            case 7000:
                Iterator<AbsBYDAutoInstrumentListener> it = this.f4498a.iterator();
                while (it.hasNext()) {
                    it.next().onInstrumentThemeChange(i9);
                }
                break;
            case 7001:
                Iterator<AbsBYDAutoInstrumentListener> it2 = this.f4498a.iterator();
                while (it2.hasNext()) {
                    it2.next().onMilesTypeChanged(i9);
                }
                break;
            case 7002:
                Iterator<AbsBYDAutoInstrumentListener> it3 = this.f4498a.iterator();
                while (it3.hasNext()) {
                    it3.next().onMaintenanceInfoStateChanged(i9, i10);
                }
                break;
            case 7003:
                Iterator<AbsBYDAutoInstrumentListener> it4 = this.f4498a.iterator();
                while (it4.hasNext()) {
                    it4.next().onInstrumentAutoDimmStateChange(i9);
                }
                break;
            case 7004:
                Iterator<AbsBYDAutoInstrumentListener> it5 = this.f4498a.iterator();
                while (it5.hasNext()) {
                    it5.next().onInstrumentBrightnessChanged(i9);
                }
                break;
        }
    }

    public void b(int i8, int i9) {
        if (this.f4499b != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = i8;
            messageObtain.arg1 = i9;
            messageObtain.arg2 = 0;
            this.f4499b.sendMessage(messageObtain);
        }
    }

    public void c(AbsBYDAutoInstrumentListener absBYDAutoInstrumentListener) {
        if (absBYDAutoInstrumentListener != null) {
            this.f4498a.remove(absBYDAutoInstrumentListener);
        }
    }

    public void d(AbsBYDAutoInstrumentListener absBYDAutoInstrumentListener) {
        if (absBYDAutoInstrumentListener == null || this.f4498a.contains(absBYDAutoInstrumentListener)) {
            return;
        }
        this.f4498a.add(absBYDAutoInstrumentListener);
    }
}
