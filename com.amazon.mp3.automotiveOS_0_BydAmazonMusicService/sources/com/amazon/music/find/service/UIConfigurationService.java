package com.amazon.music.find.service;

import com.amazon.music.find.model.configuration.RecycleViewConfiguration;
import com.amazon.music.find.model.configuration.SearchHistoryConfiguration;
import com.amazon.music.find.model.configuration.SearchSuggestionConfiguration;
import com.amazon.music.find.model.filter.FilterElement;
import com.amazon.music.find.model.filter.FilterMetadata;
import kotlin.Metadata;

/* JADX INFO: compiled from: UIConfigurationService.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0007H&J\b\u0010\b\u001a\u00020\tH&J\b\u0010\n\u001a\u00020\u000bH&J\b\u0010\f\u001a\u00020\rH&¨\u0006\u000e"}, d2 = {"Lcom/amazon/music/find/service/UIConfigurationService;", "", "getFilterMetadata", "Lcom/amazon/music/find/model/filter/FilterMetadata;", "isOffline", "", "getMyMusicFilterElement", "Lcom/amazon/music/find/model/filter/FilterElement;", "getRecycleViewConfiguration", "Lcom/amazon/music/find/model/configuration/RecycleViewConfiguration;", "getSearchHistoryConfiguration", "Lcom/amazon/music/find/model/configuration/SearchHistoryConfiguration;", "getSearchSuggestionConfiguration", "Lcom/amazon/music/find/model/configuration/SearchSuggestionConfiguration;", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface UIConfigurationService {
    FilterMetadata getFilterMetadata(boolean isOffline);

    FilterElement getMyMusicFilterElement();

    RecycleViewConfiguration getRecycleViewConfiguration();

    SearchHistoryConfiguration getSearchHistoryConfiguration();

    SearchSuggestionConfiguration getSearchSuggestionConfiguration();

    /* JADX INFO: compiled from: UIConfigurationService.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public static final class DefaultImpls {
        public static /* synthetic */ FilterMetadata getFilterMetadata$default(UIConfigurationService uIConfigurationService, boolean z, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getFilterMetadata");
            }
            if ((i & 1) != 0) {
                z = false;
            }
            return uIConfigurationService.getFilterMetadata(z);
        }
    }
}
