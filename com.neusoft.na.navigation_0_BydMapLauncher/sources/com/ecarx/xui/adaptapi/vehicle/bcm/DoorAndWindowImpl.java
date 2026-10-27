package com.ecarx.xui.adaptapi.vehicle.bcm;

import android.content.Context;
import android.util.Log;
import com.ecarx.xui.adaptapi.FunctionStatus;
import com.ecarx.xui.adaptapi.Tribool;
import com.ecarx.xui.adaptapi.hvac.specific.Define;
import com.ecarx.xui.adaptapi.vehicle.bcm.IDoorAndWindow;
import com.neusoft.alfus.RpcManager;
import com.neusoft.optimus.utility.log.LogTool;
import com.neusoft.optimus.utility.offlineconfig.OfflineConfig;
import java.util.Arrays;
import kotlin.jvm.internal.ByteCompanionObject;

/* JADX INFO: loaded from: classes2.dex */
public class DoorAndWindowImpl implements IDoorAndWindow {
    public static final int ACTION_DOWN = 1;
    public static final int ACTION_GET_KEY_MSG = 2;
    public static final int ACTION_SET_KEY_MSG = 1;
    public static final int ACTION_UP = 0;
    public static final int ANTIPINCH_TYPE_FOURWINDOW = 1;
    public static final int ANTIPINCH_TYPE_FRONTLEFTWINDOW = 2;
    public static final int ANTIPINCH_TYPE_NONE = 0;
    public static final int EVENT_INPUT = 5;
    public static final int FL_WINDOW = 3;
    public static final int FR_WINDOW = 4;
    public static final int GETRPCOPECODE = 1881;
    public static final int KEYCODE = 100;
    public static final int OFFLINE_ROLLSHADE = 2;
    public static final int OFFLINE_SUNROOF = 1;
    public static final int Panoramic_Sunroof = 1;
    public static final int RL_WINDOW = 5;
    public static final int RPC_DATA_LEN = 25;
    public static final int RR_WINDOW = 6;
    public static final int SETRPCOPECODE = 1882;
    public static final int SUNROOF = 7;
    public static final int Small_Sunroof = 2;
    private static final String TAG = "DoorAndWindowImpl";
    public static final int no_Sunroof = 0;
    public Context mContext;
    private IDoorAndWindow.WindowStatusListener mWindowStatusListener;
    final int cGetRpcOpeCode = 1881;
    private RpcManager.OnRpcListener _onCallback = new RpcManager.OnRpcListener() { // from class: com.ecarx.xui.adaptapi.vehicle.bcm.DoorAndWindowImpl.1
        public void onCallback(int i, byte[] bArr) {
            if (i == 1820 && bArr[0] == 11) {
                Log.d(DoorAndWindowImpl.TAG, "Rpc onCallBack");
                Log.d(DoorAndWindowImpl.TAG, "_onCallback  bytes = " + Arrays.toString(bArr));
                byte b = bArr[4];
                byte b2 = bArr[5];
                byte b3 = bArr[12];
                byte b4 = bArr[13];
                byte b5 = bArr[7];
                if (DoorAndWindowImpl.this.mWindowStatusListener != null) {
                    DoorAndWindowImpl.this.mWindowStatusListener.onWindowOpenStatus(16, b);
                    DoorAndWindowImpl.this.mWindowStatusListener.onWindowOpenStatus(32, b2);
                    DoorAndWindowImpl.this.mWindowStatusListener.onWindowOpenStatus(256, b3);
                    DoorAndWindowImpl.this.mWindowStatusListener.onWindowOpenStatus(512, b4);
                    DoorAndWindowImpl.this.mWindowStatusListener.onWindowOpenStatus(4, b5);
                }
            }
        }
    };

