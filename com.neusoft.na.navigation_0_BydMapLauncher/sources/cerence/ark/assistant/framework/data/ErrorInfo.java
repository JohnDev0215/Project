package cerence.ark.assistant.framework.data;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public class ErrorInfo implements Parcelable {
    public static final Parcelable.Creator<ErrorInfo> CREATOR = new Parcelable.Creator<ErrorInfo>() { // from class: cerence.ark.assistant.framework.data.ErrorInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ErrorInfo createFromParcel(Parcel parcel) {
            return new ErrorInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ErrorInfo[] newArray(int i) {
            return new ErrorInfo[i];
        }
    };
    private String errorMessage;
    private ErrorCode mErrorCode;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ErrorInfo() {
    }

    public ErrorCode getErrorCode() {
        return this.mErrorCode;
    }

    public ErrorInfo setErrorCode(ErrorCode errorCode) {
        this.mErrorCode = errorCode;
        return this;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public ErrorInfo setErrorMessage(String str) {
        this.errorMessage = str;
        return this;
    }

    protected ErrorInfo(Parcel parcel) {
        this.mErrorCode = (ErrorCode) parcel.readParcelable(ErrorCode.class.getClassLoader());
        this.errorMessage = parcel.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.mErrorCode, i);
        parcel.writeString(this.errorMessage);
    }

    public enum ErrorCode implements Parcelable {
        FATAL_INITIALIZE_AUDIO,
        FATAL_RECOGNITION_START_FAILED,
        FATAL_AUDIO_OUTPUT_TIMEOUT,
        FATAL_AUDIO_INPUT_TIMEOUT,
        FATAL_RECOGNITION_TIMEOUT,
        FATAL_ASSISTANT_STOPPED,
        FATAL_UNKNOWN;

        public static final Parcelable.Creator<ErrorCode> CREATOR = new Parcelable.Creator<ErrorCode>() { // from class: cerence.ark.assistant.framework.data.ErrorInfo.ErrorCode.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public ErrorCode createFromParcel(Parcel parcel) {
                return ErrorCode.valueOf(parcel.readString());
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public ErrorCode[] newArray(int i) {
                return new ErrorCode[i];
            }
        };

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(name());
        }
    }

    public String toString() {
        return "ErrorInfo{mErrorCode=" + this.mErrorCode + ", errorMessage='" + this.errorMessage + "'}";
    }
}
