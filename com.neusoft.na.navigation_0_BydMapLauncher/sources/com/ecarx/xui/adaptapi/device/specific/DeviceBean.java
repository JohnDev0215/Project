package com.ecarx.xui.adaptapi.device.specific;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProviderInfo;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import com.ecarx.xui.adaptapi.device.daynigntmode.IDayNightMode;
import com.ecarx.xui.adaptapi.device.impl.DayNightModeImpl;
import com.ecarx.xui.adaptapi.device.specific.DayNightModeReceiver;
import com.ecarx.xui.adaptapi.device.utility.ModelHelper;
import com.ecarx.xui.adaptapi.wpc.specific.WPCModel;
import com.neusoft.alfus.RpcManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class DeviceBean {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String TAG = "DeviceBean";
    private IDayNightMode.IDayNightChangeCallBack iDayNightChangeCallBack;
    private Context mContext;
    private RpcManager rpcManager = RpcManager.getInstance();
    private String xdsnString = null;
    private String ihuidString = null;
    private DayNightModeReceiver mDayNightModeReceiver = new DayNightModeReceiver(new DayNightModeReceiver.IModeListener() { // from class: com.ecarx.xui.adaptapi.device.specific.DeviceBean.1
        @Override // com.ecarx.xui.adaptapi.device.specific.DayNightModeReceiver.IModeListener
        public void onDayNightModeChange(int i) {
            Log.d(DeviceBean.TAG, " onDayNightModeChange = " + i);
            if (i == 0) {
                DeviceBean.this.iDayNightChangeCallBack.onDayNightChanged(1);
            } else if (i == 1) {
                DeviceBean.this.iDayNightChangeCallBack.onDayNightChanged(2);
            } else {
                DeviceBean.this.iDayNightChangeCallBack.onDayNightChanged(0);
            }
        }
    });
    private IDayNightMode mIDayNightMode = new DayNightModeImpl(this);

    public static char convertIntToAscii(int i) {
        if (i < 0 || i > 255) {
            return (char) 0;
        }
        return (char) i;
    }

    public int getMaxScreenBrightness() {
        return 10;
    }

    public int getMinScreenBrightness() {
        return 1;
    }

    public DeviceBean(Context context) {
        this.mContext = context;
    }

    public String getIhuId() {
        StringBuffer stringBuffer = new StringBuffer();
        byte[] bArr = new byte[25];
        bArr[0] = 22;
        byte[] message = this.rpcManager.getMessage(1562, bArr);
        byte[] bArr2 = new byte[25];
        bArr2[0] = 23;
        sleep(10L);
        byte[] message2 = this.rpcManager.getMessage(1562, bArr2);
        if (message != null && message2 != null) {
            Log.d(TAG, "MMI1===" + Arrays.toString(message));
            Log.d(TAG, "MMI2===" + Arrays.toString(message2));
            for (int i = 1; i <= 16; i++) {
                stringBuffer.append(convertIntToAscii(message[i]));
            }
            for (int i2 = 1; i2 <= 8; i2++) {
                stringBuffer.append(convertIntToAscii(message2[i2]));
            }
            this.ihuidString = stringBuffer.toString();
            Log.d(TAG, "getIhuId = " + this.ihuidString);
        }
        return stringBuffer.toString();
    }

    public String getXdsn() {
        StringBuffer stringBuffer = new StringBuffer();
        byte[] bArr = new byte[25];
        bArr[0] = 22;
        byte[] message = this.rpcManager.getMessage(1562, bArr);
        byte[] bArr2 = new byte[25];
        bArr2[0] = 23;
        sleep(10L);
        byte[] message2 = this.rpcManager.getMessage(1562, bArr2);
        if (message != null && message2 != null) {
            Log.d(TAG, "MMI1===" + Arrays.toString(message));
            Log.d(TAG, "MMI2===" + Arrays.toString(message2));
            for (int i = 1; i <= 16; i++) {
                stringBuffer.append(convertIntToAscii(message[i]));
            }
            for (int i2 = 1; i2 <= 8; i2++) {
                if (i2 == 3) {
                    stringBuffer.append(0);
                } else {
                    stringBuffer.append(convertIntToAscii(message2[i2]));
                }
            }
            this.xdsnString = stringBuffer.toString();
            Log.d(TAG, "getXdsn = " + this.xdsnString);
        } else {
            Log.e(TAG, "getXdsn MMI1 or MMI2 is null");
        }
        return stringBuffer.toString();
    }

    private void sleep(long j) {
        try {
            Thread.sleep(j);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getVin() {
        byte[] bArr = new byte[25];
        bArr[0] = 19;
        byte[] message = this.rpcManager.getMessage(1562, bArr);
        if (message == null) {
            return "";
        }
        char cConvertIntToAscii = convertIntToAscii(message[1]);
        char cConvertIntToAscii2 = convertIntToAscii(message[2]);
        char cConvertIntToAscii3 = convertIntToAscii(message[3]);
        char cConvertIntToAscii4 = convertIntToAscii(message[4]);
        char cConvertIntToAscii5 = convertIntToAscii(message[5]);
        char cConvertIntToAscii6 = convertIntToAscii(message[6]);
        char cConvertIntToAscii7 = convertIntToAscii(message[7]);
        char cConvertIntToAscii8 = convertIntToAscii(message[8]);
        char cConvertIntToAscii9 = convertIntToAscii(message[9]);
        char cConvertIntToAscii10 = convertIntToAscii(message[10]);
        char cConvertIntToAscii11 = convertIntToAscii(message[11]);
        char cConvertIntToAscii12 = convertIntToAscii(message[12]);
        char cConvertIntToAscii13 = convertIntToAscii(message[13]);
        char cConvertIntToAscii14 = convertIntToAscii(message[14]);
        char cConvertIntToAscii15 = convertIntToAscii(message[15]);
        char cConvertIntToAscii16 = convertIntToAscii(message[16]);
        char cConvertIntToAscii17 = convertIntToAscii(message[17]);
        String str = cConvertIntToAscii + "" + cConvertIntToAscii2 + "" + cConvertIntToAscii3 + "" + cConvertIntToAscii4 + "" + cConvertIntToAscii5 + "" + cConvertIntToAscii6 + "" + cConvertIntToAscii7 + "" + cConvertIntToAscii8 + "" + cConvertIntToAscii9 + "" + cConvertIntToAscii10 + "" + cConvertIntToAscii11 + "" + cConvertIntToAscii12 + "" + cConvertIntToAscii13 + "" + cConvertIntToAscii14 + "" + cConvertIntToAscii15 + "" + cConvertIntToAscii16 + "" + cConvertIntToAscii17;
        Log.d(TAG, "getVin = " + cConvertIntToAscii + "" + cConvertIntToAscii2 + "" + cConvertIntToAscii3 + "" + cConvertIntToAscii4 + "" + cConvertIntToAscii5 + "" + cConvertIntToAscii6 + "" + cConvertIntToAscii7 + "" + cConvertIntToAscii8 + "" + cConvertIntToAscii9 + "" + cConvertIntToAscii10 + "" + cConvertIntToAscii11 + "" + cConvertIntToAscii12 + "" + cConvertIntToAscii13 + "" + cConvertIntToAscii14 + "" + cConvertIntToAscii15 + "" + cConvertIntToAscii16 + "" + cConvertIntToAscii17);
        return str;
    }

    public String getMcuSoftwareVersion() {
        Log.d(TAG, "getMcuSoftwareVersion = " + Build.DISPLAY);
        return Build.DISPLAY;
    }

    public String getMcuHardwareVersion() {
        byte[] bArr = new byte[25];
        bArr[0] = WPCModel.cSignalId0x27;
        byte[] message = this.rpcManager.getMessage(1562, bArr);
        if (message == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        int i = message[1] & 255;
        int i2 = message[2] & 255;
        int i3 = message[3] & 255;
        int i4 = message[4] & 255;
        int i5 = message[5] & 255;
        int i6 = message[6] & 255;
        int i7 = message[7] & 255;
        int i8 = message[8] & 255;
        int i9 = message[9] & 255;
        int i10 = message[10] & 255;
        int i11 = message[11] & 255;
        int i12 = message[12] & 255;
        int i13 = message[13] & 255;
        int i14 = message[14] & 255;
        int i15 = message[15] & 255;
        int i16 = message[16] & 255;
        String strValueOf = String.valueOf((char) i);
        String strValueOf2 = String.valueOf((char) i2);
        String strValueOf3 = String.valueOf((char) i3);
        String strValueOf4 = String.valueOf((char) i4);
        String strValueOf5 = String.valueOf((char) i5);
        String strValueOf6 = String.valueOf((char) i6);
        String strValueOf7 = String.valueOf((char) i7);
        String strValueOf8 = String.valueOf((char) i8);
        String strValueOf9 = String.valueOf((char) i9);
        String strValueOf10 = String.valueOf((char) i10);
        String strValueOf11 = String.valueOf((char) i11);
        String strValueOf12 = String.valueOf((char) i12);
        String strValueOf13 = String.valueOf((char) i13);
        String strValueOf14 = String.valueOf((char) i14);
        String strValueOf15 = String.valueOf((char) i15);
        String strValueOf16 = String.valueOf((char) i16);
        stringBuffer.append(strValueOf);
        stringBuffer.append(strValueOf2);
        stringBuffer.append(strValueOf3);
        stringBuffer.append(strValueOf4);
        stringBuffer.append(strValueOf5);
        stringBuffer.append(strValueOf6);
        stringBuffer.append(strValueOf7);
        stringBuffer.append(strValueOf8);
        stringBuffer.append(strValueOf9);
        stringBuffer.append(strValueOf10);
        stringBuffer.append(strValueOf11);
        stringBuffer.append(strValueOf12);
        stringBuffer.append(strValueOf13);
        stringBuffer.append(strValueOf14);
        stringBuffer.append(strValueOf15);
        stringBuffer.append(strValueOf16);
        Log.d(TAG, "getMcuHardwareVersion = " + stringBuffer.toString());
        return stringBuffer.toString();
    }

    public String getVehicleType() {
        String vehicleType = ModelHelper.getInstance(this.mContext).getVehicleType();
        Log.d(TAG, "getVehicleType = " + vehicleType);
        return vehicleType;
    }

    public String getProjectCode() {
        String projectCode = ModelHelper.getInstance(this.mContext).getProjectCode();
        Log.d(TAG, "getProjectCode = " + projectCode);
        return projectCode;
    }

    public String getSupplierCode() {
        String supplierCode = ModelHelper.getInstance(this.mContext).getSupplierCode();
        Log.d(TAG, "getSupplierCode = " + supplierCode);
        return supplierCode;
    }

    public boolean isRearViewCameraConfigured() {
        boolean zHasRearViewCamera = ModelHelper.getInstance(this.mContext).hasRearViewCamera();
        Log.d(TAG, "hasRearViewCamera = " + zHasRearViewCamera);
        return zHasRearViewCamera;
    }

    public boolean isDVRCameraConfigured() {
        boolean zHasDVR = ModelHelper.getInstance(this.mContext).hasDVR();
        Log.d(TAG, "hasDVR = " + zHasDVR);
        return zHasDVR;
    }

    public IDayNightMode getDayNightMode() {
        IDayNightMode iDayNightMode = this.mIDayNightMode;
        return iDayNightMode == null ? new DayNightModeImpl(this) : iDayNightMode;
    }

    public int getDayNightModes() {
        byte[] bArr = new byte[25];
        bArr[0] = 2;
        byte[] message = RpcManager.getInstance().getMessage(1881, bArr);
        Log.d(TAG, "getPositionLightSts() array: " + Arrays.toString(bArr));
        if (message != null) {
            Log.d(TAG, "getPositionLightSts = " + Arrays.toString(message));
            byte b = message[9];
            if (b == 0) {
                return 1;
            }
            if (b == 1) {
                return 2;
            }
        }
        Log.d(TAG, "getPositionLightSts datas is null,return DISPLAY_MODE_INVALID.");
        return 0;
    }

    public int getDayNight() {
        byte[] bArr = new byte[25];
        bArr[0] = 2;
        byte[] message = RpcManager.getInstance().getMessage(1881, bArr);
        Log.d(TAG, "getPositionLightSts() array: " + Arrays.toString(bArr));
        if (message != null) {
            Log.d(TAG, "getPositionLightSts = " + Arrays.toString(message));
            byte b = message[9];
            if (b == 0) {
                return 1;
            }
            if (b == 1) {
                return 2;
            }
        }
        Log.d(TAG, "getPositionLightSts datas is null,return DISPLAY_INVALID.");
        return 0;
    }

    public List<ComponentName> getSupportedComponents() {
        ArrayList arrayList = new ArrayList();
        Iterator<AppWidgetProviderInfo> it = AppWidgetManager.getInstance(this.mContext).getInstalledProviders().iterator();
        while (it.hasNext()) {
            ComponentName componentName = it.next().provider;
            if (isComponentSupported(componentName)) {
                arrayList.add(componentName);
            }
        }
        Log.d(TAG, "getSupportedComponents = " + arrayList.toString());
        return arrayList;
    }

    private boolean isComponentSupported(ComponentName componentName) {
        if (!componentName.getPackageName().equalsIgnoreCase("com.neusoft.ecarx.settingwidget")) {
            return false;
        }
        ModelHelper modelHelper = ModelHelper.getInstance(this.mContext);
        if (componentName.getClassName().equals("com.neusoft.ecarx.settingwidget.widget.BeepSwitch")) {
            return true;
        }
        if (componentName.getClassName().equals("com.neusoft.ecarx.settingwidget.widget.DriveVideoForbiddenSwitch")) {
            return modelHelper.hasVideoForbidden();
        }
        if (componentName.getClassName().equals("com.neusoft.ecarx.settingwidget.widget.AntiPinchSwitch")) {
            return modelHelper.hasAntiPinchWarnSetResp();
        }
        if (componentName.getClassName().equals("com.neusoft.ecarx.settingwidget.widget.AnyDoorOpenWarnSwitch")) {
            return modelHelper.hasAnyDoorOpenWarnOption();
        }
        if (componentName.getClassName().equals("com.neusoft.ecarx.settingwidget.widget.APSwitch")) {
            return !modelHelper.noWifiAp();
        }
        if (componentName.getClassName().equals("com.neusoft.ecarx.settingwidget.widget.APUSwitch")) {
            return modelHelper.hasAPUOption();
        }
        if (componentName.getClassName().equals("com.neusoft.ecarx.settingwidget.widget.AVASwitch")) {
            return modelHelper.hasAVASDisabledSet();
        }
        if (componentName.getClassName().equals("com.neusoft.ecarx.settingwidget.widget.BluetoothSwitch")) {
            return false;
        }
        if (componentName.getClassName().equals("com.neusoft.ecarx.settingwidget.widget.BSDSwitch")) {
            return modelHelper.hasPAS_SVA_Status();
        }
        if (componentName.getClassName().equals("com.neusoft.ecarx.settingwidget.widget.CarPlaySwitch") || componentName.getClassName().equals("com.neusoft.ecarx.settingwidget.widget.CarLifeSwitch")) {
            return modelHelper.hasCarPlayOrCarLife();
        }
        if (componentName.getClassName().equals("com.neusoft.ecarx.settingwidget.widget.ChairAnimSwitch")) {
            return modelHelper.hasWindowSeatSwitch() != 0;
        }
        if (componentName.getClassName().equals("com.neusoft.ecarx.settingwidget.widget.DayLightSwitch")) {
            return modelHelper.hasDayRunLightSet();
        }
        if (componentName.getClassName().equals("com.neusoft.ecarx.settingwidget.widget.DVRRecordSwitch")) {
            return modelHelper.hasDVRRecord();
        }
        if (componentName.getClassName().equals("com.neusoft.ecarx.settingwidget.widget.EtcSwitch")) {
            return modelHelper.hasEtc();
        }
        if (componentName.getClassName().equals("com.neusoft.ecarx.settingwidget.widget.HUDInputSwitch") || componentName.getClassName().equals("com.neusoft.ecarx.settingwidget.widget.HUDSwitch")) {
            return modelHelper.hasHUDFunction();
        }
        if (componentName.getClassName().equals("com.neusoft.ecarx.settingwidget.widget.RearMirrorSwitch")) {
            return modelHelper.hasRearMirrorFoldSet();
        }
        if (componentName.getClassName().equals("com.neusoft.ecarx.settingwidget.widget.RLSSwitch")) {
            return modelHelper.hasRLSModeEnable();
        }
        if (componentName.getClassName().equals("com.neusoft.ecarx.settingwidget.widget.TerminalOnWarnSwitch")) {
            return modelHelper.hasTerminalNotOFFWarnOption();
        }
        if (componentName.getClassName().equals("com.neusoft.ecarx.settingwidget.widget.TiltedRearMirror")) {
            return modelHelper.hasRearMirrorReverseInclineReq();
        }
        if (componentName.getClassName().equals("com.neusoft.ecarx.settingwidget.widget.UsherLightSwitch")) {
            return modelHelper.hasWelcomeLightSet();
        }
        if (componentName.getClassName().equals("com.neusoft.ecarx.settingwidget.widget.VRSwitch")) {
            return modelHelper.hasVrSetting();
        }
        if (componentName.getClassName().equals("com.neusoft.ecarx.settingwidget.widget.WALSwitch")) {
            return modelHelper.hasWALOption();
        }
        if (componentName.getClassName().equals("com.neusoft.ecarx.settingwidget.widget.WifiSwitch")) {
            return modelHelper.hasWifi();
        }
        if (componentName.getClassName().equals("com.neusoft.ecarx.settingwidget.widget.WiperLinkageSwitch")) {
            return modelHelper.hasWiperLinkage();
        }
        if (componentName.getClassName().equals("com.neusoft.ecarx.settingwidget.widget.WirelessChargeSwitch")) {
            return modelHelper.hasWirelessCharge();
        }
        if (componentName.getClassName().equals("com.neusoft.ecarx.settingwidget.widget.VolumeVersusSpeedSwitch")) {
            return modelHelper.hasVolumeVersusSpeed();
        }
        if (componentName.getClassName().equals("com.neusoft.ecarx.settingwidget.widget.ALADSwitch")) {
            return modelHelper.hasALAD();
        }
        if (componentName.getClassName().equals("com.neusoft.ecarx.settingwidget.widget.SLIFSwitch")) {
            return modelHelper.hasSLIF();
        }
        if (componentName.getClassName().equals("com.neusoft.ecarx.settingwidget.widget.RCTASwitch")) {
            return modelHelper.hasRCTA();
        }
        if (componentName.getClassName().equals("com.neusoft.ecarx.settingwidget.widget.LCASwitch")) {
            return modelHelper.hasLCA();
        }
        if (componentName.getClassName().equals("com.neusoft.ecarx.settingwidget.widget.DOWSwitch")) {
            return modelHelper.hasDOW();
        }
        if (componentName.getClassName().equals("com.neusoft.ecarx.settingwidget.widget.RCWSwitch")) {
            return modelHelper.hasRCW();
        }
        if (componentName.getClassName().equals("com.neusoft.ecarx.settingwidget.widget.ALODSwitch")) {
            return modelHelper.hasALOD();
        }
        if (componentName.getClassName().equals("com.neusoft.ecarx.settingwidget.widget.StopStartSwitch")) {
            return modelHelper.hasStopStart();
        }
        if (componentName.getClassName().equals("com.neusoft.ecarx.settingwidget.widget.PBVolumeMaintenanceSwitch")) {
            return modelHelper.hasPBVolumeMaintenanceSwitch();
        }
        if (componentName.getClassName().equals("com.neusoft.ecarx.settingwidget.widget.PBChargingSwitch")) {
            return modelHelper.hasPBChargingSwitch();
        }
        if (componentName.getClassName().equals("com.neusoft.ecarx.settingwidget.widget.PDCSwitch")) {
            return modelHelper.hasPDC();
        }
        if (componentName.getClassName().equals("com.neusoft.ecarx.settingwidget.widget.AUTOHOLDSwitch")) {
            return modelHelper.hasAUTOHOLD();
        }
        if (componentName.getClassName().equals("com.neusoft.ecarx.settingwidget.widget.HDCSwitch")) {
            return modelHelper.hasHDC();
        }
        return false;
    }

    public boolean registerDayNightModeCallBack(IDayNightMode.IDayNightChangeCallBack iDayNightChangeCallBack) {
        Log.d(TAG, "registerDayNightModeCallBack");
        this.iDayNightChangeCallBack = iDayNightChangeCallBack;
        this.mDayNightModeReceiver.registerDayNightModeCallBack();
        return true;
    }

    public boolean unregisterDayNigntModeCallBack(IDayNightMode.IDayNightChangeCallBack iDayNightChangeCallBack) {
        Log.d(TAG, "unregisterDayNigntModeCallBack");
        this.iDayNightChangeCallBack = iDayNightChangeCallBack;
        this.mDayNightModeReceiver.unregisterDayNigntModeCallBack();
        return true;
    }
}
