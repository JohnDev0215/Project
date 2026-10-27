package com.byd.base.hilt.impl;

import android.content.Context;
import android.hardware.bydauto.dipilot.BYDAutoDiPilotDevice;
import android.hardware.bydauto.gearbox.BYDAutoGearboxDevice;
import android.hardware.bydauto.uistyleconfig.BYDAutoStudy;
import android.hardware.bydauto.vehiclehealth.BYDAutoVehicleHealthDevice;
import android.os.SystemProperties;
import android.text.TextUtils;
import androidx.annotation.MainThread;
import androidx.annotation.WorkerThread;
import b.b.a.f.h;
import b.b.a.h.e;
import com.byd.base.hilt.inter.IStudyConfig;
import com.bydux.api.common.tools.log.BydLogHelper;
import okio.SegmentPool;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: loaded from: classes.dex */
public class StudyConfigImpl extends TsCommonCarImpl implements IStudyConfig {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public BYDAutoStudy f2438c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f2439d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f2440e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public byte f2441f;
    public volatile long g;
    public volatile int h;
    public byte i;
    public BodyDeviceImpl j;

    public static class InitCompleteMsg {
    }

    public StudyConfigImpl(Context context, BodyDeviceImpl bodyDeviceImpl) {
        super(context);
        this.f2439d = 1;
        this.f2440e = 5;
        this.f2438c = BYDAutoStudy.getInstance(context);
        this.j = bodyDeviceImpl;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: B, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void C() {
        BydLogHelper.logW(this.f2442a, "checkDataComplete end! safe capacity = " + Long.toBinaryString(this.g) + ", driver and park capacity = " + Integer.toBinaryString(this.h));
        this.f2439d = 3;
        EventBus.getDefault().post(new InitCompleteMsg());
    }

    public final void F(int i) {
        this.f2441f = (byte) ((1 << i) | this.f2441f);
    }

    @Override // com.byd.base.hilt.inter.IStudyConfig
    public boolean a() {
        return this.f2439d == 3;
    }

    @Override // com.byd.base.hilt.inter.IStudyConfig
    @MainThread
    public void b() {
        BydLogHelper.logI(this.f2442a, "initAdasConfig, mInitState = " + this.f2439d);
        if (this.f2439d != 1) {
            return;
        }
        BydLogHelper.logW(this.f2442a, "initAdasConfig, init now!");
        this.f2439d = 2;
        h.a().j(new Runnable() { // from class: b.b.a.b.a.e
            @Override // java.lang.Runnable
            public final void run() {
                this.f474a.u();
            }
        });
        h.a().j(new Runnable() { // from class: b.b.a.b.a.d
            @Override // java.lang.Runnable
            public final void run() {
                this.f473a.t();
            }
        });
        h.a().j(new Runnable() { // from class: b.b.a.b.a.b
            @Override // java.lang.Runnable
            public final void run() {
                this.f471a.r();
            }
        });
        h.a().j(new Runnable() { // from class: b.b.a.b.a.a
            @Override // java.lang.Runnable
            public final void run() {
                this.f470a.v();
            }
        });
        h.a().j(new Runnable() { // from class: b.b.a.b.a.c
            @Override // java.lang.Runnable
            public final void run() {
                this.f472a.s();
            }
        });
    }

    @Override // com.byd.base.hilt.inter.IStudyConfig
    public boolean c(int i) {
        return i >= 64 ? ((this.h >>> (i - 64)) & 1) == 1 : ((this.g >>> i) & 1) == 1;
    }

    @Override // com.byd.base.hilt.inter.IStudyConfig
    public boolean d(int i) {
        return i != 1 ? i == 4 && ((this.f2441f >> 1) & 1) == 1 : (this.f2441f & 1) == 1;
    }

    @Override // com.byd.base.hilt.inter.IStudyConfig
    public byte e() {
        return this.i;
    }

    @Override // com.byd.base.hilt.inter.IStudyConfig
    public BYDAutoStudy f() {
        return this.f2438c;
    }

    @Override // com.byd.base.hilt.inter.IStudyConfig
    public int g() {
        int i = (this.h & 65535) == 0 ? 2 : 3;
        return m(0, 30) == 0 ? i - 1 : i;
    }

    @Override // com.byd.base.hilt.inter.IStudyConfig
    public long h() {
        return this.g;
    }

    @Override // com.byd.base.hilt.inter.IStudyConfig
    public int i(int i) {
        boolean z = m(0, 30) > 0;
        boolean z2 = (this.h & 65535) > 0;
        if (z && z2) {
            return i;
        }
        if (!z && !z2) {
            return 2;
        }
        if (z) {
            return i == 0 ? 1 : 2;
        }
        if (i == 0) {
            return i;
        }
        return 2;
    }

    @Override // com.byd.base.hilt.impl.TsCommonCarImpl
    public void j() {
        super.j();
        this.j = null;
        this.f2438c = null;
        this.f2441f = (byte) 0;
    }

    public final void k() {
        this.f2440e--;
        BydLogHelper.logD("checkDataComplete, mInitStep = " + this.f2440e);
        if (this.f2440e <= 0) {
            h.a().d(new Runnable() { // from class: b.b.a.b.a.f
                @Override // java.lang.Runnable
                public final void run() {
                    this.f475a.C();
                }
            });
        }
    }

    public boolean l(int i) {
        BodyDeviceImpl bodyDeviceImpl;
        if (this.f2438c == null || (bodyDeviceImpl = this.j) == null) {
            return false;
        }
        int iG = bodyDeviceImpl.g();
        if (iG == 68 || 73 == iG || 82 == iG || 83 == iG || 84 == iG || 110 == iG || 134 == iG) {
            return true;
        }
        return i != 3 && 1 == this.f2438c.getBrakePedalModeSupport();
    }

    public long m(int i, int i2) {
        return ((long) ((1 << Math.max(0, i2 - i)) - 1)) & (this.g >> i);
    }

    public final boolean n() {
        BodyDeviceImpl bodyDeviceImpl;
        if (this.f2438c == null || (bodyDeviceImpl = this.j) == null) {
            BydLogHelper.logW(this.f2442a, "getSupportEsc false! studyConfig = " + this.f2438c + " bodyImpl = " + this.j);
            return false;
        }
        if (!bodyDeviceImpl.j(1)) {
            BydLogHelper.logW(this.f2442a, "getSupportEsc false! 040 not found!");
            return false;
        }
        if (!this.j.j(19)) {
            BydLogHelper.logW(this.f2442a, "getSupportEsc false! 321 not found!");
            return false;
        }
        int iG = this.j.g();
        if (53 == iG || 54 == iG || 55 == iG) {
            BydLogHelper.logW(this.f2442a, "getSupportEsc tang true!");
            return true;
        }
        if (69 == iG && p(18)) {
            BydLogHelper.logW(this.f2442a, "getSupportEsc 045 true!");
            return true;
        }
        int supportESP = this.f2438c.getSupportESP();
        BydLogHelper.logW(this.f2442a, "getSupportEsc  getSupportESP return " + supportESP);
        return supportESP == 2;
    }

    public final boolean o(int i) {
        if ((i & 1) == 0) {
            BydLogHelper.logD("getSupportLdw notHasLdw, false");
            return false;
        }
        if (this.f2438c.getDMSAssembleStatus() == 1) {
            BydLogHelper.logD("getSupportLdw indirectDms, true");
            return true;
        }
        boolean zA = e.a();
        BydLogHelper.logD("getSupportLdw hasDms = " + zA);
        return zA;
    }

    public boolean p(int i) {
        BodyDeviceImpl bodyDeviceImpl = this.j;
        return bodyDeviceImpl != null && bodyDeviceImpl.j(i);
    }

    public final boolean q() {
        BYDAutoVehicleHealthDevice bYDAutoVehicleHealthDevice = BYDAutoVehicleHealthDevice.getInstance(this.f2443b);
        if (bYDAutoVehicleHealthDevice == null) {
            return false;
        }
        int ePBConfig = bYDAutoVehicleHealthDevice.getEPBConfig();
        int ePBType = bYDAutoVehicleHealthDevice.getEPBType();
        BydLogHelper.logD(this.f2442a, "epbConfig " + ePBConfig + ", eptType =" + ePBType);
        return ePBConfig == 1 && ePBType == 0;
    }

    public final void r() {
        BydLogHelper.logW(this.f2442a, "initCrashAlert start");
        long j = 1 == this.f2438c.getSupportAiEmergencyBrake() ? 8192L : 0L;
        if (1 == this.f2438c.getSupportRearCollisionWarning()) {
            j |= 16384;
        }
        if (1 == this.f2438c.getHasPredictCrashAlertMode() && this.f2438c.getSupportPredictCrashAlertMode() == 0) {
            j |= 32768;
        }
        if (this.f2438c.getSupportFrontCrossingTrafficAlert() == 0) {
            j |= SegmentPool.MAX_SIZE;
        }
        if (this.f2438c.getSupportFrontCrossingTrafficBrake() == 0) {
            j |= 131072;
        }
        if (1 == this.f2438c.getSupportRearTrafficCrossingAlert()) {
            j |= 262144;
        }
        if (1 == this.f2438c.getSupportRearCrossBrake()) {
            j |= 524288;
        }
        synchronized (this) {
            this.g |= j;
            BydLogHelper.logW(this.f2442a, "initCrashAlert init result = " + Long.toBinaryString(j));
            k();
        }
    }

    @WorkerThread
    public final void s() {
        BydLogHelper.logW(this.f2442a, "initDriverPark start");
        int i = this.f2438c.getSupportHighwayNavigation() == 0 ? 1 : 0;
        if (1 == this.f2438c.getSupportUrbanNavigation()) {
            i |= 2;
        }
        if (1 == this.f2438c.getSupportEndToEndNavigation()) {
            i |= 4;
        }
        if (this.f2438c.getSupportLaneChangeAssist() == 0) {
            i |= 8;
        }
        if (2 == this.f2438c.getSupportIop()) {
            i |= 32;
        }
        if (2 == this.f2438c.getSupportACC()) {
            i |= 64;
        }
        if (1 == this.f2438c.getSupportIntelligentNavigation() && 1 == this.f2438c.getSupportIntelligentNavigationBy432()) {
            i |= 16;
        }
        if (w()) {
            i |= 65536;
        }
        if (x()) {
            i |= 131072;
        }
        synchronized (this) {
            this.h |= i;
            BydLogHelper.logW(this.f2442a, "initDriverPark init result = " + Integer.toBinaryString(i));
            k();
        }
    }

    public final void t() {
        BydLogHelper.logW(this.f2442a, "initLaneAssist start");
        long j = 2 == this.f2438c.getSupportAVH() ? 512L : 0L;
        if (p(43) && q()) {
            j |= 1024;
        }
        this.i = (byte) 0;
        if (1 == this.f2438c.getSupportLaneDepartureWarning()) {
            this.i = (byte) (this.i | 1);
        }
        if (this.f2438c.getSupportLaneDepartureStop() == 0) {
            this.i = (byte) (this.i | 4);
        }
        byte b2 = this.i;
        if (b2 > 0) {
            j |= 2048;
            if (o(b2)) {
                j |= 536870912;
            }
        }
        if (1 == this.f2438c.getSupportAlarmMode()) {
            j |= 4096;
        }
        if (2 == this.f2438c.getSupportSaoc()) {
            j |= 8589934592L;
        }
        synchronized (this) {
            this.g |= j;
            BydLogHelper.logW(this.f2442a, "initLaneAssist init result = " + Long.toBinaryString(j));
            k();
        }
    }

    @WorkerThread
    public final void u() {
        BydLogHelper.logW(this.f2442a, "initSafeList start");
        long j = this.f2438c.getSupportEmergencyLaneKeepAssist() == 0 ? 1L : 0L;
        boolean z = 1 == this.f2438c.getSupportBlindSpotAssist();
        if (z || p(11)) {
            j |= 2;
            if (z) {
                F(0);
            }
        }
        if (1 == this.f2438c.getSupportDoorOpenWarning()) {
            j |= 4;
        }
        if (this.f2438c.getSupportTrafficLightAttention() == 0) {
            j |= 8;
        }
        boolean z2 = 1 == this.f2438c.getSupportAiMatrixLight();
        if (z2 || 1 == this.f2438c.getSupportAiDistanceLight()) {
            j |= 16;
            if (z2) {
                F(1);
            }
        }
        if (n()) {
            j |= 32;
        }
        if (l((byte) BYDAutoDiPilotDevice.getInstance(this.f2443b).getSteepDescentSlowDownState())) {
            j |= 64;
        }
        if (this.f2438c.getSupportMistakeGas() == 0) {
            j |= 128;
        }
        synchronized (this) {
            this.g |= j;
            BydLogHelper.logW(this.f2442a, "initSafeList init result = " + Long.toBinaryString(j));
            k();
        }
    }

    public final void v() {
        long j;
        BydLogHelper.logW(this.f2442a, "initTrafficSign start");
        if (1 == this.f2438c.getSupportTrafficSignRecogniton()) {
            j = 2 == this.f2438c.getSupportSLIF() ? 4296015872L : 1048576L;
            if (2 == this.f2438c.getTSRLitSpdChgSwitchState()) {
                j |= 2097152;
            }
            if (this.f2438c.getSupportIntellignetSpeedLimitAlert() == 0) {
                j |= 4194304;
                if (1 != this.f2438c.getIntelligentSpeedLimitMapVersionDisplay()) {
                    j |= 8388608;
                }
            }
            if (this.f2438c.getSupportIntellignetSpeedLimitControl() == 0) {
                j |= 16777216;
            }
            if (2 == this.f2438c.getSupportManualModel()) {
                j |= 33554432;
            }
            if (2 == this.f2438c.getSupportTSRFlow()) {
                j |= 268435456;
            }
        } else {
            j = 0;
        }
        if (1 == this.f2438c.getBSISSupport()) {
            j |= 67108864;
        }
        if (1 == this.f2438c.getMOISSupport()) {
            j |= 134217728;
        }
        if (1 == this.f2438c.getSupportDoubleConfirm()) {
            j |= 2147483648L;
        }
        if (1 == this.f2438c.getAcceleratorPedalSuppressionSystem()) {
            j |= 1073741824;
        }
        synchronized (this) {
            this.g |= j;
            BydLogHelper.logW(this.f2442a, "initTrafficSign init result = " + Long.toBinaryString(j));
            k();
        }
    }

    public boolean w() {
        String str = SystemProperties.get("persist.vendor.bosch.cfg.supplies.avm.camera.isp", "-1");
        String str2 = SystemProperties.get("persist.vendor.bosch.cfg.supplies.avm.name", "-1");
        if (TextUtils.equals(str, "-1") || TextUtils.equals(str2, "-1")) {
            return false;
        }
        int supportPanoramaImage = this.f2438c.getSupportPanoramaImage();
        return supportPanoramaImage == 1 || supportPanoramaImage == 2;
    }

    public final boolean x() {
        int iG = this.j.g();
        BydLogHelper.logD("configAutoStudy", "isCarEV : " + iG);
        if (iG == 47 || iG == 54 || iG == 58) {
            return false;
        }
        if (iG != 73) {
            return true;
        }
        BYDAutoGearboxDevice bYDAutoGearboxDevice = BYDAutoGearboxDevice.getInstance(this.f2443b);
        return (bYDAutoGearboxDevice == null || bYDAutoGearboxDevice.getGearboxType() == 1) ? false : true;
    }
}
