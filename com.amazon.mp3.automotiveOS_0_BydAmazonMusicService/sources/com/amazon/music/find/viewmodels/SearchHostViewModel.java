package com.amazon.music.find.viewmodels;

import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import com.amazon.music.find.R;
import com.amazon.music.find.metrics.SearchMetricsService;
import com.amazon.music.find.metrics.SearchMetricsServiceDefault;
import com.amazon.music.find.model.configuration.SearchSuggestionConfiguration;
import com.amazon.music.find.model.event.SearchViewQueryTextEvent;
import com.amazon.music.find.model.filter.FilterElement;
import com.amazon.music.find.model.filter.FilterMetadata;
import com.amazon.music.find.model.filter.SearchFilterSelections;
import com.amazon.music.find.model.search.ContentOwnershipItem;
import com.amazon.music.find.model.search.SearchResponse;
import com.amazon.music.find.providers.SearchFeaturesProvider;
import com.amazon.music.find.service.ExecutionContext;
import com.amazon.music.find.service.ExecutionContextDefault;
import com.amazon.music.find.service.UIConfigurationService;
import com.amazon.music.find.skyfire.Constants;
import com.amazon.music.find.utils.ContentUtils;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.platform.find.common.EntityType;
import com.amazon.music.search.SpellCorrections;
import com.amazon.music.skyfire.ui.fragment.TemplateFragment;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.models.FilterItemModel;
import com.google.android.gms.actions.SearchIntents;
import com.google.android.gms.common.internal.ImagesContract;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.subjects.PublishSubject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt__StringsKt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: SearchHostViewModel.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000ª\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\u0018\u0000 |2\u00020\u0001:\u0003{|}B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0006\u0010*\u001a\u00020\u001fJ\u0010\u0010+\u001a\u00020\u00122\u0006\u0010,\u001a\u00020\nH\u0002J\u000e\u0010-\u001a\u00020\u00122\u0006\u0010.\u001a\u00020\u001fJ\u0006\u0010/\u001a\u00020\u0012J\u0006\u00100\u001a\u000201J\u0006\u00102\u001a\u00020\u0012J\u001a\u00103\u001a\u00020\u00122\b\u00104\u001a\u0004\u0018\u00010\u001c2\b\u00105\u001a\u0004\u0018\u00010\u001cJB\u00106\u001a\u00020\u00122\f\u00107\u001a\b\u0012\u0004\u0012\u000209082\f\u0010:\u001a\b\u0012\u0004\u0012\u00020\u001c082\u001c\u0010;\u001a\u0018\u0012\n\u0012\b\u0012\u0004\u0012\u00020=08\u0012\u0004\u0012\u00020\u00120<j\u0002`>H\u0016J\u0010\u0010?\u001a\u00020\u00122\u0006\u0010@\u001a\u00020AH\u0002JP\u0010B\u001a\u00020\u00122\u0006\u0010@\u001a\u00020A2\u0006\u00104\u001a\u00020\u001c2\b\u0010C\u001a\u0004\u0018\u00010D2\u0006\u0010E\u001a\u0002012\u0006\u0010F\u001a\u00020\u001f2\u0006\u0010G\u001a\u00020\u001f2\b\b\u0002\u0010H\u001a\u00020\u001f2\n\b\u0002\u0010I\u001a\u0004\u0018\u00010\u001cH\u0002J\u0018\u0010J\u001a\u00020\u00122\u0006\u0010@\u001a\u00020A2\u0006\u00104\u001a\u00020\u001cH\u0002J\b\u0010\u000e\u001a\u0004\u0018\u00010\rJ\b\u0010K\u001a\u00020\rH\u0002J\u0010\u0010L\u001a\u00020\r2\u0006\u0010F\u001a\u00020\u001fH\u0002J\b\u0010M\u001a\u00020\rH\u0002J\b\u0010N\u001a\u00020\rH\u0002J\u0012\u0010O\u001a\u00020\n2\n\b\u0002\u0010P\u001a\u0004\u0018\u00010\nJ\f\u0010Q\u001a\b\u0012\u0004\u0012\u00020R08J\u0010\u0010S\u001a\u0002012\u0006\u0010F\u001a\u00020\u001fH\u0002J\n\u0010T\u001a\u0004\u0018\u00010\u001cH\u0002J\u0006\u0010U\u001a\u00020\u0012J\u0010\u0010V\u001a\u00020\u00122\u0006\u0010@\u001a\u00020AH\u0014J\u0006\u0010W\u001a\u00020\u001fJ\u0006\u0010X\u001a\u00020\u0012J\u0006\u0010Y\u001a\u00020\u001fJ\u0006\u0010F\u001a\u00020\u001fJ\b\u0010Z\u001a\u00020\u0012H\u0016J\b\u0010[\u001a\u00020\u0012H\u0016J%\u0010\\\u001a\u00020\u001f2\f\u0010]\u001a\b\u0012\u0004\u0012\u00020_0^2\b\u0010`\u001a\u0004\u0018\u000109H\u0002¢\u0006\u0002\u0010aJ\u0010\u0010b\u001a\u00020\u00122\b\u0010c\u001a\u0004\u0018\u00010\nJ\u0010\u0010d\u001a\u00020\u00122\b\u0010c\u001a\u0004\u0018\u00010\u001cJ\u0006\u0010e\u001a\u00020\u001fJ\u0006\u0010f\u001a\u00020\u0012J\u0006\u0010g\u001a\u00020\u0012J\u0014\u0010h\u001a\u00020i2\f\u0010j\u001a\b\u0012\u0004\u0012\u00020k0\fJ\u0006\u0010l\u001a\u00020\u0012J\u000e\u0010m\u001a\u00020\u00122\u0006\u0010n\u001a\u00020\u001cJ\u001a\u0010o\u001a\u00020\u00122\b\u0010p\u001a\u0004\u0018\u00010\u001c2\b\u0010q\u001a\u0004\u0018\u00010\u001cJ\u0018\u0010r\u001a\u00020\u00122\u0006\u0010s\u001a\u00020\n2\b\u0010t\u001a\u0004\u0018\u00010\u001cJ\u0010\u0010u\u001a\u00020\u00122\u0006\u0010\u000b\u001a\u00020\rH\u0002J\u000e\u0010v\u001a\u00020\u00122\u0006\u0010w\u001a\u000201J\b\u0010x\u001a\u00020\u0012H\u0002J\u000e\u0010y\u001a\u00020\u00122\u0006\u0010z\u001a\u00020\u001fR\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f8F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u00118F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\r0\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001c0\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001f0\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0013\u0010!\u001a\u0004\u0018\u00010\u001c8F¢\u0006\u0006\u001a\u0004\b\"\u0010#R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010$\u001a\b\u0012\u0004\u0012\u00020\u001c0\f8F¢\u0006\u0006\u001a\u0004\b%\u0010\u000fR\u0013\u0010&\u001a\u0004\u0018\u00010\u001c8F¢\u0006\u0006\u001a\u0004\b'\u0010#R\u0017\u0010(\u001a\b\u0012\u0004\u0012\u00020\u001f0\f8F¢\u0006\u0006\u001a\u0004\b)\u0010\u000fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006~"}, d2 = {"Lcom/amazon/music/find/viewmodels/SearchHostViewModel;", "Lcom/amazon/music/find/viewmodels/BaseHostViewModel;", "searchMetricsService", "Lcom/amazon/music/find/metrics/SearchMetricsService;", "uiConfigurationService", "Lcom/amazon/music/find/service/UIConfigurationService;", "searchFeaturesProvider", "Lcom/amazon/music/find/providers/SearchFeaturesProvider;", "(Lcom/amazon/music/find/metrics/SearchMetricsService;Lcom/amazon/music/find/service/UIConfigurationService;Lcom/amazon/music/find/providers/SearchFeaturesProvider;)V", "DEFAULT_SEARCH_EXPERIENCE_MODE", "Lcom/amazon/music/find/viewmodels/SearchExperienceMode;", "actionBarState", "Lio/reactivex/rxjava3/core/Observable;", "Lcom/amazon/music/find/viewmodels/SearchHostViewModel$ActionBarState;", "getActionBarState", "()Lio/reactivex/rxjava3/core/Observable;", "externalNavigationRequested", "Lio/reactivex/rxjava3/subjects/PublishSubject;", "", "getExternalNavigationRequested", "()Lio/reactivex/rxjava3/subjects/PublishSubject;", "mActionBarState", "mData", "Lcom/amazon/music/find/viewmodels/SearchHostViewModel$SearchHostViewModelData;", "mExperienceMode", "mExternalNavigationRequested", "mInitialExperienceMode", "mPageUri", "", "mSearchQueryTextUpdated", "mSearchViewFocusUpdated", "", "mSearchViewFocused", "pageUri", "getPageUri", "()Ljava/lang/String;", "searchQueryTextUpdated", "getSearchQueryTextUpdated", "searchTerm", "getSearchTerm", "searchViewFocusUpdated", "getSearchViewFocusUpdated", "actionBarStateHasObservers", "changeSearchExperienceMode", "toMode", "changeSearchFocus", "hasFocus", "clearSearchQuery", "cloneFilterSelections", "Lcom/amazon/music/find/model/filter/SearchFilterSelections;", "executeSearchFromQuery", "executeSearchFromTerm", SearchIntents.EXTRA_QUERY, "viewId", "fetchRequestState", "trackIds", "", "", "trackAsins", "callback", "Lkotlin/Function1;", "Lcom/amazon/music/find/model/search/ContentOwnershipItem;", "Lcom/amazon/music/find/model/search/ContentOwnershipCallback;", "fetchSearchHistoryPageData", "executionContext", "Lcom/amazon/music/find/service/ExecutionContext;", "fetchSearchResultsPageData", "spellCorrections", "Lcom/amazon/music/search/SpellCorrections;", "searchFilterSelections", "isOffline", "showOrdinal", "enableSpellCorrections", PageUriUtils.QUERYMETADATA_QUERY_PARAM, "fetchSuggestionPageData", "getActionBarStateForSearchHistory", "getActionBarStateForSearchResults", "getActionBarStateForSearchSuggestion", "getActionBarStateForSeeMore", "getExperienceModeOrDefault", "defaultValue", "getFilterItemModels", "Lcom/amazon/music/views/library/models/FilterItemModel;", "getFilterSelections", "getSeeMoreTitle", "goBack", "handleOperationCompleted", "hasFilterItemModels", "initiateSearch", "isLibrary", "onRefresh", "onResume", "pageShouldBeUpdated", NotificationCompat.CATEGORY_EVENT, "Lcom/amazon/music/find/viewmodels/PageDataShouldBeUpdatedEvent;", "Lcom/amazon/music/find/viewmodels/SearchHostViewData;", "fetchRequestedAt", "(Lcom/amazon/music/find/viewmodels/PageDataShouldBeUpdatedEvent;Ljava/lang/Long;)Z", "setInitialExperienceMode", "value", "setPageUri", "showIvyRefinementPills", "showSearchHistory", "showSuggestions", "subscribeToSearchViewQueryTextChange", "Lio/reactivex/rxjava3/disposables/Disposable;", "observable", "Lcom/amazon/music/find/model/event/SearchViewQueryTextEvent;", "toggleExposedRefinements", "toggleFilterSelection", "filterId", "trackGenericUiClickEvent", ImagesContract.URL, "blockRef", "trackSearchQueryUiClickEvent", "mode", "entityPos", "updateActionBar", "updateFilterSelections", "filterSelections", "updateSearchTerm", "updateSearchViewFocus", "visible", "ActionBarState", "Companion", "SearchHostViewModelData", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SearchHostViewModel extends BaseHostViewModel {
    private static final int RESET_ORDINAL_VALUE = 0;
    private final SearchExperienceMode DEFAULT_SEARCH_EXPERIENCE_MODE;
    private final PublishSubject<ActionBarState> mActionBarState;
    private final SearchHostViewModelData mData;
    private SearchExperienceMode mExperienceMode;
    private final PublishSubject<Unit> mExternalNavigationRequested;
    private SearchExperienceMode mInitialExperienceMode;
    private String mPageUri;
    private final PublishSubject<String> mSearchQueryTextUpdated;
    private final PublishSubject<Boolean> mSearchViewFocusUpdated;
    private boolean mSearchViewFocused;
    private final SearchFeaturesProvider searchFeaturesProvider;
    private final SearchMetricsService searchMetricsService;
    private final UIConfigurationService uiConfigurationService;
    private static final String TAG = "SPL";
    private static final Logger logger = LoggerFactory.getLogger(SearchHostViewModel.class.getName());

    /* JADX INFO: compiled from: SearchHostViewModel.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[SearchExperienceMode.values().length];
            iArr[SearchExperienceMode.SEARCH_HISTORY.ordinal()] = 1;
            iArr[SearchExperienceMode.SEARCH_SUGGESTION.ordinal()] = 2;
            iArr[SearchExperienceMode.SEARCH_RESULT.ordinal()] = 3;
            iArr[SearchExperienceMode.SEE_ALL.ordinal()] = 4;
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[EntityType.values().length];
            iArr2[EntityType.ARTIST.ordinal()] = 1;
            iArr2[EntityType.ALBUM.ordinal()] = 2;
            iArr2[EntityType.TRACK.ordinal()] = 3;
            iArr2[EntityType.PLAYLIST.ordinal()] = 4;
            iArr2[EntityType.STATION.ordinal()] = 5;
            iArr2[EntityType.VIDEO.ordinal()] = 6;
            iArr2[EntityType.VIDEO_PLAYLIST.ordinal()] = 7;
            iArr2[EntityType.PODCAST_SHOW.ordinal()] = 8;
            iArr2[EntityType.PODCAST_EPISODE.ordinal()] = 9;
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    @Override // com.amazon.music.find.viewmodels.BaseViewModel
    public void fetchRequestState(List<Long> trackIds, List<String> trackAsins, Function1<? super List<ContentOwnershipItem>, Unit> callback) {
        Intrinsics.checkNotNullParameter(trackIds, "trackIds");
        Intrinsics.checkNotNullParameter(trackAsins, "trackAsins");
        Intrinsics.checkNotNullParameter(callback, "callback");
    }

    @Override // com.amazon.music.find.viewmodels.BaseViewModelLifecycle
    public void onRefresh() {
    }

    @Override // com.amazon.music.find.viewmodels.BaseViewModelLifecycle
    public void onResume() {
    }

    public SearchHostViewModel(SearchMetricsService searchMetricsService, UIConfigurationService uiConfigurationService, SearchFeaturesProvider searchFeaturesProvider) {
        Intrinsics.checkNotNullParameter(searchMetricsService, "searchMetricsService");
        Intrinsics.checkNotNullParameter(uiConfigurationService, "uiConfigurationService");
        Intrinsics.checkNotNullParameter(searchFeaturesProvider, "searchFeaturesProvider");
        this.searchMetricsService = searchMetricsService;
        this.uiConfigurationService = uiConfigurationService;
        this.searchFeaturesProvider = searchFeaturesProvider;
        this.mData = new SearchHostViewModelData("", null, false, null, null, null, null, null, null, false, new SearchFilterSelections(uiConfigurationService.getFilterMetadata(isOffline()), CollectionsKt__CollectionsKt.mutableListOf(uiConfigurationService.getMyMusicFilterElement())), false, 0, new LinkedHashMap(), null, false, null, 114724, null);
        PublishSubject<String> publishSubjectCreate = PublishSubject.create();
        Intrinsics.checkNotNullExpressionValue(publishSubjectCreate, "create()");
        this.mSearchQueryTextUpdated = publishSubjectCreate;
        PublishSubject<ActionBarState> publishSubjectCreate2 = PublishSubject.create();
        Intrinsics.checkNotNullExpressionValue(publishSubjectCreate2, "create()");
        this.mActionBarState = publishSubjectCreate2;
        PublishSubject<Unit> publishSubjectCreate3 = PublishSubject.create();
        Intrinsics.checkNotNullExpressionValue(publishSubjectCreate3, "create()");
        this.mExternalNavigationRequested = publishSubjectCreate3;
        PublishSubject<Boolean> publishSubjectCreate4 = PublishSubject.create();
        Intrinsics.checkNotNullExpressionValue(publishSubjectCreate4, "create()");
        this.mSearchViewFocusUpdated = publishSubjectCreate4;
        this.DEFAULT_SEARCH_EXPERIENCE_MODE = SearchExperienceMode.SEARCH_HISTORY;
    }

    /* JADX INFO: renamed from: getPageUri, reason: from getter */
    public final String getMPageUri() {
        return this.mPageUri;
    }

    public final String getSearchTerm() {
        return this.mData.getExecutedTerm();
    }

    public final Observable<String> getSearchQueryTextUpdated() {
        return this.mSearchQueryTextUpdated;
    }

    /* JADX INFO: renamed from: getActionBarState, reason: collision with other method in class */
    public final Observable<ActionBarState> m2081getActionBarState() {
        return this.mActionBarState;
    }

    public final PublishSubject<Unit> getExternalNavigationRequested() {
        return this.mExternalNavigationRequested;
    }

    public final Observable<Boolean> getSearchViewFocusUpdated() {
        return this.mSearchViewFocusUpdated;
    }

    public final boolean showIvyRefinementPills() {
        return this.searchFeaturesProvider.getAreIvyRefinementPillsEnabled();
    }

    public final void setPageUri(String value) {
        logger.debug(TAG + "::setPageUri. value=" + ((Object) value));
        this.mPageUri = value;
    }

    public final void setInitialExperienceMode(SearchExperienceMode value) {
        logger.debug(TAG + "::setResumeExperienceMode. value=" + value);
        this.mInitialExperienceMode = value;
    }

    public static /* synthetic */ SearchExperienceMode getExperienceModeOrDefault$default(SearchHostViewModel searchHostViewModel, SearchExperienceMode searchExperienceMode, int i, Object obj) {
        if ((i & 1) != 0) {
            searchExperienceMode = null;
        }
        return searchHostViewModel.getExperienceModeOrDefault(searchExperienceMode);
    }

    public final SearchExperienceMode getExperienceModeOrDefault(SearchExperienceMode defaultValue) {
        SearchExperienceMode searchExperienceMode = this.mExperienceMode;
        return searchExperienceMode == null ? defaultValue == null ? this.DEFAULT_SEARCH_EXPERIENCE_MODE : defaultValue : searchExperienceMode;
    }

    public final boolean isOffline() {
        return this.searchFeaturesProvider.isOffline();
    }

    public final void toggleExposedRefinements() {
        boolean zIsOffline = isOffline();
        this.mData.setShowExposedRefinements(!r1.getShowExposedRefinements());
        updateActionBar(getActionBarStateForSearchResults(zIsOffline));
    }

    public final boolean isLibrary() {
        return this.mData.getFilterSelections().hasMyMusicFilter();
    }

    public final SearchFilterSelections cloneFilterSelections() {
        return this.mData.getFilterSelections().deepCopy();
    }

    public final List<FilterItemModel> getFilterItemModels() {
        boolean zIsOffline = isOffline();
        this.mData.updateFilterSelections(new SearchFilterSelections(this.uiConfigurationService.getFilterMetadata(zIsOffline), this.mData.getFilterSelections().getSelections()), zIsOffline);
        return this.mData.getFilterSelections().getFilterItemModels();
    }

    public final boolean hasFilterItemModels() {
        return !getFilterItemModels().isEmpty();
    }

    public final ActionBarState getActionBarState() {
        SearchExperienceMode searchExperienceMode = this.mExperienceMode;
        int i = searchExperienceMode == null ? -1 : WhenMappings.$EnumSwitchMapping$0[searchExperienceMode.ordinal()];
        if (i == 1) {
            return getActionBarStateForSearchHistory();
        }
        if (i == 2) {
            return getActionBarStateForSearchSuggestion();
        }
        if (i == 3) {
            return getActionBarStateForSearchResults(isOffline());
        }
        if (i != 4) {
            return null;
        }
        return getActionBarStateForSeeMore();
    }

    public final void goBack() {
        SearchExperienceMode searchExperienceMode = this.mExperienceMode;
        logger.debug(TAG + "::goBack. mode: " + searchExperienceMode);
        SearchHostViewModelData.reset$default(this.mData, false, false, false, false, false, false, false, false, true, false, false, true, false, 5887, null);
        this.mExternalNavigationRequested.onNext(Unit.INSTANCE);
        this.searchMetricsService.trackGoBackButtonUiClick(searchExperienceMode, Boolean.valueOf(isLibrary()), null);
    }

    public final void initiateSearch() {
        SearchExperienceMode searchExperienceMode = this.mExperienceMode;
        logger.debug(TAG + "::initiateSearch. mode: " + searchExperienceMode);
    }

    public final void changeSearchFocus(boolean hasFocus) {
        SearchExperienceMode searchExperienceMode = this.mExperienceMode;
        boolean z = this.mSearchViewFocused;
        logger.debug(TAG + "::changeFocus: " + hasFocus + ". mode: " + searchExperienceMode + ", mSearchViewFocused: " + z + ", hasFocus: " + hasFocus);
        this.mSearchViewFocused = hasFocus;
        if (z || !hasFocus) {
            return;
        }
        this.searchMetricsService.trackInitiateSearchUiClick(searchExperienceMode, Boolean.valueOf(isLibrary()));
    }

    public final void clearSearchQuery() {
        ExecutionContextDefault executionContextDefaultBuild = new ExecutionContextDefault.Builder().withSearchOperation(SearchOperation.CLEAR_SEARCH_QUERY).build();
        executionContextDefaultBuild.markOperationStarted();
        SearchExperienceMode searchExperienceMode = this.mExperienceMode;
        boolean zIsOffline = isOffline();
        logger.debug(TAG + "::clearSearchQuery. mode: " + searchExperienceMode);
        this.mSearchViewFocused = true;
        this.mData.updateIsOffline(zIsOffline);
        SearchHostViewModelData.reset$default(this.mData, true, true, false, false, false, true, true, false, true, true, true, true, false, 4252, null);
        if (searchExperienceMode == null || searchExperienceMode != SearchExperienceMode.SEARCH_HISTORY) {
            changeSearchExperienceMode(SearchExperienceMode.SEARCH_HISTORY);
            updateActionBar(getActionBarStateForSearchHistory());
        }
        fetchSearchHistoryPageData(executionContextDefaultBuild);
        this.searchMetricsService.trackClearSearchButtonUiClick(this.mExperienceMode, Boolean.valueOf(isLibrary()));
    }

    public final void showSuggestions() {
        SearchExperienceMode searchExperienceMode = this.mExperienceMode;
        logger.debug(TAG + "::showSuggestions. mode: " + searchExperienceMode);
        ExecutionContextDefault executionContextDefaultBuild = new ExecutionContextDefault.Builder().withSearchOperation(SearchOperation.SHOW_SEARCH_SUGGESTIONS).build();
        executionContextDefaultBuild.markOperationStarted();
        SearchExperienceMode searchExperienceMode2 = SearchExperienceMode.SEARCH_SUGGESTION;
        changeSearchExperienceMode(searchExperienceMode2);
        if (searchExperienceMode == null || (searchExperienceMode != SearchExperienceMode.SEARCH_HISTORY && searchExperienceMode != searchExperienceMode2)) {
            updateActionBar(getActionBarStateForSearchSuggestion());
        }
        updateSearchViewFocus(true);
        fetchSuggestionPageData(executionContextDefaultBuild, this.mData.getQueryString());
    }

    public final void showSearchHistory() {
        ExecutionContextDefault executionContextDefaultBuild = new ExecutionContextDefault.Builder().withSearchOperation(SearchOperation.SHOW_SEARCH_HISTORY).withSearchViewFocus(true).build();
        executionContextDefaultBuild.markOperationStarted();
        SearchExperienceMode searchExperienceMode = this.mExperienceMode;
        Logger logger2 = logger;
        StringBuilder sb = new StringBuilder();
        String str = TAG;
        sb.append(str);
        sb.append("::showSearchHistory. mode: ");
        sb.append(searchExperienceMode);
        logger2.debug(sb.toString());
        changeSearchExperienceMode(SearchExperienceMode.SEARCH_HISTORY);
        logger2.debug(str + "::showSearchHistory. mode: " + searchExperienceMode + ", expMode: " + this.mExperienceMode);
        updateActionBar(getActionBarStateForSearchHistory());
        updateSearchViewFocus(true);
        fetchSearchHistoryPageData(executionContextDefaultBuild);
    }

    public final void executeSearchFromTerm(String query, String viewId) {
        SearchExperienceMode searchExperienceMode = this.mExperienceMode;
        boolean zIsOffline = isOffline();
        logger.debug(TAG + "::executeSearchFromTerm. mode: " + searchExperienceMode);
        if (query == null) {
            return;
        }
        this.mData.updateQueryString(query, zIsOffline);
        SearchHostViewModelData.reset$default(this.mData, false, true, false, false, false, true, true, false, true, true, true, true, false, 4253, null);
        ExecutionContextDefault.Builder builderWithSearchOperation = new ExecutionContextDefault.Builder().withSearchOperation(SearchOperation.EXECUTE_SEARCH_FROM_TERM);
        SearchExperienceMode searchExperienceMode2 = SearchExperienceMode.SEARCH_RESULT;
        ExecutionContextDefault executionContextDefaultBuild = ExecutionContextDefault.Builder.withPageContext$default(builderWithSearchOperation, searchExperienceMode2, Boolean.valueOf(zIsOffline), Boolean.valueOf(isLibrary()), null, 8, null).withUiPageView(true).withPageLatencies().withSearchViewFocus(false).build();
        executionContextDefaultBuild.markOperationStarted();
        updateSearchTerm();
        changeSearchExperienceMode(searchExperienceMode2);
        updateActionBar(getActionBarStateForSearchResults(zIsOffline));
        updateSearchViewFocus(false);
        fetchSearchResultsPageData$default(this, executionContextDefaultBuild, StringsKt__StringsKt.trim((CharSequence) this.mData.getQueryString()).toString(), this.mData.getCatalogSpellCorrections(), getFilterSelections(zIsOffline), zIsOffline, this.mData.getShowOrdinals(), false, null, 192, null);
        SearchMetricsService.DefaultImpls.trackSearchQueryUiClick$default(this.searchMetricsService, searchExperienceMode, Boolean.valueOf(isLibrary()), viewId, null, null, 24, null);
    }

    public final void toggleFilterSelection(String filterId) {
        Intrinsics.checkNotNullParameter(filterId, "filterId");
        if (this.mData.getFilterSelections().containsSelection(filterId)) {
            this.mData.getFilterSelections().removeSelection(filterId);
        } else {
            this.mData.getFilterSelections().addSelection(filterId);
        }
        updateFilterSelections(this.mData.getFilterSelections());
    }

    public final void updateFilterSelections(SearchFilterSelections filterSelections) {
        Intrinsics.checkNotNullParameter(filterSelections, "filterSelections");
        SearchExperienceMode searchExperienceMode = this.mExperienceMode;
        boolean zIsOffline = isOffline();
        logger.debug(TAG + "::updateFilterSelections. mode: " + searchExperienceMode);
        this.mData.updateFilterSelections(filterSelections, zIsOffline);
        SearchHostViewModelData.reset$default(this.mData, false, false, false, true, false, false, true, false, true, true, false, true, false, 5303, null);
        SearchExperienceMode searchExperienceMode2 = SearchExperienceMode.SEARCH_RESULT;
        changeSearchExperienceMode(searchExperienceMode2);
        updateActionBar(getActionBarStateForSearchResults(zIsOffline));
        boolean zHasMyMusicFilter = filterSelections.hasMyMusicFilter();
        ExecutionContextDefault executionContextDefaultBuild = ExecutionContextDefault.Builder.withPageContext$default(new ExecutionContextDefault.Builder().withSearchOperation(SearchOperation.EXECUTE_SEARCH_FROM_APPLY_FILTER), searchExperienceMode2, Boolean.valueOf(zIsOffline), Boolean.valueOf(isLibrary()), null, 8, null).withUiPageView(true).withPageLatencies().withSearchViewFocus(false).build();
        executionContextDefaultBuild.markOperationStarted();
        fetchSearchResultsPageData$default(this, executionContextDefaultBuild, StringsKt__StringsKt.trim((CharSequence) this.mData.getQueryString()).toString(), zHasMyMusicFilter ? this.mData.getLibrarySpellCorrections() : this.mData.getCatalogSpellCorrections(), getFilterSelections(zIsOffline), zIsOffline, this.mData.getShowOrdinals(), this.mData.getEnableSpellCorrections(), null, 128, null);
    }

    public final void executeSearchFromQuery() {
        SearchExperienceMode searchExperienceMode = this.mExperienceMode;
        boolean zIsOffline = isOffline();
        logger.debug(TAG + "::submitQuery. mode: " + searchExperienceMode);
        if (StringsKt__StringsKt.trim((CharSequence) this.mData.getQueryString()).toString().length() > 0) {
            this.mData.updateIsOffline(zIsOffline);
            SearchHostViewModelData.reset$default(this.mData, false, true, false, false, false, true, true, false, true, true, true, true, false, 4253, null);
            ExecutionContextDefault.Builder builderWithSearchOperation = new ExecutionContextDefault.Builder().withSearchOperation(SearchOperation.EXECUTE_SEARCH_FROM_QUERY);
            SearchExperienceMode searchExperienceMode2 = SearchExperienceMode.SEARCH_RESULT;
            ExecutionContextDefault executionContextDefaultBuild = ExecutionContextDefault.Builder.withPageContext$default(builderWithSearchOperation, searchExperienceMode2, Boolean.valueOf(zIsOffline), Boolean.valueOf(isLibrary()), null, 8, null).withUiPageView(true).withPageLatencies().withSearchViewFocus(false).build();
            executionContextDefaultBuild.markOperationStarted();
            changeSearchExperienceMode(searchExperienceMode2);
            updateActionBar(getActionBarStateForSearchResults(zIsOffline));
            updateSearchViewFocus(false);
            fetchSearchResultsPageData$default(this, executionContextDefaultBuild, StringsKt__StringsKt.trim((CharSequence) this.mData.getQueryString()).toString(), this.mData.getCatalogSpellCorrections(), getFilterSelections(zIsOffline), zIsOffline, this.mData.getShowOrdinals(), false, null, 192, null);
            SearchMetricsService.DefaultImpls.trackExecuteSearchUiClick$default(this.searchMetricsService, searchExperienceMode, Boolean.valueOf(isLibrary()), null, 4, null);
        }
    }

    private final void fetchSearchHistoryPageData(ExecutionContext executionContext) {
        setIsFetching(true);
        executionContext.markConversionCompleted();
        updatePageData(executionContext, new SearchHostViewData(ChildFragmentType.Native, null, SearchMetricsServiceDefault.CONTENT_NAME_SEARCH_HISTORY, null, null, null, null, null, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION, null));
        setIsFetching(false);
    }

    private final void fetchSuggestionPageData(ExecutionContext executionContext, String query) {
        final Long isFetching = setIsFetching(true);
        executionContext.markConversionCompleted();
        Bundle bundle = new Bundle();
        bundle.putString(TemplateFragment.ARG_SCREEN_ID, Constants.SEARCH_SUGGESTIONS_SCREEN_ID);
        updatePageData(executionContext, new SearchHostViewData(ChildFragmentType.Skyfire, "/showSearchSuggestionResults", null, MapsKt__MapsJVMKt.mapOf(TuplesKt.to("suggestedKeyword", query)), bundle, null, null, null, 228, null), new Function1<PageDataShouldBeUpdatedEvent<SearchHostViewData>, Boolean>() { // from class: com.amazon.music.find.viewmodels.SearchHostViewModel.fetchSuggestionPageData.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(PageDataShouldBeUpdatedEvent<SearchHostViewData> event) {
                Intrinsics.checkNotNullParameter(event, "event");
                return Boolean.valueOf(SearchHostViewModel.this.pageShouldBeUpdated(event, isFetching));
            }
        });
    }

    static /* synthetic */ void fetchSearchResultsPageData$default(SearchHostViewModel searchHostViewModel, ExecutionContext executionContext, String str, SpellCorrections spellCorrections, SearchFilterSelections searchFilterSelections, boolean z, boolean z2, boolean z3, String str2, int i, Object obj) {
        searchHostViewModel.fetchSearchResultsPageData(executionContext, str, spellCorrections, searchFilterSelections, z, z2, (i & 64) != 0 ? true : z3, (i & 128) != 0 ? null : str2);
    }

    private final void fetchSearchResultsPageData(ExecutionContext executionContext, String query, SpellCorrections spellCorrections, SearchFilterSelections searchFilterSelections, boolean isOffline, boolean showOrdinal, boolean enableSpellCorrections, String queryMetadata) {
        setIsFetching(true);
        this.mData.updateQueryString(query, isOffline);
        if (spellCorrections != null) {
            this.mData.setCatalogSpellCorrections(spellCorrections);
        }
        Bundle bundle = new Bundle();
        bundle.putString(TemplateFragment.ARG_SCREEN_ID, Constants.SEARCH_RESULTS_SCREEN_ID);
        updatePageData(executionContext, new SearchHostViewData(ChildFragmentType.Skyfire, "/showSearchResults", null, MapsKt__MapsJVMKt.mapOf(TuplesKt.to("suggestedKeyword", query)), bundle, null, null, null, 228, null));
        executionContext.markConversionCompleted();
        setIsFetching(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean pageShouldBeUpdated(PageDataShouldBeUpdatedEvent<SearchHostViewData> event, Long fetchRequestedAt) {
        return event.getLastFetchRequestedAt() == null || fetchRequestedAt == null || fetchRequestedAt.longValue() >= event.getLastFetchRequestedAt().longValue();
    }

    @Override // com.amazon.music.find.viewmodels.BaseViewModel
    protected void handleOperationCompleted(ExecutionContext executionContext) {
        Intrinsics.checkNotNullParameter(executionContext, "executionContext");
        logger.debug(TAG + "::handleOperationCompleted. operation: " + executionContext.getSearchOperation());
        executionContext.markOperationCompleted();
        executionContext.complete(this.searchMetricsService);
    }

    public final void trackGenericUiClickEvent(String url, String blockRef) {
        this.searchMetricsService.trackGenericUiClick(url, blockRef);
    }

    public final void trackSearchQueryUiClickEvent(SearchExperienceMode mode, String entityPos) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        SearchMetricsService.DefaultImpls.trackSearchQueryUiClick$default(this.searchMetricsService, mode, Boolean.valueOf(isLibrary()), entityPos, null, null, 24, null);
    }

    private final void changeSearchExperienceMode(SearchExperienceMode toMode) {
        SearchExperienceMode searchExperienceMode = this.mExperienceMode;
        logger.debug(TAG + "::changeSearchExperienceMode. currentMode: " + searchExperienceMode + ", toMode: " + toMode);
        if (searchExperienceMode == null || searchExperienceMode != toMode) {
            this.mExperienceMode = toMode;
        }
    }

    private final void updateActionBar(ActionBarState actionBarState) {
        logger.debug(Intrinsics.stringPlus(TAG, "::updateActionBar"));
        this.mActionBarState.onNext(actionBarState);
    }

    public final void updateSearchViewFocus(boolean visible) {
        logger.debug(TAG + "::focusSearchView. visible=" + visible + ", mSearchViewFocused=" + this.mSearchViewFocused);
        if (this.mSearchViewFocused != visible) {
            this.mSearchViewFocusUpdated.onNext(Boolean.valueOf(visible));
        }
    }

    private final void updateSearchTerm() {
        this.mSearchQueryTextUpdated.onNext(this.mData.getQueryString());
    }

    private final SearchFilterSelections getFilterSelections(boolean isOffline) {
        if (isOffline) {
            FilterMetadata filterMetadata = this.uiConfigurationService.getFilterMetadata(true);
            List<FilterElement> selections = this.mData.getFilterSelections().getSelections();
            ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(selections, 10));
            Iterator<T> it = selections.iterator();
            while (it.hasNext()) {
                arrayList.add((FilterElement) it.next());
            }
            SearchFilterSelections searchFilterSelections = new SearchFilterSelections(filterMetadata, CollectionsKt___CollectionsKt.toMutableList((Collection) arrayList));
            if (searchFilterSelections.hasMyMusicFilter()) {
                return searchFilterSelections;
            }
            searchFilterSelections.addMyMusicFilter();
            return searchFilterSelections;
        }
        return this.mData.getFilterSelections();
    }

    private final ActionBarState getActionBarStateForSearchHistory() {
        return new ActionBarState(false, null, true, true, false, false, false, false, true, true, 2, null);
    }

    private final ActionBarState getActionBarStateForSearchSuggestion() {
        return new ActionBarState(false, null, true, true, false, false, false, false, true, false, 2, null);
    }

    private final ActionBarState getActionBarStateForSearchResults(boolean isOffline) {
        return new ActionBarState(false, null, true, false, false, showIvyRefinementPills() || !isOffline, this.mData.getShowExposedRefinements(), this.mData.getFilterSelections().getHasActiveFilters(), false, false, 2, null);
    }

    private final ActionBarState getActionBarStateForSeeMore() {
        return new ActionBarState(false, getSeeMoreTitle(), true, false, false, false, false, false, false, false);
    }

    private final String getSeeMoreTitle() {
        String executedTerm = this.mData.getExecutedTerm();
        if (executedTerm == null) {
            executedTerm = this.mData.getQueryString();
        }
        EntityType seeMoreEntityType = this.mData.getSeeMoreEntityType();
        switch (seeMoreEntityType == null ? -1 : WhenMappings.$EnumSwitchMapping$1[seeMoreEntityType.ordinal()]) {
            case 1:
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                String str = String.format(this.searchFeaturesProvider.getLocalizedString(R.string.see_more_title_artists), Arrays.copyOf(new Object[]{executedTerm}, 1));
                Intrinsics.checkNotNullExpressionValue(str, "format(format, *args)");
                return str;
            case 2:
                StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
                String str2 = String.format(this.searchFeaturesProvider.getLocalizedString(R.string.see_more_title_albums), Arrays.copyOf(new Object[]{executedTerm}, 1));
                Intrinsics.checkNotNullExpressionValue(str2, "format(format, *args)");
                return str2;
            case 3:
                StringCompanionObject stringCompanionObject3 = StringCompanionObject.INSTANCE;
                String str3 = String.format(this.searchFeaturesProvider.getLocalizedString(R.string.see_more_title_songs), Arrays.copyOf(new Object[]{executedTerm}, 1));
                Intrinsics.checkNotNullExpressionValue(str3, "format(format, *args)");
                return str3;
            case 4:
                StringCompanionObject stringCompanionObject4 = StringCompanionObject.INSTANCE;
                String str4 = String.format(this.searchFeaturesProvider.getLocalizedString(R.string.see_more_title_playlists), Arrays.copyOf(new Object[]{executedTerm}, 1));
                Intrinsics.checkNotNullExpressionValue(str4, "format(format, *args)");
                return str4;
            case 5:
                StringCompanionObject stringCompanionObject5 = StringCompanionObject.INSTANCE;
                String str5 = String.format(this.searchFeaturesProvider.getLocalizedString(R.string.see_more_title_stations), Arrays.copyOf(new Object[]{executedTerm}, 1));
                Intrinsics.checkNotNullExpressionValue(str5, "format(format, *args)");
                return str5;
            case 6:
                StringCompanionObject stringCompanionObject6 = StringCompanionObject.INSTANCE;
                String str6 = String.format(this.searchFeaturesProvider.getLocalizedString(R.string.see_more_title_videos), Arrays.copyOf(new Object[]{executedTerm}, 1));
                Intrinsics.checkNotNullExpressionValue(str6, "format(format, *args)");
                return str6;
            case 7:
                StringCompanionObject stringCompanionObject7 = StringCompanionObject.INSTANCE;
                String str7 = String.format(this.searchFeaturesProvider.getLocalizedString(R.string.see_more_title_video_playlists), Arrays.copyOf(new Object[]{executedTerm}, 1));
                Intrinsics.checkNotNullExpressionValue(str7, "format(format, *args)");
                return str7;
            case 8:
                StringCompanionObject stringCompanionObject8 = StringCompanionObject.INSTANCE;
                String str8 = String.format(this.searchFeaturesProvider.getLocalizedString(R.string.see_more_title_podcasts), Arrays.copyOf(new Object[]{executedTerm}, 1));
                Intrinsics.checkNotNullExpressionValue(str8, "format(format, *args)");
                return str8;
            case 9:
                StringCompanionObject stringCompanionObject9 = StringCompanionObject.INSTANCE;
                String str9 = String.format(this.searchFeaturesProvider.getLocalizedString(R.string.see_more_title_podcast_episodes), Arrays.copyOf(new Object[]{executedTerm}, 1));
                Intrinsics.checkNotNullExpressionValue(str9, "format(format, *args)");
                return str9;
            default:
                StringCompanionObject stringCompanionObject10 = StringCompanionObject.INSTANCE;
                String str10 = String.format(this.searchFeaturesProvider.getLocalizedString(R.string.see_more_title), Arrays.copyOf(new Object[]{executedTerm}, 1));
                Intrinsics.checkNotNullExpressionValue(str10, "format(format, *args)");
                return str10;
        }
    }

    public final Disposable subscribeToSearchViewQueryTextChange(Observable<SearchViewQueryTextEvent> observable) {
        Intrinsics.checkNotNullParameter(observable, "observable");
        SearchSuggestionConfiguration searchSuggestionConfiguration = this.uiConfigurationService.getSearchSuggestionConfiguration();
        final boolean zIsOffline = isOffline();
        Disposable disposableSubscribe = observable.map(new Function() { // from class: com.amazon.music.find.viewmodels.SearchHostViewModel$$ExternalSyntheticLambda0
            @Override // io.reactivex.rxjava3.functions.Function
            public final Object apply(Object obj) {
                return SearchHostViewModel.m2079subscribeToSearchViewQueryTextChange$lambda3((SearchViewQueryTextEvent) obj);
            }
        }).debounce(searchSuggestionConfiguration.getDebounceDurationInMilliseconds(), TimeUnit.MILLISECONDS).distinctUntilChanged().observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.amazon.music.find.viewmodels.SearchHostViewModel$$ExternalSyntheticLambda1
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                SearchHostViewModel.m2080subscribeToSearchViewQueryTextChange$lambda4(this.f$0, zIsOffline, (SearchViewQueryTextEvent) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(disposableSubscribe, "observable\n            .…          }\n            }");
        return disposableSubscribe;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: subscribeToSearchViewQueryTextChange$lambda-3, reason: not valid java name */
    public static final SearchViewQueryTextEvent m2079subscribeToSearchViewQueryTextChange$lambda3(SearchViewQueryTextEvent searchViewQueryTextEvent) {
        return new SearchViewQueryTextEvent(StringsKt__StringsKt.trim((CharSequence) searchViewQueryTextEvent.getQuery()).toString(), searchViewQueryTextEvent.isSubmit());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: subscribeToSearchViewQueryTextChange$lambda-4, reason: not valid java name */
    public static final void m2080subscribeToSearchViewQueryTextChange$lambda4(SearchHostViewModel this$0, boolean z, SearchViewQueryTextEvent searchViewQueryTextEvent) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (searchViewQueryTextEvent.isSubmit()) {
            logger.debug(Intrinsics.stringPlus(TAG, "::subscribeToSearchViewQueryTextChange. Submit query"));
            this$0.mData.updateQueryString(searchViewQueryTextEvent.getQuery(), z);
            this$0.executeSearchFromQuery();
            return;
        }
        if (Intrinsics.areEqual(this$0.mData.getQueryString(), searchViewQueryTextEvent.getQuery())) {
            return;
        }
        this$0.mData.updateQueryString(searchViewQueryTextEvent.getQuery(), z);
        if (this$0.mData.getQueryString().length() == 0) {
            logger.debug(Intrinsics.stringPlus(TAG, "::subscribeToSearchViewQueryTextChange. showSearchHistory. mQueryString is empty"));
            this$0.showSearchHistory();
            return;
        }
        logger.debug(TAG + "::subscribeToSearchViewQueryTextChange. showSearchSuggestions. mQueryString: " + this$0.mData + ".mQueryString");
        this$0.showSuggestions();
    }

    public final boolean actionBarStateHasObservers() {
        return this.mActionBarState.hasObservers();
    }

    /* JADX INFO: compiled from: SearchHostViewModel.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b/\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001Bk\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003\u0012\b\b\u0002\u0010\n\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\f\u001a\u00020\u0003\u0012\b\b\u0002\u0010\r\u001a\u00020\u0003¢\u0006\u0002\u0010\u000eJ\t\u0010'\u001a\u00020\u0003HÆ\u0003J\t\u0010(\u001a\u00020\u0003HÆ\u0003J\u000b\u0010)\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010*\u001a\u00020\u0003HÆ\u0003J\t\u0010+\u001a\u00020\u0003HÆ\u0003J\t\u0010,\u001a\u00020\u0003HÆ\u0003J\t\u0010-\u001a\u00020\u0003HÆ\u0003J\t\u0010.\u001a\u00020\u0003HÆ\u0003J\t\u0010/\u001a\u00020\u0003HÆ\u0003J\t\u00100\u001a\u00020\u0003HÆ\u0003Jo\u00101\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u0003HÆ\u0001J\u0013\u00102\u001a\u00020\u00032\b\u00103\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00104\u001a\u000205HÖ\u0001J\t\u00106\u001a\u00020\u0005HÖ\u0001R\u001a\u0010\u000b\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0010\"\u0004\b\u0018\u0010\u0012R\u001a\u0010\u0007\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0010\"\u0004\b\u001a\u0010\u0012R\u001a\u0010\u0006\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0010\"\u0004\b\u001c\u0010\u0012R\u001a\u0010\b\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0010\"\u0004\b\u001e\u0010\u0012R\u001a\u0010\f\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0010\"\u0004\b \u0010\u0012R\u001a\u0010\n\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u0010\"\u0004\b\"\u0010\u0012R\u001a\u0010\t\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u0010\"\u0004\b$\u0010\u0012R\u001a\u0010\r\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u0010\"\u0004\b&\u0010\u0012¨\u00067"}, d2 = {"Lcom/amazon/music/find/viewmodels/SearchHostViewModel$ActionBarState;", "", "showActionBar", "", "findSearchBarTitle", "", "showBackButton", "showAlexaButton", "showCancelTextView", "showFilterButton", "showExposedRefinements", "activeFilter", "showClearButton", "updateQueryHint", "(ZLjava/lang/String;ZZZZZZZZ)V", "getActiveFilter", "()Z", "setActiveFilter", "(Z)V", "getFindSearchBarTitle", "()Ljava/lang/String;", "setFindSearchBarTitle", "(Ljava/lang/String;)V", "getShowActionBar", "setShowActionBar", "getShowAlexaButton", "setShowAlexaButton", "getShowBackButton", "setShowBackButton", "getShowCancelTextView", "setShowCancelTextView", "getShowClearButton", "setShowClearButton", "getShowExposedRefinements", "setShowExposedRefinements", "getShowFilterButton", "setShowFilterButton", "getUpdateQueryHint", "setUpdateQueryHint", "component1", "component10", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "", "toString", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final /* data */ class ActionBarState {
        private boolean activeFilter;
        private String findSearchBarTitle;
        private boolean showActionBar;
        private boolean showAlexaButton;
        private boolean showBackButton;
        private boolean showCancelTextView;
        private boolean showClearButton;
        private boolean showExposedRefinements;
        private boolean showFilterButton;
        private boolean updateQueryHint;

        public ActionBarState() {
            this(false, null, false, false, false, false, false, false, false, false, 1023, null);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final boolean getShowActionBar() {
            return this.showActionBar;
        }

        /* JADX INFO: renamed from: component10, reason: from getter */
        public final boolean getUpdateQueryHint() {
            return this.updateQueryHint;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getFindSearchBarTitle() {
            return this.findSearchBarTitle;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final boolean getShowBackButton() {
            return this.showBackButton;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final boolean getShowAlexaButton() {
            return this.showAlexaButton;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final boolean getShowCancelTextView() {
            return this.showCancelTextView;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final boolean getShowFilterButton() {
            return this.showFilterButton;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final boolean getShowExposedRefinements() {
            return this.showExposedRefinements;
        }

        /* JADX INFO: renamed from: component8, reason: from getter */
        public final boolean getActiveFilter() {
            return this.activeFilter;
        }

        /* JADX INFO: renamed from: component9, reason: from getter */
        public final boolean getShowClearButton() {
            return this.showClearButton;
        }

        public final ActionBarState copy(boolean showActionBar, String findSearchBarTitle, boolean showBackButton, boolean showAlexaButton, boolean showCancelTextView, boolean showFilterButton, boolean showExposedRefinements, boolean activeFilter, boolean showClearButton, boolean updateQueryHint) {
            return new ActionBarState(showActionBar, findSearchBarTitle, showBackButton, showAlexaButton, showCancelTextView, showFilterButton, showExposedRefinements, activeFilter, showClearButton, updateQueryHint);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ActionBarState)) {
                return false;
            }
            ActionBarState actionBarState = (ActionBarState) other;
            return this.showActionBar == actionBarState.showActionBar && Intrinsics.areEqual(this.findSearchBarTitle, actionBarState.findSearchBarTitle) && this.showBackButton == actionBarState.showBackButton && this.showAlexaButton == actionBarState.showAlexaButton && this.showCancelTextView == actionBarState.showCancelTextView && this.showFilterButton == actionBarState.showFilterButton && this.showExposedRefinements == actionBarState.showExposedRefinements && this.activeFilter == actionBarState.activeFilter && this.showClearButton == actionBarState.showClearButton && this.updateQueryHint == actionBarState.updateQueryHint;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v1, types: [int] */
        /* JADX WARN: Type inference failed for: r0v20 */
        /* JADX WARN: Type inference failed for: r0v21 */
        /* JADX WARN: Type inference failed for: r1v0 */
        /* JADX WARN: Type inference failed for: r1v1, types: [int] */
        /* JADX WARN: Type inference failed for: r1v2 */
        /* JADX WARN: Type inference failed for: r2v10, types: [int] */
        /* JADX WARN: Type inference failed for: r2v12, types: [int] */
        /* JADX WARN: Type inference failed for: r2v14, types: [int] */
        /* JADX WARN: Type inference failed for: r2v16, types: [int] */
        /* JADX WARN: Type inference failed for: r2v18 */
        /* JADX WARN: Type inference failed for: r2v19 */
        /* JADX WARN: Type inference failed for: r2v20 */
        /* JADX WARN: Type inference failed for: r2v21 */
        /* JADX WARN: Type inference failed for: r2v22 */
        /* JADX WARN: Type inference failed for: r2v23 */
        /* JADX WARN: Type inference failed for: r2v24 */
        /* JADX WARN: Type inference failed for: r2v26 */
        /* JADX WARN: Type inference failed for: r2v27 */
        /* JADX WARN: Type inference failed for: r2v28 */
        /* JADX WARN: Type inference failed for: r2v29 */
        /* JADX WARN: Type inference failed for: r2v30 */
        /* JADX WARN: Type inference failed for: r2v31 */
        /* JADX WARN: Type inference failed for: r2v32 */
        /* JADX WARN: Type inference failed for: r2v4, types: [int] */
        /* JADX WARN: Type inference failed for: r2v6, types: [int] */
        /* JADX WARN: Type inference failed for: r2v8, types: [int] */
        public int hashCode() {
            boolean z = this.showActionBar;
            ?? r0 = z;
            if (z) {
                r0 = 1;
            }
            int i = r0 * 31;
            String str = this.findSearchBarTitle;
            int iHashCode = (i + (str == null ? 0 : str.hashCode())) * 31;
            boolean z2 = this.showBackButton;
            ?? r2 = z2;
            if (z2) {
                r2 = 1;
            }
            int i2 = (iHashCode + r2) * 31;
            boolean z3 = this.showAlexaButton;
            ?? r22 = z3;
            if (z3) {
                r22 = 1;
            }
            int i3 = (i2 + r22) * 31;
            boolean z4 = this.showCancelTextView;
            ?? r23 = z4;
            if (z4) {
                r23 = 1;
            }
            int i4 = (i3 + r23) * 31;
            boolean z5 = this.showFilterButton;
            ?? r24 = z5;
            if (z5) {
                r24 = 1;
            }
            int i5 = (i4 + r24) * 31;
            boolean z6 = this.showExposedRefinements;
            ?? r25 = z6;
            if (z6) {
                r25 = 1;
            }
            int i6 = (i5 + r25) * 31;
            boolean z7 = this.activeFilter;
            ?? r26 = z7;
            if (z7) {
                r26 = 1;
            }
            int i7 = (i6 + r26) * 31;
            boolean z8 = this.showClearButton;
            ?? r27 = z8;
            if (z8) {
                r27 = 1;
            }
            int i8 = (i7 + r27) * 31;
            boolean z9 = this.updateQueryHint;
            return i8 + (z9 ? 1 : z9);
        }

        public String toString() {
            return "ActionBarState(showActionBar=" + this.showActionBar + ", findSearchBarTitle=" + ((Object) this.findSearchBarTitle) + ", showBackButton=" + this.showBackButton + ", showAlexaButton=" + this.showAlexaButton + ", showCancelTextView=" + this.showCancelTextView + ", showFilterButton=" + this.showFilterButton + ", showExposedRefinements=" + this.showExposedRefinements + ", activeFilter=" + this.activeFilter + ", showClearButton=" + this.showClearButton + ", updateQueryHint=" + this.updateQueryHint + ')';
        }

        public ActionBarState(boolean z, String str, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8, boolean z9) {
            this.showActionBar = z;
            this.findSearchBarTitle = str;
            this.showBackButton = z2;
            this.showAlexaButton = z3;
            this.showCancelTextView = z4;
            this.showFilterButton = z5;
            this.showExposedRefinements = z6;
            this.activeFilter = z7;
            this.showClearButton = z8;
            this.updateQueryHint = z9;
        }

        public /* synthetic */ ActionBarState(boolean z, String str, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8, boolean z9, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? false : z, (i & 2) != 0 ? null : str, (i & 4) != 0 ? true : z2, (i & 8) != 0 ? true : z3, (i & 16) != 0 ? false : z4, (i & 32) != 0 ? false : z5, (i & 64) != 0 ? false : z6, (i & 128) != 0 ? false : z7, (i & 256) == 0 ? z8 : false, (i & 512) == 0 ? z9 : true);
        }

        public final boolean getShowActionBar() {
            return this.showActionBar;
        }

        public final void setShowActionBar(boolean z) {
            this.showActionBar = z;
        }

        public final String getFindSearchBarTitle() {
            return this.findSearchBarTitle;
        }

        public final void setFindSearchBarTitle(String str) {
            this.findSearchBarTitle = str;
        }

        public final boolean getShowBackButton() {
            return this.showBackButton;
        }

        public final void setShowBackButton(boolean z) {
            this.showBackButton = z;
        }

        public final boolean getShowAlexaButton() {
            return this.showAlexaButton;
        }

        public final void setShowAlexaButton(boolean z) {
            this.showAlexaButton = z;
        }

        public final boolean getShowCancelTextView() {
            return this.showCancelTextView;
        }

        public final void setShowCancelTextView(boolean z) {
            this.showCancelTextView = z;
        }

        public final boolean getShowFilterButton() {
            return this.showFilterButton;
        }

        public final void setShowFilterButton(boolean z) {
            this.showFilterButton = z;
        }

        public final boolean getShowExposedRefinements() {
            return this.showExposedRefinements;
        }

        public final void setShowExposedRefinements(boolean z) {
            this.showExposedRefinements = z;
        }

        public final boolean getActiveFilter() {
            return this.activeFilter;
        }

        public final void setActiveFilter(boolean z) {
            this.activeFilter = z;
        }

        public final boolean getShowClearButton() {
            return this.showClearButton;
        }

        public final void setShowClearButton(boolean z) {
            this.showClearButton = z;
        }

        public final boolean getUpdateQueryHint() {
            return this.updateQueryHint;
        }

        public final void setUpdateQueryHint(boolean z) {
            this.updateQueryHint = z;
        }
    }

    /* JADX INFO: compiled from: SearchHostViewModel.kt */
    @Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\bP\n\u0002\u0010\u0002\n\u0002\b\u001c\b\u0086\b\u0018\u00002\u00020\u0001BÉ\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0006\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0010\u0012\u0014\b\u0002\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00180\u0017\u0012\b\b\u0002\u0010\u0019\u001a\u00020\u001a\u0012\b\b\u0002\u0010\u001b\u001a\u00020\u0006\u0012\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u001dJ\t\u0010T\u001a\u00020\u0003HÆ\u0003J\t\u0010U\u001a\u00020\u0006HÆ\u0003J\t\u0010V\u001a\u00020\u0013HÆ\u0003J\t\u0010W\u001a\u00020\u0006HÆ\u0003J\t\u0010X\u001a\u00020\u0010HÆ\u0003J\u0015\u0010Y\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00180\u0017HÆ\u0003J\t\u0010Z\u001a\u00020\u001aHÆ\u0003J\t\u0010[\u001a\u00020\u0006HÆ\u0003J\u000b\u0010\\\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010]\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010^\u001a\u00020\u0006HÆ\u0003J\u000b\u0010_\u001a\u0004\u0018\u00010\bHÆ\u0003J\u000b\u0010`\u001a\u0004\u0018\u00010\bHÆ\u0003J\u000b\u0010a\u001a\u0004\u0018\u00010\u000bHÆ\u0003J\u000b\u0010b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010c\u001a\u0004\u0018\u00010\u000eHÆ\u0003J\u0010\u0010d\u001a\u0004\u0018\u00010\u0010HÆ\u0003¢\u0006\u0002\u0010JJÔ\u0001\u0010e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00062\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00062\b\b\u0002\u0010\u0015\u001a\u00020\u00102\u0014\b\u0002\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00180\u00172\b\b\u0002\u0010\u0019\u001a\u00020\u001a2\b\b\u0002\u0010\u001b\u001a\u00020\u00062\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010fJ\u0013\u0010g\u001a\u00020\u00062\b\u0010h\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010i\u001a\u00020\u0010HÖ\u0001J\u0088\u0001\u0010j\u001a\u00020k2\b\b\u0002\u0010l\u001a\u00020\u00062\b\b\u0002\u0010m\u001a\u00020\u00062\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\u00062\b\b\u0002\u0010n\u001a\u00020\u00062\b\b\u0002\u0010o\u001a\u00020\u00062\b\b\u0002\u0010p\u001a\u00020\u00062\b\b\u0002\u0010q\u001a\u00020\u00062\b\b\u0002\u0010\u0019\u001a\u00020\u00062\b\b\u0002\u0010\u001b\u001a\u00020\u00062\b\b\u0002\u0010\u001c\u001a\u00020\u00062\b\b\u0002\u0010r\u001a\u00020\u0006J\t\u0010s\u001a\u00020\u0003HÖ\u0001J\u000e\u0010t\u001a\u00020k2\u0006\u0010\u0005\u001a\u00020\u0006J\u0018\u0010u\u001a\u00020k2\b\u0010\u0004\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0011\u001a\u00020\u0006J\u0016\u0010v\u001a\u00020k2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0011\u001a\u00020\u0006J\u000e\u0010w\u001a\u00020k2\u0006\u0010\u0019\u001a\u00020\u001aJ\u000e\u0010x\u001a\u00020k2\u0006\u0010\u0011\u001a\u00020\u0006J\u000e\u0010y\u001a\u00020\u00102\u0006\u0010z\u001a\u00020\u0018J\u0018\u0010{\u001a\u00020k2\u0006\u0010\u0011\u001a\u00020\u00062\b\u0010\t\u001a\u0004\u0018\u00010\bJ\u0010\u0010|\u001a\u00020k2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0003J\u0016\u0010}\u001a\u00020k2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u0006J \u0010~\u001a\u00020k2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bJ\u0019\u0010\u007f\u001a\u00020k2\t\u0010\u0080\u0001\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0011\u001a\u00020\u0006J \u0010\u0081\u0001\u001a\u00020k2\t\u0010\u0082\u0001\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u0006¢\u0006\u0003\u0010\u0083\u0001J\u001a\u0010\u0084\u0001\u001a\u00020k2\t\u0010\u0085\u0001\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0011\u001a\u00020\u0006J\u000f\u0010\u0086\u0001\u001a\u00020k2\u0006\u0010\u001b\u001a\u00020\u0006R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)R\u001a\u0010\u0012\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R\u001a\u0010\u0019\u001a\u00020\u001aX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010/\"\u0004\b0\u00101R\u001a\u0010\u0011\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010#\"\u0004\b2\u0010%R\u001a\u0010\u0015\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b3\u00104\"\u0004\b5\u00106R&\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00180\u0017X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b7\u00108\"\u0004\b9\u0010:R\u001c\u0010\t\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b;\u0010\u001f\"\u0004\b<\u0010!R\u001c\u0010\u001c\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b=\u0010'\"\u0004\b>\u0010)R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b?\u0010'\"\u0004\b@\u0010)R\u001c\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bA\u0010B\"\u0004\bC\u0010DR\u001c\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bE\u0010F\"\u0004\bG\u0010HR\u001e\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0086\u000e¢\u0006\u0010\n\u0002\u0010M\u001a\u0004\bI\u0010J\"\u0004\bK\u0010LR\u001c\u0010\f\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bN\u0010'\"\u0004\bO\u0010)R\u001a\u0010\u0014\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bP\u0010#\"\u0004\bQ\u0010%R\u001a\u0010\u001b\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bR\u0010#\"\u0004\bS\u0010%¨\u0006\u0087\u0001"}, d2 = {"Lcom/amazon/music/find/viewmodels/SearchHostViewModel$SearchHostViewModelData;", "", "queryString", "", "executedTerm", "enableSpellCorrections", "", "catalogSpellCorrections", "Lcom/amazon/music/search/SpellCorrections;", "librarySpellCorrections", "searchResponse", "Lcom/amazon/music/find/model/search/SearchResponse;", "seeMorePageToken", "seeMoreEntityType", "Lcom/amazon/music/platform/find/common/EntityType;", "seeMorePageIndex", "", "isOffline", "filterSelections", "Lcom/amazon/music/find/model/filter/SearchFilterSelections;", "showExposedRefinements", "itemIndex", "itemMap", "", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "interactionType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/InteractionType;", "showOrdinals", PageUriUtils.QUERYMETADATA_QUERY_PARAM, "(Ljava/lang/String;Ljava/lang/String;ZLcom/amazon/music/search/SpellCorrections;Lcom/amazon/music/search/SpellCorrections;Lcom/amazon/music/find/model/search/SearchResponse;Ljava/lang/String;Lcom/amazon/music/platform/find/common/EntityType;Ljava/lang/Integer;ZLcom/amazon/music/find/model/filter/SearchFilterSelections;ZILjava/util/Map;Lcom/amazon/music/metrics/mts/event/types/uiinteraction/InteractionType;ZLjava/lang/String;)V", "getCatalogSpellCorrections", "()Lcom/amazon/music/search/SpellCorrections;", "setCatalogSpellCorrections", "(Lcom/amazon/music/search/SpellCorrections;)V", "getEnableSpellCorrections", "()Z", "setEnableSpellCorrections", "(Z)V", "getExecutedTerm", "()Ljava/lang/String;", "setExecutedTerm", "(Ljava/lang/String;)V", "getFilterSelections", "()Lcom/amazon/music/find/model/filter/SearchFilterSelections;", "setFilterSelections", "(Lcom/amazon/music/find/model/filter/SearchFilterSelections;)V", "getInteractionType", "()Lcom/amazon/music/metrics/mts/event/types/uiinteraction/InteractionType;", "setInteractionType", "(Lcom/amazon/music/metrics/mts/event/types/uiinteraction/InteractionType;)V", "setOffline", "getItemIndex", "()I", "setItemIndex", "(I)V", "getItemMap", "()Ljava/util/Map;", "setItemMap", "(Ljava/util/Map;)V", "getLibrarySpellCorrections", "setLibrarySpellCorrections", "getQueryMetadata", "setQueryMetadata", "getQueryString", "setQueryString", "getSearchResponse", "()Lcom/amazon/music/find/model/search/SearchResponse;", "setSearchResponse", "(Lcom/amazon/music/find/model/search/SearchResponse;)V", "getSeeMoreEntityType", "()Lcom/amazon/music/platform/find/common/EntityType;", "setSeeMoreEntityType", "(Lcom/amazon/music/platform/find/common/EntityType;)V", "getSeeMorePageIndex", "()Ljava/lang/Integer;", "setSeeMorePageIndex", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getSeeMorePageToken", "setSeeMorePageToken", "getShowExposedRefinements", "setShowExposedRefinements", "getShowOrdinals", "setShowOrdinals", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/String;Ljava/lang/String;ZLcom/amazon/music/search/SpellCorrections;Lcom/amazon/music/search/SpellCorrections;Lcom/amazon/music/find/model/search/SearchResponse;Ljava/lang/String;Lcom/amazon/music/platform/find/common/EntityType;Ljava/lang/Integer;ZLcom/amazon/music/find/model/filter/SearchFilterSelections;ZILjava/util/Map;Lcom/amazon/music/metrics/mts/event/types/uiinteraction/InteractionType;ZLjava/lang/String;)Lcom/amazon/music/find/viewmodels/SearchHostViewModel$SearchHostViewModelData;", "equals", "other", "hashCode", "reset", "", ContentUtils.KEY_SEARCH_QUERY, "filterSelection", "allSpellCorrections", PageUriUtils.DO_SEE_MORE_VALUE, "seeMorePage", "ordinals", "all", "toString", "updateEnableSpellCorrections", "updateExecutedTerm", "updateFilterSelections", "updateInteractionType", "updateIsOffline", "updateItemMap", "contentMetadata", "updateLibrarySpellCorrections", "updateQueryMetadata", "updateQueryString", "updateQueryStringAndCatalogSpellCorrection", "updateSeeMoreEntityType", PageUriUtils.ENTITY_TYPE_QUERY_PARAM, "updateSeeMorePageIndex", "pageIndex", "(Ljava/lang/Integer;Z)V", "updateSeeMorePageToken", "pageToken", "updateShowOrdinals", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final /* data */ class SearchHostViewModelData {
        private SpellCorrections catalogSpellCorrections;
        private boolean enableSpellCorrections;
        private String executedTerm;
        private SearchFilterSelections filterSelections;
        private InteractionType interactionType;
        private boolean isOffline;
        private int itemIndex;
        private Map<Integer, ContentMetadata> itemMap;
        private SpellCorrections librarySpellCorrections;
        private String queryMetadata;
        private String queryString;
        private SearchResponse searchResponse;
        private EntityType seeMoreEntityType;
        private Integer seeMorePageIndex;
        private String seeMorePageToken;
        private boolean showExposedRefinements;
        private boolean showOrdinals;

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getQueryString() {
            return this.queryString;
        }

        /* JADX INFO: renamed from: component10, reason: from getter */
        public final boolean getIsOffline() {
            return this.isOffline;
        }

        /* JADX INFO: renamed from: component11, reason: from getter */
        public final SearchFilterSelections getFilterSelections() {
            return this.filterSelections;
        }

        /* JADX INFO: renamed from: component12, reason: from getter */
        public final boolean getShowExposedRefinements() {
            return this.showExposedRefinements;
        }

        /* JADX INFO: renamed from: component13, reason: from getter */
        public final int getItemIndex() {
            return this.itemIndex;
        }

        public final Map<Integer, ContentMetadata> component14() {
            return this.itemMap;
        }

        /* JADX INFO: renamed from: component15, reason: from getter */
        public final InteractionType getInteractionType() {
            return this.interactionType;
        }

        /* JADX INFO: renamed from: component16, reason: from getter */
        public final boolean getShowOrdinals() {
            return this.showOrdinals;
        }

        /* JADX INFO: renamed from: component17, reason: from getter */
        public final String getQueryMetadata() {
            return this.queryMetadata;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getExecutedTerm() {
            return this.executedTerm;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final boolean getEnableSpellCorrections() {
            return this.enableSpellCorrections;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final SpellCorrections getCatalogSpellCorrections() {
            return this.catalogSpellCorrections;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final SpellCorrections getLibrarySpellCorrections() {
            return this.librarySpellCorrections;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final SearchResponse getSearchResponse() {
            return this.searchResponse;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final String getSeeMorePageToken() {
            return this.seeMorePageToken;
        }

        /* JADX INFO: renamed from: component8, reason: from getter */
        public final EntityType getSeeMoreEntityType() {
            return this.seeMoreEntityType;
        }

        /* JADX INFO: renamed from: component9, reason: from getter */
        public final Integer getSeeMorePageIndex() {
            return this.seeMorePageIndex;
        }

        public final SearchHostViewModelData copy(String queryString, String executedTerm, boolean enableSpellCorrections, SpellCorrections catalogSpellCorrections, SpellCorrections librarySpellCorrections, SearchResponse searchResponse, String seeMorePageToken, EntityType seeMoreEntityType, Integer seeMorePageIndex, boolean isOffline, SearchFilterSelections filterSelections, boolean showExposedRefinements, int itemIndex, Map<Integer, ContentMetadata> itemMap, InteractionType interactionType, boolean showOrdinals, String queryMetadata) {
            Intrinsics.checkNotNullParameter(queryString, "queryString");
            Intrinsics.checkNotNullParameter(filterSelections, "filterSelections");
            Intrinsics.checkNotNullParameter(itemMap, "itemMap");
            Intrinsics.checkNotNullParameter(interactionType, "interactionType");
            return new SearchHostViewModelData(queryString, executedTerm, enableSpellCorrections, catalogSpellCorrections, librarySpellCorrections, searchResponse, seeMorePageToken, seeMoreEntityType, seeMorePageIndex, isOffline, filterSelections, showExposedRefinements, itemIndex, itemMap, interactionType, showOrdinals, queryMetadata);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof SearchHostViewModelData)) {
                return false;
            }
            SearchHostViewModelData searchHostViewModelData = (SearchHostViewModelData) other;
            return Intrinsics.areEqual(this.queryString, searchHostViewModelData.queryString) && Intrinsics.areEqual(this.executedTerm, searchHostViewModelData.executedTerm) && this.enableSpellCorrections == searchHostViewModelData.enableSpellCorrections && Intrinsics.areEqual(this.catalogSpellCorrections, searchHostViewModelData.catalogSpellCorrections) && Intrinsics.areEqual(this.librarySpellCorrections, searchHostViewModelData.librarySpellCorrections) && Intrinsics.areEqual(this.searchResponse, searchHostViewModelData.searchResponse) && Intrinsics.areEqual(this.seeMorePageToken, searchHostViewModelData.seeMorePageToken) && this.seeMoreEntityType == searchHostViewModelData.seeMoreEntityType && Intrinsics.areEqual(this.seeMorePageIndex, searchHostViewModelData.seeMorePageIndex) && this.isOffline == searchHostViewModelData.isOffline && Intrinsics.areEqual(this.filterSelections, searchHostViewModelData.filterSelections) && this.showExposedRefinements == searchHostViewModelData.showExposedRefinements && this.itemIndex == searchHostViewModelData.itemIndex && Intrinsics.areEqual(this.itemMap, searchHostViewModelData.itemMap) && this.interactionType == searchHostViewModelData.interactionType && this.showOrdinals == searchHostViewModelData.showOrdinals && Intrinsics.areEqual(this.queryMetadata, searchHostViewModelData.queryMetadata);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r1v24, types: [int] */
        /* JADX WARN: Type inference failed for: r1v28, types: [int] */
        /* JADX WARN: Type inference failed for: r1v37 */
        /* JADX WARN: Type inference failed for: r1v38 */
        /* JADX WARN: Type inference failed for: r1v4, types: [int] */
        /* JADX WARN: Type inference failed for: r1v45 */
        /* JADX WARN: Type inference failed for: r1v47 */
        /* JADX WARN: Type inference failed for: r1v48 */
        /* JADX WARN: Type inference failed for: r1v49 */
        /* JADX WARN: Type inference failed for: r3v0 */
        /* JADX WARN: Type inference failed for: r3v1, types: [int] */
        /* JADX WARN: Type inference failed for: r3v2 */
        public int hashCode() {
            int iHashCode = this.queryString.hashCode() * 31;
            String str = this.executedTerm;
            int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
            boolean z = this.enableSpellCorrections;
            ?? r1 = z;
            if (z) {
                r1 = 1;
            }
            int i = (iHashCode2 + r1) * 31;
            SpellCorrections spellCorrections = this.catalogSpellCorrections;
            int iHashCode3 = (i + (spellCorrections == null ? 0 : spellCorrections.hashCode())) * 31;
            SpellCorrections spellCorrections2 = this.librarySpellCorrections;
            int iHashCode4 = (iHashCode3 + (spellCorrections2 == null ? 0 : spellCorrections2.hashCode())) * 31;
            SearchResponse searchResponse = this.searchResponse;
            int iHashCode5 = (iHashCode4 + (searchResponse == null ? 0 : searchResponse.hashCode())) * 31;
            String str2 = this.seeMorePageToken;
            int iHashCode6 = (iHashCode5 + (str2 == null ? 0 : str2.hashCode())) * 31;
            EntityType entityType = this.seeMoreEntityType;
            int iHashCode7 = (iHashCode6 + (entityType == null ? 0 : entityType.hashCode())) * 31;
            Integer num = this.seeMorePageIndex;
            int iHashCode8 = (iHashCode7 + (num == null ? 0 : num.hashCode())) * 31;
            boolean z2 = this.isOffline;
            ?? r12 = z2;
            if (z2) {
                r12 = 1;
            }
            int iHashCode9 = (((iHashCode8 + r12) * 31) + this.filterSelections.hashCode()) * 31;
            boolean z3 = this.showExposedRefinements;
            ?? r13 = z3;
            if (z3) {
                r13 = 1;
            }
            int iHashCode10 = (((((((iHashCode9 + r13) * 31) + Integer.hashCode(this.itemIndex)) * 31) + this.itemMap.hashCode()) * 31) + this.interactionType.hashCode()) * 31;
            boolean z4 = this.showOrdinals;
            int i2 = (iHashCode10 + (z4 ? 1 : z4)) * 31;
            String str3 = this.queryMetadata;
            return i2 + (str3 != null ? str3.hashCode() : 0);
        }

        public String toString() {
            return "SearchHostViewModelData(queryString=" + this.queryString + ", executedTerm=" + ((Object) this.executedTerm) + ", enableSpellCorrections=" + this.enableSpellCorrections + ", catalogSpellCorrections=" + this.catalogSpellCorrections + ", librarySpellCorrections=" + this.librarySpellCorrections + ", searchResponse=" + this.searchResponse + ", seeMorePageToken=" + ((Object) this.seeMorePageToken) + ", seeMoreEntityType=" + this.seeMoreEntityType + ", seeMorePageIndex=" + this.seeMorePageIndex + ", isOffline=" + this.isOffline + ", filterSelections=" + this.filterSelections + ", showExposedRefinements=" + this.showExposedRefinements + ", itemIndex=" + this.itemIndex + ", itemMap=" + this.itemMap + ", interactionType=" + this.interactionType + ", showOrdinals=" + this.showOrdinals + ", queryMetadata=" + ((Object) this.queryMetadata) + ')';
        }

        public SearchHostViewModelData(String queryString, String str, boolean z, SpellCorrections spellCorrections, SpellCorrections spellCorrections2, SearchResponse searchResponse, String str2, EntityType entityType, Integer num, boolean z2, SearchFilterSelections filterSelections, boolean z3, int i, Map<Integer, ContentMetadata> itemMap, InteractionType interactionType, boolean z4, String str3) {
            Intrinsics.checkNotNullParameter(queryString, "queryString");
            Intrinsics.checkNotNullParameter(filterSelections, "filterSelections");
            Intrinsics.checkNotNullParameter(itemMap, "itemMap");
            Intrinsics.checkNotNullParameter(interactionType, "interactionType");
            this.queryString = queryString;
            this.executedTerm = str;
            this.enableSpellCorrections = z;
            this.catalogSpellCorrections = spellCorrections;
            this.librarySpellCorrections = spellCorrections2;
            this.searchResponse = searchResponse;
            this.seeMorePageToken = str2;
            this.seeMoreEntityType = entityType;
            this.seeMorePageIndex = num;
            this.isOffline = z2;
            this.filterSelections = filterSelections;
            this.showExposedRefinements = z3;
            this.itemIndex = i;
            this.itemMap = itemMap;
            this.interactionType = interactionType;
            this.showOrdinals = z4;
            this.queryMetadata = str3;
        }

        public final String getQueryString() {
            return this.queryString;
        }

        public final void setQueryString(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.queryString = str;
        }

        public final String getExecutedTerm() {
            return this.executedTerm;
        }

        public final void setExecutedTerm(String str) {
            this.executedTerm = str;
        }

        public final boolean getEnableSpellCorrections() {
            return this.enableSpellCorrections;
        }

        public final void setEnableSpellCorrections(boolean z) {
            this.enableSpellCorrections = z;
        }

        public final SpellCorrections getCatalogSpellCorrections() {
            return this.catalogSpellCorrections;
        }

        public final void setCatalogSpellCorrections(SpellCorrections spellCorrections) {
            this.catalogSpellCorrections = spellCorrections;
        }

        public final SpellCorrections getLibrarySpellCorrections() {
            return this.librarySpellCorrections;
        }

        public final void setLibrarySpellCorrections(SpellCorrections spellCorrections) {
            this.librarySpellCorrections = spellCorrections;
        }

        public final SearchResponse getSearchResponse() {
            return this.searchResponse;
        }

        public final void setSearchResponse(SearchResponse searchResponse) {
            this.searchResponse = searchResponse;
        }

        public final String getSeeMorePageToken() {
            return this.seeMorePageToken;
        }

        public final void setSeeMorePageToken(String str) {
            this.seeMorePageToken = str;
        }

        public final EntityType getSeeMoreEntityType() {
            return this.seeMoreEntityType;
        }

        public final void setSeeMoreEntityType(EntityType entityType) {
            this.seeMoreEntityType = entityType;
        }

        public final Integer getSeeMorePageIndex() {
            return this.seeMorePageIndex;
        }

        public final void setSeeMorePageIndex(Integer num) {
            this.seeMorePageIndex = num;
        }

        public final boolean isOffline() {
            return this.isOffline;
        }

        public final void setOffline(boolean z) {
            this.isOffline = z;
        }

        public final SearchFilterSelections getFilterSelections() {
            return this.filterSelections;
        }

        public final void setFilterSelections(SearchFilterSelections searchFilterSelections) {
            Intrinsics.checkNotNullParameter(searchFilterSelections, "<set-?>");
            this.filterSelections = searchFilterSelections;
        }

        public final boolean getShowExposedRefinements() {
            return this.showExposedRefinements;
        }

        public final void setShowExposedRefinements(boolean z) {
            this.showExposedRefinements = z;
        }

        public final int getItemIndex() {
            return this.itemIndex;
        }

        public final void setItemIndex(int i) {
            this.itemIndex = i;
        }

        public /* synthetic */ SearchHostViewModelData(String str, String str2, boolean z, SpellCorrections spellCorrections, SpellCorrections spellCorrections2, SearchResponse searchResponse, String str3, EntityType entityType, Integer num, boolean z2, SearchFilterSelections searchFilterSelections, boolean z3, int i, Map map, InteractionType interactionType, boolean z4, String str4, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this((i2 & 1) != 0 ? "" : str, (i2 & 2) != 0 ? null : str2, (i2 & 4) != 0 ? true : z, (i2 & 8) != 0 ? null : spellCorrections, (i2 & 16) != 0 ? null : spellCorrections2, (i2 & 32) != 0 ? null : searchResponse, (i2 & 64) != 0 ? null : str3, (i2 & 128) != 0 ? null : entityType, (i2 & 256) != 0 ? null : num, (i2 & 512) != 0 ? false : z2, searchFilterSelections, (i2 & 2048) != 0 ? false : z3, (i2 & 4096) != 0 ? 0 : i, (i2 & 8192) != 0 ? new LinkedHashMap() : map, (i2 & 16384) != 0 ? InteractionType.TAP : interactionType, (32768 & i2) != 0 ? false : z4, (i2 & 65536) != 0 ? null : str4);
        }

        public final Map<Integer, ContentMetadata> getItemMap() {
            return this.itemMap;
        }

        public final void setItemMap(Map<Integer, ContentMetadata> map) {
            Intrinsics.checkNotNullParameter(map, "<set-?>");
            this.itemMap = map;
        }

        public final InteractionType getInteractionType() {
            return this.interactionType;
        }

        public final void setInteractionType(InteractionType interactionType) {
            Intrinsics.checkNotNullParameter(interactionType, "<set-?>");
            this.interactionType = interactionType;
        }

        public final boolean getShowOrdinals() {
            return this.showOrdinals;
        }

        public final void setShowOrdinals(boolean z) {
            this.showOrdinals = z;
        }

        public final String getQueryMetadata() {
            return this.queryMetadata;
        }

        public final void setQueryMetadata(String str) {
            this.queryMetadata = str;
        }

        public static /* synthetic */ void reset$default(SearchHostViewModelData searchHostViewModelData, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8, boolean z9, boolean z10, boolean z11, boolean z12, boolean z13, int i, Object obj) {
            searchHostViewModelData.reset((i & 1) != 0 ? false : z, (i & 2) != 0 ? false : z2, (i & 4) != 0 ? false : z3, (i & 8) != 0 ? false : z4, (i & 16) != 0 ? false : z5, (i & 32) != 0 ? false : z6, (i & 64) != 0 ? false : z7, (i & 128) != 0 ? false : z8, (i & 256) != 0 ? false : z9, (i & 512) != 0 ? false : z10, (i & 1024) != 0 ? false : z11, (i & 2048) != 0 ? false : z12, (i & 4096) == 0 ? z13 : false);
        }

        public final void reset(boolean searchQuery, boolean filterSelection, boolean enableSpellCorrections, boolean catalogSpellCorrections, boolean librarySpellCorrections, boolean allSpellCorrections, boolean seeMore, boolean seeMorePage, boolean ordinals, boolean interactionType, boolean showOrdinals, boolean queryMetadata, boolean all) {
            if (all || searchQuery) {
                this.queryString = "";
                this.executedTerm = null;
            }
            if (all || filterSelection) {
                this.filterSelections.reset();
            }
            if (all || enableSpellCorrections || allSpellCorrections) {
                this.enableSpellCorrections = true;
            }
            if (all || catalogSpellCorrections || allSpellCorrections) {
                this.catalogSpellCorrections = null;
            }
            if (all || librarySpellCorrections || allSpellCorrections) {
                this.librarySpellCorrections = null;
            }
            if (all || seeMore) {
                this.seeMoreEntityType = null;
            }
            if (all || seeMore || seeMorePage) {
                this.seeMorePageToken = null;
                this.seeMorePageIndex = null;
            }
            if (all || ordinals || searchQuery) {
                this.itemMap.clear();
                this.itemIndex = 0;
            }
            if (all || interactionType) {
                this.interactionType = InteractionType.TAP;
            }
            if (all || showOrdinals) {
                this.showOrdinals = false;
            }
            if (all || queryMetadata) {
                this.queryMetadata = null;
            }
        }

        public final void updateSeeMoreEntityType(EntityType entityType, boolean isOffline) {
            this.seeMoreEntityType = entityType;
            this.seeMorePageToken = null;
            this.seeMorePageIndex = null;
            this.isOffline = isOffline;
        }

        public final void updateSeeMorePageToken(String pageToken, boolean isOffline) {
            this.seeMorePageToken = pageToken;
            this.isOffline = isOffline;
        }

        public final void updateSeeMorePageIndex(Integer pageIndex, boolean isOffline) {
            this.seeMorePageIndex = pageIndex;
            this.isOffline = isOffline;
        }

        public final void updateIsOffline(boolean isOffline) {
            this.isOffline = isOffline;
        }

        public final void updateQueryString(String queryString, boolean isOffline) {
            Intrinsics.checkNotNullParameter(queryString, "queryString");
            this.queryString = queryString;
            this.isOffline = isOffline;
        }

        public final void updateQueryStringAndCatalogSpellCorrection(String queryString, boolean isOffline, SpellCorrections catalogSpellCorrections) {
            Intrinsics.checkNotNullParameter(queryString, "queryString");
            this.queryString = queryString;
            this.catalogSpellCorrections = catalogSpellCorrections;
            this.isOffline = isOffline;
        }

        public final void updateEnableSpellCorrections(boolean enableSpellCorrections) {
            this.enableSpellCorrections = enableSpellCorrections;
        }

        public final void updateLibrarySpellCorrections(boolean isOffline, SpellCorrections librarySpellCorrections) {
            this.librarySpellCorrections = librarySpellCorrections;
        }

        public final void updateExecutedTerm(String executedTerm, boolean isOffline) {
            this.executedTerm = executedTerm;
            this.isOffline = isOffline;
        }

        public final void updateFilterSelections(SearchFilterSelections filterSelections, boolean isOffline) {
            Intrinsics.checkNotNullParameter(filterSelections, "filterSelections");
            if (isOffline) {
                filterSelections.removeMyMusicFilter();
            }
            this.filterSelections = filterSelections;
            this.isOffline = isOffline;
        }

        public final int updateItemMap(ContentMetadata contentMetadata) {
            Intrinsics.checkNotNullParameter(contentMetadata, "contentMetadata");
            Map<Integer, ContentMetadata> map = this.itemMap;
            int i = this.itemIndex + 1;
            this.itemIndex = i;
            map.put(Integer.valueOf(i), contentMetadata);
            return this.itemIndex;
        }

        public final void updateInteractionType(InteractionType interactionType) {
            Intrinsics.checkNotNullParameter(interactionType, "interactionType");
            this.interactionType = interactionType;
        }

        public final void updateShowOrdinals(boolean showOrdinals) {
            this.showOrdinals = showOrdinals;
        }

        public final void updateQueryMetadata(String queryMetadata) {
            this.queryMetadata = queryMetadata;
        }
    }
}
