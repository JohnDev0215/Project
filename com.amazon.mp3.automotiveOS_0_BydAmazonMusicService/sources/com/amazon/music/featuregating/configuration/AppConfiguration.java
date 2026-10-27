package com.amazon.music.featuregating.configuration;

import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.music.featuregating.configuration.Configuration;
import com.amazon.music.featuregating.configuration.customsubscriptiontypes.MusicSubscriptionType;
import com.amazon.music.featuregating.configuration.dataclasses.ConfigurationItem;
import com.amazon.music.featuregating.configuration.dataclasses.ConfigurationValue;
import com.amazon.music.featuregating.configuration.dataclasses.CustomRegions;
import com.amazon.music.featuregating.configuration.dataclasses.Marketplace;
import com.amazon.music.featuregating.configuration.dataclasses.MarketplaceKt;
import com.amazon.music.featuregating.configuration.dataclasses.MetaConfiguration;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlinx.serialization.KSerializer;

/* JADX INFO: compiled from: AppConfiguration.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u00002\u00020\u0001BO\u0012\u0016\b\u0002\u0010\u001d\u001a\u0010\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u001b\u0012\f\u0010!\u001a\b\u0012\u0004\u0012\u00020 0\u001f\u0012\u0012\u0010$\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020#0\u00130\u001f\u0012\f\u0010&\u001a\b\u0012\u0004\u0012\u00020%0\u0002¢\u0006\u0004\b-\u0010.J\u000e\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0002J\u0006\u0010\u0006\u001a\u00020\u0005JA\u0010\u000f\u001a\u0004\u0018\u00018\u0000\"\b\b\u0000\u0010\b*\u00020\u00072\u0006\u0010\n\u001a\u00020\t2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\u000b2\u000e\u0010\u000e\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\rH\u0016¢\u0006\u0004\b\u000f\u0010\u0010J\u000e\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0002J\u0015\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\t0\u0013H\u0000¢\u0006\u0004\b\u0014\u0010\u0015J\u0010\u0010\u0018\u001a\u00020\u00172\u0006\u0010\n\u001a\u00020\tH\u0016J\u0010\u0010\u001a\u001a\u00020\u00192\u0006\u0010\n\u001a\u00020\tH\u0016R\"\u0010\u001d\u001a\u0010\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u001b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001d\u0010\u001eR\u001a\u0010!\u001a\b\u0012\u0004\u0012\u00020 0\u001f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b!\u0010\"R \u0010$\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020#0\u00130\u001f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b$\u0010\"R\u001d\u0010&\u001a\b\u0012\u0004\u0012\u00020%0\u00028\u0006¢\u0006\f\n\u0004\b&\u0010'\u001a\u0004\b(\u0010)R\u0016\u0010*\u001a\u00020 8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b*\u0010+R\u001c\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\t0\u00138\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0016\u0010,¨\u0006/"}, d2 = {"Lcom/amazon/music/featuregating/configuration/AppConfiguration;", "Lcom/amazon/music/featuregating/configuration/Configuration;", "", "Lcom/amazon/music/featuregating/configuration/dataclasses/CustomRegions;", "mergedCustomRegions", "", "reload", "", "T", "", "key", "Lkotlin/reflect/KClass;", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "Lkotlinx/serialization/KSerializer;", "serializer", "value", "(Ljava/lang/String;Lkotlin/reflect/KClass;Lkotlinx/serialization/KSerializer;)Ljava/lang/Object;", "Lcom/amazon/music/featuregating/configuration/dataclasses/Marketplace;", "getMarketplaces", "", "customerRegions$MPFeatureGating_release", "()Ljava/util/Set;", "customerRegions", "", "getBooleanForKey", "", "getIntForKey", "Lkotlin/Function1;", "Lcom/amazon/music/featuregating/configuration/NotificationType;", "notificationCenter", "Lkotlin/jvm/functions/Function1;", "Lkotlin/Function0;", "Lcom/amazon/music/featuregating/configuration/TerritoryInfo;", "territoryProvider", "Lkotlin/jvm/functions/Function0;", "Lcom/amazon/music/featuregating/configuration/customsubscriptiontypes/MusicSubscriptionType;", "subscriptionsProvider", "Lcom/amazon/music/featuregating/configuration/ConfigurationSource;", "sources", "Ljava/util/List;", "getSources", "()Ljava/util/List;", "territory", "Lcom/amazon/music/featuregating/configuration/TerritoryInfo;", "Ljava/util/Set;", "<init>", "(Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Ljava/util/List;)V", "MPFeatureGating_release"}, k = 1, mv = {1, 7, 1})
public final class AppConfiguration implements Configuration {
    private Set<String> customerRegions;
    private final Function1<NotificationType, Unit> notificationCenter;
    private final List<ConfigurationSource> sources;
    private final Function0<Set<MusicSubscriptionType>> subscriptionsProvider;
    private TerritoryInfo territory;
    private final Function0<TerritoryInfo> territoryProvider;

    /* JADX WARN: Multi-variable type inference failed */
    public AppConfiguration(Function1<? super NotificationType, Unit> function1, Function0<TerritoryInfo> territoryProvider, Function0<? extends Set<? extends MusicSubscriptionType>> subscriptionsProvider, List<? extends ConfigurationSource> sources) {
        Intrinsics.checkNotNullParameter(territoryProvider, "territoryProvider");
        Intrinsics.checkNotNullParameter(subscriptionsProvider, "subscriptionsProvider");
        Intrinsics.checkNotNullParameter(sources, "sources");
        this.notificationCenter = function1;
        this.territoryProvider = territoryProvider;
        this.subscriptionsProvider = subscriptionsProvider;
        this.sources = sources;
        this.territory = territoryProvider.invoke();
        this.customerRegions = customerRegions$MPFeatureGating_release();
    }

    @Override // com.amazon.music.featuregating.configuration.Configuration
    public Boolean bool(String str) {
        return Configuration.DefaultImpls.bool(this, str);
    }

    @Override // com.amazon.music.featuregating.configuration.Configuration
    /* JADX INFO: renamed from: int, reason: not valid java name */
    public int mo2012int(String str, int i) {
        return Configuration.DefaultImpls.m2014int(this, str, i);
    }

    @Override // com.amazon.music.featuregating.configuration.Configuration
    /* JADX INFO: renamed from: int, reason: not valid java name */
    public Integer mo2013int(String str) {
        return Configuration.DefaultImpls.m2015int(this, str);
    }

    @Override // com.amazon.music.featuregating.configuration.Configuration
    public String string(String str) {
        return Configuration.DefaultImpls.string(this, str);
    }

    @Override // com.amazon.music.featuregating.configuration.Configuration
    public String string(String str, String str2) {
        return Configuration.DefaultImpls.string(this, str, str2);
    }

    @Override // com.amazon.music.featuregating.configuration.Configuration
    public <T> T value(String str, KClass<T> kClass, T t) {
        return (T) Configuration.DefaultImpls.value(this, str, kClass, t);
    }

    public final void reload() {
        this.territory = this.territoryProvider.invoke();
        Iterator<T> it = this.sources.iterator();
        while (it.hasNext()) {
            ((ConfigurationSource) it.next()).reload();
        }
        this.customerRegions = customerRegions$MPFeatureGating_release();
        Function1<NotificationType, Unit> function1 = this.notificationCenter;
        if (function1 != null) {
            function1.invoke(NotificationType.CONFIG_CHANGED);
        }
    }

    @Override // com.amazon.music.featuregating.configuration.Configuration
    public <T> T value(String key, KClass<T> type, KSerializer<T> serializer) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(type, "type");
        Iterator<ConfigurationSource> it = this.sources.iterator();
        while (it.hasNext()) {
            ConfigurationItem<T> configurationItemSync = it.next().getConfigurationItemSync(key, type, serializer);
            if (configurationItemSync != null) {
                Set mutableSet = CollectionsKt___CollectionsKt.toMutableSet(this.subscriptionsProvider.invoke());
                if (mutableSet.size() > 2 || (mutableSet.size() == 2 && !mutableSet.contains(MusicSubscriptionType.MUSIC_HOME))) {
                    mutableSet.remove(MusicSubscriptionType.FREE);
                }
                if (mutableSet.contains(MusicSubscriptionType.MUSIC_UNLIMITED)) {
                    mutableSet.remove(MusicSubscriptionType.SONIC_RUSH);
                }
                for (String str : this.customerRegions) {
                    for (ConfigurationValue<T> configurationValue : configurationItemSync.getValues()) {
                        List<String> regions = configurationValue.getRegions();
                        if (regions == null) {
                            regions = CollectionsKt__CollectionsJVMKt.listOf("ALL");
                        }
                        if (regions.contains(str)) {
                            List<MusicSubscriptionType> subscriptions = configurationValue.getSubscriptions();
                            if (subscriptions == null || subscriptions.isEmpty()) {
                                return configurationValue.getValue();
                            }
                            if (!CollectionsKt___CollectionsKt.intersect(mutableSet, CollectionsKt___CollectionsKt.toSet(subscriptions)).isEmpty()) {
                                return configurationValue.getValue();
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    public final List<Marketplace> getMarketplaces() {
        List<Marketplace> marketplaces;
        Iterator<ConfigurationSource> it = this.sources.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            MetaConfiguration metaConfiguration = it.next().getMetaConfiguration();
            marketplaces = metaConfiguration != null ? metaConfiguration.getMarketplaces() : null;
            if (marketplaces != null && !marketplaces.isEmpty()) {
                break;
            }
        }
        return marketplaces;
    }

    public final Set<String> customerRegions$MPFeatureGating_release() {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        List<Marketplace> marketplaces = getMarketplaces();
        linkedHashSet.add(this.territory.getMusicTerritory());
        List<CustomRegions> listMergedCustomRegions = mergedCustomRegions();
        for (CustomRegions customRegions : listMergedCustomRegions) {
            if (customRegions.getRegions().contains(this.territory.getMusicTerritory())) {
                linkedHashSet.add(customRegions.getValue());
            }
        }
        String marketplaceName = marketplaces != null ? MarketplaceKt.getMarketplaceName(marketplaces, this.territory.getMarketplaceId()) : null;
        if (marketplaceName != null) {
            linkedHashSet.add(marketplaceName);
            for (CustomRegions customRegions2 : listMergedCustomRegions) {
                if (customRegions2.getRegions().contains(marketplaceName)) {
                    linkedHashSet.add(customRegions2.getValue());
                }
            }
        }
        linkedHashSet.add("ALL");
        return linkedHashSet;
    }

    private final List<CustomRegions> mergedCustomRegions() {
        List<CustomRegions> listEmptyList = CollectionsKt__CollectionsKt.emptyList();
        Iterator it = CollectionsKt___CollectionsKt.reversed(this.sources).iterator();
        while (it.hasNext()) {
            MetaConfiguration metaConfiguration = ((ConfigurationSource) it.next()).getMetaConfiguration();
            if (metaConfiguration != null) {
                listEmptyList = AppConfigurationKt.merged(listEmptyList, metaConfiguration.getCustomRegions());
            }
        }
        return listEmptyList;
    }

    @Override // com.amazon.music.platform.providers.ConfigurationProvider
    public boolean getBooleanForKey(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return ((Boolean) value(key, (KClass<Boolean>) Reflection.getOrCreateKotlinClass(Boolean.TYPE), Boolean.FALSE)).booleanValue();
    }

    @Override // com.amazon.music.platform.providers.ConfigurationProvider
    public int getIntForKey(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return ((Number) value(key, (KClass<int>) Reflection.getOrCreateKotlinClass(Integer.TYPE), 0)).intValue();
    }
}
