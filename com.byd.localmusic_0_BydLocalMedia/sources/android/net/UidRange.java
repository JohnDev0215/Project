package android.net;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.UserHandle;
import com.android.internal.content.NativeLibraryHelper;
import java.util.Collection;
import java.util.Iterator;

/* JADX INFO: loaded from: classes2.dex */
public final class UidRange implements Parcelable {
    public static final Parcelable.Creator<UidRange> CREATOR = new Parcelable.Creator<UidRange>() { // from class: android.net.UidRange.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public UidRange createFromParcel(Parcel parcel) {
            return new UidRange(parcel.readInt(), parcel.readInt());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public UidRange[] newArray(int i) {
            return new UidRange[i];
        }
    };
    public final int start;
    public final int stop;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public UidRange(int i, int i2) {
        if (i < 0) {
            throw new IllegalArgumentException("Invalid start UID.");
        }
        if (i2 < 0) {
            throw new IllegalArgumentException("Invalid stop UID.");
        }
        if (i > i2) {
            throw new IllegalArgumentException("Invalid UID range.");
        }
        this.start = i;
        this.stop = i2;
    }

    public static UidRange createForUser(int i) {
        return new UidRange(i * UserHandle.PER_USER_RANGE, ((i + 1) * UserHandle.PER_USER_RANGE) - 1);
    }

    public int getStartUser() {
        return this.start / UserHandle.PER_USER_RANGE;
    }

    public int getEndUser() {
        return this.stop / UserHandle.PER_USER_RANGE;
    }

    public boolean contains(int i) {
        return this.start <= i && i <= this.stop;
    }

    public int count() {
        return (this.stop + 1) - this.start;
    }

    public boolean containsRange(UidRange uidRange) {
        return this.start <= uidRange.start && uidRange.stop <= this.stop;
    }

    public int hashCode() {
        return ((527 + this.start) * 31) + this.stop;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof UidRange)) {
            return false;
        }
        UidRange uidRange = (UidRange) obj;
        return this.start == uidRange.start && this.stop == uidRange.stop;
    }

    public String toString() {
        return this.start + NativeLibraryHelper.CLEAR_ABI_OVERRIDE + this.stop;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.start);
        parcel.writeInt(this.stop);
    }

    public static boolean containsUid(Collection<UidRange> collection, int i) {
        if (collection == null) {
            return false;
        }
        Iterator<UidRange> it = collection.iterator();
        while (it.hasNext()) {
            if (it.next().contains(i)) {
                return true;
            }
        }
        return false;
    }
}
