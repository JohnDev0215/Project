package com.ecarx.xui.adaptapi.vehicle.bcm;

import android.content.Context;
import android.content.Intent;
import com.ecarx.xui.adaptapi.FunctionStatus;
import com.ecarx.xui.adaptapi.Tribool;
import com.ecarx.xui.adaptapi.vehicle.ModelHelper;
import com.ecarx.xui.adaptapi.vehicle.bcm.ILighting;
import com.neusoft.alfus.RpcManager;
import com.neusoft.optimus.utility.log.LogTool;
import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class LightingImpl implements ILighting {
    public static final String TAG = "LightingImpl";
    public String ETC_CALLBACK = "neusoft.etcLight.change";
    private RpcManager.OnRpcListener _onCallback = new RpcManager.OnRpcListener() { // from class: com.ecarx.xui.adaptapi.vehicle.bcm.LightingImpl.1
        public void onCallback(int i, byte[] bArr) {
            if (i == 1835 && bArr[0] == 6) {
                LogTool.d(LightingImpl.TAG, "_onCallback  bytes: " + Arrays.toString(bArr));
                if (bArr[6] == 1) {
                    LogTool.d(LightingImpl.TAG, "onLightingReminder ILighting.DEVICE_MAIN_BEAM");
                    LightingImpl.this.mLightingListener.onLightingReminder(2);
                }
            }
        }
    };
    public Context mContext;
    private ILighting.LightingStatusListener mLightingListener;

    private boolean getAccState() {
        return true;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.bcm.ILighting
    public int getLightDeviceBrightness(int i) {
        return 0;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.bcm.ILighting
    public int getLightDeviceMaxBrightness(int i) {
        return 0;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.bcm.ILighting
    public int getLightDeviceMinBrightness(int i) {
        return 0;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.bcm.ILighting
    public int getTurnSignalLampEffective() {
        return 0;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.bcm.ILighting
    public FunctionStatus isFunctionSupported(int i, int i2) {
        return null;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.bcm.ILighting
    public boolean setLightDeviceBrightness(int i, int i2) {
        return false;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.bcm.ILighting
    public void setTurnSignalLampEffective(int i) {
    }

    public LightingImpl(Context context) {
        this.mContext = context;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.bcm.ILighting
    public FunctionStatus isLightDeviceSupported(int i) {
        LogTool.d(TAG, "isLightDeviceSupported() device " + i);
        if (16 == i) {
            return FunctionStatus.notavailable;
        }
        return null;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.bcm.ILighting
    public FunctionStatus isLightDeviceSupported(int i, int i2) {
        LogTool.d(TAG, "device = " + i + " operation = " + i2 + " hasEtc = " + ModelHelper.getInstance(this.mContext).hasEtc());
        if (16 == i) {
            return FunctionStatus.notavailable;
        }
        return FunctionStatus.active;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.bcm.ILighting
    public FunctionStatus isFunctionSupported(int i) {
        LogTool.d(TAG, "isFunctionSupported function=" + i);
        FunctionStatus functionStatus = FunctionStatus.notavailable;
        if (i != 1) {
            if (i != 2) {
                if (i == 16) {
                    functionStatus = FunctionStatus.notavailable;
                }
            } else if (ModelHelper.getInstance(this.mContext).hasFollowMeHomeTimeSelect()) {
                functionStatus = FunctionStatus.active;
            } else if (getAccState()) {
                functionStatus = FunctionStatus.notavailable;
            } else {
                functionStatus = FunctionStatus.notactive;
            }
        } else if (ModelHelper.getInstance(this.mContext).hasFlasherAnimControlSet()) {
            functionStatus = FunctionStatus.active;
        } else if (getAccState()) {
            functionStatus = FunctionStatus.notavailable;
        } else {
            functionStatus = FunctionStatus.notactive;
        }
        LogTool.d(TAG, " isFunctionSupported functionStatus =" + functionStatus);
        return functionStatus;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.bcm.ILighting
    public int getFollowMeHomeTimeSetting() {
        LogTool.d(TAG, "getFollowMeHomeTimeSetting");
        byte[] bArr = new byte[25];
        bArr[0] = 4;
        byte[] message = RpcManager.getInstance().getMessage(1881, bArr);
        if (message == null || message.length == 0) {
            return 0;
        }
        LogTool.d(TAG, "getFollowMe state. dataOut: " + Arrays.toString(message));
        byte b = message[4];
        if (b == 0) {
            return 1;
        }
        if (b != 1) {
            return b != 2 ? 0 : 3;
        }
        return 2;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.bcm.ILighting
    public void setFollowMeHomeTime(int i) {
        LogTool.d(TAG, "%s setFollowMeHomeTime time:--" + i);
        int i2 = 1;
        if (i != 1) {
            i2 = 2;
            if (i != 2) {
                i2 = 3;
                if (i != 3) {
                    i2 = 0;
                }
            }
        }
        setOpeCode(1884, 52, i2);
    }

    private void setOpeCode(int i, int i2, int i3) {
        byte[] bArr = new byte[25];
        bArr[0] = (byte) i2;
        bArr[1] = (byte) i3;
        LogTool.d(TAG, "%s setInfo" + Arrays.toString(bArr));
        RpcManager.getInstance().sendMessage(i, bArr);
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.bcm.ILighting
    public Tribool isLightingOrSignallingDeviceOn(int i) {
        if (11 == i && isPositionLightOpen()) {
            return Tribool.yes;
        }
        if (16 == i && getEtcLight() != 0) {
            return Tribool.yes;
        }
        return Tribool.no;
    }

    public boolean isPositionLightOpen() {
        byte[] bArr = new byte[25];
        bArr[0] = 2;
        byte[] message = RpcManager.getInstance().getMessage(1881, bArr);
        LogTool.d(TAG, "positionLightStsData: " + Arrays.toString(message));
        if (message != null) {
            byte b = message[9];
            if (b == 0) {
                return false;
            }
            if (b == 1) {
                return true;
            }
            LogTool.d(TAG, "getPositionLightStsData() state is  " + ((int) b));
        } else {
            LogTool.d(TAG, "getPositionLightStsData() positionLightStsData is null. ");
        }
        return false;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.bcm.ILighting
    public boolean setLightDeviceOnOff(int i, boolean z) {
        boolean etcLight;
        LogTool.d(TAG, "setLightDeviceOnOff() device is " + i + " on " + z);
        int i2 = 0;
        if (16 != i) {
            return false;
        }
        if (!ModelHelper.getInstance(this.mContext).hasEtc()) {
            LogTool.d(TAG, "Not etc ,do nothing ");
            return false;
        }
        int etcLight2 = getEtcLight();
        LogTool.d(TAG, "setLightDeviceOnOff() currentLight is " + etcLight2);
        if (!z) {
            etcLight = setEtcLight(0);
            LogTool.d(TAG, "setLightDeviceOnOff() set ETC light 0, result " + etcLight);
        } else if (etcLight2 > 0) {
            LogTool.d(TAG, "setLightDeviceOnOff() ETC is opened ,do nothing.");
            etcLight = true;
            i2 = etcLight2;
        } else {
            i2 = 5;
            etcLight = setEtcLight(5);
            LogTool.d(TAG, "setLightDeviceOnOff() set ETC light 5, result " + etcLight);
        }
        if (etcLight) {
            Intent intent = new Intent();
            intent.setAction(this.ETC_CALLBACK);
            intent.putExtra("currentLight", i2);
            this.mContext.sendBroadcast(intent);
        }
        return etcLight;
    }

    public static boolean setEtcLight(int i) {
        byte[] bArr = new byte[25];
        bArr[0] = 0;
        bArr[1] = 2;
        bArr[2] = (byte) i;
        boolean zSendMessage = RpcManager.getInstance().sendMessage(1638, bArr);
        LogTool.d(TAG, "setEtcLight() result " + zSendMessage + " array: " + Arrays.toString(bArr));
        return zSendMessage;
    }

    public static int getEtcLight() {
        byte[] bArr = new byte[25];
        bArr[0] = 0;
        byte[] message = RpcManager.getInstance().getMessage(1648, bArr);
        LogTool.d(TAG, "getEtcLight() array: " + Arrays.toString(bArr));
        if (message == null) {
            return -1;
        }
        LogTool.d(TAG, "getEtcLight = " + Arrays.toString(message));
        return message[10];
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.bcm.ILighting
    public void attachLightingAndSignallingDeviceStatusListener(ILighting.LightingStatusListener lightingStatusListener, int[] iArr) {
        LogTool.d(TAG, "attachLightingAndSignallingDeviceStatusListener");
        for (int i : iArr) {
            if (i == 2) {
                LogTool.d(TAG, "attachLightingAndSignallingDeviceStatusListener ILighting.DEVICE_MAIN_BEAM");
                this.mLightingListener = lightingStatusListener;
                RpcManager.getInstance().registerListener(1835, (byte) 6, this._onCallback);
            }
        }
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.bcm.ILighting
    public void detachLightingAndSignallingDeviceStatusListener(ILighting.LightingStatusListener lightingStatusListener) {
        LogTool.d(TAG, "detachLightingAndSignallingDeviceStatusListener");
        RpcManager.getInstance().unregisterListener(1835, (byte) 6, this._onCallback);
    }

    public byte getPositionLightSts() {
        byte[] bArr = new byte[25];
        bArr[0] = 2;
        byte[] message = RpcManager.getInstance().getMessage(1881, bArr);
        LogTool.d(TAG, "getPositionLightSts() array: " + Arrays.toString(bArr));
        if (message != null) {
            LogTool.d(TAG, "getPositionLightSts = " + Arrays.toString(message));
            return message[9];
        }
        LogTool.d(TAG, "getPositionLightSts datas is null,return -1.");
        return (byte) -1;
    }
}
