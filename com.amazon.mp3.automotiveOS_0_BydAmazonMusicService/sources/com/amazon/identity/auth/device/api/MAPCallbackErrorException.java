package com.amazon.identity.auth.device.api;

import android.os.Bundle;
import android.os.Parcel;
import android.text.TextUtils;
import com.amazon.fireos.sdk.annotations.FireOsSdk;
import com.amazon.identity.auth.device.api.MAPError;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class MAPCallbackErrorException extends Exception {
    private static final long serialVersionUID = 1;
    private final MAPError mError;
    private final byte[] mErrorBundleBytes;
    private final String mErrorMessage;

    @FireOsSdk
    public MAPCallbackErrorException(Bundle bundle) {
        this(bundle, null, null);
    }

    public static String a(Bundle bundle) {
        String string;
        if (bundle == null || (string = bundle.getString("com.amazon.dcp.sso.ErrorMessage")) == null) {
            return null;
        }
        if (!bundle.containsKey("com.amazon.dcp.sso.ErrorCode")) {
            return string;
        }
        return string + " (" + bundle.getInt("com.amazon.dcp.sso.ErrorCode") + ")";
    }

    public MAPError getError() {
        return this.mError;
    }

    @FireOsSdk
    public Bundle getErrorBundle() {
        Parcel parcelObtain = Parcel.obtain();
        try {
            byte[] bArr = this.mErrorBundleBytes;
            parcelObtain.unmarshall(bArr, 0, bArr.length);
            parcelObtain.setDataPosition(0);
            return parcelObtain.readBundle();
        } finally {
            parcelObtain.recycle();
        }
    }

    public String getErrorMessage() {
        return this.mErrorMessage;
    }

    public MAPCallbackErrorException(Bundle bundle, MAPError mAPError, String str) {
        super(a(bundle));
        bundle = bundle == null ? new Bundle() : bundle;
        if (mAPError != null) {
            this.mError = mAPError;
        } else {
            this.mError = MAPError.getErrorFromValue(bundle.getInt(MAPError.KEY_ERROR_CODE, MAPError.CommonError.INTERNAL_ERROR.getErrorCode()));
        }
        if (TextUtils.isEmpty(str)) {
            this.mErrorMessage = bundle.getString(MAPError.KEY_ERROR_MESSAGE, "An internal error occurs!");
        } else {
            this.mErrorMessage = str;
        }
        Parcel parcelObtain = Parcel.obtain();
        try {
            parcelObtain.writeBundle(bundle);
            this.mErrorBundleBytes = parcelObtain.marshall();
        } finally {
            parcelObtain.recycle();
        }
    }
}
