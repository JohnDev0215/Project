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
    public final List<AbsBYDAutoEnergyListener> f4486a = new ArrayList();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Handler f4487b;

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
        this.f4487b = new a(handlerThread.getLooper());
    }

    public void a(Message message) {
        if (this.f4486a.isEmpty()) {
        }
        int i8 = message.what;
        int i9 = message.arg1;
        switch (i8) {
            case TsNormalNum.THOUSAND_SIX /* 6000 */:
                Iterator<AbsBYDAutoEnergyListener> it = this.f4486a.iterator();
                while (it.hasNext()) {
                    it.next().onEPedalStateChanged(i9);
                }
                break;
            case 6001:
                Iterator<AbsBYDAutoEnergyListener> it2 = this.f4486a.iterator();
                while (it2.hasNext()) {
                    it2.next().onEnergyFeedbackLevelChanged(i9);
                }
                break;
            case 6002:
                Iterator<AbsBYDAutoEnergyListener> it3 = this.f4486a.iterator();
                while (it3.hasNext()) {
                    it3.next().onDriveDistanceShowStandardChanged(i9);
                }
                break;
            case 6003:
                Iterator<AbsBYDAutoEnergyListener> it4 = this.f4486a.iterator();
                while (it4.hasNext()) {
                    it4.next().onMemoryMaxEvStateChanged(i9);
                }
                break;
            case 6004:
                Iterator<AbsBYDAutoEnergyListener> it5 = this.f4486a.iterator();
                while (it5.hasNext()) {
                    it5.next().onElectricShowModeChanged(i9);
                }
                break;
            case 6005:
                Iterator<AbsBYDAutoEnergyListener> it6 = this.f4486a.iterator();
                while (it6.hasNext()) {
                    it6.next().onMemorySportStateChanged(i9);
                }
                break;
            case 6006:
                Iterator<AbsBYDAutoEnergyListener> it7 = this.f4486a.iterator();
                while (it7.hasNext()) {
                    it7.next().onMandatoryElectricStateChanged(i9);
                }
                break;
            case 6007:
                Iterator<AbsBYDAutoEnergyListener> it8 = this.f4486a.iterator();
                while (it8.hasNext()) {
                    it8.next().onSOCTargetChanged(i9);
                }
                break;
            case 6008:
                Iterator<AbsBYDAutoEnergyListener> it9 = this.f4486a.iterator();
                while (it9.hasNext()) {
                    it9.next().onMemoryElectricStateChanged(i9);
                }
                break;
            case 6009:
                Iterator<AbsBYDAutoEnergyListener> it10 = this.f4486a.iterator();
                while (it10.hasNext()) {
                    it10.next().onDriveDistanceShowModeChanged(i9);
                }
                break;
            case 6010:
                Iterator<AbsBYDAutoEnergyListener> it11 = this.f4486a.iterator();
                while (it11.hasNext()) {
                    it11.next().onPreSaleModelStateChanged(i9);
                }
                break;
            case 6011:
                Iterator<AbsBYDAutoEnergyListener> it12 = this.f4486a.iterator();
                while (it12.hasNext()) {
                    it12.next().onResetCurveDataChanged(i9, 0);
                }
                break;
        }
    }

    public void b(int i8, int i9) {
        if (this.f4487b != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = i8;
            messageObtain.arg1 = i9;
            messageObtain.arg2 = 0;
            this.f4487b.sendMessage(messageObtain);
        }
    }

    public void c(AbsBYDAutoEnergyListener absBYDAutoEnergyListener) {
        if (absBYDAutoEnergyListener != null) {
            this.f4486a.remove(absBYDAutoEnergyListener);
        }
    }

    public void d(AbsBYDAutoEnergyListener absBYDAutoEnergyListener) {
        if (absBYDAutoEnergyListener == null || this.f4486a.contains(absBYDAutoEnergyListener)) {
            return;
        }
        this.f4486a.add(absBYDAutoEnergyListener);
    }
}
