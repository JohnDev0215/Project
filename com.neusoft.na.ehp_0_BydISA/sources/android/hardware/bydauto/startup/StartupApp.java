package android.hardware.bydauto.startup;

import android.os.Parcel;
import android.os.Parcelable;
import org.java_websocket.extensions.ExtensionRequestData;

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
        public StartupApp[] newArray(int i) {
            return new StartupApp[i];
        }
    };
    private boolean mEnable;
    private String mPackageName;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public StartupApp() {
        this.mPackageName = ExtensionRequestData.EMPTY_VALUE;
        this.mEnable = false;
    }

    public StartupApp(String str, boolean z) {
        this.mPackageName = str;
        this.mEnable = z;
    }

    public void setPackageName(String str) {
        this.mPackageName = str;
    }

    public String getPackageName() {
        return this.mPackageName;
    }

    public void setEnable(boolean z) {
        this.mEnable = z;
    }

    public boolean getEnable() {
        return this.mEnable;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("mPackageName : ").append(this.mPackageName);
        sb.append(", mEnable : ").append(this.mEnable);
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mPackageName);
        parcel.writeBoolean(this.mEnable);
    }
}
