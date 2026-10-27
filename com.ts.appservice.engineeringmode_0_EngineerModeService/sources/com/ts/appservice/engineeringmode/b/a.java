package com.ts.appservice.engineeringmode.b;

import android.content.Context;
import android.util.Log;
import com.ts.appservice.engineeringmode.d.b;
import com.ts.appservice.engineeringmode.d.c;
import com.ts.appservice.engineeringmode.d.d;
import com.ts.appservice.engineeringmode.d.e;
import com.ts.appservice.engineeringmode.d.f;
import com.ts.appservice.engineeringmode.d.g;
import com.ts.appservice.engineeringmode.d.h;
import com.ts.appservice.engineeringmode.d.i;
import com.ts.lib.engineeringmode.IEngineeringSdkCallBack;
import com.ts.lib.engineeringmode.IEngineeringSdkManager;
import com.ts.lib.engineeringmode.bean.TSDiagnoseDeviceStatus;
import com.ts.lib.engineeringmode.bean.TsNetMap;
import java.util.List;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class a extends IEngineeringSdkManager.Stub implements com.ts.appservice.engineeringmode.c.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final d f32a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Context f33b;
    private final c c;
    private final com.ts.appservice.engineeringmode.a.a d;
    private final f e;
    private final h f;
    private final g g;
    private final Object h = new Object();
    private boolean i = true;
    private e j;
    private b k;
    private i l;

    public a(Context context) {
        this.f33b = context.getApplicationContext();
        com.ts.appservice.engineeringmode.a.a aVar = new com.ts.appservice.engineeringmode.a.a();
        this.d = aVar;
        this.c = new c(this, aVar, this.f33b);
        this.e = f.e();
        this.f = h.a();
        this.e.h(this.d);
        g gVarD = g.d();
        this.g = gVarD;
        gVarD.e(this.d);
        d dVarC = d.c();
        this.f32a = dVarC;
        dVarC.i(this.d);
        this.j = e.a();
        this.k = b.c();
        this.l = new i(this.f33b, this.d);
    }

    @Override // com.ts.appservice.engineeringmode.c.a
    public void a() {
        synchronized (this.h) {
            com.ts.appservice.engineeringmode.e.a.a("EngineeringModeBinder", "successToCallback in");
            boolean zE = this.c.e();
            com.ts.appservice.engineeringmode.e.a.a("EngineeringModeBinder", "dtcSuccess: " + zE);
            if (zE) {
                this.c.j(false);
                this.d.b();
                this.i = true;
                Log.w("EngineeringModeBinder", "  IsSyncData.out");
            }
            com.ts.appservice.engineeringmode.e.a.a("EngineeringModeBinder", "successToCallback out");
        }
    }

    public void b() {
        this.d.d();
    }

    public int enableAlarmLog(boolean z) {
        Log.w("EngineeringModeBinder", " enableAlarmLog.w()n");
        Log.w("EngineeringModeBinder", " enableAlarmLog.out=-1");
        return -1;
    }

    public int enableCloudServiceLog(boolean z) {
        Log.w("EngineeringModeBinder", " enableCloudServiceLog.w()n");
        Log.w("EngineeringModeBinder", " enableCloudServiceLog.out=-1");
        return -1;
    }

    public int enableConsoleSuspand(boolean z) {
        Log.w("EngineeringModeBinder", " enableConsoleSuspand.in");
        int iB = this.e.b(z);
        Log.w("EngineeringModeBinder", " enableConsoleSuspand.out=" + iB);
        return iB;
    }

    public int enableDexOpt(boolean z) {
        Log.w("EngineeringModeBinder", " enableDexOpt.in");
        Log.w("EngineeringModeBinder", " enableDexOpt.out=-1");
        return -1;
    }

    public int enableRamdumps(boolean z) {
        Log.w("EngineeringModeBinder", " enableRamdumps.in");
        Log.w("EngineeringModeBinder", " enableRamdumps.out=-1");
        return -1;
    }

    public int enableTCPDump(boolean z) {
        Log.w("EngineeringModeBinder", " enableTCPDump.in");
        int iC = this.e.c(z);
        Log.w("EngineeringModeBinder", " enableTCPDump.out=" + iC);
        return iC;
    }

    public TSDiagnoseDeviceStatus getA2BState() {
        Log.w("EngineeringModeBinder", " getA2BState.in");
        TSDiagnoseDeviceStatus tSDiagnoseDeviceStatusF = this.c.f("A2B");
        Log.w("EngineeringModeBinder", " getA2BState.out=" + tSDiagnoseDeviceStatusF.toString());
        return tSDiagnoseDeviceStatusF;
    }

    public TSDiagnoseDeviceStatus getADASState() {
        Log.w("EngineeringModeBinder", " getADASState.in");
        TSDiagnoseDeviceStatus tSDiagnoseDeviceStatusF = this.c.f("ADAS");
        Log.w("EngineeringModeBinder", " getADASState.out=" + tSDiagnoseDeviceStatusF.toString());
        return tSDiagnoseDeviceStatusF;
    }

    public TSDiagnoseDeviceStatus getAmplifierState() {
        Log.w("EngineeringModeBinder", " getAmplifierState.in");
        TSDiagnoseDeviceStatus tSDiagnoseDeviceStatusF = this.c.f("AMPLIFIER");
        Log.w("EngineeringModeBinder", " getAmplifierState.out=" + tSDiagnoseDeviceStatusF.toString());
        return tSDiagnoseDeviceStatusF;
    }

    public TSDiagnoseDeviceStatus getArmrestState() {
        Log.w("EngineeringModeBinder", " getArmrestState.in");
        TSDiagnoseDeviceStatus tSDiagnoseDeviceStatusF = this.c.f("ARMREST");
        Log.w("EngineeringModeBinder", " getArmrestState.out=" + tSDiagnoseDeviceStatusF.toString());
        return tSDiagnoseDeviceStatusF;
    }

    public TSDiagnoseDeviceStatus getAuxInCoPilotState() {
        Log.w("EngineeringModeBinder", " getAuxInCoPilotState.in");
        TSDiagnoseDeviceStatus tSDiagnoseDeviceStatusF = this.c.f("AUX_IN_CO_PILOT");
        Log.w("EngineeringModeBinder", " getAuxInCoPilotState.out = " + tSDiagnoseDeviceStatusF);
        return tSDiagnoseDeviceStatusF;
    }

    public TSDiagnoseDeviceStatus getAuxInMainDriverState() {
        Log.w("EngineeringModeBinder", " getAuxInMainDriverState.in");
        TSDiagnoseDeviceStatus tSDiagnoseDeviceStatusF = this.c.f("AUX_IN_MAIN_DRIVER");
        Log.w("EngineeringModeBinder", " getAuxInMainDriverState.out = " + tSDiagnoseDeviceStatusF);
        return tSDiagnoseDeviceStatusF;
    }

    public TSDiagnoseDeviceStatus getAvmFirstState() {
        Log.w("EngineeringModeBinder", " getAvmFirstState.in");
        TSDiagnoseDeviceStatus tSDiagnoseDeviceStatusF = this.c.f("AVM");
        Log.w("EngineeringModeBinder", " getAvmFirstState.out = " + tSDiagnoseDeviceStatusF);
        return tSDiagnoseDeviceStatusF;
    }

    public TSDiagnoseDeviceStatus getAvmFourthState() {
        Log.w("EngineeringModeBinder", " getAvmFourthState.in");
        TSDiagnoseDeviceStatus tSDiagnoseDeviceStatusF = this.c.f("AVM_FOURTH");
        Log.w("EngineeringModeBinder", " getAvmFourthState.out = " + tSDiagnoseDeviceStatusF);
        return tSDiagnoseDeviceStatusF;
    }

    public TSDiagnoseDeviceStatus getAvmSecondState() {
        Log.w("EngineeringModeBinder", " getAvmSecondState.in");
        TSDiagnoseDeviceStatus tSDiagnoseDeviceStatusF = this.c.f("AVM_SECOND");
        Log.w("EngineeringModeBinder", " getAvmSecondState.out = " + tSDiagnoseDeviceStatusF);
        return tSDiagnoseDeviceStatusF;
    }

    public TSDiagnoseDeviceStatus getAvmThirdState() {
        Log.w("EngineeringModeBinder", " getAvmThirdState.in");
        TSDiagnoseDeviceStatus tSDiagnoseDeviceStatusF = this.c.f("AVM_THIRD");
        Log.w("EngineeringModeBinder", " getAvmThirdState.out = " + tSDiagnoseDeviceStatusF);
        return tSDiagnoseDeviceStatusF;
    }

    public TSDiagnoseDeviceStatus getBTState() {
        Log.w("EngineeringModeBinder", " getBTState.in");
        TSDiagnoseDeviceStatus tSDiagnoseDeviceStatusF = this.c.f("BT");
        Log.w("EngineeringModeBinder", " getBTState.out=" + tSDiagnoseDeviceStatusF.toString());
        return tSDiagnoseDeviceStatusF;
    }

    public TSDiagnoseDeviceStatus getCanState() {
        Log.w("EngineeringModeBinder", " getCanState.in");
        TSDiagnoseDeviceStatus tSDiagnoseDeviceStatusF = this.c.f("CAN");
        Log.w("EngineeringModeBinder", " getCanState.out=" + tSDiagnoseDeviceStatusF.toString());
        return tSDiagnoseDeviceStatusF;
    }

    public boolean getConsoleSuspandState() {
        Log.w("EngineeringModeBinder", " getConsoleSuspandState.in");
        boolean zD = this.e.d();
        Log.w("EngineeringModeBinder", " getConsoleSuspandState.out = " + zD);
        return zD;
    }

    public TSDiagnoseDeviceStatus getDMSPowerState() {
        Log.w("EngineeringModeBinder", " getDMSPowerState.in");
        TSDiagnoseDeviceStatus tSDiagnoseDeviceStatusF = this.c.f("DMS_POWER");
        Log.w("EngineeringModeBinder", " getDMSPowerState.out=" + tSDiagnoseDeviceStatusF.toString());
        return tSDiagnoseDeviceStatusF;
    }

    public TSDiagnoseDeviceStatus getDMSState() {
        Log.w("EngineeringModeBinder", " getDMSState.in");
        TSDiagnoseDeviceStatus tSDiagnoseDeviceStatusF = this.c.f("DMS");
        Log.w("EngineeringModeBinder", " getDMSState.out=" + tSDiagnoseDeviceStatusF.toString());
        return tSDiagnoseDeviceStatusF;
    }

    public TSDiagnoseDeviceStatus getDVRState() {
        Log.w("EngineeringModeBinder", " getDVRState.in");
        TSDiagnoseDeviceStatus tSDiagnoseDeviceStatusF = this.c.f("DVR");
        Log.w("EngineeringModeBinder", " getDVRState.out=" + tSDiagnoseDeviceStatusF.toString());
        return tSDiagnoseDeviceStatusF;
    }

    public boolean getDisplayForwardLinkMarginValue(int i) {
        Log.w("EngineeringModeBinder", " getDisplayForwardLinkMarginValue.in displayPanelId : " + i);
        boolean zA = this.k.a(i);
        Log.w("EngineeringModeBinder", " getDisplayForwardLinkMarginValue.out = " + zA);
        return zA;
    }

    public boolean getDisplayReverseLinkMarginValue(int i) {
        Log.w("EngineeringModeBinder", " getDisplayReverseLinkMarginValue.in displayPanelId : " + i);
        boolean zB = this.k.b(i);
        Log.w("EngineeringModeBinder", " getDisplayReverseLinkMarginValue.out = " + zB);
        return zB;
    }

    public TSDiagnoseDeviceStatus getFaceIDState() {
        Log.w("EngineeringModeBinder", " getFaceIDState.in");
        TSDiagnoseDeviceStatus tSDiagnoseDeviceStatusF = this.c.f("FACE");
        Log.w("EngineeringModeBinder", " getFaceIDState.out=" + tSDiagnoseDeviceStatusF.toString());
        return tSDiagnoseDeviceStatusF;
    }

    public TSDiagnoseDeviceStatus getGNSSState() {
        Log.w("EngineeringModeBinder", " getGNSSState.in");
        TSDiagnoseDeviceStatus tSDiagnoseDeviceStatusF = this.c.f("GNSS");
        Log.w("EngineeringModeBinder", " getGNSSState.out=" + tSDiagnoseDeviceStatusF.toString());
        return tSDiagnoseDeviceStatusF;
    }

    public TSDiagnoseDeviceStatus getHIDNetworkLeftState() {
        Log.w("EngineeringModeBinder", " getHIDNetworkLeftState.in");
        TSDiagnoseDeviceStatus tSDiagnoseDeviceStatusF = this.c.f("HID_NETWORK_LEFT");
        Log.w("EngineeringModeBinder", " getHIDNetworkLeftState.out=" + tSDiagnoseDeviceStatusF.toString());
        return tSDiagnoseDeviceStatusF;
    }

    public TSDiagnoseDeviceStatus getHIDNetworkRightState() {
        Log.w("EngineeringModeBinder", " getHIDNetworkRightState.in");
        TSDiagnoseDeviceStatus tSDiagnoseDeviceStatusF = this.c.f("HID_NETWORK_RIGHT");
        Log.w("EngineeringModeBinder", " getHIDNetworkRightState.out=" + tSDiagnoseDeviceStatusF.toString());
        return tSDiagnoseDeviceStatusF;
    }

    public TSDiagnoseDeviceStatus getHUDNetWorkState() {
        Log.w("EngineeringModeBinder", " getHUDNetWorkState.in");
        TSDiagnoseDeviceStatus tSDiagnoseDeviceStatusF = this.c.f("HUD_NET_WORK");
        Log.w("EngineeringModeBinder", " getHUDNetWorkState.out=" + tSDiagnoseDeviceStatusF.toString());
        return tSDiagnoseDeviceStatusF;
    }

    public TSDiagnoseDeviceStatus getHUDState() {
        Log.w("EngineeringModeBinder", " getHUDState.in");
        TSDiagnoseDeviceStatus tSDiagnoseDeviceStatusF = this.c.f("HUD");
        Log.w("EngineeringModeBinder", " getHUDState.out=" + tSDiagnoseDeviceStatusF.toString());
        return tSDiagnoseDeviceStatusF;
    }

    public TSDiagnoseDeviceStatus getIMSPowerState() {
        Log.w("EngineeringModeBinder", " getIMSPowerState.in");
        TSDiagnoseDeviceStatus tSDiagnoseDeviceStatusF = this.c.f("IMS_POWER");
        Log.w("EngineeringModeBinder", " getIMSPowerState.out=" + tSDiagnoseDeviceStatusF.toString());
        return tSDiagnoseDeviceStatusF;
    }

    public TSDiagnoseDeviceStatus getIMSState() {
        Log.w("EngineeringModeBinder", " getIMSState.in");
        TSDiagnoseDeviceStatus tSDiagnoseDeviceStatusF = this.c.f("IMS");
        Log.w("EngineeringModeBinder", " getIMSState.out=" + tSDiagnoseDeviceStatusF.toString());
        return tSDiagnoseDeviceStatusF;
    }

    public TSDiagnoseDeviceStatus getIVIState() {
        Log.w("EngineeringModeBinder", " getIVIState.in");
        TSDiagnoseDeviceStatus tSDiagnoseDeviceStatusF = this.c.f("IVI");
        Log.w("EngineeringModeBinder", " getIVIState.out=" + tSDiagnoseDeviceStatusF.toString());
        return tSDiagnoseDeviceStatusF;
    }

    public TSDiagnoseDeviceStatus getInstrumentState() {
        Log.w("EngineeringModeBinder", " getInstrumentState.in");
        TSDiagnoseDeviceStatus tSDiagnoseDeviceStatusF = this.c.f("INSTRUMENT");
        Log.w("EngineeringModeBinder", " getInstrumentState.out=" + tSDiagnoseDeviceStatusF.toString());
        return tSDiagnoseDeviceStatusF;
    }

    public TSDiagnoseDeviceStatus getMicLeftFrontState() {
        Log.w("EngineeringModeBinder", " getMicLeftFrontState.in");
        TSDiagnoseDeviceStatus tSDiagnoseDeviceStatusF = this.c.f("MIC_LEFT_FRONT");
        Log.w("EngineeringModeBinder", " getMicLeftFrontState.out = " + tSDiagnoseDeviceStatusF);
        return tSDiagnoseDeviceStatusF;
    }

    public TSDiagnoseDeviceStatus getMicLeftMiddleState() {
        Log.w("EngineeringModeBinder", " getMicLeftMiddleState.in");
        TSDiagnoseDeviceStatus tSDiagnoseDeviceStatusF = this.c.f("MIC_LEFT_MIDDLE");
        Log.w("EngineeringModeBinder", " getMicLeftMiddleState.out = " + tSDiagnoseDeviceStatusF);
        return tSDiagnoseDeviceStatusF;
    }

    public TSDiagnoseDeviceStatus getMicLeftRearState() {
        Log.w("EngineeringModeBinder", " getMicLeftRearState.in");
        TSDiagnoseDeviceStatus tSDiagnoseDeviceStatusF = this.c.f("MIC_LEFT_REAR");
        Log.w("EngineeringModeBinder", " getMicLeftRearState.out = " + tSDiagnoseDeviceStatusF);
        return tSDiagnoseDeviceStatusF;
    }

    public TSDiagnoseDeviceStatus getMicRightFrontState() {
        Log.w("EngineeringModeBinder", " getMicRightFrontState.in");
        TSDiagnoseDeviceStatus tSDiagnoseDeviceStatusF = this.c.f("MIC_RIGHT_FRONT");
        Log.w("EngineeringModeBinder", " getMicRightFrontState.out = " + tSDiagnoseDeviceStatusF);
        return tSDiagnoseDeviceStatusF;
    }

    public TSDiagnoseDeviceStatus getMicRightMiddleState() {
        Log.w("EngineeringModeBinder", " getMicRightMiddleState.in");
        TSDiagnoseDeviceStatus tSDiagnoseDeviceStatusF = this.c.f("MIC_RIGHT_MIDDLE");
        Log.w("EngineeringModeBinder", " getMicRightMiddleState.out = " + tSDiagnoseDeviceStatusF);
        return tSDiagnoseDeviceStatusF;
    }

    public TSDiagnoseDeviceStatus getMicRightRearState() {
        Log.w("EngineeringModeBinder", " getMicRightRearState.in");
        TSDiagnoseDeviceStatus tSDiagnoseDeviceStatusF = this.c.f("MIC_RIGHT_REAR");
        Log.w("EngineeringModeBinder", " getMicRightRearState.out = " + tSDiagnoseDeviceStatusF);
        return tSDiagnoseDeviceStatusF;
    }

    public TSDiagnoseDeviceStatus getOMSPowerState() {
        Log.w("EngineeringModeBinder", " getOMSPowerState.in");
        TSDiagnoseDeviceStatus tSDiagnoseDeviceStatusF = this.c.f("OMS_POWER");
        Log.w("EngineeringModeBinder", " getOMSPowerState.out=" + tSDiagnoseDeviceStatusF.toString());
        return tSDiagnoseDeviceStatusF;
    }

    public TSDiagnoseDeviceStatus getOMSState() {
        Log.w("EngineeringModeBinder", " getOMSState.in");
        TSDiagnoseDeviceStatus tSDiagnoseDeviceStatusF = this.c.f("OMS");
        Log.w("EngineeringModeBinder", " getOMSState.out=" + tSDiagnoseDeviceStatusF.toString());
        return tSDiagnoseDeviceStatusF;
    }

    public int getPowerOc(int i) {
        Log.w("EngineeringModeBinder", " getPowerOc.in");
        int iB = this.f.b(i);
        Log.w("EngineeringModeBinder", " getPowerOc.out = " + iB);
        return iB;
    }

    public TSDiagnoseDeviceStatus getRSEState() {
        Log.w("EngineeringModeBinder", " getRSEState.in");
        TSDiagnoseDeviceStatus tSDiagnoseDeviceStatusF = this.c.f("RSE");
        Log.w("EngineeringModeBinder", " getRSEState.out=" + tSDiagnoseDeviceStatusF.toString());
        return tSDiagnoseDeviceStatusF;
    }

    public TSDiagnoseDeviceStatus getRadioAntennaFirstState() {
        Log.w("EngineeringModeBinder", " getRadioAntennaFirstState.in");
        TSDiagnoseDeviceStatus tSDiagnoseDeviceStatusF = this.c.f("RADIO_FIRST");
        Log.w("EngineeringModeBinder", " getRadioAntennaFirstState.out = " + tSDiagnoseDeviceStatusF);
        return tSDiagnoseDeviceStatusF;
    }

    public TSDiagnoseDeviceStatus getRadioAntennaSecondState() {
        Log.w("EngineeringModeBinder", " getRadioAntennaSecondState.in");
        TSDiagnoseDeviceStatus tSDiagnoseDeviceStatusF = this.c.f("RADIO_SECOND");
        Log.w("EngineeringModeBinder", " getRadioAntennaSecondState.out = " + tSDiagnoseDeviceStatusF);
        return tSDiagnoseDeviceStatusF;
    }

    public String getRealAutoVin() {
        Log.w("EngineeringModeBinder", " getRealAutoVin.in");
        String strB = this.j.b();
        Log.w("EngineeringModeBinder", " getRealAutoVin.out");
        return strB;
    }

    public TSDiagnoseDeviceStatus getSignalIntensityState() {
        Log.w("EngineeringModeBinder", " getSignalIntensityState.in");
        TSDiagnoseDeviceStatus tSDiagnoseDeviceStatusF = this.c.f("TBOX");
        Log.w("EngineeringModeBinder", " getSignalIntensityState.out=" + tSDiagnoseDeviceStatusF.toString());
        return tSDiagnoseDeviceStatusF;
    }

    public TSDiagnoseDeviceStatus getStreamMediaState() {
        Log.w("EngineeringModeBinder", " getStreamMediaState.in");
        TSDiagnoseDeviceStatus tSDiagnoseDeviceStatusF = this.c.f("STREAM_MEDIA");
        Log.w("EngineeringModeBinder", " getStreamMediaState.out=" + tSDiagnoseDeviceStatusF.toString());
        return tSDiagnoseDeviceStatusF;
    }

    public boolean getTcpDumpState() {
        Log.w("EngineeringModeBinder", " getTcpDumpState.in");
        boolean zF = this.e.f();
        Log.w("EngineeringModeBinder", " getTcpDumpState.out = " + zF);
        return zF;
    }

    public TSDiagnoseDeviceStatus getUAVState() {
        Log.w("EngineeringModeBinder", " getUAVState.in");
        TSDiagnoseDeviceStatus tSDiagnoseDeviceStatusF = this.c.f("UAV");
        Log.w("EngineeringModeBinder", " getUAVState.out=" + tSDiagnoseDeviceStatusF.toString());
        return tSDiagnoseDeviceStatusF;
    }

    public TSDiagnoseDeviceStatus getUSBCommunicationState() {
        Log.w("EngineeringModeBinder", " getUSBCommunicationState.in");
        TSDiagnoseDeviceStatus tSDiagnoseDeviceStatusF = this.c.f("USB_COMMUNICATION");
        Log.w("EngineeringModeBinder", " getUSBCommunicationState.out=" + tSDiagnoseDeviceStatusF.toString());
        return tSDiagnoseDeviceStatusF;
    }

    public TSDiagnoseDeviceStatus getUSBPowerState() {
        Log.w("EngineeringModeBinder", " getUSBPowerState.in");
        TSDiagnoseDeviceStatus tSDiagnoseDeviceStatusF = this.c.f("USB_POWER");
        Log.w("EngineeringModeBinder", " getUSBPowerState.out=" + tSDiagnoseDeviceStatusF.toString());
        return tSDiagnoseDeviceStatusF;
    }

    public boolean getUsbConnectStatus(int i) {
        Log.w("EngineeringModeBinder", " getUsbConnectStatus.in");
        boolean zC = this.f.c(i);
        Log.w("EngineeringModeBinder", " getUsbConnectStatus.out = " + zC);
        return zC;
    }

    public TSDiagnoseDeviceStatus getWhudState() {
        Log.w("EngineeringModeBinder", " getWhudState.in");
        TSDiagnoseDeviceStatus tSDiagnoseDeviceStatusF = this.c.f("W_HUD");
        Log.w("EngineeringModeBinder", " getWhudState.out = " + tSDiagnoseDeviceStatusF);
        return tSDiagnoseDeviceStatusF;
    }

    public TSDiagnoseDeviceStatus getWifiState() {
        Log.w("EngineeringModeBinder", " getWifiState.in");
        TSDiagnoseDeviceStatus tSDiagnoseDeviceStatusF = this.c.f("WIFI");
        Log.w("EngineeringModeBinder", " getWifiState.out=" + tSDiagnoseDeviceStatusF.toString());
        return tSDiagnoseDeviceStatusF;
    }

    public boolean obdRequest(String str, String str2) {
        Log.w("EngineeringModeBinder", " obdRequest.in");
        boolean zE = this.f32a.e(str, str2);
        Log.w("EngineeringModeBinder", " obdRequest.out = " + zE);
        return zE;
    }

    public boolean obdTsRequest(int i, int i2, List<TsNetMap> list, boolean z) {
        Log.w("EngineeringModeBinder", " obdRequest.in");
        boolean zD = this.f32a.d(i, i2, list, z);
        Log.w("EngineeringModeBinder", " obdRequest.out = " + zD);
        return zD;
    }

    public int queryOffScreenFlag(int i) {
        Log.w("EngineeringModeBinder", " queryOffScreenFlag.in type : " + i);
        int iH = this.l.h(i);
        Log.w("EngineeringModeBinder", " queryOffScreenFlag.out = " + iH);
        return iH;
    }

    public void registerListener(IEngineeringSdkCallBack iEngineeringSdkCallBack) {
        this.d.j(iEngineeringSdkCallBack);
        if (this.c.h()) {
            this.d.c(true);
        }
    }

    public void setEnergyTest(int i) {
        Log.w("EngineeringModeBinder", "setEnergyTest.in status : " + i);
        this.f32a.h(i);
    }

    public int setOffScreenFlag(int i, int i2) {
        Log.w("EngineeringModeBinder", " setOffScreenFlag.in type : " + i + ", flag : " + i2);
        int iJ = this.l.j(i, i2);
        StringBuilder sb = new StringBuilder();
        sb.append(" setOffScreenFlag.out = ");
        sb.append(iJ);
        Log.w("EngineeringModeBinder", sb.toString());
        return iJ;
    }

    public void synchronousQNXData() {
        Log.w("EngineeringModeBinder", "  synchronousQNXData.in");
        synchronized (this.h) {
            if (this.i) {
                Log.w("EngineeringModeBinder", "  IsSyncData.in");
                this.i = false;
                this.c.k();
            }
        }
        Log.w("EngineeringModeBinder", "  synchronousQNXData.out");
    }

    public void unregisterListener(IEngineeringSdkCallBack iEngineeringSdkCallBack) {
        this.d.k(iEngineeringSdkCallBack);
    }
}
