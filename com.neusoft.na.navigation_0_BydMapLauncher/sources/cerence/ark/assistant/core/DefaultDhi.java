package cerence.ark.assistant.core;

import android.content.Context;
import cerence.ark.assistant.api.BaseResult;
import cerence.ark.assistant.api.Dhi;
import cerence.ark.assistant.api.IRequest;
import cerence.ark.assistant.api.IResponseSender;
import cerence.ark.assistant.api.hmi.HmiListener;
import cerence.ark.assistant.common.Logger;

/* JADX INFO: loaded from: classes2.dex */
public final class DefaultDhi implements Dhi {
    private static final String TAG = "DefaultDhi";
    private final Context mContext;
    private final HmiListener mHmiListener;

    public DefaultDhi(HmiListener hmiListener, Context context) {
        this.mHmiListener = hmiListener;
        this.mContext = context;
    }

    @Override // cerence.ark.assistant.api.Dhi
    public void sendHmiRequest(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
        HmiListener hmiListener = this.mHmiListener;
        if (hmiListener == null) {
            Logger.error(TAG, "sendHmiRequest: hmi is null");
        } else {
            hmiListener.onRequest(iRequest, iResponseSender);
        }
    }

    @Override // cerence.ark.assistant.api.Dhi
    public Context getContext() {
        return this.mContext;
    }
}
