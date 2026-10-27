package android.hardware.camera2.params;

import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.utils.HashCodeHelpers;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes2.dex */
public final class SessionConfiguration implements Parcelable {
    public static final Parcelable.Creator<SessionConfiguration> CREATOR = new Parcelable.Creator<SessionConfiguration>() { // from class: android.hardware.camera2.params.SessionConfiguration.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SessionConfiguration createFromParcel(Parcel parcel) {
            try {
                return new SessionConfiguration(parcel);
            } catch (Exception e) {
                Log.e(SessionConfiguration.TAG, "Exception creating SessionConfiguration from parcel", e);
                return null;
            }
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SessionConfiguration[] newArray(int i) {
            return new SessionConfiguration[i];
        }
    };
    public static final int SESSION_HIGH_SPEED = 1;
    public static final int SESSION_REGULAR = 0;
    public static final int SESSION_VENDOR_START = 32768;
    private static final String TAG = "SessionConfiguration";
    private Executor mExecutor;
    private InputConfiguration mInputConfig;
    private List<OutputConfiguration> mOutputConfigurations;
    private CaptureRequest mSessionParameters;
    private int mSessionType;
    private CameraCaptureSession.StateCallback mStateCallback;

    @Retention(RetentionPolicy.SOURCE)
    public @interface SessionMode {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public SessionConfiguration(int i, List<OutputConfiguration> list, Executor executor, CameraCaptureSession.StateCallback stateCallback) {
        this.mExecutor = null;
        this.mInputConfig = null;
        this.mSessionParameters = null;
        this.mSessionType = i;
        this.mOutputConfigurations = Collections.unmodifiableList(new ArrayList(list));
        this.mStateCallback = stateCallback;
        this.mExecutor = executor;
    }

    private SessionConfiguration(Parcel parcel) {
        this.mExecutor = null;
        this.mInputConfig = null;
        this.mSessionParameters = null;
        int i = parcel.readInt();
        int i2 = parcel.readInt();
        int i3 = parcel.readInt();
        int i4 = parcel.readInt();
        ArrayList arrayList = new ArrayList();
        parcel.readTypedList(arrayList, OutputConfiguration.CREATOR);
        if (i2 > 0 && i3 > 0 && i4 != -1) {
            this.mInputConfig = new InputConfiguration(i2, i3, i4);
        }
        this.mSessionType = i;
        this.mOutputConfigurations = arrayList;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel == null) {
            throw new IllegalArgumentException("dest must not be null");
        }
        parcel.writeInt(this.mSessionType);
        InputConfiguration inputConfiguration = this.mInputConfig;
        if (inputConfiguration != null) {
            parcel.writeInt(inputConfiguration.getWidth());
            parcel.writeInt(this.mInputConfig.getHeight());
            parcel.writeInt(this.mInputConfig.getFormat());
        } else {
            parcel.writeInt(0);
            parcel.writeInt(0);
            parcel.writeInt(-1);
        }
        parcel.writeTypedList(this.mOutputConfigurations);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (obj instanceof SessionConfiguration) {
            SessionConfiguration sessionConfiguration = (SessionConfiguration) obj;
            if (this.mInputConfig == sessionConfiguration.mInputConfig && this.mSessionType == sessionConfiguration.mSessionType && this.mOutputConfigurations.size() == sessionConfiguration.mOutputConfigurations.size()) {
                for (int i = 0; i < this.mOutputConfigurations.size(); i++) {
                    if (!this.mOutputConfigurations.get(i).equals(sessionConfiguration.mOutputConfigurations.get(i))) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return HashCodeHelpers.hashCode(this.mOutputConfigurations.hashCode(), this.mInputConfig.hashCode(), this.mSessionType);
    }

    public int getSessionType() {
        return this.mSessionType;
    }

    public List<OutputConfiguration> getOutputConfigurations() {
        return this.mOutputConfigurations;
    }

    public CameraCaptureSession.StateCallback getStateCallback() {
        return this.mStateCallback;
    }

    public Executor getExecutor() {
        return this.mExecutor;
    }

    public void setInputConfiguration(InputConfiguration inputConfiguration) {
        if (this.mSessionType != 1) {
            this.mInputConfig = inputConfiguration;
            return;
        }
        throw new UnsupportedOperationException("Method not supported for high speed session types");
    }

    public InputConfiguration getInputConfiguration() {
        return this.mInputConfig;
    }

    public void setSessionParameters(CaptureRequest captureRequest) {
        this.mSessionParameters = captureRequest;
    }

    public CaptureRequest getSessionParameters() {
        return this.mSessionParameters;
    }
}
