package com.amazon.music.find.featuregating;

import com.amazon.music.featuregating.configuration.Configuration;
import com.amazon.music.find.model.SearchSuggestionFieldType;
import com.amazon.music.find.model.configuration.InstantSearchSuggestionConfig;
import com.amazon.music.find.model.configuration.InstantSearchSuggestionMode;
import com.amazon.music.platform.AtomicMap;
import com.amazon.music.platform.find.FeatureGateKeys;
import com.amazon.music.platform.find.common.EntityType;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.locator.ProvidersKt;
import com.amazon.music.platform.providers.ConfigurationProvider;
import com.amazon.music.platform.providers.FeatureGateProvider;
import com.amazon.music.platform.providers.InterfaceProvider;
import com.amazon.music.platform.providers.WeblabProvider;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.serialization.json.JsonArray;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonElementKt;

/* JADX INFO: compiled from: FindFeatureConfigurationProviderDefault.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B#\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\b\u0010\u000f\u001a\u00020\u0010H\u0016J\u000f\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016¢\u0006\u0002\u0010\u0013R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0014"}, d2 = {"Lcom/amazon/music/find/featuregating/FindFeatureConfigurationProviderDefault;", "Lcom/amazon/music/find/featuregating/FindFeatureConfigurationProvider;", "configurationProvider", "Lcom/amazon/music/platform/providers/ConfigurationProvider;", "weblabProvider", "Lcom/amazon/music/platform/providers/WeblabProvider;", "featureGateProvider", "Lcom/amazon/music/platform/providers/FeatureGateProvider;", "(Lcom/amazon/music/platform/providers/ConfigurationProvider;Lcom/amazon/music/platform/providers/WeblabProvider;Lcom/amazon/music/platform/providers/FeatureGateProvider;)V", "getConfigurationProvider", "()Lcom/amazon/music/platform/providers/ConfigurationProvider;", "getFeatureGateProvider", "()Lcom/amazon/music/platform/providers/FeatureGateProvider;", "getWeblabProvider", "()Lcom/amazon/music/platform/providers/WeblabProvider;", "buildInstantSearchConfig", "Lcom/amazon/music/find/model/configuration/InstantSearchSuggestionConfig;", "getSearchHistoryTTLDays", "", "()Ljava/lang/Integer;", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class FindFeatureConfigurationProviderDefault implements FindFeatureConfigurationProvider {
    private final ConfigurationProvider configurationProvider;
    private final FeatureGateProvider featureGateProvider;
    private final WeblabProvider weblabProvider;

    public FindFeatureConfigurationProviderDefault(ConfigurationProvider configurationProvider, WeblabProvider weblabProvider, FeatureGateProvider featureGateProvider) {
        this.configurationProvider = configurationProvider;
        this.weblabProvider = weblabProvider;
        this.featureGateProvider = featureGateProvider;
    }

    public final ConfigurationProvider getConfigurationProvider() {
        return this.configurationProvider;
    }

    public final WeblabProvider getWeblabProvider() {
        return this.weblabProvider;
    }

    public final FeatureGateProvider getFeatureGateProvider() {
        return this.featureGateProvider;
    }

    @Override // com.amazon.music.find.featuregating.FindFeatureConfigurationProvider
    public InstantSearchSuggestionConfig buildInstantSearchConfig() {
        String id;
        Set set;
        Set<EntityType> supported_local_entity_types;
        InstantSearchSuggestionMode instantSearchSuggestionMode = InstantSearchSuggestionMode.ENTITY_FIRST;
        AtomicMap<String, InterfaceProvider> providerInstances = Providers.INSTANCE.getProviderInstances();
        String originalClassName = ProvidersKt.getOriginalClassName(Reflection.getOrCreateKotlinClass(ConfigurationProvider.class));
        Intrinsics.checkNotNull(originalClassName);
        InterfaceProvider interfaceProvider = providerInstances.get(originalClassName);
        if (!(interfaceProvider instanceof ConfigurationProvider)) {
            interfaceProvider = null;
        }
        ConfigurationProvider configurationProvider = (ConfigurationProvider) interfaceProvider;
        Configuration configuration = configurationProvider instanceof Configuration ? (Configuration) configurationProvider : null;
        if (FindFeatureGating.INSTANT_SEARCH_API_ENTITIES_WITH_OVERFLOW.isEnabled()) {
            if (FindFeatureGating.INSTANT_SEARCH_API_ENTITIES_SR_FT.isEnabled()) {
                id = FeatureGateKeys.INSTANT_SEARCH_API_LEVEL_ENTITIES_SR_FT.getId();
            } else {
                id = FeatureGateKeys.INSTANT_SEARCH_API_LEVEL_ENTITIES.getId();
            }
        } else {
            id = FeatureGateKeys.INSTANT_SEARCH_API_LEVEL.getId();
        }
        JsonArray jsonArray = configuration == null ? null : (JsonArray) Configuration.DefaultImpls.value$default(configuration, id, Reflection.getOrCreateKotlinClass(JsonArray.class), null, 4, null);
        if (jsonArray == null) {
            set = null;
        } else {
            ArrayList arrayList = new ArrayList();
            Iterator<JsonElement> it = jsonArray.iterator();
            while (it.hasNext()) {
                String content = JsonElementKt.getJsonPrimitive(it.next()).getContent();
                SearchSuggestionFieldType searchSuggestionFieldType = SearchSuggestionFieldType.TEXT;
                if (!Intrinsics.areEqual(content, searchSuggestionFieldType.toString())) {
                    searchSuggestionFieldType = SearchSuggestionFieldType.TRACK;
                    if (!Intrinsics.areEqual(content, searchSuggestionFieldType.toString())) {
                        searchSuggestionFieldType = SearchSuggestionFieldType.ARTIST;
                        if (!Intrinsics.areEqual(content, searchSuggestionFieldType.toString())) {
                            searchSuggestionFieldType = SearchSuggestionFieldType.PODCAST_SHOW;
                            if (!Intrinsics.areEqual(content, searchSuggestionFieldType.toString())) {
                                searchSuggestionFieldType = null;
                            }
                        }
                    }
                }
                if (searchSuggestionFieldType != null) {
                    arrayList.add(searchSuggestionFieldType);
                }
            }
            set = CollectionsKt___CollectionsKt.toSet(arrayList);
        }
        if (FindFeatureGating.HIDE_TRACKS_IN_LOCAL_SUGGESTIONS_AND_HISTORY.isEnabled()) {
            supported_local_entity_types = InstantSearchSuggestionConfig.INSTANCE.getSUPPORTED_LOCAL_ENTITY_TYPES_MINUS_TRACKS();
        } else {
            supported_local_entity_types = InstantSearchSuggestionConfig.INSTANCE.getSUPPORTED_LOCAL_ENTITY_TYPES();
        }
        Set<EntityType> set2 = supported_local_entity_types;
        ConfigurationProvider configurationProvider2 = this.configurationProvider;
        InstantSearchSuggestionConfig instantSearchSuggestionConfig = configurationProvider2 != null ? new InstantSearchSuggestionConfig(instantSearchSuggestionMode, configurationProvider2.getIntForKey(FeatureGateKeys.INSTANT_SEARCH_MAX_SUGGESTION_RESULTS.getId()), configurationProvider2.getIntForKey(FeatureGateKeys.INSTANT_SEARCH_MIN_ENTITY_QUERY_LENGTH.getId()), configurationProvider2.getIntForKey(FeatureGateKeys.INSTANT_SEARCH_ENTITY_QUERY_SIZE.getId()), configurationProvider2.getIntForKey(FeatureGateKeys.INSTANT_SEARCH_ENTITY_RESULT_COUNT_BASIS.getId()), 0, 0, configurationProvider2.getIntForKey(FeatureGateKeys.INSTANT_SEARCH_RECENT_ENTITY_DATE_RANGE.getId()), set2, set, 96, null) : null;
        return instantSearchSuggestionConfig == null ? new InstantSearchSuggestionConfig(instantSearchSuggestionMode, 0, 0, 0, 0, 0, 0, 0L, set2, null, 766, null) : instantSearchSuggestionConfig;
    }

    @Override // com.amazon.music.find.featuregating.FindFeatureConfigurationProvider
    public Integer getSearchHistoryTTLDays() {
        ConfigurationProvider configurationProvider = this.configurationProvider;
        if (configurationProvider == null) {
            return null;
        }
        return Integer.valueOf(configurationProvider.getIntForKey(FeatureGateKeys.SEARCH_HISTORY_TTL_DAYS.getId()));
    }
}
