package com.amazon.music.find.api;

import com.amazon.music.find.model.filter.SearchFilterSelections;
import com.amazon.music.find.model.search.GenericSearchResponse;
import com.amazon.music.find.model.search.SeeMoreResponse;
import com.amazon.music.find.service.ExecutionContext;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.platform.find.common.EntityType;
import com.amazon.music.search.SpellCorrections;
import com.google.android.gms.actions.SearchIntents;
import io.reactivex.rxjava3.core.Observable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: EmptyLibrarySearchApi.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003JY\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\b2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016¢\u0006\u0002\u0010\u0013Jk\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\b2\u0006\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\b2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016¢\u0006\u0002\u0010\u0019¨\u0006\u001a"}, d2 = {"Lcom/amazon/music/find/api/EmptyLibrarySearchApi;", "Lcom/amazon/music/find/api/SearchApi;", "Lcom/amazon/music/find/model/search/GenericSearchResponse;", "()V", SearchIntents.EXTRA_QUERY, "Lio/reactivex/rxjava3/core/Observable;", "executionContext", "Lcom/amazon/music/find/service/ExecutionContext;", "", "enableSpellCorrections", "", "spellCorrections", "Lcom/amazon/music/search/SpellCorrections;", "isOffline", "searchFilterSelections", "Lcom/amazon/music/find/model/filter/SearchFilterSelections;", PageUriUtils.QUERYMETADATA_QUERY_PARAM, "pageSize", "", "(Lcom/amazon/music/find/service/ExecutionContext;Ljava/lang/String;ZLcom/amazon/music/search/SpellCorrections;ZLcom/amazon/music/find/model/filter/SearchFilterSelections;Ljava/lang/String;Ljava/lang/Integer;)Lio/reactivex/rxjava3/core/Observable;", PageUriUtils.DO_SEE_MORE_VALUE, "Lcom/amazon/music/find/model/search/SeeMoreResponse;", PageUriUtils.ENTITY_TYPE_QUERY_PARAM, "Lcom/amazon/music/platform/find/common/EntityType;", "pageToken", "(Lcom/amazon/music/find/service/ExecutionContext;Ljava/lang/String;ZLcom/amazon/music/search/SpellCorrections;Lcom/amazon/music/platform/find/common/EntityType;Ljava/lang/String;ZLcom/amazon/music/find/model/filter/SearchFilterSelections;Ljava/lang/String;Ljava/lang/Integer;)Lio/reactivex/rxjava3/core/Observable;", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class EmptyLibrarySearchApi implements SearchApi<GenericSearchResponse> {
    @Override // com.amazon.music.find.api.SearchApi
    public Observable<GenericSearchResponse> query(ExecutionContext executionContext, String query, boolean enableSpellCorrections, SpellCorrections spellCorrections, boolean isOffline, SearchFilterSelections searchFilterSelections, String queryMetadata, Integer pageSize) {
        Intrinsics.checkNotNullParameter(executionContext, "executionContext");
        Intrinsics.checkNotNullParameter(query, "query");
        Intrinsics.checkNotNullParameter(searchFilterSelections, "searchFilterSelections");
        Observable<GenericSearchResponse> observableJust = Observable.just(new GenericSearchResponse());
        Intrinsics.checkNotNullExpressionValue(observableJust, "just(GenericSearchResponse())");
        return observableJust;
    }

    @Override // com.amazon.music.find.api.SearchApi
    public Observable<SeeMoreResponse> seeMore(ExecutionContext executionContext, String query, boolean enableSpellCorrections, SpellCorrections spellCorrections, EntityType entityType, String pageToken, boolean isOffline, SearchFilterSelections searchFilterSelections, String queryMetadata, Integer pageSize) {
        Intrinsics.checkNotNullParameter(executionContext, "executionContext");
        Intrinsics.checkNotNullParameter(query, "query");
        Intrinsics.checkNotNullParameter(entityType, "entityType");
        Intrinsics.checkNotNullParameter(searchFilterSelections, "searchFilterSelections");
        Observable<SeeMoreResponse> observableJust = Observable.just(new SeeMoreResponse(null, null, null, 7, null));
        Intrinsics.checkNotNullExpressionValue(observableJust, "just(SeeMoreResponse())");
        return observableJust;
    }
}
