package b.b.g.k;

import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener;
import android.hardware.bydauto.dipilot.BYDAutoDiPilotDevice;
import androidx.appcompat.widget.ActivityChooserView;
import com.byd.base.hilt.Router;
import com.byd.base.hilt.inter.IAdasDataRepository;
import com.byd.base.hilt.inter.IStudyConfig;
import com.byd.carsettings.R;
import com.bydux.api.common.tools.log.BydLogHelper;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: compiled from: BydDiPilotListener.java */
/* JADX INFO: loaded from: classes2.dex */
public class i extends AbsBYDAutoDiPilotListener implements IAdasDataRepository {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public volatile byte f847d;
    public BYDAutoDiPilotDevice u0;
    public volatile boolean v0;
    public volatile int w0;
    public short x0;
    public byte y0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f845b = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public volatile int f846c = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public volatile int f848e = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public volatile int f849f = Integer.MIN_VALUE;
    public volatile int g = Integer.MIN_VALUE;
    public volatile int h = Integer.MIN_VALUE;
    public volatile int i = Integer.MIN_VALUE;
    public volatile int j = Integer.MIN_VALUE;
    public volatile int k = Integer.MIN_VALUE;
    public volatile int l = Integer.MIN_VALUE;
    public volatile int m = Integer.MIN_VALUE;
    public volatile int n = Integer.MIN_VALUE;
    public volatile int o = Integer.MIN_VALUE;
    public volatile int p = Integer.MIN_VALUE;
    public volatile int q = Integer.MIN_VALUE;
    public volatile int r = Integer.MIN_VALUE;
    public volatile int s = Integer.MIN_VALUE;
    public volatile int t = Integer.MIN_VALUE;
    public volatile int u = Integer.MIN_VALUE;
    public volatile int v = Integer.MIN_VALUE;
    public volatile int w = Integer.MIN_VALUE;
    public volatile int x = Integer.MIN_VALUE;
    public volatile int y = Integer.MIN_VALUE;
    public volatile int z = Integer.MIN_VALUE;
    public volatile int A = Integer.MIN_VALUE;
    public volatile int B = Integer.MIN_VALUE;
    public volatile int C = Integer.MIN_VALUE;
    public volatile int D = Integer.MIN_VALUE;
    public volatile int E = Integer.MIN_VALUE;
    public volatile int F = Integer.MIN_VALUE;
    public volatile int G = Integer.MIN_VALUE;
    public volatile int H = Integer.MIN_VALUE;
    public volatile int I = Integer.MIN_VALUE;
    public volatile int J = Integer.MIN_VALUE;
    public volatile int K = Integer.MIN_VALUE;
    public volatile int L = Integer.MIN_VALUE;
    public volatile int M = Integer.MIN_VALUE;
    public volatile int N = Integer.MIN_VALUE;
    public volatile int O = Integer.MIN_VALUE;
    public volatile int P = Integer.MIN_VALUE;
    public volatile int Q = Integer.MIN_VALUE;
    public volatile int R = Integer.MIN_VALUE;
    public volatile int S = Integer.MIN_VALUE;
    public volatile int T = Integer.MIN_VALUE;
    public volatile int U = Integer.MIN_VALUE;
    public volatile int V = Integer.MIN_VALUE;
    public volatile int W = Integer.MIN_VALUE;
    public volatile byte X = -1;
    public volatile int Y = Integer.MIN_VALUE;
    public volatile int Z = Integer.MIN_VALUE;
    public volatile int a0 = Integer.MIN_VALUE;
    public volatile int b0 = Integer.MIN_VALUE;
    public volatile int c0 = Integer.MIN_VALUE;
    public volatile int d0 = Integer.MIN_VALUE;
    public volatile int e0 = Integer.MIN_VALUE;
    public volatile int f0 = Integer.MIN_VALUE;
    public volatile int g0 = Integer.MIN_VALUE;
    public volatile int h0 = Integer.MIN_VALUE;
    public volatile int i0 = Integer.MIN_VALUE;
    public volatile int j0 = Integer.MIN_VALUE;
    public volatile int k0 = Integer.MIN_VALUE;
    public volatile int l0 = Integer.MIN_VALUE;
    public volatile int m0 = Integer.MIN_VALUE;
    public volatile int n0 = Integer.MIN_VALUE;
    public volatile int o0 = Integer.MIN_VALUE;
    public volatile int p0 = Integer.MIN_VALUE;
    public volatile int q0 = Integer.MIN_VALUE;
    public volatile int r0 = Integer.MIN_VALUE;
    public volatile int s0 = Integer.MIN_VALUE;
    public volatile int t0 = Integer.MIN_VALUE;

    public i() {
        Router.b().h(this);
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = BYDAutoDiPilotDevice.getInstance(Router.b().a());
        this.u0 = bYDAutoDiPilotDevice;
        this.f847d = (byte) bYDAutoDiPilotDevice.getUrbanAIAssistanceState();
        this.u0.registerListener((AbsBYDAutoDiPilotListener) this);
    }

    @Override // com.byd.base.hilt.inter.IAdasDataRepository
    public byte a() {
        return this.f847d;
    }

    @Override // com.byd.base.hilt.inter.IAdasDataRepository
    public void b() {
        if (this.u0 == null || this.v0) {
            BydLogHelper.logW(AbsBYDAutoDiPilotListener.TAG, "initFault wrong! mHasInitFault = " + this.v0);
            return;
        }
        int i = this.u0.getEmergencyLaneKeepFault() == 1 ? 1 : 0;
        if (this.u0.getBlindFault() == 1) {
            i |= 2;
        }
        if (this.u0.getDoorOpenFault() == 1) {
            i |= 4;
        }
        if (this.u0.getRearCrossAlartFault() == 1) {
            i |= 262144;
        }
        if (this.u0.getRearCrossBrakeFault() == 1) {
            i |= 524288;
        }
        if (this.u0.getRearCollisionWarnFault() == 1) {
            i |= 16384;
        }
        if (this.u0.getFrontCrossAlarkFault() == 1) {
            i |= 65536;
        }
        if (this.u0.getFrontCrossBrakeFault() == 1) {
            i |= 131072;
        }
        if (this.u0.getAutoEmergencyFault() == 1) {
            i |= 8192;
        }
        if (this.u0.getIntelligentLimitFaultController() == 1) {
            i |= 16777216;
        }
        if (this.u0.getForwardCollisionWarnFault() == 1) {
            i |= 32768;
        }
        if (this.u0.getAdaptiveFrontLightFault() == 1) {
            i |= 16;
        }
        if (this.u0.getTrafficAttentionFault() == 1) {
            i |= 8;
        }
        if (this.u0.getTsrFault() == 1) {
            i |= 1048576;
        }
        if (this.u0.getIntelligentLimitFaultCall() == 1) {
            i |= 4194304;
        }
        if (this.u0.getLimitSpeedChangeFault() == 1) {
            i |= 2097152;
        }
        if (this.u0.getBSISFault() == 1) {
            i |= 67108864;
        }
        if (this.u0.getMOISFault() == 1) {
            i |= 134217728;
        }
        if (this.u0.getHighNaviFault() == 1) {
            i |= 1 << i(64);
        }
        if (this.u0.getNrbanNaviFault() == 1) {
            i |= 1 << i(65);
        }
        if (this.u0.getInteractiveLaneChangeFault() == 1) {
            i |= 1 << i(67);
        }
        if (this.u0.getLaneCenteringControlFault() == 1) {
            i |= 1 << i(68);
        }
        this.w0 = i;
        this.v0 = true;
        this.X = (byte) this.u0.getTrafficSignRecognize2B4State();
        BydLogHelper.logW(AbsBYDAutoDiPilotListener.TAG, "initFault fault = " + Integer.toBinaryString(i));
    }

