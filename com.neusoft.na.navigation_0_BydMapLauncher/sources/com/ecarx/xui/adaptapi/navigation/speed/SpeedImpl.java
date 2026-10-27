package com.ecarx.xui.adaptapi.navigation.speed;

import com.neusoft.alfus.RpcManager;
import com.neusoft.optimus.utility.log.LogTool;
import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class SpeedImpl implements ISpeed {
    public static final String TAG = "naviSpeed";
    private ISpeedCallback mSpeedCallback;
    private RpcManager.OnRpcListener _onCallbackFcs = new RpcManager.OnRpcListener() { // from class: com.ecarx.xui.adaptapi.navigation.speed.SpeedImpl.1
        public void onCallback(int i, byte[] bArr) {
            if (i == 1822 && bArr[0] == 4) {
                LogTool.d(SpeedImpl.TAG, "_onCallback  bytes: " + Arrays.toString(bArr));
                if (SpeedImpl.this.mSpeedCallback != null) {
                    if (bArr[4] != 0) {
                        SpeedImpl.this.mSpeedCallback.onSpeedLimitEnableChanged(true);
                    } else {
                        SpeedImpl.this.mSpeedCallback.onSpeedLimitEnableChanged(false);
                    }
                    if (bArr[3] != 0) {
                        SpeedImpl.this.mSpeedCallback.onTsrSpeedLimit(bArr[3]);
                    }
                }
            }
        }
    };
    private RpcManager.OnRpcListener _onCallback = new RpcManager.OnRpcListener() { // from class: com.ecarx.xui.adaptapi.navigation.speed.SpeedImpl.2
        public void onCallback(int i, byte[] bArr) {
            if (i == 1835 && bArr[0] == 6) {
                LogTool.d(SpeedImpl.TAG, "_onCallback  bytes: " + Arrays.toString(bArr));
                if (bArr[5] != 1 || SpeedImpl.this.mSpeedCallback == null) {
                    return;
                }
                SpeedImpl.this.mSpeedCallback.onSpeedDownReminder(1);
            }
        }
    };

    @Override // com.ecarx.xui.adaptapi.navigation.speed.ISpeed
    public boolean isSpeedLimitEnabled() {
        byte[] bArr = new byte[25];
        bArr[0] = 5;
        byte[] message = RpcManager.getInstance().getMessage(1881, bArr);
        LogTool.d(TAG, "isSpeedLimitEnabled = " + Arrays.toString(message));
        return getRpcOK(message, 7) && message[7] != 0;
    }

    @Override // com.ecarx.xui.adaptapi.navigation.speed.ISpeed
    public void setSpeedLimitingInfo(int i, ISpeedLimitingInfo iSpeedLimitingInfo) {
        if (i == 1073741826) {
            int speed = iSpeedLimitingInfo.getElecLimitingInfo().getSpeed();
            int dist = iSpeedLimitingInfo.getElecLimitingInfo().getDist();
            LogTool.d(TAG, "setSpeedLimitingInfo limited speed:" + speed + "  distance:" + dist + " Longitude：" + iSpeedLimitingInfo.getElecLimitingInfo().getLong() + " latitude：" + iSpeedLimitingInfo.getElecLimitingInfo().getLat());
            setLimitSpeed(speed, dist);
            return;
        }
        if (i == 1073741827) {
            int roadClass = iSpeedLimitingInfo.getRoadInfo().getRoadClass();
            LogTool.d(TAG, "setSpeedLimitingInfo roadClass:" + roadClass);
            setRoadType(roadClass);
        } else if (i == 1073741835) {
            int dist2 = iSpeedLimitingInfo.getJctWayInfo().getDist();
            LogTool.d(TAG, "setSpeedLimitingInfo JctWay distance:" + dist2);
            setMMI_Nav_RampSts(dist2);
        }
    }

    @Override // com.ecarx.xui.adaptapi.navigation.speed.ISpeed
    public void registerSpeedCallback(ISpeedCallback iSpeedCallback) {
        if (iSpeedCallback == null) {
            LogTool.d(TAG, "registerSpeedCallback ISpeedCallback = null");
            return;
        }
        this.mSpeedCallback = iSpeedCallback;
        RpcManager.getInstance().registerListener(1835, (byte) 6, this._onCallback);
        RpcManager.getInstance().registerListener(1822, (byte) 4, this._onCallbackFcs);
        LogTool.d(TAG, "registerSpeedCallback");
    }

    @Override // com.ecarx.xui.adaptapi.navigation.speed.ISpeed
    public void unregisterSpeedCallback(ISpeedCallback iSpeedCallback) {
        LogTool.d(TAG, "unregisterSpeedCallback");
        RpcManager.getInstance().unregisterListener(1835, (byte) 6, this._onCallback);
        RpcManager.getInstance().unregisterListener(1822, (byte) 4, this._onCallbackFcs);
        this.mSpeedCallback = null;
    }

    private boolean getRpcOK(byte[] bArr, int i) {
        if (bArr != null && bArr.length > i) {
            return true;
        }
        LogTool.d(TAG, "RPC not get");
        return false;
    }

    private boolean callBackIndexOut(byte[] bArr, int i) {
        return bArr.length > i;
    }

    public void setLimitSpeed(int i, int i2) {
        byte[] bArr = new byte[25];
        bArr[0] = 12;
        if (i2 == -1) {
            bArr[1] = 0;
        } else {
            bArr[1] = (byte) i;
        }
        setCountryID();
        RpcManager.getInstance().sendMessage(1883, bArr);
    }

    public void setNavStatus(int i) {
        byte[] bArr = new byte[25];
        bArr[0] = 17;
        if (i == -1) {
            bArr[1] = 1;
        } else {
            bArr[1] = 0;
        }
        RpcManager.getInstance().sendMessage(1883, bArr);
    }

    public void setRoadType(int i) {
        byte[] bArr = new byte[25];
        bArr[0] = 14;
        setNavStatus(i);
        switch (i) {
            case 0:
                bArr[1] = 1;
                break;
            case 1:
                bArr[1] = 3;
                break;
            case 2:
                bArr[1] = 4;
                break;
            case 3:
            case 7:
            case 9:
                bArr[1] = 5;
                break;
            case 4:
            case 5:
            case 8:
                bArr[1] = 6;
                break;
            case 6:
                bArr[1] = 2;
                break;
            default:
                bArr[1] = 0;
                LogTool.d(TAG, "The road type :" + i);
                break;
        }
        setCountryID();
        RpcManager.getInstance().sendMessage(1883, bArr);
    }

    public void setCountryID() {
        byte[] bArr = new byte[25];
        bArr[0] = 16;
        bArr[1] = 1;
        RpcManager.getInstance().sendMessage(1883, bArr);
    }

    private void setMMI_Nav_RampSts(int i) {
        byte[] bArr = new byte[25];
        bArr[0] = 13;
        if (i == -1) {
            bArr[1] = 0;
        } else if (i <= 200) {
            bArr[1] = 3;
        } else if (i <= 500) {
            bArr[1] = 2;
        } else if (i <= 1000) {
            bArr[1] = 1;
        } else {
            bArr[1] = 0;
        }
        setCountryID();
        RpcManager.getInstance().sendMessage(1883, bArr);
    }
}
