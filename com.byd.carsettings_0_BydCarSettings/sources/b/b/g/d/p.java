package b.b.g.d;

import android.hardware.bydauto.setting.BYDAutoSettingDevice;
import b.b.g.n.q.u;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: compiled from: SeatModelStudy.java */
/* JADX INFO: loaded from: classes2.dex */
public class p extends l {
    public boolean A() {
        return j(2);
    }

    public boolean B() {
        return BYDAutoSettingDevice.getInstance(b.b.a.a.a.b().a()).getRearLeftMassageSwConfiguration() == 1;
    }

    public boolean C() {
        return b.b.g.n.q.m.p0().w1(3) == 1 || BYDAutoSettingDevice.getInstance(b.b.a.a.a.b().a()).getLeftRearMassageChairConfigurationForECU() == 1;
    }

    public boolean D() {
        return C() || F() || B() || E();
    }

    public boolean E() {
        return BYDAutoSettingDevice.getInstance(b.b.a.a.a.b().a()).getRearRightMassageSwConfiguration() == 1;
    }

    public boolean F() {
        return b.b.g.n.q.m.p0().w1(4) == 1 || BYDAutoSettingDevice.getInstance(b.b.a.a.a.b().a()).getRightRearMassageChairConfigurationForECU() == 1;
    }

    public final boolean G() {
        for (int i = 1; i <= 4; i++) {
            int i2 = BYDAutoSettingDevice.getInstance(b.b.a.a.a.b().a()).get5LevelSeatHeatingState(i);
            BydLogHelper.logD("SeatModelStudy", " isSeat5HeatSupport  i" + i + " , heatValue : " + i2);
            if (i2 >= 1) {
                return true;
            }
        }
        return false;
    }

    public final boolean H() {
        for (int i = 1; i <= 4; i++) {
            int i2 = BYDAutoSettingDevice.getInstance(b.b.a.a.a.b().a()).get5LevelSeatVentilatingState(i);
            BydLogHelper.logD("SeatModelStudy", " isSeat5HeatSupport  i" + i + " , ventilateValue : " + i2);
            if (i2 >= 1) {
                return true;
            }
        }
        return false;
    }

    public boolean I() {
        return G() || s() || x() || n(3) || n(3);
    }

    public boolean J() {
        return H() || t() || y() || o(3) || o(3);
    }

    public boolean K() {
        return I() || J();
    }

    @Override // b.b.g.d.l
    public boolean a() {
        return v() || A() || p() || q() || K() || w();
    }

    public boolean b(int i) {
        if (i == 1) {
            return (BYDAutoSettingDevice.getInstance(b.b.a.a.a.b().a()).getMainDriveElectricLegSupportAdjustmentByGCtrl() == 1) || BYDAutoSettingDevice.getInstance(b.b.a.a.a.b().a()).getMainDriveElectricLegSupportAdjustmentByLeftBodyCtrl() == 1;
        }
        if (i == 2) {
            return (BYDAutoSettingDevice.getInstance(b.b.a.a.a.b().a()).getPassengerSeatLegSupportAdjustmentConfigurationByECU() == 1) || (u.I().V() == 1);
        }
        if (i == 3) {
            return (BYDAutoSettingDevice.getInstance(b.b.a.a.a.b().a()).getRearLeftLegSupportAdjustmentConfigurationByECU() == 1) || (BYDAutoSettingDevice.getInstance(b.b.a.a.a.b().a()).getLeftRearSeatLegBracket() == 1);
        }
        if (i != 4) {
            return false;
        }
        return (BYDAutoSettingDevice.getInstance(b.b.a.a.a.b().a()).getRearRightLegSupportAdjustmentConfigurationByECU() == 1) || (BYDAutoSettingDevice.getInstance(b.b.a.a.a.b().a()).getRightRearSeatLegBracket() == 1);
    }

    public boolean c(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i == 4 && BYDAutoSettingDevice.getInstance(b.b.a.a.a.b().a()).getRearRightBidetAdjustmentConfigurationByECU() == 1 : BYDAutoSettingDevice.getInstance(b.b.a.a.a.b().a()).getRearLeftBidetAdjustmentConfigurationByECU() == 1 : BYDAutoSettingDevice.getInstance(b.b.a.a.a.b().a()).getPassengerSeatElectricAdjustmentConfigurationByECU() >= 3 : BYDAutoSettingDevice.getInstance(b.b.a.a.a.b().a()).getMainDriverSeatAdjustmentConfigurationByGCtrl() == 3 || BYDAutoSettingDevice.getInstance(b.b.a.a.a.b().a()).getMainDriverSeatAdjustmentConfigurationByLeftBodyCtrl() == 3;
    }

