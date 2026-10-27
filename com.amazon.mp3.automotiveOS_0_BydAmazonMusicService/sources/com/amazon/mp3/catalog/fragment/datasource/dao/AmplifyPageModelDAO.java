package com.amazon.mp3.catalog.fragment.datasource.dao;

import androidx.annotation.VisibleForTesting;
import com.amazon.digitalmusicxp.callbacks.Outcome;
import com.amazon.digitalmusicxp.clients.CloudQueueClient;
import com.amazon.digitalmusicxp.models.QueueEntityMetadata;
import com.amazon.digitalmusicxp.models.QueueSequenceSlice;
import com.amazon.digitalmusicxp.types.EntityReference;
import com.amazon.mp3.catalog.fragment.datasource.dao.EditablePageViewModelDao;
import com.amazon.mp3.catalog.fragment.viewmodel.FetchType;
import com.amazon.mp3.cloudqueue.CloudQueueClientSingleton;
import com.amazon.mp3.cloudqueue.CloudQueuePlaybackUtil;
import com.amazon.mp3.cloudqueue.sequencer.CloudQueueSequencer;
import com.amazon.mp3.util.Log;
import com.amazon.music.views.library.models.PageGridViewModel;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AmplifyPageModelDAO.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B\u0011\u0012\b\b\u0002\u0010\u0019\u001a\u00020\u0018¢\u0006\u0004\b%\u0010&J0\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00052\b\u0010\b\u001a\u0004\u0018\u00010\u0007H\u0016J\u0018\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u0002H\u0016J\u0010\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u0002H\u0016J\"\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00120\u00142\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00070\u0012H\u0007J\b\u0010\u0017\u001a\u00020\u000eH\u0016R\u0014\u0010\u0019\u001a\u00020\u00188\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0019\u0010\u001aR\u001c\u0010\u001c\u001a\n \u001b*\u0004\u0018\u00010\u00070\u00078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001c\u0010\u001dR\u0019\u0010\u001f\u001a\u0004\u0018\u00010\u001e8\u0006¢\u0006\f\n\u0004\b\u001f\u0010 \u001a\u0004\b!\u0010\"R\u0014\u0010#\u001a\u00020\u00028\u0002X\u0082D¢\u0006\u0006\n\u0004\b#\u0010$¨\u0006'"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/datasource/dao/AmplifyPageModelDAO;", "Lcom/amazon/mp3/catalog/fragment/datasource/dao/EditablePageViewModelDao;", "", "pageIndex", "pageSize", "Lcom/amazon/mp3/catalog/fragment/viewmodel/FetchType;", "fetchType", "", "nextPageToken", "Lio/reactivex/Single;", "Lcom/amazon/music/views/library/models/PageGridViewModel;", "fetchViewModelItems", "from", "to", "", "reorderItem", "index", "deleteItem", "", "ids", "Lio/reactivex/Observable;", "Lcom/amazon/digitalmusicxp/models/QueueEntityMetadata;", "fetchItemsObservable", "clearItems", "", "isCarModePage", "Z", "kotlin.jvm.PlatformType", "TAG", "Ljava/lang/String;", "Lcom/amazon/mp3/cloudqueue/sequencer/CloudQueueSequencer;", "sequencer", "Lcom/amazon/mp3/cloudqueue/sequencer/CloudQueueSequencer;", "getSequencer", "()Lcom/amazon/mp3/cloudqueue/sequencer/CloudQueueSequencer;", "DEFAULT_PAGE_SIZE", "I", "<init>", "(Z)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class AmplifyPageModelDAO implements EditablePageViewModelDao {
    private final int DEFAULT_PAGE_SIZE;
    private final String TAG;
    private final boolean isCarModePage;
    private final CloudQueueSequencer sequencer;

    public AmplifyPageModelDAO() {
        this(false, 1, null);
    }

    public AmplifyPageModelDAO(boolean z) {
        this.isCarModePage = z;
        this.TAG = AmplifyPageModelDAO.class.getSimpleName();
        this.sequencer = CloudQueueSequencer.INSTANCE.getCloudQueueSequencer();
        this.DEFAULT_PAGE_SIZE = 100;
    }

    public /* synthetic */ AmplifyPageModelDAO(boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z);
    }

    @Override // com.amazon.mp3.catalog.fragment.datasource.dao.EditablePageViewModelDao, com.amazon.mp3.catalog.fragment.datasource.dao.PageViewModelDao
    public int getDefaultPageSize() {
        return EditablePageViewModelDao.DefaultImpls.getDefaultPageSize(this);
    }

    @Override // com.amazon.mp3.catalog.fragment.datasource.dao.PageViewModelDao
    public Single<PageGridViewModel> fetchViewModelItems(final int pageIndex, int pageSize, FetchType fetchType, String nextPageToken) {
        List<EntityReference> entityReferences;
        Intrinsics.checkNotNullParameter(fetchType, "fetchType");
        if (this.sequencer == null) {
            Log.warning(this.TAG, "fetchViewModelItems: sequencer is not initialized");
            Single<PageGridViewModel> singleCreate = Single.create(new SingleOnSubscribe() { // from class: com.amazon.mp3.catalog.fragment.datasource.dao.AmplifyPageModelDAO$$ExternalSyntheticLambda0
                @Override // io.reactivex.SingleOnSubscribe
                public final void subscribe(SingleEmitter singleEmitter) {
                    Intrinsics.checkNotNullParameter(singleEmitter, "it");
                }
            });
            Intrinsics.checkNotNullExpressionValue(singleCreate, "create {}");
            return singleCreate;
        }
        QueueSequenceSlice currentPlayingLocalQueueSequenceSlice = CloudQueueSequencer.INSTANCE.getCurrentPlayingLocalQueueSequenceSlice();
        ArrayList arrayList = null;
        if (currentPlayingLocalQueueSequenceSlice != null && (entityReferences = currentPlayingLocalQueueSequenceSlice.getEntityReferences()) != null) {
            arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(entityReferences, 10));
            Iterator<T> it = entityReferences.iterator();
            while (it.hasNext()) {
                arrayList.add(((EntityReference) it.next()).getIdentifier());
            }
        }
        if (arrayList == null) {
            Single<PageGridViewModel> singleCreate2 = Single.create(new SingleOnSubscribe() { // from class: com.amazon.mp3.catalog.fragment.datasource.dao.AmplifyPageModelDAO$$ExternalSyntheticLambda1
                @Override // io.reactivex.SingleOnSubscribe
                public final void subscribe(SingleEmitter singleEmitter) {
                    Intrinsics.checkNotNullParameter(singleEmitter, "it");
                }
            });
            Intrinsics.checkNotNullExpressionValue(singleCreate2, "create {}");
            return singleCreate2;
        }
        int i = this.DEFAULT_PAGE_SIZE;
        int i2 = pageIndex * i;
        int iMin = Math.min((pageIndex + 1) * i, Math.min((this.sequencer.getIsOnlineInteraction() && CloudQueueSequencer.INSTANCE.hideUpcomingItem()) ? this.sequencer.getMaxStationItems() : arrayList.size(), arrayList.size()));
        if (i2 < iMin) {
            List<String> listSubList = arrayList.subList(i2, iMin);
            Map<String, QueueEntityMetadata> localEntityMap = this.sequencer.getLocalEntityMap();
            if (listSubList.size() <= localEntityMap.keySet().size()) {
                final ArrayList arrayList2 = new ArrayList();
                Iterator<T> it2 = listSubList.iterator();
                while (it2.hasNext()) {
                    QueueEntityMetadata queueEntityMetadata = localEntityMap.get((String) it2.next());
                    if (queueEntityMetadata != null) {
                        arrayList2.add(queueEntityMetadata);
                    }
                }
                Single<PageGridViewModel> singleCreate3 = Single.create(new SingleOnSubscribe() { // from class: com.amazon.mp3.catalog.fragment.datasource.dao.AmplifyPageModelDAO$$ExternalSyntheticLambda3
                    @Override // io.reactivex.SingleOnSubscribe
                    public final void subscribe(SingleEmitter singleEmitter) {
                        AmplifyPageModelDAO.m546fetchViewModelItems$lambda5(arrayList2, pageIndex, this, singleEmitter);
                    }
                });
                Intrinsics.checkNotNullExpressionValue(singleCreate3, "create { emitter ->\n    …dViewModel)\n            }");
                return singleCreate3;
            }
            if (!(listSubList.isEmpty())) {
                Single<PageGridViewModel> singleSingleOrError = fetchItemsObservable(listSubList).subscribeOn(Schedulers.io()).map(new Function() { // from class: com.amazon.mp3.catalog.fragment.datasource.dao.AmplifyPageModelDAO$$ExternalSyntheticLambda4
                    @Override // io.reactivex.functions.Function
                    public final Object apply(Object obj) {
                        return AmplifyPageModelDAO.m547fetchViewModelItems$lambda6(pageIndex, this, (List) obj);
                    }
                }).singleOrError();
                Intrinsics.checkNotNullExpressionValue(singleSingleOrError, "{\n                fetchI…leOrError()\n            }");
                return singleSingleOrError;
            }
            Single<PageGridViewModel> singleCreate4 = Single.create(new SingleOnSubscribe() { // from class: com.amazon.mp3.catalog.fragment.datasource.dao.AmplifyPageModelDAO$$ExternalSyntheticLambda5
                @Override // io.reactivex.SingleOnSubscribe
                public final void subscribe(SingleEmitter singleEmitter) {
                    Intrinsics.checkNotNullParameter(singleEmitter, "it");
                }
            });
            Intrinsics.checkNotNullExpressionValue(singleCreate4, "{\n                Single.create {}\n            }");
            return singleCreate4;
        }
        Single<PageGridViewModel> singleCreate5 = Single.create(new SingleOnSubscribe() { // from class: com.amazon.mp3.catalog.fragment.datasource.dao.AmplifyPageModelDAO$$ExternalSyntheticLambda2
            @Override // io.reactivex.SingleOnSubscribe
            public final void subscribe(SingleEmitter singleEmitter) {
                Intrinsics.checkNotNullParameter(singleEmitter, "it");
            }
        });
        Intrinsics.checkNotNullExpressionValue(singleCreate5, "create { }");
        return singleCreate5;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: fetchViewModelItems$lambda-5, reason: not valid java name */
    public static final void m546fetchViewModelItems$lambda5(List metadataList, int i, AmplifyPageModelDAO this$0, SingleEmitter emitter) {
        Intrinsics.checkNotNullParameter(metadataList, "$metadataList");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(emitter, "emitter");
        emitter.onSuccess(CloudQueuePlaybackUtil.INSTANCE.convertQueueEntitiesIntoViewModel(metadataList, i, this$0.DEFAULT_PAGE_SIZE, this$0.isCarModePage));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: fetchViewModelItems$lambda-6, reason: not valid java name */
    public static final PageGridViewModel m547fetchViewModelItems$lambda6(int i, AmplifyPageModelDAO this$0, List it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        return CloudQueuePlaybackUtil.INSTANCE.convertQueueEntitiesIntoViewModel(it, i, this$0.DEFAULT_PAGE_SIZE, this$0.isCarModePage);
    }

    @Override // com.amazon.mp3.catalog.fragment.datasource.dao.EditablePageViewModelDao
    public void reorderItem(int from, int to) {
        CloudQueueSequencer cloudQueueSequencer = this.sequencer;
        if (cloudQueueSequencer == null) {
            return;
        }
        cloudQueueSequencer.move(from, to);
    }

    @Override // com.amazon.mp3.catalog.fragment.datasource.dao.EditablePageViewModelDao
    public void deleteItem(int index) {
        CloudQueueSequencer cloudQueueSequencer = this.sequencer;
        if (cloudQueueSequencer == null) {
            return;
        }
        cloudQueueSequencer.removeTracks(index, false);
    }

    @VisibleForTesting
    public final Observable<List<QueueEntityMetadata>> fetchItemsObservable(final List<String> ids) {
        Intrinsics.checkNotNullParameter(ids, "ids");
        Observable observableConcatMap = CloudQueueClientSingleton.INSTANCE.getCloudQueueClientObservable().concatMap(new Function() { // from class: com.amazon.mp3.catalog.fragment.datasource.dao.AmplifyPageModelDAO$$ExternalSyntheticLambda6
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return AmplifyPageModelDAO.m540fetchItemsObservable$lambda17(ids, this, (CloudQueueClient) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(observableConcatMap, "CloudQueueClientSingleto…              }\n        }");
        return observableConcatMap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: fetchItemsObservable$lambda-17, reason: not valid java name */
    public static final ObservableSource m540fetchItemsObservable$lambda17(List ids, final AmplifyPageModelDAO this$0, final CloudQueueClient cloudQueueClient) {
        Intrinsics.checkNotNullParameter(ids, "$ids");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(cloudQueueClient, "cloudQueueClient");
        return Observable.just(ids).concatMap(new Function() { // from class: com.amazon.mp3.catalog.fragment.datasource.dao.AmplifyPageModelDAO$$ExternalSyntheticLambda7
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return AmplifyPageModelDAO.m541fetchItemsObservable$lambda17$lambda16(cloudQueueClient, this$0, (List) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: fetchItemsObservable$lambda-17$lambda-16, reason: not valid java name */
    public static final ObservableSource m541fetchItemsObservable$lambda17$lambda16(final CloudQueueClient cloudQueueClient, final AmplifyPageModelDAO this$0, final List ids) {
        Intrinsics.checkNotNullParameter(cloudQueueClient, "$cloudQueueClient");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(ids, "ids");
        return Observable.create(new ObservableOnSubscribe() { // from class: com.amazon.mp3.catalog.fragment.datasource.dao.AmplifyPageModelDAO$$ExternalSyntheticLambda8
            @Override // io.reactivex.ObservableOnSubscribe
            public final void subscribe(ObservableEmitter observableEmitter) {
                AmplifyPageModelDAO.m542fetchItemsObservable$lambda17$lambda16$lambda15(cloudQueueClient, ids, this$0, observableEmitter);
            }
        }).subscribeOn(Schedulers.computation()).observeOn(Schedulers.computation());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: fetchItemsObservable$lambda-17$lambda-16$lambda-15, reason: not valid java name */
    public static final void m542fetchItemsObservable$lambda17$lambda16$lambda15(CloudQueueClient cloudQueueClient, List ids, AmplifyPageModelDAO this$0, ObservableEmitter observableEmitter) {
        Intrinsics.checkNotNullParameter(cloudQueueClient, "$cloudQueueClient");
        Intrinsics.checkNotNullParameter(ids, "$ids");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(observableEmitter, "observableEmitter");
        Outcome<List<QueueEntityMetadata>> queueEntityById = cloudQueueClient.getQueueEntityById(ids);
        if (queueEntityById instanceof Outcome.Success) {
            Outcome.Success success = (Outcome.Success) queueEntityById;
            if (((List) success.getValue()).size() == ids.size()) {
                observableEmitter.onNext(success.getValue());
                observableEmitter.onComplete();
                return;
            }
        }
        ArrayList arrayList = new ArrayList();
        CloudQueueSequencer.Companion companion = CloudQueueSequencer.INSTANCE;
        CloudQueueSequencer cloudQueueSequencer = companion.getCloudQueueSequencer();
        Map<String, QueueEntityMetadata> entityMap = cloudQueueSequencer == null ? null : cloudQueueSequencer.getEntityMap();
        CloudQueueSequencer cloudQueueSequencer2 = companion.getCloudQueueSequencer();
        Map<String, QueueEntityMetadata> localEntityMap = cloudQueueSequencer2 == null ? null : cloudQueueSequencer2.getLocalEntityMap();
        Iterator it = ids.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            QueueEntityMetadata queueEntityMetadata = entityMap == null ? null : entityMap.get(str);
            if (queueEntityMetadata != null) {
                arrayList.add(queueEntityMetadata);
            } else {
                QueueEntityMetadata queueEntityMetadata2 = localEntityMap == null ? null : localEntityMap.get(str);
                if (queueEntityMetadata2 != null) {
                    arrayList.add(queueEntityMetadata2);
                } else {
                    Log.error(this$0.TAG, Intrinsics.stringPlus("Failed to get queue entity with id ", str));
                }
            }
        }
        observableEmitter.onNext(arrayList);
        observableEmitter.onComplete();
    }

    @Override // com.amazon.mp3.catalog.fragment.datasource.dao.EditablePageViewModelDao
    public void clearItems() {
        CloudQueueSequencer cloudQueueSequencer = this.sequencer;
        if (cloudQueueSequencer == null) {
            return;
        }
        cloudQueueSequencer.clearQueue();
    }
}
