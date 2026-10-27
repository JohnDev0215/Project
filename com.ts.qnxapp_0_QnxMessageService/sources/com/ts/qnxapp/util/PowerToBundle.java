package com.ts.qnxapp.util;

import android.os.Bundle;
import com.bosch.cm.housekeeping.pb.susdif.ComBoschCmHousekeepingSusdif;
import com.ts.qnxapp.fdbusClientForAndroid.FdbusProtoBuilder;
import ipc.fdbus.FdbusMsgBuilder;

/* JADX INFO: loaded from: classes.dex */
public class PowerToBundle {
    private static final String POWER_CYCLE_REASON = "powerCycleReason";
    private static final String POWER_CYCLE_TYPE = "powerCycleType";
    private static final String REQUESTER = "requester";
    private static final String SHUTDOWN_TYPE = "shutDownType";
    private static final String SOC_BOOT_TARGET = "socBootTarget";
    private static final String TAG = "PowerToBundle";
    private static final String VIP_BOOT_TARGET = "vipBootTarget";
    private static volatile PowerToBundle sInstance;

    public static PowerToBundle getInstance() {
        if (sInstance != null) {
            return sInstance;
        }
        synchronized (PowerToBundle.class) {
            if (sInstance == null) {
                sInstance = new PowerToBundle();
            }
        }
        return sInstance;
    }

    public Object getPowerMessage(int subType, String content, Bundle bundle) {
        if (subType != 82) {
            return null;
        }
        ComBoschCmHousekeepingSusdif.SUSDSetPowerCycle.Builder powerCycleBuilder = ComBoschCmHousekeepingSusdif.SUSDSetPowerCycle.newBuilder();
        powerCycleBuilder.setRequester(bundle.getString(REQUESTER));
        powerCycleBuilder.setSusdPowerCycleType(ComBoschCmHousekeepingSusdif.SUSDPowerCycleType.valueOf(bundle.getInt(POWER_CYCLE_TYPE)));
        powerCycleBuilder.setSusdShutdownType(ComBoschCmHousekeepingSusdif.SUSDShutdownType.valueOf(bundle.getInt(SHUTDOWN_TYPE)));
        powerCycleBuilder.setSusdSocBootTarget(ComBoschCmHousekeepingSusdif.SUSDSoCBootTarget.valueOf(bundle.getInt(SOC_BOOT_TARGET)));
        powerCycleBuilder.setSusdVipBootTarget(ComBoschCmHousekeepingSusdif.SUSDVipBootTarget.valueOf(bundle.getInt(VIP_BOOT_TARGET)));
        powerCycleBuilder.setSusdPowerCycleReason(ComBoschCmHousekeepingSusdif.SUSDPowerCycleReason.valueOf(bundle.getInt(POWER_CYCLE_REASON)));
        ComBoschCmHousekeepingSusdif.SUSDSetPowerCycle powerCycleReq = powerCycleBuilder.build();
        FdbusMsgBuilder msg = new FdbusProtoBuilder(powerCycleReq);
        return msg;
    }
}
