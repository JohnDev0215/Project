package cerence.ark.assistant.api;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.AudioAttributes;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Process;
import android.text.TextUtils;
import cerence.ark.assistant.api.context.IAssistantContext;
import cerence.ark.assistant.api.controller.IHostController;
import cerence.ark.assistant.api.data.DynamicWord;
import cerence.ark.assistant.api.data.RecoveryConfig;
import cerence.ark.assistant.api.dynamic.DefaultDynamicContentProvider;
import cerence.ark.assistant.api.dynamic.IDynamicContentProvider;
import cerence.ark.assistant.api.dynamic.observer.RadioStationObserver;
import cerence.ark.assistant.api.event.AppEvent;
import cerence.ark.assistant.api.hmi.HmiListener;
import cerence.ark.assistant.api.prompter.PrompterInstance;
import cerence.ark.assistant.api.prompter.TtsVoice;
import cerence.ark.assistant.api.token.ITokenProvider;
import cerence.ark.assistant.common.Logger;
import cerence.ark.assistant.common.Scheduler;
import cerence.ark.assistant.common.callback.IResultCallback;
import cerence.ark.assistant.common.callback.Result;
import cerence.ark.assistant.core.DefaultDhi;
import cerence.ark.assistant.core.HmiListenerImpl;
import cerence.ark.assistant.core.NotificationListenerImpl;
import cerence.ark.assistant.core.ParamChecker;
import cerence.ark.assistant.core.PrompterProxy;
import cerence.ark.assistant.core.RecoveryTokenProviderImpl;
import cerence.ark.assistant.core.TokenProviderProxy;
import cerence.ark.assistant.core.UnhandledRequestListenerImpl;
import cerence.ark.assistant.core.UpdateListenerProxy;
import cerence.ark.assistant.core.UpdaterProxy;
import cerence.ark.assistant.core.dcc.AppUpdater;
import cerence.ark.assistant.core.dcc.ContactUpdater;
import cerence.ark.assistant.core.dcc.ContentUpdater;
import cerence.ark.assistant.core.dcc.DccUtils;
import cerence.ark.assistant.core.dcc.IDCCallback;
import cerence.ark.assistant.core.dcc.IDCUpdater;
import cerence.ark.assistant.core.dcc.MusicUpdater;
import cerence.ark.assistant.core.dcc.RadioStationUpdater;
import cerence.ark.assistant.dm.data.DmRecoveryModeParams;
import cerence.ark.assistant.framework.AssistantState;
import cerence.ark.assistant.framework.data.AssistantConfiguration;
import cerence.ark.assistant.framework.data.Config;
import cerence.ark.assistant.framework.data.ErrorCode;
import cerence.ark.assistant.framework.data.ErrorInfo;
import cerence.ark.assistant.framework.data.LogConsumer;
import cerence.ark.assistant.framework.data.RppLocationType;
import cerence.ark.assistant.framework.data.VoiceConfig;
import cerence.ark.assistant.framework.data.wuw.WuwConfig;
import cerence.ark.assistant.framework.impl.updater.RecoveryUpdaterImpl;
import cerence.ark.assistant.framework.impl.updater.UpdateListenerImpl;
import cerence.ark.assistant.framework.prompter.BasePrompter;
import cerence.ark.assistant.framework.prompter.IPrompter;
import cerence.ark.assistant.framework.updater.IRecoveryUpdater;
import cerence.ark.assistant.framework.updater.IUpdateListener;
import cerence.ark.assistant.framework.updater.IUpdater;
import cerence.ark.assistant.library.ErrorMessage;
import cerence.ark.assistant.library.JsonKey;
import cerence.ark.assistant.library.Utils;
import cerence.ark.assistant.library.ipctransaction.ISplitter;
import cerence.ark.assistant.library.ipctransaction.ipclist.ListSplitter;
import cerence.ark.assistant.library.ipctransaction.ipcstring.StringSplitter;
import cerence.ark.assistant.service.IAssistant;
import cerence.ark.assistant.service.ICallback;
import cerence.ark.assistant.service.IErrorListener;
import cerence.ark.assistant.service.IPrompterService;
import cerence.ark.assistant.service.RemoteResult;
import cerence.ark.assistant.service.RemoteUpdater;
import cerence.ark.assistant.service.SpeechService;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public final class ArkAssistant {
    private static final String TAG = "ArkAssistant";
    private static Handler sHandler;
    private IDCUpdater<List<String>> mAppUpdater;
    private IAssistant mAssistant;
    private final AssistantClient mAssistantClient;
    private final List<AssistantListener> mAssistantListenerList;
    private final Object mBindingLock;
    private ServiceConnection mConnection;
    private final Object mConnectionLock;
    private IDCUpdater<List<String>> mContactUpdater;
    private IDCUpdater<List<String>> mContentUpdater;
    private final Object mDialogPrompterLock;
    private List<BasePrompter> mDialogPrompters;
    private IDynamicContentProvider mDynamicContentProvider;
    private HmiListener mHmiListener;
    private boolean mIsBinding;
    private IDCUpdater<List<String>> mMusicUpdater;
    private final List<IObserver> mObservers;
    private IDCUpdater<List<String>> mRadioStationUpdater;

    /* synthetic */ ArkAssistant(AnonymousClass1 anonymousClass1) {
        this();
    }

    private ArkAssistant() {
        this.mAssistantListenerList = new CopyOnWriteArrayList();
        this.mBindingLock = new Object();
        this.mConnectionLock = new Object();
        this.mObservers = new ArrayList();
        this.mAssistantClient = new AssistantClient();
        this.mDialogPrompterLock = new Object();
    }

    static {
        createAssistantThread();
    }

    public static ArkAssistant get() {
        return SingletonInstance.INSTANCE;
    }

    public void queryAvailableAsrLanguages(final IResultCallback<List<String>, ErrorCode> iResultCallback) {
        sHandler.post(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda62
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m36x3c4fa1c7(iResultCallback);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$queryAvailableAsrLanguages$3$cerence-ark-assistant-api-ArkAssistant, reason: not valid java name */
    /* synthetic */ void m36x3c4fa1c7(final IResultCallback iResultCallback) {
        if (!Utils.isInitialized(this.mAssistantClient.getAssistantState())) {
            Logger.error(TAG, Utils.format("queryAvailableAsrLanguages: %s", ErrorMessage.ASSISTANT_NOT_INITIALIZED));
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda10
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifyError(ErrorCode.ASSISTANT_NOT_INITIALIZED, iResultCallback);
                }
            });
            return;
        }
        try {
            List<String> availableAsrLanguages = this.mAssistant.getAvailableAsrLanguages();
            final ArrayList arrayList = new ArrayList();
            if (availableAsrLanguages != null && !availableAsrLanguages.isEmpty()) {
                arrayList.addAll(availableAsrLanguages);
            }
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda12
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifySuccess(arrayList, iResultCallback);
                }
            });
        } catch (Exception e) {
            Logger.error(TAG, "queryAvailableAsrLanguages: exception", e);
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda13
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifyError(ErrorCode.EXCEPTION, iResultCallback);
                }
            });
        }
    }

    /* JADX INFO: renamed from: lambda$queryAvailableTtsLanguages$4$cerence-ark-assistant-api-ArkAssistant, reason: not valid java name */
    /* synthetic */ void m37x802c7759(IResultCallback iResultCallback) {
        this.mAssistantClient.m51x3cfcf135(iResultCallback);
    }

    public void queryAvailableTtsLanguages(final IResultCallback<List<String>, ErrorCode> iResultCallback) {
        sHandler.post(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda67
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m37x802c7759(iResultCallback);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$queryAvailableTtsVoices$5$cerence-ark-assistant-api-ArkAssistant, reason: not valid java name */
    /* synthetic */ void m38x99011488(String str, IResultCallback iResultCallback) {
        this.mAssistantClient.m52x7ae0dde9(str, iResultCallback);
    }

    public void queryAvailableTtsVoices(final String str, final IResultCallback<List<TtsVoice>, ErrorCode> iResultCallback) {
        sHandler.post(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda24
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m38x99011488(str, iResultCallback);
            }
        });
    }

    private static synchronized void createAssistantThread() {
        if (sHandler != null) {
            Logger.warn(TAG, "ca_assistant thread has been created");
            return;
        }
        HandlerThread handlerThread = new HandlerThread("ca_assistant");
        handlerThread.start();
        sHandler = new Handler(handlerThread.getLooper());
    }

    public void initializeVoiceAssistant(Context context, Config config, IResultCallback<Void, ErrorCode> iResultCallback) {
        initializeVoiceAssistant(context, config, null, iResultCallback);
    }

    public void initializeVoiceAssistant(final Context context, final Config config, final ITokenProvider iTokenProvider, final IResultCallback<Void, ErrorCode> iResultCallback) {
        sHandler.post(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda59
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m30xc0366941(context, config, iResultCallback, iTokenProvider);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$initializeVoiceAssistant$9$cerence-ark-assistant-api-ArkAssistant, reason: not valid java name */
    /* synthetic */ void m30xc0366941(Context context, Config config, final IResultCallback iResultCallback, ITokenProvider iTokenProvider) {
        Objects.requireNonNull(context, "Context must not be null");
        int iMyPid = Process.myPid();
        String str = TAG;
        Logger.info(str, Utils.format("%s: sdk_version = %s, pid = %d", "initializeVoiceAssistant", cerence.ark.assistant.service.BuildConfig.SDK_VERSION, Integer.valueOf(iMyPid)));
        Utils.init(context);
        Utils.setClientPid(iMyPid);
        final Result<Void, ErrorCode> resultCheckConfigValid = ParamChecker.checkConfigValid(config);
        if (!resultCheckConfigValid.isSuccess()) {
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda20
                @Override // java.lang.Runnable
                public final void run() {
                    ArkAssistant.lambda$initializeVoiceAssistant$6(iResultCallback, resultCheckConfigValid);
                }
            });
            return;
        }
        if (Utils.isInitialized(this.mAssistantClient.getAssistantState())) {
            Logger.error(str, Utils.format2Argv("initializeVoiceAssistant", ErrorMessage.ASSISTANT_ALREADY_INITIALIZED));
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda21
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifyError(ErrorCode.ASSISTANT_ALREADY_INITIALIZED, iResultCallback);
                }
            });
            return;
        }
        synchronized (this.mBindingLock) {
            this.mIsBinding = true;
        }
        if (Utils.getContext().bindService(new Intent(Utils.getContext(), (Class<?>) SpeechService.class), createServiceConnection(), 1)) {
            synchronized (this.mBindingLock) {
                while (this.mIsBinding) {
                    try {
                        Logger.info(TAG, "wait for bind speech service");
                        this.mBindingLock.wait();
                    } catch (InterruptedException e) {
                        Logger.error(TAG, "interrupted", e);
                        Thread.currentThread().interrupt();
                    }
                }
                Logger.info(TAG, "wait for finish");
            }
            initializeVoiceAssistantImpl(config, iTokenProvider, iResultCallback);
            return;
        }
        Logger.error(str, Utils.format2Argv("initializeVoiceAssistant", ErrorMessage.BIND_SPEECH_SERVICE_ERROR));
        Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda23
            @Override // java.lang.Runnable
            public final void run() {
                Utils.notifyError(ErrorCode.BIND_SPEECH_SERVICE_ERROR, iResultCallback);
            }
        });
    }

    static /* synthetic */ void lambda$initializeVoiceAssistant$6(IResultCallback iResultCallback, Result result) {
        if (iResultCallback != null) {
            iResultCallback.onResult(result);
        }
    }

    /* JADX INFO: renamed from: cerence.ark.assistant.api.ArkAssistant$1, reason: invalid class name */
    class AnonymousClass1 implements ServiceConnection {
        AnonymousClass1() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Logger.info(ArkAssistant.TAG, "onServiceConnected: bind service successful");
            synchronized (ArkAssistant.this.mConnectionLock) {
                if (ArkAssistant.this.mConnection != null) {
                    Utils.getContext().unbindService(ArkAssistant.this.mConnection);
                }
                ArkAssistant.this.mConnection = this;
            }
            ArkAssistant.this.mAssistant = IAssistant.Stub.asInterface(iBinder);
            synchronized (ArkAssistant.this.mBindingLock) {
                ArkAssistant.this.mIsBinding = false;
                ArkAssistant.this.mBindingLock.notifyAll();
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            Logger.error(ArkAssistant.TAG, "onServiceDisconnected");
            synchronized (ArkAssistant.this.mConnectionLock) {
                if (ArkAssistant.this.mConnection != null) {
                    Utils.getContext().unbindService(ArkAssistant.this.mConnection);
                }
                ArkAssistant.this.mConnection = null;
            }
            ArkAssistant.this.mAssistant = null;
            final ErrorInfo errorInfo = new ErrorInfo();
            errorInfo.setErrorCode(ErrorInfo.ErrorCode.FATAL_ASSISTANT_STOPPED).setErrorMessage("assistant is stopped");
            ArkAssistant.this.notifyAssistantListeners(new Consumer() { // from class: cerence.ark.assistant.api.ArkAssistant$1$$ExternalSyntheticLambda0
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((AssistantListener) obj).onError(errorInfo);
                }
            });
        }
    }

    private ServiceConnection createServiceConnection() {
        return new AnonymousClass1();
    }

    private void initializeVoiceAssistantImpl(Config config, ITokenProvider iTokenProvider, final IResultCallback<Void, ErrorCode> iResultCallback) {
        this.mAssistantClient.setAssistant(this.mAssistant);
        try {
            this.mAssistant.initialize(config, new AnonymousClass2(), iTokenProvider == null ? null : new TokenProviderProxy(iTokenProvider), new AnonymousClass3("initializeVoiceAssistantImpl", iResultCallback));
        } catch (Exception e) {
            Logger.error(TAG, Utils.formatException("initializeVoiceAssistantImpl"), e);
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda64
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifyError(ErrorCode.EXCEPTION, iResultCallback);
                }
            });
        }
    }

    /* JADX INFO: renamed from: cerence.ark.assistant.api.ArkAssistant$2, reason: invalid class name */
    class AnonymousClass2 extends IErrorListener.Stub {
        AnonymousClass2() {
        }

        @Override // cerence.ark.assistant.service.IErrorListener
        public void onError(final ErrorInfo errorInfo) {
            Logger.error(ArkAssistant.TAG, "onError: " + errorInfo);
            ArkAssistant.this.notifyAssistantListeners(new Consumer() { // from class: cerence.ark.assistant.api.ArkAssistant$2$$ExternalSyntheticLambda0
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((AssistantListener) obj).onError(errorInfo);
                }
            });
        }
    }

    /* JADX INFO: renamed from: cerence.ark.assistant.api.ArkAssistant$3, reason: invalid class name */
    class AnonymousClass3 extends ICallback.Stub {
        final /* synthetic */ IResultCallback val$callback;
        final /* synthetic */ String val$methodName;

        AnonymousClass3(String str, IResultCallback iResultCallback) {
            this.val$methodName = str;
            this.val$callback = iResultCallback;
        }

        @Override // cerence.ark.assistant.service.ICallback
        public void onSuccess(String str) {
            Logger.info(ArkAssistant.TAG, Utils.format("%s: initialize voice assistant successful", this.val$methodName));
            Logger.info(ArkAssistant.TAG, ArkAssistant.this.getVersion());
            ArkAssistant.this.notifyAssistantListeners(new Consumer() { // from class: cerence.ark.assistant.api.ArkAssistant$3$$ExternalSyntheticLambda1
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((AssistantListener) obj).onInitialized();
                }
            });
            final IResultCallback iResultCallback = this.val$callback;
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$3$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifySuccess(iResultCallback);
                }
            });
        }

        @Override // cerence.ark.assistant.service.ICallback
        public void onError(final ErrorCode errorCode, String str) {
            final IResultCallback iResultCallback = this.val$callback;
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$3$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifyError(errorCode, iResultCallback);
                }
            });
        }
    }

    public void startVoiceAssistant(final AssistantConfiguration assistantConfiguration, final HmiListener hmiListener, final Handler handler, final IHostController iHostController, final IResultCallback<Void, ErrorCode> iResultCallback) {
        sHandler.post(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda18
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m43x77c747b6(hmiListener, iHostController, assistantConfiguration, iResultCallback, handler);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$startVoiceAssistant$17$cerence-ark-assistant-api-ArkAssistant, reason: not valid java name */
    /* synthetic */ void m43x77c747b6(final HmiListener hmiListener, final IHostController iHostController, final AssistantConfiguration assistantConfiguration, final IResultCallback iResultCallback, final Handler handler) {
        Objects.requireNonNull(hmiListener, "hmiListener must not be null");
        Objects.requireNonNull(iHostController, "controller must not be null");
        if (Objects.isNull(assistantConfiguration)) {
            Logger.error(TAG, Utils.format2Argv("startVoiceAssistant", ErrorMessage.ASSISTANT_CONFIGURATION_NULL));
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifyError(ErrorCode.ASSISTANT_CONFIGURATION_NULL, iResultCallback);
                }
            });
            return;
        }
        if (TextUtils.isEmpty(assistantConfiguration.getAsrLanguage())) {
            Logger.error(TAG, Utils.format2Argv("startVoiceAssistant", ErrorMessage.LANGUAGE_NULL));
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda11
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifyError(ErrorCode.LANGUAGE_NULL, iResultCallback);
                }
            });
            return;
        }
        AssistantState assistantState = this.mAssistantClient.getAssistantState();
        if (!Utils.isInitialized(assistantState)) {
            Logger.error(TAG, Utils.format2Argv("startVoiceAssistant", ErrorMessage.ASSISTANT_NOT_INITIALIZED));
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda22
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifyError(ErrorCode.ASSISTANT_NOT_INITIALIZED, iResultCallback);
                }
            });
        } else if (assistantState == AssistantState.RUNNING) {
            Logger.error(TAG, Utils.format2Argv("startVoiceAssistant", ErrorMessage.ASSISTANT_ALREADY_STARTED));
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda33
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifyError(ErrorCode.ASSISTANT_ALREADY_STARTED, iResultCallback);
                }
            });
        } else {
            Utils.logMethodBegin(TAG, "startVoiceAssistant");
            this.mAssistantClient.registerRequestListener(iHostController, new DefaultDhi(hmiListener, Utils.getContext()), null, new IResultCallback() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda44
                @Override // cerence.ark.assistant.common.callback.IResultCallback
                public final void onResult(Result result) {
                    this.f$0.m42x20a956d7(assistantConfiguration, hmiListener, handler, iHostController, iResultCallback, result);
                }
            });
        }
    }

    /* JADX INFO: renamed from: lambda$startVoiceAssistant$16$cerence-ark-assistant-api-ArkAssistant, reason: not valid java name */
    /* synthetic */ void m42x20a956d7(AssistantConfiguration assistantConfiguration, HmiListener hmiListener, Handler handler, IHostController iHostController, final IResultCallback iResultCallback, final Result result) {
        if (result.isSuccess()) {
            startVoiceAssistantImpl(assistantConfiguration, hmiListener, handler, iHostController, iResultCallback);
        } else {
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda15
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifyError((ErrorCode) result.getErrorCode(), iResultCallback);
                }
            });
        }
    }

    private void startVoiceAssistantImpl(AssistantConfiguration assistantConfiguration, HmiListener hmiListener, Handler handler, IHostController iHostController, final IResultCallback<Void, ErrorCode> iResultCallback) {
        try {
            this.mHmiListener = hmiListener;
            hmiListener.activate();
            this.mAssistant.setUnhandledRequestListener(new UnhandledRequestListenerImpl(iHostController, this.mAssistant));
            this.mAssistant.startVoiceAssistant(assistantConfiguration, new NotificationListenerImpl(hmiListener, handler), new HmiListenerImpl(hmiListener, this.mAssistant, handler), new AnonymousClass4(iHostController, assistantConfiguration, iResultCallback));
        } catch (Exception e) {
            Logger.error(TAG, Utils.formatException("startVoiceAssistantImpl"), e);
            deactivateHmi();
            this.mAssistantClient.deactivateDomains();
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda82
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifyError(ErrorCode.EXCEPTION, iResultCallback);
                }
            });
        }
    }

    /* JADX INFO: renamed from: cerence.ark.assistant.api.ArkAssistant$4, reason: invalid class name */
    class AnonymousClass4 extends ICallback.Stub {
        final /* synthetic */ IResultCallback val$callback;
        final /* synthetic */ AssistantConfiguration val$configuration;
        final /* synthetic */ IHostController val$controller;

        AnonymousClass4(IHostController iHostController, AssistantConfiguration assistantConfiguration, IResultCallback iResultCallback) {
            this.val$controller = iHostController;
            this.val$configuration = assistantConfiguration;
            this.val$callback = iResultCallback;
        }

        @Override // cerence.ark.assistant.service.ICallback
        public void onSuccess(String str) {
            ArkAssistant.this.mDynamicContentProvider = this.val$controller.loadDynamicContentProvider();
            if (ArkAssistant.this.mDynamicContentProvider != null) {
                ArkAssistant.this.mDynamicContentProvider.activate();
                ArkAssistant.this.mContentUpdater = new ContentUpdater(ArkAssistant.this.mDynamicContentProvider);
                ArkAssistant.this.mContactUpdater = new ContactUpdater(ArkAssistant.this.mDynamicContentProvider);
                ArkAssistant.this.mMusicUpdater = new MusicUpdater(ArkAssistant.this.mDynamicContentProvider);
                ArkAssistant.this.mAppUpdater = new AppUpdater(ArkAssistant.this.mDynamicContentProvider);
                ArkAssistant.this.mRadioStationUpdater = new RadioStationUpdater(ArkAssistant.this.mDynamicContentProvider);
                List<IObserver> observers = this.val$configuration.getObservers();
                ArkAssistant.this.mObservers.clear();
                if (observers != null && !observers.isEmpty()) {
                    for (IObserver iObserver : observers) {
                        if (iObserver == null) {
                            Logger.warn(ArkAssistant.TAG, "registerObservers: observer is null");
                        } else {
                            if ((iObserver instanceof RadioStationObserver) && (ArkAssistant.this.mDynamicContentProvider instanceof DefaultDynamicContentProvider)) {
                                ((DefaultDynamicContentProvider) ArkAssistant.this.mDynamicContentProvider).setRadioStationObserver((RadioStationObserver) iObserver);
                            }
                            iObserver.activate();
                            ArkAssistant.this.mObservers.add(iObserver);
                        }
                    }
                }
            } else {
                Logger.warn(ArkAssistant.TAG, "dynamic content provider is null");
            }
            ArkAssistant.this.notifyAssistantListeners(new Consumer() { // from class: cerence.ark.assistant.api.ArkAssistant$4$$ExternalSyntheticLambda0
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((AssistantListener) obj).onStarted();
                }
            });
            final IResultCallback iResultCallback = this.val$callback;
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$4$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifySuccess(iResultCallback);
                }
            });
        }

        @Override // cerence.ark.assistant.service.ICallback
        public void onError(final ErrorCode errorCode, String str) {
            ArkAssistant.this.deactivateHmi();
            ArkAssistant.this.mAssistantClient.deactivateDomains();
            final IResultCallback iResultCallback = this.val$callback;
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$4$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifyError(errorCode, iResultCallback);
                }
            });
        }
    }

    public void stopVoiceAssistant(final IResultCallback<Void, ErrorCode> iResultCallback) {
        sHandler.post(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda14
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m44x6a8100f7(iResultCallback);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$stopVoiceAssistant$21$cerence-ark-assistant-api-ArkAssistant, reason: not valid java name */
    /* synthetic */ void m44x6a8100f7(final IResultCallback iResultCallback) {
        if (!this.mAssistantClient.isRunning()) {
            Logger.error(TAG, Utils.format2Argv("stopVoiceAssistant", ErrorMessage.ASSISTANT_NOT_STARTED));
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda60
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifyError(ErrorCode.ASSISTANT_NOT_STARTED, iResultCallback);
                }
            });
            return;
        }
        Utils.logMethodBegin(TAG, "stopVoiceAssistant");
        try {
            this.mAssistant.stopVoiceAssistant(new AnonymousClass5(iResultCallback));
        } catch (Exception e) {
            Logger.error(TAG, Utils.formatException("stopVoiceAssistant"), e);
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda61
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifyError(ErrorCode.EXCEPTION, iResultCallback);
                }
            });
        }
    }

    /* JADX INFO: renamed from: cerence.ark.assistant.api.ArkAssistant$5, reason: invalid class name */
    class AnonymousClass5 extends ICallback.Stub {
        final /* synthetic */ IResultCallback val$callback;

        AnonymousClass5(IResultCallback iResultCallback) {
            this.val$callback = iResultCallback;
        }

        @Override // cerence.ark.assistant.service.ICallback
        public void onSuccess(String str) {
            ArkAssistant.this.deactivateHmi();
            ArkAssistant.this.mAssistantClient.deactivateDomains();
            ArkAssistant.this.deactivateObservers();
            ArkAssistant.this.destroyDynamicContentProvider();
            ArkAssistant.this.notifyAssistantListeners(new Consumer() { // from class: cerence.ark.assistant.api.ArkAssistant$5$$ExternalSyntheticLambda0
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((AssistantListener) obj).onStopped();
                }
            });
            final IResultCallback iResultCallback = this.val$callback;
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$5$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifySuccess(iResultCallback);
                }
            });
        }

        @Override // cerence.ark.assistant.service.ICallback
        public void onError(final ErrorCode errorCode, String str) {
            final IResultCallback iResultCallback = this.val$callback;
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$5$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifyError(errorCode, iResultCallback);
                }
            });
        }
    }

    public String getVersion() {
        JSONObject jSONObject = new JSONObject();
        if (!Utils.isInitialized(this.mAssistantClient.getAssistantState())) {
            Logger.error(TAG, Utils.format2Argv("getVersion", ErrorMessage.ASSISTANT_NOT_INITIALIZED));
            return jSONObject.toString();
        }
        try {
            jSONObject.put("name", BuildConfig.COMMPENT_NAME);
            jSONObject.put("version", BuildConfig.VERSION);
            jSONObject.put(JsonKey.VERSION_TAG, BuildConfig.TAG_NAME);
            jSONObject.put(JsonKey.VERSION_COMMIT, BuildConfig.COMMIT_ID);
            String version = this.mAssistant.getVersion();
            if (!TextUtils.isEmpty(version)) {
                JSONArray jSONArray = new JSONArray();
                jSONArray.put(new JSONObject(version));
                jSONObject.put(JsonKey.VERSION_COMPONENTS, jSONArray);
            } else {
                Logger.error(TAG, Utils.format("%s: get components is empty", "getVersion"));
            }
        } catch (Exception e) {
            Logger.error(TAG, Utils.format("%s: ", "getVersion"), e);
        }
        return jSONObject.toString();
    }

    public AssistantState getAssistantState() {
        return this.mAssistantClient.getAssistantState();
    }

    public void sendAppEvent(AppEvent appEvent, IResultCallback<Void, ErrorCode> iResultCallback) {
        this.mAssistantClient.sendAppEvent(appEvent, iResultCallback);
    }

    public Result<Void, ErrorCode> setVoiceConfig(VoiceConfig voiceConfig) {
        if (voiceConfig == null) {
            Logger.error(TAG, Utils.format("%s: voiceConfig is empty", "setVoiceConfig"));
            return Utils.buildErrorResult(ErrorCode.VOICE_CONFIG_INVALID);
        }
        if (!this.mAssistantClient.isRunning()) {
            Logger.error(TAG, Utils.format2Argv("setVoiceConfig", ErrorMessage.ASSISTANT_NOT_STARTED));
            return Utils.buildErrorResult(ErrorCode.ASSISTANT_NOT_STARTED);
        }
        try {
            return Utils.convertToResult(this.mAssistant.setVoiceConfig(voiceConfig));
        } catch (Exception e) {
            Logger.error(TAG, Utils.formatException("setVoiceConfig"), e);
            return Utils.buildErrorResult(ErrorCode.UNKNOWN);
        }
    }

    public List<BasePrompter> getDialogPrompters() {
        List<PrompterInstance> dialogPrompterInstances;
        if (!Utils.isInitialized(this.mAssistantClient.getAssistantState())) {
            Logger.error(TAG, Utils.format2Argv("getDialogPrompters", ErrorMessage.ASSISTANT_NOT_INITIALIZED));
            return new ArrayList();
        }
        synchronized (this.mDialogPrompterLock) {
            List<BasePrompter> list = this.mDialogPrompters;
            if (list != null && !list.isEmpty()) {
                return this.mDialogPrompters;
            }
            try {
                this.mDialogPrompters = new ArrayList();
                dialogPrompterInstances = this.mAssistant.getDialogPrompterInstances();
            } catch (Exception e) {
                Logger.error(TAG, "getDialogPrompters: exception", e);
            }
            if (dialogPrompterInstances == null) {
                Logger.error(TAG, "getDialogPrompters: dialog prompter instances is empty");
                return this.mDialogPrompters;
            }
            for (PrompterInstance prompterInstance : dialogPrompterInstances) {
                IPrompterService iPrompterServiceCreatePrompter = this.mAssistant.createPrompter(prompterInstance, null);
                if (iPrompterServiceCreatePrompter == null) {
                    Logger.warn(TAG, "getDialogPrompters: prompterService is null, prompterInstance: " + prompterInstance);
                } else {
                    this.mDialogPrompters.add(new PrompterProxy(prompterInstance, iPrompterServiceCreatePrompter));
                }
            }
            return this.mDialogPrompters;
        }
    }

    public List<PrompterInstance> getAvailablePrompterInstances() {
        return this.mAssistantClient.getAvailablePrompterInstances();
    }

    public IPrompter createPrompter(PrompterInstance prompterInstance, int i) {
        return this.mAssistantClient.createPrompter(prompterInstance, i);
    }

    public IPrompter createPrompter(PrompterInstance prompterInstance, AudioAttributes audioAttributes) {
        return this.mAssistantClient.createPrompter(prompterInstance, audioAttributes);
    }

    public void destroyPrompter(IPrompter iPrompter) {
        this.mAssistantClient.destroyPrompter(iPrompter);
    }

    public void registerAssistantListener(AssistantListener assistantListener) {
        if (assistantListener == null || this.mAssistantListenerList.contains(assistantListener)) {
            Logger.warn(TAG, "registerAssistantListener: assistantListener is null or already registered");
        } else {
            Logger.info(TAG, "registerAssistantListener: " + assistantListener.hashCode());
            this.mAssistantListenerList.add(assistantListener);
        }
    }

    public void unregisterAssistantListener(AssistantListener assistantListener) {
        if (assistantListener == null) {
            Logger.warn(TAG, "unregisterAssistantListener: assistantListener is null");
        } else {
            Logger.info(TAG, "unregisterAssistantListener: " + assistantListener.hashCode());
            this.mAssistantListenerList.remove(assistantListener);
        }
    }

    public void destroyVoiceAssistant(final IResultCallback<Void, ErrorCode> iResultCallback) {
        sHandler.post(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda19
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m29xf3d966f2(iResultCallback);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$destroyVoiceAssistant$24$cerence-ark-assistant-api-ArkAssistant, reason: not valid java name */
    /* synthetic */ void m29xf3d966f2(final IResultCallback iResultCallback) {
        if (!Utils.isInitialized(this.mAssistantClient.getAssistantState())) {
            Logger.error(TAG, Utils.format2Argv("destroyVoiceAssistant", ErrorMessage.ASSISTANT_NOT_INITIALIZED));
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda5
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifyError(ErrorCode.ASSISTANT_NOT_INITIALIZED, iResultCallback);
                }
            });
            return;
        }
        Utils.logMethodBegin(TAG, "destroyVoiceAssistant");
        try {
            this.mAssistant.destroy(new AnonymousClass6(iResultCallback));
        } catch (Exception e) {
            Logger.error(TAG, Utils.formatException("destroyVoiceAssistant"), e);
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda6
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifyError(ErrorCode.EXCEPTION, iResultCallback);
                }
            });
        }
    }

    /* JADX INFO: renamed from: cerence.ark.assistant.api.ArkAssistant$6, reason: invalid class name */
    class AnonymousClass6 extends ICallback.Stub {
        final /* synthetic */ IResultCallback val$callback;

        AnonymousClass6(IResultCallback iResultCallback) {
            this.val$callback = iResultCallback;
        }

        @Override // cerence.ark.assistant.service.ICallback
        public void onSuccess(String str) {
            ArkAssistant.this.deactivateHmi();
            ArkAssistant.this.mAssistantClient.destroy();
            ArkAssistant.this.deactivateObservers();
            ArkAssistant.this.destroyDynamicContentProvider();
            ArkAssistant.this.clearDialogPrompters();
            synchronized (ArkAssistant.this.mConnectionLock) {
                if (ArkAssistant.this.mConnection != null) {
                    Utils.getContext().unbindService(ArkAssistant.this.mConnection);
                }
                ArkAssistant.this.mConnection = null;
            }
            ArkAssistant.this.mAssistant = null;
            ArkAssistant.this.notifyAssistantListeners(new Consumer() { // from class: cerence.ark.assistant.api.ArkAssistant$6$$ExternalSyntheticLambda0
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((AssistantListener) obj).onDestroyed();
                }
            });
            final IResultCallback iResultCallback = this.val$callback;
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$6$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifySuccess(iResultCallback);
                }
            });
        }

        @Override // cerence.ark.assistant.service.ICallback
        public void onError(final ErrorCode errorCode, String str) {
            final IResultCallback iResultCallback = this.val$callback;
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$6$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifyError(errorCode, iResultCallback);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void deactivateObservers() {
        for (IObserver iObserver : this.mObservers) {
            if (iObserver == null) {
                Logger.warn(TAG, "deactivateObservers: observer is null");
            } else {
                iObserver.deactivate();
            }
        }
        this.mObservers.clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void destroyDynamicContentProvider() {
        IDynamicContentProvider iDynamicContentProvider = this.mDynamicContentProvider;
        if (iDynamicContentProvider != null) {
            iDynamicContentProvider.deactivate();
            this.mDynamicContentProvider = null;
        }
        IDCUpdater<List<String>> iDCUpdater = this.mContactUpdater;
        if (iDCUpdater != null) {
            iDCUpdater.destroy();
            this.mContactUpdater = null;
        }
        IDCUpdater<List<String>> iDCUpdater2 = this.mMusicUpdater;
        if (iDCUpdater2 != null) {
            iDCUpdater2.destroy();
            this.mMusicUpdater = null;
        }
        IDCUpdater<List<String>> iDCUpdater3 = this.mAppUpdater;
        if (iDCUpdater3 != null) {
            iDCUpdater3.destroy();
            this.mAppUpdater = null;
        }
        IDCUpdater<List<String>> iDCUpdater4 = this.mRadioStationUpdater;
        if (iDCUpdater4 != null) {
            iDCUpdater4.destroy();
            this.mRadioStationUpdater = null;
        }
        IDCUpdater<List<String>> iDCUpdater5 = this.mContentUpdater;
        if (iDCUpdater5 != null) {
            iDCUpdater5.destroy();
            this.mContentUpdater = null;
        }
    }

    public void updateAssistantContext(final IAssistantContext iAssistantContext, final IResultCallback<Void, ErrorCode> iResultCallback) {
        sHandler.post(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda9
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m46x4242acc3(iAssistantContext, iResultCallback);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$updateAssistantContext$32$cerence-ark-assistant-api-ArkAssistant, reason: not valid java name */
    /* synthetic */ void m46x4242acc3(final IAssistantContext iAssistantContext, final IResultCallback iResultCallback) {
        if (iAssistantContext == null) {
            Logger.error(TAG, Utils.format("%s: assistant context is null", "updateAssistantContext"));
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda29
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifyError(ErrorCode.PARAMETER_INVALID, iResultCallback);
                }
            });
            return;
        }
        if (iAssistantContext.getContextType() == null) {
            Logger.error(TAG, Utils.format("%s: context type is empty", "updateAssistantContext"));
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda30
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifyError(ErrorCode.PARAMETER_INVALID, iResultCallback);
                }
            });
            return;
        }
        if (!this.mAssistantClient.isRunning()) {
            Logger.error(TAG, Utils.format2Argv("updateAssistantContext", ErrorMessage.ASSISTANT_NOT_STARTED));
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda31
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifyError(ErrorCode.ASSISTANT_NOT_STARTED, iResultCallback);
                }
            });
            return;
        }
        try {
            String jsonString = iAssistantContext.toJsonString();
            if (jsonString == null) {
                Logger.error(TAG, Utils.format("%s: context data is null", "updateAssistantContext"));
                Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda34
                    @Override // java.lang.Runnable
                    public final void run() {
                        Utils.notifyError(ErrorCode.PARAMETER_INVALID, iResultCallback);
                    }
                });
            } else {
                new StringSplitter().split(jsonString, new ISplitter.SplitterListener() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda35
                    @Override // cerence.ark.assistant.library.ipctransaction.ISplitter.SplitterListener
                    public final void onSplit(Object obj, String str, int i, int i2) {
                        this.f$0.m45xeb24bbe4(iAssistantContext, iResultCallback, (String) obj, str, i, i2);
                    }
                });
            }
        } catch (JSONException e) {
            Logger.error(TAG, Utils.formatException("updateAssistantContext"), e);
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda32
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifyError(ErrorCode.EXCEPTION, iResultCallback);
                }
            });
        }
    }

    /* JADX INFO: renamed from: cerence.ark.assistant.api.ArkAssistant$7, reason: invalid class name */
    class AnonymousClass7 extends ICallback.Stub {
        final /* synthetic */ IResultCallback val$callback;

        AnonymousClass7(IResultCallback iResultCallback) {
            this.val$callback = iResultCallback;
        }

        @Override // cerence.ark.assistant.service.ICallback
        public void onSuccess(String str) {
            final IResultCallback iResultCallback = this.val$callback;
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$7$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifySuccess(iResultCallback);
                }
            });
        }

        @Override // cerence.ark.assistant.service.ICallback
        public void onError(final ErrorCode errorCode, String str) {
            final IResultCallback iResultCallback = this.val$callback;
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$7$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifyError(errorCode, iResultCallback);
                }
            });
        }
    }

    /* JADX INFO: renamed from: lambda$updateAssistantContext$31$cerence-ark-assistant-api-ArkAssistant, reason: not valid java name */
    /* synthetic */ void m45xeb24bbe4(IAssistantContext iAssistantContext, final IResultCallback iResultCallback, String str, String str2, int i, int i2) {
        try {
            this.mAssistant.updateAssistantContext(iAssistantContext.getContextType(), str, str2, i, i2, new AnonymousClass7(iResultCallback));
        } catch (Exception e) {
            Logger.error(TAG, Utils.formatException("updateAssistantContext"), e);
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda26
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifyError(ErrorCode.EXCEPTION, iResultCallback);
                }
            });
        }
    }

    public void setWuwConfig(final WuwConfig wuwConfig, final IResultCallback<Void, ErrorCode> iResultCallback) {
        final String str = "setWuwConfig";
        if (wuwConfig == null) {
            Logger.error(TAG, Utils.format("%s: wuwConfig is null", "setWuwConfig"));
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda16
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifyError(ErrorCode.PARAMETER_INVALID, iResultCallback);
                }
            });
        } else {
            sHandler.post(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda17
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m41lambda$setWuwConfig$36$cerencearkassistantapiArkAssistant(str, iResultCallback, wuwConfig);
                }
            });
        }
    }

    /* JADX INFO: renamed from: lambda$setWuwConfig$36$cerence-ark-assistant-api-ArkAssistant, reason: not valid java name */
    /* synthetic */ void m41lambda$setWuwConfig$36$cerencearkassistantapiArkAssistant(String str, final IResultCallback iResultCallback, WuwConfig wuwConfig) {
        if (!this.mAssistantClient.isRunning()) {
            Logger.error(TAG, Utils.format2Argv(str, ErrorMessage.ASSISTANT_NOT_STARTED));
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda7
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifyError(ErrorCode.ASSISTANT_NOT_STARTED, iResultCallback);
                }
            });
            return;
        }
        try {
            this.mAssistant.setWuwConfig(wuwConfig, new AnonymousClass8(iResultCallback));
        } catch (Exception e) {
            Logger.error(TAG, Utils.formatException(str), e);
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda8
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifyError(ErrorCode.EXCEPTION, iResultCallback);
                }
            });
        }
    }

    /* JADX INFO: renamed from: cerence.ark.assistant.api.ArkAssistant$8, reason: invalid class name */
    class AnonymousClass8 extends ICallback.Stub {
        final /* synthetic */ IResultCallback val$callback;

        AnonymousClass8(IResultCallback iResultCallback) {
            this.val$callback = iResultCallback;
        }

        @Override // cerence.ark.assistant.service.ICallback
        public void onSuccess(String str) {
            final IResultCallback iResultCallback = this.val$callback;
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$8$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifySuccess(iResultCallback);
                }
            });
        }

        @Override // cerence.ark.assistant.service.ICallback
        public void onError(final ErrorCode errorCode, String str) {
            final IResultCallback iResultCallback = this.val$callback;
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$8$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifyError(errorCode, iResultCallback);
                }
            });
        }
    }

    public void notifyContentChanged(final String str) {
        sHandler.post(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda36
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m35x8c47e090(str);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$notifyContentChanged$40$cerence-ark-assistant-api-ArkAssistant, reason: not valid java name */
    /* synthetic */ void m35x8c47e090(final String str) {
        if (TextUtils.isEmpty(str)) {
            Logger.error(TAG, "notifyContentChanged: contentType is null");
            return;
        }
        IDCUpdater<List<String>> dCUpdater = getDCUpdater(str);
        if (dCUpdater == null) {
            Logger.error(TAG, "updateContent: updater is null for content type " + str);
        } else if (!this.mAssistantClient.isRunning()) {
            Logger.error(TAG, Utils.format("notifyContentChanged: contentType = %s, %s", str, ErrorMessage.ASSISTANT_NOT_STARTED));
        } else {
            dCUpdater.getContent(str, new IDCCallback() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda68
                @Override // cerence.ark.assistant.core.dcc.IDCCallback
                public final void onCompleted(Object obj) {
                    this.f$0.m34xfb52d66(str, (List) obj);
                }
            });
        }
    }

    /* JADX INFO: renamed from: lambda$notifyContentChanged$39$cerence-ark-assistant-api-ArkAssistant, reason: not valid java name */
    /* synthetic */ void m34xfb52d66(final String str, final List list) {
        sHandler.post(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda63
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m33xb8973c87(list, str);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$notifyContentChanged$38$cerence-ark-assistant-api-ArkAssistant, reason: not valid java name */
    /* synthetic */ void m33xb8973c87(List list, final String str) {
        new ListSplitter().split(list, new ISplitter.SplitterListener() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda39
            @Override // cerence.ark.assistant.library.ipctransaction.ISplitter.SplitterListener
            public final void onSplit(Object obj, String str2, int i, int i2) {
                this.f$0.m32x61794ba8(str, (List) obj, str2, i, i2);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$notifyContentChanged$37$cerence-ark-assistant-api-ArkAssistant, reason: not valid java name */
    /* synthetic */ void m32x61794ba8(String str, List list, String str2, int i, int i2) {
        try {
            this.mAssistant.updateDynamicContent(str, list, str2, i, i2, null);
        } catch (Exception e) {
            Logger.error(TAG, "updateContent: dcc content update exception", e);
        }
    }

    public void clearUserData(final IResultCallback<Void, ErrorCode> iResultCallback) {
        sHandler.post(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda65
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m26lambda$clearUserData$43$cerencearkassistantapiArkAssistant(iResultCallback);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$clearUserData$43$cerence-ark-assistant-api-ArkAssistant, reason: not valid java name */
    /* synthetic */ void m26lambda$clearUserData$43$cerencearkassistantapiArkAssistant(final IResultCallback iResultCallback) {
        if (!this.mAssistantClient.isRunning()) {
            Logger.error(TAG, Utils.format2Argv("clearUserData", ErrorMessage.ASSISTANT_NOT_STARTED));
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda55
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifyError(ErrorCode.ASSISTANT_NOT_STARTED, iResultCallback);
                }
            });
            return;
        }
        try {
            this.mAssistant.clearUserData(new AnonymousClass9(iResultCallback));
        } catch (Exception e) {
            Logger.error(TAG, Utils.format2Argv("clearUserData", ErrorMessage.EXCEPTION), e);
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda66
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifyError(ErrorCode.EXCEPTION, iResultCallback);
                }
            });
        }
    }

    /* JADX INFO: renamed from: cerence.ark.assistant.api.ArkAssistant$9, reason: invalid class name */
    class AnonymousClass9 extends ICallback.Stub {
        final /* synthetic */ IResultCallback val$callback;

        AnonymousClass9(IResultCallback iResultCallback) {
            this.val$callback = iResultCallback;
        }

        @Override // cerence.ark.assistant.service.ICallback
        public void onSuccess(String str) {
            final IResultCallback iResultCallback = this.val$callback;
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$9$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifySuccess(iResultCallback);
                }
            });
        }

        @Override // cerence.ark.assistant.service.ICallback
        public void onError(final ErrorCode errorCode, String str) {
            final IResultCallback iResultCallback = this.val$callback;
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$9$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifyError(errorCode, iResultCallback);
                }
            });
        }
    }

    public void setDynamicWords(final List<DynamicWord> list, final IResultCallback<Void, ErrorCode> iResultCallback) {
        sHandler.post(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda69
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m40lambda$setDynamicWords$47$cerencearkassistantapiArkAssistant(iResultCallback, list);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$setDynamicWords$47$cerence-ark-assistant-api-ArkAssistant, reason: not valid java name */
    /* synthetic */ void m40lambda$setDynamicWords$47$cerencearkassistantapiArkAssistant(final IResultCallback iResultCallback, List list) {
        if (!this.mAssistantClient.isRunning()) {
            Logger.error(TAG, Utils.format2Argv("setDynamicWords", ErrorMessage.ASSISTANT_NOT_STARTED));
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda27
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifyError(ErrorCode.ASSISTANT_NOT_STARTED, iResultCallback);
                }
            });
        } else {
            new ListSplitter().split((List) DccUtils.getDynamicWords(list), new ISplitter.SplitterListener() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda28
                @Override // cerence.ark.assistant.library.ipctransaction.ISplitter.SplitterListener
                public final void onSplit(Object obj, String str, int i, int i2) {
                    this.f$0.m39lambda$setDynamicWords$46$cerencearkassistantapiArkAssistant(iResultCallback, (List) obj, str, i, i2);
                }
            });
        }
    }

    /* JADX INFO: renamed from: cerence.ark.assistant.api.ArkAssistant$10, reason: invalid class name */
    class AnonymousClass10 extends ICallback.Stub {
        final /* synthetic */ IResultCallback val$callback;

        AnonymousClass10(IResultCallback iResultCallback) {
            this.val$callback = iResultCallback;
        }

        @Override // cerence.ark.assistant.service.ICallback
        public void onSuccess(String str) {
            final IResultCallback iResultCallback = this.val$callback;
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$10$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifySuccess(iResultCallback);
                }
            });
        }

        @Override // cerence.ark.assistant.service.ICallback
        public void onError(final ErrorCode errorCode, String str) {
            final IResultCallback iResultCallback = this.val$callback;
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$10$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifyError(errorCode, iResultCallback);
                }
            });
        }
    }

    /* JADX INFO: renamed from: lambda$setDynamicWords$46$cerence-ark-assistant-api-ArkAssistant, reason: not valid java name */
    /* synthetic */ void m39lambda$setDynamicWords$46$cerencearkassistantapiArkAssistant(final IResultCallback iResultCallback, List list, String str, int i, int i2) {
        try {
            this.mAssistant.setDynamicWords(list, str, i, i2, new AnonymousClass10(iResultCallback));
        } catch (Exception e) {
            Logger.error(TAG, Utils.formatException("setDynamicWords"), e);
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda25
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifyError(ErrorCode.EXCEPTION, iResultCallback);
                }
            });
        }
    }

    @Deprecated
    public void updateLocation(final String str, final String str2, final String str3, final IResultCallback<Void, ErrorCode> iResultCallback) {
        if (TextUtils.isEmpty(str)) {
            Logger.error(TAG, Utils.format("%s: the country is empty", "updateLocation"));
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda37
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifyError(ErrorCode.PARAMETER_INVALID, iResultCallback);
                }
            });
        } else {
            sHandler.post(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda38
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m47lambda$updateLocation$51$cerencearkassistantapiArkAssistant(iResultCallback, str, str2, str3);
                }
            });
        }
    }

    /* JADX INFO: renamed from: lambda$updateLocation$51$cerence-ark-assistant-api-ArkAssistant, reason: not valid java name */
    /* synthetic */ void m47lambda$updateLocation$51$cerencearkassistantapiArkAssistant(final IResultCallback iResultCallback, String str, String str2, String str3) {
        if (!this.mAssistantClient.isRunning()) {
            Logger.error(TAG, Utils.format2Argv("updateLocation", ErrorMessage.ASSISTANT_NOT_STARTED));
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda51
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifyError(ErrorCode.ASSISTANT_NOT_STARTED, iResultCallback);
                }
            });
            return;
        }
        try {
            this.mAssistant.updateLocation(str, str2, str3, new AnonymousClass11(iResultCallback));
        } catch (Exception e) {
            Logger.error(TAG, Utils.format2Argv("updateLocation", ErrorMessage.EXCEPTION), e);
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda52
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifyError(ErrorCode.EXCEPTION, iResultCallback);
                }
            });
        }
    }

    /* JADX INFO: renamed from: cerence.ark.assistant.api.ArkAssistant$11, reason: invalid class name */
    class AnonymousClass11 extends ICallback.Stub {
        final /* synthetic */ IResultCallback val$callback;

        AnonymousClass11(IResultCallback iResultCallback) {
            this.val$callback = iResultCallback;
        }

        @Override // cerence.ark.assistant.service.ICallback
        public void onSuccess(String str) {
            final IResultCallback iResultCallback = this.val$callback;
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$11$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifySuccess(iResultCallback);
                }
            });
        }

        @Override // cerence.ark.assistant.service.ICallback
        public void onError(final ErrorCode errorCode, String str) {
            final IResultCallback iResultCallback = this.val$callback;
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$11$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifyError(errorCode, iResultCallback);
                }
            });
        }
    }

    public void updateUdeDataConfig(final String str, final String str2, final String str3, final IResultCallback<Void, ErrorCode> iResultCallback) {
        Logger.debug(TAG, Utils.format("updateUdeDataConfig: country: %s, state: %s, city: %s", str, str2, str3));
        sHandler.post(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda58
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m48x4fd0dfe6(iResultCallback, str, str2, str3);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$updateUdeDataConfig$56$cerence-ark-assistant-api-ArkAssistant, reason: not valid java name */
    /* synthetic */ void m48x4fd0dfe6(final IResultCallback iResultCallback, String str, String str2, String str3) {
        if (!this.mAssistantClient.isRunning()) {
            Logger.error(TAG, Utils.format2Argv("updateUdeDataConfig", ErrorMessage.ASSISTANT_NOT_STARTED));
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda43
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifyError(ErrorCode.ASSISTANT_NOT_STARTED, iResultCallback);
                }
            });
            return;
        }
        try {
            final RemoteResult remoteResultUpdateUdeDataConfig = this.mAssistant.updateUdeDataConfig(str, str2, str3);
            if (remoteResultUpdateUdeDataConfig.isSuccess()) {
                Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda45
                    @Override // java.lang.Runnable
                    public final void run() {
                        Utils.notifySuccess(iResultCallback);
                    }
                });
            } else {
                Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda46
                    @Override // java.lang.Runnable
                    public final void run() {
                        Utils.notifyError(remoteResultUpdateUdeDataConfig.getErrorCode(), iResultCallback);
                    }
                });
            }
        } catch (Exception e) {
            Logger.error(TAG, Utils.format2Argv("updateUdeDataConfig", ErrorMessage.EXCEPTION), e);
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda47
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifyError(ErrorCode.EXCEPTION, iResultCallback);
                }
            });
        }
    }

    public void updateUdeRppConfig(final RppLocationType rppLocationType, final String str, final String str2, final String str3, final IResultCallback<Void, ErrorCode> iResultCallback) {
        Logger.debug(TAG, Utils.format("updateUdeRppConfig: rppLocationType: %s, country: %s, state: %s, city: %s", rppLocationType, str, str2, str3));
        sHandler.post(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda79
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m49xa6891f74(iResultCallback, rppLocationType, str, str2, str3);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$updateUdeRppConfig$61$cerence-ark-assistant-api-ArkAssistant, reason: not valid java name */
    /* synthetic */ void m49xa6891f74(final IResultCallback iResultCallback, RppLocationType rppLocationType, String str, String str2, String str3) {
        if (!this.mAssistantClient.isRunning()) {
            Logger.error(TAG, Utils.format2Argv("updateUdeRppConfig", ErrorMessage.ASSISTANT_NOT_STARTED));
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda77
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifyError(ErrorCode.ASSISTANT_NOT_STARTED, iResultCallback);
                }
            });
            return;
        }
        try {
            final RemoteResult remoteResultUpdateUdeRppConfig = this.mAssistant.updateUdeRppConfig(rppLocationType, str, str2, str3);
            if (remoteResultUpdateUdeRppConfig.isSuccess()) {
                Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda83
                    @Override // java.lang.Runnable
                    public final void run() {
                        Utils.notifySuccess(iResultCallback);
                    }
                });
            } else {
                Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda84
                    @Override // java.lang.Runnable
                    public final void run() {
                        Utils.notifyError(remoteResultUpdateUdeRppConfig.getErrorCode(), iResultCallback);
                    }
                });
            }
        } catch (Exception e) {
            Logger.error(TAG, Utils.format2Argv("updateUdeRppConfig", ErrorMessage.EXCEPTION), e);
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifyError(ErrorCode.EXCEPTION, iResultCallback);
                }
            });
        }
    }

    public void createUpdater(final IUpdateListener iUpdateListener, final IResultCallback<IUpdater, ErrorCode> iResultCallback) {
        final String str = "createUpdater";
        if (iUpdateListener == null) {
            Logger.error(TAG, Utils.format("%s: the parameter updateListener is null", "createUpdater"));
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda71
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifyError(ErrorCode.PARAMETER_INVALID, iResultCallback);
                }
            });
        } else {
            sHandler.post(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda72
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m27lambda$createUpdater$67$cerencearkassistantapiArkAssistant(str, iResultCallback, iUpdateListener);
                }
            });
        }
    }

    /* JADX INFO: renamed from: lambda$createUpdater$67$cerence-ark-assistant-api-ArkAssistant, reason: not valid java name */
    /* synthetic */ void m27lambda$createUpdater$67$cerencearkassistantapiArkAssistant(String str, final IResultCallback iResultCallback, IUpdateListener iUpdateListener) {
        if (!Utils.isInitialized(this.mAssistantClient.getAssistantState())) {
            Logger.error(TAG, Utils.format2Argv(str, ErrorMessage.ASSISTANT_NOT_INITIALIZED));
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda53
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifyError(ErrorCode.ASSISTANT_NOT_INITIALIZED, iResultCallback);
                }
            });
            return;
        }
        try {
            Logger.info(TAG, "createUpdater: begin");
            RemoteUpdater remoteUpdaterCreateUpdater = this.mAssistant.createUpdater(new UpdateListenerProxy(iUpdateListener));
            if (remoteUpdaterCreateUpdater != null) {
                final UpdaterProxy updaterProxy = new UpdaterProxy(remoteUpdaterCreateUpdater, sHandler);
                Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda54
                    @Override // java.lang.Runnable
                    public final void run() {
                        Utils.notifySuccess(updaterProxy, iResultCallback);
                    }
                });
            } else {
                Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda56
                    @Override // java.lang.Runnable
                    public final void run() {
                        Utils.notifyError(ErrorCode.UNKNOWN, iResultCallback);
                    }
                });
            }
        } catch (Exception e) {
            Logger.error(TAG, Utils.format2Argv(str, ErrorMessage.EXCEPTION), e);
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda57
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifyError(ErrorCode.EXCEPTION, iResultCallback);
                }
            });
        }
    }

    public void destroyUpdater(final IUpdater iUpdater, final IResultCallback<Void, ErrorCode> iResultCallback) {
        final String str = "destroyUpdater";
        if (iUpdater == null) {
            Logger.error(TAG, Utils.format("%s: the parameter updater is null", "destroyUpdater"));
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda40
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifyError(ErrorCode.PARAMETER_INVALID, iResultCallback);
                }
            });
        } else if (!(iUpdater instanceof UpdaterProxy)) {
            Logger.error(TAG, Utils.format("%s: updater not instanceof UpdaterProxy", "destroyUpdater"));
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda41
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifyError(ErrorCode.PARAMETER_INVALID, iResultCallback);
                }
            });
        } else {
            sHandler.post(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda42
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m28lambda$destroyUpdater$73$cerencearkassistantapiArkAssistant(iUpdater, iResultCallback, str);
                }
            });
        }
    }

    /* JADX INFO: renamed from: lambda$destroyUpdater$73$cerence-ark-assistant-api-ArkAssistant, reason: not valid java name */
    /* synthetic */ void m28lambda$destroyUpdater$73$cerencearkassistantapiArkAssistant(IUpdater iUpdater, final IResultCallback iResultCallback, String str) {
        try {
            Logger.info(TAG, "destroyUpdater: begin");
            final RemoteResult remoteResultDestroyUpdater = this.mAssistant.destroyUpdater(((UpdaterProxy) iUpdater).getRemoteUpdater());
            if (remoteResultDestroyUpdater.isSuccess()) {
                Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda2
                    @Override // java.lang.Runnable
                    public final void run() {
                        Utils.notifySuccess(iResultCallback);
                    }
                });
            } else {
                Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda3
                    @Override // java.lang.Runnable
                    public final void run() {
                        Utils.notifyError(remoteResultDestroyUpdater.getErrorCode(), iResultCallback);
                    }
                });
            }
        } catch (Exception e) {
            Logger.error(TAG, Utils.format2Argv(str, ErrorMessage.EXCEPTION), e);
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda4
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifyError(ErrorCode.EXCEPTION, iResultCallback);
                }
            });
        }
    }

    public static void enterRecoveryMode(RecoveryConfig recoveryConfig, IUpdateListener iUpdateListener, IResultCallback<IRecoveryUpdater, ErrorCode> iResultCallback) {
        enterRecoveryMode(recoveryConfig, iUpdateListener, null, iResultCallback);
    }

    public static void enterRecoveryMode(final RecoveryConfig recoveryConfig, final IUpdateListener iUpdateListener, final ITokenProvider iTokenProvider, final IResultCallback<IRecoveryUpdater, ErrorCode> iResultCallback) {
        if (!ParamChecker.checkRecoveryConfigValid(recoveryConfig)) {
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda48
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifyError(ErrorCode.PARAMETER_INVALID, iResultCallback);
                }
            });
            return;
        }
        if (iUpdateListener == null) {
            Logger.error(TAG, Utils.format("%s: updateListener is null", "enterRecoveryMode"));
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda49
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifyError(ErrorCode.PARAMETER_INVALID, iResultCallback);
                }
            });
        } else {
            Logger.info(TAG, Utils.format("%s: sdk_version = %s, pid = %d", "enterRecoveryMode", cerence.ark.assistant.service.BuildConfig.SDK_VERSION, Integer.valueOf(Process.myPid())));
            sHandler.post(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda50
                @Override // java.lang.Runnable
                public final void run() {
                    ArkAssistant.lambda$enterRecoveryMode$78(recoveryConfig, iTokenProvider, iUpdateListener, iResultCallback);
                }
            });
        }
    }

    static /* synthetic */ void lambda$enterRecoveryMode$78(RecoveryConfig recoveryConfig, ITokenProvider iTokenProvider, IUpdateListener iUpdateListener, final IResultCallback iResultCallback) {
        Logger.info(TAG, Utils.format("enterRecoveryMode: %s", recoveryConfig));
        DmRecoveryModeParams.Builder builder = new DmRecoveryModeParams.Builder();
        builder.setAttribute(recoveryConfig.getAttribute()).setRecoveryConfigDir(recoveryConfig.getRecoveryConfigDir()).setUpdateStorageDir(recoveryConfig.getUpdateStorageDir()).setUserId(recoveryConfig.getUserId()).setDeviceId(recoveryConfig.getDeviceId());
        final RecoveryUpdaterImpl recoveryUpdaterImpl = new RecoveryUpdaterImpl(sHandler);
        final Result<Void, ErrorCode> resultInitUpdater = recoveryUpdaterImpl.initUpdater(builder.build(), iTokenProvider == null ? null : new RecoveryTokenProviderImpl(iTokenProvider), new UpdateListenerImpl(iUpdateListener));
        if (resultInitUpdater.isSuccess()) {
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda76
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifySuccess(recoveryUpdaterImpl, iResultCallback);
                }
            });
        } else {
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda78
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifyError((ErrorCode) resultInitUpdater.getErrorCode(), iResultCallback);
                }
            });
        }
    }

    public static void exitRecoveryMode(final IRecoveryUpdater iRecoveryUpdater, final IResultCallback<Void, ErrorCode> iResultCallback) {
        if (iRecoveryUpdater == null) {
            Logger.error(TAG, Utils.format("%s: updater is null", "exitRecoveryMode"));
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda73
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifyError(ErrorCode.PARAMETER_INVALID, iResultCallback);
                }
            });
        } else if (!(iRecoveryUpdater instanceof RecoveryUpdaterImpl)) {
            Logger.error(TAG, Utils.format("%s: updater is not as RecoveryUpdater", "exitRecoveryMode"));
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda74
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifyError(ErrorCode.PARAMETER_INVALID, iResultCallback);
                }
            });
        } else {
            sHandler.post(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda75
                @Override // java.lang.Runnable
                public final void run() {
                    ArkAssistant.lambda$exitRecoveryMode$83(iRecoveryUpdater, iResultCallback);
                }
            });
        }
    }

    static /* synthetic */ void lambda$exitRecoveryMode$83(IRecoveryUpdater iRecoveryUpdater, final IResultCallback iResultCallback) {
        Logger.info(TAG, "exitRecoveryMode: begin");
        final Result<Void, ErrorCode> resultDestroyUpdater = ((RecoveryUpdaterImpl) iRecoveryUpdater).destroyUpdater();
        if (resultDestroyUpdater.isSuccess()) {
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda80
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifySuccess(iResultCallback);
                }
            });
        } else {
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda81
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifyError((ErrorCode) resultDestroyUpdater.getErrorCode(), iResultCallback);
                }
            });
        }
    }

    public Result<Void, ErrorCode> overrideConfiguration(String str, String str2) {
        if (!Utils.isInitialized(getAssistantState())) {
            Logger.error(TAG, Utils.format2Argv("overrideConfiguration", ErrorMessage.ASSISTANT_NOT_INITIALIZED));
            return Utils.buildErrorResult(ErrorCode.ASSISTANT_NOT_INITIALIZED);
        }
        try {
            return Utils.convertToResult(this.mAssistant.overrideConfiguration(str, str2));
        } catch (Exception e) {
            Logger.error(TAG, Utils.formatException("overrideConfiguration"), e);
            return Utils.buildErrorResult(ErrorCode.UNKNOWN);
        }
    }

    public List<String> getLogConsumerNames() {
        ArrayList arrayList = new ArrayList();
        if (!Utils.isInitialized(getAssistantState())) {
            Logger.error(TAG, Utils.format2Argv("getLogConsumerNames", ErrorMessage.ASSISTANT_NOT_INITIALIZED));
            return arrayList;
        }
        try {
            return this.mAssistant.getLogConsumerNames();
        } catch (Exception e) {
            Logger.error(TAG, Utils.formatException("getLogConsumerNames"), e);
            return arrayList;
        }
    }

    public Result<Void, ErrorCode> configureLogConsumer(LogConsumer logConsumer) {
        if (!Utils.isInitialized(getAssistantState())) {
            Logger.error(TAG, Utils.format2Argv("configureLogConsumer", ErrorMessage.ASSISTANT_NOT_INITIALIZED));
            return Utils.buildErrorResult(ErrorCode.ASSISTANT_NOT_INITIALIZED);
        }
        try {
            return Utils.convertToResult(this.mAssistant.configureLogConsumer(logConsumer));
        } catch (Exception e) {
            Logger.error(TAG, Utils.formatException("configureLogConsumer"), e);
            return Utils.buildErrorResult(ErrorCode.UNKNOWN);
        }
    }

    public Result<Void, ErrorCode> requestTrimMemory() {
        if (!this.mAssistantClient.isRunning()) {
            Logger.error(TAG, Utils.format2Argv("requestTrimMemory", ErrorMessage.ASSISTANT_NOT_STARTED));
            return Utils.buildErrorResult(ErrorCode.ASSISTANT_NOT_STARTED);
        }
        try {
            String str = TAG;
            Utils.logMethodBegin(str, "requestTrimMemory");
            RemoteResult remoteResultRequestTrimMemory = this.mAssistant.requestTrimMemory();
            Utils.logMethodEnd(str, "requestTrimMemory");
            return Utils.convertToResult(remoteResultRequestTrimMemory);
        } catch (Exception e) {
            Logger.error(TAG, Utils.formatException("requestTrimMemory"), e);
            return Utils.buildErrorResult(ErrorCode.UNKNOWN);
        }
    }

    private IDCUpdater<List<String>> getDCUpdater(String str) {
        str.hashCode();
        switch (str) {
            case "DCC_APP":
                return this.mAppUpdater;
            case "DCC_STATION":
                return this.mRadioStationUpdater;
            case "DCC_MUSIC":
                return this.mMusicUpdater;
            case "DCC_CONTACT":
                return this.mContactUpdater;
            default:
                return this.mContentUpdater;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearDialogPrompters() {
        synchronized (this.mDialogPrompterLock) {
            List<BasePrompter> list = this.mDialogPrompters;
            if (list != null) {
                list.clear();
                this.mDialogPrompters = null;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyAssistantListeners(final Consumer<AssistantListener> consumer) {
        if (consumer == null) {
            Logger.error(TAG, "consumer is null");
        } else {
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda70
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m31x4f402296(consumer);
                }
            });
        }
    }

    /* JADX INFO: renamed from: lambda$notifyAssistantListeners$84$cerence-ark-assistant-api-ArkAssistant, reason: not valid java name */
    /* synthetic */ void m31x4f402296(Consumer consumer) {
        Iterator<AssistantListener> it = this.mAssistantListenerList.iterator();
        while (it.hasNext()) {
            consumer.accept(it.next());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void deactivateHmi() {
        HmiListener hmiListener = this.mHmiListener;
        if (hmiListener != null) {
            hmiListener.deactivate();
            this.mHmiListener = null;
        }
    }

    private static class SingletonInstance {
        private static final ArkAssistant INSTANCE = new ArkAssistant(null);

        private SingletonInstance() {
        }
    }
}
