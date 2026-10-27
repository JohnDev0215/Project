package com.android.car.vehiclehal;

import android.hardware.automotive.vehicle.V2_0.VehiclePropConfig;
import android.hardware.automotive.vehicle.V2_0.VehicleProperty;

/* JADX INFO: loaded from: classes3.dex */
public class DiagnosticJsonReader {
    public static final String FRAME_TYPE_FREEZE = "freeze";
    public static final String FRAME_TYPE_LIVE = "live";
    private final DiagnosticEventBuilder mFreezeFrameBuilder;
    private final DiagnosticEventBuilder mLiveFrameBuilder;

    public DiagnosticJsonReader(VehiclePropConfig liveConfig, VehiclePropConfig freezeConfig) {
        this.mLiveFrameBuilder = new DiagnosticEventBuilder(VehicleProperty.OBD2_LIVE_FRAME, liveConfig.configArray.get(0).intValue(), liveConfig.configArray.get(1).intValue());
        this.mFreezeFrameBuilder = new DiagnosticEventBuilder(VehicleProperty.OBD2_FREEZE_FRAME, freezeConfig.configArray.get(0).intValue(), freezeConfig.configArray.get(1).intValue());
    }

    public DiagnosticJsonReader() {
        this.mLiveFrameBuilder = new DiagnosticEventBuilder(VehicleProperty.OBD2_LIVE_FRAME);
        this.mFreezeFrameBuilder = new DiagnosticEventBuilder(VehicleProperty.OBD2_FREEZE_FRAME);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x002a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.hardware.automotive.vehicle.V2_0.VehiclePropValue build(android.util.JsonReader r6) throws java.io.IOException {
        /*
            r5 = this;
            com.android.car.vehiclehal.DiagnosticJson r0 = com.android.car.vehiclehal.DiagnosticJson.build(r6)
            java.lang.String r1 = r0.type
            int r2 = r1.hashCode()
            r3 = -1266402665(0xffffffffb4843a97, float:-2.4629546E-7)
            r4 = 1
            if (r2 == r3) goto L20
            r3 = 3322092(0x32b0ec, float:4.655242E-39)
            if (r2 == r3) goto L16
        L15:
            goto L2a
        L16:
            java.lang.String r2 = "live"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L15
            r1 = 0
            goto L2b
        L20:
            java.lang.String r2 = "freeze"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L15
            r1 = r4
            goto L2b
        L2a:
            r1 = -1
        L2b:
            if (r1 == 0) goto L38
            if (r1 == r4) goto L31
            r1 = 0
            return r1
        L31:
            com.android.car.vehiclehal.DiagnosticEventBuilder r1 = r5.mFreezeFrameBuilder
            android.hardware.automotive.vehicle.V2_0.VehiclePropValue r1 = r0.build(r1)
            return r1
        L38:
            com.android.car.vehiclehal.DiagnosticEventBuilder r1 = r5.mLiveFrameBuilder
            android.hardware.automotive.vehicle.V2_0.VehiclePropValue r1 = r0.build(r1)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.car.vehiclehal.DiagnosticJsonReader.build(android.util.JsonReader):android.hardware.automotive.vehicle.V2_0.VehiclePropValue");
    }
}