    public boolean d(int i) {
        if (i != 1) {
            if (i != 2) {
                return i != 3 ? i == 4 && BYDAutoSettingDevice.getInstance(b.b.a.a.a.b().a()).getRearRightHeadrestHeightAdjustmentConfigurationByECU() == 1 : BYDAutoSettingDevice.getInstance(b.b.a.a.a.b().a()).getRearLeftHeadrestHeightAdjustmentConfigurationByECU() == 1;
            }
            int passengerSeatHeadrestConfigurationByECU = BYDAutoSettingDevice.getInstance(b.b.a.a.a.b().a()).getPassengerSeatHeadrestConfigurationByECU();
            int iU = u.I().U();
            return passengerSeatHeadrestConfigurationByECU == 2 || passengerSeatHeadrestConfigurationByECU == 3 || iU == 2 || iU == 3;
        }
        int mainDriveElectricHeadrestAdjustmentByGCtrl = BYDAutoSettingDevice.getInstance(b.b.a.a.a.b().a()).getMainDriveElectricHeadrestAdjustmentByGCtrl();
        if (mainDriveElectricHeadrestAdjustmentByGCtrl == 2 || mainDriveElectricHeadrestAdjustmentByGCtrl == 3) {
            return true;
        }
        int mainDriveElectricHeadrestAdjustmentByLeftBodyCtrl = BYDAutoSettingDevice.getInstance(b.b.a.a.a.b().a()).getMainDriveElectricHeadrestAdjustmentByLeftBodyCtrl();
        return mainDriveElectricHeadrestAdjustmentByLeftBodyCtrl == 2 || mainDriveElectricHeadrestAdjustmentByLeftBodyCtrl == 3;
    }

    public boolean e(int i) {
        if (i != 1) {
            if (i != 2) {
                return i != 3 ? i == 4 && u.I().Y() == 1 : u.I().X() == 1;
            }
            int iT = u.I().T();
            int iU = u.I().U();
            return iT == 1 || iT == 3 || iU == 1 || iU == 3;
        }
        int iN = u.I().N();
        if (iN == 1 || iN == 3) {
            return true;
        }
        int iO = u.I().O();
        return iO == 1 || iO == 3;
    }

    public boolean f() {
        return false;
    }

    public boolean g(int i) {
        if (i == 1) {
            int mainDriverSeatAdjustmentConfigurationByGCtrl = BYDAutoSettingDevice.getInstance(b.b.a.a.a.b().a()).getMainDriverSeatAdjustmentConfigurationByGCtrl();
            if (mainDriverSeatAdjustmentConfigurationByGCtrl >= 1 && mainDriverSeatAdjustmentConfigurationByGCtrl <= 3) {
                return true;
            }
            int iP = u.I().P();
            return iP >= 1 && iP <= 3;
        }
        if (i == 2) {
            return BYDAutoSettingDevice.getInstance(b.b.a.a.a.b().a()).getPassengerSeatMemoryFunctionCanByECU() == 1;
        }
        if (i == 3) {
            return BYDAutoSettingDevice.getInstance(b.b.a.a.a.b().a()).getRearLeftMemorySwitchConfigurationByECU() == 1 || BYDAutoSettingDevice.getInstance(b.b.a.a.a.b().a()).getLeftRearMemoryConfiguration() == 1;
        }
        if (i != 4) {
            return false;
        }
        return BYDAutoSettingDevice.getInstance(b.b.a.a.a.b().a()).getRearRightMemorySwitchConfigurationByECU() == 1 || BYDAutoSettingDevice.getInstance(b.b.a.a.a.b().a()).getRightRearMemoryConfiguration() == 1;
    }

    public int h() {
        if (!I() || J()) {
            return (!J() || I()) ? 0 : 1;
        }
        return 2;
    }

