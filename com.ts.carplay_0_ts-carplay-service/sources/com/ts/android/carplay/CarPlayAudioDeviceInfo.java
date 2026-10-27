package com.ts.android.carplay;

/* JADX INFO: loaded from: classes.dex */
public class CarPlayAudioDeviceInfo {
    public static final int TYPE_ALERT = 3;
    public static final int TYPE_MUSIC = 0;
    public static final int TYPE_NAVI = 2;
    public static final int TYPE_PHONE_16K = 5;
    public static final int TYPE_PHONE_24K = 8;
    public static final int TYPE_PHONE_32K = 6;
    public static final int TYPE_PHONE_48K = 7;
    public static final int TYPE_PHONE_8K = 4;
    public static final int TYPE_SIRI = 1;
    public int mMusicId = 0;
    public int mSiriId = 0;
    public int mNaviId = 0;
    public int mAlertId = 0;
    public int mPhone8kId = 0;
    public int mPhone16kId = 0;
    public int mPhone24kId = 0;
    public int mPhone32kId = 0;
    public int mPhone48kId = 0;

    public void setAAudioDeviceId(int deviceId, int type) {
        switch (type) {
            case 0:
                this.mMusicId = deviceId;
                break;
            case 1:
                this.mSiriId = deviceId;
                break;
            case 2:
                this.mNaviId = deviceId;
                break;
            case 3:
                this.mAlertId = deviceId;
                break;
            case 4:
                this.mPhone8kId = deviceId;
                break;
            case 5:
                this.mPhone16kId = deviceId;
                break;
            case 6:
                this.mPhone32kId = deviceId;
                break;
            case 7:
                this.mPhone48kId = deviceId;
                break;
            case 8:
                this.mPhone24kId = deviceId;
                break;
        }
    }
}
