package com.ecarx.xui.adaptapi.vehicle.dashboard;

import android.util.Log;
import androidx.core.view.MotionEventCompat;
import com.ecarx.xui.adaptapi.Tribool;
import com.ecarx.xui.adaptapi.hvac.specific.Define;
import com.ecarx.xui.adaptapi.vehicle.dashboard.IDashboard;
import com.neusoft.alfus.ExtraUtils;
import com.neusoft.alfus.RpcManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX INFO: loaded from: classes2.dex */
public class Dashboard implements IDashboard {
    private static final int ABS_ESC_OPECODE = 1821;
    private static final byte ABS_ESC_SUBOPECODE = 1;
    private static final int GET_OPECODE = 1881;
    private static final byte SUB_OPECODE_GETMileageInfo = 23;
    public static final byte SUB_OPECODE_SPEED_CHANGE = 1;
    private static final String TAG = "neuapi";
    private static final int cCallbackOpeCode = 1313;
    private static final int openCode = 1881;
    private int _speedState;
    private IDashboard.IVehicleFuelLevelObserver mFuelLevelObserver;
    private IDashboard.IVehicleACCStatusObserver vehicleACCStatusObserver;
    private IDashboard.IVehicleMileageObserver vehicleMileageObserver;
    private IDashboard.IVehicleSpeedObserver vehicleSpeedObserver;
    private IDashboard.IVehicleSpeedObserver vehicleSpeedObserver_trend;
    private IDashboard.IVehicleWarningObserver vehicleWarningObserver;
    private ExecutorService pool = Executors.newSingleThreadExecutor();
    ExtraUtils eu = ExtraUtils.getInstance();
    private boolean reverse_flag = false;
    private RpcManager.OnRpcListener fuelLevelCallback = new RpcManager.OnRpcListener() { // from class: com.ecarx.xui.adaptapi.vehicle.dashboard.Dashboard.1
        public void onCallback(int i, byte[] bArr) {
            if (i == 1820 && bArr[0] == 1) {
                Log.d(Dashboard.TAG, "IPK FuelLevel _onCallback  bytes:%s" + Arrays.toString(bArr));
                double unsignedByte = Dashboard.this.getUnsignedByte(bArr[7]);
                Log.d(Dashboard.TAG, "fuelLevelCallback ~ ：" + unsignedByte);
                double d = unsignedByte * 0.5d;
                Log.d(Dashboard.TAG, "fuelLevelCallback result = " + d);
                Dashboard.this.mFuelLevelObserver.onVehicleFuelLevelChanged(d);
            }
        }
    };
    private RpcManager.OnRpcListener _onCallback = new RpcManager.OnRpcListener() { // from class: com.ecarx.xui.adaptapi.vehicle.dashboard.Dashboard.2
        public void onCallback(int i, byte[] bArr) {
            if (i == 1821 && bArr[0] == 4) {
                Log.d(Dashboard.TAG, "speed _onCallback  bytes:%s" + Arrays.toString(bArr));
                Dashboard.this.sendPules(bArr);
            }
        }
    };
    private RpcManager.OnRpcListener onCallback_trend = new RpcManager.OnRpcListener() { // from class: com.ecarx.xui.adaptapi.vehicle.dashboard.Dashboard.3
        public void onCallback(int i, byte[] bArr) {
            if (i == 1313) {
                Log.d(Dashboard.TAG, "onCallback_trend" + Arrays.toString(bArr));
                if (bArr[0] == 1) {
                    Dashboard.this._speedState = (byte) (bArr[1] & 255);
                    Log.d(Dashboard.TAG, "SpeedState change callback :" + ((int) bArr[1]));
                }
                Dashboard.this.shield();
            }
        }
    };
    private RpcManager.OnRpcListener _TCUACUCallBack = new RpcManager.OnRpcListener() { // from class: com.ecarx.xui.adaptapi.vehicle.dashboard.Dashboard.5
        public void onCallback(int i, byte[] bArr) {
            if (i == 1835 && bArr[0] == 4) {
                Log.d(Dashboard.TAG, "_onCallback  bytes: " + Arrays.toString(bArr));
                byte b = bArr[5];
                byte b2 = bArr[4];
                byte b3 = bArr[1];
                byte b4 = bArr[6];
                byte b5 = bArr[7];
                byte b6 = bArr[8];
                byte b7 = bArr[9];
                Log.d(Dashboard.TAG, "_onCallback  cCoolantTemperature = " + ((int) b));
                Log.d(Dashboard.TAG, "_onCallback  cAirBag = " + ((int) b2));
                Log.d(Dashboard.TAG, "_onCallback  cEPBErrorStatus = " + ((int) b3));
                Log.d(Dashboard.TAG, "_onCallback  cTCU_Warning = " + ((int) b4));
                Log.d(Dashboard.TAG, "_onCallback  cOverTemp = " + ((int) b5));
                Log.d(Dashboard.TAG, "_onCallback  cOverTemp2 = " + ((int) b6));
                if (b < 0 && b >= -47 && Dashboard.this.vehicleWarningObserver != null) {
                    Log.d(Dashboard.TAG, "cCoolantTemperature");
                    IWarningInfo[] iWarningInfoArr = new IWarningInfo[10];
                    WarningInfo warningInfo = new WarningInfo();
                    warningInfo.setWarningId(1);
                    warningInfo.setWarningPriority(-1);
                    iWarningInfoArr[0] = warningInfo;
                    Dashboard.this.vehicleWarningObserver.onVehicleWarningChanged(iWarningInfoArr);
                }
                if (b2 == 1 && Dashboard.this.vehicleWarningObserver != null) {
                    Log.d(Dashboard.TAG, "cAirBag");
                    IWarningInfo[] iWarningInfoArr2 = new IWarningInfo[10];
                    WarningInfo warningInfo2 = new WarningInfo();
                    warningInfo2.setWarningId(2);
                    warningInfo2.setWarningPriority(-1);
                    iWarningInfoArr2[0] = warningInfo2;
                    Dashboard.this.vehicleWarningObserver.onVehicleWarningChanged(iWarningInfoArr2);
                }
                if (b3 == 1 && Dashboard.this.vehicleWarningObserver != null) {
                    Log.d(Dashboard.TAG, "cEPBErrorStatus");
                    IWarningInfo[] iWarningInfoArr3 = new IWarningInfo[10];
                    WarningInfo warningInfo3 = new WarningInfo();
                    warningInfo3.setWarningId(7);
                    warningInfo3.setWarningPriority(-1);
                    iWarningInfoArr3[0] = warningInfo3;
                    Dashboard.this.vehicleWarningObserver.onVehicleWarningChanged(iWarningInfoArr3);
                }
                if (b4 == 1 && Dashboard.this.vehicleWarningObserver != null) {
                    Log.d(Dashboard.TAG, "cTCU_Warning");
                    IWarningInfo[] iWarningInfoArr4 = new IWarningInfo[10];
                    WarningInfo warningInfo4 = new WarningInfo();
                    warningInfo4.setWarningId(10);
                    warningInfo4.setWarningPriority(-1);
                    iWarningInfoArr4[0] = warningInfo4;
                    Dashboard.this.vehicleWarningObserver.onVehicleWarningChanged(iWarningInfoArr4);
                }
                if (Dashboard.this.vehicleWarningObserver != null) {
                    if (b5 == 2) {
                        Log.d(Dashboard.TAG, "TCU_OvertempLevel OvertempLevel 2");
                        IWarningInfo[] iWarningInfoArr5 = new IWarningInfo[10];
                        WarningInfo warningInfo5 = new WarningInfo();
                        warningInfo5.setWarningId(10);
                        warningInfo5.setWarningPriority(-1);
                        iWarningInfoArr5[0] = warningInfo5;
                        WarningInfo warningInfo6 = new WarningInfo();
                        warningInfo6.setWarningId(24);
                        warningInfo6.setWarningPriority(-1);
                        iWarningInfoArr5[1] = warningInfo6;
                        Dashboard.this.vehicleWarningObserver.onVehicleWarningChanged(iWarningInfoArr5);
                    } else if (b5 == 1) {
                        Log.d(Dashboard.TAG, "TCU_OvertempLevel OvertempLevel 1");
                        IWarningInfo[] iWarningInfoArr6 = new IWarningInfo[10];
                        WarningInfo warningInfo7 = new WarningInfo();
                        warningInfo7.setWarningId(10);
                        warningInfo7.setWarningPriority(-1);
                        iWarningInfoArr6[0] = warningInfo7;
                        WarningInfo warningInfo8 = new WarningInfo();
                        warningInfo8.setWarningId(23);
                        warningInfo8.setWarningPriority(-1);
                        iWarningInfoArr6[1] = warningInfo8;
                        Dashboard.this.vehicleWarningObserver.onVehicleWarningChanged(iWarningInfoArr6);
                    }
                }
                if (b6 == 1 && Dashboard.this.vehicleWarningObserver != null) {
                    Log.d(Dashboard.TAG, "cOverTemp 0x07");
                    IWarningInfo[] iWarningInfoArr7 = new IWarningInfo[10];
                    WarningInfo warningInfo9 = new WarningInfo();
                    warningInfo9.setWarningId(10);
                    warningInfo9.setWarningPriority(-1);
                    iWarningInfoArr7[0] = warningInfo9;
                    Dashboard.this.vehicleWarningObserver.onVehicleWarningChanged(iWarningInfoArr7);
                }
                if (b7 != 1 || Dashboard.this.vehicleWarningObserver == null) {
                    return;
                }
                Log.d(Dashboard.TAG, "cParkFailureReport");
                IWarningInfo[] iWarningInfoArr8 = new IWarningInfo[10];
                WarningInfo warningInfo10 = new WarningInfo();
                warningInfo10.setWarningId(10);
                warningInfo10.setWarningPriority(-1);
                iWarningInfoArr8[0] = warningInfo10;
                Dashboard.this.vehicleWarningObserver.onVehicleWarningChanged(iWarningInfoArr8);
            }
        }
    };
    private RpcManager.OnRpcListener _ESCCallBack = new RpcManager.OnRpcListener() { // from class: com.ecarx.xui.adaptapi.vehicle.dashboard.Dashboard.6
        public void onCallback(int i, byte[] bArr) {
            if (i == 1820 && bArr[0] == 3) {
                Log.d(Dashboard.TAG, "ESConCallback  bytes: " + Arrays.toString(bArr));
                byte b = bArr[1];
                byte b2 = bArr[4];
                if (b == 1 && Dashboard.this.vehicleWarningObserver != null) {
                    Log.d(Dashboard.TAG, "cESC_EBDFailed");
                    IWarningInfo[] iWarningInfoArr = new IWarningInfo[10];
                    WarningInfo warningInfo = new WarningInfo();
                    warningInfo.setWarningId(6);
                    warningInfo.setWarningPriority(-1);
                    iWarningInfoArr[0] = warningInfo;
                    Dashboard.this.vehicleWarningObserver.onVehicleWarningChanged(iWarningInfoArr);
                }
                if (b2 != 1 || Dashboard.this.vehicleWarningObserver == null) {
                    return;
                }
                Log.d(Dashboard.TAG, "cESPFailed");
                IWarningInfo[] iWarningInfoArr2 = new IWarningInfo[10];
                WarningInfo warningInfo2 = new WarningInfo();
                warningInfo2.setWarningId(8);
                warningInfo2.setWarningPriority(-1);
                iWarningInfoArr2[0] = warningInfo2;
                Dashboard.this.vehicleWarningObserver.onVehicleWarningChanged(iWarningInfoArr2);
            }
        }
    };
    private RpcManager.OnRpcListener _HHC_ErrorCallBack = new RpcManager.OnRpcListener() { // from class: com.ecarx.xui.adaptapi.vehicle.dashboard.Dashboard.7
        public void onCallback(int i, byte[] bArr) {
            if (i == 1835 && bArr[0] == 5) {
                Log.d(Dashboard.TAG, "_HHC_ErrorCallBack  bytes: " + Arrays.toString(bArr));
                if (bArr[10] != 1 || Dashboard.this.vehicleWarningObserver == null) {
                    return;
                }
                Log.d(Dashboard.TAG, "cHHC_Error");
                IWarningInfo[] iWarningInfoArr = new IWarningInfo[10];
                WarningInfo warningInfo = new WarningInfo();
                warningInfo.setWarningId(13);
                warningInfo.setWarningPriority(-1);
                iWarningInfoArr[0] = warningInfo;
                Dashboard.this.vehicleWarningObserver.onVehicleWarningChanged(iWarningInfoArr);
            }
        }
    };
    private RpcManager.OnRpcListener fuelCallBack = new RpcManager.OnRpcListener() { // from class: com.ecarx.xui.adaptapi.vehicle.dashboard.Dashboard.8
        public void onCallback(int i, byte[] bArr) {
            if (i == 1820 && bArr[0] == 1) {
                Log.d(Dashboard.TAG, "FUELonCallback  bytes: " + Arrays.toString(bArr));
                byte b = bArr[5];
                byte b2 = bArr[6];
                if (b == 1 && Dashboard.this.vehicleWarningObserver != null) {
                    Log.d(Dashboard.TAG, "cFuelLevelLowWarning");
                    if (Dashboard.this.isKCProject()) {
                        IWarningInfo[] iWarningInfoArr = new IWarningInfo[10];
                        WarningInfo warningInfo = new WarningInfo();
                        warningInfo.setWarningId(9);
                        warningInfo.setWarningPriority(-1);
                        iWarningInfoArr[0] = warningInfo;
                        Dashboard.this.vehicleWarningObserver.onVehicleWarningChanged(iWarningInfoArr);
                    }
                }
                if (b2 != 1 || Dashboard.this.vehicleWarningObserver == null) {
                    return;
                }
                Log.d(Dashboard.TAG, "cLowOilWarning");
                IWarningInfo[] iWarningInfoArr2 = new IWarningInfo[10];
                WarningInfo warningInfo2 = new WarningInfo();
                warningInfo2.setWarningId(5);
                warningInfo2.setWarningPriority(-1);
                iWarningInfoArr2[0] = warningInfo2;
                Dashboard.this.vehicleWarningObserver.onVehicleWarningChanged(iWarningInfoArr2);
            }
        }
    };
    private RpcManager.OnRpcListener _FCSFRSCallBack = new RpcManager.OnRpcListener() { // from class: com.ecarx.xui.adaptapi.vehicle.dashboard.Dashboard.9
        public void onCallback(int i, byte[] bArr) {
            if (i == 1822 && bArr[0] == 4) {
                Log.d(Dashboard.TAG, "_onCallback  bytes: " + Arrays.toString(bArr));
                byte b = bArr[16];
                byte b2 = bArr[1];
                if (b == 14 && Dashboard.this.vehicleWarningObserver != null) {
                    Log.d(Dashboard.TAG, "cAlod");
                    IWarningInfo[] iWarningInfoArr = new IWarningInfo[10];
                    WarningInfo warningInfo = new WarningInfo();
                    warningInfo.setWarningId(11);
                    warningInfo.setWarningPriority(-1);
                    iWarningInfoArr[0] = warningInfo;
                    Dashboard.this.vehicleWarningObserver.onVehicleWarningChanged(iWarningInfoArr);
                }
                if (b2 != 1 || Dashboard.this.vehicleWarningObserver == null) {
                    return;
                }
                Log.d(Dashboard.TAG, "cFCS_Status");
                IWarningInfo[] iWarningInfoArr2 = new IWarningInfo[10];
                WarningInfo warningInfo2 = new WarningInfo();
                warningInfo2.setWarningId(12);
                warningInfo2.setWarningPriority(-1);
                iWarningInfoArr2[0] = warningInfo2;
                Dashboard.this.vehicleWarningObserver.onVehicleWarningChanged(iWarningInfoArr2);
            }
        }
    };
    private RpcManager.OnRpcListener mileageCallback = new RpcManager.OnRpcListener() { // from class: com.ecarx.xui.adaptapi.vehicle.dashboard.Dashboard.10
        public void onCallback(int i, byte[] bArr) {
            if (i == 1835 && bArr[0] == 6) {
                Log.d(Dashboard.TAG, "_onCallback  bytes:%s" + Arrays.toString(bArr));
                if (bArr[10] == 1) {
                    if (Dashboard.this.vehicleMileageObserver != null) {
                        Log.d(Dashboard.TAG, "cMaintanceWarningSts");
                        Dashboard.this.vehicleMileageObserver.onMaintenanceReminder(1);
                    } else {
                        Log.d(Dashboard.TAG, "onCallback  vehicleMileageObserver = null");
                    }
                }
            }
        }
    };
    private RpcManager.OnRpcListener totalMileageCallback = new RpcManager.OnRpcListener() { // from class: com.ecarx.xui.adaptapi.vehicle.dashboard.Dashboard.11
        public void onCallback(int i, byte[] bArr) {
            if (i == 1821 && bArr[0] == 11) {
                Log.d(Dashboard.TAG, "_onCallback  bytes:%s" + Arrays.toString(bArr));
                int i2 = ((((((char) bArr[13]) & 255) << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK) | (((char) bArr[14]) & 255)) * 1000;
                Log.d(Dashboard.TAG, "onCallback  totalMileageCallback *1000 result = " + i2);
                if (Dashboard.this.vehicleMileageObserver != null) {
                    MileageInfo mileageInfo = new MileageInfo();
                    mileageInfo.setEnduranceMileage(i2);
                    Dashboard.this.vehicleMileageObserver.onVehicleMileageChanged(mileageInfo);
                    return;
                }
                Log.d(Dashboard.TAG, "onCallback  vehicleMileageObserver = null");
            }
        }
    };
    private RpcManager.OnRpcListener accCallback = new RpcManager.OnRpcListener() { // from class: com.ecarx.xui.adaptapi.vehicle.dashboard.Dashboard.12
        public void onCallback(int i, byte[] bArr) {
            if (i == 1820 && bArr[0] == 8) {
                Log.d(Dashboard.TAG, "_onCallback  bytes:%s" + Arrays.toString(bArr));
                byte b = bArr[6];
                if (Dashboard.this.vehicleACCStatusObserver != null) {
                    Log.d(Dashboard.TAG, "accCallback powerMode = " + ((int) b));
                    if (b == 0) {
                        Dashboard.this.vehicleACCStatusObserver.onVehicleACCStatusChanged(1);
                        return;
                    }
                    if (b == 1) {
                        Dashboard.this.vehicleACCStatusObserver.onVehicleACCStatusChanged(6);
                        return;
                    }
                    if (b == 2) {
                        Dashboard.this.vehicleACCStatusObserver.onVehicleACCStatusChanged(2);
                    } else if (b == 4) {
                        Dashboard.this.vehicleACCStatusObserver.onVehicleACCStatusChanged(3);
                    } else {
                        Dashboard.this.vehicleACCStatusObserver.onVehicleACCStatusChanged(Integer.MIN_VALUE);
                    }
                }
            }
        }
    };
    private RpcManager.OnRpcListener mGearCallback = new RpcManager.OnRpcListener() { // from class: com.ecarx.xui.adaptapi.vehicle.dashboard.Dashboard.13
        public void onCallback(int i, byte[] bArr) {
            if (i == 1834 && bArr[0] == 2) {
                Dashboard.this.setReverse(bArr[1]);
            }
        }
    };

    @Override // com.ecarx.xui.adaptapi.vehicle.dashboard.IDashboard
    public boolean attachDashboardHintChangeObserver(IDashboard.IDashboardHintObserver iDashboardHintObserver) {
        return false;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.dashboard.IDashboard
    public boolean clearMaintenanceWarning() {
        return false;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.dashboard.IDashboard
    public void detachDashboardHintChangeObserver(IDashboard.IDashboardHintObserver iDashboardHintObserver) {
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.dashboard.IDashboard
    public double getFullFuelLevel() {
        return 0.0d;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.dashboard.IDashboard
    public int getHandBrakeStatus() {
        return 0;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.dashboard.IDashboard
    public double getInstantaneousFuelConsumptionLevel() {
        return 0.0d;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.dashboard.IDashboard
    public ITpmsInfo getTpmsInfo() {
        return null;
    }

    public int getUnsignedByte(byte b) {
        return b & 255;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.dashboard.IDashboard
    public int getVehicleSpeed() {
        return 0;
    }

    public boolean isKCProject() {
        return false;
    }

    public Dashboard() {
        RpcManager.getInstance().registerListener(1834, (byte) 2, this.mGearCallback);
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.dashboard.IDashboard
    public double getFuelLevel() {
        byte[] bArr = new byte[25];
        bArr[0] = 19;
        double unsignedByte = getUnsignedByte(RpcManager.getInstance().getMessage(1881, bArr)[4]);
        Log.d(TAG, "getFuelLevel ~ ：" + unsignedByte);
        double d = unsignedByte * 0.5d;
        Log.d(TAG, "getFuelLevel result = " + d);
        return d;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.dashboard.IDashboard
    public boolean attachVehicleFuelLevelObserver(IDashboard.IVehicleFuelLevelObserver iVehicleFuelLevelObserver) {
        if (iVehicleFuelLevelObserver == null) {
            Log.d(TAG, "IVehicleFuelLevelObserver == null");
            return false;
        }
        this.mFuelLevelObserver = iVehicleFuelLevelObserver;
        RpcManager.getInstance().registerListener(1820, (byte) 1, this.fuelLevelCallback);
        Log.d(TAG, "attachVehicleFuelLevelObserver = " + iVehicleFuelLevelObserver.toString());
        return true;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.dashboard.IDashboard
    public void detachVehicleFuelLevelObserver(IDashboard.IVehicleFuelLevelObserver iVehicleFuelLevelObserver) {
        this.mFuelLevelObserver = null;
        RpcManager.getInstance().unregisterListener(1820, (byte) 1, this.fuelLevelCallback);
        Log.d(TAG, "detachVehicleFuelLevelObserver");
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.dashboard.IDashboard
    public double getAverageFuelConsumptionLevel() {
        byte[] bArr = new byte[25];
        bArr[0] = 24;
        byte[] message = RpcManager.getInstance().getMessage(1881, bArr);
        int i = (((char) message[6]) & 255) | (((((char) message[5]) & 255) << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK);
        Log.d(TAG, "getAverageFuelConsumptionLevel averageFuel = " + i);
        double d = ((double) i) * 0.01d;
        Log.d(TAG, "getAverageFuelConsumptionLevel result = " + d);
        return d;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.dashboard.IDashboard
    public double getVehicleSpeedInDouble() {
        byte[] bArr = new byte[25];
        bArr[0] = 2;
        byte[] message = RpcManager.getInstance().getMessage(1881, bArr);
        if (!getRpcOK(message, 11)) {
            return 0.0d;
        }
        double d = ((double) ((((char) message[11]) & 255) | (((((char) message[10]) & 255) << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK))) * 0.05625d;
        if (this.reverse_flag) {
            d = -d;
        }
        Log.d(TAG, "getVehicleSpeed = " + d);
        return d;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.dashboard.IDashboard
    public boolean attachVehicleSpeedObserver(IDashboard.IVehicleSpeedObserver iVehicleSpeedObserver) {
        if (iVehicleSpeedObserver == null) {
            Log.d(TAG, "vehicleSpeedObserver == null");
            return false;
        }
        this.vehicleSpeedObserver = iVehicleSpeedObserver;
        RpcManager.getInstance().registerListener(1821, (byte) 4, this._onCallback);
        Log.d(TAG, "attachVehicleSpeedObserver new = " + iVehicleSpeedObserver.toString());
        return true;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.dashboard.IDashboard
    public boolean attachVehicleSpeedObserver(IDashboard.IVehicleSpeedObserver iVehicleSpeedObserver, double[] dArr, int i, int i2) {
        if (iVehicleSpeedObserver == null) {
            Log.d(TAG, "vehicleSpeedObserver 2 == null");
            return false;
        }
        this.vehicleSpeedObserver_trend = iVehicleSpeedObserver;
        RpcManager.getInstance().registerListener(1313, (byte) 1, this.onCallback_trend);
        Log.d(TAG, "attachVehicleSpeedObserver 2 new = " + iVehicleSpeedObserver.toString());
        return true;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.dashboard.IDashboard
    public void detachVehicleSpeedObserver(IDashboard.IVehicleSpeedObserver iVehicleSpeedObserver) {
        if (iVehicleSpeedObserver == null) {
            Log.d(TAG, "vehicleSpeedObserver == null");
            return;
        }
        this.vehicleSpeedObserver = null;
        RpcManager.getInstance().unregisterListener(1821, (byte) 4, this._onCallback);
        RpcManager.getInstance().unregisterListener(1313, (byte) 1, this.onCallback_trend);
        Log.d(TAG, "detachVehicleSpeedObserver");
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.dashboard.IDashboard
    public IMileageInfo getMileageInfo() {
        byte[] bArr = new byte[25];
        bArr[0] = 23;
        byte[] message = RpcManager.getInstance().getMessage(1881, bArr);
        for (int i = 0; i < message.length; i++) {
            Log.d(TAG, i + "===>" + ((int) message[i]));
        }
        int i2 = ((((char) message[20]) & 255) | (((((char) message[19]) & 255) << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK)) * 1000;
        Log.d(TAG, "getMileageInfo result = " + i2);
        MileageInfo mileageInfo = new MileageInfo();
        mileageInfo.setEnduranceMileage(i2);
        return mileageInfo;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.dashboard.IDashboard
    public boolean attachVehicleMileageObserver(IDashboard.IVehicleMileageObserver iVehicleMileageObserver) {
        if (iVehicleMileageObserver == null) {
            Log.d(TAG, "IVehicleMileageObserver == null");
            return false;
        }
        this.vehicleMileageObserver = iVehicleMileageObserver;
        RpcManager.getInstance().registerListener(1835, (byte) 6, this.mileageCallback);
        RpcManager.getInstance().registerListener(1821, (byte) 11, this.totalMileageCallback);
        Log.d(TAG, "attachVehicleMileageObserver = " + iVehicleMileageObserver.toString());
        return true;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.dashboard.IDashboard
    public void detachVehicleMileageObserver(IDashboard.IVehicleMileageObserver iVehicleMileageObserver) {
        if (iVehicleMileageObserver == null) {
            Log.d(TAG, "IVehicleMileageObserver == null");
            return;
        }
        RpcManager.getInstance().unregisterListener(1835, (byte) 6, this.mileageCallback);
        RpcManager.getInstance().unregisterListener(1821, (byte) 11, this.totalMileageCallback);
        this.vehicleMileageObserver = null;
        Log.d(TAG, "detachVehicleMileageObserver");
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.dashboard.IDashboard
    public int getAccStatus() {
        byte[] bArr = new byte[25];
        int i = 6;
        bArr[0] = 6;
        byte b = RpcManager.getInstance().getMessage(1881, bArr)[11];
        Log.d(TAG, "getAccStatus powerMode = " + ((int) b));
        if (b == 0) {
            i = 1;
        } else if (b != 1) {
            i = 2;
            if (b != 2) {
                i = b == 4 ? 3 : Integer.MIN_VALUE;
            }
        }
        Log.d(TAG, "getAccStatus result = " + i);
        return i;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.dashboard.IDashboard
    public boolean attachVehicleACCStatusObserver(IDashboard.IVehicleACCStatusObserver iVehicleACCStatusObserver) {
        if (iVehicleACCStatusObserver == null) {
            Log.d(TAG, "IVehicleACCStatusObserver == null");
            return false;
        }
        this.vehicleACCStatusObserver = iVehicleACCStatusObserver;
        RpcManager.getInstance().registerListener(1820, (byte) 8, this.accCallback);
        Log.d(TAG, "attachVehicleACCStatusObserver = " + iVehicleACCStatusObserver.toString());
        return true;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.dashboard.IDashboard
    public void detachVehicleACCStatusObserver(IDashboard.IVehicleACCStatusObserver iVehicleACCStatusObserver) {
        if (iVehicleACCStatusObserver == null) {
            Log.d(TAG, "IVehicleACCStatusObserver == null");
            return;
        }
        RpcManager.getInstance().unregisterListener(1820, (byte) 8, this.accCallback);
        this.vehicleACCStatusObserver = null;
        Log.d(TAG, "detachVehicleACCStatusObserver");
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.dashboard.IDashboard
    public int getMileageToNextMaintenance() {
        byte[] bArr = new byte[25];
        bArr[0] = Define.cSubCodeSetAcSync;
        byte[] message = RpcManager.getInstance().getMessage(1881, bArr);
        int i = (((char) message[2]) & 255) | (((((char) message[1]) & 255) << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK);
        Log.d(TAG, "getMileageToNextMaintenance nextMaintenance = " + i);
        return i;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.dashboard.IDashboard
    public Tribool hasMaintenanceWarning() {
        byte[] bArr = new byte[25];
        bArr[0] = 68;
        byte[] message = RpcManager.getInstance().getMessage(1881, bArr);
        Log.d(TAG, "hasMaintenanceWarning getValue[] = " + Arrays.toString(message));
        if (message[1] == 1) {
            return Tribool.yes;
        }
        return Tribool.no;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.dashboard.IDashboard
    public IWarningInfo[] getWanringInfo() {
        ArrayList arrayList = new ArrayList();
        byte[] canInfo = getCanInfo(1881, 19);
        if (canInfo[3] == 1) {
            WarningInfo warningInfo = new WarningInfo();
            warningInfo.setWarningId(9);
            warningInfo.setWarningPriority(-1);
            arrayList.add(warningInfo);
            Log.d(TAG, "getWanringInfo ID_FUEL_LEVEL ");
        }
        if (canInfo[5] == 1) {
            WarningInfo warningInfo2 = new WarningInfo();
            warningInfo2.setWarningId(5);
            warningInfo2.setWarningPriority(-1);
            arrayList.add(warningInfo2);
            Log.d(TAG, "getWanringInfo ID_ENGINE_OIL_PRESSURE ");
        }
        if (canInfo[10] == 1) {
            WarningInfo warningInfo3 = new WarningInfo();
            warningInfo3.setWarningId(6);
            warningInfo3.setWarningPriority(-1);
            arrayList.add(warningInfo3);
            Log.d(TAG, "getWanringInfo ID_ELECTRIC_BRAKEFORCE_DISTRIBUTION ");
        }
        if (canInfo[13] == 1) {
            WarningInfo warningInfo4 = new WarningInfo();
            warningInfo4.setWarningId(8);
            warningInfo4.setWarningPriority(-1);
            arrayList.add(warningInfo4);
            Log.d(TAG, "getWanringInfo ID_ELECTRONIC_STABILITY_CONTROL ");
        }
        byte b = getCanInfo(1881, 45)[1];
        Log.d(TAG, "getWanringInfo  cCoolantTemperature = " + ((int) b));
        if (b < 0 && b >= -47) {
            Log.d(TAG, "cCoolantTemperature");
            WarningInfo warningInfo5 = new WarningInfo();
            warningInfo5.setWarningId(1);
            warningInfo5.setWarningPriority(-1);
            arrayList.add(warningInfo5);
        }
        if (getCanInfo(1881, 44)[1] == 1) {
            WarningInfo warningInfo6 = new WarningInfo();
            warningInfo6.setWarningId(2);
            warningInfo6.setWarningPriority(-1);
            arrayList.add(warningInfo6);
            Log.d(TAG, "getWanringInfo ID_AIRBAG ");
        }
        if (getCanInfo(1881, 41)[1] == 1) {
            WarningInfo warningInfo7 = new WarningInfo();
            warningInfo7.setWarningId(7);
            warningInfo7.setWarningPriority(-1);
            arrayList.add(warningInfo7);
            Log.d(TAG, "getWanringInfo ID_ELECTRIC_PARKING_BRAKE ");
        }
        WarningInfo tCUCanInfo = getTCUCanInfo();
        if (tCUCanInfo != null) {
            arrayList.add(tCUCanInfo);
        }
        if (getCanInfo(1881, 50)[1] == 14) {
            WarningInfo warningInfo8 = new WarningInfo();
            warningInfo8.setWarningId(11);
            warningInfo8.setWarningPriority(-1);
            arrayList.add(warningInfo8);
            Log.d(TAG, "getWanringInfo ID_ADAPTIVE_CRUISE_CONTROL");
        }
        if (getCanInfo(1881, 52)[1] == 1) {
            WarningInfo warningInfo9 = new WarningInfo();
            warningInfo9.setWarningId(12);
            warningInfo9.setWarningPriority(-1);
            arrayList.add(warningInfo9);
            Log.d(TAG, "getWanringInfo ID_LANE_DEPARTURE_WARNING");
        }
        if (getCanInfo(1881, 53)[1] == 1) {
            WarningInfo warningInfo10 = new WarningInfo();
            warningInfo10.setWarningId(13);
            warningInfo10.setWarningPriority(-1);
            arrayList.add(warningInfo10);
            Log.d(TAG, "getWanringInfo ID_HILL_ASSIST_CONTROL");
        }
        byte b2 = getCanInfo(1881, 47)[1];
        if (b2 == 1) {
            WarningInfo warningInfo11 = new WarningInfo();
            warningInfo11.setWarningId(23);
            warningInfo11.setWarningPriority(-1);
            arrayList.add(warningInfo11);
            Log.d(TAG, "getWanringInfo ID_TRANS_TEMP_WARN_LEVEL_1");
        } else if (b2 == 2) {
            WarningInfo warningInfo12 = new WarningInfo();
            warningInfo12.setWarningId(24);
            warningInfo12.setWarningPriority(-1);
            arrayList.add(warningInfo12);
            Log.d(TAG, "getWanringInfo ID_TRANS_TEMP_WARN_LEVEL_2");
        }
        Log.d(TAG, "onVehicleWarningChanged CallBack mlist size = " + arrayList.size());
        int size = arrayList.size();
        IWarningInfo[] iWarningInfoArr = new IWarningInfo[size];
        for (int i = 0; i < arrayList.size(); i++) {
            iWarningInfoArr[i] = (IWarningInfo) arrayList.get(i);
        }
        arrayList.clear();
        Log.d(TAG, "onVehicleWarningChanged CallBack iWarningInfos length = " + size);
        return iWarningInfoArr;
    }

    private byte[] getCanInfo(int i, int i2) {
        byte[] bArr = new byte[25];
        bArr[0] = (byte) i2;
        byte[] message = RpcManager.getInstance().getMessage(i, bArr);
        Log.d(TAG, Arrays.toString(message));
        return message;
    }

    private WarningInfo getTCUCanInfo() {
        boolean z;
        WarningInfo warningInfo = new WarningInfo();
        boolean z2 = true;
        if (getCanInfo(1881, 46)[1] == 1) {
            warningInfo.setWarningId(10);
            warningInfo.setWarningPriority(-1);
            Log.d(TAG, "getWanringInfo ID_TRANSMISSION_CONTROL_UNIT tcu");
            z = true;
        } else {
            z = false;
        }
        if (getCanInfo(1881, 47)[1] == 2) {
            warningInfo.setWarningId(10);
            warningInfo.setWarningPriority(-1);
            Log.d(TAG, "getWanringInfo ID_TRANSMISSION_CONTROL_UNIT overtemp");
            z = true;
        }
        if (getCanInfo(1881, 48)[1] == 1) {
            warningInfo.setWarningId(10);
            warningInfo.setWarningPriority(-1);
            Log.d(TAG, "getWanringInfo ID_TRANSMISSION_CONTROL_UNIT gearbox");
            z = true;
        }
        if (getCanInfo(1881, 49)[1] == 1) {
            warningInfo.setWarningId(10);
            warningInfo.setWarningPriority(-1);
            Log.d(TAG, "getWanringInfo ID_TRANSMISSION_CONTROL_UNIT parkFailure");
        } else {
            z2 = z;
        }
        if (z2) {
            return warningInfo;
        }
        return null;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.dashboard.IDashboard
    public boolean attachVehicleWarningObserver(IDashboard.IVehicleWarningObserver iVehicleWarningObserver) {
        if (iVehicleWarningObserver == null) {
            Log.d(TAG, "IVehicleWarningObserver == null");
            return false;
        }
        this.vehicleWarningObserver = iVehicleWarningObserver;
        RpcManager.getInstance().registerListener(1820, (byte) 1, this.fuelCallBack);
        RpcManager.getInstance().registerListener(1820, (byte) 3, this._ESCCallBack);
        RpcManager.getInstance().registerListener(1835, (byte) 4, this._TCUACUCallBack);
        RpcManager.getInstance().registerListener(1822, (byte) 4, this._FCSFRSCallBack);
        RpcManager.getInstance().registerListener(1835, (byte) 5, this._HHC_ErrorCallBack);
        Log.d(TAG, "attachVehicleWarningObserver = " + iVehicleWarningObserver.toString());
        return true;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.dashboard.IDashboard
    public void detachVehicleWarningObserver(IDashboard.IVehicleWarningObserver iVehicleWarningObserver) {
        if (iVehicleWarningObserver == null) {
            Log.d(TAG, "IVehicleWarningObserver == null");
            return;
        }
        RpcManager.getInstance().unregisterListener(1820, (byte) 1, this.fuelCallBack);
        RpcManager.getInstance().unregisterListener(1820, (byte) 3, this._ESCCallBack);
        RpcManager.getInstance().unregisterListener(1835, (byte) 4, this._TCUACUCallBack);
        RpcManager.getInstance().unregisterListener(1822, (byte) 4, this._FCSFRSCallBack);
        RpcManager.getInstance().unregisterListener(1835, (byte) 5, this._HHC_ErrorCallBack);
        this.vehicleWarningObserver = null;
        Log.d(TAG, "detachVehicleWarningObserver");
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.dashboard.IDashboard
    public double getCurrentTripFuelConsumptionLevel() {
        byte[] bArr = new byte[25];
        bArr[0] = 84;
        byte[] message = RpcManager.getInstance().getMessage(1881, bArr);
        int i = (((char) message[2]) & 255) | (((((char) message[1]) & 255) << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK);
        Log.i(TAG, "before = " + i);
        double d = ((double) i) * 0.1d;
        Log.i(TAG, "移位计算乘品质因数后的值 = " + d);
        if (d > 0.0d && d < 19.9d) {
            Log.i(TAG, "数据范围正常返回 = " + d);
            return d;
        }
        Log.i(TAG, "数据异常无效 = 4.9E-324");
        return Double.MIN_VALUE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void shield() {
        Log.d(TAG, "shield isShield _speed state =" + this._speedState);
        int i = this._speedState;
        if (i == 5) {
            IDashboard.IVehicleSpeedObserver iVehicleSpeedObserver = this.vehicleSpeedObserver_trend;
            if (iVehicleSpeedObserver != null) {
                iVehicleSpeedObserver.onVehicleSpeedChanged(15.0d, 1);
                return;
            } else {
                Log.d(TAG, "vehicleSpeedObserver_trend = null");
                return;
            }
        }
        if (i != 6) {
            return;
        }
        IDashboard.IVehicleSpeedObserver iVehicleSpeedObserver2 = this.vehicleSpeedObserver_trend;
        if (iVehicleSpeedObserver2 != null) {
            iVehicleSpeedObserver2.onVehicleSpeedChanged(15.0d, -1);
        } else {
            Log.d(TAG, "vehicleSpeedObserver_trend = null");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendPules(final byte[] bArr) {
        if (this.pool == null) {
            this.pool = Executors.newSingleThreadExecutor();
        }
        this.pool.execute(new Runnable() { // from class: com.ecarx.xui.adaptapi.vehicle.dashboard.Dashboard.4
            @Override // java.lang.Runnable
            public void run() {
                Log.d(Dashboard.TAG, "sendPules pool _onCallback  bytes:%s" + Arrays.toString(bArr));
                byte[] bArr2 = bArr;
                double d = ((double) ((((char) bArr2[2]) & 255) | (((((char) bArr2[1]) & 255) << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK))) * 0.05625d;
                if (Dashboard.this.vehicleSpeedObserver != null) {
                    if (Dashboard.this.reverse_flag) {
                        d = -d;
                    }
                    Dashboard.this.vehicleSpeedObserver.onVehicleSpeedChanged(d);
                } else if (System.currentTimeMillis() % 10000 < 150) {
                    Log.d(Dashboard.TAG, "onVehicleSpeedChanged vehicleSpeedObserver = null");
                }
                if (System.currentTimeMillis() % 10000 < 150) {
                    Log.d(Dashboard.TAG, "onVehicleSpeedChanged = " + d);
                }
            }
        });
    }

    private boolean getRpcOK(byte[] bArr, int i) {
        if (bArr != null && bArr.length > i) {
            return true;
        }
        Log.d(TAG, "RPC not get");
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setReverse(byte b) {
        if (b == 2) {
            this.reverse_flag = true;
            Log.d(TAG, "reverse status");
        } else {
            this.reverse_flag = false;
            Log.d(TAG, "not reverse status");
        }
    }
}
