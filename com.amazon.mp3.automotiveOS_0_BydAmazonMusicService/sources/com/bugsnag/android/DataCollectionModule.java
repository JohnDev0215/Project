package com.bugsnag.android;

import android.content.Context;
import android.content.res.Resources;
import android.os.Environment;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.bugsnag.android.internal.BackgroundTaskService;
import com.bugsnag.android.internal.ImmutableConfig;
import com.bugsnag.android.internal.dag.ConfigModule;
import com.bugsnag.android.internal.dag.ContextModule;
import com.bugsnag.android.internal.dag.DependencyModule;
import com.bugsnag.android.internal.dag.SystemServiceModule;
import java.io.File;
import kotlin.Lazy;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: DataCollectionModule.kt */
/* JADX INFO: loaded from: classes5.dex */
@kotlin.Metadata(bv = {1, 0, 3}, d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001BQ\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u000f\u0012\u0006\u0010\u0011\u001a\u00020\u0012¢\u0006\u0002\u0010\u0013R\u001b\u0010\u0014\u001a\u00020\u00158FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u0016\u0010\u0017R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001dX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u001e\u001a\n  *\u0004\u0018\u00010\u001f0\u001fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\"X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010#\u001a\u00020$8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b'\u0010\u0019\u001a\u0004\b%\u0010&R\u000e\u0010(\u001a\u00020)X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010*\u001a\u00020+8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b.\u0010\u0019\u001a\u0004\b,\u0010-¨\u0006/"}, d2 = {"Lcom/bugsnag/android/DataCollectionModule;", "Lcom/bugsnag/android/internal/dag/DependencyModule;", "contextModule", "Lcom/bugsnag/android/internal/dag/ContextModule;", "configModule", "Lcom/bugsnag/android/internal/dag/ConfigModule;", "systemServiceModule", "Lcom/bugsnag/android/internal/dag/SystemServiceModule;", "trackerModule", "Lcom/bugsnag/android/TrackerModule;", "bgTaskService", "Lcom/bugsnag/android/internal/BackgroundTaskService;", "connectivity", "Lcom/bugsnag/android/Connectivity;", Splash.PARAMS_DEVICE_ID, "", "internalDeviceId", "memoryTrimState", "Lcom/bugsnag/android/MemoryTrimState;", "(Lcom/bugsnag/android/internal/dag/ContextModule;Lcom/bugsnag/android/internal/dag/ConfigModule;Lcom/bugsnag/android/internal/dag/SystemServiceModule;Lcom/bugsnag/android/TrackerModule;Lcom/bugsnag/android/internal/BackgroundTaskService;Lcom/bugsnag/android/Connectivity;Ljava/lang/String;Ljava/lang/String;Lcom/bugsnag/android/MemoryTrimState;)V", "appDataCollector", "Lcom/bugsnag/android/AppDataCollector;", "getAppDataCollector", "()Lcom/bugsnag/android/AppDataCollector;", "appDataCollector$delegate", "Lkotlin/Lazy;", "cfg", "Lcom/bugsnag/android/internal/ImmutableConfig;", "ctx", "Landroid/content/Context;", "dataDir", "Ljava/io/File;", "kotlin.jvm.PlatformType", "deviceBuildInfo", "Lcom/bugsnag/android/DeviceBuildInfo;", "deviceDataCollector", "Lcom/bugsnag/android/DeviceDataCollector;", "getDeviceDataCollector", "()Lcom/bugsnag/android/DeviceDataCollector;", "deviceDataCollector$delegate", "logger", "Lcom/bugsnag/android/Logger;", "rootDetector", "Lcom/bugsnag/android/RootDetector;", "getRootDetector", "()Lcom/bugsnag/android/RootDetector;", "rootDetector$delegate", "bugsnag-android-core_release"}, k = 1, mv = {1, 4, 2})
public final class DataCollectionModule extends DependencyModule {

    /* JADX INFO: renamed from: appDataCollector$delegate, reason: from kotlin metadata */
    private final Lazy appDataCollector;
    private final ImmutableConfig cfg;
    private final Context ctx;
    private final File dataDir;
    private final DeviceBuildInfo deviceBuildInfo;

    /* JADX INFO: renamed from: deviceDataCollector$delegate, reason: from kotlin metadata */
    private final Lazy deviceDataCollector;
    private final Logger logger;

    /* JADX INFO: renamed from: rootDetector$delegate, reason: from kotlin metadata */
    private final Lazy rootDetector;

    /* JADX INFO: Access modifiers changed from: private */
    public final RootDetector getRootDetector() {
        return (RootDetector) this.rootDetector.getValue();
    }

    public final AppDataCollector getAppDataCollector() {
        return (AppDataCollector) this.appDataCollector.getValue();
    }

    public final DeviceDataCollector getDeviceDataCollector() {
        return (DeviceDataCollector) this.deviceDataCollector.getValue();
    }

    public DataCollectionModule(ContextModule contextModule, ConfigModule configModule, final SystemServiceModule systemServiceModule, final TrackerModule trackerModule, final BackgroundTaskService backgroundTaskService, final Connectivity connectivity, final String str, final String str2, final MemoryTrimState memoryTrimState) {
        this.ctx = contextModule.getCtx();
        ImmutableConfig config = configModule.getConfig();
        this.cfg = config;
        this.logger = config.getLogger();
        this.deviceBuildInfo = DeviceBuildInfo.INSTANCE.defaultInfo();
        this.dataDir = Environment.getDataDirectory();
        this.appDataCollector = future(new Function0<AppDataCollector>() { // from class: com.bugsnag.android.DataCollectionModule$appDataCollector$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final AppDataCollector invoke() {
                return new AppDataCollector(this.this$0.ctx, this.this$0.ctx.getPackageManager(), this.this$0.cfg, trackerModule.getSessionTracker(), systemServiceModule.getActivityManager(), trackerModule.getLaunchCrashTracker(), memoryTrimState);
            }
        });
        this.rootDetector = future(new Function0<RootDetector>() { // from class: com.bugsnag.android.DataCollectionModule$rootDetector$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final RootDetector invoke() {
                return new RootDetector(this.this$0.deviceBuildInfo, null, null, this.this$0.logger, 6, null);
            }
        });
        this.deviceDataCollector = future(new Function0<DeviceDataCollector>() { // from class: com.bugsnag.android.DataCollectionModule$deviceDataCollector$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final DeviceDataCollector invoke() {
                Connectivity connectivity2 = connectivity;
                Context context = this.this$0.ctx;
                Resources resources = this.this$0.ctx.getResources();
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(resources, "ctx.resources");
                String str3 = str;
                String str4 = str2;
                DeviceBuildInfo deviceBuildInfo = this.this$0.deviceBuildInfo;
                File dataDir = this.this$0.dataDir;
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(dataDir, "dataDir");
                return new DeviceDataCollector(connectivity2, context, resources, str3, str4, deviceBuildInfo, dataDir, this.this$0.getRootDetector(), backgroundTaskService, this.this$0.logger);
            }
        });
    }
}
