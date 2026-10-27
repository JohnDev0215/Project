package a.b.f;

import android.hardware.bydauto.TsNormalNum;
import android.hardware.bydauto.energy.AbsBYDAutoEnergyListener;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final List<AbsBYDAutoEnergyListener> f26a = new ArrayList();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Handler f27b;

    /* JADX INFO: compiled from: caradapter-bug */
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
        this.f27b = new a(handlerThread.getLooper());
    }

    public void a(Message message) {
        if (this.f26a.isEmpty()) {
        }
        int i = message.what;
        int i2 = message.arg1;
        switch (i) {
            case TsNormalNum.THOUSAND_SIX /* 6000 */:
                Iterator<AbsBYDAutoEnergyListener> it = this.f26a.iterator();
                while (it.hasNext()) {
                    it.next().onEPedalStateChanged(i2);
                }
                break;
            case 6001:
                Iterator<AbsBYDAutoEnergyListener> it2 = this.f26a.iterator();
                while (it2.hasNext()) {
                    it2.next().onEnergyFeedbackLevelChanged(i2);
                }
                break;
            case 6002:
                Iterator<AbsBYDAutoEnergyListener> it3 = this.f26a.iterator();
                while (it3.hasNext()) {
                    it3.next().onDriveDistanceShowStandardChanged(i2);
                }
                break;
            case 6003:
                Iterator<AbsBYDAutoEnergyListener> it4 = this.f26a.iterator();
                while (it4.hasNext()) {
                    it4.next().onMemoryMaxEvStateChanged(i2);
                }
                break;
            case 6004:
                Iterator<AbsBYDAutoEnergyListener> it5 = this.f26a.iterator();
                while (it5.hasNext()) {
                    it5.next().onElectricShowModeChanged(i2);
                }
                break;
            case 6005:
                Iterator<AbsBYDAutoEnergyListener> it6 = this.f26a.iterator();
                while (it6.hasNext()) {
                    it6.next().onMemorySportStateChanged(i2);
                }
                break;
            case 6006:
                Iterator<AbsBYDAutoEnergyListener> it7 = this.f26a.iterator();
                while (it7.hasNext()) {
                    it7.next().onMandatoryElectricStateChanged(i2);
                }
                break;
            case 6007:
                Iterator<AbsBYDAutoEnergyListener> it8 = this.f26a.iterator();
                while (it8.hasNext()) {
                    it8.next().onSOCTargetChanged(i2);
                }
                break;
            case 6008:
                Iterator<AbsBYDAutoEnergyListener> it9 = this.f26a.iterator();
                while (it9.hasNext()) {
                    it9.next().onMemoryElectricStateChanged(i2);
                }
                break;
            case 6009:
                Iterator<AbsBYDAutoEnergyListener> it10 = this.f26a.iterator();
                while (it10.hasNext()) {
                    it10.next().onDriveDistanceShowModeChanged(i2);
                }
                break;
            case 6010:
                Iterator<AbsBYDAutoEnergyListener> it11 = this.f26a.iterator();
                while (it11.hasNext()) {
                    it11.next().onPreSaleModelStateChanged(i2);
                }
                break;
            case 6011:
                Iterator<AbsBYDAutoEnergyListener> it12 = this.f26a.iterator();
                while (it12.hasNext()) {
                    it12.next().onResetCurveDataChanged(i2, 0);
                }
                break;
        }
    }

    public void b(int i, int i2) {
        if (this.f27b != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = i;
            messageObtain.arg1 = i2;
            messageObtain.arg2 = 0;
            this.f27b.sendMessage(messageObtain);
        }
    }

    public void c(AbsBYDAutoEnergyListener absBYDAutoEnergyListener) {
        if (absBYDAutoEnergyListener != null) {
            this.f26a.remove(absBYDAutoEnergyListener);
        }
    }

    public void d(AbsBYDAutoEnergyListener absBYDAutoEnergyListener) {
        if (absBYDAutoEnergyListener == null || this.f26a.contains(absBYDAutoEnergyListener)) {
            return;
        }
        this.f26a.add(absBYDAutoEnergyListener);
    }
}
