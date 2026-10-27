package android.hardware.bydauto.startup;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class StartupApp implements Parcelable {
    public static final Parcelable.Creator<StartupApp> CREATOR = new Parcelable.Creator<StartupApp>() { // from class: android.hardware.bydauto.startup.StartupApp.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public StartupApp createFromParcel(Parcel parcel) {
            StartupApp startupApp = new StartupApp();
            startupApp.mPackageName = parcel.readString();
            startupApp.mEnable = parcel.readBoolean();
            return startupApp;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public StartupApp[] newArray(int i3) {
            return new StartupApp[i3];
        }
    };
    private boolean mEnable;
    private String mPackageName;

    public StartupApp() {
        this.mPackageName = "";
        this.mEnable = false;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean getEnable() {
        return this.mEnable;
    }

    public String getPackageName() {
        return this.mPackageName;
    }

    public void setEnable(boolean z3) {
        this.mEnable = z3;
    }

    public void setPackageName(String str) {
        this.mPackageName = str;
    }

    public String toString() {
        return "mPackageName : " + this.mPackageName + ", mEnable : " + this.mEnable;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i3) {
        parcel.writeString(this.mPackageName);
        parcel.writeBoolean(this.mEnable);
    }

    public StartupApp(String str, boolean z3) {
        this.mPackageName = str;
        this.mEnable = z3;
    }
}
