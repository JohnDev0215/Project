package b.a.a.f.i1;

import android.hardware.bydauto.ac.AbsBYDAutoAcListener;
import com.bydux.api.common.tools.log.BydLogHelper;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class c extends AbsBYDAutoAcListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f134a = -1;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f135b = -1;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f136c = -1;

    @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
    public void onACWorkModeChanged(int i, int i2) {
        super.onACWorkModeChanged(i, i2);
        BydLogHelper.logW("HvacListener", "onACWorkModeChanged area = " + i + " -- mode = " + i2);
        if (i != 0) {
            if (i != 1) {
                if (i == 4) {
                    if (i2 == this.f136c) {
                        return;
                    } else {
                        this.f136c = i2;
                    }
                }
            } else if (i2 == this.f135b) {
                return;
            } else {
                this.f135b = i2;
            }
        } else if (i2 == this.f134a) {
            return;
        } else {
            this.f134a = i2;
        }
        b.a.a.h.a.e(1032, i, i2);
    }

    @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
    public void onAcCompressorManualSignChanged(int i) {
        super.onAcCompressorManualSignChanged(i);
        BydLogHelper.logW("HvacListener", "onAcCompressorManualSignChanged sign = " + i);
        if (b.a.a.h.a.a(1014)) {
            return;
        }
        EventBus.getDefault().post(new b.a.a.e.a(1014, i));
    }

    @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
    public void onAcCompressorModeChanged(int i) {
        super.onAcCompressorModeChanged(i);
        BydLogHelper.logW("HvacListener", "onAcCompressorModeChanged mode = " + i);
        if (b.a.a.h.a.a(1004)) {
            return;
        }
        EventBus.getDefault().post(new b.a.a.e.a(1004, i));
    }

    @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
    public void onAcCtrlModeChanged(int i) {
        super.onAcCtrlModeChanged(i);
        BydLogHelper.logW("HvacListener", "onAcCtrlModeChanged mode = " + i);
        if (b.a.a.h.a.a(1003)) {
            return;
        }
        EventBus.getDefault().post(new b.a.a.e.a(1003, i));
    }

    @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
    public void onAcCycleModeChanged(int i) {
        super.onAcCycleModeChanged(i);
        BydLogHelper.logW("HvacListener", "onAcCycleModeChanged mode = " + i);
        if (b.a.a.h.a.a(1007)) {
            return;
        }
        EventBus.getDefault().post(new b.a.a.e.a(1007, i));
    }

    @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
    public void onAcDefrostStateChanged(int i, int i2) {
        super.onAcDefrostStateChanged(i, i2);
        BydLogHelper.logW("HvacListener", "onAcDefrostStateChanged area = " + i + " -- state = " + i2);
        if (i == 1) {
            if (b.a.a.h.a.a(1006)) {
                return;
            }
            EventBus.getDefault().post(new b.a.a.e.a(1006, i2));
        } else if (i == 2) {
            if (!b.a.a.h.a.a(1038)) {
                EventBus.getDefault().post(new b.a.a.e.a(1038, i2));
            }
            if (b.a.a.h.a.a(1039)) {
                return;
            }
            EventBus.getDefault().post(new b.a.a.e.a(1039, i2));
        }
    }

    @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
    public void onAcHeatingStateChanged(int i) {
        super.onAcHeatingStateChanged(i);
        BydLogHelper.logW("HvacListener", "onAcHeatingStateChanged  state = " + i);
        if (b.a.a.h.a.a(1041)) {
            return;
        }
        EventBus.getDefault().post(new b.a.a.e.a(1041, i));
    }

    @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
    public void onAcMaxCoolingStateChanged(int i) {
        super.onAcMaxCoolingStateChanged(i);
        BydLogHelper.logW("HvacListener", "onAcMaxCoolingStateChanged state = " + i);
        if (b.a.a.h.a.a(1005)) {
            return;
        }
        EventBus.getDefault().post(new b.a.a.e.a(1005, i));
    }

    @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
    public void onAcPromptBoxShownStateChanged(int i) {
        super.onAcPromptBoxShownStateChanged(i);
        BydLogHelper.logW("HvacListener", "onAcPromptBoxShownStateChanged state = " + i);
        if (b.a.a.h.a.a(1037)) {
            return;
        }
        EventBus.getDefault().post(new b.a.a.e.a(1037, i));
    }

    @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
    public void onAcRearPanelLockStateChanged(int i) {
        super.onAcRearPanelLockStateChanged(i);
        BydLogHelper.logW("HvacListener", "onAcRearPanelLockStateChanged state = " + i);
        if (b.a.a.h.a.a(1105)) {
            return;
        }
        EventBus.getDefault().post(new b.a.a.e.a(1105, i));
    }

    @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
    public void onAcRearStarted() {
        super.onAcRearStarted();
        BydLogHelper.logW("HvacListener", "onAcRearStarted  = ");
        if (b.a.a.h.a.a(1106)) {
            return;
        }
        EventBus.getDefault().post(new b.a.a.e.a(1106, 1));
    }

    @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
    public void onAcRearStoped() {
        super.onAcRearStoped();
        BydLogHelper.logW("HvacListener", "onAcRearStoped  = ");
        if (b.a.a.h.a.a(1106)) {
            return;
        }
        EventBus.getDefault().post(new b.a.a.e.a(1106, 0));
    }

    @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
    public void onAcStarted() {
        super.onAcStarted();
        BydLogHelper.logW("HvacListener", "onAcStarted");
        if (b.a.a.h.a.a(1022)) {
            return;
        }
        EventBus.getDefault().post(new b.a.a.e.a(1022, 1));
    }

    @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
    public void onAcStoped() {
        super.onAcStoped();
        BydLogHelper.logW("HvacListener", "onAcStoped");
        if (b.a.a.h.a.a(1022)) {
            return;
        }
        EventBus.getDefault().post(new b.a.a.e.a(1022, 0));
    }

    @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
    public void onAcTemperatureControlModeChanged(int i) {
        super.onAcTemperatureControlModeChanged(i);
        BydLogHelper.logW("HvacListener", "onAcTemperatureControlModeChanged mode = " + i);
        if (b.a.a.h.a.a(1011)) {
            return;
        }
        EventBus.getDefault().post(new b.a.a.e.a(1011, i));
    }

    @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
    public void onAcTuyereSwitchStateChanged(int i, int i2) {
        super.onAcTuyereSwitchStateChanged(i, i2);
        BydLogHelper.logW("HvacListener", "onAcTuyereSwitchStateChanged area = " + i + " -- state = " + i2);
        if (i == 0) {
            b.a.a.h.a.d(1044, i2);
            return;
        }
        if (i == 1) {
            b.a.a.h.a.d(1045, i2);
        } else if (i == 2) {
            b.a.a.h.a.d(1046, i2);
        } else if (i == 3) {
            b.a.a.h.a.d(1047, i2);
        }
    }

    @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
    public void onAcVentilationStateChanged(int i) {
        super.onAcVentilationStateChanged(i);
        BydLogHelper.logW("HvacListener", "onAcVentilationStateChanged state = " + i);
        if (b.a.a.h.a.a(1008)) {
            return;
        }
        EventBus.getDefault().post(new b.a.a.e.a(1008, i));
    }

    @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
    public void onAcWindLevelChanged(int i) {
        super.onAcWindLevelChanged(i);
        BydLogHelper.logW("HvacListener", "onAcWindLevelChanged level = " + i);
        if (b.a.a.h.a.a(1012)) {
            return;
        }
        EventBus.getDefault().post(new b.a.a.e.a(1012, i));
    }

    @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
    public void onAcWindLevelManualSignChanged(int i) {
        super.onAcWindLevelManualSignChanged(i);
        BydLogHelper.logW("HvacListener", "onAcWindLevelManualSignChanged sign = " + i);
        if (b.a.a.h.a.a(1016)) {
            return;
        }
        EventBus.getDefault().post(new b.a.a.e.a(1016, i));
    }

    @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
    public void onAcWindModeChanged(int i) {
        super.onAcWindModeChanged(i);
        BydLogHelper.logW("HvacListener", "onAcWindModeChanged mode = " + i);
        if (b.a.a.h.a.a(1009)) {
            return;
        }
        EventBus.getDefault().post(new b.a.a.e.a(1009, i));
    }

    @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
    public void onAcWindModeManualSignChanged(int i) {
        super.onAcWindModeManualSignChanged(i);
        BydLogHelper.logW("HvacListener", "onAcWindModeManualSignChanged sign = " + i);
        if (b.a.a.h.a.a(1015)) {
            return;
        }
        EventBus.getDefault().post(new b.a.a.e.a(1015, i));
    }

    @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
    public void onAirOutleOnlineStateChanged(int i) {
        super.onAirOutleOnlineStateChanged(i);
        BydLogHelper.logW("HvacListener", "onAirOutleOnlineStateChanged  state = " + i);
        if (b.a.a.h.a.a(1031)) {
            return;
        }
        EventBus.getDefault().post(new b.a.a.e.a(1031, i));
    }

    @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
    public void onAirOutletStateChanged(int i, int i2) {
        super.onAirOutletStateChanged(i, i2);
        BydLogHelper.logW("HvacListener", "onAirOutletStateChanged area = " + i + " -- state = " + i2);
        if (i == 0) {
            b.a.a.h.a.d(1028, i2);
        } else if (i == 1) {
            b.a.a.h.a.d(1052, i2);
        }
    }

    @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
    public void onAirOutletValueChanged(int i, float f) {
        super.onAirOutletValueChanged(i, f);
        int i2 = (int) ((100.0f * f) - 128.0f);
        BydLogHelper.logW("HvacListener", "onAirOutletValueChanged area = " + i + " -- value = " + f + " -- valueResult = " + i2);
        switch (i) {
            case 0:
                b.a.a.h.a.d(1040, i2);
                break;
            case 1:
                b.a.a.h.a.d(1053, i2);
                break;
            case 2:
                b.a.a.h.a.d(1054, i2);
                break;
            case 3:
                b.a.a.h.a.d(1055, i2);
                break;
            case 4:
                b.a.a.h.a.d(1056, i2);
                break;
            case 5:
                b.a.a.h.a.d(1057, i2);
                break;
            case 6:
                b.a.a.h.a.d(1058, i2);
                break;
            case 7:
                b.a.a.h.a.d(1059, i2);
                break;
        }
    }

    @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
    public void onAvoidBlowPeopleStateChanged(int i, int i2) {
        super.onAvoidBlowPeopleStateChanged(i, i2);
        BydLogHelper.logW("HvacListener", "onAvoidBlowPeopleStateChanged area = " + i + " -- state = " + i2);
        if (i == 0) {
            b.a.a.h.a.d(1026, i2);
        } else if (i == 1) {
            b.a.a.h.a.d(1050, i2);
        }
    }

    @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
    public void onBlowPeopleState(int i, int i2) {
        super.onBlowPeopleState(i, i2);
        BydLogHelper.logW("HvacListener", "onBlowPeopleState area = " + i + " -- state = " + i2);
        if (i == 0) {
            b.a.a.h.a.d(1025, i2);
        } else if (i == 1) {
            b.a.a.h.a.d(1049, i2);
        }
    }

    @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
    public void onFreeWindBTNStateChanged(int i, int i2) {
        super.onFreeWindBTNStateChanged(i, i2);
        BydLogHelper.logW("HvacListener", "onFreeWindBTNStateChanged area = " + i + " -- state = " + i2);
        if (i == 0) {
            b.a.a.h.a.d(1024, i2);
        } else if (i == 1) {
            b.a.a.h.a.d(1048, i2);
        }
    }

    @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
    public void onFrontRowAirOutletStateChanged(int i) {
        super.onFrontRowAirOutletStateChanged(i);
        BydLogHelper.logW("HvacListener", "onFrontRowAirOutletStateChanged   state = " + i);
        b.a.a.h.a.d(1030, i);
    }

    @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
    public void onHeatPumpCompressorWorkModeChanged(int i) {
        super.onHeatPumpCompressorWorkModeChanged(i);
        BydLogHelper.logW("HvacListener", "onHeatPumpCompressorWorkModeChanged mode = " + i);
        if (b.a.a.h.a.a(1020)) {
            return;
        }
        EventBus.getDefault().post(new b.a.a.e.a(1020, i));
    }

    @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
    public void onIntelligentControlStateChanged(int i) {
        super.onIntelligentControlStateChanged(i);
        BydLogHelper.logW("HvacListener", "onIntelligentControlStateChanged  state = " + i);
        b.a.a.h.a.d(1052, i);
    }

    @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
    public void onMaxAcStateChanged(int i) {
        super.onMaxAcStateChanged(i);
        BydLogHelper.logW("HvacListener", "onMaxAcStateChanged state = " + i);
        if (b.a.a.h.a.a(1005)) {
            return;
        }
        EventBus.getDefault().post(new b.a.a.e.a(1005, i));
    }

    @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
    public void onQuickCleanAirStateChanged(int i) {
        super.onQuickCleanAirStateChanged(i);
        BydLogHelper.logW("HvacListener", "onQuickCleanAirStateChanged state = " + i);
        if (b.a.a.h.a.a(1308)) {
            return;
        }
        EventBus.getDefault().post(new b.a.a.e.a(1308, i));
    }

    @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
    public void onQuickCleanTipChanged(int i) {
        super.onQuickCleanTipChanged(i);
        BydLogHelper.logW("HvacListener", "onQuickCleanTipChanged tip = " + i);
        if (b.a.a.h.a.a(1201)) {
            return;
        }
        EventBus.getDefault().post(new b.a.a.e.a(1201, i));
    }

    @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
    public void onRearAcCtrlModeChanged(int i) {
        super.onRearAcCtrlModeChanged(i);
        BydLogHelper.logW("HvacListener", "onRearAcCtrlModeChanged mode = " + i);
        if (b.a.a.h.a.a(1102)) {
            return;
        }
        EventBus.getDefault().post(new b.a.a.e.a(1102, i));
    }

    @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
    public void onRearAcKeyLockStateChanged(int i) {
        super.onRearAcKeyLockStateChanged(i);
        BydLogHelper.logW("HvacListener", "onRearAcKeyLockStateChanged state = " + i);
        if (b.a.a.h.a.a(1105)) {
            return;
        }
        EventBus.getDefault().post(new b.a.a.e.a(1105, i));
    }

    @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
    public void onRearAcWindLevelChanged(int i) {
        super.onRearAcWindLevelChanged(i);
        BydLogHelper.logW("HvacListener", "onRearAcWindLevelChanged level = " + i);
        if (b.a.a.h.a.a(1104)) {
            return;
        }
        EventBus.getDefault().post(new b.a.a.e.a(1104, i));
    }

    @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
    public void onRearAcWindModeChanged(int i) {
        super.onRearAcWindModeChanged(i);
        BydLogHelper.logW("HvacListener", "onRearAcWindModeChanged mode = " + i);
        if (b.a.a.h.a.a(1103)) {
            return;
        }
        EventBus.getDefault().post(new b.a.a.e.a(1103, i));
    }

    @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
    public void onSweepBlowStateChanged(int i, int i2) {
        super.onSweepBlowStateChanged(i, i2);
        BydLogHelper.logW("HvacListener", "onSweepBlowStateChanged area = " + i + " -- state = " + i2);
        if (i == 0) {
            b.a.a.h.a.d(1027, i2);
        } else if (i == 1) {
            b.a.a.h.a.d(1051, i2);
        }
    }

    @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
    public void onTemperatureChanged(int i, int i2) {
        super.onTemperatureChanged(i, i2);
        BydLogHelper.logW("HvacListener", "onTemperatureChanged area = " + i + " -- value = " + i2);
        if (i == 1) {
            if (b.a.a.h.a.a(1034)) {
                return;
            }
            EventBus.getDefault().post(new b.a.a.e.a(1034, i2));
        } else if (i == 2) {
            if (b.a.a.h.a.a(1035)) {
                return;
            }
            EventBus.getDefault().post(new b.a.a.e.a(1035, i2));
        } else {
            if (i != 3 || b.a.a.h.a.a(1036)) {
                return;
            }
            EventBus.getDefault().post(new b.a.a.e.a(1036, i2));
        }
    }

    @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
    public void onTemperatureUnitChanged(int i) {
        super.onTemperatureUnitChanged(i);
        BydLogHelper.logW("HvacListener", "onTemperatureUnitChanged unit = " + i);
        if (b.a.a.h.a.a(1017)) {
            return;
        }
        EventBus.getDefault().post(new b.a.a.e.a(1017, i));
    }
}
