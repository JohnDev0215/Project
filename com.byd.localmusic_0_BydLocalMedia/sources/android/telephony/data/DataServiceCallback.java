package android.telephony.data;

import android.annotation.SystemApi;
import android.os.RemoteException;
import com.android.telephony.Rlog;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
@SystemApi
public class DataServiceCallback {
    private static final boolean DBG = true;
    public static final int RESULT_ERROR_BUSY = 3;
    public static final int RESULT_ERROR_ILLEGAL_STATE = 4;
    public static final int RESULT_ERROR_INVALID_ARG = 2;
    public static final int RESULT_ERROR_UNSUPPORTED = 1;
    public static final int RESULT_SUCCESS = 0;
    private static final String TAG = DataServiceCallback.class.getSimpleName();
    private final IDataServiceCallback mCallback;

    @Retention(RetentionPolicy.SOURCE)
    public @interface ResultCode {
    }

    public DataServiceCallback(IDataServiceCallback iDataServiceCallback) {
        this.mCallback = iDataServiceCallback;
    }

    public void onSetupDataCallComplete(int i, DataCallResponse dataCallResponse) {
        if (this.mCallback != null) {
            try {
                Rlog.d(TAG, "onSetupDataCallComplete");
                this.mCallback.onSetupDataCallComplete(i, dataCallResponse);
                return;
            } catch (RemoteException unused) {
                Rlog.e(TAG, "Failed to onSetupDataCallComplete on the remote");
                return;
            }
        }
        Rlog.e(TAG, "onSetupDataCallComplete: callback is null!");
    }

    public void onDeactivateDataCallComplete(int i) {
        if (this.mCallback != null) {
            try {
                Rlog.d(TAG, "onDeactivateDataCallComplete");
                this.mCallback.onDeactivateDataCallComplete(i);
                return;
            } catch (RemoteException unused) {
                Rlog.e(TAG, "Failed to onDeactivateDataCallComplete on the remote");
                return;
            }
        }
        Rlog.e(TAG, "onDeactivateDataCallComplete: callback is null!");
    }

    public void onSetInitialAttachApnComplete(int i) {
        IDataServiceCallback iDataServiceCallback = this.mCallback;
        if (iDataServiceCallback != null) {
            try {
                iDataServiceCallback.onSetInitialAttachApnComplete(i);
                return;
            } catch (RemoteException unused) {
                Rlog.e(TAG, "Failed to onSetInitialAttachApnComplete on the remote");
                return;
            }
        }
        Rlog.e(TAG, "onSetInitialAttachApnComplete: callback is null!");
    }

    public void onSetDataProfileComplete(int i) {
        IDataServiceCallback iDataServiceCallback = this.mCallback;
        if (iDataServiceCallback != null) {
            try {
                iDataServiceCallback.onSetDataProfileComplete(i);
                return;
            } catch (RemoteException unused) {
                Rlog.e(TAG, "Failed to onSetDataProfileComplete on the remote");
                return;
            }
        }
        Rlog.e(TAG, "onSetDataProfileComplete: callback is null!");
    }

    public void onRequestDataCallListComplete(int i, List<DataCallResponse> list) {
        IDataServiceCallback iDataServiceCallback = this.mCallback;
        if (iDataServiceCallback != null) {
            try {
                iDataServiceCallback.onRequestDataCallListComplete(i, list);
                return;
            } catch (RemoteException unused) {
                Rlog.e(TAG, "Failed to onRequestDataCallListComplete on the remote");
                return;
            }
        }
        Rlog.e(TAG, "onRequestDataCallListComplete: callback is null!");
    }

    public void onDataCallListChanged(List<DataCallResponse> list) {
        if (this.mCallback != null) {
            try {
                Rlog.d(TAG, "onDataCallListChanged");
                this.mCallback.onDataCallListChanged(list);
                return;
            } catch (RemoteException unused) {
                Rlog.e(TAG, "Failed to onDataCallListChanged on the remote");
                return;
            }
        }
        Rlog.e(TAG, "onDataCallListChanged: callback is null!");
    }
}
