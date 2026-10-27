package com.byd.data.common.entiry.extension;

import com.byd.data.common.entiry.gson.BaseEntity;
import com.byd.data.common.entiry.gson.DataEntity;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ExtensionAlarm extends BaseEntity {
    public DataEntity absActionAlarm;
    public byte acceleratorPedal;
    public DataEntity chargeStop;
    public List<DataEntity> chargeSystemFault;
    public byte collisionAlarm;
    public byte dcChargeFault;
    public byte dcStatusAlarm;
    public DataEntity ebdActionAlarm;
    public DataEntity ebdFailureAlarm;
    public byte evFault;
    public DataEntity igbtPowerSystemFault;
    public DataEntity leakageFault;
    public DataEntity milLamp;
    public DataEntity powerSystemFault;
    public byte socJumpFault;
    public DataEntity tcsActionAlarm;
    public DataEntity thermalRunaway;
    public DataEntity vacuumPumpFault;
    public byte vacuumPumpSeriousFault;
    public DataEntity vdcActionAlarm;
    public DataEntity vehicleSystemFailure;
    public byte waterTempAlarm;
}
