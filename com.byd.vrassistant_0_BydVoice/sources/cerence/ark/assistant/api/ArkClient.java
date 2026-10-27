package cerence.ark.assistant.api;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.media.AudioAttributes;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Process;
import android.text.TextUtils;
import cerence.ark.assistant.api.controller.Domain;
import cerence.ark.assistant.api.controller.IController;
import cerence.ark.assistant.api.event.AppEvent;
import cerence.ark.assistant.api.prompter.TtsVoice;
import cerence.ark.assistant.common.Logger;
import cerence.ark.assistant.common.Scheduler;
import cerence.ark.assistant.common.callback.IResultCallback;
import cerence.ark.assistant.common.callback.Result;
import cerence.ark.assistant.core.MessageHandler;
import cerence.ark.assistant.core.PrompterProxy;
import cerence.ark.assistant.framework.AssistantState;
import cerence.ark.assistant.framework.prompter.IPrompter;
import cerence.ark.assistant.library.Constant;
import cerence.ark.assistant.library.ErrorMessage;
import cerence.ark.assistant.library.Utils;
import cerence.ark.assistant.library.ipctransaction.ipcstring.StringAssembler;
import cerence.ark.assistant.service.IActivated;
import cerence.ark.assistant.service.IAssistant;
import cerence.ark.assistant.service.ICallback;
import cerence.ark.assistant.service.IPrompterService;
import cerence.ark.assistant.service.IRequestListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: classes.dex */
public class ArkClient {
    private static final String PKG_SPEECH_SERVICE = "cerence.ark.assistant.service.SpeechService";
    private static final String TAG = "ArkClient";
    private IAssistant mAssistant;
    private AssistantStateChangedListener mAssistantStateChangedListener;
    private BroadcastReceiver mAssistantStateReceiver;
    private ServiceConnection mConnection;
    private Map<String, Domain> mDomainMap;
    private final Handler mHandler;
    private volatile boolean mIsBindingService;
    private final ReentrantLock mLock;
    private MessageHandler mMessageHandler;
    private final Map<String, PrompterProxy> mPrompterMap;
    private StringAssembler mRequestAssembler;

    public interface AssistantStateChangedListener {
        void onStateChanged(AssistantState assistantState);
    }

    public static ArkClient get() {
        return SingletonInstance.INSTANCE;
    }

    private ArkClient() {
        this.mLock = new ReentrantLock();
        this.mPrompterMap = new ConcurrentHashMap(16);
        HandlerThread handlerThread = new HandlerThread("client_thread");
        handlerThread.start();
        this.mHandler = new Handler(handlerThread.getLooper());
    }

    private IRequestListener.Stub createRequestListener() {
        return new IRequestListener.Stub() { // from class: cerence.ark.assistant.api.ArkClient.1
            @Override // cerence.ark.assistant.service.IRequestListener
            public void onRequestReceived(String str, boolean z, boolean z2) {
                MessageHandler messageHandler = ArkClient.this.mMessageHandler;
                if (messageHandler == null) {
                    Logger.error(ArkClient.TAG, "onRequestReceived: MessageHandler is null");
                    return;
                }
                if (z) {
                    ArkClient.this.mRequestAssembler = new StringAssembler();
                }
                if (z2) {
                    String strAssemble = ArkClient.this.mRequestAssembler.assemble(str);
                    ArkClient.this.mRequestAssembler = null;
                    messageHandler.handleRequest(strAssemble);
                    return;
                }
                ArkClient.this.mRequestAssembler.assemble(str);
            }

            @Override // cerence.ark.assistant.service.IRequestListener
            public boolean onAbortRequest(String str) {
                MessageHandler messageHandler = ArkClient.this.mMessageHandler;
                if (messageHandler == null) {
                    Logger.error(ArkClient.TAG, "onAbortRequest: MessageHandler is null");
                    return true;
                }
                return messageHandler.handleAbortRequest(str);
            }
        };
    }

    /* JADX INFO: renamed from: cerence.ark.assistant.api.ArkClient$2, reason: invalid class name */
    class AnonymousClass2 implements ServiceConnection {
        final /* synthetic */ IResultCallback val$callBack;
        final /* synthetic */ IController val$controller;

