package cerence.ark.assistant.core;

import android.text.TextUtils;
import cerence.ark.assistant.api.BaseResult;
import cerence.ark.assistant.api.IResponse;
import cerence.ark.assistant.api.controller.IHostController;
import cerence.ark.assistant.common.Logger;
import cerence.ark.assistant.core.MessageHandler;
import cerence.ark.assistant.library.JsonKey;
import cerence.ark.assistant.library.Utils;
import cerence.ark.assistant.library.ipctransaction.IAssemblerListener;
import cerence.ark.assistant.service.IAssistant;
import cerence.ark.assistant.service.IUnhandledRequestListener;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class UnhandledRequestListenerImpl extends IUnhandledRequestListener.Stub {
    private static final String TAG = "UnhandledRequestListenerImpl";
    private final IAssistant mAssistant;
    private final IHostController mHostController;

    public UnhandledRequestListenerImpl(IHostController iHostController, IAssistant iAssistant) {
        this.mHostController = iHostController;
        this.mAssistant = iAssistant;
    }

    @Override // cerence.ark.assistant.service.IUnhandledRequestListener
    public void onUnhandledRequest(String str, String str2, int i, int i2) {
        Utils.processStringAssembler(str, str2, i, i2, new IAssemblerListener() { // from class: cerence.ark.assistant.core.UnhandledRequestListenerImpl$$ExternalSyntheticLambda0
            @Override // cerence.ark.assistant.library.ipctransaction.IAssemblerListener
            public final void onFinal(Object obj) {
                this.f$0.unhandledRequest((String) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void unhandledRequest(String str) {
        if (TextUtils.isEmpty(str)) {
            Logger.error(TAG, "unhandledRequest: jsonData is null");
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            String strOptString = jSONObject.optString(JsonKey.DOMAIN_NAME);
            String strOptString2 = jSONObject.optString("name");
            long jOptLong = jSONObject.optLong("id", -1L);
            if (!Utils.isSameProcess()) {
                String str2 = TAG;
                Logger.debug(str2, Utils.format("unhandledRequest: %s", str));
                Logger.info(str2, Utils.format("unhandledRequest: %s", Utils.buildMessageLog(jOptLong, strOptString, strOptString2)));
            }
            if (this.mHostController == null) {
                Logger.error(TAG, "unhandledRequest: host controller is null");
                return;
            }
            IResponse<BaseResult> iResponseOnUnhandledRequest = this.mHostController.onUnhandledRequest(MessageHandler.createRequest(str, jSONObject, jOptLong, strOptString, strOptString2));
            if (iResponseOnUnhandledRequest == null) {
                Logger.error(TAG, "unhandledRequest: response is null");
            } else {
                new MessageHandler.ResponseSender(jOptLong, strOptString, strOptString2, this.mAssistant).send(iResponseOnUnhandledRequest);
            }
        } catch (Exception e) {
            Logger.error(TAG, "unhandledRequest: exception", e);
        }
    }
}