    public boolean i(int i) {
        if (i == 1) {
            int mainDriverSeatAdjustmentConfigurationByGCtrl = BYDAutoSettingDevice.getInstance(b.b.a.a.a.b().a()).getMainDriverSeatAdjustmentConfigurationByGCtrl();
            if (mainDriverSeatAdjustmentConfigurationByGCtrl >= 1 && mainDriverSeatAdjustmentConfigurationByGCtrl <= 3) {
                return true;
            }
            int mainDriverSeatAdjustmentConfigurationByLeftBodyCtrl = BYDAutoSettingDevice.getInstance(b.b.a.a.a.b().a()).getMainDriverSeatAdjustmentConfigurationByLeftBodyCtrl();
            return mainDriverSeatAdjustmentConfigurationByLeftBodyCtrl >= 1 && mainDriverSeatAdjustmentConfigurationByLeftBodyCtrl <= 3;
        }
        if (i == 2) {
            return BYDAutoSettingDevice.getInstance(b.b.a.a.a.b().a()).getPassengerSeatElectricAdjustmentConfigurationByECU() >= 1;
        }
        if (i == 3) {
            return BYDAutoSettingDevice.getInstance(b.b.a.a.a.b().a()).getRearLeftLevelAdjustmentConfigurationByECU() == 1 || BYDAutoSettingDevice.getInstance(b.b.a.a.a.b().a()).getLeftRearSeatHorizontalAdjustmentConfiguration() == 1;
        }
        if (i != 4) {
            return false;
        }
        return BYDAutoSettingDevice.getInstance(b.b.a.a.a.b().a()).getRearRightLevelAdjustmentConfigurationByECU() == 1 || BYDAutoSettingDevice.getInstance(b.b.a.a.a.b().a()).getRightRearSeatHorizontalAdjustmentConfiguration() == 1;
    }

    public boolean j(int i) {
        return i(i) || k(i) || l(i) || c(i) || b(i) || d(i) || e(i);
    }

    public boolean k(int i) {
        if (i != 1) {
            return i != 2 ? i != 3 ? i == 4 && BYDAutoSettingDevice.getInstance(b.b.a.a.a.b().a()).getRearRightHeightAdjustmentConfigurationByECU() == 1 : BYDAutoSettingDevice.getInstance(b.b.a.a.a.b().a()).getRearLeftHeightAdjustmentConfigurationByECU() == 1 : BYDAutoSettingDevice.getInstance(b.b.a.a.a.b().a()).getPassengerSeatElectricAdjustmentConfigurationByECU() >= 2;
        }
        int mainDriverSeatAdjustmentConfigurationByGCtrl = BYDAutoSettingDevice.getInstance(b.b.a.a.a.b().a()).getMainDriverSeatAdjustmentConfigurationByGCtrl();
        if (mainDriverSeatAdjustmentConfigurationByGCtrl >= 2 && mainDriverSeatAdjustmentConfigurationByGCtrl <= 3) {
            return true;
        }
        int mainDriverSeatAdjustmentConfigurationByLeftBodyCtrl = BYDAutoSettingDevice.getInstance(b.b.a.a.a.b().a()).getMainDriverSeatAdjustmentConfigurationByLeftBodyCtrl();
        return mainDriverSeatAdjustmentConfigurationByLeftBodyCtrl >= 2 && mainDriverSeatAdjustmentConfigurationByLeftBodyCtrl <= 3;
    }

    public boolean l(int i) {
        if (i == 1) {
            int mainDriverSeatAdjustmentConfigurationByGCtrl = BYDAutoSettingDevice.getInstance(b.b.a.a.a.b().a()).getMainDriverSeatAdjustmentConfigurationByGCtrl();
            if (mainDriverSeatAdjustmentConfigurationByGCtrl >= 1 && mainDriverSeatAdjustmentConfigurationByGCtrl <= 3) {
                return true;
            }
            int mainDriverSeatAdjustmentConfigurationByLeftBodyCtrl = BYDAutoSettingDevice.getInstance(b.b.a.a.a.b().a()).getMainDriverSeatAdjustmentConfigurationByLeftBodyCtrl();
            return mainDriverSeatAdjustmentConfigurationByLeftBodyCtrl >= 1 && mainDriverSeatAdjustmentConfigurationByLeftBodyCtrl <= 3;
        }
        if (i == 2) {
            return BYDAutoSettingDevice.getInstance(b.b.a.a.a.b().a()).getPassengerSeatElectricAdjustmentConfigurationByECU() >= 1;
        }
        if (i == 3) {
            return (BYDAutoSettingDevice.getInstance(b.b.a.a.a.b().a()).getRearLeftBackrestAdjustmentConfigurationByECU() == 1) || (BYDAutoSettingDevice.getInstance(b.b.a.a.a.b().a()).getLeftRearSeatBackAdjustmentConfiguration() == 1);
        }
        if (i != 4) {
            return false;
        }
        return (BYDAutoSettingDevice.getInstance(b.b.a.a.a.b().a()).getRearRightBackrestAdjustmentConfigurationByECU() == 1) || (BYDAutoSettingDevice.getInstance(b.b.a.a.a.b().a()).getRightRearSeatBackAdjustmentConfiguration() == 1);
    }

