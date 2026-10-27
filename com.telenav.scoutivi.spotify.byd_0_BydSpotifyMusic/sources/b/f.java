package b;

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
    private final List<AbsBYDAutoEnergyListener> f3438a = new ArrayList();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Handler f3439b;

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
        this.f3439b = new a(handlerThread.getLooper());
    }

    public void a(Message message) {
        if (this.f3438a.isEmpty()) {
        }
        int i7 = message.what;
        int i8 = message.arg1;
        switch (i7) {
            case TsNormalNum.THOUSAND_SIX /* 6000 */:
                Iterator<AbsBYDAutoEnergyListener> it = this.f3438a.iterator();
                while (it.hasNext()) {
                    it.next().onEPedalStateChanged(i8);
                }
                break;
            case 6001:
                Iterator<AbsBYDAutoEnergyListener> it2 = this.f3438a.iterator();
                while (it2.hasNext()) {
                    it2.next().onEnergyFeedbackLevelChanged(i8);
                }
                break;
            case 6002:
                Iterator<AbsBYDAutoEnergyListener> it3 = this.f3438a.iterator();
                while (it3.hasNext()) {
                    it3.next().onDriveDistanceShowStandardChanged(i8);
                }
                break;
            case 6003:
                Iterator<AbsBYDAutoEnergyListener> it4 = this.f3438a.iterator();
                while (it4.hasNext()) {
                    it4.next().onMemoryMaxEvStateChanged(i8);
                }
                break;
            case 6004:
                Iterator<AbsBYDAutoEnergyListener> it5 = this.f3438a.iterator();
                while (it5.hasNext()) {
                    it5.next().onElectricShowModeChanged(i8);
                }
                break;
            case 6005:
                Iterator<AbsBYDAutoEnergyListener> it6 = this.f3438a.iterator();
                while (it6.hasNext()) {
                    it6.next().onMemorySportStateChanged(i8);
                }
                break;
            case 6006:
                Iterator<AbsBYDAutoEnergyListener> it7 = this.f3438a.iterator();
                while (it7.hasNext()) {
                    it7.next().onMandatoryElectricStateChanged(i8);
                }
                break;
            case 6007:
                Iterator<AbsBYDAutoEnergyListener> it8 = this.f3438a.iterator();
                while (it8.hasNext()) {
                    it8.next().onSOCTargetChanged(i8);
                }
                break;
            case 6008:
                Iterator<AbsBYDAutoEnergyListener> it9 = this.f3438a.iterator();
                while (it9.hasNext()) {
                    it9.next().onMemoryElectricStateChanged(i8);
                }
                break;
            case 6009:
                Iterator<AbsBYDAutoEnergyListener> it10 = this.f3438a.iterator();
                while (it10.hasNext()) {
                    it10.next().onDriveDistanceShowModeChanged(i8);
                }
                break;
            case 6010:
                Iterator<AbsBYDAutoEnergyListener> it11 = this.f3438a.iterator();
                while (it11.hasNext()) {
                    it11.next().onPreSaleModelStateChanged(i8);
                }
                break;
            case 6011:
                Iterator<AbsBYDAutoEnergyListener> it12 = this.f3438a.iterator();
                while (it12.hasNext()) {
                    it12.next().onResetCurveDataChanged(i8, 0);
                }
                break;
        }
    }

    public void b(int i7, int i8) {
        if (this.f3439b != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = i7;
            messageObtain.arg1 = i8;
            messageObtain.arg2 = 0;
            this.f3439b.sendMessage(messageObtain);
        }
    }

    public void c(AbsBYDAutoEnergyListener absBYDAutoEnergyListener) {
        if (absBYDAutoEnergyListener != null) {
            this.f3438a.remove(absBYDAutoEnergyListener);
        }
    }

    public void d(AbsBYDAutoEnergyListener absBYDAutoEnergyListener) {
        if (absBYDAutoEnergyListener == null || this.f3438a.contains(absBYDAutoEnergyListener)) {
            return;
        }
        this.f3438a.add(absBYDAutoEnergyListener);
    }
}
