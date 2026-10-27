package com.appstore.aidl;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class AidlUpdateBean implements Parcelable {
    public static final Parcelable.Creator<AidlUpdateBean> CREATOR = new Parcelable.Creator<AidlUpdateBean>() { // from class: com.appstore.aidl.AidlUpdateBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AidlUpdateBean createFromParcel(Parcel in) {
            return new AidlUpdateBean(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AidlUpdateBean[] newArray(int size) {
            return new AidlUpdateBean[size];
        }
    };
    private String downloadUrl;
    private Long id;
    private String packageName;
    private String upgradeContent;
    private int versionCode;
    private String versionName;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public AidlUpdateBean() {
    }

    protected AidlUpdateBean(Parcel in) {
        if (in.readByte() == 0) {
            this.id = null;
        } else {
            this.id = Long.valueOf(in.readLong());
        }
        this.packageName = in.readString();
        this.downloadUrl = in.readString();
        this.versionCode = in.readInt();
        this.versionName = in.readString();
        this.upgradeContent = in.readString();
    }

    public String getVersionName() {
        return this.versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getUpgradeContent() {
        return this.upgradeContent;
    }

    public void setUpgradeContent(String upgradeContent) {
        this.upgradeContent = upgradeContent;
    }

    public int getVersionCode() {
        return this.versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getDownloadUrl() {
        return this.downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        if (this.id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(this.id.longValue());
        }
        dest.writeString(this.packageName);
        dest.writeString(this.downloadUrl);
        dest.writeInt(this.versionCode);
        dest.writeString(this.versionName);
        dest.writeString(this.upgradeContent);
    }
}
