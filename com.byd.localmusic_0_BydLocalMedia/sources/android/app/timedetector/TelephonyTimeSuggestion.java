package android.app.timedetector;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.TimestampedValue;
import android.text.format.DateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class TelephonyTimeSuggestion implements Parcelable {
    public static final Parcelable.Creator<TelephonyTimeSuggestion> CREATOR = new Parcelable.Creator<TelephonyTimeSuggestion>() { // from class: android.app.timedetector.TelephonyTimeSuggestion.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TelephonyTimeSuggestion createFromParcel(Parcel parcel) {
            return TelephonyTimeSuggestion.createFromParcel(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TelephonyTimeSuggestion[] newArray(int i) {
            return new TelephonyTimeSuggestion[i];
        }
    };
    private ArrayList<String> mDebugInfo;
    private final int mSlotIndex;
    private final TimestampedValue<Long> mUtcTime;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private TelephonyTimeSuggestion(Builder builder) {
        this.mSlotIndex = builder.mSlotIndex;
        this.mUtcTime = builder.mUtcTime;
        this.mDebugInfo = builder.mDebugInfo != null ? new ArrayList<>(builder.mDebugInfo) : null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static TelephonyTimeSuggestion createFromParcel(Parcel parcel) {
        TelephonyTimeSuggestion telephonyTimeSuggestionBuild = new Builder(parcel.readInt()).setUtcTime((TimestampedValue) parcel.readParcelable(null)).build();
        ArrayList arrayList = parcel.readArrayList(null);
        if (arrayList != null) {
            telephonyTimeSuggestionBuild.addDebugInfo(arrayList);
        }
        return telephonyTimeSuggestionBuild;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mSlotIndex);
        parcel.writeParcelable(this.mUtcTime, 0);
        parcel.writeList(this.mDebugInfo);
    }

    public int getSlotIndex() {
        return this.mSlotIndex;
    }

    public TimestampedValue<Long> getUtcTime() {
        return this.mUtcTime;
    }

    public List<String> getDebugInfo() {
        ArrayList<String> arrayList = this.mDebugInfo;
        return arrayList == null ? Collections.emptyList() : Collections.unmodifiableList(arrayList);
    }

    public void addDebugInfo(String str) {
        if (this.mDebugInfo == null) {
            this.mDebugInfo = new ArrayList<>();
        }
        this.mDebugInfo.add(str);
    }

    public void addDebugInfo(List<String> list) {
        if (this.mDebugInfo == null) {
            this.mDebugInfo = new ArrayList<>(list.size());
        }
        this.mDebugInfo.addAll(list);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        TelephonyTimeSuggestion telephonyTimeSuggestion = (TelephonyTimeSuggestion) obj;
        return this.mSlotIndex == telephonyTimeSuggestion.mSlotIndex && Objects.equals(this.mUtcTime, telephonyTimeSuggestion.mUtcTime);
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.mSlotIndex), this.mUtcTime);
    }

    public String toString() {
        return "TelephonyTimeSuggestion{mSlotIndex='" + this.mSlotIndex + DateFormat.QUOTE + ", mUtcTime=" + this.mUtcTime + ", mDebugInfo=" + this.mDebugInfo + '}';
    }

    public static final class Builder {
        private List<String> mDebugInfo;
        private final int mSlotIndex;
        private TimestampedValue<Long> mUtcTime;

        public Builder(int i) {
            this.mSlotIndex = i;
        }

        public Builder setUtcTime(TimestampedValue<Long> timestampedValue) {
            if (timestampedValue != null) {
                Objects.requireNonNull(timestampedValue.getValue());
            }
            this.mUtcTime = timestampedValue;
            return this;
        }

        public Builder addDebugInfo(String str) {
            if (this.mDebugInfo == null) {
                this.mDebugInfo = new ArrayList();
            }
            this.mDebugInfo.add(str);
            return this;
        }

        public TelephonyTimeSuggestion build() {
            return new TelephonyTimeSuggestion(this);
        }
    }
}