    @Override // com.ecarx.xui.adaptapi.vehicle.bcm.IDoorAndWindow
    public void attachDoorStatusListener(IDoorAndWindow.DoorStatusListener doorStatusListener, int[] iArr) {
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.bcm.IDoorAndWindow
    public void attachSunroofWindowStatusListener(IDoorAndWindow.ISunroofWindowStatusListener iSunroofWindowStatusListener) {
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.bcm.IDoorAndWindow
    public void detachDoorStatusListener(IDoorAndWindow.DoorStatusListener doorStatusListener) {
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.bcm.IDoorAndWindow
    public void detachSunroofWindowStatusListener(IDoorAndWindow.ISunroofWindowStatusListener iSunroofWindowStatusListener) {
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.bcm.IDoorAndWindow
    public Tribool isDoorLocked(int i) {
        return null;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.bcm.IDoorAndWindow
    public boolean lockDoor(int i, boolean z) {
        return false;
    }

    public DoorAndWindowImpl(Context context) {
        this.mContext = context;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.bcm.IDoorAndWindow
    public Tribool isWindowOpened(int i) {
        Log.d(TAG, "isWindowOpened window = " + i);
        byte[] bArr = new byte[25];
        bArr[0] = 28;
        byte[] message = RpcManager.getInstance().getMessage(1881, bArr);
        Log.d(TAG, "isWindowOpened result = " + Arrays.toString(message));
        if (i == 4) {
            byte b = message[1];
            if (b == 1) {
                return Tribool.no;
            }
            if (b == 0) {
                return Tribool.indeterminate;
            }
            return Tribool.yes;
        }
        if (i == 16) {
            byte b2 = message[5];
            if (b2 == 2) {
                return Tribool.no;
            }
            if (b2 == 3) {
                return Tribool.indeterminate;
            }
            return Tribool.yes;
        }
        if (i == 32) {
            byte b3 = message[3];
            if (b3 == 2) {
                return Tribool.no;
            }
            if (b3 == 3) {
                return Tribool.indeterminate;
            }
            return Tribool.yes;
        }
        if (i == 256) {
            byte b4 = message[4];
            if (b4 == 2) {
                return Tribool.no;
            }
            if (b4 == 3) {
                return Tribool.indeterminate;
            }
            return Tribool.yes;
        }
        if (i == 512) {
            byte b5 = message[2];
            if (b5 == 2) {
                return Tribool.no;
            }
            if (b5 == 3) {
                return Tribool.indeterminate;
            }
            return Tribool.yes;
        }
        return Tribool.indeterminate;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.bcm.IDoorAndWindow
    public int getWindowStatus(int i) {
        Log.d(TAG, "getWindowStatus window = " + i);
        byte[] bArr = new byte[25];
        bArr[0] = 3;
        byte[] message = RpcManager.getInstance().getMessage(1881, bArr);
        Log.d(TAG, "getWindowStatus result = " + Arrays.toString(message));
        if (i == 4) {
            return message[16];
        }
        if (i == 16) {
            return message[14];
        }
        if (i == 32) {
            return message[15];
        }
        if (i == 256) {
            return message[17];
        }
        if (i != 512) {
            return -1;
        }
        return message[18];
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.bcm.IDoorAndWindow
    public boolean setWindowStatus(int i, int i2) {
        Log.d(TAG, "setWindowStatus window = " + i + ",percent = " + i2);
        int e01OfflineConfig = OfflineConfig.getE01OfflineConfig(156);
        boolean offlineConfig = getOfflineConfig(2);
        Log.d(TAG, "setWindowStatus sunroofType = " + e01OfflineConfig);
        byte[] bArr = new byte[25];
        switch (i) {
            case 4:
                if (e01OfflineConfig == 0 && !offlineConfig) {
                    return false;
                }
                if (e01OfflineConfig == 0 && offlineConfig) {
                    bArr[0] = 20;
                    bArr[1] = (byte) i2;
                    bArr[2] = 0;
                    bArr[3] = 1;
                } else if (e01OfflineConfig != 1 || !isSunroofAvailable()) {
                    if (e01OfflineConfig != 2 || !isSunroofAvailable()) {
                        return false;
                    }
                    if (i2 != 0) {
                        bArr[0] = 20;
                        bArr[1] = (byte) i2;
                        bArr[2] = 0;
                        bArr[3] = 0;
                    } else {
                        bArr[0] = 20;
                        bArr[1] = (byte) i2;
                        bArr[2] = 2;
                        bArr[3] = 0;
                    }
                } else if (i2 != 0) {
                    bArr[0] = 20;
                    bArr[1] = (byte) i2;
                    bArr[2] = 0;
                    bArr[3] = 0;
                } else {
                    bArr[0] = 20;
                    bArr[1] = ByteCompanionObject.MAX_VALUE;
                    bArr[2] = 2;
                    bArr[3] = 0;
                }
                break;
                break;
            case 8:
                if (!offlineConfig) {
                    return false;
                }
                bArr[0] = 20;
                bArr[1] = (byte) i2;
                bArr[2] = 0;
                bArr[3] = 1;
                break;
                break;
            case 16:
                if (!isWindowAvailable(3)) {
                    return false;
                }
                bArr[0] = 16;
                bArr[1] = (byte) i2;
                bArr[2] = ByteCompanionObject.MAX_VALUE;
                bArr[3] = ByteCompanionObject.MAX_VALUE;
                bArr[4] = ByteCompanionObject.MAX_VALUE;
                break;
                break;
            case 32:
                if (!isWindowAvailable(4)) {
                    return false;
                }
                bArr[0] = 16;
                bArr[1] = ByteCompanionObject.MAX_VALUE;
                bArr[2] = (byte) i2;
                bArr[3] = ByteCompanionObject.MAX_VALUE;
                bArr[4] = ByteCompanionObject.MAX_VALUE;
                break;
                break;
            case 48:
                if (!isWindowAvailable(3) || !isWindowAvailable(4)) {
                    return false;
                }
                bArr[0] = 16;
                byte b = (byte) i2;
                bArr[1] = b;
                bArr[2] = b;
                bArr[3] = ByteCompanionObject.MAX_VALUE;
                bArr[4] = ByteCompanionObject.MAX_VALUE;
                break;
                break;
            case 256:
                if (!isWindowAvailable(5)) {
                    return false;
                }
                bArr[0] = 16;
                bArr[1] = ByteCompanionObject.MAX_VALUE;
                bArr[2] = ByteCompanionObject.MAX_VALUE;
                bArr[3] = (byte) i2;
                bArr[4] = ByteCompanionObject.MAX_VALUE;
                break;
                break;
            case 272:
                if (!isWindowAvailable(3) || !isWindowAvailable(5)) {
                    return false;
                }
                bArr[0] = 16;
                byte b2 = (byte) i2;
                bArr[1] = b2;
                bArr[2] = ByteCompanionObject.MAX_VALUE;
                bArr[3] = b2;
                bArr[4] = ByteCompanionObject.MAX_VALUE;
                break;
                break;
            case 512:
                if (!isWindowAvailable(6)) {
                    return false;
                }
                bArr[0] = 16;
                bArr[1] = ByteCompanionObject.MAX_VALUE;
                bArr[2] = ByteCompanionObject.MAX_VALUE;
                bArr[3] = ByteCompanionObject.MAX_VALUE;
                bArr[4] = (byte) i2;
                break;
                break;
            case 544:
                if (!isWindowAvailable(4) || !isWindowAvailable(6)) {
                    return false;
                }
                bArr[0] = 16;
                bArr[1] = ByteCompanionObject.MAX_VALUE;
                byte b3 = (byte) i2;
                bArr[2] = b3;
                bArr[3] = ByteCompanionObject.MAX_VALUE;
                bArr[4] = b3;
                break;
                break;
            case 768:
                if (!isWindowAvailable(5) || !isWindowAvailable(6)) {
                    return false;
                }
                bArr[0] = 16;
                bArr[1] = ByteCompanionObject.MAX_VALUE;
                bArr[2] = ByteCompanionObject.MAX_VALUE;
                byte b4 = (byte) i2;
                bArr[3] = b4;
                bArr[4] = b4;
                break;
                break;
            case 816:
                if (!isWindowAvailable(3) || !isWindowAvailable(4) || !isWindowAvailable(5) || !isWindowAvailable(6)) {
                    return false;
                }
                bArr[0] = 16;
                byte b5 = (byte) i2;
                bArr[1] = b5;
                bArr[2] = b5;
                bArr[3] = b5;
                bArr[4] = b5;
                break;
                break;
            default:
                return false;
        }
        Log.d(TAG, "setWindowStatus dataIn = " + Arrays.toString(bArr));
        RpcManager.getInstance().sendMessage(1884, bArr);
        return true;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.bcm.IDoorAndWindow
    public boolean setWindowRolling(boolean z) {
        if (z) {
            return false;
        }
        byte[] bArr = new byte[25];
        bArr[0] = 16;
        bArr[1] = ByteCompanionObject.MAX_VALUE;
        bArr[2] = ByteCompanionObject.MAX_VALUE;
        bArr[3] = ByteCompanionObject.MAX_VALUE;
        bArr[4] = ByteCompanionObject.MAX_VALUE;
        bArr[5] = 1;
        RpcManager.getInstance().sendMessage(1884, bArr);
        Log.d(TAG, "setWindowRolling dataIn = " + Arrays.toString(bArr));
        return true;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.bcm.IDoorAndWindow
    public boolean setWindowRolling(int i, boolean z) {
        if (z) {
            return false;
        }
        byte[] bArr = new byte[25];
        bArr[0] = 16;
        bArr[1] = ByteCompanionObject.MAX_VALUE;
        bArr[2] = ByteCompanionObject.MAX_VALUE;
        bArr[3] = ByteCompanionObject.MAX_VALUE;
        bArr[4] = ByteCompanionObject.MAX_VALUE;
        bArr[5] = 1;
        RpcManager.getInstance().sendMessage(1884, bArr);
        Log.d(TAG, "setWindowRolling WindowType = " + i + "dataIn = " + Arrays.toString(bArr));
        return true;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.bcm.IDoorAndWindow
    public boolean tiltSunroofWindow(boolean z) {
        Log.d(TAG, "tiltSunroofWindow switch = " + z);
        int e01OfflineConfig = OfflineConfig.getE01OfflineConfig(156);
        Log.d(TAG, "tiltSunroofWindow getE01OfflineConfig Sunroof = " + e01OfflineConfig);
        getOfflineConfig(2);
        if (!(e01OfflineConfig != 0) || !isSunroofAvailable()) {
            return false;
        }
        byte[] bArr = new byte[25];
        bArr[0] = 20;
        if (e01OfflineConfig != 1) {
            bArr[1] = 0;
            if (z) {
                bArr[2] = 1;
            } else {
                bArr[2] = 2;
            }
        } else if (z) {
            bArr[1] = 0;
            bArr[2] = 1;
        } else {
            bArr[1] = ByteCompanionObject.MAX_VALUE;
            bArr[2] = 2;
        }
        bArr[3] = 0;
        Log.d(TAG, "tiltSunroofWindow dataIn = " + Arrays.toString(bArr));
        RpcManager.getInstance().sendMessage(1884, bArr);
        return true;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.bcm.IDoorAndWindow
    public void attachWindowStatusListener(IDoorAndWindow.WindowStatusListener windowStatusListener, int[] iArr) {
        Log.d(TAG, "attachWindowStatusListener");
        this.mWindowStatusListener = windowStatusListener;
        RpcManager.getInstance().registerListener(1820, (byte) 11, this._onCallback);
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.bcm.IDoorAndWindow
    public void detachWindowStatusListener(IDoorAndWindow.WindowStatusListener windowStatusListener) {
        Log.d(TAG, "detachWindowStatusListener");
        RpcManager.getInstance().unregisterListener(1820, (byte) 11, this._onCallback);
        this.mWindowStatusListener = null;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.bcm.IDoorAndWindow
    public FunctionStatus isWindowOperationSupported(int i, int i2) {
        Log.d(TAG, "isWindowOperationSupported window = " + i + ", operation = " + i2);
        if (i2 != 2) {
            return FunctionStatus.notavailable;
        }
        if (i == 4) {
            int e01OfflineConfig = OfflineConfig.getE01OfflineConfig(156);
            Log.d(TAG, "getE01OfflineConfig Sunroof result = " + e01OfflineConfig);
            boolean offlineConfig = getOfflineConfig(2);
            boolean z = e01OfflineConfig != 0;
            Log.d(TAG, "isWindowOperationSupported hasSunroof = " + z);
            if (z && isSunroofAvailable()) {
                return FunctionStatus.active;
            }
            if (!z && !offlineConfig) {
                return FunctionStatus.notavailable;
            }
            if (!z && offlineConfig) {
                return FunctionStatus.active;
            }
            if (!isSunroofAvailable()) {
                return FunctionStatus.notactive;
            }
            return FunctionStatus.notavailable;
        }
        if (i == 8) {
            boolean offlineConfig2 = getOfflineConfig(2);
            Log.d(TAG, "isWindowOperationSupported hasRoll = " + offlineConfig2);
            if (offlineConfig2) {
                return FunctionStatus.active;
            }
            return FunctionStatus.notavailable;
        }
        if (i == 16) {
            if (isWindowAvailable(3)) {
                return FunctionStatus.active;
            }
            return FunctionStatus.notactive;
        }
        if (i == 32) {
            if (isWindowAvailable(4)) {
                return FunctionStatus.active;
            }
            return FunctionStatus.notactive;
        }
        if (i == 256) {
            if (isWindowAvailable(5)) {
                return FunctionStatus.active;
            }
            return FunctionStatus.notactive;
        }
        if (i == 512) {
            if (isWindowAvailable(6)) {
                return FunctionStatus.active;
            }
            return FunctionStatus.notactive;
        }
        return FunctionStatus.notavailable;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.bcm.IDoorAndWindow
    public FunctionStatus isSunroofWindowTiltSupported(int i) {
        Log.d(TAG, "isSunroofWindowTiltSupported operation = " + i);
        if (i == 2) {
            boolean z = OfflineConfig.getE01OfflineConfig(156) != 0;
            Log.d(TAG, "isSunroofWindowTiltSupported hasSunroof = " + z);
            if (z && isSunroofAvailable()) {
                return FunctionStatus.active;
            }
            if (!z) {
                return FunctionStatus.notavailable;
            }
            if (!isSunroofAvailable()) {
                return FunctionStatus.notactive;
            }
            return FunctionStatus.notavailable;
        }
        return FunctionStatus.notavailable;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.bcm.IDoorAndWindow
    public int[] getSunroofWindowSupportedTiltStatus() {
        return new int[]{0, 2};
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.bcm.IDoorAndWindow
    public boolean setSunroofWindowTiltStatus(int i) {
        Log.d(TAG, "setSunroofWindowTiltStatus switch = " + i);
        int e01OfflineConfig = OfflineConfig.getE01OfflineConfig(156);
        Log.d(TAG, "tiltSunroofWindow getE01OfflineConfig Sunroof = " + e01OfflineConfig);
        boolean offlineConfig = getOfflineConfig(2);
        if (isSunroofAvailable() && i != 1) {
            byte[] bArr = new byte[25];
            bArr[0] = 20;
            if (e01OfflineConfig == 1) {
                if (i == 2) {
                    bArr[1] = 0;
                    bArr[2] = 1;
                } else {
                    bArr[1] = ByteCompanionObject.MAX_VALUE;
                    bArr[2] = 2;
                }
                bArr[3] = 0;
            } else if (e01OfflineConfig == 2) {
                bArr[1] = 0;
                if (i == 2) {
                    bArr[2] = 1;
                } else {
                    bArr[2] = 2;
                }
                bArr[3] = 0;
            } else if (e01OfflineConfig == 0 && offlineConfig) {
                bArr[1] = 0;
                bArr[2] = 0;
                bArr[3] = 1;
            }
            Log.d(TAG, "setSunroofWindowTiltStatus dataIn = " + Arrays.toString(bArr));
            RpcManager.getInstance().sendMessage(1884, bArr);
            return true;
        }
        return false;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.bcm.IDoorAndWindow
    public int getSunroofWindowTiltStatus() {
        byte[] bArr = new byte[25];
        int i = 0;
        bArr[0] = 28;
        byte[] message = RpcManager.getInstance().getMessage(1881, bArr);
        byte b = message[1];
        if (b == 4 || b == 2) {
            i = 2;
        } else if (b != 1) {
            i = -1;
        }
        Log.d(TAG, "getSunroofWindowTiltStatus result = " + i + ",windowValue = " + Arrays.toString(message));
        return i;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.bcm.IDoorAndWindow
    public Tribool isDoorOpened(int i) {
        byte[] bArr = new byte[25];
        bArr[0] = 57;
        byte[] message = RpcManager.getInstance().getMessage(1881, bArr);
        byte[] bArr2 = new byte[25];
        bArr2[0] = 58;
        byte[] message2 = RpcManager.getInstance().getMessage(1881, bArr2);
        Log.d(TAG, "isDoorOpened doorData = " + Arrays.toString(message));
        Log.d(TAG, "isDoorOpened doorRear = " + Arrays.toString(message2));
        if (i == 1) {
            if (message[4] == 1) {
                return Tribool.yes;
            }
            return Tribool.no;
        }
        if (i == 4) {
            if (message[2] == 1) {
                return Tribool.yes;
            }
            return Tribool.no;
        }
        if (i == 16) {
            if (message[1] == 1) {
                return Tribool.yes;
            }
            return Tribool.no;
        }
        if (i == 64) {
            if (message[3] == 1) {
                return Tribool.yes;
            }
            return Tribool.no;
        }
        if (i == 536870912) {
            if (message2[1] == 1) {
                return Tribool.yes;
            }
            return Tribool.no;
        }
        return Tribool.indeterminate;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.bcm.IDoorAndWindow
    public boolean setDoorOnOff(int i, boolean z) {
        boolean z2 = false;
        if (i == 536870912) {
            Log.d(TAG, "setDoorOnOff device = DOOR_REAR, on = " + z);
            byte[] bArr = new byte[25];
            bArr[0] = Define.cSubCodeSetAcEco;
            z2 = true;
            if (z) {
                bArr[1] = 1;
            } else {
                bArr[1] = 2;
            }
            RpcManager.getInstance().sendMessage(1884, bArr);
            Log.d(TAG, "setDoorOnOff dataIn = " + Arrays.toString(bArr));
        }
        return z2;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.bcm.IDoorAndWindow
    public FunctionStatus isDoorOperationSupported(int i, int i2) {
        if (i2 != 2) {
            return FunctionStatus.notavailable;
        }
        if (i == 1) {
            return FunctionStatus.notavailable;
        }
        if (i == 4) {
            return FunctionStatus.notavailable;
        }
        if (i == 16) {
            return FunctionStatus.notavailable;
        }
        if (i == 64) {
            return FunctionStatus.notavailable;
        }
        if (i == 536870912) {
            int e01OfflineConfig = OfflineConfig.getE01OfflineConfig(21);
            Log.d(TAG, "isDoorOperationSupported  new OfflineConfig result = " + e01OfflineConfig);
            if (e01OfflineConfig == 1) {
                return FunctionStatus.active;
            }
            return FunctionStatus.notavailable;
        }
        return FunctionStatus.notavailable;
    }

    public boolean getOfflineConfig(int i) {
        int e01OfflineConfig;
        if (i == 1) {
            e01OfflineConfig = OfflineConfig.getE01OfflineConfig(156);
        } else {
            e01OfflineConfig = i == 2 ? OfflineConfig.getE01OfflineConfig(112) : -1;
        }
        Log.d(TAG, "Vehicle new getOfflineConfig type = " + i + "result = " + e01OfflineConfig);
        return e01OfflineConfig == 1;
    }

    public int getAntiPinchOfflineConfig() {
        int e01OfflineConfig = OfflineConfig.getE01OfflineConfig(113);
        Log.d(TAG, "getAntiPinchOfflineConfig new result = " + e01OfflineConfig);
        if (e01OfflineConfig != 2) {
            return e01OfflineConfig != 3 ? 0 : 2;
        }
        return 1;
    }

    public boolean isWindowAvailable(int i) {
        boolean z = false;
        if (!isWindowError(i) && isWindowValid(i) && !isWindowAntiPinch(i) && !isWindowRunning(i) && !isWindowObsInhibit(i)) {
            z = true;
        }
        LogTool.d(TAG, "isWindowAvailable result = " + z);
        return z;
    }

    public boolean isWindowError(int i) {
        byte[] bArr = new byte[25];
        bArr[0] = 3;
        byte[] message = RpcManager.getInstance().getMessage(1881, bArr);
        LogTool.d(TAG, "isWindowError " + Arrays.toString(message));
        return i != 3 ? i != 4 ? i != 5 ? i != 6 ? i == 7 && message[7] == 1 : message[9] == 1 : message[8] == 1 : message[6] == 1 : message[5] == 1;
    }

    public boolean isWindowValid(int i) {
        byte[] bArr = new byte[25];
        bArr[0] = 28;
        byte[] message = RpcManager.getInstance().getMessage(1881, bArr);
        LogTool.d(TAG, "isWindowValid " + Arrays.toString(message));
        return i != 3 ? i != 4 ? i != 5 ? i == 6 && message[18] == 1 : message[10] == 1 : message[14] == 1 : message[6] == 1;
    }

    public boolean isWindowAntiPinch(int i) {
        int antiPinchOfflineConfig = getAntiPinchOfflineConfig();
        LogTool.d(TAG, "isWindowAntiPinch window = " + i);
        if (antiPinchOfflineConfig == 0) {
            return true;
        }
        if (antiPinchOfflineConfig == 2 && i != 3) {
            return true;
        }
        byte[] bArr = new byte[25];
        bArr[0] = 28;
        byte[] message = RpcManager.getInstance().getMessage(1881, bArr);
        LogTool.d(TAG, "isWindowAntiPinch " + Arrays.toString(message));
        return i != 3 ? i != 4 ? i != 5 ? i != 6 ? i == 7 && message[22] == 0 : message[19] == 1 : message[11] == 1 : message[15] == 1 : message[7] == 1;
    }

    public boolean isWindowRunning(int i) {
        byte[] bArr = new byte[25];
        bArr[0] = 28;
        byte[] message = RpcManager.getInstance().getMessage(1881, bArr);
        LogTool.d(TAG, "isWindowRunning " + Arrays.toString(message));
        return i != 3 ? i != 4 ? i != 5 ? (i == 6 && message[21] == 0) ? false : true : message[13] != 0 : message[17] != 0 : message[9] != 0;
    }

    public boolean isWindowObsInhibit(int i) {
        byte[] bArr = new byte[25];
        bArr[0] = 28;
        byte[] message = RpcManager.getInstance().getMessage(1881, bArr);
        LogTool.d(TAG, "isWindowObsInhibit " + Arrays.toString(message));
        return i != 3 ? i != 4 ? i != 5 ? i == 6 && message[20] == 1 : message[12] == 1 : message[16] == 1 : message[8] == 1;
    }

    public boolean isSunroofAvailable() {
        boolean z = false;
        if (isWindowAntiPinch(7) && !isWindowError(7) && !isSunroofRunning()) {
            z = true;
        }
        LogTool.d(TAG, "isWindowAvailable result = " + z);
        return z;
    }

    public boolean isSunroofRunning() {
        byte[] bArr = new byte[25];
        bArr[0] = 4;
        byte[] message = RpcManager.getInstance().getMessage(1881, bArr);
        LogTool.d(TAG, "isSunroofRunning " + Arrays.toString(message));
        return message[20] != 0;
    }
}
