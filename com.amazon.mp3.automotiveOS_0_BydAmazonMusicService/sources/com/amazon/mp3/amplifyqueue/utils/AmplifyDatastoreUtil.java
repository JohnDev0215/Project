package com.amazon.mp3.amplifyqueue.utils;

import android.util.Log;
import com.amazon.digitalmusicxp.callbacks.Outcome;
import com.amazon.digitalmusicxp.enums.ActionStrategyTypeEnum;
import com.amazon.digitalmusicxp.enums.QueueInteractionPatternEnum;
import com.amazon.digitalmusicxp.enums.ServiceTierEnum;
import com.amazon.digitalmusicxp.enums.SingleActionStrategyActionTypeEnum;
import com.amazon.digitalmusicxp.models.QueueCapabilitySet;
import com.amazon.digitalmusicxp.models.QueueEntityCapabilitySet;
import com.amazon.digitalmusicxp.types.ActionStrategy;
import com.amazon.digitalmusicxp.types.DefaultQueueCapabilityIds;
import com.amazon.digitalmusicxp.types.QueueCapability;
import com.amazon.digitalmusicxp.types.QueueEntityCapability;
import com.amazon.digitalmusicxp.types.Setting;
import com.amazon.digitalmusicxp.types.SingleActionStrategy;
import com.amazon.mp3.amplifyqueue.converters.AmplifyModelConverter;
import com.amazon.music.platform.metrics.event.FlexWrapper;
import com.amazon.music.platform.providers.MetricsProvider;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.datastore.DataStoreChannelEventName;
import com.amplifyframework.datastore.DataStoreException;
import com.amplifyframework.hub.HubChannel;
import com.amplifyframework.hub.HubEvent;
import com.amplifyframework.rx.RxAmplify;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.schedulers.Schedulers;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AmplifyDatastoreUtil.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b9\u0010:J\u000e\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0002J\u0010\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005J%\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00000\f\"\b\b\u0000\u0010\n*\u00020\t2\u0006\u0010\u000b\u001a\u00028\u0000¢\u0006\u0004\b\r\u0010\u000eJ\b\u0010\u000f\u001a\u0004\u0018\u00010\u0003J\u000e\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\u0010J\u000e\u0010\u0014\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\u0010J\u0014\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00170\u00022\u0006\u0010\u0016\u001a\u00020\u0015J\u0014\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00170\u00022\u0006\u0010\u001a\u001a\u00020\u0019J\u0014\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001c0\u00022\u0006\u0010\u001a\u001a\u00020\u0019R\u001c\u0010\u001f\u001a\n \u001e*\u0004\u0018\u00010\u00190\u00198\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001f\u0010 R\u0018\u0010!\u001a\u0004\u0018\u00010\u00038\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b!\u0010\"R\u0016\u0010#\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b#\u0010$R$\u0010&\u001a\u00020\u00072\u0006\u0010%\u001a\u00020\u00078\u0006@BX\u0086\u000e¢\u0006\f\n\u0004\b&\u0010$\u001a\u0004\b&\u0010'R\u0014\u0010)\u001a\u00020(8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b)\u0010*R\u0014\u0010+\u001a\u00020(8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b+\u0010*R\u0014\u0010-\u001a\u00020,8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b-\u0010.R\u0014\u0010/\u001a\u00020,8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b/\u0010.R\u0014\u00100\u001a\u00020,8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b0\u0010.R\u0014\u00102\u001a\u0002018\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b2\u00103R\u0017\u00105\u001a\u0002048\u0006¢\u0006\f\n\u0004\b5\u00106\u001a\u0004\b7\u00108¨\u0006;"}, d2 = {"Lcom/amazon/mp3/amplifyqueue/utils/AmplifyDatastoreUtil;", "", "Lcom/amazon/digitalmusicxp/callbacks/Outcome;", "Lcom/amazon/digitalmusicxp/types/Setting;", "getSettingHelper", "", "e", "", "isErrorShouldRetry", "Lcom/amplifyframework/core/model/Model;", "T", "data", "Lio/reactivex/rxjava3/core/Observable;", "saveAndReturnObservable", "(Lcom/amplifyframework/core/model/Model;)Lio/reactivex/rxjava3/core/Observable;", "getSetting", "Lcom/amazon/music/platform/providers/MetricsProvider;", "metricsProvider", "", "startDataStore", "startHubListener", "Lcom/amazon/digitalmusicxp/enums/ServiceTierEnum;", "serviceTier", "Lcom/amazon/digitalmusicxp/models/QueueCapabilitySet;", "getDefaultQueueCapabilitySet", "", "queueInteractionPattern", "getDefaultQueueCapabilitySetV2", "Lcom/amazon/digitalmusicxp/models/QueueEntityCapabilitySet;", "getDefaultQueueEntityCapabilitySet", "kotlin.jvm.PlatformType", "TAG", "Ljava/lang/String;", "defaultSetting", "Lcom/amazon/digitalmusicxp/types/Setting;", "isHubStarted", "Z", "<set-?>", "isSyncQueriesReady", "()Z", "Lcom/amazon/digitalmusicxp/types/ActionStrategy;", "actionStrategyEnabled", "Lcom/amazon/digitalmusicxp/types/ActionStrategy;", "actionStrategyDisabled", "Lcom/amazon/digitalmusicxp/types/QueueCapability;", "defaultOnlineQueueCapability", "Lcom/amazon/digitalmusicxp/types/QueueCapability;", "defaultOfflineQueueCapability", "defaultNonSonicRushQueueCapability", "Lcom/amazon/digitalmusicxp/types/QueueEntityCapability;", "defaultEntityCapability", "Lcom/amazon/digitalmusicxp/types/QueueEntityCapability;", "Lcom/amazon/digitalmusicxp/types/DefaultQueueCapabilityIds;", "defaultQueueCapabilityIdsNonSonicRushTier", "Lcom/amazon/digitalmusicxp/types/DefaultQueueCapabilityIds;", "getDefaultQueueCapabilityIdsNonSonicRushTier", "()Lcom/amazon/digitalmusicxp/types/DefaultQueueCapabilityIds;", "<init>", "()V", "amplifyqueue_release"}, k = 1, mv = {1, 6, 0})
public final class AmplifyDatastoreUtil {
    public static final AmplifyDatastoreUtil INSTANCE = new AmplifyDatastoreUtil();
    private static final String TAG = AmplifyDatastoreUtil.class.getSimpleName();
    private static final ActionStrategy actionStrategyDisabled;
    private static final ActionStrategy actionStrategyEnabled;
    private static final QueueEntityCapability defaultEntityCapability;
    private static final QueueCapability defaultNonSonicRushQueueCapability;
    private static final QueueCapability defaultOfflineQueueCapability;
    private static final QueueCapability defaultOnlineQueueCapability;
    private static final DefaultQueueCapabilityIds defaultQueueCapabilityIdsNonSonicRushTier;
    private static Setting defaultSetting;
    private static boolean isHubStarted;
    private static boolean isSyncQueriesReady;

