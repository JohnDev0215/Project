package com.amazon.music.find.api;

import com.amazon.music.find.data.database.LibrarySearchDao;
import com.amazon.music.find.model.filter.SearchFilterSelections;
import com.amazon.music.find.model.search.GenericSearchResponse;
import com.amazon.music.find.model.search.SearchFollowSource;
import com.amazon.music.find.model.search.SeeMoreResponse;
import com.amazon.music.find.providers.SearchFeaturesProvider;
import com.amazon.music.find.service.ExecutionContext;
import com.amazon.music.find.service.SearchServiceException;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.platform.find.common.EntityType;
import com.amazon.music.search.SpellCorrections;
import com.google.android.gms.actions.SearchIntents;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: LocalLibrarySearchApi.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u001e2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u001eB\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007JY\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\f2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016¢\u0006\u0002\u0010\u0017Jk\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\f2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016¢\u0006\u0002\u0010\u001dR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lcom/amazon/music/find/api/LocalLibrarySearchApi;", "Lcom/amazon/music/find/api/SearchApi;", "Lcom/amazon/music/find/model/search/GenericSearchResponse;", "librarySearchDao", "Lcom/amazon/music/find/data/database/LibrarySearchDao;", "searchFeaturesProvider", "Lcom/amazon/music/find/providers/SearchFeaturesProvider;", "(Lcom/amazon/music/find/data/database/LibrarySearchDao;Lcom/amazon/music/find/providers/SearchFeaturesProvider;)V", SearchIntents.EXTRA_QUERY, "Lio/reactivex/rxjava3/core/Observable;", "executionContext", "Lcom/amazon/music/find/service/ExecutionContext;", "", "enableSpellCorrections", "", "spellCorrections", "Lcom/amazon/music/search/SpellCorrections;", "isOffline", "searchFilterSelections", "Lcom/amazon/music/find/model/filter/SearchFilterSelections;", PageUriUtils.QUERYMETADATA_QUERY_PARAM, "pageSize", "", "(Lcom/amazon/music/find/service/ExecutionContext;Ljava/lang/String;ZLcom/amazon/music/search/SpellCorrections;ZLcom/amazon/music/find/model/filter/SearchFilterSelections;Ljava/lang/String;Ljava/lang/Integer;)Lio/reactivex/rxjava3/core/Observable;", PageUriUtils.DO_SEE_MORE_VALUE, "Lcom/amazon/music/find/model/search/SeeMoreResponse;", PageUriUtils.ENTITY_TYPE_QUERY_PARAM, "Lcom/amazon/music/platform/find/common/EntityType;", "pageToken", "(Lcom/amazon/music/find/service/ExecutionContext;Ljava/lang/String;ZLcom/amazon/music/search/SpellCorrections;Lcom/amazon/music/platform/find/common/EntityType;Ljava/lang/String;ZLcom/amazon/music/find/model/filter/SearchFilterSelections;Ljava/lang/String;Ljava/lang/Integer;)Lio/reactivex/rxjava3/core/Observable;", "Companion", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class LocalLibrarySearchApi implements SearchApi<GenericSearchResponse> {
    private static final Logger logger = LoggerFactory.getLogger(LocalLibrarySearchApi.class.getName());
    private final LibrarySearchDao librarySearchDao;
    private final SearchFeaturesProvider searchFeaturesProvider;

    public LocalLibrarySearchApi(LibrarySearchDao librarySearchDao, SearchFeaturesProvider searchFeaturesProvider) {
        Intrinsics.checkNotNullParameter(librarySearchDao, "librarySearchDao");
        Intrinsics.checkNotNullParameter(searchFeaturesProvider, "searchFeaturesProvider");
        this.librarySearchDao = librarySearchDao;
        this.searchFeaturesProvider = searchFeaturesProvider;
    }

    @Override // com.amazon.music.find.api.SearchApi
    public Observable<GenericSearchResponse> query(ExecutionContext executionContext, final String query, boolean enableSpellCorrections, final SpellCorrections spellCorrections, final boolean isOffline, final SearchFilterSelections searchFilterSelections, String queryMetadata, final Integer pageSize) {
        Intrinsics.checkNotNullParameter(executionContext, "executionContext");
        Intrinsics.checkNotNullParameter(query, "query");
        Intrinsics.checkNotNullParameter(searchFilterSelections, "searchFilterSelections");
        Observable<GenericSearchResponse> observableCreate = Observable.create(new ObservableOnSubscribe() { // from class: com.amazon.music.find.api.LocalLibrarySearchApi$$ExternalSyntheticLambda1
            @Override // io.reactivex.rxjava3.core.ObservableOnSubscribe
            public final void subscribe(ObservableEmitter observableEmitter) {
                LocalLibrarySearchApi.m2021query$lambda0(this.f$0, query, isOffline, searchFilterSelections, spellCorrections, pageSize, observableEmitter);
            }
        });
        Intrinsics.checkNotNullExpressionValue(observableCreate, "create { subscriber ->\n …)\n            }\n        }");
        return observableCreate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: query$lambda-0, reason: not valid java name */
    public static final void m2021query$lambda0(LocalLibrarySearchApi this$0, String query, boolean z, SearchFilterSelections searchFilterSelections, SpellCorrections spellCorrections, Integer num, ObservableEmitter observableEmitter) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(query, "$query");
        Intrinsics.checkNotNullParameter(searchFilterSelections, "$searchFilterSelections");
        try {
            observableEmitter.onNext(this$0.librarySearchDao.query(query, z, searchFilterSelections, spellCorrections, num, SearchFollowSource.FOLLOWED));
            observableEmitter.onComplete();
        } catch (SearchServiceException e) {
            String strStringPlus = Intrinsics.stringPlus("Something went wrong while getting library response:", e.getMessage());
            logger.warn(strStringPlus);
            observableEmitter.onError(new SearchServiceException(strStringPlus));
        }
    }

    @Override // com.amazon.music.find.api.SearchApi
    public Observable<SeeMoreResponse> seeMore(ExecutionContext executionContext, final String query, boolean enableSpellCorrections, SpellCorrections spellCorrections, final EntityType entityType, String pageToken, final boolean isOffline, final SearchFilterSelections searchFilterSelections, String queryMetadata, final Integer pageSize) {
        Intrinsics.checkNotNullParameter(executionContext, "executionContext");
        Intrinsics.checkNotNullParameter(query, "query");
        Intrinsics.checkNotNullParameter(entityType, "entityType");
        Intrinsics.checkNotNullParameter(searchFilterSelections, "searchFilterSelections");
        Observable<SeeMoreResponse> observableCreate = Observable.create(new ObservableOnSubscribe() { // from class: com.amazon.music.find.api.LocalLibrarySearchApi$$ExternalSyntheticLambda0
            @Override // io.reactivex.rxjava3.core.ObservableOnSubscribe
            public final void subscribe(ObservableEmitter observableEmitter) {
                LocalLibrarySearchApi.m2022seeMore$lambda1(this.f$0, query, isOffline, entityType, searchFilterSelections, pageSize, observableEmitter);
            }
        });
        Intrinsics.checkNotNullExpressionValue(observableCreate, "create { subscriber ->\n …)\n            }\n        }");
        return observableCreate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: seeMore$lambda-1, reason: not valid java name */
    public static final void m2022seeMore$lambda1(LocalLibrarySearchApi this$0, String query, boolean z, EntityType entityType, SearchFilterSelections searchFilterSelections, Integer num, ObservableEmitter observableEmitter) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(query, "$query");
        Intrinsics.checkNotNullParameter(entityType, "$entityType");
        Intrinsics.checkNotNullParameter(searchFilterSelections, "$searchFilterSelections");
        try {
            observableEmitter.onNext(this$0.librarySearchDao.seeMore(query, z, entityType, searchFilterSelections, num, SearchFollowSource.FOLLOWED));
            observableEmitter.onComplete();
        } catch (SearchServiceException e) {
            String strStringPlus = Intrinsics.stringPlus("Something went wrong while getting library seeMore response:", e.getMessage());
            logger.warn(strStringPlus);
            observableEmitter.onError(new SearchServiceException(strStringPlus));
        }
    }
}
