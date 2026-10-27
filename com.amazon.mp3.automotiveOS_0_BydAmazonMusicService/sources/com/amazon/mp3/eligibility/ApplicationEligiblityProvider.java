package com.amazon.mp3.eligibility;

import android.content.Context;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.capability.Capabilities;
import com.amazon.mp3.casting.CastingUtil;
import com.amazon.mp3.featuregate.Feature;
import com.amazon.mp3.ondemandplaylists.api.GetOnDemandPlaylistsService;
import com.amazon.mp3.prime.PrimePlaylistUtil;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.music.account.AccountManagerSingleton;
import com.amazon.music.account.AccountStateChangeListener;
import com.amazon.music.account.AccountStateField;
import com.amazon.music.freetier.featuregating.FMPMFeatureGating;
import com.amazon.music.platform.data.eligibility.AppStateEligibility;
import com.amazon.music.platform.data.entity.CollectionType;
import com.amazon.music.platform.data.entity.EntityItemIdentifier;
import com.amazon.music.platform.data.identifiers.Asin;
import com.amazon.music.platform.data.identifiers.UserPlaylistId;
import com.amazon.music.platform.data.subscription.SubscriptionType;
import com.amazon.music.platform.featuregate.MpcFeatureKeys;
import com.amazon.music.platform.providers.EligibilityProvider;
import com.amazon.music.subscription.UserSubscription;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;

