package b.b.g.k;

import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.statistic.AbsBYDAutoStatisticListener;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: compiled from: BydStatisticListener.java */
/* JADX INFO: loaded from: classes2.dex */
public class p extends AbsBYDAutoStatisticListener {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public float f862b = Float.MIN_VALUE;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public double f863c = Double.MIN_VALUE;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public double f864d = Double.MIN_VALUE;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f865e = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f866f = Integer.MIN_VALUE;
    public double g = Double.MIN_VALUE;
    public double h = Double.MIN_VALUE;
    public int i = Integer.MIN_VALUE;
    public double j = Double.MIN_VALUE;
    public double k = Double.MIN_VALUE;
    public double l = Double.MIN_VALUE;
    public double m = Double.MIN_VALUE;
    public float n = Float.MIN_VALUE;

    @Override // android.hardware.bydauto.statistic.AbsBYDAutoStatisticListener
    public void onAverageElectricConsumptionChanged(int i, float f2) {
        super.onAverageElectricConsumptionChanged(i, f2);
    }

    @Override // android.hardware.bydauto.statistic.AbsBYDAutoStatisticListener
    public void onAverageFuelConsumptionChanged(int i, float f2) {
        super.onAverageFuelConsumptionChanged(i, f2);
    }

    @Override // android.hardware.bydauto.statistic.AbsBYDAutoStatisticListener
    public void onAverageSpeedChanged(int i) {
        if (this.i == i) {
            return;
        }
        this.i = i;
        super.onAverageSpeedChanged(i);
        EventBus.getDefault().post(new b.b.a.c.a(180007, i));
    }

