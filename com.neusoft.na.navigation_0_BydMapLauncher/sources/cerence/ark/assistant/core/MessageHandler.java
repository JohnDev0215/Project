package cerence.ark.assistant.core;

import android.text.TextUtils;
import cerence.ark.assistant.api.BaseResult;
import cerence.ark.assistant.api.IRequest;
import cerence.ark.assistant.api.IResponse;
import cerence.ark.assistant.api.IResponseSender;
import cerence.ark.assistant.api.controller.Domain;
import cerence.ark.assistant.api.controller.RequestHandler;
import cerence.ark.assistant.common.Logger;
import cerence.ark.assistant.framework.data.ErrorCode;
import cerence.ark.assistant.library.JsonKey;
import cerence.ark.assistant.library.Utils;
import cerence.ark.assistant.library.ipctransaction.ISplitter;
import cerence.ark.assistant.library.ipctransaction.ipcstring.StringSplitter;
import cerence.ark.assistant.service.IAssistant;
import cerence.ark.assistant.service.ICallback;
import java.util.Collection;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class MessageHandler {
    private static final String DOMAIN_REQUEST_WILDCARD_SYMBOL = "*";
    private static final String TAG = "MessageHandler";
    private String mActiveZone;
    private IAssistant mAssistant;
    private Map<String, Domain> mDomainMap;
    private Map<String, Map<String, RequestHandler<? extends BaseResult>>> mDomainRequestHandlerMap;

    public MessageHandler(Map<String, Domain> map, Map<String, Map<String, RequestHandler<? extends BaseResult>>> map2) {
        this.mDomainMap = map;
        this.mDomainRequestHandlerMap = map2;
    }

    public void setAssistant(IAssistant iAssistant) {
        this.mAssistant = iAssistant;
    }

    public void destroy() {
        this.mAssistant = null;
        Map<String, Map<String, RequestHandler<? extends BaseResult>>> map = this.mDomainRequestHandlerMap;
        if (map != null) {
            map.clear();
            this.mDomainRequestHandlerMap = null;
        }
        this.mDomainMap = null;
    }

    public void handleRequest(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            Logger.error(TAG, "handleRequest: jsonData is null");
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str2);
            String strOptString = jSONObject.optString(JsonKey.DOMAIN_NAME);
            String strOptString2 = jSONObject.optString("name");
            long jOptLong = jSONObject.optLong("id", -1L);
            if (!Utils.isSameProcess()) {
                String str3 = TAG;
                Logger.debug(str3, Utils.format("handleRequest: %s", str2));
                Logger.info(str3, Utils.format("handleRequest: %s", Utils.buildMessageLog(jOptLong, strOptString, strOptString2)));
            }
            if (TextUtils.isEmpty(strOptString)) {
                Logger.error(TAG, "handleRequest: domain name is null");
                return;
            }
            if (TextUtils.isEmpty(strOptString2)) {
                Logger.error(TAG, "handleRequest: requestName is null");
                return;
            }
            Map<String, Map<String, RequestHandler<? extends BaseResult>>> map = this.mDomainRequestHandlerMap;
            if (map != null && !map.isEmpty()) {
                Map<String, RequestHandler<? extends BaseResult>> requestHandlerMap = getRequestHandlerMap(map, strOptString);
                if (requestHandlerMap == null) {
                    Logger.error(TAG, "handleRequest: domain is not registered");
                    return;
                }
                RequestHandler<? extends BaseResult> requestHandler = getRequestHandler(strOptString2, requestHandlerMap);
                if (requestHandler == null) {
                    Logger.error(TAG, "handleRequest: request handler is not registered");
                    return;
                }
                Request requestCreateRequest = createRequest(str2, jSONObject, jOptLong, strOptString, strOptString2);
                ResponseSender responseSender = new ResponseSender(jOptLong, strOptString, strOptString2, this.mAssistant);
                notifyActiveZoneChangedIfNeeded(str);
                requestHandler.onHandle(requestCreateRequest, responseSender);
                return;
            }
            Logger.error(TAG, "handleRequest: domain map is empty");
        } catch (JSONException e) {
            Logger.error(TAG, "handleRequest: parse json exception", e);
        }
    }

    private void notifyActiveZoneChangedIfNeeded(String str) {
        if (TextUtils.isEmpty(str) || str.equals(this.mActiveZone)) {
            return;
        }
        this.mActiveZone = str;
        Map<String, Domain> map = this.mDomainMap;
        if (map == null) {
            Logger.error(TAG, "activeZoneChanged: domain map is null");
            return;
        }
        Collection<Domain> collectionValues = map.values();
        if (collectionValues.isEmpty()) {
            Logger.error(TAG, "activeZoneChanged: domains is empty");
            return;
        }
        for (Domain domain : collectionValues) {
            if (domain == null) {
                Logger.error(TAG, "activeZoneChanged: domain is null");
            } else {
                domain.onActiveZoneChanged(str);
            }
        }
    }

    public boolean handleAbortRequest(String str) {
        JSONObject jSONObject;
        if (TextUtils.isEmpty(str)) {
            Logger.error(TAG, "handleAbortRequest: jsonData is null");
            return true;
        }
        try {
            jSONObject = new JSONObject(str);
        } catch (JSONException e) {
            Logger.error(TAG, "handleAbortRequest: parse json exception", e);
            jSONObject = null;
        }
        JSONObject jSONObject2 = jSONObject;
        if (jSONObject2 == null) {
            return true;
        }
        String strOptString = jSONObject2.optString(JsonKey.DOMAIN_NAME);
        String strOptString2 = jSONObject2.optString("name");
        long jOptLong = jSONObject2.optLong("id", -1L);
        if (!Utils.isSameProcess()) {
            Logger.info(TAG, "handleAbortRequest: " + Utils.buildMessageLog(jOptLong, strOptString, strOptString2));
        }
        if (TextUtils.isEmpty(strOptString)) {
            Logger.error(TAG, "handleAbortRequest: domain name is null");
            return true;
        }
        if (TextUtils.isEmpty(strOptString2)) {
            Logger.error(TAG, "handleAbortRequest: requestName is null");
            return true;
        }
        Map<String, Map<String, RequestHandler<? extends BaseResult>>> map = this.mDomainRequestHandlerMap;
        if (map == null || map.isEmpty()) {
            Logger.error(TAG, "handleAbortRequest: domain map is empty");
            return true;
        }
        Map<String, RequestHandler<? extends BaseResult>> requestHandlerMap = getRequestHandlerMap(map, strOptString);
        if (requestHandlerMap == null) {
            Logger.error(TAG, "handleAbortRequest: domain is not registered");
            return true;
        }
        RequestHandler<? extends BaseResult> requestHandler = getRequestHandler(strOptString2, requestHandlerMap);
        if (requestHandler == null) {
            Logger.error(TAG, "handleAbortRequest: request handler is not registered");
            return true;
        }
        Request requestCreateRequest = createRequest(str, jSONObject2, jOptLong, strOptString, strOptString2);
        String str2 = TAG;
        Logger.warn(str2, "handleAbortRequest: begin");
        boolean zOnAbort = requestHandler.onAbort(requestCreateRequest);
        Logger.warn(str2, Utils.format("handleAbortRequest: end, isAborted = %s", Boolean.valueOf(zOnAbort)));
        return zOnAbort;
    }

    static Request createRequest(String str, JSONObject jSONObject, long j, String str2, String str3) {
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("params");
        return new Request(j, str3, str2, jSONObjectOptJSONObject != null ? jSONObjectOptJSONObject.toString() : null, str);
    }

    private static RequestHandler<? extends BaseResult> getRequestHandler(String str, Map<String, RequestHandler<? extends BaseResult>> map) {
        RequestHandler<? extends BaseResult> requestHandler = map.get(str);
        return requestHandler == null ? map.get("*") : requestHandler;
    }

    private static Map<String, RequestHandler<? extends BaseResult>> getRequestHandlerMap(Map<String, Map<String, RequestHandler<? extends BaseResult>>> map, String str) {
        Map<String, RequestHandler<? extends BaseResult>> map2 = map.get(str);
        return map2 == null ? map.get("*") : map2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    static class Request implements IRequest {
        private final String mDomain;
        private final long mId;
        private final String mName;
        private final String mParams;
        private final String mRequestJson;

        public Request(long j, String str, String str2, String str3, String str4) {
            this.mId = j;
            this.mName = str;
            this.mDomain = str2;
            this.mParams = str3;
            this.mRequestJson = str4;
        }

        @Override // cerence.ark.assistant.api.IRequest
        public long getId() {
            return this.mId;
        }

        @Override // cerence.ark.assistant.api.IRequest
        public String getName() {
            return this.mName;
        }

        @Override // cerence.ark.assistant.api.IRequest
        public String getDomainName() {
            return this.mDomain;
        }

        @Override // cerence.ark.assistant.api.IRequest
        public String getParams() {
            return this.mParams;
        }

        @Override // cerence.ark.assistant.api.IRequest, cerence.ark.assistant.core.JsonCompliant
        public String toJsonString() {
            return this.mRequestJson;
        }
    }

    static class ResponseSender implements IResponseSender<BaseResult> {
        private final IAssistant mAssistant;
        private final String mDomainName;
        private final long mMessageId;
        private final String mRequestName;

        ResponseSender(long j, String str, String str2, IAssistant iAssistant) {
            this.mMessageId = j;
            this.mDomainName = str;
            this.mRequestName = str2;
            this.mAssistant = iAssistant;
        }

        @Override // cerence.ark.assistant.api.IResponseSender
        public long getId() {
            return this.mMessageId;
        }

        public String toString() {
            return Utils.buildMessageLog(this.mMessageId, this.mDomainName, this.mRequestName);
        }

        @Override // cerence.ark.assistant.api.IResponseSender
        public void send(IResponse<BaseResult> iResponse) {
            if (this.mAssistant == null) {
                Logger.error(MessageHandler.TAG, Utils.format("sendResponse: remote assistant is null, %s", this));
                return;
            }
            if (iResponse == null) {
                throw new NullPointerException("The response cannot be null, " + this);
            }
            try {
                String strCreateResponseJson = Utils.createResponseJson(this.mMessageId, this.mRequestName, iResponse.toJsonString());
                if (!Utils.isSameProcess()) {
                    Logger.debug(MessageHandler.TAG, Utils.format("sendResponse: %s", strCreateResponseJson));
                    Logger.info(MessageHandler.TAG, Utils.format("sendResponse: %s", this));
                }
                new StringSplitter().split(strCreateResponseJson, new ISplitter.SplitterListener() { // from class: cerence.ark.assistant.core.MessageHandler$ResponseSender$$ExternalSyntheticLambda0
                    @Override // cerence.ark.assistant.library.ipctransaction.ISplitter.SplitterListener
                    public final void onSplit(Object obj, String str, int i, int i2) {
                        this.f$0.m59xa03cafb0((String) obj, str, i, i2);
                    }
                });
            } catch (Exception e) {
                Logger.error(MessageHandler.TAG, "sendResponse: exception", e);
            }
        }

        /* JADX INFO: renamed from: lambda$send$0$cerence-ark-assistant-core-MessageHandler$ResponseSender, reason: not valid java name */
        /* synthetic */ void m59xa03cafb0(String str, String str2, int i, int i2) {
            try {
                this.mAssistant.sendResponse(str, str2, i, i2, new ICallback.Stub() { // from class: cerence.ark.assistant.core.MessageHandler.ResponseSender.1
                    @Override // cerence.ark.assistant.service.ICallback
                    public void onSuccess(String str3) {
                    }

                    @Override // cerence.ark.assistant.service.ICallback
                    public void onError(ErrorCode errorCode, String str3) {
                        Logger.error(MessageHandler.TAG, Utils.format("sendResponse: errorCode = %s, errorMessage = %s", errorCode, str3));
                    }
                });
            } catch (Exception e) {
                Logger.error(MessageHandler.TAG, "sendResponse: send response exception", e);
            }
        }
    }
}
