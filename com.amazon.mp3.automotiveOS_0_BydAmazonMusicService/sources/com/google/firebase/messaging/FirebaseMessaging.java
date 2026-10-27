package com.google.firebase.messaging;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.GuardedBy;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.android.datatransport.TransportFactory;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.concurrent.NamedThreadFactory;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.DataCollectionDefaultChange;
import com.google.firebase.FirebaseApp;
import com.google.firebase.events.Event;
import com.google.firebase.events.EventHandler;
import com.google.firebase.events.Subscriber;
import com.google.firebase.heartbeatinfo.HeartBeatInfo;
import com.google.firebase.iid.internal.FirebaseInstanceIdInternal;
import com.google.firebase.inject.Provider;
import com.google.firebase.installations.FirebaseInstallationsApi;
import com.google.firebase.messaging.RequestDeduplicator;
import com.google.firebase.messaging.Store;
import com.google.firebase.platforminfo.UserAgentPublisher;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes6.dex */
public class FirebaseMessaging {
    private static final long MAX_DELAY_SEC = TimeUnit.HOURS.toSeconds(8);

    @GuardedBy("FirebaseMessaging.class")
    private static Store store;

    @GuardedBy("FirebaseMessaging.class")
    @VisibleForTesting
    static ScheduledExecutorService syncExecutor;

    @Nullable
    @SuppressLint({"FirebaseUnknownNullness"})
    @VisibleForTesting
    static TransportFactory transportFactory;
    private final AutoInit autoInit;
    private final Context context;
    private final Executor fileExecutor;
    private final FirebaseApp firebaseApp;
    private final FirebaseInstallationsApi fis;
    private final GmsRpc gmsRpc;

    @Nullable
    private final FirebaseInstanceIdInternal iid;
    private final Executor initExecutor;
    private final Application.ActivityLifecycleCallbacks lifecycleCallbacks;
    private final Metadata metadata;
    private final RequestDeduplicator requestDeduplicator;

    @GuardedBy("this")
    private boolean syncScheduledOrRunning;
    private final Executor taskExecutor;
    private final Task<TopicsSubscriber> topicsSubscriberTask;

    @NonNull
    private static synchronized Store getStore(Context context) {
        if (store == null) {
            store = new Store(context);
        }
        return store;
    }

    @NonNull
    @Keep
    static synchronized FirebaseMessaging getInstance(@NonNull FirebaseApp firebaseApp) {
        FirebaseMessaging firebaseMessaging;
        firebaseMessaging = (FirebaseMessaging) firebaseApp.get(FirebaseMessaging.class);
        Preconditions.checkNotNull(firebaseMessaging, "Firebase Messaging component is not present");
        return firebaseMessaging;
    }

    FirebaseMessaging(FirebaseApp firebaseApp, @Nullable FirebaseInstanceIdInternal firebaseInstanceIdInternal, Provider<UserAgentPublisher> provider, Provider<HeartBeatInfo> provider2, FirebaseInstallationsApi firebaseInstallationsApi, @Nullable TransportFactory transportFactory2, Subscriber subscriber) {
        this(firebaseApp, firebaseInstanceIdInternal, provider, provider2, firebaseInstallationsApi, transportFactory2, subscriber, new Metadata(firebaseApp.getApplicationContext()));
    }

    FirebaseMessaging(FirebaseApp firebaseApp, @Nullable FirebaseInstanceIdInternal firebaseInstanceIdInternal, Provider<UserAgentPublisher> provider, Provider<HeartBeatInfo> provider2, FirebaseInstallationsApi firebaseInstallationsApi, @Nullable TransportFactory transportFactory2, Subscriber subscriber, Metadata metadata) {
        this(firebaseApp, firebaseInstanceIdInternal, firebaseInstallationsApi, transportFactory2, subscriber, metadata, new GmsRpc(firebaseApp, metadata, provider, provider2, firebaseInstallationsApi), FcmExecutors.newTaskExecutor(), FcmExecutors.newInitExecutor(), FcmExecutors.newFileIOExecutor());
    }