    /* JADX INFO: compiled from: AmplifyDatastoreUtil.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ServiceTierEnum.values().length];
            iArr[ServiceTierEnum.UNLIMITED.ordinal()] = 1;
            iArr[ServiceTierEnum.PRIME.ordinal()] = 2;
            iArr[ServiceTierEnum.LIBRARY.ordinal()] = 3;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private AmplifyDatastoreUtil() {
    }

    static {
        ActionStrategyTypeEnum actionStrategyTypeEnum = ActionStrategyTypeEnum.SINGLE;
        SingleActionStrategyActionTypeEnum singleActionStrategyActionTypeEnum = SingleActionStrategyActionTypeEnum.BOOLEAN;
        ActionStrategy actionStrategy = new ActionStrategy(actionStrategyTypeEnum, new SingleActionStrategy(singleActionStrategyActionTypeEnum, null, Boolean.TRUE, 2, null), null, 4, null);
        actionStrategyEnabled = actionStrategy;
        ActionStrategy actionStrategy2 = new ActionStrategy(actionStrategyTypeEnum, new SingleActionStrategy(singleActionStrategyActionTypeEnum, null, Boolean.FALSE, 2, null), null, 4, null);
        actionStrategyDisabled = actionStrategy2;
        defaultOnlineQueueCapability = new QueueCapability(actionStrategy, actionStrategy, actionStrategy2, actionStrategy2, null, 16, null);
        defaultOfflineQueueCapability = new QueueCapability(actionStrategy2, actionStrategy, actionStrategy, actionStrategy, null, 16, null);
        defaultNonSonicRushQueueCapability = new QueueCapability(actionStrategy2, actionStrategy2, actionStrategy, actionStrategy, null, 16, null);
        defaultEntityCapability = new QueueEntityCapability(actionStrategy, actionStrategy, actionStrategy, actionStrategy, actionStrategy2, actionStrategy, actionStrategy);
        defaultQueueCapabilityIdsNonSonicRushTier = new DefaultQueueCapabilityIds("00000000-0000-0000-0000-000000000021", "00000000-0000-0000-0000-000000000012", "00000000-0000-0000-0000-000000000004", "00000000-0000-0000-0000-000000000000");
    }

    public final DefaultQueueCapabilityIds getDefaultQueueCapabilityIdsNonSonicRushTier() {
        return defaultQueueCapabilityIdsNonSonicRushTier;
    }

    public final boolean isErrorShouldRetry(Throwable e) {
        if (e == null || !(e instanceof DataStoreException)) {
            return false;
        }
        DataStoreException dataStoreException = (DataStoreException) e;
        if (!Intrinsics.areEqual(dataStoreException.getMessage(), "Timed out acquiring orchestrator lock.")) {
            Throwable cause = dataStoreException.getCause();
            if (!Intrinsics.areEqual(cause == null ? null : cause.getMessage(), "Timed out acquiring orchestrator lock.")) {
                return false;
            }
        }
        return true;
    }

    public final <T extends Model> Observable<T> saveAndReturnObservable(T data) {
        Intrinsics.checkNotNullParameter(data, "data");
        Observable<T> observableDefaultIfEmpty = RxAmplify.DataStore.save(data).toObservable().defaultIfEmpty(data);
        Intrinsics.checkNotNullExpressionValue(observableDefaultIfEmpty, "DataStore.save(data).toO…T>().defaultIfEmpty(data)");
        return observableDefaultIfEmpty;
    }

    public final Setting getSetting() {
        Setting setting = defaultSetting;
        if (setting != null) {
            return setting;
        }
        Outcome<Setting> settingHelper = getSettingHelper();
        if (settingHelper instanceof Outcome.Success) {
            Setting setting2 = (Setting) ((Outcome.Success) settingHelper).getValue();
            defaultSetting = setting2;
            return setting2;
        }
        if (settingHelper instanceof Outcome.Failure) {
            return null;
        }
        throw new NoWhenBranchMatchedException();
    }

    private final Outcome<Setting> getSettingHelper() {
        try {
            Setting setting = (Setting) RxAmplify.DataStore.query(com.amazon.mp3.amplifyqueue.model.Setting.class).firstOrError().subscribeOn(Schedulers.io()).map(new Function() { // from class: com.amazon.mp3.amplifyqueue.utils.AmplifyDatastoreUtil$$ExternalSyntheticLambda2
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return AmplifyDatastoreUtil.m217getSettingHelper$lambda2((com.amazon.mp3.amplifyqueue.model.Setting) obj);
                }
            }).blockingGet();
            if (setting != null) {
                return new Outcome.Success(setting);
            }
            Log.e(TAG, "getDefaultSetting no data");
            return new Outcome.Failure(new RuntimeException("no data for setting"));
        } catch (Exception e) {
            Log.e(TAG, "getDefaultSetting failed", e);
            return new Outcome.Failure(new RuntimeException("getSetting failed", e));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getSettingHelper$lambda-2, reason: not valid java name */
    public static final Setting m217getSettingHelper$lambda2(com.amazon.mp3.amplifyqueue.model.Setting setting) {
        if (setting == null) {
            return null;
        }
        return (Setting) AmplifyModelConverter.INSTANCE.convertFromAmplify(setting, Setting.class);
    }

