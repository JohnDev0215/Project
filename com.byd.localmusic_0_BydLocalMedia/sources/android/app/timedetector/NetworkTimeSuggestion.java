package android.app.timedetector;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.TimestampedValue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class NetworkTimeSuggestion implements Parcelable {
    public static final Parcelable.Creator<NetworkTimeSuggestion> CREATOR = new Parcelable.Creator<NetworkTimeSuggestion>() { // from class: android.app.timedetector.NetworkTimeSuggestion.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NetworkTimeSuggestion createFromParcel(Parcel parcel) {
            return NetworkTimeSuggestion.createFromParcel(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NetworkTimeSuggestion[] newArray(int i) {
            return new NetworkTimeSuggestion[i];
        }
    };
    private ArrayList<String> mDebugInfo;
    private final TimestampedValue<Long> mUtcTime;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public NetworkTimeSuggestion(TimestampedValue<Long> timestampedValue) {
        this.mUtcTime = (TimestampedValue) Objects.requireNonNull(timestampedValue);
        Objects.requireNonNull(timestampedValue.getValue());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static NetworkTimeSuggestion createFromParcel(Parcel parcel) {
        NetworkTimeSuggestion networkTimeSuggestion = new NetworkTimeSuggestion((TimestampedValue) parcel.readParcelable(null));
        networkTimeSuggestion.mDebugInfo = parcel.readArrayList(null);
        return networkTimeSuggestion;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.mUtcTime, 0);
        parcel.writeList(this.mDebugInfo);
    }

    public TimestampedValue<Long> getUtcTime() {
        return this.mUtcTime;
    }

    public List<String> getDebugInfo() {
        ArrayList<String> arrayList = this.mDebugInfo;
        return arrayList == null ? Collections.emptyList() : Collections.unmodifiableList(arrayList);
    }

    public void addDebugInfo(String... strArr) {
        if (this.mDebugInfo == null) {
            this.mDebugInfo = new ArrayList<>();
        }
        this.mDebugInfo.addAll(Arrays.asList(strArr));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return Objects.equals(this.mUtcTime, ((NetworkTimeSuggestion) obj).mUtcTime);
    }

    public int hashCode() {
        return Objects.hash(this.mUtcTime);
    }

    public String toString() {
        return "NetworkTimeSuggestion{mUtcTime=" + this.mUtcTime + ", mDebugInfo=" + this.mDebugInfo + '}';
    }
}
