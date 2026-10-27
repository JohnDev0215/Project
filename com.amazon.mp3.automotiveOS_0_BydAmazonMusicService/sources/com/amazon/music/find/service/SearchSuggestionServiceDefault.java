package com.amazon.music.find.service;

import android.database.sqlite.SQLiteException;
import androidx.annotation.VisibleForTesting;
import com.amazon.music.find.api.searchsuggestion.SearchSuggestionApi;
import com.amazon.music.find.api.searchsuggestion.SearchSuggestionRequest;
import com.amazon.music.find.api.searchsuggestion.SearchSuggestionResponse;
import com.amazon.music.find.data.database.SearchEntityDao;
import com.amazon.music.find.data.database.entities.SearchItem;
import com.amazon.music.find.model.ElementOrigin;
import com.amazon.music.find.model.SearchSuggestion;
import com.amazon.music.find.model.SearchSuggestionEntity;
import com.amazon.music.find.model.SearchSuggestionText;
import com.amazon.music.find.model.configuration.InstantSearchSuggestionConfig;
import com.amazon.music.find.model.configuration.InstantSearchSuggestionMode;
import com.amazon.music.find.providers.SearchFeaturesProvider;
import com.amazon.music.platform.find.common.EntityType;
import com.amazon.music.platform.find.service.common.EntityProperties;
import com.amazon.music.platform.find.service.common.SearchEntity;
import com.google.android.gms.actions.SearchIntents;
import com.google.android.gms.common.internal.ImagesContract;
import defpackage.Base64;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.BiFunction;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt___RangesKt;
import kotlin.text.StringsKt__StringsKt;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: SearchSuggestionServiceDefault.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0094\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u0000 B2\u00020\u0001:\u0001BB%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ2\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\f2\u0006\u0010\u0012\u001a\u00020\u0013H\u0007JN\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\r0\f2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00110\f2\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00110\f2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u000f0\f2\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u000f0\f2\u0006\u0010\u0012\u001a\u00020\u0013H\u0007J\u0018\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J\u0018\u0010\u001f\u001a\u00020\u001a2\u0006\u0010 \u001a\u00020\u001e2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J\u0018\u0010!\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J$\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u001c0\f2\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u001c0\f2\u0006\u0010\u001d\u001a\u00020\u001eH\u0007J,\u0010$\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\f0%2\u0006\u0010&\u001a\u00020\u001e2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\u001e\u0010'\u001a\b\u0012\u0004\u0012\u00020(0%2\u0006\u0010)\u001a\u00020*2\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\u001e\u0010+\u001a\b\u0012\u0004\u0012\u00020,0\f2\u0006\u0010 \u001a\u00020\u001e2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J*\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00110\f2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00110\f2\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00110\fH\u0007JZ\u0010.\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u0002H0\u0012\n\u0012\b\u0012\u0004\u0012\u000202010/0\f\"\b\b\u0000\u00100*\u00020\r2\f\u00103\u001a\b\u0012\u0004\u0012\u0002H00\f2\f\u00104\u001a\b\u0012\u0004\u0012\u0002H00\f2\u0012\u00105\u001a\u000e\u0012\u0004\u0012\u0002H0\u0012\u0004\u0012\u00020\u001e06H\u0007J2\u00107\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u00108\u001a\u0002092\f\u0010:\u001a\b\u0012\u0004\u0012\u00020\u00110\f2\f\u0010;\u001a\b\u0012\u0004\u0012\u00020\u000f0\fH\u0007JH\u0010<\u001a\b\u0012\u0004\u0012\u0002H00\f\"\b\b\u0000\u00100*\u00020\r2\u001e\u0010=\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u0002H0\u0012\n\u0012\b\u0012\u0004\u0012\u000202010/0\f2\u0006\u0010>\u001a\u00020,2\u0006\u0010?\u001a\u00020,H\u0007J\u0018\u0010@\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J\u0018\u0010A\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006C"}, d2 = {"Lcom/amazon/music/find/service/SearchSuggestionServiceDefault;", "Lcom/amazon/music/find/service/SearchSuggestionService;", "searchEntityDao", "Lcom/amazon/music/find/data/database/SearchEntityDao;", "suggestionApi", "Lcom/amazon/music/find/api/searchsuggestion/SearchSuggestionApi;", "uiConfigurationService", "Lcom/amazon/music/find/service/UIConfigurationService;", "searchFeaturesProvider", "Lcom/amazon/music/find/providers/SearchFeaturesProvider;", "(Lcom/amazon/music/find/data/database/SearchEntityDao;Lcom/amazon/music/find/api/searchsuggestion/SearchSuggestionApi;Lcom/amazon/music/find/service/UIConfigurationService;Lcom/amazon/music/find/providers/SearchFeaturesProvider;)V", "aggregateSuggestions", "", "Lcom/amazon/music/find/model/SearchSuggestion;", "textSuggestions", "Lcom/amazon/music/find/model/SearchSuggestionText;", "entitySuggestions", "Lcom/amazon/music/find/model/SearchSuggestionEntity;", "instantSearchConfig", "Lcom/amazon/music/find/model/configuration/InstantSearchSuggestionConfig;", "aggregateSuggestionsV2", "remoteEntities", "localEntities", "remoteTexts", "localTexts", "artistNameContainsQuery", "", "entity", "Lcom/amazon/music/find/data/database/entities/SearchEntityData;", SearchIntents.EXTRA_QUERY, "", "contains", "target", "curatorContainsQuery", "filterEntityHistory", "entities", "getLocalSuggestionEntities", "Lio/reactivex/rxjava3/core/Observable;", "profileId", "getSearchSuggestions", "Lcom/amazon/music/find/api/searchsuggestion/SearchSuggestionResponse;", "executionContext", "Lcom/amazon/music/find/service/ExecutionContext;", "indexesOf", "", "mergeEntities", "mergeSuggestions", "Lkotlin/Pair;", "T", "Ljava/util/EnumSet;", "Lcom/amazon/music/find/model/ElementOrigin;", "remote", ImagesContract.LOCAL, "hash", "Lkotlin/Function1;", "sequenceSuggestions", "suggestionMode", "Lcom/amazon/music/find/model/configuration/InstantSearchSuggestionMode;", "entitiesToDisplay", "textsToDisplay", "sliceSuggestions", "input", "minLocalCount", "maxCount", "subtitleContainsQuery", "titleContainsQuery", "Companion", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SearchSuggestionServiceDefault implements SearchSuggestionService {
    private static final int MAX_QUERY_LENGTH = 100;
    private final SearchEntityDao searchEntityDao;
    private final SearchFeaturesProvider searchFeaturesProvider;
    private final SearchSuggestionApi suggestionApi;
    private final UIConfigurationService uiConfigurationService;
    private static final Logger logger = LoggerFactory.getLogger(SearchSuggestionServiceDefault.class.getName());

    /* JADX INFO: compiled from: SearchSuggestionServiceDefault.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[EntityType.values().length];
            iArr[EntityType.COMMUNITY_PLAYLIST.ordinal()] = 1;
            iArr[EntityType.TRACK.ordinal()] = 2;
            iArr[EntityType.ALBUM.ordinal()] = 3;
            iArr[EntityType.VIDEO.ordinal()] = 4;
            iArr[EntityType.PODCAST_SHOW.ordinal()] = 5;
            iArr[EntityType.PODCAST_EPISODE.ordinal()] = 6;
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[InstantSearchSuggestionMode.values().length];
            iArr2[InstantSearchSuggestionMode.ENTITY_FIRST.ordinal()] = 1;
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    public SearchSuggestionServiceDefault(SearchEntityDao searchEntityDao, SearchSuggestionApi suggestionApi, UIConfigurationService uiConfigurationService, SearchFeaturesProvider searchFeaturesProvider) {
        Intrinsics.checkNotNullParameter(searchEntityDao, "searchEntityDao");
        Intrinsics.checkNotNullParameter(suggestionApi, "suggestionApi");
        Intrinsics.checkNotNullParameter(uiConfigurationService, "uiConfigurationService");
        Intrinsics.checkNotNullParameter(searchFeaturesProvider, "searchFeaturesProvider");
        this.searchEntityDao = searchEntityDao;
        this.suggestionApi = suggestionApi;
        this.uiConfigurationService = uiConfigurationService;
        this.searchFeaturesProvider = searchFeaturesProvider;
    }

    @Override // com.amazon.music.find.service.SearchSuggestionService
    public Observable<SearchSuggestionResponse> getSearchSuggestions(ExecutionContext executionContext, String query) {
        Intrinsics.checkNotNullParameter(executionContext, "executionContext");
        Intrinsics.checkNotNullParameter(query, "query");
        final InstantSearchSuggestionConfig instantSearchConfig = this.uiConfigurationService.getSearchSuggestionConfiguration().getInstantSearchConfig();
        if (this.searchFeaturesProvider.isOffline()) {
            Observable<SearchSuggestionResponse> observableJust = Observable.just(new SearchSuggestionResponse(null, null, 3, null));
            Intrinsics.checkNotNullExpressionValue(observableJust, "just(SearchSuggestionResponse())");
            return observableJust;
        }
        if (StringUtils.isEmpty(query) || StringUtils.isWhitespace(query)) {
            Observable<SearchSuggestionResponse> observableJust2 = Observable.just(new SearchSuggestionResponse(null, null, 3, null));
            Intrinsics.checkNotNullExpressionValue(observableJust2, "just(SearchSuggestionResponse())");
            return observableJust2;
        }
        if (query.length() > 100) {
            Observable<SearchSuggestionResponse> observableError = Observable.error(new SearchSuggestionServiceException("Search query max length exceeded"));
            Intrinsics.checkNotNullExpressionValue(observableError, "error(SearchSuggestionSe…ry max length exceeded\"))");
            return observableError;
        }
        String string = StringsKt__StringsKt.trim((CharSequence) query).toString();
        Observable<SearchSuggestionResponse> observableZip = Observable.zip(this.suggestionApi.getSearchSuggestions(executionContext, new SearchSuggestionRequest(string)).onErrorReturnItem(new SearchSuggestionResponse(null, null, 3, null)), getLocalSuggestionEntities(this.searchFeaturesProvider.getCurrentProfileMusicDirectedId(), string, instantSearchConfig).onErrorReturnItem(CollectionsKt__CollectionsKt.emptyList()), new BiFunction() { // from class: com.amazon.music.find.service.SearchSuggestionServiceDefault$$ExternalSyntheticLambda0
            @Override // io.reactivex.rxjava3.functions.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return SearchSuggestionServiceDefault.m2052getSearchSuggestions$lambda0(instantSearchConfig, this, (SearchSuggestionResponse) obj, (List) obj2);
            }
        });
        Intrinsics.checkNotNullExpressionValue(observableZip, "zip(\n            remoteS…d\n            )\n        }");
        return observableZip;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getSearchSuggestions$lambda-0, reason: not valid java name */
    public static final SearchSuggestionResponse m2052getSearchSuggestions$lambda0(InstantSearchSuggestionConfig instantSearchConfig, SearchSuggestionServiceDefault this$0, SearchSuggestionResponse searchSuggestionResponse, List localEntities) throws SearchSuggestionServiceException {
        List<SearchSuggestion> listAggregateSuggestions;
        Intrinsics.checkNotNullParameter(instantSearchConfig, "$instantSearchConfig");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        List<SearchSuggestion> suggestions = searchSuggestionResponse.getSuggestions();
        ArrayList arrayList = new ArrayList();
        for (Object obj : suggestions) {
            if (obj instanceof SearchSuggestionEntity) {
                arrayList.add(obj);
            }
        }
        List<SearchSuggestion> suggestions2 = searchSuggestionResponse.getSuggestions();
        ArrayList arrayList2 = new ArrayList();
        for (Object obj2 : suggestions2) {
            if (obj2 instanceof SearchSuggestionText) {
                arrayList2.add(obj2);
            }
        }
        if (instantSearchConfig.isAnyEntitySpecEnabled()) {
            Intrinsics.checkNotNullExpressionValue(localEntities, "localEntities");
            listAggregateSuggestions = this$0.aggregateSuggestionsV2(arrayList, localEntities, arrayList2, CollectionsKt__CollectionsKt.emptyList(), instantSearchConfig);
        } else {
            Intrinsics.checkNotNullExpressionValue(localEntities, "localEntities");
            listAggregateSuggestions = this$0.aggregateSuggestions(arrayList2, this$0.mergeEntities(arrayList, localEntities), instantSearchConfig);
        }
        return new SearchSuggestionResponse(listAggregateSuggestions, searchSuggestionResponse.getQueryId());
    }

    @VisibleForTesting
    public final List<SearchSuggestionEntity> mergeEntities(List<SearchSuggestionEntity> remoteEntities, List<SearchSuggestionEntity> localEntities) {
        Intrinsics.checkNotNullParameter(remoteEntities, "remoteEntities");
        Intrinsics.checkNotNullParameter(localEntities, "localEntities");
        List listPlus = CollectionsKt___CollectionsKt.plus((Collection) remoteEntities, (Iterable) localEntities);
        HashSet hashSet = new HashSet();
        ArrayList arrayList = new ArrayList();
        for (Object obj : listPlus) {
            SearchSuggestionEntity searchSuggestionEntity = (SearchSuggestionEntity) obj;
            if (hashSet.add(Intrinsics.stringPlus(searchSuggestionEntity.getSearchEntity().getEntityId(), searchSuggestionEntity.getSearchEntity().getEntityIdType()))) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    @VisibleForTesting
    public final List<SearchSuggestion> aggregateSuggestionsV2(List<SearchSuggestionEntity> remoteEntities, List<SearchSuggestionEntity> localEntities, List<SearchSuggestionText> remoteTexts, List<SearchSuggestionText> localTexts, InstantSearchSuggestionConfig instantSearchConfig) throws SearchSuggestionServiceException {
        Intrinsics.checkNotNullParameter(remoteEntities, "remoteEntities");
        Intrinsics.checkNotNullParameter(localEntities, "localEntities");
        Intrinsics.checkNotNullParameter(remoteTexts, "remoteTexts");
        Intrinsics.checkNotNullParameter(localTexts, "localTexts");
        Intrinsics.checkNotNullParameter(instantSearchConfig, "instantSearchConfig");
        if (instantSearchConfig.getMinLocalEntitiesCount() > instantSearchConfig.getMaxElementsToDisplay() || instantSearchConfig.getMinLocalEntitiesCount() > instantSearchConfig.getMaxEntitiesCount() || instantSearchConfig.getMaxEntitiesCount() > instantSearchConfig.getMaxElementsToDisplay()) {
            throw new SearchSuggestionServiceException("Invalid instant search config");
        }
        List listMergeSuggestions = mergeSuggestions(remoteEntities, localEntities, new Function1<SearchSuggestionEntity, String>() { // from class: com.amazon.music.find.service.SearchSuggestionServiceDefault$aggregateSuggestionsV2$normalizedEntities$1
            @Override // kotlin.jvm.functions.Function1
            public final String invoke(SearchSuggestionEntity entity) {
                Intrinsics.checkNotNullParameter(entity, "entity");
                return Intrinsics.stringPlus(entity.getSearchEntity().getEntityId(), entity.getSearchEntity().getEntityIdType());
            }
        });
        List listMergeSuggestions2 = mergeSuggestions(remoteTexts, localTexts, new Function1<SearchSuggestionText, String>() { // from class: com.amazon.music.find.service.SearchSuggestionServiceDefault$aggregateSuggestionsV2$normalizedTexts$1
            @Override // kotlin.jvm.functions.Function1
            public final String invoke(SearchSuggestionText text) {
                Intrinsics.checkNotNullParameter(text, "text");
                return text.getTitle();
            }
        });
        if (listMergeSuggestions.isEmpty()) {
            return sliceSuggestions(listMergeSuggestions2, instantSearchConfig.getMinLocalSuggestionsCount(), instantSearchConfig.getMaxElementsToDisplay());
        }
        if (listMergeSuggestions2.isEmpty()) {
            return sliceSuggestions(listMergeSuggestions, instantSearchConfig.getMinLocalEntitiesCount(), instantSearchConfig.getMaxElementsToDisplay());
        }
        if (listMergeSuggestions.size() + listMergeSuggestions2.size() <= instantSearchConfig.getMaxElementsToDisplay()) {
            List listPlus = CollectionsKt___CollectionsKt.plus((Collection) listMergeSuggestions, (Iterable) listMergeSuggestions2);
            ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(listPlus, 10));
            Iterator it = listPlus.iterator();
            while (it.hasNext()) {
                arrayList.add((SearchSuggestion) ((Pair) it.next()).getFirst());
            }
            return arrayList;
        }
        if (listMergeSuggestions.size() > instantSearchConfig.getMaxEntitiesCount()) {
            if (listMergeSuggestions2.size() < instantSearchConfig.getMaxElementsToDisplay() - instantSearchConfig.getMaxEntitiesCount()) {
                List listSliceSuggestions = sliceSuggestions(listMergeSuggestions, instantSearchConfig.getMinLocalEntitiesCount(), instantSearchConfig.getMaxElementsToDisplay() - listMergeSuggestions2.size());
                ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(listMergeSuggestions2, 10));
                Iterator it2 = listMergeSuggestions2.iterator();
                while (it2.hasNext()) {
                    arrayList2.add((SearchSuggestionText) ((Pair) it2.next()).getFirst());
                }
                return CollectionsKt___CollectionsKt.plus((Collection) listSliceSuggestions, (Iterable) arrayList2);
            }
            return CollectionsKt___CollectionsKt.plus((Collection) sliceSuggestions(listMergeSuggestions, instantSearchConfig.getMinLocalEntitiesCount(), instantSearchConfig.getMaxEntitiesCount()), (Iterable) sliceSuggestions(listMergeSuggestions2, instantSearchConfig.getMinLocalSuggestionsCount(), instantSearchConfig.getMaxElementsToDisplay() - instantSearchConfig.getMaxEntitiesCount()));
        }
        ArrayList arrayList3 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(listMergeSuggestions, 10));
        Iterator it3 = listMergeSuggestions.iterator();
        while (it3.hasNext()) {
            arrayList3.add((SearchSuggestionEntity) ((Pair) it3.next()).getFirst());
        }
        return CollectionsKt___CollectionsKt.plus((Collection) arrayList3, (Iterable) sliceSuggestions(listMergeSuggestions2, instantSearchConfig.getMinLocalSuggestionsCount(), instantSearchConfig.getMaxElementsToDisplay() - listMergeSuggestions.size()));
    }

    @VisibleForTesting
    public final <T extends SearchSuggestion> List<Pair<T, EnumSet<ElementOrigin>>> mergeSuggestions(List<? extends T> remote, List<? extends T> local, Function1<? super T, String> hash) {
        Intrinsics.checkNotNullParameter(remote, "remote");
        Intrinsics.checkNotNullParameter(local, "local");
        Intrinsics.checkNotNullParameter(hash, "hash");
        if (!remote.isEmpty()) {
            if (!local.isEmpty()) {
                LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt___RangesKt.coerceAtLeast(MapsKt__MapsJVMKt.mapCapacity(CollectionsKt__IterablesKt.collectionSizeOrDefault(local, 10)), 16));
                Iterator<T> it = local.iterator();
                while (it.hasNext()) {
                    Base64 base64 = (Object) it.next();
                    linkedHashMap.put(hash.invoke(base64), base64);
                }
                Map mutableMap = MapsKt__MapsKt.toMutableMap(linkedHashMap);
                ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(remote, 10));
                Iterator<T> it2 = remote.iterator();
                while (it2.hasNext()) {
                    SearchSuggestion searchSuggestion = (SearchSuggestion) it2.next();
                    EnumSet enumSetOf = ((SearchSuggestion) mutableMap.remove(hash.invoke(searchSuggestion))) == null ? null : EnumSet.of(ElementOrigin.Remote, ElementOrigin.Local);
                    if (enumSetOf == null) {
                        enumSetOf = EnumSet.of(ElementOrigin.Remote);
                    }
                    arrayList.add(new Pair(searchSuggestion, enumSetOf));
                }
                ArrayList arrayList2 = new ArrayList(mutableMap.size());
                Iterator it3 = mutableMap.entrySet().iterator();
                while (it3.hasNext()) {
                    arrayList2.add(new Pair(((Map.Entry) it3.next()).getValue(), EnumSet.of(ElementOrigin.Local)));
                }
                return CollectionsKt___CollectionsKt.plus((Collection) arrayList, (Iterable) arrayList2);
            }
            ArrayList arrayList3 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(remote, 10));
            Iterator<T> it4 = remote.iterator();
            while (it4.hasNext()) {
                arrayList3.add(new Pair((SearchSuggestion) it4.next(), EnumSet.of(ElementOrigin.Remote)));
            }
            return arrayList3;
        }
        ArrayList arrayList4 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(local, 10));
        Iterator<T> it5 = local.iterator();
        while (it5.hasNext()) {
            arrayList4.add(new Pair((SearchSuggestion) it5.next(), EnumSet.of(ElementOrigin.Local)));
        }
        return arrayList4;
    }

    @VisibleForTesting
    public final <T extends SearchSuggestion> List<T> sliceSuggestions(List<? extends Pair<? extends T, ? extends EnumSet<ElementOrigin>>> input, int minLocalCount, int maxCount) throws SearchSuggestionServiceException {
        int i;
        Intrinsics.checkNotNullParameter(input, "input");
        if (minLocalCount > maxCount) {
            throw new SearchSuggestionServiceException("Invalid instant search config");
        }
        if (input.size() <= maxCount) {
            ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(input, 10));
            Iterator<T> it = input.iterator();
            while (it.hasNext()) {
                arrayList.add((SearchSuggestion) ((Pair) it.next()).getFirst());
            }
            return arrayList;
        }
        int i2 = 0;
        if (input.isEmpty()) {
            i = 0;
        } else {
            Iterator<T> it2 = input.iterator();
            i = 0;
            while (it2.hasNext()) {
                if (((EnumSet) ((Pair) it2.next()).getSecond()).contains(ElementOrigin.Local) && (i = i + 1) < 0) {
                    CollectionsKt__CollectionsKt.throwCountOverflow();
                }
            }
        }
        int iMin = Math.min(minLocalCount, i);
        List mutableList = CollectionsKt___CollectionsKt.toMutableList((Collection) input);
        int i3 = -1;
        int i4 = 0;
        while (i2 < maxCount) {
            int i5 = i2 + 1;
            if (((EnumSet) ((Pair) mutableList.get(i2)).getSecond()).contains(ElementOrigin.Local)) {
                i4++;
            } else if (((EnumSet) ((Pair) mutableList.get(i2)).getSecond()).contains(ElementOrigin.Remote)) {
                i3 = i2;
            }
            i2 = i5;
        }
        while (i4 < iMin && i3 > 0 && mutableList.size() > maxCount) {
            mutableList.remove(i3);
            if (((EnumSet) ((Pair) mutableList.get(maxCount - 1)).getSecond()).contains(ElementOrigin.Local)) {
                i4++;
            }
            while (i3 > 0 && ((EnumSet) ((Pair) mutableList.get(i3)).getSecond()).contains(ElementOrigin.Local)) {
                i3--;
            }
        }
        List listTake = CollectionsKt___CollectionsKt.take(mutableList, Math.min(maxCount, mutableList.size()));
        ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(listTake, 10));
        Iterator it3 = listTake.iterator();
        while (it3.hasNext()) {
            arrayList2.add((SearchSuggestion) ((Pair) it3.next()).getFirst());
        }
        return arrayList2;
    }

    private final Observable<List<SearchSuggestionEntity>> getLocalSuggestionEntities(final String profileId, final String query, final InstantSearchSuggestionConfig instantSearchConfig) {
        if (instantSearchConfig.getSuggestionMode() != InstantSearchSuggestionMode.DISABLED && query.length() >= instantSearchConfig.getMinEntityQueryLength()) {
            Observable<List<SearchSuggestionEntity>> observableFromCallable = Observable.fromCallable(new Callable() { // from class: com.amazon.music.find.service.SearchSuggestionServiceDefault$$ExternalSyntheticLambda1
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return SearchSuggestionServiceDefault.m2051getLocalSuggestionEntities$lambda14(this.f$0, profileId, instantSearchConfig, query);
                }
            });
            Intrinsics.checkNotNullExpressionValue(observableFromCallable, "{\n            Observable…}\n            }\n        }");
            return observableFromCallable;
        }
        Observable<List<SearchSuggestionEntity>> observableJust = Observable.just(CollectionsKt__CollectionsKt.emptyList());
        Intrinsics.checkNotNullExpressionValue(observableJust, "just(emptyList())");
        return observableJust;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getLocalSuggestionEntities$lambda-14, reason: not valid java name */
    public static final List m2051getLocalSuggestionEntities$lambda14(SearchSuggestionServiceDefault this$0, String profileId, InstantSearchSuggestionConfig instantSearchConfig, String query) throws SearchSuggestionServiceException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(profileId, "$profileId");
        Intrinsics.checkNotNullParameter(instantSearchConfig, "$instantSearchConfig");
        Intrinsics.checkNotNullParameter(query, "$query");
        try {
            List<SearchItem> listFilterEntityHistory = this$0.filterEntityHistory(this$0.searchEntityDao.getRecentSearchEntities(profileId, instantSearchConfig.getEntityQuerySize(), System.currentTimeMillis() - TimeUnit.DAYS.toMillis(instantSearchConfig.getSuggestionRecentEntityDateRange()), instantSearchConfig.getLocalEntityTypes()), query);
            ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(listFilterEntityHistory, 10));
            for (SearchItem searchItem : listFilterEntityHistory) {
                arrayList.add(new SearchSuggestionEntity(searchItem.getTitle(), null, new SearchEntity(searchItem.getEntityId(), searchItem.getEntityIdType(), searchItem.getEntityType(), searchItem.getImageUrl(), searchItem.getContentUrl(), searchItem.getEntityProperties(), null, 64, null), 2, null));
            }
            return arrayList;
        } catch (SQLiteException e) {
            String strStringPlus = Intrinsics.stringPlus("SQLite error occurred when retrieving list of matches in search entities: ", e.getMessage());
            logger.warn(strStringPlus);
            throw new SearchSuggestionServiceException(strStringPlus);
        } catch (Exception e2) {
            String strStringPlus2 = Intrinsics.stringPlus("Failed to retrieve list of matches in search entities: ", e2.getMessage());
            logger.warn(strStringPlus2);
            throw new SearchSuggestionServiceException(strStringPlus2);
        }
    }

    @VisibleForTesting
    public final List<SearchItem> filterEntityHistory(List<SearchItem> entities, String query) {
        Intrinsics.checkNotNullParameter(entities, "entities");
        Intrinsics.checkNotNullParameter(query, "query");
        ArrayList arrayList = new ArrayList();
        for (SearchItem searchItem : entities) {
            if (titleContainsQuery(searchItem, query)) {
                arrayList.add(searchItem);
            } else {
                switch (WhenMappings.$EnumSwitchMapping$0[searchItem.getEntityType().ordinal()]) {
                    case 1:
                        if (curatorContainsQuery(searchItem, query)) {
                            arrayList.add(searchItem);
                        }
                        break;
                    case 2:
                    case 3:
                    case 4:
                        if (artistNameContainsQuery(searchItem, query)) {
                            arrayList.add(searchItem);
                        }
                        break;
                    case 5:
                    case 6:
                        if (subtitleContainsQuery(searchItem, query)) {
                            arrayList.add(searchItem);
                        }
                        break;
                }
            }
        }
        return arrayList;
    }

    private final boolean titleContainsQuery(SearchItem entity, String query) {
        return contains(entity.getTitle(), query);
    }

    private final boolean subtitleContainsQuery(SearchItem entity, String query) {
        String subTitle;
        EntityProperties entityProperties = entity.getEntityProperties();
        if (entityProperties == null || (subTitle = entityProperties.getSubTitle()) == null) {
            return false;
        }
        return contains(subTitle, query);
    }

    private final boolean artistNameContainsQuery(SearchItem entity, String query) {
        String artistName;
        EntityProperties entityProperties = entity.getEntityProperties();
        if (entityProperties == null || (artistName = entityProperties.getArtistName()) == null) {
            return false;
        }
        return contains(artistName, query);
    }

    private final boolean curatorContainsQuery(SearchItem entity, String query) {
        String curator;
        EntityProperties entityProperties = entity.getEntityProperties();
        if (entityProperties == null || (curator = entityProperties.getCurator()) == null) {
            return false;
        }
        return contains(curator, query);
    }

    private final boolean contains(String target, String query) {
        com.amazon.music.find.utils.StringUtils stringUtils = com.amazon.music.find.utils.StringUtils.INSTANCE;
        List<Integer> listIndexesOf = indexesOf(stringUtils.normalize(target), stringUtils.normalize(query));
        if (!listIndexesOf.isEmpty()) {
            if (listIndexesOf.contains(0)) {
                return true;
            }
            Iterator<T> it = listIndexesOf.iterator();
            while (it.hasNext()) {
                if (target.charAt(((Number) it.next()).intValue() - 1) == ' ') {
                    return true;
                }
            }
        }
        return false;
    }

    private final List<Integer> indexesOf(String target, String query) {
        ArrayList arrayList = new ArrayList();
        int iIndexOf$default = StringsKt__StringsKt.indexOf$default((CharSequence) target, query, 0, false, 6, (Object) null);
        while (iIndexOf$default >= 0) {
            arrayList.add(Integer.valueOf(iIndexOf$default));
            iIndexOf$default = StringsKt__StringsKt.indexOf$default((CharSequence) target, query, iIndexOf$default + 1, false, 4, (Object) null);
        }
        return arrayList;
    }

    @VisibleForTesting
    public final List<SearchSuggestion> aggregateSuggestions(List<SearchSuggestionText> textSuggestions, List<SearchSuggestionEntity> entitySuggestions, InstantSearchSuggestionConfig instantSearchConfig) {
        Intrinsics.checkNotNullParameter(textSuggestions, "textSuggestions");
        Intrinsics.checkNotNullParameter(entitySuggestions, "entitySuggestions");
        Intrinsics.checkNotNullParameter(instantSearchConfig, "instantSearchConfig");
        if (instantSearchConfig.getSuggestionMode() == InstantSearchSuggestionMode.DISABLED) {
            return textSuggestions;
        }
        int size = textSuggestions.size();
        int size2 = entitySuggestions.size();
        if (size2 == 0) {
            return CollectionsKt___CollectionsKt.take(textSuggestions, Math.min(size, instantSearchConfig.getMaxElementsToDisplay()));
        }
        if (size == 0) {
            return CollectionsKt___CollectionsKt.take(entitySuggestions, Math.min(size2, instantSearchConfig.getMaxElementsToDisplay()));
        }
        if (size + size2 < instantSearchConfig.getMaxElementsToDisplay()) {
            return sequenceSuggestions(instantSearchConfig.getSuggestionMode(), entitySuggestions, textSuggestions);
        }
        if (size2 <= instantSearchConfig.getMaxEntitiesCount()) {
            return sequenceSuggestions(instantSearchConfig.getSuggestionMode(), entitySuggestions, CollectionsKt___CollectionsKt.take(textSuggestions, Math.min(size, instantSearchConfig.getMaxElementsToDisplay() - size2)));
        }
        if (size < instantSearchConfig.getMaxElementsToDisplay() - instantSearchConfig.getMaxEntitiesCount()) {
            return sequenceSuggestions(instantSearchConfig.getSuggestionMode(), CollectionsKt___CollectionsKt.take(entitySuggestions, Math.min(size2, instantSearchConfig.getMaxElementsToDisplay() - size)), textSuggestions);
        }
        return sequenceSuggestions(instantSearchConfig.getSuggestionMode(), CollectionsKt___CollectionsKt.take(entitySuggestions, instantSearchConfig.getMaxEntitiesCount()), CollectionsKt___CollectionsKt.take(textSuggestions, instantSearchConfig.getMaxElementsToDisplay() - instantSearchConfig.getMaxEntitiesCount()));
    }

    @VisibleForTesting
    public final List<SearchSuggestion> sequenceSuggestions(InstantSearchSuggestionMode suggestionMode, List<SearchSuggestionEntity> entitiesToDisplay, List<SearchSuggestionText> textsToDisplay) {
        Intrinsics.checkNotNullParameter(suggestionMode, "suggestionMode");
        Intrinsics.checkNotNullParameter(entitiesToDisplay, "entitiesToDisplay");
        Intrinsics.checkNotNullParameter(textsToDisplay, "textsToDisplay");
        if (WhenMappings.$EnumSwitchMapping$1[suggestionMode.ordinal()] == 1) {
            return CollectionsKt___CollectionsKt.plus((Collection) entitiesToDisplay, (Iterable) textsToDisplay);
        }
        return CollectionsKt___CollectionsKt.plus((Collection) textsToDisplay, (Iterable) entitiesToDisplay);
    }
}
