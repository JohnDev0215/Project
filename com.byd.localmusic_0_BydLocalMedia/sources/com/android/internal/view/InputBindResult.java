package com.android.internal.view;

import android.graphics.Matrix;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.InputChannel;
import com.android.internal.view.IInputMethodSession;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes4.dex */
public final class InputBindResult implements Parcelable {
    public final InputChannel channel;
    public final String id;
    private final float[] mActivityViewToScreenMatrixValues;
    public final IInputMethodSession method;
    public final int result;
    public final int sequence;
    public static final Parcelable.Creator<InputBindResult> CREATOR = new Parcelable.Creator<InputBindResult>() { // from class: com.android.internal.view.InputBindResult.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public InputBindResult createFromParcel(Parcel parcel) {
            return new InputBindResult(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public InputBindResult[] newArray(int i) {
            return new InputBindResult[i];
        }
    };
    public static final InputBindResult NULL = error(5);
    public static final InputBindResult NO_IME = error(6);
    public static final InputBindResult NO_EDITOR = error(13);
    public static final InputBindResult INVALID_PACKAGE_NAME = error(7);
    public static final InputBindResult NULL_EDITOR_INFO = error(11);
    public static final InputBindResult NOT_IME_TARGET_WINDOW = error(12);
    public static final InputBindResult IME_NOT_CONNECTED = error(9);
    public static final InputBindResult INVALID_USER = error(10);
    public static final InputBindResult DISPLAY_ID_MISMATCH = error(14);
    public static final InputBindResult INVALID_DISPLAY_ID = error(15);
    public static final InputBindResult INVALID_CLIENT = error(16);
    public static final InputBindResult USER_SWITCHING = error(3);

    @Retention(RetentionPolicy.SOURCE)
    public @interface ResultCode {
        public static final int ERROR_DISPLAY_ID_MISMATCH = 14;
        public static final int ERROR_IME_NOT_CONNECTED = 9;
        public static final int ERROR_INVALID_CLIENT = 16;
        public static final int ERROR_INVALID_DISPLAY_ID = 15;
        public static final int ERROR_INVALID_PACKAGE_NAME = 7;
        public static final int ERROR_INVALID_USER = 10;
        public static final int ERROR_NOT_IME_TARGET_WINDOW = 12;
        public static final int ERROR_NO_EDITOR = 13;
        public static final int ERROR_NO_IME = 6;
        public static final int ERROR_NULL = 5;
        public static final int ERROR_NULL_EDITOR_INFO = 11;
        public static final int ERROR_SYSTEM_NOT_READY = 8;
        public static final int SUCCESS_REPORT_WINDOW_FOCUS_ONLY = 4;
        public static final int SUCCESS_WAITING_IME_BINDING = 2;
        public static final int SUCCESS_WAITING_IME_SESSION = 1;
        public static final int SUCCESS_WAITING_USER_SWITCHING = 3;
        public static final int SUCCESS_WITH_IME_SESSION = 0;
    }

    public Matrix getActivityViewToScreenMatrix() {
        if (this.mActivityViewToScreenMatrixValues == null) {
            return null;
        }
        Matrix matrix = new Matrix();
        matrix.setValues(this.mActivityViewToScreenMatrixValues);
        return matrix;
    }

    public InputBindResult(int i, IInputMethodSession iInputMethodSession, InputChannel inputChannel, String str, int i2, Matrix matrix) {
        this.result = i;
        this.method = iInputMethodSession;
        this.channel = inputChannel;
        this.id = str;
        this.sequence = i2;
        if (matrix == null) {
            this.mActivityViewToScreenMatrixValues = null;
            return;
        }
        float[] fArr = new float[9];
        this.mActivityViewToScreenMatrixValues = fArr;
        matrix.getValues(fArr);
    }

    InputBindResult(Parcel parcel) {
        this.result = parcel.readInt();
        this.method = IInputMethodSession.Stub.asInterface(parcel.readStrongBinder());
        if (parcel.readInt() != 0) {
            this.channel = InputChannel.CREATOR.createFromParcel(parcel);
        } else {
            this.channel = null;
        }
        this.id = parcel.readString();
        this.sequence = parcel.readInt();
        this.mActivityViewToScreenMatrixValues = parcel.createFloatArray();
    }

    public String toString() {
        return "InputBindResult{result=" + getResultString() + " method=" + this.method + " id=" + this.id + " sequence=" + this.sequence + " activityViewToScreenMatrix=" + getActivityViewToScreenMatrix() + "}";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.result);
        parcel.writeStrongInterface(this.method);
        if (this.channel != null) {
            parcel.writeInt(1);
            this.channel.writeToParcel(parcel, i);
        } else {
            parcel.writeInt(0);
        }
        parcel.writeString(this.id);
        parcel.writeInt(this.sequence);
        parcel.writeFloatArray(this.mActivityViewToScreenMatrixValues);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        InputChannel inputChannel = this.channel;
        if (inputChannel != null) {
            return inputChannel.describeContents();
        }
        return 0;
    }

    public String getResultString() {
        switch (this.result) {
            case 0:
                return "SUCCESS_WITH_IME_SESSION";
            case 1:
                return "SUCCESS_WAITING_IME_SESSION";
            case 2:
                return "SUCCESS_WAITING_IME_BINDING";
            case 3:
                return "SUCCESS_WAITING_USER_SWITCHING";
            case 4:
                return "SUCCESS_REPORT_WINDOW_FOCUS_ONLY";
            case 5:
                return "ERROR_NULL";
            case 6:
                return "ERROR_NO_IME";
            case 7:
                return "ERROR_INVALID_PACKAGE_NAME";
            case 8:
                return "ERROR_SYSTEM_NOT_READY";
            case 9:
                return "ERROR_IME_NOT_CONNECTED";
            case 10:
                return "ERROR_INVALID_USER";
            case 11:
                return "ERROR_NULL_EDITOR_INFO";
            case 12:
                return "ERROR_NOT_IME_TARGET_WINDOW";
            case 13:
                return "ERROR_NO_EDITOR";
            case 14:
                return "ERROR_DISPLAY_ID_MISMATCH";
            case 15:
                return "ERROR_INVALID_DISPLAY_ID";
            case 16:
                return "ERROR_INVALID_CLIENT";
            default:
                return "Unknown(" + this.result + ")";
        }
    }

    private static InputBindResult error(int i) {
        return new InputBindResult(i, null, null, null, -1, null);
    }
}
