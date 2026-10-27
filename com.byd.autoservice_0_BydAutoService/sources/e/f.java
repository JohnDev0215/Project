package e;

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
    public final List<AbsBYDAutoEnergyListener> f2758a = new ArrayList();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Handler f2759b;

    /* JADX INFO: compiled from: TestEngergyManagerImplCallback.java */
    public class a extends Handler {
        public a(Looper looper) {
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
        this.f2759b = new a(handlerThread.getLooper());
    }

    public void a(Message message) {
        if (this.f2758a.isEmpty()) {
        }
        int i3 = message.what;
        int i4 = message.arg1;
        switch (i3) {
            case TsNormalNum.THOUSAND_SIX /* 6000 */:
                Iterator<AbsBYDAutoEnergyListener> it = this.f2758a.iterator();
                while (it.hasNext()) {
                    it.next().onEPedalStateChanged(i4);
                }
                break;
            case 6001:
                Iterator<AbsBYDAutoEnergyListener> it2 = this.f2758a.iterator();
                while (it2.hasNext()) {
                    it2.next().onEnergyFeedbackLevelChanged(i4);
                }
                break;
            case 6002:
                Iterator<AbsBYDAutoEnergyListener> it3 = this.f2758a.iterator();
                while (it3.hasNext()) {
                    it3.next().onDriveDistanceShowStandardChanged(i4);
                }
                break;
            case 6003:
                Iterator<AbsBYDAutoEnergyListener> it4 = this.f2758a.iterator();
                while (it4.hasNext()) {
                    it4.next().onMemoryMaxEvStateChanged(i4);
                }
                break;
            case 6004:
                Iterator<AbsBYDAutoEnergyListener> it5 = this.f2758a.iterator();
                while (it5.hasNext()) {
                    it5.next().onElectricShowModeChanged(i4);
                }
                break;
            case 6005:
                Iterator<AbsBYDAutoEnergyListener> it6 = this.f2758a.iterator();
                while (it6.hasNext()) {
                    it6.next().onMemorySportStateChanged(i4);
                }
                break;
            case 6006:
                Iterator<AbsBYDAutoEnergyListener> it7 = this.f2758a.iterator();
                while (it7.hasNext()) {
                    it7.next().onMandatoryElectricStateChanged(i4);
                }
                break;
            case 6007:
                Iterator<AbsBYDAutoEnergyListener> it8 = this.f2758a.iterator();
                while (it8.hasNext()) {
                    it8.next().onSOCTargetChanged(i4);
                }
                break;
            case 6008:
                Iterator<AbsBYDAutoEnergyListener> it9 = this.f2758a.iterator();
                while (it9.hasNext()) {
                    it9.next().onMemoryElectricStateChanged(i4);
                }
                break;
            case 6009:
                Iterator<AbsBYDAutoEnergyListener> it10 = this.f2758a.iterator();
                while (it10.hasNext()) {
                    it10.next().onDriveDistanceShowModeChanged(i4);
                }
                break;
            case 6010:
                Iterator<AbsBYDAutoEnergyListener> it11 = this.f2758a.iterator();
                while (it11.hasNext()) {
                    it11.next().onPreSaleModelStateChanged(i4);
                }
                break;
            case 6011:
                Iterator<AbsBYDAutoEnergyListener> it12 = this.f2758a.iterator();
                while (it12.hasNext()) {
                    it12.next().onResetCurveDataChanged(i4, 0);
                }
                break;
        }
    }

    public void b(int i3, int i4) {
        if (this.f2759b != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = i3;
            messageObtain.arg1 = i4;
            messageObtain.arg2 = 0;
            this.f2759b.sendMessage(messageObtain);
        }
    }

    public void c(AbsBYDAutoEnergyListener absBYDAutoEnergyListener) {
        if (absBYDAutoEnergyListener != null) {
            this.f2758a.remove(absBYDAutoEnergyListener);
        }
    }

    public void d(AbsBYDAutoEnergyListener absBYDAutoEnergyListener) {
        if (absBYDAutoEnergyListener == null || this.f2758a.contains(absBYDAutoEnergyListener)) {
            return;
        }
        this.f2758a.add(absBYDAutoEnergyListener);
    }
}
