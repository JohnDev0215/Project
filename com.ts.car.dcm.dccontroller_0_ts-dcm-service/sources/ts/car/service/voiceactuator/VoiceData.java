package ts.car.service.voiceactuator;

import android.content.Intent;

/* JADX INFO: loaded from: classes.dex */
public class VoiceData {
    private String mAlbum;
    private String mArtist;
    private int mErrorCode;
    private Intent mIntent;
    private String mName;
    private int mResult;
    private int mSubType;
    private float mTargetValue;
    private int mType;

    public void setResult(int result) {
        this.mResult = result;
    }

    public void setType(int type) {
        this.mType = type;
    }

    public void setSubType(int subType) {
        this.mSubType = subType;
    }

    public void setIntent(Intent intent) {
        this.mIntent = intent;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public void setAlbum(String album) {
        this.mAlbum = album;
    }

    public void setArtist(String artist) {
        this.mArtist = artist;
    }

    public void setTargetValue(float targetValue) {
        this.mTargetValue = targetValue;
    }

    public void setErrorCode(int errorCode) {
        this.mErrorCode = errorCode;
    }

    public int getType() {
        return this.mType;
    }

    public int getSubType() {
        return this.mSubType;
    }

    public Intent getIntent() {
        return this.mIntent;
    }

    public String getName() {
        return this.mName;
    }

    public String getAlbum() {
        return this.mAlbum;
    }

    public String getArtist() {
        return this.mArtist;
    }

    public float getTargetValue() {
        return this.mTargetValue;
    }

    public int getResult() {
        return this.mResult;
    }

    public int getErrorCode() {
        return this.mErrorCode;
    }

    public VoiceData() {
    }

    public VoiceData(int type, int subType, String name, float targetValue) {
        this.mType = type;
        this.mSubType = subType;
        this.mName = name;
        this.mTargetValue = targetValue;
    }

    public VoiceData(int type, int subType, Intent intent, String name, float targetValue) {
        this.mType = type;
        this.mSubType = subType;
        this.mIntent = intent;
        this.mName = name;
        this.mTargetValue = targetValue;
    }

    public VoiceData(int type, int subType, Intent intent, String name, String album, String artist, float targetValue) {
        this.mType = type;
        this.mSubType = subType;
        this.mIntent = intent;
        this.mName = name;
        this.mAlbum = album;
        this.mArtist = artist;
        this.mTargetValue = targetValue;
    }

    public VoiceData(int type, int subType, Intent intent, String name, String album, String artist, float targetValue, int result, int errorCode) {
        this.mType = type;
        this.mSubType = subType;
        this.mIntent = intent;
        this.mName = name;
        this.mAlbum = album;
        this.mArtist = artist;
        this.mTargetValue = targetValue;
        this.mResult = result;
        this.mErrorCode = errorCode;
    }
}
