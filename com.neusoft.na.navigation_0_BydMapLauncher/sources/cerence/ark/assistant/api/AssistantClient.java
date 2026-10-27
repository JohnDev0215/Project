package cerence.ark.assistant.api;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.media.AudioAttributes;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Process;
import android.text.TextUtils;
import cerence.ark.assistant.api.controller.Domain;
import cerence.ark.assistant.api.controller.IController;
import cerence.ark.assistant.api.event.AppEvent;
import cerence.ark.assistant.api.prompter.PrompterInstance;
import cerence.ark.assistant.api.prompter.TtsVoice;
import cerence.ark.assistant.common.Logger;
import cerence.ark.assistant.common.Scheduler;
import cerence.ark.assistant.common.callback.IResultCallback;
import cerence.ark.assistant.common.callback.Result;
import cerence.ark.assistant.core.MessageHandler;
import cerence.ark.assistant.core.PrompterProxy;
import cerence.ark.assistant.framework.AssistantState;
import cerence.ark.assistant.framework.data.ErrorCode;
import cerence.ark.assistant.framework.prompter.IPrompter;
import cerence.ark.assistant.library.Constant;
import cerence.ark.assistant.library.ErrorMessage;
import cerence.ark.assistant.library.Utils;
import cerence.ark.assistant.library.ipctransaction.IAssemblerListener;
import cerence.ark.assistant.library.ipctransaction.ISplitter;
import cerence.ark.assistant.library.ipctransaction.ipcstring.StringSplitter;
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
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: classes2.dex */
public final class AssistantClient {
    private static final String PKG_SPEECH_SERVICE = "cerence.ark.assistant.service.SpeechService";
    private static final String TAG = "AssistantClient";
    private IAssistant mAssistant;
    private BroadcastReceiver mAssistantStateReceiver;
    private String mClientUid;
    private ServiceConnection mConnection;
    private Map<String, Domain> mDomainMap;
    private Handler mHandler;
    private boolean mIsBinding;
    private MessageHandler mMessageHandler;
    private final ReentrantLock mLock = new ReentrantLock();
    private final Map<String, PrompterProxy> mPrompterMap = new ConcurrentHashMap(16);
    private final List<AssistantStateChangedListener> mStateChangedListeners = new CopyOnWriteArrayList();
    private final Object mBindingLock = new Object();

    public interface AssistantStateChangedListener {
        void onStateChanged(AssistantState assistantState);
    }

    private synchronized void createClientThread() {
        if (this.mHandler == null) {
            HandlerThread handlerThread = new HandlerThread("ca_client");
            handlerThread.start();
            this.mHandler = new Handler(handlerThread.getLooper());
            Logger.info(TAG, Utils.format("client thread: %s, %s", handlerThread.getName(), Long.valueOf(handlerThread.getId())));
        }
    }

    /* JADX INFO: renamed from: cerence.ark.assistant.api.AssistantClient$1, reason: invalid class name */
    class AnonymousClass1 extends IRequestListener.Stub {
        AnonymousClass1() {
        }

        @Override // cerence.ark.assistant.service.IRequestListener
        public void onRequestReceived(final String str, String str2, String str3, int i, int i2) {
            final MessageHandler messageHandler = AssistantClient.this.mMessageHandler;
            if (messageHandler == null) {
                Logger.error(AssistantClient.TAG, "onRequestReceived: messageHandler is null");
            } else {
                Utils.processStringAssembler(str2, str3, i, i2, new IAssemblerListener() { // from class: cerence.ark.assistant.api.AssistantClient$1$$ExternalSyntheticLambda0
                    @Override // cerence.ark.assistant.library.ipctransaction.IAssemblerListener
                    public final void onFinal(Object obj) {
                        messageHandler.handleRequest(str, (String) obj);
                    }
                });
            }
        }

        @Override // cerence.ark.assistant.service.IRequestListener
        public boolean onAbortRequest(String str) {
            MessageHandler messageHandler = AssistantClient.this.mMessageHandler;
            if (messageHandler == null) {
                Logger.error(AssistantClient.TAG, "onAbortRequest: messageHandler is null");
                return true;
            }
            return messageHandler.handleAbortRequest(str);
        }
    }

