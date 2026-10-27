package com.amazon.mp3.brush;

import com.amazon.mp3.playback.state.PageStateManager;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.metadata.TrackMetadata;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

/* JADX INFO: compiled from: LibraryStateUseCase.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\n\u001a\u00020\t¢\u0006\u0004\b\u0018\u0010\u0019J\b\u0010\u0003\u001a\u00020\u0002H\u0002J\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004J\u0016\u0010\b\u001a\u00020\u00022\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0016R\u0014\u0010\n\u001a\u00020\t8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\n\u0010\u000bR$\u0010\u000f\u001a\u0012\u0012\u0004\u0012\u00020\r0\fj\b\u0012\u0004\u0012\u00020\r`\u000e8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000f\u0010\u0010R\u0018\u0010\u0012\u001a\u0004\u0018\u00010\u00118\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0012\u0010\u0013R:\u0010\u0016\u001a&\u0012\f\u0012\n \u0015*\u0004\u0018\u00010\u00050\u0005 \u0015*\u0012\u0012\f\u0012\n \u0015*\u0004\u0018\u00010\u00050\u0005\u0018\u00010\u00140\u00148\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0016\u0010\u0017¨\u0006\u001a"}, d2 = {"Lcom/amazon/mp3/brush/LibraryStateUseCase;", "", "", "initializeModelSet", "Lrx/Observable;", "Ljava/lang/Void;", "observeLibraryStateInitialization", "observable", "observeOnCleared", "Lcom/amazon/mp3/playback/state/PageStateManager;", "mStateManager", "Lcom/amazon/mp3/playback/state/PageStateManager;", "Ljava/util/HashSet;", "", "Lkotlin/collections/HashSet;", "mLibraryStateModels", "Ljava/util/HashSet;", "Lrx/Subscription;", "mSubscription", "Lrx/Subscription;", "Lrx/subjects/PublishSubject;", "kotlin.jvm.PlatformType", "mLibraryModelsSubject", "Lrx/subjects/PublishSubject;", "<init>", "(Lcom/amazon/mp3/playback/state/PageStateManager;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class LibraryStateUseCase {
    private PublishSubject<Void> mLibraryModelsSubject;
    private final HashSet<String> mLibraryStateModels;
    private final PageStateManager mStateManager;
    private Subscription mSubscription;

    public LibraryStateUseCase(PageStateManager mStateManager) {
        Intrinsics.checkNotNullParameter(mStateManager, "mStateManager");
        this.mStateManager = mStateManager;
        this.mLibraryStateModels = new HashSet<>();
        this.mLibraryModelsSubject = PublishSubject.create();
        initializeModelSet();
    }

    public final Observable<Void> observeLibraryStateInitialization() {
        Observable<Void> observableSubscribeOn = this.mLibraryModelsSubject.observeOn(Schedulers.io()).subscribeOn(Schedulers.io());
        Intrinsics.checkNotNullExpressionValue(observableSubscribeOn, "mLibraryModelsSubject.ob…scribeOn(Schedulers.io())");
        return observableSubscribeOn;
    }

    public void observeOnCleared(Observable<Void> observable) {
        Intrinsics.checkNotNullParameter(observable, "observable");
        observable.subscribe(new Action1() { // from class: com.amazon.mp3.brush.LibraryStateUseCase$$ExternalSyntheticLambda3
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                LibraryStateUseCase.m440observeOnCleared$lambda1(this.f$0, (Void) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: observeOnCleared$lambda-1, reason: not valid java name */
    public static final void m440observeOnCleared$lambda1(LibraryStateUseCase this$0, Void r1) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Subscription subscription = this$0.mSubscription;
        if (subscription != null) {
            subscription.unsubscribe();
        }
        this$0.mSubscription = null;
    }

    private final void initializeModelSet() {
        final Ref.IntRef intRef = new Ref.IntRef();
        this.mSubscription = this.mStateManager.getCachedIds().observeOn(Schedulers.io()).subscribeOn(Schedulers.io()).flatMap(new Func1() { // from class: com.amazon.mp3.brush.LibraryStateUseCase$$ExternalSyntheticLambda0
            @Override // rx.functions.Func1
            public final Object call(Object obj) {
                return LibraryStateUseCase.m437initializeModelSet$lambda2(this.f$0, intRef, (Set) obj);
            }
        }).map(new Func1() { // from class: com.amazon.mp3.brush.LibraryStateUseCase$$ExternalSyntheticLambda1
            @Override // rx.functions.Func1
            public final Object call(Object obj) {
                return LibraryStateUseCase.m438initializeModelSet$lambda3(this.f$0, (String) obj);
            }
        }).subscribe(new Action1() { // from class: com.amazon.mp3.brush.LibraryStateUseCase$$ExternalSyntheticLambda2
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                LibraryStateUseCase.m439initializeModelSet$lambda4(this.f$0, intRef, (Pair) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initializeModelSet$lambda-2, reason: not valid java name */
    public static final Observable m437initializeModelSet$lambda2(LibraryStateUseCase this$0, Ref.IntRef idCount, Set set) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(idCount, "$idCount");
        if (!((set == null || set.isEmpty()) ? false : true)) {
            this$0.mLibraryStateModels.clear();
            idCount.element = 0;
            return Observable.from(new ArrayList());
        }
        idCount.element = set.size();
        return Observable.from(set);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initializeModelSet$lambda-3, reason: not valid java name */
    public static final Pair m438initializeModelSet$lambda3(LibraryStateUseCase this$0, String str) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        return new Pair(str, this$0.mStateManager.getMetadataForId(str));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initializeModelSet$lambda-4, reason: not valid java name */
    public static final void m439initializeModelSet$lambda4(LibraryStateUseCase this$0, Ref.IntRef idCount, Pair pair) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(idCount, "$idCount");
        Intrinsics.checkNotNullParameter(pair, "pair");
        if (((ContentMetadata) pair.getSecond()) instanceof TrackMetadata) {
            HashSet<String> hashSet = this$0.mLibraryStateModels;
            Object first = pair.getFirst();
            Intrinsics.checkNotNull(first);
            hashSet.add((String) first);
        }
        int i = idCount.element - 1;
        idCount.element = i;
        if (i <= 0) {
            this$0.mLibraryModelsSubject.onNext(null);
        }
    }
}
