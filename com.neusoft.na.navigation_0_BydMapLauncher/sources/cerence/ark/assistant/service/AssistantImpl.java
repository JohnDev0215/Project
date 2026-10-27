package cerence.ark.assistant.service;

import android.media.AudioAttributes;
import android.text.TextUtils;
import cerence.ark.assistant.api.prompter.PrompterInstance;
import cerence.ark.assistant.api.prompter.TtsVoice;
import cerence.ark.assistant.common.Logger;
import cerence.ark.assistant.common.callback.IResultCallback;
import cerence.ark.assistant.common.callback.Result;
import cerence.ark.assistant.dm.data.RecognitionErrorCode;
import cerence.ark.assistant.framework.AssistantState;
import cerence.ark.assistant.framework.ErrorListener;
import cerence.ark.assistant.framework.Framework;
import cerence.ark.assistant.framework.FrameworkMessageListener;
import cerence.ark.assistant.framework.data.AssistantConfiguration;
import cerence.ark.assistant.framework.data.AssistantContextType;
import cerence.ark.assistant.framework.data.Config;
import cerence.ark.assistant.framework.data.ErrorCode;
import cerence.ark.assistant.framework.data.ErrorInfo;
import cerence.ark.assistant.framework.data.LogConsumer;
import cerence.ark.assistant.framework.data.RppLocationType;
import cerence.ark.assistant.framework.data.VoiceConfig;
import cerence.ark.assistant.framework.data.wuw.WuwConfig;
import cerence.ark.assistant.framework.impl.ArkFramework;
import cerence.ark.assistant.framework.impl.updater.IDataUpdater;
import cerence.ark.assistant.library.ErrorMessage;
import cerence.ark.assistant.library.JsonKey;
import cerence.ark.assistant.library.Utils;
import cerence.ark.assistant.library.ipctransaction.IAssemblerListener;
import cerence.ark.assistant.library.ipctransaction.ISplitter;
import cerence.ark.assistant.library.ipctransaction.ipcstring.StringSplitter;
import cerence.ark.assistant.service.AssistantImpl;
import cerence.ark.assistant.service.IAssistant;
import cerence.ark.assistant.service.SpeechService;
import com.bydux.api.common.tools.util.ListUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public final class AssistantImpl extends IAssistant.Stub {
    private static final String DOMAIN_REQUEST_WILDCARD_SYMBOL = "*";
    private static final String HMI_FLAG = "HMI";
    private static final String TAG = "AssistantImpl";
    private FrameworkMessageListener mDialogMessageListener;
    private IDictationManagerListener mDictationManagerListener;
    private Framework mFramework;
    private IHmiListener mHmiListener;
    private final SpeechService.ILifecycle mLifecycle;
    private INotificationListener mNotificationListener;
    private IRequestListener mRequestListener;
    private SpeechSynthesisCallback mSpeechSynthesisCallback;
    private IUnhandledRequestListener mUnhandledRequestListener;
    private final Map<String, PrompterServiceImpl> mPrompterMap = new ConcurrentHashMap(16);
    private final ConcurrentHashMap<String, ConcurrentHashMap<String, ClientHolder>> mDomainMap = new ConcurrentHashMap<>(16);

    public AssistantImpl(SpeechService.ILifecycle iLifecycle) {
        this.mLifecycle = iLifecycle;
    }

    /* JADX INFO: renamed from: cerence.ark.assistant.service.AssistantImpl$1, reason: invalid class name */
    class AnonymousClass1 implements FrameworkMessageListener {
        AnonymousClass1() {
        }

        @Override // cerence.ark.assistant.framework.FrameworkMessageListener
        public void onRequestReceived(final String str, final String str2) {
            try {
                JSONObject jSONObject = new JSONObject(str2);
                String strOptString = jSONObject.optString(JsonKey.DOMAIN_NAME);
                long jOptLong = jSONObject.optLong("id", -1L);
                String strOptString2 = jSONObject.optString("name");
                Logger.debug(AssistantImpl.TAG, Utils.format("onRequestReceived: activeZone = %s, jsonData = %s", str, str2));
                Logger.info(AssistantImpl.TAG, Utils.format("onRequestReceived: activeZone = %s, %s", str, Utils.buildMessageLog(jOptLong, strOptString, strOptString2)));
                if (TextUtils.isEmpty(strOptString)) {
                    Logger.error(AssistantImpl.TAG, "onRequestReceived: domain name is null");
                    return;
                }
                if (TextUtils.isEmpty(strOptString2)) {
                    Logger.error(AssistantImpl.TAG, "onRequestReceived: requestName is null");
                    return;
                }
                if ("HMI".equals(strOptString)) {
                    if (AssistantImpl.this.mHmiListener == null) {
                        Logger.error(AssistantImpl.TAG, "onRequestReceived: hmi listener is null");
                        return;
                    } else {
                        new StringSplitter().split(str2, new ISplitter.SplitterListener() { // from class: cerence.ark.assistant.service.AssistantImpl$1$$ExternalSyntheticLambda1
                            @Override // cerence.ark.assistant.library.ipctransaction.ISplitter.SplitterListener
                            public final void onSplit(Object obj, String str3, int i, int i2) {
                                this.f$0.m88x765cde1b((String) obj, str3, i, i2);
                            }
                        });
                        return;
                    }
                }
                final ClientHolder clientHolder = AssistantImpl.this.getClientHolder(strOptString, strOptString2);
                if (clientHolder == null) {
                    AssistantImpl.this.unHandledRequest(str2);
                } else {
                    new StringSplitter().split(str2, new ISplitter.SplitterListener() { // from class: cerence.ark.assistant.service.AssistantImpl$1$$ExternalSyntheticLambda2
                        @Override // cerence.ark.assistant.library.ipctransaction.ISplitter.SplitterListener
                        public final void onSplit(Object obj, String str3, int i, int i2) {
                            this.f$0.m89xebd7045c(clientHolder, str, str2, (String) obj, str3, i, i2);
                        }
                    });
                }
            } catch (Exception e) {
                Logger.error(AssistantImpl.TAG, "onRequestReceived: parse json exception", e);
            }
        }

        /* JADX INFO: renamed from: lambda$onRequestReceived$0$cerence-ark-assistant-service-AssistantImpl$1, reason: not valid java name */
        /* synthetic */ void m88x765cde1b(String str, String str2, int i, int i2) {
            try {
                AssistantImpl.this.mHmiListener.onRequest(str, str2, i, i2);
            } catch (Exception e) {
                Logger.error(AssistantImpl.TAG, "onRequestReceived: exception", e);
            }
        }

        /* JADX INFO: renamed from: lambda$onRequestReceived$1$cerence-ark-assistant-service-AssistantImpl$1, reason: not valid java name */
        /* synthetic */ void m89xebd7045c(ClientHolder clientHolder, String str, String str2, String str3, String str4, int i, int i2) {
            try {
                clientHolder.getRequestListener().onRequestReceived(str, str3, str4, i, i2);
            } catch (Exception e) {
                Logger.error(AssistantImpl.TAG, "onRequestReceived: exception", e);
                if (clientHolder.getRequestListener() != AssistantImpl.this.mRequestListener) {
                    Logger.error(AssistantImpl.TAG, Utils.format("client [%s] disconnected, send to assistant client", clientHolder.getClientUid()));
                    AssistantImpl.this.unHandledRequest(str2);
                }
            }
        }

        @Override // cerence.ark.assistant.framework.FrameworkMessageListener
        public void onNotificationReceived(String str) {
            if (AssistantImpl.this.mNotificationListener == null) {
                Logger.error(AssistantImpl.TAG, "onNotificationReceived: message listener is null");
            } else {
                new StringSplitter().split(str, new ISplitter.SplitterListener() { // from class: cerence.ark.assistant.service.AssistantImpl$1$$ExternalSyntheticLambda0
                    @Override // cerence.ark.assistant.library.ipctransaction.ISplitter.SplitterListener
                    public final void onSplit(Object obj, String str2, int i, int i2) {
                        this.f$0.m87xe1640671((String) obj, str2, i, i2);
                    }
                });
            }
        }

        /* JADX INFO: renamed from: lambda$onNotificationReceived$2$cerence-ark-assistant-service-AssistantImpl$1, reason: not valid java name */
        /* synthetic */ void m87xe1640671(String str, String str2, int i, int i2) {
            try {
                AssistantImpl.this.mNotificationListener.onNotificationReceived(str, str2, i, i2);
            } catch (Exception e) {
                Logger.error(AssistantImpl.TAG, "onNotificationReceived: exception", e);
            }
        }

        @Override // cerence.ark.assistant.framework.FrameworkMessageListener
        public boolean onAbortRequest(String str) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                String strOptString = jSONObject.optString(JsonKey.DOMAIN_NAME);
                long jOptLong = jSONObject.optLong("id", -1L);
                String strOptString2 = jSONObject.optString("name");
                Logger.info(AssistantImpl.TAG, "onAbortRequest: " + Utils.buildMessageLog(jOptLong, strOptString, strOptString2));
                if (TextUtils.isEmpty(strOptString)) {
                    Logger.error(AssistantImpl.TAG, "onAbortRequest: domain name is null");
                    return true;
                }
                if ("HMI".equals(strOptString)) {
                    Logger.error(AssistantImpl.TAG, "onAbortRequest: hmi domain is null");
                    return true;
                }
                if (TextUtils.isEmpty(strOptString2)) {
                    Logger.error(AssistantImpl.TAG, "onAbortRequest: requestName is null");
                    return true;
                }
                ClientHolder clientHolder = AssistantImpl.this.getClientHolder(strOptString, strOptString2);
                if (clientHolder == null) {
                    Logger.error(AssistantImpl.TAG, "onAbortRequest: no client was found to process the request");
                    return true;
                }
                return clientHolder.getRequestListener().onAbortRequest(str);
            } catch (Exception e) {
                Logger.error(AssistantImpl.TAG, "onAbortRequest: exception", e);
                return true;
            }
        }

        @Override // cerence.ark.assistant.framework.FrameworkMessageListener
        public void onPromptAudio(String str, byte[] bArr, int i) {
            SpeechSynthesisCallback speechSynthesisCallback = AssistantImpl.this.mSpeechSynthesisCallback;
            if (speechSynthesisCallback == null) {
                return;
            }
            try {
                speechSynthesisCallback.onAudio(str, bArr, i);
            } catch (Exception e) {
                Logger.error(AssistantImpl.TAG, "onPromptAudio: exception", e);
            }
        }

        @Override // cerence.ark.assistant.framework.FrameworkMessageListener
        public void onPromptError(String str, String str2, int i) {
            SpeechSynthesisCallback speechSynthesisCallback = AssistantImpl.this.mSpeechSynthesisCallback;
            if (speechSynthesisCallback == null) {
                return;
            }
            try {
                speechSynthesisCallback.onError(str, str2, i);
            } catch (Exception e) {
                Logger.error(AssistantImpl.TAG, "onEndOfStream: exception", e);
            }
        }

        @Override // cerence.ark.assistant.framework.FrameworkMessageListener
        public void onPromptEvent(String str, String str2, int i) {
            SpeechSynthesisCallback speechSynthesisCallback = AssistantImpl.this.mSpeechSynthesisCallback;
            if (speechSynthesisCallback == null) {
                return;
            }
            try {
                speechSynthesisCallback.onEvent(str, str2, i);
            } catch (Exception e) {
                Logger.error(AssistantImpl.TAG, "onAbortPrompt: exception", e);
            }
        }

        @Override // cerence.ark.assistant.framework.FrameworkMessageListener
        public void onBeginningOfSpeech() {
            try {
                AssistantImpl.this.mDictationManagerListener.onBeginningOfSpeech();
            } catch (Exception e) {
                Logger.error(AssistantImpl.TAG, "onBeginningOfSpeech: exception", e);
            }
        }

        @Override // cerence.ark.assistant.framework.FrameworkMessageListener
        public void onEndOfSpeech() {
            try {
                AssistantImpl.this.mDictationManagerListener.onEndOfSpeech();
            } catch (Exception e) {
                Logger.error(AssistantImpl.TAG, "onEndOfSpeech: exception", e);
            }
        }

        @Override // cerence.ark.assistant.framework.FrameworkMessageListener
        public void onError(RecognitionErrorCode recognitionErrorCode) {
            try {
                AssistantImpl.this.mDictationManagerListener.onError(recognitionErrorCode.ordinal());
            } catch (Exception e) {
                Logger.error(AssistantImpl.TAG, "onError: exception", e);
            }
        }

        @Override // cerence.ark.assistant.framework.FrameworkMessageListener
        public void onReadyForSpeech() {
            try {
                AssistantImpl.this.mDictationManagerListener.onReadyForSpeech();
            } catch (Exception e) {
                Logger.error(AssistantImpl.TAG, "onReadyForSpeech: exception", e);
            }
        }

        @Override // cerence.ark.assistant.framework.FrameworkMessageListener
        public void onRecognizedSpeech(String str, boolean z) {
            try {
                AssistantImpl.this.mDictationManagerListener.onRecognizedSpeech(str, z);
            } catch (Exception e) {
                Logger.error(AssistantImpl.TAG, "onRecognizedSpeech: exception", e);
            }
        }

        @Override // cerence.ark.assistant.framework.FrameworkMessageListener
        public void onRmsChanged(int i) {
            try {
                AssistantImpl.this.mDictationManagerListener.onRmsChanged(i);
            } catch (Exception e) {
                Logger.error(AssistantImpl.TAG, "onRmsChanged: exception", e);
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
            ConcurrentHashMap<String, ClientHolder> concurrentHashMap2 = this.mDomainMap.get("*");
            if (concurrentHashMap2 == null) {
                return null;
            }
            ClientHolder clientHolder = concurrentHashMap2.get(str2);
            return clientHolder == null ? concurrentHashMap2.get("*") : clientHolder;
        }
        ClientHolder clientHolder2 = concurrentHashMap.get(str2);
        return clientHolder2 == null ? concurrentHashMap.get("*") : clientHolder2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void unHandledRequest(String str) {
        final IUnhandledRequestListener iUnhandledRequestListener = this.mUnhandledRequestListener;
        if (iUnhandledRequestListener == null) {
            Logger.error(TAG, "unHandledRequest: unhandledRequestListener is null");
        } else {
            new StringSplitter().split(str, new ISplitter.SplitterListener() { // from class: cerence.ark.assistant.service.AssistantImpl$$ExternalSyntheticLambda6
                @Override // cerence.ark.assistant.library.ipctransaction.ISplitter.SplitterListener
                public final void onSplit(Object obj, String str2, int i, int i2) {
                    AssistantImpl.lambda$unHandledRequest$0(iUnhandledRequestListener, (String) obj, str2, i, i2);
                }
            });
        }
    }

    static /* synthetic */ void lambda$unHandledRequest$0(IUnhandledRequestListener iUnhandledRequestListener, String str, String str2, int i, int i2) {
        try {
            iUnhandledRequestListener.onUnhandledRequest(str, str2, i, i2);
        } catch (Exception e) {
            Logger.error(TAG, "unHandledRequest: exception", e);
        }
    }

    @Override // cerence.ark.assistant.service.IAssistant
    public void initialize(Config config, IErrorListener iErrorListener, TokenProviderCallback tokenProviderCallback, final ICallback iCallback) {
        if (config == null) {
            Logger.error(TAG, Utils.format2Argv("initialize", ErrorMessage.CONFIG_NULL));
            notifyError("initialize", ErrorCode.PARAMETER_INVALID, iCallback);
            return;
        }
        if (this.mFramework == null) {
            this.mFramework = new ArkFramework();
        }
        this.mFramework.setErrorListener(new ErrorListenerImpl(iErrorListener));
        SpeechService.ILifecycle iLifecycle = this.mLifecycle;
        if (iLifecycle != null) {
            iLifecycle.onInitialize(config);
        }
        this.mDialogMessageListener = createMessageListener();
        this.mFramework.initialize(config, this.mDialogMessageListener, tokenProviderCallback == null ? null : new DmTokenProviderImpl(tokenProviderCallback), new IResultCallback() { // from class: cerence.ark.assistant.service.AssistantImpl$$ExternalSyntheticLambda10
            @Override // cerence.ark.assistant.common.callback.IResultCallback
            public final void onResult(Result result) {
                AssistantImpl.lambda$initialize$1(iCallback, result);
            }
        });
    }

    static /* synthetic */ void lambda$initialize$1(ICallback iCallback, Result result) {
        if (result.isSuccess()) {
            notifySuccess("initialize", iCallback);
        } else {
            notifyError("initialize", (ErrorCode) result.getErrorCode(), iCallback);
        }
    }

    @Override // cerence.ark.assistant.service.IAssistant
    public void registerRequestListener(String str, Map map, IRequestListener iRequestListener, ICallback iCallback) {
        char c = 0;
        if (TextUtils.isEmpty(str)) {
            Logger.error(TAG, Utils.format("%s: client uid is empty", "registerRequestListener"));
            notifyError("registerRequestListener", ErrorCode.UNKNOWN, iCallback);
            return;
        }
        if (str.equals(Utils.getContext().getPackageName())) {
            this.mRequestListener = iRequestListener;
        }
        if (iRequestListener == null) {
            Logger.error(TAG, Utils.format("%s: requestListener is null", "registerRequestListener"));
            notifyError("registerRequestListener", ErrorCode.UNKNOWN, iCallback);
            return;
        }
        unregisterRequestListener(str);
        int i = 16;
        HashMap map2 = new HashMap(16);
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap(16);
        int i2 = 2;
        if (map != null && !map.isEmpty()) {
            for (Map.Entry entry : map.entrySet()) {
                List<String> list = (List) entry.getValue();
                String str2 = (String) entry.getKey();
                if (TextUtils.isEmpty(str2) || list == null || list.isEmpty()) {
                    Logger.warn(TAG, Utils.format("%s: domain or request list is null", "registerRequestListener"));
                } else {
                    String str3 = TAG;
                    Object[] objArr = new Object[i2];
                    objArr[c] = str2;
                    objArr[1] = TextUtils.join(ListUtils.DEFAULT_JOIN_SEPARATOR, list);
                    Logger.info(str3, Utils.format("registerRequests: %s [%s]", objArr));
                    ConcurrentHashMap<String, ClientHolder> concurrentHashMap2 = this.mDomainMap.get(str2);
                    ConcurrentHashMap concurrentHashMap3 = new ConcurrentHashMap(i);
                    for (String str4 : list) {
                        concurrentHashMap3.put(str4, new ClientHolder(str, iRequestListener));
                        boolean z = this.mDomainMap.containsKey("*") && "*".equals(str2);
                        boolean z2 = concurrentHashMap2 != null && ((concurrentHashMap2.containsKey("*") && "*".equals(str4)) || concurrentHashMap2.containsKey(str4));
                        if (z || z2) {
                            addRequestName(map2, str2, str4);
                        }
                    }
                    concurrentHashMap.put(str2, concurrentHashMap3);
                }
                c = 0;
                i = 16;
                i2 = 2;
            }
        }
        if (map2.isEmpty()) {
            for (Map.Entry entry2 : concurrentHashMap.entrySet()) {
                String str5 = (String) entry2.getKey();
                ConcurrentHashMap<String, ClientHolder> concurrentHashMap4 = (ConcurrentHashMap) entry2.getValue();
                ConcurrentHashMap<String, ClientHolder> concurrentHashMap5 = this.mDomainMap.get(str5);
                if (concurrentHashMap5 == null) {
                    this.mDomainMap.put(str5, concurrentHashMap4);
                } else {
                    for (Map.Entry<String, ClientHolder> entry3 : concurrentHashMap4.entrySet()) {
                        concurrentHashMap5.put(entry3.getKey(), entry3.getValue());
                    }
                }
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
        Logger.error(TAG, Utils.format("%s: the request name has already been registered by other application: %s", str, sb));
        notifyError("registerRequestListener", ErrorCode.UNKNOWN, iCallback);
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
            Logger.error(TAG, Utils.format("%s: client uid is empty", "unregisterRequestListener"));
            return;
        }
        for (Map.Entry<String, ConcurrentHashMap<String, ClientHolder>> entry : this.mDomainMap.entrySet()) {
            ConcurrentHashMap<String, ClientHolder> value = entry.getValue();
            if (value != null) {
                for (Map.Entry<String, ClientHolder> entry2 : value.entrySet()) {
                    if (entry2.getValue() == null) {
                        Logger.warn(TAG, Utils.format("%s: client holder is null, requestName = %s", "unregisterRequestListener", entry2.getKey()));
                        value.remove(entry2.getKey());
                    } else if (str.equals(entry2.getValue().getClientUid())) {
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
    public void startVoiceAssistant(AssistantConfiguration assistantConfiguration, INotificationListener iNotificationListener, IHmiListener iHmiListener, final ICallback iCallback) {
        Framework framework = this.mFramework;
        if (framework == null) {
            Logger.error(TAG, Utils.format2Argv("startVoiceAssistant", ErrorMessage.FRAMEWORK_NULL));
            notifyError("startVoiceAssistant", ErrorCode.UNKNOWN, iCallback);
        } else {
            this.mNotificationListener = iNotificationListener;
            this.mHmiListener = iHmiListener;
            framework.startVoiceAssistant(assistantConfiguration, new IResultCallback() { // from class: cerence.ark.assistant.service.AssistantImpl$$ExternalSyntheticLambda5
                @Override // cerence.ark.assistant.common.callback.IResultCallback
                public final void onResult(Result result) {
                    AssistantImpl.lambda$startVoiceAssistant$2(iCallback, result);
                }
            });
        }
    }

    static /* synthetic */ void lambda$startVoiceAssistant$2(ICallback iCallback, Result result) {
        if (result.isSuccess()) {
            notifySuccess("startVoiceAssistant", iCallback);
        } else {
            notifyError("startVoiceAssistant", (ErrorCode) result.getErrorCode(), iCallback);
        }
    }

    @Override // cerence.ark.assistant.service.IAssistant
    public void stopVoiceAssistant(final ICallback iCallback) {
        Framework framework = this.mFramework;
        if (framework == null) {
            Logger.error(TAG, Utils.format2Argv("stopVoiceAssistant", ErrorMessage.FRAMEWORK_NULL));
            notifyError("stopVoiceAssistant", ErrorCode.UNKNOWN, iCallback);
        } else {
            framework.stopVoiceAssistant(new IResultCallback() { // from class: cerence.ark.assistant.service.AssistantImpl$$ExternalSyntheticLambda7
                @Override // cerence.ark.assistant.common.callback.IResultCallback
                public final void onResult(Result result) {
                    AssistantImpl.lambda$stopVoiceAssistant$3(iCallback, result);
                }
            });
        }
    }

    static /* synthetic */ void lambda$stopVoiceAssistant$3(ICallback iCallback, Result result) {
        if (result.isSuccess()) {
            notifySuccess("stopVoiceAssistant", iCallback);
        } else {
            notifyError("stopVoiceAssistant", (ErrorCode) result.getErrorCode(), iCallback);
        }
    }

    @Override // cerence.ark.assistant.service.IAssistant
    public void destroy(final ICallback iCallback) {
        Framework framework = this.mFramework;
        if (framework == null) {
            Logger.error(TAG, Utils.format2Argv("destroy", ErrorMessage.FRAMEWORK_NULL));
            notifyError("destroy", ErrorCode.UNKNOWN, iCallback);
        } else {
            framework.destroy(new IResultCallback() { // from class: cerence.ark.assistant.service.AssistantImpl$$ExternalSyntheticLambda1
                @Override // cerence.ark.assistant.common.callback.IResultCallback
                public final void onResult(Result result) {
                    this.f$0.m86lambda$destroy$4$cerencearkassistantserviceAssistantImpl(iCallback, result);
                }
            });
        }
    }

    /* JADX INFO: renamed from: lambda$destroy$4$cerence-ark-assistant-service-AssistantImpl, reason: not valid java name */
    /* synthetic */ void m86lambda$destroy$4$cerencearkassistantserviceAssistantImpl(ICallback iCallback, Result result) {
        if (result.isSuccess()) {
            this.mDomainMap.clear();
            this.mPrompterMap.clear();
            this.mFramework = null;
            this.mNotificationListener = null;
            this.mHmiListener = null;
            this.mDialogMessageListener = null;
            Utils.cleanAssemblerMap();
            SpeechService.ILifecycle iLifecycle = this.mLifecycle;
            if (iLifecycle != null) {
                iLifecycle.onDestroy();
            }
            notifySuccess("destroy", iCallback);
            return;
        }
        notifyError("destroy", (ErrorCode) result.getErrorCode(), iCallback);
    }

    /* JADX INFO: renamed from: cerence.ark.assistant.service.AssistantImpl$2, reason: invalid class name */
    class AnonymousClass2 implements IAssemblerListener<String> {
        final /* synthetic */ ICallback val$callback;

        AnonymousClass2(ICallback iCallback) {
            this.val$callback = iCallback;
        }

        @Override // cerence.ark.assistant.library.ipctransaction.IAssemblerListener
        public void onFinal(String str) {
            Framework framework = AssistantImpl.this.mFramework;
            if (framework == null) {
                Logger.error(AssistantImpl.TAG, Utils.format2Argv("sendAppEvent", ErrorMessage.FRAMEWORK_NULL));
                AssistantImpl.notifyError("sendAppEvent", ErrorCode.UNKNOWN, this.val$callback);
            } else {
                final ICallback iCallback = this.val$callback;
                framework.sendAppEvent(str, new IResultCallback() { // from class: cerence.ark.assistant.service.AssistantImpl$2$$ExternalSyntheticLambda0
                    @Override // cerence.ark.assistant.common.callback.IResultCallback
                    public final void onResult(Result result) {
                        AssistantImpl.AnonymousClass2.lambda$onFinal$0(iCallback, result);
                    }
                });
            }
        }

        static /* synthetic */ void lambda$onFinal$0(ICallback iCallback, Result result) {
            if (result.isSuccess()) {
                AssistantImpl.notifySuccess("sendAppEvent", iCallback);
            } else {
                AssistantImpl.notifyError("sendAppEvent", (ErrorCode) result.getErrorCode(), iCallback);
            }
        }

        @Override // cerence.ark.assistant.library.ipctransaction.IAssemblerListener
        public void onError() {
            AssistantImpl.notifyError("sendAppEvent", ErrorCode.UNKNOWN, this.val$callback);
        }
    }

    @Override // cerence.ark.assistant.service.IAssistant
    public void sendAppEvent(String str, String str2, int i, int i2, ICallback iCallback) {
        Utils.processStringAssembler(str, str2, i, i2, new AnonymousClass2(iCallback));
    }

    /* JADX INFO: renamed from: cerence.ark.assistant.service.AssistantImpl$3, reason: invalid class name */
    class AnonymousClass3 implements IAssemblerListener<String> {
        final /* synthetic */ ICallback val$callback;

        AnonymousClass3(ICallback iCallback) {
            this.val$callback = iCallback;
        }

        @Override // cerence.ark.assistant.library.ipctransaction.IAssemblerListener
        public void onFinal(String str) {
            Framework framework = AssistantImpl.this.mFramework;
            if (framework == null) {
                Logger.error(AssistantImpl.TAG, Utils.format2Argv("sendResponse", ErrorMessage.FRAMEWORK_NULL));
                AssistantImpl.notifyError("sendResponse", ErrorCode.UNKNOWN, this.val$callback);
            } else {
                final ICallback iCallback = this.val$callback;
                framework.sendResponse(str, new IResultCallback() { // from class: cerence.ark.assistant.service.AssistantImpl$3$$ExternalSyntheticLambda0
                    @Override // cerence.ark.assistant.common.callback.IResultCallback
                    public final void onResult(Result result) {
                        AssistantImpl.AnonymousClass3.lambda$onFinal$0(iCallback, result);
                    }
                });
            }
        }

        static /* synthetic */ void lambda$onFinal$0(ICallback iCallback, Result result) {
            if (result.isSuccess()) {
                AssistantImpl.notifySuccess("sendResponse", iCallback);
            } else {
                AssistantImpl.notifyError("sendResponse", (ErrorCode) result.getErrorCode(), iCallback);
            }
        }

        @Override // cerence.ark.assistant.library.ipctransaction.IAssemblerListener
        public void onError() {
            AssistantImpl.notifyError("sendResponse", ErrorCode.UNKNOWN, this.val$callback);
        }
    }

    @Override // cerence.ark.assistant.service.IAssistant
    public void sendResponse(String str, String str2, int i, int i2, ICallback iCallback) {
        Utils.processStringAssembler(str, str2, i, i2, new AnonymousClass3(iCallback));
    }

    @Override // cerence.ark.assistant.service.IAssistant
    public RemoteResult setVoiceConfig(VoiceConfig voiceConfig) {
        Framework framework = this.mFramework;
        if (framework == null) {
            Logger.error(TAG, Utils.format2Argv("setVoiceConfig", ErrorMessage.FRAMEWORK_NULL));
            return Utils.buildErrorRemoteResult(ErrorCode.UNKNOWN);
        }
        return Utils.convertToRemoteResult(framework.setVoiceConfig(voiceConfig));
    }

    @Override // cerence.ark.assistant.service.IAssistant
    public String getVersion() {
        Framework framework = this.mFramework;
        if (framework == null) {
            Logger.error(TAG, Utils.format2Argv("getVersion", ErrorMessage.FRAMEWORK_NULL));
            return null;
        }
        return framework.getVersion();
    }

    @Override // cerence.ark.assistant.service.IAssistant
    public List<String> getAvailableAsrLanguages() {
        Framework framework = this.mFramework;
        if (framework == null) {
            Logger.error(TAG, Utils.format2Argv("getAvailableAsrLanguages", ErrorMessage.FRAMEWORK_NULL));
            return new ArrayList();
        }
        return framework.getAvailableAsrLanguages();
    }

    @Override // cerence.ark.assistant.service.IAssistant
    public List<String> getAvailableTtsLanguages() {
        Framework framework = this.mFramework;
        if (framework == null) {
            Logger.error(TAG, Utils.format2Argv("getAvailableTtsLanguages", ErrorMessage.FRAMEWORK_NULL));
            return new ArrayList();
        }
        return framework.getAvailableTtsLanguages();
    }

    @Override // cerence.ark.assistant.service.IAssistant
    public List<TtsVoice> getAvailableTtsVoices(String str) {
        Framework framework = this.mFramework;
        if (framework == null) {
            Logger.error(TAG, Utils.format2Argv("getAvailableTtsVoices", ErrorMessage.FRAMEWORK_NULL));
            return new ArrayList();
        }
        return framework.getAvailableTtsVoices(str);
    }

    @Override // cerence.ark.assistant.service.IAssistant
    public List<PrompterInstance> getDialogPrompterInstances() {
        Framework framework = this.mFramework;
        if (framework == null) {
            Logger.error(TAG, Utils.format2Argv("getDialogPrompterInstances", ErrorMessage.FRAMEWORK_NULL));
            return new ArrayList();
        }
        return framework.getDialogPrompterInstances();
    }

    @Override // cerence.ark.assistant.service.IAssistant
    public List<PrompterInstance> getAvailablePrompterInstances() {
        Framework framework = this.mFramework;
        if (framework == null) {
            Logger.error(TAG, Utils.format2Argv("getAvailablePrompterInstances", ErrorMessage.FRAMEWORK_NULL));
            return new ArrayList();
        }
        return framework.getAvailablePrompterInstances();
    }

    @Override // cerence.ark.assistant.service.IAssistant
    public PrompterInstance getTtsPrompterInstance() {
        Framework framework = this.mFramework;
        if (framework == null) {
            Logger.error(TAG, Utils.format2Argv("getTtsPrompterInstance", ErrorMessage.FRAMEWORK_NULL));
            return null;
        }
        return framework.getTtsPrompterInstance();
    }

    @Override // cerence.ark.assistant.service.IAssistant
    public IPrompterService createPrompter(PrompterInstance prompterInstance, AudioAttributes audioAttributes) {
        PrompterServiceImpl prompterServiceImpl = this.mPrompterMap.get(prompterInstance.getName());
        if (prompterServiceImpl != null) {
            Logger.warn(TAG, "The prompter already exist: prompterInstance = " + prompterInstance);
            return prompterServiceImpl;
        }
        PrompterServiceImpl prompterServiceImpl2 = new PrompterServiceImpl(this.mFramework);
        if (!prompterServiceImpl2.createPrompter(prompterInstance, audioAttributes)) {
            return null;
        }
        this.mPrompterMap.put(prompterInstance.getName(), prompterServiceImpl2);
        return prompterServiceImpl2;
    }

    @Override // cerence.ark.assistant.service.IAssistant
    public void setSpeechSynthesisCallback(SpeechSynthesisCallback speechSynthesisCallback) {
        this.mSpeechSynthesisCallback = speechSynthesisCallback;
    }

    @Override // cerence.ark.assistant.service.IAssistant
    public void destroyPrompter(IPrompterService iPrompterService) {
        if (iPrompterService instanceof PrompterServiceImpl) {
            PrompterServiceImpl prompterServiceImpl = (PrompterServiceImpl) iPrompterService;
            this.mPrompterMap.remove(prompterServiceImpl.getPrompterInstance().getName());
            prompterServiceImpl.destroyPrompter();
        }
    }

    /* JADX INFO: renamed from: cerence.ark.assistant.service.AssistantImpl$4, reason: invalid class name */
    class AnonymousClass4 implements IAssemblerListener<List<String>> {
        final /* synthetic */ ICallback val$callback;
        final /* synthetic */ String val$contentType;

        AnonymousClass4(ICallback iCallback, String str) {
            this.val$callback = iCallback;
            this.val$contentType = str;
        }

        @Override // cerence.ark.assistant.library.ipctransaction.IAssemblerListener
        public void onFinal(List<String> list) {
            Framework framework = AssistantImpl.this.mFramework;
            if (framework == null) {
                Logger.error(AssistantImpl.TAG, Utils.format2Argv("updateDynamicContent", ErrorMessage.FRAMEWORK_NULL));
                AssistantImpl.notifyError("updateDynamicContent", ErrorCode.UNKNOWN, this.val$callback);
            } else {
                String str = this.val$contentType;
                final ICallback iCallback = this.val$callback;
                framework.updateDynamicContent(str, list, new IResultCallback() { // from class: cerence.ark.assistant.service.AssistantImpl$4$$ExternalSyntheticLambda0
                    @Override // cerence.ark.assistant.common.callback.IResultCallback
                    public final void onResult(Result result) {
                        AssistantImpl.AnonymousClass4.lambda$onFinal$0(iCallback, result);
                    }
                });
            }
        }

        static /* synthetic */ void lambda$onFinal$0(ICallback iCallback, Result result) {
            if (result.isSuccess()) {
                AssistantImpl.notifySuccess("updateDynamicContent", iCallback);
            } else {
                AssistantImpl.notifyError("updateDynamicContent", (ErrorCode) result.getErrorCode(), iCallback);
            }
        }

        @Override // cerence.ark.assistant.library.ipctransaction.IAssemblerListener
        public void onError() {
            AssistantImpl.notifyError("updateDynamicContent", ErrorCode.UNKNOWN, this.val$callback);
        }
    }

    @Override // cerence.ark.assistant.service.IAssistant
    public void updateDynamicContent(String str, List<String> list, String str2, int i, int i2, ICallback iCallback) {
        Utils.processListAssembler(list, str2, i, i2, new AnonymousClass4(iCallback, str));
    }

    @Override // cerence.ark.assistant.service.IAssistant
    public void setWuwConfig(WuwConfig wuwConfig, final ICallback iCallback) {
        Framework framework = this.mFramework;
        if (framework == null) {
            Logger.error(TAG, Utils.format2Argv("setWuwConfig", ErrorMessage.FRAMEWORK_NULL));
            notifyError("setWuwConfig", ErrorCode.UNKNOWN, iCallback);
        } else {
            framework.setWuwConfig(wuwConfig, new IResultCallback() { // from class: cerence.ark.assistant.service.AssistantImpl$$ExternalSyntheticLambda0
                @Override // cerence.ark.assistant.common.callback.IResultCallback
                public final void onResult(Result result) {
                    AssistantImpl.lambda$setWuwConfig$5(iCallback, result);
                }
            });
        }
    }

    static /* synthetic */ void lambda$setWuwConfig$5(ICallback iCallback, Result result) {
        if (result.isSuccess()) {
            notifySuccess("setWuwConfig", iCallback);
        } else {
            notifyError("setWuwConfig", (ErrorCode) result.getErrorCode(), iCallback);
        }
    }

    /* JADX INFO: renamed from: cerence.ark.assistant.service.AssistantImpl$5, reason: invalid class name */
    class AnonymousClass5 implements IAssemblerListener<String> {
        final /* synthetic */ ICallback val$callback;
        final /* synthetic */ AssistantContextType val$contextType;

        AnonymousClass5(ICallback iCallback, AssistantContextType assistantContextType) {
            this.val$callback = iCallback;
            this.val$contextType = assistantContextType;
        }

        @Override // cerence.ark.assistant.library.ipctransaction.IAssemblerListener
        public void onFinal(String str) {
            Framework framework = AssistantImpl.this.mFramework;
            if (framework == null) {
                Logger.error(AssistantImpl.TAG, Utils.format2Argv("updateAssistantContext", ErrorMessage.FRAMEWORK_NULL));
                AssistantImpl.notifyError("updateAssistantContext", ErrorCode.UNKNOWN, this.val$callback);
            } else {
                AssistantContextType assistantContextType = this.val$contextType;
                final ICallback iCallback = this.val$callback;
                framework.updateAssistantContext(assistantContextType, str, new IResultCallback() { // from class: cerence.ark.assistant.service.AssistantImpl$5$$ExternalSyntheticLambda0
                    @Override // cerence.ark.assistant.common.callback.IResultCallback
                    public final void onResult(Result result) {
                        AssistantImpl.AnonymousClass5.lambda$onFinal$0(iCallback, result);
                    }
                });
            }
        }

        static /* synthetic */ void lambda$onFinal$0(ICallback iCallback, Result result) {
            if (result.isSuccess()) {
                AssistantImpl.notifySuccess("updateAssistantContext", iCallback);
            } else {
                AssistantImpl.notifyError("updateAssistantContext", (ErrorCode) result.getErrorCode(), iCallback);
            }
        }

        @Override // cerence.ark.assistant.library.ipctransaction.IAssemblerListener
        public void onError() {
            AssistantImpl.notifyError("updateAssistantContext", ErrorCode.UNKNOWN, this.val$callback);
        }
    }

    @Override // cerence.ark.assistant.service.IAssistant
    public void updateAssistantContext(AssistantContextType assistantContextType, String str, String str2, int i, int i2, ICallback iCallback) {
        Utils.processStringAssembler(str, str2, i, i2, new AnonymousClass5(iCallback, assistantContextType));
    }

    @Override // cerence.ark.assistant.service.IAssistant
    public AssistantState getAssistantState() {
        Framework framework = this.mFramework;
        if (framework == null) {
            Logger.error(TAG, Utils.format2Argv("getAssistantState", ErrorMessage.FRAMEWORK_NULL));
            return AssistantState.UNKNOWN;
        }
        return framework.getAssistantState();
    }

    @Override // cerence.ark.assistant.service.IAssistant
    public void clearUserData(final ICallback iCallback) {
        Framework framework = this.mFramework;
        if (framework == null) {
            Logger.error(TAG, Utils.format2Argv("clearUserData", ErrorMessage.FRAMEWORK_NULL));
            notifyError("clearUserData", ErrorCode.UNKNOWN, iCallback);
        } else {
            framework.clearUserData(new IResultCallback() { // from class: cerence.ark.assistant.service.AssistantImpl$$ExternalSyntheticLambda2
                @Override // cerence.ark.assistant.common.callback.IResultCallback
                public final void onResult(Result result) {
                    AssistantImpl.lambda$clearUserData$6(iCallback, result);
                }
            });
        }
    }

    static /* synthetic */ void lambda$clearUserData$6(ICallback iCallback, Result result) {
        if (result.isSuccess()) {
            notifySuccess("clearUserData", iCallback);
        } else {
            notifyError("clearUserData", (ErrorCode) result.getErrorCode(), iCallback);
        }
    }

    /* JADX INFO: renamed from: cerence.ark.assistant.service.AssistantImpl$6, reason: invalid class name */
    class AnonymousClass6 implements IAssemblerListener<List<String>> {
        final /* synthetic */ ICallback val$callback;

        AnonymousClass6(ICallback iCallback) {
            this.val$callback = iCallback;
        }

        @Override // cerence.ark.assistant.library.ipctransaction.IAssemblerListener
        public void onFinal(List<String> list) {
            Framework framework = AssistantImpl.this.mFramework;
            if (framework == null) {
                Logger.error(AssistantImpl.TAG, Utils.format2Argv("setDynamicWords", ErrorMessage.FRAMEWORK_NULL));
                AssistantImpl.notifyError("setDynamicWords", ErrorCode.UNKNOWN, this.val$callback);
            } else {
                final ICallback iCallback = this.val$callback;
                framework.setDynamicWords(list, new IResultCallback() { // from class: cerence.ark.assistant.service.AssistantImpl$6$$ExternalSyntheticLambda0
                    @Override // cerence.ark.assistant.common.callback.IResultCallback
                    public final void onResult(Result result) {
                        AssistantImpl.AnonymousClass6.lambda$onFinal$0(iCallback, result);
                    }
                });
            }
        }

        static /* synthetic */ void lambda$onFinal$0(ICallback iCallback, Result result) {
            if (result.isSuccess()) {
                AssistantImpl.notifySuccess("setDynamicWords", iCallback);
            } else {
                AssistantImpl.notifyError("setDynamicWords", (ErrorCode) result.getErrorCode(), iCallback);
            }
        }

        @Override // cerence.ark.assistant.library.ipctransaction.IAssemblerListener
        public void onError() {
            AssistantImpl.notifyError("setDynamicWords", ErrorCode.UNKNOWN, this.val$callback);
        }
    }

    @Override // cerence.ark.assistant.service.IAssistant
    public void setDynamicWords(List<String> list, String str, int i, int i2, ICallback iCallback) {
        Utils.processListAssembler(list, str, i, i2, new AnonymousClass6(iCallback));
    }

    @Override // cerence.ark.assistant.service.IAssistant
    public void updateLocation(String str, String str2, String str3, final ICallback iCallback) {
        Framework framework = this.mFramework;
        if (framework == null) {
            Logger.error(TAG, Utils.format2Argv("updateLocation", ErrorMessage.FRAMEWORK_NULL));
            notifyError("updateLocation", ErrorCode.UNKNOWN, iCallback);
        } else {
            framework.updateLocation(str, str2, str3, new IResultCallback() { // from class: cerence.ark.assistant.service.AssistantImpl$$ExternalSyntheticLambda8
                @Override // cerence.ark.assistant.common.callback.IResultCallback
                public final void onResult(Result result) {
                    AssistantImpl.lambda$updateLocation$7(iCallback, result);
                }
            });
        }
    }

    static /* synthetic */ void lambda$updateLocation$7(ICallback iCallback, Result result) {
        if (result.isSuccess()) {
            notifySuccess("updateLocation", iCallback);
        } else {
            notifyError("updateLocation", (ErrorCode) result.getErrorCode(), iCallback);
        }
    }

    @Override // cerence.ark.assistant.service.IAssistant
    public RemoteUpdater createUpdater(RemoteUpdateListener remoteUpdateListener) {
        Framework framework = this.mFramework;
        if (framework == null) {
            Logger.error(TAG, Utils.format2Argv("createUpdater", ErrorMessage.FRAMEWORK_NULL));
            return null;
        }
        IDataUpdater iDataUpdaterCreateUpdater = framework.createUpdater(new RemoteUpdateListenerProxy(remoteUpdateListener));
        if (iDataUpdaterCreateUpdater != null) {
            return new RemoteUpdaterImpl(iDataUpdaterCreateUpdater);
        }
        return null;
    }

    @Override // cerence.ark.assistant.service.IAssistant
    public RemoteResult destroyUpdater(RemoteUpdater remoteUpdater) {
        Framework framework = this.mFramework;
        if (framework == null) {
            Logger.error(TAG, Utils.format2Argv("destroyUpdater", ErrorMessage.FRAMEWORK_NULL));
            return Utils.buildErrorRemoteResult(ErrorCode.UNKNOWN);
        }
        if (!(remoteUpdater instanceof RemoteUpdaterImpl)) {
            Logger.error(TAG, Utils.format("%s: updater is not as RemoteUpdaterImpl", "destroyUpdater"));
            return Utils.buildErrorRemoteResult(ErrorCode.PARAMETER_INVALID);
        }
        return Utils.convertToRemoteResult(framework.destroyUpdater(((RemoteUpdaterImpl) remoteUpdater).getUpdater()));
    }

    @Override // cerence.ark.assistant.service.IAssistant
    public RemoteResult overrideConfiguration(String str, String str2) {
        Framework framework = this.mFramework;
        if (framework == null) {
            Logger.error(TAG, Utils.format2Argv("overrideConfiguration", ErrorMessage.FRAMEWORK_NULL));
            return Utils.buildErrorRemoteResult(ErrorCode.UNKNOWN);
        }
        return Utils.convertToRemoteResult(framework.overrideConfiguration(str, str2));
    }

    @Override // cerence.ark.assistant.service.IAssistant
    public List<String> getLogConsumerNames() {
        Framework framework = this.mFramework;
        if (framework == null) {
            Logger.error(TAG, Utils.format2Argv("getLogConsumerNames", ErrorMessage.FRAMEWORK_NULL));
            return new ArrayList();
        }
        return framework.getLogConsumerNames();
    }

    @Override // cerence.ark.assistant.service.IAssistant
    public RemoteResult configureLogConsumer(LogConsumer logConsumer) {
        Framework framework = this.mFramework;
        if (framework == null) {
            Logger.error(TAG, Utils.format2Argv("configureLogConsumer", ErrorMessage.FRAMEWORK_NULL));
            return Utils.buildErrorRemoteResult(ErrorCode.UNKNOWN);
        }
        return Utils.convertToRemoteResult(framework.configureLogConsumer(logConsumer));
    }

    @Override // cerence.ark.assistant.service.IAssistant
    public RemoteResult updateUdeDataConfig(String str, String str2, String str3) {
        Framework framework = this.mFramework;
        if (framework == null) {
            Logger.error(TAG, Utils.format2Argv("updateUdeDataConfig", ErrorMessage.FRAMEWORK_NULL));
            return Utils.buildErrorRemoteResult(ErrorCode.UNKNOWN);
        }
        return Utils.convertToRemoteResult(framework.updateUdeDataConfig(str, str2, str3));
    }

    @Override // cerence.ark.assistant.service.IAssistant
    public RemoteResult updateUdeRppConfig(RppLocationType rppLocationType, String str, String str2, String str3) {
        Framework framework = this.mFramework;
        if (framework == null) {
            Logger.error(TAG, Utils.format2Argv("updateUdeRppConfig", ErrorMessage.FRAMEWORK_NULL));
            return Utils.buildErrorRemoteResult(ErrorCode.UNKNOWN);
        }
        return Utils.convertToRemoteResult(framework.updateUdeRppConfig(rppLocationType, str, str2, str3));
    }

    @Override // cerence.ark.assistant.service.IAssistant
    public RemoteResult requestTrimMemory() {
        Framework framework = this.mFramework;
        if (framework == null) {
            Logger.error(TAG, Utils.format2Argv("requestTrimMemory", ErrorMessage.FRAMEWORK_NULL));
            return Utils.buildErrorRemoteResult(ErrorCode.UNKNOWN);
        }
        return Utils.convertToRemoteResult(framework.requestTrimMemory());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void notifySuccess(String str, ICallback iCallback) {
        if (iCallback == null) {
            return;
        }
        try {
            iCallback.onSuccess(null);
        } catch (Exception e) {
            Logger.error(TAG, Utils.format("%s: callback onSuccess", str), e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void notifyError(String str, ErrorCode errorCode, ICallback iCallback) {
        if (iCallback == null) {
            return;
        }
        try {
            iCallback.onError(errorCode, null);
        } catch (Exception e) {
            Logger.error(TAG, Utils.format("%s: callback onError", str), e);
        }
    }

    @Override // cerence.ark.assistant.service.IAssistant
    public void startListening(String str, IDictationManagerListener iDictationManagerListener, final ICallback iCallback) {
        this.mDictationManagerListener = iDictationManagerListener;
        Framework framework = this.mFramework;
        if (framework == null) {
            Logger.error(TAG, Utils.format2Argv("startListening", ErrorMessage.FRAMEWORK_NULL));
            notifyError("startListening", ErrorCode.UNKNOWN, iCallback);
        } else {
            framework.startListening(str, new IResultCallback() { // from class: cerence.ark.assistant.service.AssistantImpl$$ExternalSyntheticLambda9
                @Override // cerence.ark.assistant.common.callback.IResultCallback
                public final void onResult(Result result) {
                    AssistantImpl.lambda$startListening$8(iCallback, result);
                }
            });
        }
    }

    static /* synthetic */ void lambda$startListening$8(ICallback iCallback, Result result) {
        if (result.isSuccess()) {
            notifySuccess("startListening", iCallback);
        } else {
            notifyError("startListening", (ErrorCode) result.getErrorCode(), iCallback);
        }
    }

    @Override // cerence.ark.assistant.service.IAssistant
    public void stopListening(final ICallback iCallback) {
        Framework framework = this.mFramework;
        if (framework == null) {
            Logger.error(TAG, Utils.format2Argv("stopListening", ErrorMessage.FRAMEWORK_NULL));
            notifyError("stopListening", ErrorCode.UNKNOWN, iCallback);
        } else {
            framework.stopListening(new IResultCallback() { // from class: cerence.ark.assistant.service.AssistantImpl$$ExternalSyntheticLambda3
                @Override // cerence.ark.assistant.common.callback.IResultCallback
                public final void onResult(Result result) {
                    AssistantImpl.lambda$stopListening$9(iCallback, result);
                }
            });
        }
    }

    static /* synthetic */ void lambda$stopListening$9(ICallback iCallback, Result result) {
        if (result.isSuccess()) {
            notifySuccess("stopListening", iCallback);
        } else {
            notifyError("stopListening", (ErrorCode) result.getErrorCode(), iCallback);
        }
    }

    @Override // cerence.ark.assistant.service.IAssistant
    public void cancelListening(final ICallback iCallback) {
        Framework framework = this.mFramework;
        if (framework == null) {
            Logger.error(TAG, Utils.format2Argv("cancelListening", ErrorMessage.FRAMEWORK_NULL));
            notifyError("cancelListening", ErrorCode.UNKNOWN, iCallback);
        } else {
            framework.cancelListening(new IResultCallback() { // from class: cerence.ark.assistant.service.AssistantImpl$$ExternalSyntheticLambda4
                @Override // cerence.ark.assistant.common.callback.IResultCallback
                public final void onResult(Result result) {
                    AssistantImpl.lambda$cancelListening$10(iCallback, result);
                }
            });
        }
    }

    static /* synthetic */ void lambda$cancelListening$10(ICallback iCallback, Result result) {
        if (result.isSuccess()) {
            notifySuccess("cancelListening", iCallback);
        } else {
            notifyError("cancelListening", (ErrorCode) result.getErrorCode(), iCallback);
        }
    }

    private static class ErrorListenerImpl implements ErrorListener {
        private final IErrorListener mErrorListener;

        public ErrorListenerImpl(IErrorListener iErrorListener) {
            this.mErrorListener = iErrorListener;
        }

        @Override // cerence.ark.assistant.framework.ErrorListener
        public void onError(ErrorInfo errorInfo) {
            IErrorListener iErrorListener = this.mErrorListener;
            if (iErrorListener == null) {
                Logger.error(AssistantImpl.TAG, "onError: error listener is null");
                return;
            }
            try {
                iErrorListener.onError(errorInfo);
            } catch (Exception e) {
                Logger.error(AssistantImpl.TAG, "onError: exception", e);
            }
        }
    }
}
