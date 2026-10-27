package com.amazon.music.find.service;

import android.content.Context;
import com.amazon.music.find.R;
import com.amazon.music.find.featuregating.FindFeatureConfigurationProvider;
import com.amazon.music.find.featuregating.FindFeatureGating;
import com.amazon.music.find.model.configuration.RecycleViewConfiguration;
import com.amazon.music.find.model.configuration.SearchEntityConfiguration;
import com.amazon.music.find.model.configuration.SearchHistoryConfiguration;
import com.amazon.music.find.model.configuration.SearchQueryConfiguration;
import com.amazon.music.find.model.configuration.SearchSuggestionConfiguration;
import com.amazon.music.find.model.filter.FilterContainer;
import com.amazon.music.find.model.filter.FilterElement;
import com.amazon.music.find.model.filter.FilterMetadata;
import com.amazon.music.find.model.filter.SelectionMode;
import com.amazon.music.find.providers.SearchFeaturesProvider;
import com.amazon.music.platform.find.common.EntityType;
import java.util.ArrayList;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: UIConfigurationServiceDefault.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\b\u0010\u0013\u001a\u00020\u0014H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/amazon/music/find/service/UIConfigurationServiceDefault;", "Lcom/amazon/music/find/service/UIConfigurationService;", "context", "Landroid/content/Context;", "searchFeaturesProvider", "Lcom/amazon/music/find/providers/SearchFeaturesProvider;", "findFeatureConfigProvider", "Lcom/amazon/music/find/featuregating/FindFeatureConfigurationProvider;", "(Landroid/content/Context;Lcom/amazon/music/find/providers/SearchFeaturesProvider;Lcom/amazon/music/find/featuregating/FindFeatureConfigurationProvider;)V", "getFilterMetadata", "Lcom/amazon/music/find/model/filter/FilterMetadata;", "isOffline", "", "getMyMusicFilterElement", "Lcom/amazon/music/find/model/filter/FilterElement;", "getRecycleViewConfiguration", "Lcom/amazon/music/find/model/configuration/RecycleViewConfiguration;", "getSearchHistoryConfiguration", "Lcom/amazon/music/find/model/configuration/SearchHistoryConfiguration;", "getSearchSuggestionConfiguration", "Lcom/amazon/music/find/model/configuration/SearchSuggestionConfiguration;", "Companion", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class UIConfigurationServiceDefault implements UIConfigurationService {
    private static final long debounceDurationInMilliseconds = 200;
    private static final long invalidateRecycleViewDelayedInMilliseconds = 200;
    private static final int maxSearchSuggestionCount = 10;
    private static final int searchHistoryEntityCount = 10;
    private static final int searchHistoryQueryCount = 10;
    private final Context context;
    private final FindFeatureConfigurationProvider findFeatureConfigProvider;
    private final SearchFeaturesProvider searchFeaturesProvider;

    public UIConfigurationServiceDefault(Context context, SearchFeaturesProvider searchFeaturesProvider, FindFeatureConfigurationProvider findFeatureConfigProvider) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(searchFeaturesProvider, "searchFeaturesProvider");
        Intrinsics.checkNotNullParameter(findFeatureConfigProvider, "findFeatureConfigProvider");
        this.context = context;
        this.searchFeaturesProvider = searchFeaturesProvider;
        this.findFeatureConfigProvider = findFeatureConfigProvider;
    }

    @Override // com.amazon.music.find.service.UIConfigurationService
    public SearchHistoryConfiguration getSearchHistoryConfiguration() {
        Set<EntityType> supported_local_entity_types;
        boolean zIsEnabled = FindFeatureGating.HIDE_TRACKS_IN_LOCAL_SUGGESTIONS_AND_HISTORY.isEnabled();
        Integer searchHistoryTTLDays = this.findFeatureConfigProvider.getSearchHistoryTTLDays();
        if (zIsEnabled) {
            supported_local_entity_types = SearchEntityConfiguration.INSTANCE.getSUPPORTED_LOCAL_ENTITY_TYPES_MINUS_TRACKS();
        } else {
            supported_local_entity_types = SearchEntityConfiguration.INSTANCE.getSUPPORTED_LOCAL_ENTITY_TYPES();
        }
        return new SearchHistoryConfiguration(new SearchEntityConfiguration(10, searchHistoryTTLDays, supported_local_entity_types), new SearchQueryConfiguration(10, this.findFeatureConfigProvider.getSearchHistoryTTLDays()), 10);
    }

    @Override // com.amazon.music.find.service.UIConfigurationService
    public SearchSuggestionConfiguration getSearchSuggestionConfiguration() {
        return new SearchSuggestionConfiguration(this.findFeatureConfigProvider.buildInstantSearchConfig(), 10, 200L);
    }

    @Override // com.amazon.music.find.service.UIConfigurationService
    public RecycleViewConfiguration getRecycleViewConfiguration() {
        return new RecycleViewConfiguration(200L);
    }

    @Override // com.amazon.music.find.service.UIConfigurationService
    public FilterElement getMyMusicFilterElement() {
        String string = this.context.getResources().getString(R.string.music_library_filter);
        Intrinsics.checkNotNullExpressionValue(string, "context.resources.getStr…ing.music_library_filter)");
        return new FilterElement(FilterMetadata.MY_MUSIC_FILTER_ID, string, false, 4, null);
    }

    @Override // com.amazon.music.find.service.UIConfigurationService
    public FilterMetadata getFilterMetadata(boolean isOffline) {
        String string;
        ArrayList arrayList = new ArrayList();
        if (!isOffline) {
            if (this.searchFeaturesProvider.getAreQualityFiltersEnabled()) {
                string = this.context.getResources().getString(R.string.library_filters_title);
            } else {
                string = this.context.getResources().getString(R.string.filters);
            }
            String str = string;
            Intrinsics.checkNotNullExpressionValue(str, "if (searchFeaturesProvid…tString(R.string.filters)");
            arrayList.add(new FilterContainer(FilterMetadata.LIBRARY_CONTAINER_ID, str, false, CollectionsKt__CollectionsJVMKt.listOf(getMyMusicFilterElement()), SelectionMode.SELECTION_MODE_SINGLE, 4, null));
        }
        if (this.searchFeaturesProvider.getAreQualityFiltersEnabled()) {
            String string2 = this.context.getResources().getString(R.string.audio_quality_filters_title);
            Intrinsics.checkNotNullExpressionValue(string2, "context.resources.getStr…io_quality_filters_title)");
            String string3 = this.context.getResources().getString(R.string.uhd_filter);
            Intrinsics.checkNotNullExpressionValue(string3, "context.resources.getString(R.string.uhd_filter)");
            String string4 = this.context.getResources().getString(R.string.sony_360_audio_filter);
            Intrinsics.checkNotNullExpressionValue(string4, "context.resources.getStr…ng.sony_360_audio_filter)");
            String string5 = this.context.getResources().getString(R.string.dolby_atmos_filter);
            Intrinsics.checkNotNullExpressionValue(string5, "context.resources.getStr…tring.dolby_atmos_filter)");
            arrayList.add(new FilterContainer(FilterMetadata.QUALITY_CONTAINER_ID, string2, false, CollectionsKt__CollectionsKt.listOf((Object[]) new FilterElement[]{new FilterElement(FilterMetadata.UHD_FILTER_ID, string3, false, 4, null), new FilterElement(FilterMetadata.SONY_360_FILTER_ID, string4, false, 4, null), new FilterElement(FilterMetadata.DOLBY_ATMOS_FILTER_ID, string5, false, 4, null)}), SelectionMode.SELECTION_MODE_MULTIPLE, 4, null));
        }
        return new FilterMetadata(arrayList);
    }
}
