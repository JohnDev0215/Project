package b.b.e;

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
public class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final List<AbsBYDAutoEnergyListener> f1938a = new ArrayList();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Handler f1939b;

    /* JADX INFO: compiled from: TestEngergyManagerImplCallback.java */
    class a extends Handler {
        a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            f.this.a(message);
        }
    }

    public f() {
        HandlerThread handlerThread = new HandlerThread("TestEngergyManagerImplCallback");
        handlerThread.start();
        this.f1939b = new a(handlerThread.getLooper());
    }

    public void a(Message message) {
        if (this.f1938a.isEmpty()) {
        }
        int i = message.what;
        int i2 = message.arg1;
        switch (i) {
            case TsNormalNum.THOUSAND_SIX /* 6000 */:
                Iterator<AbsBYDAutoEnergyListener> it = this.f1938a.iterator();
                while (it.hasNext()) {
                    it.next().onEPedalStateChanged(i2);
                }
                break;
            case 6001:
                Iterator<AbsBYDAutoEnergyListener> it2 = this.f1938a.iterator();
                while (it2.hasNext()) {
                    it2.next().onEnergyFeedbackLevelChanged(i2);
                }
                break;
            case 6002:
                Iterator<AbsBYDAutoEnergyListener> it3 = this.f1938a.iterator();
                while (it3.hasNext()) {
                    it3.next().onDriveDistanceShowStandardChanged(i2);
                }
                break;
            case 6003:
                Iterator<AbsBYDAutoEnergyListener> it4 = this.f1938a.iterator();
                while (it4.hasNext()) {
                    it4.next().onMemoryMaxEvStateChanged(i2);
                }
                break;
            case 6004:
                Iterator<AbsBYDAutoEnergyListener> it5 = this.f1938a.iterator();
                while (it5.hasNext()) {
                    it5.next().onElectricShowModeChanged(i2);
                }
                break;
            case 6005:
                Iterator<AbsBYDAutoEnergyListener> it6 = this.f1938a.iterator();
                while (it6.hasNext()) {
                    it6.next().onMemorySportStateChanged(i2);
                }
                break;
            case 6006:
                Iterator<AbsBYDAutoEnergyListener> it7 = this.f1938a.iterator();
                while (it7.hasNext()) {
                    it7.next().onMandatoryElectricStateChanged(i2);
                }
                break;
            case 6007:
                Iterator<AbsBYDAutoEnergyListener> it8 = this.f1938a.iterator();
                while (it8.hasNext()) {
                    it8.next().onSOCTargetChanged(i2);
                }
                break;
            case 6008:
                Iterator<AbsBYDAutoEnergyListener> it9 = this.f1938a.iterator();
                while (it9.hasNext()) {
                    it9.next().onMemoryElectricStateChanged(i2);
                }
                break;
            case 6009:
                Iterator<AbsBYDAutoEnergyListener> it10 = this.f1938a.iterator();
                while (it10.hasNext()) {
                    it10.next().onDriveDistanceShowModeChanged(i2);
                }
                break;
            case 6010:
                Iterator<AbsBYDAutoEnergyListener> it11 = this.f1938a.iterator();
                while (it11.hasNext()) {
                    it11.next().onPreSaleModelStateChanged(i2);
                }
                break;
            case 6011:
                Iterator<AbsBYDAutoEnergyListener> it12 = this.f1938a.iterator();
                while (it12.hasNext()) {
                    it12.next().onResetCurveDataChanged(i2, 0);
                }
                break;
        }
    }

    public void b(int i, int i2) {
        if (this.f1939b != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = i;
            messageObtain.arg1 = i2;
            messageObtain.arg2 = 0;
            this.f1939b.sendMessage(messageObtain);
        }
    }

    public void c(AbsBYDAutoEnergyListener absBYDAutoEnergyListener) {
        if (absBYDAutoEnergyListener != null) {
            this.f1938a.remove(absBYDAutoEnergyListener);
        }
    }

    public void d(AbsBYDAutoEnergyListener absBYDAutoEnergyListener) {
        if (absBYDAutoEnergyListener == null || this.f1938a.contains(absBYDAutoEnergyListener)) {
            return;
        }
        this.f1938a.add(absBYDAutoEnergyListener);
    }
}
