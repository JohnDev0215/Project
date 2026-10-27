package com.amazon.avod.playback.core;

import amazon.android.config.ConfigRegistry;
import android.app.Application;
import android.content.Context;
import com.amazon.avod.config.ServiceDebugConfig;
import com.amazon.avod.config.internal.PlayerAppStartupConfigSynchronizer;
import com.amazon.avod.connectivity.NetworkConnectionManager;
import com.amazon.avod.core.ApplicationComponentsBase;
import com.amazon.avod.core.Framework;
import com.amazon.avod.core.InitializationException;
import com.amazon.avod.experiments.NoOpWeblabClientProxy;
import com.amazon.avod.experiments.WeblabClientProxyProvider;
import com.amazon.avod.http.ServiceClient;
import com.amazon.avod.perf.Profiler;
import com.amazon.avod.perf.TraceKey;
import com.amazon.avod.qahooks.PlaybackQASettings;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.session.ServiceSessionManager;
import com.amazon.avod.threading.SystemMonitor;
import com.amazon.avod.threading.Watchdog;
import com.amazon.avod.util.CurrentActivityTracker;
import com.amazon.avod.util.DLog;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class PlayerApplicationComponents extends ApplicationComponentsBase {

    private static final class SingletonHolder {
        public static final PlayerApplicationComponents INSTANCE = new PlayerApplicationComponents();

        private SingletonHolder() {
        }
    }

    public static PlayerApplicationComponents getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private PlayerApplicationComponents() {
        Profiler.TraceLevel traceLevel = Profiler.TraceLevel.INFO;
        TraceKey traceKeyBeginTrace = Profiler.beginTrace(traceLevel, "Profiler:Initialize");
        Profiler.initialize(PlaybackQASettings.getInstance().getPerfSettings());
        Profiler.endTrace(traceKeyBeginTrace);
        TraceKey traceKeyBeginTrace2 = Profiler.beginTrace(traceLevel, "Watchdog:Initialize");
        Watchdog.getInstance().initialize();
        Profiler.endTrace(traceKeyBeginTrace2);
        TraceKey traceKeyBeginTrace3 = Profiler.beginTrace(traceLevel, "SystemMonitor:Initialize");
        SystemMonitor.getInstance().initialize();
        Profiler.endTrace(traceKeyBeginTrace3);
    }

    public void preInitializeWithValidContext(@Nonnull Application application) {
        TraceKey traceKeyBeginTrace = Profiler.beginTrace(Profiler.TraceLevel.CRITICAL, "ApplicationComponents:preInitializeWithValidContext");
        ConfigRegistry.getInstance().initialize(application);
        ServiceSessionManager.getInstance().initialize(application);
        NetworkConnectionManager.getInstance().initialize(application);
        ServiceTypesProxy.getInstance().initialize(new ApplicationComponentsBase.ServiceTypesFrameworkProxy());
        CurrentActivityTracker.getInstance().attachActivityLifecycleTracker(application);
        Profiler.endTrace(traceKeyBeginTrace);
    }

    @Override // com.amazon.avod.core.ApplicationComponentsBase
    protected void preInitialize(@Nonnull ApplicationComponentsBase.InitParams initParams) {
        DLog.logf("No-op initialization of weblab client proxy provider");
        WeblabClientProxyProvider.getInstance().initialize(new NoOpWeblabClientProxy());
    }

    @Override // com.amazon.avod.core.ApplicationComponentsBase
    protected ImmutableList<ApplicationComponentsBase.InitializationTask> initializeCoreTasks(@Nonnull ApplicationComponentsBase.InitParams initParams) {
        return ImmutableList.of((InitializeIdentity) new InitializeDeviceProperties((InitParams) initParams), (InitializeIdentity) new InitializeSession(), (InitializeIdentity) new InitializeServiceClient(initParams.getContext()), new InitializeIdentity());
    }

    static class InitializeDeviceProperties implements ApplicationComponentsBase.InitializationTask {
        private final InitParams mInitParams;

        InitializeDeviceProperties(@Nonnull InitParams initParams) {
            this.mInitParams = initParams;
        }

        @Override // com.amazon.avod.core.ApplicationComponentsBase.InitializationTask
        public void initialize() throws InitializationException {
            AndroidDeviceIdentity.getInstance().initialize(this.mInitParams);
            DLog.devf("DeviceProperties initialized");
        }
    }

    static class InitializeSession implements ApplicationComponentsBase.InitializationTask {
        InitializeSession() {
        }

        @Override // com.amazon.avod.core.ApplicationComponentsBase.InitializationTask
        public void initialize() {
            TraceKey traceKeyBeginTrace = Profiler.beginTrace(Profiler.TraceLevel.CRITICAL, "ApplicationComponents:RetrieveSessionId");
            ServiceSessionManager.getInstance().retrieveSessionId();
            DLog.logf("SessionId initialized");
            Profiler.endTrace(traceKeyBeginTrace);
        }
    }

    static class InitializeServiceClient implements ApplicationComponentsBase.InitializationTask {
        private static final ImmutableSet<String> SUPPORTED_DEBUG_APIS = ImmutableSet.of("/cdp/usage/v3/GetAppStartupConfig", "/cdp/catalog/GetPlaybackResources");
        private final Context mContext;

        InitializeServiceClient(@Nonnull Context context) {
            this.mContext = context;
        }

        @Override // com.amazon.avod.core.ApplicationComponentsBase.InitializationTask
        public void initialize() {
            TraceKey traceKeyBeginTrace = Profiler.beginTrace(Profiler.TraceLevel.CRITICAL, "ApplicationComponents:InitializeServiceClient");
            ServiceClient.getInstance().initialize(this.mContext);
            if (Framework.isDebugConfigurationEnabled()) {
                ServiceDebugConfig.getInstance().initialize(SUPPORTED_DEBUG_APIS);
            }
            DLog.logf("ServiceClient initialized");
            Profiler.endTrace(traceKeyBeginTrace);
        }
    }

    static class InitializeIdentity implements ApplicationComponentsBase.InitializationTask {
        InitializeIdentity() {
        }

        @Override // com.amazon.avod.core.ApplicationComponentsBase.InitializationTask
        public void initialize() throws InitializationException {
            TraceKey traceKeyBeginTrace = Profiler.beginTrace(Profiler.TraceLevel.CRITICAL, "ApplicationComponents:InitializeIdentity");
            ServiceClient.getInstance().waitOnInitialized();
            AndroidDeviceIdentity.getInstance().waitOnInitialized();
            ServiceSessionManager.getInstance().waitOnInitialized();
            PlayerAppStartupConfigSynchronizer.getInstance().initialize();
            DLog.logf("Identity initialized");
            Profiler.endTrace(traceKeyBeginTrace);
        }
    }

    public static class InitParams implements ApplicationComponentsBase.InitParams {
        private final Context mContext;
        private final String mDeviceId;
        private final String mDeviceTypeId;

        public InitParams(@Nonnull Context context, @Nonnull String str, @Nonnull String str2) {
            this.mContext = (Context) Preconditions.checkNotNull(context, "context");
            this.mDeviceId = (String) Preconditions.checkNotNull(str, Splash.PARAMS_DEVICE_ID);
            this.mDeviceTypeId = (String) Preconditions.checkNotNull(str2, "deviceTypeId");
        }

        @Override // com.amazon.avod.core.ApplicationComponentsBase.InitParams
        @Nonnull
        public Context getContext() {
            return this.mContext;
        }

        @Nonnull
        public String getDeviceId() {
            return this.mDeviceId;
        }

        @Nonnull
        public String getDeviceTypeId() {
            return this.mDeviceTypeId;
        }
    }
}
