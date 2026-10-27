package android.hardware.bydauto.startup;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class StartupApp implements Parcelable {
    public static final Parcelable.Creator<StartupApp> CREATOR = new Parcelable.Creator<StartupApp>() { // from class: android.hardware.bydauto.startup.StartupApp.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public StartupApp createFromParcel(Parcel in) {
            StartupApp item = new StartupApp();
            item.mPackageName = in.readString();
            item.mEnable = in.readBoolean();
            return item;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public StartupApp[] newArray(int size) {
            return new StartupApp[size];
        }
    };
    private boolean mEnable;
    private String mPackageName;

    public StartupApp() {
        this.mPackageName = "";
        this.mEnable = false;
    }

    public StartupApp(String pkg, boolean enable) {
        this.mPackageName = pkg;
        this.mEnable = enable;
    }

    public void setPackageName(String pkg) {
        this.mPackageName = pkg;
    }

    public String getPackageName() {
        return this.mPackageName;
    }

    public void setEnable(boolean enable) {
        this.mEnable = enable;
    }

    public boolean getEnable() {
        return this.mEnable;
    }

    public String toString() {
        return "mPackageName : " + this.mPackageName + ", mEnable : " + this.mEnable;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mPackageName);
        dest.writeBoolean(this.mEnable);
    }
}
