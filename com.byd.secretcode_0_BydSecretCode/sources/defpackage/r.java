package defpackage;

import android.hardware.bydauto.TsNormalNum;
import android.hardware.bydauto.energy.AbsBYDAutoEnergyListener;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: TestEngergyManagerImplCallback.java */
/* JADX INFO: loaded from: classes.dex */
public class r {
    public final List<AbsBYDAutoEnergyListener> a = new ArrayList();
    public Handler b;

    /* JADX INFO: compiled from: TestEngergyManagerImplCallback.java */
    public class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            r.this.a(message);
        }
    }

    public r() {
        HandlerThread handlerThread = new HandlerThread("TestEngergyManagerImplCallback");
        handlerThread.start();
        this.b = new a(handlerThread.getLooper());
    }

    public void a(Message message) {
        if (this.a.isEmpty()) {
        }
        int i = message.what;
        int i2 = message.arg1;
        switch (i) {
            case TsNormalNum.THOUSAND_SIX /* 6000 */:
                Iterator<AbsBYDAutoEnergyListener> it = this.a.iterator();
                while (it.hasNext()) {
                    it.next().onEPedalStateChanged(i2);
                }
                break;
            case 6001:
                Iterator<AbsBYDAutoEnergyListener> it2 = this.a.iterator();
                while (it2.hasNext()) {
                    it2.next().onEnergyFeedbackLevelChanged(i2);
                }
                break;
            case 6002:
                Iterator<AbsBYDAutoEnergyListener> it3 = this.a.iterator();
                while (it3.hasNext()) {
                    it3.next().onDriveDistanceShowStandardChanged(i2);
                }
                break;
            case 6003:
                Iterator<AbsBYDAutoEnergyListener> it4 = this.a.iterator();
                while (it4.hasNext()) {
                    it4.next().onMemoryMaxEvStateChanged(i2);
                }
                break;
            case 6004:
                Iterator<AbsBYDAutoEnergyListener> it5 = this.a.iterator();
                while (it5.hasNext()) {
                    it5.next().onElectricShowModeChanged(i2);
                }
                break;
            case 6005:
                Iterator<AbsBYDAutoEnergyListener> it6 = this.a.iterator();
                while (it6.hasNext()) {
                    it6.next().onMemorySportStateChanged(i2);
                }
                break;
            case 6006:
                Iterator<AbsBYDAutoEnergyListener> it7 = this.a.iterator();
                while (it7.hasNext()) {
                    it7.next().onMandatoryElectricStateChanged(i2);
                }
                break;
            case 6007:
                Iterator<AbsBYDAutoEnergyListener> it8 = this.a.iterator();
                while (it8.hasNext()) {
                    it8.next().onSOCTargetChanged(i2);
                }
                break;
            case 6008:
                Iterator<AbsBYDAutoEnergyListener> it9 = this.a.iterator();
                while (it9.hasNext()) {
                    it9.next().onMemoryElectricStateChanged(i2);
                }
                break;
            case 6009:
                Iterator<AbsBYDAutoEnergyListener> it10 = this.a.iterator();
                while (it10.hasNext()) {
                    it10.next().onDriveDistanceShowModeChanged(i2);
                }
                break;
            case 6010:
                Iterator<AbsBYDAutoEnergyListener> it11 = this.a.iterator();
                while (it11.hasNext()) {
                    it11.next().onPreSaleModelStateChanged(i2);
                }
                break;
            case 6011:
                Iterator<AbsBYDAutoEnergyListener> it12 = this.a.iterator();
                while (it12.hasNext()) {
                    it12.next().onResetCurveDataChanged(i2, 0);
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

    public void c(AbsBYDAutoEnergyListener absBYDAutoEnergyListener) {
        if (absBYDAutoEnergyListener != null) {
            this.a.remove(absBYDAutoEnergyListener);
        }
    }

    public void d(AbsBYDAutoEnergyListener absBYDAutoEnergyListener) {
        if (absBYDAutoEnergyListener == null || this.a.contains(absBYDAutoEnergyListener)) {
            return;
        }
        this.a.add(absBYDAutoEnergyListener);
    }
}
