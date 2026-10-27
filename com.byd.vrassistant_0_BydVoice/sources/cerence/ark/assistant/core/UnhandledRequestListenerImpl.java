package cerence.ark.assistant.core;

import android.text.TextUtils;
import cerence.ark.assistant.api.BaseResult;
import cerence.ark.assistant.api.IResponse;
import cerence.ark.assistant.api.controller.IHostController;
import cerence.ark.assistant.common.Logger;
import cerence.ark.assistant.core.MessageHandler;
import cerence.ark.assistant.library.JsonKey;
import cerence.ark.assistant.library.Utils;
import cerence.ark.assistant.library.ipctransaction.ipcstring.StringAssembler;
import cerence.ark.assistant.service.IAssistant;
import cerence.ark.assistant.service.IUnhandledRequestListener;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class UnhandledRequestListenerImpl extends IUnhandledRequestListener.Stub {
    private static final String TAG = "UnhandledRequestListenerImpl";
    private final IAssistant mAssistant;
    private final IHostController mHostController;
    private StringAssembler mRequestAssembler;

    public UnhandledRequestListenerImpl(IHostController iHostController, IAssistant iAssistant) {
        this.mHostController = iHostController;
        this.mAssistant = iAssistant;
    }

    @Override // cerence.ark.assistant.service.IUnhandledRequestListener
    public void onUnhandledRequest(String str, boolean z, boolean z2) {
        if (z) {
            this.mRequestAssembler = new StringAssembler();
        }
        if (z2) {
            String strAssemble = this.mRequestAssembler.assemble(str);
            this.mRequestAssembler = null;
            unhandledRequest(strAssemble);
            return;
        }
        this.mRequestAssembler.assemble(str);
    }

    private void unhandledRequest(String str) {
        if (TextUtils.isEmpty(str)) {
            Logger.error(TAG, "unhandledRequest: jsonData is null");
            return;
        }
        String str2 = TAG;
        Logger.debug(str2, String.format("unhandledRequest: %s", str));
        try {
            JSONObject jSONObject = new JSONObject(str);
            String strOptString = jSONObject.optString(JsonKey.DOMAIN_NAME);
            String strOptString2 = jSONObject.optString("name");
            int iOptInt = jSONObject.optInt("id", -1);
            if (this.mHostController == null) {
                Logger.error(str2, "unhandledRequest: host controller is null, " + Utils.buildMessageLog(iOptInt, strOptString, strOptString2));
                return;
            }
            IResponse<BaseResult> iResponseOnUnhandledRequest = this.mHostController.onUnhandledRequest(MessageHandler.createRequest(str, jSONObject, strOptString, strOptString2));
            if (iResponseOnUnhandledRequest == null) {
                Logger.error(str2, "unhandledRequest: response is null, " + Utils.buildMessageLog(iOptInt, strOptString, strOptString2));
            } else {
                new MessageHandler.ResponseSender(iOptInt, strOptString, strOptString2, this.mAssistant).send(iResponseOnUnhandledRequest);
            }
        } catch (Exception e) {
            Logger.error(TAG, "unhandledRequest: exception", e);
        }
    }
}
