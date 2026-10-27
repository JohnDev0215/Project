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
public final class ManualTimeSuggestion implements Parcelable {
    public static final Parcelable.Creator<ManualTimeSuggestion> CREATOR = new Parcelable.Creator<ManualTimeSuggestion>() { // from class: android.app.timedetector.ManualTimeSuggestion.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ManualTimeSuggestion createFromParcel(Parcel parcel) {
            return ManualTimeSuggestion.createFromParcel(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ManualTimeSuggestion[] newArray(int i) {
            return new ManualTimeSuggestion[i];
        }
    };
    private ArrayList<String> mDebugInfo;
    private final TimestampedValue<Long> mUtcTime;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ManualTimeSuggestion(TimestampedValue<Long> timestampedValue) {
        this.mUtcTime = (TimestampedValue) Objects.requireNonNull(timestampedValue);
        Objects.requireNonNull(timestampedValue.getValue());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ManualTimeSuggestion createFromParcel(Parcel parcel) {
        ManualTimeSuggestion manualTimeSuggestion = new ManualTimeSuggestion((TimestampedValue) parcel.readParcelable(null));
        manualTimeSuggestion.mDebugInfo = parcel.readArrayList(null);
        return manualTimeSuggestion;
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
        return Objects.equals(this.mUtcTime, ((ManualTimeSuggestion) obj).mUtcTime);
    }

    public int hashCode() {
        return Objects.hash(this.mUtcTime);
    }

    public String toString() {
        return "ManualTimeSuggestion{mUtcTime=" + this.mUtcTime + ", mDebugInfo=" + this.mDebugInfo + '}';
    }
}
