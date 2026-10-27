package android.car.storagemonitoring;

import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.JsonReader;
import android.util.JsonWriter;
import java.io.IOException;
import java.util.Objects;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
@SystemApi
public final class WearEstimate implements Parcelable {
    public static final int UNKNOWN = -1;
    public final int typeA;
    public final int typeB;
    public static final WearEstimate UNKNOWN_ESTIMATE = new WearEstimate(-1, -1);
    public static final Parcelable.Creator<WearEstimate> CREATOR = new Parcelable.Creator<WearEstimate>() { // from class: android.car.storagemonitoring.WearEstimate.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WearEstimate createFromParcel(Parcel parcel) {
            return new WearEstimate(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WearEstimate[] newArray(int i) {
            return new WearEstimate[i];
        }
    };

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private static int validateWearValue(int i) {
        if (i == -1) {
            return i;
        }
        if (i >= 0 && i <= 100) {
            return i;
        }
        throw new IllegalArgumentException(i + " is not a valid wear estimate");
    }

    public WearEstimate(int i, int i2) {
        this.typeA = validateWearValue(i);
        this.typeB = validateWearValue(i2);
    }

    public WearEstimate(Parcel parcel) {
        this.typeA = validateWearValue(parcel.readInt());
        this.typeB = validateWearValue(parcel.readInt());
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public WearEstimate(JsonReader jsonReader) throws IOException {
        byte b;
        jsonReader.beginObject();
        int iValidateWearValue = -1;
        int iValidateWearValue2 = -1;
        while (jsonReader.hasNext()) {
            String strNextName = jsonReader.nextName();
            switch (strNextName.hashCode()) {
                case -1388170560:
                    b = !strNextName.equals("wearEstimateTypeA") ? (byte) -1 : (byte) 0;
                    break;
                case -1388170559:
                    b = !strNextName.equals("wearEstimateTypeB") ? (byte) -1 : (byte) 1;
                    break;
                default:
                    b = -1;
                    break;
            }
            if (b == 0) {
                iValidateWearValue = validateWearValue(jsonReader.nextInt());
            } else if (b == 1) {
                iValidateWearValue2 = validateWearValue(jsonReader.nextInt());
            }
        }
        jsonReader.endObject();
        this.typeA = iValidateWearValue;
        this.typeB = iValidateWearValue2;
    }

    public WearEstimate(JSONObject jSONObject) throws JSONException {
        this.typeA = jSONObject.getInt("wearEstimateTypeA");
        this.typeB = jSONObject.getInt("wearEstimateTypeB");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.typeA);
        parcel.writeInt(this.typeB);
    }

    public void writeToJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("wearEstimateTypeA").value(this.typeA);
        jsonWriter.name("wearEstimateTypeB").value(this.typeB);
        jsonWriter.endObject();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof WearEstimate)) {
            return false;
        }
        WearEstimate wearEstimate = (WearEstimate) obj;
        return wearEstimate.typeA == this.typeA && wearEstimate.typeB == this.typeB;
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.typeA), Integer.valueOf(this.typeB));
    }

    private static String wearValueToString(int i) {
        if (i == -1) {
            return "unknown";
        }
        return i + "%";
    }

    public String toString() {
        return "type A: " + wearValueToString(this.typeA) + ", type B: " + wearValueToString(this.typeB);
    }
}
