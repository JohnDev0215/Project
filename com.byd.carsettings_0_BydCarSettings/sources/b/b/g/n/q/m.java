package b.b.g.n.q;

import android.content.Context;
import android.hardware.bydauto.audio.BYDAutoAudioDevice;
import android.hardware.bydauto.setting.AbsBYDAutoSettingListener;
import android.hardware.bydauto.setting.BYDAutoSettingDevice;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: compiled from: BYDAutoSettingDeviceService.java */
/* JADX INFO: loaded from: classes2.dex */
public class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static volatile m f1286a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public BYDAutoSettingDevice f1287b;

    public static m p0() {
        if (f1286a == null) {
            synchronized (m.class) {
                if (f1286a == null) {
                    f1286a = new m();
                }
            }
        }
        return f1286a;
    }

    public int A() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getDelayPowerOffStatus();
        }
        return 0;
    }

    public int A0() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getMicroSwitchLockWindowState();
        }
        return 0;
    }

    public int A1() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getTrailerModeState();
        }
        return 0;
    }

    public int A2(int i) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice == null) {
            return 0;
        }
        bYDAutoSettingDevice.setHudMode(i);
        return 0;
    }

    public void A3(int i, int i2) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            bYDAutoSettingDevice.turnHeadrestTurnHorization(i, i2);
        }
    }

    public int B() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getDelayPowerOffTime();
        }
        return 0;
    }

    public int B0() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getMicroSwitchUnlockWindowState();
        }
        return 0;
    }

    public int B1() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getTrayInitState();
        }
        return 0;
    }

    public int B2(int i) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice == null) {
            return 0;
        }
        bYDAutoSettingDevice.setHudRotationAngle(i);
        return 0;
    }

    public void B3(int i, int i2) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            bYDAutoSettingDevice.turnSeatCaliperSplint(i, i2);
        }
    }

    public int C() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getDisinfState();
        }
        return 0;
    }

    public int C0() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getMotorPretensionerSeatbeltState();
        }
        return 0;
    }

    public int C1() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getTrayState();
        }
        return 0;
    }

    public int C2(int i) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice == null) {
            return 0;
        }
        bYDAutoSettingDevice.setHudState(i);
        return 0;
    }

    public void C3(int i, int i2) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            bYDAutoSettingDevice.turnSeatCushion(i, i2);
        }
    }

    public int D() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getDoorHeightSupport();
        }
        return 0;
    }

    public int D0() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getOverspeedLock();
        }
        return 0;
    }

    public int D1() {
        if (this.f1287b == null) {
            return 0;
        }
        BydLogHelper.logD(m.class.getSimpleName(), "getWadingPatternSocTips ");
        return this.f1287b.getWadingPatternSocTips();
    }

    public void D2(int i, int i2, int i3) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            bYDAutoSettingDevice.setIALBrightness(i, i2, i3);
        }
    }

    public void D3(int i, int i2) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            bYDAutoSettingDevice.turnSeatHeight(i, i2);
        }
    }

    public int E() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getDoorknobAutoShrinkState();
        }
        return 0;
    }

    public int E0() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getPM25Power();
        }
        return 0;
    }

    public int E1() {
        if (this.f1287b == null) {
            return 0;
        }
        BydLogHelper.logD(m.class.getSimpleName(), "getWadingPatternSpeedTips ");
        return this.f1287b.getWadingPatternSpeedTips();
    }

    public void E2(int i, int i2, int i3) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            bYDAutoSettingDevice.setIALColor(i, i2, i3);
        }
    }

    public void E3(int i, int i2) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            bYDAutoSettingDevice.turnSeatHorization(i, i2);
        }
    }

    public int F() {
        return this.f1287b.getDriveMode();
    }

    public int F0() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getPM25SwitchCheck();
        }
        return 0;
    }

    public int F1() {
        if (this.f1287b == null) {
            return 0;
        }
        BydLogHelper.logD(m.class.getSimpleName(), "getWadingPatternStateTips ");
        return this.f1287b.getWadingPatternStateTips();
    }

    public int F2(int i) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice == null) {
            return 0;
        }
        bYDAutoSettingDevice.setIntelligentAcAreaState(i);
        return 0;
    }

    public void F3(int i, int i2) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            bYDAutoSettingDevice.turnSeatbackrest(i, i2);
        }
    }

    public int G() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getDriverSeatAutoReturn();
        }
        return 0;
    }

    public int G0() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getPM25TimeCheck();
        }
        return 0;
    }

    public int G1() {
        if (this.f1287b == null) {
            return 0;
        }
        BydLogHelper.logD(m.class.getSimpleName(), "getWadingPatternTestTips ");
        return this.f1287b.getWadingPatternTestTips();
    }

    public int G2(int i) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice == null) {
            return 0;
        }
        bYDAutoSettingDevice.setIntelligentLockState(i);
        return 0;
    }

    public void G3(int i) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            bYDAutoSettingDevice.turnSteeringWheel(i);
        }
    }

    public int H() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getDriverSeatAutoReturnB3();
        }
        return 0;
    }

    public byte H0() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        int pedalSuppressionSystemState = bYDAutoSettingDevice == null ? 0 : bYDAutoSettingDevice.getPedalSuppressionSystemState();
        BydLogHelper.logD("getPedalSuppressionSystemState =" + pedalSuppressionSystemState);
        return (byte) pedalSuppressionSystemState;
    }

    public int H1() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getWorkState();
        }
        return 0;
    }

    public int H2(int i) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice == null) {
            return 0;
        }
        bYDAutoSettingDevice.setIntelligentUnlockState(i);
        return 0;
    }

    public void H3(AbsBYDAutoSettingListener absBYDAutoSettingListener) {
        if (this.f1287b == null) {
            return;
        }
        String simpleName = m.class.getSimpleName();
        StringBuilder sb = new StringBuilder();
        sb.append("unregisterListener BydSettingListener start listener == null : ");
        sb.append(absBYDAutoSettingListener == null);
        BydLogHelper.logD(simpleName, sb.toString());
        this.f1287b.unregisterListener(absBYDAutoSettingListener);
    }

    public int I() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getDynamicSuspensionMode();
        }
        return 0;
    }

    public int I0() {
        if (this.f1287b == null) {
            return 0;
        }
        BydLogHelper.logD(m.class.getSimpleName(), "getPhysicalkeyConfig ");
        return this.f1287b.getPhysicalkeyConfig();
    }

    public void I1(Context context) {
        this.f1287b = BYDAutoSettingDevice.getInstance(context);
    }

    public int I2(int i) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice == null) {
            return 0;
        }
        bYDAutoSettingDevice.setInternationalLanguage(i);
        return 0;
    }

    public int J(int i) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getEcuMassageLevel(i);
        }
        return 0;
    }

    public int J0() {
        if (this.f1287b == null) {
            return 0;
        }
        BydLogHelper.logD(m.class.getSimpleName(), "getPhysicalkeySecuConfig ");
        return this.f1287b.getPhysicalkeySecuConfig();
    }

    public void J1(int i, int i2) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            bYDAutoSettingDevice.resetSeatParams(i, i2);
        }
    }

    public int J2(int i) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.setKeyVibratorLevel(i);
        }
        return 0;
    }

    public int K(int i) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getEcuMassageMode(i);
        }
        return 0;
    }

    public int K0() {
        if (this.f1287b == null) {
            return 0;
        }
        BydLogHelper.logD(m.class.getSimpleName(), "getPhysicalkeySecupConfig ");
        return this.f1287b.getPhysicalkeySecupConfig();
    }

    public void K1(AbsBYDAutoSettingListener absBYDAutoSettingListener) {
        if (this.f1287b == null) {
            return;
        }
        String simpleName = m.class.getSimpleName();
        StringBuilder sb = new StringBuilder();
        sb.append("registerListener BydSettingListener start listener == null : ");
        sb.append(absBYDAutoSettingListener == null);
        BydLogHelper.logD(simpleName, sb.toString());
        this.f1287b.registerListener(absBYDAutoSettingListener);
    }

    public void K2(int i) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            bYDAutoSettingDevice.setLanternLanguageMode(i);
        }
    }

    public int L() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getEnergyFeedback();
        }
        return 0;
    }

    public int L0() {
        if (this.f1287b == null) {
            return 0;
        }
        BydLogHelper.logD(m.class.getSimpleName(), "getPhysicalkeySecurlConfig ");
        return this.f1287b.getPhysicalkeySecurlConfig();
    }

    public int L1(int i) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.resetRearviewMirrorIsSets(i);
        }
        return 0;
    }

    public int L2(int i) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.setLeftHomeLightDelayValue(i);
        }
        return 0;
    }

    public int M() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getEngineSoundType();
        }
        return 0;
    }

    public int M0() {
        if (this.f1287b == null) {
            return 0;
        }
        BydLogHelper.logD(m.class.getSimpleName(), "getPhysicalkeySecurrConfig ");
        return this.f1287b.getPhysicalkeySecurrConfig();
    }

    public int M1(int i, int i2) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.saveSeatParamsAll(i, i2);
        }
        return 0;
    }

    public int M2(int i) {
        b.b.g.r.e.i(200008);
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.setLiftModeState(i);
        }
        return 0;
    }

    public int N() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getExhibitionMode();
        }
        return 0;
    }

    public int N0() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getRainAutoLockWindowState();
        }
        return 0;
    }

    public int N1(int i) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice == null) {
            return 0;
        }
        bYDAutoSettingDevice.setACAutoAir(i);
        return 0;
    }

    public int N2(int i) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice == null) {
            return 0;
        }
        bYDAutoSettingDevice.setLockCarRiseWindow(i);
        return 0;
    }

    public int O() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getFdComSpeedUpState();
        }
        return 0;
    }

    public int O0() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getRearAcPanelAutoLockTime();
        }
        return 0;
    }

    public int O1(int i) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice == null) {
            return 0;
        }
        bYDAutoSettingDevice.setACBTWind(i);
        return 0;
    }

    public int O2(int i) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice == null) {
            return 0;
        }
        bYDAutoSettingDevice.setLockOff(i);
        return 0;
    }

    public int P() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getFdDynamicSuspensionMode();
        }
        return 0;
    }

    public int P0() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getRearRightSeatGreetState();
        }
        return 0;
    }

    public int P1(int i) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice == null) {
            return 0;
        }
        bYDAutoSettingDevice.setACPauseCycle(i);
        return 0;
    }

    public void P2(int i, int i2) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            bYDAutoSettingDevice.setMassageMode(i, i2);
        }
    }

    public int Q() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getFridgeChildLock();
        }
        return 0;
    }

    public int Q0() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getRearSeatGreetState();
        }
        return 0;
    }

    public int Q1(int i) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice == null) {
            return 0;
        }
        bYDAutoSettingDevice.setACTunnelCycle(i);
        return 0;
    }

    public int Q2(int i) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice == null) {
            return 0;
        }
        bYDAutoSettingDevice.setMicroSwitchLockWindowState(i);
        return 0;
    }

    public int R() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getFridgeChildLockFlag();
        }
        return 0;
    }

    public int R0() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getRearViewEcuMirrorFlipState();
        }
        return 0;
    }

    public int R1(int i) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice == null) {
            return 0;
        }
        bYDAutoSettingDevice.setAcWarmMode(i);
        return 0;
    }

    public int R2(int i) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice == null) {
            return 0;
        }
        bYDAutoSettingDevice.setMicroSwitchUnlockWindowState(i);
        return 0;
    }

    public int S() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getFridgeDelayedPowerOutage();
        }
        return 0;
    }

    public int S0() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getRearViewMirrorFlip();
        }
        return 0;
    }

    public int S1(int i, int i2) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice == null) {
            return 0;
        }
        bYDAutoSettingDevice.setAmbientMusicModeState(i, i2);
        return 0;
    }

    public int S2(int i) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice == null) {
            return 0;
        }
        bYDAutoSettingDevice.setMotorPretensionerSeatbeltState(i);
        return 0;
    }

    public int T() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getFridgeDoorState();
        }
        return 0;
    }

    public int T0() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getRearViewMirrorFlipSet();
        }
        return 0;
    }

    public int T1(int i) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice == null) {
            return 0;
        }
        bYDAutoSettingDevice.setAutoExternalRearMirrorFollowUpSwitch(i);
        return 0;
    }

    public int T2(int i) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice == null) {
            return 0;
        }
        bYDAutoSettingDevice.setOverspeedLock(i);
        return 0;
    }

    public int U() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getFridgeExpansionAndContractionFunction();
        }
        return 0;
    }

    public int U0() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getRearviewMirrorIsAutoLightState();
        }
        return 0;
    }

    public int U1(int i) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice == null) {
            return 0;
        }
        bYDAutoSettingDevice.setAutoPowerOffState(i);
        return 0;
    }

    public int U2(int i) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice == null) {
            return 0;
        }
        bYDAutoSettingDevice.setPM25Power(i);
        return 0;
    }

    public int V() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getFridgeTemperatureRegulation();
        }
        return 0;
    }

    public int V0() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getRearviewMirrorIsState();
        }
        return 0;
    }

    public int V1(int i) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice == null) {
            return 0;
        }
        bYDAutoSettingDevice.setAutoPowerOnState(i);
        return 0;
    }

    public int V2(int i) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice == null) {
            return 0;
        }
        bYDAutoSettingDevice.setPM25SwitchCheck(i);
        return 0;
    }

    public int W() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getFridgeWorkColdTemperature();
        }
        return 0;
    }

    public int W0() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getRearviewMirrorIsTurnedState();
        }
        return 0;
    }

    public int W1(int i) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice == null) {
            return 0;
        }
        bYDAutoSettingDevice.setAutoPurification(i);
        return 0;
    }

    public int W2(int i) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice == null) {
            return 0;
        }
        bYDAutoSettingDevice.setPM25TimeCheck(i);
        return 0;
    }

    public int X() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getFridgeWorkHeatTemperature();
        }
        return 0;
    }

    public int X0() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getRefrigeratorDoorState();
        }
        return 0;
    }

    public int X1(int i) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice == null) {
            return 0;
        }
        bYDAutoSettingDevice.setAutoRainWiperState(i);
        return 0;
    }

    public void X2(int i) {
        b.b.g.r.e.i(210109);
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            BydLogHelper.logD("setPedalSuppressionSystemState =" + i + ", ret =" + bYDAutoSettingDevice.setPedalSuppressionSystemState(i));
        }
    }

    public int Y() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getFridgeWorkSaveTemperature();
        }
        return 0;
    }

    public int Y0() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getRefrigeratorMode();
        }
        return 0;
    }

    public int Y1(int i) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice == null) {
            return 0;
        }
        bYDAutoSettingDevice.setBackDoorOpenedHeight(i);
        return 0;
    }

    public int Y2(int i) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice == null) {
            return 0;
        }
        bYDAutoSettingDevice.setRainAutoLockWindowState(i);
        return 0;
    }

    public int Z() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getFridgeWorkingMode();
        }
        return 0;
    }

    public int Z0() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getRefrigeratorState();
        }
        return 1;
    }

    public void Z1(int i) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            bYDAutoSettingDevice.setBackHomeLightDelayValue(i);
        }
    }

    public int Z2(int i) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice == null) {
            return 0;
        }
        bYDAutoSettingDevice.setRearAcPanelAutoLockTime(i);
        return 0;
    }

    public int a() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getACAutoAir();
        }
        return 0;
    }

    public int a0() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getFrontFridgeDisinfectFlag();
        }
        return 0;
    }

    public int a1() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getRefrigeratorWorkMode();
        }
        return 0;
    }

    public int a2(int i) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice == null) {
            return 0;
        }
        bYDAutoSettingDevice.setBrakePedalMode(i);
        return 0;
    }

    public int a3(int i) {
        if (this.f1287b == null) {
            return 0;
        }
        BydLogHelper.logD(m.class.getSimpleName(), "setRearRightSeatGreetState " + i);
        this.f1287b.setRearRightSeatGreetState(i);
        return 0;
    }

    public int b() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getACBTWind();
        }
        return 0;
    }

    public int b0() {
        if (this.f1287b == null) {
            return 0;
        }
        BydLogHelper.logD(m.class.getSimpleName(), "getGoalRoadMode ");
        return this.f1287b.getGoalRoadMode();
    }

    public int b1() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getRefrigeratorWorkTemperature();
        }
        return 0;
    }

    public void b2(int i) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            bYDAutoSettingDevice.setCarDoorTipsSoundType(i);
        }
    }

    public void b3(int i) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            bYDAutoSettingDevice.setRearSeatGreetState(i);
        }
    }

    public int c() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getACPauseCycle();
        }
        return 0;
    }

    public int c0() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getHSpeedAutoCloseState();
        }
        return 0;
    }

    public int c1() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getRemberPowerOffState();
        }
        return 0;
    }

    public int c2(int i) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice == null) {
            return 0;
        }
        bYDAutoSettingDevice.setCarTipsSoundType(i);
        return 0;
    }

    public int c3(int i) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice == null) {
            return 0;
        }
        bYDAutoSettingDevice.setRearViewMirrorFlip(i);
        return 0;
    }

    public int d() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getACTunnelCycle();
        }
        return 0;
    }

    public int d0() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getHeadlampLevel();
        }
        return 0;
    }

    public int d1() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getRemenberPowerOffState();
        }
        return 0;
    }

    public int d2(int i, int i2) {
        return BYDAutoAudioDevice.getInstance(b.b.a.a.a.b().a()).setCarTipsVoiceState(i, i2);
    }

    public int d3(int i) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.setRearviewMirrorIsAutoLightState(i);
        }
        return 0;
    }

    public int e() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getAcWarmMode();
        }
        return 0;
    }

    public int e0() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getHevChargingPort();
        }
        return 0;
    }

    public int e1() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getRemoteControlDownwindowState();
        }
        return 0;
    }

    public int e2(int i, int i2) {
        b.b.g.r.e.j(140003, i2);
        b.b.g.r.e.j(140013, i2);
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice == null) {
            return 0;
        }
        bYDAutoSettingDevice.setChargingPort(i);
        return 0;
    }

    public int e3(int i) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.setRearviewMirrorIsScreenState(i);
        }
        return 0;
    }

    public int f() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getAmbientLightColorSupport();
        }
        return 0;
    }

    public int f0() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getHudBrightness();
        }
        return 0;
    }

    public int f1() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getRemoteControlUpwindowState();
        }
        return 0;
    }

    public int f2(int i) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.setComSpeedDownState(i);
        }
        return 0;
    }

    public int f3(int i) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.setRearviewMirrorIsTurnedState(i);
        }
        return 0;
    }

    public int g() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getAmbientLightSupport();
        }
        return 0;
    }

    public int g0(int i) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getHudDisplayContent(i);
        }
        return 0;
    }

    public int g1() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getRuningCloseDoorState();
        }
        return 0;
    }

    public int g2(int i) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice == null) {
            return 0;
        }
        bYDAutoSettingDevice.setComfortableParkingState(i);
        return 0;
    }

    public int g3(int i) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice == null) {
            return 0;
        }
        bYDAutoSettingDevice.setRefrigeratorMode(i);
        return 0;
    }

    public int h(int i) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getAmbientMusicModeState(i);
        }
        return 0;
    }

    public int h0() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getHudHeight();
        }
        return 0;
    }

    public int h1() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getSOCTarget();
        }
        return 0;
    }

    public int h2(int i) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice == null) {
            return 0;
        }
        bYDAutoSettingDevice.setCpdImsSwitchState(i);
        return 0;
    }

    public int h3(int i) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.setRefrigeratorWorkMode(i);
        }
        return 0;
    }

    public int i() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getAutoExternalRearMirrorFollowUpSwitch();
        }
        return 0;
    }

    public int i0() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getHudMode();
        }
        return 0;
    }

    public int[] i1() {
        int iR = u.I().r();
        return iR == 3 ? new int[]{15, 70} : iR == 2 ? new int[]{25, 70} : new int[]{25, 70};
    }

    public int i2(int i) {
        if (this.f1287b == null) {
            return 0;
        }
        BydLogHelper.logD(m.class.getSimpleName(), "setCuraSwitchState " + i);
        this.f1287b.setCuraSwitchState(i);
        return 0;
    }

    public int i3(int i) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.setRefrigeratorWorkTemperature(i);
        }
        return 0;
    }

    public int j() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getAutoExternalRearMirrorFollowUpSwitchSupport();
        }
        return 0;
    }

    public int j0() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getHudModeParams();
        }
        return 0;
    }

    public int j1() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getSafeBeltWarnState();
        }
        return 0;
    }

    public int j2(int i) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.setDelayPowerDownState(i);
        }
        return 0;
    }

    public int j3(int i) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.setRemberPowerOffState(i);
        }
        return 0;
    }

    public int k() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getAutoPowerOffState();
        }
        return 0;
    }

    public int k0() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getHudRotationAngle();
        }
        return 0;
    }

    public int k1() {
        return this.f1287b.getSandAbsModeState();
    }

    public int k2(int i) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.setDelayPowerDownTime(i);
        }
        return 0;
    }

    public int k3(int i) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice == null) {
            return 0;
        }
        bYDAutoSettingDevice.setRemenberPowerOffState(i);
        return 0;
    }

    public int l() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getAutoPowerOnState();
        }
        return 0;
    }

    public int l0() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getHudState();
        }
        return 0;
    }

    public int l1(int i) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getSeatHeatingState(i);
        }
        return 0;
    }

    public int l2(int i) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.setDelayPowerOffTime(i);
        }
        return 0;
    }

    public int l3(int i) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice == null) {
            return 0;
        }
        bYDAutoSettingDevice.setSpeedReminderValue(i);
        return 0;
    }

    public int m() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getAutoPurificationState();
        }
        return 0;
    }

    public int m0() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getIALArea();
        }
        return 0;
    }

    public int m1(int i) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getMassageLevel(i);
        }
        return 2;
    }

    public int m2(int i) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice == null) {
            return 0;
        }
        bYDAutoSettingDevice.setDisinfState(i);
        return 0;
    }

    public int m3(int i) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice == null) {
            return 0;
        }
        bYDAutoSettingDevice.setRemoteControlUpwindowState(i);
        return 0;
    }

    public int n() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getAutoRainWiperState();
        }
        return 0;
    }

    public int n0(int i) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getIALBrightness(i);
        }
        return 0;
    }

    public int n1(int i) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getMassageMode(i);
        }
        return 2;
    }

    public int n2(int i) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice == null) {
            return 0;
        }
        bYDAutoSettingDevice.setDoorknobAutoShrinkState(i);
        return 0;
    }

    public void n3(int i) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            bYDAutoSettingDevice.setRemoteControlDownwindowState(i);
        }
    }

    public int o() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getBackDoorOpenedHeight();
        }
        return 0;
    }

    public int o0(int i) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getIALColor(i);
        }
        return 0;
    }

    public int o1(int i) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getSeatVentilatingState(i);
        }
        return 0;
    }

    public int o2(int i) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice == null) {
            return 0;
        }
        bYDAutoSettingDevice.setDriverSeatAutoReturn(i);
        return 0;
    }

    public int o3(int i) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice == null) {
            return 0;
        }
        bYDAutoSettingDevice.setRuningCloseDoorState(i);
        return 0;
    }

    public int p() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getBackHomeLightDelayValue();
        }
        return 0;
    }

    public int p1() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getSmartWelcomeLightState();
        }
        return 0;
    }

    public int p2(int i) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice == null) {
            return 0;
        }
        bYDAutoSettingDevice.setDynamicSuspensionMode(i);
        return 0;
    }

    public int p3(int i, int i2) {
        b.b.g.r.e.j(150016, i2);
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice == null) {
            return 0;
        }
        bYDAutoSettingDevice.setSOCTarget(i);
        return 0;
    }

    public int q() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getBrakePedalMode();
        }
        return 0;
    }

    public int q0() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getIntelligentAcAreaState();
        }
        return 0;
    }

    public int q1() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getSocTargetLowLimit();
        }
        return 0;
    }

    public int q2(int i) {
        b.b.g.r.e.j(150015, i == 3 ? 2 : 3);
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice == null) {
            return 0;
        }
        bYDAutoSettingDevice.setEnergyFeedback(i);
        return 0;
    }

    public int q3(int i) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.setSafeBeltWarnState(i);
        }
        return 0;
    }

    public int r() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getCarFridgeType();
        }
        return 0;
    }

    public int r0() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getIntelligentLockState();
        }
        return 0;
    }

    public int r1() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getSocTargetRange();
        }
        return 0;
    }

    public int r2(int i) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice == null) {
            return 0;
        }
        bYDAutoSettingDevice.setEngineSoundType(i);
        return 0;
    }

    public int r3(int i) {
        return this.f1287b.setSandAbsModeState(i);
    }

    public int s() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getCarTipsSoundType();
        }
        return 0;
    }

    public int s0() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getIntelligentUnlockState();
        }
        return 0;
    }

    public int s1() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getSteerAssis();
        }
        return 0;
    }

    public int s2(int i) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.setFridgeChildLock(i);
        }
        return 0;
    }

    public void s3(int i, int i2) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            bYDAutoSettingDevice.setMassageLevel(i, i2);
        }
    }

    public int t(int i) {
        return BYDAutoAudioDevice.getInstance(b.b.a.a.a.b().a()).getCarTipsVoiceState(i);
    }

    public int t0() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getKeyVibratorLevel();
        }
        return 0;
    }

    public int t1() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getSteerAssisSupported();
        }
        return 0;
    }

    public int t2(int i) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.setFridgeDoorState(i);
        }
        return 0;
    }

    public void t3(int i) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            bYDAutoSettingDevice.setSmartWelcomeLightState(i);
        }
    }

    public int u() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getChargingPort();
        }
        return 0;
    }

    public int u0() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getLanternLanguageMode();
        }
        return 0;
    }

    public int u1() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getSteerPositionAutoReturn();
        }
        return 0;
    }

    public int u2(int i) {
        if (this.f1287b == null) {
            return 0;
        }
        BydLogHelper.logD(m.class.getSimpleName(), "setGoalRoadMode " + i);
        this.f1287b.setGoalRoadMode(i);
        return 0;
    }

    public int u3(int i) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice == null) {
            return 0;
        }
        bYDAutoSettingDevice.setSteerAssis(i);
        return 0;
    }

    public int v() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getComSpeedUpState();
        }
        return 0;
    }

    public int v0() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getLeftHeadlampLevel();
        }
        return 0;
    }

    public int v1() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getSterilizationState();
        }
        return 0;
    }

    public int v2(int i) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.setHSpeedAutoCloseState(i);
        }
        return 0;
    }

    public int v3(int i) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice == null) {
            return 0;
        }
        bYDAutoSettingDevice.setSteerPositionAutoReturn(i);
        return 0;
    }

    public int w() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getComfortableParkingState();
        }
        return 0;
    }

    public int w0() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getLeftHomeLightDelayValue();
        }
        return 0;
    }

    public int w1(int i) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getSupportSeatMassage(i);
        }
        return 0;
    }

    public int w2(int i) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice == null) {
            return 0;
        }
        bYDAutoSettingDevice.setHeadlampLevel(i);
        return 0;
    }

    public int w3(int i) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.setSterilizationState(i);
        }
        return 0;
    }

    public int x() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getCpdImsSwitchState();
        }
        return 0;
    }

    public int x0() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getLiftModeState();
        }
        return 0;
    }

    public int x1() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getSwcontrolState();
        }
        return 0;
    }

    public int x2(int i) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice == null) {
            return 0;
        }
        bYDAutoSettingDevice.setHudBrightness(i);
        return 0;
    }

    public int x3(int i) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice == null) {
            return 0;
        }
        bYDAutoSettingDevice.setTrailerLightCheckState(i);
        return 0;
    }

    public int y() {
        if (this.f1287b == null) {
            return 0;
        }
        BydLogHelper.logD(m.class.getSimpleName(), "getCuraSwitchState ");
        return this.f1287b.getCuraSwitchState();
    }

    public int y0() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getLockCarRiseWindow();
        }
        return 0;
    }

    public int y1() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getTrailerLightCheckState();
        }
        return 0;
    }

    public int y2(int i, int i2) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice == null) {
            return 0;
        }
        bYDAutoSettingDevice.setHudDisplayContent(i, i2);
        return 0;
    }

    public int y3(int i) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice == null) {
            return 0;
        }
        bYDAutoSettingDevice.setTrayState(i);
        return 0;
    }

    public int z() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getDelayPowerDownTime();
        }
        return 0;
    }

    public int z0() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getLockOff();
        }
        return 0;
    }

    public int z1() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            return bYDAutoSettingDevice.getTrailerModeMileage();
        }
        return 0;
    }

    public int z2(int i) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice == null) {
            return 0;
        }
        bYDAutoSettingDevice.setHudHeight(i);
        return 0;
    }

    public void z3(int i, int i2) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.f1287b;
        if (bYDAutoSettingDevice != null) {
            bYDAutoSettingDevice.turnHeadrestTurnHeight(i, i2);
        }
    }
}
