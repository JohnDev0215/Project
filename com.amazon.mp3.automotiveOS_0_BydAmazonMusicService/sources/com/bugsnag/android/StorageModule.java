package com.bugsnag.android;

import android.content.Context;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.bugsnag.android.internal.ImmutableConfig;
import com.bugsnag.android.internal.dag.DependencyModule;
import kotlin.Lazy;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: StorageModule.kt */
/* JADX INFO: loaded from: classes5.dex */
@kotlin.Metadata(bv = {1, 0, 3}, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bR\u001d\u0010\t\u001a\u0004\u0018\u00010\n8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\fR\u001b\u0010\u000f\u001a\u00020\u00108BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0013\u0010\u000e\u001a\u0004\b\u0011\u0010\u0012R\u001d\u0010\u0014\u001a\u0004\u0018\u00010\n8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0016\u0010\u000e\u001a\u0004\b\u0015\u0010\fR\u001d\u0010\u0017\u001a\u0004\u0018\u00010\u00188FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u001b\u0010\u000e\u001a\u0004\b\u0019\u0010\u001aR\u001b\u0010\u001c\u001a\u00020\u001d8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b \u0010\u000e\u001a\u0004\b\u001e\u0010\u001fR\u001b\u0010!\u001a\u00020\"8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b%\u0010\u000e\u001a\u0004\b#\u0010$R\u001b\u0010&\u001a\u00020'8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b*\u0010\u000e\u001a\u0004\b(\u0010)R\u001b\u0010+\u001a\u00020,8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b/\u0010\u000e\u001a\u0004\b-\u0010.¨\u00060"}, d2 = {"Lcom/bugsnag/android/StorageModule;", "Lcom/bugsnag/android/internal/dag/DependencyModule;", "appContext", "Landroid/content/Context;", "immutableConfig", "Lcom/bugsnag/android/internal/ImmutableConfig;", "logger", "Lcom/bugsnag/android/Logger;", "(Landroid/content/Context;Lcom/bugsnag/android/internal/ImmutableConfig;Lcom/bugsnag/android/Logger;)V", Splash.PARAMS_DEVICE_ID, "", "getDeviceId", "()Ljava/lang/String;", "deviceId$delegate", "Lkotlin/Lazy;", "deviceIdStore", "Lcom/bugsnag/android/DeviceIdStore;", "getDeviceIdStore", "()Lcom/bugsnag/android/DeviceIdStore;", "deviceIdStore$delegate", "internalDeviceId", "getInternalDeviceId", "internalDeviceId$delegate", "lastRunInfo", "Lcom/bugsnag/android/LastRunInfo;", "getLastRunInfo", "()Lcom/bugsnag/android/LastRunInfo;", "lastRunInfo$delegate", "lastRunInfoStore", "Lcom/bugsnag/android/LastRunInfoStore;", "getLastRunInfoStore", "()Lcom/bugsnag/android/LastRunInfoStore;", "lastRunInfoStore$delegate", "sessionStore", "Lcom/bugsnag/android/SessionStore;", "getSessionStore", "()Lcom/bugsnag/android/SessionStore;", "sessionStore$delegate", "sharedPrefMigrator", "Lcom/bugsnag/android/SharedPrefMigrator;", "getSharedPrefMigrator", "()Lcom/bugsnag/android/SharedPrefMigrator;", "sharedPrefMigrator$delegate", "userStore", "Lcom/bugsnag/android/UserStore;", "getUserStore", "()Lcom/bugsnag/android/UserStore;", "userStore$delegate", "bugsnag-android-core_release"}, k = 1, mv = {1, 4, 2})
public final class StorageModule extends DependencyModule {

    /* JADX INFO: renamed from: deviceIdStore$delegate, reason: from kotlin metadata */
    private final Lazy deviceIdStore;

    /* JADX INFO: renamed from: lastRunInfoStore$delegate, reason: from kotlin metadata */
    private final Lazy lastRunInfoStore;

    /* JADX INFO: renamed from: sessionStore$delegate, reason: from kotlin metadata */
    private final Lazy sessionStore;

