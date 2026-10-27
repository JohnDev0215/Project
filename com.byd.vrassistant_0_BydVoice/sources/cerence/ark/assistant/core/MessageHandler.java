package cerence.ark.assistant.core;

import android.text.TextUtils;
import cerence.ark.assistant.api.BaseResult;
import cerence.ark.assistant.api.IResponse;
import cerence.ark.assistant.api.IResponseSender;
import cerence.ark.assistant.api.controller.RequestHandler;
import cerence.ark.assistant.common.Logger;
import cerence.ark.assistant.library.JsonKey;
import cerence.ark.assistant.library.Utils;
import cerence.ark.assistant.library.ipctransaction.ISplitter;
import cerence.ark.assistant.library.ipctransaction.ipcstring.StringSplitter;
import cerence.ark.assistant.service.IAssistant;
import cerence.ark.assistant.service.ICallback;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class MessageHandler {
    private static final String DOMAIN_REQUEST_WILDCARD_SYMBOL = "*";
    private static final String TAG = "MessageHandler";
    private IAssistant mAssistant;
    private Map<String, Map<String, RequestHandler<? extends BaseResult>>> mDomainRequestHandlerMap;

    public MessageHandler(Map<String, Map<String, RequestHandler<? extends BaseResult>>> map) {
        this.mDomainRequestHandlerMap = map;
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
    }

    public void handleRequest(String str) {
        if (TextUtils.isEmpty(str)) {
            Logger.error(TAG, "handleRequest: jsonData is null");
            return;
        }
        if (!Utils.isSameProcess()) {
            Logger.debug(TAG, String.format("handleRequest: %s", str));
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            String strOptString = jSONObject.optString(JsonKey.DOMAIN_NAME);
            String strOptString2 = jSONObject.optString("name");
            int iOptInt = jSONObject.optInt("id", -1);
            if (TextUtils.isEmpty(strOptString)) {
                Logger.error(TAG, "handleRequest: domain name is null, " + Utils.buildMessageLog(iOptInt, strOptString, strOptString2));
                return;
            }
            if (TextUtils.isEmpty(strOptString2)) {
                Logger.error(TAG, "handleRequest: requestName is null, " + Utils.buildMessageLog(iOptInt, strOptString, strOptString2));
                return;
            }
            Map<String, Map<String, RequestHandler<? extends BaseResult>>> map = this.mDomainRequestHandlerMap;
            if (map != null && !map.isEmpty()) {
                Map<String, RequestHandler<? extends BaseResult>> requestHandlerMap = getRequestHandlerMap(map, strOptString);
                if (requestHandlerMap == null) {
                    Logger.error(TAG, String.format("handleRequest: domain is not registered, %s", Utils.buildMessageLog(iOptInt, strOptString, strOptString2)));
                    return;
                }
                RequestHandler<? extends BaseResult> requestHandler = getRequestHandler(strOptString2, requestHandlerMap);
                if (requestHandler == null) {
                    Logger.error(TAG, String.format("handleRequest: request handler is not registered, %s", Utils.buildMessageLog(iOptInt, strOptString, strOptString2)));
                    return;
                } else {
                    requestHandler.onHandle(createRequest(str, jSONObject, strOptString, strOptString2), new ResponseSender(iOptInt, strOptString, strOptString2, this.mAssistant));
                    return;
                }
            }
            Logger.error(TAG, "handleRequest: domain map is empty, " + Utils.buildMessageLog(iOptInt, strOptString, strOptString2));
        } catch (JSONException e) {
            Logger.error(TAG, "handleRequest: parse json exception", e);
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
        if (jSONObject == null) {
            return true;
        }
        String strOptString = jSONObject.optString(JsonKey.DOMAIN_NAME);
        String strOptString2 = jSONObject.optString("name");
        int iOptInt = jSONObject.optInt("id", -1);
        if (TextUtils.isEmpty(strOptString)) {
            Logger.error(TAG, "handleAbortRequest: domain name is null, " + Utils.buildMessageLog(iOptInt, strOptString, strOptString2));
            return true;
        }
        if (TextUtils.isEmpty(strOptString2)) {
            Logger.error(TAG, "handleAbortRequest: requestName is null, " + Utils.buildMessageLog(iOptInt, strOptString, strOptString2));
            return true;
        }
        Map<String, Map<String, RequestHandler<? extends BaseResult>>> map = this.mDomainRequestHandlerMap;
        if (map == null || map.isEmpty()) {
            Logger.error(TAG, "handleAbortRequest: domain map is empty, " + Utils.buildMessageLog(iOptInt, strOptString, strOptString2));
            return true;
        }
        Map<String, RequestHandler<? extends BaseResult>> requestHandlerMap = getRequestHandlerMap(map, strOptString);
        if (requestHandlerMap == null) {
            Logger.error(TAG, String.format("handleAbortRequest: domain is not registered, %s", Utils.buildMessageLog(iOptInt, strOptString, strOptString2)));
            return true;
        }
        RequestHandler<? extends BaseResult> requestHandler = getRequestHandler(strOptString2, requestHandlerMap);
        if (requestHandler == null) {
            Logger.error(TAG, String.format("handleAbortRequest: request handler is not registered, %s", Utils.buildMessageLog(iOptInt, strOptString, strOptString2)));
            return true;
        }
        Request requestCreateRequest = createRequest(str, jSONObject, strOptString, strOptString2);
        String str2 = TAG;
        Logger.warn(str2, String.format("begin: handleAbortRequest: %s", Utils.buildMessageLog(iOptInt, strOptString, strOptString2)));
        boolean zOnAbort = requestHandler.onAbort(requestCreateRequest);
        Logger.warn(str2, String.format("end: handleAbortRequest: %s, isAborted = %s,", Utils.buildMessageLog(iOptInt, strOptString, strOptString2), Boolean.valueOf(zOnAbort)));
        return zOnAbort;
    }

    public static Request createRequest(String str, JSONObject jSONObject, String str2, String str3) {
        Request request = new Request();
        request.setName(str3).setDomain(str2).setRequestJson(str);
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("params");
        if (jSONObjectOptJSONObject != null) {
            request.setParams(jSONObjectOptJSONObject.toString());
        }
        return request;
    }

    private static RequestHandler<? extends BaseResult> getRequestHandler(String str, Map<String, RequestHandler<? extends BaseResult>> map) {
        RequestHandler<? extends BaseResult> requestHandler = map.get(str);
        return requestHandler == null ? map.get(DOMAIN_REQUEST_WILDCARD_SYMBOL) : requestHandler;
    }

    private static Map<String, RequestHandler<? extends BaseResult>> getRequestHandlerMap(Map<String, Map<String, RequestHandler<? extends BaseResult>>> map, String str) {
        Map<String, RequestHandler<? extends BaseResult>> map2 = map.get(str);
        return map2 == null ? map.get(DOMAIN_REQUEST_WILDCARD_SYMBOL) : map2;
    }

    public static class ResponseSender implements IResponseSender<BaseResult> {
        private final IAssistant mAssistant;
        private final String mDomainName;
        private final int mMessageId;
        private final String mRequestName;

        public ResponseSender(int i, String str, String str2, IAssistant iAssistant) {
            this.mMessageId = i;
            this.mDomainName = str;
            this.mRequestName = str2;
            this.mAssistant = iAssistant;
        }

        @Override // cerence.ark.assistant.api.IResponseSender
        public void send(IResponse<BaseResult> iResponse) {
            if (this.mAssistant == null) {
                Logger.error(MessageHandler.TAG, String.format("sendResponse: remote assistant is null, %s", Utils.buildMessageLog(this.mMessageId, this.mDomainName, this.mRequestName)));
                return;
            }
            if (iResponse == null) {
                throw new NullPointerException("The response cannot be null, " + Utils.buildMessageLog(this.mMessageId, this.mDomainName, this.mRequestName));
            }
            try {
                String strCreateResponseJson = Utils.createResponseJson(this.mMessageId, this.mRequestName, iResponse.toJsonString());
                Logger.info(MessageHandler.TAG, String.format("sendResponse: %s", Utils.buildMessageLog(this.mMessageId, this.mDomainName, this.mRequestName)));
                new StringSplitter().split(strCreateResponseJson, new ISplitter.SplitterListener() { // from class: cerence.ark.assistant.core.MessageHandler$ResponseSender$$ExternalSyntheticLambda0
                    @Override // cerence.ark.assistant.library.ipctransaction.ISplitter.SplitterListener
                    public final void onSplit(Object obj, boolean z, boolean z2) {
                        this.f$0.m41xa03cafb0((String) obj, z, z2);
                    }
                });
            } catch (Exception e) {
                Logger.error(MessageHandler.TAG, String.format("sendResponse: exception, %s", Utils.buildMessageLog(this.mMessageId, this.mDomainName, this.mRequestName)), e);
            }
        }

        /* JADX INFO: renamed from: lambda$send$0$cerence-ark-assistant-core-MessageHandler$ResponseSender, reason: not valid java name */
        /* synthetic */ void m41xa03cafb0(String str, boolean z, boolean z2) {
            try {
                this.mAssistant.sendResponse(str, z, z2, new ICallback.Stub() { // from class: cerence.ark.assistant.core.MessageHandler.ResponseSender.1
                    @Override // cerence.ark.assistant.service.ICallback
                    public void onSuccess() {
                    }

                    @Override // cerence.ark.assistant.service.ICallback
                    public void onFailure(String str2) {
                        Logger.error(MessageHandler.TAG, String.format("sendResponse: onFailure: errorMessage = %s", str2));
                    }
                });
            } catch (Exception e) {
                Logger.error(MessageHandler.TAG, "sendResponse: exception", e);
            }
        }
    }
}