    @Override // com.byd.base.hilt.inter.IAdasDataRepository
    public byte c() {
        return this.X;
    }

    @Override // com.byd.base.hilt.inter.IAdasDataRepository
    public void d() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = this.u0;
        if (bYDAutoDiPilotDevice != null) {
            bYDAutoDiPilotDevice.unregisterListener((AbsBYDAutoDiPilotListener) this);
            this.u0 = null;
        }
        this.v0 = false;
        this.w0 = 0;
    }

    @Override // com.byd.base.hilt.inter.IAdasDataRepository
    public int e() {
        return this.O;
    }

    @Override // com.byd.base.hilt.inter.IAdasDataRepository
    public void f(byte b2) {
        this.y0 = b2;
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = this.u0;
        if (bYDAutoDiPilotDevice == null || this.x0 != 0) {
            return;
        }
        int i = bYDAutoDiPilotDevice.getLKSFaultState() == 1 ? 1 : 0;
        if (this.u0.getLDWFaultState() == 1) {
            i |= 2;
        }
        if (this.u0.getLaneDeparturePreventionFault() == 1) {
            i |= 4;
        }
        if (this.u0.getLaneDepartureWarnFault() == 1) {
            i |= 8;
        }
        this.O = this.u0.getLaneAssistType();
        if (this.u0.getAEBFaultState() == 1) {
            i |= 32;
        }
        if (this.u0.getFCWFaultState() == 1) {
            i |= 64;
        }
        if (this.u0.getVRUFaultState() == 1) {
            i |= 128;
        }
        this.x0 = (short) i;
        BydLogHelper.logI(AbsBYDAutoDiPilotListener.TAG, "initLaneFault =" + Integer.toHexString(i));
    }

    @Override // com.byd.base.hilt.inter.IAdasDataRepository
    public boolean g(byte b2) {
        return j(this.x0, b2) == 0;
    }

    @Override // com.byd.base.hilt.inter.IAdasDataRepository
    public boolean h(int i) {
        return this.v0 && ((this.w0 >>> i(i)) & 1) == 0;
    }

    public final int i(int i) {
        return i > 64 ? i - 59 : i;
    }

    public final int j(short s, byte b2) {
        return b2 == 11 ? s & 3 : b2 == 12 ? (s >>> 2) & 7 : b2 == 13 ? (s >>> 5) & 7 : s;
    }

    public final boolean k(int i, boolean z) {
        if (!this.v0 || n(i)) {
            BydLogHelper.logI(AbsBYDAutoDiPilotListener.TAG, "isChangeFaultState wrong! mHasInitFault = " + this.v0);
            return false;
        }
        int i2 = i(i);
        int i3 = this.w0 & ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        if (!((((i3 >>> i2) & 1) == 1) ^ z)) {
            BydLogHelper.logD(AbsBYDAutoDiPilotListener.TAG, "isChangeFaultState mask " + i2 + " return false");
            return false;
        }
        this.w0 = p(i2, z, i3);
        BydLogHelper.logI(AbsBYDAutoDiPilotListener.TAG, "isChangeFaultState mask " + i2 + " fault " + z + " return true");
        return true;
    }

    public final short l(int i, boolean z) {
        int i2 = this.x0 & Short.MAX_VALUE;
        if (!((((i2 >>> i) & 1) == 1) ^ z)) {
            return (short) -1;
        }
        short sP = (short) p(i, z, i2);
        BydLogHelper.logI(AbsBYDAutoDiPilotListener.TAG, "isLaneFaultChange mask " + i + " fault " + z);
        this.x0 = sP;
        return sP;
    }

    public final int m(int i, byte b2) {
        byte b3 = b2 <= 1 ? (byte) 11 : b2 >= 5 ? (byte) 13 : (byte) 12;
        boolean zG = g(b3);
        short sL = l(b2, i == 1);
        if (sL == -1) {
            return -1;
        }
        short sJ = (short) j(sL, b3);
        if (zG == (sJ == 0)) {
            return -1;
        }
        return sJ;
    }

    public final boolean n(int i) {
        return !Router.b().f().c(i);
    }

    public final void o(int i) {
        if (b.b.g.r.e.c(i)) {
            b.b.g.r.e.h(i);
        }
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onACCStateChanged(int i) {
        if (this.h == i) {
            return;
        }
        this.h = i;
        super.onACCStateChanged(i);
        BydLogHelper.logD("onAccStateChanged " + i);
        if (n(70)) {
            return;
        }
        o(210113);
        EventBus.getDefault().post(new b.b.a.c.a(210113, i));
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onAEBFaultStateStateChanged(int i) {
        int iM = m(i, (byte) 5);
        BydLogHelper.logD("onAEBFaultStateStateChanged " + i + ", ret =" + iM);
        if (iM == -1) {
            return;
        }
        EventBus.getDefault().post(new b.b.a.c.a(210080, iM));
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onAINavigationStateChanged(int i) {
        if (this.i == i) {
            return;
        }
        this.i = i;
        super.onAINavigationStateChanged(i);
        BydLogHelper.logD("onAINavigationStateChanged " + i);
        if (n(68)) {
            return;
        }
        if (b.b.g.r.e.c(210013)) {
            BydLogHelper.logD("hasMessages AI_NAVIGATION_STATE_CHANGE return!");
        } else {
            o(210013);
            EventBus.getDefault().post(new b.b.a.c.a(210013, i));
        }
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onAdaptiveFrontLightFault(byte b2) {
        BydLogHelper.logD("onAdaptiveFrontLightFault " + ((int) b2));
        if (k(4, b2 == 1) && !Router.b().f().d(4)) {
            EventBus.getDefault().post(new b.b.a.c.a(210098, (int) b2));
        }
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onAiCareStateChanged(int i) {
        if (this.k == i) {
            return;
        }
        this.k = i;
        super.onAiCareStateChanged(i);
        BydLogHelper.logD("onAiCareStateChanged " + i);
        EventBus.getDefault().post(new b.b.a.c.a(210016, i));
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onAiDistanceLight2B4StateChanged(int i) {
        if (this.c0 == i) {
            return;
        }
        this.c0 = i;
        BydLogHelper.logD("onAiDistanceLight2B4StateChanged " + i);
        if (b.b.g.r.e.c(210020)) {
            BydLogHelper.logD("hasMessages AI_DISTANCE_LIGHT_2B4_STATE_CHANGE return!");
        } else {
            EventBus.getDefault().post(new b.b.a.c.a(210020, i));
        }
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onAiDistanceLight316StateChanged(int i) {
        if (this.d0 == i) {
            return;
        }
        this.d0 = i;
        BydLogHelper.logD("onAiDistanceLight316StateChanged " + i);
        if (b.b.g.r.e.c(210020)) {
            BydLogHelper.logD("hasMessages AI_DISTANCE_LIGHT_316_STATE_CHANGE return!");
        } else {
            EventBus.getDefault().post(new b.b.a.c.a(210020, i));
        }
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onAiEmergencyBrakeStateChanged(int i) {
        if (this.s == i) {
            return;
        }
        this.s = i;
        super.onAiEmergencyBrakeStateChanged(i);
        BydLogHelper.logD("onAiEmergencyBrakeStateChanged " + i);
        if (b.b.g.r.e.c(210028)) {
            BydLogHelper.logD("hasMessages AI_EMERGENCY_BRAKE_STATE_CHANGE return!");
        } else {
            o(210028);
            EventBus.getDefault().post(new b.b.a.c.a(210028, i));
        }
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onAiMatrixLight3ACStateChanged(int i) {
        if (this.k0 == i) {
            return;
        }
        this.k0 = i;
        BydLogHelper.logD("onAiMatrixLight3ACStateChanged " + i);
        if (Router.b().f().d(4)) {
            if (b.b.g.r.e.c(210071)) {
                BydLogHelper.logD("hasMessages AI_MATRIX_LIGHT_3AC_STATE_CHANGE return!");
            } else {
                o(210071);
                EventBus.getDefault().post(new b.b.a.c.a(210071, i));
            }
        }
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onAiMatrixLight3ADStateChanged(int i) {
        if (this.l0 == i) {
            return;
        }
        this.l0 = i;
        BydLogHelper.logD("onAiMatrixLight3ADStateChanged " + i);
        if (Router.b().f().d(4)) {
            if (b.b.g.r.e.c(210071)) {
                BydLogHelper.logD("hasMessages AI_MATRIX_LIGHT_3AD_STATE_CHANGE return!");
            } else {
                EventBus.getDefault().post(new b.b.a.c.a(210072, i));
            }
        }
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onAiMatrixLightStateChanged(int i) {
        if (this.m == i) {
            return;
        }
        this.m = i;
        super.onAiMatrixLightStateChanged(i);
        BydLogHelper.logD("onAiMatrixLightStateChanged " + i);
        EventBus.getDefault().post(new b.b.a.c.a(210019, i));
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onAiTurnLightStateChanged(int i) {
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onAlarmTypeChanged(int i) {
        if (this.j == i) {
            return;
        }
        this.j = i;
        super.onAlarmTypeChanged(i);
        BydLogHelper.logD("onAlarmTypeChanged " + i);
        if (b.b.g.r.e.c(210014)) {
            BydLogHelper.logD("hasMessages ALARM_TYPE_CHANGE return!");
        } else {
            o(210014);
            EventBus.getDefault().post(new b.b.a.c.a(210014, i));
        }
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onAttentionDetectionStateChanged(int i) {
        if (this.D == i) {
            return;
        }
        this.D = i;
        super.onAttentionDetectionStateChanged(i);
        BydLogHelper.logD("onAttentionDetectionStateChanged " + i);
        EventBus.getDefault().post(new b.b.a.c.a(210040, i));
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onAutoEmergencyFault(byte b2) {
        BydLogHelper.logD("onAutoEmergencyFault " + ((int) b2));
        if (k(13, b2 == 1)) {
            EventBus.getDefault().post(new b.b.a.c.a(210092, (int) b2));
        }
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onAutoParkingStateChanged(int i) {
        if (this.w == i) {
            return;
        }
        this.w = i;
        super.onAutoParkingStateChanged(i);
        BydLogHelper.logD("onAutoParkingStateChanged " + i);
        if (b.b.g.r.e.c(210033)) {
            BydLogHelper.logD("hasMessages AUTO_PARKING_STATE_CHANGE return!");
        } else {
            o(210033);
            EventBus.getDefault().post(new b.b.a.c.a(210033, i));
        }
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onBSISFault(byte b2) {
        if (k(26, b2 == 1)) {
            BydLogHelper.logD("onBSISFault " + ((int) b2));
            EventBus.getDefault().post(new b.b.a.c.a(210104, (int) b2));
        }
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onBSISSwitchChange(int i) {
        if (this.p0 == i) {
            return;
        }
        this.p0 = i;
        super.onBSISSwitchChange(i);
        if (b.b.g.r.e.c(210076)) {
            BydLogHelper.logD("hasMessages BLIND_SPOT_CODE return!");
        } else {
            o(210076);
            EventBus.getDefault().post(new b.b.a.c.a(210076, i));
        }
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onBSISSystemChange(int i) {
        if (this.q0 == i) {
            return;
        }
        this.q0 = i;
        super.onBSISSystemChange(i);
        EventBus.getDefault().post(new b.b.a.c.a(210079, i));
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onBlindFault(byte b2) {
        BydLogHelper.logD("onBlindFault " + ((int) b2));
        if (k(1, b2 == 1)) {
            EventBus.getDefault().post(new b.b.a.c.a(210089, (int) b2));
        }
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onBlindMonitorStateChanged(int i) {
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onBlindMonitorStateChanged1(int i) {
        if (this.m0 == i) {
            return;
        }
        this.m0 = i;
        super.onBlindMonitorStateChanged1(i);
        BydLogHelper.logD("onBlindMonitorStateChanged1 " + i);
        IStudyConfig iStudyConfigF = Router.b().f();
        if (iStudyConfigF.d(1) && iStudyConfigF.c(1)) {
            if (b.b.g.r.e.c(210023)) {
                BydLogHelper.logD("hasMessages BLIND_MONITOR_STATE_CHANGE1 return!");
            } else {
                o(210023);
                EventBus.getDefault().post(new b.b.a.c.a(210023, i));
            }
        }
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onBlindMonitorStateChanged2(int i) {
        if (this.n0 == i) {
            return;
        }
        this.n0 = i;
        super.onBlindMonitorStateChanged2(i);
        BydLogHelper.logD("onBlindMonitorStateChanged2 " + i);
        IStudyConfig iStudyConfigF = Router.b().f();
        if (!iStudyConfigF.c(1) || iStudyConfigF.d(1)) {
            return;
        }
        if (b.b.g.r.e.c(210023)) {
            BydLogHelper.logD("hasMessages BLIND_MONITOR_STATE_CHANGE2 return!");
        } else {
            EventBus.getDefault().post(new b.b.a.c.a(210023, i));
        }
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onChargerCoverStateChanged(int i) {
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onChildCareStateChanged(int i) {
        if (this.G == i) {
            return;
        }
        this.G = i;
        super.onChildCareStateChanged(i);
        BydLogHelper.logD("onChildCareStateChanged " + i);
        EventBus.getDefault().post(new b.b.a.c.a(210043, i));
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onCpdStatusChanged(int i) {
        if (this.o0 == i) {
            return;
        }
        this.o0 = i;
        super.onCpdStatusChanged(i);
        if (b.b.g.r.e.c(210075)) {
            return;
        }
        EventBus.getDefault().post(new b.b.a.c.a(210075, i));
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onDangerousActionDetectionStateChanged(int i) {
        if (this.E == i) {
            return;
        }
        this.E = i;
        super.onDangerousActionDetectionStateChanged(i);
        BydLogHelper.logD("onDangerousActionDetectionStateChanged " + i);
        EventBus.getDefault().post(new b.b.a.c.a(210041, i));
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onDataChanged(a.b.c cVar) {
        BydLogHelper.logD("onDataChanged ibydAutoEvent: " + cVar.toString());
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
        BydLogHelper.logD("onDataEventChanged i: " + i + " == bydAutoEventValue: " + bYDAutoEventValue.toString());
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onDistractionDetectionAidStateChanged(int i) {
        if (this.C == i) {
            return;
        }
        this.C = i;
        super.onDistractionDetectionAidStateChanged(i);
        BydLogHelper.logD("onDistractionDetectionAidStateChanged " + i);
        EventBus.getDefault().post(new b.b.a.c.a(210039, i));
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onDoorOpenFault(byte b2) {
        BydLogHelper.logD("onDoorOpenFault " + ((int) b2));
        if (k(2, b2 == 1)) {
            EventBus.getDefault().post(new b.b.a.c.a(210083, (int) b2));
        }
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onDriveDepartureStateChanged(int i) {
        if (this.M == i) {
            return;
        }
        this.M = i;
        super.onDriveDepartureStateChanged(i);
        BydLogHelper.logD("onDriveDepartureStateChanged " + i);
        EventBus.getDefault().post(new b.b.a.c.a(210001, i));
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onDriveLevelChanged(int i) {
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onDriveRemindStateChanged(int i) {
        if (this.L == i) {
            return;
        }
        this.L = i;
        super.onDriveRemindStateChanged(i);
        BydLogHelper.logD("onDriveRemindStateChanged " + i);
        EventBus.getDefault().post(new b.b.a.c.a(210000, i));
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onDrivieDepartureStateChanged(int i) {
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onDrivieRemindStateChanged(int i) {
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onE2EAIAssistanceStateChanged(int i) {
        if (this.f848e == i) {
            return;
        }
        this.f848e = i;
        super.onE2EAIAssistanceStateChanged(i);
        BydLogHelper.logD("onE2EAIAssistanceStateChanged " + i);
        if (b.b.g.r.e.c(210011)) {
            BydLogHelper.logD("hasMessages E2E_AI_ASSISTANCE_STATE_CHANGE return!");
        } else {
            o(210011);
            EventBus.getDefault().post(new b.b.a.c.a(210011, i));
        }
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onElecCarBodyStablySysStateChanged(int i) {
        if (this.v == i) {
            return;
        }
        this.v = i;
        BydLogHelper.logD("onElecCarBodyStablySysStateChanged " + i);
        if (n(5)) {
            return;
        }
        if (b.b.g.r.e.c(210032)) {
            BydLogHelper.logD("hasMessages ELEC_CAR_BODY_STABLY_SYS_STATE_CHANGE return!");
        } else {
            o(210032);
            EventBus.getDefault().post(new b.b.a.c.a(210032, i));
        }
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onEmergencyLaneAssistance2F0StateChanged(int i) {
        if (this.h0 == i) {
            return;
        }
        this.h0 = i;
        super.onEmergencyLaneAssistance2F0StateChanged(i);
        BydLogHelper.logD("onEmergencyLaneAssistance2F0StateChanged " + i);
        if (b.b.g.r.e.c(210067)) {
            BydLogHelper.logD("hasMessages EMERGENCY_LANE_ASSISTANCE_2F0_STATE_CHANGE return!");
        } else {
            EventBus.getDefault().post(new b.b.a.c.a(210067, i));
        }
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onEmergencyLaneAssistance32FStateChanged(int i) {
        if (this.g0 == i) {
            return;
        }
        this.g0 = i;
        super.onEmergencyLaneAssistance32FStateChanged(i);
        BydLogHelper.logD("onEmergencyLaneAssistance32FStateChanged " + i);
        if (b.b.g.r.e.c(210067)) {
            BydLogHelper.logD("hasMessages EMERGENCY_LANE_ASSISTANCE_32F_STATE_CHANGE return!");
        } else {
            EventBus.getDefault().post(new b.b.a.c.a(210067, i));
        }
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onEmergencyLaneKeepFault(byte b2) {
        if (k(0, b2 == 1)) {
            BydLogHelper.logD("onEmergencyLaneKeepFault " + ((int) b2));
            EventBus.getDefault().post(new b.b.a.c.a(210093, (int) b2));
        }
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onError(int i, String str) {
        BydLogHelper.logD("onError i: " + i + " == s: " + str);
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onFCWFaultStateChanged(int i) {
        int iM = m(i, (byte) 6);
        BydLogHelper.logD("onFCWFaultStateChanged " + i + ", ret =" + iM);
        if (iM == -1) {
            return;
        }
        EventBus.getDefault().post(new b.b.a.c.a(210081, iM));
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onFatigueDetectionAidStateChanged(int i) {
        if (this.y == i) {
            return;
        }
        this.y = i;
        super.onFatigueDetectionAidStateChanged(i);
        BydLogHelper.logD("onFatigueDetectionAidStateChanged " + i);
        EventBus.getDefault().post(new b.b.a.c.a(210035, i));
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onFaultHandlerStateChanged(int i) {
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onForwardCollisionWarnFault(byte b2) {
        BydLogHelper.logD("onForwardCollisionWarnFault " + ((int) b2));
        if (k(15, b2 == 1)) {
            EventBus.getDefault().post(new b.b.a.c.a(210097, (int) b2));
        }
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onFrontCarStartStateChange(int i) {
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onFrontCrossAlarkFault(byte b2) {
        BydLogHelper.logD("onFrontCrossAlarkFault " + ((int) b2));
        if (k(16, b2 == 1)) {
            EventBus.getDefault().post(new b.b.a.c.a(210087, (int) b2));
        }
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onFrontCrossBrakeFault(byte b2) {
        BydLogHelper.logD("onFrontCrossBrakeFault " + ((int) b2));
        if (k(17, b2 == 1)) {
            EventBus.getDefault().post(new b.b.a.c.a(210088, (int) b2));
        }
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onFrontTrafficBrakeAlertStateChanged(int i) {
        if (this.u == i) {
            return;
        }
        this.u = i;
        BydLogHelper.logD("onFrontTrafficBrakeAlertStateChanged " + i);
        if (b.b.g.r.e.c(210031)) {
            BydLogHelper.logD("hasMessages FRONT_TRAFFIC_BRAKE_ALERT_STATE_CHANGE return!");
        } else {
            o(210031);
            EventBus.getDefault().post(new b.b.a.c.a(210031, i));
        }
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onFrontTrafficCrossAlertStateChanged(int i) {
        if (this.t == i) {
            return;
        }
        this.t = i;
        super.onFrontTrafficCrossAlertStateChanged(i);
        BydLogHelper.logD("onFrontTrafficCrossAlertStateChanged " + i);
        if (b.b.g.r.e.c(210030)) {
            BydLogHelper.logD("hasMessages FRONT_TRAFFIC_CROSS_ALERT_STATE_CHANGE return!");
        } else {
            o(210030);
            EventBus.getDefault().post(new b.b.a.c.a(210030, i));
        }
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onGazePerceptionStateChanged(int i) {
        if (this.F == i) {
            return;
        }
        this.F = i;
        super.onGazePerceptionStateChanged(i);
        BydLogHelper.logD("onGazePerceptionStateChanged " + i);
        EventBus.getDefault().post(new b.b.a.c.a(210042, i));
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onGestureRecognitionStateChanged(int i) {
        if (this.H == i) {
            return;
        }
        this.H = i;
        super.onGestureRecognitionStateChanged(i);
        BydLogHelper.logD("onGestureRecognitionStateChanged " + i);
        EventBus.getDefault().post(new b.b.a.c.a(210044, i));
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onHighNaviFault(byte b2) {
        BydLogHelper.logD("onHighNaviFault " + ((int) b2));
        if (k(64, b2 == 1)) {
            EventBus.getDefault().post(new b.b.a.c.a(210091, (int) b2));
        }
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onHighSpeedAINavigationStateChanged(int i) {
        if (this.f846c == i) {
            return;
        }
        this.f846c = i;
        super.onHighSpeedAINavigationStateChanged(i);
        BydLogHelper.logD("onHighSpeedAINavigationStateChanged " + i);
        if (n(64)) {
            return;
        }
        if (b.b.g.r.e.c(210009)) {
            BydLogHelper.logD("hasMessages HIGH_SPEED_AI_NAVIGATION_STATE_CHANGE return!");
        } else {
            o(210009);
            EventBus.getDefault().post(new b.b.a.c.a(210009, i));
        }
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onHighSpeedOpenWindowStateChanged(int i) {
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onIOPStateChanged(int i) {
        if (this.g == i) {
            return;
        }
        this.g = i;
        super.onIOPStateChanged(i);
        BydLogHelper.logD("onIopStateChanged " + i);
        if (n(69)) {
            return;
        }
        if (b.b.g.r.e.c(210110)) {
            BydLogHelper.logD("hasMessages IOP_STATE return!");
        } else {
            o(210110);
            EventBus.getDefault().post(new b.b.a.c.a(210110, i));
        }
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onIntelligentLimitFaultCall(byte b2) {
        if (k(22, b2 == 1)) {
            BydLogHelper.logD("onIntelligentLimitFaultCall " + ((int) b2));
            EventBus.getDefault().post(new b.b.a.c.a(210101, (int) b2));
        }
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onIntelligentLimitFaultController(byte b2) {
        if (k(24, b2 == 1)) {
            BydLogHelper.logD("IntelligentLimitFaultController " + ((int) b2));
            EventBus.getDefault().post(new b.b.a.c.a(210094, (int) b2));
        }
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onIntelligentSpeedLimitAlert2B4StateChanged(int i) {
        if (this.T == i) {
            return;
        }
        this.T = i;
        super.onIntelligentSpeedLimitAlert2B4StateChanged(i);
        BydLogHelper.logD("onIntelligentSpeedLimitAlert2B4StateChanged " + i);
        if (b.b.g.r.e.c(210056)) {
            BydLogHelper.logD("hasMessages INTELLIGENT_SPEED_LIMIT_ALERT_2B4_STATE_CHANGE return!");
        } else {
            EventBus.getDefault().post(new b.b.a.c.a(210056, i));
        }
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onIntelligentSpeedLimitAlert2D5StateChanged(int i) {
        if (this.S == i) {
            return;
        }
        this.S = i;
        super.onIntelligentSpeedLimitAlert2D5StateChanged(i);
        BydLogHelper.logD("onIntelligentSpeedLimitAlert2D5StateChanged " + i);
        if (b.b.g.r.e.c(210056)) {
            BydLogHelper.logD("hasMessages INTELLIGENT_SPEED_LIMIT_ALERT_2D5_STATE_CHANGE return!");
        } else {
            EventBus.getDefault().post(new b.b.a.c.a(210056, i));
        }
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onIntelligentSpeedLimitControl2D5StateChanged(int i) {
        if (this.U == i) {
            return;
        }
        this.U = i;
        super.onIntelligentSpeedLimitControl2D5StateChanged(i);
        BydLogHelper.logD("onIntelligentSpeedLimitControl2D5StateChanged " + i);
        if (b.b.g.r.e.c(210058)) {
            BydLogHelper.logD("hasMessages INTELLIGENT_SPEED_LIMIT_CONTROL_2D5_STATE_CHANGE return!");
        } else {
            o(210058);
            EventBus.getDefault().post(new b.b.a.c.a(210058, i));
        }
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onIntelligentSpeedLimitControl32FStateChanged(int i) {
        if (this.V == i) {
            return;
        }
        this.V = i;
        super.onIntelligentSpeedLimitControl32FStateChanged(i);
        BydLogHelper.logD("onIntelligentSpeedLimitControl32FStateChanged " + i);
        if (b.b.g.r.e.c(210058)) {
            BydLogHelper.logD("hasMessages INTELLIGENT_SPEED_LIMIT_CONTROL_32F_STATE_CHANGE return!");
        } else {
            EventBus.getDefault().post(new b.b.a.c.a(210058, i));
        }
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onInteractiveLaneChangeAssistanceStateChanged(int i) {
        if (this.f849f == i) {
            return;
        }
        this.f849f = i;
        super.onInteractiveLaneChangeAssistanceStateChanged(i);
        BydLogHelper.logD("onInteractiveLaneChangeAssistanceStateChanged " + i);
        if (n(67)) {
            return;
        }
        if (b.b.g.r.e.c(210012)) {
            BydLogHelper.logD("hasMessages INTERACTIVE_LANE_CHANGE_ASSISTANCE_STATE_CHANGE return!");
        } else {
            o(210012);
            EventBus.getDefault().post(new b.b.a.c.a(210012, i));
        }
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onInteractiveLaneChangeFault(byte b2) {
        if (k(67, b2 == 1)) {
            BydLogHelper.logD("onInteractiveLaneChangeFault " + ((int) b2));
            EventBus.getDefault().post(new b.b.a.c.a(210103, (int) b2));
        }
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onLDWFaultStateChanged(int i) {
        int iM = m(i, (byte) 1);
        BydLogHelper.logD("onLDWFaultStateChanged " + i + ", ret =" + iM);
        if (iM == -1) {
            return;
        }
        EventBus.getDefault().post(new b.b.a.c.a(R.id.tv_lane_assist, iM));
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onLDWSensitivityChange(int i) {
        if (this.t0 == i) {
            return;
        }
        this.t0 = i;
        super.onLDWSensitivityChange(i);
        EventBus.getDefault().post(new b.b.a.c.a(R.string.adas_ldw_sensitive_title, i));
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onLKSFaultStateStateChanged(int i) {
        int iM = m(i, (byte) 0);
        BydLogHelper.logD("onLKSFaultStateStateChanged " + i + ", ret =" + iM);
        if (iM == -1) {
            return;
        }
        EventBus.getDefault().post(new b.b.a.c.a(R.id.tv_lane_assist, iM));
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onLaneAssistTypeChanged(int i) {
        if (this.O == i) {
            return;
        }
        this.O = i;
        super.onLaneAssistTypeChanged(i);
        BydLogHelper.logD("onLaneAssistTypeChanged " + i);
        if (b.b.g.r.e.c(210047)) {
            BydLogHelper.logD("hasMessages LANE_ASSIST_TYPE_CHANGE return!");
        } else {
            o(210047);
            EventBus.getDefault().post(new b.b.a.c.a(210047, i));
        }
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onLaneCenteringControlFault(byte b2) {
        if (k(68, b2 == 1)) {
            EventBus.getDefault().post(new b.b.a.c.a(210108, (int) b2));
        }
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onLaneDeparturePreventionFault(byte b2) {
        int iM = m(b2, (byte) 2);
        BydLogHelper.logD("LaneDeparturePreventionFault " + ((int) b2) + ", ret =" + iM);
        if (iM == -1) {
            return;
        }
        EventBus.getDefault().post(new b.b.a.c.a(210095, iM));
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onLaneDepartureWarnFault(byte b2) {
        int iM = m(b2, (byte) 3);
        BydLogHelper.logD("LaneDepartureWarnFault " + ((int) b2) + ", ret =" + iM);
        if (iM == -1) {
            return;
        }
        EventBus.getDefault().post(new b.b.a.c.a(210096, iM));
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    @Deprecated
    public void onLaneKeepFault(byte b2) {
        BydLogHelper.logD("LaneKeepFault " + ((int) b2));
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onLeftBehindReminderStateChanged(int i) {
        if (this.I == i) {
            return;
        }
        this.I = i;
        super.onLeftBehindReminderStateChanged(i);
        BydLogHelper.logD("onLeftBehindReminderStateChanged " + i);
        EventBus.getDefault().post(new b.b.a.c.a(210045, i));
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onLimitSpeedChangeFault(byte b2) {
        if (k(21, b2 == 1)) {
            BydLogHelper.logD("onLimitSpeedChangeFault " + ((int) b2));
            EventBus.getDefault().post(new b.b.a.c.a(210102, (int) b2));
        }
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onMOISFault(byte b2) {
        if (k(27, b2 == 1)) {
            BydLogHelper.logD("onMOISFault " + ((int) b2));
            EventBus.getDefault().post(new b.b.a.c.a(210105, (int) b2));
        }
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onMOISSwitchChange(int i) {
        if (this.r0 == i) {
            return;
        }
        this.r0 = i;
        super.onMOISSwitchChange(i);
        if (b.b.g.r.e.c(210077)) {
            BydLogHelper.logD("hasMessages MOVE_OFF_SYSTEM return!");
        } else {
            o(210077);
            EventBus.getDefault().post(new b.b.a.c.a(210077, i));
        }
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onMOISSystemChange(int i) {
        if (this.s0 == i) {
            return;
        }
        this.s0 = i;
        super.onMOISSystemChange(i);
        EventBus.getDefault().post(new b.b.a.c.a(210052, i));
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onMistakeGasStateChanged(int i) {
        if (this.l == i) {
            return;
        }
        this.l = i;
        super.onMistakeGasStateChanged(i);
        BydLogHelper.logD("onMistakeGasStateChanged " + i);
        if (n(7)) {
            return;
        }
        if (b.b.g.r.e.c(210018)) {
            BydLogHelper.logD("hasMessages MISTAKE_GAS_STATE_CHANGE return!");
        } else {
            o(210018);
            EventBus.getDefault().post(new b.b.a.c.a(210018, i));
        }
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onNavigationPilotFault(byte b2) {
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onNrbanNaviFault(byte b2) {
        BydLogHelper.logD("onNrbanNaviFault " + ((int) b2));
        if (k(65, b2 == 1)) {
            EventBus.getDefault().post(new b.b.a.c.a(210090, (int) b2));
        }
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onOpenDoorAlertStateChanged(int i) {
        if (this.o == i) {
            return;
        }
        this.o = i;
        super.onOpenDoorAlertStateChanged(i);
        BydLogHelper.logD("onOpenDoorAlertStateChanged " + i);
        if (n(2)) {
            return;
        }
        if (b.b.g.r.e.c(210024)) {
            BydLogHelper.logD("hasMessages OPEN_DOOR_ALERT_STATE_CHANGE return!");
        } else {
            o(210024);
            EventBus.getDefault().post(new b.b.a.c.a(210024, i));
        }
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onPredictCrashAlert138ModeChanged(int i) {
        if (this.i0 == i) {
            return;
        }
        this.i0 = i;
        super.onPredictCrashAlert138ModeChanged(i);
        BydLogHelper.logD("onPredictCrashAlert138ModeChanged " + i);
        o(210069);
        EventBus.getDefault().post(new b.b.a.c.a(210069, i));
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onPredictCrashAlert32DModeChanged(int i) {
        if (this.j0 == i) {
            return;
        }
        this.j0 = i;
        super.onPredictCrashAlert32DModeChanged(i);
        boolean zJ = Router.b().e().j(112);
        o(210069);
        BydLogHelper.logD("onPredictCrashAlert32DModeChanged " + i + ", online138 =" + zJ);
        if (zJ) {
            return;
        }
        EventBus.getDefault().post(new b.b.a.c.a(210069, i));
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onPredictCrashAlertStateChange(int i) {
        if (this.N == i) {
            return;
        }
        this.N = i;
        super.onPredictCrashAlertStateChange(i);
        BydLogHelper.logD("onPredictCrashAlertStateChange " + i);
        if (b.b.g.r.e.c(210048)) {
            BydLogHelper.logD("hasMessages PREDICT_CRASH_ALERT_STATE_CHANGE return!");
        } else {
            o(210048);
            EventBus.getDefault().post(new b.b.a.c.a(210048, i));
        }
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onRainOpenFogLightStateChanged(int i) {
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onRearCollisionWarnFault(byte b2) {
        BydLogHelper.logD("onRearCollisionWarnFault " + ((int) b2));
        if (k(14, b2 == 1)) {
            EventBus.getDefault().post(new b.b.a.c.a(210086, (int) b2));
        }
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onRearCrashAlertStateChanged(int i) {
        if (this.p == i) {
            return;
        }
        this.p = i;
        super.onRearCrashAlertStateChanged(i);
        BydLogHelper.logD("onRearCrashAlertStateChanged " + i);
        if (b.b.g.r.e.c(210025)) {
            BydLogHelper.logD("hasMessages REAR_CRASH_ALERT_STATE_CHANGE return!");
        } else {
            o(210025);
            EventBus.getDefault().post(new b.b.a.c.a(210025, i));
        }
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onRearCrossAlartFault(byte b2) {
        BydLogHelper.logD("onRearCrossAlartFault " + ((int) b2));
        if (k(18, b2 == 1)) {
            EventBus.getDefault().post(new b.b.a.c.a(210084, (int) b2));
        }
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onRearCrossBrakeFault(byte b2) {
        BydLogHelper.logD("onRearCrossBrakeFault " + ((int) b2));
        if (k(19, b2 == 1)) {
            EventBus.getDefault().post(new b.b.a.c.a(210085, (int) b2));
        }
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onRearTrafficBrakeAlertStateChanged(int i) {
        if (this.r == i) {
            return;
        }
        this.r = i;
        super.onRearTrafficBrakeAlertStateChanged(i);
        BydLogHelper.logD("onRearTrafficBrakeAlertStateChanged " + i);
        o(210027);
        if (b.b.g.r.e.c(210027)) {
            BydLogHelper.logD("hasMessages REAR_TRAFFIC_BRAKE_ALERT_STATE_CHANGE return!");
        } else {
            EventBus.getDefault().post(new b.b.a.c.a(210027, i));
        }
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onRearTrafficCrossAlertStateChanged(int i) {
        if (this.q == i) {
            return;
        }
        this.q = i;
        super.onRearTrafficCrossAlertStateChanged(i);
        BydLogHelper.logD("onRearTrafficCrossAlertStateChanged " + i);
        if (b.b.g.r.e.c(210026)) {
            BydLogHelper.logD("hasMessages REAR_TRAFFIC_CROSS_ALERT_STATE_CHANGE return!");
        } else {
            o(210026);
            EventBus.getDefault().post(new b.b.a.c.a(210026, i));
        }
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onRecommendRestSpotStateChanged(int i) {
        if (this.A == i) {
            return;
        }
        this.A = i;
        super.onRecommendRestSpotStateChanged(i);
        BydLogHelper.logD("onRecommendRestSpotStateChanged " + i);
        EventBus.getDefault().post(new b.b.a.c.a(210037, i));
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onRelieveFatigueStateChanged(int i) {
        if (this.z == i) {
            return;
        }
        this.z = i;
        super.onRelieveFatigueStateChanged(i);
        BydLogHelper.logD("onRelieveFatigueStateChanged " + i);
        EventBus.getDefault().post(new b.b.a.c.a(210036, i));
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onRemoteControlDriveCloseTops(int i) {
        if (this.P == i) {
            return;
        }
        this.P = i;
        super.onRemoteControlDriveCloseTops(i);
        BydLogHelper.logD("onRemoteControlDriveCloseTops " + i);
        EventBus.getDefault().post(new b.b.a.c.a(210053, i));
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onRequestHUNPClose(int i) {
        if (this.J == i) {
            return;
        }
        this.J = i;
        super.onRequestHUNPClose(i);
        BydLogHelper.logD("onRequestHUNPClose " + i);
        EventBus.getDefault().post(new b.b.a.c.a(210046, i));
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onSAOCStateChanged(int i) {
        if (this.a0 == i) {
            return;
        }
        this.a0 = i;
        BydLogHelper.logD("onSAOCStateChanged " + i);
        if (n(33)) {
            return;
        }
        if (b.b.g.r.e.c(210112)) {
            BydLogHelper.logD("hasMessages SAOC_STATE return!");
        } else {
            o(210112);
            EventBus.getDefault().post(new b.b.a.c.a(210112, i));
        }
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onSLIFStateChanged(int i) {
        if (this.Z == i) {
            return;
        }
        this.Z = i;
        BydLogHelper.logD("onSLIFStateChanged " + i);
        if (n(32)) {
            return;
        }
        if (b.b.g.r.e.c(210111)) {
            BydLogHelper.logD("hasMessages SLIF_STATE return!");
        } else {
            o(210111);
            EventBus.getDefault().post(new b.b.a.c.a(210111, i));
        }
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onSpeedLimitRange2B4Changed(int i) {
        if (this.R == i) {
            return;
        }
        this.R = i;
        super.onSpeedLimitRange2B4Changed(i);
        BydLogHelper.logD("onSpeedLimitRange2B4Changed " + i);
        if (b.b.g.r.e.c(210055)) {
            BydLogHelper.logD("hasMessages SPEED_LIMIT_RANGE_2B4_CHANGE return!");
        } else {
            o(210054);
            EventBus.getDefault().post(new b.b.a.c.a(210055, i));
        }
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onSpeedLimitRange2D5Changed(int i) {
        if (this.Q == i) {
            return;
        }
        this.Q = i;
        super.onSpeedLimitRange2D5Changed(i);
        BydLogHelper.logD("onSpeedLimitRange2D5Changed " + i);
        if (b.b.g.r.e.c(210054)) {
            BydLogHelper.logD("hasMessages SPEED_LIMIT_RANGE_2D5_CHANGE return!");
        } else {
            EventBus.getDefault().post(new b.b.a.c.a(210054, i));
        }
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onSteepDescentSlowDownStateChanged(int i) {
        if (this.x == i) {
            return;
        }
        this.x = i;
        BydLogHelper.logD("onSteepDescentSlowDownStateChanged " + i);
        if (n(6)) {
            return;
        }
        if (b.b.g.r.e.c(210034)) {
            BydLogHelper.logD("hasMessages STEEP_DESCENT_SLOW_DOWN_STATE_CHANGE return!");
        } else {
            o(210034);
            EventBus.getDefault().post(new b.b.a.c.a(210034, i));
        }
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onTSRLitSpdChgSwitchStateChanged(int i) {
        if (this.b0 == i) {
            return;
        }
        this.b0 = i;
        if (n(21)) {
            return;
        }
        if (b.b.g.r.e.c(R.id.ll_speed_control_alarm_sensitivity_container)) {
            BydLogHelper.logD("hasMessages ll_speed_control_alarm_sensitivity_container return!");
        } else {
            o(R.id.ll_speed_control_alarm_sensitivity_container);
            EventBus.getDefault().post(new b.b.a.c.a(R.id.ll_speed_control_alarm_sensitivity_container, i));
        }
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onTemperatureWhileFatigueChanged(int i) {
        if (this.B == i) {
            return;
        }
        this.B = i;
        super.onTemperatureWhileFatigueChanged(i);
        BydLogHelper.logD("onTemperatureWhileFatigueChanged " + i);
        EventBus.getDefault().post(new b.b.a.c.a(210038, i));
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onTrafficAttentionFault(byte b2) {
        if (k(3, b2 == 1)) {
            BydLogHelper.logD("onTrafficAttentionFault " + ((int) b2));
            EventBus.getDefault().post(new b.b.a.c.a(210099, (int) b2));
        }
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onTrafficLightRecognize2B4StateChanged(int i) {
        if (this.f0 == i) {
            return;
        }
        this.f0 = i;
        super.onTrafficLightRecognize2B4StateChanged(i);
        BydLogHelper.logD("onTrafficLightRecognize2B4StateChanged " + i);
        if (b.b.g.r.e.c(210065)) {
            BydLogHelper.logD("hasMessages TRAFFIC_LIGHT_RECOGNIZE_2B4_STATE_CHANGE return!");
        } else {
            EventBus.getDefault().post(new b.b.a.c.a(210065, i, 3));
        }
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onTrafficLightRecognize2D5StateChanged(int i) {
        if (this.e0 == i) {
            return;
        }
        this.e0 = i;
        if (n(3)) {
            return;
        }
        BydLogHelper.logD("onTrafficLightRecognize2D5StateChanged " + i);
        if (b.b.g.r.e.c(210065)) {
            BydLogHelper.logD("hasMessages TRAFFIC_LIGHT_RECOGNIZE_2D5_STATE_CHANGE return!");
        } else {
            EventBus.getDefault().post(new b.b.a.c.a(210065, i, 3));
        }
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onTrafficSignRecognize2B4StateChanged(int i) {
        if (this.X == i) {
            return;
        }
        this.X = (byte) i;
        BydLogHelper.logD("onTrafficSignRecognize2B4StateChanged " + i);
        if (n(20)) {
            return;
        }
        if (b.b.g.r.e.c(210015)) {
            BydLogHelper.logD("hasMessages TRAFFIC_SIGN_RECOGNIZE_2B4_STATE_CHANGE return!");
        } else {
            EventBus.getDefault().post(new b.b.a.c.a(210015, i));
        }
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onTrafficSignRecognize2D5StateChanged(int i) {
        if (this.W == i) {
            return;
        }
        this.W = i;
        BydLogHelper.logD("onTrafficSignRecognize2D5StateChanged " + i);
        if (n(20)) {
            return;
        }
        if (b.b.g.r.e.c(210015)) {
            BydLogHelper.logD("hasMessages TRAFFIC_SIGN_RECOGNIZE_2D5_STATE_CHANGE return!");
        } else {
            EventBus.getDefault().post(new b.b.a.c.a(210015, i));
        }
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onTrafficSignRecognize316StateChanged(int i) {
        if (this.Y == i) {
            return;
        }
        this.Y = i;
        BydLogHelper.logD("onTrafficSignRecognize316StateChanged " + i);
        if (n(20)) {
            return;
        }
        if (b.b.g.r.e.c(210015)) {
            BydLogHelper.logD("hasMessages TRAFFIC_SIGN_RECOGNIZE_316_STATE_CHANGE return!");
        } else {
            EventBus.getDefault().post(new b.b.a.c.a(210015, i));
        }
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onTsrFault(byte b2) {
        if (k(20, b2 == 1)) {
            BydLogHelper.logD("onTsrFault " + ((int) b2));
            EventBus.getDefault().post(new b.b.a.c.a(210100, (int) b2));
        }
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onUrbanAIAssistanceStateChanged(int i) {
        if (this.f847d == i) {
            return;
        }
        this.f847d = (byte) i;
        super.onUrbanAIAssistanceStateChanged(i);
        BydLogHelper.logD("onUrbanAIAssistanceStateChanged " + i);
        if (n(65)) {
            return;
        }
        if (b.b.g.r.e.c(210010)) {
            BydLogHelper.logD("hasMessages URBAN_AI_ASSISTANCE_STATE_CHANGE return!");
        } else {
            o(210010);
            EventBus.getDefault().post(new b.b.a.c.a(210010, i));
        }
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onVRUFaultStateStateChanged(int i) {
        int iM = m(i, (byte) 7);
        BydLogHelper.logD("onVRUFaultStateStateChanged " + i + ", ret =" + iM);
        if (iM == -1) {
            return;
        }
        EventBus.getDefault().post(new b.b.a.c.a(210082, iM));
    }

    @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
    public void onVerifyModeChanged(int i) {
        if (this.K == i) {
            return;
        }
        this.K = i;
        super.onVerifyModeChanged(i);
        BydLogHelper.logD("onVerifyModeChanged " + i);
        if (b.b.g.r.e.c(210051)) {
            BydLogHelper.logD("hasMessages VERIFY_MODE_CHANGE return!");
        } else {
            o(210051);
            EventBus.getDefault().post(new b.b.a.c.a(210051, i));
        }
    }

    public final int p(int i, boolean z, int i2) {
        int i3 = z ? (1 << i) | i2 : (~(1 << i)) & i2;
        BydLogHelper.logD(AbsBYDAutoDiPilotListener.TAG, "mask =" + i + ", faultVal =" + Integer.toHexString(i3));
        return i3;
    }
}
