package com.byd.amazon.music.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class Result implements Parcelable {
    public static final Parcelable.Creator<Result> CREATOR = new Parcelable.Creator<Result>() { // from class: com.byd.amazon.music.bean.Result.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Result createFromParcel(Parcel parcel) {
            return new Result(parcel.readInt());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Result[] newArray(int i) {
            return new Result[i];
        }
    };
    public static final int RESULT_ERROR_MEDIA_IS_COLLECTED = 1010;
    public static final int RESULT_ERROR_MEDIA_IS_NOT_COLLECTED = 1011;
    public static final int RESULT_ERROR_NOT_EXIST_PLAY_LIST = 1003;
    public static final int RESULT_ERROR_NOT_LOGE_IN = 1001;
    public static final int RESULT_ERROR_NOT_SUPPORT = 1002;
    public static final int RESULT_ERROR_NOT_SUPPORT_IN__AD_SCENE = 1005;
    public static final int RESULT_ERROR_NOT_SUPPORT_LANGUAGES = 1004;
    public static final int RESULT_ERROR_NOT_SUPPORT_SEEK_TO_ENABLED = 1006;
    public static final int RESULT_ERROR_NOT_SUPPORT_SKIP_NEXT_ENABLED = 1008;
    public static final int RESULT_ERROR_NOT_SUPPORT_SKIP_PREVIOUS_ENABLED = 1007;
    public static final int RESULT_ERROR_NO_NEXT_SONG = 1009;
    public static final int RESULT_ERROR_REPEAT_SET_MODE = 1012;
    public static final int RESULT_ERROR_UNKNOWN = -1;
    public static final int RESULT_SUCCESS = 0;
    protected int mResult;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Result() {
    }

    public Result(int i) {
        this.mResult = i;
    }

    public int getValue() {
        return this.mResult;
    }

    public static Result success() {
        return new Result(0);
    }

    public static Result error() {
        return new Result(-1);
    }

    public static Result result(int i) {
        return new Result(i);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mResult);
    }
}
