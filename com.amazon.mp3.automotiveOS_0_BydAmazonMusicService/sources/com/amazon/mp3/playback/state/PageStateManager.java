package com.amazon.mp3.playback.state;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import com.amazon.music.views.library.metadata.AlbumMetadata;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.metadata.PlaylistMetadata;
import com.amazon.music.views.library.metadata.PodcastMetadata;
import com.amazon.music.views.library.metadata.RecentsMetadata;
import com.amazon.music.views.library.metadata.StationMetadata;
import com.amazon.music.views.library.metadata.TrackMetadata;
import com.amazon.music.views.library.metadata.UserPlaylistMetadata;
import com.amazon.music.views.library.models.PageGridViewModel;
import com.amazon.music.views.library.models.PodcastFeaturePlayModel;
import com.amazon.music.views.library.models.SimpleHorizontalTileModel;
import com.amazon.music.views.library.models.SimpleVerticalTileModel;
import com.amazon.music.views.library.models.base.BaseViewContainerModel;
import com.amazon.music.views.library.models.base.BaseViewContentModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.podcast.Podcast;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CompletableJob;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

/* JADX INFO: compiled from: PageStateManager.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000´\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0016\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\bN\u0010OJ\u001e\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0002J(\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\b2\u0016\u0010\r\u001a\u0012\u0012\u0004\u0012\u00020\u000b0\nj\b\u0012\u0004\u0012\u00020\u000b`\fH\u0002J\u0010\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u000bH\u0002J\u0012\u0010\u0014\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0013\u001a\u00020\u0012H\u0002J \u0001\u0010%\u001a\u00020\u000e\"\u0004\b\u0000\u0010\u0015\"\u0004\b\u0001\u0010\u00162\u0006\u0010\u0018\u001a\u00020\u00172\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\b0\u00192\u0006\u0010\u001b\u001a\u00028\u00002\u0006\u0010\u001c\u001a\u00028\u00012K\u0010\"\u001aG\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b( \u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b(\u001b\u0012\u0013\u0012\u00118\u0001¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b(\u001c\u0012\u0004\u0012\u00020!0\u001d2\u0010\b\u0002\u0010$\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010#H\u0002¢\u0006\u0004\b%\u0010&J\u0014\u0010)\u001a\u00020\u000e2\f\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00020'J\u0006\u0010*\u001a\u00020\u000eJ\u0016\u0010-\u001a\u00020\u000e2\f\u0010,\u001a\b\u0012\u0004\u0012\u00020+0\u0004H\u0016J\u0012\u0010.\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0004J\u0018\u00100\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u00052\b\u0010/\u001a\u0004\u0018\u00010\u0006J\u0012\u00101\u001a\u0004\u0018\u00010\u00122\b\u0010/\u001a\u0004\u0018\u00010\u0006J)\u00104\u001a\u00020\u000e2!\u00103\u001a\u001d\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b( \u0012\u0004\u0012\u00020\u000e02J)\u00105\u001a\u00020\u000e2!\u00103\u001a\u001d\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b( \u0012\u0004\u0012\u00020\u000e02J7\u00105\u001a\u00020\u000e2\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\b0\u00192!\u00103\u001a\u001d\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b( \u0012\u0004\u0012\u00020\u000e02J-\u00107\u001a\u00020\u000e2%\u00103\u001a!\u0012\u0017\u0012\u0015\u0012\u0002\b\u000306¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b( \u0012\u0004\u0012\u00020\u000e02J\u0088\u0001\u0010%\u001a\u00020\u000e\"\u0004\b\u0000\u0010\u0015\"\u0004\b\u0001\u0010\u00162\u0006\u0010\u001b\u001a\u00028\u00002\u0006\u0010\u001c\u001a\u00028\u00012K\u0010\"\u001aG\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b( \u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b(\u001b\u0012\u0013\u0012\u00118\u0001¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b(\u001c\u0012\u0004\u0012\u00020!0\u001d2\u0010\b\u0002\u0010$\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010#¢\u0006\u0004\b%\u00108J\u0088\u0001\u00109\u001a\u00020\u000e\"\u0004\b\u0000\u0010\u0015\"\u0004\b\u0001\u0010\u00162\u0006\u0010\u001b\u001a\u00028\u00002\u0006\u0010\u001c\u001a\u00028\u00012K\u0010\"\u001aG\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b( \u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b(\u001b\u0012\u0013\u0012\u00118\u0001¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b(\u001c\u0012\u0004\u0012\u00020\u000e0\u001d2\u0010\b\u0002\u0010$\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010#¢\u0006\u0004\b9\u00108J\u0018\u0010<\u001a\u00020\u000e2\u0006\u0010 \u001a\u00020\u000b2\u0006\u0010;\u001a\u00020:H\u0016R\u0014\u0010>\u001a\u00020=8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b>\u0010?R\u0014\u0010@\u001a\u00020\u00178\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b@\u0010ARP\u0010D\u001a>\u0012\u0004\u0012\u00020\u0006\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u000b0\nj\b\u0012\u0004\u0012\u00020\u000b`\f0Bj\u001e\u0012\u0004\u0012\u00020\u0006\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u000b0\nj\b\u0012\u0004\u0012\u00020\u000b`\f`C8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bD\u0010ERP\u0010H\u001a>\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00020\u0006 G*\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00050\u0005 G*\u001e\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00020\u0006 G*\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00050\u0005\u0018\u00010F0F8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bH\u0010IR\u001e\u0010(\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010'8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b(\u0010JR\u001a\u0010L\u001a\b\u0012\u0004\u0012\u00020\u00020K8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bL\u0010M¨\u0006P"}, d2 = {"Lcom/amazon/mp3/playback/state/PageStateManager;", "", "Lcom/amazon/music/views/library/models/PageGridViewModel;", "pageModel", "Lrx/Observable;", "", "", "storeCurrentState", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "baseModel", "Ljava/util/HashSet;", "Lcom/amazon/music/views/library/models/base/BaseViewContentModel;", "Lkotlin/collections/HashSet;", "contentModels", "", "saveAllContentModels", "contentModel", "addToModelMap", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "metadata", "getKeyForMetadata", "T", "S", "Lkotlinx/coroutines/CoroutineScope;", "coroutineScope", "", "models", "itemToCompare", "newState", "Lkotlin/Function3;", "Lkotlin/ParameterName;", "name", "model", "", "updateCheck", "Lkotlin/Function0;", "callback", "updateModelState", "(Lkotlinx/coroutines/CoroutineScope;Ljava/util/List;Ljava/lang/Object;Ljava/lang/Object;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function0;)V", "Landroidx/lifecycle/MutableLiveData;", "pageData", "observePageData", "onCleared", "Ljava/lang/Void;", "observable", "observeOnCleared", "getCachedIds", "id", "getStoredModelsForId", "getMetadataForId", "Lkotlin/Function1;", "performAction", "performActionPerContentModel", "performActionPerModel", "Lcom/amazon/music/views/library/models/base/BaseViewContainerModel;", "performActionPerContainerModel", "(Ljava/lang/Object;Ljava/lang/Object;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function0;)V", "updateContentModelState", "", "playState", "updatePodcastPlayState", "Lkotlinx/coroutines/CompletableJob;", "equalizerJob", "Lkotlinx/coroutines/CompletableJob;", "defaultScope", "Lkotlinx/coroutines/CoroutineScope;", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "mContentModelMap", "Ljava/util/HashMap;", "Lrx/subjects/PublishSubject;", "kotlin.jvm.PlatformType", "mStateMapSubject", "Lrx/subjects/PublishSubject;", "Landroidx/lifecycle/MutableLiveData;", "Landroidx/lifecycle/Observer;", "pageDataObserver", "Landroidx/lifecycle/Observer;", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public class PageStateManager {
    private final CoroutineScope defaultScope;
    private final CompletableJob equalizerJob;
    private final HashMap<String, HashSet<BaseViewContentModel>> mContentModelMap;
    private final PublishSubject<Set<String>> mStateMapSubject;
    private MutableLiveData<PageGridViewModel> pageData;
    private final Observer<PageGridViewModel> pageDataObserver;

    public PageStateManager() {
        CompletableJob completableJobSupervisorJob$default = SupervisorKt.SupervisorJob$default((Job) null, 1, (Object) null);
        this.equalizerJob = completableJobSupervisorJob$default;
        this.defaultScope = CoroutineScopeKt.CoroutineScope(Dispatchers.getDefault().plus(completableJobSupervisorJob$default));
        this.mContentModelMap = new HashMap<>();
        this.mStateMapSubject = PublishSubject.create();
        this.pageDataObserver = new Observer() { // from class: com.amazon.mp3.playback.state.PageStateManager$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                PageStateManager.m1498pageDataObserver$lambda1(this.f$0, (PageGridViewModel) obj);
            }
        };
    }

    public static final /* synthetic */ MutableLiveData access$getPageData$p(PageStateManager pageStateManager) {
        return pageStateManager.pageData;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: pageDataObserver$lambda-1, reason: not valid java name */
    public static final void m1498pageDataObserver$lambda1(final PageStateManager this$0, PageGridViewModel pageGridViewModel) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.storeCurrentState(pageGridViewModel).subscribe(new Action1() { // from class: com.amazon.mp3.playback.state.PageStateManager$$ExternalSyntheticLambda2
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                PageStateManager.m1499pageDataObserver$lambda1$lambda0(this.f$0, (Set) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: pageDataObserver$lambda-1$lambda-0, reason: not valid java name */
    public static final void m1499pageDataObserver$lambda1$lambda0(PageStateManager this$0, Set set) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.mStateMapSubject.onNext(set);
    }

    public final void observePageData(MutableLiveData<PageGridViewModel> pageData) {
        Intrinsics.checkNotNullParameter(pageData, "pageData");
        this.pageData = pageData;
        pageData.observeForever(this.pageDataObserver);
    }

    public final void onCleared() {
        MutableLiveData<PageGridViewModel> mutableLiveData = this.pageData;
        if (mutableLiveData == null) {
            return;
        }
        mutableLiveData.removeObserver(this.pageDataObserver);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: observeOnCleared$lambda-2, reason: not valid java name */
    public static final void m1497observeOnCleared$lambda2(PageStateManager this$0, Void r1) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        MutableLiveData<PageGridViewModel> mutableLiveData = this$0.pageData;
        if (mutableLiveData == null) {
            return;
        }
        mutableLiveData.removeObserver(this$0.pageDataObserver);
    }

    public void observeOnCleared(Observable<Void> observable) {
        Intrinsics.checkNotNullParameter(observable, "observable");
        observable.subscribe(new Action1() { // from class: com.amazon.mp3.playback.state.PageStateManager$$ExternalSyntheticLambda1
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                PageStateManager.m1497observeOnCleared$lambda2(this.f$0, (Void) obj);
            }
        });
    }

    public final Observable<Set<String>> getCachedIds() {
        Observable<Set<String>> observableSubscribeOn = this.mStateMapSubject.observeOn(Schedulers.io()).subscribeOn(Schedulers.io());
        Intrinsics.checkNotNullExpressionValue(observableSubscribeOn, "mStateMapSubject.observe…scribeOn(Schedulers.io())");
        return observableSubscribeOn;
    }

    public final Set<BaseViewContentModel> getStoredModelsForId(String id) {
        if (id == null) {
            return null;
        }
        return this.mContentModelMap.get(id);
    }

    public final ContentMetadata getMetadataForId(String id) {
        BaseViewContentModel baseViewContentModel;
        Set<BaseViewContentModel> storedModelsForId = getStoredModelsForId(id);
        if (storedModelsForId == null || (baseViewContentModel = (BaseViewContentModel) CollectionsKt___CollectionsKt.elementAt(storedModelsForId, 0)) == null) {
            return null;
        }
        return baseViewContentModel.getMetadata();
    }

    private final Observable<Set<String>> storeCurrentState(final PageGridViewModel pageModel) {
        Observable<Set<String>> observableCreate = Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.mp3.playback.state.PageStateManager$$ExternalSyntheticLambda3
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                PageStateManager.m1500storeCurrentState$lambda3(pageModel, this, (Subscriber) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(observableCreate, "create { subscriber ->\n …tModelMap.keys)\n        }");
        return observableCreate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: storeCurrentState$lambda-3, reason: not valid java name */
    public static final void m1500storeCurrentState$lambda3(PageGridViewModel pageGridViewModel, PageStateManager this$0, Subscriber subscriber) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (pageGridViewModel == null) {
            this$0.mContentModelMap.clear();
        } else {
            HashSet<BaseViewContentModel> hashSet = new HashSet<>();
            Iterator<BaseViewModel> it = pageGridViewModel.getModels().iterator();
            while (it.hasNext()) {
                this$0.saveAllContentModels(it.next(), hashSet);
            }
            for (BaseViewContentModel contentModel : hashSet) {
                Intrinsics.checkNotNullExpressionValue(contentModel, "contentModel");
                this$0.addToModelMap(contentModel);
            }
        }
        subscriber.onNext(this$0.mContentModelMap.keySet());
    }

    private final void saveAllContentModels(BaseViewModel baseModel, HashSet<BaseViewContentModel> contentModels) {
        if (baseModel instanceof BaseViewContainerModel) {
            List models = ((BaseViewContainerModel) baseModel).getModels();
            ArrayList arrayList = new ArrayList();
            for (Object obj : models) {
                if (obj instanceof BaseViewModel) {
                    arrayList.add(obj);
                }
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                saveAllContentModels((BaseViewModel) it.next(), contentModels);
            }
            return;
        }
        if (baseModel instanceof BaseViewContentModel) {
            contentModels.add(baseModel);
        }
    }

    private final void addToModelMap(BaseViewContentModel contentModel) {
        HashSet<BaseViewContentModel> hashSet;
        String keyForMetadata = getKeyForMetadata(contentModel.getMetadata());
        if (keyForMetadata == null) {
            return;
        }
        if (!this.mContentModelMap.containsKey(keyForMetadata)) {
            hashSet = new HashSet<>();
        } else {
            HashSet<BaseViewContentModel> hashSet2 = this.mContentModelMap.get(keyForMetadata);
            Intrinsics.checkNotNull(hashSet2);
            Intrinsics.checkNotNullExpressionValue(hashSet2, "{\n                    mC…p[it]!!\n                }");
            hashSet = hashSet2;
        }
        hashSet.add(contentModel);
        this.mContentModelMap.put(keyForMetadata, hashSet);
    }

    private final String getKeyForMetadata(ContentMetadata metadata) {
        if (metadata instanceof StationMetadata) {
            return ((StationMetadata) metadata).getKey();
        }
        if (metadata instanceof TrackMetadata) {
            return ((TrackMetadata) metadata).getAsin();
        }
        if (metadata instanceof AlbumMetadata) {
            return ((AlbumMetadata) metadata).getAsin();
        }
        if (metadata instanceof PlaylistMetadata) {
            return ((PlaylistMetadata) metadata).getAsin();
        }
        if (metadata instanceof UserPlaylistMetadata) {
            return ((UserPlaylistMetadata) metadata).getPlaylistId();
        }
        if (metadata instanceof RecentsMetadata) {
            return ((RecentsMetadata) metadata).getCollectionAsin();
        }
        return null;
    }

    public final void performActionPerContentModel(Function1<? super BaseViewContentModel, Unit> performAction) {
        PageGridViewModel value;
        List<BaseViewModel> models;
        Intrinsics.checkNotNullParameter(performAction, "performAction");
        MutableLiveData<PageGridViewModel> mutableLiveData = this.pageData;
        Iterator<BaseViewModel> it = null;
        if (mutableLiveData != null && (value = mutableLiveData.getValue()) != null && (models = value.getModels()) != null) {
            it = models.iterator();
        }
        if (it == null) {
            return;
        }
        while (it.hasNext()) {
            BaseViewModel next = it.next();
            if (next instanceof BaseViewContainerModel) {
                Iterator it2 = ((BaseViewContainerModel) next).getModels().iterator();
                if (it2 != null) {
                    while (it2.hasNext()) {
                        BaseViewModel baseViewModel = (BaseViewModel) it2.next();
                        if (baseViewModel instanceof BaseViewContentModel) {
                            performAction.invoke(baseViewModel);
                        }
                    }
                }
            } else if (next instanceof BaseViewContentModel) {
                performAction.invoke(next);
            }
        }
    }

    public final void performActionPerModel(Function1<? super BaseViewModel, Unit> performAction) {
        PageGridViewModel value;
        List<BaseViewModel> models;
        Intrinsics.checkNotNullParameter(performAction, "performAction");
        MutableLiveData<PageGridViewModel> mutableLiveData = this.pageData;
        if (mutableLiveData == null || (value = mutableLiveData.getValue()) == null || (models = value.getModels()) == null) {
            return;
        }
        performActionPerModel(models, performAction);
    }

    public final void performActionPerModel(List<? extends BaseViewModel> models, Function1<? super BaseViewModel, Unit> performAction) {
        Intrinsics.checkNotNullParameter(models, "models");
        Intrinsics.checkNotNullParameter(performAction, "performAction");
        for (BaseViewModel baseViewModel : models) {
            if (baseViewModel instanceof BaseViewContainerModel) {
                performActionPerModel(((BaseViewContainerModel) baseViewModel).getModels(), performAction);
            } else if (baseViewModel instanceof BaseViewModel) {
                performAction.invoke(baseViewModel);
            }
        }
    }

    public final void performActionPerContainerModel(Function1<? super BaseViewContainerModel<?>, Unit> performAction) {
        PageGridViewModel value;
        List<BaseViewModel> models;
        Intrinsics.checkNotNullParameter(performAction, "performAction");
        MutableLiveData<PageGridViewModel> mutableLiveData = this.pageData;
        Iterator<BaseViewModel> it = null;
        if (mutableLiveData != null && (value = mutableLiveData.getValue()) != null && (models = value.getModels()) != null) {
            it = models.iterator();
        }
        if (it == null) {
            return;
        }
        while (it.hasNext()) {
            BaseViewModel next = it.next();
            if (next instanceof BaseViewContainerModel) {
                performAction.invoke(next);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void updateModelState$default(PageStateManager pageStateManager, Object obj, Object obj2, Function3 function3, Function0 function0, int i, Object obj3) {
        if (obj3 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: updateModelState");
        }
        if ((i & 8) != 0) {
            function0 = null;
        }
        pageStateManager.updateModelState(obj, obj2, function3, function0);
    }

    /* JADX INFO: renamed from: com.amazon.mp3.playback.state.PageStateManager$updateModelState$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: PageStateManager.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0004\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\u00020\u0002H\u008a@"}, d2 = {"T", "S", "Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.mp3.playback.state.PageStateManager$updateModelState$1", f = "PageStateManager.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C04011 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function0<Unit> $callback;
        final /* synthetic */ T $itemToCompare;
        final /* synthetic */ S $newState;
        final /* synthetic */ Function3<BaseViewModel, T, S, Boolean> $updateCheck;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C04011(T t, S s, Function3<? super BaseViewModel, ? super T, ? super S, Boolean> function3, Function0<Unit> function0, Continuation<? super C04011> continuation) {
            super(2, continuation);
            this.$itemToCompare = t;
            this.$newState = s;
            this.$updateCheck = function3;
            this.$callback = function0;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C04011 c04011 = PageStateManager.this.new C04011(this.$itemToCompare, this.$newState, this.$updateCheck, this.$callback, continuation);
            c04011.L$0 = obj;
            return c04011;
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C04011) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            PageGridViewModel pageGridViewModel;
            List<BaseViewModel> models;
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            MutableLiveData mutableLiveData = PageStateManager.this.pageData;
            if (mutableLiveData != null && (pageGridViewModel = (PageGridViewModel) mutableLiveData.getValue()) != null && (models = pageGridViewModel.getModels()) != null) {
                PageStateManager.this.updateModelState(coroutineScope, models, this.$itemToCompare, this.$newState, this.$updateCheck, this.$callback);
            }
            return Unit.INSTANCE;
        }
    }

    public final <T, S> void updateModelState(T itemToCompare, S newState, Function3<? super BaseViewModel, ? super T, ? super S, Boolean> updateCheck, Function0<Unit> callback) {
        Intrinsics.checkNotNullParameter(updateCheck, "updateCheck");
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getDefault(), null, new C04011(itemToCompare, newState, updateCheck, callback, null), 2, null);
    }

    /* JADX INFO: renamed from: com.amazon.mp3.playback.state.PageStateManager$updateContentModelState$1, reason: invalid class name */
    /* JADX INFO: compiled from: PageStateManager.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0004\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\u00020\u0002H\u008a@"}, d2 = {"T", "S", "Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.mp3.playback.state.PageStateManager$updateContentModelState$1", f = "PageStateManager.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function0<Unit> $callback;
        final /* synthetic */ T $itemToCompare;
        final /* synthetic */ S $newState;
        final /* synthetic */ Function3<BaseViewContentModel, T, S, Unit> $updateCheck;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass1(Function3<? super BaseViewContentModel, ? super T, ? super S, Unit> function3, T t, S s, Function0<Unit> function0, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$updateCheck = function3;
            this.$itemToCompare = t;
            this.$newState = s;
            this.$callback = function0;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = PageStateManager.this.new AnonymousClass1(this.$updateCheck, this.$itemToCompare, this.$newState, this.$callback, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference fix 'apply assigned field type' failed
        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
         */
        /*  JADX ERROR: JadxRuntimeException in pass: FinishTypeInference
            jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r6v5 boolean
            	at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:236)
            	at jadx.core.dex.visitors.typeinference.FinishTypeInference.lambda$visit$0(FinishTypeInference.java:27)
            	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
            	at jadx.core.dex.visitors.typeinference.FinishTypeInference.visit(FinishTypeInference.java:22)
            */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final java.lang.Object invokeSuspend(java.lang.Object r9) {
            /*
                r8 = this;
                kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r0 = r8.label
                if (r0 != 0) goto L85
                kotlin.ResultKt.throwOnFailure(r9)
                java.lang.Object r9 = r8.L$0
                r0 = r9
                kotlinx.coroutines.CoroutineScope r0 = (kotlinx.coroutines.CoroutineScope) r0
                com.amazon.mp3.playback.state.PageStateManager r9 = com.amazon.mp3.playback.state.PageStateManager.this
                androidx.lifecycle.MutableLiveData r9 = com.amazon.mp3.playback.state.PageStateManager.access$getPageData$p(r9)
                r1 = 0
                if (r9 != 0) goto L1a
            L18:
                r9 = r1
                goto L2e
            L1a:
                java.lang.Object r9 = r9.getValue()
                com.amazon.music.views.library.models.PageGridViewModel r9 = (com.amazon.music.views.library.models.PageGridViewModel) r9
                if (r9 != 0) goto L23
                goto L18
            L23:
                java.util.List r9 = r9.getModels()
                if (r9 != 0) goto L2a
                goto L18
            L2a:
                java.util.Iterator r9 = r9.iterator()
            L2e:
                if (r9 != 0) goto L31
                goto L70
            L31:
                kotlin.jvm.functions.Function3<com.amazon.music.views.library.models.base.BaseViewContentModel, T, S, kotlin.Unit> r2 = r8.$updateCheck
                T r3 = r8.$itemToCompare
                S r4 = r8.$newState
            L37:
                boolean r5 = r9.hasNext()
                if (r5 == 0) goto L70
                java.lang.Object r5 = r9.next()
                com.amazon.music.views.library.models.base.BaseViewModel r5 = (com.amazon.music.views.library.models.base.BaseViewModel) r5
                boolean r6 = r5 instanceof com.amazon.music.views.library.models.base.BaseViewContainerModel
                if (r6 == 0) goto L68
                com.amazon.music.views.library.models.base.BaseViewContainerModel r5 = (com.amazon.music.views.library.models.base.BaseViewContainerModel) r5
                java.util.List r5 = r5.getModels()
                java.util.Iterator r5 = r5.iterator()
                if (r5 != 0) goto L54
                goto L37
            L54:
                boolean r6 = r5.hasNext()
                if (r6 == 0) goto L37
                java.lang.Object r6 = r5.next()
                com.amazon.music.views.library.models.base.BaseViewModel r6 = (com.amazon.music.views.library.models.base.BaseViewModel) r6
                boolean r7 = r6 instanceof com.amazon.music.views.library.models.base.BaseViewContentModel
                if (r7 == 0) goto L54
                r2.invoke(r6, r3, r4)
                goto L54
            L68:
                boolean r6 = r5 instanceof com.amazon.music.views.library.models.base.BaseViewContentModel
                if (r6 == 0) goto L37
                r2.invoke(r5, r3, r4)
                goto L37
            L70:
                kotlinx.coroutines.MainCoroutineDispatcher r9 = kotlinx.coroutines.Dispatchers.getMain()
                r2 = 0
                com.amazon.mp3.playback.state.PageStateManager$updateContentModelState$1$2 r3 = new com.amazon.mp3.playback.state.PageStateManager$updateContentModelState$1$2
                kotlin.jvm.functions.Function0<kotlin.Unit> r4 = r8.$callback
                r3.<init>(r4, r1)
                r4 = 2
                r5 = 0
                r1 = r9
                kotlinx.coroutines.BuildersKt.launch$default(r0, r1, r2, r3, r4, r5)
                kotlin.Unit r9 = kotlin.Unit.INSTANCE
                return r9
            L85:
                java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r9.<init>(r0)
                throw r9
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.playback.state.PageStateManager.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        /* JADX INFO: renamed from: com.amazon.mp3.playback.state.PageStateManager$updateContentModelState$1$2, reason: invalid class name */
        /* JADX INFO: compiled from: PageStateManager.kt */
        @Metadata(bv = {}, d1 = {"\u0000\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0004\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\u00020\u0002H\u008a@"}, d2 = {"T", "S", "Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
        @DebugMetadata(c = "com.amazon.mp3.playback.state.PageStateManager$updateContentModelState$1$2", f = "PageStateManager.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ Function0<Unit> $callback;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass2(Function0<Unit> function0, Continuation<? super AnonymousClass2> continuation) {
                super(2, continuation);
                this.$callback = function0;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass2(this.$callback, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
            public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                Function0<Unit> function0 = this.$callback;
                if (function0 != null) {
                    function0.invoke();
                }
                return Unit.INSTANCE;
            }
        }
    }

    public final <T, S> void updateContentModelState(T itemToCompare, S newState, Function3<? super BaseViewContentModel, ? super T, ? super S, Unit> updateCheck, Function0<Unit> callback) {
        Intrinsics.checkNotNullParameter(updateCheck, "updateCheck");
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getDefault(), null, new AnonymousClass1(updateCheck, itemToCompare, newState, callback, null), 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final <T, S> void updateModelState(CoroutineScope coroutineScope, List<? extends BaseViewModel> models, T itemToCompare, S newState, Function3<? super BaseViewModel, ? super T, ? super S, Boolean> updateCheck, Function0<Unit> callback) {
        for (BaseViewModel baseViewModel : models) {
            if (baseViewModel instanceof BaseViewContainerModel) {
                updateModelState(coroutineScope, ((BaseViewContainerModel) baseViewModel).getModels(), itemToCompare, newState, updateCheck, callback);
            } else if (updateCheck.invoke(baseViewModel, itemToCompare, newState).booleanValue()) {
                BuildersKt__Builders_commonKt.launch$default(coroutineScope, Dispatchers.getMain(), null, new PageStateManager$updateModelState$2$1(callback, null), 2, null);
                return;
            }
        }
    }

    public void updatePodcastPlayState(BaseViewContentModel model, int playState) {
        Intrinsics.checkNotNullParameter(model, "model");
        if ((model instanceof PodcastFeaturePlayModel) || (model instanceof SimpleHorizontalTileModel) || (model instanceof SimpleVerticalTileModel)) {
            ContentMetadata metadata = model.getMetadata();
            if (metadata instanceof PodcastMetadata) {
                PodcastMetadata podcastMetadata = (PodcastMetadata) metadata;
                if (Intrinsics.areEqual(podcastMetadata.getCatalogId(), Podcast.getMediaId()) || Intrinsics.areEqual(podcastMetadata.getCatalogId(), Podcast.getMediaCollectionId())) {
                    model.updatePlayState(playState);
                } else {
                    model.updatePlayState(1);
                }
            }
        }
    }
}
