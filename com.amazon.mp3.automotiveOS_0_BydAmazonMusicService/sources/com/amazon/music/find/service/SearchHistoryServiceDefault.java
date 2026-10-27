package com.amazon.music.find.service;

import android.database.sqlite.SQLiteException;
import com.amazon.music.find.data.database.SearchEntityDao;
import com.amazon.music.find.data.database.SearchQueryDao;
import com.amazon.music.find.data.database.entities.SearchHistory;
import com.amazon.music.find.data.database.entities.SearchItem;
import com.amazon.music.find.model.configuration.SearchEntityConfiguration;
import com.amazon.music.find.model.configuration.SearchHistoryConfiguration;
import com.amazon.music.find.model.configuration.SearchQueryConfiguration;
import com.amazon.music.find.providers.SearchFeaturesProvider;
import com.amazon.music.platform.find.common.EntityType;
import com.amazon.music.platform.find.service.common.SearchEntity;
import com.amazon.music.platform.find.service.history.SearchHistoryEntity;
import com.amazon.music.platform.find.service.history.SearchHistoryItem;
import com.amazon.music.platform.find.service.history.SearchHistoryQuery;
import com.amazon.music.skyfire.models.TetheredMessageKey;
import com.google.android.gms.actions.SearchIntents;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.functions.BiFunction;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.comparisons.ComparisonsKt__ComparisonsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: SearchHistoryServiceDefault.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 )2\u00020\u0001:\u0001)B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\u0018\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016J\u0018\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016J\u0018\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J$\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001a0\u00190\u000e2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0017H\u0002J,\u0010\u001e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001f0\u00190\u000e2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010 \u001a\u00020\u00172\u0006\u0010!\u001a\u00020\"H\u0002J\u001e\u0010#\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020$0\u00190\u000e2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016J(\u0010%\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010&\u001a\u00020\u001c2\u0006\u0010'\u001a\u00020\u0014H\u0016J\u000e\u0010(\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006*"}, d2 = {"Lcom/amazon/music/find/service/SearchHistoryServiceDefault;", "Lcom/amazon/music/find/service/SearchHistoryService;", "searchQueryDao", "Lcom/amazon/music/find/data/database/SearchQueryDao;", "searchEntityDao", "Lcom/amazon/music/find/data/database/SearchEntityDao;", "uiConfigurationService", "Lcom/amazon/music/find/service/UIConfigurationService;", "userSettingService", "Lcom/amazon/music/find/service/UserSettingsService;", "searchFeaturesProvider", "Lcom/amazon/music/find/providers/SearchFeaturesProvider;", "(Lcom/amazon/music/find/data/database/SearchQueryDao;Lcom/amazon/music/find/data/database/SearchEntityDao;Lcom/amazon/music/find/service/UIConfigurationService;Lcom/amazon/music/find/service/UserSettingsService;Lcom/amazon/music/find/providers/SearchFeaturesProvider;)V", "deleteExpiredHistory", "Lio/reactivex/rxjava3/core/Observable;", "", "executionContext", "Lcom/amazon/music/find/service/ExecutionContext;", "deleteSearchHistory", "getExpiredTimestamp", "", "currentTimeMillis", "ttlDays", "", "getRecentQueries", "", "Lcom/amazon/music/find/data/database/entities/SearchQueryData;", "profileId", "", "searchQueryCount", "getRecentSearchEntities", "Lcom/amazon/music/find/data/database/entities/SearchEntityData;", "searchEntityCount", "searchHistoryConfiguration", "Lcom/amazon/music/find/model/configuration/SearchHistoryConfiguration;", "getRecentSearchHistory", "Lcom/amazon/music/platform/find/service/history/SearchHistoryItem;", "putRecentSearchQuery", SearchIntents.EXTRA_QUERY, TetheredMessageKey.timestamp, "syncDefaultProfileHistory", "Companion", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SearchHistoryServiceDefault implements SearchHistoryService {
    private static final int DEFAULT_TTL_DAYS = 90;
    private final SearchEntityDao searchEntityDao;
    private final SearchFeaturesProvider searchFeaturesProvider;
    private final SearchQueryDao searchQueryDao;
    private final UIConfigurationService uiConfigurationService;
    private final UserSettingsService userSettingService;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Logger logger = LoggerFactory.getLogger(SearchHistoryServiceDefault.class.getName());
    private static final double matchDistanceMin = 0.92d;

    public SearchHistoryServiceDefault(SearchQueryDao searchQueryDao, SearchEntityDao searchEntityDao, UIConfigurationService uiConfigurationService, UserSettingsService userSettingService, SearchFeaturesProvider searchFeaturesProvider) {
        Intrinsics.checkNotNullParameter(searchQueryDao, "searchQueryDao");
        Intrinsics.checkNotNullParameter(searchEntityDao, "searchEntityDao");
        Intrinsics.checkNotNullParameter(uiConfigurationService, "uiConfigurationService");
        Intrinsics.checkNotNullParameter(userSettingService, "userSettingService");
        Intrinsics.checkNotNullParameter(searchFeaturesProvider, "searchFeaturesProvider");
        this.searchQueryDao = searchQueryDao;
        this.searchEntityDao = searchEntityDao;
        this.uiConfigurationService = uiConfigurationService;
        this.userSettingService = userSettingService;
        this.searchFeaturesProvider = searchFeaturesProvider;
    }

    /* JADX INFO: compiled from: SearchHistoryServiceDefault.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J&\u0010\n\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J \u0010\u0013\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u00102\u0006\u0010\b\u001a\u00020\tH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082D¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/amazon/music/find/service/SearchHistoryServiceDefault$Companion;", "", "()V", "DEFAULT_TTL_DAYS", "", "logger", "Lorg/slf4j/Logger;", "kotlin.jvm.PlatformType", "matchDistanceMin", "", "hasUniqueTitleOrNewer", "", "entities", "", "Lcom/amazon/music/find/data/database/entities/SearchEntityData;", "queryTitle", "", "queryTimestamp", "", "isSameTitle", "title1", "title2", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        private final boolean isSameTitle(String title1, String title2, double matchDistanceMin) {
            Locale locale = Locale.getDefault();
            Intrinsics.checkNotNullExpressionValue(locale, "getDefault()");
            String lowerCase = title1.toLowerCase(locale);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(locale)");
            Locale locale2 = Locale.getDefault();
            Intrinsics.checkNotNullExpressionValue(locale2, "getDefault()");
            String lowerCase2 = title2.toLowerCase(locale2);
            Intrinsics.checkNotNullExpressionValue(lowerCase2, "this as java.lang.String).toLowerCase(locale)");
            return StringUtils.getJaroWinklerDistance(lowerCase, lowerCase2) > matchDistanceMin;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final boolean hasUniqueTitleOrNewer(List<SearchItem> entities, String queryTitle, long queryTimestamp) {
            for (SearchItem searchItem : entities) {
                if (SearchHistoryServiceDefault.INSTANCE.isSameTitle(searchItem.getTitle(), queryTitle, SearchHistoryServiceDefault.matchDistanceMin) && queryTimestamp < searchItem.getTimestamp()) {
                    return false;
                }
            }
            return true;
        }
    }

    @Override // com.amazon.music.find.service.SearchHistoryService
    public Observable<List<SearchHistoryItem>> getRecentSearchHistory(ExecutionContext executionContext) {
        if (this.searchFeaturesProvider.isOffline()) {
            Observable<List<SearchHistoryItem>> observableJust = Observable.just(CollectionsKt__CollectionsKt.emptyList());
            Intrinsics.checkNotNullExpressionValue(observableJust, "just(emptyList())");
            return observableJust;
        }
        String currentProfileMusicDirectedId = this.searchFeaturesProvider.getCurrentProfileMusicDirectedId();
        final SearchHistoryConfiguration searchHistoryConfiguration = this.uiConfigurationService.getSearchHistoryConfiguration();
        SearchEntityConfiguration searchEntityConfiguration = searchHistoryConfiguration.getSearchEntityConfiguration();
        int count = searchEntityConfiguration == null ? 0 : searchEntityConfiguration.getCount();
        SearchQueryConfiguration searchQueryConfiguration = searchHistoryConfiguration.getSearchQueryConfiguration();
        Observable<List<SearchHistoryItem>> observableZip = Observable.zip(getRecentSearchEntities(currentProfileMusicDirectedId, count, searchHistoryConfiguration), getRecentQueries(currentProfileMusicDirectedId, searchQueryConfiguration != null ? searchQueryConfiguration.getCount() : 0), new BiFunction() { // from class: com.amazon.music.find.service.SearchHistoryServiceDefault$$ExternalSyntheticLambda6
            @Override // io.reactivex.rxjava3.functions.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return SearchHistoryServiceDefault.m2048getRecentSearchHistory$lambda5(searchHistoryConfiguration, (List) obj, (List) obj2);
            }
        });
        Intrinsics.checkNotNullExpressionValue(observableZip, "zip(recentEntities, rece…          }\n            }");
        return observableZip;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getRecentSearchHistory$lambda-5, reason: not valid java name */
    public static final List m2048getRecentSearchHistory$lambda5(SearchHistoryConfiguration searchHistoryConfiguration, List recentEntities, List recentQueries) {
        SearchHistoryItem searchHistoryQuery;
        Intrinsics.checkNotNullParameter(searchHistoryConfiguration, "$searchHistoryConfiguration");
        Intrinsics.checkNotNullExpressionValue(recentQueries, "recentQueries");
        ArrayList<SearchHistory> arrayList = new ArrayList();
        for (Object obj : recentQueries) {
            SearchHistory searchHistory = (SearchHistory) obj;
            Companion companion = INSTANCE;
            Intrinsics.checkNotNullExpressionValue(recentEntities, "recentEntities");
            if (companion.hasUniqueTitleOrNewer(recentEntities, searchHistory.getQuery(), searchHistory.getTimestamp())) {
                arrayList.add(obj);
            }
        }
        List[] listArr = new List[2];
        Intrinsics.checkNotNullExpressionValue(recentEntities, "recentEntities");
        ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(recentEntities, 10));
        Iterator it = recentEntities.iterator();
        while (it.hasNext()) {
            SearchItem searchItem = (SearchItem) it.next();
            arrayList2.add(new Pair(Long.valueOf(searchItem.getTimestamp()), searchItem));
        }
        listArr[0] = arrayList2;
        ArrayList arrayList3 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList, 10));
        for (SearchHistory searchHistory2 : arrayList) {
            arrayList3.add(new Pair(Long.valueOf(searchHistory2.getTimestamp()), searchHistory2));
        }
        listArr[1] = arrayList3;
        List listTake = CollectionsKt___CollectionsKt.take(CollectionsKt___CollectionsKt.sortedWith(CollectionsKt__IterablesKt.flatten(CollectionsKt__CollectionsKt.listOf((Object[]) listArr)), new Comparator() { // from class: com.amazon.music.find.service.SearchHistoryServiceDefault$getRecentSearchHistory$lambda-5$$inlined$sortedByDescending$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return ComparisonsKt__ComparisonsKt.compareValues((Long) ((Pair) t2).getFirst(), (Long) ((Pair) t).getFirst());
            }
        }), searchHistoryConfiguration.getMaxReturnEntityCount());
        ArrayList arrayList4 = new ArrayList();
        Iterator it2 = listTake.iterator();
        while (it2.hasNext()) {
            Object second = ((Pair) it2.next()).getSecond();
            if (second instanceof SearchItem) {
                SearchItem searchItem2 = (SearchItem) second;
                searchHistoryQuery = new SearchHistoryEntity(searchItem2.getTitle(), new SearchEntity(searchItem2.getEntityId(), searchItem2.getEntityIdType(), searchItem2.getEntityType(), searchItem2.getImageUrl(), searchItem2.getContentUrl(), searchItem2.getEntityProperties(), null, 64, null));
            } else {
                searchHistoryQuery = second instanceof SearchHistory ? new SearchHistoryQuery(((SearchHistory) second).getQuery()) : null;
            }
            if (searchHistoryQuery != null) {
                arrayList4.add(searchHistoryQuery);
            }
        }
        return arrayList4;
    }

    private final Observable<List<SearchItem>> getRecentSearchEntities(final String profileId, final int searchEntityCount, final SearchHistoryConfiguration searchHistoryConfiguration) {
        if (searchEntityCount > 0) {
            Observable<List<SearchItem>> observableFromCallable = Observable.fromCallable(new Callable() { // from class: com.amazon.music.find.service.SearchHistoryServiceDefault$$ExternalSyntheticLambda5
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return SearchHistoryServiceDefault.m2047getRecentSearchEntities$lambda6(this.f$0, profileId, searchEntityCount, searchHistoryConfiguration);
                }
            });
            Intrinsics.checkNotNullExpressionValue(observableFromCallable, "fromCallable {\n         …)\n            }\n        }");
            return observableFromCallable;
        }
        Observable<List<SearchItem>> observableJust = Observable.just(CollectionsKt__CollectionsKt.emptyList());
        Intrinsics.checkNotNullExpressionValue(observableJust, "just(emptyList())");
        return observableJust;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getRecentSearchEntities$lambda-6, reason: not valid java name */
    public static final List m2047getRecentSearchEntities$lambda6(SearchHistoryServiceDefault this$0, String profileId, int i, SearchHistoryConfiguration searchHistoryConfiguration) throws SearchHistoryServiceException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(profileId, "$profileId");
        Intrinsics.checkNotNullParameter(searchHistoryConfiguration, "$searchHistoryConfiguration");
        try {
            SearchEntityDao searchEntityDao = this$0.searchEntityDao;
            SearchEntityConfiguration searchEntityConfiguration = searchHistoryConfiguration.getSearchEntityConfiguration();
            Set<EntityType> localEntityTypes = searchEntityConfiguration == null ? null : searchEntityConfiguration.getLocalEntityTypes();
            if (localEntityTypes == null) {
                localEntityTypes = SearchEntityConfiguration.INSTANCE.getSUPPORTED_LOCAL_ENTITY_TYPES();
            }
            return searchEntityDao.getRecentSearchEntitiesFromSearch(profileId, i, localEntityTypes);
        } catch (SQLiteException e) {
            String strStringPlus = Intrinsics.stringPlus("SQLite error occurred when getting search entities in database: ", e.getMessage());
            logger.warn(strStringPlus);
            throw new SearchHistoryServiceException(strStringPlus);
        } catch (Exception e2) {
            String strStringPlus2 = Intrinsics.stringPlus("Failed to get get recent search entities: ", e2.getMessage());
            logger.warn(strStringPlus2);
            throw new SearchHistoryServiceException(strStringPlus2);
        }
    }

    private final Observable<List<SearchHistory>> getRecentQueries(final String profileId, final int searchQueryCount) {
        if (searchQueryCount > 0) {
            Observable<List<SearchHistory>> observableFromCallable = Observable.fromCallable(new Callable() { // from class: com.amazon.music.find.service.SearchHistoryServiceDefault$$ExternalSyntheticLambda1
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return SearchHistoryServiceDefault.m2046getRecentQueries$lambda7(this.f$0, profileId, searchQueryCount);
                }
            });
            Intrinsics.checkNotNullExpressionValue(observableFromCallable, "fromCallable {\n         …)\n            }\n        }");
            return observableFromCallable;
        }
        Observable<List<SearchHistory>> observableJust = Observable.just(CollectionsKt__CollectionsKt.emptyList());
        Intrinsics.checkNotNullExpressionValue(observableJust, "just(emptyList())");
        return observableJust;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getRecentQueries$lambda-7, reason: not valid java name */
    public static final List m2046getRecentQueries$lambda7(SearchHistoryServiceDefault this$0, String profileId, int i) throws SearchHistoryServiceException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(profileId, "$profileId");
        try {
            return this$0.searchQueryDao.getRecentSearchHistory(profileId, i);
        } catch (SQLiteException e) {
            String strStringPlus = Intrinsics.stringPlus("SQLite error occurred when getting recent search queries in database: ", e.getMessage());
            logger.warn(strStringPlus);
            throw new SearchHistoryServiceException(strStringPlus);
        } catch (Exception e2) {
            String strStringPlus2 = Intrinsics.stringPlus("Failed to get get recent search queries: ", e2.getMessage());
            logger.warn(strStringPlus2);
            throw new SearchHistoryServiceException(strStringPlus2);
        }
    }

    @Override // com.amazon.music.find.service.SearchHistoryService
    public Observable<Unit> putRecentSearchQuery(ExecutionContext executionContext, final String query, final long timestamp) {
        Intrinsics.checkNotNullParameter(query, "query");
        Observable<Unit> observableCreate = Observable.create(new ObservableOnSubscribe() { // from class: com.amazon.music.find.service.SearchHistoryServiceDefault$$ExternalSyntheticLambda0
            @Override // io.reactivex.rxjava3.core.ObservableOnSubscribe
            public final void subscribe(ObservableEmitter observableEmitter) {
                SearchHistoryServiceDefault.m2049putRecentSearchQuery$lambda8(this.f$0, query, timestamp, observableEmitter);
            }
        });
        Intrinsics.checkNotNullExpressionValue(observableCreate, "create { subscriber ->\n …)\n            }\n        }");
        return observableCreate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: putRecentSearchQuery$lambda-8, reason: not valid java name */
    public static final void m2049putRecentSearchQuery$lambda8(SearchHistoryServiceDefault this$0, String query, long j, ObservableEmitter observableEmitter) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(query, "$query");
        try {
            this$0.searchQueryDao.put(new SearchHistory(query, j, this$0.searchFeaturesProvider.getCurrentProfileMusicDirectedId()));
            observableEmitter.onComplete();
        } catch (SQLiteException e) {
            String strStringPlus = Intrinsics.stringPlus("SQLite error occurred when inserting/updating search query in database: ", e.getMessage());
            logger.warn(strStringPlus);
            observableEmitter.onError(new SearchHistoryServiceException(strStringPlus));
        } catch (Exception e2) {
            String strStringPlus2 = Intrinsics.stringPlus("Failed to insert/update search query in database: ", e2.getMessage());
            logger.warn(strStringPlus2);
            observableEmitter.onError(new SearchHistoryServiceException(strStringPlus2));
        }
    }

    @Override // com.amazon.music.find.service.SearchHistoryService
    public Observable<Unit> deleteSearchHistory(ExecutionContext executionContext) {
        Observable<Unit> observableCreate = Observable.create(new ObservableOnSubscribe() { // from class: com.amazon.music.find.service.SearchHistoryServiceDefault$$ExternalSyntheticLambda4
            @Override // io.reactivex.rxjava3.core.ObservableOnSubscribe
            public final void subscribe(ObservableEmitter observableEmitter) {
                SearchHistoryServiceDefault.m2045deleteSearchHistory$lambda9(this.f$0, observableEmitter);
            }
        });
        Intrinsics.checkNotNullExpressionValue(observableCreate, "create { subscriber ->\n …)\n            }\n        }");
        return observableCreate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: deleteSearchHistory$lambda-9, reason: not valid java name */
    public static final void m2045deleteSearchHistory$lambda9(SearchHistoryServiceDefault this$0, ObservableEmitter observableEmitter) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        try {
            String currentProfileMusicDirectedId = this$0.searchFeaturesProvider.getCurrentProfileMusicDirectedId();
            this$0.searchQueryDao.deleteAll(currentProfileMusicDirectedId);
            this$0.searchEntityDao.resetNavigationFromSearch(currentProfileMusicDirectedId, false);
            this$0.searchEntityDao.removeByEntityType(currentProfileMusicDirectedId, CollectionsKt__CollectionsJVMKt.listOf(EntityType.COMMUNITY_PLAYLIST));
            this$0.userSettingService.updateLastClearSearchHistoryDate(System.currentTimeMillis());
            observableEmitter.onNext(Unit.INSTANCE);
            observableEmitter.onComplete();
        } catch (SQLiteException e) {
            String strStringPlus = Intrinsics.stringPlus("SQLite error occurred when inserting/updating search query in database when deleting search history in database: ", e.getMessage());
            logger.warn(strStringPlus);
            observableEmitter.onError(new SearchHistoryServiceException(strStringPlus));
        } catch (Exception e2) {
            String strStringPlus2 = Intrinsics.stringPlus("Failed to delete search history in database: ", e2.getMessage());
            logger.warn(strStringPlus2);
            observableEmitter.onError(new SearchHistoryServiceException(strStringPlus2));
        }
    }

    @Override // com.amazon.music.find.service.SearchHistoryService
    public Observable<Unit> deleteExpiredHistory(ExecutionContext executionContext) {
        Integer ttlDays;
        SearchEntityConfiguration searchEntityConfiguration = this.uiConfigurationService.getSearchHistoryConfiguration().getSearchEntityConfiguration();
        int iIntValue = 90;
        if (searchEntityConfiguration != null && (ttlDays = searchEntityConfiguration.getTtlDays()) != null) {
            iIntValue = ttlDays.intValue();
        }
        final long expiredTimestamp = getExpiredTimestamp(System.currentTimeMillis(), iIntValue);
        Observable<Unit> observableCreate = Observable.create(new ObservableOnSubscribe() { // from class: com.amazon.music.find.service.SearchHistoryServiceDefault$$ExternalSyntheticLambda3
            @Override // io.reactivex.rxjava3.core.ObservableOnSubscribe
            public final void subscribe(ObservableEmitter observableEmitter) {
                SearchHistoryServiceDefault.m2044deleteExpiredHistory$lambda10(this.f$0, expiredTimestamp, observableEmitter);
            }
        });
        Intrinsics.checkNotNullExpressionValue(observableCreate, "create { subscriber ->\n …)\n            }\n        }");
        return observableCreate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: deleteExpiredHistory$lambda-10, reason: not valid java name */
    public static final void m2044deleteExpiredHistory$lambda10(SearchHistoryServiceDefault this$0, long j, ObservableEmitter observableEmitter) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        try {
            this$0.searchQueryDao.deleteExpired(j);
            this$0.searchEntityDao.deleteExpired(j);
            observableEmitter.onNext(Unit.INSTANCE);
            observableEmitter.onComplete();
        } catch (SQLiteException e) {
            String strStringPlus = Intrinsics.stringPlus("SQLite error occurred when deleting expired history in database: ", e.getMessage());
            logger.warn(strStringPlus);
            observableEmitter.onError(new SearchHistoryServiceException(strStringPlus));
        } catch (Exception e2) {
            String strStringPlus2 = Intrinsics.stringPlus("Failed to delete expired search history in database: ", e2.getMessage());
            logger.warn(strStringPlus2);
            observableEmitter.onError(new SearchHistoryServiceException(strStringPlus2));
        }
    }

    private final long getExpiredTimestamp(long currentTimeMillis, int ttlDays) {
        return currentTimeMillis - TimeUnit.DAYS.toMillis(ttlDays);
    }

    @Override // com.amazon.music.find.service.SearchHistoryService
    public Observable<Unit> syncDefaultProfileHistory() {
        Observable<Unit> observableCreate = Observable.create(new ObservableOnSubscribe() { // from class: com.amazon.music.find.service.SearchHistoryServiceDefault$$ExternalSyntheticLambda2
            @Override // io.reactivex.rxjava3.core.ObservableOnSubscribe
            public final void subscribe(ObservableEmitter observableEmitter) {
                SearchHistoryServiceDefault.m2050syncDefaultProfileHistory$lambda11(this.f$0, observableEmitter);
            }
        });
        Intrinsics.checkNotNullExpressionValue(observableCreate, "create { subscriber ->\n …)\n            }\n        }");
        return observableCreate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: syncDefaultProfileHistory$lambda-11, reason: not valid java name */
    public static final void m2050syncDefaultProfileHistory$lambda11(SearchHistoryServiceDefault this$0, ObservableEmitter observableEmitter) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        try {
            if (Intrinsics.areEqual(this$0.searchFeaturesProvider.isDefaultProfile(), Boolean.TRUE)) {
                String currentProfileMusicDirectedId = this$0.searchFeaturesProvider.getCurrentProfileMusicDirectedId();
                this$0.searchQueryDao.syncDefaultProfileHistory(currentProfileMusicDirectedId);
                this$0.searchEntityDao.syncDefaultProfileHistory(currentProfileMusicDirectedId);
            }
            observableEmitter.onNext(Unit.INSTANCE);
            observableEmitter.onComplete();
        } catch (SQLiteException e) {
            String strStringPlus = Intrinsics.stringPlus("SQLite error occurred when updating profile_id for default profile in database: ", e.getMessage());
            logger.warn(strStringPlus);
            observableEmitter.onError(new SearchHistoryServiceException(strStringPlus));
        } catch (Exception e2) {
            String strStringPlus2 = Intrinsics.stringPlus("Failed to sync history for default profile in database: ", e2.getMessage());
            logger.warn(strStringPlus2);
            observableEmitter.onError(new SearchHistoryServiceException(strStringPlus2));
        }
    }
}
