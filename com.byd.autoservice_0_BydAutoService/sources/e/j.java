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
    public final List<AbsBYDAutoInstrumentListener> f2770a = new ArrayList();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Handler f2771b;

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
        this.f2771b = new a(handlerThread.getLooper());
    }

    public void a(Message message) {
        if (this.f2770a.isEmpty()) {
        }
        int i3 = message.what;
        int i4 = message.arg1;
        int i5 = message.arg2;
        switch (i3) {
            case 7000:
                Iterator<AbsBYDAutoInstrumentListener> it = this.f2770a.iterator();
                while (it.hasNext()) {
                    it.next().onInstrumentThemeChange(i4);
                }
                break;
            case 7001:
                Iterator<AbsBYDAutoInstrumentListener> it2 = this.f2770a.iterator();
                while (it2.hasNext()) {
                    it2.next().onMilesTypeChanged(i4);
                }
                break;
            case 7002:
                Iterator<AbsBYDAutoInstrumentListener> it3 = this.f2770a.iterator();
                while (it3.hasNext()) {
                    it3.next().onMaintenanceInfoStateChanged(i4, i5);
                }
                break;
            case 7003:
                Iterator<AbsBYDAutoInstrumentListener> it4 = this.f2770a.iterator();
                while (it4.hasNext()) {
                    it4.next().onInstrumentAutoDimmStateChange(i4);
                }
                break;
            case 7004:
                Iterator<AbsBYDAutoInstrumentListener> it5 = this.f2770a.iterator();
                while (it5.hasNext()) {
                    it5.next().onInstrumentBrightnessChanged(i4);
                }
                break;
        }
    }

    public void b(int i3, int i4) {
        if (this.f2771b != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = i3;
            messageObtain.arg1 = i4;
            messageObtain.arg2 = 0;
            this.f2771b.sendMessage(messageObtain);
        }
    }

    public void c(AbsBYDAutoInstrumentListener absBYDAutoInstrumentListener) {
        if (absBYDAutoInstrumentListener != null) {
            this.f2770a.remove(absBYDAutoInstrumentListener);
        }
    }

    public void d(AbsBYDAutoInstrumentListener absBYDAutoInstrumentListener) {
        if (absBYDAutoInstrumentListener == null || this.f2770a.contains(absBYDAutoInstrumentListener)) {
            return;
        }
        this.f2770a.add(absBYDAutoInstrumentListener);
    }
}
