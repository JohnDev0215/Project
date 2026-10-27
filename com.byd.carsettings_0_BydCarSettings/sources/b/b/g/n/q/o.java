package b.b.g.n.q;

import android.content.Context;
import android.hardware.bydauto.statistic.AbsBYDAutoStatisticListener;
import android.hardware.bydauto.statistic.BYDAutoStatisticDevice;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: compiled from: BYDAutoStatisticDeviceService.java */
/* JADX INFO: loaded from: classes2.dex */
public class o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static o f1290a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public BYDAutoStatisticDevice f1291b;

    public static synchronized o m() {
        if (f1290a == null) {
            f1290a = new o();
        }
        return f1290a;
    }

    public int A(int i) {
        BYDAutoStatisticDevice bYDAutoStatisticDevice = this.f1291b;
        if (bYDAutoStatisticDevice != null) {
            return bYDAutoStatisticDevice.setRoadCongested(i);
        }
        return 0;
    }

    public int B(int i) {
        BYDAutoStatisticDevice bYDAutoStatisticDevice = this.f1291b;
        if (bYDAutoStatisticDevice != null) {
            return bYDAutoStatisticDevice.setRoadConstruction(i);
        }
        return 0;
    }

    public int C(int i) {
        BYDAutoStatisticDevice bYDAutoStatisticDevice = this.f1291b;
        if (bYDAutoStatisticDevice != null) {
            return bYDAutoStatisticDevice.setRoadImpassable(i);
        }
        return 0;
    }

    public int D(int i) {
        BYDAutoStatisticDevice bYDAutoStatisticDevice = this.f1291b;
        if (bYDAutoStatisticDevice != null) {
            return bYDAutoStatisticDevice.setTrafficSignType(i);
        }
        return 0;
    }

    public void E(AbsBYDAutoStatisticListener absBYDAutoStatisticListener) {
        if (this.f1291b == null) {
            return;
        }
        String simpleName = o.class.getSimpleName();
        StringBuilder sb = new StringBuilder();
        sb.append("unregisterListener BydStatisticListener start listener == null : ");
        sb.append(absBYDAutoStatisticListener == null);
        BydLogHelper.logD(simpleName, sb.toString());
        this.f1291b.unregisterListener(absBYDAutoStatisticListener);
    }

    public double a(int i) {
        if (this.f1291b != null) {
            return r0.getAverageSpeed(i);
        }
        return 0.0d;
    }

    public double b() {
        if (this.f1291b != null) {
            return r0.getAverageSpeedValue();
        }
        return 0.0d;
    }

    public float c() {
        BYDAutoStatisticDevice bYDAutoStatisticDevice = this.f1291b;
        if (bYDAutoStatisticDevice != null) {
            return bYDAutoStatisticDevice.getBatteryPackEnergy();
        }
        return 0.0f;
    }

    public double d() {
        BYDAutoStatisticDevice bYDAutoStatisticDevice = this.f1291b;
        if (bYDAutoStatisticDevice != null) {
            return bYDAutoStatisticDevice.getDrivingTimeValue();
        }
        return 0.0d;
    }

    public int e() {
        BYDAutoStatisticDevice bYDAutoStatisticDevice = this.f1291b;
        if (bYDAutoStatisticDevice != null) {
            return bYDAutoStatisticDevice.getEVMileageValue();
        }
        return 0;
    }

    public float f() {
        BYDAutoStatisticDevice bYDAutoStatisticDevice = this.f1291b;
        if (bYDAutoStatisticDevice != null) {
            return bYDAutoStatisticDevice.getEVRemainingBatteryPower();
        }
        return 0.0f;
    }

    public int g() {
        BYDAutoStatisticDevice bYDAutoStatisticDevice = this.f1291b;
        if (bYDAutoStatisticDevice != null) {
            return bYDAutoStatisticDevice.getElecDrivingRangeByStandard();
        }
        return 0;
    }

    public int h() {
        BYDAutoStatisticDevice bYDAutoStatisticDevice = this.f1291b;
        if (bYDAutoStatisticDevice != null) {
            return bYDAutoStatisticDevice.getElecDrivingRangeValue();
        }
        return 0;
    }

    public double i() {
        BYDAutoStatisticDevice bYDAutoStatisticDevice = this.f1291b;
        if (bYDAutoStatisticDevice != null) {
            return bYDAutoStatisticDevice.getElecPercentageValue();
        }
        return 0.0d;
    }

    public int j() {
        BYDAutoStatisticDevice bYDAutoStatisticDevice = this.f1291b;
        if (bYDAutoStatisticDevice != null) {
            return bYDAutoStatisticDevice.getFuelDrivingRangeValue();
        }
        return 0;
    }

    public int k() {
        BYDAutoStatisticDevice bYDAutoStatisticDevice = this.f1291b;
        if (bYDAutoStatisticDevice != null) {
            return bYDAutoStatisticDevice.getFuelPercentageValue();
        }
        return 0;
    }

    public int l() {
        BYDAutoStatisticDevice bYDAutoStatisticDevice = this.f1291b;
        if (bYDAutoStatisticDevice != null) {
            return bYDAutoStatisticDevice.getHEVMileageValue();
        }
        return 0;
    }

    public int n() {
        BYDAutoStatisticDevice bYDAutoStatisticDevice = this.f1291b;
        if (bYDAutoStatisticDevice != null) {
            return bYDAutoStatisticDevice.getKeyBatteryLevel();
        }
        return 0;
    }

    public float o(int i) {
        BYDAutoStatisticDevice bYDAutoStatisticDevice = this.f1291b;
        if (bYDAutoStatisticDevice != null) {
            return bYDAutoStatisticDevice.getMileageNumber(i);
        }
        return 0.0f;
    }

    public float p() {
        BYDAutoStatisticDevice bYDAutoStatisticDevice = this.f1291b;
        if (bYDAutoStatisticDevice != null) {
            return bYDAutoStatisticDevice.getRemainingBatteryPower();
        }
        return 0.0f;
    }

    public double q() {
        BYDAutoStatisticDevice bYDAutoStatisticDevice = this.f1291b;
        if (bYDAutoStatisticDevice != null) {
            return bYDAutoStatisticDevice.getTotalElecConPHMValue();
        }
        return 0.0d;
    }

    public double r() {
        BYDAutoStatisticDevice bYDAutoStatisticDevice = this.f1291b;
        if (bYDAutoStatisticDevice != null) {
            return bYDAutoStatisticDevice.getTotalElecConValue();
        }
        return 0.0d;
    }

    public double s() {
        BYDAutoStatisticDevice bYDAutoStatisticDevice = this.f1291b;
        if (bYDAutoStatisticDevice != null) {
            return bYDAutoStatisticDevice.getTotalFuelConPHMValue();
        }
        return 0.0d;
    }

    public double t() {
        BYDAutoStatisticDevice bYDAutoStatisticDevice = this.f1291b;
        if (bYDAutoStatisticDevice != null) {
            return bYDAutoStatisticDevice.getTotalFuelConValue();
        }
        return 0.0d;
    }

    public int u() {
        BYDAutoStatisticDevice bYDAutoStatisticDevice = this.f1291b;
        if (bYDAutoStatisticDevice == null) {
            return 0;
        }
        float totalMileageValue = bYDAutoStatisticDevice.getTotalMileageValue();
        StringBuilder sb = new StringBuilder();
        sb.append(totalMileageValue);
        sb.append("----getTotalMileageValue----");
        int i = (int) totalMileageValue;
        sb.append(i);
        BydLogHelper.logI("getTotalMileageValue", sb.toString());
        return i;
    }

    public void v(Context context) {
        this.f1291b = BYDAutoStatisticDevice.getInstance(context);
    }

    public void w(AbsBYDAutoStatisticListener absBYDAutoStatisticListener) {
        if (this.f1291b == null) {
            return;
        }
        String simpleName = o.class.getSimpleName();
        StringBuilder sb = new StringBuilder();
        sb.append("registerListener BydStatisticListener start listener == null : ");
        sb.append(absBYDAutoStatisticListener == null);
        BydLogHelper.logD(simpleName, sb.toString());
        this.f1291b.registerListener(absBYDAutoStatisticListener);
    }

    public int x(int i, int i2, int i3, int i4) {
        BYDAutoStatisticDevice bYDAutoStatisticDevice = this.f1291b;
        if (bYDAutoStatisticDevice != null) {
            return bYDAutoStatisticDevice.resetStatisticDate(i, i2, i3, i4);
        }
        return 0;
    }

    public int y(int[] iArr) {
        BYDAutoStatisticDevice bYDAutoStatisticDevice = this.f1291b;
        if (bYDAutoStatisticDevice != null) {
            return bYDAutoStatisticDevice.sendIsaDataToAdas(iArr);
        }
        return 0;
    }

    public int z(int i) {
        BYDAutoStatisticDevice bYDAutoStatisticDevice = this.f1291b;
        if (bYDAutoStatisticDevice != null) {
            return bYDAutoStatisticDevice.setRoadAccident(i);
        }
        return 0;
    }
}
