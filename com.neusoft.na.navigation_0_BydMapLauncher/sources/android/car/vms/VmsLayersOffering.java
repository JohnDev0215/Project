package android.car.vms;

import android.annotation.NonNull;
import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.ArraySet;
import com.android.internal.util.AnnotationValidations;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
@SystemApi
@Deprecated
public final class VmsLayersOffering implements Parcelable {
    public static final Parcelable.Creator<VmsLayersOffering> CREATOR = new Parcelable.Creator<VmsLayersOffering>() { // from class: android.car.vms.VmsLayersOffering.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public VmsLayersOffering[] newArray(int i) {
            return new VmsLayersOffering[i];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public VmsLayersOffering createFromParcel(Parcel parcel) {
            return new VmsLayersOffering(parcel);
        }
    };
    private Set<VmsLayerDependency> mDependencies;
    private final int mPublisherId;

    @Deprecated
    private void __metadata() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private void onConstructed() {
        this.mDependencies = Collections.unmodifiableSet(this.mDependencies);
    }

    private void parcelDependencies(Parcel parcel, int i) {
        parcel.writeArraySet(new ArraySet(this.mDependencies));
    }

    private Set<VmsLayerDependency> unparcelDependencies(Parcel parcel) {
        return parcel.readArraySet(VmsLayerDependency.class.getClassLoader());
    }

    public VmsLayersOffering(Set<VmsLayerDependency> set, int i) {
        this.mDependencies = set;
        AnnotationValidations.validate(NonNull.class, (NonNull) null, set);
        this.mPublisherId = i;
        onConstructed();
    }

    public Set<VmsLayerDependency> getDependencies() {
        return this.mDependencies;
    }

    public int getPublisherId() {
        return this.mPublisherId;
    }

    public String toString() {
        return "VmsLayersOffering { dependencies = " + this.mDependencies + ", publisherId = " + this.mPublisherId + " }";
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        VmsLayersOffering vmsLayersOffering = (VmsLayersOffering) obj;
        return Objects.equals(this.mDependencies, vmsLayersOffering.mDependencies) && this.mPublisherId == vmsLayersOffering.mPublisherId;
    }

    public int hashCode() {
        return ((Objects.hashCode(this.mDependencies) + 31) * 31) + this.mPublisherId;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcelDependencies(parcel, i);
        parcel.writeInt(this.mPublisherId);
    }

    VmsLayersOffering(Parcel parcel) {
        Set<VmsLayerDependency> setUnparcelDependencies = unparcelDependencies(parcel);
        int i = parcel.readInt();
        this.mDependencies = setUnparcelDependencies;
        AnnotationValidations.validate(NonNull.class, (NonNull) null, setUnparcelDependencies);
        this.mPublisherId = i;
        onConstructed();
    }
}
