package com.ts.lib.settings.boot;

import android.os.Parcel;
import android.os.Parcelable;
import com.ts.androidauto.fsdk.common.AndroidAutoConst;

/* JADX INFO: loaded from: classes2.dex */
public class BootApp implements Parcelable {
    public static final Parcelable.Creator<BootApp> CREATOR = new Parcelable.Creator<BootApp>() { // from class: com.ts.lib.settings.boot.BootApp.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BootApp createFromParcel(Parcel in) {
            BootApp item = new BootApp();
            item.mPackageName = in.readString();
            item.mEnable = in.readBoolean();
            return item;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BootApp[] newArray(int size) {
            return new BootApp[size];
        }
    };
    private boolean mEnable;
    private String mPackageName;

    public BootApp() {
        this.mPackageName = AndroidAutoConst.DEFAULT_SCREEN_NAME;
        this.mEnable = false;
    }

    public BootApp(String pkg, boolean enable) {
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
