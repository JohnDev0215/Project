package android.permission;

import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;
import com.android.internal.util.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
@SystemApi
public final class RuntimePermissionPresentationInfo implements Parcelable {
    public static final Parcelable.Creator<RuntimePermissionPresentationInfo> CREATOR = new Parcelable.Creator<RuntimePermissionPresentationInfo>() { // from class: android.permission.RuntimePermissionPresentationInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RuntimePermissionPresentationInfo createFromParcel(Parcel parcel) {
            CharSequence charSequence = parcel.readCharSequence();
            int i = parcel.readInt();
            return new RuntimePermissionPresentationInfo(charSequence, (i & 1) != 0, (i & 2) != 0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RuntimePermissionPresentationInfo[] newArray(int i) {
            return new RuntimePermissionPresentationInfo[i];
        }
    };
    private static final int FLAG_GRANTED = 1;
    private static final int FLAG_STANDARD = 2;
    private final int mFlags;
    private final CharSequence mLabel;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public RuntimePermissionPresentationInfo(CharSequence charSequence, boolean z, boolean z2) {
        Preconditions.checkNotNull(charSequence);
        this.mLabel = charSequence;
        int i = z ? 1 : 0;
        this.mFlags = z2 ? i | 2 : i;
    }

    public boolean isGranted() {
        return (this.mFlags & 1) != 0;
    }

    public boolean isStandard() {
        return (this.mFlags & 2) != 0;
    }

    public CharSequence getLabel() {
        return this.mLabel;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeCharSequence(this.mLabel);
        parcel.writeInt(this.mFlags);
    }
}
