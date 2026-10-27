package e;

import android.hardware.bydauto.charging.AbsBYDAutoChargingListener;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: TestChargingManagerImplCallback.java */
/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Handler f2749a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final List<AbsBYDAutoChargingListener> f2750b = new ArrayList();

    /* JADX INFO: compiled from: TestChargingManagerImplCallback.java */
    public class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            c.this.a(message);
        }
    }

    public c() {
        HandlerThread handlerThread = new HandlerThread("TestChargingManagerImplCallback");
        handlerThread.start();
        this.f2749a = new a(handlerThread.getLooper());
    }

    public void a(Message message) {
        if (this.f2750b.isEmpty()) {
        }
        int i3 = message.what;
        int i4 = message.arg1;
        Object obj = message.obj;
        switch (i3) {
            case 6011:
                for (AbsBYDAutoChargingListener absBYDAutoChargingListener : this.f2750b) {
                    absBYDAutoChargingListener.onIntelligentKeepTemModeChanged(i4);
                    absBYDAutoChargingListener.onChargeTempCtlStateChanged(i4);
                }
                break;
            case 6012:
                for (AbsBYDAutoChargingListener absBYDAutoChargingListener2 : this.f2750b) {
                    absBYDAutoChargingListener2.onChargeLimitChanged(((Float) obj).floatValue());
                    absBYDAutoChargingListener2.onChargeStopCapacityStateChanged(((Integer) obj).intValue());
                }
                break;
            case 6013:
                Iterator<AbsBYDAutoChargingListener> it = this.f2750b.iterator();
                while (it.hasNext()) {
                    it.next().onChargeToggleGuardChanged(i4);
                }
                break;
            case 6014:
                Iterator<AbsBYDAutoChargingListener> it2 = this.f2750b.iterator();
                while (it2.hasNext()) {
                    it2.next().onMovePowerStationToggleChanged(i4);
                }
                break;
            case 6015:
                Iterator<AbsBYDAutoChargingListener> it3 = this.f2750b.iterator();
                while (it3.hasNext()) {
                    it3.next().onDischargeLimitChanged(i4);
                }
                break;
            case 6016:
                Iterator<AbsBYDAutoChargingListener> it4 = this.f2750b.iterator();
                while (it4.hasNext()) {
                    it4.next().onDischargeToggleChanged(i4);
                }
                break;
            case 6017:
                Iterator<AbsBYDAutoChargingListener> it5 = this.f2750b.iterator();
                while (it5.hasNext()) {
                    it5.next().onDischargeTimeChanged(((Long) obj).longValue());
                }
                break;
            case 6018:
                Iterator<AbsBYDAutoChargingListener> it6 = this.f2750b.iterator();
                while (it6.hasNext()) {
                    it6.next().onPorducePowerWhenLowPowerChanged(i4);
                }
                break;
        }
    }

    public void b(int i3, int i4) {
        if (this.f2749a != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = i3;
            messageObtain.arg1 = i4;
            this.f2749a.sendMessage(messageObtain);
        }
    }

    public void c(int i3, Object obj) {
        if (this.f2749a != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = i3;
            messageObtain.obj = obj;
            this.f2749a.sendMessage(messageObtain);
        }
    }

    public void d(AbsBYDAutoChargingListener absBYDAutoChargingListener) {
        if (absBYDAutoChargingListener != null) {
            this.f2750b.remove(absBYDAutoChargingListener);
        }
    }

    public void e(AbsBYDAutoChargingListener absBYDAutoChargingListener) {
        if (absBYDAutoChargingListener == null || this.f2750b.contains(absBYDAutoChargingListener)) {
            return;
        }
        this.f2750b.add(absBYDAutoChargingListener);
    }
}
