package b;

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
    private Handler f3429a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final List<AbsBYDAutoChargingListener> f3430b = new ArrayList();

    /* JADX INFO: compiled from: TestChargingManagerImplCallback.java */
    class a extends Handler {
        a(Looper looper) {
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
        this.f3429a = new a(handlerThread.getLooper());
    }

    public void a(Message message) {
        if (this.f3430b.isEmpty()) {
        }
        int i7 = message.what;
        int i8 = message.arg1;
        Object obj = message.obj;
        switch (i7) {
            case 6011:
                for (AbsBYDAutoChargingListener absBYDAutoChargingListener : this.f3430b) {
                    absBYDAutoChargingListener.onIntelligentKeepTemModeChanged(i8);
                    absBYDAutoChargingListener.onChargeTempCtlStateChanged(i8);
                }
                break;
            case 6012:
                for (AbsBYDAutoChargingListener absBYDAutoChargingListener2 : this.f3430b) {
                    absBYDAutoChargingListener2.onChargeLimitChanged(((Float) obj).floatValue());
                    absBYDAutoChargingListener2.onChargeStopCapacityStateChanged(((Integer) obj).intValue());
                }
                break;
            case 6013:
                Iterator<AbsBYDAutoChargingListener> it = this.f3430b.iterator();
                while (it.hasNext()) {
                    it.next().onChargeToggleGuardChanged(i8);
                }
                break;
            case 6014:
                Iterator<AbsBYDAutoChargingListener> it2 = this.f3430b.iterator();
                while (it2.hasNext()) {
                    it2.next().onMovePowerStationToggleChanged(i8);
                }
                break;
            case 6015:
                Iterator<AbsBYDAutoChargingListener> it3 = this.f3430b.iterator();
                while (it3.hasNext()) {
                    it3.next().onDischargeLimitChanged(i8);
                }
                break;
            case 6016:
                Iterator<AbsBYDAutoChargingListener> it4 = this.f3430b.iterator();
                while (it4.hasNext()) {
                    it4.next().onDischargeToggleChanged(i8);
                }
                break;
            case 6017:
                Iterator<AbsBYDAutoChargingListener> it5 = this.f3430b.iterator();
                while (it5.hasNext()) {
                    it5.next().onDischargeTimeChanged(((Long) obj).longValue());
                }
                break;
            case 6018:
                Iterator<AbsBYDAutoChargingListener> it6 = this.f3430b.iterator();
                while (it6.hasNext()) {
                    it6.next().onPorducePowerWhenLowPowerChanged(i8);
                }
                break;
        }
    }

    public void b(int i7, int i8) {
        if (this.f3429a != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = i7;
            messageObtain.arg1 = i8;
            this.f3429a.sendMessage(messageObtain);
        }
    }

    public void c(int i7, Object obj) {
        if (this.f3429a != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = i7;
            messageObtain.obj = obj;
            this.f3429a.sendMessage(messageObtain);
        }
    }

    public void d(AbsBYDAutoChargingListener absBYDAutoChargingListener) {
        if (absBYDAutoChargingListener != null) {
            this.f3430b.remove(absBYDAutoChargingListener);
        }
    }

    public void e(AbsBYDAutoChargingListener absBYDAutoChargingListener) {
        if (absBYDAutoChargingListener == null || this.f3430b.contains(absBYDAutoChargingListener)) {
            return;
        }
        this.f3430b.add(absBYDAutoChargingListener);
    }
}