    private IRequestListener.Stub createRequestListener() {
        return new AnonymousClass1();
    }

    private ServiceConnection createServiceConnection() {
        return new ServiceConnection() { // from class: cerence.ark.assistant.api.AssistantClient.2
            @Override // android.content.ServiceConnection
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                Logger.info(AssistantClient.TAG, "onServiceConnected: bind service successful");
                synchronized (AssistantClient.this) {
                    if (AssistantClient.this.mConnection != null) {
                        Utils.getContext().unbindService(AssistantClient.this.mConnection);
                    }
                    AssistantClient.this.mConnection = this;
                }
                AssistantClient.this.mAssistant = IAssistant.Stub.asInterface(iBinder);
                synchronized (AssistantClient.this.mBindingLock) {
                    AssistantClient.this.mIsBinding = false;
                    AssistantClient.this.mBindingLock.notifyAll();
                }
            }

            @Override // android.content.ServiceConnection
            public void onServiceDisconnected(ComponentName componentName) {
                Logger.error(AssistantClient.TAG, "onServiceDisconnected");
                AssistantClient.this.mAssistant = null;
                synchronized (AssistantClient.this) {
                    AssistantClient.this.mConnection = null;
                }
                MessageHandler messageHandler = AssistantClient.this.mMessageHandler;
                if (messageHandler != null) {
                    messageHandler.setAssistant(null);
                }
            }

            @Override // android.content.ServiceConnection
            public void onBindingDied(ComponentName componentName) {
                Logger.error(AssistantClient.TAG, "onBindingDied");
                AssistantClient.this.mAssistant = null;
                synchronized (AssistantClient.this) {
                    AssistantClient.this.mConnection = null;
                }
                MessageHandler messageHandler = AssistantClient.this.mMessageHandler;
                if (messageHandler != null) {
                    messageHandler.setAssistant(null);
                }
            }
        };
    }

    public synchronized void bindSpeechService(final Context context, final String str, final String str2, final IController iController, final IResultCallback<Void, ErrorCode> iResultCallback) {
        createClientThread();
        this.mHandler.post(new Runnable() { // from class: cerence.ark.assistant.api.AssistantClient$$ExternalSyntheticLambda7
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m50xdf22d0a7(context, str, iController, str2, iResultCallback);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$bindSpeechService$4$cerence-ark-assistant-api-AssistantClient, reason: not valid java name */
    /* synthetic */ void m50xdf22d0a7(Context context, String str, IController iController, String str2, final IResultCallback iResultCallback) {
        Objects.requireNonNull(context, "Context must not be null");
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("Voice assistant package name must not be null");
        }
        int iMyPid = Process.myPid();
        Utils.init(context);
        Utils.setClientPid(iMyPid);
        String str3 = TAG;
        Logger.info(str3, Utils.format("%s: client_version = %s, pid = %s ", "bindSpeechService", cerence.ark.assistant.client.BuildConfig.CLIENT_VERSION, Integer.valueOf(iMyPid)));
        registerAssistantStateReceiver();
        synchronized (this.mBindingLock) {
            this.mIsBinding = true;
        }
        ServiceConnection serviceConnectionCreateServiceConnection = createServiceConnection();
        Intent intent = new Intent();
        intent.setClassName(str, PKG_SPEECH_SERVICE);
        if (Utils.getContext().bindService(intent, serviceConnectionCreateServiceConnection, 1)) {
            synchronized (this.mBindingLock) {
                while (this.mIsBinding) {
                    try {
                        Logger.info(TAG, "wait for bind speech service");
                        this.mBindingLock.wait();
                    } catch (InterruptedException e) {
                        Logger.error(TAG, "interrupted", e);
                        Thread.currentThread().interrupt();
                    }
                    Logger.info(TAG, "wait for finish");
                }
            }
            registerRequestListener(iController, null, str2, new IResultCallback() { // from class: cerence.ark.assistant.api.AssistantClient$$ExternalSyntheticLambda13
                @Override // cerence.ark.assistant.common.callback.IResultCallback
                public final void onResult(Result result) {
                    AssistantClient.lambda$bindSpeechService$2(iResultCallback, result);
                }
            });
            return;
        }
        Logger.error(str3, Utils.format2Argv("bindSpeechService", ErrorMessage.BIND_SPEECH_SERVICE_ERROR));
        Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.AssistantClient$$ExternalSyntheticLambda14
            @Override // java.lang.Runnable
            public final void run() {
                Utils.notifyError(ErrorCode.BIND_SPEECH_SERVICE_ERROR, iResultCallback);
            }
        });
    }

    static /* synthetic */ void lambda$bindSpeechService$2(final IResultCallback iResultCallback, final Result result) {
        if (result.isSuccess()) {
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.AssistantClient$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifySuccess(iResultCallback);
                }
            });
        } else {
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.AssistantClient$$ExternalSyntheticLambda11
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifyError((ErrorCode) result.getErrorCode(), iResultCallback);
                }
            });
        }
    }

    void registerRequestListener(IController iController, Dhi dhi, String str, final IResultCallback<Void, ErrorCode> iResultCallback) {
        createClientThread();
        registerAssistantStateReceiver();
        try {
            HashMap map = new HashMap(16);
            HashMap map2 = new HashMap(16);
            HashMap map3 = new HashMap(16);
            this.mDomainMap = map3;
            if (iController != null) {
                iController.loadDomains(map3, dhi);
            }
            for (String str2 : this.mDomainMap.keySet()) {
                Domain domain = this.mDomainMap.get(str2);
                if (domain == null) {
                    Logger.warn(TAG, Utils.format("%s: domain instance is null, domainName = %s", "registerRequestListener", str2));
                } else {
                    HashMap map4 = new HashMap(16);
                    domain.loadRequestHandlers(map4);
                    if (!map4.isEmpty()) {
                        map.put(str2, map4);
                        map2.put(str2, new ArrayList(map4.keySet()));
                    }
                }
            }
            MessageHandler messageHandler = new MessageHandler(new HashMap(this.mDomainMap), map);
            this.mMessageHandler = messageHandler;
            messageHandler.setAssistant(this.mAssistant);
            Logger.info(TAG, "start register request listener");
            String packageName = Utils.getContext().getPackageName();
            if (str != null) {
                packageName = packageName + "_" + str;
            }
            this.mClientUid = packageName;
            this.mAssistant.registerRequestListener(packageName, map2, createRequestListener(), new ICallback.Stub() { // from class: cerence.ark.assistant.api.AssistantClient.3
                @Override // cerence.ark.assistant.service.ICallback
                public void onSuccess(String str3) {
                    AssistantClient.this.activateDomains();
                    Utils.notifySuccess(iResultCallback);
                }

                @Override // cerence.ark.assistant.service.ICallback
                public void onError(ErrorCode errorCode, String str3) {
                    Utils.notifyError(errorCode, iResultCallback);
                }
            });
        } catch (Exception e) {
            Logger.error(TAG, Utils.formatException("registerRequestListener"), e);
            Utils.notifyError(ErrorCode.EXCEPTION, iResultCallback);
        }
    }

    IAssistant getAssistant() {
        return this.mAssistant;
    }

    void setAssistant(IAssistant iAssistant) {
        this.mAssistant = iAssistant;
    }

    public synchronized void unbindSpeechService() {
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.post(new Runnable() { // from class: cerence.ark.assistant.api.AssistantClient$$ExternalSyntheticLambda5
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.destroy();
                }
            });
            this.mHandler.getLooper().quitSafely();
            this.mHandler = null;
        } else {
            Logger.error(TAG, "unbindSpeechService: Client did not registered");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void activateDomains() {
        if (this.mDomainMap == null) {
            Logger.warn(TAG, "activateDomains: domain map is null");
            return;
        }
        Logger.info(TAG, "activateDomains");
        for (Domain domain : this.mDomainMap.values()) {
            if (domain != null) {
                domain.activate();
            }
        }
    }

    void deactivateDomains() {
        if (this.mDomainMap == null) {
            Logger.warn(TAG, "deactivateDomains: domain map is null");
            return;
        }
        Logger.info(TAG, "deactivateDomains");
        for (Domain domain : this.mDomainMap.values()) {
            if (domain != null) {
                domain.deactivate();
            }
        }
        this.mDomainMap.clear();
        this.mDomainMap = null;
    }

    void destroy() {
        Utils.cleanAssemblerMap();
        unregisterAssistantStateReceiver();
        deactivateDomains();
        destroyPrompters();
        try {
            IAssistant iAssistant = this.mAssistant;
            if (iAssistant != null) {
                iAssistant.unregisterRequestListener(this.mClientUid);
            }
        } catch (Exception e) {
            Logger.error(TAG, "destroy: unregisterRequestListener exception", e);
        }
        synchronized (this) {
            if (this.mConnection != null) {
                Utils.getContext().unbindService(this.mConnection);
            }
            this.mConnection = null;
        }
        MessageHandler messageHandler = this.mMessageHandler;
        if (messageHandler != null) {
            messageHandler.destroy();
        }
        this.mMessageHandler = null;
        this.mAssistant = null;
    }

    public void queryAvailableTtsLanguages(final IResultCallback<List<String>, ErrorCode> iResultCallback) {
        this.mHandler.post(new Runnable() { // from class: cerence.ark.assistant.api.AssistantClient$$ExternalSyntheticLambda17
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m51x3cfcf135(iResultCallback);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX INFO: renamed from: queryAvailableTtsLanguagesImpl, reason: merged with bridge method [inline-methods] */
    public void m51x3cfcf135(final IResultCallback<List<String>, ErrorCode> iResultCallback) {
        if (!Utils.isInitialized(getAssistantState())) {
            Logger.error(TAG, Utils.format("queryAvailableTtsLanguages: %s", ErrorMessage.ASSISTANT_NOT_INITIALIZED));
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.AssistantClient$$ExternalSyntheticLambda18
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifyError(ErrorCode.ASSISTANT_NOT_INITIALIZED, iResultCallback);
                }
            });
            return;
        }
        try {
            List<String> availableTtsLanguages = this.mAssistant.getAvailableTtsLanguages();
            final ArrayList arrayList = new ArrayList();
            if (availableTtsLanguages != null && !availableTtsLanguages.isEmpty()) {
                arrayList.addAll(availableTtsLanguages);
            }
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.AssistantClient$$ExternalSyntheticLambda19
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifySuccess(arrayList, iResultCallback);
                }
            });
        } catch (Exception e) {
            Logger.error(TAG, "queryAvailableTtsLanguages: exception", e);
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.AssistantClient$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifyError(ErrorCode.EXCEPTION, iResultCallback);
                }
            });
        }
    }

    public void queryAvailableTtsVoices(final String str, final IResultCallback<List<TtsVoice>, ErrorCode> iResultCallback) {
        this.mHandler.post(new Runnable() { // from class: cerence.ark.assistant.api.AssistantClient$$ExternalSyntheticLambda6
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m52x7ae0dde9(str, iResultCallback);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX INFO: renamed from: queryAvailableTtsVoicesImpl, reason: merged with bridge method [inline-methods] */
    public void m52x7ae0dde9(String str, final IResultCallback<List<TtsVoice>, ErrorCode> iResultCallback) {
        if (!Utils.isInitialized(getAssistantState())) {
            Logger.error(TAG, Utils.format("queryAvailableTtsVoices: %s", ErrorMessage.ASSISTANT_NOT_INITIALIZED));
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.AssistantClient$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifyError(ErrorCode.ASSISTANT_NOT_INITIALIZED, iResultCallback);
                }
            });
            return;
        }
        try {
            List<TtsVoice> availableTtsVoices = this.mAssistant.getAvailableTtsVoices(str);
            final ArrayList arrayList = new ArrayList();
            if (availableTtsVoices != null && !availableTtsVoices.isEmpty()) {
                arrayList.addAll(availableTtsVoices);
            }
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.AssistantClient$$ExternalSyntheticLambda3
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifySuccess(arrayList, iResultCallback);
                }
            });
        } catch (Exception e) {
            Logger.error(TAG, "queryAvailableTtsVoices: exception", e);
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.AssistantClient$$ExternalSyntheticLambda4
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifyError(ErrorCode.EXCEPTION, iResultCallback);
                }
            });
        }
    }

    public List<PrompterInstance> getAvailablePrompterInstances() {
        ArrayList arrayList = new ArrayList();
        if (!Utils.isInitialized(getAssistantState())) {
            Logger.error(TAG, Utils.format2Argv("getAvailablePrompterInstances", ErrorMessage.ASSISTANT_NOT_INITIALIZED));
            return arrayList;
        }
        try {
            List<PrompterInstance> availablePrompterInstances = this.mAssistant.getAvailablePrompterInstances();
            if (availablePrompterInstances != null) {
                return availablePrompterInstances;
            }
            Logger.error(TAG, Utils.format("%s: prompt instances is empty", "getAvailablePrompterInstances"));
            return arrayList;
        } catch (Exception e) {
            Logger.error(TAG, Utils.format2Argv("getAvailablePrompterInstances", ErrorMessage.EXCEPTION), e);
            return arrayList;
        }
    }

    public IPrompter createPrompter(PrompterInstance prompterInstance, int i) {
        return createPrompter(prompterInstance, new AudioAttributes.Builder().setLegacyStreamType(i).build());
    }

    public IPrompter createPrompter(PrompterInstance prompterInstance, AudioAttributes audioAttributes) {
        if (prompterInstance == null) {
            Logger.error(TAG, "createPrompter: prompterInstance is null.");
            return null;
        }
        if (!Utils.isInitialized(getAssistantState())) {
            Logger.error(TAG, "createPrompter: voice assistant not initialized");
            return null;
        }
        this.mLock.lock();
        try {
            PrompterProxy prompterProxy = this.mPrompterMap.get(prompterInstance.getName());
            if (prompterProxy != null) {
                Logger.warn(TAG, Utils.format("createPrompter: the prompter %s has already been created.", prompterInstance));
            } else {
                IPrompterService iPrompterServiceCreatePrompter = this.mAssistant.createPrompter(prompterInstance, audioAttributes);
                if (iPrompterServiceCreatePrompter == null) {
                    return null;
                }
                prompterProxy = new PrompterProxy(prompterInstance, iPrompterServiceCreatePrompter);
                this.mPrompterMap.put(prompterInstance.getName(), prompterProxy);
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
                PrompterProxy prompterProxyRemove = this.mPrompterMap.remove(prompterProxy.getPrompterInstance().getName());
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

    class AssistantStateReceiver extends BroadcastReceiver {
        AssistantStateReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (Constant.ACTION_ASSISTANT_STATE_CHANGED.equals(intent.getAction())) {
                AssistantState assistantState = (AssistantState) intent.getParcelableExtra(Constant.KEY_ASSISTANT_STATE);
                Logger.info(AssistantClient.TAG, "AssistantStateReceiver: assistant_state = " + assistantState);
                for (AssistantStateChangedListener assistantStateChangedListener : AssistantClient.this.mStateChangedListeners) {
                    if (assistantStateChangedListener != null) {
                        assistantStateChangedListener.onStateChanged(assistantState);
                    }
                }
            }
        }
    }

    private void registerAssistantStateReceiver() {
        if (this.mAssistantStateReceiver != null) {
            return;
        }
        try {
            this.mAssistantStateReceiver = new AssistantStateReceiver();
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(Constant.ACTION_ASSISTANT_STATE_CHANGED);
            if (Build.VERSION.SDK_INT >= 34) {
                Utils.getContext().registerReceiver(this.mAssistantStateReceiver, intentFilter, 2);
            } else {
                Utils.getContext().registerReceiver(this.mAssistantStateReceiver, intentFilter);
            }
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
            Logger.error(TAG, Utils.format("%s: remote assistant is null", "getAssistantState"));
            return AssistantState.UNKNOWN;
        }
        try {
            return iAssistant.getAssistantState();
        } catch (Exception e) {
            Logger.error(TAG, Utils.format2Argv("getAssistantState", ErrorMessage.EXCEPTION), e);
            return AssistantState.UNKNOWN;
        }
    }

    public void registerAssistantStateChangedListener(AssistantStateChangedListener assistantStateChangedListener) {
        if (assistantStateChangedListener == null || this.mStateChangedListeners.contains(assistantStateChangedListener)) {
            return;
        }
        this.mStateChangedListeners.add(assistantStateChangedListener);
    }

    public void unregisterAssistantStateChangedListener(AssistantStateChangedListener assistantStateChangedListener) {
        this.mStateChangedListeners.remove(assistantStateChangedListener);
    }

    public synchronized void sendAppEvent(final AppEvent appEvent, final IResultCallback<Void, ErrorCode> iResultCallback) {
        Objects.requireNonNull(appEvent, "event must not be null");
        Handler handler = this.mHandler;
        if (handler == null) {
            Logger.error(TAG, Utils.format2Argv("sendAppEvent", ErrorMessage.CLIENT_HANDLER_NULL));
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.AssistantClient$$ExternalSyntheticLambda15
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifyError(ErrorCode.UNKNOWN, iResultCallback);
                }
            });
        } else {
            handler.post(new Runnable() { // from class: cerence.ark.assistant.api.AssistantClient$$ExternalSyntheticLambda16
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m54lambda$sendAppEvent$18$cerencearkassistantapiAssistantClient(iResultCallback, appEvent);
                }
            });
        }
    }

    /* JADX INFO: renamed from: lambda$sendAppEvent$18$cerence-ark-assistant-api-AssistantClient, reason: not valid java name */
    /* synthetic */ void m54lambda$sendAppEvent$18$cerencearkassistantapiAssistantClient(final IResultCallback iResultCallback, AppEvent appEvent) {
        if (!isRunning()) {
            Logger.error(TAG, Utils.format2Argv("sendAppEvent", ErrorMessage.ASSISTANT_NOT_STARTED));
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.AssistantClient$$ExternalSyntheticLambda8
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifyError(ErrorCode.ASSISTANT_NOT_STARTED, iResultCallback);
                }
            });
            return;
        }
        try {
            new StringSplitter().split(Utils.createEvent(appEvent.getName(), appEvent.getJsonData()), new ISplitter.SplitterListener() { // from class: cerence.ark.assistant.api.AssistantClient$$ExternalSyntheticLambda9
                @Override // cerence.ark.assistant.library.ipctransaction.ISplitter.SplitterListener
                public final void onSplit(Object obj, String str, int i, int i2) {
                    this.f$0.m53lambda$sendAppEvent$16$cerencearkassistantapiAssistantClient(iResultCallback, (String) obj, str, i, i2);
                }
            });
        } catch (Exception e) {
            Logger.error(TAG, Utils.formatException("sendAppEvent"), e);
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.AssistantClient$$ExternalSyntheticLambda10
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifyError(ErrorCode.EXCEPTION, iResultCallback);
                }
            });
        }
    }

    /* JADX INFO: renamed from: cerence.ark.assistant.api.AssistantClient$4, reason: invalid class name */
    class AnonymousClass4 extends ICallback.Stub {
        final /* synthetic */ IResultCallback val$callback;

        AnonymousClass4(IResultCallback iResultCallback) {
            this.val$callback = iResultCallback;
        }

        @Override // cerence.ark.assistant.service.ICallback
        public void onSuccess(String str) {
            final IResultCallback iResultCallback = this.val$callback;
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.AssistantClient$4$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifySuccess(iResultCallback);
                }
            });
        }

        @Override // cerence.ark.assistant.service.ICallback
        public void onError(final ErrorCode errorCode, String str) {
            final IResultCallback iResultCallback = this.val$callback;
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.AssistantClient$4$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifyError(errorCode, iResultCallback);
                }
            });
        }
    }

    /* JADX INFO: renamed from: lambda$sendAppEvent$16$cerence-ark-assistant-api-AssistantClient, reason: not valid java name */
    /* synthetic */ void m53lambda$sendAppEvent$16$cerencearkassistantapiAssistantClient(final IResultCallback iResultCallback, String str, String str2, int i, int i2) {
        try {
            this.mAssistant.sendAppEvent(str, str2, i, i2, new AnonymousClass4(iResultCallback));
        } catch (Exception e) {
            Logger.error(TAG, Utils.formatException("sendAppEvent"), e);
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.AssistantClient$$ExternalSyntheticLambda12
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifyError(ErrorCode.EXCEPTION, iResultCallback);
                }
            });
        }
    }

    boolean isRunning() {
        return getAssistantState() == AssistantState.RUNNING;
    }
}
