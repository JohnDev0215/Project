package b.b.g.k;

import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.energy.AbsBYDAutoEnergyListener;
import com.bydux.api.common.tools.log.BydLogHelper;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: compiled from: BydEnergyListener.java */
/* JADX INFO: loaded from: classes2.dex */
public class j extends AbsBYDAutoEnergyListener {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f850b = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f851c = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f852d = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f853e = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f854f = Integer.MIN_VALUE;
    public int g = Integer.MIN_VALUE;
    public int h = Integer.MIN_VALUE;
    public int i = Integer.MIN_VALUE;
    public int j = Integer.MIN_VALUE;
    public int k = Integer.MIN_VALUE;
    public int l = Integer.MIN_VALUE;
    public float m = Float.MIN_VALUE;
    public float n = Float.MIN_VALUE;
    public int o = Integer.MIN_VALUE;
    public int p = Integer.MIN_VALUE;
    public int q = Integer.MIN_VALUE;
    public int r = Integer.MIN_VALUE;
    public int s = Integer.MIN_VALUE;
    public int t = Integer.MIN_VALUE;

    @Override // android.hardware.bydauto.energy.AbsBYDAutoEnergyListener
    public void onBCMStateChanged(int i) {
        super.onBCMStateChanged(i);
    }

    @Override // android.hardware.bydauto.energy.AbsBYDAutoEnergyListener
    public void onChangePHintChanged(int i) {
        if (this.q == i) {
            return;
        }
        this.q = i;
        super.onChangePHintChanged(i);
        EventBus.getDefault().post(new b.b.a.c.a(150026, i));
    }

    @Override // android.hardware.bydauto.energy.AbsBYDAutoEnergyListener
    public void onDCWorkModeChanged(int i) {
        super.onDCWorkModeChanged(i);
    }

