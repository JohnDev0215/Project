package com.byd.data.common.entiry.gson;

import com.byd.data.common.entiry.extension.EvExtData;
import com.byd.data.common.entiry.extension.ExtensionAlarm;
import com.byd.data.common.server.b;

/* JADX INFO: loaded from: classes.dex */
public class DataCollectBean {
    public AlarmData alarmData;
    public boolean bigEnd;
    public byte dmType;
    public DriverMotorData driverMotorData;
    public byte encryptType;
    public EngineData engineData;
    public EvExtData evExtData;
    public ExtensionAlarm extensionAlarm;
    public ExtremeData extremeData;
    public VehicleData vehicleData;
    public byte dmVersion = 5;
    public boolean isEncrypt = false;

    public boolean a() {
        return this.dmVersion == 2;
    }

    public boolean b() {
        byte b2 = this.dmVersion;
        return b2 == 3 || b2 == 8;
    }

    public boolean c() {
        return b.e().r();
    }
}
