package android.hardware.camera2.utils;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: loaded from: classes2.dex */
public class ConcurrentCameraIdCombination implements Parcelable {
    public static final Parcelable.Creator<ConcurrentCameraIdCombination> CREATOR = new Parcelable.Creator<ConcurrentCameraIdCombination>() { // from class: android.hardware.camera2.utils.ConcurrentCameraIdCombination.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ConcurrentCameraIdCombination createFromParcel(Parcel parcel) {
            return new ConcurrentCameraIdCombination(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ConcurrentCameraIdCombination[] newArray(int i) {
            return new ConcurrentCameraIdCombination[i];
        }
    };
    private Set<String> mConcurrentCameraIds;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private ConcurrentCameraIdCombination(Parcel parcel) {
        this.mConcurrentCameraIds = new HashSet();
        readFromParcel(parcel);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mConcurrentCameraIds.size());
        Iterator<String> it = this.mConcurrentCameraIds.iterator();
        while (it.hasNext()) {
            parcel.writeString(it.next());
        }
    }

    public void readFromParcel(Parcel parcel) {
        this.mConcurrentCameraIds.clear();
        int i = parcel.readInt();
        if (i < 0) {
            throw new RuntimeException("cameraCombinationSize " + i + " should not be negative");
        }
        for (int i2 = 0; i2 < i; i2++) {
            String string = parcel.readString();
            if (string == null) {
                throw new RuntimeException("Failed to read camera id from Parcel");
            }
            this.mConcurrentCameraIds.add(string);
        }
    }

    public Set<String> getConcurrentCameraIdCombination() {
        return this.mConcurrentCameraIds;
    }
}
