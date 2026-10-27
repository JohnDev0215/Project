package com.amazon.music.find.service;

import com.amazon.music.find.model.search.ContentOwnershipItem;
import io.reactivex.rxjava3.core.Observable;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: LibraryStateService.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001JI\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\u0006\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u00042\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00042\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\rH&¢\u0006\u0002\u0010\u000e¨\u0006\u000f"}, d2 = {"Lcom/amazon/music/find/service/LibraryStateService;", "", "requestState", "Lio/reactivex/rxjava3/core/Observable;", "", "Lcom/amazon/music/find/model/search/ContentOwnershipItem;", "executionContext", "Lcom/amazon/music/find/service/ExecutionContext;", "trackIds", "", "trackAsins", "", "pageSize", "", "(Lcom/amazon/music/find/service/ExecutionContext;Ljava/util/List;Ljava/util/List;Ljava/lang/Integer;)Lio/reactivex/rxjava3/core/Observable;", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface LibraryStateService {
    Observable<List<ContentOwnershipItem>> requestState(ExecutionContext executionContext, List<Long> trackIds, List<String> trackAsins, Integer pageSize);

    /* JADX INFO: compiled from: LibraryStateService.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public static final class DefaultImpls {
        public static /* synthetic */ Observable requestState$default(LibraryStateService libraryStateService, ExecutionContext executionContext, List list, List list2, Integer num, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: requestState");
            }
            if ((i & 8) != 0) {
                num = null;
            }
            return libraryStateService.requestState(executionContext, list, list2, num);
        }
    }
}
