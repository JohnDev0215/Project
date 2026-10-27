package com.amazon.mp3.timer;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.ResultReceiver;

/* JADX INFO: loaded from: classes4.dex */
public class SleepTimerCallback implements Parcelable {
    public static final Parcelable.Creator<SleepTimerCallback> CREATOR = new Parcelable.Creator<SleepTimerCallback>() { // from class: com.amazon.mp3.timer.SleepTimerCallback.2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SleepTimerCallback createFromParcel(Parcel in) {
            return new SleepTimerCallback((ResultReceiver) ResultReceiver.CREATOR.createFromParcel(in));
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SleepTimerCallback[] newArray(int size) {
            return new SleepTimerCallback[size];
        }
    };
    private final ResultReceiver mResultReceiver;

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    protected void onCanceled() {
    }

    protected void onFinished() {
    }

    protected void onTick(long seconds) {
    }

    protected SleepTimerCallback() {
        this(new Handler(Looper.getMainLooper()));
    }

    protected SleepTimerCallback(Handler handler) {
        this.mResultReceiver = new ResultReceiver(handler) { // from class: com.amazon.mp3.timer.SleepTimerCallback.1
            @Override // android.os.ResultReceiver
            protected void onReceiveResult(int resultCode, Bundle resultData) {
                super.onReceiveResult(resultCode, resultData);
                if (resultCode == 1) {
                    SleepTimerCallback.this.onFinished();
                } else if (resultCode == 3) {
                    SleepTimerCallback.this.onTick(resultData.getLong("time_reamining"));
                } else {
                    SleepTimerCallback.this.onCanceled();
                }
            }
        };
    }

    public final void send(int resultCode, Bundle resultData) {
        this.mResultReceiver.send(resultCode, resultData);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel dest, int flags) {
        this.mResultReceiver.writeToParcel(dest, flags);
    }

    SleepTimerCallback(ResultReceiver resultReceiver) {
        this.mResultReceiver = resultReceiver;
    }
}
