package com.ecarx.xui.adaptapi.device.impl;

import android.app.UiModeManager;
import android.content.ComponentName;
import android.content.Context;
import com.ecarx.xui.adaptapi.device.Device;
import com.ecarx.xui.adaptapi.device.ads.AdvertiseImpl;
import com.ecarx.xui.adaptapi.device.ads.IAdvertise;
import com.ecarx.xui.adaptapi.device.daynigntmode.IDayNightMode;
import com.ecarx.xui.adaptapi.device.log.DeviceLog;
import com.ecarx.xui.adaptapi.device.log.DeviceLogImpl;
import com.ecarx.xui.adaptapi.device.specific.DeviceBean;
import com.ecarx.xui.adaptapi.device.storage.IStorage;
import com.ecarx.xui.adaptapi.hvac.specific.Define;
import com.neusoft.alfus.RpcManager;
import com.neusoft.optimus.utility.log.LogTool;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class DeviceImpl extends Device {
    private Context mContext;
    private DeviceBean mDeviceBean;
    private UiModeManager mUiModeManager;
    private byte[] f101bytes = null;
    private byte[] f110bytes = null;
    private final byte VehicleSubOpeCode = 37;
    private final int OpeCode = 1562;
    private final byte SubOpeCode = 21;

    @Override // com.ecarx.xui.adaptapi.device.Device
    public int getMcuSoftwareVersionInt() {
        return 0;
    }

    @Override // com.ecarx.xui.adaptapi.device.Device
    public String getMpuHardwareVersion() {
        return "";
    }

    @Override // com.ecarx.xui.adaptapi.device.Device
    public String getMpuSoftwareVersion() {
        return "";
    }

    @Override // com.ecarx.xui.adaptapi.device.Device
    public int getOperatorCode() {
        return 1;
    }

    @Override // com.ecarx.xui.adaptapi.device.Device
    public String getOperatorName() {
        return "GEELY";
    }

    @Override // com.ecarx.xui.adaptapi.device.Device
    public String getVehicleCountryCode() {
        return null;
    }

    @Override // com.ecarx.xui.adaptapi.device.Device
    public boolean isMobileNetworkConfigured() {
        return true;
    }

    @Override // com.ecarx.xui.adaptapi.device.Device
    public boolean isTboxConfigured() {
        return false;
    }

    public DeviceImpl(Context context) {
        LogTool.d("DeviceImpl", "DeviceImpl create");
        this.mContext = context;
        this.mDeviceBean = new DeviceBean(this.mContext);
        this.mUiModeManager = (UiModeManager) context.getSystemService("uimode");
    }

    @Override // com.ecarx.xui.adaptapi.device.Device
    public String getIhuId() {
        return this.mDeviceBean.getIhuId();
    }

    @Override // com.ecarx.xui.adaptapi.device.Device
    public String getSerialNo() {
        return this.mDeviceBean.getIhuId();
    }

    @Override // com.ecarx.xui.adaptapi.device.Device
    public String getPartNo() {
        String ihuId = this.mDeviceBean.getIhuId();
        if (ihuId == null || "".equals(ihuId)) {
            return null;
        }
        LogTool.i("deviceImpl", "getPartNo =" + ihuId.substring(6, 16));
        return ihuId.substring(6, 16);
    }

    @Override // com.ecarx.xui.adaptapi.device.Device
    public String getIHUHardwareVersion() {
        byte[] bArr = new byte[25];
        bArr[0] = Define.cSubCodeSetFraganceSwitch;
        byte[] message = RpcManager.getInstance().getMessage(1562, bArr);
        LogTool.d("DeviceImpl", "%s hardware data", Arrays.toString(message));
        if (message == null) {
            return "";
        }
        String strSubstring = new String(message).substring(1);
        LogTool.d("DeviceImpl", "getHardwareVersion() vName " + strSubstring);
        return strSubstring.trim();
    }

    @Override // com.ecarx.xui.adaptapi.device.Device
    public String getXdsn() {
        return this.mDeviceBean.getXdsn();
    }

    @Override // com.ecarx.xui.adaptapi.device.Device
    public String getVin() {
        return this.mDeviceBean.getVin();
    }

    @Override // com.ecarx.xui.adaptapi.device.Device
    public String getMcuSoftwareVersion() {
        return this.mDeviceBean.getMcuSoftwareVersion();
    }

    @Override // com.ecarx.xui.adaptapi.device.Device
    public String getMcuHardwareVersion() {
        return this.mDeviceBean.getMcuHardwareVersion();
    }

    @Override // com.ecarx.xui.adaptapi.device.Device
    public String getVehicleType() {
        return this.mDeviceBean.getVehicleType();
    }

    @Override // com.ecarx.xui.adaptapi.device.Device
    public String getProjectCode() {
        return this.mDeviceBean.getProjectCode();
    }

    @Override // com.ecarx.xui.adaptapi.device.Device
    public String getSupplierCode() {
        return this.mDeviceBean.getSupplierCode();
    }

    @Override // com.ecarx.xui.adaptapi.device.Device
    public boolean isRearViewCameraConfigured() {
        return this.mDeviceBean.isRearViewCameraConfigured();
    }

    @Override // com.ecarx.xui.adaptapi.device.Device
    public boolean isDVRCameraConfigured() {
        return this.mDeviceBean.isDVRCameraConfigured();
    }

    @Override // com.ecarx.xui.adaptapi.device.Device
    public IDayNightMode getDayNightMode() {
        return this.mDeviceBean.getDayNightMode();
    }

    @Override // com.ecarx.xui.adaptapi.device.Device
    public boolean setDayNightMode(int i) {
        if (i == 3) {
            i = 0;
        }
        this.mUiModeManager.enableCarMode(0);
        this.mUiModeManager.setNightMode(i);
        return false;
    }

    @Override // com.ecarx.xui.adaptapi.device.Device
    public List<ComponentName> getSupportedComponents() {
        return this.mDeviceBean.getSupportedComponents();
    }

    @Override // com.ecarx.xui.adaptapi.device.Device
    public int getMinScreenBrightness() {
        return this.mDeviceBean.getMinScreenBrightness();
    }

    @Override // com.ecarx.xui.adaptapi.device.Device
    public int getMaxScreenBrightness() {
        return this.mDeviceBean.getMaxScreenBrightness();
    }

    @Override // com.ecarx.xui.adaptapi.device.Device
    public DeviceLog getDeviceLog() {
        return new DeviceLogImpl();
    }

    @Override // com.ecarx.xui.adaptapi.device.Device
    public IStorage getStorage() {
        return new StorageImpl();
    }

    @Override // com.ecarx.xui.adaptapi.device.Device
    public IAdvertise getAdvertise() {
        return new AdvertiseImpl();
    }

    @Override // com.ecarx.xui.adaptapi.device.Device
    public String getRolloffConfigCode() {
        if (this.f101bytes == null) {
            byte[] bArr = new byte[25];
            bArr[0] = 21;
            this.f101bytes = RpcManager.getInstance().getMessage(1562, bArr);
            LogTool.i("deviceImpl", "request 101 Code:" + Arrays.toString(this.f101bytes));
            if (this.f101bytes == null) {
                LogTool.i("deviceImpl", "request 101 Code, bug i get null");
            }
        }
        if (this.f110bytes == null) {
            byte[] bArr2 = new byte[25];
            bArr2[0] = 37;
            this.f110bytes = RpcManager.getInstance().getMessage(1562, bArr2);
            LogTool.i("deviceImpl", "request 110 Code" + Arrays.toString(this.f110bytes));
            if (this.f110bytes == null) {
                LogTool.i("deviceImpl", "request 110 Code, bug i get null");
            }
        }
        if (this.f101bytes != null && this.f110bytes != null) {
            return Arrays.toString(this.f101bytes) + Arrays.toString(this.f110bytes);
        }
        LogTool.e("deviceImpl", "getRolloffConfigCode f101 or f110 is NULL!!!");
        return "";
    }
}
