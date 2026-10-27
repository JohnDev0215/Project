package com.amazon.mp3.catalog.fragment.viewmodel;

import android.app.Application;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelKt;
import com.amazon.mp3.featuregate.Feature;
import com.amazon.mp3.prime.CTAPrimeCache;
import com.amazon.mp3.prime.cta.CtaAlbumRequester;
import com.amazon.mp3.util.Log;
import com.amazon.music.metrics.mts.event.definition.latency.LatencyEventDescriptor;
import com.amazon.music.metrics.mts.event.definition.latency.LatencyTracker;
import com.amazon.music.metrics.mts.event.types.pagelatencyinfo.LatencyTrackingLeg;
import com.amazon.music.metrics.mts.event.types.pagelatencyinfo.PageLoadLatencyCode;
import com.amazon.music.views.library.models.MultiStateButtonModel;
import com.amazon.music.views.library.models.PageGridViewModel;
import com.amazon.music.views.library.models.TableRowItemModel;
import com.amazon.music.views.library.models.TextRowItemModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.music.views.library.models.base.Hideable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.SetsKt__SetsJVMKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;

/* JADX INFO: compiled from: AlbumBrushViewModel.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006*\u0002\u0013\u0016\u0018\u0000 :2\u00020\u0001:\u00029:Bc\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\b\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\u001a\u0010\u000b\u001a\u0016\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\r\u0018\u00010\f\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u000fJ\u0010\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020.H\u0016J\f\u0010/\u001a\b\u0012\u0004\u0012\u00020#0\"J\u001e\u00100\u001a\n\u0012\u0004\u0012\u000201\u0018\u00010\r2\f\u00102\u001a\b\u0012\u0004\u0012\u0002010\rH\u0002J\u0012\u00103\u001a\u00020,2\b\u00104\u001a\u0004\u0018\u000105H\u0002J\u001a\u00106\u001a\u00020,2\b\u00104\u001a\u0004\u0018\u0001052\b\b\u0002\u00107\u001a\u00020\bJ\u0006\u00108\u001a\u00020,R\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0010\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0014R\u0010\u0010\u0015\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0017R\u001a\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00198TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001cR\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u001e\u001a\u0004\u0018\u00010\u001fX\u0082\u000e¢\u0006\u0004\n\u0002\u0010 R\u0014\u0010!\u001a\b\u0012\u0004\u0012\u00020#0\"X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010%\u001a\u00020\b2\u0006\u0010$\u001a\u00020\b@BX\u0082\u000e¢\u0006\b\n\u0000\"\u0004\b&\u0010'R\u0014\u0010(\u001a\u00020\bX\u0094D¢\u0006\b\n\u0000\u001a\u0004\b)\u0010*¨\u0006;"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/viewmodel/AlbumBrushViewModel;", "Lcom/amazon/mp3/catalog/fragment/viewmodel/BrushViewModel;", "application", "Landroid/app/Application;", "uri", "", "nextPageToken", "isArtistUpsell", "", "supportRefinements", "activeFilterModelBrowseId", "refinementLists", "", "", "albumAsin", "(Landroid/app/Application;Ljava/lang/String;Ljava/lang/String;ZZLjava/lang/String;Ljava/util/Map;Ljava/lang/String;)V", "getAlbumAsin", "()Ljava/lang/String;", "albumLatencyEventListener", "com/amazon/mp3/catalog/fragment/viewmodel/AlbumBrushViewModel$albumLatencyEventListener$1", "Lcom/amazon/mp3/catalog/fragment/viewmodel/AlbumBrushViewModel$albumLatencyEventListener$1;", "albumMetadataChangeListener", "com/amazon/mp3/catalog/fragment/viewmodel/AlbumBrushViewModel$albumMetadataChangeListener$1", "Lcom/amazon/mp3/catalog/fragment/viewmodel/AlbumBrushViewModel$albumMetadataChangeListener$1;", "brushLatencyLegs", "", "Lcom/amazon/music/metrics/mts/event/types/pagelatencyinfo/LatencyTrackingLeg;", "getBrushLatencyLegs", "()Ljava/util/Set;", "changedAlbumAsin", "changedAlbumId", "", "Ljava/lang/Long;", "initialAlbumDataFetchStatus", "Landroidx/lifecycle/MutableLiveData;", "Lcom/amazon/mp3/catalog/fragment/viewmodel/AlbumBrushViewModel$AlbumDataFetchInfo;", "value", "isTracklistVisible", "setTracklistVisible", "(Z)V", "shouldPerformInitialFetch", "getShouldPerformInitialFetch", "()Z", "fetchInitPage", "", "pageSize", "", "getInitialAlbumDataFetchStatus", "getTracklistSection", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "models", "resetCopyrightVisibility", "pageModel", "Lcom/amazon/music/views/library/models/PageGridViewModel;", "setTracklistModelsVisibility", "hidden", "toggleTracklistVisibility", "AlbumDataFetchInfo", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class AlbumBrushViewModel extends BrushViewModel {
    private static final String TAG = AlbumBrushViewModel.class.getSimpleName();
    private final String albumAsin;
    private final AlbumBrushViewModel$albumLatencyEventListener$1 albumLatencyEventListener;
    private final AlbumBrushViewModel$albumMetadataChangeListener$1 albumMetadataChangeListener;
    private String changedAlbumAsin;
    private Long changedAlbumId;
    private final MutableLiveData<AlbumDataFetchInfo> initialAlbumDataFetchStatus;
    private boolean isTracklistVisible;
    private final boolean shouldPerformInitialFetch;

    public final String getAlbumAsin() {
        return this.albumAsin;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Type inference failed for: r2v2, types: [com.amazon.mp3.catalog.fragment.viewmodel.AlbumBrushViewModel$albumMetadataChangeListener$1] */
    /* JADX WARN: Type inference failed for: r2v3, types: [com.amazon.mp3.catalog.fragment.viewmodel.AlbumBrushViewModel$albumLatencyEventListener$1] */
    public AlbumBrushViewModel(Application application, String uri, String str, boolean z, boolean z2, String str2, Map<String, ? extends List<String>> map, String str3) {
        super(application, uri, str, z, z2, str2, map);
        Intrinsics.checkNotNullParameter(application, "application");
        Intrinsics.checkNotNullParameter(uri, "uri");
        this.albumAsin = str3;
        this.initialAlbumDataFetchStatus = new MutableLiveData<>();
        this.albumMetadataChangeListener = new CTAPrimeCache.AlbumMetadataChangeListener() { // from class: com.amazon.mp3.catalog.fragment.viewmodel.AlbumBrushViewModel$albumMetadataChangeListener$1
            @Override // com.amazon.mp3.prime.CTAPrimeCache.AlbumMetadataChangeListener
            public void onAlbumIdChanged(long albumId) {
                Log.debug(AlbumBrushViewModel.TAG, Intrinsics.stringPlus("album ID change has occurred, the new album ID: ", Long.valueOf(albumId)));
                this.this$0.changedAlbumId = Long.valueOf(albumId);
            }

            @Override // com.amazon.mp3.prime.CTAPrimeCache.AlbumMetadataChangeListener
            public void onAlbumAsinChanged(String albumAsin) {
                Intrinsics.checkNotNullParameter(albumAsin, "albumAsin");
                Log.debug(AlbumBrushViewModel.TAG, Intrinsics.stringPlus("album ASIN has changed, the new album ASIN is: ", albumAsin));
                this.this$0.changedAlbumAsin = albumAsin;
            }
        };
        this.albumLatencyEventListener = new CtaAlbumRequester.AlbumResponseListener() { // from class: com.amazon.mp3.catalog.fragment.viewmodel.AlbumBrushViewModel$albumLatencyEventListener$1
            @Override // com.amazon.mp3.prime.cta.CtaAlbumRequester.AlbumResponseListener
            public void onAlbumRequestComplete(String requestedAlbumAsin) {
                if (Feature.album_detail_telemetry_correction.isEnabled()) {
                    LatencyTracker.INSTANCE.track(new LatencyEventDescriptor(LatencyTrackingLeg.RESPONSE, PageLoadLatencyCode.ALBUM_DETAIL));
                    Log.debug(AlbumBrushViewModel.TAG, "Begin LatencyTrackingLeg.RESPONSE");
                }
            }
        };
    }

    /* JADX INFO: compiled from: AlbumBrushViewModel.kt */
    @Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\t\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\t\u001a\u00020\u0007\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0011¢\u0006\u0004\b\u0016\u0010\u0017J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0019\u0010\r\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R\u0019\u0010\u0012\u001a\u0004\u0018\u00010\u00118\u0006¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015¨\u0006\u0018"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/viewmodel/AlbumBrushViewModel$AlbumDataFetchInfo;", "", "", "toString", "", "hashCode", "other", "", "equals", "completed", "Z", "getCompleted", "()Z", "albumAsin", "Ljava/lang/String;", "getAlbumAsin", "()Ljava/lang/String;", "", "albumId", "Ljava/lang/Long;", "getAlbumId", "()Ljava/lang/Long;", "<init>", "(ZLjava/lang/String;Ljava/lang/Long;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
    public static final /* data */ class AlbumDataFetchInfo {
        private final String albumAsin;
        private final Long albumId;
        private final boolean completed;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof AlbumDataFetchInfo)) {
                return false;
            }
            AlbumDataFetchInfo albumDataFetchInfo = (AlbumDataFetchInfo) other;
            return this.completed == albumDataFetchInfo.completed && Intrinsics.areEqual(this.albumAsin, albumDataFetchInfo.albumAsin) && Intrinsics.areEqual(this.albumId, albumDataFetchInfo.albumId);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v1, types: [int] */
        /* JADX WARN: Type inference failed for: r0v6 */
        /* JADX WARN: Type inference failed for: r0v7 */
        public int hashCode() {
            boolean z = this.completed;
            ?? r0 = z;
            if (z) {
                r0 = 1;
            }
            int i = r0 * 31;
            String str = this.albumAsin;
            int iHashCode = (i + (str == null ? 0 : str.hashCode())) * 31;
            Long l = this.albumId;
            return iHashCode + (l != null ? l.hashCode() : 0);
        }

        public String toString() {
            return "AlbumDataFetchInfo(completed=" + this.completed + ", albumAsin=" + ((Object) this.albumAsin) + ", albumId=" + this.albumId + ')';
        }

        public AlbumDataFetchInfo(boolean z, String str, Long l) {
            this.completed = z;
            this.albumAsin = str;
            this.albumId = l;
        }

        public final String getAlbumAsin() {
            return this.albumAsin;
        }

        public final Long getAlbumId() {
            return this.albumId;
        }

        public final boolean getCompleted() {
            return this.completed;
        }
    }

    @Override // com.amazon.mp3.catalog.fragment.viewmodel.BrushViewModel
    protected boolean getShouldPerformInitialFetch() {
        return this.shouldPerformInitialFetch;
    }

    @Override // com.amazon.mp3.catalog.fragment.viewmodel.BrushViewModel
    protected Set<LatencyTrackingLeg> getBrushLatencyLegs() {
        if (Feature.album_detail_telemetry_correction.isEnabled()) {
            return SetsKt__SetsJVMKt.setOf(LatencyTrackingLeg.PROCESSING);
        }
        return super.getBrushLatencyLegs();
    }

    public final void toggleTracklistVisibility() {
        setTracklistVisible(!this.isTracklistVisible);
    }

    private final void setTracklistVisible(boolean z) {
        if (this.isTracklistVisible == z) {
            return;
        }
        this.isTracklistVisible = z;
        PageGridViewModel value = getPageModelLiveData().getValue();
        if (value == null) {
            return;
        }
        setTracklistModelsVisibility$default(this, value, false, 2, null);
        getPageModelLiveData().postValue(value);
    }

    public static /* synthetic */ void setTracklistModelsVisibility$default(AlbumBrushViewModel albumBrushViewModel, PageGridViewModel pageGridViewModel, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = !albumBrushViewModel.isTracklistVisible;
        }
        albumBrushViewModel.setTracklistModelsVisibility(pageGridViewModel, z);
    }

    public final void setTracklistModelsVisibility(PageGridViewModel pageModel, boolean hidden) {
        if (pageModel == null) {
            return;
        }
        List<BaseViewModel> tracklistSection = getTracklistSection(pageModel.getModels());
        if (tracklistSection != null) {
            ArrayList arrayList = new ArrayList();
            for (Object obj : tracklistSection) {
                if (obj instanceof Hideable) {
                    arrayList.add(obj);
                }
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ((Hideable) it.next()).updateVisibility(hidden);
            }
        }
        List<BaseViewModel> models = pageModel.getModels();
        ArrayList arrayList2 = new ArrayList();
        for (Object obj2 : models) {
            if (obj2 instanceof MultiStateButtonModel) {
                arrayList2.add(obj2);
            }
        }
        MultiStateButtonModel multiStateButtonModel = (MultiStateButtonModel) CollectionsKt___CollectionsKt.firstOrNull((List) arrayList2);
        if (multiStateButtonModel != null) {
            multiStateButtonModel.updateDisplayText(hidden);
        }
        resetCopyrightVisibility(pageModel);
    }

    private final void resetCopyrightVisibility(PageGridViewModel pageModel) {
        List<BaseViewModel> models;
        Object next;
        BaseViewModel baseViewModel;
        if (pageModel == null || (models = pageModel.getModels()) == null) {
            baseViewModel = null;
        } else {
            Iterator<T> it = models.iterator();
            while (true) {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
                List<String> tags = ((BaseViewModel) next).getTags();
                if (tags != null && tags.contains("COPYRIGHT")) {
                    break;
                }
            }
            baseViewModel = (BaseViewModel) next;
        }
        TextRowItemModel textRowItemModel = baseViewModel instanceof TextRowItemModel ? (TextRowItemModel) baseViewModel : null;
        if (textRowItemModel == null) {
            return;
        }
        textRowItemModel.updateVisibility(true);
        textRowItemModel.updateVisibility(false);
    }

    @Override // com.amazon.mp3.catalog.fragment.viewmodel.BrushViewModel, com.amazon.mp3.catalog.fragment.viewmodel.PageViewModelProvider
    public void fetchInitPage(int pageSize) {
        Log.debug(TAG, "fetching initial album data for Album Detail");
        setIsFetching(true);
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new AnonymousClass1(null), 2, null);
    }

    /* JADX INFO: renamed from: com.amazon.mp3.catalog.fragment.viewmodel.AlbumBrushViewModel$fetchInitPage$1, reason: invalid class name */
    /* JADX INFO: compiled from: AlbumBrushViewModel.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.mp3.catalog.fragment.viewmodel.AlbumBrushViewModel$fetchInitPage$1", f = "AlbumBrushViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return AlbumBrushViewModel.this.new AnonymousClass1(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            String albumAsin = AlbumBrushViewModel.this.getAlbumAsin();
            if (albumAsin != null) {
                AlbumBrushViewModel albumBrushViewModel = AlbumBrushViewModel.this;
                try {
                    new CtaAlbumRequester(albumBrushViewModel.getApplication().getApplicationContext(), false, albumBrushViewModel.albumMetadataChangeListener, albumBrushViewModel.albumLatencyEventListener).requestTracksForAsin(albumAsin);
                } catch (Exception unused) {
                    Log.error(AlbumBrushViewModel.TAG, "Error when requesting initial album data via Album Lookup");
                }
                albumBrushViewModel.initialAlbumDataFetchStatus.postValue(new AlbumDataFetchInfo(true, albumBrushViewModel.changedAlbumAsin, albumBrushViewModel.changedAlbumId));
            }
            return Unit.INSTANCE;
        }
    }

    public final MutableLiveData<AlbumDataFetchInfo> getInitialAlbumDataFetchStatus() {
        return this.initialAlbumDataFetchStatus;
    }

    private final List<BaseViewModel> getTracklistSection(List<? extends BaseViewModel> models) {
        int iNextIndex;
        Iterator<? extends BaseViewModel> it = models.iterator();
        int i = 0;
        while (true) {
            if (!it.hasNext()) {
                i = -1;
                break;
            }
            if (it.next() instanceof MultiStateButtonModel) {
                break;
            }
            i++;
        }
        ListIterator<? extends BaseViewModel> listIterator = models.listIterator(models.size());
        while (true) {
            if (!listIterator.hasPrevious()) {
                iNextIndex = -1;
                break;
            }
            if (listIterator.previous() instanceof TableRowItemModel) {
                iNextIndex = listIterator.nextIndex();
                break;
            }
        }
        if (i == -1 || iNextIndex == -1) {
            return null;
        }
        return models.subList(i + 1, iNextIndex + 1);
    }
}