    @Override // android.hardware.bydauto.energy.AbsBYDAutoEnergyListener
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
        super.onDataEventChanged(i, bYDAutoEventValue);
    }

    @Override // android.hardware.bydauto.energy.AbsBYDAutoEnergyListener
    public void onDriveDistanceShowModeChanged(int i) {
        BydLogHelper.logI("BydEnergyListener", "onDriveDistanceShowModeChanged----" + i);
        if (this.j == i) {
            return;
        }
        this.j = i;
        super.onDriveDistanceShowModeChanged(i);
        if (b.b.g.r.e.c(150018)) {
            return;
        }
        EventBus.getDefault().post(new b.b.a.c.a(150018, i));
    }

    @Override // android.hardware.bydauto.energy.AbsBYDAutoEnergyListener
    public void onDriveDistanceShowStandardChanged(int i) {
        if (this.f854f == i) {
            return;
        }
        this.f854f = i;
        super.onDriveDistanceShowStandardChanged(i);
        if (b.b.g.r.e.c(150012)) {
            return;
        }
        EventBus.getDefault().post(new b.b.a.c.a(150012, i));
    }

    @Override // android.hardware.bydauto.energy.AbsBYDAutoEnergyListener
    public void onEPedalStateChanged(int i) {
        if (this.f851c == i) {
            return;
        }
        this.f851c = i;
        super.onEPedalStateChanged(i);
        if (b.b.g.r.e.c(150009)) {
            return;
        }
        EventBus.getDefault().post(new b.b.a.c.a(150009, i));
    }

    @Override // android.hardware.bydauto.energy.AbsBYDAutoEnergyListener
    public void onElectricPointFlagChanged(int i) {
        super.onElectricPointFlagChanged(i);
    }

    @Override // android.hardware.bydauto.energy.AbsBYDAutoEnergyListener
    public void onElectricShowModeChanged(int i) {
        if (this.f852d == i) {
            return;
        }
        this.f852d = i;
        super.onElectricShowModeChanged(i);
        if (b.b.g.r.e.c(150010)) {
            return;
        }
        EventBus.getDefault().post(new b.b.a.c.a(150010, i));
    }

    @Override // android.hardware.bydauto.energy.AbsBYDAutoEnergyListener
    public void onEnergyModeChanged(int i) {
        if (this.i == i) {
            return;
        }
        this.i = i;
        super.onEnergyModeChanged(i);
        EventBus.getDefault().post(new b.b.a.c.a(150001, i));
    }

    @Override // android.hardware.bydauto.energy.AbsBYDAutoEnergyListener
    public void onEnergyModelChanged(int i) {
        super.onEnergyModelChanged(i);
    }

    @Override // android.hardware.bydauto.energy.AbsBYDAutoEnergyListener
    public void onEnergyStateChanged(int i) {
        if (this.f850b == i) {
            return;
        }
        this.f850b = i;
        super.onEnergyStateChanged(i);
        EventBus.getDefault().post(new b.b.a.c.a(150000, i));
    }

    @Override // android.hardware.bydauto.energy.AbsBYDAutoEnergyListener
    public void onError(int i, String str) {
        super.onError(i, str);
    }

    @Override // android.hardware.bydauto.energy.AbsBYDAutoEnergyListener
    public void onHEVElectricPointFlagChanged(int i) {
        super.onHEVElectricPointFlagChanged(i);
    }

    @Override // android.hardware.bydauto.energy.AbsBYDAutoEnergyListener
    public void onHEVLast200MElectricConChanged(float f2) {
        super.onHEVLast200MElectricConChanged(f2);
    }

    @Override // android.hardware.bydauto.energy.AbsBYDAutoEnergyListener
    public void onLast200MElectricConChanged(float f2) {
        super.onLast200MElectricConChanged(f2);
    }

    @Override // android.hardware.bydauto.energy.AbsBYDAutoEnergyListener
    public void onLast200MOilConChanged(float f2) {
        super.onLast200MOilConChanged(f2);
    }

    @Override // android.hardware.bydauto.energy.AbsBYDAutoEnergyListener
    public void onLast50KMElectricConAvgChanged(float f2) {
        if (this.m == f2) {
            return;
        }
        this.m = f2;
        super.onLast50KMElectricConAvgChanged(f2);
        EventBus.getDefault().post(new b.b.a.c.a(150022, f2));
    }

    @Override // android.hardware.bydauto.energy.AbsBYDAutoEnergyListener
    public void onLast50KMOilConAvgChanged(float f2) {
        if (this.n == f2) {
            return;
        }
        this.n = f2;
        super.onLast50KMOilConAvgChanged(f2);
        EventBus.getDefault().post(new b.b.a.c.a(150023, f2));
    }

    @Override // android.hardware.bydauto.energy.AbsBYDAutoEnergyListener
    public void onMandatoryElectricStateChanged(int i) {
        if (this.f853e == i) {
            return;
        }
        this.f853e = i;
        super.onMandatoryElectricStateChanged(i);
        if (b.b.g.r.e.c(150011)) {
            return;
        }
        EventBus.getDefault().post(new b.b.a.c.a(150011, i));
    }

    @Override // android.hardware.bydauto.energy.AbsBYDAutoEnergyListener
    public void onMemoryElectricStateChanged(int i) {
        if (this.k == i) {
            return;
        }
        this.k = i;
        super.onMemoryElectricStateChanged(i);
        if (b.b.g.r.e.c(150019)) {
            return;
        }
        EventBus.getDefault().post(new b.b.a.c.a(150019, i));
    }

    @Override // android.hardware.bydauto.energy.AbsBYDAutoEnergyListener
    public void onMemoryMaxEvStateChanged(int i) {
        if (this.h == i) {
            return;
        }
        this.h = i;
        super.onMemoryMaxEvStateChanged(i);
        if (b.b.g.r.e.c(150014)) {
            return;
        }
        EventBus.getDefault().post(new b.b.a.c.a(150014, i));
    }

    @Override // android.hardware.bydauto.energy.AbsBYDAutoEnergyListener
    public void onMemorySportStateChanged(int i) {
        if (this.g == i) {
            return;
        }
        this.g = i;
        super.onMemorySportStateChanged(i);
        if (b.b.g.r.e.c(150013)) {
            return;
        }
        EventBus.getDefault().post(new b.b.a.c.a(150013, i));
    }

    @Override // android.hardware.bydauto.energy.AbsBYDAutoEnergyListener
    public void onOilPointFlagChanged(int i) {
        super.onOilPointFlagChanged(i);
    }

    @Override // android.hardware.bydauto.energy.AbsBYDAutoEnergyListener
    public void onOperationModeChanged(int i) {
        super.onOperationModeChanged(i);
    }

    @Override // android.hardware.bydauto.energy.AbsBYDAutoEnergyListener
    public void onPowerGenerationStateChanged(int i) {
        super.onPowerGenerationStateChanged(i);
    }

    @Override // android.hardware.bydauto.energy.AbsBYDAutoEnergyListener
    public void onPowerGenerationValueChanged(int i) {
        super.onPowerGenerationValueChanged(i);
    }

    @Override // android.hardware.bydauto.energy.AbsBYDAutoEnergyListener
    public void onPreSaleModelStateChanged(int i) {
        if (this.l == i) {
            return;
        }
        this.l = i;
        super.onPreSaleModelStateChanged(i);
        if (b.b.g.r.e.c(150020)) {
            return;
        }
        EventBus.getDefault().post(new b.b.a.c.a(150020, i));
    }

    @Override // android.hardware.bydauto.energy.AbsBYDAutoEnergyListener
    public void onResetCurveDataChanged(int i, int i2) {
        super.onResetCurveDataChanged(i, i2);
    }

    @Override // android.hardware.bydauto.energy.AbsBYDAutoEnergyListener
    public void onRoadSurfaceChanged(int i) {
        super.onRoadSurfaceChanged(i);
    }

    @Override // android.hardware.bydauto.energy.AbsBYDAutoEnergyListener
    public void onRoadSurfaceKindChanged(int i) {
        super.onRoadSurfaceKindChanged(i);
    }

    @Override // android.hardware.bydauto.energy.AbsBYDAutoEnergyListener
    public void onRollBenchModeStatusChanged(int i) {
        super.onRollBenchModeStatusChanged(i);
        if (this.t == i) {
            return;
        }
        this.t = i;
        if (b.b.g.r.e.c(10012)) {
            return;
        }
        EventBus.getDefault().post(new b.b.a.c.a(10012, i));
    }

    @Override // android.hardware.bydauto.energy.AbsBYDAutoEnergyListener
    public void oniTACAllowTagChanged(int i) {
        if (this.p == i) {
            return;
        }
        this.p = i;
        super.oniTACAllowTagChanged(i);
        EventBus.getDefault().post(new b.b.a.c.a(150024, i));
    }

    @Override // android.hardware.bydauto.energy.AbsBYDAutoEnergyListener
    public void oniTACExitTagChanged(int i) {
        if (this.r == i) {
            return;
        }
        this.r = i;
        super.oniTACExitTagChanged(i);
        EventBus.getDefault().post(new b.b.a.c.a(150027, i));
    }

    @Override // android.hardware.bydauto.energy.AbsBYDAutoEnergyListener
    public void oniTACModeChanged(int i) {
        if (this.s == i) {
            return;
        }
        this.s = i;
        super.oniTACModeChanged(i);
        if (b.b.g.r.e.c(150028)) {
            return;
        }
        EventBus.getDefault().post(new b.b.a.c.a(150028, i));
    }

    @Override // android.hardware.bydauto.energy.AbsBYDAutoEnergyListener
    public void oniTACStateChanged(int i) {
        if (this.o == i) {
            return;
        }
        this.o = i;
        super.oniTACStateChanged(i);
        if (b.b.g.r.e.c(150025)) {
            return;
        }
        EventBus.getDefault().post(new b.b.a.c.a(150025, i));
    }
}
