package com.amazon.music.find.service;

import com.amazon.music.find.data.database.LibrarySearchDao;
import com.amazon.music.find.model.search.ContentOwnershipItem;
import com.amazon.music.find.providers.SearchFeaturesProvider;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: LibraryStateServiceDefault.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006JG\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\b2\u0006\u0010\u000b\u001a\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\t2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\t2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016¢\u0006\u0002\u0010\u0013R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/amazon/music/find/service/LibraryStateServiceDefault;", "Lcom/amazon/music/find/service/LibraryStateService;", "librarySearchDao", "Lcom/amazon/music/find/data/database/LibrarySearchDao;", "searchFeaturesProvider", "Lcom/amazon/music/find/providers/SearchFeaturesProvider;", "(Lcom/amazon/music/find/data/database/LibrarySearchDao;Lcom/amazon/music/find/providers/SearchFeaturesProvider;)V", "requestState", "Lio/reactivex/rxjava3/core/Observable;", "", "Lcom/amazon/music/find/model/search/ContentOwnershipItem;", "executionContext", "Lcom/amazon/music/find/service/ExecutionContext;", "trackIds", "", "trackAsins", "", "pageSize", "", "(Lcom/amazon/music/find/service/ExecutionContext;Ljava/util/List;Ljava/util/List;Ljava/lang/Integer;)Lio/reactivex/rxjava3/core/Observable;", "Companion", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class LibraryStateServiceDefault implements LibraryStateService {
    private static final Logger logger = LoggerFactory.getLogger(LibraryStateServiceDefault.class.getName());
    private final LibrarySearchDao librarySearchDao;
    private final SearchFeaturesProvider searchFeaturesProvider;

    public LibraryStateServiceDefault(LibrarySearchDao librarySearchDao, SearchFeaturesProvider searchFeaturesProvider) {
        Intrinsics.checkNotNullParameter(librarySearchDao, "librarySearchDao");
        Intrinsics.checkNotNullParameter(searchFeaturesProvider, "searchFeaturesProvider");
        this.librarySearchDao = librarySearchDao;
        this.searchFeaturesProvider = searchFeaturesProvider;
    }

    @Override // com.amazon.music.find.service.LibraryStateService
    public Observable<List<ContentOwnershipItem>> requestState(ExecutionContext executionContext, final List<Long> trackIds, final List<String> trackAsins, final Integer pageSize) {
        Intrinsics.checkNotNullParameter(executionContext, "executionContext");
        Intrinsics.checkNotNullParameter(trackIds, "trackIds");
        Intrinsics.checkNotNullParameter(trackAsins, "trackAsins");
        Observable<List<ContentOwnershipItem>> observableCreate = Observable.create(new ObservableOnSubscribe() { // from class: com.amazon.music.find.service.LibraryStateServiceDefault$$ExternalSyntheticLambda0
            @Override // io.reactivex.rxjava3.core.ObservableOnSubscribe
            public final void subscribe(ObservableEmitter observableEmitter) {
                LibraryStateServiceDefault.m2043requestState$lambda0(this.f$0, trackIds, trackAsins, pageSize, observableEmitter);
            }
        });
        Intrinsics.checkNotNullExpressionValue(observableCreate, "create { subscriber ->\n …)\n            }\n        }");
        return observableCreate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: requestState$lambda-0, reason: not valid java name */
    public static final void m2043requestState$lambda0(LibraryStateServiceDefault this$0, List trackIds, List trackAsins, Integer num, ObservableEmitter observableEmitter) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(trackIds, "$trackIds");
        Intrinsics.checkNotNullParameter(trackAsins, "$trackAsins");
        try {
            observableEmitter.onNext(this$0.librarySearchDao.requestState(trackIds, trackAsins, this$0.searchFeaturesProvider.isOffline(), num));
            observableEmitter.onComplete();
        } catch (SearchServiceException e) {
            String strStringPlus = Intrinsics.stringPlus("Something went wrong while getting library requestState, exception:", e.getMessage());
            logger.warn(strStringPlus);
            observableEmitter.onError(new SearchServiceException(strStringPlus));
        }
    }
}
