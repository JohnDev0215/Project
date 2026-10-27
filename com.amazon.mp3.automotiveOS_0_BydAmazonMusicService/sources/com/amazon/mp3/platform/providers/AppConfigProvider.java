package com.amazon.mp3.platform.providers;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
import androidx.autofill.HintConstants;
import com.amazon.mp3.account.credentials.AccountCredentialStorage;
import com.amazon.mp3.account.credentials.AccountCredentialUtil;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.config.ApplicationAttributes;
import com.amazon.mp3.configuration.IntlConfiguration;
import com.amazon.mp3.util.IoUtil;
import com.amazon.music.account.AccountManagerSingleton;
import com.amazon.music.account.User;
import com.amazon.music.featuregating.configuration.AppConfiguration;
import com.amazon.music.featuregating.configuration.ArcusAccountReader;
import com.amazon.music.featuregating.configuration.Attributes;
import com.amazon.music.featuregating.configuration.Configuration;
import com.amazon.music.featuregating.configuration.ConfigurationMetrics;
import com.amazon.music.featuregating.configuration.ConfigurationSource;
import com.amazon.music.featuregating.configuration.InternalArcusAccountReader;
import com.amazon.music.featuregating.configuration.LocalConfiguration;
import com.amazon.music.featuregating.configuration.NotificationType;
import com.amazon.music.featuregating.configuration.RemoteConfiguration;
import com.amazon.music.featuregating.configuration.TerritoryInfo;
import com.amazon.music.featuregating.configuration.customsubscriptiontypes.MusicSubscriptionType;
import com.amazon.music.featuregating.configuration.dataclasses.Marketplace;
import com.amazon.music.featuregating.configuration.dataclasses.MarketplaceKt;
import com.amazon.music.featuregating.configuration.dataclasses.MetaConfiguration;
import com.amazon.music.platform.data.Callback;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.io.InputStream;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* JADX INFO: compiled from: AppConfigProvider.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\u000e\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00180\u000fH\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082D¢\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u000eX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/amazon/mp3/platform/providers/AppConfigProvider;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "accountCompositeDisposable", "Lio/reactivex/rxjava3/disposables/CompositeDisposable;", "appConfiguration", "Lcom/amazon/music/featuregating/configuration/AppConfiguration;", "metrics", "Lcom/amazon/music/featuregating/configuration/ConfigurationMetrics;", "resourceId", "", "subscriptionProvider", "Lkotlin/Function0;", "", "Lcom/amazon/music/featuregating/configuration/customsubscriptiontypes/MusicSubscriptionType;", "territoryProvider", "Lcom/amazon/music/featuregating/configuration/TerritoryInfo;", "getConfiguration", "Lcom/amazon/music/featuregating/configuration/Configuration;", "updateLastUsedMarketplace", "", "userBenefits", "Lcom/amazon/music/account/User$Benefit;", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class AppConfigProvider {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = AppConfigProvider.class.getSimpleName();
    private static volatile AppConfigProvider instance;
    private final CompositeDisposable accountCompositeDisposable;
    private final AppConfiguration appConfiguration;
    private final ConfigurationMetrics metrics;
    private final int resourceId;
    private final Function0<Set<MusicSubscriptionType>> subscriptionProvider;
    private final Function0<TerritoryInfo> territoryProvider;

    public /* synthetic */ AppConfigProvider(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    @JvmStatic
    public static final AppConfigProvider getInstance(Context context) {
        return INSTANCE.getInstance(context);
    }

    /* JADX WARN: Type inference failed for: r1v14, types: [T, java.lang.String] */
    private AppConfigProvider(final Context context) {
        this.resourceId = R.raw.configuration;
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        this.accountCompositeDisposable = compositeDisposable;
        this.metrics = new ConfigurationMetrics();
        Function0<TerritoryInfo> function0 = new Function0<TerritoryInfo>() { // from class: com.amazon.mp3.platform.providers.AppConfigProvider$territoryProvider$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TerritoryInfo invoke() {
                String marketplaceId = AccountDetailUtil.get(context).getHomeMarketPlaceId();
                String musicTerritory = AccountDetailUtil.getMusicTerritoryOfResidence();
                Intrinsics.checkNotNullExpressionValue(musicTerritory, "musicTerritory");
                Intrinsics.checkNotNullExpressionValue(marketplaceId, "marketplaceId");
                return new TerritoryInfo(musicTerritory, marketplaceId);
            }
        };
        this.territoryProvider = function0;
        Function0<Set<MusicSubscriptionType>> function02 = new Function0<Set<MusicSubscriptionType>>() { // from class: com.amazon.mp3.platform.providers.AppConfigProvider$subscriptionProvider$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Set<MusicSubscriptionType> invoke() {
                Set setUserBenefits = this.this$0.userBenefits();
                LinkedHashSet linkedHashSet = new LinkedHashSet();
                if (setUserBenefits.contains(User.Benefit.HAWKFIRE_KATANA_ACCESS)) {
                    linkedHashSet.add(MusicSubscriptionType.MUSIC_HD);
                }
                User.Benefit benefit = User.Benefit.HAWKFIRE_BROWSE_AND_ADD;
                if (setUserBenefits.contains(benefit) && setUserBenefits.contains(User.Benefit.HAWKFIRE_PLAYBACK_ACCESS)) {
                    linkedHashSet.add(MusicSubscriptionType.MUSIC_UNLIMITED);
                }
                if (setUserBenefits.contains(benefit) && !setUserBenefits.contains(User.Benefit.PRIME_MUSIC_CONTENT_ACCESS) && !setUserBenefits.contains(User.Benefit.HAWKFIRE_PLAYBACK_ACCESS)) {
                    linkedHashSet.add(MusicSubscriptionType.MUSIC_HOME);
                }
                User.Benefit benefit2 = User.Benefit.SONIC_RUSH;
                if (setUserBenefits.contains(benefit2)) {
                    linkedHashSet.add(MusicSubscriptionType.SONIC_RUSH);
                }
                if (setUserBenefits.contains(User.Benefit.PRIME_MUSIC_BROWSE) && setUserBenefits.contains(User.Benefit.PRIME_MUSIC_CONTENT_ACCESS) && !setUserBenefits.contains(benefit2)) {
                    linkedHashSet.add(MusicSubscriptionType.PRIME);
                }
                if (setUserBenefits.contains(User.Benefit.NIGHTWING)) {
                    linkedHashSet.add(MusicSubscriptionType.FREE);
                }
                if (linkedHashSet.isEmpty()) {
                    linkedHashSet.add(MusicSubscriptionType.NONE);
                }
                return linkedHashSet;
            }
        };
        this.subscriptionProvider = function02;
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        Completable completableSubscribeOn = Completable.fromAction(new Action() { // from class: com.amazon.mp3.platform.providers.AppConfigProvider$$ExternalSyntheticLambda0
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                AppConfigProvider.m1365_init_$lambda0(objectRef, context, this);
            }
        }).subscribeOn(Schedulers.computation());
        final String lastUsedMarketplaceFromSharedPreferences = INSTANCE.getLastUsedMarketplaceFromSharedPreferences(context);
        boolean z = lastUsedMarketplaceFromSharedPreferences == null;
        final boolean z2 = z;
        final Function0<Attributes> function03 = new Function0<Attributes>() { // from class: com.amazon.mp3.platform.providers.AppConfigProvider$attributesProvider$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Attributes invoke() {
                String marketplaceName;
                MetaConfiguration metaConfiguration;
                TerritoryInfo territoryInfo = (TerritoryInfo) this.this$0.territoryProvider.invoke();
                if (z2) {
                    ConfigurationSource configurationSource = objectRef.element;
                    if (configurationSource == null) {
                        Log.w(AppConfigProvider.TAG, "localConfiguration is null, attributesProvider may be invoked too early before reading local config completes");
                    }
                    List<Marketplace> marketplaces = null;
                    if (configurationSource != null && (metaConfiguration = configurationSource.getMetaConfiguration()) != null) {
                        marketplaces = metaConfiguration.getMarketplaces();
                    }
                    marketplaceName = MarketplaceKt.getMarketplaceName(marketplaces, territoryInfo.getMarketplaceId());
                    if (marketplaceName == null) {
                        marketplaceName = AppConfigProvider.INSTANCE.countryCode(context);
                    }
                    AppConfigProvider.INSTANCE.saveLastUsedMarketplaceToSharedPreferences(context, marketplaceName);
                } else {
                    marketplaceName = lastUsedMarketplaceFromSharedPreferences;
                }
                return new Attributes(null, AccountCredentialStorage.get(context).getDeviceId(), Build.MODEL, Build.MANUFACTURER, marketplaceName, territoryInfo.getMusicTerritory(), Integer.valueOf(ApplicationAttributes.getInstance(context).getVersionCode()), Integer.valueOf(Build.VERSION.SDK_INT));
            }
        };
        Completable completableSubscribeOn2 = Completable.fromAction(new Action() { // from class: com.amazon.mp3.platform.providers.AppConfigProvider$$ExternalSyntheticLambda1
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                AppConfigProvider.m1366_init_$lambda1(objectRef2, context, this, function03);
            }
        }).subscribeOn(Schedulers.computation());
        if (z) {
            Completable.concatArray(completableSubscribeOn, completableSubscribeOn2).doOnError(new Consumer() { // from class: com.amazon.mp3.platform.providers.AppConfigProvider$$ExternalSyntheticLambda2
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    AppConfigProvider.m1367_init_$lambda2((Throwable) obj);
                }
            }).blockingSubscribe();
        } else {
            Completable.mergeArray(completableSubscribeOn, completableSubscribeOn2).doOnError(new Consumer() { // from class: com.amazon.mp3.platform.providers.AppConfigProvider$$ExternalSyntheticLambda3
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    AppConfigProvider.m1368_init_$lambda3((Throwable) obj);
                }
            }).blockingSubscribe();
        }
        this.appConfiguration = new AppConfiguration(new Function1<NotificationType, Unit>() { // from class: com.amazon.mp3.platform.providers.AppConfigProvider.3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(NotificationType notificationType) {
                invoke2(notificationType);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(NotificationType it) {
                Intrinsics.checkNotNullParameter(it, "it");
                AppConfigProvider.this.updateLastUsedMarketplace(context);
            }
        }, function0, function02, CollectionsKt__CollectionsKt.listOfNotNull((Object[]) new ConfigurationSource[]{(ConfigurationSource) objectRef2.element, (ConfigurationSource) objectRef.element}));
        updateLastUsedMarketplace(context);
        final AtomicBoolean atomicBoolean = new AtomicBoolean(AccountCredentialUtil.get(context).isSignedIn());
        compositeDisposable.add(AccountCredentialUtil.get(context).getIsAuthenticatedObservable().subscribeOn(Schedulers.io()).filter(new Predicate() { // from class: com.amazon.mp3.platform.providers.AppConfigProvider$$ExternalSyntheticLambda4
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Object obj) {
                return AppConfigProvider.m1369_init_$lambda4(atomicBoolean, (Boolean) obj);
            }
        }).subscribe(new Consumer() { // from class: com.amazon.mp3.platform.providers.AppConfigProvider$$ExternalSyntheticLambda5
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                AppConfigProvider.m1370_init_$lambda6(this.f$0, context, (Boolean) obj);
            }
        }));
        final Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
        objectRef3.element = function0.invoke().getMarketplaceId();
        compositeDisposable.add(AccountDetailUtil.get(context).getMarketPlaceIdObservable().subscribeOn(Schedulers.io()).filter(new Predicate() { // from class: com.amazon.mp3.platform.providers.AppConfigProvider$$ExternalSyntheticLambda6
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Object obj) {
                return AppConfigProvider.m1371_init_$lambda7(objectRef3, (String) obj);
            }
        }).subscribe(new Consumer() { // from class: com.amazon.mp3.platform.providers.AppConfigProvider$$ExternalSyntheticLambda7
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                AppConfigProvider.m1372_init_$lambda9(this.f$0, objectRef3, (String) obj);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Set<User.Benefit> userBenefits() {
        try {
            Set<User.Benefit> benefits = AccountManagerSingleton.get().getUser().getBenefits();
            Intrinsics.checkNotNullExpressionValue(benefits, "{\n            val accoun…r.user.benefits\n        }");
            return benefits;
        } catch (Exception e) {
            Log.e(TAG, Intrinsics.stringPlus("Failed to fetch user benefits, defaulting to None. Error: ", e.getMessage()));
            return SetsKt__SetsKt.emptySet();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r3v2, types: [T, com.amazon.music.featuregating.configuration.ConfigurationSource] */
    /* JADX INFO: renamed from: _init_$lambda-0, reason: not valid java name */
    public static final void m1365_init_$lambda0(Ref.ObjectRef localConfiguration, Context context, AppConfigProvider this$0) {
        Intrinsics.checkNotNullParameter(localConfiguration, "$localConfiguration");
        Intrinsics.checkNotNullParameter(context, "$context");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        try {
            localConfiguration.element = INSTANCE.localConfigSource(context, this$0.resourceId, this$0.metrics);
        } catch (Exception e) {
            Log.e(TAG, "Error initializing local configuration!", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r10v2, types: [T, com.amazon.music.featuregating.configuration.ConfigurationSource] */
    /* JADX INFO: renamed from: _init_$lambda-1, reason: not valid java name */
    public static final void m1366_init_$lambda1(Ref.ObjectRef remoteConfiguration, Context context, AppConfigProvider this$0, Function0 attributesProvider) {
        Intrinsics.checkNotNullParameter(remoteConfiguration, "$remoteConfiguration");
        Intrinsics.checkNotNullParameter(context, "$context");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(attributesProvider, "$attributesProvider");
        try {
            Companion companion = INSTANCE;
            remoteConfiguration.element = companion.remoteConfigSource(context, Companion.getArcusConfigId$default(companion, context, 0, 0, false, this$0.metrics, 14, null), attributesProvider, this$0.metrics, null);
        } catch (Exception e) {
            Log.e(TAG, "Error initializing remote configuration!", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: _init_$lambda-2, reason: not valid java name */
    public static final void m1367_init_$lambda2(Throwable th) {
        Log.e(TAG, "Got exception when read local and remote config sequentially", th);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: _init_$lambda-3, reason: not valid java name */
    public static final void m1368_init_$lambda3(Throwable th) {
        Log.e(TAG, "Got exception when read local and remote config in parallel", th);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: _init_$lambda-4, reason: not valid java name */
    public static final boolean m1369_init_$lambda4(AtomicBoolean isAuthenticated, Boolean it) {
        Intrinsics.checkNotNullParameter(isAuthenticated, "$isAuthenticated");
        boolean z = !it.booleanValue();
        Intrinsics.checkNotNullExpressionValue(it, "it");
        return isAuthenticated.compareAndSet(z, it.booleanValue());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: _init_$lambda-6, reason: not valid java name */
    public static final void m1370_init_$lambda6(AppConfigProvider this$0, Context context, Boolean bool) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(context, "$context");
        synchronized (this$0) {
            INSTANCE.saveLastUsedMarketplaceToSharedPreferences(context, null);
            this$0.appConfiguration.reload();
            Unit unit = Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: _init_$lambda-7, reason: not valid java name */
    public static final boolean m1371_init_$lambda7(Ref.ObjectRef lastUserMarketPlaceId, String str) {
        Intrinsics.checkNotNullParameter(lastUserMarketPlaceId, "$lastUserMarketPlaceId");
        return !Intrinsics.areEqual(lastUserMarketPlaceId.element, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: _init_$lambda-9, reason: not valid java name */
    public static final void m1372_init_$lambda9(AppConfigProvider this$0, Ref.ObjectRef lastUserMarketPlaceId, String marketplaceId) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(lastUserMarketPlaceId, "$lastUserMarketPlaceId");
        synchronized (this$0) {
            Intrinsics.checkNotNullExpressionValue(marketplaceId, "marketplaceId");
            lastUserMarketPlaceId.element = marketplaceId;
            this$0.appConfiguration.reload();
            Unit unit = Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateLastUsedMarketplace(Context context) {
        List<Marketplace> marketplaces = this.appConfiguration.getMarketplaces();
        if (marketplaces != null) {
            INSTANCE.saveLastUsedMarketplaceToSharedPreferences(context, MarketplaceKt.getMarketplaceName(marketplaces, this.territoryProvider.invoke().getMarketplaceId()));
        }
    }

    public Configuration getConfiguration() {
        return this.appConfiguration;
    }

    /* JADX INFO: compiled from: AppConfigProvider.kt */
    @Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\rJ<\u0010\u000e\u001a\u0004\u0018\u00010\u00042\u0006\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00102\b\b\u0002\u0010\u0012\u001a\u00020\u00132\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0007J\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u00042\u0006\u0010\f\u001a\u00020\rJ\u001e\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00040\u00182\u0006\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u0019\u001a\u00020\u0010J\u0010\u0010\u001a\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\rH\u0007J\u0012\u0010\u001b\u001a\u0004\u0018\u00010\u00042\u0006\u0010\f\u001a\u00020\rH\u0002J\u001c\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\u0006\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u0019\u001a\u00020\u0010H\u0002J\"\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0019\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\u0018\u0010 \u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0019\u001a\u00020\u0010H\u0002J<\u0010!\u001a\u0004\u0018\u00010\u001f2\u0006\u0010\f\u001a\u00020\r2\b\u0010\"\u001a\u0004\u0018\u00010\u00042\f\u0010#\u001a\b\u0012\u0004\u0012\u00020%0$2\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010&\u001a\u0004\u0018\u00010'H\u0002J\u0018\u0010(\u001a\u00020\u00132\u0006\u0010\f\u001a\u00020\r2\b\u0010)\u001a\u0004\u0018\u00010\u0004J\u001a\u0010*\u001a\u00020+2\u0006\u0010\f\u001a\u00020\r2\b\u0010,\u001a\u0004\u0018\u00010\u0004H\u0002R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006-"}, d2 = {"Lcom/amazon/mp3/platform/providers/AppConfigProvider$Companion;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "fallbackMarketplace", "instance", "Lcom/amazon/mp3/platform/providers/AppConfigProvider;", "lastUsedMarketplace", "remoteConfigEnvironment", "countryCode", "context", "Landroid/content/Context;", "getArcusConfigId", "arucusResourceId", "", "internalArcusResourceId", "isDebugBuild", "", "metrics", "Lcom/amazon/music/featuregating/configuration/ConfigurationMetrics;", "getEnvironmentFromSharedPreferences", "getEnvironments", "", "resourceId", "getInstance", "getLastUsedMarketplaceFromSharedPreferences", "internalArcusAccounts", "Lcom/amazon/music/featuregating/configuration/InternalArcusAccountReader;", "localConfigSource", "Lcom/amazon/music/featuregating/configuration/ConfigurationSource;", "readResourceFile", "remoteConfigSource", "configId", "attributesProvider", "Lkotlin/Function0;", "Lcom/amazon/music/featuregating/configuration/Attributes;", "callback", "Lcom/amazon/music/platform/data/Callback;", "saveEnvironmentToSharedPreferences", "environment", "saveLastUsedMarketplaceToSharedPreferences", "", IntlConfiguration.MARKETPLACE, "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public static /* synthetic */ String getArcusConfigId$default(Companion companion, Context context, int i, int i2, boolean z, ConfigurationMetrics configurationMetrics, int i3, Object obj) {
            if ((i3 & 2) != 0) {
                i = R.raw.arcus_account;
            }
            int i4 = i;
            if ((i3 & 4) != 0) {
                i2 = R.raw.internal_arcus_accounts;
            }
            int i5 = i2;
            if ((i3 & 8) != 0) {
                z = false;
            }
            boolean z2 = z;
            if ((i3 & 16) != 0) {
                configurationMetrics = null;
            }
            return companion.getArcusConfigId(context, i4, i5, z2, configurationMetrics);
        }

        @VisibleForTesting
        public final String getArcusConfigId(Context context, int arucusResourceId, int internalArcusResourceId, boolean isDebugBuild, ConfigurationMetrics metrics) {
            InternalArcusAccountReader internalArcusAccountReaderInternalArcusAccounts;
            String configId;
            Intrinsics.checkNotNullParameter(context, "context");
            if (isDebugBuild) {
                try {
                    String environmentFromSharedPreferences = getEnvironmentFromSharedPreferences(context);
                    if (environmentFromSharedPreferences != null && (internalArcusAccountReaderInternalArcusAccounts = AppConfigProvider.INSTANCE.internalArcusAccounts(context, internalArcusResourceId)) != null && (configId = internalArcusAccountReaderInternalArcusAccounts.getConfigId(environmentFromSharedPreferences)) != null) {
                        return configId;
                    }
                } catch (Exception e) {
                    if (metrics != null) {
                        metrics.arcusConfigIdNotAvailable(e.getMessage());
                    }
                    return null;
                }
            }
            return ArcusAccountReader.INSTANCE.getArcusConfigId(readResourceFile(context, arucusResourceId));
        }

        private final InternalArcusAccountReader internalArcusAccounts(Context context, int resourceId) {
            try {
                return new InternalArcusAccountReader(readResourceFile(context, resourceId));
            } catch (Exception unused) {
                return null;
            }
        }

        private final String readResourceFile(Context context, int resourceId) throws Exception {
            InputStream inputStreamOpenRawResource = context.getResources().openRawResource(resourceId);
            Intrinsics.checkNotNullExpressionValue(inputStreamOpenRawResource, "context.resources.openRawResource(resourceId)");
            String jsonString = IoUtil.streamToString(inputStreamOpenRawResource);
            try {
                inputStreamOpenRawResource.close();
            } catch (Exception e) {
                Log.e(AppConfigProvider.TAG, "Failed to close input stream", e);
            }
            Intrinsics.checkNotNullExpressionValue(jsonString, "jsonString");
            return jsonString;
        }

        public final List<String> getEnvironments(Context context, int resourceId) {
            Intrinsics.checkNotNullParameter(context, "context");
            InternalArcusAccountReader internalArcusAccountReaderInternalArcusAccounts = internalArcusAccounts(context, resourceId);
            List<String> environments = internalArcusAccountReaderInternalArcusAccounts == null ? null : internalArcusAccountReaderInternalArcusAccounts.getEnvironments();
            return environments == null ? CollectionsKt__CollectionsKt.emptyList() : environments;
        }

        public final String getEnvironmentFromSharedPreferences(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            SharedPreferences sharedPreferences = context.getSharedPreferences(AppConfigProvider.TAG, 0);
            if (sharedPreferences == null) {
                return null;
            }
            return sharedPreferences.getString("remoteConfigEnvironment", null);
        }

        public final boolean saveEnvironmentToSharedPreferences(Context context, String environment) {
            Intrinsics.checkNotNullParameter(context, "context");
            return context.getSharedPreferences(AppConfigProvider.TAG, 0).edit().putString("remoteConfigEnvironment", environment).commit();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final String getLastUsedMarketplaceFromSharedPreferences(Context context) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(AppConfigProvider.TAG, 0);
            if (sharedPreferences == null) {
                return null;
            }
            return sharedPreferences.getString("marketplaceName", null);
        }

        public final String countryCode(Context context) {
            String networkCountryIso;
            Intrinsics.checkNotNullParameter(context, "context");
            Object systemService = context.getSystemService(HintConstants.AUTOFILL_HINT_PHONE);
            String upperCase = null;
            TelephonyManager telephonyManager = systemService instanceof TelephonyManager ? (TelephonyManager) systemService : null;
            if (telephonyManager != null && (networkCountryIso = telephonyManager.getNetworkCountryIso()) != null) {
                Locale locale = Locale.getDefault();
                Intrinsics.checkNotNullExpressionValue(locale, "getDefault()");
                upperCase = networkCountryIso.toUpperCase(locale);
                Intrinsics.checkNotNullExpressionValue(upperCase, "this as java.lang.String).toUpperCase(locale)");
            }
            if (upperCase != null) {
                Log.d(AppConfigProvider.TAG, Intrinsics.stringPlus("Telephony manager marketplace value: ", upperCase));
            } else {
                Log.e(AppConfigProvider.TAG, "Telephony manager marketplace fetch failed! Falling back to US marketplace");
            }
            return upperCase == null ? "US" : upperCase;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void saveLastUsedMarketplaceToSharedPreferences(Context context, String marketplace) {
            context.getSharedPreferences(AppConfigProvider.TAG, 0).edit().putString("marketplaceName", marketplace).apply();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final ConfigurationSource localConfigSource(Context context, int resourceId, ConfigurationMetrics metrics) {
            try {
                return new LocalConfiguration(readResourceFile(context, resourceId), null, 2, null);
            } catch (Exception e) {
                metrics.localConfigurationCreationFailed(e.getMessage());
                Log.e(AppConfigProvider.TAG, Intrinsics.stringPlus("Failed to init configuration with resourceId ", Integer.valueOf(resourceId)), e);
                return null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final ConfigurationSource remoteConfigSource(Context context, String configId, Function0<Attributes> attributesProvider, ConfigurationMetrics metrics, Callback callback) {
            if (configId == null) {
                return null;
            }
            try {
                return new RemoteConfiguration(context, configId, attributesProvider, callback);
            } catch (Exception e) {
                metrics.remoteConfigurationCreationFailed(e.getMessage());
                Log.e(AppConfigProvider.TAG, Intrinsics.stringPlus("Failed to init remote configuration with id ", configId), e);
                return null;
            }
        }

        @JvmStatic
        public final AppConfigProvider getInstance(Context context) {
            AppConfigProvider appConfigProvider;
            Intrinsics.checkNotNullParameter(context, "context");
            AppConfigProvider appConfigProvider2 = AppConfigProvider.instance;
            if (appConfigProvider2 != null) {
                return appConfigProvider2;
            }
            synchronized (this) {
                appConfigProvider = AppConfigProvider.instance;
                if (appConfigProvider == null) {
                    appConfigProvider = new AppConfigProvider(context, null);
                    AppConfigProvider.instance = appConfigProvider;
                }
            }
            return appConfigProvider;
        }
    }
}