    public final void startDataStore(final MetricsProvider metricsProvider) {
        Intrinsics.checkNotNullParameter(metricsProvider, "metricsProvider");
        final long jCurrentTimeMillis = System.currentTimeMillis();
        RxAmplify.DataStore.start().subscribe(new Action() { // from class: com.amazon.mp3.amplifyqueue.utils.AmplifyDatastoreUtil$$ExternalSyntheticLambda0
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                AmplifyDatastoreUtil.m218startDataStore$lambda3(jCurrentTimeMillis);
            }
        }, new Consumer() { // from class: com.amazon.mp3.amplifyqueue.utils.AmplifyDatastoreUtil$$ExternalSyntheticLambda1
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                AmplifyDatastoreUtil.m219startDataStore$lambda4(jCurrentTimeMillis, metricsProvider, (Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: startDataStore$lambda-3, reason: not valid java name */
    public static final void m218startDataStore$lambda3(long j) {
        long jCurrentTimeMillis = System.currentTimeMillis() - j;
        Log.i(TAG, "DataStore started after " + jCurrentTimeMillis + " ms");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: startDataStore$lambda-4, reason: not valid java name */
    public static final void m219startDataStore$lambda4(long j, final MetricsProvider metricsProvider, Throwable error) {
        Intrinsics.checkNotNullParameter(metricsProvider, "$metricsProvider");
        Intrinsics.checkNotNullParameter(error, "error");
        long jCurrentTimeMillis = System.currentTimeMillis() - j;
        FlexWrapper.Builder builderWithStr2 = new FlexWrapper.Builder().withName("cloudQueueDatastoreOperation").withStr1("configure").withStr2(error.getMessage());
        Throwable cause = error.getCause();
        FlexWrapper.Builder builderWithStr3 = builderWithStr2.withStr3(cause == null ? null : cause.getMessage());
        DataStoreException dataStoreException = error instanceof DataStoreException ? (DataStoreException) error : null;
        final FlexWrapper flexWrapperBuild = builderWithStr3.withStr4(dataStoreException != null ? dataStoreException.getRecoverySuggestion() : null).withNum1(Float.valueOf(0.0f)).withNum2(Float.valueOf(1.0f)).withNum3(Float.valueOf(jCurrentTimeMillis)).build();
        CQEThrottleUtil.INSTANCE.datastoreThrottle(new Function0<Unit>() { // from class: com.amazon.mp3.amplifyqueue.utils.AmplifyDatastoreUtil$startDataStore$2$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                metricsProvider.sendMetricsEvent(flexWrapperBuild);
            }
        });
        Log.e(TAG, "Error starting DataStore", error);
    }

    public final void startHubListener(MetricsProvider metricsProvider) {
        Intrinsics.checkNotNullParameter(metricsProvider, "metricsProvider");
        if (isHubStarted) {
            return;
        }
        Log.i(TAG, "Start hub listener");
        final long jCurrentTimeMillis = System.currentTimeMillis();
        RxAmplify.Hub.on(HubChannel.DATASTORE).filter(new Predicate() { // from class: com.amazon.mp3.amplifyqueue.utils.AmplifyDatastoreUtil$$ExternalSyntheticLambda3
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Object obj) {
                return AmplifyDatastoreUtil.m220startHubListener$lambda5((HubEvent) obj);
            }
        }).subscribe(new Consumer() { // from class: com.amazon.mp3.amplifyqueue.utils.AmplifyDatastoreUtil$$ExternalSyntheticLambda4
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                AmplifyDatastoreUtil.m221startHubListener$lambda6(jCurrentTimeMillis, (HubEvent) obj);
            }
        });
        isHubStarted = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: startHubListener$lambda-5, reason: not valid java name */
    public static final boolean m220startHubListener$lambda5(HubEvent hubEvent) {
        return Intrinsics.areEqual(DataStoreChannelEventName.SYNC_QUERIES_READY.toString(), hubEvent.getName());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: startHubListener$lambda-6, reason: not valid java name */
    public static final void m221startHubListener$lambda6(long j, HubEvent hubEvent) {
        long jCurrentTimeMillis = System.currentTimeMillis() - j;
        isSyncQueriesReady = true;
        Log.i(TAG, "Sync queries completed after " + jCurrentTimeMillis + " ms");
    }

    public final Outcome<QueueCapabilitySet> getDefaultQueueCapabilitySet(ServiceTierEnum serviceTier) {
        Intrinsics.checkNotNullParameter(serviceTier, "serviceTier");
        int i = WhenMappings.$EnumSwitchMapping$0[serviceTier.ordinal()];
        if (i == 1) {
            return new Outcome.Success(new QueueCapabilitySet("00000000-0000-0000-0000-000000000021", defaultNonSonicRushQueueCapability, 0L, 0L, null, serviceTier, null));
        }
        if (i == 2) {
            return new Outcome.Success(new QueueCapabilitySet("00000000-0000-0000-0000-000000000012", defaultNonSonicRushQueueCapability, 0L, 0L, null, serviceTier, null));
        }
        if (i == 3) {
            return new Outcome.Success(new QueueCapabilitySet("00000000-0000-0000-0000-000000000004", defaultNonSonicRushQueueCapability, 0L, 0L, null, serviceTier, null));
        }
        return new Outcome.Failure(new RuntimeException(Intrinsics.stringPlus("No default value for ", serviceTier)));
    }

    public final Outcome<QueueCapabilitySet> getDefaultQueueCapabilitySetV2(String queueInteractionPattern) {
        Intrinsics.checkNotNullParameter(queueInteractionPattern, "queueInteractionPattern");
        if (Intrinsics.areEqual(queueInteractionPattern, QueueInteractionPatternEnum.ONLINE_QUEUE_CREATION_ONLINE_TRACK_CONSUMPTION.name())) {
            return new Outcome.Success(new QueueCapabilitySet("b7c16454-047b-4c28-af36-599aa19bbccc", defaultOnlineQueueCapability, 0L, 0L, queueInteractionPattern, null, null, 96, null));
        }
        if (Intrinsics.areEqual(queueInteractionPattern, QueueInteractionPatternEnum.LOCAL_QUEUE_CREATION_LOCAL_TRACK_CONSUMPTION.name())) {
            return new Outcome.Success(new QueueCapabilitySet("b317ca67-12ed-407b-a8da-c2479c0f0bba", defaultOfflineQueueCapability, 0L, 0L, queueInteractionPattern, null, null, 96, null));
        }
        return new Outcome.Failure(new RuntimeException(Intrinsics.stringPlus("No default value for ", queueInteractionPattern)));
    }

    public final Outcome<QueueEntityCapabilitySet> getDefaultQueueEntityCapabilitySet(String queueInteractionPattern) {
        Intrinsics.checkNotNullParameter(queueInteractionPattern, "queueInteractionPattern");
        if (Intrinsics.areEqual(queueInteractionPattern, QueueInteractionPatternEnum.LOCAL_QUEUE_CREATION_LOCAL_TRACK_CONSUMPTION.name())) {
            return new Outcome.Success(new QueueEntityCapabilitySet("00000000-0000-0000-0000-000000000005", defaultEntityCapability, 0L, 0L));
        }
        return new Outcome.Failure(new RuntimeException("No default value"));
    }
}
