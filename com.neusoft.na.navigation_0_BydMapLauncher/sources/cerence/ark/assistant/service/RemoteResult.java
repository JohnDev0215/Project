package cerence.ark.assistant.service;

import android.os.Parcel;
import android.os.Parcelable;
import cerence.ark.assistant.framework.data.ErrorCode;

/* JADX INFO: loaded from: classes2.dex */
public class RemoteResult implements Parcelable {
    public static final Parcelable.Creator<RemoteResult> CREATOR = new Parcelable.Creator<RemoteResult>() { // from class: cerence.ark.assistant.service.RemoteResult.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RemoteResult createFromParcel(Parcel parcel) {
            return new RemoteResult(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RemoteResult[] newArray(int i) {
            return new RemoteResult[i];
        }
    };
    private final String mData;
    private final ErrorCode mErrorCode;
    private final String mErrorMessage;
    private final boolean mIsSuccess;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private RemoteResult(Builder builder) {
        this.mIsSuccess = builder.mIsSuccess;
        this.mData = builder.mData;
        this.mErrorCode = builder.mErrorCode;
        this.mErrorMessage = builder.mErrorMessage;
    }

    protected RemoteResult(Parcel parcel) {
        this.mIsSuccess = parcel.readByte() != 0;
        this.mData = parcel.readString();
        this.mErrorCode = (ErrorCode) parcel.readParcelable(ErrorCode.class.getClassLoader());
        this.mErrorMessage = parcel.readString();
    }

    public static Builder builder() {
        return new Builder();
    }

    public boolean isSuccess() {
        return this.mIsSuccess;
    }

    public String getData() {
        return this.mData;
    }

    public ErrorCode getErrorCode() {
        return this.mErrorCode;
    }

    public String getErrorMessage() {
        return this.mErrorMessage;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte(this.mIsSuccess ? (byte) 1 : (byte) 0);
        parcel.writeString(this.mData);
        parcel.writeParcelable(this.mErrorCode, i);
        parcel.writeString(this.mErrorMessage);
    }

    public static final class Builder {
        private String mData;
        private ErrorCode mErrorCode;
        private String mErrorMessage;
        private boolean mIsSuccess;

        private Builder() {
        }

        public Builder setIsSuccess(boolean z) {
            this.mIsSuccess = z;
            return this;
        }

        public Builder setData(String str) {
            this.mData = str;
            return this;
        }

        public Builder setErrorCode(ErrorCode errorCode) {
            this.mErrorCode = errorCode;
            return this;
        }

        public Builder setErrorMessage(String str) {
            this.mErrorMessage = str;
            return this;
        }

        public RemoteResult buildSuccess() {
            this.mIsSuccess = true;
            return new RemoteResult(this);
        }

        public RemoteResult buildSuccess(String str) {
            this.mIsSuccess = true;
            this.mData = str;
            return new RemoteResult(this);
        }

        public RemoteResult buildError() {
            this.mIsSuccess = false;
            return new RemoteResult(this);
        }
    }
}