        AnonymousClass2(IResultCallback iResultCallback, IController iController) {
            this.val$callBack = iResultCallback;
            this.val$controller = iController;
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Logger.debug(ArkClient.TAG, "onServiceConnected: bind service successful");
            IAssistant iAssistantAsInterface = IAssistant.Stub.asInterface(iBinder);
            final String str = "onServiceConnected";
            if (iAssistantAsInterface == null) {
                ArkClient.this.mIsBindingService = false;
                final IResultCallback iResultCallback = this.val$callBack;
                Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkClient$2$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        Utils.notifyFailure(ArkClient.TAG, str, ErrorMessage.REMOTE_NULL, iResultCallback);
                    }
                });
            } else {
                ArkClient arkClient = ArkClient.this;
                IController iController = this.val$controller;
                final IResultCallback iResultCallback2 = this.val$callBack;
                arkClient.registerRequestListener(iAssistantAsInterface, iController, null, new IResultCallback() { // from class: cerence.ark.assistant.api.ArkClient$2$$ExternalSyntheticLambda1
                    @Override // cerence.ark.assistant.common.callback.IResultCallback
                    public final void onResult(Result result) {
                        this.f$0.m38x3fd4dc67(iResultCallback2, str, result);
                    }
                });
            }
        }

        /* JADX INFO: renamed from: lambda$onServiceConnected$3$cerence-ark-assistant-api-ArkClient$2, reason: not valid java name */
        /* synthetic */ void m38x3fd4dc67(final IResultCallback iResultCallback, final String str, final Result result) {
            ArkClient.this.mIsBindingService = false;
            if (result.isSuccess()) {
                Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkClient$2$$ExternalSyntheticLambda2
                    @Override // java.lang.Runnable
                    public final void run() {
                        Utils.notifySuccess(iResultCallback);
                    }
                });
            } else {
                Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkClient$2$$ExternalSyntheticLambda3
                    @Override // java.lang.Runnable
                    public final void run() {
                        Utils.notifyFailure(ArkClient.TAG, str, result.getErrorMessage(), iResultCallback);
                    }
                });
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            Logger.error(ArkClient.TAG, "onServiceDisconnected");
            ArkClient.this.mAssistant = null;
            ArkClient.this.mConnection = null;
            ArkClient.this.mIsBindingService = false;
            MessageHandler messageHandler = ArkClient.this.mMessageHandler;
            if (messageHandler != null) {
                messageHandler.setAssistant(null);
            }
        }

        @Override // android.content.ServiceConnection
        public void onBindingDied(ComponentName componentName) {
            Logger.error(ArkClient.TAG, "onBindingDied");
            ArkClient.this.mAssistant = null;
            ArkClient.this.mConnection = null;
            ArkClient.this.mIsBindingService = false;
            MessageHandler messageHandler = ArkClient.this.mMessageHandler;
            if (messageHandler != null) {
                messageHandler.setAssistant(null);
            }
        }
    }

    private ServiceConnection createServiceConnection(IController iController, IResultCallback<Void> iResultCallback) {
        return new AnonymousClass2(iResultCallback, iController);
    }

    public void bindSpeechService(final Context context, final String str, final IController iController, final IResultCallback<Void> iResultCallback) {
        this.mHandler.post(new Runnable() { // from class: cerence.ark.assistant.api.ArkClient$$ExternalSyntheticLambda8
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m34lambda$bindSpeechService$3$cerencearkassistantapiArkClient(iResultCallback, context, str, iController);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$bindSpeechService$3$cerence-ark-assistant-api-ArkClient, reason: not valid java name */
    /* synthetic */ void m34lambda$bindSpeechService$3$cerencearkassistantapiArkClient(final IResultCallback iResultCallback, Context context, String str, IController iController) {
        final String str2 = "bindSpeechService";
        if (this.mIsBindingService) {
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkClient$$ExternalSyntheticLambda9
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifyFailure(ArkClient.TAG, str2, ErrorMessage.BINDING_SERVICE, iResultCallback);
                }
            });
            return;
        }
        Objects.requireNonNull(context, "Context must not be null");
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("Voice assistant package name must not be null");
        }
        if (iController == null) {
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkClient$$ExternalSyntheticLambda10
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifyFailure(ArkClient.TAG, str2, "controller is null", iResultCallback);
                }
            });
            return;
        }
        int iMyPid = Process.myPid();
        Utils.init(context);
        Utils.setClientPid(iMyPid);
        Logger.debug(TAG, String.format("%s: client_version = %s, pid = %s ", "bindSpeechService", "CA-SDK-ANDROID_Branch22cw24__2025_cw08A#20250221.2002#929254f9f", Integer.valueOf(iMyPid)));
        this.mIsBindingService = true;
        registerAssistantStateReceiver();
        ServiceConnection serviceConnection = this.mConnection;
        if (serviceConnection != null) {
            Utils.getContext().unbindService(serviceConnection);
        }
        this.mConnection = createServiceConnection(iController, iResultCallback);
        Intent intent = new Intent();
        intent.setClassName(str, PKG_SPEECH_SERVICE);
        if (Utils.getContext().bindService(intent, this.mConnection, 1)) {
            return;
        }
        this.mIsBindingService = false;
        Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkClient$$ExternalSyntheticLambda11
            @Override // java.lang.Runnable
            public final void run() {
                Utils.notifyFailure(ArkClient.TAG, str2, ErrorMessage.BIND_SERVICE_ERROR, iResultCallback);
            }
        });
    }

    void registerRequestListener(final IAssistant iAssistant, final IController iController, final Dhi dhi, final IResultCallback<Void> iResultCallback) {
        this.mHandler.post(new Runnable() { // from class: cerence.ark.assistant.api.ArkClient$$ExternalSyntheticLambda7
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m36x3be28ced(iController, dhi, iAssistant, iResultCallback);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$registerRequestListener$4$cerence-ark-assistant-api-ArkClient, reason: not valid java name */
    /* synthetic */ void m36x3be28ced(IController iController, Dhi dhi, IAssistant iAssistant, final IResultCallback iResultCallback) {
        final String str = "registerRequestListener";
        try {
            HashMap map = new HashMap(16);
            HashMap map2 = new HashMap(16);
            HashMap map3 = new HashMap(16);
            this.mDomainMap = map3;
            iController.loadDomains(map3, dhi);
            for (String str2 : this.mDomainMap.keySet()) {
                Domain domain = this.mDomainMap.get(str2);
                if (domain != null) {
                    HashMap map4 = new HashMap(16);
                    domain.loadRequestHandlers(map4);
                    if (!map4.isEmpty()) {
                        map.put(str2, map4);
                        map2.put(str2, new ArrayList(map4.keySet()));
                    }
                }
            }
            MessageHandler messageHandler = new MessageHandler(map);
            this.mMessageHandler = messageHandler;
            messageHandler.setAssistant(iAssistant);
            this.mAssistant = iAssistant;
            ICallback.Stub stub = new ICallback.Stub() { // from class: cerence.ark.assistant.api.ArkClient.3
                @Override // cerence.ark.assistant.service.ICallback
                public void onSuccess() {
                    Logger.debug(ArkClient.TAG, str + ": successful");
                    Utils.notifySuccess(iResultCallback);
                }

                @Override // cerence.ark.assistant.service.ICallback
                public void onFailure(String str3) {
                    Utils.notifyFailure(ArkClient.TAG, str, str3, iResultCallback);
                }
            };
            Logger.debug(TAG, "start register request listener");
            String packageName = Utils.getContext().getPackageName();
            iAssistant.registerActivatedListener(packageName, new IActivated.Stub() { // from class: cerence.ark.assistant.api.ArkClient.4
                @Override // cerence.ark.assistant.service.IActivated
                public void activate() {
                    ArkClient.this.activateDomains();
                }

                @Override // cerence.ark.assistant.service.IActivated
                public void deactivate() {
                    ArkClient.this.deactivateDomains();
                }
            });
            iAssistant.registerRequestListener(packageName, map2, createRequestListener(), stub);
        } catch (Exception e) {
            Utils.notifyFailure(TAG, "registerRequestListener", "register requests exception", e, iResultCallback);
        }
    }

    public void unbindSpeechService() {
        if (Utils.getContext() == null) {
            Logger.error(TAG, "unbindSpeechService: Client did not registered");
        } else {
            this.mAssistantStateChangedListener = null;
            this.mHandler.post(new Runnable() { // from class: cerence.ark.assistant.api.ArkClient$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.destroy();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void activateDomains() {
        this.mHandler.post(new Runnable() { // from class: cerence.ark.assistant.api.ArkClient$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m33lambda$activateDomains$5$cerencearkassistantapiArkClient();
            }
        });
    }

    /* JADX INFO: renamed from: lambda$activateDomains$5$cerence-ark-assistant-api-ArkClient, reason: not valid java name */
    /* synthetic */ void m33lambda$activateDomains$5$cerencearkassistantapiArkClient() {
        if (this.mDomainMap == null) {
            return;
        }
        Logger.debug(TAG, "activateDomains");
        for (Domain domain : this.mDomainMap.values()) {
            if (domain != null) {
                domain.activate();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void deactivateDomains() {
        this.mHandler.post(new Runnable() { // from class: cerence.ark.assistant.api.ArkClient$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m35lambda$deactivateDomains$6$cerencearkassistantapiArkClient();
            }
        });
    }

    /* JADX INFO: renamed from: lambda$deactivateDomains$6$cerence-ark-assistant-api-ArkClient, reason: not valid java name */
    /* synthetic */ void m35lambda$deactivateDomains$6$cerencearkassistantapiArkClient() {
        if (this.mDomainMap == null) {
            return;
        }
        Logger.debug(TAG, "deactivateDomains");
        for (Domain domain : this.mDomainMap.values()) {
            if (domain != null) {
                domain.deactivate();
            }
        }
        this.mDomainMap.clear();
        this.mDomainMap = null;
    }

    void destroy() {
        unregisterAssistantStateReceiver();
        destroyPrompters();
        try {
            IAssistant iAssistant = this.mAssistant;
            if (iAssistant != null) {
                iAssistant.unregisterRequestListener(Utils.getContext().getPackageName());
            }
        } catch (Exception e) {
            Logger.error(TAG, "destroy: unregisterRequestListener exception", e);
        }
        try {
            ServiceConnection serviceConnection = this.mConnection;
            if (serviceConnection != null) {
                Utils.getContext().unbindService(serviceConnection);
            }
        } catch (Exception e2) {
            Logger.error(TAG, "destroy: unbindService exception", e2);
        }
        MessageHandler messageHandler = this.mMessageHandler;
        if (messageHandler != null) {
            messageHandler.destroy();
        }
        this.mMessageHandler = null;
        this.mConnection = null;
        this.mAssistant = null;
        this.mIsBindingService = false;
    }

    public List<String> getAvailableTtsLanguages() {
        ArrayList arrayList = new ArrayList();
        IAssistant iAssistant = this.mAssistant;
        if (iAssistant == null) {
            Logger.error(TAG, Utils.buildErrorMsg("getAvailableTtsLanguages", ErrorMessage.REMOTE_NULL));
            return arrayList;
        }
        try {
            return iAssistant.getAvailableTtsLanguages();
        } catch (Exception e) {
            Logger.error(TAG, Utils.buildErrorMsg("getAvailableTtsLanguages", ErrorMessage.EXCEPTION), e);
            return arrayList;
        }
    }

    public List<TtsVoice> getAvailableTtsVoices(String str) {
        ArrayList arrayList = new ArrayList();
        IAssistant iAssistant = this.mAssistant;
        if (iAssistant == null) {
            Logger.error(TAG, Utils.buildErrorMsg("getAvailableTtsVoices", ErrorMessage.REMOTE_NULL));
            return arrayList;
        }
        try {
            return iAssistant.getAvailableTtsVoices(str);
        } catch (Exception e) {
            Logger.error(TAG, Utils.buildErrorMsg("getAvailableTtsVoices", ErrorMessage.EXCEPTION), e);
            return arrayList;
        }
    }

    public List<String> getAvailablePrompterNames() {
        ArrayList arrayList = new ArrayList();
        IAssistant iAssistant = this.mAssistant;
        if (iAssistant == null) {
            Logger.error(TAG, Utils.buildErrorMsg("getAvailablePrompterNames", ErrorMessage.REMOTE_NULL));
            return arrayList;
        }
        try {
            List<String> availablePrompterNames = iAssistant.getAvailablePrompterNames();
            if (availablePrompterNames != null) {
                return availablePrompterNames;
            }
            Logger.error(TAG, String.format("%s: prompt instance names is null", "getAvailablePrompterNames"));
            return arrayList;
        } catch (Exception e) {
            Logger.error(TAG, Utils.buildErrorMsg("getAvailablePrompterNames", ErrorMessage.EXCEPTION), e);
            return arrayList;
        }
    }

    public IPrompter createPrompter(String str, int i) {
        return createPrompter(str, new AudioAttributes.Builder().setLegacyStreamType(i).build());
    }

    public IPrompter createPrompter(String str, AudioAttributes audioAttributes) {
        if (TextUtils.isEmpty(str)) {
            Logger.error(TAG, "createPrompter: prompt name is empty.");
            return null;
        }
        if (this.mAssistant == null) {
            Logger.error(TAG, "createPrompter: remote assistant is null");
            return null;
        }
        this.mLock.lock();
        try {
            PrompterProxy prompterProxy = this.mPrompterMap.get(str);
            if (prompterProxy != null) {
                Logger.warn(TAG, String.format("createPrompter: the prompter %s has already been created.", str));
            } else {
                IPrompterService iPrompterServiceCreatePrompter = this.mAssistant.createPrompter(str, audioAttributes);
                if (iPrompterServiceCreatePrompter == null) {
                    return null;
                }
                prompterProxy = new PrompterProxy(str, iPrompterServiceCreatePrompter);
                this.mPrompterMap.put(str, prompterProxy);
            }
            return prompterProxy;
        } catch (Exception e) {
            Logger.error(TAG, "createPrompter: exception", e);
            return null;
        } finally {
            this.mLock.unlock();
        }
    }

    public void destroyPrompter(IPrompter iPrompter) {
        Objects.requireNonNull(iPrompter, "prompter must not be null");
        if (this.mAssistant == null) {
            Logger.error(TAG, "destroyPrompter: remote assistant is null");
            return;
        }
        if (!(iPrompter instanceof PrompterProxy)) {
            Logger.error(TAG, "destroyPrompter: prompter not instanceof PrompterProxy");
            return;
        }
        this.mLock.lock();
        try {
            try {
                PrompterProxy prompterProxy = (PrompterProxy) iPrompter;
                PrompterProxy prompterProxyRemove = this.mPrompterMap.remove(prompterProxy.getPrompterName());
                if (prompterProxyRemove != null && prompterProxyRemove.getPrompterService() != null) {
                    this.mAssistant.destroyPrompter(prompterProxyRemove.getPrompterService());
                }
                prompterProxy.release();
            } catch (Exception e) {
                Logger.error(TAG, "destroyPrompter: exception", e);
            }
        } finally {
            this.mLock.unlock();
        }
    }

    private void destroyPrompters() {
        PrompterProxy prompterProxy;
        for (String str : this.mPrompterMap.keySet()) {
            if (!TextUtils.isEmpty(str) && (prompterProxy = this.mPrompterMap.get(str)) != null) {
                destroyPrompter(prompterProxy);
            }
        }
        this.mPrompterMap.clear();
    }

    private static class SingletonInstance {
        private static final ArkClient INSTANCE = new ArkClient();

        private SingletonInstance() {
        }
    }

    class AssistantStateReceiver extends BroadcastReceiver {
        AssistantStateReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (Constant.ACTION_ASSISTANT_STATE_CHANGED.equals(intent.getAction())) {
                AssistantState assistantState = (AssistantState) intent.getParcelableExtra(Constant.KEY_ASSISTANT_STATE);
                Logger.warn(ArkClient.TAG, String.format("AssistantStateReceiver.onReceive: assistant_state = %s", assistantState.name()));
                AssistantStateChangedListener assistantStateChangedListener = ArkClient.this.mAssistantStateChangedListener;
                if (assistantStateChangedListener != null) {
                    assistantStateChangedListener.onStateChanged(assistantState);
                }
            }
        }
    }

    private void registerAssistantStateReceiver() {
        try {
            unregisterAssistantStateReceiver();
            this.mAssistantStateReceiver = new AssistantStateReceiver();
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(Constant.ACTION_ASSISTANT_STATE_CHANGED);
            Utils.getContext().registerReceiver(this.mAssistantStateReceiver, intentFilter);
        } catch (Exception e) {
            Logger.error(TAG, "registerAssistantStateReceiver: exception", e);
        }
    }

    private void unregisterAssistantStateReceiver() {
        if (this.mAssistantStateReceiver == null) {
            return;
        }
        try {
            try {
                Utils.getContext().unregisterReceiver(this.mAssistantStateReceiver);
            } catch (Exception e) {
                Logger.error(TAG, "unregisterAssistantStateReceiver: exception", e);
            }
        } finally {
            this.mAssistantStateReceiver = null;
        }
    }

    public AssistantState getAssistantState() {
        IAssistant iAssistant = this.mAssistant;
        if (iAssistant == null) {
            Logger.error(TAG, String.format("%s: remote assistant is null", "getAssistantState"));
            return AssistantState.UNKNOWN;
        }
        try {
            return iAssistant.getAssistantState();
        } catch (Exception e) {
            Logger.error(TAG, Utils.buildErrorMsg("getAssistantState", ErrorMessage.EXCEPTION), e);
            return AssistantState.UNKNOWN;
        }
    }

    public void setAssistantStateChangedListener(AssistantStateChangedListener assistantStateChangedListener) {
        this.mAssistantStateChangedListener = assistantStateChangedListener;
    }

    public void sendAppEvent(final AppEvent appEvent, final IResultCallback<Void> iResultCallback) {
        Objects.requireNonNull(appEvent, "event must not be null");
        this.mHandler.post(new Runnable() { // from class: cerence.ark.assistant.api.ArkClient$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m37lambda$sendAppEvent$10$cerencearkassistantapiArkClient(iResultCallback, appEvent);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$sendAppEvent$10$cerence-ark-assistant-api-ArkClient, reason: not valid java name */
    /* synthetic */ void m37lambda$sendAppEvent$10$cerencearkassistantapiArkClient(final IResultCallback iResultCallback, AppEvent appEvent) {
        if (this.mAssistant == null) {
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkClient$$ExternalSyntheticLambda4
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifyFailure(ArkClient.TAG, "sendAppEvent", ErrorMessage.REMOTE_NULL, iResultCallback);
                }
            });
            return;
        }
        if (!isRunning()) {
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkClient$$ExternalSyntheticLambda5
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifyFailure(ArkClient.TAG, "sendAppEvent", ErrorMessage.ASSISTANT_NOT_STARTED, iResultCallback);
                }
            });
            return;
        }
        try {
            String strCreateEvent = Utils.createEvent(appEvent.getName(), appEvent.getJsonData());
            Logger.info(TAG, String.format("sendAppEvent: %s", appEvent.getName()));
            this.mAssistant.sendAppEvent(strCreateEvent, new AnonymousClass5(iResultCallback));
        } catch (Exception e) {
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkClient$$ExternalSyntheticLambda6
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifyFailure(ArkClient.TAG, "sendAppEvent", null, e, iResultCallback);
                }
            });
        }
    }

    /* JADX INFO: renamed from: cerence.ark.assistant.api.ArkClient$5, reason: invalid class name */
    class AnonymousClass5 extends ICallback.Stub {
        final /* synthetic */ IResultCallback val$callBack;

        AnonymousClass5(IResultCallback iResultCallback) {
            this.val$callBack = iResultCallback;
        }

        @Override // cerence.ark.assistant.service.ICallback
        public void onSuccess() {
            final IResultCallback iResultCallback = this.val$callBack;
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkClient$5$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifySuccess(iResultCallback);
                }
            });
        }

        @Override // cerence.ark.assistant.service.ICallback
        public void onFailure(final String str) {
            final IResultCallback iResultCallback = this.val$callBack;
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkClient$5$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifyFailure(ArkClient.TAG, "sendAppEvent", str, iResultCallback);
                }
            });
        }
    }

    boolean isRunning() {
        return get().getAssistantState() == AssistantState.RUNNING;
    }
}
