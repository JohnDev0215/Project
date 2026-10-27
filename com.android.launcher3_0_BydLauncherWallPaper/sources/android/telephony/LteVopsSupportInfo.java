package android.telephony;

import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
@SystemApi
public final class LteVopsSupportInfo implements Parcelable {
    public static final Parcelable.Creator<LteVopsSupportInfo> CREATOR = new Parcelable.Creator<LteVopsSupportInfo>() { // from class: android.telephony.LteVopsSupportInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LteVopsSupportInfo createFromParcel(Parcel parcel) {
            return new LteVopsSupportInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LteVopsSupportInfo[] newArray(int i) {
            return new LteVopsSupportInfo[i];
        }
    };
    public static final int LTE_STATUS_NOT_AVAILABLE = 1;
    public static final int LTE_STATUS_NOT_SUPPORTED = 3;
    public static final int LTE_STATUS_SUPPORTED = 2;
    private final int mEmcBearerSupport;
    private final int mVopsSupport;

    @Retention(RetentionPolicy.SOURCE)
    public @interface LteVopsStatus {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public LteVopsSupportInfo(int i, int i2) {
        this.mVopsSupport = i;
        this.mEmcBearerSupport = i2;
    }

    public int getVopsSupport() {
        return this.mVopsSupport;
    }

    public int getEmcBearerSupport() {
        return this.mEmcBearerSupport;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mVopsSupport);
        parcel.writeInt(this.mEmcBearerSupport);
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof LteVopsSupportInfo)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        LteVopsSupportInfo lteVopsSupportInfo = (LteVopsSupportInfo) obj;
        return this.mVopsSupport == lteVopsSupportInfo.mVopsSupport && this.mEmcBearerSupport == lteVopsSupportInfo.mEmcBearerSupport;
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.mVopsSupport), Integer.valueOf(this.mEmcBearerSupport));
    }

    public String toString() {
        return "LteVopsSupportInfo :  mVopsSupport = " + this.mVopsSupport + " mEmcBearerSupport = " + this.mEmcBearerSupport;
    }

    private LteVopsSupportInfo(Parcel parcel) {
        this.mVopsSupport = parcel.readInt();
        this.mEmcBearerSupport = parcel.readInt();
    }
}
