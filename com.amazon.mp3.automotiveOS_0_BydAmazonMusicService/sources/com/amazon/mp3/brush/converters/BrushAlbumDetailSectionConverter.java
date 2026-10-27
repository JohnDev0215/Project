package com.amazon.mp3.brush.converters;

import android.content.Context;
import com.amazon.mp3.baseviews.model.configuration.ModelConfiguration;
import com.amazon.mp3.baseviews.model.configuration.TrackModelConfiguration;
import com.amazon.mp3.cache.AlbumCache;
import com.amazon.mp3.detailpages.util.DiscHeaderIndexer;
import com.amazon.mp3.eligibility.CachedCapabilityActionProviderImpl;
import com.amazon.music.views.library.converter.BaseModelConverter;
import com.amazon.music.views.library.converter.SingleBaseModelConverter;
import com.amazon.music.views.library.models.DescriptiveRowItemModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.musicensembleservice.brush.Block;
import com.amazon.musicensembleservice.brush.Content;
import com.amazon.musicensembleservice.brush.Entity;
import com.amazon.musicensembleservice.brush.Track;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: BrushAlbumDetailSectionConverter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B)\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\u0018\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n2\u0006\u0010\f\u001a\u00020\rH\u0016J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\f\u001a\u00020\rH\u0016J\u001a\u0010\u0010\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J \u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\u0014H\u0002R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/amazon/mp3/brush/converters/BrushAlbumDetailSectionConverter;", "Lcom/amazon/mp3/brush/converters/BrushSectionGridConverter;", "modelConfiguration", "Lcom/amazon/mp3/baseviews/model/configuration/ModelConfiguration;", "cachedActionProviderImpl", "Lcom/amazon/mp3/eligibility/CachedCapabilityActionProviderImpl;", "albumCache", "Lcom/amazon/mp3/cache/AlbumCache;", "(Lcom/amazon/mp3/baseviews/model/configuration/ModelConfiguration;Lcom/amazon/mp3/eligibility/CachedCapabilityActionProviderImpl;Lcom/amazon/mp3/cache/AlbumCache;)V", "getChildrenModels", "", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "data", "Lcom/amazon/musicensembleservice/brush/Block;", "getTileType", "", "shouldCreateDiscHeaderModelForTrack", "discHeaderIndexer", "Lcom/amazon/mp3/detailpages/util/DiscHeaderIndexer;", "track", "Lcom/amazon/musicensembleservice/brush/Track;", "updateTrackModelTrackNumberForDiscHeaders", "", "trackModel", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class BrushAlbumDetailSectionConverter extends BrushSectionGridConverter {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final AlbumCache albumCache;
    private final CachedCapabilityActionProviderImpl cachedActionProviderImpl;
    private final ModelConfiguration modelConfiguration;

    public BrushAlbumDetailSectionConverter() {
        this(null, null, null, 7, null);
    }

    public /* synthetic */ BrushAlbumDetailSectionConverter(ModelConfiguration modelConfiguration, CachedCapabilityActionProviderImpl cachedCapabilityActionProviderImpl, AlbumCache albumCache, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : modelConfiguration, (i & 2) != 0 ? null : cachedCapabilityActionProviderImpl, (i & 4) != 0 ? null : albumCache);
    }

    public BrushAlbumDetailSectionConverter(ModelConfiguration modelConfiguration, CachedCapabilityActionProviderImpl cachedCapabilityActionProviderImpl, AlbumCache albumCache) {
        this.modelConfiguration = modelConfiguration;
        this.cachedActionProviderImpl = cachedCapabilityActionProviderImpl;
        this.albumCache = albumCache;
    }

    /* JADX INFO: compiled from: BrushAlbumDetailSectionConverter.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/amazon/mp3/brush/converters/BrushAlbumDetailSectionConverter$Companion;", "", "()V", "ALBUM_DETAIL_TRACKS_BLOCK_URI", "", "isAlbumDetailUseCase", "", "data", "Lcom/amazon/musicensembleservice/brush/Block;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final boolean isAlbumDetailUseCase(Block data) {
            Intrinsics.checkNotNullParameter(data, "data");
            if (data.getUri() == null) {
                return false;
            }
            String uri = data.getUri();
            Intrinsics.checkNotNullExpressionValue(uri, "data.uri");
            return StringsKt__StringsKt.contains$default((CharSequence) uri, (CharSequence) "album-tracks", false, 2, (Object) null) && Intrinsics.areEqual(data.getRenderingType(), "DESCRIPTIVE_LISTING");
        }
    }

    @Override // com.amazon.mp3.brush.converters.BrushSectionGridConverter
    public int getTileType(Block data) {
        Intrinsics.checkNotNullParameter(data, "data");
        if (Intrinsics.areEqual(data.getRenderingType(), "DESCRIPTIVE_LISTING")) {
            return 18;
        }
        return super.getTileType(data);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.mp3.brush.converters.BrushSectionGridConverter, com.amazon.music.views.library.converter.BaseContainerModelConverter
    public List<BaseViewModel> getChildrenModels(Block data) {
        BaseViewModel baseViewModelConvert;
        Intrinsics.checkNotNullParameter(data, "data");
        Content content = data.getContent();
        Context context = null;
        byte b = 0;
        List<Entity> entities = content == null ? null : content.getEntities();
        if (entities == null) {
            return null;
        }
        DiscHeaderIndexer discHeaderIndexerInitializeIndexerWithEntities = DiscHeaderIndexer.INSTANCE.initializeIndexerWithEntities(entities);
        ArrayList arrayList = new ArrayList(entities.size());
        BrushConverterFactory brushConverterFactory = BrushConverterFactory.INSTANCE;
        CachedCapabilityActionProviderImpl cachedCapabilityActionProviderImpl = this.cachedActionProviderImpl;
        if (cachedCapabilityActionProviderImpl == null) {
            cachedCapabilityActionProviderImpl = new CachedCapabilityActionProviderImpl(context, 1, b == true ? 1 : 0);
        }
        BaseModelConverter descriptiveRowItemConverter = brushConverterFactory.getDescriptiveRowItemConverter(new TrackModelConfiguration(cachedCapabilityActionProviderImpl), this.modelConfiguration, this.albumCache);
        SingleBaseModelConverter singleBaseModelConverter = descriptiveRowItemConverter instanceof SingleBaseModelConverter ? (SingleBaseModelConverter) descriptiveRowItemConverter : null;
        for (Entity entity : entities) {
            if (singleBaseModelConverter == null) {
                baseViewModelConvert = null;
            } else {
                Intrinsics.checkNotNullExpressionValue(entity, "entity");
                baseViewModelConvert = singleBaseModelConverter.convert(entity);
            }
            if (entity instanceof Track) {
                Track track = (Track) entity;
                BaseViewModel baseViewModelShouldCreateDiscHeaderModelForTrack = shouldCreateDiscHeaderModelForTrack(discHeaderIndexerInitializeIndexerWithEntities, track);
                if (baseViewModelShouldCreateDiscHeaderModelForTrack != null) {
                    arrayList.add(baseViewModelShouldCreateDiscHeaderModelForTrack);
                }
                if (baseViewModelConvert != null) {
                    updateTrackModelTrackNumberForDiscHeaders(discHeaderIndexerInitializeIndexerWithEntities, baseViewModelConvert, track);
                }
            }
            if (baseViewModelConvert != null) {
                arrayList.add(baseViewModelConvert);
            }
        }
        return arrayList;
    }

    private final BaseViewModel shouldCreateDiscHeaderModelForTrack(DiscHeaderIndexer discHeaderIndexer, Track track) {
        DiscHeaderIndexer.Disc discForTrackEntity = discHeaderIndexer.getDiscForTrackEntity(track);
        if (discForTrackEntity != null) {
            return new BrushDiscHeaderRowItemConverter().convert(discForTrackEntity);
        }
        return null;
    }

    private final void updateTrackModelTrackNumberForDiscHeaders(DiscHeaderIndexer discHeaderIndexer, BaseViewModel trackModel, Track track) {
        DiscHeaderIndexer.Disc disc;
        if (!(trackModel instanceof DescriptiveRowItemModel) || !discHeaderIndexer.hasDiscs() || track.getDiscNumber() == null || (disc = discHeaderIndexer.getDisc((int) track.getDiscNumber().longValue())) == null) {
            return;
        }
        DescriptiveRowItemModel descriptiveRowItemModel = (DescriptiveRowItemModel) trackModel;
        descriptiveRowItemModel.setRowNumber(descriptiveRowItemModel.getRowNumber() == null ? null : Integer.valueOf(((int) track.getTrackNumber().longValue()) - disc.getStartPosition()));
    }
}