    /* JADX INFO: renamed from: sharedPrefMigrator$delegate, reason: from kotlin metadata */
    private final Lazy sharedPrefMigrator;

    /* JADX INFO: renamed from: userStore$delegate, reason: from kotlin metadata */
    private final Lazy userStore;

    /* JADX INFO: renamed from: deviceId$delegate, reason: from kotlin metadata */
    private final Lazy deviceId = future(new Function0<String>() { // from class: com.bugsnag.android.StorageModule$deviceId$2
        {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final String invoke() {
            return this.this$0.getDeviceIdStore().loadDeviceId();
        }
    });

    /* JADX INFO: renamed from: internalDeviceId$delegate, reason: from kotlin metadata */
    private final Lazy internalDeviceId = future(new Function0<String>() { // from class: com.bugsnag.android.StorageModule$internalDeviceId$2
        {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final String invoke() {
            return this.this$0.getDeviceIdStore().loadInternalDeviceId();
        }
    });

    /* JADX INFO: renamed from: lastRunInfo$delegate, reason: from kotlin metadata */
    private final Lazy lastRunInfo = future(new Function0<LastRunInfo>() { // from class: com.bugsnag.android.StorageModule$lastRunInfo$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final LastRunInfo invoke() {
            LastRunInfo lastRunInfoLoad = this.this$0.getLastRunInfoStore().load();
            this.this$0.getLastRunInfoStore().persist(new LastRunInfo(0, false, false));
            return lastRunInfoLoad;
        }
    });

    /* JADX INFO: Access modifiers changed from: private */
    public final DeviceIdStore getDeviceIdStore() {
        return (DeviceIdStore) this.deviceIdStore.getValue();
    }

    public final String getDeviceId() {
        return (String) this.deviceId.getValue();
    }

    public final String getInternalDeviceId() {
        return (String) this.internalDeviceId.getValue();
    }

    public final LastRunInfo getLastRunInfo() {
        return (LastRunInfo) this.lastRunInfo.getValue();
    }

    public final LastRunInfoStore getLastRunInfoStore() {
        return (LastRunInfoStore) this.lastRunInfoStore.getValue();
    }

    public final SessionStore getSessionStore() {
        return (SessionStore) this.sessionStore.getValue();
    }

    public final SharedPrefMigrator getSharedPrefMigrator() {
        return (SharedPrefMigrator) this.sharedPrefMigrator.getValue();
    }

    public final UserStore getUserStore() {
        return (UserStore) this.userStore.getValue();
    }

    public StorageModule(final Context context, final ImmutableConfig immutableConfig, final Logger logger) {
        this.sharedPrefMigrator = future(new Function0<SharedPrefMigrator>() { // from class: com.bugsnag.android.StorageModule$sharedPrefMigrator$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final SharedPrefMigrator invoke() {
                return new SharedPrefMigrator(context);
            }
        });
        this.deviceIdStore = future(new Function0<DeviceIdStore>() { // from class: com.bugsnag.android.StorageModule$deviceIdStore$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final DeviceIdStore invoke() {
                return new DeviceIdStore(context, null, null, null, null, this.this$0.getSharedPrefMigrator(), logger, 30, null);
            }
        });
        this.userStore = future(new Function0<UserStore>() { // from class: com.bugsnag.android.StorageModule$userStore$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final UserStore invoke() {
                return new UserStore(immutableConfig, this.this$0.getDeviceId(), null, this.this$0.getSharedPrefMigrator(), logger, 4, null);
            }
        });
        this.lastRunInfoStore = future(new Function0<LastRunInfoStore>() { // from class: com.bugsnag.android.StorageModule$lastRunInfoStore$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final LastRunInfoStore invoke() {
                return new LastRunInfoStore(immutableConfig);
            }
        });
        this.sessionStore = future(new Function0<SessionStore>() { // from class: com.bugsnag.android.StorageModule$sessionStore$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final SessionStore invoke() {
                return new SessionStore(immutableConfig, logger, null);
            }
        });
    }
}
