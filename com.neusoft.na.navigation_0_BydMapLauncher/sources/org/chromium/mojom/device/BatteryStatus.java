package org.chromium.mojom.device;

import org.chromium.mojo.bindings.BindingsHelper;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

/* JADX INFO: loaded from: classes3.dex */
public final class BatteryStatus extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO;
    private static final int STRUCT_SIZE = 40;
    private static final DataHeader[] VERSION_ARRAY;
    public boolean charging;
    public double chargingTime;
    public double dischargingTime;
    public double level;

    static {
        DataHeader dataHeader = new DataHeader(40, 0);
        VERSION_ARRAY = new DataHeader[]{dataHeader};
        DEFAULT_STRUCT_INFO = dataHeader;
    }

    private BatteryStatus(int i) {
        super(40, i);
        this.charging = true;
        this.chargingTime = 0.0d;
        this.dischargingTime = Double.POSITIVE_INFINITY;
        this.level = 1.0d;
    }

    public BatteryStatus() {
        this(0);
    }

    public static BatteryStatus deserialize(Message message) {
        return decode(new Decoder(message));
    }

    public static BatteryStatus decode(Decoder decoder) {
        if (decoder == null) {
            return null;
        }
        DataHeader andValidateDataHeader = decoder.readAndValidateDataHeader(VERSION_ARRAY);
        BatteryStatus batteryStatus = new BatteryStatus(andValidateDataHeader.elementsOrVersion);
        if (andValidateDataHeader.elementsOrVersion >= 0) {
            batteryStatus.charging = decoder.readBoolean(8, 0);
        }
        if (andValidateDataHeader.elementsOrVersion >= 0) {
            batteryStatus.chargingTime = decoder.readDouble(16);
        }
        if (andValidateDataHeader.elementsOrVersion >= 0) {
            batteryStatus.dischargingTime = decoder.readDouble(24);
        }
        if (andValidateDataHeader.elementsOrVersion >= 0) {
            batteryStatus.level = decoder.readDouble(32);
        }
        return batteryStatus;
    }

    @Override // org.chromium.mojo.bindings.Struct
    protected final void encode(Encoder encoder) {
        Encoder encoderAtDataOffset = encoder.getEncoderAtDataOffset(DEFAULT_STRUCT_INFO);
        encoderAtDataOffset.encode(this.charging, 8, 0);
        encoderAtDataOffset.encode(this.chargingTime, 16);
        encoderAtDataOffset.encode(this.dischargingTime, 24);
        encoderAtDataOffset.encode(this.level, 32);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        BatteryStatus batteryStatus = (BatteryStatus) obj;
        return this.charging == batteryStatus.charging && this.chargingTime == batteryStatus.chargingTime && this.dischargingTime == batteryStatus.dischargingTime && this.level == batteryStatus.level;
    }

    public int hashCode() {
        return ((((((((getClass().hashCode() + 31) * 31) + BindingsHelper.hashCode(this.charging)) * 31) + BindingsHelper.hashCode(this.chargingTime)) * 31) + BindingsHelper.hashCode(this.dischargingTime)) * 31) + BindingsHelper.hashCode(this.level);
    }
}
