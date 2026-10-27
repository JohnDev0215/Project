package android.view.autofill;

import android.os.Parcel;
import android.os.Parcelable;
import com.android.internal.accessibility.common.ShortcutConstants;

/* JADX INFO: loaded from: classes3.dex */
public final class AutofillId implements Parcelable {
    public static final Parcelable.Creator<AutofillId> CREATOR = new Parcelable.Creator<AutofillId>() { // from class: android.view.autofill.AutofillId.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AutofillId createFromParcel(Parcel parcel) {
            int i = parcel.readInt();
            int i2 = parcel.readInt();
            int i3 = (i2 & 4) != 0 ? parcel.readInt() : 0;
            if ((i2 & 1) != 0) {
                return new AutofillId(i2, i, parcel.readInt(), i3);
            }
            if ((i2 & 2) != 0) {
                return new AutofillId(i2, i, parcel.readLong(), i3);
            }
            return new AutofillId(i2, i, -1L, i3);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AutofillId[] newArray(int i) {
            return new AutofillId[i];
        }
    };
    private static final int FLAG_HAS_SESSION = 4;
    private static final int FLAG_IS_VIRTUAL_INT = 1;
    private static final int FLAG_IS_VIRTUAL_LONG = 2;
    public static final int NO_SESSION = 0;
    private int mFlags;
    private int mSessionId;
    private final int mViewId;
    private final int mVirtualIntId;
    private final long mVirtualLongId;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public AutofillId(int i) {
        this(0, i, -1L, 0);
    }

    public AutofillId(AutofillId autofillId, int i) {
        this(1, autofillId.mViewId, i, 0);
    }

    public AutofillId(int i, int i2) {
        this(1, i, i2, 0);
    }

    public AutofillId(AutofillId autofillId, long j, int i) {
        this(6, autofillId.mViewId, j, i);
    }

    private AutofillId(int i, int i2, long j, int i3) {
        this.mFlags = i;
        this.mViewId = i2;
        this.mVirtualIntId = (i & 1) != 0 ? (int) j : -1;
        this.mVirtualLongId = (i & 2) == 0 ? -1L : j;
        this.mSessionId = i3;
    }

    public static AutofillId withoutSession(AutofillId autofillId) {
        long j;
        int i = autofillId.mFlags;
        int i2 = i & (-5);
        if ((i & 2) != 0) {
            j = autofillId.mVirtualLongId;
        } else {
            j = autofillId.mVirtualIntId;
        }
        return new AutofillId(i2, autofillId.mViewId, j, 0);
    }

    public int getViewId() {
        return this.mViewId;
    }

    public int getVirtualChildIntId() {
        return this.mVirtualIntId;
    }

    public long getVirtualChildLongId() {
        return this.mVirtualLongId;
    }

    public boolean isVirtualInt() {
        return (this.mFlags & 1) != 0;
    }

    public boolean isVirtualLong() {
        return (this.mFlags & 2) != 0;
    }

    public boolean isNonVirtual() {
        return (isVirtualInt() || isVirtualLong()) ? false : true;
    }

    public boolean hasSession() {
        return (this.mFlags & 4) != 0;
    }

    public int getSessionId() {
        return this.mSessionId;
    }

    public void setSessionId(int i) {
        this.mFlags |= 4;
        this.mSessionId = i;
    }

    public void resetSessionId() {
        this.mFlags &= -5;
        this.mSessionId = 0;
    }

    public int hashCode() {
        int i = (((this.mViewId + 31) * 31) + this.mVirtualIntId) * 31;
        long j = this.mVirtualLongId;
        return ((i + ((int) (j ^ (j >>> 32)))) * 31) + this.mSessionId;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AutofillId autofillId = (AutofillId) obj;
        return this.mViewId == autofillId.mViewId && this.mVirtualIntId == autofillId.mVirtualIntId && this.mVirtualLongId == autofillId.mVirtualLongId && this.mSessionId == autofillId.mSessionId;
    }

    public boolean equalsIgnoreSession(AutofillId autofillId) {
        if (this == autofillId) {
            return true;
        }
        return autofillId != null && this.mViewId == autofillId.mViewId && this.mVirtualIntId == autofillId.mVirtualIntId && this.mVirtualLongId == autofillId.mVirtualLongId;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.mViewId);
        if (isVirtualInt()) {
            sb.append(ShortcutConstants.SERVICES_SEPARATOR);
            sb.append(this.mVirtualIntId);
        } else if (isVirtualLong()) {
            sb.append(ShortcutConstants.SERVICES_SEPARATOR);
            sb.append(this.mVirtualLongId);
        }
        if (hasSession()) {
            sb.append('@');
            sb.append(this.mSessionId);
        }
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mViewId);
        parcel.writeInt(this.mFlags);
        if (hasSession()) {
            parcel.writeInt(this.mSessionId);
        }
        if (isVirtualInt()) {
            parcel.writeInt(this.mVirtualIntId);
        } else if (isVirtualLong()) {
            parcel.writeLong(this.mVirtualLongId);
        }
    }
}
