package com.amazon.mp3.playback.service.exception;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.net.dmls.ContentId;
import com.amazon.mp3.net.dmls.DMLSExceptions;
import com.amazon.mp3.net.dmls.DMLSStreamingStatusResponse;
import com.amazon.mp3.net.dmls.IStreamingConcurrencyStatusResponse;
import com.amazon.mp3.net.dmls.IdentifierType;
import com.amazon.mp3.net.dmls.StatusCode;
import com.amazon.mp3.util.Log;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes3.dex */
public class PrimeStreamingConcurrencyException extends Exception implements IStreamingConcurrencyStatusResponse, Parcelable {
    private ContentId mContentId;
    private Context mContext;
    private long mInactivityThreshold;
    private boolean mIsMultipleConcurrentStreams;
    private StatusCode mStatusCode;
    private String mStatusMessage;
    private ArrayList<DMLSStreamingStatusResponse> mStreamingResponseList;
    private long mTrackDuration;
    private static final String TAG = PrimeStreamingConcurrencyException.class.getSimpleName();
    public static final Parcelable.Creator<PrimeStreamingConcurrencyException> CREATOR = new Parcelable.Creator<PrimeStreamingConcurrencyException>() { // from class: com.amazon.mp3.playback.service.exception.PrimeStreamingConcurrencyException.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PrimeStreamingConcurrencyException createFromParcel(Parcel in) {
            return new PrimeStreamingConcurrencyException(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PrimeStreamingConcurrencyException[] newArray(int size) {
            return new PrimeStreamingConcurrencyException[size];
        }
    };
    private String mDialogTitle = null;
    private String mDialogMessage = null;
    private String mPositiveButtonText = null;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public PrimeStreamingConcurrencyException(Parcel in) {
        this.mStatusCode = StatusCode.getFromString(in.readString());
        this.mStatusMessage = in.readString();
        ArrayList<DMLSStreamingStatusResponse> arrayList = in.readArrayList(DMLSStreamingStatusResponse.class.getClassLoader());
        this.mStreamingResponseList = arrayList;
        this.mIsMultipleConcurrentStreams = arrayList.size() > 1;
        try {
            this.mContentId = new ContentId(IdentifierType.fromString(in.readString()), in.readString());
        } catch (DMLSExceptions.InvalidContentIdException e) {
            Log.warning(TAG, "Unable to read in Content Id from Parcel", e);
            this.mContentId = new ContentId();
        }
        this.mTrackDuration = in.readLong();
    }

    @Override // com.amazon.mp3.net.dmls.IStreamingConcurrencyStatusResponse
    public StatusCode getStatusCode() {
        return this.mStatusCode;
    }

    @Override // com.amazon.mp3.net.dmls.IStreamingConcurrencyStatusResponse
    public String getStatusMessage() {
        return this.mStatusMessage;
    }

    @Override // com.amazon.mp3.net.dmls.IStreamingConcurrencyStatusResponse
    public ArrayList<DMLSStreamingStatusResponse> getStreamingResponseList() {
        return this.mStreamingResponseList;
    }

    @Override // com.amazon.mp3.net.dmls.IStreamingConcurrencyStatusResponse
    public long getInactivityThreshold() {
        return this.mInactivityThreshold;
    }

    public ContentId getContentId() {
        return this.mContentId;
    }

    public long getTrackDuration() {
        return this.mTrackDuration;
    }

    public String getPositiveButtonText() {
        if (this.mPositiveButtonText == null) {
            this.mPositiveButtonText = this.mContext.getResources().getString(R.string.dmusic_prime_streaming_concurrency_dlg_continue);
        }
        return this.mPositiveButtonText;
    }

    public String getDialogMessage() {
        if (this.mDialogMessage == null) {
            if (this.mStreamingResponseList.size() == 1) {
                if (TextUtils.isEmpty(this.mStreamingResponseList.get(0).getRemoteDeviceName())) {
                    this.mDialogMessage = this.mContext.getResources().getString(R.string.dmusic_prime_streaming_concurrency_dlg_message_no_device_name_no_time, getPositiveButtonText());
                } else {
                    this.mDialogMessage = this.mContext.getResources().getString(R.string.dmusic_prime_streaming_concurrency_dlg_message_w_device_name_no_time, this.mStreamingResponseList.get(0).getRemoteDeviceName(), getPositiveButtonText());
                }
            } else if (this.mStreamingResponseList.size() > 1) {
                this.mDialogMessage = this.mContext.getResources().getString(R.string.dmusic_prime_streaming_concurrency_dlg_message_family, getPositiveButtonText());
            }
        }
        return this.mDialogMessage;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mStatusCode.toString());
        dest.writeString(this.mStatusMessage);
        dest.writeList(this.mStreamingResponseList);
        dest.writeString(this.mContentId.getType().getName());
        dest.writeString(this.mContentId.getId());
        dest.writeLong(this.mTrackDuration);
    }
}
