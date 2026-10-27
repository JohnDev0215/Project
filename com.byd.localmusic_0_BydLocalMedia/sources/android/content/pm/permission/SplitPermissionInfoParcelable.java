package android.content.pm.permission;

import android.annotation.IntRange;
import android.annotation.NonNull;
import android.os.Parcel;
import android.os.Parcelable;
import com.android.internal.util.AnnotationValidations;
import com.android.internal.util.Preconditions;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class SplitPermissionInfoParcelable implements Parcelable {
    public static final Parcelable.Creator<SplitPermissionInfoParcelable> CREATOR = new Parcelable.Creator<SplitPermissionInfoParcelable>() { // from class: android.content.pm.permission.SplitPermissionInfoParcelable.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SplitPermissionInfoParcelable[] newArray(int i) {
            return new SplitPermissionInfoParcelable[i];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SplitPermissionInfoParcelable createFromParcel(Parcel parcel) {
            String string = parcel.readString();
            ArrayList arrayList = new ArrayList();
            parcel.readStringList(arrayList);
            return new SplitPermissionInfoParcelable(string, arrayList, parcel.readInt());
        }
    };
    private final List<String> mNewPermissions;
    private final String mSplitPermission;
    private final int mTargetSdk;

    @Deprecated
    private void __metadata() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private void onConstructed() {
        Preconditions.checkCollectionElementsNotNull(this.mNewPermissions, "newPermissions");
    }

    public SplitPermissionInfoParcelable(String str, List<String> list, int i) {
        this.mSplitPermission = str;
        AnnotationValidations.validate((Class<NonNull>) NonNull.class, (NonNull) null, (Object) str);
        this.mNewPermissions = list;
        AnnotationValidations.validate((Class<NonNull>) NonNull.class, (NonNull) null, (Object) list);
        this.mTargetSdk = i;
        AnnotationValidations.validate((Class<IntRange>) IntRange.class, (IntRange) null, i, "from", 0L);
        onConstructed();
    }

    public String getSplitPermission() {
        return this.mSplitPermission;
    }

    public List<String> getNewPermissions() {
        return this.mNewPermissions;
    }

    public int getTargetSdk() {
        return this.mTargetSdk;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SplitPermissionInfoParcelable splitPermissionInfoParcelable = (SplitPermissionInfoParcelable) obj;
        return Objects.equals(this.mSplitPermission, splitPermissionInfoParcelable.mSplitPermission) && Objects.equals(this.mNewPermissions, splitPermissionInfoParcelable.mNewPermissions) && this.mTargetSdk == splitPermissionInfoParcelable.mTargetSdk;
    }

    public int hashCode() {
        return ((((Objects.hashCode(this.mSplitPermission) + 31) * 31) + Objects.hashCode(this.mNewPermissions)) * 31) + this.mTargetSdk;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mSplitPermission);
        parcel.writeStringList(this.mNewPermissions);
        parcel.writeInt(this.mTargetSdk);
    }
}
