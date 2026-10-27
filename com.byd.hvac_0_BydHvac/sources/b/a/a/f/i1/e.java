package b.a.a.f.i1;

import android.hardware.bydauto.setting.AbsBYDAutoSettingListener;
import com.bydux.api.common.tools.log.BydLogHelper;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class e extends AbsBYDAutoSettingListener {
    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void on5LevelSeatHeatingLevelChanged(int i, int i2) {
        super.on5LevelSeatHeatingLevelChanged(i, i2);
        BydLogHelper.logW(AbsBYDAutoSettingListener.TAG, "on5LevelSeatHeatingLevelChanged seatID = " + i + " -- value = " + i2);
        if (i == 1) {
            if (b.a.a.h.a.a(1722)) {
                return;
            }
            EventBus.getDefault().post(new b.a.a.e.a(1722, i2));
        } else if (i == 2) {
            if (b.a.a.h.a.a(1723)) {
                return;
            }
            EventBus.getDefault().post(new b.a.a.e.a(1723, i2));
        } else if (i == 3) {
            if (b.a.a.h.a.a(1724)) {
                return;
            }
            EventBus.getDefault().post(new b.a.a.e.a(1724, i2));
        } else if (i == 4 && !b.a.a.h.a.a(1725)) {
            EventBus.getDefault().post(new b.a.a.e.a(1725, i2));
        }
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void on5LevelSeatHeatingModeChanged(int i, int i2) {
        super.on5LevelSeatHeatingModeChanged(i, i2);
        BydLogHelper.logW(AbsBYDAutoSettingListener.TAG, "on5LevelSeatHeatingModeChanged seatID = " + i + " -- value = " + i2);
        if (i == 1) {
            if (b.a.a.h.a.a(1713)) {
                return;
            }
            EventBus.getDefault().post(new b.a.a.e.a(1713, i2));
        } else if (i == 2) {
            if (b.a.a.h.a.a(1714)) {
                return;
            }
            EventBus.getDefault().post(new b.a.a.e.a(1714, i2));
        } else if (i == 3) {
            if (b.a.a.h.a.a(1715)) {
                return;
            }
            EventBus.getDefault().post(new b.a.a.e.a(1715, i2));
        } else if (i == 4 && !b.a.a.h.a.a(1716)) {
            EventBus.getDefault().post(new b.a.a.e.a(1716, i2));
        }
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void on5LevelSeatHeatingStateChanged(int i, int i2) {
        super.on5LevelSeatHeatingStateChanged(i, i2);
        BydLogHelper.logW(AbsBYDAutoSettingListener.TAG, "on5LevelSeatHeatingStateChanged seatID = " + i + " -- value = " + i2);
        if (i == 1) {
            if (b.a.a.h.a.a(1705)) {
                return;
            }
            EventBus.getDefault().post(new b.a.a.e.a(1705, i2));
        } else if (i == 2) {
            if (b.a.a.h.a.a(1706)) {
                return;
            }
            EventBus.getDefault().post(new b.a.a.e.a(1706, i2));
        } else if (i == 3) {
            if (b.a.a.h.a.a(1707)) {
                return;
            }
            EventBus.getDefault().post(new b.a.a.e.a(1707, i2));
        } else if (i == 4 && !b.a.a.h.a.a(1708)) {
            EventBus.getDefault().post(new b.a.a.e.a(1708, i2));
        }
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void on5LevelSeatVentilatingLevelChanged(int i, int i2) {
        super.on5LevelSeatVentilatingLevelChanged(i, i2);
        BydLogHelper.logW(AbsBYDAutoSettingListener.TAG, "on5LevelSeatVentilatingLevelChanged seatID = " + i + " -- value = " + i2);
        if (i == 1) {
            if (b.a.a.h.a.a(1717)) {
                return;
            }
            EventBus.getDefault().post(new b.a.a.e.a(1717, i2));
        } else if (i == 2) {
            if (b.a.a.h.a.a(1718)) {
                return;
            }
            EventBus.getDefault().post(new b.a.a.e.a(1718, i2));
        } else if (i == 3) {
            if (b.a.a.h.a.a(1720)) {
                return;
            }
            EventBus.getDefault().post(new b.a.a.e.a(1720, i2));
        } else if (i == 4 && !b.a.a.h.a.a(1721)) {
            EventBus.getDefault().post(new b.a.a.e.a(1721, i2));
        }
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void on5LevelSeatVentilatingModeChanged(int i, int i2) {
        super.on5LevelSeatVentilatingModeChanged(i, i2);
        BydLogHelper.logW(AbsBYDAutoSettingListener.TAG, "on5LevelSeatVentilatingModeChanged seatID = " + i + " -- value = " + i2);
        if (i == 1) {
            if (b.a.a.h.a.a(1709)) {
                return;
            }
            EventBus.getDefault().post(new b.a.a.e.a(1709, i2));
        } else if (i == 2) {
            if (b.a.a.h.a.a(1710)) {
                return;
            }
            EventBus.getDefault().post(new b.a.a.e.a(1710, i2));
        } else if (i == 3) {
            if (b.a.a.h.a.a(1711)) {
                return;
            }
            EventBus.getDefault().post(new b.a.a.e.a(1711, i2));
        } else if (i == 4 && !b.a.a.h.a.a(1712)) {
            EventBus.getDefault().post(new b.a.a.e.a(1712, i2));
        }
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void on5LevelSeatVentilatingStateChanged(int i, int i2) {
        super.on5LevelSeatVentilatingStateChanged(i, i2);
        BydLogHelper.logW(AbsBYDAutoSettingListener.TAG, "on5LevelSeatVentilatingStateChanged seatID = " + i + " -- value = " + i2);
        if (i == 1) {
            if (b.a.a.h.a.a(1701)) {
                return;
            }
            EventBus.getDefault().post(new b.a.a.e.a(1701, i2));
        } else if (i == 2) {
            if (b.a.a.h.a.a(1702)) {
                return;
            }
            EventBus.getDefault().post(new b.a.a.e.a(1702, i2));
        } else if (i == 3) {
            if (b.a.a.h.a.a(1703)) {
                return;
            }
            EventBus.getDefault().post(new b.a.a.e.a(1703, i2));
        } else if (i == 4 && !b.a.a.h.a.a(1704)) {
            EventBus.getDefault().post(new b.a.a.e.a(1704, i2));
        }
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onSeatHeatingNotL1PStateChanged(int i) {
        super.onSeatHeatingNotL1PStateChanged(i);
        BydLogHelper.logW(AbsBYDAutoSettingListener.TAG, "onSeatHeatingNotL1PStateChanged value = " + i);
        if (b.a.a.h.a.a(1407)) {
            return;
        }
        EventBus.getDefault().post(new b.a.a.e.a(1407, i));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onSeatHeatingState2Changed(int i, int i2) {
        super.onSeatHeatingState2Changed(i, i2);
        BydLogHelper.logW(AbsBYDAutoSettingListener.TAG, "onSeatHeatingState2Changed seatId = " + i + " -- value = " + i2);
        if (i == 3) {
            if (b.a.a.h.a.a(1420)) {
                return;
            }
            EventBus.getDefault().post(new b.a.a.e.a(1420, i2));
        } else if (i == 4 && !b.a.a.h.a.a(1421)) {
            EventBus.getDefault().post(new b.a.a.e.a(1421, i2));
        }
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onSeatHeatingStateChanged(int i, int i2) {
        super.onSeatHeatingStateChanged(i, i2);
        BydLogHelper.logW(AbsBYDAutoSettingListener.TAG, "onSeatHeatingStateChanged seatID = " + i + " -- state = " + i2);
        if (i == 1) {
            if (b.a.a.h.a.a(1414)) {
                return;
            }
            EventBus.getDefault().post(new b.a.a.e.a(1414, i2));
        } else if (i == 2) {
            if (b.a.a.h.a.a(1415)) {
                return;
            }
            EventBus.getDefault().post(new b.a.a.e.a(1415, i2));
        } else if (i == 3) {
            if (b.a.a.h.a.a(1416)) {
                return;
            }
            EventBus.getDefault().post(new b.a.a.e.a(1416, i2));
        } else if (i == 4 && !b.a.a.h.a.a(1417)) {
            EventBus.getDefault().post(new b.a.a.e.a(1417, i2));
        }
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onSeatVentilatingNotL1PStateChanged(int i) {
        super.onSeatVentilatingNotL1PStateChanged(i);
        BydLogHelper.logW(AbsBYDAutoSettingListener.TAG, "onSeatVentilatingNotL1PStateChanged value = " + i);
        if (b.a.a.h.a.a(1409)) {
            return;
        }
        EventBus.getDefault().post(new b.a.a.e.a(1409, i));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onSeatVentilatingState2Changed(int i, int i2) {
        super.onSeatVentilatingState2Changed(i, i2);
        BydLogHelper.logW(AbsBYDAutoSettingListener.TAG, "onSeatVentilatingState2Changed seatId = " + i + " -- value = " + i2);
        if (i == 3) {
            if (b.a.a.h.a.a(1424)) {
                return;
            }
            EventBus.getDefault().post(new b.a.a.e.a(1424, i2));
        } else if (i == 4 && !b.a.a.h.a.a(1425)) {
            EventBus.getDefault().post(new b.a.a.e.a(1425, i2));
        }
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onSeatVentilatingStateChanged(int i, int i2) {
        super.onSeatVentilatingStateChanged(i, i2);
        BydLogHelper.logW(AbsBYDAutoSettingListener.TAG, "onSeatVentilatingStateChanged seatID = " + i + " -- state = " + i2);
        if (i == 1) {
            if (b.a.a.h.a.a(1410)) {
                return;
            }
            EventBus.getDefault().post(new b.a.a.e.a(1410, i2));
        } else if (i == 2) {
            if (b.a.a.h.a.a(1411)) {
                return;
            }
            EventBus.getDefault().post(new b.a.a.e.a(1411, i2));
        } else if (i == 3) {
            if (b.a.a.h.a.a(1412)) {
                return;
            }
            EventBus.getDefault().post(new b.a.a.e.a(1412, i2));
        } else if (i == 4 && !b.a.a.h.a.a(1413)) {
            EventBus.getDefault().post(new b.a.a.e.a(1413, i2));
        }
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onSteeringWheelHeatingAutoStateChanged(int i) {
        super.onSteeringWheelHeatingAutoStateChanged(i);
        BydLogHelper.logW(AbsBYDAutoSettingListener.TAG, "onSteeringWheelHeatingAutoStateChanged value = " + i);
        if (b.a.a.h.a.a(1700)) {
            return;
        }
        EventBus.getDefault().post(new b.a.a.e.a(1700, i));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onSteeringWheelHeatingGearChanged(int i) {
        super.onSteeringWheelHeatingGearChanged(i);
        BydLogHelper.logW(AbsBYDAutoSettingListener.TAG, "onSteeringWheelHeatingGearChanged  value = " + i);
        if (b.a.a.h.a.a(1726)) {
            return;
        }
        EventBus.getDefault().post(new b.a.a.e.a(1726, i));
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onSteeringWheelHeatingStateChanged(int i) {
        super.onSteeringWheelHeatingStateChanged(i);
        BydLogHelper.logW(AbsBYDAutoSettingListener.TAG, "onSteeringWheelHeatingStateChanged state = " + i);
        if (b.a.a.h.a.a(1400)) {
            return;
        }
        EventBus.getDefault().post(new b.a.a.e.a(1400, i));
    }
}
