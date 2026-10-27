package com.amazon.mp3.library.provider;

import com.amazon.mp3.artist.view.PrimeItemsTransformationUtil;
import com.amazon.mp3.brush.converters.TrackDownloadStateModelConverter;
import com.amazon.mp3.playback.state.PageStateManager;
import com.amazon.mp3.prime.ContentOwnershipStatus;
import com.amazon.mp3.prime.browse.metadata.PrimeTrack;
import com.amazon.music.find.model.search.ContentOwnershipItem;
import com.amazon.music.find.model.search.SearchItem;
import com.amazon.music.views.library.metadata.AlbumMetadata;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.metadata.EligibilityMetadata;
import com.amazon.music.views.library.metadata.TrackMetadata;
import com.amazon.music.views.library.models.HorizontalTileModel;
import com.amazon.music.views.library.models.TableRowItemModel;
import com.amazon.music.views.library.models.base.BaseViewContentModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.music.views.library.models.base.ContentEligibilityData;
import com.amazon.music.views.library.models.base.Eligible;
import com.amazon.music.views.library.models.base.LibraryAddableModel;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ContentModelLibraryStateManager.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0090\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J8\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e0\f2\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u000e0\fH\u0002J\u0010\u0010\u0011\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\u0006\u0010\u0014\u001a\u00020\bJ\u0010\u0010\u0014\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0015H\u0002Ju\u0010\u0016\u001a\u00020\b2m\u0010\u0017\u001ai\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\r0\u0019¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001c\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u00100\u0019¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001d\u0012)\u0012'\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001f0\u0019\u0012\u0004\u0012\u00020\b0\u001ej\u0002` ¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(!\u0012\u0004\u0012\u00020\b0\u0018J\u0010\u0010\"\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020#H\u0002J\u0016\u0010$\u001a\u00020\b2\u0006\u0010\t\u001a\u00020%2\u0006\u0010&\u001a\u00020'J(\u0010(\u001a\u00020\b2\u0006\u0010)\u001a\u00020*2\u0006\u0010&\u001a\u00020'2\u0010\b\u0002\u0010!\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010+J(\u0010(\u001a\u00020\b2\u0006\u0010,\u001a\u00020\u00102\u0006\u0010&\u001a\u00020'2\u0010\b\u0002\u0010!\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010+J\u001e\u0010-\u001a\u00020.2\u0006\u0010\t\u001a\u00020\u00152\u0006\u0010,\u001a\u00020\u00102\u0006\u0010&\u001a\u00020'J(\u0010/\u001a\u00020\b2\u0006\u00100\u001a\u00020\u000e2\u0006\u00101\u001a\u00020.2\u0010\b\u0002\u0010!\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010+J(\u0010/\u001a\u00020\b2\u0006\u0010)\u001a\u00020*2\u0006\u00101\u001a\u00020.2\u0010\b\u0002\u0010!\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010+J \u00102\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u00100\u001a\u00020\u000e2\u0006\u00101\u001a\u00020.H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00063"}, d2 = {"Lcom/amazon/mp3/library/provider/ContentModelLibraryStateManager;", "", "pageStateManager", "Lcom/amazon/mp3/playback/state/PageStateManager;", "libraryStateProvider", "Lcom/amazon/mp3/library/provider/LibraryStateProvider;", "(Lcom/amazon/mp3/playback/state/PageStateManager;Lcom/amazon/mp3/library/provider/LibraryStateProvider;)V", "collectContentIdOrAsin", "", "model", "Lcom/amazon/music/views/library/models/base/BaseViewContentModel;", "mapIdToTrack", "", "", "Lcom/amazon/mp3/prime/browse/metadata/PrimeTrack;", "mapAsinToTrack", "", "requestAlbumState", "metadata", "Lcom/amazon/music/views/library/metadata/AlbumMetadata;", "requestLibraryState", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "requestLibraryStateInBatch", "fetchRequestStateHandler", "Lkotlin/Function3;", "", "Lkotlin/ParameterName;", "name", "trackIds", "trackAsins", "Lkotlin/Function1;", "Lcom/amazon/music/find/model/search/ContentOwnershipItem;", "Lcom/amazon/music/find/model/search/ContentOwnershipCallback;", "callback", "requestTrackState", "Lcom/amazon/music/views/library/metadata/TrackMetadata;", "updateEligibilityModel", "Lcom/amazon/music/views/library/models/base/Eligible;", "libraryStateData", "Lcom/amazon/mp3/library/provider/LibraryStateData;", "updateLibraryState", "item", "Lcom/amazon/music/find/model/search/SearchItem;", "Lkotlin/Function0;", "asin", "updateLibraryStateData", "", "updateLikeState", "track", "isLiked", "updateTrackLikeState", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ContentModelLibraryStateManager {
    private final LibraryStateProvider libraryStateProvider;
    private final PageStateManager pageStateManager;

    public ContentModelLibraryStateManager(PageStateManager pageStateManager, LibraryStateProvider libraryStateProvider) {
        Intrinsics.checkNotNullParameter(pageStateManager, "pageStateManager");
        Intrinsics.checkNotNullParameter(libraryStateProvider, "libraryStateProvider");
        this.pageStateManager = pageStateManager;
        this.libraryStateProvider = libraryStateProvider;
    }

    /* JADX INFO: renamed from: com.amazon.mp3.library.provider.ContentModelLibraryStateManager$requestLibraryState$1, reason: invalid class name */
    /* JADX INFO: compiled from: ContentModelLibraryStateManager.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<BaseViewModel, Unit> {
        AnonymousClass1(Object obj) {
            super(1, obj, ContentModelLibraryStateManager.class, "requestLibraryState", "requestLibraryState(Lcom/amazon/music/views/library/models/base/BaseViewModel;)V", 0);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(BaseViewModel baseViewModel) {
            invoke2(baseViewModel);
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(BaseViewModel p0) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            ((ContentModelLibraryStateManager) this.receiver).requestLibraryState(p0);
        }
    }

    public final void requestLibraryState() {
        this.pageStateManager.performActionPerModel(new AnonymousClass1(this));
    }

    public final void requestLibraryStateInBatch(Function3<? super List<Long>, ? super List<String>, ? super Function1<? super List<ContentOwnershipItem>, Unit>, Unit> fetchRequestStateHandler) {
        Intrinsics.checkNotNullParameter(fetchRequestStateHandler, "fetchRequestStateHandler");
        final LinkedHashMap linkedHashMap = new LinkedHashMap();
        final LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        this.pageStateManager.performActionPerContentModel(new Function1<BaseViewContentModel, Unit>() { // from class: com.amazon.mp3.library.provider.ContentModelLibraryStateManager.requestLibraryStateInBatch.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(BaseViewContentModel baseViewContentModel) {
                invoke2(baseViewContentModel);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(BaseViewContentModel model) {
                Intrinsics.checkNotNullParameter(model, "model");
                ContentModelLibraryStateManager.this.collectContentIdOrAsin(model, linkedHashMap, linkedHashMap2);
            }
        });
        fetchRequestStateHandler.invoke(CollectionsKt___CollectionsKt.toList(linkedHashMap.keySet()), CollectionsKt___CollectionsKt.toList(linkedHashMap2.keySet()), new Function1<List<? extends ContentOwnershipItem>, Unit>() { // from class: com.amazon.mp3.library.provider.ContentModelLibraryStateManager.requestLibraryStateInBatch.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(List<? extends ContentOwnershipItem> list) {
                invoke2((List<ContentOwnershipItem>) list);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(List<ContentOwnershipItem> contentOwnershipItems) {
                PrimeTrack primeTrack;
                Intrinsics.checkNotNullParameter(contentOwnershipItems, "contentOwnershipItems");
                Map<Long, PrimeTrack> map = linkedHashMap;
                Map<String, PrimeTrack> map2 = linkedHashMap2;
                ContentModelLibraryStateManager contentModelLibraryStateManager = this;
                for (ContentOwnershipItem contentOwnershipItem : contentOwnershipItems) {
                    Integer ownershipStatus = contentOwnershipItem.getOwnershipStatus();
                    if (ownershipStatus != null) {
                        int iIntValue = ownershipStatus.intValue();
                        Long id = contentOwnershipItem.getId();
                        if (id != null && map.containsKey(id)) {
                            primeTrack = map.get(id);
                        } else {
                            String asin = contentOwnershipItem.getAsin();
                            primeTrack = asin == null ? null : map2.get(asin);
                        }
                        PrimeTrack primeTrack2 = primeTrack;
                        ContentOwnershipStatus contentOwnershipStatusFromValue = ContentOwnershipStatus.fromValue(iIntValue);
                        if (primeTrack2 != null) {
                            ContentModelLibraryStateManager.updateLibraryState$default(contentModelLibraryStateManager, primeTrack2, new LibraryStateData(Boolean.valueOf(contentOwnershipStatusFromValue.isOwned()), Boolean.valueOf(contentOwnershipStatusFromValue.isInLibrary()), null, 4, null), (Function0) null, 4, (Object) null);
                        }
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void collectContentIdOrAsin(BaseViewContentModel model, Map<Long, PrimeTrack> mapIdToTrack, Map<String, PrimeTrack> mapAsinToTrack) {
        ContentMetadata metadata = model.getMetadata();
        if (metadata instanceof TrackMetadata) {
            PrimeTrack track = PrimeItemsTransformationUtil.convertToPrimeTrack((TrackMetadata) metadata);
            Long id = track.getId();
            String asin = track.getAsin();
            if (id != null) {
                Intrinsics.checkNotNullExpressionValue(track, "track");
                mapIdToTrack.put(id, track);
            } else if (asin != null) {
                Intrinsics.checkNotNullExpressionValue(track, "track");
                mapAsinToTrack.put(asin, track);
            }
        }
    }

    /* JADX INFO: renamed from: com.amazon.mp3.library.provider.ContentModelLibraryStateManager$updateLibraryState$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ContentModelLibraryStateManager.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    /* synthetic */ class C03691 extends FunctionReferenceImpl implements Function3<BaseViewModel, String, LibraryStateData, Boolean> {
        C03691(Object obj) {
            super(3, obj, ContentModelLibraryStateManager.class, "updateLibraryStateData", "updateLibraryStateData(Lcom/amazon/music/views/library/models/base/BaseViewModel;Ljava/lang/String;Lcom/amazon/mp3/library/provider/LibraryStateData;)Z", 0);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Boolean invoke(BaseViewModel p0, String p1, LibraryStateData p2) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            Intrinsics.checkNotNullParameter(p1, "p1");
            Intrinsics.checkNotNullParameter(p2, "p2");
            return Boolean.valueOf(((ContentModelLibraryStateManager) this.receiver).updateLibraryStateData(p0, p1, p2));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void updateLibraryState$default(ContentModelLibraryStateManager contentModelLibraryStateManager, String str, LibraryStateData libraryStateData, Function0 function0, int i, Object obj) {
        if ((i & 4) != 0) {
            function0 = null;
        }
        contentModelLibraryStateManager.updateLibraryState(str, libraryStateData, (Function0<Unit>) function0);
    }

    public final void updateLibraryState(String asin, LibraryStateData libraryStateData, Function0<Unit> callback) {
        Intrinsics.checkNotNullParameter(asin, "asin");
        Intrinsics.checkNotNullParameter(libraryStateData, "libraryStateData");
        this.pageStateManager.updateModelState(asin, libraryStateData, new C03691(this), callback);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void updateLikeState$default(ContentModelLibraryStateManager contentModelLibraryStateManager, SearchItem searchItem, boolean z, Function0 function0, int i, Object obj) {
        if ((i & 4) != 0) {
            function0 = null;
        }
        contentModelLibraryStateManager.updateLikeState(searchItem, z, (Function0<Unit>) function0);
    }

    public final void updateLikeState(SearchItem item, boolean isLiked, Function0<Unit> callback) {
        Intrinsics.checkNotNullParameter(item, "item");
        if (item instanceof PrimeTrack) {
            updateLikeState((PrimeTrack) item, isLiked, callback);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void updateLibraryState$default(ContentModelLibraryStateManager contentModelLibraryStateManager, SearchItem searchItem, LibraryStateData libraryStateData, Function0 function0, int i, Object obj) {
        if ((i & 4) != 0) {
            function0 = null;
        }
        contentModelLibraryStateManager.updateLibraryState(searchItem, libraryStateData, (Function0<Unit>) function0);
    }

    public final void updateLibraryState(SearchItem item, LibraryStateData libraryStateData, Function0<Unit> callback) {
        Intrinsics.checkNotNullParameter(item, "item");
        Intrinsics.checkNotNullParameter(libraryStateData, "libraryStateData");
        String asin = item.getAsin();
        if (asin == null) {
            return;
        }
        this.pageStateManager.updateModelState(asin, libraryStateData, new ContentModelLibraryStateManager$updateLibraryState$2$1(this), callback);
    }

    /* JADX INFO: renamed from: com.amazon.mp3.library.provider.ContentModelLibraryStateManager$updateLikeState$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ContentModelLibraryStateManager.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    /* synthetic */ class C03701 extends FunctionReferenceImpl implements Function3<BaseViewContentModel, PrimeTrack, Boolean, Unit> {
        C03701(Object obj) {
            super(3, obj, ContentModelLibraryStateManager.class, "updateTrackLikeState", "updateTrackLikeState(Lcom/amazon/music/views/library/models/base/BaseViewContentModel;Lcom/amazon/mp3/prime/browse/metadata/PrimeTrack;Z)V", 0);
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(BaseViewContentModel baseViewContentModel, PrimeTrack primeTrack, Boolean bool) {
            invoke(baseViewContentModel, primeTrack, bool.booleanValue());
            return Unit.INSTANCE;
        }

        public final void invoke(BaseViewContentModel p0, PrimeTrack p1, boolean z) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            Intrinsics.checkNotNullParameter(p1, "p1");
            ((ContentModelLibraryStateManager) this.receiver).updateTrackLikeState(p0, p1, z);
        }
    }

    public final void updateLikeState(PrimeTrack track, boolean isLiked, Function0<Unit> callback) {
        Intrinsics.checkNotNullParameter(track, "track");
        this.pageStateManager.updateContentModelState(track, Boolean.valueOf(isLiked), new C03701(this), callback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public final void requestLibraryState(BaseViewModel model) {
        ContentMetadata eligibilityMetadata;
        if (model instanceof BaseViewContentModel) {
            eligibilityMetadata = ((BaseViewContentModel) model).getMetadata();
        } else {
            eligibilityMetadata = model instanceof Eligible ? ((Eligible) model).getEligibilityMetadata() : null;
        }
        if (eligibilityMetadata == null) {
            return;
        }
        if (eligibilityMetadata instanceof TrackMetadata) {
            requestTrackState((TrackMetadata) eligibilityMetadata);
        } else if (eligibilityMetadata instanceof AlbumMetadata) {
            requestAlbumState((AlbumMetadata) eligibilityMetadata);
        }
    }

    private final void requestTrackState(TrackMetadata metadata) {
        this.libraryStateProvider.requestState(PrimeItemsTransformationUtil.convertToPrimeTrack(metadata));
    }

    private final void requestAlbumState(AlbumMetadata metadata) {
        this.libraryStateProvider.requestState(PrimeItemsTransformationUtil.convertToPrimeAlbum(metadata));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateTrackLikeState(BaseViewContentModel model, PrimeTrack track, boolean isLiked) {
        ContentMetadata metadata = model.getMetadata();
        if ((metadata instanceof TrackMetadata) && Intrinsics.areEqual(((TrackMetadata) metadata).getAsin(), track.getAsin()) && (model instanceof LibraryAddableModel)) {
            LibraryAddableModel libraryAddableModel = (LibraryAddableModel) model;
            if (libraryAddableModel.getIsLiked() != isLiked) {
                libraryAddableModel.setLiked(isLiked);
                libraryAddableModel.notifyChangedLibraryState();
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean updateLibraryStateData(BaseViewModel model, String asin, LibraryStateData libraryStateData) {
        Intrinsics.checkNotNullParameter(model, "model");
        Intrinsics.checkNotNullParameter(asin, "asin");
        Intrinsics.checkNotNullParameter(libraryStateData, "libraryStateData");
        if (!(model instanceof Eligible)) {
            return false;
        }
        Eligible eligible = (Eligible) model;
        EligibilityMetadata eligibilityMetadata = eligible.getEligibilityMetadata();
        if (eligibilityMetadata instanceof TrackMetadata) {
            TrackMetadata trackMetadata = (TrackMetadata) eligibilityMetadata;
            if (Intrinsics.areEqual(trackMetadata.getAsin(), asin)) {
                Boolean isInLibrary = libraryStateData.getIsInLibrary();
                if (isInLibrary != null) {
                    boolean zBooleanValue = isInLibrary.booleanValue();
                    if (model instanceof LibraryAddableModel) {
                        LibraryAddableModel.updateLibraryState$default((LibraryAddableModel) model, zBooleanValue, null, 2, null);
                    }
                    trackMetadata.setInLibrary(Boolean.valueOf(zBooleanValue));
                }
                Integer downloadState = libraryStateData.getDownloadState();
                if (downloadState != null) {
                    int iIntValue = downloadState.intValue();
                    if (model instanceof TableRowItemModel) {
                        ((TableRowItemModel) model).updateDownloadState(new TrackDownloadStateModelConverter().convert(Integer.valueOf(iIntValue)));
                    }
                    if (model instanceof HorizontalTileModel) {
                        ((HorizontalTileModel) model).updateDownloadState(new TrackDownloadStateModelConverter().convert(Integer.valueOf(iIntValue)));
                    }
                    trackMetadata.setDownloadState(Integer.valueOf(iIntValue));
                }
                updateEligibilityModel(eligible, libraryStateData);
                return true;
            }
        } else if (eligibilityMetadata instanceof AlbumMetadata) {
            AlbumMetadata albumMetadata = (AlbumMetadata) eligibilityMetadata;
            if (Intrinsics.areEqual(albumMetadata.getAsin(), asin)) {
                Boolean isInLibrary2 = libraryStateData.getIsInLibrary();
                if (isInLibrary2 != null) {
                    boolean zBooleanValue2 = isInLibrary2.booleanValue();
                    if (model instanceof LibraryAddableModel) {
                        LibraryAddableModel.updateLibraryState$default((LibraryAddableModel) model, zBooleanValue2, null, 2, null);
                    }
                    albumMetadata.setInLibrary(Boolean.valueOf(zBooleanValue2));
                }
                updateEligibilityModel(eligible, libraryStateData);
                return true;
            }
        }
        return false;
    }

    public final void updateEligibilityModel(Eligible model, LibraryStateData libraryStateData) {
        Intrinsics.checkNotNullParameter(model, "model");
        Intrinsics.checkNotNullParameter(libraryStateData, "libraryStateData");
        Boolean isOwned = libraryStateData.getIsOwned();
        Integer downloadState = libraryStateData.getDownloadState();
        model.updateEligibilityData(new ContentEligibilityData(isOwned, Boolean.valueOf(downloadState != null && downloadState.intValue() == 0), null, 4, null));
    }
}
