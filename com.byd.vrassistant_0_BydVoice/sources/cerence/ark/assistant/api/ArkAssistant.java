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
import cerence.ark.assistant.api.data.ShortcutCommand;
import cerence.ark.assistant.api.dynamic.IDynamicContentProvider;
import cerence.ark.assistant.api.event.AppEvent;
import cerence.ark.assistant.api.hmi.HmiListener;
import cerence.ark.assistant.api.prompter.TtsVoice;
import cerence.ark.assistant.common.Logger;
import cerence.ark.assistant.common.Scheduler;
import cerence.ark.assistant.common.callback.IResultCallback;
import cerence.ark.assistant.common.callback.Result;
import cerence.ark.assistant.core.DefaultDhi;
import cerence.ark.assistant.core.HmiListenerImpl;
import cerence.ark.assistant.core.NotificationListenerImpl;
import cerence.ark.assistant.core.UnhandledRequestListenerImpl;
import cerence.ark.assistant.core.dcc.AppUpdater;
import cerence.ark.assistant.core.dcc.ContactUpdater;
import cerence.ark.assistant.core.dcc.ContentUpdater;
import cerence.ark.assistant.core.dcc.DccConstant;
import cerence.ark.assistant.core.dcc.IDCCallback;
import cerence.ark.assistant.core.dcc.IDCUpdater;
import cerence.ark.assistant.core.dcc.MusicUpdater;
import cerence.ark.assistant.core.dcc.RadioStationUpdater;
import cerence.ark.assistant.dm.NativeDialogManager;
import cerence.ark.assistant.framework.AssistantState;
import cerence.ark.assistant.framework.data.AssistantConfiguration;
import cerence.ark.assistant.framework.data.Config;
import cerence.ark.assistant.framework.data.ErrorInfo;
import cerence.ark.assistant.framework.data.wuw.WuwConfig;
import cerence.ark.assistant.framework.data.wuw.WuwInfo;
import cerence.ark.assistant.framework.prompter.IPrompter;
import cerence.ark.assistant.library.ErrorMessage;
import cerence.ark.assistant.library.JsonKey;
import cerence.ark.assistant.library.Utils;
import cerence.ark.assistant.library.exception.ArkAssistantException;
import cerence.ark.assistant.library.ipctransaction.ISplitter;
import cerence.ark.assistant.library.ipctransaction.ipclist.ListSplitter;
import cerence.ark.assistant.library.ipctransaction.ipcstring.StringSplitter;
import cerence.ark.assistant.service.AssistantImpl;
import cerence.ark.assistant.service.IAssistant;
import cerence.ark.assistant.service.ICallback;
import cerence.ark.assistant.service.IErrorListener;
import cerence.ark.assistant.service.SpeechService;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public final class ArkAssistant {
    private static final String TAG = "ArkAssistant";
    private IDCUpdater<List<String>> mAppUpdater;
    private IAssistant mAssistant;
    private final List<AssistantListener> mAssistantListenerList;
    private ServiceConnection mConnection;
    private IDCUpdater<List<String>> mContactUpdater;
    private IDCUpdater<List<String>> mContentUpdater;
    private IDynamicContentProvider mDynamicContentProvider;
    private Handler mHandler;
    private volatile boolean mIsBindingService;
    private boolean mIsRegisterRequestSuccess;
    private IDCUpdater<List<String>> mMusicUpdater;
    private IDCUpdater<List<String>> mRadioStationUpdater;

    private ArkAssistant() {
        this.mAssistantListenerList = new CopyOnWriteArrayList();
        createAssistantThread();
    }

    public static ArkAssistant get() {
        return SingletonInstance.INSTANCE;
    }

    public static List<String> getAvailableAsrLanguages(String str) {
        String str2 = TAG;
        Logger.debug(str2, String.format("getAvailableAsrLanguages: dataPath: %s", str));
        ArrayList arrayList = new ArrayList();
        if (TextUtils.isEmpty(str)) {
            Logger.error(str2, "getAvailableAsrLanguages: data path is empty");
            return arrayList;
        }
        if (!new File(str).exists()) {
            Logger.error(str2, "getAvailableAsrLanguages: The data path does not exist");
            return arrayList;
        }
        String[] availableAsrLanguages = NativeDialogManager.getAvailableAsrLanguages(str);
        if (availableAsrLanguages == null || availableAsrLanguages.length == 0) {
            Logger.error(str2, "getAvailableAsrLanguages: language list is empty");
            return arrayList;
        }
        arrayList.addAll(Arrays.asList(availableAsrLanguages));
        return arrayList;
    }

    public static List<String> getAvailableTtsLanguages(String str) {
        return AssistantImpl.getAvailableTtsLanguages(str);
    }

    public static List<TtsVoice> getAvailableTtsVoices(String str, String str2) {
        return AssistantImpl.getAvailableTtsVoices(str, str2);
    }

    private void createAssistantThread() {
        HandlerThread handlerThread = new HandlerThread("assistant_thread");
        handlerThread.start();
        this.mHandler = new Handler(handlerThread.getLooper());
    }

    private void checkParamValid(Config config) {
        if (TextUtils.isEmpty(config.getAsrLanguage())) {
            throw new ArkAssistantException("Language cannot be null");
        }
        if (TextUtils.isEmpty(config.getDataPath())) {
            throw new ArkAssistantException("Data path cannot be null");
        }
        if (TextUtils.isEmpty(config.getUserId())) {
            throw new ArkAssistantException("UserId cannot be empty");
        }
        if (TextUtils.isEmpty(config.getDeviceId())) {
            throw new ArkAssistantException("DeviceId cannot be empty");
        }
        if (TextUtils.isEmpty(config.getWritablePath())) {
            throw new ArkAssistantException("Write path cannot be null");
        }
        File file = new File(config.getDataPath());
        if (!file.exists()) {
            throw new ArkAssistantException(String.format("The data dir %s does not exist", config.getDataPath()));
        }
        if (!file.isDirectory()) {
            throw new ArkAssistantException(String.format("The data path %s must be directory", config.getDataPath()));
        }
        String[] list = file.list();
        if (list == null || list.length == 0) {
            throw new ArkAssistantException(String.format("Empty datapack %s directory", config.getDataPath()));
        }
        File file2 = new File(config.getWritablePath());
        if (!file2.exists() && !file2.mkdirs()) {
            Logger.warn(TAG, "Create writable path fail");
        } else if (!file2.isDirectory()) {
            throw new ArkAssistantException("The writable path must be directory");
        }
    }

    public void initializeVoiceAssistant(final Context context, final Config config, final HmiListener hmiListener, final IHostController iHostController, final IResultCallback<Void> iResultCallback) {
        this.mHandler.post(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda33
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m15xb582c407(iResultCallback, context, config, hmiListener, iHostController);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$initializeVoiceAssistant$3$cerence-ark-assistant-api-ArkAssistant, reason: not valid java name */
    /* synthetic */ void m15xb582c407(final IResultCallback iResultCallback, Context context, Config config, HmiListener hmiListener, IHostController iHostController) {
        final String str = "initializeVoiceAssistant";
        if (this.mIsBindingService) {
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda21
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifyFailure(ArkAssistant.TAG, str, ErrorMessage.BINDING_SERVICE, iResultCallback);
                }
            });
            return;
        }
        if (isAssistantInitialized()) {
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda23
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifyFailure(ArkAssistant.TAG, str, ErrorMessage.ASSISTANT_ALREADY_INITIALIZED, iResultCallback);
                }
            });
            return;
        }
        Objects.requireNonNull(context, "Context must not be null");
        Objects.requireNonNull(config, "Config must not be null");
        Objects.requireNonNull(hmiListener, "hmiListener must not be null");
        Objects.requireNonNull(iHostController, "controller must not be null");
        int iMyPid = Process.myPid();
        Utils.init(context);
        Utils.setClientPid(iMyPid);
        Logger.debug(TAG, String.format("%s: sdk_version = %s, pid = %s ", "initializeVoiceAssistant", "CA-SDK-ANDROID_Branch22cw24__2025_cw08A#20250221.2002#929254f9f", Integer.valueOf(iMyPid)));
        checkParamValid(config);
        this.mIsBindingService = true;
        if (Utils.getContext().bindService(new Intent(Utils.getContext(), (Class<?>) SpeechService.class), createServiceConnection(config, hmiListener, iHostController, iResultCallback), 1)) {
            return;
        }
        this.mIsBindingService = false;
        Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda24
            @Override // java.lang.Runnable
            public final void run() {
                Utils.notifyFailure(ArkAssistant.TAG, str, ErrorMessage.BIND_SERVICE_ERROR, iResultCallback);
            }
        });
    }

    private ServiceConnection createServiceConnection(final Config config, final HmiListener hmiListener, final IHostController iHostController, final IResultCallback<Void> iResultCallback) {
        return new ServiceConnection() { // from class: cerence.ark.assistant.api.ArkAssistant.1
            @Override // android.content.ServiceConnection
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                Logger.debug(ArkAssistant.TAG, "onServiceConnected: bind service successful");
                if (ArkAssistant.this.mConnection != null) {
                    Utils.getContext().unbindService(ArkAssistant.this.mConnection);
                }
                ArkAssistant.this.mConnection = this;
                ArkAssistant.this.onBindServiceSuccess(iBinder, iHostController, hmiListener, config, iResultCallback);
            }

            @Override // android.content.ServiceConnection
            public void onServiceDisconnected(ComponentName componentName) {
                Logger.error(ArkAssistant.TAG, "onServiceDisconnected");
                ArkAssistant.this.notifyDestroyed();
                ArkAssistant.this.mAssistant = null;
                ArkAssistant.this.mIsBindingService = false;
            }

            @Override // android.content.ServiceConnection
            public void onBindingDied(ComponentName componentName) {
                Logger.error(ArkAssistant.TAG, "onBindingDied");
                ArkAssistant.this.notifyDestroyed();
                ArkAssistant.this.mAssistant = null;
                ArkAssistant.this.mIsBindingService = false;
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onBindServiceSuccess(final IBinder iBinder, final IHostController iHostController, final HmiListener hmiListener, final Config config, final IResultCallback<Void> iResultCallback) {
        this.mHandler.post(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda32
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m23x67675e93(iResultCallback, iBinder, iHostController, hmiListener, config);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$onBindServiceSuccess$9$cerence-ark-assistant-api-ArkAssistant, reason: not valid java name */
    /* synthetic */ void m23x67675e93(final IResultCallback iResultCallback, IBinder iBinder, IHostController iHostController, HmiListener hmiListener, Config config) {
        final String str = "onBindServiceSuccess";
        if (isAssistantInitialized()) {
            this.mIsBindingService = false;
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda6
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifyFailure(ArkAssistant.TAG, str, ErrorMessage.ASSISTANT_ALREADY_INITIALIZED, iResultCallback);
                }
            });
            return;
        }
        IAssistant iAssistantAsInterface = IAssistant.Stub.asInterface(iBinder);
        if (iAssistantAsInterface == null) {
            this.mIsBindingService = false;
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda7
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifyFailure(ArkAssistant.TAG, str, ErrorMessage.REMOTE_NULL, iResultCallback);
                }
            });
            return;
        }
        this.mAssistant = iAssistantAsInterface;
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        this.mIsRegisterRequestSuccess = false;
        ArkClient.get().registerRequestListener(iAssistantAsInterface, iHostController, new DefaultDhi(hmiListener), new IResultCallback() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda8
            @Override // cerence.ark.assistant.common.callback.IResultCallback
            public final void onResult(Result result) {
                this.f$0.m22x620d8bf6(countDownLatch, result);
            }
        });
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            Logger.error(TAG, "onBindServiceSuccess: await exception", e);
        }
        if (!this.mIsRegisterRequestSuccess) {
            this.mIsBindingService = false;
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda9
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifyFailure(ArkAssistant.TAG, str, "register requests fail", iResultCallback);
                }
            });
            return;
        }
        this.mDynamicContentProvider = iHostController.loadDynamicContentProvider();
        try {
            this.mAssistant.setUnhandledRequestListener(new UnhandledRequestListenerImpl(iHostController, this.mAssistant));
            this.mAssistant.initialize(config, new NotificationListenerImpl(hmiListener), new HmiListenerImpl(hmiListener, this.mAssistant), new AnonymousClass2("onBindServiceSuccess", iResultCallback));
            this.mAssistant.setErrorListener(new IErrorListener.Stub() { // from class: cerence.ark.assistant.api.ArkAssistant.3
                @Override // cerence.ark.assistant.service.IErrorListener
                public void onError(ErrorInfo errorInfo) {
                    Logger.error(ArkAssistant.TAG, "onError: " + errorInfo.toString());
                    ArkAssistant.this.notifyError(errorInfo);
                }
            });
        } catch (Exception e2) {
            this.mIsBindingService = false;
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda10
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifyFailure(ArkAssistant.TAG, str, null, e2, iResultCallback);
                }
            });
        }
    }

    /* JADX INFO: renamed from: lambda$onBindServiceSuccess$6$cerence-ark-assistant-api-ArkAssistant, reason: not valid java name */
    /* synthetic */ void m22x620d8bf6(CountDownLatch countDownLatch, Result result) {
        this.mIsRegisterRequestSuccess = result.isSuccess();
        countDownLatch.countDown();
    }

    /* JADX INFO: renamed from: cerence.ark.assistant.api.ArkAssistant$2, reason: invalid class name */
    class AnonymousClass2 extends ICallback.Stub {
        final /* synthetic */ IResultCallback val$callBack;
        final /* synthetic */ String val$methodName;

        AnonymousClass2(String str, IResultCallback iResultCallback) {
            this.val$methodName = str;
            this.val$callBack = iResultCallback;
        }

        @Override // cerence.ark.assistant.service.ICallback
        public void onSuccess() {
            ArkAssistant.this.mIsBindingService = false;
            Logger.debug(ArkAssistant.TAG, this.val$methodName + ": initialize voice assistant successful");
            if (ArkAssistant.this.mDynamicContentProvider != null) {
                ArkAssistant.this.mDynamicContentProvider.activate();
                ArkAssistant.this.mContentUpdater = new ContentUpdater(ArkAssistant.this.mDynamicContentProvider);
                ArkAssistant.this.mContactUpdater = new ContactUpdater(ArkAssistant.this.mDynamicContentProvider);
                ArkAssistant.this.mMusicUpdater = new MusicUpdater(ArkAssistant.this.mDynamicContentProvider);
                ArkAssistant.this.mAppUpdater = new AppUpdater(ArkAssistant.this.mDynamicContentProvider);
                ArkAssistant.this.mRadioStationUpdater = new RadioStationUpdater(ArkAssistant.this.mDynamicContentProvider);
            }
            final IResultCallback iResultCallback = this.val$callBack;
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$2$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifySuccess(iResultCallback);
                }
            });
            ArkAssistant.this.notifyInitialized();
        }

        @Override // cerence.ark.assistant.service.ICallback
        public void onFailure(final String str) {
            ArkAssistant.this.mIsBindingService = false;
            final String str2 = this.val$methodName;
            final IResultCallback iResultCallback = this.val$callBack;
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$2$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifyFailure(ArkAssistant.TAG, str2 + ": onFailure", str, iResultCallback);
                }
            });
        }
    }

    public void startVoiceAssistant(final AssistantConfiguration assistantConfiguration, final IResultCallback<Void> iResultCallback) {
        this.mHandler.post(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda34
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m25x726d7519(assistantConfiguration, iResultCallback);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$startVoiceAssistant$14$cerence-ark-assistant-api-ArkAssistant, reason: not valid java name */
    /* synthetic */ void m25x726d7519(AssistantConfiguration assistantConfiguration, final IResultCallback iResultCallback) {
        Objects.requireNonNull(assistantConfiguration, "assistant configuration must not be null");
        if (this.mAssistant == null) {
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda14
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifyFailure(ArkAssistant.TAG, "startVoiceAssistant", ErrorMessage.REMOTE_NULL, iResultCallback);
                }
            });
            return;
        }
        AssistantState assistantState = ArkClient.get().getAssistantState();
        if (assistantState == AssistantState.UNINITIALIZED) {
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda15
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifyFailure(ArkAssistant.TAG, "startVoiceAssistant", ErrorMessage.ASSISTANT_NOT_INITIALIZED, iResultCallback);
                }
            });
            return;
        }
        if (assistantState == AssistantState.RUNNING) {
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda16
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifyFailure(ArkAssistant.TAG, "startVoiceAssistant", ErrorMessage.ASSISTANT_ALREADY_STARTED, iResultCallback);
                }
            });
            return;
        }
        try {
            this.mAssistant.startVoiceAssistant(assistantConfiguration, new AnonymousClass4(iResultCallback));
        } catch (Exception e) {
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda17
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifyFailure(ArkAssistant.TAG, "startVoiceAssistant", null, e, iResultCallback);
                }
            });
        }
    }

    /* JADX INFO: renamed from: cerence.ark.assistant.api.ArkAssistant$4, reason: invalid class name */
    class AnonymousClass4 extends ICallback.Stub {
        final /* synthetic */ IResultCallback val$callBack;

        AnonymousClass4(IResultCallback iResultCallback) {
            this.val$callBack = iResultCallback;
        }

        @Override // cerence.ark.assistant.service.ICallback
        public void onSuccess() {
            final IResultCallback iResultCallback = this.val$callBack;
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$4$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifySuccess(iResultCallback);
                }
            });
            ArkAssistant.this.notifyStarted();
        }

        @Override // cerence.ark.assistant.service.ICallback
        public void onFailure(final String str) {
            final IResultCallback iResultCallback = this.val$callBack;
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$4$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifyFailure(ArkAssistant.TAG, "startVoiceAssistant", str, iResultCallback);
                }
            });
        }
    }

    public void stopVoiceAssistant(final IResultCallback<Void> iResultCallback) {
        this.mHandler.post(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda35
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m26x3fb26c0f(iResultCallback);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$stopVoiceAssistant$18$cerence-ark-assistant-api-ArkAssistant, reason: not valid java name */
    /* synthetic */ void m26x3fb26c0f(final IResultCallback iResultCallback) {
        if (this.mAssistant == null) {
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda18
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifyFailure(ArkAssistant.TAG, "stopVoiceAssistant", ErrorMessage.REMOTE_NULL, iResultCallback);
                }
            });
            return;
        }
        if (!ArkClient.get().isRunning()) {
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda19
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifyFailure(ArkAssistant.TAG, "stopVoiceAssistant", ErrorMessage.ASSISTANT_NOT_STARTED, iResultCallback);
                }
            });
            return;
        }
        try {
            this.mAssistant.stopVoiceAssistant(new AnonymousClass5(iResultCallback));
        } catch (Exception e) {
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda20
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifyFailure(ArkAssistant.TAG, "stopVoiceAssistant", null, e, iResultCallback);
                }
            });
        }
    }

    /* JADX INFO: renamed from: cerence.ark.assistant.api.ArkAssistant$5, reason: invalid class name */
    class AnonymousClass5 extends ICallback.Stub {
        final /* synthetic */ IResultCallback val$callBack;

        AnonymousClass5(IResultCallback iResultCallback) {
            this.val$callBack = iResultCallback;
        }

        @Override // cerence.ark.assistant.service.ICallback
        public void onSuccess() {
            final IResultCallback iResultCallback = this.val$callBack;
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$5$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifySuccess(iResultCallback);
                }
            });
            ArkAssistant.this.notifyStopped();
        }

        @Override // cerence.ark.assistant.service.ICallback
        public void onFailure(final String str) {
            final IResultCallback iResultCallback = this.val$callBack;
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$5$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifyFailure(ArkAssistant.TAG, "stopVoiceAssistant", str, iResultCallback);
                }
            });
        }
    }

    public static String getVersion(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", BuildConfig.COMMPENT_NAME);
            jSONObject.put(JsonKey.VERSION_NAME, BuildConfig.VERSION);
            jSONObject.put(JsonKey.VERSION_TAG, BuildConfig.TAG_NAME);
            jSONObject.put(JsonKey.VERSION_COMMIT, BuildConfig.COMMIT_ID);
            if (!TextUtils.isEmpty(str)) {
                String version = NativeDialogManager.getVersion(str);
                String str2 = TAG;
                Logger.debug(str2, String.format("%s: version = %s", "getVersion", version));
                if (!TextUtils.isEmpty(version)) {
                    JSONArray jSONArray = new JSONArray();
                    jSONArray.put(new JSONObject(version));
                    jSONObject.put(JsonKey.VERSION_COMPONENTS, jSONArray);
                } else {
                    Logger.error(str2, String.format("%s: get components is empty", "getVersion"));
                }
            } else {
                Logger.error(TAG, String.format("%s: dataPath is empty", "getVersion"));
            }
        } catch (Exception e) {
            Logger.error(TAG, String.format("%s: ", "getVersion"), e);
        }
        return jSONObject.toString();
    }

    public AssistantState getAssistantState() {
        return ArkClient.get().getAssistantState();
    }

    public void sendAppEvent(AppEvent appEvent, IResultCallback<Void> iResultCallback) {
        ArkClient.get().sendAppEvent(appEvent, iResultCallback);
    }

    public boolean setTtsVoiceId(String str) {
        if (TextUtils.isEmpty(str)) {
            Logger.error(TAG, "setTtsVoiceId: tts voice id is empty");
            return false;
        }
        if (this.mAssistant == null) {
            Logger.error(TAG, "setTtsVoiceId: remote assistant is null");
            return false;
        }
        if (ArkClient.get().getAssistantState() == AssistantState.UNINITIALIZED) {
            Logger.error(TAG, "setTtsVoiceId: voice assistant not initialized");
            return false;
        }
        try {
            return this.mAssistant.setTtsVoiceId(str);
        } catch (Exception e) {
            Logger.error(TAG, "setTtsVoiceId: exception", e);
            return false;
        }
    }

    public boolean setUserDataRetentionEnabled(boolean z) {
        if (this.mAssistant == null) {
            Logger.error(TAG, "setUserDataRetentionEnabled: remote assistant is null");
            return false;
        }
        if (ArkClient.get().getAssistantState() != AssistantState.RUNNING) {
            Logger.error(TAG, "setUserDataRetentionEnabled: voice assistant not started");
            return false;
        }
        Logger.info(TAG, "setUserDataRetentionEnabled: enabled = " + z);
        try {
            return this.mAssistant.setUserDataRetentionEnabled(z);
        } catch (Exception e) {
            Logger.error(TAG, "setUserDataRetentionEnabled: exception", e);
            return false;
        }
    }

    public boolean requestTrimMemory() {
        if (this.mAssistant == null) {
            Logger.error(TAG, "requestTrimMemory: remote assistant is null");
            return false;
        }
        if (!ArkClient.get().isRunning()) {
            Logger.error(TAG, "requestTrimMemory: voice assistant not started");
            return false;
        }
        try {
            return this.mAssistant.requestTrimMemory();
        } catch (Exception e) {
            Logger.error(TAG, "requestTrimMemory: exception", e);
            return false;
        }
    }

    public List<String> getAvailablePrompterNames() {
        return ArkClient.get().getAvailablePrompterNames();
    }

    public IPrompter createPrompter(String str, int i) {
        return ArkClient.get().createPrompter(str, i);
    }

    public IPrompter createPrompter(String str, AudioAttributes audioAttributes) {
        return ArkClient.get().createPrompter(str, audioAttributes);
    }

    public void destroyPrompter(IPrompter iPrompter) {
        ArkClient.get().destroyPrompter(iPrompter);
    }

    public void registerAssistantListener(AssistantListener assistantListener) {
        if (assistantListener == null || this.mAssistantListenerList.contains(assistantListener)) {
            return;
        }
        this.mAssistantListenerList.add(assistantListener);
    }

    public void unregisterAssistantListener(AssistantListener assistantListener) {
        this.mAssistantListenerList.remove(assistantListener);
    }

    public void destroyVoiceAssistant(final IResultCallback<Void> iResultCallback) {
        this.mHandler.post(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m14xee7f9455(iResultCallback);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$destroyVoiceAssistant$21$cerence-ark-assistant-api-ArkAssistant, reason: not valid java name */
    /* synthetic */ void m14xee7f9455(final IResultCallback iResultCallback) {
        this.mIsBindingService = false;
        if (ArkClient.get().getAssistantState() == AssistantState.UNINITIALIZED) {
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda12
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifyFailure(ArkAssistant.TAG, "destroyVoiceAssistant", ErrorMessage.ASSISTANT_NOT_INITIALIZED, iResultCallback);
                }
            });
            return;
        }
        IAssistant iAssistant = this.mAssistant;
        if (iAssistant != null) {
            try {
                iAssistant.destroy(null);
            } catch (Exception e) {
                Logger.error(TAG, "destroyVoiceAssistant: destroy exception", e);
            }
        }
        ArkClient.get().destroy();
        clean();
        this.mAssistant = null;
        Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda13
            @Override // java.lang.Runnable
            public final void run() {
                Utils.notifySuccess(iResultCallback);
            }
        });
        notifyDestroyed();
    }

    private void clean() {
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

    public boolean updateAssistantContext(final IAssistantContext iAssistantContext) {
        if (iAssistantContext == null) {
            Logger.error(TAG, "updateAssistantContext: assistant context is null");
            return false;
        }
        if (iAssistantContext.getContextType() == null) {
            Logger.error(TAG, "updateAssistantContext: context type is empty");
            return false;
        }
        if (this.mAssistant == null) {
            Logger.error(TAG, "updateAssistantContext: remote assistant is null");
            return false;
        }
        if (ArkClient.get().getAssistantState() == AssistantState.UNINITIALIZED) {
            Logger.error(TAG, "updateAssistantContext: voice assistant not initialized");
            return false;
        }
        synchronized (this) {
            try {
                try {
                    String jsonString = iAssistantContext.toJsonString();
                    if (jsonString == null) {
                        Logger.error(TAG, "updateAssistantContext: context data is null");
                        return false;
                    }
                    final ArrayList arrayList = new ArrayList();
                    new StringSplitter().split(jsonString, new ISplitter.SplitterListener() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda31
                        @Override // cerence.ark.assistant.library.ipctransaction.ISplitter.SplitterListener
                        public final void onSplit(Object obj, boolean z, boolean z2) {
                            this.f$0.m27xb5a281c2(iAssistantContext, arrayList, (String) obj, z, z2);
                        }
                    });
                    AtomicBoolean atomicBoolean = new AtomicBoolean(true);
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        atomicBoolean.set(atomicBoolean.get() && ((Boolean) it.next()).booleanValue());
                        if (!atomicBoolean.get()) {
                            break;
                        }
                    }
                    return atomicBoolean.get();
                } catch (JSONException e) {
                    Logger.error(TAG, "updateAssistantContext: exception", e);
                    return false;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* JADX INFO: renamed from: lambda$updateAssistantContext$22$cerence-ark-assistant-api-ArkAssistant, reason: not valid java name */
    /* synthetic */ void m27xb5a281c2(IAssistantContext iAssistantContext, List list, String str, boolean z, boolean z2) {
        try {
            list.add(Boolean.valueOf(this.mAssistant.updateAssistantContext(iAssistantContext.getContextType(), str, z, z2)));
        } catch (Exception e) {
            Logger.error(TAG, "updateAssistantContext: exception", e);
            list.add(false);
        }
    }

    public void notifyContentChanged(final String str) {
        if (TextUtils.isEmpty(str)) {
            Logger.error(TAG, "notifyContentChanged: contentType is null");
        } else {
            this.mHandler.post(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda25
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m16x78615d2b(str);
                }
            });
        }
    }

    /* JADX INFO: renamed from: lambda$notifyContentChanged$23$cerence-ark-assistant-api-ArkAssistant, reason: not valid java name */
    /* synthetic */ void m16x78615d2b(String str) {
        if (ArkClient.get().getAssistantState() == AssistantState.UNINITIALIZED) {
            Logger.error(TAG, String.format("notifyContentChanged: contentType = %s, %s", str, ErrorMessage.ASSISTANT_NOT_INITIALIZED));
        } else {
            updateContent(str);
        }
    }

    public void setWuwConfig(final WuwConfig wuwConfig, final IResultCallback<Void> iResultCallback) {
        final String str = "setWuwConfig";
        if (wuwConfig == null) {
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifyFailure(ArkAssistant.TAG, str, " wuwConfig is null", iResultCallback);
                }
            });
        } else {
            this.mHandler.post(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m24lambda$setWuwConfig$27$cerencearkassistantapiArkAssistant(str, iResultCallback, wuwConfig);
                }
            });
        }
    }

    /* JADX INFO: renamed from: lambda$setWuwConfig$27$cerence-ark-assistant-api-ArkAssistant, reason: not valid java name */
    /* synthetic */ void m24lambda$setWuwConfig$27$cerencearkassistantapiArkAssistant(final String str, final IResultCallback iResultCallback, WuwConfig wuwConfig) {
        if (!ArkClient.get().isRunning()) {
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifyFailure(ArkAssistant.TAG, str, ErrorMessage.ASSISTANT_NOT_STARTED, iResultCallback);
                }
            });
            return;
        }
        try {
            this.mAssistant.setWuwConfig(wuwConfig, new AnonymousClass6(iResultCallback, str));
        } catch (Exception e) {
            Logger.error(TAG, String.format("%s: exception", str), e);
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda11
                @Override // java.lang.Runnable
                public final void run() {
                    String str2 = str;
                    Exception exc = e;
                    Utils.notifyFailure(ArkAssistant.TAG, str2, exc.getMessage(), exc, iResultCallback);
                }
            });
        }
    }

    /* JADX INFO: renamed from: cerence.ark.assistant.api.ArkAssistant$6, reason: invalid class name */
    class AnonymousClass6 extends ICallback.Stub {
        final /* synthetic */ IResultCallback val$callback;
        final /* synthetic */ String val$methodName;

        AnonymousClass6(IResultCallback iResultCallback, String str) {
            this.val$callback = iResultCallback;
            this.val$methodName = str;
        }

        @Override // cerence.ark.assistant.service.ICallback
        public void onSuccess() {
            final IResultCallback iResultCallback = this.val$callback;
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$6$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifySuccess(iResultCallback);
                }
            });
        }

        @Override // cerence.ark.assistant.service.ICallback
        public void onFailure(final String str) {
            final String str2 = this.val$methodName;
            final IResultCallback iResultCallback = this.val$callback;
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$6$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifyFailure(ArkAssistant.TAG, str2, str, iResultCallback);
                }
            });
        }
    }

    public WuwInfo getWuwInfo() {
        if (!ArkClient.get().isRunning()) {
            Logger.error(TAG, Utils.format2Argv("getWuwInfo", ErrorMessage.ASSISTANT_NOT_STARTED));
            return null;
        }
        try {
            return this.mAssistant.getWuwInfo();
        } catch (Exception e) {
            Logger.error(TAG, "getWuwInfo: exception", e);
            return null;
        }
    }

    public void updateShortcutCommand(final List<ShortcutCommand> list, final IResultCallback<Void> iResultCallback) {
        this.mHandler.post(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda30
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m32x41ae86b(iResultCallback, list);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$updateShortcutCommand$32$cerence-ark-assistant-api-ArkAssistant, reason: not valid java name */
    /* synthetic */ void m32x41ae86b(final IResultCallback iResultCallback, List list) {
        final String str = "updateShortcutCommand";
        if (!isAssistantInitialized()) {
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda37
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifyFailure(ArkAssistant.TAG, str, ErrorMessage.ASSISTANT_NOT_INITIALIZED, iResultCallback);
                }
            });
            return;
        }
        ArrayList arrayList = new ArrayList();
        if (list != null) {
            int size = list.size();
            int i = 0;
            int i2 = 1;
            while (i < size) {
                ShortcutCommand shortcutCommand = (ShortcutCommand) list.get(i);
                if (shortcutCommand == null) {
                    Logger.warn(TAG, String.format("%s: shortcutInfo is null, index = %s", "updateShortcutCommand", Integer.valueOf(i)));
                } else {
                    arrayList.add(i2 + DccConstant.SEPARATOR + shortcutCommand.getPhrase() + DccConstant.SEPARATOR + shortcutCommand.getTagThreshold() + DccConstant.SEPARATOR + shortcutCommand.getWordThreshold());
                }
                i++;
                i2++;
            }
        }
        final ArrayList arrayList2 = new ArrayList();
        new ListSplitter().split((List) arrayList, new ISplitter.SplitterListener() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda38
            @Override // cerence.ark.assistant.library.ipctransaction.ISplitter.SplitterListener
            public final void onSplit(Object obj, boolean z, boolean z2) {
                this.f$0.m31xd94c5383(arrayList2, str, (List) obj, z, z2);
            }
        });
        AtomicBoolean atomicBoolean = new AtomicBoolean(true);
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            atomicBoolean.set(atomicBoolean.get() && ((Boolean) it.next()).booleanValue());
            if (!atomicBoolean.get()) {
                break;
            }
        }
        if (atomicBoolean.get()) {
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda39
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifySuccess(iResultCallback);
                }
            });
        } else {
            Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda40
                @Override // java.lang.Runnable
                public final void run() {
                    Utils.notifyFailure(ArkAssistant.TAG, str, ErrorMessage.UPDATE_SHORTCUT_FAIL, iResultCallback);
                }
            });
        }
    }

    /* JADX INFO: renamed from: lambda$updateShortcutCommand$29$cerence-ark-assistant-api-ArkAssistant, reason: not valid java name */
    /* synthetic */ void m31xd94c5383(List list, String str, List list2, boolean z, boolean z2) {
        try {
            list.add(Boolean.valueOf(this.mAssistant.updateShortcutCommand(list2, z, z2)));
        } catch (Exception e) {
            Logger.error(TAG, String.format("%s: update shortcut exception", str), e);
            list.add(false);
        }
    }

    private void updateContent(final String str) {
        IDCUpdater<List<String>> iDCUpdater;
        str.hashCode();
        switch (str) {
            case "DCC_APP":
                iDCUpdater = this.mAppUpdater;
                break;
            case "DCC_STATION":
                iDCUpdater = this.mRadioStationUpdater;
                break;
            case "DCC_MUSIC":
                iDCUpdater = this.mMusicUpdater;
                break;
            case "DCC_CONTACT":
                iDCUpdater = this.mContactUpdater;
                break;
            default:
                iDCUpdater = this.mContentUpdater;
                break;
        }
        if (iDCUpdater == null) {
            Logger.error(TAG, "updateContent: updater is null for content type " + str);
        } else {
            iDCUpdater.getContent(str, new IDCCallback() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda28
                @Override // cerence.ark.assistant.core.dcc.IDCCallback
                public final void onCompleted(Object obj) {
                    this.f$0.m30lambda$updateContent$35$cerencearkassistantapiArkAssistant(str, (List) obj);
                }
            });
        }
    }

    /* JADX INFO: renamed from: lambda$updateContent$35$cerence-ark-assistant-api-ArkAssistant, reason: not valid java name */
    /* synthetic */ void m30lambda$updateContent$35$cerencearkassistantapiArkAssistant(final String str, final List list) {
        this.mHandler.post(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda27
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m29lambda$updateContent$34$cerencearkassistantapiArkAssistant(str, list);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$updateContent$34$cerence-ark-assistant-api-ArkAssistant, reason: not valid java name */
    /* synthetic */ void m29lambda$updateContent$34$cerencearkassistantapiArkAssistant(final String str, List list) {
        if (this.mAssistant == null) {
            Logger.error(TAG, String.format("updateContent: contentType = %s, %s", str, ErrorMessage.REMOTE_NULL));
        } else {
            new ListSplitter().split(list, new ISplitter.SplitterListener() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda4
                @Override // cerence.ark.assistant.library.ipctransaction.ISplitter.SplitterListener
                public final void onSplit(Object obj, boolean z, boolean z2) {
                    this.f$0.m28lambda$updateContent$33$cerencearkassistantapiArkAssistant(str, (List) obj, z, z2);
                }
            });
        }
    }

    /* JADX INFO: renamed from: lambda$updateContent$33$cerence-ark-assistant-api-ArkAssistant, reason: not valid java name */
    /* synthetic */ void m28lambda$updateContent$33$cerencearkassistantapiArkAssistant(String str, List list, boolean z, boolean z2) {
        try {
            this.mAssistant.updateDynamicContent(str, list, z, z2);
        } catch (Exception e) {
            Logger.error(TAG, "updateContent: dcc content update exception", e);
        }
    }

    public boolean overrideConfiguration(String str, String str2) {
        if (ArkClient.get().getAssistantState() == AssistantState.UNINITIALIZED) {
            Logger.error(TAG, "overrideConfiguration: voice assistant not initialized");
            return false;
        }
        try {
            return this.mAssistant.overrideConfiguration(str, str2);
        } catch (Exception e) {
            Logger.error(TAG, "overrideConfiguration: exception", e);
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyInitialized() {
        Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda22
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m19xe4cc1656();
            }
        });
    }

    /* JADX INFO: renamed from: lambda$notifyInitialized$36$cerence-ark-assistant-api-ArkAssistant, reason: not valid java name */
    /* synthetic */ void m19xe4cc1656() {
        for (AssistantListener assistantListener : this.mAssistantListenerList) {
            if (assistantListener != null) {
                assistantListener.onInitialized();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyStarted() {
        Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda26
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m20lambda$notifyStarted$37$cerencearkassistantapiArkAssistant();
            }
        });
    }

    /* JADX INFO: renamed from: lambda$notifyStarted$37$cerence-ark-assistant-api-ArkAssistant, reason: not valid java name */
    /* synthetic */ void m20lambda$notifyStarted$37$cerencearkassistantapiArkAssistant() {
        for (AssistantListener assistantListener : this.mAssistantListenerList) {
            if (assistantListener != null) {
                assistantListener.onStarted();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyStopped() {
        Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda5
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m21lambda$notifyStopped$38$cerencearkassistantapiArkAssistant();
            }
        });
    }

    /* JADX INFO: renamed from: lambda$notifyStopped$38$cerence-ark-assistant-api-ArkAssistant, reason: not valid java name */
    /* synthetic */ void m21lambda$notifyStopped$38$cerencearkassistantapiArkAssistant() {
        for (AssistantListener assistantListener : this.mAssistantListenerList) {
            if (assistantListener != null) {
                assistantListener.onStopped();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyError(final ErrorInfo errorInfo) {
        Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda36
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m18lambda$notifyError$39$cerencearkassistantapiArkAssistant(errorInfo);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$notifyError$39$cerence-ark-assistant-api-ArkAssistant, reason: not valid java name */
    /* synthetic */ void m18lambda$notifyError$39$cerencearkassistantapiArkAssistant(ErrorInfo errorInfo) {
        for (AssistantListener assistantListener : this.mAssistantListenerList) {
            if (assistantListener != null) {
                assistantListener.onError(errorInfo);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyDestroyed() {
        Scheduler.runOnUiThread(new Runnable() { // from class: cerence.ark.assistant.api.ArkAssistant$$ExternalSyntheticLambda29
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m17lambda$notifyDestroyed$40$cerencearkassistantapiArkAssistant();
            }
        });
    }

    /* JADX INFO: renamed from: lambda$notifyDestroyed$40$cerence-ark-assistant-api-ArkAssistant, reason: not valid java name */
    /* synthetic */ void m17lambda$notifyDestroyed$40$cerencearkassistantapiArkAssistant() {
        for (AssistantListener assistantListener : this.mAssistantListenerList) {
            if (assistantListener != null) {
                assistantListener.onDestroyed();
            }
        }
    }

    private boolean isAssistantInitialized() {
        AssistantState assistantState = ArkClient.get().getAssistantState();
        return assistantState == AssistantState.INITIALIZED || assistantState == AssistantState.RUNNING;
    }

    private static class SingletonInstance {
        private static final ArkAssistant INSTANCE = new ArkAssistant();

        private SingletonInstance() {
        }
    }
}