    @Override // android.hardware.bydauto.statistic.AbsBYDAutoStatisticListener
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
        super.onDataEventChanged(i, bYDAutoEventValue);
    }

    @Override // android.hardware.bydauto.statistic.AbsBYDAutoStatisticListener
    public void onDrivingRangeAllChanged(int i) {
        double d2 = i;
        if (this.m == d2) {
            return;
        }
        this.m = d2;
        super.onDrivingRangeAllChanged(i);
        EventBus.getDefault().post(new b.b.a.c.a(180011, i));
    }

    @Override // android.hardware.bydauto.statistic.AbsBYDAutoStatisticListener
    public void onDrivingRangeUnitChanged(int i) {
        double d2 = i;
        if (this.l == d2) {
            return;
        }
        this.l = d2;
        super.onDrivingRangeUnitChanged(i);
        EventBus.getDefault().post(new b.b.a.c.a(180010, i));
    }

    @Override // android.hardware.bydauto.statistic.AbsBYDAutoStatisticListener
    public void onDrivingRangeValueChanged(int i) {
        super.onDrivingRangeValueChanged(i);
    }

    @Override // android.hardware.bydauto.statistic.AbsBYDAutoStatisticListener
    public void onDrivingTimeChanged(double d2) {
        if (this.j == d2) {
            return;
        }
        this.j = d2;
        super.onDrivingTimeChanged(d2);
        EventBus.getDefault().post(new b.b.a.c.a(180008, d2));
    }

    @Override // android.hardware.bydauto.statistic.AbsBYDAutoStatisticListener
    public void onEVDrivingMileageConfigChanged(int i) {
        super.onEVDrivingMileageConfigChanged(i);
    }

    @Override // android.hardware.bydauto.statistic.AbsBYDAutoStatisticListener
    public void onEVDrivingMileageModeChanged(int i) {
        super.onEVDrivingMileageModeChanged(i);
    }

    @Override // android.hardware.bydauto.statistic.AbsBYDAutoStatisticListener
    public void onEVMileageValueChanged(int i) {
        super.onEVMileageValueChanged(i);
    }

    @Override // android.hardware.bydauto.statistic.AbsBYDAutoStatisticListener
    public void onElecDrivingRangeChanged(int i) {
        if (this.f866f == i) {
            return;
        }
        this.f866f = i;
        super.onElecDrivingRangeChanged(i);
        EventBus.getDefault().post(new b.b.a.c.a(180004, i));
    }

    @Override // android.hardware.bydauto.statistic.AbsBYDAutoStatisticListener
    public void onElecPercentageChanged(double d2) {
        if (this.k == d2) {
            return;
        }
        this.k = d2;
        super.onElecPercentageChanged(d2);
        EventBus.getDefault().post(new b.b.a.c.a(180009, d2));
    }

    @Override // android.hardware.bydauto.statistic.AbsBYDAutoStatisticListener
    public void onElectricConsumptionChanged(int i, float f2) {
        super.onElectricConsumptionChanged(i, f2);
    }

    @Override // android.hardware.bydauto.statistic.AbsBYDAutoStatisticListener
    public void onError(int i, String str) {
        super.onError(i, str);
    }

    @Override // android.hardware.bydauto.statistic.AbsBYDAutoStatisticListener
    public void onEvCurveFlagChanged(int i) {
        super.onEvCurveFlagChanged(i);
    }

    @Override // android.hardware.bydauto.statistic.AbsBYDAutoStatisticListener
    public void onEvLast200MChanged(int i) {
        super.onEvLast200MChanged(i);
    }

    @Override // android.hardware.bydauto.statistic.AbsBYDAutoStatisticListener
    public void onFuelADValueChanged(int i) {
        super.onFuelADValueChanged(i);
    }

    @Override // android.hardware.bydauto.statistic.AbsBYDAutoStatisticListener
    public void onFuelConsumptionChanged(int i, float f2) {
        super.onFuelConsumptionChanged(i, f2);
    }

    @Override // android.hardware.bydauto.statistic.AbsBYDAutoStatisticListener
    public void onFuelCurveFlagChanged(int i) {
        super.onFuelCurveFlagChanged(i);
    }

    @Override // android.hardware.bydauto.statistic.AbsBYDAutoStatisticListener
    public void onFuelDrivingRangeChanged(int i) {
        if (this.f865e == i) {
            return;
        }
        this.f865e = i;
        super.onFuelDrivingRangeChanged(i);
        EventBus.getDefault().post(new b.b.a.c.a(180003, i));
    }

    @Override // android.hardware.bydauto.statistic.AbsBYDAutoStatisticListener
    public void onFuelLast200MChanged(int i) {
        super.onFuelLast200MChanged(i);
    }

    @Override // android.hardware.bydauto.statistic.AbsBYDAutoStatisticListener
    public void onFuelPercentageChanged(int i) {
        super.onFuelPercentageChanged(i);
    }

    @Override // android.hardware.bydauto.statistic.AbsBYDAutoStatisticListener
    public void onHEVMileageValueChanged(int i) {
        super.onHEVMileageValueChanged(i);
    }

    @Override // android.hardware.bydauto.statistic.AbsBYDAutoStatisticListener
    public void onInstantElecConChanged(float f2) {
        super.onInstantElecConChanged(f2);
    }

    @Override // android.hardware.bydauto.statistic.AbsBYDAutoStatisticListener
    public void onInstantFuelConChanged(float f2) {
        super.onInstantFuelConChanged(f2);
    }

    @Override // android.hardware.bydauto.statistic.AbsBYDAutoStatisticListener
    public void onKeyBatteryLevelChanged(int i) {
        super.onKeyBatteryLevelChanged(i);
    }

    @Override // android.hardware.bydauto.statistic.AbsBYDAutoStatisticListener
    public void onLast50KmEqualFuelConValueChanged(float f2) {
        super.onLast50KmEqualFuelConValueChanged(f2);
    }

    @Override // android.hardware.bydauto.statistic.AbsBYDAutoStatisticListener
    public void onLastElecConPHMChanged(double d2) {
        super.onLastElecConPHMChanged(d2);
    }

    @Override // android.hardware.bydauto.statistic.AbsBYDAutoStatisticListener
    public void onLastFuelConPHMChanged(double d2) {
        super.onLastFuelConPHMChanged(d2);
    }

    @Override // android.hardware.bydauto.statistic.AbsBYDAutoStatisticListener
    public void onMessage5sOnlineStateChanged(int i, int i2) {
        super.onMessage5sOnlineStateChanged(i, i2);
    }

    @Override // android.hardware.bydauto.statistic.AbsBYDAutoStatisticListener
    public void onMileageNumberChanged(int i, float f2) {
        super.onMileageNumberChanged(i, f2);
    }

    @Override // android.hardware.bydauto.statistic.AbsBYDAutoStatisticListener
    public void onSOCBatteryPercentageChanged(int i) {
        super.onSOCBatteryPercentageChanged(i);
    }

    @Override // android.hardware.bydauto.statistic.AbsBYDAutoStatisticListener
    public void onSpeedSignalVDisValueChanged(int i) {
        super.onSpeedSignalVDisValueChanged(i);
    }

    @Override // android.hardware.bydauto.statistic.AbsBYDAutoStatisticListener
    public void onTotalElecConChanged(double d2) {
        if (this.g == d2) {
            return;
        }
        this.g = d2;
        super.onTotalElecConChanged(d2);
        EventBus.getDefault().post(new b.b.a.c.a(180005, d2));
    }

    @Override // android.hardware.bydauto.statistic.AbsBYDAutoStatisticListener
    public void onTotalElecConPHMChanged(double d2) {
        if (this.f864d == d2) {
            return;
        }
        this.f864d = d2;
        super.onTotalElecConPHMChanged(d2);
        EventBus.getDefault().post(new b.b.a.c.a(180002, d2));
    }

    @Override // android.hardware.bydauto.statistic.AbsBYDAutoStatisticListener
    public void onTotalElecConPhmUnitChanged(int i) {
        super.onTotalElecConPhmUnitChanged(i);
    }

    @Override // android.hardware.bydauto.statistic.AbsBYDAutoStatisticListener
    public void onTotalFuelConChanged(double d2) {
        if (this.h == d2) {
            return;
        }
        this.h = d2;
        super.onTotalFuelConChanged(d2);
        EventBus.getDefault().post(new b.b.a.c.a(180006, d2));
    }

    @Override // android.hardware.bydauto.statistic.AbsBYDAutoStatisticListener
    public void onTotalFuelConPHMChanged(double d2) {
        if (this.f863c == d2) {
            return;
        }
        this.f863c = d2;
        super.onTotalFuelConPHMChanged(d2);
        EventBus.getDefault().post(new b.b.a.c.a(180001, d2));
    }

    @Override // android.hardware.bydauto.statistic.AbsBYDAutoStatisticListener
    public void onTotalFuelConsumptionUnitChanged(int i) {
        super.onTotalFuelConsumptionUnitChanged(i);
    }

    @Override // android.hardware.bydauto.statistic.AbsBYDAutoStatisticListener
    public void onTotalMileageValueChanged(float f2) {
        if (this.f862b == f2) {
            return;
        }
        this.f862b = f2;
        super.onTotalMileageValueChanged(f2);
        EventBus.getDefault().post(new b.b.a.c.a(180000, (int) f2));
    }

    @Override // android.hardware.bydauto.statistic.AbsBYDAutoStatisticListener
    public void onTravelTimeChanged(int i, float f2) {
        super.onTravelTimeChanged(i, f2);
    }

    @Override // android.hardware.bydauto.statistic.AbsBYDAutoStatisticListener
    public void onWaterTemperatureChanged(int i) {
        super.onWaterTemperatureChanged(i);
    }

    @Override // android.hardware.bydauto.statistic.AbsBYDAutoStatisticListener
    public void ontKeyBatteryLevelChanged(int i) {
        super.ontKeyBatteryLevelChanged(i);
    }
}
