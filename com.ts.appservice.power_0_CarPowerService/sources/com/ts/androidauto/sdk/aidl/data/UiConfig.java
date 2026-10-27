package com.ts.androidauto.sdk.aidl.data;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class UiConfig implements Parcelable {
    public static final Parcelable.Creator<UiConfig> CREATOR = new Parcelable.Creator<UiConfig>() { // from class: com.ts.androidauto.sdk.aidl.data.UiConfig.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public UiConfig createFromParcel(Parcel in) {
            return new UiConfig(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public UiConfig[] newArray(int size) {
            return new UiConfig[size];
        }
    };
    public AapClusterUiConfig mAapClusterUiConfig1080p;
    public AapClusterUiConfig mAapClusterUiConfig480p;
    public AapClusterUiConfig mAapClusterUiConfig720p;

    public static class AapClusterUiConfig implements Parcelable {
        public static final Parcelable.Creator<AapClusterUiConfig> CREATOR = new Parcelable.Creator<AapClusterUiConfig>() { // from class: com.ts.androidauto.sdk.aidl.data.UiConfig.AapClusterUiConfig.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public AapClusterUiConfig createFromParcel(Parcel in) {
                return new AapClusterUiConfig(in);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public AapClusterUiConfig[] newArray(int size) {
                return new AapClusterUiConfig[size];
            }
        };
        public int mAapContentInsetsBottom;
        public int mAapContentInsetsLeft;
        public int mAapContentInsetsRight;
        public int mAapContentInsetsTop;
        public int mAapMarginBottom;
        public int mAapMarginLeft;
        public int mAapMarginRight;
        public int mAapMarginTop;
        public int mAapStableContentInsetsBottom;
        public int mAapStableContentInsetsLeft;
        public int mAapStableContentInsetsRight;
        public int mAapStableContentInsetsTop;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public AapClusterUiConfig() {
        }

        public AapClusterUiConfig(Parcel in) {
            this.mAapContentInsetsLeft = in.readInt();
            this.mAapContentInsetsRight = in.readInt();
            this.mAapContentInsetsTop = in.readInt();
            this.mAapContentInsetsBottom = in.readInt();
            this.mAapStableContentInsetsLeft = in.readInt();
            this.mAapStableContentInsetsRight = in.readInt();
            this.mAapStableContentInsetsTop = in.readInt();
            this.mAapStableContentInsetsBottom = in.readInt();
            this.mAapMarginLeft = in.readInt();
            this.mAapMarginRight = in.readInt();
            this.mAapMarginTop = in.readInt();
            this.mAapMarginBottom = in.readInt();
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.mAapContentInsetsLeft);
            dest.writeInt(this.mAapContentInsetsRight);
            dest.writeInt(this.mAapContentInsetsTop);
            dest.writeInt(this.mAapContentInsetsBottom);
            dest.writeInt(this.mAapStableContentInsetsLeft);
            dest.writeInt(this.mAapStableContentInsetsRight);
            dest.writeInt(this.mAapStableContentInsetsTop);
            dest.writeInt(this.mAapStableContentInsetsBottom);
            dest.writeInt(this.mAapMarginLeft);
            dest.writeInt(this.mAapMarginRight);
            dest.writeInt(this.mAapMarginTop);
            dest.writeInt(this.mAapMarginBottom);
        }

        public String toString() {
            StringBuffer sb = new StringBuffer();
            sb.append("AapClusterUiConfig={");
            sb.append(" mAapContentInsetsLeft=");
            sb.append(this.mAapContentInsetsLeft);
            sb.append(" mAapContentInsetsRight=");
            sb.append(this.mAapContentInsetsRight);
            sb.append(" mAapContentInsetsTop=");
            sb.append(this.mAapContentInsetsTop);
            sb.append(" mAapContentInsetsBottom=");
            sb.append(this.mAapContentInsetsBottom);
            sb.append(" mAapStableContentInsetsLeft=");
            sb.append(this.mAapStableContentInsetsLeft);
            sb.append(" mAapStableContentInsetsRight=");
            sb.append(this.mAapStableContentInsetsRight);
            sb.append(" mAapStableContentTop=");
            sb.append(this.mAapStableContentInsetsTop);
            sb.append(" mAapStableContentInsetsBottom=");
            sb.append(this.mAapStableContentInsetsBottom);
            sb.append(" mAapMarginLeft=");
            sb.append(this.mAapMarginLeft);
            sb.append(" mAapMarginRight=");
            sb.append(this.mAapMarginRight);
            sb.append(" mAapMarginTop=");
            sb.append(this.mAapMarginTop);
            sb.append(" mAapMarginBottom=");
            sb.append(this.mAapMarginBottom);
            sb.append(" }");
            return sb.toString();
        }
    }

    public UiConfig() {
        this.mAapClusterUiConfig480p = new AapClusterUiConfig();
        this.mAapClusterUiConfig720p = new AapClusterUiConfig();
        this.mAapClusterUiConfig1080p = new AapClusterUiConfig();
    }

    public UiConfig(Parcel in) {
        this.mAapClusterUiConfig480p = new AapClusterUiConfig();
        this.mAapClusterUiConfig720p = new AapClusterUiConfig();
        this.mAapClusterUiConfig1080p = new AapClusterUiConfig();
        this.mAapClusterUiConfig480p = (AapClusterUiConfig) in.readParcelable(AapClusterUiConfig.class.getClassLoader());
        this.mAapClusterUiConfig720p = (AapClusterUiConfig) in.readParcelable(AapClusterUiConfig.class.getClassLoader());
        this.mAapClusterUiConfig1080p = (AapClusterUiConfig) in.readParcelable(AapClusterUiConfig.class.getClassLoader());
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.mAapClusterUiConfig480p, flags);
        dest.writeParcelable(this.mAapClusterUiConfig720p, flags);
        dest.writeParcelable(this.mAapClusterUiConfig1080p, flags);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        return "mAapClusterUiConfig480p:" + this.mAapClusterUiConfig480p + " mAapClusterUiConfig720p:" + this.mAapClusterUiConfig720p + " mAapClusterUiConfig1080p:" + this.mAapClusterUiConfig1080p;
    }
}