    public boolean m() {
        int iC0 = u.I().c0();
        return iC0 == 1 || iC0 == 2;
    }

    public final boolean n(int i) {
        int iL1 = b.b.g.n.q.m.p0().l1(i);
        int seatHeatingState2 = BYDAutoSettingDevice.getInstance(b.b.a.a.a.b().a()).getSeatHeatingState2(i);
        BydLogHelper.logD("SeatModelStudy", " isBehind2SeatHeatingSupport   ,  value_0x402 : " + seatHeatingState2 + " ,  value_0x486 : " + iL1);
        return iL1 >= 1 || seatHeatingState2 >= 1;
    }

    public final boolean o(int i) {
        int iO1 = b.b.g.n.q.m.p0().o1(i);
        int seatVentilatingState2 = BYDAutoSettingDevice.getInstance(b.b.a.a.a.b().a()).getSeatVentilatingState2(i);
        BydLogHelper.logD("SeatModelStudy", " isPassenger2SeatVentilatingSupport  ,    platformConfigs : " + iO1 + " value_0x486 : " + seatVentilatingState2 + " value_0x402 : ");
        return iO1 >= 1 || seatVentilatingState2 >= 1;
    }

    public boolean p() {
        return j(3);
    }

    public boolean q() {
        return j(4);
    }

    public boolean r() {
        return u() || z();
    }

    public final boolean s() {
        int iL1 = b.b.g.n.q.m.p0().l1(1);
        BydLogHelper.logD("SeatModelStudy", " isMain2SeatHeatingSupport   ,  value : " + iL1);
        return iL1 >= 1;
    }

    public final boolean t() {
        int iO1 = b.b.g.n.q.m.p0().o1(1);
        BydLogHelper.logD("SeatModelStudy", " isSeat5HeatSupport  ,    value : " + iO1);
        return iO1 >= 1;
    }

    public boolean u() {
        return b.b.g.n.q.m.p0().w1(1) == 1;
    }

    public boolean v() {
        return j(1) || m() || f();
    }

    public boolean w() {
        return r() || D();
    }

    public final boolean x() {
        int iM = u.I().M();
        int iL1 = b.b.g.n.q.m.p0().l1(2);
        int seatHeatingNotL1PState = BYDAutoSettingDevice.getInstance(b.b.a.a.a.b().a()).getSeatHeatingNotL1PState();
        BydLogHelper.logD("SeatModelStudy", " isPassenger2SeatHeatingSupport   ,  platformConfigs : " + iM + " ,  valueL1 : " + iL1 + " ,  valueNotL1 : " + seatHeatingNotL1PState);
        return (iM == 1 && iL1 >= 1) || seatHeatingNotL1PState >= 1;
    }

    public final boolean y() {
        int iM = u.I().M();
        int iO1 = b.b.g.n.q.m.p0().o1(2);
        int seatVentilatingNotL1PState = BYDAutoSettingDevice.getInstance(b.b.a.a.a.b().a()).getSeatVentilatingNotL1PState();
        BydLogHelper.logD("SeatModelStudy", " isPassenger2SeatVentilatingSupport  ,    platformConfigs : " + iM + " valueL1 : " + iO1 + " valueNotL1 : " + seatVentilatingNotL1PState);
        return (iM == 1 && iO1 >= 1) || seatVentilatingNotL1PState >= 1;
    }

    public boolean z() {
        return b.b.g.n.q.m.p0().w1(2) == 1;
    }
}
