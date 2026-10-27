package android.car.vms;

import android.annotation.NonNull;
import android.os.Parcel;
import android.os.Parcelable;
import com.android.internal.util.AnnotationValidations;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class VmsRegistrationInfo implements Parcelable {
    public static final Parcelable.Creator<VmsRegistrationInfo> CREATOR = new Parcelable.Creator<VmsRegistrationInfo>() { // from class: android.car.vms.VmsRegistrationInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public VmsRegistrationInfo[] newArray(int i) {
            return new VmsRegistrationInfo[i];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public VmsRegistrationInfo createFromParcel(Parcel parcel) {
            return new VmsRegistrationInfo(parcel);
        }
    };
    private VmsAvailableLayers mAvailableLayers;
    private VmsSubscriptionState mSubscriptionState;

    @Deprecated
    private void __metadata() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public VmsRegistrationInfo(VmsAvailableLayers vmsAvailableLayers, VmsSubscriptionState vmsSubscriptionState) {
        this.mAvailableLayers = vmsAvailableLayers;
        AnnotationValidations.validate((Class<NonNull>) NonNull.class, (NonNull) null, (Object) vmsAvailableLayers);
        this.mSubscriptionState = vmsSubscriptionState;
        AnnotationValidations.validate((Class<NonNull>) NonNull.class, (NonNull) null, (Object) vmsSubscriptionState);
    }

    public VmsAvailableLayers getAvailableLayers() {
        return this.mAvailableLayers;
    }

    public VmsSubscriptionState getSubscriptionState() {
        return this.mSubscriptionState;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        VmsRegistrationInfo vmsRegistrationInfo = (VmsRegistrationInfo) obj;
        return Objects.equals(this.mAvailableLayers, vmsRegistrationInfo.mAvailableLayers) && Objects.equals(this.mSubscriptionState, vmsRegistrationInfo.mSubscriptionState);
    }

    public int hashCode() {
        return ((Objects.hashCode(this.mAvailableLayers) + 31) * 31) + Objects.hashCode(this.mSubscriptionState);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedObject(this.mAvailableLayers, i);
        parcel.writeTypedObject(this.mSubscriptionState, i);
    }

    protected VmsRegistrationInfo(Parcel parcel) {
        VmsAvailableLayers vmsAvailableLayers = (VmsAvailableLayers) parcel.readTypedObject(VmsAvailableLayers.CREATOR);
        VmsSubscriptionState vmsSubscriptionState = (VmsSubscriptionState) parcel.readTypedObject(VmsSubscriptionState.CREATOR);
        this.mAvailableLayers = vmsAvailableLayers;
        AnnotationValidations.validate((Class<NonNull>) NonNull.class, (NonNull) null, (Object) vmsAvailableLayers);
        this.mSubscriptionState = vmsSubscriptionState;
        AnnotationValidations.validate((Class<NonNull>) NonNull.class, (NonNull) null, (Object) vmsSubscriptionState);
    }
}
