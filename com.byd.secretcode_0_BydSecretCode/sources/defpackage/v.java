package defpackage;

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
public class v {
    public final List<AbsBYDAutoInstrumentListener> a = new ArrayList();
    public Handler b;

    /* JADX INFO: compiled from: TestInstrumentManagerImplCallback.java */
    public class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            v.this.a(message);
        }
    }

    public v() {
        HandlerThread handlerThread = new HandlerThread("TestInstrumentManagerImplCallback");
        handlerThread.start();
        this.b = new a(handlerThread.getLooper());
    }

    public void a(Message message) {
        if (this.a.isEmpty()) {
        }
        int i = message.what;
        int i2 = message.arg1;
        int i3 = message.arg2;
        switch (i) {
            case 7000:
                Iterator<AbsBYDAutoInstrumentListener> it = this.a.iterator();
                while (it.hasNext()) {
                    it.next().onInstrumentThemeChange(i2);
                }
                break;
            case 7001:
                Iterator<AbsBYDAutoInstrumentListener> it2 = this.a.iterator();
                while (it2.hasNext()) {
                    it2.next().onMilesTypeChanged(i2);
                }
                break;
            case 7002:
                Iterator<AbsBYDAutoInstrumentListener> it3 = this.a.iterator();
                while (it3.hasNext()) {
                    it3.next().onMaintenanceInfoStateChanged(i2, i3);
                }
                break;
            case 7003:
                Iterator<AbsBYDAutoInstrumentListener> it4 = this.a.iterator();
                while (it4.hasNext()) {
                    it4.next().onInstrumentAutoDimmStateChange(i2);
                }
                break;
            case 7004:
                Iterator<AbsBYDAutoInstrumentListener> it5 = this.a.iterator();
                while (it5.hasNext()) {
                    it5.next().onInstrumentBrightnessChanged(i2);
                }
                break;
        }
    }

    public void b(int i, int i2) {
        if (this.b != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = i;
            messageObtain.arg1 = i2;
            messageObtain.arg2 = 0;
            this.b.sendMessage(messageObtain);
        }
    }

    public void c(AbsBYDAutoInstrumentListener absBYDAutoInstrumentListener) {
        if (absBYDAutoInstrumentListener != null) {
            this.a.remove(absBYDAutoInstrumentListener);
        }
    }

    public void d(AbsBYDAutoInstrumentListener absBYDAutoInstrumentListener) {
        if (absBYDAutoInstrumentListener == null || this.a.contains(absBYDAutoInstrumentListener)) {
            return;
        }
        this.a.add(absBYDAutoInstrumentListener);
    }
}
