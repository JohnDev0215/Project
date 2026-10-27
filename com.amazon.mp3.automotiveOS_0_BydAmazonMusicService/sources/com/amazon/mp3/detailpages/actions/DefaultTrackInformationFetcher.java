package com.amazon.mp3.detailpages.actions;

import android.net.Uri;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.detailpages.actions.PlayCollectionAction;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.util.DbUtil;
import com.amazon.mp3.util.Log;
import com.amazon.music.views.library.metadata.AlbumMetadata;
import com.amazon.music.views.library.metadata.ArtistMetadata;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.metadata.PlaylistMetadata;
import com.amazon.music.views.library.metadata.UserPlaylistMetadata;
import com.amazon.music.views.library.metadata.VideoStoryMetadata;
import com.amazon.music.views.library.models.PageGridViewModel;
import com.amazon.music.views.library.models.PageHeaderModel;
import com.amazon.music.views.library.models.base.BaseViewContentModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import rx.Observable;
import rx.Subscriber;

/* JADX INFO: compiled from: DefaultTrackInformationFetcher.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\b\u0016\u0018\u0000 \"2\u00020\u0001:\u0001\"B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J3\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2#\u0010\u0010\u001a\u001f\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0015\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u0011H\u0016J\u0014\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\u000e0\u0017H\u0016J\u0014\u0010\u0019\u001a\u0004\u0018\u00010\u00182\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0014J\u0016\u0010\u001c\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00180\u00172\u0006\u0010\u001a\u001a\u00020\u001bJ\u0018\u0010\u001c\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00180\u00172\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\b\u0010\u001f\u001a\u00020\u001eH\u0016J\u0017\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00180\u000eH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010!R\u0014\u0010\u0002\u001a\u00020\u0003X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\f\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006#"}, d2 = {"Lcom/amazon/mp3/detailpages/actions/DefaultTrackInformationFetcher;", "Lcom/amazon/mp3/detailpages/actions/PlayCollectionAction$TrackInformationFetcher;", "modelFetcher", "Lcom/amazon/mp3/detailpages/actions/BaseViewModelFetcher;", "(Lcom/amazon/mp3/detailpages/actions/BaseViewModelFetcher;)V", "getModelFetcher", "()Lcom/amazon/mp3/detailpages/actions/BaseViewModelFetcher;", "pageModel", "Lcom/amazon/music/views/library/models/PageGridViewModel;", "getPageModel", "()Lcom/amazon/music/views/library/models/PageGridViewModel;", "setPageModel", "(Lcom/amazon/music/views/library/models/PageGridViewModel;)V", "allIds", "", "", "idRetriever", "Lkotlin/Function1;", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "Lkotlin/ParameterName;", "name", "currentModel", "getAllTracks", "Lrx/Observable;", "Lcom/amazon/mp3/library/item/MusicTrack;", "getMusicTrack", "uri", "Landroid/net/Uri;", "getTrack", "position", "", "getTrackTotalCount", "getTracks", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class DefaultTrackInformationFetcher implements PlayCollectionAction.TrackInformationFetcher {
    private static final String TAG = Reflection.getOrCreateKotlinClass(DefaultTrackInformationFetcher.class).getSimpleName();
    private final BaseViewModelFetcher modelFetcher;
    private PageGridViewModel pageModel;

    /* JADX INFO: renamed from: com.amazon.mp3.detailpages.actions.DefaultTrackInformationFetcher$getTracks$1, reason: invalid class name */
    /* JADX INFO: compiled from: DefaultTrackInformationFetcher.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.amazon.mp3.detailpages.actions.DefaultTrackInformationFetcher", f = "DefaultTrackInformationFetcher.kt", i = {0, 0, 0}, l = {70}, m = "getTracks", n = {"this", "trackList", "trackModel"}, s = {"L$0", "L$1", "L$3"})
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return DefaultTrackInformationFetcher.this.getTracks(this);
        }
    }

    public DefaultTrackInformationFetcher(BaseViewModelFetcher modelFetcher) {
        Intrinsics.checkNotNullParameter(modelFetcher, "modelFetcher");
        this.modelFetcher = modelFetcher;
    }

    @Override // com.amazon.mp3.detailpages.actions.PlayCollectionAction.TrackInformationFetcher
    public Observable<List<String>> fetchAllIds(Function1<? super BaseViewModel, String> function1) {
        return PlayCollectionAction.TrackInformationFetcher.DefaultImpls.fetchAllIds(this, function1);
    }

    @Override // com.amazon.mp3.detailpages.actions.PlayCollectionAction.TrackInformationFetcher
    public DbUtil.WhereClause getFilterByIds(List<String> list, String str) {
        return PlayCollectionAction.TrackInformationFetcher.DefaultImpls.getFilterByIds(this, list, str);
    }

    protected final BaseViewModelFetcher getModelFetcher() {
        return this.modelFetcher;
    }

    public final PageGridViewModel getPageModel() {
        return this.pageModel;
    }

    public final void setPageModel(PageGridViewModel pageGridViewModel) {
        this.pageModel = pageGridViewModel;
    }

    @Override // com.amazon.mp3.detailpages.actions.PlayCollectionAction.TrackInformationFetcher
    public Observable<List<MusicTrack>> getAllTracks() {
        Observable<List<MusicTrack>> observableCreate = Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.mp3.detailpages.actions.DefaultTrackInformationFetcher$$ExternalSyntheticLambda1
            @Override // rx.functions.Action1
            public final void call(Object obj) throws InterruptedException {
                DefaultTrackInformationFetcher.m799getAllTracks$lambda0(this.f$0, (Subscriber) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(observableCreate, "create { subscriber ->\n …)\n            }\n        }");
        return observableCreate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getAllTracks$lambda-0, reason: not valid java name */
    public static final void m799getAllTracks$lambda0(DefaultTrackInformationFetcher this$0, Subscriber subscriber) throws InterruptedException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BuildersKt__BuildersKt.runBlocking$default(null, new DefaultTrackInformationFetcher$getAllTracks$1$1(this$0, subscriber, null), 1, null);
    }

    @Override // com.amazon.mp3.detailpages.actions.PlayCollectionAction.TrackInformationFetcher
    public List<String> allIds(Function1<? super BaseViewModel, String> idRetriever) {
        Intrinsics.checkNotNullParameter(idRetriever, "idRetriever");
        ArrayList arrayList = new ArrayList();
        List<BaseViewModel> listFetchModels = this.modelFetcher.fetchModels(this.pageModel);
        if (listFetchModels == null) {
            listFetchModels = CollectionsKt__CollectionsKt.emptyList();
        }
        Iterator<BaseViewModel> it = listFetchModels.iterator();
        while (it.hasNext()) {
            String strInvoke = idRetriever.invoke(it.next());
            if (strInvoke != null) {
                arrayList.add(strInvoke);
            }
        }
        return CollectionsKt___CollectionsKt.toList(arrayList);
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0081  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:21:0x007a -> B:23:0x007d). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object getTracks(kotlin.coroutines.Continuation<? super java.util.List<? extends com.amazon.mp3.library.item.MusicTrack>> r8) {
        /*
            r7 = this;
            boolean r0 = r8 instanceof com.amazon.mp3.detailpages.actions.DefaultTrackInformationFetcher.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r8
            com.amazon.mp3.detailpages.actions.DefaultTrackInformationFetcher$getTracks$1 r0 = (com.amazon.mp3.detailpages.actions.DefaultTrackInformationFetcher.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.amazon.mp3.detailpages.actions.DefaultTrackInformationFetcher$getTracks$1 r0 = new com.amazon.mp3.detailpages.actions.DefaultTrackInformationFetcher$getTracks$1
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L41
            if (r2 != r3) goto L39
            java.lang.Object r2 = r0.L$3
            com.amazon.music.views.library.models.base.BaseViewModel r2 = (com.amazon.music.views.library.models.base.BaseViewModel) r2
            java.lang.Object r4 = r0.L$2
            java.util.Iterator r4 = (java.util.Iterator) r4
            java.lang.Object r5 = r0.L$1
            java.util.List r5 = (java.util.List) r5
            java.lang.Object r6 = r0.L$0
            com.amazon.mp3.detailpages.actions.DefaultTrackInformationFetcher r6 = (com.amazon.mp3.detailpages.actions.DefaultTrackInformationFetcher) r6
            kotlin.ResultKt.throwOnFailure(r8)
            goto L7d
        L39:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r0)
            throw r8
        L41:
            kotlin.ResultKt.throwOnFailure(r8)
            java.util.ArrayList r8 = new java.util.ArrayList
            r8.<init>()
            com.amazon.mp3.detailpages.actions.BaseViewModelFetcher r2 = r7.getModelFetcher()
            com.amazon.music.views.library.models.PageGridViewModel r4 = r7.getPageModel()
            java.util.List r2 = r2.fetchModels(r4)
            if (r2 != 0) goto L58
            goto La3
        L58:
            java.util.Iterator r2 = r2.iterator()
            r6 = r7
            r5 = r8
            r4 = r2
        L5f:
            boolean r8 = r4.hasNext()
            if (r8 == 0) goto La2
            java.lang.Object r8 = r4.next()
            r2 = r8
            com.amazon.music.views.library.models.base.BaseViewModel r2 = (com.amazon.music.views.library.models.base.BaseViewModel) r2
            r0.L$0 = r6
            r0.L$1 = r5
            r0.L$2 = r4
            r0.L$3 = r2
            r0.label = r3
            java.lang.Object r8 = kotlinx.coroutines.YieldKt.yield(r0)
            if (r8 != r1) goto L7d
            return r1
        L7d:
            boolean r8 = r2 instanceof com.amazon.music.views.library.models.base.BaseViewContentModel
            if (r8 == 0) goto L5f
            com.amazon.music.views.library.models.base.BaseViewContentModel r2 = (com.amazon.music.views.library.models.base.BaseViewContentModel) r2
            com.amazon.music.views.library.metadata.ContentMetadata r8 = r2.getMetadata()
            boolean r8 = r8 instanceof com.amazon.music.views.library.metadata.TrackMetadata
            if (r8 == 0) goto L5f
            com.amazon.music.views.library.metadata.ContentMetadata r8 = r2.getMetadata()
            android.net.Uri r8 = r8.getUri()
            com.amazon.mp3.library.item.MusicTrack r8 = r6.getMusicTrack(r8)
            if (r8 != 0) goto L9a
            goto L5f
        L9a:
            boolean r8 = r5.add(r8)
            kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r8)
            goto L5f
        La2:
            r8 = r5
        La3:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.detailpages.actions.DefaultTrackInformationFetcher.getTracks(kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // com.amazon.mp3.detailpages.actions.PlayCollectionAction.TrackInformationFetcher
    public Observable<MusicTrack> getTrack(int position) {
        BaseViewModel baseViewModel;
        ContentMetadata metadata;
        List<BaseViewModel> listFetchModels = this.modelFetcher.fetchModels(this.pageModel);
        if (listFetchModels != null) {
            if (position < listFetchModels.size() && position >= 0) {
                baseViewModel = listFetchModels.get(position);
                if (!(baseViewModel instanceof BaseViewContentModel)) {
                    Log.warning(TAG, "filterable item model is not a BaseViewContentModel type, return null");
                }
            } else {
                Log.warning(TAG, "invalid position: " + position + " for container with " + Integer.valueOf(listFetchModels.size()) + " filterable items, return null");
            }
            baseViewModel = null;
        } else {
            baseViewModel = null;
        }
        BaseViewContentModel baseViewContentModel = (BaseViewContentModel) baseViewModel;
        Uri uri = (baseViewContentModel == null || (metadata = baseViewContentModel.getMetadata()) == null) ? null : metadata.getUri();
        if (uri != null) {
            Log.debug(TAG, "retrieved track from position " + position + ", its uri is: " + uri);
            return getTrack(uri);
        }
        Log.warning(TAG, Intrinsics.stringPlus("Unable to find model for track in position ", Integer.valueOf(position)));
        Observable<MusicTrack> observableJust = Observable.just(null);
        Intrinsics.checkNotNullExpressionValue(observableJust, "{\n            Log.warnin…able.just(null)\n        }");
        return observableJust;
    }

    @Override // com.amazon.mp3.detailpages.actions.PlayCollectionAction.TrackInformationFetcher
    public int getTrackTotalCount() {
        List<BaseViewModel> models;
        PageGridViewModel pageGridViewModel = this.pageModel;
        if (pageGridViewModel == null || (models = pageGridViewModel.getModels()) == null) {
            return 0;
        }
        ArrayList arrayList = new ArrayList();
        for (Object obj : models) {
            if (obj instanceof PageHeaderModel) {
                arrayList.add(obj);
            }
        }
        PageHeaderModel pageHeaderModel = (PageHeaderModel) CollectionsKt___CollectionsKt.getOrNull(arrayList, 0);
        if (pageHeaderModel == null) {
            return 0;
        }
        ContentMetadata metadata = pageHeaderModel.getMetadata();
        Integer trackCount = null;
        if (metadata instanceof AlbumMetadata) {
            trackCount = ((AlbumMetadata) metadata).getTrackCount();
        } else if (metadata instanceof PlaylistMetadata) {
            trackCount = ((PlaylistMetadata) metadata).getTrackCount();
        } else if (metadata instanceof ArtistMetadata) {
            Long trackCount2 = ((ArtistMetadata) metadata).getTrackCount();
            if (trackCount2 != null) {
                trackCount = Integer.valueOf((int) trackCount2.longValue());
            }
        } else if (metadata instanceof UserPlaylistMetadata) {
            Long trackCount3 = ((UserPlaylistMetadata) metadata).getTrackCount();
            if (trackCount3 != null) {
                trackCount = Integer.valueOf((int) trackCount3.longValue());
            }
        } else {
            trackCount = metadata instanceof VideoStoryMetadata ? ((VideoStoryMetadata) metadata).getTrackCount() : 0;
        }
        if (trackCount == null) {
            return 0;
        }
        return trackCount.intValue();
    }

    public final Observable<MusicTrack> getTrack(final Uri uri) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        Observable<MusicTrack> observableCreate = Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.mp3.detailpages.actions.DefaultTrackInformationFetcher$$ExternalSyntheticLambda0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                DefaultTrackInformationFetcher.m800getTrack$lambda6(this.f$0, uri, (Subscriber) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(observableCreate, "create { subscriber ->\n …r.onCompleted()\n        }");
        return observableCreate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getTrack$lambda-6, reason: not valid java name */
    public static final void m800getTrack$lambda6(DefaultTrackInformationFetcher this$0, Uri uri, Subscriber subscriber) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(uri, "$uri");
        subscriber.onNext(this$0.getMusicTrack(uri));
        subscriber.onCompleted();
    }

    protected MusicTrack getMusicTrack(Uri uri) {
        return AmazonApplication.getLibraryItemFactory().getTrackIncludingPrimePlaylistTrack(uri);
    }
}
