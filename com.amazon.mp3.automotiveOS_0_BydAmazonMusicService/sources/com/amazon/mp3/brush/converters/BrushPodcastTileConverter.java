package com.amazon.mp3.brush.converters;

import android.net.Uri;
import com.amazon.music.views.library.converter.SingleBaseModelConverter;
import com.amazon.music.views.library.metadata.PodcastMetadata;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.musicensembleservice.brush.Entity;
import com.amazon.musicensembleservice.brush.PodcastEpisode;
import com.amazon.musicensembleservice.brush.PodcastShow;
import com.amazon.podcast.deeplinks.Deeplinks;
import com.amazon.podcast.views.verticalItemView.VerticalItemModel;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BrushPodcastTileConverter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u000e2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u000eB\u0007¢\u0006\u0004\b\f\u0010\rJ\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0004\u001a\u00020\u0003H\u0002J\u0012\u0010\b\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0004\u001a\u00020\u0007H\u0002J\u0012\u0010\u000b\u001a\u0004\u0018\u00010\n2\u0006\u0010\t\u001a\u00020\u0002H\u0016¨\u0006\u000f"}, d2 = {"Lcom/amazon/mp3/brush/converters/BrushPodcastTileConverter;", "Lcom/amazon/music/views/library/converter/SingleBaseModelConverter;", "Lcom/amazon/musicensembleservice/brush/Entity;", "Lcom/amazon/musicensembleservice/brush/PodcastShow;", "item", "Lcom/amazon/podcast/views/verticalItemView/VerticalItemModel;", "convertPodcastShow", "Lcom/amazon/musicensembleservice/brush/PodcastEpisode;", "convertPodcastEpisode", "data", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "convert", "<init>", "()V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class BrushPodcastTileConverter implements SingleBaseModelConverter<Entity> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    @Override // com.amazon.music.views.library.converter.SingleBaseModelConverter
    public BaseViewModel convert(Entity data) {
        Intrinsics.checkNotNullParameter(data, "data");
        if (data instanceof PodcastShow) {
            return convertPodcastShow((PodcastShow) data);
        }
        if (data instanceof PodcastEpisode) {
            return convertPodcastEpisode((PodcastEpisode) data);
        }
        return null;
    }

    private final VerticalItemModel convertPodcastShow(PodcastShow item) {
        String strPodcast = Deeplinks.podcast(item.getCatalogId(), item.getTitle());
        String blockRef = item.getBlockRef();
        Uri EMPTY = Uri.EMPTY;
        Intrinsics.checkNotNullExpressionValue(EMPTY, "EMPTY");
        List listEmptyList = CollectionsKt__CollectionsKt.emptyList();
        String catalogId = item.getCatalogId();
        Intrinsics.checkNotNullExpressionValue(catalogId, "item.catalogId");
        return new VerticalItemModel(item.getBlockRef(), new PodcastMetadata(blockRef, EMPTY, listEmptyList, catalogId, "", strPodcast, "", "", "", item.getCatalogId(), null, 1024, null), 12, item.getCatalogId(), "", item.getTitle(), item.getSecondaryTitle(), 0L, item.getImage().getUrl(), false, "", item.getPodcastShowVariantId(), item.getAvailableUpsells(), item.getPlaybackMode(), item.getContentTraits(), "", false);
    }

    private final VerticalItemModel convertPodcastEpisode(PodcastEpisode item) {
        String strEpisode = Deeplinks.episode(item.getPodcastShowCatalogId(), item.getCatalogId(), item.getPodcastShowTitle(), item.getTitle());
        String blockRef = item.getBlockRef();
        Uri EMPTY = Uri.EMPTY;
        Intrinsics.checkNotNullExpressionValue(EMPTY, "EMPTY");
        List listEmptyList = CollectionsKt__CollectionsKt.emptyList();
        String catalogId = item.getCatalogId();
        Intrinsics.checkNotNullExpressionValue(catalogId, "item.catalogId");
        PodcastMetadata podcastMetadata = new PodcastMetadata(blockRef, EMPTY, listEmptyList, catalogId, "", strEpisode, "", "", "", item.getCatalogId(), null, 1024, null);
        String blockRef2 = item.getBlockRef();
        String catalogId2 = item.getCatalogId();
        String podcastShowCatalogId = item.getPodcastShowCatalogId();
        String title = item.getTitle();
        String secondaryTitle = item.getSecondaryTitle();
        Long durationSeconds = item.getDurationSeconds();
        Intrinsics.checkNotNullExpressionValue(durationSeconds, "item.durationSeconds");
        return new VerticalItemModel(blockRef2, podcastMetadata, 11, catalogId2, podcastShowCatalogId, title, secondaryTitle, durationSeconds.longValue(), item.getImage().getUrl(), false, item.getPodcastEpisodeVariantId(), item.getPodcastShowVariantId(), item.getAvailableUpsells(), item.getPlaybackMode(), item.getContentTraits(), item.getAvailabilityDate(), false);
    }

    /* JADX INFO: compiled from: BrushPodcastTileConverter.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0005\u001a\u00020\u00062\u000e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/amazon/mp3/brush/converters/BrushPodcastTileConverter$Companion;", "", "()V", "PODCAST_VIEW_TAG", "", "containsPodcastViewTag", "", "tags", "", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final boolean containsPodcastViewTag(List<String> tags) {
            if (tags == null) {
                return false;
            }
            return tags.contains("podcast-view");
        }
    }
}
