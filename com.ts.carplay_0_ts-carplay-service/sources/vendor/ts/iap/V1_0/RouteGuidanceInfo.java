package vendor.ts.iap.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class RouteGuidanceInfo {
    public short routeGuidanceDisplayIdentifier = 0;
    public String routeGuidanceDisplayName = new String();
    public short maxCurrentRoadNameLength = 0;
    public short maxDestinationNameLength = 0;
    public short maxAfterManeuverRoadNameLength = 0;
    public short maxManeuverDescriptionLength = 0;
    public short maxGuidanceManeuverCapacity = 0;
    public short maxLaneGuidanceDescriptionLength = 0;
    public short maxLaneGuidanceStorageCapacity = 0;

    public final boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject == null || otherObject.getClass() != RouteGuidanceInfo.class) {
            return false;
        }
        RouteGuidanceInfo other = (RouteGuidanceInfo) otherObject;
        if (this.routeGuidanceDisplayIdentifier == other.routeGuidanceDisplayIdentifier && HidlSupport.deepEquals(this.routeGuidanceDisplayName, other.routeGuidanceDisplayName) && this.maxCurrentRoadNameLength == other.maxCurrentRoadNameLength && this.maxDestinationNameLength == other.maxDestinationNameLength && this.maxAfterManeuverRoadNameLength == other.maxAfterManeuverRoadNameLength && this.maxManeuverDescriptionLength == other.maxManeuverDescriptionLength && this.maxGuidanceManeuverCapacity == other.maxGuidanceManeuverCapacity && this.maxLaneGuidanceDescriptionLength == other.maxLaneGuidanceDescriptionLength && this.maxLaneGuidanceStorageCapacity == other.maxLaneGuidanceStorageCapacity) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(HidlSupport.deepHashCode(Short.valueOf(this.routeGuidanceDisplayIdentifier))), Integer.valueOf(HidlSupport.deepHashCode(this.routeGuidanceDisplayName)), Integer.valueOf(HidlSupport.deepHashCode(Short.valueOf(this.maxCurrentRoadNameLength))), Integer.valueOf(HidlSupport.deepHashCode(Short.valueOf(this.maxDestinationNameLength))), Integer.valueOf(HidlSupport.deepHashCode(Short.valueOf(this.maxAfterManeuverRoadNameLength))), Integer.valueOf(HidlSupport.deepHashCode(Short.valueOf(this.maxManeuverDescriptionLength))), Integer.valueOf(HidlSupport.deepHashCode(Short.valueOf(this.maxGuidanceManeuverCapacity))), Integer.valueOf(HidlSupport.deepHashCode(Short.valueOf(this.maxLaneGuidanceDescriptionLength))), Integer.valueOf(HidlSupport.deepHashCode(Short.valueOf(this.maxLaneGuidanceStorageCapacity))));
    }

    public final String toString() {
        return "{.routeGuidanceDisplayIdentifier = " + ((int) this.routeGuidanceDisplayIdentifier) + ", .routeGuidanceDisplayName = " + this.routeGuidanceDisplayName + ", .maxCurrentRoadNameLength = " + ((int) this.maxCurrentRoadNameLength) + ", .maxDestinationNameLength = " + ((int) this.maxDestinationNameLength) + ", .maxAfterManeuverRoadNameLength = " + ((int) this.maxAfterManeuverRoadNameLength) + ", .maxManeuverDescriptionLength = " + ((int) this.maxManeuverDescriptionLength) + ", .maxGuidanceManeuverCapacity = " + ((int) this.maxGuidanceManeuverCapacity) + ", .maxLaneGuidanceDescriptionLength = " + ((int) this.maxLaneGuidanceDescriptionLength) + ", .maxLaneGuidanceStorageCapacity = " + ((int) this.maxLaneGuidanceStorageCapacity) + "}";
    }

    public final void readFromParcel(HwParcel parcel) {
        HwBlob blob = parcel.readBuffer(40L);
        readEmbeddedFromParcel(parcel, blob, 0L);
    }

    public static final ArrayList<RouteGuidanceInfo> readVectorFromParcel(HwParcel parcel) {
        ArrayList<RouteGuidanceInfo> _hidl_vec = new ArrayList<>();
        HwBlob _hidl_blob = parcel.readBuffer(16L);
        int _hidl_vec_size = _hidl_blob.getInt32(8L);
        HwBlob childBlob = parcel.readEmbeddedBuffer(_hidl_vec_size * 40, _hidl_blob.handle(), 0L, true);
        _hidl_vec.clear();
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            RouteGuidanceInfo _hidl_vec_element = new RouteGuidanceInfo();
            _hidl_vec_element.readEmbeddedFromParcel(parcel, childBlob, _hidl_index_0 * 40);
            _hidl_vec.add(_hidl_vec_element);
        }
        return _hidl_vec;
    }

    public final void readEmbeddedFromParcel(HwParcel parcel, HwBlob _hidl_blob, long _hidl_offset) {
        this.routeGuidanceDisplayIdentifier = _hidl_blob.getInt16(_hidl_offset + 0);
        this.routeGuidanceDisplayName = _hidl_blob.getString(_hidl_offset + 8);
        parcel.readEmbeddedBuffer(r6.getBytes().length + 1, _hidl_blob.handle(), _hidl_offset + 8 + 0, false);
        this.maxCurrentRoadNameLength = _hidl_blob.getInt16(_hidl_offset + 24);
        this.maxDestinationNameLength = _hidl_blob.getInt16(_hidl_offset + 26);
        this.maxAfterManeuverRoadNameLength = _hidl_blob.getInt16(_hidl_offset + 28);
        this.maxManeuverDescriptionLength = _hidl_blob.getInt16(_hidl_offset + 30);
        this.maxGuidanceManeuverCapacity = _hidl_blob.getInt16(_hidl_offset + 32);
        this.maxLaneGuidanceDescriptionLength = _hidl_blob.getInt16(_hidl_offset + 34);
        this.maxLaneGuidanceStorageCapacity = _hidl_blob.getInt16(_hidl_offset + 36);
    }

    public final void writeToParcel(HwParcel parcel) {
        HwBlob _hidl_blob = new HwBlob(40);
        writeEmbeddedToBlob(_hidl_blob, 0L);
        parcel.writeBuffer(_hidl_blob);
    }

    public static final void writeVectorToParcel(HwParcel parcel, ArrayList<RouteGuidanceInfo> _hidl_vec) {
        HwBlob _hidl_blob = new HwBlob(16);
        int _hidl_vec_size = _hidl_vec.size();
        _hidl_blob.putInt32(8L, _hidl_vec_size);
        _hidl_blob.putBool(12L, false);
        HwBlob childBlob = new HwBlob(_hidl_vec_size * 40);
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            _hidl_vec.get(_hidl_index_0).writeEmbeddedToBlob(childBlob, _hidl_index_0 * 40);
        }
        _hidl_blob.putBlob(0L, childBlob);
        parcel.writeBuffer(_hidl_blob);
    }

    public final void writeEmbeddedToBlob(HwBlob _hidl_blob, long _hidl_offset) {
        _hidl_blob.putInt16(0 + _hidl_offset, this.routeGuidanceDisplayIdentifier);
        _hidl_blob.putString(8 + _hidl_offset, this.routeGuidanceDisplayName);
        _hidl_blob.putInt16(24 + _hidl_offset, this.maxCurrentRoadNameLength);
        _hidl_blob.putInt16(26 + _hidl_offset, this.maxDestinationNameLength);
        _hidl_blob.putInt16(28 + _hidl_offset, this.maxAfterManeuverRoadNameLength);
        _hidl_blob.putInt16(30 + _hidl_offset, this.maxManeuverDescriptionLength);
        _hidl_blob.putInt16(32 + _hidl_offset, this.maxGuidanceManeuverCapacity);
        _hidl_blob.putInt16(34 + _hidl_offset, this.maxLaneGuidanceDescriptionLength);
        _hidl_blob.putInt16(36 + _hidl_offset, this.maxLaneGuidanceStorageCapacity);
    }
}
