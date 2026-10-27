package com.neusoft.na.sens;

import android.content.Context;

/* JADX INFO: loaded from: classes2.dex */
public class OneCoreSenRegister {
    public static OneCoreSenRegister instance;
    private NaviAccelerSensorListener m_AccelerSensorListener;
    private NaviAtmosphericPressureSensorListener m_AtmosphericPressureSensorListener;
    private NaviGyroSensorListener m_GyroSensorListener;
    private NaviMagneticSensorListener m_MagneticSensorListener;
    private NmeaDataListener m_NaviNmeaListener;
    private OneCoreCarInform m_OneCoreCarInform;
    private NaviRotationVectorSensorListener m_RotationVectorSensorListener;
    private NaviTemperatureSensorListener m_TemperatureSensorListener;
    private NaviVisionLaneLine m_VisionLaneLine;
    private Context mContext = null;
    private boolean m_registerFlag = false;

    public static synchronized OneCoreSenRegister getInstance() {
        if (instance == null) {
            instance = new OneCoreSenRegister();
        }
        return instance;
    }

    private OneCoreSenRegister() {
        this.m_NaviNmeaListener = null;
        this.m_GyroSensorListener = null;
        this.m_AccelerSensorListener = null;
        this.m_MagneticSensorListener = null;
        this.m_RotationVectorSensorListener = null;
        this.m_AtmosphericPressureSensorListener = null;
        this.m_TemperatureSensorListener = null;
        this.m_OneCoreCarInform = null;
        this.m_VisionLaneLine = null;
        this.m_NaviNmeaListener = new NmeaDataListener();
        this.m_GyroSensorListener = new NaviGyroSensorListener();
        this.m_AccelerSensorListener = new NaviAccelerSensorListener();
        this.m_MagneticSensorListener = new NaviMagneticSensorListener();
        this.m_RotationVectorSensorListener = new NaviRotationVectorSensorListener();
        this.m_AtmosphericPressureSensorListener = new NaviAtmosphericPressureSensorListener();
        this.m_TemperatureSensorListener = new NaviTemperatureSensorListener();
        this.m_OneCoreCarInform = new OneCoreCarInform();
        this.m_VisionLaneLine = new NaviVisionLaneLine();
    }

    public void registerFiter(Context context, boolean z) {
        this.m_registerFlag = z;
        this.m_NaviNmeaListener.registerFilter(context);
        if (this.m_registerFlag) {
            this.m_AccelerSensorListener.registerFilter(context, 2);
            this.m_GyroSensorListener.registerFilter(context, 2);
            this.m_MagneticSensorListener.registerFilter(context, 2);
            this.m_RotationVectorSensorListener.registerFilter(context, 2);
            this.m_AtmosphericPressureSensorListener.registerFilter(context, 2);
            this.m_TemperatureSensorListener.registerFilter(context, 2);
        }
    }

    public void registerFilter(Context context, boolean z, int i) {
        this.m_registerFlag = z;
        this.m_NaviNmeaListener.registerFilter(context);
        if (this.m_registerFlag) {
            this.m_AccelerSensorListener.registerFilter(context, i);
            this.m_GyroSensorListener.registerFilter(context, i);
        }
    }

    public void unregisterFiter() {
        this.m_NaviNmeaListener.unregisterFilter();
        if (this.m_registerFlag) {
            this.m_registerFlag = false;
            this.m_AccelerSensorListener.unregisterFilter();
            this.m_GyroSensorListener.unregisterFilter();
            this.m_MagneticSensorListener.unregisterFilter();
            this.m_RotationVectorSensorListener.unregisterFilter();
            this.m_AtmosphericPressureSensorListener.unregisterFilter();
            this.m_TemperatureSensorListener.unregisterFilter();
        }
    }

    public String getGnssString() {
        return this.m_NaviNmeaListener.getNmeaString();
    }

    public float[] getNaviAccelerSensor() {
        return this.m_AccelerSensorListener.getNaviAccelerSensor();
    }

    public float[] getNaviGyroSensor() {
        return this.m_GyroSensorListener.getNaviGyroSensor();
    }

    public float[] getNaviMagneticSensor() {
        return this.m_MagneticSensorListener.getNaviMagneticSensor();
    }

    public float[] getNaviRotationVectorSensor() {
        return this.m_RotationVectorSensorListener.getNaviRotationVectorSensor();
    }

    public float[] getNaviAtmosphericPressureSensor() {
        return this.m_AtmosphericPressureSensorListener.getNaviAtmosphericPressureSensor();
    }

    public float[] getNaviTemperatureSensor() {
        return this.m_TemperatureSensorListener.getNaviTemperatureSensor();
    }

    public float getNaviSpeed() {
        return this.m_OneCoreCarInform.getVehicleSpeed();
    }

    public boolean getReverseSignal() {
        return this.m_OneCoreCarInform.getReverseSignal();
    }

    public synchronized int getVehicleSpeedPulse() {
        return this.m_OneCoreCarInform.getVehicleSpeedPulse();
    }

    public synchronized int[] getVehicleSpeedPulseArry() {
        return new int[3];
    }

    public synchronized void onVehicleSpeedPulse(int i) {
    }

    public void setNaviSpeedSensor(float f, long j) {
        this.m_OneCoreCarInform.setNaviVehicleSpeed(f, j);
    }

    public void setNaviAccelerSensor(float[] fArr, long j) {
        this.m_AccelerSensorListener.setNaviAccelerSensor(fArr, j);
    }

    public void setNaviGyroSensor(float[] fArr, long j) {
        this.m_GyroSensorListener.setNaviGyroSensor(fArr, j);
    }

    public void setNaviVisionLaneLine(VisionLaneLineInfo visionLaneLineInfo, long j) {
        this.m_VisionLaneLine.setNaviVisionLaneLine(visionLaneLineInfo, j);
    }

    public void setNaviNMEA(String str, long j) {
        this.m_NaviNmeaListener.setNaviNmeaSensor(str, j);
    }
}
