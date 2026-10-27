package cerence.ark.assistant.api.context;

import android.os.Parcel;
import android.os.Parcelable;
import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class LatLng implements Parcelable, JsonCompliant {
    public static final Parcelable.Creator<LatLng> CREATOR = new Parcelable.Creator<LatLng>() { // from class: cerence.ark.assistant.api.context.LatLng.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LatLng createFromParcel(Parcel parcel) {
            return new LatLng(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LatLng[] newArray(int i) {
            return new LatLng[i];
        }
    };
    private final double mLatitude;
    private final double mLongitude;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public LatLng(double d, double d2) {
        this.mLatitude = d;
        this.mLongitude = d2;
    }

    protected LatLng(Parcel parcel) {
        this.mLatitude = parcel.readDouble();
        this.mLongitude = parcel.readDouble();
    }

    public double getLatitude() {
        return this.mLatitude;
    }

    public double getLongitude() {
        return this.mLongitude;
    }

    public String toString() {
        return "LatLng{latitude=" + this.mLatitude + ", longitude=" + this.mLongitude + '}';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(this.mLatitude);
        parcel.writeDouble(this.mLongitude);
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("latitude", this.mLatitude);
        jSONObject.put("longitude", this.mLongitude);
        return jSONObject.toString();
    }
}
