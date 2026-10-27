package cerence.ark.assistant.core;

import android.text.TextUtils;
import cerence.ark.assistant.api.IRequest;
import cerence.ark.assistant.api.hmi.HmiListener;
import cerence.ark.assistant.common.Logger;
import cerence.ark.assistant.common.Scheduler;
import cerence.ark.assistant.core.MessageHandler;
import cerence.ark.assistant.library.JsonKey;
import cerence.ark.assistant.library.Utils;
import cerence.ark.assistant.library.ipctransaction.ipcstring.StringAssembler;
import cerence.ark.assistant.service.IAssistant;
import cerence.ark.assistant.service.IHmiListener;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class HmiListenerImpl extends IHmiListener.Stub {
    private static final String TAG = "HmiListenerImpl";
    private final IAssistant mAssistant;
    private final HmiListener mHmiListener;
    private StringAssembler mHmiRequestAssembler;

    public HmiListenerImpl(HmiListener hmiListener, IAssistant iAssistant) {
        this.mHmiListener = hmiListener;
        this.mAssistant = iAssistant;
    }

    @Override // cerence.ark.assistant.service.IHmiListener
    public void onRequest(String str, boolean z, boolean z2) {
        if (z) {
            this.mHmiRequestAssembler = new StringAssembler();
        }
        if (z2) {
            String strAssemble = this.mHmiRequestAssembler.assemble(str);
            this.mHmiRequestAssembler = null;
            handleHmiRequest(strAssemble);
            return;
        }
        this.mHmiRequestAssembler.assemble(str);
    }

    private void handleHmiRequest(String str) {
        if (TextUtils.isEmpty(str)) {
            Logger.error(TAG, "handleHmiRequest: jsonData is null");
            return;
        }
        if (!Utils.isSameProcess()) {
            Logger.debug(TAG, String.format("handleHmiRequest: %s", str));
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            final String strOptString = jSONObject.optString(JsonKey.DOMAIN_NAME);
            final String strOptString2 = jSONObject.optString("name");
            final int iOptInt = jSONObject.optInt("id", -1);
            final Request requestCreateRequest = MessageHandler.createRequest(str, jSONObject, strOptString, strOptString2);
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.core.HmiListenerImpl$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m40x2e159c6(iOptInt, strOptString, strOptString2, requestCreateRequest);
                }
            });
        } catch (Exception e) {
            Logger.error(TAG, "handleHmiRequest: exception", e);
        }
    }

    /* JADX INFO: renamed from: lambda$handleHmiRequest$0$cerence-ark-assistant-core-HmiListenerImpl, reason: not valid java name */
    /* synthetic */ void m40x2e159c6(int i, String str, String str2, IRequest iRequest) {
        if (this.mHmiListener == null) {
            Logger.warn(TAG, "handleHmiRequest: HmiListener is null, " + Utils.buildMessageLog(i, str, str2));
        } else {
            this.mHmiListener.onRequest(iRequest, new MessageHandler.ResponseSender(i, str, str2, this.mAssistant));
        }
    }
}
