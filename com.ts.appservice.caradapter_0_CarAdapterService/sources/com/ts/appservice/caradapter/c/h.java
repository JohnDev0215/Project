package com.ts.appservice.caradapter.c;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.ts.lib.car.CarNotConnectedException;
import com.ts.lib.car.Log;
import com.ts.lib.car.hardware.cloudmessage.CarVendorCloudMessageManager;
import com.ts.lib.car.hardware.cloudmessage.CloudMessageEventChangeValue;
import com.ts.lib.car.hardware.collectdata.CarVendorCollectdataManager;
import com.ts.lib.car.hardware.collectdata.CollectdataEventChangeValue;
import com.ts.lib.car.hardware.yun.CarVendorYunManager;
import com.ts.lib.car.hardware.yun.YunEventChangeValue;
import com.ts.lib.caradapter.cloud.CarCloudPacket;
import com.ts.lib.caradapter.cloud.CarCollectDataPacket;
import com.ts.lib.caradapter.cloud.CarRemotePacket;
import com.ts.lib.caradapter.cloud.ICarCloudCallback;
import com.ts.lib.caradapter.cloud.ICarCloudService;
import com.ts.lib.caradapter.common.utilities.LogUtil;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: CarCloudServiceImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class h extends ICarCloudService.Stub implements com.ts.appservice.caradapter.d.c, com.ts.appservice.caradapter.d.d {
    private static final String l = h.class.getSimpleName();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Context f69b;
    private com.ts.appservice.caradapter.a.d d;
    private a e;
    private CarVendorCloudMessageManager h;
    private CarVendorYunManager i;
    private CarVendorCollectdataManager j;
    private boolean c = false;
    private CarRemotePacket f = new CarRemotePacket();
    private CarCollectDataPacket g = new CarCollectDataPacket();
    private HashMap<String, Byte> k = new HashMap<>();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final HandlerThread f68a = com.ts.appservice.caradapter.e.b.a("VEHICLE-CLOUD");

    /* JADX INFO: compiled from: CarCloudServiceImpl.java */
    private final class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            int i = message.what;
            if (i == 1) {
                h.this.p(message.arg1, message.arg2, (CloudMessageEventChangeValue) message.obj);
                return;
            }
            if (i == 2) {
                h.this.r(message.arg1, message.arg2, (YunEventChangeValue) message.obj);
                return;
            }
            if (i == 3) {
                h.this.q(message.arg1, message.arg2, (CollectdataEventChangeValue) message.obj);
                return;
            }
            LogUtil.error(h.l, "Unexpected message: " + message.what);
        }
    }

    public h(Context context) {
        this.f69b = context;
        this.d = new com.ts.appservice.caradapter.a.d(this.f69b);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p(int i, int i2, CloudMessageEventChangeValue cloudMessageEventChangeValue) {
        String str = l;
        LogUtil.optionError(str, "cloud event property is " + i);
        if (561049648 == i) {
            LogUtil.optionError(str, "cloud event value property id is " + i + " value is " + cloudMessageEventChangeValue.toString());
            byte[] cloudMessage = cloudMessageEventChangeValue.getCloudMessage();
            LogUtil.optionError(str, cloudMessage.length + " content:" + Arrays.toString(cloudMessage));
            try {
                byte b2 = cloudMessage[1];
                byte b3 = cloudMessage[2];
                byte[] bArr = new byte[cloudMessage.length - 5];
                for (int i3 = 5; i3 < cloudMessage.length; i3++) {
                    bArr[i3 - 5] = cloudMessage[i3];
                }
                CarCloudPacket carCloudPacketObtain = CarCloudPacket.obtain();
                carCloudPacketObtain.setCanId(new byte[]{b3, b2});
                carCloudPacketObtain.setData(bArr);
                carCloudPacketObtain.setChildId(bArr[0]);
                this.d.q(i, i2, carCloudPacketObtain);
                cloudMessageEventChangeValue.recycle();
            } catch (Exception unused) {
                LogUtil.info(str, "data resolve is error!");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q(int i, int i2, CollectdataEventChangeValue collectdataEventChangeValue) {
        String str = l;
        try {
            if (i != 559969290) {
                switch (i) {
                    case 557872128:
                        int driverMotorSpeedFront = collectdataEventChangeValue.getDriverMotorSpeedFront();
                        this.g.setDriverMotorSpeedFront(driverMotorSpeedFront);
                        LogUtil.verbose(str, "ID_DRIVER_MOTOR_SPEED_FRONT value is " + driverMotorSpeedFront);
                        break;
                    case 557872129:
                        int driverMotorSpeedRear = collectdataEventChangeValue.getDriverMotorSpeedRear();
                        this.g.setDriverMotorSpeedRear(driverMotorSpeedRear);
                        LogUtil.verbose(str, "ID_DRIVER_MOTOR_SPEED_REAR value is " + driverMotorSpeedRear);
                        break;
                    case 557872130:
                        int driverMotorTorqueFront = collectdataEventChangeValue.getDriverMotorTorqueFront();
                        this.g.setDriverMotorTorqueFront(driverMotorTorqueFront);
                        LogUtil.verbose(str, "ID_DRIVER_MOTOR_TORQUE_FRONT value is " + driverMotorTorqueFront);
                        break;
                    case 557872131:
                        int driverMotorTorqueRear = collectdataEventChangeValue.getDriverMotorTorqueRear();
                        this.g.setDriverMotorTorqueRear(driverMotorTorqueRear);
                        LogUtil.verbose(str, "ID_DRIVER_MOTOR_TORQUE_REAR value is " + driverMotorTorqueRear);
                        break;
                    case 557872132:
                        int driverMotorTemperatureFront = collectdataEventChangeValue.getDriverMotorTemperatureFront();
                        this.g.setDriverMotorTemperatureFront(driverMotorTemperatureFront);
                        LogUtil.verbose(str, "ID_DRIVER_MOTOR_TEMPERATURE_FRONT value is " + driverMotorTemperatureFront);
                        break;
                    case 557872133:
                        int driverMotorTemperatureRear = collectdataEventChangeValue.getDriverMotorTemperatureRear();
                        this.g.setDriverMotorTemperatureRear(driverMotorTemperatureRear);
                        LogUtil.verbose(str, "ID_DRIVER_MOTOR_TEMPERATURE_REAR value is " + driverMotorTemperatureRear);
                        break;
                    case 557872134:
                        int motorMCUGeneratrixVoltFront = collectdataEventChangeValue.getMotorMCUGeneratrixVoltFront();
                        this.g.setMotorMCUGeneratrixVoltFront(motorMCUGeneratrixVoltFront);
                        LogUtil.verbose(str, "ID_MOTOR_MCU_GENERATRIX_VOLT_FRONT value is " + motorMCUGeneratrixVoltFront);
                        break;
                    case 557872135:
                        int motorMCUGeneratrixVoltRear = collectdataEventChangeValue.getMotorMCUGeneratrixVoltRear();
                        this.g.setMotorMCUGeneratrixVoltRear(motorMCUGeneratrixVoltRear);
                        LogUtil.verbose(str, "ID_MOTOR_MCU_GENERATRIX_VOLT_REAR value is " + motorMCUGeneratrixVoltRear);
                        break;
                    case 557872136:
                        int motorMCUGeneratrixCurrentFront = collectdataEventChangeValue.getMotorMCUGeneratrixCurrentFront();
                        this.g.setMotorMCUGeneratrixCurrentFront(motorMCUGeneratrixCurrentFront);
                        LogUtil.verbose(str, "ID_MOTOR_MCU_GENERATRIX_CURRENT_FRONT value is " + motorMCUGeneratrixCurrentFront);
                        break;
                    case 557872137:
                        int motorMCUGeneratrixCurrentRear = collectdataEventChangeValue.getMotorMCUGeneratrixCurrentRear();
                        this.g.setMotorMCUGeneratrixCurrentRear(motorMCUGeneratrixCurrentRear);
                        LogUtil.verbose(str, "ID_MOTOR_MCU_GENERATRIX_CURRENT_FRONT value is " + motorMCUGeneratrixCurrentRear);
                        break;
                    default:
                        switch (i) {
                            case 557872139:
                                int dCChargingFault = collectdataEventChangeValue.getDCChargingFault();
                                this.g.setDCChargingFault(dCChargingFault);
                                LogUtil.verbose(str, "ID_DC_CHARGING_FAULT value is " + dCChargingFault);
                                break;
                            case 557872140:
                                int waterTemperationAlarm = collectdataEventChangeValue.getWaterTemperationAlarm();
                                this.g.setWaterTemperationAlarm(waterTemperationAlarm);
                                LogUtil.verbose(str, "ID_WATER_TEMPERATION_ALARM value is " + waterTemperationAlarm);
                                break;
                            case 557872141:
                                int collision = collectdataEventChangeValue.getCollision();
                                this.g.setCollision(collision);
                                LogUtil.verbose(str, "ID_COLLISION value is " + collision);
                                break;
                        }
                        break;
                }
            } else {
                float engineSpeed = collectdataEventChangeValue.getEngineSpeed();
                this.g.setEngineSpeed(engineSpeed);
                LogUtil.verbose(str, "ID_ENGINE_SPEED value is " + engineSpeed);
            }
            this.d.o(i, i2, this.g);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r(int i, int i2, YunEventChangeValue yunEventChangeValue) {
        String str = l;
        try {
            switch (i) {
                case 557846044:
                    break;
                case 557846045:
                default:
                    Log.verbose(str, "break other properties ,except yun");
                    break;
                case 557846046:
                    int leftFrontDoor = yunEventChangeValue.getLeftFrontDoor();
                    this.f.setLeftFrontDoor(leftFrontDoor);
                    LogUtil.verbose(str, "setLeftFrontDoor value is " + leftFrontDoor);
                    break;
                case 557846047:
                    int rightFrontDoor = yunEventChangeValue.getRightFrontDoor();
                    this.f.setRightFrontDoor(rightFrontDoor);
                    LogUtil.verbose(str, "setRightFrontDoor value is " + rightFrontDoor);
                    break;
                case 557846048:
                    int leftBackDoor = yunEventChangeValue.getLeftBackDoor();
                    this.f.setLeftRearDoor(leftBackDoor);
                    LogUtil.verbose(str, "setLeftRearDoor value is " + leftBackDoor);
                    break;
                case 557846049:
                    int rightBackDoor = yunEventChangeValue.getRightBackDoor();
                    this.f.setRightRearDoor(rightBackDoor);
                    LogUtil.verbose(str, "setRightRearDoor value is " + rightBackDoor);
                    break;
                case 557846050:
                    int functionNumber497 = yunEventChangeValue.getFunctionNumber497();
                    this.f.setSearchCarType(functionNumber497);
                    LogUtil.verbose(str, "setSearchCarType value is " + functionNumber497);
                    break;
                case 557846051:
                    int contrlResultsFBInfo497s16 = yunEventChangeValue.getContrlResultsFBInfo497s16();
                    this.f.setSearchCallback(contrlResultsFBInfo497s16);
                    LogUtil.verbose(str, "setSearchCallback value is " + contrlResultsFBInfo497s16);
                    break;
                case 557846052:
                    int failureReasons497s16 = yunEventChangeValue.getFailureReasons497s16();
                    this.f.setSearchFailed(failureReasons497s16);
                    LogUtil.verbose(str, "setSearchFailed value is " + failureReasons497s16);
                    break;
                case 557846053:
                    int engStartResultFdbkInfo = yunEventChangeValue.getEngStartResultFdbkInfo();
                    this.f.setStartEngineResult(engStartResultFdbkInfo);
                    LogUtil.verbose(str, "setStartEngineResult value is " + engStartResultFdbkInfo);
                    break;
                case 557846054:
                    int engStopResultFdbkInfo = yunEventChangeValue.getEngStopResultFdbkInfo();
                    this.f.setCloseEngineResult(engStopResultFdbkInfo);
                    LogUtil.verbose(str, "setCloseEngineResult value is " + engStopResultFdbkInfo);
                    break;
                case 557846055:
                    int aCHMCACOpenRsltFdbckInfor = yunEventChangeValue.getACHMCACOpenRsltFdbckInfor();
                    this.f.setAcStartResult(aCHMCACOpenRsltFdbckInfor);
                    LogUtil.verbose(str, "setAcStartResult value is " + aCHMCACOpenRsltFdbckInfor);
                    break;
                case 557846056:
                    int aCHMCACFailureCause = yunEventChangeValue.getACHMCACFailureCause();
                    this.f.setAcStartFail(aCHMCACFailureCause);
                    LogUtil.verbose(str, "setAcStartFail value is " + aCHMCACFailureCause);
                    break;
                case 557846057:
                    int aVCloseResultsFeedback = yunEventChangeValue.getAVCloseResultsFeedback();
                    this.f.setAcCloseFail(aVCloseResultsFeedback);
                    LogUtil.verbose(str, "setAcCloseFail value is " + aVCloseResultsFeedback);
                    break;
                case 557846058:
                    int aCHMCACShutdownFailReason = yunEventChangeValue.getACHMCACShutdownFailReason();
                    this.f.setAcCloseResult(aCHMCACShutdownFailReason);
                    LogUtil.verbose(str, "setAcCloseResult value is " + aCHMCACShutdownFailReason);
                    break;
                case 557846059:
                    int steeringWheelHeatingState = yunEventChangeValue.getSteeringWheelHeatingState();
                    this.f.setSteeringHeatingResult(steeringWheelHeatingState);
                    LogUtil.verbose(str, "setSteeringHeatingResult value is " + steeringWheelHeatingState);
                    break;
                case 557846060:
                    int pilotSeatVtilatStat = yunEventChangeValue.getPilotSeatVtilatStat();
                    this.f.setRemoteSeatDriverVent(pilotSeatVtilatStat);
                    LogUtil.verbose(str, "setRemoteSeatDriverVent value is " + pilotSeatVtilatStat);
                    break;
                case 557846061:
                    int pilotSeatHeatStat = yunEventChangeValue.getPilotSeatHeatStat();
                    this.f.setRemoteSeatDriverHeat(pilotSeatHeatStat);
                    LogUtil.verbose(str, "setRemoteSeatDriverHeat value is " + pilotSeatHeatStat);
                    break;
                case 557846062:
                    int copilotSeatVtilatStat4FA = yunEventChangeValue.getCopilotSeatVtilatStat4FA();
                    this.f.setRemoteSeatPsgVent(copilotSeatVtilatStat4FA);
                    LogUtil.verbose(str, "setRemoteSeatPsgVent value is " + copilotSeatVtilatStat4FA);
                    break;
                case 557846063:
                    int copilotSeatHeatStat4FA = yunEventChangeValue.getCopilotSeatHeatStat4FA();
                    this.f.setRemoteSeatPsgHeat(copilotSeatHeatStat4FA);
                    LogUtil.verbose(str, "setRemoteSeatPsgHeat value is " + copilotSeatHeatStat4FA);
                    break;
                case 557846064:
                    int rLSeatVtilatStat486 = yunEventChangeValue.getRLSeatVtilatStat486();
                    this.f.setRemoteSeatRearLeftVent(rLSeatVtilatStat486);
                    LogUtil.verbose(str, "setRemoteSeatRearLeftVent value is " + rLSeatVtilatStat486);
                    break;
                case 557846065:
                    int rightFrontDoor2 = yunEventChangeValue.getRightFrontDoor();
                    this.f.setRemoteSeatRearLeftHeat(rightFrontDoor2);
                    LogUtil.verbose(str, "setRemoteSeatRearLeftHeat value is " + rightFrontDoor2);
                    break;
                case 557846066:
                    int rRSeatVtilatStat486 = yunEventChangeValue.getRRSeatVtilatStat486();
                    this.f.setRemoteSeatRearRightVent(rRSeatVtilatStat486);
                    LogUtil.verbose(str, "setRemoteSeatRearRightVent value is " + rRSeatVtilatStat486);
                    break;
                case 557846067:
                    int rRSeatHeatStat486 = yunEventChangeValue.getRRSeatHeatStat486();
                    this.f.setRemoteSeatRearRightHeat(rRSeatHeatStat486);
                    LogUtil.verbose(str, "setRemoteSeatRearRightHeat value is " + rRSeatHeatStat486);
                    break;
            }
            this.d.r(i, i2, this.f);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void s() {
        LogUtil.info(l, "quit");
        if (this.f68a != null) {
            a aVar = this.e;
            if (aVar != null) {
                aVar.removeCallbacksAndMessages(null);
            }
            this.f68a.quitSafely();
        }
    }

    @Override // com.ts.appservice.caradapter.d.c
    public void a(YunEventChangeValue yunEventChangeValue) {
        String str = l;
        if (yunEventChangeValue == null) {
            LogUtil.error(str, "onRemoteChangeEvent:value=null");
            return;
        }
        int propertyId = yunEventChangeValue.getPropertyId();
        LogUtil.optionError(str, "onRemoteChangeEvent-" + propertyId);
        Message messageObtain = Message.obtain();
        messageObtain.what = 2;
        messageObtain.arg1 = propertyId;
        messageObtain.arg2 = yunEventChangeValue.getAreaId();
        messageObtain.obj = yunEventChangeValue;
        this.e.sendMessage(messageObtain);
    }

    @Override // com.ts.appservice.caradapter.d.d
    public void b(CollectdataEventChangeValue collectdataEventChangeValue) {
        String str = l;
        if (collectdataEventChangeValue == null) {
            LogUtil.error(str, "onCollectDataChangeEvent:value=null");
            return;
        }
        int propertyId = collectdataEventChangeValue.getPropertyId();
        LogUtil.verbose(str, "onCollectDataChangeEvent-" + propertyId);
        Message messageObtain = Message.obtain();
        messageObtain.what = 3;
        messageObtain.arg1 = propertyId;
        messageObtain.arg2 = collectdataEventChangeValue.getAreaId();
        messageObtain.obj = collectdataEventChangeValue;
        this.e.sendMessage(messageObtain);
    }

    public byte[] getCanBytes(int i, int i2) {
        LogUtil.verbose(l, "Service getCanBytes");
        return new byte[0];
    }

    public void getCanBytesSync(int i, int i2) {
        LogUtil.verbose(l, "Service getCanBytesSync");
    }

    public int getCollision() {
        CarVendorCollectdataManager carVendorCollectdataManager = this.j;
        if (carVendorCollectdataManager == null) {
            return 0;
        }
        try {
            int collision = carVendorCollectdataManager.getCollision();
            LogUtil.verbose(l, "getCollision" + collision);
            return collision;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getDCChargingFault() {
        CarVendorCollectdataManager carVendorCollectdataManager = this.j;
        if (carVendorCollectdataManager == null) {
            return 0;
        }
        try {
            int dCChargingFault = carVendorCollectdataManager.getDCChargingFault();
            LogUtil.verbose(l, "getDCChargingFault" + dCChargingFault);
            return dCChargingFault;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getDmVersionCode() {
        String str = l;
        LogUtil.verbose(str, "Service getDmVersionCode");
        CarVendorCollectdataManager carVendorCollectdataManager = this.j;
        if (carVendorCollectdataManager == null) {
            return 0;
        }
        try {
            int dmVersionCodeR = carVendorCollectdataManager.getDmVersionCodeR();
            LogUtil.verbose(str, "getDmVersionCode" + dmVersionCodeR);
            return dmVersionCodeR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getDriveMoterSerial() {
        return 0;
    }

    public int getDriverMotorSpeed(int i) {
        String str = l;
        LogUtil.verbose(str, "Service getDriverMotorSpeed" + i);
        CarVendorCollectdataManager carVendorCollectdataManager = this.j;
        if (carVendorCollectdataManager == null) {
            return 0;
        }
        int driverMotorSpeedRear = -1;
        try {
            if (1 == i) {
                driverMotorSpeedRear = carVendorCollectdataManager.getDriverMotorSpeedFront();
            } else if (2 == i) {
                driverMotorSpeedRear = carVendorCollectdataManager.getDriverMotorSpeedRear();
            } else {
                LogUtil.verbose(str, "Service getDriverMotorSpeed param error");
            }
            LogUtil.verbose(str, "getDriverMotorSpeed" + driverMotorSpeedRear);
            return driverMotorSpeedRear;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getDriverMotorTemperature(int i) {
        String str = l;
        LogUtil.verbose(str, "Service getDriverMotorTemperature" + i);
        CarVendorCollectdataManager carVendorCollectdataManager = this.j;
        if (carVendorCollectdataManager == null) {
            return 0;
        }
        int driverMotorTemperatureRear = -1;
        try {
            if (1 == i) {
                driverMotorTemperatureRear = carVendorCollectdataManager.getDriverMotorTemperatureFront();
            } else if (2 == i) {
                driverMotorTemperatureRear = carVendorCollectdataManager.getDriverMotorTemperatureRear();
            } else {
                LogUtil.verbose(str, "Service getDriverMotorTemperature param error");
            }
            LogUtil.verbose(str, "getDriverMotorTemperature" + driverMotorTemperatureRear);
            return driverMotorTemperatureRear;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getDriverMotorTorque(int i) {
        String str = l;
        LogUtil.verbose(str, "Service getDriverMotorTorque" + i);
        CarVendorCollectdataManager carVendorCollectdataManager = this.j;
        if (carVendorCollectdataManager == null) {
            return 0;
        }
        int driverMotorTorqueRear = -1;
        try {
            if (1 == i) {
                driverMotorTorqueRear = carVendorCollectdataManager.getDriverMotorTorqueFront();
            } else if (2 == i) {
                driverMotorTorqueRear = carVendorCollectdataManager.getDriverMotorTorqueRear();
            } else {
                LogUtil.verbose(str, "Service getDriverMotorTorque param error");
            }
            LogUtil.verbose(str, "getDriverMotorTorque" + driverMotorTorqueRear);
            return driverMotorTorqueRear;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public double getEngineSpeed() {
        CarVendorCollectdataManager carVendorCollectdataManager = this.j;
        if (carVendorCollectdataManager == null) {
            return 0.0d;
        }
        try {
            double engineSpeed = carVendorCollectdataManager.getEngineSpeed();
            LogUtil.verbose(l, "getEngineSpeed" + engineSpeed);
            return engineSpeed;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0.0d;
        }
    }

    public int getMotorMCUGeneratrixCurrent(int i) {
        String str = l;
        LogUtil.verbose(str, "Service getMotorMCUGeneratrixVolt" + i);
        CarVendorCollectdataManager carVendorCollectdataManager = this.j;
        if (carVendorCollectdataManager == null) {
            return 0;
        }
        int motorMCUGeneratrixCurrentRear = -1;
        try {
            if (1 == i) {
                motorMCUGeneratrixCurrentRear = carVendorCollectdataManager.getMotorMCUGeneratrixCurrentFront();
            } else if (2 == i) {
                motorMCUGeneratrixCurrentRear = carVendorCollectdataManager.getMotorMCUGeneratrixCurrentRear();
            } else {
                LogUtil.verbose(str, "Service getMotorMCUGeneratrixCurrent param error");
            }
            LogUtil.verbose(str, "getMotorMCUGeneratrixCurrent" + motorMCUGeneratrixCurrentRear);
            return motorMCUGeneratrixCurrentRear;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getMotorMCUGeneratrixVolt(int i) {
        String str = l;
        LogUtil.verbose(str, "Service getMotorMCUGeneratrixVolt" + i);
        CarVendorCollectdataManager carVendorCollectdataManager = this.j;
        if (carVendorCollectdataManager == null) {
            return 0;
        }
        int motorMCUGeneratrixVoltRear = -1;
        try {
            if (1 == i) {
                motorMCUGeneratrixVoltRear = carVendorCollectdataManager.getMotorMCUGeneratrixVoltFront();
            } else if (2 == i) {
                motorMCUGeneratrixVoltRear = carVendorCollectdataManager.getMotorMCUGeneratrixVoltRear();
            } else {
                LogUtil.verbose(str, "Service getMotorMCUGeneratrixVolt param error");
            }
            LogUtil.verbose(str, "getMotorMCUGeneratrixVolt" + motorMCUGeneratrixVoltRear);
            return motorMCUGeneratrixVoltRear;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getRemainBattery() {
        String str = l;
        LogUtil.verbose(str, "Service getRemainBattery");
        CarVendorYunManager carVendorYunManager = this.i;
        if (carVendorYunManager != null) {
            try {
                int remainBattery = carVendorYunManager.getRemainBattery();
                if (remainBattery == 0) {
                    LogUtil.verbose(str, "getRemainBattery is null");
                    return 0;
                }
                LogUtil.verbose(str, "getRemainBattery=" + remainBattery);
                return remainBattery;
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public byte getVehicleSpeed() {
        String str = l;
        LogUtil.verbose(str, "Service getVehicleSpeed");
        CarVendorYunManager carVendorYunManager = this.i;
        if (carVendorYunManager != null) {
            try {
                byte b2 = carVendorYunManager.getVehicleSpeed()[0];
                if (b2 == 0) {
                    LogUtil.verbose(str, "getVehicleSpeed is null");
                    return (byte) 0;
                }
                LogUtil.verbose(str, "getVehicleSpeed=" + ((int) b2));
                return b2;
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
        return (byte) 0;
    }

    public int getWaterTemperationAlarm() {
        CarVendorCollectdataManager carVendorCollectdataManager = this.j;
        if (carVendorCollectdataManager == null) {
            return 0;
        }
        try {
            int waterTemperationAlarm = carVendorCollectdataManager.getWaterTemperationAlarm();
            LogUtil.verbose(l, "getWaterTemperationAlarm" + waterTemperationAlarm);
            return waterTemperationAlarm;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public byte getWaterThermometer() {
        String str = l;
        LogUtil.verbose(str, "Service getWaterThermometer");
        CarVendorYunManager carVendorYunManager = this.i;
        if (carVendorYunManager != null) {
            try {
                byte b2 = carVendorYunManager.getWaterThermometer()[0];
                if (b2 == 0) {
                    LogUtil.error(str, "getWaterThermometer is null");
                    return (byte) 0;
                }
                LogUtil.verbose(str, "getWaterThermometer=" + ((int) b2));
                return b2;
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
        return (byte) 0;
    }

    public void n() {
        s();
        synchronized (h.class) {
            this.k.clear();
        }
        com.ts.appservice.caradapter.a.d dVar = this.d;
        if (dVar != null) {
            this.c = false;
            dVar.g();
        }
        this.f69b = null;
    }

    public void o(CarVendorCloudMessageManager carVendorCloudMessageManager, CarVendorYunManager carVendorYunManager, CarVendorCollectdataManager carVendorCollectdataManager) {
        this.h = carVendorCloudMessageManager;
        this.i = carVendorYunManager;
        this.j = carVendorCollectdataManager;
        try {
            if (this.e == null) {
                this.e = new a(this.f68a.getLooper());
                LogUtil.info(l, "initCloudManager");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        com.ts.appservice.caradapter.a.d dVar = this.d;
        if (dVar != null) {
            this.c = true;
            dVar.p();
        }
    }

    public void remoteAcControl(int i, int i2, int i3, int i4) {
        String str = l;
        LogUtil.verbose(str, "Service remoteAcControl " + i + "," + i2 + "," + i3 + "," + i4);
        CarVendorYunManager carVendorYunManager = this.i;
        if (carVendorYunManager != null) {
            try {
                carVendorYunManager.setRemoteAcControlStart(i);
                int iB = com.ts.appservice.caradapter.e.d.b(i2);
                int iH = com.ts.appservice.caradapter.e.d.h(i2);
                int iC = com.ts.appservice.caradapter.e.d.c(i3);
                int iG = com.ts.appservice.caradapter.e.d.g(i3);
                int iC2 = com.ts.appservice.caradapter.e.d.c(i4);
                int iG2 = com.ts.appservice.caradapter.e.d.g(i4);
                int iA = com.ts.appservice.caradapter.e.d.a(iC2);
                int iE = com.ts.appservice.caradapter.e.d.e(iC2);
                int iA2 = com.ts.appservice.caradapter.e.d.a(iG2);
                int iE2 = com.ts.appservice.caradapter.e.d.e(iG2);
                LogUtil.verbose(str, "p1 is " + i + " p2 height3 is " + iB + " p2 low5 is" + iH + " p3 height4 is " + iC + " p3 low4 is " + iG + " p4 low2low is " + iE2 + " p4 low2height is " + iA2 + " p4 height2low is " + iE + " p4 height2height is " + iA);
                this.i.setRemoteAcControlDriverTemperature(iH);
                this.i.setRemoteAcControlStartTime(iB);
                this.i.setRemoteAcControlAirGear(iG);
                this.i.setRemoteAcControlWindPattern(iC);
                this.i.setRemoteAcControlSwitch(iE2);
                this.i.setRemoteAcControlManner(iA2);
                this.i.setRemoteAcControlCycleMode(iE);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
    }

    public void remoteControl(int i) {
        LogUtil.verbose(l, "Service remoteControl" + i);
        CarVendorYunManager carVendorYunManager = this.i;
        if (carVendorYunManager != null) {
            try {
                if (i == 2) {
                    carVendorYunManager.setRemoteControlStartEngine(2);
                } else if (i == 5) {
                    carVendorYunManager.setRemoteControlUnlockFourDoors(5);
                } else if (i != 22) {
                    switch (i) {
                        case 7:
                            carVendorYunManager.setRemoteControlLockout(7);
                            break;
                        case 8:
                            carVendorYunManager.setRemoteControlStopAc(8);
                            break;
                        case 9:
                            carVendorYunManager.setRemoteControlStopEngine(9);
                            break;
                        case 10:
                            carVendorYunManager.setRemoteControlSearchFlashingLightsAndWhistle(10);
                            break;
                        default:
                            return;
                    }
                } else {
                    carVendorYunManager.setRemoteControlSearchFlashingLight(22);
                }
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
    }

    public void remoteSeatControl(int i, int i2, int i3) {
        String str = l;
        LogUtil.verbose(str, "Service remoteSeatControl" + i + "," + i2 + "," + i3);
        CarVendorYunManager carVendorYunManager = this.i;
        if (carVendorYunManager != null) {
            try {
                carVendorYunManager.setRemoteSeatControlFrontSeats(i);
                int iF = com.ts.appservice.caradapter.e.d.f(i2);
                int iD = com.ts.appservice.caradapter.e.d.d(i2);
                int iF2 = com.ts.appservice.caradapter.e.d.f(iD);
                int iF3 = com.ts.appservice.caradapter.e.d.f(i3);
                int iD2 = com.ts.appservice.caradapter.e.d.d(i3);
                int iF4 = com.ts.appservice.caradapter.e.d.f(iD2);
                LogUtil.verbose(str, "p1 is " + i + " p2 Low3 is " + iF + " p2 Height5 is" + iD + " p2 Height2low3 is" + iF2 + " p3 Low3 is " + iF3 + " p3 Height5 is " + iD2 + " p3 Height2low3 is " + iF4);
                this.i.setRemoteSeatControlDriverVentilate(iF);
                this.i.setRemoteSeatControlDriverHeat(iF2);
                this.i.setRemoteSeatControlCopilotVentilate(iF3);
                this.i.setRemoteSeatControlCopilotHeat(iF4);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
    }

    public void remoteSeatRearControl(int i, int i2, int i3, int i4, int i5) {
        String str = l;
        LogUtil.verbose(str, "Service remoteSeatControl" + i + "," + i2 + "," + i3 + "," + i4 + "," + i5);
        CarVendorYunManager carVendorYunManager = this.i;
        if (carVendorYunManager != null) {
            try {
                carVendorYunManager.setRemoteSeatControlFrontSeats(i);
                int iF = com.ts.appservice.caradapter.e.d.f(i2);
                int iD = com.ts.appservice.caradapter.e.d.d(i2);
                int iF2 = com.ts.appservice.caradapter.e.d.f(iD);
                int iF3 = com.ts.appservice.caradapter.e.d.f(i3);
                int iD2 = com.ts.appservice.caradapter.e.d.d(i3);
                int iF4 = com.ts.appservice.caradapter.e.d.f(iD2);
                int iF5 = com.ts.appservice.caradapter.e.d.f(i4);
                int iD3 = com.ts.appservice.caradapter.e.d.d(i4);
                int iF6 = com.ts.appservice.caradapter.e.d.f(iD3);
                int iF7 = com.ts.appservice.caradapter.e.d.f(i5);
                int iD4 = com.ts.appservice.caradapter.e.d.d(i5);
                int iF8 = com.ts.appservice.caradapter.e.d.f(iD4);
                LogUtil.verbose(str, "p1 is " + i + " p2 Low3 is " + iF + " p2 Height5 is" + iD + " p2 Height2low3 is" + iF2 + " p3 Low3 is " + iF3 + " p3 Height5 is " + iD2 + " p3 Height2low3 is " + iF4 + " p4 Low3 is " + iF5 + " p4 Height5 is " + iD3 + " p4 Height2low3 is " + iF6 + " p5 Low3 is " + iF7 + " p5 Height5 is " + iD4 + " p5 Height2low3 is " + iF8);
                this.i.setRemoteSeatControlDriverVentilate(iF);
                this.i.setRemoteSeatControlDriverHeat(iF2);
                this.i.setRemoteSeatControlCopilotVentilate(iF3);
                this.i.setRemoteSeatControlCopilotHeat(iF4);
                this.i.setRLeftSeatVtilatStat(iF5);
                this.i.setRLeftSeatHeatStat(iF6);
                this.i.setRRightSeatVtilatStat(iF7);
                this.i.setRRightSeatHeatStat(iF8);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
    }

    public void remoteSteeringHeatingControl(int i, int i2) {
        LogUtil.verbose(l, "Service remoteSeatControl" + i + "," + i2);
        CarVendorYunManager carVendorYunManager = this.i;
        if (carVendorYunManager != null) {
            try {
                carVendorYunManager.setRemoteControl33S(i);
                this.i.setSteeringWheelHeat(i2);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
    }

    public void remoteUnlockFourDoors() {
        LogUtil.verbose(l, "Service remoteUnlockFourDoors");
        CarVendorYunManager carVendorYunManager = this.i;
        if (carVendorYunManager != null) {
            try {
                carVendorYunManager.setRemoteUnlockFourDoors(5);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean subscribeCarCloudCallback(ICarCloudCallback iCarCloudCallback) {
        if (this.d == null || iCarCloudCallback == null) {
            return false;
        }
        LogUtil.verbose(l, "into subscribe: " + iCarCloudCallback.toString());
        this.d.h(iCarCloudCallback);
        if (!this.c) {
            return true;
        }
        this.d.p();
        return true;
    }

    public void subscribeMessageTarget(ICarCloudCallback iCarCloudCallback, List<CarCloudPacket> list) {
        String str = l;
        LogUtil.info(str, "subscribeMessageTarget");
        if (this.h != null) {
            synchronized (h.class) {
                LogUtil.info(str, "subscribe message is " + list.toString());
                this.d.n(iCarCloudCallback, list);
                HashSet hashSet = new HashSet();
                Iterator<CarCloudPacket> it = list.iterator();
                while (it.hasNext()) {
                    hashSet.add(Arrays.toString(it.next().getCanId()));
                }
                this.h.setCanId(hashSet);
                for (CarCloudPacket carCloudPacket : list) {
                    String string = Arrays.toString(carCloudPacket.getCanId());
                    if (this.k.containsKey(string)) {
                        LogUtil.info(str, "remove duplicate subscribe can id=" + string);
                    } else {
                        this.k.put(string, Byte.valueOf(carCloudPacket.getChildId()));
                        byte length = carCloudPacket.getMask() != null ? (byte) carCloudPacket.getMask().length : (byte) 0;
                        byte[] bArr = new byte[length + 5];
                        bArr[0] = 1;
                        bArr[1] = carCloudPacket.getCanId()[1];
                        bArr[2] = carCloudPacket.getCanId()[0];
                        bArr[4] = length;
                        if (carCloudPacket.getMask() != null) {
                            System.arraycopy(carCloudPacket.getMask(), 0, bArr, 5, length);
                        }
                        LogUtil.info(str, "subscribe message is " + Arrays.toString(bArr));
                        try {
                            this.h.setCloudMessage(bArr);
                        } catch (CarNotConnectedException e) {
                            e.printStackTrace();
                            LogUtil.info(str, "subscribe message is error");
                        }
                    }
                }
            }
        }
    }

    public void t(CarVendorCloudMessageManager carVendorCloudMessageManager, CarVendorYunManager carVendorYunManager, CarVendorCollectdataManager carVendorCollectdataManager) {
        this.h = carVendorCloudMessageManager;
        this.i = carVendorYunManager;
        this.j = carVendorCollectdataManager;
    }

    public boolean unsubscribeCarCloudCallback(ICarCloudCallback iCarCloudCallback) {
        if (this.d == null || iCarCloudCallback == null) {
            return false;
        }
        LogUtil.verbose(l, "into unsubscribe: " + iCarCloudCallback.toString());
        this.d.i(iCarCloudCallback);
        return true;
    }

    public void unsubscribeMessageTarget(ICarCloudCallback iCarCloudCallback, List<CarCloudPacket> list) {
        String str = l;
        LogUtil.info(str, "unsubscribeMessageTarget");
        if (this.h != null) {
            synchronized (h.class) {
                this.d.s(iCarCloudCallback, list);
                for (CarCloudPacket carCloudPacket : list) {
                    String string = Arrays.toString(carCloudPacket.getCanId());
                    if (this.k.containsKey(string)) {
                        LogUtil.info(str, "remove duplicate subscribe can id");
                        this.k.remove(string);
                        byte[] bArr = {2, carCloudPacket.getCanId()[1], carCloudPacket.getCanId()[0], 0, 0};
                        LogUtil.info(str, "unsubscribe message is " + Arrays.toString(bArr));
                        try {
                            this.h.setCloudMessage(bArr);
                        } catch (CarNotConnectedException e) {
                            e.printStackTrace();
                            LogUtil.info(str, "unsubscribe message is error");
                        }
                    }
                }
            }
        }
    }
}
