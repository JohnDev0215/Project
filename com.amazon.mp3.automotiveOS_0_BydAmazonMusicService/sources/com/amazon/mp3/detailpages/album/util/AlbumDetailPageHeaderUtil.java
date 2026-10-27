package com.amazon.mp3.detailpages.album.util;

import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.baseviews.model.configuration.AlbumModelConfiguration;
import com.amazon.mp3.baseviews.model.configuration.ModelConfiguration;
import com.amazon.mp3.brush.converters.BrushConverterUtils;
import com.amazon.mp3.cache.AlbumCache;
import com.amazon.mp3.featuregate.Feature;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.mp3.util.extensions.ModelConfigurationKt;
import com.amazon.music.views.library.metadata.AlbumMetadata;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.models.PageHeaderActionIconModel;
import com.amazon.musicensembleservice.brush.Album;
import com.amazon.musicensembleservice.brush.Track;
import java.util.ArrayList;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AlbumDetailPageHeaderUtil.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J.\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\b\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u000fJ,\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\u0013\u001a\u00020\u00142\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00162\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0018J\u0014\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\u0013\u001a\u00020\u001aJ8\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u000f2\b\b\u0002\u0010\u001e\u001a\u00020\u000fJ\u001e\u0010\u001f\u001a\u00020\u000f2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020!0\n2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006\""}, d2 = {"Lcom/amazon/mp3/detailpages/album/util/AlbumDetailPageHeaderUtil;", "", "()V", "configuration", "Lcom/amazon/mp3/baseviews/model/configuration/AlbumModelConfiguration;", "getConfiguration", "()Lcom/amazon/mp3/baseviews/model/configuration/AlbumModelConfiguration;", "configuration$delegate", "Lkotlin/Lazy;", "generateAlbumDetailHeaderIcons", "", "Lcom/amazon/music/views/library/models/PageHeaderActionIconModel;", "asin", "", "isOwned", "", "isAddable", "isOffline", "generateCatalogAlbumDetailHeaderIcons", "album", "Lcom/amazon/musicensembleservice/brush/Album;", "modelConfiguration", "Lcom/amazon/mp3/baseviews/model/configuration/ModelConfiguration;", "cache", "Lcom/amazon/mp3/cache/AlbumCache;", "generateDownloadedAlbumDetailHeaderIcons", "Lcom/amazon/mp3/library/item/Album;", "generateVRAlbumDetailHeaderIcons", "albumMetadata", "Lcom/amazon/music/views/library/metadata/AlbumMetadata;", "isCommentaryEnabled", "shouldShowAddIcon", "tracks", "Lcom/amazon/musicensembleservice/brush/Track;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class AlbumDetailPageHeaderUtil {
    public static final AlbumDetailPageHeaderUtil INSTANCE = new AlbumDetailPageHeaderUtil();

    /* JADX INFO: renamed from: configuration$delegate, reason: from kotlin metadata */
    private static final Lazy configuration = LazyKt__LazyJVMKt.lazy(new Function0<AlbumModelConfiguration>() { // from class: com.amazon.mp3.detailpages.album.util.AlbumDetailPageHeaderUtil$configuration$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final AlbumModelConfiguration invoke() {
            return new AlbumModelConfiguration(null, null, 3, null);
        }
    });

    private AlbumDetailPageHeaderUtil() {
    }

    private final AlbumModelConfiguration getConfiguration() {
        return (AlbumModelConfiguration) configuration.getValue();
    }

    public final List<PageHeaderActionIconModel> generateCatalogAlbumDetailHeaderIcons(Album album, ModelConfiguration modelConfiguration, AlbumCache cache) {
        boolean zBooleanValue;
        Boolean isOwned;
        Intrinsics.checkNotNullParameter(album, "album");
        ContentMetadata contentMetadata = modelConfiguration == null ? null : ModelConfigurationKt.getContentMetadata(modelConfiguration);
        boolean z = contentMetadata instanceof AlbumMetadata;
        AlbumMetadata albumMetadata = z ? (AlbumMetadata) contentMetadata : null;
        Boolean boolValueOf = (albumMetadata == null ? null : albumMetadata.getIsFollowed()) != null ? Boolean.valueOf(!r3.booleanValue()) : null;
        if (boolValueOf != null) {
            zBooleanValue = boolValueOf.booleanValue();
        } else {
            List<Track> tracks = album.getTracks();
            Intrinsics.checkNotNullExpressionValue(tracks, "album.tracks");
            zBooleanValue = shouldShowAddIcon(tracks, cache);
        }
        boolean z2 = zBooleanValue;
        if (z) {
            isOwned = Boolean.valueOf(Intrinsics.areEqual(((AlbumMetadata) contentMetadata).getIsOwned(), Boolean.TRUE));
        } else {
            isOwned = album.isIsOwned();
        }
        if (Feature.SR_page_headers.isEnabled()) {
            AlbumMetadata albumMetadata2 = BrushConverterUtils.INSTANCE.getAlbumMetadata(album, modelConfiguration, cache);
            Intrinsics.checkNotNullExpressionValue(isOwned, "isOwned");
            return generateVRAlbumDetailHeaderIcons$default(this, albumMetadata2, isOwned.booleanValue(), z2, false, false, 16, null);
        }
        String asin = album.getAsin();
        Intrinsics.checkNotNullExpressionValue(isOwned, "isOwned");
        return generateAlbumDetailHeaderIcons(asin, isOwned.booleanValue(), z2, false);
    }

    public final List<PageHeaderActionIconModel> generateDownloadedAlbumDetailHeaderIcons(com.amazon.mp3.library.item.Album album) {
        Intrinsics.checkNotNullParameter(album, "album");
        if (!Feature.SR_page_headers.isEnabled()) {
            return generateAlbumDetailHeaderIcons(album.getAsin(), album.isAllOwned(), false, true);
        }
        AlbumMetadata albumMetadata = BrushConverterUtils.INSTANCE.getAlbumMetadata(album);
        Boolean isOwned = albumMetadata.getIsOwned();
        return generateVRAlbumDetailHeaderIcons$default(this, albumMetadata, isOwned == null ? album.isAllOwned() : isOwned.booleanValue(), false, true, false, 16, null);
    }

    public final List<PageHeaderActionIconModel> generateAlbumDetailHeaderIcons(String asin, boolean isOwned, boolean isAddable, boolean isOffline) {
        ArrayList arrayList = new ArrayList();
        if (asin == null || asin.length() == 0) {
            arrayList.add(new PageHeaderActionIconModel(PageHeaderActionIconModel.ActionType.PLAY, false, false, false, null, null, 62, null));
            arrayList.add(new PageHeaderActionIconModel(PageHeaderActionIconModel.ActionType.SHUFFLE, false, false, false, null, null, 62, null));
            return arrayList;
        }
        if (UserSubscriptionUtil.isNightwingOnly() && !isOwned) {
            arrayList.add(new PageHeaderActionIconModel(PageHeaderActionIconModel.ActionType.STATION, false, false, true, null, null, 54, null));
        } else {
            boolean zIsLikesEverywhereEnabled = AmazonApplication.getCapabilities().isLikesEverywhereEnabled();
            PageHeaderActionIconModel pageHeaderActionIconModel = new PageHeaderActionIconModel(PageHeaderActionIconModel.ActionType.FAVORITE, false, false, false, null, null, 62, null);
            PageHeaderActionIconModel pageHeaderActionIconModel2 = new PageHeaderActionIconModel(PageHeaderActionIconModel.ActionType.ADD, false, false, false, null, null, 62, null);
            arrayList.add(new PageHeaderActionIconModel(PageHeaderActionIconModel.ActionType.PLAY, false, false, false, null, null, 62, null));
            arrayList.add(new PageHeaderActionIconModel(PageHeaderActionIconModel.ActionType.SHUFFLE, false, false, false, null, null, 62, null));
            if (zIsLikesEverywhereEnabled) {
                if (!isOffline) {
                    arrayList.add(pageHeaderActionIconModel);
                }
            } else if (isAddable) {
                arrayList.add(pageHeaderActionIconModel2);
            }
            arrayList.add(new PageHeaderActionIconModel(PageHeaderActionIconModel.ActionType.DOWNLOAD, false, false, false, null, null, 62, null));
        }
        if (Feature.add_sharing_to_top_level_action.isEnabled()) {
            arrayList.add(new PageHeaderActionIconModel(PageHeaderActionIconModel.ActionType.SHARE, false, false, false, null, null, 62, null));
        }
        arrayList.add(new PageHeaderActionIconModel(PageHeaderActionIconModel.ActionType.OVERFLOW, false, false, false, null, null, 62, null));
        return arrayList;
    }

    public static /* synthetic */ List generateVRAlbumDetailHeaderIcons$default(AlbumDetailPageHeaderUtil albumDetailPageHeaderUtil, AlbumMetadata albumMetadata, boolean z, boolean z2, boolean z3, boolean z4, int i, Object obj) {
        if ((i & 16) != 0) {
            z4 = false;
        }
        return albumDetailPageHeaderUtil.generateVRAlbumDetailHeaderIcons(albumMetadata, z, z2, z3, z4);
    }

    public final List<PageHeaderActionIconModel> generateVRAlbumDetailHeaderIcons(AlbumMetadata albumMetadata, boolean isOwned, boolean isAddable, boolean isOffline, boolean isCommentaryEnabled) {
        ArrayList arrayList = new ArrayList();
        boolean z = false;
        boolean zShouldShowOnDemandCapability = albumMetadata == null ? false : INSTANCE.getConfiguration().shouldShowOnDemandCapability(albumMetadata);
        String asin = albumMetadata == null ? null : albumMetadata.getAsin();
        if (asin == null || asin.length() == 0) {
            arrayList.add(new PageHeaderActionIconModel(PageHeaderActionIconModel.ActionType.PLAY, false, false, false, null, null, 62, null));
            arrayList.add(new PageHeaderActionIconModel(PageHeaderActionIconModel.ActionType.SHUFFLE, false, false, false, null, null, 62, null));
            return arrayList;
        }
        boolean zIsNightwingOnly = UserSubscriptionUtil.getUserSubscription().isNightwingOnly();
        boolean zIsEnabled = Feature.add_sharing_to_top_level_action.isEnabled();
        if (Feature.supports_download_text_button.isEnabled() && !zIsEnabled) {
            z = true;
        }
        PageHeaderActionIconModel pageHeaderActionIconModel = new PageHeaderActionIconModel(PageHeaderActionIconModel.ActionType.FAVORITE, false, false, false, null, null, 62, null);
        PageHeaderActionIconModel pageHeaderActionIconModel2 = new PageHeaderActionIconModel(PageHeaderActionIconModel.ActionType.ADD, false, false, false, null, null, 62, null);
        PageHeaderActionIconModel pageHeaderActionIconModel3 = new PageHeaderActionIconModel(PageHeaderActionIconModel.ActionType.OVERFLOW, false, false, false, null, null, 62, null);
        PageHeaderActionIconModel pageHeaderActionIconModel4 = new PageHeaderActionIconModel(PageHeaderActionIconModel.ActionType.PLAY, false, false, false, null, null, 62, null);
        PageHeaderActionIconModel.ActionType actionType = PageHeaderActionIconModel.ActionType.DOWNLOAD;
        PageHeaderActionIconModel pageHeaderActionIconModel5 = new PageHeaderActionIconModel(actionType, false, false, true, null, null, 54, null);
        PageHeaderActionIconModel pageHeaderActionIconModel6 = new PageHeaderActionIconModel(actionType, false, false, false, null, null, 62, null);
        PageHeaderActionIconModel.ActionType actionType2 = PageHeaderActionIconModel.ActionType.SHUFFLE;
        PageHeaderActionIconModel pageHeaderActionIconModel7 = new PageHeaderActionIconModel(actionType2, !isCommentaryEnabled, false, false, null, null, 60, null);
        PageHeaderActionIconModel pageHeaderActionIconModel8 = new PageHeaderActionIconModel(actionType2, false, false, true, null, null, 54, null);
        PageHeaderActionIconModel pageHeaderActionIconModel9 = new PageHeaderActionIconModel(PageHeaderActionIconModel.ActionType.SHARE, false, false, false, null, null, 62, null);
        if (zIsNightwingOnly && !Feature.should_migrate_free_to_cq_for_non_station.isEnabled()) {
            if (isOwned) {
                arrayList.add(pageHeaderActionIconModel4);
                arrayList.add(pageHeaderActionIconModel5);
                arrayList.add(pageHeaderActionIconModel7);
            } else {
                arrayList.add(pageHeaderActionIconModel8);
            }
            if (zIsEnabled) {
                arrayList.add(pageHeaderActionIconModel9);
            }
            arrayList.add(pageHeaderActionIconModel3);
            return arrayList;
        }
        boolean zIsLikesEverywhereEnabled = AmazonApplication.getCapabilities().isLikesEverywhereEnabled();
        if (zShouldShowOnDemandCapability) {
            arrayList.add(pageHeaderActionIconModel4);
        } else {
            arrayList.add(new PageHeaderActionIconModel(actionType2, false, false, true, null, null, 54, null));
        }
        if (isOwned && z) {
            arrayList.add(pageHeaderActionIconModel5);
        }
        if (zShouldShowOnDemandCapability) {
            arrayList.add(pageHeaderActionIconModel7);
        }
        if (zIsLikesEverywhereEnabled) {
            if (!isOffline) {
                arrayList.add(pageHeaderActionIconModel);
            }
        } else if (isAddable) {
            arrayList.add(pageHeaderActionIconModel2);
        }
        if (zShouldShowOnDemandCapability && !z) {
            arrayList.add(pageHeaderActionIconModel6);
        }
        if (zIsEnabled) {
            arrayList.add(pageHeaderActionIconModel9);
        }
        arrayList.add(pageHeaderActionIconModel3);
        return arrayList;
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x0075  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x001f A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:41:? A[LOOP:0: B:16:0x0039->B:41:?, LOOP_END, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean shouldShowAddIcon(java.util.List<? extends com.amazon.musicensembleservice.brush.Track> r6, com.amazon.mp3.cache.AlbumCache r7) {
        /*
            r5 = this;
            java.lang.String r0 = "tracks"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
            com.amazon.mp3.featuregate.Feature r0 = com.amazon.mp3.featuregate.Feature.should_migrate_free_to_cq_for_non_station
            boolean r0 = r0.isEnabled()
            r1 = 0
            if (r0 == 0) goto L10
            return r1
        L10:
            r0 = 1
            if (r7 == 0) goto L21
            com.amazon.mp3.cache.BrushAlbumCacheHelper r2 = com.amazon.mp3.cache.BrushAlbumCacheHelper.INSTANCE
            int r6 = r6.size()
            boolean r6 = r2.checkIfCachedAlbumIsFullyAdded(r7, r6)
            if (r6 != 0) goto L79
        L1f:
            r1 = r0
            goto L79
        L21:
            com.amazon.mp3.library.item.factory.CirrusSourceLibraryItemFactory r7 = new com.amazon.mp3.library.item.factory.CirrusSourceLibraryItemFactory
            android.content.Context r2 = com.amazon.mp3.AmazonApplication.getContext()
            r7.<init>(r2)
            boolean r2 = r6 instanceof java.util.Collection
            if (r2 == 0) goto L35
            boolean r2 = r6.isEmpty()
            if (r2 == 0) goto L35
            goto L79
        L35:
            java.util.Iterator r6 = r6.iterator()
        L39:
            boolean r2 = r6.hasNext()
            if (r2 == 0) goto L79
            java.lang.Object r2 = r6.next()
            com.amazon.musicensembleservice.brush.Track r2 = (com.amazon.musicensembleservice.brush.Track) r2
            java.lang.String r3 = r2.getAsin()
            if (r3 == 0) goto L75
            java.util.List r2 = r2.getContentTiers()
            java.lang.String r4 = "it.contentTiers"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r4)
            boolean r2 = r2.isEmpty()
            r2 = r2 ^ r0
            if (r2 == 0) goto L75
            com.amazon.mp3.library.item.MusicTrack r2 = r7.getTrackForAsin(r3)
            if (r2 != 0) goto L63
        L61:
            r2 = r1
            goto L71
        L63:
            com.amazon.mp3.prime.ContentOwnershipStatus r2 = r2.getOwnershipStatus()
            if (r2 != 0) goto L6a
            goto L61
        L6a:
            boolean r2 = r2.isInLibrary()
            if (r2 != r0) goto L61
            r2 = r0
        L71:
            if (r2 != 0) goto L75
            r2 = r0
            goto L76
        L75:
            r2 = r1
        L76:
            if (r2 == 0) goto L39
            goto L1f
        L79:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.detailpages.album.util.AlbumDetailPageHeaderUtil.shouldShowAddIcon(java.util.List, com.amazon.mp3.cache.AlbumCache):boolean");
    }
}
