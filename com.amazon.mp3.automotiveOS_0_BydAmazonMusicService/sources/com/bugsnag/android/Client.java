package com.bugsnag.android;

import android.app.Application;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bugsnag.android.internal.BackgroundTaskService;
import com.bugsnag.android.internal.ImmutableConfig;
import com.bugsnag.android.internal.InternalMetrics;
import com.bugsnag.android.internal.InternalMetricsImpl;
import com.bugsnag.android.internal.InternalMetricsNoop;
import com.bugsnag.android.internal.StateObserver;
import com.bugsnag.android.internal.TaskType;
import com.bugsnag.android.internal.dag.ConfigModule;
import com.bugsnag.android.internal.dag.ContextModule;
import com.bugsnag.android.internal.dag.SystemServiceModule;
import java.io.File;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.RejectedExecutionException;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;

/* JADX INFO: loaded from: classes5.dex */
public class Client {
    final Context appContext;

    @NonNull
    final AppDataCollector appDataCollector;
    final BackgroundTaskService bgTaskService;

    @NonNull
    final BreadcrumbState breadcrumbState;
    private final CallbackState callbackState;
    final ClientObservable clientObservable;
    private final Map<String, Object> configDifferences;
    final Connectivity connectivity;
    private final ContextState contextState;
    final DeliveryDelegate deliveryDelegate;

    @NonNull
    final DeviceDataCollector deviceDataCollector;

    @NonNull
    protected final EventStore eventStore;
    private final ExceptionHandler exceptionHandler;
    final FeatureFlagState featureFlagState;
    final ImmutableConfig immutableConfig;
    private final InternalMetrics internalMetrics;

    @Nullable
    final LastRunInfo lastRunInfo;
    final LastRunInfoStore lastRunInfoStore;
    final LaunchCrashTracker launchCrashTracker;
    final Logger logger;

    @NonNull
    final MemoryTrimState memoryTrimState;
    final MetadataState metadataState;
    final Notifier notifier;
    PluginClient pluginClient;
    final SessionTracker sessionTracker;
    final SystemBroadcastReceiver systemBroadcastReceiver;
    private final UserState userState;

    public Client(@NonNull Context context, @NonNull Configuration configuration) {
        MemoryTrimState memoryTrimState = new MemoryTrimState();
        this.memoryTrimState = memoryTrimState;
        BackgroundTaskService backgroundTaskService = new BackgroundTaskService();
        this.bgTaskService = backgroundTaskService;
        ContextModule contextModule = new ContextModule(context);
        Context ctx = contextModule.getCtx();
        this.appContext = ctx;
        Notifier notifier = configuration.getNotifier();
        this.notifier = notifier;
        ConnectivityCompat connectivityCompat = new ConnectivityCompat(ctx, new Function2<Boolean, String, Unit>() { // from class: com.bugsnag.android.Client.1
            @Override // kotlin.jvm.functions.Function2
            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
            public Unit mo4invoke(Boolean bool, String str) {
                HashMap map = new HashMap();
                map.put("hasConnection", bool);
                map.put("networkState", str);
                Client.this.leaveAutoBreadcrumb("Connectivity changed", BreadcrumbType.STATE, map);
                if (!bool.booleanValue()) {
                    return null;
                }
                Client.this.eventStore.flushAsync();
                Client.this.sessionTracker.flushAsync();
                return null;
            }
        });
        this.connectivity = connectivityCompat;
        ConfigModule configModule = new ConfigModule(contextModule, configuration, connectivityCompat);
        ImmutableConfig config = configModule.getConfig();
        this.immutableConfig = config;
        Logger logger = config.getLogger();
        this.logger = logger;
        if (!(context instanceof Application)) {
            logger.w("You should initialize Bugsnag from the onCreate() callback of your Application subclass, as this guarantees errors are captured as early as possible. If a custom Application subclass is not possible in your app then you should suppress this warning by passing the Application context instead: Bugsnag.start(context.getApplicationContext()). For further info see: https://docs.bugsnag.com/platforms/android/#basic-configuration");
        }
        StorageModule storageModule = new StorageModule(ctx, config, logger);
        BugsnagStateModule bugsnagStateModule = new BugsnagStateModule(config, configuration);
        this.clientObservable = bugsnagStateModule.getClientObservable();
        CallbackState callbackState = bugsnagStateModule.getCallbackState();
        this.callbackState = callbackState;
        this.breadcrumbState = bugsnagStateModule.getBreadcrumbState();
        this.contextState = bugsnagStateModule.getContextState();
        this.metadataState = bugsnagStateModule.getMetadataState();
        this.featureFlagState = bugsnagStateModule.getFeatureFlagState();
        SystemServiceModule systemServiceModule = new SystemServiceModule(contextModule);
        TaskType taskType = TaskType.IO;
        storageModule.resolveDependencies(backgroundTaskService, taskType);
        TrackerModule trackerModule = new TrackerModule(configModule, storageModule, this, backgroundTaskService, callbackState);
        this.launchCrashTracker = trackerModule.getLaunchCrashTracker();
        this.sessionTracker = trackerModule.getSessionTracker();
        DataCollectionModule dataCollectionModule = new DataCollectionModule(contextModule, configModule, systemServiceModule, trackerModule, backgroundTaskService, connectivityCompat, storageModule.getDeviceId(), storageModule.getInternalDeviceId(), memoryTrimState);
        dataCollectionModule.resolveDependencies(backgroundTaskService, taskType);
        this.appDataCollector = dataCollectionModule.getAppDataCollector();
        this.deviceDataCollector = dataCollectionModule.getDeviceDataCollector();
        this.userState = storageModule.getUserStore().load(configuration.getUser());
        storageModule.getSharedPrefMigrator().deleteLegacyPrefs();
        EventStorageModule eventStorageModule = new EventStorageModule(contextModule, configModule, dataCollectionModule, backgroundTaskService, trackerModule, systemServiceModule, notifier, callbackState);
        eventStorageModule.resolveDependencies(backgroundTaskService, taskType);
        EventStore eventStore = eventStorageModule.getEventStore();
        this.eventStore = eventStore;
        this.deliveryDelegate = new DeliveryDelegate(logger, eventStore, config, callbackState, notifier, backgroundTaskService);
        this.exceptionHandler = new ExceptionHandler(this, logger);
        this.lastRunInfoStore = storageModule.getLastRunInfoStore();
        this.lastRunInfo = storageModule.getLastRunInfo();
        this.pluginClient = new PluginClient(configuration.getPlugins(), config, logger);
        if (configuration.getTelemetry().contains(Telemetry.USAGE)) {
            this.internalMetrics = new InternalMetricsImpl();
        } else {
            this.internalMetrics = new InternalMetricsNoop();
        }
        this.configDifferences = configuration.impl.getConfigDifferences();
        this.systemBroadcastReceiver = new SystemBroadcastReceiver(this, logger);
        start();
    }

