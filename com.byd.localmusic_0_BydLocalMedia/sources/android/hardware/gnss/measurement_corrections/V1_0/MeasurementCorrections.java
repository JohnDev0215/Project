package android.hardware.gnss.measurement_corrections.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public final class MeasurementCorrections {
    public double latitudeDegrees = 0.0d;
    public double longitudeDegrees = 0.0d;
    public double altitudeMeters = 0.0d;
    public double horizontalPositionUncertaintyMeters = 0.0d;
    public double verticalPositionUncertaintyMeters = 0.0d;
    public long toaGpsNanosecondsOfWeek = 0;
    public ArrayList<SingleSatCorrection> satCorrections = new ArrayList<>();

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != MeasurementCorrections.class) {
            return false;
        }
        MeasurementCorrections measurementCorrections = (MeasurementCorrections) obj;
        return this.latitudeDegrees == measurementCorrections.latitudeDegrees && this.longitudeDegrees == measurementCorrections.longitudeDegrees && this.altitudeMeters == measurementCorrections.altitudeMeters && this.horizontalPositionUncertaintyMeters == measurementCorrections.horizontalPositionUncertaintyMeters && this.verticalPositionUncertaintyMeters == measurementCorrections.verticalPositionUncertaintyMeters && this.toaGpsNanosecondsOfWeek == measurementCorrections.toaGpsNanosecondsOfWeek && HidlSupport.deepEquals(this.satCorrections, measurementCorrections.satCorrections);
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(HidlSupport.deepHashCode(Double.valueOf(this.latitudeDegrees))), Integer.valueOf(HidlSupport.deepHashCode(Double.valueOf(this.longitudeDegrees))), Integer.valueOf(HidlSupport.deepHashCode(Double.valueOf(this.altitudeMeters))), Integer.valueOf(HidlSupport.deepHashCode(Double.valueOf(this.horizontalPositionUncertaintyMeters))), Integer.valueOf(HidlSupport.deepHashCode(Double.valueOf(this.verticalPositionUncertaintyMeters))), Integer.valueOf(HidlSupport.deepHashCode(Long.valueOf(this.toaGpsNanosecondsOfWeek))), Integer.valueOf(HidlSupport.deepHashCode(this.satCorrections)));
    }

    public final String toString() {
        return "{.latitudeDegrees = " + this.latitudeDegrees + ", .longitudeDegrees = " + this.longitudeDegrees + ", .altitudeMeters = " + this.altitudeMeters + ", .horizontalPositionUncertaintyMeters = " + this.horizontalPositionUncertaintyMeters + ", .verticalPositionUncertaintyMeters = " + this.verticalPositionUncertaintyMeters + ", .toaGpsNanosecondsOfWeek = " + this.toaGpsNanosecondsOfWeek + ", .satCorrections = " + this.satCorrections + "}";
    }

    public final void readFromParcel(HwParcel hwParcel) {
        readEmbeddedFromParcel(hwParcel, hwParcel.readBuffer(64L), 0L);
    }

    public static final ArrayList<MeasurementCorrections> readVectorFromParcel(HwParcel hwParcel) {
        ArrayList<MeasurementCorrections> arrayList = new ArrayList<>();
        HwBlob buffer = hwParcel.readBuffer(16L);
        int int32 = buffer.getInt32(8L);
        HwBlob embeddedBuffer = hwParcel.readEmbeddedBuffer(int32 * 64, buffer.handle(), 0L, true);
        arrayList.clear();
        for (int i = 0; i < int32; i++) {
            MeasurementCorrections measurementCorrections = new MeasurementCorrections();
            measurementCorrections.readEmbeddedFromParcel(hwParcel, embeddedBuffer, i * 64);
            arrayList.add(measurementCorrections);
        }
        return arrayList;
    }

    public final void readEmbeddedFromParcel(HwParcel hwParcel, HwBlob hwBlob, long j) {
        this.latitudeDegrees = hwBlob.getDouble(j + 0);
        this.longitudeDegrees = hwBlob.getDouble(j + 8);
        this.altitudeMeters = hwBlob.getDouble(j + 16);
        this.horizontalPositionUncertaintyMeters = hwBlob.getDouble(j + 24);
        this.verticalPositionUncertaintyMeters = hwBlob.getDouble(j + 32);
        this.toaGpsNanosecondsOfWeek = hwBlob.getInt64(j + 40);
        long j2 = j + 48;
        int int32 = hwBlob.getInt32(8 + j2);
        HwBlob embeddedBuffer = hwParcel.readEmbeddedBuffer(int32 * 56, hwBlob.handle(), j2 + 0, true);
        this.satCorrections.clear();
        for (int i = 0; i < int32; i++) {
            SingleSatCorrection singleSatCorrection = new SingleSatCorrection();
            singleSatCorrection.readEmbeddedFromParcel(hwParcel, embeddedBuffer, i * 56);
            this.satCorrections.add(singleSatCorrection);
        }
    }

    public final void writeToParcel(HwParcel hwParcel) {
        HwBlob hwBlob = new HwBlob(64);
        writeEmbeddedToBlob(hwBlob, 0L);
        hwParcel.writeBuffer(hwBlob);
    }

    public static final void writeVectorToParcel(HwParcel hwParcel, ArrayList<MeasurementCorrections> arrayList) {
        HwBlob hwBlob = new HwBlob(16);
        int size = arrayList.size();
        hwBlob.putInt32(8L, size);
        hwBlob.putBool(12L, false);
        HwBlob hwBlob2 = new HwBlob(size * 64);
        for (int i = 0; i < size; i++) {
            arrayList.get(i).writeEmbeddedToBlob(hwBlob2, i * 64);
        }
        hwBlob.putBlob(0L, hwBlob2);
        hwParcel.writeBuffer(hwBlob);
    }

    public final void writeEmbeddedToBlob(HwBlob hwBlob, long j) {
        hwBlob.putDouble(j + 0, this.latitudeDegrees);
        hwBlob.putDouble(j + 8, this.longitudeDegrees);
        hwBlob.putDouble(16 + j, this.altitudeMeters);
        hwBlob.putDouble(24 + j, this.horizontalPositionUncertaintyMeters);
        hwBlob.putDouble(32 + j, this.verticalPositionUncertaintyMeters);
        hwBlob.putInt64(40 + j, this.toaGpsNanosecondsOfWeek);
        int size = this.satCorrections.size();
        long j2 = j + 48;
        hwBlob.putInt32(8 + j2, size);
        hwBlob.putBool(12 + j2, false);
        HwBlob hwBlob2 = new HwBlob(size * 56);
        for (int i = 0; i < size; i++) {
            this.satCorrections.get(i).writeEmbeddedToBlob(hwBlob2, i * 56);
        }
        hwBlob.putBlob(j2 + 0, hwBlob2);
    }
}
