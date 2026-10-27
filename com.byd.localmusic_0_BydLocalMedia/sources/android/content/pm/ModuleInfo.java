package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class ModuleInfo implements Parcelable {
    public static final Parcelable.Creator<ModuleInfo> CREATOR = new Parcelable.Creator<ModuleInfo>() { // from class: android.content.pm.ModuleInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ModuleInfo createFromParcel(Parcel parcel) {
            return new ModuleInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ModuleInfo[] newArray(int i) {
            return new ModuleInfo[i];
        }
    };
    private String mApexModuleName;
    private boolean mHidden;
    private CharSequence mName;
    private String mPackageName;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ModuleInfo() {
    }

    public ModuleInfo(ModuleInfo moduleInfo) {
        this.mName = moduleInfo.mName;
        this.mPackageName = moduleInfo.mPackageName;
        this.mHidden = moduleInfo.mHidden;
        this.mApexModuleName = moduleInfo.mApexModuleName;
    }

    public ModuleInfo setName(CharSequence charSequence) {
        this.mName = charSequence;
        return this;
    }

    public CharSequence getName() {
        return this.mName;
    }

    public ModuleInfo setPackageName(String str) {
        this.mPackageName = str;
        return this;
    }

    public String getPackageName() {
        return this.mPackageName;
    }

    public ModuleInfo setHidden(boolean z) {
        this.mHidden = z;
        return this;
    }

    public boolean isHidden() {
        return this.mHidden;
    }

    public ModuleInfo setApexModuleName(String str) {
        this.mApexModuleName = str;
        return this;
    }

    public String getApexModuleName() {
        return this.mApexModuleName;
    }

    public String toString() {
        return "ModuleInfo{" + Integer.toHexString(System.identityHashCode(this)) + " " + ((Object) this.mName) + "}";
    }

    public int hashCode() {
        return ((((((0 + Objects.hashCode(this.mName)) * 31) + Objects.hashCode(this.mPackageName)) * 31) + Objects.hashCode(this.mApexModuleName)) * 31) + Boolean.hashCode(this.mHidden);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ModuleInfo)) {
            return false;
        }
        ModuleInfo moduleInfo = (ModuleInfo) obj;
        return Objects.equals(this.mName, moduleInfo.mName) && Objects.equals(this.mPackageName, moduleInfo.mPackageName) && Objects.equals(this.mApexModuleName, moduleInfo.mApexModuleName) && this.mHidden == moduleInfo.mHidden;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeCharSequence(this.mName);
        parcel.writeString(this.mPackageName);
        parcel.writeBoolean(this.mHidden);
        parcel.writeString(this.mApexModuleName);
    }

    private ModuleInfo(Parcel parcel) {
        this.mName = parcel.readCharSequence();
        this.mPackageName = parcel.readString();
        this.mHidden = parcel.readBoolean();
        this.mApexModuleName = parcel.readString();
    }
}
