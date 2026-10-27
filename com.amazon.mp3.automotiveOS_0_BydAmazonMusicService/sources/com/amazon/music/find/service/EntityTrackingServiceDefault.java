package com.amazon.music.find.service;

import android.database.sqlite.SQLiteException;
import com.amazon.music.find.data.database.SearchEntityDao;
import com.amazon.music.find.data.database.entities.SearchItem;
import com.amazon.music.find.intents.EntityIdentifierType;
import com.amazon.music.find.intents.EntityRefreshIntentInfo;
import com.amazon.music.find.intents.EntityVisitIntentInfo;
import com.amazon.music.find.providers.SearchFeaturesProvider;
import com.amazon.music.platform.find.common.EntityIdType;
import com.amazon.music.skyfire.models.TetheredMessageKey;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: EntityTrackingServiceDefault.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ(\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\b\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J(\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\b\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0010\u001a\u00020\u0011H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/amazon/music/find/service/EntityTrackingServiceDefault;", "Lcom/amazon/music/find/service/EntityTrackingService;", "searchEntityDao", "Lcom/amazon/music/find/data/database/SearchEntityDao;", "uiConfigurationService", "Lcom/amazon/music/find/service/UIConfigurationService;", "searchFeaturesProvider", "Lcom/amazon/music/find/providers/SearchFeaturesProvider;", "(Lcom/amazon/music/find/data/database/SearchEntityDao;Lcom/amazon/music/find/service/UIConfigurationService;Lcom/amazon/music/find/providers/SearchFeaturesProvider;)V", "refreshEntityLastAccessTime", "Lio/reactivex/rxjava3/core/Observable;", "", "executionContext", "Lcom/amazon/music/find/service/ExecutionContext;", "entityRefreshIntentInfo", "Lcom/amazon/music/find/intents/EntityRefreshIntentInfo;", TetheredMessageKey.timestamp, "", "trackEntity", "entityVisitIntentInfo", "Lcom/amazon/music/find/intents/EntityVisitIntentInfo;", "Companion", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class EntityTrackingServiceDefault implements EntityTrackingService {
    private static final Logger logger = LoggerFactory.getLogger(EntityTrackingServiceDefault.class.getName());
    private final SearchEntityDao searchEntityDao;
    private final SearchFeaturesProvider searchFeaturesProvider;
    private final UIConfigurationService uiConfigurationService;

    /* JADX INFO: compiled from: EntityTrackingServiceDefault.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[EntityIdentifierType.values().length];
            iArr[EntityIdentifierType.CONTENT_URL.ordinal()] = 1;
            iArr[EntityIdentifierType.ENTITY_ID_AND_TYPE.ordinal()] = 2;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public EntityTrackingServiceDefault(SearchEntityDao searchEntityDao, UIConfigurationService uiConfigurationService, SearchFeaturesProvider searchFeaturesProvider) {
        Intrinsics.checkNotNullParameter(searchEntityDao, "searchEntityDao");
        Intrinsics.checkNotNullParameter(uiConfigurationService, "uiConfigurationService");
        Intrinsics.checkNotNullParameter(searchFeaturesProvider, "searchFeaturesProvider");
        this.searchEntityDao = searchEntityDao;
        this.uiConfigurationService = uiConfigurationService;
        this.searchFeaturesProvider = searchFeaturesProvider;
    }

    @Override // com.amazon.music.find.service.EntityTrackingService
    public Observable<Unit> trackEntity(ExecutionContext executionContext, final EntityVisitIntentInfo entityVisitIntentInfo, final long timestamp) {
        Intrinsics.checkNotNullParameter(entityVisitIntentInfo, "entityVisitIntentInfo");
        Observable<Unit> observableCreate = Observable.create(new ObservableOnSubscribe() { // from class: com.amazon.music.find.service.EntityTrackingServiceDefault$$ExternalSyntheticLambda1
            @Override // io.reactivex.rxjava3.core.ObservableOnSubscribe
            public final void subscribe(ObservableEmitter observableEmitter) {
                EntityTrackingServiceDefault.m2041trackEntity$lambda0(this.f$0, entityVisitIntentInfo, timestamp, observableEmitter);
            }
        });
        Intrinsics.checkNotNullExpressionValue(observableCreate, "create { subscriber ->\n …)\n            }\n        }");
        return observableCreate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: trackEntity$lambda-0, reason: not valid java name */
    public static final void m2041trackEntity$lambda0(EntityTrackingServiceDefault this$0, EntityVisitIntentInfo entityVisitIntentInfo, long j, ObservableEmitter observableEmitter) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(entityVisitIntentInfo, "$entityVisitIntentInfo");
        try {
            this$0.searchEntityDao.update(new SearchItem(entityVisitIntentInfo.getTitle(), entityVisitIntentInfo.getEntityId(), entityVisitIntentInfo.getEntityIdType(), entityVisitIntentInfo.getEntityType(), entityVisitIntentInfo.getImageUrl(), entityVisitIntentInfo.getEntityProperties(), j, entityVisitIntentInfo.getDidNavigateFromSearch(), entityVisitIntentInfo.getContentUrl(), this$0.searchFeaturesProvider.getCurrentProfileMusicDirectedId()));
            observableEmitter.onNext(Unit.INSTANCE);
            observableEmitter.onComplete();
        } catch (SQLiteException e) {
            String strStringPlus = Intrinsics.stringPlus("SQLite error occurred when inserting/updating search entity in database: ", e.getMessage());
            logger.warn(strStringPlus);
            observableEmitter.onError(new EntityTrackingServiceException(strStringPlus));
        } catch (Exception e2) {
            String strStringPlus2 = Intrinsics.stringPlus("Failed to insert/update search entity in database: ", e2.getMessage());
            logger.warn(strStringPlus2);
            observableEmitter.onError(new EntityTrackingServiceException(strStringPlus2));
        }
    }

    @Override // com.amazon.music.find.service.EntityTrackingService
    public Observable<Unit> refreshEntityLastAccessTime(ExecutionContext executionContext, final EntityRefreshIntentInfo entityRefreshIntentInfo, final long timestamp) {
        Intrinsics.checkNotNullParameter(entityRefreshIntentInfo, "entityRefreshIntentInfo");
        Observable<Unit> observableCreate = Observable.create(new ObservableOnSubscribe() { // from class: com.amazon.music.find.service.EntityTrackingServiceDefault$$ExternalSyntheticLambda0
            @Override // io.reactivex.rxjava3.core.ObservableOnSubscribe
            public final void subscribe(ObservableEmitter observableEmitter) {
                EntityTrackingServiceDefault.m2040refreshEntityLastAccessTime$lambda1(entityRefreshIntentInfo, this, timestamp, observableEmitter);
            }
        });
        Intrinsics.checkNotNullExpressionValue(observableCreate, "create { subscriber ->\n …)\n            }\n        }");
        return observableCreate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: refreshEntityLastAccessTime$lambda-1, reason: not valid java name */
    public static final void m2040refreshEntityLastAccessTime$lambda1(EntityRefreshIntentInfo entityRefreshIntentInfo, EntityTrackingServiceDefault this$0, long j, ObservableEmitter observableEmitter) {
        Intrinsics.checkNotNullParameter(entityRefreshIntentInfo, "$entityRefreshIntentInfo");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        try {
            int i = WhenMappings.$EnumSwitchMapping$0[entityRefreshIntentInfo.getEntityIdentifierType().ordinal()];
            if (i == 1) {
                String contentUrl = entityRefreshIntentInfo.getContentUrl();
                if (contentUrl != null) {
                    this$0.searchEntityDao.updateSearchEntityLastAccessTime(this$0.searchFeaturesProvider.getCurrentProfileMusicDirectedId(), contentUrl, j);
                }
            } else if (i == 2) {
                String entityId = entityRefreshIntentInfo.getEntityId();
                EntityIdType entityIdType = entityRefreshIntentInfo.getEntityIdType();
                if (entityId != null && entityIdType != null) {
                    this$0.searchEntityDao.updateSearchEntityLastAccessTime(this$0.searchFeaturesProvider.getCurrentProfileMusicDirectedId(), entityId, entityIdType, j);
                }
            }
            observableEmitter.onNext(Unit.INSTANCE);
            observableEmitter.onComplete();
        } catch (SQLiteException e) {
            String strStringPlus = Intrinsics.stringPlus("SQLite error occurred when updating last access time for search entity in database: ", e.getMessage());
            logger.warn(strStringPlus);
            observableEmitter.onError(new EntityTrackingServiceException(strStringPlus));
        } catch (Exception e2) {
            String strStringPlus2 = Intrinsics.stringPlus("Failed to update last access time for search entity in database: ", e2.getMessage());
            logger.warn(strStringPlus2);
            observableEmitter.onError(new EntityTrackingServiceException(strStringPlus2));
        }
    }
}
