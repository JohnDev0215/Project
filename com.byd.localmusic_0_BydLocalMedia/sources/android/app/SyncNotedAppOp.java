package android.app;

import android.annotation.IntRange;
import android.os.Parcel;
import android.os.Parcelable;
import com.android.internal.util.AnnotationValidations;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class SyncNotedAppOp implements Parcelable {
    public static final Parcelable.Creator<SyncNotedAppOp> CREATOR = new Parcelable.Creator<SyncNotedAppOp>() { // from class: android.app.SyncNotedAppOp.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SyncNotedAppOp[] newArray(int i) {
            return new SyncNotedAppOp[i];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SyncNotedAppOp createFromParcel(Parcel parcel) {
            return new SyncNotedAppOp(parcel);
        }
    };
    private final String mAttributionTag;
    private final int mOpCode;

    @Deprecated
    private void __metadata() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public SyncNotedAppOp(int i, String str) {
        this.mOpCode = i;
        AnnotationValidations.validate((Class<IntRange>) IntRange.class, (IntRange) null, i, "from", 0L, "to", 102L);
        this.mAttributionTag = str;
    }

    public String getOp() {
        return AppOpsManager.opToPublicName(this.mOpCode);
    }

    public String getAttributionTag() {
        return this.mAttributionTag;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SyncNotedAppOp syncNotedAppOp = (SyncNotedAppOp) obj;
        return this.mOpCode == syncNotedAppOp.mOpCode && Objects.equals(this.mAttributionTag, syncNotedAppOp.mAttributionTag);
    }

    public int hashCode() {
        return ((this.mOpCode + 31) * 31) + Objects.hashCode(this.mAttributionTag);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte(this.mAttributionTag != null ? (byte) 2 : (byte) 0);
        parcel.writeInt(this.mOpCode);
        String str = this.mAttributionTag;
        if (str != null) {
            parcel.writeString(str);
        }
    }

    SyncNotedAppOp(Parcel parcel) {
        byte b = parcel.readByte();
        int i = parcel.readInt();
        String string = (b & 2) == 0 ? null : parcel.readString();
        this.mOpCode = i;
        AnnotationValidations.validate((Class<IntRange>) IntRange.class, (IntRange) null, i, "from", 0L, "to", 102L);
        this.mAttributionTag = string;
    }
}