    FirebaseMessaging(FirebaseApp firebaseApp, @Nullable FirebaseInstanceIdInternal firebaseInstanceIdInternal, FirebaseInstallationsApi firebaseInstallationsApi, @Nullable TransportFactory transportFactory2, Subscriber subscriber, Metadata metadata, GmsRpc gmsRpc, Executor executor, Executor executor2, Executor executor3) {
        this.syncScheduledOrRunning = false;
        transportFactory = transportFactory2;
        this.firebaseApp = firebaseApp;
        this.iid = firebaseInstanceIdInternal;
        this.fis = firebaseInstallationsApi;
        this.autoInit = new AutoInit(subscriber);
        Context applicationContext = firebaseApp.getApplicationContext();
        this.context = applicationContext;
        FcmLifecycleCallbacks fcmLifecycleCallbacks = new FcmLifecycleCallbacks();
        this.lifecycleCallbacks = fcmLifecycleCallbacks;
        this.metadata = metadata;
        this.taskExecutor = executor;
        this.gmsRpc = gmsRpc;
        this.requestDeduplicator = new RequestDeduplicator(executor);
        this.initExecutor = executor2;
        this.fileExecutor = executor3;
        Context applicationContext2 = firebaseApp.getApplicationContext();
        if (applicationContext2 instanceof Application) {
            ((Application) applicationContext2).registerActivityLifecycleCallbacks(fcmLifecycleCallbacks);
        } else {
            Log.w("FirebaseMessaging", "Context " + applicationContext2 + " was not an application, can't register for lifecycle callbacks. Some notification events may be dropped as a result.");
        }
        if (firebaseInstanceIdInternal != null) {
            firebaseInstanceIdInternal.addNewTokenListener(new FirebaseInstanceIdInternal.NewTokenListener() { // from class: com.google.firebase.messaging.FirebaseMessaging$$ExternalSyntheticLambda0
            });
        }
        executor2.execute(new Runnable() { // from class: com.google.firebase.messaging.FirebaseMessaging$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$new$1();
            }
        });
        Task<TopicsSubscriber> taskCreateInstance = TopicsSubscriber.createInstance(this, metadata, gmsRpc, applicationContext, FcmExecutors.newTopicsSyncExecutor());
        this.topicsSubscriberTask = taskCreateInstance;
        taskCreateInstance.addOnSuccessListener(executor2, new OnSuccessListener() { // from class: com.google.firebase.messaging.FirebaseMessaging$$ExternalSyntheticLambda2
            @Override // com.google.android.gms.tasks.OnSuccessListener
            public final void onSuccess(Object obj) {
                this.f$0.lambda$new$2((TopicsSubscriber) obj);
            }
        });
        executor2.execute(new Runnable() { // from class: com.google.firebase.messaging.FirebaseMessaging$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$new$3();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$1() {
        if (isAutoInitEnabled()) {
            startSyncIfNecessary();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$2(TopicsSubscriber topicsSubscriber) {
        if (isAutoInitEnabled()) {
            topicsSubscriber.startTopicsSyncIfNecessary();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$3() {
        ProxyNotificationInitializer.initialize(this.context);
    }

    public boolean isAutoInitEnabled() {
        return this.autoInit.isEnabled();
    }

    @Nullable
    public static TransportFactory getTransportFactory() {
        return transportFactory;
    }

    @VisibleForTesting
    boolean isGmsCorePresent() {
        return this.metadata.isGmscorePresent();
    }

    Context getApplicationContext() {
        return this.context;
    }

    synchronized void setSyncScheduledOrRunning(boolean z) {
        this.syncScheduledOrRunning = z;
    }

    synchronized void syncWithDelaySecondsInternal(long j) {
        enqueueTaskWithDelaySeconds(new SyncTask(this, Math.min(Math.max(30L, 2 * j), MAX_DELAY_SEC)), j);
        this.syncScheduledOrRunning = true;
    }

    void enqueueTaskWithDelaySeconds(Runnable runnable, long j) {
        synchronized (FirebaseMessaging.class) {
            if (syncExecutor == null) {
                syncExecutor = new ScheduledThreadPoolExecutor(1, new NamedThreadFactory("TAG"));
            }
            syncExecutor.schedule(runnable, j, TimeUnit.SECONDS);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startSyncIfNecessary() {
        FirebaseInstanceIdInternal firebaseInstanceIdInternal = this.iid;
        if (firebaseInstanceIdInternal != null) {
            firebaseInstanceIdInternal.getToken();
        } else if (tokenNeedsRefresh(getTokenWithoutTriggeringSync())) {
            startSync();
        }
    }

    private synchronized void startSync() {
        if (!this.syncScheduledOrRunning) {
            syncWithDelaySecondsInternal(0L);
        }
    }

    @Nullable
    @VisibleForTesting
    Store.Token getTokenWithoutTriggeringSync() {
        return getStore(this.context).getToken(getSubtype(), Metadata.getDefaultSenderId(this.firebaseApp));
    }

    String blockingGetToken() throws IOException {
        FirebaseInstanceIdInternal firebaseInstanceIdInternal = this.iid;
        if (firebaseInstanceIdInternal != null) {
            try {
                return (String) Tasks.await(firebaseInstanceIdInternal.getTokenTask());
            } catch (InterruptedException | ExecutionException e) {
                throw new IOException(e);
            }
        }
        final Store.Token tokenWithoutTriggeringSync = getTokenWithoutTriggeringSync();
        if (!tokenNeedsRefresh(tokenWithoutTriggeringSync)) {
            return tokenWithoutTriggeringSync.token;
        }
        final String defaultSenderId = Metadata.getDefaultSenderId(this.firebaseApp);
        try {
            return (String) Tasks.await(this.requestDeduplicator.getOrStartGetTokenRequest(defaultSenderId, new RequestDeduplicator.GetTokenRequest() { // from class: com.google.firebase.messaging.FirebaseMessaging$$ExternalSyntheticLambda4
                @Override // com.google.firebase.messaging.RequestDeduplicator.GetTokenRequest
                public final Task start() {
                    return this.f$0.lambda$blockingGetToken$10(defaultSenderId, tokenWithoutTriggeringSync);
                }
            }));
        } catch (InterruptedException | ExecutionException e2) {
            throw new IOException(e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Task lambda$blockingGetToken$10(final String str, final Store.Token token) {
        return this.gmsRpc.getToken().onSuccessTask(this.fileExecutor, new SuccessContinuation() { // from class: com.google.firebase.messaging.FirebaseMessaging$$ExternalSyntheticLambda5
            @Override // com.google.android.gms.tasks.SuccessContinuation
            public final Task then(Object obj) {
                return this.f$0.lambda$blockingGetToken$9(str, token, (String) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Task lambda$blockingGetToken$9(String str, Store.Token token, String str2) throws Exception {
        getStore(this.context).saveToken(getSubtype(), str, str2, this.metadata.getAppVersionCode());
        if (token == null || !str2.equals(token.token)) {
            invokeOnTokenRefresh(str2);
        }
        return Tasks.forResult(str2);
    }

    private String getSubtype() {
        return "[DEFAULT]".equals(this.firebaseApp.getName()) ? "" : this.firebaseApp.getPersistenceKey();
    }

    @VisibleForTesting
    boolean tokenNeedsRefresh(@Nullable Store.Token token) {
        return token == null || token.needsRefresh(this.metadata.getAppVersionCode());
    }

    private void invokeOnTokenRefresh(String str) {
        if ("[DEFAULT]".equals(this.firebaseApp.getName())) {
            if (Log.isLoggable("FirebaseMessaging", 3)) {
                Log.d("FirebaseMessaging", "Invoking onNewToken for app: " + this.firebaseApp.getName());
            }
            Intent intent = new Intent("com.google.firebase.messaging.NEW_TOKEN");
            intent.putExtra("token", str);
            new FcmBroadcastProcessor(this.context).process(intent);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    class AutoInit {

        @Nullable
        @GuardedBy("this")
        private Boolean autoInitEnabled;

        @Nullable
        @GuardedBy("this")
        private EventHandler<DataCollectionDefaultChange> dataCollectionDefaultChangeEventHandler;

        @GuardedBy("this")
        private boolean initialized;
        private final Subscriber subscriber;

        AutoInit(Subscriber subscriber) {
            this.subscriber = subscriber;
        }

        synchronized void initialize() {
            if (this.initialized) {
                return;
            }
            Boolean enabled = readEnabled();
            this.autoInitEnabled = enabled;
            if (enabled == null) {
                EventHandler<DataCollectionDefaultChange> eventHandler = new EventHandler() { // from class: com.google.firebase.messaging.FirebaseMessaging$AutoInit$$ExternalSyntheticLambda0
                    @Override // com.google.firebase.events.EventHandler
                    public final void handle(Event event) {
                        this.f$0.lambda$initialize$0(event);
                    }
                };
                this.dataCollectionDefaultChangeEventHandler = eventHandler;
                this.subscriber.subscribe(DataCollectionDefaultChange.class, eventHandler);
            }
            this.initialized = true;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$initialize$0(Event event) {
            if (isEnabled()) {
                FirebaseMessaging.this.startSyncIfNecessary();
            }
        }

        synchronized boolean isEnabled() {
            boolean zIsDataCollectionDefaultEnabled;
            initialize();
            Boolean bool = this.autoInitEnabled;
            if (bool == null) {
                zIsDataCollectionDefaultEnabled = FirebaseMessaging.this.firebaseApp.isDataCollectionDefaultEnabled();
            } else {
                zIsDataCollectionDefaultEnabled = bool.booleanValue();
            }
            return zIsDataCollectionDefaultEnabled;
        }

        @Nullable
        private Boolean readEnabled() {
            ApplicationInfo applicationInfo;
            Bundle bundle;
            Context applicationContext = FirebaseMessaging.this.firebaseApp.getApplicationContext();
            SharedPreferences sharedPreferences = applicationContext.getSharedPreferences("com.google.firebase.messaging", 0);
            if (sharedPreferences.contains("auto_init")) {
                return Boolean.valueOf(sharedPreferences.getBoolean("auto_init", false));
            }
            try {
                PackageManager packageManager = applicationContext.getPackageManager();
                if (packageManager == null || (applicationInfo = packageManager.getApplicationInfo(applicationContext.getPackageName(), 128)) == null || (bundle = applicationInfo.metaData) == null || !bundle.containsKey("firebase_messaging_auto_init_enabled")) {
                    return null;
                }
                return Boolean.valueOf(applicationInfo.metaData.getBoolean("firebase_messaging_auto_init_enabled"));
            } catch (PackageManager.NameNotFoundException unused) {
                return null;
            }
        }
    }
}
