package com.amazon.mp3.catalog.fragment.datasource.dao;

import android.content.Context;
import android.net.Uri;
import com.amazon.mp3.catalog.fragment.converter.LibraryListPageConverter;
import com.amazon.mp3.catalog.fragment.converter.LibraryVisualRowItemConverter;
import com.amazon.mp3.detailpages.track.LibraryTracksViewModel;
import com.amazon.mp3.library.item.AbstractItem;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.item.TrackPlaceHolder;
import com.amazon.mp3.library.refinement.datasource.DatabaseSource;
import com.amazon.mp3.library.refinement.datasource.DatabaseSourcePaged;
import com.amazon.mp3.view.refinements.RefinementFilterType;
import com.amazon.music.metrics.mts.event.types.pagelatencyinfo.PageLoadLatencyCode;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.views.library.models.PageGridViewModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Deferred;
import kotlinx.coroutines.GlobalScope;
import org.objectweb.asm.commons.GeneratorAdapter;

/* JADX INFO: compiled from: LibraryTracksDao.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u0014\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u00030\u0001BC\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\n\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\u0010\b\u0002\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000e¢\u0006\u0002\u0010\u0010J\b\u0010'\u001a\u00020(H\u0016J\u001c\u0010)\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020*\u0018\u00010\u00142\u0006\u0010+\u001a\u00020\u0007J\b\u0010,\u001a\u00020 H\u0016R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010\u0013\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u0003\u0018\u00010\u0014X\u0094\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u000e\u0010\b\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010\u0017\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u0003\u0018\u00010\u0014X\u0094\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0094\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR,\u0010\u001d\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u001f\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020!0\u001eX\u0094\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010$\u001a\u00020\u00028TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b%\u0010&¨\u0006-"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/datasource/dao/LibraryTracksDao;", "Lcom/amazon/mp3/catalog/fragment/datasource/dao/RefinablePageViewModelDao;", "Lcom/amazon/mp3/library/item/MusicTrack;", "Lcom/amazon/mp3/library/refinement/datasource/DatabaseSource;", "context", "Landroid/content/Context;", "onlineUri", "Landroid/net/Uri;", "offlineUri", "pageType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "pageLoadLatencyCode", "Lcom/amazon/music/metrics/mts/event/types/pagelatencyinfo/PageLoadLatencyCode;", "defaultFilters", "", "Lcom/amazon/mp3/view/refinements/RefinementFilterType;", "(Landroid/content/Context;Landroid/net/Uri;Landroid/net/Uri;Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;Lcom/amazon/music/metrics/mts/event/types/pagelatencyinfo/PageLoadLatencyCode;Ljava/util/Set;)V", "contentConverter", "Lcom/amazon/mp3/catalog/fragment/converter/LibraryVisualRowItemConverter;", "offlineDataSource", "Lkotlinx/coroutines/Deferred;", "getOfflineDataSource", "()Lkotlinx/coroutines/Deferred;", "onlineDataSource", "getOnlineDataSource", "pageConverter", "Lcom/amazon/mp3/catalog/fragment/converter/LibraryListPageConverter;", "getPageLoadLatencyCode", "()Lcom/amazon/music/metrics/mts/event/types/pagelatencyinfo/PageLoadLatencyCode;", "pageModelConverter", "Lkotlin/Function2;", "", "", "Lcom/amazon/music/views/library/models/PageGridViewModel;", "getPageModelConverter", "()Lkotlin/jvm/functions/Function2;", "placeHolder", "getPlaceHolder", "()Lcom/amazon/mp3/library/item/MusicTrack;", "clearListeners", "", "getDataSourceForUri", "Lcom/amazon/mp3/library/refinement/datasource/DatabaseSourcePaged;", "uri", "getDefaultPageSize", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class LibraryTracksDao extends RefinablePageViewModelDao<MusicTrack, DatabaseSource<MusicTrack>> {
    private final LibraryVisualRowItemConverter contentConverter;
    private final Context context;
    private final Deferred<DatabaseSource<MusicTrack>> offlineDataSource;
    private final Uri offlineUri;
    private final Deferred<DatabaseSource<MusicTrack>> onlineDataSource;
    private final Uri onlineUri;
    private final LibraryListPageConverter pageConverter;
    private final PageLoadLatencyCode pageLoadLatencyCode;
    private final Function2<List<? extends MusicTrack>, Integer, PageGridViewModel> pageModelConverter;
    private final PageType pageType;

    @Override // com.amazon.mp3.catalog.fragment.datasource.dao.RefinablePageViewModelDao
    protected PageLoadLatencyCode getPageLoadLatencyCode() {
        return this.pageLoadLatencyCode;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LibraryTracksDao(Context context, Uri onlineUri, Uri offlineUri, PageType pageType, PageLoadLatencyCode pageLoadLatencyCode, Set<? extends RefinementFilterType> set) {
        Uri uri;
        Uri uri2;
        LibraryVisualRowItemConverter libraryVisualRowItemConverter;
        super(pageLoadLatencyCode, true, false, 4, null);
        Intrinsics.checkNotNullParameter(onlineUri, "onlineUri");
        Intrinsics.checkNotNullParameter(offlineUri, "offlineUri");
        Intrinsics.checkNotNullParameter(pageType, "pageType");
        this.context = context;
        this.onlineUri = onlineUri;
        this.offlineUri = offlineUri;
        this.pageType = pageType;
        this.pageLoadLatencyCode = pageLoadLatencyCode;
        if (set != null && set.contains(RefinementFilterType.DOWNLOADED)) {
            setUseOnlineSource(false);
        }
        if (context == null) {
            libraryVisualRowItemConverter = null;
            uri = offlineUri;
            uri2 = onlineUri;
        } else {
            uri = offlineUri;
            uri2 = onlineUri;
            libraryVisualRowItemConverter = new LibraryVisualRowItemConverter(context, null, null, false, false, null, null, GeneratorAdapter.AND, null);
        }
        this.contentConverter = libraryVisualRowItemConverter;
        this.pageConverter = new LibraryListPageConverter(context, libraryVisualRowItemConverter, true, set, Integer.valueOf(LibraryTracksViewModel.INSTANCE.getPAGE_SIZE()));
        this.pageModelConverter = new Function2<List<? extends MusicTrack>, Integer, PageGridViewModel>() { // from class: com.amazon.mp3.catalog.fragment.datasource.dao.LibraryTracksDao$pageModelConverter$1
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            /* JADX INFO: renamed from: invoke */
            public /* bridge */ /* synthetic */ PageGridViewModel mo4invoke(List<? extends MusicTrack> list, Integer num) {
                return invoke(list, num.intValue());
            }

            public final PageGridViewModel invoke(List<? extends MusicTrack> data, int i) {
                Intrinsics.checkNotNullParameter(data, "data");
                return this.this$0.pageConverter.convert2((List<? extends AbstractItem>) data, (List<? extends BaseViewModel>) this.this$0.pageConverter.getTracksChildrenModels(data, i));
            }
        };
        this.onlineDataSource = getDataSourceForUri(uri2);
        this.offlineDataSource = getDataSourceForUri(uri);
    }

    @Override // com.amazon.mp3.catalog.fragment.datasource.dao.RefinablePageViewModelDao
    protected Function2<List<? extends MusicTrack>, Integer, PageGridViewModel> getPageModelConverter() {
        return this.pageModelConverter;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.mp3.catalog.fragment.datasource.dao.RefinablePageViewModelDao
    public MusicTrack getPlaceHolder() {
        return TrackPlaceHolder.INSTANCE;
    }

    @Override // com.amazon.mp3.catalog.fragment.datasource.dao.RefinablePageViewModelDao
    protected Deferred<DatabaseSource<MusicTrack>> getOnlineDataSource() {
        return this.onlineDataSource;
    }

    @Override // com.amazon.mp3.catalog.fragment.datasource.dao.RefinablePageViewModelDao
    protected Deferred<DatabaseSource<MusicTrack>> getOfflineDataSource() {
        return this.offlineDataSource;
    }

    public final Deferred<DatabaseSourcePaged<MusicTrack>> getDataSourceForUri(Uri uri) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        if (this.context == null) {
            return null;
        }
        return BuildersKt__Builders_commonKt.async$default(GlobalScope.INSTANCE, null, null, new LibraryTracksDao$getDataSourceForUri$1$1(this, uri, null), 3, null);
    }

    @Override // com.amazon.mp3.catalog.fragment.datasource.dao.RefinablePageViewModelDao, com.amazon.mp3.catalog.fragment.datasource.dao.PageViewModelDao
    public int getDefaultPageSize() {
        return LibraryTracksViewModel.INSTANCE.getPAGE_SIZE();
    }

    /* JADX INFO: renamed from: com.amazon.mp3.catalog.fragment.datasource.dao.LibraryTracksDao$clearListeners$1, reason: invalid class name */
    /* JADX INFO: compiled from: LibraryTracksDao.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.mp3.catalog.fragment.datasource.dao.LibraryTracksDao$clearListeners$1", f = "LibraryTracksDao.kt", i = {}, l = {88, 89}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return LibraryTracksDao.this.new AnonymousClass1(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:19:0x003b  */
        /* JADX WARN: Removed duplicated region for block: B:20:0x003e  */
        /* JADX WARN: Removed duplicated region for block: B:23:0x0042  */
        /* JADX WARN: Removed duplicated region for block: B:26:0x004d  */
        /* JADX WARN: Removed duplicated region for block: B:27:0x004f  */
        /* JADX WARN: Removed duplicated region for block: B:33:0x005e  */
        /* JADX WARN: Removed duplicated region for block: B:36:0x0064  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r6) {
            /*
                r5 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r5.label
                r2 = 2
                r3 = 1
                r4 = 0
                if (r1 == 0) goto L1f
                if (r1 == r3) goto L1b
                if (r1 != r2) goto L13
                kotlin.ResultKt.throwOnFailure(r6)
                goto L58
            L13:
                java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r6.<init>(r0)
                throw r6
            L1b:
                kotlin.ResultKt.throwOnFailure(r6)
                goto L35
            L1f:
                kotlin.ResultKt.throwOnFailure(r6)
                com.amazon.mp3.catalog.fragment.datasource.dao.LibraryTracksDao r6 = com.amazon.mp3.catalog.fragment.datasource.dao.LibraryTracksDao.this
                kotlinx.coroutines.Deferred r6 = r6.getOnlineDataSource()
                if (r6 != 0) goto L2c
                r6 = r4
                goto L37
            L2c:
                r5.label = r3
                java.lang.Object r6 = r6.await(r5)
                if (r6 != r0) goto L35
                return r0
            L35:
                com.amazon.mp3.library.refinement.datasource.DatabaseSource r6 = (com.amazon.mp3.library.refinement.datasource.DatabaseSource) r6
            L37:
                boolean r1 = r6 instanceof com.amazon.mp3.library.refinement.datasource.DatabaseSourcePaged
                if (r1 == 0) goto L3e
                com.amazon.mp3.library.refinement.datasource.DatabaseSourcePaged r6 = (com.amazon.mp3.library.refinement.datasource.DatabaseSourcePaged) r6
                goto L3f
            L3e:
                r6 = r4
            L3f:
                if (r6 != 0) goto L42
                goto L45
            L42:
                r6.close()
            L45:
                com.amazon.mp3.catalog.fragment.datasource.dao.LibraryTracksDao r6 = com.amazon.mp3.catalog.fragment.datasource.dao.LibraryTracksDao.this
                kotlinx.coroutines.Deferred r6 = r6.getOfflineDataSource()
                if (r6 != 0) goto L4f
                r6 = r4
                goto L5a
            L4f:
                r5.label = r2
                java.lang.Object r6 = r6.await(r5)
                if (r6 != r0) goto L58
                return r0
            L58:
                com.amazon.mp3.library.refinement.datasource.DatabaseSource r6 = (com.amazon.mp3.library.refinement.datasource.DatabaseSource) r6
            L5a:
                boolean r0 = r6 instanceof com.amazon.mp3.library.refinement.datasource.DatabaseSourcePaged
                if (r0 == 0) goto L61
                r4 = r6
                com.amazon.mp3.library.refinement.datasource.DatabaseSourcePaged r4 = (com.amazon.mp3.library.refinement.datasource.DatabaseSourcePaged) r4
            L61:
                if (r4 != 0) goto L64
                goto L67
            L64:
                r4.close()
            L67:
                kotlin.Unit r6 = kotlin.Unit.INSTANCE
                return r6
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.catalog.fragment.datasource.dao.LibraryTracksDao.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    @Override // com.amazon.mp3.catalog.fragment.datasource.dao.RefinablePageViewModelDao
    public void clearListeners() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new AnonymousClass1(null), 3, null);
    }
}
