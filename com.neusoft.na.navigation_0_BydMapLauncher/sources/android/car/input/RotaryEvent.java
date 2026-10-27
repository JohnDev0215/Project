package android.car.input;

import android.annotation.NonNull;
import android.car.input.CarInputManager;
import android.os.Parcel;
import android.os.Parcelable;
import com.android.internal.util.AnnotationValidations;
import java.lang.annotation.Annotation;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class RotaryEvent implements Parcelable {
    public static final Parcelable.Creator<RotaryEvent> CREATOR = new Parcelable.Creator<RotaryEvent>() { // from class: android.car.input.RotaryEvent.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RotaryEvent[] newArray(int i) {
            return new RotaryEvent[i];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RotaryEvent createFromParcel(Parcel parcel) {
            return new RotaryEvent(parcel);
        }
    };
    private final boolean mClockwise;
    private final int mInputType;
    private final long[] mUptimeMillisForClicks;

    @Deprecated
    private void __metadata() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getNumberOfClicks() {
        return this.mUptimeMillisForClicks.length;
    }

    public long getUptimeMillisForClick(int i) {
        return this.mUptimeMillisForClicks[i];
    }

    public String toString() {
        return new StringBuilder(128).append("RotaryEvent{mInputType:").append(this.mInputType).append(",mClockwise:").append(this.mClockwise).append(",mUptimeMillisForClicks:").append(Arrays.toString(this.mUptimeMillisForClicks)).append("}").toString();
    }

    public RotaryEvent(int i, boolean z, long[] jArr) {
        this.mInputType = i;
        AnnotationValidations.validate(CarInputManager.InputTypeEnum.class, (Annotation) null, i);
        this.mClockwise = z;
        this.mUptimeMillisForClicks = jArr;
        AnnotationValidations.validate(NonNull.class, (NonNull) null, jArr);
    }

    public int getInputType() {
        return this.mInputType;
    }

    public boolean isClockwise() {
        return this.mClockwise;
    }

    public long[] getUptimeMillisForClicks() {
        return this.mUptimeMillisForClicks;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        RotaryEvent rotaryEvent = (RotaryEvent) obj;
        return this.mInputType == rotaryEvent.mInputType && this.mClockwise == rotaryEvent.mClockwise && Arrays.equals(this.mUptimeMillisForClicks, rotaryEvent.mUptimeMillisForClicks);
    }

    public int hashCode() {
        return ((((this.mInputType + 31) * 31) + Boolean.hashCode(this.mClockwise)) * 31) + Arrays.hashCode(this.mUptimeMillisForClicks);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte(this.mClockwise ? (byte) 2 : (byte) 0);
        parcel.writeInt(this.mInputType);
        parcel.writeLongArray(this.mUptimeMillisForClicks);
    }

    RotaryEvent(Parcel parcel) {
        boolean z = (parcel.readByte() & 2) != 0;
        int i = parcel.readInt();
        long[] jArrCreateLongArray = parcel.createLongArray();
        this.mInputType = i;
        AnnotationValidations.validate(CarInputManager.InputTypeEnum.class, (Annotation) null, i);
        this.mClockwise = z;
        this.mUptimeMillisForClicks = jArrCreateLongArray;
        AnnotationValidations.validate(NonNull.class, (NonNull) null, jArrCreateLongArray);
    }
}
