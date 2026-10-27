package cerence.ark.assistant.service;

import android.media.AudioAttributes;
import android.text.TextUtils;
import cerence.ark.assistant.api.prompter.TtsVoice;
import cerence.ark.assistant.common.Logger;
import cerence.ark.assistant.common.callback.IResultCallback;
import cerence.ark.assistant.common.callback.Result;
import cerence.ark.assistant.dm.NativeDialogManager;
import cerence.ark.assistant.dm.data.PrompterVoice;
import cerence.ark.assistant.framework.AssistantState;
import cerence.ark.assistant.framework.ErrorListener;
import cerence.ark.assistant.framework.Framework;
import cerence.ark.assistant.framework.FrameworkMessageListener;
import cerence.ark.assistant.framework.data.AssistantConfiguration;
import cerence.ark.assistant.framework.data.AssistantContextType;
import cerence.ark.assistant.framework.data.Config;
import cerence.ark.assistant.framework.data.ErrorInfo;
import cerence.ark.assistant.framework.data.wuw.WuwConfig;
import cerence.ark.assistant.framework.data.wuw.WuwInfo;
import cerence.ark.assistant.framework.impl.ArkFramework;
import cerence.ark.assistant.library.ErrorMessage;
import cerence.ark.assistant.library.JsonKey;
import cerence.ark.assistant.library.Utils;
import cerence.ark.assistant.library.ipctransaction.ISplitter;
import cerence.ark.assistant.library.ipctransaction.ipclist.ListAssembler;
import cerence.ark.assistant.library.ipctransaction.ipcstring.StringAssembler;
import cerence.ark.assistant.library.ipctransaction.ipcstring.StringSplitter;
import cerence.ark.assistant.service.IAssistant;
import cerence.ark.assistant.service.SpeechService;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public final class AssistantImpl extends IAssistant.Stub {
    private static final String DOMAIN_REQUEST_WILDCARD_SYMBOL = "*";
    private static final String HMI_FLAG = "HMI";
    private static final String TAG = "AssistantImpl";
    private StringAssembler mContextDataStringAssembler;
    private String mDataPath;
    private FrameworkMessageListener mDialogMessageListener;
    private ListAssembler<String> mDynamicContentAssembler;
    private IErrorListener mErrorListener;
    private Framework mFramework;
    private IHmiListener mHmiListener;
    private volatile boolean mIsActivated;
    private final SpeechService.ILifecycle mLifecycle;
    private INotificationListener mNotificationListener;
    private IRequestListener mRequestListener;
    private StringAssembler mResponseStringAssembler;
    private ListAssembler<String> mShortcutAssembler;
    private IUnhandledRequestListener mUnhandledRequestListener;
    private final Map<String, PrompterServiceImpl> mPrompterMap = new ConcurrentHashMap(16);
    private final ConcurrentHashMap<String, ConcurrentHashMap<String, ClientHolder>> mDomainMap = new ConcurrentHashMap<>(16);
    private final ConcurrentHashMap<String, IActivated> mActivatedListenerMap = new ConcurrentHashMap<>(16);

    public AssistantImpl(SpeechService.ILifecycle iLifecycle) {
        this.mLifecycle = iLifecycle;
    }

    /* JADX INFO: renamed from: cerence.ark.assistant.service.AssistantImpl$1, reason: invalid class name */
    class AnonymousClass1 implements FrameworkMessageListener {
        AnonymousClass1() {
        }

        @Override // cerence.ark.assistant.framework.FrameworkMessageListener
        public void onRequestReceived(final String str) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                final String strOptString = jSONObject.optString(JsonKey.DOMAIN_NAME);
                final int iOptInt = jSONObject.optInt("id", -1);
                final String strOptString2 = jSONObject.optString("name");
                if (TextUtils.isEmpty(strOptString)) {
                    Logger.error(AssistantImpl.TAG, "onRequestReceived: domain name is null, " + Utils.buildMessageLog(iOptInt, strOptString, strOptString2));
                    return;
                }
                if (TextUtils.isEmpty(strOptString2)) {
                    Logger.error(AssistantImpl.TAG, "onRequestReceived: requestName is null, " + Utils.buildMessageLog(iOptInt, strOptString, strOptString2));
                    return;
                }
                if ("HMI".equals(strOptString)) {
                    if (AssistantImpl.this.mHmiListener == null) {
                        Logger.error(AssistantImpl.TAG, "onRequestReceived: hmi listener is null, " + Utils.buildMessageLog(iOptInt, strOptString, strOptString2));
                        return;
                    } else {
                        new StringSplitter().split(str, new ISplitter.SplitterListener() { // from class: cerence.ark.assistant.service.AssistantImpl$1$$ExternalSyntheticLambda0
                            @Override // cerence.ark.assistant.library.ipctransaction.ISplitter.SplitterListener
                            public final void onSplit(Object obj, boolean z, boolean z2) {
                                this.f$0.m62x765cde1b((String) obj, z, z2);
                            }
                        });
                        return;
                    }
                }
                final ClientHolder clientHolder = AssistantImpl.this.getClientHolder(strOptString, strOptString2);
                if (clientHolder == null) {
                    AssistantImpl.this.unHandledRequest(str, iOptInt, strOptString, strOptString2);
                } else {
                    new StringSplitter().split(str, new ISplitter.SplitterListener() { // from class: cerence.ark.assistant.service.AssistantImpl$1$$ExternalSyntheticLambda1
                        @Override // cerence.ark.assistant.library.ipctransaction.ISplitter.SplitterListener
                        public final void onSplit(Object obj, boolean z, boolean z2) {
                            this.f$0.m63xebd7045c(clientHolder, str, iOptInt, strOptString, strOptString2, (String) obj, z, z2);
                        }
                    });
                }
            } catch (Exception e) {
                Logger.error(AssistantImpl.TAG, "onRequestReceived: parse json exception", e);
            }
        }

        /* JADX INFO: renamed from: lambda$onRequestReceived$0$cerence-ark-assistant-service-AssistantImpl$1, reason: not valid java name */
        /* synthetic */ void m62x765cde1b(String str, boolean z, boolean z2) {
            try {
                AssistantImpl.this.mHmiListener.onRequest(str, z, z2);
            } catch (Exception e) {
                Logger.error(AssistantImpl.TAG, "onRequestReceived: exception", e);
            }
        }

        /* JADX INFO: renamed from: lambda$onRequestReceived$1$cerence-ark-assistant-service-AssistantImpl$1, reason: not valid java name */
        /* synthetic */ void m63xebd7045c(ClientHolder clientHolder, String str, int i, String str2, String str3, String str4, boolean z, boolean z2) {
            try {
                clientHolder.getRequestListener().onRequestReceived(str4, z, z2);
            } catch (Exception e) {
                if (clientHolder.getRequestListener() == AssistantImpl.this.mRequestListener) {
                    Logger.error(AssistantImpl.TAG, "onRequestReceived: exception", e);
                } else {
                    Logger.error(AssistantImpl.TAG, String.format("client [%s] disconnected, send to assistant client", clientHolder.getPackageName()));
                    AssistantImpl.this.unHandledRequest(str, i, str2, str3);
                }
            }
        }

        @Override // cerence.ark.assistant.framework.FrameworkMessageListener
        public void onNotificationReceived(String str) {
            if (AssistantImpl.this.mNotificationListener == null) {
                Logger.error(AssistantImpl.TAG, "onNotificationReceived: message listener is null");
            } else {
                new StringSplitter().split(str, new ISplitter.SplitterListener() { // from class: cerence.ark.assistant.service.AssistantImpl$1$$ExternalSyntheticLambda2
                    @Override // cerence.ark.assistant.library.ipctransaction.ISplitter.SplitterListener
                    public final void onSplit(Object obj, boolean z, boolean z2) {
                        this.f$0.m61xe1640671((String) obj, z, z2);
                    }
                });
            }
        }

        /* JADX INFO: renamed from: lambda$onNotificationReceived$2$cerence-ark-assistant-service-AssistantImpl$1, reason: not valid java name */
        /* synthetic */ void m61xe1640671(String str, boolean z, boolean z2) {
            try {
                AssistantImpl.this.mNotificationListener.onNotificationReceived(str, z, z2);
            } catch (Exception e) {
                Logger.error(AssistantImpl.TAG, "onNotificationReceived: exception", e);
            }
        }

        @Override // cerence.ark.assistant.framework.FrameworkMessageListener
        public boolean onAbortRequest(String str) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                String strOptString = jSONObject.optString(JsonKey.DOMAIN_NAME);
                int iOptInt = jSONObject.optInt("id", -1);
                String strOptString2 = jSONObject.optString("name");
                if (TextUtils.isEmpty(strOptString)) {
                    Logger.error(AssistantImpl.TAG, "onAbortRequest: domain name is null, " + Utils.buildMessageLog(iOptInt, strOptString, strOptString2));
                    return true;
                }
                if ("HMI".equals(strOptString)) {
                    Logger.error(AssistantImpl.TAG, "onAbortRequest: hmi domain, " + Utils.buildMessageLog(iOptInt, strOptString, strOptString2));
                    return true;
                }
                if (!TextUtils.isEmpty(strOptString2)) {
                    ClientHolder clientHolder = AssistantImpl.this.getClientHolder(strOptString, strOptString2);
                    if (clientHolder == null) {
                        Logger.error(AssistantImpl.TAG, "onAbortRequest: no client was found to process the request, " + Utils.buildMessageLog(iOptInt, strOptString, strOptString2));
                        return true;
                    }
                    return clientHolder.getRequestListener().onAbortRequest(str);
                }
                Logger.error(AssistantImpl.TAG, "onAbortRequest: requestName is null, " + Utils.buildMessageLog(iOptInt, strOptString, strOptString2));
                return true;
            } catch (Exception e) {
                Logger.error(AssistantImpl.TAG, "onAbortRequest: exception", e);
                return true;
            }
        }
    }

    private FrameworkMessageListener createMessageListener() {
        return new AnonymousClass1();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ClientHolder getClientHolder(String str, String str2) {
        ConcurrentHashMap<String, ClientHolder> concurrentHashMap = this.mDomainMap.get(str);
        if (concurrentHashMap == null) {
            ConcurrentHashMap<String, ClientHolder> concurrentHashMap2 = this.mDomainMap.get(DOMAIN_REQUEST_WILDCARD_SYMBOL);
            if (concurrentHashMap2 == null) {
                return null;
            }
            ClientHolder clientHolder = concurrentHashMap2.get(str2);
            return clientHolder == null ? concurrentHashMap2.get(DOMAIN_REQUEST_WILDCARD_SYMBOL) : clientHolder;
        }
        ClientHolder clientHolder2 = concurrentHashMap.get(str2);
        return clientHolder2 == null ? concurrentHashMap.get(DOMAIN_REQUEST_WILDCARD_SYMBOL) : clientHolder2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void unHandledRequest(String str, int i, String str2, String str3) {
        final IUnhandledRequestListener iUnhandledRequestListener = this.mUnhandledRequestListener;
        if (iUnhandledRequestListener == null) {
            Logger.error(TAG, "unHandledRequest: unhandledRequestListener is null, " + Utils.buildMessageLog(i, str2, str3));
        } else {
            new StringSplitter().split(str, new ISplitter.SplitterListener() { // from class: cerence.ark.assistant.service.AssistantImpl$$ExternalSyntheticLambda0
                @Override // cerence.ark.assistant.library.ipctransaction.ISplitter.SplitterListener
                public final void onSplit(Object obj, boolean z, boolean z2) {
                    AssistantImpl.lambda$unHandledRequest$0(iUnhandledRequestListener, (String) obj, z, z2);
                }
            });
        }
    }

    static /* synthetic */ void lambda$unHandledRequest$0(IUnhandledRequestListener iUnhandledRequestListener, String str, boolean z, boolean z2) {
        try {
            iUnhandledRequestListener.onUnhandledRequest(str, z, z2);
        } catch (Exception e) {
            Logger.error(TAG, "unHandledRequest: exception", e);
        }
    }

    private ErrorListener createErrorListener() {
        return new ErrorListener() { // from class: cerence.ark.assistant.service.AssistantImpl$$ExternalSyntheticLambda10
            @Override // cerence.ark.assistant.framework.ErrorListener
            public final void onError(ErrorInfo errorInfo) {
                this.f$0.m58x1d39f21e(errorInfo);
            }
        };
    }

    /* JADX INFO: renamed from: lambda$createErrorListener$1$cerence-ark-assistant-service-AssistantImpl, reason: not valid java name */
    /* synthetic */ void m58x1d39f21e(ErrorInfo errorInfo) {
        IErrorListener iErrorListener = this.mErrorListener;
        if (iErrorListener == null) {
            Logger.error(TAG, "onError: error listener is null");
            return;
        }
        try {
            iErrorListener.onError(errorInfo);
        } catch (Exception e) {
            Logger.error(TAG, "onError: exception", e);
        }
    }

    @Override // cerence.ark.assistant.service.IAssistant
    public void initialize(Config config, INotificationListener iNotificationListener, IHmiListener iHmiListener, final ICallback iCallback) {
        if (this.mFramework == null) {
            this.mFramework = new ArkFramework();
        }
        if (config == null) {
            notifyFailure("initialize", "config is null", true, iCallback);
            return;
        }
        SpeechService.ILifecycle iLifecycle = this.mLifecycle;
        if (iLifecycle != null) {
            iLifecycle.onInitialize(config);
        }
        this.mDataPath = config.getDataPath();
        this.mNotificationListener = iNotificationListener;
        this.mHmiListener = iHmiListener;
        FrameworkMessageListener frameworkMessageListenerCreateMessageListener = createMessageListener();
        this.mDialogMessageListener = frameworkMessageListenerCreateMessageListener;
        this.mFramework.initialize(config, frameworkMessageListenerCreateMessageListener, new IResultCallback() { // from class: cerence.ark.assistant.service.AssistantImpl$$ExternalSyntheticLambda8
            @Override // cerence.ark.assistant.common.callback.IResultCallback
            public final void onResult(Result result) {
                this.f$0.m60lambda$initialize$2$cerencearkassistantserviceAssistantImpl(iCallback, result);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$initialize$2$cerence-ark-assistant-service-AssistantImpl, reason: not valid java name */
    /* synthetic */ void m60lambda$initialize$2$cerencearkassistantserviceAssistantImpl(ICallback iCallback, Result result) {
        if (result.isSuccess()) {
            this.mIsActivated = true;
            for (IActivated iActivated : this.mActivatedListenerMap.values()) {
                if (iActivated != null) {
                    activateDomain(iActivated);
                }
            }
            notifySuccess("initialize", iCallback);
            return;
        }
        notifyFailure("initialize: onFailure", result.getErrorMessage(), false, iCallback);
    }

    @Override // cerence.ark.assistant.service.IAssistant
    public void registerRequestListener(String str, Map map, IRequestListener iRequestListener, ICallback iCallback) {
        if (TextUtils.isEmpty(str)) {
            notifyFailure("registerRequestListener", "client package name is empty", true, iCallback);
            return;
        }
        if (str.equals(Utils.getContext().getPackageName())) {
            this.mRequestListener = iRequestListener;
        }
        if (iRequestListener == null) {
            notifyFailure("registerRequestListener", "requestListener is null", true, iCallback);
            return;
        }
        unregisterRequestListener(str);
        int i = 16;
        HashMap map2 = new HashMap(16);
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap(16);
        if (map != null && !map.isEmpty()) {
            for (Map.Entry entry : map.entrySet()) {
                List<String> list = (List) entry.getValue();
                String str2 = (String) entry.getKey();
                if (TextUtils.isEmpty(str2) || list == null || list.isEmpty()) {
                    Logger.warn(TAG, "registerRequestListener: domain or request list is null");
                } else {
                    ConcurrentHashMap<String, ClientHolder> concurrentHashMap2 = this.mDomainMap.get(str2);
                    ConcurrentHashMap concurrentHashMap3 = new ConcurrentHashMap(i);
                    for (String str3 : list) {
                        concurrentHashMap3.put(str3, new ClientHolder(str, iRequestListener));
                        boolean z = this.mDomainMap.containsKey(DOMAIN_REQUEST_WILDCARD_SYMBOL) && DOMAIN_REQUEST_WILDCARD_SYMBOL.equals(str2);
                        boolean z2 = concurrentHashMap2 != null && ((concurrentHashMap2.containsKey(DOMAIN_REQUEST_WILDCARD_SYMBOL) && DOMAIN_REQUEST_WILDCARD_SYMBOL.equals(str3)) || concurrentHashMap2.containsKey(str3));
                        if (z || z2) {
                            addRequestName(map2, str2, str3);
                        }
                    }
                    concurrentHashMap.put(str2, concurrentHashMap3);
                }
                i = 16;
            }
        }
        if (map2.isEmpty()) {
            for (Map.Entry entry2 : concurrentHashMap.entrySet()) {
                String str4 = (String) entry2.getKey();
                ConcurrentHashMap<String, ClientHolder> concurrentHashMap4 = (ConcurrentHashMap) entry2.getValue();
                ConcurrentHashMap<String, ClientHolder> concurrentHashMap5 = this.mDomainMap.get(str4);
                if (concurrentHashMap5 == null) {
                    this.mDomainMap.put(str4, concurrentHashMap4);
                } else {
                    for (Map.Entry<String, ClientHolder> entry3 : concurrentHashMap4.entrySet()) {
                        concurrentHashMap5.put(entry3.getKey(), entry3.getValue());
                    }
                }
            }
            IActivated iActivated = this.mActivatedListenerMap.get(str);
            if (this.mIsActivated && iActivated != null) {
                activateDomain(iActivated);
            }
            notifySuccess("registerRequestListener", iCallback);
            return;
        }
        Set<Map.Entry<String, List<String>>> setEntrySet = map2.entrySet();
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, List<String>> entry4 : setEntrySet) {
            sb.append(entry4.getKey());
            sb.append(Arrays.toString((String[]) entry4.getValue().toArray(new String[0])));
        }
        notifyFailure("registerRequestListener", String.format("%s: the request name has already been registered by other application: %s", str, sb.toString()), true, iCallback);
    }

    private void activateDomain(IActivated iActivated) {
        try {
            iActivated.activate();
        } catch (Exception e) {
            Logger.error(TAG, "active domain exception", e);
        }
    }

    private void addRequestName(Map<String, List<String>> map, String str, String str2) {
        List<String> list = map.get(str);
        if (list == null) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(str2);
            map.put(str, arrayList);
            return;
        }
        list.add(str2);
    }

    @Override // cerence.ark.assistant.service.IAssistant
    public void unregisterRequestListener(String str) {
        if (TextUtils.isEmpty(str)) {
            notifyFailure("unregisterRequestListener", "client package name is empty", true, null);
            return;
        }
        for (Map.Entry<String, ConcurrentHashMap<String, ClientHolder>> entry : this.mDomainMap.entrySet()) {
            ConcurrentHashMap<String, ClientHolder> value = entry.getValue();
            if (value != null) {
                for (Map.Entry<String, ClientHolder> entry2 : value.entrySet()) {
                    if (entry2.getValue() == null) {
                        Logger.warn(TAG, "client holder is null, request name = " + entry2.getKey());
                        value.remove(entry2.getKey());
                    } else if (str.equals(entry2.getValue().getPackageName())) {
                        value.remove(entry2.getKey());
                    }
                }
                ConcurrentHashMap<String, ClientHolder> concurrentHashMap = this.mDomainMap.get(entry.getKey());
                if (concurrentHashMap != null && concurrentHashMap.isEmpty()) {
                    this.mDomainMap.remove(entry.getKey());
                }
            }
        }
    }

    @Override // cerence.ark.assistant.service.IAssistant
    public void setUnhandledRequestListener(IUnhandledRequestListener iUnhandledRequestListener) {
        this.mUnhandledRequestListener = iUnhandledRequestListener;
    }

    @Override // cerence.ark.assistant.service.IAssistant
    public void registerActivatedListener(String str, IActivated iActivated) {
        if (TextUtils.isEmpty(str)) {
            Logger.error(TAG, "registerActivatedListener: client package name is empty");
        } else if (iActivated == null) {
            Logger.error(TAG, "registerActivatedListener: activate listener is null");
        } else {
            this.mActivatedListenerMap.put(str, iActivated);
        }
    }

    @Override // cerence.ark.assistant.service.IAssistant
    public void startVoiceAssistant(AssistantConfiguration assistantConfiguration, final ICallback iCallback) {
        Framework framework = this.mFramework;
        if (framework == null) {
            notifyFailure("startVoiceAssistant", ErrorMessage.FRAMEWORK_NULL, true, iCallback);
        } else {
            framework.startVoiceAssistant(assistantConfiguration, new IResultCallback() { // from class: cerence.ark.assistant.service.AssistantImpl$$ExternalSyntheticLambda11
                @Override // cerence.ark.assistant.common.callback.IResultCallback
                public final void onResult(Result result) {
                    AssistantImpl.lambda$startVoiceAssistant$3(iCallback, result);
                }
            });
        }
    }

    static /* synthetic */ void lambda$startVoiceAssistant$3(ICallback iCallback, Result result) {
        if (result.isSuccess()) {
            notifySuccess("startVoiceAssistant", iCallback);
        } else {
            notifyFailure("startVoiceAssistant: onFailure", result.getErrorMessage(), false, iCallback);
        }
    }

    @Override // cerence.ark.assistant.service.IAssistant
    public void stopVoiceAssistant(final ICallback iCallback) {
        Framework framework = this.mFramework;
        if (framework == null) {
            notifyFailure("stopVoiceAssistant", ErrorMessage.FRAMEWORK_NULL, true, iCallback);
        } else {
            framework.stopVoiceAssistant(new IResultCallback() { // from class: cerence.ark.assistant.service.AssistantImpl$$ExternalSyntheticLambda9
                @Override // cerence.ark.assistant.common.callback.IResultCallback
                public final void onResult(Result result) {
                    AssistantImpl.lambda$stopVoiceAssistant$4(iCallback, result);
                }
            });
        }
    }

    static /* synthetic */ void lambda$stopVoiceAssistant$4(ICallback iCallback, Result result) {
        if (result.isSuccess()) {
            notifySuccess("stopVoiceAssistant", iCallback);
        } else {
            notifyFailure("stopVoiceAssistant: onFailure", result.getErrorMessage(), false, iCallback);
        }
    }

    @Override // cerence.ark.assistant.service.IAssistant
    public void destroy(final ICallback iCallback) {
        Framework framework = this.mFramework;
        if (framework == null) {
            notifyFailure("destroy", ErrorMessage.FRAMEWORK_NULL, true, iCallback);
            return;
        }
        destroyPrompters();
        framework.destroy(new IResultCallback() { // from class: cerence.ark.assistant.service.AssistantImpl$$ExternalSyntheticLambda2
            @Override // cerence.ark.assistant.common.callback.IResultCallback
            public final void onResult(Result result) {
                this.f$0.m59lambda$destroy$5$cerencearkassistantserviceAssistantImpl(iCallback, result);
            }
        });
        this.mDomainMap.clear();
        this.mFramework = null;
        this.mNotificationListener = null;
        this.mHmiListener = null;
        this.mDialogMessageListener = null;
        this.mErrorListener = null;
        this.mDynamicContentAssembler = null;
        this.mResponseStringAssembler = null;
        this.mDataPath = null;
        SpeechService.ILifecycle iLifecycle = this.mLifecycle;
        if (iLifecycle != null) {
            iLifecycle.onDestroy();
        }
    }

    /* JADX INFO: renamed from: lambda$destroy$5$cerence-ark-assistant-service-AssistantImpl, reason: not valid java name */
    /* synthetic */ void m59lambda$destroy$5$cerencearkassistantserviceAssistantImpl(ICallback iCallback, Result result) {
        if (result.isSuccess()) {
            deactivateDomains();
            notifySuccess("destroy", iCallback);
        } else {
            notifyFailure("destroy: onFailure", result.getErrorMessage(), false, iCallback);
        }
    }

    private void destroyPrompters() {
        PrompterServiceImpl prompterServiceImpl;
        for (String str : this.mPrompterMap.keySet()) {
            if (!TextUtils.isEmpty(str) && (prompterServiceImpl = this.mPrompterMap.get(str)) != null) {
                prompterServiceImpl.destroyPrompter();
            }
        }
        this.mPrompterMap.clear();
    }

    private void deactivateDomains() {
        for (IActivated iActivated : this.mActivatedListenerMap.values()) {
            if (iActivated != null) {
                try {
                    iActivated.deactivate();
                } catch (Exception e) {
                    Logger.error(TAG, "deactivate domain exception", e);
                }
            }
        }
        this.mActivatedListenerMap.clear();
        this.mIsActivated = false;
    }

    @Override // cerence.ark.assistant.service.IAssistant
    public void sendAppEvent(String str, final ICallback iCallback) {
        Framework framework = this.mFramework;
        if (framework == null) {
            notifyFailure("sendAppEvent", ErrorMessage.FRAMEWORK_NULL, true, iCallback);
        } else if (TextUtils.isEmpty(str)) {
            notifyFailure("sendAppEvent", "event is null", true, iCallback);
        } else {
            framework.sendAppEvent(str, new IResultCallback() { // from class: cerence.ark.assistant.service.AssistantImpl$$ExternalSyntheticLambda5
                @Override // cerence.ark.assistant.common.callback.IResultCallback
                public final void onResult(Result result) {
                    AssistantImpl.lambda$sendAppEvent$6(iCallback, result);
                }
            });
        }
    }

    static /* synthetic */ void lambda$sendAppEvent$6(ICallback iCallback, Result result) {
        if (result.isSuccess()) {
            notifySuccess("sendAppEvent", iCallback);
        } else {
            notifyFailure("sendAppEvent", result.getErrorMessage(), false, iCallback);
        }
    }

    @Override // cerence.ark.assistant.service.IAssistant
    public void sendResponse(String str, boolean z, boolean z2, final ICallback iCallback) {
        Framework framework = this.mFramework;
        if (framework == null) {
            notifyFailure("sendResponse", ErrorMessage.FRAMEWORK_NULL, true, iCallback);
            return;
        }
        if (z) {
            this.mResponseStringAssembler = new StringAssembler();
        }
        if (z2) {
            String strAssemble = this.mResponseStringAssembler.assemble(str);
            this.mResponseStringAssembler = null;
            framework.sendResponse(strAssemble, new IResultCallback() { // from class: cerence.ark.assistant.service.AssistantImpl$$ExternalSyntheticLambda7
                @Override // cerence.ark.assistant.common.callback.IResultCallback
                public final void onResult(Result result) {
                    AssistantImpl.lambda$sendResponse$7(iCallback, result);
                }
            });
            return;
        }
        this.mResponseStringAssembler.assemble(str);
    }

    static /* synthetic */ void lambda$sendResponse$7(ICallback iCallback, Result result) {
        if (result.isSuccess()) {
            notifySuccess("sendResponse", iCallback);
        } else {
            notifyFailure("sendResponse", result.getErrorMessage(), false, iCallback);
        }
    }

    @Override // cerence.ark.assistant.service.IAssistant
    public boolean setTtsVoiceId(String str) {
        Framework framework = this.mFramework;
        if (framework == null) {
            Logger.error(TAG, "setTtsVoiceId: framework is null");
            return false;
        }
        return framework.setTtsVoiceId(str);
    }

    @Override // cerence.ark.assistant.service.IAssistant
    public List<String> getAvailableTtsLanguages() {
        return getAvailableTtsLanguages(this.mDataPath);
    }

    @Override // cerence.ark.assistant.service.IAssistant
    public List<TtsVoice> getAvailableTtsVoices(String str) {
        return getAvailableTtsVoices(this.mDataPath, str);
    }

    @Override // cerence.ark.assistant.service.IAssistant
    public List<String> getAvailablePrompterNames() {
        Framework framework = this.mFramework;
        if (framework == null) {
            Logger.error(TAG, "getAvailablePrompterNames: framework is null");
            return new ArrayList();
        }
        return framework.getAvailablePrompterNames();
    }

    @Override // cerence.ark.assistant.service.IAssistant
    public IPrompterService createPrompter(String str, AudioAttributes audioAttributes) {
        PrompterServiceImpl prompterServiceImpl = this.mPrompterMap.get(str);
        if (prompterServiceImpl != null) {
            Logger.warn(TAG, "The prompter already exist: prompterName = " + str);
            return prompterServiceImpl;
        }
        PrompterServiceImpl prompterServiceImpl2 = new PrompterServiceImpl(this.mFramework);
        if (!prompterServiceImpl2.createPrompter(str, audioAttributes)) {
            return null;
        }
        this.mPrompterMap.put(str, prompterServiceImpl2);
        return prompterServiceImpl2;
    }

    @Override // cerence.ark.assistant.service.IAssistant
    public void destroyPrompter(IPrompterService iPrompterService) {
        if (iPrompterService instanceof PrompterServiceImpl) {
            PrompterServiceImpl prompterServiceImpl = (PrompterServiceImpl) iPrompterService;
            this.mPrompterMap.remove(prompterServiceImpl.getPrompterName());
            prompterServiceImpl.destroyPrompter();
        }
    }

    @Override // cerence.ark.assistant.service.IAssistant
    public void updateDynamicContent(String str, List<String> list, boolean z, boolean z2) {
        Framework framework = this.mFramework;
        if (framework == null) {
            Logger.error(TAG, "updateDynamicContent: framework is null");
            return;
        }
        if (z) {
            this.mDynamicContentAssembler = new ListAssembler<>();
        }
        if (z2) {
            List<String> listAssemble = this.mDynamicContentAssembler.assemble(list);
            this.mDynamicContentAssembler = null;
            framework.updateDynamicContent(str, listAssemble);
            return;
        }
        this.mDynamicContentAssembler.assemble(list);
    }

    @Override // cerence.ark.assistant.service.IAssistant
    public boolean updateShortcutCommand(List<String> list, boolean z, boolean z2) {
        Framework framework = this.mFramework;
        if (framework == null) {
            Logger.error(TAG, "updateShortcutCommand: framework is null");
            return false;
        }
        if (z) {
            this.mShortcutAssembler = new ListAssembler<>();
        }
        if (z2) {
            List<String> listAssemble = this.mShortcutAssembler.assemble(list);
            this.mShortcutAssembler = null;
            return framework.updateShortcutCommand(listAssemble);
        }
        this.mShortcutAssembler.assemble(list);
        return true;
    }

    @Override // cerence.ark.assistant.service.IAssistant
    public void setWuwConfig(WuwConfig wuwConfig, final ICallback iCallback) {
        Framework framework = this.mFramework;
        if (framework == null) {
            notifyFailure("setWuwConfig", ErrorMessage.FRAMEWORK_NULL, false, iCallback);
        } else {
            framework.setWuwConfig(wuwConfig, new IResultCallback() { // from class: cerence.ark.assistant.service.AssistantImpl$$ExternalSyntheticLambda3
                @Override // cerence.ark.assistant.common.callback.IResultCallback
                public final void onResult(Result result) {
                    AssistantImpl.lambda$setWuwConfig$8(iCallback, result);
                }
            });
        }
    }

    static /* synthetic */ void lambda$setWuwConfig$8(ICallback iCallback, Result result) {
        if (result.isSuccess()) {
            notifySuccess("setWuwConfig", iCallback);
        } else {
            notifyFailure("setWuwConfig", result.getErrorMessage(), false, iCallback);
        }
    }

    @Override // cerence.ark.assistant.service.IAssistant
    public WuwInfo getWuwInfo() {
        Framework framework = this.mFramework;
        if (framework == null) {
            Logger.error(TAG, Utils.format2Argv("getWuwInfo", ErrorMessage.FRAMEWORK_NULL));
            return null;
        }
        return framework.getWuwInfo();
    }

    @Override // cerence.ark.assistant.service.IAssistant
    public boolean updateAssistantContext(AssistantContextType assistantContextType, String str, boolean z, boolean z2) {
        Framework framework = this.mFramework;
        if (framework == null) {
            Logger.error(TAG, "updateAssistantContext: framework is null");
            return false;
        }
        if (z) {
            this.mContextDataStringAssembler = new StringAssembler();
        }
        if (z2) {
            String strAssemble = this.mContextDataStringAssembler.assemble(str);
            this.mContextDataStringAssembler = null;
            return framework.updateAssistantContext(assistantContextType, strAssemble);
        }
        this.mContextDataStringAssembler.assemble(str);
        return true;
    }

    public static List<String> getAvailableTtsLanguages(String str) {
        String str2 = TAG;
        Logger.debug(str2, String.format("getAvailableTtsLanguages: dataPath: %s", str));
        ArrayList arrayList = new ArrayList();
        if (TextUtils.isEmpty(str)) {
            Logger.error(str2, "getAvailableTtsLanguages: data path is empty");
            return arrayList;
        }
        if (!new File(str).exists()) {
            Logger.error(str2, "getAvailableTtsLanguages: The data path does not exist");
            return arrayList;
        }
        String[] availablePromptLanguages = NativeDialogManager.getAvailablePromptLanguages(str);
        if (availablePromptLanguages == null || availablePromptLanguages.length == 0) {
            Logger.error(str2, "getAvailableTtsLanguages: language list is empty");
            return arrayList;
        }
        arrayList.addAll(Arrays.asList(availablePromptLanguages));
        return arrayList;
    }

    public static List<TtsVoice> getAvailableTtsVoices(String str, String str2) {
        String str3 = TAG;
        Logger.debug(str3, String.format("getAvailableTtsVoices: language: %s, dataPath: %s", str2, str));
        ArrayList arrayList = new ArrayList();
        if (TextUtils.isEmpty(str)) {
            Logger.error(str3, "getAvailableTtsVoices: data path is empty");
            return arrayList;
        }
        if (!new File(str).exists()) {
            Logger.error(str3, "getAvailableTtsVoices: the data path does not exist");
            return arrayList;
        }
        PrompterVoice[] availablePromptVoices = NativeDialogManager.getAvailablePromptVoices(str, str2);
        if (availablePromptVoices == null || availablePromptVoices.length == 0) {
            Logger.error(str3, "getAvailableTtsVoices: prompter voice list is empty");
            return arrayList;
        }
        for (PrompterVoice prompterVoice : availablePromptVoices) {
            if (prompterVoice == null) {
                Logger.warn(TAG, "getAvailableTtsVoices: prompter voice is empty");
            } else {
                arrayList.add(new TtsVoice.Builder().setVoiceId(prompterVoice.getVoiceId()).setLanguage(prompterVoice.getLanguage()).setVoiceName(prompterVoice.getVoiceName()).setGender(prompterVoice.getGender()).setOperatingPoint(prompterVoice.getOperatingPoint()).setVersion(prompterVoice.getVersion()).setAvailableDomains(prompterVoice.getAvailableDomains()).build());
            }
        }
        return arrayList;
    }

    @Override // cerence.ark.assistant.service.IAssistant
    public void setErrorListener(IErrorListener iErrorListener) {
        Framework framework = this.mFramework;
        if (framework == null) {
            Logger.error(TAG, "setErrorListener: framework is null");
        } else {
            framework.setErrorListener(createErrorListener());
            this.mErrorListener = iErrorListener;
        }
    }

    @Override // cerence.ark.assistant.service.IAssistant
    public void activateDevice(String str, final ICallback iCallback) {
        Framework framework = this.mFramework;
        final String str2 = "activateDevice";
        if (framework == null) {
            notifyFailure("activateDevice", ErrorMessage.FRAMEWORK_NULL, true, iCallback);
        } else {
            framework.activateDevice(str, new IResultCallback() { // from class: cerence.ark.assistant.service.AssistantImpl$$ExternalSyntheticLambda1
                @Override // cerence.ark.assistant.common.callback.IResultCallback
                public final void onResult(Result result) {
                    AssistantImpl.lambda$activateDevice$9(str2, iCallback, result);
                }
            });
        }
    }

    static /* synthetic */ void lambda$activateDevice$9(String str, ICallback iCallback, Result result) {
        if (result.isSuccess()) {
            notifySuccess(str, iCallback);
        } else {
            notifyFailure(str, result.getErrorMessage(), true, iCallback);
        }
    }

    @Override // cerence.ark.assistant.service.IAssistant
    public void deactivateDevice(String str, final ICallback iCallback) {
        Framework framework = this.mFramework;
        final String str2 = "deactivateDevice";
        if (framework == null) {
            notifyFailure("deactivateDevice", ErrorMessage.FRAMEWORK_NULL, true, iCallback);
        } else {
            framework.deactivateDevice(str, new IResultCallback() { // from class: cerence.ark.assistant.service.AssistantImpl$$ExternalSyntheticLambda4
                @Override // cerence.ark.assistant.common.callback.IResultCallback
                public final void onResult(Result result) {
                    AssistantImpl.lambda$deactivateDevice$10(str2, iCallback, result);
                }
            });
        }
    }

    static /* synthetic */ void lambda$deactivateDevice$10(String str, ICallback iCallback, Result result) {
        if (result.isSuccess()) {
            notifySuccess(str, iCallback);
        } else {
            notifyFailure(str, result.getErrorMessage(), true, iCallback);
        }
    }

    @Override // cerence.ark.assistant.service.IAssistant
    public void queryDeviceStatus(String str, final IResultListener iResultListener) {
        Framework framework = this.mFramework;
        final String str2 = "queryDeviceStatus";
        if (framework == null) {
            notifyFailure("queryDeviceStatus", ErrorMessage.FRAMEWORK_NULL, iResultListener);
        } else {
            framework.queryDeviceStatus(str, new IResultCallback() { // from class: cerence.ark.assistant.service.AssistantImpl$$ExternalSyntheticLambda6
                @Override // cerence.ark.assistant.common.callback.IResultCallback
                public final void onResult(Result result) {
                    AssistantImpl.lambda$queryDeviceStatus$11(str2, iResultListener, result);
                }
            });
        }
    }

    static /* synthetic */ void lambda$queryDeviceStatus$11(String str, IResultListener iResultListener, Result result) {
        if (result.isSuccess()) {
            notifySuccess(str, (String) result.getData(), iResultListener);
        } else {
            notifyFailure(str, result.getErrorMessage(), iResultListener);
        }
    }

    @Override // cerence.ark.assistant.service.IAssistant
    public AssistantState getAssistantState() {
        Framework framework = this.mFramework;
        if (framework == null) {
            Logger.error(TAG, buildErrorMessage("getAssistantState", ErrorMessage.FRAMEWORK_NULL));
            return AssistantState.UNKNOWN;
        }
        return framework.getAssistantState();
    }

    @Override // cerence.ark.assistant.service.IAssistant
    public boolean requestTrimMemory() {
        Framework framework = this.mFramework;
        if (framework == null) {
            Logger.error(TAG, buildErrorMessage("requestTrimMemory", ErrorMessage.FRAMEWORK_NULL));
            return false;
        }
        return framework.requestTrimMemory();
    }

    @Override // cerence.ark.assistant.service.IAssistant
    public boolean setUserDataRetentionEnabled(boolean z) {
        Framework framework = this.mFramework;
        if (framework == null) {
            Logger.error(TAG, buildErrorMessage("setUserDataRetentionEnabled", ErrorMessage.FRAMEWORK_NULL));
            return false;
        }
        return framework.setUserDataRetentionEnabled(z);
    }

    @Override // cerence.ark.assistant.service.IAssistant
    public boolean overrideConfiguration(String str, String str2) {
        Framework framework = this.mFramework;
        if (framework == null) {
            Logger.error(TAG, buildErrorMessage("overrideConfiguration", ErrorMessage.FRAMEWORK_NULL));
            return false;
        }
        return framework.overrideConfiguration(str, str2);
    }

    private static void notifySuccess(String str, ICallback iCallback) {
        if (iCallback != null) {
            try {
                iCallback.onSuccess();
            } catch (Exception e) {
                Logger.error(TAG, String.format("%s: callback onSuccess: exception", str), e);
            }
        }
    }

    private static void notifySuccess(String str, String str2, IResultListener iResultListener) {
        if (iResultListener != null) {
            try {
                iResultListener.onResult(str2);
            } catch (Exception e) {
                Logger.error(TAG, String.format("%s: callback onSuccess: exception", str), e);
            }
        }
    }

    private static void notifyFailure(String str, String str2, boolean z, ICallback iCallback) {
        notifyFailure(str, str2, null, z, iCallback);
    }

    private static void notifyFailure(String str, String str2, Exception exc, boolean z, ICallback iCallback) {
        if (z) {
            if (exc == null) {
                Logger.error(TAG, buildErrorMessage(str, str2));
            } else {
                Logger.error(TAG, buildErrorMessage(str, str2), exc);
            }
        }
        if (iCallback != null) {
            try {
                iCallback.onFailure(str2);
            } catch (Exception e) {
                Logger.error(TAG, String.format("%s: callback onFailure: exception", str), e);
            }
        }
    }

    private static void notifyFailure(String str, String str2, IResultListener iResultListener) {
        Logger.error(TAG, buildErrorMessage(str, str2));
        if (iResultListener != null) {
            try {
                iResultListener.onFailure(str2);
            } catch (Exception e) {
                Logger.error(TAG, String.format("%s: callback onFailure: exception", str), e);
            }
        }
    }

    private static String buildErrorMessage(String str, String str2) {
        return String.format("%s: %s", str, str2);
    }
}
