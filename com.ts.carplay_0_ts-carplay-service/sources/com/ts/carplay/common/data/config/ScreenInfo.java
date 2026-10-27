package com.ts.carplay.common.data.config;

import android.os.Parcel;
import android.os.Parcelable;
import com.ts.carplay.common.util.LogEncrypt;

/* JADX INFO: loaded from: classes.dex */
public class ScreenInfo implements Parcelable {
    public static final Parcelable.Creator<ScreenInfo> CREATOR = new Parcelable.Creator<ScreenInfo>() { // from class: com.ts.carplay.common.data.config.ScreenInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ScreenInfo createFromParcel(Parcel in) {
            return new ScreenInfo(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ScreenInfo[] newArray(int size) {
            return new ScreenInfo[size];
        }
    };
    private int mHeightPhysical;
    private int mHeightPixels;
    private int mInitialUrl;
    private boolean mIsSupportMap;
    private int mMapMode;
    private int mMapSetting;
    private int mMaxFps;
    private int mUiMode;
    private int mUiSetting;
    private String mUuid;
    private int mWidthPhysical;
    private int mWidthPixels;

    public static final class InitialUrl {
        public static final int INURL_INSTRUCTION_CARD = 2;
        public static final int INURL_INSTRUMENT_CLUSTER = 3;
        public static final int INURL_INVALID = -1;
        public static final int INURL_MAP = 1;
    }

    public static final class InitialUrlParameter {
        public static final int URL_SHOW_COMPASS = 0;
        public static final int URL_SHOW_ETA = 1;
        public static final int URL_SHOW_SPEED_LIMIT = 2;
    }

    public static final class Mode {
        public static final int DARK = 1;
        public static final int LIGHT = 0;
    }

    public static final class Setting {
        public static final int ALWAYS = 2;
        public static final int AUTOMATIC = 0;
        public static final int USERCHOICE = 1;
    }

    public ScreenInfo() {
        this.mUuid = null;
        this.mHeightPhysical = -1;
        this.mWidthPhysical = -1;
        this.mHeightPixels = -1;
        this.mWidthPixels = -1;
        this.mMaxFps = -1;
        this.mInitialUrl = -1;
        this.mUiMode = -1;
        this.mUiSetting = -1;
        this.mIsSupportMap = false;
        this.mMapMode = -1;
        this.mMapSetting = -1;
    }

    protected ScreenInfo(Parcel in) {
        this.mUuid = in.readString();
        this.mHeightPhysical = in.readInt();
        this.mWidthPhysical = in.readInt();
        this.mHeightPixels = in.readInt();
        this.mWidthPixels = in.readInt();
        this.mMaxFps = in.readInt();
        this.mUiMode = in.readInt();
        this.mUiSetting = in.readInt();
        this.mIsSupportMap = in.readByte() != 0;
        this.mMapMode = in.readInt();
        this.mMapSetting = in.readInt();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mUuid);
        parcel.writeInt(this.mHeightPhysical);
        parcel.writeInt(this.mWidthPhysical);
        parcel.writeInt(this.mHeightPixels);
        parcel.writeInt(this.mWidthPixels);
        parcel.writeInt(this.mMaxFps);
        parcel.writeInt(this.mUiMode);
        parcel.writeInt(this.mUiSetting);
        parcel.writeByte(this.mIsSupportMap ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.mMapMode);
        parcel.writeInt(this.mMapSetting);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getHeightPhysical() {
        return this.mHeightPhysical;
    }

    public void setHeightPhysical(int heightPhysical) {
        this.mHeightPhysical = heightPhysical;
    }

    public int getWidthPhysical() {
        return this.mWidthPhysical;
    }

    public void setWidthPhysical(int widthPhysical) {
        this.mWidthPhysical = widthPhysical;
    }

    public int getHeightPixels() {
        return this.mHeightPixels;
    }

    public void setHeightPixels(int heightPixels) {
        this.mHeightPixels = heightPixels;
    }

    public int getWidthPixels() {
        return this.mWidthPixels;
    }

    public void setWidthPixels(int widthPixels) {
        this.mWidthPixels = widthPixels;
    }

    public int getMaxFps() {
        return this.mMaxFps;
    }

    public void setMaxFps(int maxFps) {
        this.mMaxFps = maxFps;
    }

    public int getInitialUrl() {
        return this.mInitialUrl;
    }

    public void setInitialUrl(int initialUrl) {
        this.mInitialUrl = initialUrl;
    }

    public int getUiMode() {
        return this.mUiMode;
    }

    public void setUiMode(int uiMode) {
        this.mUiMode = uiMode;
    }

    public int getUiSetting() {
        return this.mUiSetting;
    }

    public void setUiSetting(int uiSetting) {
        this.mUiSetting = uiSetting;
    }

    public boolean isIsSupportMap() {
        return this.mIsSupportMap;
    }

    public void setIsSupportMap(boolean isSupportMap) {
        this.mIsSupportMap = isSupportMap;
    }

    public int getMapMode() {
        return this.mMapMode;
    }

    public void setMapMode(int mapMode) {
        this.mMapMode = mapMode;
    }

    public int getMapSetting() {
        return this.mMapSetting;
    }

    public void setMapSetting(int mapSetting) {
        this.mMapSetting = mapSetting;
    }

    public String getUuid() {
        return this.mUuid;
    }

    public void setUuid(String uuid) {
        this.mUuid = uuid;
    }

    public String toString() {
        return "ScreenInfo{mUuid='" + LogEncrypt.MacAddressEncrypt(this.mUuid) + "', mHeightPhysical=" + this.mHeightPhysical + ", mWidthPhysical=" + this.mWidthPhysical + ", mHeightPixels=" + this.mHeightPixels + ", mWidthPixels=" + this.mWidthPixels + ", mMaxFps=" + this.mMaxFps + ", mInitialUrl=" + this.mInitialUrl + ", mUiMode=" + this.mUiMode + ", mUiSetting=" + this.mUiSetting + ", mIsSupportMap=" + this.mIsSupportMap + ", mMapMode=" + this.mMapMode + ", mMapSetting=" + this.mMapSetting + '}';
    }
}