    private void start() {
        if (this.immutableConfig.getEnabledErrorTypes().getUnhandledExceptions()) {
            this.exceptionHandler.install();
        }
        NativeInterface.setClient(this);
        this.pluginClient.loadPlugins(this);
        NdkPluginCaller ndkPluginCaller = NdkPluginCaller.INSTANCE;
        ndkPluginCaller.setNdkPlugin(this.pluginClient.getNdkPlugin());
        if (this.immutableConfig.getTelemetry().contains(Telemetry.USAGE)) {
            ndkPluginCaller.setInternalMetricsEnabled(true);
        }
        this.eventStore.flushOnLaunch();
        this.eventStore.flushAsync();
        this.sessionTracker.flushAsync();
        this.internalMetrics.setConfigDifferences(this.configDifferences);
        this.callbackState.setInternalMetrics(this.internalMetrics);
        registerLifecycleCallbacks();
        registerComponentCallbacks();
        registerListenersInBackground();
        leaveAutoBreadcrumb("Bugsnag loaded", BreadcrumbType.STATE, new HashMap());
        this.logger.d("Bugsnag loaded");
    }

    void registerLifecycleCallbacks() {
        Context context = this.appContext;
        if (context instanceof Application) {
            Application application = (Application) context;
            application.registerActivityLifecycleCallbacks(new SessionLifecycleCallback(this.sessionTracker));
            if (this.immutableConfig.shouldDiscardBreadcrumb(BreadcrumbType.STATE)) {
                return;
            }
            application.registerActivityLifecycleCallbacks(new ActivityBreadcrumbCollector(new Function2<String, Map<String, ? extends Object>, Unit>() { // from class: com.bugsnag.android.Client.2
                @Override // kotlin.jvm.functions.Function2
                /* JADX INFO: renamed from: invoke */
                public /* bridge */ /* synthetic */ Unit mo4invoke(String str, Map<String, ? extends Object> map) {
                    return invoke2(str, (Map<String, ?>) map);
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public Unit invoke2(String str, Map<String, ?> map) {
                    Client.this.leaveBreadcrumb(str, map, BreadcrumbType.STATE);
                    return null;
                }
            }));
        }
    }

    void registerListenersInBackground() {
        try {
            this.bgTaskService.submitTask(TaskType.DEFAULT, new Runnable() { // from class: com.bugsnag.android.Client.3
                @Override // java.lang.Runnable
                public void run() {
                    Client.this.connectivity.registerForNetworkChanges();
                    Client client = Client.this;
                    SystemBroadcastReceiver.register(client.appContext, client.systemBroadcastReceiver, client.logger);
                }
            });
        } catch (RejectedExecutionException e) {
            this.logger.w("Failed to register for system events", e);
        }
    }

    private void persistRunInfo(final LastRunInfo lastRunInfo) {
        try {
            this.bgTaskService.submitTask(TaskType.IO, new Runnable() { // from class: com.bugsnag.android.Client.4
                @Override // java.lang.Runnable
                public void run() {
                    Client.this.lastRunInfoStore.persist(lastRunInfo);
                }
            });
        } catch (RejectedExecutionException e) {
            this.logger.w("Failed to persist last run info", e);
        }
    }

    private void logNull(String str) {
        this.logger.e("Invalid null value supplied to client." + str + ", ignoring");
    }

    private void registerComponentCallbacks() {
        this.appContext.registerComponentCallbacks(new ClientComponentCallbacks(this.deviceDataCollector, new Function2<String, String, Unit>() { // from class: com.bugsnag.android.Client.5
            @Override // kotlin.jvm.functions.Function2
            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
            public Unit mo4invoke(String str, String str2) {
                HashMap map = new HashMap();
                map.put("from", str);
                map.put("to", str2);
                Client.this.leaveAutoBreadcrumb("Orientation changed", BreadcrumbType.STATE, map);
                Client.this.clientObservable.postOrientationChange(str2);
                return null;
            }
        }, new Function2<Boolean, Integer, Unit>() { // from class: com.bugsnag.android.Client.6
            @Override // kotlin.jvm.functions.Function2
            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
            public Unit mo4invoke(Boolean bool, Integer num) {
                Client.this.memoryTrimState.setLowMemory(Boolean.TRUE.equals(bool));
                if (Client.this.memoryTrimState.updateMemoryTrimLevel(num)) {
                    Client client = Client.this;
                    client.leaveAutoBreadcrumb("Trim Memory", BreadcrumbType.STATE, Collections.singletonMap("trimLevel", client.memoryTrimState.getTrimLevelDescription()));
                }
                Client.this.memoryTrimState.emitObservableEvent();
                return null;
            }
        }));
    }

    void setupNdkPlugin() {
        if (!setupNdkDirectory()) {
            this.logger.w("Failed to setup NDK directory.");
            return;
        }
        String absolutePath = this.lastRunInfoStore.getFile().getAbsolutePath();
        LastRunInfo lastRunInfo = this.lastRunInfo;
        this.clientObservable.postNdkInstall(this.immutableConfig, absolutePath, lastRunInfo != null ? lastRunInfo.getConsecutiveLaunchCrashes() : 0);
        syncInitialState();
        this.clientObservable.postNdkDeliverPending();
    }

    private boolean setupNdkDirectory() {
        try {
            return ((Boolean) this.bgTaskService.submitTask(TaskType.IO, new Callable<Boolean>() { // from class: com.bugsnag.android.Client.7
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // java.util.concurrent.Callable
                public Boolean call() {
                    File nativeReportPath = NativeInterface.getNativeReportPath();
                    return Boolean.valueOf(nativeReportPath.exists() || nativeReportPath.mkdirs());
                }
            }).get()).booleanValue();
        } catch (Throwable unused) {
            return false;
        }
    }

    void addObserver(StateObserver stateObserver) {
        this.metadataState.addObserver(stateObserver);
        this.breadcrumbState.addObserver(stateObserver);
        this.sessionTracker.addObserver(stateObserver);
        this.clientObservable.addObserver(stateObserver);
        this.userState.addObserver(stateObserver);
        this.contextState.addObserver(stateObserver);
        this.deliveryDelegate.addObserver(stateObserver);
        this.launchCrashTracker.addObserver(stateObserver);
        this.memoryTrimState.addObserver(stateObserver);
        this.featureFlagState.addObserver(stateObserver);
    }

    void removeObserver(StateObserver stateObserver) {
        this.metadataState.removeObserver(stateObserver);
        this.breadcrumbState.removeObserver(stateObserver);
        this.sessionTracker.removeObserver(stateObserver);
        this.clientObservable.removeObserver(stateObserver);
        this.userState.removeObserver(stateObserver);
        this.contextState.removeObserver(stateObserver);
        this.deliveryDelegate.removeObserver(stateObserver);
        this.launchCrashTracker.removeObserver(stateObserver);
        this.memoryTrimState.removeObserver(stateObserver);
        this.featureFlagState.removeObserver(stateObserver);
    }

    void syncInitialState() {
        this.metadataState.emitObservableEvent();
        this.contextState.emitObservableEvent();
        this.userState.emitObservableEvent();
        this.memoryTrimState.emitObservableEvent();
        this.featureFlagState.emitObservableEvent();
    }

    public void startSession() {
        this.sessionTracker.startSession(false);
    }

    public void pauseSession() {
        this.sessionTracker.pauseSession();
    }

    public boolean resumeSession() {
        return this.sessionTracker.resumeSession();
    }

    @Nullable
    public String getContext() {
        return this.contextState.getContext();
    }

    public void setContext(@Nullable String str) {
        this.contextState.setManualContext(str);
    }

    public void setUser(@Nullable String str, @Nullable String str2, @Nullable String str3) {
        this.userState.setUser(new User(str, str2, str3));
    }

    @NonNull
    public User getUser() {
        return this.userState.getUser();
    }

    public void notify(@NonNull Throwable th, @Nullable OnErrorCallback onErrorCallback) {
        if (th != null) {
            if (this.immutableConfig.shouldDiscardError(th)) {
                return;
            }
            populateAndNotifyAndroidEvent(new Event(th, this.immutableConfig, SeverityReason.newInstance("handledException"), this.metadataState.getMetadata(), this.featureFlagState.getFeatureFlags(), this.logger), onErrorCallback);
            return;
        }
        logNull("notify");
    }

    void notifyUnhandledException(@NonNull Throwable th, Metadata metadata, String str, @Nullable String str2) {
        populateAndNotifyAndroidEvent(new Event(th, this.immutableConfig, SeverityReason.newInstance(str, Severity.ERROR, str2), Metadata.INSTANCE.merge(this.metadataState.getMetadata(), metadata), this.featureFlagState.getFeatureFlags(), this.logger), null);
        LastRunInfo lastRunInfo = this.lastRunInfo;
        int consecutiveLaunchCrashes = lastRunInfo != null ? lastRunInfo.getConsecutiveLaunchCrashes() : 0;
        boolean zIsLaunching = this.launchCrashTracker.isLaunching();
        if (zIsLaunching) {
            consecutiveLaunchCrashes++;
        }
        persistRunInfo(new LastRunInfo(consecutiveLaunchCrashes, true, zIsLaunching));
        this.bgTaskService.shutdown();
    }

    void populateAndNotifyAndroidEvent(@NonNull Event event, @Nullable OnErrorCallback onErrorCallback) {
        event.setDevice(this.deviceDataCollector.generateDeviceWithState(new Date().getTime()));
        event.addMetadata("device", this.deviceDataCollector.getDeviceMetadata());
        event.setApp(this.appDataCollector.generateAppWithState());
        event.addMetadata("app", this.appDataCollector.getAppDataMetadata());
        event.setBreadcrumbs(this.breadcrumbState.copy());
        User user = this.userState.getUser();
        event.setUser(user.getId(), user.getEmail(), user.getName());
        event.setContext(this.contextState.getContext());
        event.setInternalMetrics(this.internalMetrics);
        notifyInternal(event, onErrorCallback);
    }

    void notifyInternal(@NonNull Event event, @Nullable OnErrorCallback onErrorCallback) {
        event.setRedactedKeys(this.metadataState.getMetadata().getRedactedKeys());
        Session currentSession = this.sessionTracker.getCurrentSession();
        if (currentSession != null && (this.immutableConfig.getAutoTrackSessions() || !currentSession.isAutoCaptured())) {
            event.setSession(currentSession);
        }
        if (!this.callbackState.runOnErrorTasks(event, this.logger) || (onErrorCallback != null && !onErrorCallback.onError(event))) {
            this.logger.d("Skipping notification - onError task returned false");
        } else {
            leaveErrorBreadcrumb(event);
            this.deliveryDelegate.deliver(event);
        }
    }

    @NonNull
    public List<Breadcrumb> getBreadcrumbs() {
        return this.breadcrumbState.copy();
    }

    @NonNull
    AppDataCollector getAppDataCollector() {
        return this.appDataCollector;
    }

    @NonNull
    DeviceDataCollector getDeviceDataCollector() {
        return this.deviceDataCollector;
    }

    public void addMetadata(@NonNull String str, @NonNull Map<String, ?> map) {
        if (str != null && map != null) {
            this.metadataState.addMetadata(str, map);
        } else {
            logNull("addMetadata");
        }
    }

    public void addMetadata(@NonNull String str, @NonNull String str2, @Nullable Object obj) {
        if (str != null && str2 != null) {
            this.metadataState.addMetadata(str, str2, obj);
        } else {
            logNull("addMetadata");
        }
    }

    public void clearMetadata(@NonNull String str) {
        if (str != null) {
            this.metadataState.clearMetadata(str);
        } else {
            logNull("clearMetadata");
        }
    }

    public void clearMetadata(@NonNull String str, @NonNull String str2) {
        if (str != null && str2 != null) {
            this.metadataState.clearMetadata(str, str2);
        } else {
            logNull("clearMetadata");
        }
    }

    @NonNull
    Map<String, Object> getMetadata() {
        return this.metadataState.getMetadata().toMap();
    }

    public void leaveBreadcrumb(@NonNull String str) {
        if (str != null) {
            this.breadcrumbState.add(new Breadcrumb(str, this.logger));
        } else {
            logNull("leaveBreadcrumb");
        }
    }

    public void leaveBreadcrumb(@NonNull String str, @NonNull Map<String, Object> map, @NonNull BreadcrumbType breadcrumbType) {
        if (str != null && breadcrumbType != null && map != null) {
            this.breadcrumbState.add(new Breadcrumb(str, breadcrumbType, map, new Date(), this.logger));
        } else {
            logNull("leaveBreadcrumb");
        }
    }

    void leaveAutoBreadcrumb(@NonNull String str, @NonNull BreadcrumbType breadcrumbType, @NonNull Map<String, Object> map) {
        if (this.immutableConfig.shouldDiscardBreadcrumb(breadcrumbType)) {
            return;
        }
        this.breadcrumbState.add(new Breadcrumb(str, breadcrumbType, map, new Date(), this.logger));
    }

    private void leaveErrorBreadcrumb(@NonNull Event event) {
        List<Error> errors = event.getErrors();
        if (errors.size() > 0) {
            String errorClass = errors.get(0).getErrorClass();
            String errorMessage = errors.get(0).getErrorMessage();
            HashMap map = new HashMap();
            map.put("errorClass", errorClass);
            map.put("message", errorMessage);
            map.put("unhandled", String.valueOf(event.isUnhandled()));
            map.put("severity", event.getSeverity().toString());
            this.breadcrumbState.add(new Breadcrumb(errorClass, BreadcrumbType.ERROR, map, new Date(), this.logger));
        }
    }

    public void addFeatureFlag(@NonNull String str, @Nullable String str2) {
        if (str != null) {
            this.featureFlagState.addFeatureFlag(str, str2);
        } else {
            logNull("addFeatureFlag");
        }
    }

    @Nullable
    public LastRunInfo getLastRunInfo() {
        return this.lastRunInfo;
    }

    public void markLaunchCompleted() {
        this.launchCrashTracker.markLaunchCompleted();
    }

    SessionTracker getSessionTracker() {
        return this.sessionTracker;
    }

    @NonNull
    EventStore getEventStore() {
        return this.eventStore;
    }

    protected void finalize() throws Throwable {
        SystemBroadcastReceiver systemBroadcastReceiver = this.systemBroadcastReceiver;
        if (systemBroadcastReceiver != null) {
            try {
                ContextExtensionsKt.unregisterReceiverSafe(this.appContext, systemBroadcastReceiver, this.logger);
            } catch (IllegalArgumentException unused) {
                this.logger.w("Receiver not registered");
            }
        }
        super.finalize();
    }

    ImmutableConfig getConfig() {
        return this.immutableConfig;
    }

    void setBinaryArch(String str) {
        getAppDataCollector().setBinaryArch(str);
    }

    Context getAppContext() {
        return this.appContext;
    }

    Logger getLogger() {
        return this.logger;
    }

    @Nullable
    Plugin getPlugin(@NonNull Class cls) {
        return this.pluginClient.findPlugin(cls);
    }

    Notifier getNotifier() {
        return this.notifier;
    }

    MetadataState getMetadataState() {
        return this.metadataState;
    }

    FeatureFlagState getFeatureFlagState() {
        return this.featureFlagState;
    }

    ContextState getContextState() {
        return this.contextState;
    }

    void setAutoNotify(boolean z) {
        this.pluginClient.setAutoNotify(this, z);
        if (z) {
            this.exceptionHandler.install();
        } else {
            this.exceptionHandler.uninstall();
        }
    }

    void setAutoDetectAnrs(boolean z) {
        this.pluginClient.setAutoDetectAnrs(this, z);
    }
}
