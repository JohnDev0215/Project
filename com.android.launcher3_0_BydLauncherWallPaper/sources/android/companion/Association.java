package android.companion;

import android.annotation.NonNull;
import android.os.Parcel;
import android.os.Parcelable;
import com.android.internal.util.AnnotationValidations;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class Association implements Parcelable {
    public static final Parcelable.Creator<Association> CREATOR = new Parcelable.Creator<Association>() { // from class: android.companion.Association.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Association[] newArray(int i) {
            return new Association[i];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Association createFromParcel(Parcel parcel) {
            return new Association(parcel);
        }
    };
    public final String companionAppPackage;
    public final String deviceAddress;
    public final int userId;

    @Deprecated
    private void __metadata() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Association(int i, String str, String str2) {
        this.userId = i;
        this.deviceAddress = str;
        AnnotationValidations.validate((Class<NonNull>) NonNull.class, (NonNull) null, (Object) str);
        this.companionAppPackage = str2;
        AnnotationValidations.validate((Class<NonNull>) NonNull.class, (NonNull) null, (Object) str2);
    }

    public String toString() {
        return "Association { userId = " + this.userId + ", deviceAddress = " + this.deviceAddress + ", companionAppPackage = " + this.companionAppPackage + " }";
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Association association = (Association) obj;
        return this.userId == association.userId && Objects.equals(this.deviceAddress, association.deviceAddress) && Objects.equals(this.companionAppPackage, association.companionAppPackage);
    }

    public int hashCode() {
        return ((((this.userId + 31) * 31) + Objects.hashCode(this.deviceAddress)) * 31) + Objects.hashCode(this.companionAppPackage);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.userId);
        parcel.writeString(this.deviceAddress);
        parcel.writeString(this.companionAppPackage);
    }

    protected Association(Parcel parcel) {
        int i = parcel.readInt();
        String string = parcel.readString();
        String string2 = parcel.readString();
        this.userId = i;
        this.deviceAddress = string;
        AnnotationValidations.validate((Class<NonNull>) NonNull.class, (NonNull) null, (Object) string);
        this.companionAppPackage = string2;
        AnnotationValidations.validate((Class<NonNull>) NonNull.class, (NonNull) null, (Object) string2);
    }
}
