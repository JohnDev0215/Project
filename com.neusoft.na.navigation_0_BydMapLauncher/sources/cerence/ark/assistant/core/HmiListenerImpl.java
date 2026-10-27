package cerence.ark.assistant.core;

import android.os.Handler;
import android.text.TextUtils;
import cerence.ark.assistant.api.IRequest;
import cerence.ark.assistant.api.hmi.HmiListener;
import cerence.ark.assistant.common.Logger;
import cerence.ark.assistant.core.MessageHandler;
import cerence.ark.assistant.library.JsonKey;
import cerence.ark.assistant.library.Utils;
import cerence.ark.assistant.library.ipctransaction.IAssemblerListener;
import cerence.ark.assistant.service.IAssistant;
import cerence.ark.assistant.service.IHmiListener;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class HmiListenerImpl extends IHmiListener.Stub {
    private static final String TAG = "HmiListenerImpl";
    private final IAssistant mAssistant;
    private final Handler mHandler;
    private final HmiListener mHmiListener;

    public HmiListenerImpl(HmiListener hmiListener, IAssistant iAssistant, Handler handler) {
        this.mHmiListener = hmiListener;
        this.mAssistant = iAssistant;
        this.mHandler = handler;
    }

    @Override // cerence.ark.assistant.service.IHmiListener
    public void onRequest(String str, String str2, int i, int i2) {
        Utils.processStringAssembler(str, str2, i, i2, new IAssemblerListener() { // from class: cerence.ark.assistant.core.HmiListenerImpl$$ExternalSyntheticLambda0
            @Override // cerence.ark.assistant.library.ipctransaction.IAssemblerListener
            public final void onFinal(Object obj) {
                this.f$0.handleHmiRequest((String) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleHmiRequest(String str) {
        if (TextUtils.isEmpty(str)) {
            Logger.error(TAG, "handleHmiRequest: jsonData is null");
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            final String strOptString = jSONObject.optString(JsonKey.DOMAIN_NAME);
            final String strOptString2 = jSONObject.optString("name");
            final long jOptLong = jSONObject.optLong("id", -1L);
            if (!Utils.isSameProcess()) {
                String str2 = TAG;
                Logger.debug(str2, Utils.format("handleHmiRequest: %s", str));
                Logger.info(str2, Utils.format("handleHmiRequest: %s", Utils.buildMessageLog(jOptLong, strOptString, strOptString2)));
            }
            final MessageHandler.Request requestCreateRequest = MessageHandler.createRequest(str, jSONObject, jOptLong, strOptString, strOptString2);
            Handler handler = this.mHandler;
            if (handler == null) {
                m58x2e159c6(strOptString, strOptString2, jOptLong, requestCreateRequest);
            } else {
                handler.post(new Runnable() { // from class: cerence.ark.assistant.core.HmiListenerImpl$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.m58x2e159c6(strOptString, strOptString2, jOptLong, requestCreateRequest);
                    }
                });
            }
        } catch (Exception e) {
            Logger.error(TAG, "handleHmiRequest: exception", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: sendHmiRequest, reason: merged with bridge method [inline-methods] */
    public void m58x2e159c6(String str, String str2, long j, IRequest iRequest) {
        if (this.mHmiListener == null) {
            Logger.warn(TAG, "handleHmiRequest: HmiListener is null");
        } else {
            this.mHmiListener.onRequest(iRequest, new MessageHandler.ResponseSender(j, str, str2, this.mAssistant));
        }
    }
}
