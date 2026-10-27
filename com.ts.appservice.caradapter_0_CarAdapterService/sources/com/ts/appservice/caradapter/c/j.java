package com.ts.appservice.caradapter.c;

import android.car.hardware.CarPropertyValue;
import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.SystemProperties;
import com.ts.appservice.caradapter.d.r;
import com.ts.appservice.caradapter.d.v;
import com.ts.appservice.caradapter.d.x;
import com.ts.avm.sdk.AvmManager;
import com.ts.lib.car.CarNotConnectedException;
import com.ts.lib.car.hardware.radar.CarVendorRadarManager;
import com.ts.lib.car.hardware.radar.RadarEventChangeValue;
import com.ts.lib.car.hardware.sensor.CarVendorSensorManager;
import com.ts.lib.car.hardware.sensor.SensorEventChangeValue;
import com.ts.lib.car.hardware.speed.CarVendorSpeedManager;
import com.ts.lib.car.hardware.speed.SpeedEventChangeValue;
import com.ts.lib.caradapter.common.utilities.LogUtil;
import com.ts.lib.caradapter.sensor.ICarSensorService;
import com.ts.lib.caradapter.sensor.ISensorCallback;
import com.ts.lib.caradapter.sensor.SensorPacket;

/* JADX INFO: compiled from: CarSensorServiceImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class j extends ICarSensorService.Stub implements v, r, x {
    private static final String F = j.class.getSimpleName();
    private SensorPacket A;
    private int B;
    private int C;
    private AvmManager D;
    private final com.ts.avm.sdk.a E;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f74a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f75b;
    private int c;
    private int d;
    private int e;
    private int f;
    private int g;
    private int h;
    private int i;
    private int j;
    private int k;
    private int l;
    private int m;
    private int n;
    private int o;
    private int p;
    private int q;
    private int r;
    private int s;
    private boolean t;
    private com.ts.appservice.caradapter.a.f u;
    private final HandlerThread v;
    private b w;
    private CarVendorRadarManager x;
    private CarVendorSensorManager y;
    private CarVendorSpeedManager z;

    /* JADX INFO: compiled from: CarSensorServiceImpl.java */
    class a implements com.ts.avm.sdk.a {
        a(j jVar) {
        }

        @Override // com.ts.avm.sdk.a
        public void a(int i, String str) {
        }

        @Override // com.ts.avm.sdk.a
        public void b() {
            LogUtil.info(j.F, "AvmManager onServiceConnected ");
        }

        @Override // com.ts.avm.sdk.a
        public void c() {
            LogUtil.info(j.F, "AvmManager onServiceDisConnected ");
        }
    }

    /* JADX INFO: compiled from: CarSensorServiceImpl.java */
    private final class b extends Handler {
        public b(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            int i = message.what;
            if (i == 2) {
                j.this.s(message.arg1, message.arg2, (SpeedEventChangeValue) message.obj);
            } else if (i == 6) {
                j.this.q(message.arg1, message.arg2, (RadarEventChangeValue) message.obj);
            } else {
                if (i != 7) {
                    return;
                }
                j.this.r(message.arg1, message.arg2, (SensorEventChangeValue) message.obj);
            }
        }
    }

    public j(Context context) {
        String str = F;
        this.t = false;
        this.A = new SensorPacket();
        this.E = new a(this);
        this.u = new com.ts.appservice.caradapter.a.f(context);
        this.v = com.ts.appservice.caradapter.e.b.a("VEHICLE-SENSOR");
        LogUtil.info(str, "start mAvmManager");
        AvmManager avmManagerB = AvmManager.b();
        this.D = avmManagerB;
        avmManagerB.c(context, this.E);
        LogUtil.info(str, "end mAvmManager");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q(int i, int i2, RadarEventChangeValue radarEventChangeValue) {
        String str = F;
        try {
            switch (i) {
                case 557861120:
                    int radarProbeStateFL = radarEventChangeValue.getRadarProbeStateFL();
                    this.i = radarProbeStateFL;
                    this.A.setRadarProbeStateFL(radarProbeStateFL);
                    LogUtil.verbose(str, "ID_RADAR_PROBE_STATE_FL:" + this.i);
                    break;
                case 557861121:
                    int radarProbeStateFML = radarEventChangeValue.getRadarProbeStateFML();
                    this.j = radarProbeStateFML;
                    this.A.setRadarProbeStateFML(radarProbeStateFML);
                    LogUtil.verbose(str, "ID_RADAR_PROBE_STATE_FML:" + this.j);
                    break;
                case 557861122:
                    int radarProbeStateFMR = radarEventChangeValue.getRadarProbeStateFMR();
                    this.k = radarProbeStateFMR;
                    this.A.setRadarProbeStateFMR(radarProbeStateFMR);
                    LogUtil.verbose(str, "ID_RADAR_PROBE_STATE_FMR:" + this.k);
                    break;
                case 557861123:
                    int radarProbeStateFR = radarEventChangeValue.getRadarProbeStateFR();
                    this.l = radarProbeStateFR;
                    this.A.setRadarProbeStateFR(radarProbeStateFR);
                    LogUtil.verbose(str, "ID_RADAR_PROBE_STATE_FR:" + this.l);
                    break;
                case 557861124:
                    int radarProbeStateMR = radarEventChangeValue.getRadarProbeStateMR();
                    this.m = radarProbeStateMR;
                    this.A.setRadarProbeStateMR(radarProbeStateMR);
                    LogUtil.verbose(str, "ID_RADAR_PROBE_STATE_MR:" + this.m);
                    break;
                case 557861125:
                    int radarProbeStateRL = radarEventChangeValue.getRadarProbeStateRL();
                    this.n = radarProbeStateRL;
                    this.A.setRadarProbeStateRL(radarProbeStateRL);
                    LogUtil.verbose(str, "ID_RADAR_PROBE_STATE_RL:" + this.n);
                    break;
                case 557861126:
                    int radarProbeStateRML = radarEventChangeValue.getRadarProbeStateRML();
                    this.o = radarProbeStateRML;
                    this.A.setRadarProbeStateRML(radarProbeStateRML);
                    LogUtil.verbose(str, "ID_RADAR_PROBE_STATE_RML:" + this.o);
                    break;
                case 557861127:
                    int radarProbeStateRMR = radarEventChangeValue.getRadarProbeStateRMR();
                    this.p = radarProbeStateRMR;
                    this.A.setRadarProbeStateRMR(radarProbeStateRMR);
                    LogUtil.verbose(str, "ID_RADAR_PROBE_STATE_RMR:" + this.p);
                    break;
                case 557861128:
                    int radarProbeStateRR = radarEventChangeValue.getRadarProbeStateRR();
                    this.q = radarProbeStateRR;
                    this.A.setRadarProbeStateRR(radarProbeStateRR);
                    LogUtil.verbose(str, "ID_RADAR_PROBE_STATE_RR:" + this.q);
                    break;
                case 557861129:
                    int radarObstacleDistanceFL = radarEventChangeValue.getRadarObstacleDistanceFL();
                    this.f74a = radarObstacleDistanceFL;
                    this.A.setRadarObstacleDistanceFL(radarObstacleDistanceFL);
                    LogUtil.verbose(str, "ID_RADAR_OBSTACLE_DISTANCE_FL:" + this.f74a);
                    break;
                case 557861130:
                    int radarObstacleDistanceFML = radarEventChangeValue.getRadarObstacleDistanceFML();
                    this.f75b = radarObstacleDistanceFML;
                    this.A.setRadarObstacleDistanceFML(radarObstacleDistanceFML);
                    LogUtil.verbose(str, "ID_RADAR_OBSTACLE_DISTANCE_FML:" + this.f75b);
                    break;
                case 557861131:
                    int radarObstacleDistanceFMR = radarEventChangeValue.getRadarObstacleDistanceFMR();
                    this.c = radarObstacleDistanceFMR;
                    this.A.setRadarObstacleDistanceFMR(radarObstacleDistanceFMR);
                    LogUtil.verbose(str, "ID_RADAR_OBSTACLE_DISTANCE_FMR:" + this.c);
                    break;
                case 557861132:
                    int radarObstacleDistanceFR = radarEventChangeValue.getRadarObstacleDistanceFR();
                    this.d = radarObstacleDistanceFR;
                    this.A.setRadarObstacleDistanceFR(radarObstacleDistanceFR);
                    LogUtil.verbose(str, "ID_RADAR_OBSTACLE_DISTANCE_FR:" + this.d);
                    break;
                case 557861133:
                    int radarObstacleDistanceRL = radarEventChangeValue.getRadarObstacleDistanceRL();
                    this.e = radarObstacleDistanceRL;
                    this.A.setRadarObstacleDistanceRL(radarObstacleDistanceRL);
                    LogUtil.verbose(str, "ID_RADAR_OBSTACLE_DISTANCE_RL:" + this.e);
                    break;
                case 557861134:
                    int radarObstacleDistanceRML = radarEventChangeValue.getRadarObstacleDistanceRML();
                    this.f = radarObstacleDistanceRML;
                    this.A.setRadarObstacleDistanceRML(radarObstacleDistanceRML);
                    LogUtil.verbose(str, "ID_RADAR_OBSTACLE_DISTANCE_RML:" + this.f);
                    break;
                case 557861135:
                    int radarObstacleDistanceRMR = radarEventChangeValue.getRadarObstacleDistanceRMR();
                    this.g = radarObstacleDistanceRMR;
                    this.A.setRadarObstacleDistanceRMR(radarObstacleDistanceRMR);
                    LogUtil.verbose(str, "ID_RADAR_OBSTACLE_DISTANCE_RMR:" + this.g);
                    break;
                case 557861136:
                    int radarObstacleDistanceRR = radarEventChangeValue.getRadarObstacleDistanceRR();
                    this.h = radarObstacleDistanceRR;
                    this.A.setRadarObstacleDistanceRR(radarObstacleDistanceRR);
                    LogUtil.verbose(str, "ID_RADAR_OBSTACLE_DISTANCE_RR:" + this.h);
                    break;
                case 557861137:
                    int reverseRadarSwitchStateR = radarEventChangeValue.getReverseRadarSwitchStateR();
                    this.r = reverseRadarSwitchStateR;
                    this.A.setReverseRadarSwitchStateR(reverseRadarSwitchStateR);
                    LogUtil.verbose(str, "ID_REVERSE_RADAR_SWITCH_STATE_R:" + this.r);
                    break;
                case 557861140:
                    int newReverseRadarSwitchStateR = radarEventChangeValue.getNewReverseRadarSwitchStateR();
                    this.s = newReverseRadarSwitchStateR;
                    this.A.setNewReverseRadarSwitchStateR(newReverseRadarSwitchStateR);
                    LogUtil.verbose(str, "ID_NEW_REVERSE_RADAR_SWITCH_STATE_R:" + this.s);
                    break;
            }
            this.u.l(i, i2, this.A);
            LogUtil.verbose(str, "notifyRadarEventValue:" + i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r(int i, int i2, SensorEventChangeValue sensorEventChangeValue) {
        String str = F;
        this.A.setPropertyId(i);
        try {
            switch (i) {
                case 557861632:
                    int lightIntensity = sensorEventChangeValue.getLightIntensity();
                    this.A.setLightIntensity(lightIntensity);
                    LogUtil.verbose(str, "ID_LIGHT_INTENSITY:" + lightIntensity);
                    break;
                case 557861635:
                    int slope = sensorEventChangeValue.getSlope();
                    this.A.setSlope(slope);
                    LogUtil.verbose(str, "ID_SLOPE:" + slope);
                    break;
                case 559958785:
                    float temperatureSensorValue = sensorEventChangeValue.getTemperatureSensorValue();
                    this.A.setTemperatureSensorValue(temperatureSensorValue);
                    LogUtil.verbose(str, "ID_TEMPERATURE_SENSOR_VALUE:" + temperatureSensorValue);
                    break;
                case 559958786:
                    float humiditySensorValue = sensorEventChangeValue.getHumiditySensorValue();
                    this.A.setHumiditySensorValue(humiditySensorValue);
                    LogUtil.verbose(str, "ID_HUMIDITY_SENSOR_VALUE:" + humiditySensorValue);
                    break;
            }
            this.u.l(i, i2, this.A);
            LogUtil.verbose(str, "notifySensorEventValue:" + i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s(int i, int i2, SpeedEventChangeValue speedEventChangeValue) {
        String str = F;
        switch (i) {
            case 557845760:
                int accelerateDeepnessR = speedEventChangeValue.getAccelerateDeepnessR();
                this.B = accelerateDeepnessR;
                this.A.setAccelerateDeepnessR(accelerateDeepnessR);
                LogUtil.verbose(str, "ID_ACCELERATE_DEEPNESSR:" + this.B);
                break;
            case 557845761:
                int brakeDeepnessR = speedEventChangeValue.getBrakeDeepnessR();
                this.C = brakeDeepnessR;
                this.A.setBrakeDeepnessR(brakeDeepnessR);
                LogUtil.verbose(str, "ID_BRAKE_DEEPNESSR:" + this.C);
                break;
            case 557845764:
                int accelerateDeepnessSignificantBitR = speedEventChangeValue.getAccelerateDeepnessSignificantBitR();
                this.A.setAccelerateDeepnessSignificantBitR(accelerateDeepnessSignificantBitR);
                LogUtil.verbose(str, "ID_ACCELERATE_DEEPNESS_SIGNIFICANT_BIT_R:" + accelerateDeepnessSignificantBitR);
                break;
            case 557867014:
                float currentSpeedR = speedEventChangeValue.getCurrentSpeedR() * 1.0f;
                this.A.setCurrentSpeedR(currentSpeedR);
                LogUtil.verbose(str, "ID_CURRENT_SPEEDR:" + currentSpeedR);
                break;
            case 559957505:
                float speedValueR = speedEventChangeValue.getSpeedValueR();
                this.A.setSpeedValue(speedValueR);
                LogUtil.verbose(str, "ID_SPEED_VALUE_R:" + speedValueR);
                break;
        }
        this.u.l(i, i2, this.A);
        LogUtil.verbose(str, "notifySpeedEventValue:" + i);
    }

    private void t() {
        LogUtil.info(F, "quit");
        if (this.v != null) {
            b bVar = this.w;
            if (bVar != null) {
                bVar.removeCallbacksAndMessages(null);
            }
            this.v.quitSafely();
        }
    }

    @Override // com.ts.appservice.caradapter.d.v
    public void a(SensorEventChangeValue sensorEventChangeValue) {
        String str = F;
        if (sensorEventChangeValue == null) {
            LogUtil.info(str, "onSensorChangeEvent:value=null");
            return;
        }
        int propertyId = sensorEventChangeValue.getPropertyId();
        LogUtil.verbose(str, "onSensorChangeEvent-" + propertyId);
        Message messageObtain = Message.obtain();
        messageObtain.what = 7;
        messageObtain.arg1 = propertyId;
        messageObtain.arg2 = sensorEventChangeValue.getAreaId();
        messageObtain.obj = sensorEventChangeValue;
        this.w.sendMessage(messageObtain);
    }

    @Override // com.ts.appservice.caradapter.d.x
    public void b(SpeedEventChangeValue speedEventChangeValue) {
        String str = F;
        if (speedEventChangeValue == null) {
            LogUtil.info(str, "onSpeedChangeEvent:value=null");
            return;
        }
        int propertyId = speedEventChangeValue.getPropertyId();
        LogUtil.verbose(str, "onSpeedChangeEvent-" + propertyId);
        Message messageObtain = Message.obtain();
        messageObtain.what = 2;
        messageObtain.arg1 = propertyId;
        messageObtain.arg2 = speedEventChangeValue.getAreaId();
        messageObtain.obj = speedEventChangeValue;
        this.w.sendMessage(messageObtain);
    }

    @Override // com.ts.appservice.caradapter.d.r
    public void d(RadarEventChangeValue radarEventChangeValue) {
        String str = F;
        if (radarEventChangeValue == null) {
            LogUtil.info(str, "onRadarChangeEvent:value=null");
            return;
        }
        int propertyId = radarEventChangeValue.getPropertyId();
        LogUtil.verbose(str, "onRadarChangeEvent-" + propertyId);
        Message messageObtain = Message.obtain();
        messageObtain.what = 6;
        messageObtain.arg1 = propertyId;
        messageObtain.arg2 = radarEventChangeValue.getAreaId();
        messageObtain.obj = radarEventChangeValue;
        this.w.sendMessage(messageObtain);
    }

    public int getAccelerateDeepness() {
        String str = F;
        LogUtil.verbose(str, "getAccelerateDeepness");
        CarVendorSpeedManager carVendorSpeedManager = this.z;
        if (carVendorSpeedManager == null) {
            return 0;
        }
        try {
            int accelerateDeepnessR = carVendorSpeedManager.getAccelerateDeepnessR();
            LogUtil.verbose(str, "getAccelerateDeepness=" + accelerateDeepnessR);
            return accelerateDeepnessR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getAccelerateDeepnessSignificantBit() {
        int accelerateDeepnessSignificantBitR;
        String str = F;
        LogUtil.verbose(str, " getAccelerateDeepnessSignificantBit.in");
        CarVendorSpeedManager carVendorSpeedManager = this.z;
        if (carVendorSpeedManager != null) {
            try {
                accelerateDeepnessSignificantBitR = carVendorSpeedManager.getAccelerateDeepnessSignificantBitR();
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
                accelerateDeepnessSignificantBitR = -1;
            }
            LogUtil.verbose(str, " getAccelerateDeepnessSignificantBit.out=" + accelerateDeepnessSignificantBitR);
            return accelerateDeepnessSignificantBitR;
        }
        LogUtil.verbose(str, " getAccelerateDeepnessSignificantBit.null == mSpeedManager");
        accelerateDeepnessSignificantBitR = -1;
        LogUtil.verbose(str, " getAccelerateDeepnessSignificantBit.out=" + accelerateDeepnessSignificantBitR);
        return accelerateDeepnessSignificantBitR;
    }

    public float getAccelerateValue() {
        LogUtil.verbose(F, "Service getAccelerateValue");
        return 0.0f;
    }

    public int[] getAllRadarDistance() {
        LogUtil.verbose(F, "getAllRadarDistance");
        CarVendorRadarManager carVendorRadarManager = this.x;
        if (carVendorRadarManager != null) {
            try {
                return new int[]{carVendorRadarManager.getRadarObstacleDistanceFL(), this.x.getRadarObstacleDistanceFML(), this.x.getRadarObstacleDistanceFMR(), this.x.getRadarObstacleDistanceFR(), this.x.getRadarObstacleDistanceRL(), this.x.getRadarObstacleDistanceRML(), this.x.getRadarObstacleDistanceRMR(), this.x.getRadarObstacleDistanceRR()};
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
        return new int[0];
    }

    public int[] getAllRadarProbeStates() {
        LogUtil.verbose(F, "getAllRadarProbeStates");
        CarVendorRadarManager carVendorRadarManager = this.x;
        if (carVendorRadarManager != null) {
            try {
                return new int[]{carVendorRadarManager.getRadarProbeStateFL(), this.x.getRadarProbeStateFML(), this.x.getRadarProbeStateFMR(), this.x.getRadarProbeStateFR(), this.x.getRadarProbeStateMR(), this.x.getRadarProbeStateRL(), this.x.getRadarProbeStateRML(), this.x.getRadarProbeStateRMR(), this.x.getRadarProbeStateRR()};
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
        return new int[0];
    }

    public float getAverrageSpeed() {
        LogUtil.verbose(F, "Service getAverrageSpeed");
        return 0.0f;
    }

    public int getBrakeDeepness() {
        String str = F;
        LogUtil.verbose(str, "getBrakeDeepness");
        CarVendorSpeedManager carVendorSpeedManager = this.z;
        if (carVendorSpeedManager == null) {
            return 0;
        }
        try {
            int brakeDeepnessR = carVendorSpeedManager.getBrakeDeepnessR();
            LogUtil.verbose(str, "getBrakeDeepness=" + brakeDeepnessR);
            return brakeDeepnessR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public float getCurrentSpeed() {
        String str = F;
        LogUtil.verbose(str, "getCurrentSpeed");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(557867014, 0);
        if (carPropertyValueA == null) {
            return 0.0f;
        }
        LogUtil.verbose(str, "getCurrentSpeed prop value " + carPropertyValueA.getValue());
        return ((Integer) carPropertyValueA.getValue()).floatValue();
    }

    public int getDataFlag(int i) {
        LogUtil.verbose(F, "Service getDataFlag");
        return 0;
    }

    public int getDevicetype() {
        LogUtil.verbose(F, "Service getDevicetype");
        return 0;
    }

    public float getFuelAccelerateDeepness() {
        LogUtil.verbose(F, "Service getFuelAccelerateDeepness");
        return 0.0f;
    }

    public float getHumiditySensorValue() {
        String str = F;
        LogUtil.verbose(str, "getHumiditySensorValue");
        CarVendorSensorManager carVendorSensorManager = this.y;
        if (carVendorSensorManager == null) {
            return 0.0f;
        }
        try {
            float humiditySensorValue = carVendorSensorManager.getHumiditySensorValue();
            LogUtil.verbose(str, "getHumiditySensorValue=" + humiditySensorValue);
            return humiditySensorValue;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0.0f;
        }
    }

    public float getIpbVehicleSpeedStatusR() {
        String str = F;
        LogUtil.info(str, "getIpbVehicleSpeedStatusR");
        CarVendorSpeedManager carVendorSpeedManager = this.z;
        if (carVendorSpeedManager == null) {
            return 0.0f;
        }
        try {
            float ipbVehicleSpeedStatusR = carVendorSpeedManager.getIpbVehicleSpeedStatusR();
            LogUtil.verbose(str, "getIpbVehicleSpeedStatusR=" + ipbVehicleSpeedStatusR);
            return ipbVehicleSpeedStatusR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0.0f;
        }
    }

    public int getLightIntensity() {
        String str = F;
        LogUtil.verbose(str, "getLightIntensity");
        CarVendorSensorManager carVendorSensorManager = this.y;
        if (carVendorSensorManager == null) {
            return 0;
        }
        try {
            int lightIntensity = carVendorSensorManager.getLightIntensity();
            LogUtil.verbose(str, "getLightIntensity=" + lightIntensity);
            return lightIntensity;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getNewReverseRadarSwitchState() {
        String str = F;
        LogUtil.verbose(str, "getNewReverseRadarSwitchState");
        CarVendorRadarManager carVendorRadarManager = this.x;
        if (carVendorRadarManager == null) {
            return 0;
        }
        try {
            int newReverseRadarSwitchStateR = carVendorRadarManager.getNewReverseRadarSwitchStateR();
            LogUtil.verbose(str, "getNewReverseRadarSwitchState=" + newReverseRadarSwitchStateR);
            return newReverseRadarSwitchStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getRadarObstacleDistanceFl() {
        String str = F;
        LogUtil.verbose(str, "getRadarObstacleDistanceFL");
        CarVendorRadarManager carVendorRadarManager = this.x;
        if (carVendorRadarManager == null) {
            return 0;
        }
        try {
            int radarObstacleDistanceFL = carVendorRadarManager.getRadarObstacleDistanceFL();
            LogUtil.verbose(str, "getRadarObstacleDistanceFl=" + radarObstacleDistanceFL);
            return radarObstacleDistanceFL;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getRadarObstacleDistanceFml() {
        String str = F;
        LogUtil.verbose(str, "getRadarObstacleDistanceFML");
        CarVendorRadarManager carVendorRadarManager = this.x;
        if (carVendorRadarManager == null) {
            return 0;
        }
        try {
            int radarObstacleDistanceFML = carVendorRadarManager.getRadarObstacleDistanceFML();
            LogUtil.verbose(str, "getRadarObstacleDistanceFml=" + radarObstacleDistanceFML);
            return radarObstacleDistanceFML;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getRadarObstacleDistanceFmr() {
        String str = F;
        LogUtil.verbose(str, "getRadarObstacleDistanceFMR");
        CarVendorRadarManager carVendorRadarManager = this.x;
        if (carVendorRadarManager == null) {
            return 0;
        }
        try {
            int radarObstacleDistanceFMR = carVendorRadarManager.getRadarObstacleDistanceFMR();
            LogUtil.verbose(str, "getRadarObstacleDistanceFmr=" + radarObstacleDistanceFMR);
            return radarObstacleDistanceFMR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getRadarObstacleDistanceFr() {
        String str = F;
        LogUtil.verbose(str, "getRadarObstacleDistanceFR");
        CarVendorRadarManager carVendorRadarManager = this.x;
        if (carVendorRadarManager == null) {
            return 0;
        }
        try {
            int radarObstacleDistanceFR = carVendorRadarManager.getRadarObstacleDistanceFR();
            LogUtil.verbose(str, "getRadarObstacleDistanceFr=" + radarObstacleDistanceFR);
            return radarObstacleDistanceFR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getRadarObstacleDistanceRl() {
        String str = F;
        LogUtil.verbose(str, "getRadarObstacleDistanceRL");
        CarVendorRadarManager carVendorRadarManager = this.x;
        if (carVendorRadarManager == null) {
            return 0;
        }
        try {
            int radarObstacleDistanceRL = carVendorRadarManager.getRadarObstacleDistanceRL();
            LogUtil.verbose(str, "getRadarObstacleDistanceRl=" + radarObstacleDistanceRL);
            return radarObstacleDistanceRL;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getRadarObstacleDistanceRml() {
        String str = F;
        LogUtil.verbose(str, "getRadarObstacleDistanceRML");
        CarVendorRadarManager carVendorRadarManager = this.x;
        if (carVendorRadarManager == null) {
            return 0;
        }
        try {
            int radarObstacleDistanceRML = carVendorRadarManager.getRadarObstacleDistanceRML();
            LogUtil.verbose(str, "getRadarObstacleDistanceRml=" + radarObstacleDistanceRML);
            return radarObstacleDistanceRML;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getRadarObstacleDistanceRmr() {
        String str = F;
        LogUtil.verbose(str, "getRadarObstacleDistanceRMR");
        CarVendorRadarManager carVendorRadarManager = this.x;
        if (carVendorRadarManager == null) {
            return 0;
        }
        try {
            int radarObstacleDistanceRMR = carVendorRadarManager.getRadarObstacleDistanceRMR();
            LogUtil.verbose(str, "getRadarObstacleDistanceRmr=" + radarObstacleDistanceRMR);
            return radarObstacleDistanceRMR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getRadarObstacleDistanceRr() {
        String str = F;
        LogUtil.verbose(str, "getRadarObstacleDistanceRR");
        CarVendorRadarManager carVendorRadarManager = this.x;
        if (carVendorRadarManager == null) {
            return 0;
        }
        try {
            int radarObstacleDistanceRR = carVendorRadarManager.getRadarObstacleDistanceRR();
            LogUtil.verbose(str, "getRadarObstacleDistanceRr=" + radarObstacleDistanceRR);
            return radarObstacleDistanceRR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getRadarProbeStateFl() {
        String str = F;
        LogUtil.verbose(str, "getRadarProbeStateFL");
        CarVendorRadarManager carVendorRadarManager = this.x;
        if (carVendorRadarManager == null) {
            return 0;
        }
        try {
            int radarProbeStateFL = carVendorRadarManager.getRadarProbeStateFL();
            LogUtil.verbose(str, "getRadarProbeStateFl=" + radarProbeStateFL);
            return radarProbeStateFL;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getRadarProbeStateFml() {
        String str = F;
        LogUtil.verbose(str, "getRadarProbeStateFML");
        CarVendorRadarManager carVendorRadarManager = this.x;
        if (carVendorRadarManager == null) {
            return 0;
        }
        try {
            int radarProbeStateFML = carVendorRadarManager.getRadarProbeStateFML();
            LogUtil.verbose(str, "getRadarProbeStateFml=" + radarProbeStateFML);
            return radarProbeStateFML;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getRadarProbeStateFmr() {
        String str = F;
        LogUtil.verbose(str, "getRadarProbeStateFMR");
        CarVendorRadarManager carVendorRadarManager = this.x;
        if (carVendorRadarManager == null) {
            return 0;
        }
        try {
            int radarProbeStateFMR = carVendorRadarManager.getRadarProbeStateFMR();
            LogUtil.verbose(str, "getRadarProbeStateFmr=" + radarProbeStateFMR);
            return radarProbeStateFMR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getRadarProbeStateFr() {
        String str = F;
        LogUtil.verbose(str, "getRadarProbeStateFR");
        CarVendorRadarManager carVendorRadarManager = this.x;
        if (carVendorRadarManager == null) {
            return 0;
        }
        try {
            int radarProbeStateFR = carVendorRadarManager.getRadarProbeStateFR();
            LogUtil.verbose(str, "getRadarProbeStateFr=" + radarProbeStateFR);
            return radarProbeStateFR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getRadarProbeStateMr() {
        String str = F;
        LogUtil.verbose(str, "getRadarProbeStateMR");
        CarVendorRadarManager carVendorRadarManager = this.x;
        if (carVendorRadarManager == null) {
            return 0;
        }
        try {
            int radarProbeStateMR = carVendorRadarManager.getRadarProbeStateMR();
            LogUtil.verbose(str, "getRadarProbeStateMr=" + radarProbeStateMR);
            return radarProbeStateMR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getRadarProbeStateRl() {
        String str = F;
        LogUtil.verbose(str, "getRadarProbeStateRL");
        CarVendorRadarManager carVendorRadarManager = this.x;
        if (carVendorRadarManager == null) {
            return 0;
        }
        try {
            int radarProbeStateRL = carVendorRadarManager.getRadarProbeStateRL();
            LogUtil.verbose(str, "getRadarProbeStateRl=" + radarProbeStateRL);
            return radarProbeStateRL;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getRadarProbeStateRml() {
        String str = F;
        LogUtil.verbose(str, "getRadarProbeStateRML");
        CarVendorRadarManager carVendorRadarManager = this.x;
        if (carVendorRadarManager == null) {
            return 0;
        }
        try {
            int radarProbeStateRML = carVendorRadarManager.getRadarProbeStateRML();
            LogUtil.verbose(str, "getRadarProbeStateRml=" + radarProbeStateRML);
            return radarProbeStateRML;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getRadarProbeStateRmr() {
        String str = F;
        LogUtil.verbose(str, "getRadarProbeStateRMR");
        CarVendorRadarManager carVendorRadarManager = this.x;
        if (carVendorRadarManager == null) {
            return 0;
        }
        try {
            int radarProbeStateRMR = carVendorRadarManager.getRadarProbeStateRMR();
            LogUtil.verbose(str, "getRadarProbeStateRmr=" + radarProbeStateRMR);
            return radarProbeStateRMR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getRadarProbeStateRr() {
        String str = F;
        LogUtil.verbose(str, "getRadarProbeStateRR");
        CarVendorRadarManager carVendorRadarManager = this.x;
        if (carVendorRadarManager == null) {
            return 0;
        }
        try {
            int radarProbeStateRR = carVendorRadarManager.getRadarProbeStateRR();
            LogUtil.verbose(str, "getRadarProbeStateRr=" + radarProbeStateRR);
            return radarProbeStateRR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getReverseRadarSwitchState() {
        String str = F;
        LogUtil.verbose(str, "getReverseRadarSwitchState");
        CarVendorRadarManager carVendorRadarManager = this.x;
        if (carVendorRadarManager == null) {
            return 0;
        }
        try {
            int reverseRadarSwitchStateR = carVendorRadarManager.getReverseRadarSwitchStateR();
            LogUtil.verbose(str, "getReverseRadarSwitchState=" + reverseRadarSwitchStateR);
            return reverseRadarSwitchStateR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getSensorAx(int i) {
        return 0;
    }

    public byte[] getSensorAxValue() {
        return null;
    }

    public int getSlope() {
        String str = F;
        LogUtil.verbose(str, "getSlope");
        CarVendorSensorManager carVendorSensorManager = this.y;
        if (carVendorSensorManager == null) {
            return 0;
        }
        try {
            int slope = carVendorSensorManager.getSlope();
            LogUtil.verbose(str, "getSlope=" + slope);
            return slope;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public float getSpeedAliveCounterR() {
        String str = F;
        LogUtil.info(str, "getSpeedAliveCounterR");
        CarVendorSpeedManager carVendorSpeedManager = this.z;
        if (carVendorSpeedManager == null) {
            return 0.0f;
        }
        try {
            float speedAliveCounterR = carVendorSpeedManager.getSpeedAliveCounterR();
            LogUtil.verbose(str, "getSpeedAliveCounterR=" + speedAliveCounterR);
            return speedAliveCounterR;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0.0f;
        }
    }

    public float getSpeedFromGateway() {
        LogUtil.verbose(F, "Service getSpeedFromGateway");
        return 0.0f;
    }

    public float getSpeedValue() {
        String str = F;
        LogUtil.verbose(str, "getSpeedValue");
        CarPropertyValue carPropertyValueA = com.ts.appservice.caradapter.e.c.a(559957505, 0);
        if (carPropertyValueA == null) {
            return 0.0f;
        }
        LogUtil.verbose(str, "getSpeedValue prop value " + carPropertyValueA.getValue());
        return ((Float) carPropertyValueA.getValue()).floatValue();
    }

    public float getTemperatureSensorValue() {
        String str = F;
        LogUtil.verbose(str, "getTemperatureSensorValue");
        CarVendorSensorManager carVendorSensorManager = this.y;
        if (carVendorSensorManager == null) {
            return 0.0f;
        }
        try {
            float temperatureSensorValue = carVendorSensorManager.getTemperatureSensorValue();
            LogUtil.verbose(str, "getTemperatureSensorValue=" + temperatureSensorValue);
            return temperatureSensorValue;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return 0.0f;
        }
    }

    public void o() {
        com.ts.appservice.caradapter.a.f fVar = this.u;
        if (fVar != null) {
            this.t = false;
            fVar.g();
        }
        t();
        AvmManager avmManager = this.D;
        if (avmManager != null) {
            avmManager.a(this.E);
        }
    }

    public void p(CarVendorRadarManager carVendorRadarManager, CarVendorSensorManager carVendorSensorManager, CarVendorSpeedManager carVendorSpeedManager) {
        this.x = carVendorRadarManager;
        this.y = carVendorSensorManager;
        this.z = carVendorSpeedManager;
        try {
            if (this.w == null) {
                this.w = new b(this.v.getLooper());
                LogUtil.info(F, "initSensorManager");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        com.ts.appservice.caradapter.a.f fVar = this.u;
        if (fVar != null) {
            this.t = true;
            fVar.m();
        }
    }

    public int setOrientationAngle(int i) {
        return 0;
    }

    public int setReverseRadarSwitchState(int i) {
        String str = F;
        LogUtil.verbose(str, "setReverseRadarSwitchStateOne" + i);
        if (this.x != null) {
            try {
                int i2 = SystemProperties.getInt("persist.car.sensor.radar", 0) == 1 ? 0 : 1;
                LogUtil.verbose(str, "radar jump change value " + i2);
                this.x.setReverseRadarSwitchStateS(new int[]{i, i2});
                SystemProperties.set("persist.car.sensor.radar", i2 + "");
                if (i2 == 1) {
                    this.D.d(true);
                } else if (i2 == 0) {
                    this.D.d(false);
                }
                LogUtil.verbose(str, "switchRadarAlarmToneStatus : " + i2);
            } catch (CarNotConnectedException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public boolean subscribeSensorCallback(ISensorCallback iSensorCallback) {
        com.ts.appservice.caradapter.a.f fVar = this.u;
        if (fVar == null || iSensorCallback == null) {
            return false;
        }
        fVar.h(iSensorCallback);
        if (!this.t) {
            return true;
        }
        this.u.m();
        return true;
    }

    public void u(CarVendorRadarManager carVendorRadarManager, CarVendorSensorManager carVendorSensorManager, CarVendorSpeedManager carVendorSpeedManager) {
        this.x = carVendorRadarManager;
        this.y = carVendorSensorManager;
        this.z = carVendorSpeedManager;
    }

    public boolean unsubscribeSensorCallback(ISensorCallback iSensorCallback) {
        com.ts.appservice.caradapter.a.f fVar = this.u;
        if (fVar == null || iSensorCallback == null) {
            return false;
        }
        fVar.i(iSensorCallback);
        return true;
    }
}