/* JADX INFO: compiled from: ApplicationEligiblityProvider.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001f\u0012\u0006\u0010.\u001a\u00020-\u0012\u000e\b\u0002\u0010 \u001a\b\u0012\u0004\u0012\u00020\u000b0\u001f¢\u0006\u0004\b/\u00100J\u0016\u0010\u0007\u001a\u00020\u00062\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0002J\u0010\u0010\n\u001a\n \t*\u0004\u0018\u00010\b0\bH\u0002J\b\u0010\f\u001a\u00020\u000bH\u0002J\b\u0010\r\u001a\u00020\u000bH\u0002J\b\u0010\u000f\u001a\u00020\u000eH\u0002J\b\u0010\u0011\u001a\u00020\u0010H\u0002J\u0018\u0010\u0014\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0013\u0012\u0004\u0012\u00020\u00100\u0012H\u0002J\u0014\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00160\u0012H\u0002J\u000e\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018H\u0002J\u0010\u0010\u001d\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u001bH\u0002J\b\u0010\u001e\u001a\u00020\u0002H\u0016R\u001a\u0010 \u001a\b\u0012\u0004\u0012\u00020\u000b0\u001f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b \u0010!R$\u0010\"\u001a\u0004\u0018\u00010\b8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\"\u0010#\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R\u0017\u0010)\u001a\u00020(8\u0006¢\u0006\f\n\u0004\b)\u0010*\u001a\u0004\b+\u0010,¨\u00061"}, d2 = {"Lcom/amazon/mp3/eligibility/ApplicationEligiblityProvider;", "Lcom/amazon/music/platform/providers/EligibilityProvider;", "Lcom/amazon/music/platform/data/eligibility/AppStateEligibility;", "", "Lcom/amazon/music/account/AccountStateField;", "stateSet", "", "updateSubscription", "Lcom/amazon/music/subscription/UserSubscription;", "kotlin.jvm.PlatformType", "fetchSubscription", "", "isOffline", "isCasting", "Lcom/amazon/music/platform/data/subscription/SubscriptionType;", "getActiveSubscription", "Lcom/amazon/mp3/featuregate/Feature;", "getSupportsEligibilityFeature", "", "Lkotlin/reflect/KClass;", "getCapabilityFeatures", "Lcom/amazon/music/platform/featuregate/MpcFeatureKeys;", "Lcom/amazon/music/platform/featuregate/Feature;", "getMpcFeatures", "", "Lcom/amazon/music/platform/data/entity/EntityItemIdentifier;", "getOnDemandEntities", "", "asin", "getPlaylistEntityId", "getEligibility", "Lkotlin/Function0;", "isWakeWordEnabled", "Lkotlin/jvm/functions/Function0;", "subscription", "Lcom/amazon/music/subscription/UserSubscription;", "getSubscription", "()Lcom/amazon/music/subscription/UserSubscription;", "setSubscription", "(Lcom/amazon/music/subscription/UserSubscription;)V", "Lcom/amazon/mp3/capability/Capabilities;", "capabilities", "Lcom/amazon/mp3/capability/Capabilities;", "getCapabilities", "()Lcom/amazon/mp3/capability/Capabilities;", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;Lkotlin/jvm/functions/Function0;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class ApplicationEligiblityProvider implements EligibilityProvider<AppStateEligibility> {
    private final Capabilities capabilities;
    private final Function0<Boolean> isWakeWordEnabled;
    private UserSubscription subscription;

    public ApplicationEligiblityProvider(Context context, Function0<Boolean> isWakeWordEnabled) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(isWakeWordEnabled, "isWakeWordEnabled");
        this.isWakeWordEnabled = isWakeWordEnabled;
        this.subscription = fetchSubscription();
        Capabilities capabilities = AmazonApplication.getCapabilities();
        Intrinsics.checkNotNullExpressionValue(capabilities, "getCapabilities()");
        this.capabilities = capabilities;
        AccountManagerSingleton.get().registerListener(new AccountStateChangeListener() { // from class: com.amazon.mp3.eligibility.ApplicationEligiblityProvider$$ExternalSyntheticLambda0
            @Override // com.amazon.music.account.AccountStateChangeListener
            public final void onAccountStateChange(Set set) {
                ApplicationEligiblityProvider.m972_init_$lambda0(this.f$0, set);
            }
        });
    }

    public /* synthetic */ ApplicationEligiblityProvider(final Context context, Function0 function0, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? new Function0<Boolean>() { // from class: com.amazon.mp3.eligibility.ApplicationEligiblityProvider.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Boolean invoke() {
                return Boolean.valueOf(SettingsUtil.isWakeWordUserSettingEnabled(context));
            }
        } : function0);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.music.platform.providers.EligibilityProvider
    public AppStateEligibility getEligibility() {
        return new AppStateEligibility(isOffline(), this.isWakeWordEnabled.invoke().booleanValue(), isCasting(), getActiveSubscription(), getOnDemandEntities(), getSupportsEligibilityFeature(), getCapabilityFeatures(), getMpcFeatures());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: _init_$lambda-0, reason: not valid java name */
    public static final void m972_init_$lambda0(ApplicationEligiblityProvider this$0, Set stateSet) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(stateSet, "stateSet");
        this$0.updateSubscription(stateSet);
    }

    private final void updateSubscription(Set<AccountStateField> stateSet) {
        if (stateSet.contains(AccountStateField.USER_HAS_CLOUD_PLAYER_ACCOUNT) || stateSet.contains(AccountStateField.USER_BENEFITS) || stateSet.contains(AccountStateField.USER_MUSIC_TERRITORY_OF_RESIDENCE) || stateSet.contains(AccountStateField.USER_LIBRARY_HOME_MARKETPLACE) || stateSet.contains(AccountStateField.IS_ACCOUNT_VALID)) {
            this.subscription = fetchSubscription();
        }
    }

    private final UserSubscription fetchSubscription() {
        return UserSubscriptionUtil.getUserSubscription();
    }

    private final boolean isOffline() {
        return !ConnectivityUtil.hasAnyInternetConnection(AmazonApplication.getApplication().getApplicationContext());
    }

    private final boolean isCasting() {
        return CastingUtil.isCastingToAlexa();
    }

    private final SubscriptionType getActiveSubscription() {
        UserSubscription userSubscription = this.subscription;
        boolean z = false;
        if (userSubscription != null && userSubscription.isKatana()) {
            return SubscriptionType.MUSIC_HD;
        }
        UserSubscription userSubscription2 = this.subscription;
        if (userSubscription2 != null && userSubscription2.isHawkfireAllDevices()) {
            return SubscriptionType.MUSIC_UNLIMITED;
        }
        UserSubscription userSubscription3 = this.subscription;
        if (userSubscription3 != null && userSubscription3.isPrimeOnly()) {
            return SubscriptionType.PRIME;
        }
        UserSubscription userSubscription4 = this.subscription;
        if (userSubscription4 != null && userSubscription4.isSonicRush()) {
            return SubscriptionType.SONIC_RUSH;
        }
        UserSubscription userSubscription5 = this.subscription;
        if (userSubscription5 != null && userSubscription5.isNightwingOnly()) {
            return SubscriptionType.FREE;
        }
        UserSubscription userSubscription6 = this.subscription;
        if (userSubscription6 != null && userSubscription6.isHawkfireHome()) {
            z = true;
        }
        return z ? SubscriptionType.MUSIC_HOME : SubscriptionType.NONE;
    }

    private final Feature getSupportsEligibilityFeature() {
        return Feature.supports_eligibility;
    }

    private final Map<KClass<?>, Feature> getCapabilityFeatures() {
        return MapsKt__MapsKt.emptyMap();
    }

    private final Map<MpcFeatureKeys, com.amazon.music.platform.featuregate.Feature> getMpcFeatures() {
        return MapsKt__MapsKt.mapOf(TuplesKt.to(MpcFeatureKeys.track_augmented_shuffle, Feature.track_augmented_shuffle), TuplesKt.to(MpcFeatureKeys.should_inject_ads, FMPMFeatureGating.SHOULD_INJECT_ADS));
    }

    private final Set<EntityItemIdentifier> getOnDemandEntities() {
        List listEmptyList = CollectionsKt__CollectionsKt.emptyList();
        if (this.capabilities.isOnDemandPlaylistsEnabled()) {
            Set<String> onDemandPlaylistsFromCache = GetOnDemandPlaylistsService.INSTANCE.get().getOnDemandPlaylistsFromCache();
            if (onDemandPlaylistsFromCache == null) {
                listEmptyList = null;
            } else {
                ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(onDemandPlaylistsFromCache, 10));
                Iterator<T> it = onDemandPlaylistsFromCache.iterator();
                while (it.hasNext()) {
                    arrayList.add(getPlaylistEntityId((String) it.next()));
                }
                listEmptyList = arrayList;
            }
        }
        Set<EntityItemIdentifier> set = listEmptyList != null ? CollectionsKt___CollectionsKt.toSet(listEmptyList) : null;
        return set == null ? SetsKt__SetsKt.emptySet() : set;
    }

    private final EntityItemIdentifier getPlaylistEntityId(String asin) {
        if (PrimePlaylistUtil.isAsin(asin) || !Feature.should_create_entity_item_identifier_by_luid.isEnabled(true)) {
            return new EntityItemIdentifier(new Asin(asin), CollectionType.Playlist.INSTANCE);
        }
        return new EntityItemIdentifier(new UserPlaylistId(asin), CollectionType.Playlist.INSTANCE);
    }
}
