package com.amazon.music.featuregating.configuration;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.mp3.configuration.IntlConfiguration;
import com.amazon.music.featuregating.configuration.dataclasses.ConfigurationItem;
import com.amazon.music.featuregating.configuration.dataclasses.MetaConfiguration;
import com.amazon.music.inappmessaging.external.DeviceInformation;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazon.music.platform.Outcome;
import com.amazon.music.platform.data.Callback;
import com.amazonaws.mobileconnectors.remoteconfiguration.ConfigurationSyncCallback;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlin.text.StringsKt__StringsKt;
import kotlinx.serialization.KSerializer;

/* JADX INFO: compiled from: RemoteConfiguration.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000Æ\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 a2\u00020\u00012\u00020\u0002:\u0001aBA\u0012\u0006\u00101\u001a\u000200\u0012\f\u00104\u001a\b\u0012\u0004\u0012\u00020\b03\u0012\f\u00107\u001a\b\u0012\u0004\u0012\u00020603\u0012\n\b\u0002\u00109\u001a\u0004\u0018\u000108\u0012\b\b\u0002\u0010<\u001a\u00020;¢\u0006\u0004\b[\u0010\\B1\b\u0016\u0012\u0006\u0010^\u001a\u00020]\u0012\u0006\u0010_\u001a\u00020\r\u0012\f\u0010A\u001a\b\u0012\u0004\u0012\u00020\b03\u0012\b\u00109\u001a\u0004\u0018\u000108¢\u0006\u0004\b[\u0010`J\b\u0010\u0004\u001a\u00020\u0003H\u0002J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0005H\u0002J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\bH\u0002J\"\u0010\u0010\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\r2\b\u0010\u000f\u001a\u0004\u0018\u00010\rH\u0002J)\u0010\u0010\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\r2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0011H\u0002¢\u0006\u0004\b\u0010\u0010\u0012J\u001c\u0010\u0016\u001a\u00020\u00032\b\u0010\u0014\u001a\u0004\u0018\u00010\u00132\b\b\u0002\u0010\u0015\u001a\u00020\u0005H\u0002J\n\u0010\u0018\u001a\u0004\u0018\u00010\u0017H\u0016J\b\u0010\u0019\u001a\u00020\u0003H\u0016J@\u0010!\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010 \"\b\b\u0000\u0010\u001b*\u00020\u001a2\u0006\u0010\u000e\u001a\u00020\r2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00028\u00000\u001c2\u000e\u0010\u001f\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u001eH\u0016J\b\u0010\"\u001a\u00020\u0011H\u0007J\b\u0010#\u001a\u00020\u0011H\u0007J\b\u0010$\u001a\u00020\u0005H\u0007J\b\u0010%\u001a\u00020\u0005H\u0007J\u0012\u0010'\u001a\u00020\u00032\b\u0010&\u001a\u0004\u0018\u00010\u0013H\u0016J\u0012\u0010(\u001a\u00020\u00032\b\u0010&\u001a\u0004\u0018\u00010\u0013H\u0016J\u0018\u0010,\u001a\u00020\u00032\u000e\u0010+\u001a\n\u0018\u00010)j\u0004\u0018\u0001`*H\u0016J\u0010\u0010/\u001a\u00020\u00032\u0006\u0010.\u001a\u00020-H\u0016R\u0014\u00101\u001a\u0002008\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b1\u00102R\u001a\u00104\u001a\b\u0012\u0004\u0012\u00020\b038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b4\u00105R\u001a\u00107\u001a\b\u0012\u0004\u0012\u000206038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b7\u00105R\u0016\u00109\u001a\u0004\u0018\u0001088\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b9\u0010:R\u0014\u0010<\u001a\u00020;8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b<\u0010=R\u0018\u0010>\u001a\u0004\u0018\u0001068\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b>\u0010?R\u0018\u0010@\u001a\u0004\u0018\u0001068\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b@\u0010?R\u0016\u0010A\u001a\u00020\b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bA\u0010BR\u0018\u0010D\u001a\u0004\u0018\u00010C8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bD\u0010ER\u0014\u0010G\u001a\u00020F8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bG\u0010HR\u0014\u0010I\u001a\u00020-8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bI\u0010JR(\u0010M\u001a\u0016\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u0003\u0018\u00010Kj\u0004\u0018\u0001`L8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bM\u0010NR\u001b\u0010T\u001a\u00020O8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bP\u0010Q\u001a\u0004\bR\u0010SR\"\u0010U\u001a\u00020\u00058V@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\bU\u0010V\u001a\u0004\bW\u0010X\"\u0004\bY\u0010Z¨\u0006b"}, d2 = {"Lcom/amazon/music/featuregating/configuration/RemoteConfiguration;", "Lcom/amazon/music/featuregating/configuration/ConfigurationSource;", "Lcom/amazonaws/mobileconnectors/remoteconfiguration/ConfigurationSyncCallback;", "", "readSavedConfiguration", "", "ignoringCaches", "load", "Lcom/amazon/music/featuregating/configuration/Attributes;", "newAttributes", "updateAttributes", "Lcom/amazonaws/mobileconnectors/remoteconfiguration/Attributes;", "arcusAttributes", "", "key", "value", "addTo", "", "(Lcom/amazonaws/mobileconnectors/remoteconfiguration/Attributes;Ljava/lang/String;Ljava/lang/Integer;)V", "Lcom/amazonaws/mobileconnectors/remoteconfiguration/Configuration;", "config", "remote", "read", "Lcom/amazon/music/featuregating/configuration/dataclasses/MetaConfiguration;", "getMetaConfiguration", "reload", "", "T", "Lkotlin/reflect/KClass;", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "Lkotlinx/serialization/KSerializer;", "serializer", "Lcom/amazon/music/featuregating/configuration/dataclasses/ConfigurationItem;", "getConfigurationItemSync", "throttleDurationInMillis", "cacheDurationInMillis", "isThrottled", "isMissingOrExpired", "syncedConfiguration", "onConfigurationModified", "onConfigurationUnmodified", "Ljava/lang/Exception;", "Lkotlin/Exception;", "e", "onFailure", "", "waitPeriodMillis", "onThrottle", "Lcom/amazon/music/featuregating/configuration/AWSConfigProvider;", "configProvider", "Lcom/amazon/music/featuregating/configuration/AWSConfigProvider;", "Lkotlin/Function0;", "attributesProvider", "Lkotlin/jvm/functions/Function0;", "Ljava/util/Date;", "currentDate", "Lcom/amazon/music/platform/data/Callback;", "onCallback", "Lcom/amazon/music/platform/data/Callback;", "Lcom/amazon/music/featuregating/configuration/ConfigurationMetrics;", "configurationMetrics", "Lcom/amazon/music/featuregating/configuration/ConfigurationMetrics;", "configTimestamp", "Ljava/util/Date;", "throttleUntilDate", "attributes", "Lcom/amazon/music/featuregating/configuration/Attributes;", "Lcom/amazon/music/featuregating/configuration/ConfigurationReader;", "configurationReader", "Lcom/amazon/music/featuregating/configuration/ConfigurationReader;", "Ljava/util/concurrent/atomic/AtomicLong;", "lastLoadTimestamp", "Ljava/util/concurrent/atomic/AtomicLong;", "LOAD_WAIT_TIME", "J", "Lkotlin/Function1;", "Lcom/amazon/music/featuregating/configuration/ConfigurationLogger;", "loggerInDebugBuild", "Lkotlin/jvm/functions/Function1;", "Landroid/os/Handler;", "backgroundHandler$delegate", "Lkotlin/Lazy;", "getBackgroundHandler", "()Landroid/os/Handler;", "backgroundHandler", "inactive", "Z", "getInactive", "()Z", "setInactive", "(Z)V", "<init>", "(Lcom/amazon/music/featuregating/configuration/AWSConfigProvider;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lcom/amazon/music/platform/data/Callback;Lcom/amazon/music/featuregating/configuration/ConfigurationMetrics;)V", "Landroid/content/Context;", "context", "configId", "(Landroid/content/Context;Ljava/lang/String;Lkotlin/jvm/functions/Function0;Lcom/amazon/music/platform/data/Callback;)V", "Companion", "MPFeatureGating_release"}, k = 1, mv = {1, 7, 1})
public final class RemoteConfiguration implements ConfigurationSource, ConfigurationSyncCallback {
    private static final String TAG = RemoteConfiguration.class.getSimpleName();
    private static final Object configMutex = new Object();
    private final long LOAD_WAIT_TIME;
    private Attributes attributes;
    private final Function0<Attributes> attributesProvider;

    /* JADX INFO: renamed from: backgroundHandler$delegate, reason: from kotlin metadata */
    private final Lazy backgroundHandler;
    private final AWSConfigProvider configProvider;
    private Date configTimestamp;
    private final ConfigurationMetrics configurationMetrics;
    private ConfigurationReader configurationReader;
    private final Function0<Date> currentDate;
    private final AtomicLong lastLoadTimestamp;
    private final Function1<String, Unit> loggerInDebugBuild;
    private final Callback onCallback;
    private Date throttleUntilDate;

    /* JADX WARN: Multi-variable type inference failed */
    public RemoteConfiguration(AWSConfigProvider configProvider, Function0<Attributes> attributesProvider, Function0<? extends Date> currentDate, Callback callback, ConfigurationMetrics configurationMetrics) {
        Intrinsics.checkNotNullParameter(configProvider, "configProvider");
        Intrinsics.checkNotNullParameter(attributesProvider, "attributesProvider");
        Intrinsics.checkNotNullParameter(currentDate, "currentDate");
        Intrinsics.checkNotNullParameter(configurationMetrics, "configurationMetrics");
        this.configProvider = configProvider;
        this.attributesProvider = attributesProvider;
        this.currentDate = currentDate;
        this.onCallback = callback;
        this.configurationMetrics = configurationMetrics;
        this.lastLoadTimestamp = new AtomicLong(0L);
        this.LOAD_WAIT_TIME = TimeUnit.SECONDS.toMillis(30L);
        this.loggerInDebugBuild = null;
        this.backgroundHandler = LazyKt__LazyJVMKt.lazy(new Function0<Handler>() { // from class: com.amazon.music.featuregating.configuration.RemoteConfiguration$backgroundHandler$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Handler invoke() {
                HandlerThread handlerThread = new HandlerThread("RemoteConfiguration-Background-Handler", 10);
                handlerThread.start();
                return new Handler(handlerThread.getLooper());
            }
        });
        this.attributes = attributesProvider.invoke();
        readSavedConfiguration();
        updateAttributes(attributesProvider.invoke());
        reload();
    }

    public /* synthetic */ RemoteConfiguration(AWSConfigProvider aWSConfigProvider, Function0 function0, Function0 function02, Callback callback, ConfigurationMetrics configurationMetrics, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(aWSConfigProvider, function0, function02, (i & 8) != 0 ? null : callback, (i & 16) != 0 ? new ConfigurationMetrics() : configurationMetrics);
    }

    private final Handler getBackgroundHandler() {
        return (Handler) this.backgroundHandler.getValue();
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public RemoteConfiguration(Context context, String configId, Function0<Attributes> attributes, Callback callback) {
        this(new ArcusManager(context, configId), attributes, new Function0<Date>() { // from class: com.amazon.music.featuregating.configuration.RemoteConfiguration.1
            @Override // kotlin.jvm.functions.Function0
            public final Date invoke() {
                return new Date(System.currentTimeMillis());
            }
        }, callback, null, 16, null);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(configId, "configId");
        Intrinsics.checkNotNullParameter(attributes, "attributes");
    }

    private final void readSavedConfiguration() {
        Outcome<com.amazonaws.mobileconnectors.remoteconfiguration.Configuration> outcomeOpenConfiguration = this.configProvider.openConfiguration();
        if (outcomeOpenConfiguration instanceof Outcome.Success) {
            read$default(this, (com.amazonaws.mobileconnectors.remoteconfiguration.Configuration) ((Outcome.Success) outcomeOpenConfiguration).getValue(), false, 2, null);
            return;
        }
        if (outcomeOpenConfiguration instanceof Outcome.Failure) {
            Outcome.Failure failure = (Outcome.Failure) outcomeOpenConfiguration;
            this.configurationMetrics.failedToOpenCachedArcusConfiguration(failure.getCause().getMessage());
            if (failure.getCause() instanceof NoSavedConfigurationFound) {
                return;
            }
            Log.e(TAG, "Failed to open cached Arcus configuration", failure.getCause());
        }
    }

    public boolean getInactive() {
        return this.configurationReader != null;
    }

    @Override // com.amazon.music.featuregating.configuration.ConfigurationSource
    public MetaConfiguration getMetaConfiguration() {
        ConfigurationReader configurationReader = this.configurationReader;
        if (configurationReader != null) {
            return configurationReader.getMetaConfiguration();
        }
        return null;
    }

    @Override // com.amazon.music.featuregating.configuration.ConfigurationSource
    public void reload() {
        load(true);
    }

    @Override // com.amazon.music.featuregating.configuration.ConfigurationSource
    public <T> ConfigurationItem<T> getConfigurationItemSync(String key, KClass<T> type, KSerializer<T> serializer) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(type, "type");
        ConfigurationReader configurationReader = this.configurationReader;
        if (configurationReader == null) {
            Log.e(TAG, "getConfigurationItemSync: configurationReader is null");
            return null;
        }
        ConfigurationItem<T> configurationItem = configurationReader.getConfigurationItem(key, type, serializer);
        if (configurationItem == null) {
            return null;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (jCurrentTimeMillis >= this.lastLoadTimestamp.get() + this.LOAD_WAIT_TIME) {
            this.lastLoadTimestamp.set(jCurrentTimeMillis);
            getBackgroundHandler().post(new Runnable() { // from class: com.amazon.music.featuregating.configuration.RemoteConfiguration$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    RemoteConfiguration.m2016getConfigurationItemSync$lambda2$lambda1(this.f$0);
                }
            });
        }
        return configurationItem;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getConfigurationItemSync$lambda-2$lambda-1, reason: not valid java name */
    public static final void m2016getConfigurationItemSync$lambda2$lambda1(RemoteConfiguration this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Function1<String, Unit> function1 = this$0.loggerInDebugBuild;
        if (function1 != null) {
            function1.invoke("Reload arcus configuration if required");
        }
        this$0.load(false);
    }

    private final void load(boolean ignoringCaches) {
        synchronized (configMutex) {
            if (!ignoringCaches) {
                if (!isMissingOrExpired()) {
                    Log.i(TAG, "Skip load");
                    return;
                }
            }
            Attributes attributes = this.attributes;
            Attributes attributesInvoke = this.attributesProvider.invoke();
            if (!Intrinsics.areEqual(attributesInvoke, attributes)) {
                this.configurationReader = null;
            }
            if (isThrottled()) {
                Log.e(TAG, "Arcus config fetch is being throttled");
                return;
            }
            updateAttributes(attributesInvoke);
            this.throttleUntilDate = new Date(this.currentDate.invoke().getTime() + ((long) throttleDurationInMillis()));
            getBackgroundHandler().post(new Runnable() { // from class: com.amazon.music.featuregating.configuration.RemoteConfiguration$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    RemoteConfiguration.m2017load$lambda4$lambda3(this.f$0);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: load$lambda-4$lambda-3, reason: not valid java name */
    public static final void m2017load$lambda4$lambda3(RemoteConfiguration this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.configProvider.sync(this$0);
    }

    @VisibleForTesting(otherwise = 2)
    public final int throttleDurationInMillis() {
        MetaConfiguration metaConfiguration = getMetaConfiguration();
        return (metaConfiguration != null ? (int) metaConfiguration.getThrottleDuration() : 60) * 1000;
    }

    @VisibleForTesting(otherwise = 2)
    public final int cacheDurationInMillis() {
        MetaConfiguration metaConfiguration = getMetaConfiguration();
        return (metaConfiguration != null ? (int) metaConfiguration.getCacheDuration() : 43200) * 1000;
    }

    @VisibleForTesting(otherwise = 2)
    public final boolean isThrottled() {
        Date date = this.throttleUntilDate;
        if (date == null || !this.currentDate.invoke().after(date)) {
            return false;
        }
        Log.e(TAG, "Remote config update is being throttled until " + date);
        return true;
    }

    @VisibleForTesting(otherwise = 2)
    public final boolean isMissingOrExpired() {
        Date date = this.configTimestamp;
        Long lValueOf = date != null ? Long.valueOf(date.getTime()) : null;
        return lValueOf == null || !getInactive() || this.currentDate.invoke().getTime() > lValueOf.longValue() + ((long) cacheDurationInMillis());
    }

    private final void updateAttributes(Attributes newAttributes) {
        if (Intrinsics.areEqual(newAttributes, this.attributes)) {
            return;
        }
        Outcome<com.amazonaws.mobileconnectors.remoteconfiguration.Attributes> outcomeOpenAttributes = this.configProvider.openAttributes();
        if (!(outcomeOpenAttributes instanceof Outcome.Success)) {
            if (outcomeOpenAttributes instanceof Outcome.Failure) {
                Outcome.Failure failure = (Outcome.Failure) outcomeOpenAttributes;
                this.configurationMetrics.failedToCreateOrOpenArcusAttribute(failure.getCause().toString());
                Log.e(TAG, "Failed to open Arcus attributes, assign the new attributes", failure.getCause());
                return;
            }
            throw new NoWhenBranchMatchedException();
        }
        com.amazonaws.mobileconnectors.remoteconfiguration.Attributes attributes = (com.amazonaws.mobileconnectors.remoteconfiguration.Attributes) ((Outcome.Success) outcomeOpenAttributes).getValue();
        addTo(attributes, "carrier", newAttributes.getCarrier());
        addTo(attributes, Splash.PARAMS_DEVICE_ID, newAttributes.getDeviceId());
        addTo(attributes, DeviceInformation.PARAMS_DEVICE_MODEL, newAttributes.getDeviceModel());
        addTo(attributes, "deviceManufacturer", newAttributes.getDeviceManufacturer());
        addTo(attributes, IntlConfiguration.MARKETPLACE, newAttributes.getMarketplace());
        addTo(attributes, Splash.PARAMS_MUSIC_TERRITORY, newAttributes.getTerritory());
        addTo(attributes, "applicationVersion", newAttributes.getAppVersion());
        addTo(attributes, "OSVersionNumber", newAttributes.getOsVersion());
        synchronized (configMutex) {
            this.attributes = newAttributes;
            Unit unit = Unit.INSTANCE;
        }
    }

    private final void addTo(com.amazonaws.mobileconnectors.remoteconfiguration.Attributes arcusAttributes, String key, String value) {
        if (value != null) {
            arcusAttributes.addAttribute(key, value);
        } else {
            arcusAttributes.remove(key);
        }
    }

    private final void addTo(com.amazonaws.mobileconnectors.remoteconfiguration.Attributes arcusAttributes, String key, Integer value) {
        if (value != null) {
            arcusAttributes.addAttribute(key, value);
        } else {
            arcusAttributes.remove(key);
        }
    }

    static /* synthetic */ void read$default(RemoteConfiguration remoteConfiguration, com.amazonaws.mobileconnectors.remoteconfiguration.Configuration configuration, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        remoteConfiguration.read(configuration, z);
    }

    private final void read(com.amazonaws.mobileconnectors.remoteconfiguration.Configuration config, boolean remote) {
        Callback callback;
        if (config == null) {
            Log.w(TAG, "read: configuration is null");
            return;
        }
        try {
            String jsonConfigString = config.getAsJsonString();
            Intrinsics.checkNotNullExpressionValue(jsonConfigString, "jsonConfigString");
            ConfigurationReader configurationReader = new ConfigurationReader(jsonConfigString, this.configurationMetrics, this.loggerInDebugBuild);
            synchronized (configMutex) {
                this.configTimestamp = config.getTimestamp();
                this.configurationReader = configurationReader;
                Unit unit = Unit.INSTANCE;
            }
            if (!remote || (callback = this.onCallback) == null) {
                return;
            }
            callback.callback();
        } catch (Exception e) {
            this.configurationMetrics.failedToReadConfiguration(e.getMessage());
            Log.e(TAG, "Failed to read configuration with error", e);
        }
    }

    @Override // com.amazonaws.mobileconnectors.remoteconfiguration.ConfigurationSyncCallback
    public void onConfigurationModified(com.amazonaws.mobileconnectors.remoteconfiguration.Configuration syncedConfiguration) {
        Log.i(TAG, "Did fetch Arcus config. It has been modified since last fetch");
        read(syncedConfiguration, true);
    }

    @Override // com.amazonaws.mobileconnectors.remoteconfiguration.ConfigurationSyncCallback
    public void onConfigurationUnmodified(com.amazonaws.mobileconnectors.remoteconfiguration.Configuration syncedConfiguration) {
        Log.i(TAG, "Did fetch Arcus config. It was not modified since last fetch");
        read(syncedConfiguration, true);
    }

    @Override // com.amazonaws.mobileconnectors.remoteconfiguration.ConfigurationSyncCallback
    public void onFailure(Exception e) {
        String message;
        boolean z = false;
        if (e != null && (message = e.getMessage()) != null && !StringsKt__StringsKt.contains$default((CharSequence) message, (CharSequence) "There is no network connectivity.", false, 2, (Object) null)) {
            z = true;
        }
        if (z) {
            this.configurationMetrics.arcusConfigFetchFailed(e.getMessage());
        }
        Log.e(TAG, "Arcus config fetch failed", e);
    }

    @Override // com.amazonaws.mobileconnectors.remoteconfiguration.ConfigurationSyncCallback
    public void onThrottle(long waitPeriodMillis) {
        this.configurationMetrics.arcusConfigFetchThrottled(waitPeriodMillis);
        Log.i(TAG, "Arcus config fetch throttled for " + waitPeriodMillis + " milli seconds");
    }
}
