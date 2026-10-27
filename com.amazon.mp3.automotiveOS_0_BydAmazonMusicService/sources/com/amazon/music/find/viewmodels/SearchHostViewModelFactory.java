package com.amazon.music.find.viewmodels;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.amazon.music.find.metrics.SearchMetricsService;
import com.amazon.music.find.providers.SearchFeaturesProvider;
import com.amazon.music.find.service.UIConfigurationService;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: SearchHostViewModelFactory.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ%\u0010\t\u001a\u0002H\n\"\b\b\u0000\u0010\n*\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\n0\rH\u0016¢\u0006\u0002\u0010\u000eR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/amazon/music/find/viewmodels/SearchHostViewModelFactory;", "Landroidx/lifecycle/ViewModelProvider$Factory;", "searchMetricsService", "Lcom/amazon/music/find/metrics/SearchMetricsService;", "searchFeaturesProvider", "Lcom/amazon/music/find/providers/SearchFeaturesProvider;", "uiConfigurationService", "Lcom/amazon/music/find/service/UIConfigurationService;", "(Lcom/amazon/music/find/metrics/SearchMetricsService;Lcom/amazon/music/find/providers/SearchFeaturesProvider;Lcom/amazon/music/find/service/UIConfigurationService;)V", "create", "T", "Landroidx/lifecycle/ViewModel;", "p0", "Ljava/lang/Class;", "(Ljava/lang/Class;)Landroidx/lifecycle/ViewModel;", "Companion", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SearchHostViewModelFactory implements ViewModelProvider.Factory {
    private static final String TAG = "SPL";
    private final SearchFeaturesProvider searchFeaturesProvider;
    private final SearchMetricsService searchMetricsService;
    private final UIConfigurationService uiConfigurationService;
    private static final Logger logger = LoggerFactory.getLogger(SearchHostViewModelFactory.class.getName());

    public SearchHostViewModelFactory(SearchMetricsService searchMetricsService, SearchFeaturesProvider searchFeaturesProvider, UIConfigurationService uiConfigurationService) {
        Intrinsics.checkNotNullParameter(searchMetricsService, "searchMetricsService");
        Intrinsics.checkNotNullParameter(searchFeaturesProvider, "searchFeaturesProvider");
        Intrinsics.checkNotNullParameter(uiConfigurationService, "uiConfigurationService");
        this.searchMetricsService = searchMetricsService;
        this.searchFeaturesProvider = searchFeaturesProvider;
        this.uiConfigurationService = uiConfigurationService;
    }

    @Override // androidx.lifecycle.ViewModelProvider.Factory
    public <T extends ViewModel> T create(Class<T> p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        logger.debug("SPL::create->creating SearchViewModel");
        return new SearchHostViewModel(this.searchMetricsService, this.uiConfigurationService, this.searchFeaturesProvider);
    }
}
