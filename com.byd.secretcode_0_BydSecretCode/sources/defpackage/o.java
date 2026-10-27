package defpackage;

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
public class o {
    public Handler a;
    public final List<AbsBYDAutoChargingListener> b = new ArrayList();

    /* JADX INFO: compiled from: TestChargingManagerImplCallback.java */
    public class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            o.this.a(message);
        }
    }

    public o() {
        HandlerThread handlerThread = new HandlerThread("TestChargingManagerImplCallback");
        handlerThread.start();
        this.a = new a(handlerThread.getLooper());
    }

    public void a(Message message) {
        if (this.b.isEmpty()) {
        }
        int i = message.what;
        int i2 = message.arg1;
        Object obj = message.obj;
        switch (i) {
            case 6011:
                for (AbsBYDAutoChargingListener absBYDAutoChargingListener : this.b) {
                    absBYDAutoChargingListener.onIntelligentKeepTemModeChanged(i2);
                    absBYDAutoChargingListener.onChargeTempCtlStateChanged(i2);
                }
                break;
            case 6012:
                for (AbsBYDAutoChargingListener absBYDAutoChargingListener2 : this.b) {
                    absBYDAutoChargingListener2.onChargeLimitChanged(((Float) obj).floatValue());
                    absBYDAutoChargingListener2.onChargeStopCapacityStateChanged(((Integer) obj).intValue());
                }
                break;
            case 6013:
                Iterator<AbsBYDAutoChargingListener> it = this.b.iterator();
                while (it.hasNext()) {
                    it.next().onChargeToggleGuardChanged(i2);
                }
                break;
            case 6014:
                Iterator<AbsBYDAutoChargingListener> it2 = this.b.iterator();
                while (it2.hasNext()) {
                    it2.next().onMovePowerStationToggleChanged(i2);
                }
                break;
            case 6015:
                Iterator<AbsBYDAutoChargingListener> it3 = this.b.iterator();
                while (it3.hasNext()) {
                    it3.next().onDischargeLimitChanged(i2);
                }
                break;
            case 6016:
                Iterator<AbsBYDAutoChargingListener> it4 = this.b.iterator();
                while (it4.hasNext()) {
                    it4.next().onDischargeToggleChanged(i2);
                }
                break;
            case 6017:
                Iterator<AbsBYDAutoChargingListener> it5 = this.b.iterator();
                while (it5.hasNext()) {
                    it5.next().onDischargeTimeChanged(((Long) obj).longValue());
                }
                break;
            case 6018:
                Iterator<AbsBYDAutoChargingListener> it6 = this.b.iterator();
                while (it6.hasNext()) {
                    it6.next().onPorducePowerWhenLowPowerChanged(i2);
                }
                break;
        }
    }

    public void b(int i, int i2) {
        if (this.a != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = i;
            messageObtain.arg1 = i2;
            this.a.sendMessage(messageObtain);
        }
    }

    public void c(int i, Object obj) {
        if (this.a != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = i;
            messageObtain.obj = obj;
            this.a.sendMessage(messageObtain);
        }
    }

    public void d(AbsBYDAutoChargingListener absBYDAutoChargingListener) {
        if (absBYDAutoChargingListener != null) {
            this.b.remove(absBYDAutoChargingListener);
        }
    }

    public void e(AbsBYDAutoChargingListener absBYDAutoChargingListener) {
        if (absBYDAutoChargingListener == null || this.b.contains(absBYDAutoChargingListener)) {
            return;
        }
        this.b.add(absBYDAutoChargingListener);
    }
}
